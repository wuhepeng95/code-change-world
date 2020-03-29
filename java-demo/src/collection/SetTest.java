package collection;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author wuhepeng
 * @date 2020/3/27
 */
public class SetTest {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        treeSet.add("one");
        treeSet.add("two");
        treeSet.add("two");
        treeSet.add("three");
        treeSet.add("four");
        treeSet.add("four");
        treeSet.add("five");
        System.out.println(treeSet);

        HashSet hashSet = new HashSet();
//        hashSet.add(10);
//        hashSet.add(1);
//        hashSet.add(2);
//        hashSet.add(3);
//        hashSet.add(2);
//        hashSet.add("four");
//        hashSet.add("five");

        hashSet.add("one");
        hashSet.add("two");
        hashSet.add("two");
        hashSet.add("three");
        hashSet.add("four");
        hashSet.add("four");
        hashSet.add("five");
        System.out.println(hashSet);

        // 1、set会去重
        // 2、有序 （排序规则看实现，每次add都会重新排序）
        // 3、未指定类型存入的是object
    }
}
