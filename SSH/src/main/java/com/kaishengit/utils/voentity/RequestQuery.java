package com.kaishengit.utils.voentity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/30
 */

public class RequestQuery {

    /**
     * 查询的列名
     */
    private String queryName;
    /**
     * 查询方法 例如 like eq 等
     */
    private String queryMethod;

    /**
     * 查询条件的值
     */
    private Object queryValue;


    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryMethod() {
        return queryMethod;
    }

    public void setQueryMethod(String queryMethod) {
        this.queryMethod = queryMethod;
    }

    public Object getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(Object queryValue) {
        this.queryValue = queryValue;
    }

}
