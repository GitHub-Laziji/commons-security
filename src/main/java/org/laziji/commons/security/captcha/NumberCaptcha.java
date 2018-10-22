package org.laziji.commons.security.captcha;

import org.apache.commons.lang3.RandomStringUtils;

public class NumberCaptcha extends BaseCaptcha{

    private static final String CHARS = "0123456789";

    private int validityTime;
    private String value;

    public NumberCaptcha() {
        this(6,300000);
    }

    public NumberCaptcha(int length, int validityTime) {
        this.validityTime = validityTime;
        this.value = RandomStringUtils.random(length,CHARS);
    }

    @Override
    public int getValidityTime() {
        return validityTime;
    }

    @Override
    public String getValue() {
        return value;
    }
}
