package sockettest.test2;

/**
 * Created by whp on 2018/9/25
 */

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeHtml(String html) throws IOException {
        // 拼装http响应的数据格式
        html = "http/1.1 200 ok\n"
                + "\n\n"
                + html;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.outputStream);
        bufferedOutputStream.write(html.getBytes());
        bufferedOutputStream.close();
    }
}