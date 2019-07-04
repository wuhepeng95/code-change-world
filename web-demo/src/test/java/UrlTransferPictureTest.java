//import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.junit.Test;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.net.URL;
//
///**
// * Created by whp on 2019-01-28
// */
//public class UrlTransferPictureTest {
//
//    @Test
//    public void doTest() {
//        HSSFWorkbook wb = new HSSFWorkbook();
//        drawPictureInfoExcel(wb,
//                1, "http://teststatic.yuhanginfo.com:6681//png/20190102185237t33rmtogu.png");
//    }
//
//    private void drawPictureInfoExcel(HSSFWorkbook wb, int rowIndex, String pictureUrl) {
//        try {
//            //anchor主要用于设置图片的属性
//            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 40, 40, (short) 1, rowIndex, (short) 1, rowIndex);
//            //Sets the anchor type （图片在单元格的位置）
//            //0 = Move and size with Cells, 2 = Move but don't size with cells, 3 = Don't move or size with cells.
//            anchor.setAnchorType(0);
//            URL url = new URL(pictureUrl);
//            BufferedImage bufferImg = ImageIO.read(url);
//            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//            ImageIO.write(bufferImg, "jpg", byteArrayOut);
//            byte[] data = byteArrayOut.toByteArray();
////            patriarch.createPicture(anchor, wb.addPicture(data, HSSFWorkbook.PICTURE_TYPE_JPEG));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
