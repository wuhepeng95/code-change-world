package threadtest;

public class ThreadLocalInherited {

    public void testInheritable(ThreadLocal<String> userId) {
        System.out.println("Test inheritable: " + userId.get());
    }
}
