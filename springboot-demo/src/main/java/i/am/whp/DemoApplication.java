package i.am.whp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication//表示为一个springboot应用：是下面三个注解的合集
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"i.am.whp"})
//"i.am.whp.service", "i.am.whp.controller","i.am.whp.config"
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}