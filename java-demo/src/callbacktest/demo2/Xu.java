package callbacktest.demo2;

/**
 * Created by whp on 2018/9/8
 */
public class Xu implements CallBack {
    /**
     * 小李对象的引用
     * 相当于----->背景二
     */
    private Xc xc;

    /**
     * 小徐的构造方法，持有小超的引用
     *
     * @param xc
     */
    public Xu(Xc xc) {
        this.xc = xc;
    }

    /**
     * 小徐通过这个方法去问小超的问题
     *
     * @param question 就是小徐要问的问题,做我女朋友好吗?
     */
    public void askQuestion(final String question) {
        //这里用一个线程就是异步，
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 小徐调用小超中的方法，在这里注册回调接口
                 * 这就相当于A类调用B的方法C
                 */
                xc.executeMessage(Xu.this, question);
            }
        }).start();

        //小徐问完以后，就去打篮球了。
        play();
    }

    public void play() {
        System.out.println("我要去打篮球了");
    }

    /**
     * 小超考虑好后调用此方法告诉小徐，就是所谓的小徐的回调方法
     */
    @Override
    public void solve(String result) {
        System.out.println("小超告诉小徐的答案是--->" + result);
    }

}
