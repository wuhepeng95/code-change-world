package i.am.whp.config;

import i.am.whp.filter.Log4jFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebFilterConfig {

//    @Bean
//    public FilterRegistrationBean setCharacterEncodingFilterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.addInitParameter("encoding", "UTF-8");
//        registrationBean.setName("setCharacterEncodingFilter");
//        /* SetCharacterEncodingFilter greetingFilter = new SetCharacterEncodingFilter();*/
//        CharacterEncodingFilter greetingFilter = new CharacterEncodingFilter();
//        registrationBean.setFilter(greetingFilter);
//        registrationBean.addUrlPatterns("*");
//        registrationBean.setOrder(10);
//        return registrationBean;
//    }


    @Bean
    public FilterRegistrationBean log4jFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("log4jFilter");
        Log4jFilter greetingFilter = new Log4jFilter();
        registrationBean.setFilter(greetingFilter);
//        registrationBean.addUrlPatterns("*.do");
        registrationBean.setOrder(30);
        return registrationBean;
    }

}
