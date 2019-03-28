package com.kcy.system.controller;

import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.utils.DateUtils;
import com.kcy.common.utils.Misc;
import com.kcy.system.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

@Api(value = "文件上传")
@RestController
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/wangEditorUpload")
    @ApiOperation(value = "上传图片", notes = "给wangEditor富文本编辑器配置图片上传接口")
    public ResponseWrapper wangEditorUpload(MultipartFile[] files, HttpServletResponse response) throws IllegalStateException, IOException {
        ResponseWrapper responseWrapper = fileUploadService.uploadImage(files);
        if(responseWrapper.isSuccess()) {
            return responseWrapper;
        }
        return ResponseUtils.errorResponse("上传失败");
    }
}
