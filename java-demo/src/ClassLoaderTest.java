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
        SingleTon instance = SingleTon.getInstance();
        System.out.println("count1 = " + instance.count1);
        System.out.println("count2 = " + instance.count2);
    }
}
