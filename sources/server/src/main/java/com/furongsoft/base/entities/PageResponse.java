package com.furongsoft.base.entities;

import java.util.List;

/**
 * 分页列表请求返回数据
 *
 * @author Alex
 */
public class PageResponse<T> {
    /**
     * 结果
     */
    public class Results {
        /**
         * 数据
         */
        private Data data;

        public Results(List<T> data, int pageNum, int pageSize, int pages, int total) {
            this.data = new Data(data, pageNum, pageSize, pages, total);
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    /**
     * 数据
     */
    public class Data {
        /**
         * 列表
         */
        private List<T> list;

        private int pageNum;

        private int pageSize;

        private int pages;

        private int total;

        public Data(List<T> list, int pageNum, int pageSize, int pages, int total) {
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

    /**
     * 错误码
     */
    private int errorNo;

    /**
     * 结果
     */
    private Results results;

    public PageResponse(int errorNo, List<T> results, int pageNum, int pageSize, int pages, int total) {
        this.errorNo = errorNo;
        this.results = new Results(results, pageNum, pageSize, pages, total);
    }

    public int getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
