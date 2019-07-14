package org.laziji.commons.security.form;

import javax.validation.constraints.NotNull;

public class IdForm extends BaseForm {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
