package com.furongsoft.ems.entities.cms;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furongsoft.base.entities.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 公司
 *
 * @author Alex
 */
@Entity
@Table(name = "t_cms_company")
@TableName("t_cms_company")
public class Company extends BaseEntity implements Serializable {
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
     * 公司标识
     */
    @Transient
    @TableField(exist = false)
    private String logoPath;

    /**
     * 公司标识
     */
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(32) COMMENT '公司标识'")
    private String logo;

    /**
     * 公司网站二维码
     */
    @Transient
    @TableField(exist = false)
    private String barcodePath;

    /**
     * 公司网站二维码
     */
    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(32) COMMENT '公司网站二维码'")
    private String barcode;

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

    public String getBarcodePath() {
        return barcodePath;
    }

    public void setBarcodePath(String barcodePath) {
        this.barcodePath = barcodePath;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
