package algorithm.encrypt;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 主键加解密工具 核心算法都是jdk自带的加解密工具
 * 摘于 hzero-starter-keyencrypt-1.4.0
 * @see org.hzero.starter.keyencrypt.core.EncryptionService
 */
public class PrimaryKeyEncryptUtils {

    /**
     * 安全key 目前dev、test、prd 都没有自定义，都是取默认都
     * @See org.hzero.starter.keyencrypt.core.EncryptProperties
     */
    private static final String SECRET_KEY = "";
    private static final String cipherPrefix = "__-";
    private static final String cipherSuffix = "-__";

    public static void main(String[] args) {
        // 解密
        System.out.println("【解密】后的id：\n" + PrimaryKeyEncryptUtils.decode(unwrap("__-N4cEd8C9Vb6qA5eRg7KGUA-__")));
        System.out.println("【解密】后的id：\n" + PrimaryKeyEncryptUtils.decode(unwrap("__-wkzWPLGLaaiKOctXyIG1bQ-__")));
        // 加密
        System.out.println("【加密】后的id：\n" + wrap(PrimaryKeyEncryptUtils.encode("330131494")));

        BigDecimal bigDecimal = new BigDecimal("1.2200");
        System.out.println(bigDecimal.toString());
    }

    /**
     * 解密
     *
     * @param encryptId 密文id
     * @return 明文
     */
    public static String decode(String encryptId) {
        Key keyDe = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keyDe);
            String decodeId = new String(cipher.doFinal(Base64.getUrlDecoder().decode(encryptId.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            return decodeId.replace(":: ", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 加密
     *
     * @param id 明文id
     * @return 密文
     */
    public static String encode(String id) {
        // 雪花id 加密
        SecretKeySpec key = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), "AES");
        List<String> encryptKeyList = new ArrayList<>();
        encryptKeyList.add(id);
        // tableName
        encryptKeyList.add("");
        encryptKeyList.add(" ");
        String content = collectionToDelimitedString(encryptKeyList, ":", "", "");
        // 待加密字符串加了一个 ":: "
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, key);
            return Base64.getUrlEncoder().withoutPadding().encodeToString(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 其余工具方法

    public static String wrap(String content) {
        return cipherPrefix + content + cipherSuffix;
    }

    public static String unwrap(String content) {
        return content.substring(cipherPrefix.length(), content.length() - cipherSuffix.length());
    }

    public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
        if (coll == null || coll.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Iterator<?> it = coll.iterator();
        while (it.hasNext()) {
            sb.append(prefix).append(it.next()).append(suffix);
            if (it.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }
}
