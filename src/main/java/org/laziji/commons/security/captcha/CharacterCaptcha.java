package org.laziji.commons.security.captcha;

import org.apache.commons.lang3.RandomStringUtils;

public class CharacterCaptcha extends BaseCaptcha {

    private static final String CHARS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    private int validityTime;
    private String value;

    public CharacterCaptcha() {
        this(4, 300000);
    }

    public CharacterCaptcha(int length, int validityTime) {
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
