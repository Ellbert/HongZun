package com.cecilia.framework.utils.encryption;

import com.cecilia.framework.utils.StringUtil;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * <p>RAS公私钥对生产、公私钥签名和验签、公私钥加密和解密
 * RSA协商密钥防止中间人攻击
 * http://www.360doc.com/content/13/0703/11/12747488_297323024.shtml
 * http://www.zhihu.com/question/25116415
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 和其它加密过程一样，对RSA来说分配公钥的过程是非常重要的。
 * 分配公钥的过程必须能够抵挡中间人攻击。假设Eve交给Bob一个公钥，并使Bob相信这是Alice的公钥，并且她可以截下Alice和Bob之间的信息传递，
 * 那么她可以将她自己的公钥传给Bob，Bob以为这是Alice的公钥。
 * Eve可以将所有Bob传递给Alice的消息截下来，将这个消息用她自己的密钥解密，读这个消息，
 * 然后将这个消息再用Alice的公钥加密后传给Alice。
 * 理论上Alice和Bob都不会发现Eve在偷听他们的消息。今天人们一般用数字认证来防止这样的攻击。
 * <p>
 * #手机端rsa私钥
 * systemConfig.mobile.privateKey=
 * #手机端ras公钥
 * systemConfig.mobile.publicKey=
 */
public class RSAUtil {

    /**
     * 算法
     */
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 填充方式
     */
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    /**
     * 获取公钥的key
     */
    public static final String PUBLIC_KEY = "PublicKey";
    /**
     * 获取私钥的key
     */
    public static final String PRIVATE_KEY = "PrivateKey";
    /**
     * 密钥大小
     */
    private static final int KEY_SIZE = 2048;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = KEY_SIZE / 8;
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = MAX_DECRYPT_BLOCK - 11;

    /**
     * 生成密钥对(公钥和私钥)
     */
    public static Map<String, Object> generatorKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, Object> keyMap = new HashMap<String, Object>(2);

            String publicBase64 = StringUtil.getEnBase64().encode(publicKey.getEncoded());
            String privateBase64 = StringUtil.getEnBase64().encode(privateKey.getEncoded());
            keyMap.put(PUBLIC_KEY, publicBase64);
            keyMap.put(PRIVATE_KEY, privateBase64);
            return keyMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param dataPlain  已加密数据
     * @param privateKey 私钥(BASE64编码)
     */
    public static String sign(String dataPlain, String privateKey) {
        try {
            PrivateKey privateK = KeyFactory.getInstance(KEY_ALGORITHM)
                    .generatePrivate(new PKCS8EncodedKeySpec(StringUtil.getDeBase64().decodeBuffer(privateKey)));
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateK);
            signature.update(dataPlain.getBytes());
            return StringUtil.getEnBase64().encode(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
            return "签名失败";
        }
    }

    /**
     * 校验数字签名
     *
     * @param dataPlain 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     */
    public static boolean verify(String dataPlain, String publicKey, String sign) {
        try {
            PublicKey publicK = KeyFactory.getInstance(KEY_ALGORITHM)
                    .generatePublic(new X509EncodedKeySpec(StringUtil.getDeBase64().decodeBuffer(publicKey)));
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicK);
            signature.update(dataPlain.getBytes());
            return signature.verify(StringUtil.getDeBase64().decodeBuffer(sign));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 私钥解密(推荐)
     *
     * @param encryptedBase64 已加密数据
     * @param privateKey      私钥(BASE64编码)
     */
    public static String decryptByPrivateKey(String encryptedBase64, String privateKey) {
        try {
            Key privateK = KeyFactory.getInstance(KEY_ALGORITHM)
                    .generatePrivate(new PKCS8EncodedKeySpec(StringUtil.getDeBase64().decodeBuffer(privateKey)));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateK);

            byte[] encryptedBase64s = StringUtil.getDeBase64().decodeBuffer(encryptedBase64);
            int inputLen = encryptedBase64s.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedBase64s, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedBase64s, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            out.close();
            return new String(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return "私钥解密失败";
        }
    }

    /**
     * 公钥加密
     *
     * @param dataPlain 源数据
     * @param publicKey 公钥(BASE64编码)
     */
    public static String encryptByPublicKey(String dataPlain, String publicKey) {
        try {
            Key publicK = KeyFactory.getInstance(KEY_ALGORITHM)
                    .generatePublic(new X509EncodedKeySpec(StringUtil.getDeBase64().decodeBuffer(publicKey)));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicK);

            byte[] data = dataPlain.getBytes();
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            out.close();
            return StringUtil.getEnBase64().encode(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return "公钥加密失败";
        }
    }

    /**
     * 获取私钥
     *
     * @param keyMap 密钥对
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        return (String) keyMap.get(PRIVATE_KEY);
    }

    /**
     * 获取公钥
     *
     * @param keyMap 密钥对
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        return (String) keyMap.get(PUBLIC_KEY);
    }

}