package org.laziji.commons.security.captcha;


public abstract class BaseCaptcha implements Captcha {

    private final long createTime = System.currentTimeMillis();


    public abstract int getValidityTime();

    @Override
    public boolean isNotEffective() {
        return !isEffective();
    }

    @Override
    public boolean isEffective() {
        return createTime + getValidityTime() > System.currentTimeMillis();
    }

    @Override
    public boolean verification(String value) {
        return value != null && value.equals(getValue());
    }

    @Override
    public boolean fuzzyVerification(String value) {
        return value != null && getValue() != null && value.toLowerCase().equals(getValue().toLowerCase());
    }
}
