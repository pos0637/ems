package com.furongsoft.cms.addons.companyWebsite.entities;

import com.baomidou.mybatisplus.annotations.TableName;
import com.furongsoft.base.entities.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 工作
 *
 * @author Alex
 */
@Entity
@Table(name = "t_cms_company_website_job")
@TableName("t_cms_company_website_job")
public class Job extends BaseEntity implements Serializable {
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
     * 状态
     */
    @Column(nullable = false, columnDefinition = "INT(1) default 0 COMMENT '状态:0 启用, 1 禁用'")
    private Integer state;

    /**
     * 优先级
     */
    @Column(columnDefinition = "INT(4) default 0 COMMENT '优先级'")
    private Integer priority;

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
}
