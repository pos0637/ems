package com.furongsoft.ems.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类型
 *
 * @author Alex
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /**
     * 创建用户
     */
    @Column(nullable = false, columnDefinition = "BIGINT(20) COMMENT '创建用户'")
    private Long createUser;

    /**
     * 创建时间
     */
    @Column(nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

    /**
     * 最后修改用户
     */
    @Column(nullable = false, columnDefinition = "BIGINT(20) COMMENT '最后修改用户'")
    private Long lastModifyUser;

    /**
     * 最后修改时间
     */
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyTime;

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(Long lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
