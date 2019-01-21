package pattern3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    public void method() {
        System.out.println("正在执行任务...");

    }

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-task.xml");
        //加载两次会执行两次，因为task不像bean是单例的
        //new ClassPathXmlApplicationContext("spring-task.xml");
    }
}