package i.am.whp.lock.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NotEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class Client implements ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(Client.class);

    private final String zkConnectString;
    /**
     * locks
     */
    private final String lockParentPath;
    /**
     * leaders目录, 如果空，临时用lockParentPath替换，建议配置
     */
    private String leaderParentPath;
    private CuratorFramework client;
    private LeaderManager leaderManager;

    public CuratorFramework getClient() {
        return client;
    }

    public Client(String zkConnectString, String lockParentPath) {
        this.zkConnectString = zkConnectString;
        this.lockParentPath = lockParentPath;
    }

    public String getLeaderParentPath() {
        return leaderParentPath;
    }

    public void setLeaderParentPath(String leaderParentPath) {
        this.leaderParentPath = leaderParentPath;
    }

    public String getLockParentPath() {
        return lockParentPath;
    }

    /**
     * @param mutex
     * @return
     */
    public <T> T lock(Mutex<T> mutex) {
        String path = Path.combine(this.getLockParentPath(), mutex.getResourcePath());
        InterProcessMutex lock = new InterProcessMutex(this.getClient(), path);
        boolean success = false;
        try {
            try {
                success = lock.acquire(mutex.getTimeout(), mutex.getTimeUnit());
            } catch (Exception e) {
                throw new RuntimeException(String.format("resource path %s lock error.", path), e);
            }
            if (success) {
                return (T) mutex.execute();
            } else {
                return null;
            }
        } finally {
            try {
                if (success) {
                    lock.release();
                }
            } catch (Exception e) {
                logger.error("release lock failed when path is:{}", path, e);
            }
            try {
                if (this.client.getChildren().forPath(path).isEmpty()) {
                    getClient().delete().guaranteed().forPath(path);
                }
            } catch (NoNodeException e) {
                logger.debug("delete path error for no node. path is:{}, ignore it", path, e);
            } catch (NotEmptyException e) {
                logger.warn("delete path error for not empty. path is:{}, ignore it", path, e);
            } catch (Exception e) {
                logger.error("delete path error. path is:" + path, e);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private ApplicationContext applicationContext;

    public void start() {
        logger.info("mutex Invoker Proxy init.");
        this.client = CuratorFrameworkFactory.builder()
                .connectString(this.zkConnectString)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        this.client.start();
        if (applicationContext != null) {
            Map<String, Leader> leaders = applicationContext.getBeansOfType(Leader.class);
            if (leaders != null && leaders.size() > 0) {
                this.leaderManager = new LeaderManager(this, leaders.values());
                this.leaderManager.start();
            }
        }
        logger.info("mutex Invoker Proxy inited.");
    }

    public void stop() {
        try {
            if (this.leaderManager != null) {
                this.leaderManager.stop();
            }
        } catch (Exception e) {
            logger.error("stop leaderManager error", e);
        }

        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception e) {
            logger.error("stop zookeeper client error", e);
        }

    }

}
