package com.kcy.system.service;

import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    ResponseWrapper uploadImage(MultipartFile[] files) throws IOException;
}
