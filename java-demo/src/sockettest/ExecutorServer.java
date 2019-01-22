package sockettest;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by whp on 2018/8/30
 */
public class ExecutorServer {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            ServerSocket server = new ServerSocket(8081);
            while (true) {

                // 服务端创建一个监听
                // 监听客户端连接，这里我们暂时没有多线程
                Socket socket = server.accept();

                OutputStream outputStream = socket.getOutputStream();

                PrintWriter printWriter = new PrintWriter(outputStream, true);

                String html = "http/1.1 200 ok\n" + "\n\n" + "访问成功";

                printWriter.write(html);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                bufferedOutputStream.write(html.getBytes());
                bufferedOutputStream.close();

                printWriter.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
