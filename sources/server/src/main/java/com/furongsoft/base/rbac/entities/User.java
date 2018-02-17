package com.furongsoft.base.rbac.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author Alex
 */
@Entity
@Table(name = "t_sys_user")
public class User extends BaseEntity implements Serializable {
    /**
     * 索引
     */
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 登录账户
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(32) COMMENT '登录账户'")
    private String userName;

    /**
     * 密码
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(64) COMMENT '密码'")
    private String password;

    /**
     * 盐值
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(64) COMMENT '盐值'")
    private String salt;

    /**
     * 状态
     */
    @Column(columnDefinition = "INT(10) COMMENT '状态'")
    private Integer status;

    /**
     * 姓名
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '姓名'")
    private String name;

    /**
     * 性别
     */
    @Column(columnDefinition = "INT(10) COMMENT '性别'")
    private Integer sex;

    /**
     * 年龄
     */
    @Column(columnDefinition = "INT(10) COMMENT '年龄'")
    private Integer age;

    /**
     * 生日
     */
    @Column(columnDefinition = "DATETIME COMMENT '生日'")
    private Date birthday;

    /**
     * 头衔
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '头衔'")
    private String title;

    /**
     * 头衔
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '头衔'")
    private String title2;

    /**
     * 头衔
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '头衔'")
    private String title3;

    /**
     * 工作单位
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '工作单位'")
    private String company;

    /**
     * 工作单位
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '工作单位'")
    private String company2;

    /**
     * 工作单位
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '工作单位'")
    private String company3;

    /**
     * 工作地址
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '工作地址'")
    private String businessAddress;

    /**
     * 工作地址
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '工作地址'")
    private String businessAddress2;

    /**
     * 住宅地址
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '住宅地址'")
    private String address;

    /**
     * 住宅地址
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '住宅地址'")
    private String address2;

    /**
     * 电子邮件
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '电子邮件'")
    private String email;

    /**
     * 电子邮件
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '电子邮件'")
    private String email2;

    /**
     * 电子邮件
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '电子邮件'")
    private String email3;

    /**
     * 网站
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '网站'")
    private String web;

    /**
     * 网站
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '网站'")
    private String web2;

    /**
     * 网站
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '网站'")
    private String web3;

    /**
     * 移动电话
     */
    @Column(columnDefinition = "VARCHAR(32) COMMENT '移动电话'")
    private String mobile;

    /**
     * 移动电话
     */
    @Column(columnDefinition = "VARCHAR(32) COMMENT '移动电话'")
    private String mobile2;

    /**
     * 固定电话
     */
    @Column(columnDefinition = "VARCHAR(32) COMMENT '固定电话'")
    private String telephone;

    /**
     * 固定电话
     */
    @Column(columnDefinition = "VARCHAR(32) COMMENT '固定电话'")
    private String telephone2;

    /**
     * 社交软件账号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '社交软件账号'")
    private String snsAccount;

    /**
     * 社交软件账号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '社交软件账号'")
    private String snsAccount2;

    /**
     * 社交软件账号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '社交软件账号'")
    private String snsAccount3;

    /**
     * 国家
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '国家'")
    private String country;

    /**
     * 省份/州
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '省份/州'")
    private String province;

    /**
     * 市/县
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '市/县'")
    private String city;

    /**
     * 区/镇
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '区/镇'")
    private String town;

    /**
     * 街道
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '街道'")
    private String street;

    /**
     * 邮编
     */
    @Column(columnDefinition = "VARCHAR(32) COMMENT '邮编'")
    private String zip;

    /**
     * 证件
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '证件'")
    private String identification;

    /**
     * 证件
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '证件'")
    private String identification2;

    /**
     * 证件
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '证件'")
    private String identification3;

    /**
     * 头像
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '头像'")
    private String iconUrl;

    /**
     * 照片
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '照片'")
    private String pictureUrl;

    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany2() {
        return company2;
    }

    public void setCompany2(String company2) {
        this.company2 = company2;
    }

    public String getCompany3() {
        return company3;
    }

    public void setCompany3(String company3) {
        this.company3 = company3;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessAddress2() {
        return businessAddress2;
    }

    public void setBusinessAddress2(String businessAddress2) {
        this.businessAddress2 = businessAddress2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getWeb2() {
        return web2;
    }

    public void setWeb2(String web2) {
        this.web2 = web2;
    }

    public String getWeb3() {
        return web3;
    }

    public void setWeb3(String web3) {
        this.web3 = web3;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getSnsAccount() {
        return snsAccount;
    }

    public void setSnsAccount(String snsAccount) {
        this.snsAccount = snsAccount;
    }

    public String getSnsAccount2() {
        return snsAccount2;
    }

    public void setSnsAccount2(String snsAccount2) {
        this.snsAccount2 = snsAccount2;
    }

    public String getSnsAccount3() {
        return snsAccount3;
    }

    public void setSnsAccount3(String snsAccount3) {
        this.snsAccount3 = snsAccount3;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getIdentification2() {
        return identification2;
    }

    public void setIdentification2(String identification2) {
        this.identification2 = identification2;
    }

    public String getIdentification3() {
        return identification3;
    }

    public void setIdentification3(String identification3) {
        this.identification3 = identification3;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}