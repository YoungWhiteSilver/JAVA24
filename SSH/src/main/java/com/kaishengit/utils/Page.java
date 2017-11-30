package com.kaishengit.utils;

import java.util.List;

/**
 * 分页
 *
 * @author 67675
 * @param <T>
 */
public class Page<T> {

    /**
     * 总页数
     */
    private Long pageTotal;

    /**
     *当前页
     */
    private Long pageNow;
    /**
     *当前页的元素
     */
    private List<T> pageItems;


    /**
     *每页条数
     */
    private Long pageSize = 10L;
    /**
     *起始行数
     */
    private Long pageRowStart;
    /**
     *总行数
     */
    private Long pageRowTotal;

    public Page(Long pageNow, Long count) {

        this.pageRowTotal = count;

        pageTotal = pageRowTotal / pageSize;

        if (pageTotal % pageSize > 0) {

            pageTotal++;

        }

        if (pageNow < 1) {

            pageNow = 1L;

        }

        if (pageNow > pageTotal) {

            pageNow = pageTotal;

        }

        this.pageNow = pageNow;

        pageRowStart = (pageNow - 1) * pageSize;

    }

    public Long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Long getPageNow() {
        return pageNow;
    }

    public void setPageNow(Long pageNow) {
        this.pageNow = pageNow;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageRowStart() {
        return pageRowStart;
    }

    public void setPageRowStart(Long pageRowStart) {
        this.pageRowStart = pageRowStart;
    }

    public Long getPageRowTotal() {
        return pageRowTotal;
    }

    public void setPageRowTotal(Long pageRowTotal) {
        this.pageRowTotal = pageRowTotal;
    }
}
