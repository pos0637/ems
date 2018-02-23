package com.furongsoft.base.entities;

/**
 * 分页列表请求
 *
 * @author Alex
 */
public class PageRequest {
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

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 页内数据长度
     */
    private int pageSize;
}
