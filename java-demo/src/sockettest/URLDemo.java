package sockettest;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wuhp on 2018/3/2
 */
public class URLDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.runoob.com/index.html?language=cn#j2se");
            System.out.println("URL 为：" + url.toString());
            System.out.println("协议为：" + url.getProtocol());
            System.out.println("验证信息：" + url.getAuthority());
            System.out.println("文件名及请求参数：" + url.getFile());
            System.out.println("主机名：" + url.getHost());
            System.out.println("路径：" + url.getPath());
            System.out.println("端口：" + url.getPort());
            System.out.println("默认端口：" + url.getDefaultPort());
            System.out.println("请求参数：" + url.getQuery());
            //Gets the anchor(锚) (also known as the "reference") of this
            System.out.println("定位位置也叫锚：" + url.getRef());

            System.out.println(URLEncoder.encode("不知道"));//默认
            System.out.println(URLEncoder.encode("不知道", "UTF-8"));
            System.out.println(URLEncoder.encode("不知道", "GB2312"));
            System.out.println(URLEncoder.encode("不知道", "ISO8859-1"));
            //URL转码解码地址
            //http://tool.oschina.net/encode?type=4
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
