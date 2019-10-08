package i.am.whp.config;

import i.am.whp.filter.ContextFilter;
import i.am.whp.filter.Log4jFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean setCharacterEncodingFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addInitParameter("encoding", "UTF-8");
        registrationBean.setName("setCharacterEncodingFilter");
        /* SetCharacterEncodingFilter greetingFilter = new SetCharacterEncodingFilter();*/
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        registrationBean.setFilter(encodingFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(10);//order的数值越小 则优先级越高 先修改编码格式
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean setContextFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("context filter");
        ContextFilter contextFilter = new ContextFilter();
        registrationBean.setFilter(contextFilter);
        registrationBean.addUrlPatterns("/pt/*");
        registrationBean.addUrlPatterns("/pi/*");
        registrationBean.setOrder(20);//过滤接口 自定义context的处理 做一些授权处理
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean setLog4jFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("log4jFilter");
        Log4jFilter log4jFilter = new Log4jFilter();
        registrationBean.setFilter(log4jFilter);
        registrationBean.addUrlPatterns("*.do");
        registrationBean.setOrder(30);//最后*.do的走日志过滤器 添加guid标识每次请求
        return registrationBean;
    }

}
