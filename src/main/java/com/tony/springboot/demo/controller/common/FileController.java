package com.tony.springboot.demo.controller.common;

import com.tony.springboot.demo.model.R;
import com.tony.springboot.demo.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

@Slf4j
@Api(tags = "文件管理")
@Validated
@RestController
@RequestMapping("/common/file")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping(value = "/upload", headers="content-type=multipart/form-data")
    public R fileUpload(@RequestParam("file") @RequestPart("file") MultipartFile file) {
        return fileService.fileUpload(file);
    }

    @ApiOperation("文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名", example = "abc.jpg", dataTypeClass = String.class, required = true)
    })
    @GetMapping("/download")
    public void fileDownload(@Validated @NotBlank String fileName, @ApiIgnore HttpServletResponse response) {
        fileService.fileDownload(fileName, response);
    }
}
