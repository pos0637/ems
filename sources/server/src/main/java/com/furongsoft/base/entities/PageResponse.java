package com.furongsoft.base.entities;

import com.baomidou.mybatisplus.plugins.Page;
import com.furongsoft.base.restful.entities.RestResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * 分页列表请求返回数据
 *
 * @author Alex
 */
public class PageResponse<T> extends RestResponse {
    /**
     * 数据
     */
    class Data {
        /**
         * 列表
         */
        private List<T> list;

        private int pageNum;

        private int pageSize;

        private int pages;

        private int total;

        Data(List<T> list, int pageNum, int pageSize, int pages, int total) {
            this.list = list;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.pages = pages;
            this.total = total;
        }

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
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

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public PageResponse(HttpStatus status, Page<T> page) {
        super(status);
        setData(new Data(page.getRecords(), page.getCurrent(), page.getSize(), page.getPages(), page.getTotal()));
    }
}
