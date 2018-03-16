package com.furongsoft.base.file;

import com.furongsoft.base.exceptions.BaseException;
import com.furongsoft.base.file.entities.Attachment;
import com.furongsoft.base.file.mappers.AttachmentDao;
import com.furongsoft.base.misc.StringUtils;
import com.furongsoft.base.misc.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * 文件存储服务
 *
 * @author Alex
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class StorageService {
    private final AttachmentDao attachmentDao;

    @Value("${upload.path}")
    private String mUploadPath;

    @Autowired
    public StorageService(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
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
        attachmentDao.insert(attachment);

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newName = attachment.getId() + suffixName;

        // 文件名规则: 父路径 + UUID + 扩展名
        try {
            File parent = new File(ResourceUtils.getURL("classpath:").getPath());
            File target = new File(String.format("%s%s/%s", parent.getParentFile().getAbsolutePath(), mUploadPath, newName));
            if (!target.getParentFile().exists()) {
                if (!target.getParentFile().mkdirs()) {
                    throw new BaseException.UploadFileFailException();
                }
            }

            file.transferTo(target);
        } catch (IllegalStateException | IOException e) {
            Tracker.error(e);
            throw new BaseException.UploadFileFailException();
        }

        attachment.setName(newName);
        attachment.setType(suffixName);
        attachment.setSize(file.getSize());
        attachment.setHash("");
        attachmentDao.updateById(attachment);

        return newName;
    }

    /**
     * 根据文件名称获取索引
     *
     * @param name 文件名称
     * @return 索引
     * @throws BaseException 异常
     */
    public Serializable getFileId(String name) throws BaseException {
        if (StringUtils.isNullOrEmpty(name)) {
            return null;
        }

        Attachment attachment = attachmentDao.selectOne(new Attachment(name));
        if (attachment == null) {
            throw new BaseException.IllegalArgumentException();
        }

        return attachment.getId();
    }
}
