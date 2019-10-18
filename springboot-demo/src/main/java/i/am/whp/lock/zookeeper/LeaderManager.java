package i.am.whp.lock.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
class LeaderManager {
    private final static Logger logger = LoggerFactory.getLogger(LeaderManager.class);
    private final Client client;
    private final Collection<Leader> leaders;

    public LeaderManager(Client client, Collection<Leader> leaders) {
        this.client = client;
        this.leaders = leaders;
    }

    private List<LeaderSelector> selectors;

    public void start() {
        logger.info("get leader class {}", leaders.size());
        selectors = new ArrayList<>(leaders.size());
        String parentLeaderPath = client.getLeaderParentPath();
        if (StringUtils.isEmpty(parentLeaderPath)) {
            // 兼容老代码
            logger.warn("no leader parent path declared, using lock path:{}", client.getLockParentPath());
            parentLeaderPath = client.getLockParentPath();
        }
        for (Leader leader : this.leaders) {
            String path = Path.combine(parentLeaderPath, leader.getResourcePath());
            LeaderSelector leaderSelector = new LeaderSelector( client.getClient(), path, new LeadImp(leader));
            leaderSelector.autoRequeue();
            leaderSelector.start();
            selectors.add(leaderSelector);
        }
    }

    public void stop() {
        for (LeaderSelector s : selectors) {
            try {
                s.close();
            } catch (Exception e) {
                logger.error("select close error", e);
            }

        }
    }

    private static class LeadImp extends LeaderSelectorListenerAdapter {
        private Leader leader;

        public LeadImp(Leader leader) {
            this.leader = leader;
        }

        @Override
        public void takeLeadership(CuratorFramework client) throws Exception {
            this.leader.takeLeadership(client);
        }

        @Override
        public void stateChanged(CuratorFramework client, ConnectionState newState) {
            this.leader.stateChanged(client, newState);
            super.stateChanged(client, newState);
        }

    }

}
