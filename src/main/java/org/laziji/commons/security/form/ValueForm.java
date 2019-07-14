package org.laziji.commons.security.form;

import javax.validation.constraints.NotNull;

public class ValueForm extends BaseForm {

    @NotNull
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
