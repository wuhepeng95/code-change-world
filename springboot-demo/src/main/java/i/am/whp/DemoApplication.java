package i.am.whp;

import i.am.whp.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.lang.reflect.Method;

//@SpringBootApplication//表示为一个springboot应用：是下面三个注解的合集
@EnableWebMvc
@EnableRedisRepositories
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableAsync
@ComponentScan(basePackages = {"i.am.whp"})
//@ComponentScan(basePackages = {"i.am.whp"},
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MqController.class),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DirectRabbitConfig.class)
//        })
@EnableRetry
//"i.am.whp.service", "i.am.whp.controller","i.am.whp.config"
public class DemoApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

    public final static Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        log.info("start springboot project : springboot-demo");
        LogUtils.initGlobalMDC();
//        SpringApplication.run(DemoApplication.class, args);


        int i = 0;
        while (i >= 0) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(DemoApplication.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                    return methodProxy.invokeSuper(o, args);
                }
            });
            i++;
            System.out.println(i);
            enhancer.create();
        }


//        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
//        resourceLoader.addProtocolResolver(new ProtocolResolver() {
//            @Override
//            public Resource resolve(String location, ResourceLoader resourceLoader) {
//                return new ClassPathResource(location);
//            }
//        });
//
//        Resource resource = resourceLoader.getResource("banner.txt");
//        try {
//            InputStream inputStream = resource.getInputStream();
//            System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private static Resource resolve(String location, ResourceLoader loader) {
        if (location.contains("test")) {
            return new ClassPathResource(location);
        }
        return new ClassPathResource(location);
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

    //     视图解析器配置
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
