package org.laziji.commons.security.captcha;

public interface Captcha {

    boolean isEffective();

    boolean isNotEffective();

    boolean verification(String value);

    boolean fuzzyVerification(String value);

    String getValue();

    long getCreationTime();

    long getEndTime();
}
