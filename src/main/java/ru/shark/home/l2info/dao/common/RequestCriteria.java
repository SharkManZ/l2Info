package ru.shark.home.l2info.dao.common;

import java.util.List;

public class RequestCriteria {
    private int page;
    private int size;
    private List<RequestFilter> filters;

    public RequestCriteria(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<RequestFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<RequestFilter> filters) {
        this.filters = filters;
    }
}
