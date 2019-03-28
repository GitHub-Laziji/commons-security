package org.laziji.commons.security.form;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;

public abstract class BaseModelForm<T> extends BaseForm implements ModelForm<T>{

    @Override
    public T toBean() {
        return JSON.parseObject(JSON.toJSONString(this),
                ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
