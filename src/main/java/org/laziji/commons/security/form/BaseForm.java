package org.laziji.commons.security.form;

import org.hibernate.validator.HibernateValidator;

import javax.validation.Validation;
import javax.validation.Validator;

public abstract class BaseForm {

    private final static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure()
            .failFast(true).buildValidatorFactory().getValidator();

    public boolean verification() {
        return validator.validate(this).size() == 0;
    }

}
