package com.tony.springboot.demo.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.tony.springboot.demo.constant.Codes;
import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.model.dto.common.FileUploadDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

@Slf4j
@Service
public class FileService {

    private static final String UPLOAD_FILE_PATH = "/Users/tonychen/upload-file/";

    public R fileUpload(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (StrUtil.isBlank(originalFilename)) {
                return R.error(Codes.ILLEGAL_PARAM, "文件名为空");
            }
            String generateFileName = buildFileName(originalFilename);
            log.info("upload file , 原始文件名: {}, 原始文件大小: {}, 生成文件名: {}", file.getOriginalFilename(), file.getSize(), generateFileName);
            File directory = new File(UPLOAD_FILE_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file.transferTo(new File(UPLOAD_FILE_PATH, generateFileName));
            return R.ok(FileUploadDTO.builder().generateFileName(generateFileName).build());
        }catch (Exception e) {
            log.error("upload file error: ", e);
            return R.error(e.getMessage());
        }
    }

    private String buildFileName(String originalFilename) {
        String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        return String.format("%s_%s%s", name, DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_FORMATTER), suffix);
    }

    public void fileDownload(String fileName, HttpServletResponse response) {
        try {
            File file = new File(UPLOAD_FILE_PATH, fileName);
            if(!file.exists()) {
                log.error("download file {} not exists", fileName);
                return;
            }
            //下载形式，一般跟application/octet-stream一起使用
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            //如果单纯写这个也可以进行下载功能，表示以二进制流的形式
            response.setContentType("application/octet-stream");
            //文件大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream out = response.getOutputStream();
            FileUtil.writeToStream(file, out);
        }catch (Exception e) {
            log.error("download file error: ", e);
        }

    }
}
