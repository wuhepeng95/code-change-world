package i.am.whp;

import i.am.whp.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@SpringBootApplication//表示为一个springboot应用：是下面三个注解的合集
@EnableWebMvc
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"i.am.whp"})
//"i.am.whp.service", "i.am.whp.controller","i.am.whp.config"
public class DemoApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

    public final static Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        log.info("start springboot project : springboot-demo");
        LogUtils.initGlobalMDC();
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }

    // 开启静态资源解释器
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 视图解析器配置
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        /* 设置视图路径的前缀 */
        resolver.setPrefix("/WEB-INF/jsp/");
        /* 设置视图路径的后缀 */
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
