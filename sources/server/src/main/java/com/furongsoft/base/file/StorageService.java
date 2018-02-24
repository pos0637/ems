package com.furongsoft.base.file;

import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.base.file.mappers.AttachmentDao;
import com.furongsoft.base.misc.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Service
@Transactional
public class StorageService {
    private final AttachmentDao mAttachmentDao;

    @Value("${upload.path}")
    private String mUploadPath;

    @Autowired
    public StorageService(AttachmentDao attachmentDao) {
        this.mAttachmentDao = attachmentDao;
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件索引
     * @throws BaseException 异常
     */
    public String uploadFile(MultipartFile file) throws BaseException {
        Attachment attachment = new Attachment("", "", "", 0, "", 0);
        this.mAttachmentDao.insert(attachment);

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 文件名规则: 父路径 + UUID + 扩展名
        try {
            String parentPath = ResourceUtils.getURL("classpath:").getPath();
            File dest = new File(parentPath + mUploadPath + "/" + attachment.getId() + "." + suffixName);
            if (!dest.getParentFile().exists()) {
                if (!dest.getParentFile().mkdirs()) {
                    throw new BaseException.UploadFileFailException();
                }
            }

            file.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            Tracker.error(e);
            throw new BaseException.UploadFileFailException();
        }

        attachment.setName(attachment.getId());
        attachment.setType(suffixName);
        attachment.setSize(file.getSize());
        attachment.setHash("");
        this.mAttachmentDao.updateById(attachment);

        return attachment.getId();
    }
}
