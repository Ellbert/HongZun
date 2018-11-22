package com.cecilia.framework.utils.encryption;

import com.cecilia.framework.utils.StringUtil;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * DESede对称加密算法
 */
public class DESedeUtil {

    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "DESede";

    /**
     * 加密/解密算法/工作模式/填充方式
     */
    public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    private static Key sKey;

    public DESedeUtil(byte[] key) {
        sKey = toKey(key);
    }

    private Key toKey(byte[] key) {
        try {
            // 实例化Des密钥
            DESedeKeySpec dks = new DESedeKeySpec(key);
            // 实例化密钥工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            // 生成密钥
            return keyFactory.generateSecret(dks);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Description: 加密<p>
     *
     * @param data 待加密数据
     * @return String
     */
    public static String encrypt(String data) {
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            // 执行操作
            return StringUtil.getEnBase64().encode(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return "加密失败";
        }
    }

    /**
     * Description: 解密<p>
     *
     * @param data 待解密数据
     * @return String
     */
    public String decrypt(String data) {
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            // 执行操作
            return new String(cipher.doFinal(StringUtil.getDeBase64().decodeBuffer(data)));
        } catch (Exception e) {
            e.printStackTrace();
            return "解密失败";
        }
    }
}