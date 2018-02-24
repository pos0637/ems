package com.furongsoft.base.file;

import com.furongsoft.base.entities.UploadFileResponse;
import com.furongsoft.base.misc.Tracker;
import com.furongsoft.base.restful.entities.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 *
 * @author Alex
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private final StorageService mStorageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.mStorageService = storageService;
    }

    @RequestMapping(value = "/test")
    public RestResponse test() {
        this.mStorageService.uploadFile(null);
        return new RestResponse(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public RestResponse upload(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return new RestResponse(HttpStatus.BAD_REQUEST);
        }

        String uuid = mStorageService.uploadFile(file);
        Tracker.file("upload: " + uuid);
        
        return new UploadFileResponse(HttpStatus.OK, uuid);
    }
}
