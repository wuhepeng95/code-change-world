package i.am.whp.lock.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 最好再销毁的时候调用{@link Leader#releaseLeaderShip()}，以便其他Client更快的成为Leader
 */
public abstract class Leader {
    private Logger logger = LoggerFactory.getLogger(Leader.class);

    private boolean isLeaderExecutable = true;

    public int getMinIntervalMS() {
        return 60000;
    }

    public abstract String getResourcePath();

    public void stateChanged(CuratorFramework client, ConnectionState newState) {
        logger.info("{}. leader status to " + newState, this.getClass().getCanonicalName());
        if (newState == ConnectionState.CONNECTED || newState == ConnectionState.RECONNECTED) {
            this.success();
        } else {
            this.broke();
        }
    }

    public void takeLeadership(CuratorFramework arg0) {
        while (isLeaderExecutable) {
            Long start = System.currentTimeMillis();
            try {
                this.execute();
            } catch (Exception e) {
                logger.error(String.format("path %s execute error.", this.getResourcePath()), e);
            }
            Long end = System.currentTimeMillis();

            long time = end - start;
            if (this.getMinIntervalMS() > time) {
                try {
                    Thread.sleep(this.getMinIntervalMS() - time);
                } catch (InterruptedException e) {
                    logger.warn("sleep is error", e);
                }
            }
        }
        this.endLeader();
    }

    public boolean isLeaderExecutable() {
        return isLeaderExecutable;
    }

    public void setLeaderExecutable(boolean leaderExecutable) {
        isLeaderExecutable = leaderExecutable;
    }

    public abstract void execute();

    public void endLeader() {
    }

    /**
     * 只有变化的是有才调用
     */
    public void broke() {
        isLeaderExecutable = false;
        logger.error("{} leader status detached! ", this.getClass().getCanonicalName());
    }

    /**
     * 释放Leader
     */
    public void releaseLeaderShip() {
        isLeaderExecutable = false;
        logger.info("{} leader status detached! ", this.getClass().getCanonicalName());
    }

    /**
     * 只有变化的是有才调用
     */
    public void success() {
        isLeaderExecutable = true;
        logger.info("{} granted leader!", this.getClass().getCanonicalName());
    }

}
