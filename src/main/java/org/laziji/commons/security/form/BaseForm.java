package org.laziji.commons.security.form;

import com.alibaba.fastjson.JSON;
import org.hibernate.validator.HibernateValidator;

import javax.validation.Validation;
import javax.validation.Validator;

public abstract class BaseForm implements Form{

    private final static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure()
            .failFast(true).buildValidatorFactory().getValidator();

    @Override
    public boolean verification() {
        return validator.validate(this).size() == 0;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
