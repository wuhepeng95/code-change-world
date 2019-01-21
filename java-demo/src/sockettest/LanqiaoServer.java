package sockettest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class LanqiaoServer {
    private static ServerSocket server;

    public static void main(String[] args) throws Exception {
        server = new ServerSocket(8080);
        System.out.println("服务器启动！");
        Socket socket = server.accept();
        InputStream inputStream = socket.getInputStream();
        // 2、处理请求
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        System.out.println(line);
        String url = line.substring(5, line.indexOf("HTTP") - 1);
        LanqiaoServer.class.getClassLoader();
        // 3、在文件夹中查找文件，并读取字符
        // URL u = LanqiaoServer.class.getClassLoader().getSystemResource("Test/");
        URL u = ClassLoader.getSystemResource("Test/");
        //     String ur = u.getPath() + url;
        //     System.out.println(ur);
        //    FileInputStream is = new FileInputStream(ur);
        // 4、响应服务器
        OutputStream os = socket.getOutputStream();
        byte[] b = new byte[1024];
        int c = 0;
        os.write("HTTP/l.l 200 OK\n".getBytes());
        os.write("".getBytes());
        //   while ((c = is.read(b)) != -1) {
        //        os.write(b, 0, c);
        //  }
        //  is.close();
        os.close();
        socket.close();
    }
}
