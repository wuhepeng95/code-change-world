package reflecttest;

/*
Java中instanceof和isInstance区别详解

instanceof和isInstance，instanceof和isInstance长的非常像，用法也很类似，先看看这两个的用法：


obj.instanceof(class)

也就是说这个对象是不是这种类型，

1.一个对象是本身类的一个对象

2.一个对象是本身类父类（父类的父类）和接口（接口的接口）的一个对象

3.所有对象都是Object

4.凡是null有关的都是false  null.instanceof(class)



class.inInstance(obj)

这个对象能不能被转化为这个类

1.一个对象是本身类的一个对象

2.一个对象能被转化为本身类所继承类（父类的父类等）和实现的接口（接口的父接口）强转

3.所有对象都能被Object的强转

4.凡是null有关的都是false   class.inInstance(null)

类名.class和对象.getClass()几乎没有区别，因为一个类被类加载器加载后，就是唯一的一个类。
*/
class A {

}

class B extends A {

}

class C extends B {

}

public class InstanceTest {

    public static void main(String[] args) {

        A a = new A();
        B b = new B();
        C c = new C();

        B bc = new C();
        A ac = new C();

        System.out.println(a instanceof C);    //a是C的实例否？ false
        System.out.println(a instanceof B);    //a是B的实例嘛？false
        System.out.println(a instanceof A);   //a是A的实例嘛，true
        System.out.println();

        System.out.println(c instanceof C);    //c是C的实例否？ true
        System.out.println(c instanceof B);    //c是B的实例否？true    C继承B
        System.out.println(c instanceof A);    //c是A的实例否，true    C继承B，B继承A，所以C继承A

        System.out.println();

        System.out.println(c.getClass().isInstance(c));    //c是C的实例嘛, true;
        System.out.println(c.getClass().isInstance(b));    //b是C的实例嘛，false；
        System.out.println(c.getClass().isInstance(a));    //a是C的实例嘛，false；

        System.out.println();

        System.out.println(c.getClass().isInstance(bc));   //bc是C的实例嘛， true
        System.out.println(c.getClass().isInstance(ac));

        System.out.println();

        System.out.println(A.class.isInstance(a));    //a是A的实例嘛， true；
        System.out.println(A.class.isInstance(b));    //b是A的实例嘛，true；   B继承A
        System.out.println(A.class.isInstance(c));    //c是A的实例嘛， true；
        System.out.println(A.class.isInstance(ac));
        System.out.println(A.class.isInstance(bc));

        System.out.println();

        System.out.println(B.class.isInstance(a));   //a是B的实例嘛，false。
        System.out.println(B.class.isInstance(b));
        System.out.println(B.class.isInstance(c));
        System.out.println(B.class.isInstance(ac));
        System.out.println(B.class.isInstance(bc));

    }

}
