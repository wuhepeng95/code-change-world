package i.am.whp.config;

import i.am.whp.lock.zookeeper.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuhepeng
 * @date 2019/10/16
 */
@Configuration
public class CommonConfig {

    @Value("${zookeeper.connect.string}")
    private String zookeeperConnectString;

    @Value("${zookeeper.locks.parent.path}")
    private String serverParentPath;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Client zookeeperClient() {
        return new Client(zookeeperConnectString, serverParentPath);
    }
}
