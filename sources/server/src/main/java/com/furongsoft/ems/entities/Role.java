package com.furongsoft.ems.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色
 *
 * @author Alex
 */
@Entity
@Table(name = "t_sys_role")
public class Role extends BaseEntity implements Serializable {
    /**
     * 索引
     */
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * 名称
     */
    @Column(columnDefinition = "VARCHAR(64) COMMENT '名称'")
    private String name;

    /**
     * 状态
     */
    @Column(columnDefinition = "INT(10) COMMENT '状态'")
    private Integer status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
