package com.furongsoft.ems.entities.cms;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furongsoft.base.entities.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 新闻
 *
 * @author Alex
 */
@Entity
@Table(name = "t_cms_news")
@TableName("t_cms_news")
public class News extends BaseEntity implements Serializable {
    /**
     * 索引
     */
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(32) COMMENT 'UUID'")
    private String id;

    /**
     * 新闻分类索引
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(32) COMMENT '新闻分类索引'")
    private String categoryId;

    /**
     * 名称
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(64) COMMENT '名称'")
    private String name;

    /**
     * 状态
     */
    @Column(nullable = false, columnDefinition = "INT(1) default 0 COMMENT '状态:0 启用, 1 禁用'")
    private Integer state;

    /**
     * 简述
     */
    @Column(columnDefinition = "TEXT COMMENT '简述'")
    private String description;

    /**
     * 信息
     */
    @Column(columnDefinition = "TEXT COMMENT '信息'")
    private String information;

    /**
     * 图标路径
     */
    @Transient
    @TableField(exist = false)
    private String iconPath;

    /**
     * 图标
     */
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(32) COMMENT '图标'")
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
