package javaweb.tlias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javaweb.tlias.pojo.Result;
import javaweb.tlias.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    // 上传文件
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) throws Exception {
        log.info("上传文件: {}", file.getOriginalFilename());
        // 上传文件到阿里云OSS
        byte[] content = file.getBytes();
        String url = aliyunOSSOperator.upload(content, file.getOriginalFilename());
        log.info("上传文件到阿里云OSS成功, url: {}", url);
        return Result.success(url);
    }
}
