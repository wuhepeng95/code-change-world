import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * 二维码生成
 */
public class QrcodeImg {

    /**
     * @param content 二维码存储的内容
     * @param imgPath 二维码图标保存位置
     */
    public static void getQrcodeImg(String content, String imgPath) {

        // 实例化一个Qrcode对象 基本设置
        Qrcode qcQrcode = new Qrcode();
        // 设置排错 15%空间 错误信息
        qcQrcode.setQrcodeErrorCorrect('M');
        // 设置二维码存储内容编码集
        qcQrcode.setQrcodeEncodeMode('B');
        // 设置二维码版大小
        qcQrcode.setQrcodeVersion(15);

        // 准备绘制二维码：
        // 画板：创建一个画笔
        int width = 235;
        int height = 235;
        BufferedImage imge = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 画笔：绘制工具
        Graphics2D gs = imge.createGraphics();

        // 设置背景颜色
        gs.setBackground(Color.white);
        // 绘制矩形
        gs.clearRect(0, 0, width, height);
        // 设置前景颜色
        gs.setColor(Color.black);

        try {
            // 接受内容
            byte[] codeOut = content.getBytes("utf-8");
            // 通过byte数组获取布尔类型的二维数数组
            boolean[][] code = qcQrcode.calQrcode(codeOut);
            // 遍历这个二维数组
            for (int i = 0; i < code.length; i++) {
                for (int j = 0; j < code.length; j++) {
                    // 如果是true涂黑
                    if (code[j][i]) {
                        gs.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
                    }
                }
            }
            // 释放资源
            gs.dispose();
            imge.flush();

            //写入自定路径
            ImageIO.write(imge, "png", new File(imgPath));
            System.out.println("二维码生成完成");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String path = QrcodeImg.class.getResource("").getPath();
        getQrcodeImg("http://www.baidu.com", path+"../images/qrcode.png");
    }
}
