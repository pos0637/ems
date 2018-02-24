package com.furongsoft.base.entities;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 分页列表请求
 *
 * @author Alex
 */
public class PageRequest {
    /**
     * 页码
     */
    private int pageNum;

    /**
     * 页内数据长度
     */
    private int pageSize;

    /**
     * 获取分页对象
     *
     * @return 分页对象
     */
    public <T> Page<T> getPage() {
        return new Page<T>(pageNum, pageSize);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
