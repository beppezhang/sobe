package com.kpuswebup.comom.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author zhuhuipei
 */

public class CodeUtil {

    private final static Logger LOGGER = LogManager.getLogger(CodeUtil.class);

    /**
     * 生成3DES密钥.
     * 
     * @param key_byte seed key
     * @throws Exception
     * @return javax.crypto.SecretKey Generated DES key
     */
    public static javax.crypto.SecretKey genDESKey(byte[] key_byte) throws Exception {
        SecretKey k = null;
        k = new SecretKeySpec(key_byte, "DESede");
        return k;
    }

    /**
     * 3DES 解密(byte[]).
     * 
     * @param key SecretKey
     * @param crypt byte[]
     * @throws Exception
     * @return byte[]
     */
    public static byte[] desDecrypt(String key, byte[] crypt) throws Exception {
        javax.crypto.SecretKey secretKey = null;
        try {
            secretKey = genDESKey(key.getBytes());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(crypt);
    }

    /**
     * 3DES 解密(String).
     * 
     * @param key SecretKey
     * @param crypt byte[]
     * @throws Exception
     * @return byte[]
     */
    public static String desDecrypt(String key, String crypt) throws Exception {
        return new String(desDecrypt(key, crypt.getBytes()));
    }

    /**
     * 3DES加密(byte[]).
     * 
     * @param key SecretKey
     * @param src byte[]
     * @throws Exception
     * @return byte[]
     */
    public static byte[] desEncrypt(String key, byte[] src) throws Exception {
        javax.crypto.SecretKey secretKey = genDESKey(key.getBytes());
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(src);
    }

    /**
     * 3DES加密(String).
     * 
     * @param key SecretKey
     * @param src byte[]
     * @throws Exception
     * @return byte[]
     */
    public static String desEncrypt(String key, String src) throws Exception {
        return new String(desEncrypt(key, src.getBytes()));
    }

    /**
     * MD5 摘要计算(byte[]).
     * 
     * @param src byte[]
     * @throws Exception
     * @return byte[] 16 bit digest
     */
    public static byte[] md5Digest(byte[] src) throws Exception {
        java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5");
        return alg.digest(src);
    }

    /**
     * MD5 摘要计算(String).
     * 
     * @param src String
     * @throws Exception
     * @return String
     */
    public static String md5Digest(String src) throws Exception {
        return new String(md5Digest(src.getBytes()));
    }

    /**
     * BASE64 编码.
     * 
     * @param src String inputed string
     * @return String returned string
     */
    public static String base64Encode(String src) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(src.getBytes());
    }

    /**
     * BASE64 编码(byte[]).
     * 
     * @param src byte[] inputed string
     * @return String returned string
     */
    public static String base64Encode(byte[] src) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return (src != null ? encoder.encode(src) : null);
    }

    /**
     * BASE64 解码(to byte[]).
     * 
     * @param src String inputed string
     * @return String returned string
     */
    public static byte[] base64Decode(String src) {
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            return decoder.decodeBuffer(src);
        } catch (Exception ex) {
            return null;
        }
    }

    public static byte[] encrypt(String src, String key) {
        byte[] afterEncrypted = null;
        if (src == null || key == null) return null;
        try {
            afterEncrypted = desEncrypt(key, src.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return afterEncrypted;
    }

    /**
     * 16位MD加密
     * 
     * @param plainText
     * @return
     */
    public static String md5Code(String plainText) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

}
