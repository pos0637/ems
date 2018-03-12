package com.furongsoft.base.entities;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.restful.entities.RestResponse;
import org.springframework.http.HttpStatus;

/**
 * 分页列表请求返回数据
 *
 * @author Alex
 */
public class PageResponse<T> extends RestResponse {
    /**
     * 记录总数
     */
    private int total;

    public PageResponse(HttpStatus status, Page<T> page) {
        super(status);
        setData(page.getRecords());
        setTotal(page.getTotal());
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
