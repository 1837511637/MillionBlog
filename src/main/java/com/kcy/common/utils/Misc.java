package com.kcy.common.utils;

import com.kcy.common.constant.WebConst;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

import static java.util.regex.Pattern.compile;

/**
 * Misc class
 * @author kcy
 * @date 2018/11/22
 */
public class Misc {

    public final static String ENCODE = "UTF-8", GBKCODE = "gbk", EN16CODE = "utf-16", ISOCODE = "ISO-8859-1";

    public static String[] NORESULT_WORDS = new String[]{"深网", "deep" ,"暗网", "黑暗网络", "dark", "影网", "影子网络", "shadow", "马网", "马里亚纳网络", "mariana", "ISIS", "黑客", "网赚", "电子狗", "百家乐", "北京战争", "无政府主义", "十八大", "胡锦涛", "杨匡", "刘霞", "公安大情报", "习近平", "习大大", "温家宝", "冰毒", "迷情", "siddiqlar", "istiqlaltv", "turkistantv", "siddiqlartv", "uyghurcongress", "yahxilarning bagqisi", "din wa hayat", "xarki turkistan", "red room", "好人乐园", "宗教和生命", "东突", "伊斯兰之声", "islam awazi radiosi", "husayin tajalli"};

    /**
     * 提取金额,使用正则表达式提取字符串中的金额数值
     * 格式可以为0.0或者，11
     * @param cost  字符串
     * @return Double  金额
     */
    public static Double extract_cost_dot(String cost) {
        Pattern compile = compile("(\\d+\\.\\d+)|(\\d+)");
        Matcher matcher = compile.matcher(cost);
        matcher.find();
        return Double.valueOf(matcher.group());
    }

