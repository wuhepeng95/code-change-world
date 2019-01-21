package lamdbatest;


public class LanbdaTestOverwrited {
    public static void main(String[] args) {
        SayMessahe sayMessahe = System.out::println;
        sayMessahe.method("ABC");
    }

    interface SayMessahe {
        void method(String str);
    }
}
