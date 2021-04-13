package com.gitee.inspire.dto;

public class PageQuery {

    private Integer page;

    public Integer size;

    public Integer getPage() {
        if (page == null) {
            return 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        if (size == null) {
            return 20;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
