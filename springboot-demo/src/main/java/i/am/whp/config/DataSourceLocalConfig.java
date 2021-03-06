package i.am.whp.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static i.am.whp.config.DataSourceLocalConfig.SQL_SESSION_FACTORY;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = DataSourceLocalConfig.PACKAGE_PATH, sqlSessionFactoryRef = SQL_SESSION_FACTORY)
public class DataSourceLocalConfig {

    public static final String PACKAGE_PATH = "i.am.whp.mapper.local";
    public static final String SQL_SESSION_FACTORY = "localSqlSessionFactory";
    public static final String DATA_SOURCE_NAME = "local";
    // public static final String TX_MANAGER = ConfigInFile.TRANSACTION_MANAGER_TA;

    @Value("${mysql.driver}")
    private String driver;
    @Value("${local.mysql.url}")
    private String url;
    @Value("${local.mysql.username}")
    private String username;
    @Value("${local.mysql.password}")
    private String password;

    @Bean(name = DATA_SOURCE_NAME)
    public DataSource getDataSource() {
        return DataSourceBuilder.create().driverClassName(driver).url(url).username(username).password(password).build();
    }

    @Bean(name = SQL_SESSION_FACTORY)
    /**
     * 使用声明的数据源，创建sqlSession工厂，当mybatis采用映射配置文件的方式时，指明该数据源需要是扫描的xml文件路径
     */
    public SqlSessionFactory testSqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
