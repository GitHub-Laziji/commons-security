package org.laziji.commons.security.captcha;


import java.util.Random;

public class CharacterCaptcha extends BaseCaptcha {

    private static final String RANGE = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    private int validityTime;
    private String value;

    public CharacterCaptcha() {
        this(4, 300000);
    }

    public CharacterCaptcha(int length, int validityTime) {
        this.validityTime = validityTime;
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char ch = RANGE.charAt(random.nextInt(RANGE.length()));
            stringBuilder.append(ch);
        }
        value = stringBuilder.toString();
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
