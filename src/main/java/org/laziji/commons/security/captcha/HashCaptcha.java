package org.laziji.commons.security.captcha;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class HashCaptcha extends BaseCaptcha {

    private String publicText;
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
        this.publicText = randomString + DigestUtils.md5Hex(randomString + randomNumber);
        value = DigestUtils.md5Hex(randomNumber + randomString);
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
