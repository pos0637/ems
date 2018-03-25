package com.furongsoft.ems.entities.cms;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furongsoft.base.entities.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 公司描述
 *
 * @author Alex
 */
@Entity
@Table(name = "t_cms_profile")
@TableName("t_cms_profile")
public class Profile extends BaseEntity implements Serializable {
    /**
     * 索引
     */
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(32) COMMENT 'UUID'")
    private String id;

    /**
     * 名称
     */
    @Column(columnDefinition = "TEXT COMMENT '名称'")
    private String name;

    /**
     * 链接
     */
    @Column(columnDefinition = "TEXT COMMENT '链接'")
    private String link;

    /**
     * 图片标识
     */
    @Transient
    @TableField(exist = false)
    private String picturePath;

    /**
     * 图片
     */
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(32) COMMENT '图片'")
    private String picture;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
