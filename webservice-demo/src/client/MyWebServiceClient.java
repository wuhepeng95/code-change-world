package client;

import server.MyWebServiceImpl;
import server.MyWebServiceImplService;

/**
 * Created by whp on 2018/10/23
 */
public class MyWebServiceClient {
    public static void main(String[] args) {
        MyWebServiceImplService factory = new MyWebServiceImplService();

        MyWebServiceImpl myWebServiceImplPort = factory.getMyWebServiceImplPort();

        String result = myWebServiceImplPort.sayHello("我是客户端哇");

        System.out.println("调用结果：" + result);
    }
}