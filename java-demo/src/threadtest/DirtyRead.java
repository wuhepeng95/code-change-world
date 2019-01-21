package threadtest;

public class DirtyRead {
    private String userName = "pdz";
    private String password = "12345";

    public void updateUser(String userName, String password) {
        this.userName = userName;
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("更新用户信息姓名为：" + userName + "密码为：" + password);
    }


    public void queryUser() {
        System.out.println("获取用户信息姓名：" + userName + "密码：" + password);
    }


    public static void main(String[] args) {
        final DirtyRead dirtyread = new DirtyRead();

        Thread t1 = new Thread(() -> dirtyread.updateUser("张三", "111111"));
        t1.start();
        dirtyread.queryUser();
    }
}
