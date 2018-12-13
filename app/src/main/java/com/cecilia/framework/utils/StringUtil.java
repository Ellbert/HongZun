package com.cecilia.framework.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.Formatter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

//@SuppressLint("DefaultLocale")
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class StringUtil {

    // BASE64编码的加密工具类
    private static BASE64Encoder enBase64;
    // BASE64编码的解密工具类
    private static BASE64Decoder deBase64;

    private StringUtil() {
        throw new AssertionError();
    }

    public static BASE64Encoder getEnBase64() {
        if (enBase64 == null) {
            synchronized (NetworkUtil.class) {
                if (enBase64 == null) {
                    enBase64 = new BASE64Encoder();
                }
            }
        }
        return enBase64;
    }

    public static BASE64Decoder getDeBase64() {
        if (deBase64 == null) {
            synchronized (NetworkUtil.class) {
                if (deBase64 == null) {
                    deBase64 = new BASE64Decoder();
                }
            }
        }
        return deBase64;
    }

    /**
     * 比较两个字符串是否一样
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str2 != null && str1.equals(str2);
    }

    /**
     * 比较两个字符串是否一样,不区分大小写
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str2 != null && str1.equalsIgnoreCase(str2);
    }

    /**
     * 比较两个字符串是否一样,不区分大小写,去掉符号后比较
     */
    public static boolean equalsIgnoreCaseFilterSign(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }
        return str2 != null && str1.length() != str2.length()
                && StringFilter(str1).equalsIgnoreCase(StringFilter(str2));
    }

    /**
     * 比较两个字符串是否一样,不区分大小写
     */
    public static boolean equalsIgnoreCases(String str1, String... str) {
        for (String str0 : str) {
            if (equalsIgnoreCase(str1, str0))
                return true;
        }
        return false;
    }

    /**
     * 比较多个字符串和其中一个字符串是否一样,不区分大小写,去掉符号后比较
     */
    public static boolean equalsIgnoreCaseFilterSign(String str1, String... str) {
        for (String str0 : str) {
            if (equalsIgnoreCaseFilterSign(str1, str0))
                return true;
        }
        return false;
    }

    /**
     * 过滤特殊字符
     */
    public static String StringFilter(String str) {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 判断字符串是否为null或空值
     *
     * @param str 判断字符串
     * @return true:为空；false:不为空
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否为null或空值
     *
     * @param str 判断字符串
     * @return true:为空；false:不为空
     */
    public static boolean isNullOrEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 手机号验证
     *
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        if (isNullOrEmpty(str))
            return false;
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        return p.matcher(str).matches();
    }

    /**
     * 电话号码验证
     *
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        if (isNullOrEmpty(str))
            return false;
        Pattern p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        Pattern p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
        if (str.length() > 9) {
            return p1.matcher(str).matches();
        } else {
            return p2.matcher(str).matches();
        }

    }

    /**
     * 手机简化显示 如：130****0000
     */
    public static String phoneSimplify(String phone) {
        if (isNullOrEmpty(phone))
            return phone;
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    /**
     * 手机简化显示 如：130****0000
     */
    public static String phoneSimplify(String phone, int start) {
        if (isNullOrEmpty(phone))
            return phone;
        if (start < 0 || start > phone.length()) {
            return phone;
        }
        StringBuilder simplify = new StringBuilder();
        for (int i = 0; i < phone.length() - start; i++) {
            simplify.append("*");
        }
        return phone.substring(0, start) + simplify.toString();
    }

    /**
     * 手机简化显示 如：130****0000
     */
    public static String phoneSimplify(String phone, int start, int end) {
        if (isNullOrEmpty(phone))
            return phone;
        if (start < 0 || start > phone.length() || end > phone.length() || end <= start || end < 0) {
            return phone;
        }
        StringBuilder simplify = new StringBuilder();
        for (int i = 0; i < end - start; i++) {
            simplify.append("*");
        }
        return phone.substring(0, start) + simplify.toString() + phone.substring(end);
    }

    /**
     * 字符串超出给定文字则截取
     */
    public static String trim(String str, int limit) {
        String mStr = str.trim();
        return mStr.length() > limit ? mStr.substring(0, limit) : mStr;
    }

    /**
     * 是否是正确的邮箱地址
     */
    public static boolean isEmail(String strEmail) {
        String checkEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(checkEmail);
        Matcher matcher = pattern.matcher(strEmail);
        return matcher.matches();
    }

    /**
     * 字符串长度检测
     */
    public static boolean isLenghtOk(String string, int max, int min) {
        if (null != string) {
            if (string.length() > max || string.length() < min) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串长度是否
     */
    public static boolean isLenghtOk(String string, int max) {
        if (null != string) {
            if (string.length() > max) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将输入字符串做MD5编码
     *
     * @param s 欲编码了字符串
     * @return 编码后的字符串，错误返回""
     */
    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes("UTF-8"));
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                if ((b & 0xFF) < 0x10)
                    hexString.append("0");
                hexString.append(Integer.toHexString(b & 0xFF));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 文件大小 long转成xxB、xxM、xxG、xxT....
     */
    public static String getFileSizeText(long totalBytes, Context context) {
        String sizeText = "";
        if (totalBytes >= 0) {
            sizeText = Formatter.formatFileSize(context, totalBytes);
        }
        return sizeText;
    }

    /**
     * 把中文转成Unicode
     */
    public static String StringToUnicode(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int chr1 = str.charAt(i);
            // 汉字范围 \u4e00-\u9fa5 (中文)
            if (chr1 >= 19968 && chr1 <= 171941) {
                result.append(String.valueOf("\\u" + Integer.toHexString(chr1)));
            } else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * 把Unicode转成中文
     */
    public static String UnicodeToString(String str) {
        char aChar;
        int len = str.length();
        StringBuilder outBuffer = new StringBuilder(len);
        for (int x = 0; x < len; ) {
            aChar = str.charAt(x++);
            if (aChar == '\\') {
                aChar = str.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = str.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    switch (aChar) {
                        case 't':
                            outBuffer.append('\t');
                            break;
                        case 'r':
                            outBuffer.append('\r');
                            break;
                        case 'n':
                            outBuffer.append('\n');
                            break;
                        case 'f':
                            outBuffer.append('\f');
                            break;
                        default:
                            outBuffer.append(aChar);
                            break;
                    }
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /**
     * 判断是否为中文字符
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     * 完整的判断中文汉字
     */
    public static boolean isChineseChar(String str) {
        String myReg = "^[\u4e00-\u9fa5]+$";
        return str.matches(myReg);
    }

    /**
     * 完整的判断中文汉字和符号
     */
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (char c : ch) {
            if (!isChinese(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 完整的判断包含中文汉字和符号
     */
    public static boolean isChinese2(String strName) {
        char[] ch = strName.toCharArray();
        for (char c : ch) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断身份证
     *
     * @param cardId 身份证号
     */
    public static boolean isCardId(String cardId) {
        if (StringUtil.isNullOrEmpty(cardId))
            return false;
        //String reg = "/^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$/";
        String reg = "/^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$/";
        return cardId.matches(reg);
    }

    /**
     * 比较字符串结尾一样
     *
     * @param str 字符串
     * @param end 字符串尾部
     * @return true:一样， false:不一样
     */
    public static boolean endsWithIgnoreCase(String str, String end) {
        return equals(str, end)
                || !(isNullOrEmpty(str) || isNullOrEmpty(end))
                && str.toUpperCase().endsWith(end.toUpperCase());
    }

    /**
     * 比较字符串开始一样
     *
     * @param str 字符串
     * @return true:一样， false:不一样
     */
    public static boolean startsWithIgnoreCase(String str, String start) {
        return equals(str, start)
                || !(isNullOrEmpty(str) || isNullOrEmpty(start))
                && str.toUpperCase().startsWith(start.toUpperCase());
    }

    /**
     * 比较字符串结尾一样
     *
     * @param str 字符串
     * @param end 字符串尾部
     * @return true:一样， false:不一样
     */
    public static boolean endsWithIgnoreCases(String str, String... end) {
        for (String str0 : end) {
            if (endsWithIgnoreCase(str, str0))
                return true;
        }
        return false;
    }

    /**
     * 比较字符串开始一样
     *
     * @param str 字符串
     * @return true:一样， false:不一样
     */
    public static boolean startsWithIgnoreCases(String str, String... start) {
        for (String str0 : start) {
            if (startsWithIgnoreCase(str, str0))
                return true;
        }
        return false;
    }

    /**
     * counter ASCII character as one, otherwise two
     *
     * @return count
     */
    public static int counterChars(String str) {
        // return
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            int tmp = (int) str.charAt(i);
            if (tmp > 0 && tmp < 127) {
                count += 1;
            } else {
                count += 2;
            }
        }
        return count;
    }

    /**
     * 是否是正确的QQ号
     */
    public static boolean isQQCorrect(String str) {
        String regex = "[1-9][0-9]{4,14}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String getLevel(int level) {
        String levelString = "";
        switch (level) {
            case 0:
                levelString = "泓樽士兵";
                break;
            case 1:
                levelString = "泓樽勇士";
                break;
            case 2:
                levelString = "泓樽将军";
                break;
            case 3:
                levelString = "泓樽元帅";
                break;
        }
        return levelString;
    }


    /*
     * 将时间戳转换为时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String stampToDate(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = Long.valueOf(s);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);
    }

}