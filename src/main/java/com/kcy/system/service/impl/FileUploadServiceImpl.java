package com.kcy.system.service.impl;

import com.kcy.common.constant.WebConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.utils.DateUtils;
import com.kcy.common.utils.Misc;
import com.kcy.common.utils.UploadUtils;
import com.kcy.system.service.FileUploadService;
import lombok.extern.log4j.Log4j;
import org.beetl.ext.fn.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    //上传图片
    public ResponseWrapper uploadImage(MultipartFile[] files) throws IOException {
        List<String> urls = new ArrayList();
        for(MultipartFile file : files) {
            //获取文件原名称
            String fileName = file.getOriginalFilename();
            //获取文件类型
            String type = fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            //文件校验
            if(UploadUtils.filesCheckout(file)) {
                //获取服务器路径
                String realPath = "/millionFiles/image/";
                //获取文件日期格式
                String timePaperFormat = UploadUtils.getTimePaperFormat();
                //获取文件名
                String trueFileName = DateUtils.getSystemCurrentTime().toString();
                //获取全文件名
                String trueFileNameType = trueFileName + "." + type;
                //获取全路径
                String path = realPath + timePaperFormat + trueFileNameType;
                System.out.println("图片存放的路径:" + path);
                File dirFile=new File(realPath + timePaperFormat);
                if(!dirFile.exists()){
                    dirFile.mkdirs();
                }
                //保存图片
                file.transferTo(new File(path));
                urls.add("http://localhost:8085/" + path);
            } else {
                log.info("上传失败:" + fileName);
                continue;
            }
        }
        return ResponseUtils.successResponse("paths", urls, "上传成功!");
    }
}
