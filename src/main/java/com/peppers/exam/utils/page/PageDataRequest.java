package com.peppers.exam.utils.page;

/**
 * @author peppers
 * @description
 * @since 2020/12/20
 **/
public class PageDataRequest<T> extends PageQuery {
    private static final long serialVersionUID = -342860844699580386L;
    private T query;
    private boolean needCount = true;

    public PageDataRequest() {
    }

    public int getOffset() {
        return (this.getPageNumber() - 1) * this.getPageSize();
    }

    public T getQuery() {
        return this.query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    public boolean isNeedCount() {
        return this.needCount;
    }

    public void setNeedCount(boolean needCount) {
        this.needCount = needCount;
    }

    public String toString() {
        return "PageDataRequest{query=" + this.query + ", pageNo=" + this.getPageNo() + ", pageSize=" + this.getPageSize() + ", needCount=" + this.needCount + '}';
    }
}
