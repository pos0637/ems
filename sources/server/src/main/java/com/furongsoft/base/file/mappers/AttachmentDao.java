package com.furongsoft.base.file.mappers;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.furongsoft.base.file.entities.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 文件表操作对象
 *
 * @author Alex
 */
@Mapper
@Component
public interface AttachmentDao extends BaseMapper<Attachment> {
}
