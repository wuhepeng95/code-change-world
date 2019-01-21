package pattern2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    public void method() {
        System.out.println("正在执行任务...");

    }

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-quartz.xml");
    }
}
