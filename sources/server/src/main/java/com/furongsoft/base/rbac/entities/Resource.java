package com.furongsoft.base.rbac.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 资源
 *
 * @author Alex
 */
@Entity
@Table(name = "t_sys_resource")
public class Resource extends BaseEntity implements Serializable {
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
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(64) COMMENT '名称'")
    private String name;

    /**
     * 类型
     */
    @Column(nullable = false, columnDefinition = "INT(10) COMMENT '类型:0 模块, 1 数据'")
    private Integer type;

    /**
     * 路径
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) COMMENT '路径'")
    private String path;

    /**
     * 状态
     */
    @Column(nullable = false, columnDefinition = "INT(10) COMMENT '状态:0 启用, 1 禁用'")
    private Integer state;

    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    public Resource() {
    }

    public Resource(String name, Integer type, String path, Integer state, String remark) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.state = state;
        this.remark = remark;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
