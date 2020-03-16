package i.am.whp.config;

import i.am.whp.filter.WebSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSiteMeshConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<WebSiteMeshFilter> siteMeshFilter() {
        FilterRegistrationBean<WebSiteMeshFilter> filter = new FilterRegistrationBean<>();
        WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
        filter.setFilter(siteMeshFilter);
        return filter;
    }
}
