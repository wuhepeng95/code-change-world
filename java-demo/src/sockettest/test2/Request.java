package sockettest.test2;

/**
 * Created by whp on 2018/9/25
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * http请求 接收类
 */
public class Request {

    // 用来保存输入流对象
    private InputStream inputStream;
    // 从输入流中每次读取的字节长度
    private int bufferLength = 1024;

    /**
     * 构造函数
     *
     * @param inputStream 输入流对象
     */
    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * 读取内容
     *
     * @return 返回的是字符串
     * @throws IOException
     */
    public String readHtml() throws IOException {
        // 使用BufferedInputStream对象来读取
        BufferedInputStream bufferedInputStream = new BufferedInputStream(this.inputStream);
        // 每次读取1024字节
        byte[] buffer = new byte[this.bufferLength];

        // 字符串拼接对象
        StringBuilder stringBuilder = new StringBuilder();
        int length = 0;
        while (true) {

            length = bufferedInputStream.read(buffer);

            // 把读取的字节转为String类型
            // 然后拼接成一个大的字符串
            stringBuilder.append(new String(buffer, 0, length));

            if (length <= 0 || length < this.bufferLength) {
                break; // 如果没有读取到内容就跳出循环
            }
        }
        // 返回字符串内容
        return stringBuilder.toString();
    }
}

