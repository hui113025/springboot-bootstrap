package com.module.product.web;

import com.google.common.collect.Maps;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.exception.ServiceException;
import com.module.product.common.util.ResponseGenerator;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by  on 2017/5/25.
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${file.upload.server.origin}")
    private String origin;
    @Value("${file.upload.server.path}")
    private String realPath;
    @Value("${file.upload.size.max.image}")
    private int imageFileMaxSize;

    @RequestMapping("/upload/image")
    @ResponseBody
    public ResponseJsonModel uploadImage(@RequestParam MultipartFile imageFile, @RequestParam(defaultValue = "images") String dirName) {
        validateImageFile(imageFile);
        String dateDIR = DateFormatUtils.format(new Date(), "yyyyMMdd");
        String path = dirName + "/" + dateDIR + "/";
        // 为上传的文件进行重命名（避免同名文件的相互覆盖）使用UUID + 文件后缀
        String suffix = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;
        File file = new File(realPath + path + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //将临时文件保存到磁盘
        try {
            imageFile.transferTo(file);
        } catch (IOException e) {
            logger.error("文件上传失败", e);
            throw new ServiceException("上传失败：" + e.getMessage());
        }
        Map<Object, Object> data = Maps.newLinkedHashMap();
        data.put("url", origin + path + fileName);
        return ResponseGenerator.genSuccess(data);
    }

    private void validateImageFile(MultipartFile imageFile) throws ServiceException {
        //校验类型
        if (imageFile.getContentType().indexOf("image") == -1) {
            throw new ServiceException("上传失败，仅支持图片类型！");
        }
        if (imageFile.getSize() > (imageFileMaxSize * 1024 * 1024)) {
            throw new ServiceException("上传失败，文件大小不能超过" + imageFileMaxSize + "MB！");
        }
    }
}
