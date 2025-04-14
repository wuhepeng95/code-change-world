package jvm;

import org.apache.commons.lang3.AnnotationUtils;

/**
 * Created by whp on 2018/10/26
 * 类加载机制：
 * 1、加载：加载class文件到内存中。
 * 2、链接：讲内存中到二进制数据整合到虚拟机中。
 * 验证：文件格式和元数据验证
 * 准备：为类到静态变量分配内存，并将其初始化为默认值；给常量分配内存并设置值
 * 解析：把类型中的符号引用转换为直接引用
 */

class SingleTon {
    private static SingleTon singleTon = new SingleTon();

    public static int count1;
    public static int count2 = 1;
    {

    }
    private SingleTon() {
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }
}

public class ClassLoaderTest {

    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(AnnotationUtils.class.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
        // 启动类加载器（Bootstrap ClassLoader） 加载的，而启动类加载器是用 C++ 编写的，并且它 不是 Java 类，所以无法通过 getClassLoader() 方法获取到它的类加载器。
        System.out.println(Math.class.getClassLoader()); // 一直是null
        SingleTon instance = SingleTon.getInstance();
        System.out.println("count1 = " + instance.count1);
        System.out.println("count2 = " + instance.count2);
    }
}
