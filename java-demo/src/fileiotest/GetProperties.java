package fileiotest;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by whp on 2018/9/17
 */
public class GetProperties {
    private static String USERNAME;
    private static String PASSWORD;
    private static String JDBCDRIVER;
    private static String ORACLEURL;

    static {
        Properties properties = new Properties();
        try {
            properties.load(GetProperties.class.getResourceAsStream("oracle.properties"));//src相对根路径
            USERNAME = properties.getProperty("USERNAME");
            PASSWORD = properties.getProperty("PASSWORD");
            JDBCDRIVER = properties.getProperty("JDBCDRIVER");
            ORACLEURL = properties.getProperty("ORACLEURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
