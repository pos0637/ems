package com.furongsoft.cms.addons.companyWebsite.entities;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furongsoft.base.entities.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 产品
 *
 * @author Alex
 */
@Entity
@Table(name = "t_cms_company_website_product")
@TableName("t_cms_company_website_product")
public class Product extends BaseEntity implements Serializable {
    /**
     * 索引
     */
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(32) COMMENT 'UUID'")
    private String id;

    /**
     * 产品分类索引
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(32) COMMENT '产品分类索引'")
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
     * 优先级
     */
    @Column(columnDefinition = "INT(4) default 0 COMMENT '优先级'")
    private Integer priority;

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
     * 详情
     */
    @Column(columnDefinition = "TEXT COMMENT '信息'")
    private String detail;

    /**
     * 展示价格
     */
    @Column(columnDefinition = "TEXT COMMENT '展示价格'")
    private Double price;

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

    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    /**
     * 推荐产品
     */
    @Column(nullable = false, columnDefinition = "INT(1) default 0 COMMENT '推荐产品:0 不推荐, 1 推荐'")
    private Integer recommend;

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }
}
