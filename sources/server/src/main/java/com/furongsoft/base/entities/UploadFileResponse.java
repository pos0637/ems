package com.furongsoft.base.entities;

import com.furongsoft.base.restful.entities.RestResponse;
import org.springframework.http.HttpStatus;

/**
 * 上传文件响应内容
 *
 * @author Alex
 */
public class UploadFileResponse extends RestResponse {
    /**
     * 数据
     */
    class Data {
        /**
         * 文件路径
         */
        private String filePath;

        Data(String filePath) {
            this.filePath = filePath;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }

    public UploadFileResponse(HttpStatus status, String uuid) {
        super(status);
        setData(new Data(uuid));
    }
}
