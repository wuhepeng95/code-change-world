package sockettest.test2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by whp on 2018/9/25
 */
public class MyServer {
    public static void main(String[] args) throws IOException, IOException {
        // 服务端创建一个监听
        ServerSocket serverSocket = new ServerSocket(9000);
        // 监听客户端连接，这里我们暂时没有多线程
        Socket socket = serverSocket.accept();

        // 要想获取客户端发送过来的内容，就要得到 InputStream类 的输入流对象
        InputStream inputStream = socket.getInputStream();

        //==========使用我们自定义的http处理的相关类===========//
        Request request = new Request(inputStream);
        System.out.println(request.readHtml());

        // 要想给客户端发送数据，就要得到
        OutputStream outputStream = socket.getOutputStream();
        Response response = new Response(outputStream);
        response.writeHtml("this is i.am.whp.server");

        // 关闭
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
