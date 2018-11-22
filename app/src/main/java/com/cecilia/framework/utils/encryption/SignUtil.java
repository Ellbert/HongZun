package com.cecilia.framework.utils.encryption;

import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.ViewUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {
    //    第一步：对参数按照key=value的格式，并按照参数名ASCII字典序排序如下：
    //    stringA="appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
    //    第二步：拼接API密钥：
    //    stringSignTemp="stringA&key=192006250b4c09247ec02edce69f6a2d"
    //    sign=MD5(stringSignTemp).toUpperCase()="9A0A8659F005D6984697E2CA0A9CF3B7"
    //    最终得到最终发送的数据：
    //    appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA&sign=9A0A8659F005D6984697E2CA0A9CF3B7
    public static String sign(String str) {
        String stringSignTemp = str + "&key=" + GuangUtil.loadSignKey(ViewUtil.getContext());
        return md5(stringSignTemp).toUpperCase();
    }

    //md5加密算法
    public static String md5(String password) {
        byte[] bytes = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());  //更新摘要
            bytes = digest.digest(); //再通过执行诸如填充之类的最终操作完成哈希计算。在调用此方法之后，摘要被重置。
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            // 0xFF默认是整形，一个byte跟0xFF相与会先将那个byte转化成整形运算
            if ((b & 0xFF) < 0x10) {  //如果为1位 前面补个0
                builder.append("0");
            }
            builder.append(Integer.toHexString(b & 0xFF));
        }
        return builder.toString();
    }

    public static String sign(TreeMap<String, String> requestMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : requestMap.entrySet()) {
            if (null != entry.getValue() && entry.getValue().length() > 0) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        //String plaintext=sb.toString();
        //String stringSignTemp = plaintext + "&key=" + YouRenUtils.KEY;
        //String appid = md5(stringSignTemp).toUpperCase();
        String plaintext = String.format("%s%s%s", sb.toString(), "key=", GuangUtil.loadSignKey(ViewUtil.getContext()));
        return md5(plaintext).toUpperCase();
    }

}
