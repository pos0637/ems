package com.furongsoft.cms.addons.schoolWebsite.entities;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 学校
 *
 * @author Alex
 */
@Entity
@Table(name = "t_cms_school_website_school")
@TableName("t_cms_school_website_school")
public class School {
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
     * 信息
     */
    @Column(columnDefinition = "TEXT COMMENT '信息'")
    private String information;

    /**
     * 组织架构
     */
    @Column(columnDefinition = "TEXT COMMENT '组织架构'")
    private String structure;

    /**
     * 规划
     */
    @Column(columnDefinition = "TEXT COMMENT '规划'")
    private String planning;

    /**
     * 联系方式
     */
    @Column(columnDefinition = "TEXT COMMENT '联系方式'")
    private String contact;

    /**
     * 电子邮箱
     */
    @Column(columnDefinition = "TEXT COMMENT '电子邮箱'")
    private String email;

    /**
     * 地址
     */
    @Column(columnDefinition = "TEXT COMMENT '地址'")
    private String address;

    /**
     * 联系电话
     */
    @Column(columnDefinition = "TEXT COMMENT '联系电话'")
    private String phone;

    /**
     * 权限
     */
    @Column(columnDefinition = "TEXT COMMENT '权限'")
    private String copyright;

    /**
     * 证书
     */
    @Column(columnDefinition = "TEXT COMMENT '证书'")
    private String credential;

    /**
     * 标语1
     */
    @Column(columnDefinition = "TEXT COMMENT '标语1'")
    private String slogan1;

    /**
     * 标语2
     */
    @Column(columnDefinition = "TEXT COMMENT '标语2'")
    private String slogan2;

    /**
     * 学校标识
     */
    @Transient
    @TableField(exist = false)
    private String logoPath;

    /**
     * 学校标识
     */
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(32) COMMENT '学校标识'")
    private String logo;

    /**
     * 欢迎图片
     */
    @Transient
    @TableField(exist = false)
    private String welcomePicturePath;

    /**
     * 欢迎图片
     */
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(32) COMMENT '欢迎图片'")
    private String welcomePicture;

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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getSlogan1() {
        return slogan1;
    }

    public void setSlogan1(String slogan1) {
        this.slogan1 = slogan1;
    }

    public String getSlogan2() {
        return slogan2;
    }

    public void setSlogan2(String slogan2) {
        this.slogan2 = slogan2;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWelcomePicturePath() {
        return welcomePicturePath;
    }

    public void setWelcomePicturePath(String welcomePicturePath) {
        this.welcomePicturePath = welcomePicturePath;
    }

    public String getWelcomePicture() {
        return welcomePicture;
    }

    public void setWelcomePicture(String welcomePicture) {
        this.welcomePicture = welcomePicture;
    }
}
