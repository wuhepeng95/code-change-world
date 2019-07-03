package i.am.whp.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by baixiaoxuan on 2017/5/23.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = DataSourceConfig.PACKAGE_PATH)
public class DataSourceConfig {

    public static final String PACKAGE_PATH = "i.am.whp.mapper";
    public static final String SQL_SESSION_FACTORY = "sqlTaSessionFactory";
    public static final String DATA_SOURCE_NAME = "qq";
    // public static final String TX_MANAGER = ConfigInFile.TRANSACTION_MANAGER_TA;

    @Value("${mysql.driver}")
    private String driver;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;

    @Bean(name = DATA_SOURCE_NAME)
    public DataSource getDataSource() {
        return DataSourceBuilder.create().driverClassName(driver).url(url).username(username).password(password).build();
    }

//    @Bean(name = SQL_SESSION_FACTORY)
//    public SqlSessionFactory getSqlSessionFactoryData(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
//        return new SqlSessionManager().;
//    }
//
//
//    tatic final String WALL_CONFIG = "wallConfig";
//    @Bean
//    @DependsOn(WALL_CONFIG)
//    public WallFilter wallFilter(){
//        WallFilter wallFilter = new WallFilter();
//        wallFilter.setConfig(wallConfig());
//        return wallFilter;
//    }
//    @Bean(WALL_CONFIG)
//    public WallConfig wallConfig(){
//        WallConfig wallConfig = new WallConfig();
//        wallConfig.setMultiStatementAllow(true);
//        wallConfig.setNoneBaseStatementAllow(true);
//        return wallConfig;
//    }
//    @Bean(name = TX_MANAGER)
//    public DataSourceTransactionManager getTransactionManager(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
//        return MybatisDataSourceConfigHelper.getTransactionManager(dataSource);
//    }
//
//    @Bean(name = SQL_SESSION_FACTORY)
//    public SqlSessionFactory getSqlSessionFactoryData(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
//        return MybatisDataSourceConfigHelper.getSqlSessionFactoryData(Collections.<String>emptyList(), mybatisConfigPath, dataSource);
//    }

}