    /**
     * 给get请求路径拼接参数
     * @param  param 参数
     * @param  initUrl 路径
     * @return String 拼接后的路径
     * */
    public static String spliceParamToUrl(Map<String, Object> param, String initUrl) {
        StringBuffer stringBuffer = new StringBuffer(initUrl);
        if(param != null) {
            stringBuffer.append("?");
            for(String key : param.keySet()) {
                stringBuffer.append(key);
                stringBuffer.append("=");
                stringBuffer.append(param.get(key));
                stringBuffer.append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        return stringBuffer.toString();
    }

    /**
     * 判断邮箱格式是否正确
     * @param stremail 邮箱信息
     * @return boolean
     * */
    public static boolean isValidEmail(String stremail) {
        String p = "^[\\-!#\\$%&'\\*\\+\\\\\\.\\/0-9=\\?A-Z\\^_`a-z{|}~]+@[\\-!#\\$%&'\\*\\+\\\\\\.\\/0-9=\\?A-Z\\^_`a-z{|}~]+(\\.[\\-!#\\$%&'\\*\\+\\\\\\.\\/0-9=\\?A-Z\\^_`a-z{|}~]+)+$";
        boolean result = Pattern.matches(p, stremail);
        return result;
    }

    /**
     * 字符串转Double类型
     * @param s 字符串
     * @return Double
     * */
    public static Double parseDouble(String s) {
        if (s == null) {
            return 0.0D;
        } else {
            try {
                s = s.trim().replace(",", "");
                return Double.parseDouble(s);
            } catch (Exception var2) {
                return 0.0D;
            }
        }
    }

    /**
     * 字符串转Integer类型
     * @param s 字符串
     * @return Double
     * */
    public static Integer parseInteger(String s) {
        if (s == null) {
            return 0;
        } else {
            try {
                int pos = s.indexOf(".");
                if (pos != -1) {
                    s = s.substring(0, pos);
                }
                s = s.trim().replace(",", "");
                return Integer.parseInt(s);
            } catch (Exception var2) {
                return 0;
            }
        }
    }

    /**
     * 字符串转Long类型
     * @param s 字符串
     * @return Double
     * */
    public static Long parseLong(String s) {
        if (s == null) {
            return 0L;
        } else {
            try {
                int pos = s.indexOf(".");
                if (pos != -1) {
                    s = s.substring(0, pos);
                }
                s = s.trim().replace(",", "");
                return Long.parseLong(s);
            } catch (Exception var2) {
                return 0L;
            }
        }
    }

    /**
     * 判断字符串是否是数字
     * @param str 字符串
     * @return boolean
     * */
    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        } else {
            for(int i = 0; i < str.length(); ++i) {
                if ("0123456789".indexOf(str.charAt(i)) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 判断字符串是否是空的
     * @param str 字符串
     * @return boolean
     * */
    public static boolean isStringEmpty(String str) {
        boolean ret = false;
        if (str == null || "".equals(str.trim())) {
            ret = true;
        }
        return ret;
    }

    /**
     * 判断字符串内容是否违法
     * @param keyword 字符串
     * @return boolean
     * */
    public static boolean isIllegal(String keyword) {
        String[] var4 = NORESULT_WORDS;
        int var3 = NORESULT_WORDS.length;
        for(int var2 = 0; var2 < var3; ++var2) {
            String s = var4[var2];
            if (keyword.indexOf(s) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * BigDecimal四舍五入到小数点第二位
     * @param b BigDecimal类型
     * @return String类
     */
    public static String getDecimalFormat(BigDecimal b){
        if(b==null){
            return "0";
        }
        DecimalFormat df = new DecimalFormat("####.##");
        try{
            return df.format(b);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 判断是否是图片类型
     * @param contentType 文件名或者文件类型
     * @return boolean
     * */
    public static boolean isPicuter(String contentType){
        if(contentType.contains(".")) {
            contentType = contentType.substring(contentType.indexOf(".")+1,contentType.length());
        }
        if(contentType.equals("gif") || contentType.equals("jpg") || contentType.equals("jpeg") ||
                contentType.equals("png") || contentType.equals("bmp")){
            return true;
        }
        return false;
    }



    /**
     * 过滤表情符号
     * @param source 字符串
     * @return String 过滤后的新内容
     * */
    public static String filterEmoji(String source) {
        if(source != null)
        {
            Pattern emoji = compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find())
            {
                source = emojiMatcher.replaceAll("*");
                return source ;
            }
            return source;
        }
        return source;
    }

    /**
     * 获取N位随机数
     * @param len N
     * @return String
     * */
    public static String getNumberRandom(int len) {
        String sRand = "";
        for(int i=0;i<len;i++){
            sRand += (int) Math.round(Math.random() * 9);
        }
        return sRand;
    }

    /**
     * 校验手机号码
     *
     * @param mobile 手机号
     * @return boolean
     * */
    public static boolean isMobile(String mobile){
        boolean isMobile = false;
	    if(mobile.length() == 11 && "1".equals(mobile.substring(0,1))) {
	        return isMobile = true;
        }
        return isMobile;
    }



    /**
     * 使用反射根据属性名称获取属性值
     *
     * @param  fieldName 属性名称
     * @param  o 操作对象
     * @return Object 属性值
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取对象属性，返回一个字符串数组
     *
     * @param  o 对象
     * @return String[] 字符串数组
     */
    public static String[] getFiledName(Object o) {
        try {
            Field[] fields = o.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for (int i=0; i < fields.length; i++) {
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 剔除html标签
     *
     * @param content 字符串内容
     * @return String 新内容
     * */
    public static String excludeHtmlTags(String content) {
        if (isStringEmpty(content)) {
            return content;
        }
        return content.replaceAll("</?[^>]+>","");
    }

    /**
     * 根据字符编码得到字符串实际占用长度
     */
    public static int getStringLength(String str,String encoding){
        if(isStringEmpty(str)) {
            return 0;
        } else {
            try{
                return str.getBytes(encoding).length;
            } catch (Exception e) {
                return 0;
            }
        }
    }

    //更改编码
    public static String compress(String st) throws  Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(out);
        gzipOutputStream.write(st.getBytes());
        gzipOutputStream.close();
        return out.toString("ISO-8859-1");
    }

    //获取随机数
    public static String getRandom(int size) {
        StringBuffer sb = new StringBuffer();//定义变长字符串
        Random random = new Random();
        //随机生成数字，并添加到字符串
        for (int i = 0; i < size; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }



    public static void main(String[] args) {

    }
}
