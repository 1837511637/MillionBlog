package com.kcy.common.utils;

import com.kcy.common.constant.WebConst;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;

public class UploadUtils {

    //获取年月路径
    public static String getTimePaperFormat() {
        Calendar c = Calendar .getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        return year + "/" + (month+1) + "/";
    }

    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    //文件信息校验    精选
    public static boolean filesCheckout(MultipartFile multipartFile) {
        boolean bflag = true;
        //获取文件名
        String fileName = multipartFile.getOriginalFilename();

        if(multipartFile.getSize() == 0 && multipartFile.isEmpty()) {
            bflag = false;
        }
        if(!checkMediaType(getFileExt(fileName))) {
            bflag = false;
        }
        return bflag;
    }

    //判断后缀是否是图片
    private static boolean checkMediaType(String fileEnd) {
        Iterator<String> type = Arrays.asList(WebConst.allowIMG).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileEnd.equals(ext)) {
                return true;
            }
        }
        return false;
    }
}
