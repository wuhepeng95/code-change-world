package annotation.test1;

public class AnnotationTest {
    @MyMessage()
    private static int a;

    @MyMessage(name = "非默认name", num = 120, desc = "非默认dest")
    public void test() {
    }

    public static void main(String[] args) {
        AnnotationTest annotationTest = new AnnotationTest();
        System.out.println(annotationTest.a);
    }
}
