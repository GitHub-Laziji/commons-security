package org.laziji.commons.security.captcha;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class HashCaptcha extends BaseCaptcha {

    private String privateKey;
    private int validityTime;
    private String value;

    public HashCaptcha() {
        this(10000, 300000);
    }

    public HashCaptcha(int range, int validityTime) {
        this.validityTime = validityTime;
        Random random = new Random();
        String randomString = DigestUtils.md5Hex(random.nextInt() + "");
        int randomNumber = random.nextInt(range);
        value = randomString + DigestUtils.md5Hex(randomString + randomNumber);
        privateKey = DigestUtils.md5Hex(randomNumber + randomString);
    }

    @Override
    public int getValidityTime() {
        return validityTime;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean fuzzyVerification(String value) {
        return verification(value);
    }

    @Override
    public boolean verification(String value) {
        return value != null && value.equals(privateKey);
    }
}
