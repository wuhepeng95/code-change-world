package i.am.whp.config;

import i.am.whp.test.MyBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Value("${app.name}")
    public String name;

    @Bean
    public MyBean setMyServiceFiled() {
        MyBean myBean = new MyBean();
        myBean.setName(name);
        return myBean;
    }
}
