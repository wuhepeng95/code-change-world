package callbacktest.demo2;

/**
 * Created by whp on 2018/9/8
 */
public class Xc {

    public void executeMessage(CallBack callBack, String question){
        System.out.println("小徐问的问题--->" + question);

        //模拟小超要睡觉了，加入睡了很长时间
        for(int i=0; i<2000000000;i++){

        }

        /**
         * 小超睡醒后想到的是 好，同意！！！
         */
        String result = "好，同意！！！";

        /**
         * 于是就告诉小徐，调用小徐中的方法
         * 这就相当于B类反过来调用A的方法D
         */
        callBack.solve(result);



    }

}
