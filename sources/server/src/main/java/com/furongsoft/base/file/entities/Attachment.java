package com.furongsoft.base.file.entities;

import com.baomidou.mybatisplus.annotations.TableName;
import com.furongsoft.base.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 文件
 *
 * @author Alex
 */
@Entity
@Table(name = "t_sys_attachment")
@TableName("t_sys_attachment")
public class Attachment extends BaseEntity implements Serializable {
    /**
     * 索引
     */
    @Id
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(32) COMMENT 'UUID'")
    private String id;

    /**
     * 文件名称
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    private String name;

    /**
     * 类型
     */
    @Column(columnDefinition = "VARCHAR(32) COMMENT '类型'")
    private String type;

    /**
     * URL
     */
    @Column(columnDefinition = "VARCHAR(1024) COMMENT 'URL'")
    private String url;

    /**
     * 文件大小
     */
    @Column(columnDefinition = "BIGINT(20) COMMENT '文件大小'")
    private long size;

    /**
     * 文件哈希
     */
    @Column(columnDefinition = "VARCHAR(128) COMMENT '文件哈希'")
    private String hash;

    /**
     * 状态
     */
    @Column(columnDefinition = "INT(1) COMMENT '状态:0 启用, 1 禁用'")
    private Integer state;

    public Attachment() {
    }

    public Attachment(String name, String type, String url, long size, String hash, Integer state) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.size = size;
        this.hash = hash;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
