package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.utils.AliOSSUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author moZiA
 * @date 2025/5/16 19:47
 * @description
 */

@RestController
@Slf4j
@Tag(name = "通用接口")
public class CommonController {


  @Resource
  private AliOSSUtils aliOSSUtils;

  @PostMapping("/admin/common/upload")
  public Result<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
    //调用阿里云OSS工具类进行文件上传
    String url = aliOSSUtils.upload(file);
    log.info("文件上传成功，{}", url);
    return Result.success(url);
  }


}