package org.laziji.commons.security.form;

import javax.validation.constraints.NotNull;

public class PageQuery<T extends WhereQuery> extends BaseForm {

    @NotNull
    private Long page;

    @NotNull
    private Long size;

    @NotNull
    private T where;

    @Override
    public boolean verify() {
        return super.verify() && where.verify();
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public T getWhere() {
        return where;
    }

    public void setWhere(T where) {
        this.where = where;
    }

}
