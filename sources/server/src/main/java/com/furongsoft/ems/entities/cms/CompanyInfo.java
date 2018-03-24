package com.furongsoft.ems.entities.cms;

import com.baomidou.mybatisplus.annotations.TableName;
import com.furongsoft.base.entities.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 公司信息
 *
 * @author Alex
 */
@Entity
@Table(name = "t_cms_company_info")
@TableName("t_cms_company_info")
public class CompanyInfo extends BaseEntity implements Serializable {
    /**
     * 索引
     */
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(32) COMMENT 'UUID'")
    private String id;

    /**
     * 信息
     */
    @Column(columnDefinition = "TEXT COMMENT '信息'")
    private String information;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
