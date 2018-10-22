package org.laziji.commons.security.captcha;

import java.io.IOException;
import java.io.OutputStream;

public interface Captcha {

    boolean isEffective();

    boolean isNotEffective();

    boolean verification(String value);

    boolean fuzzyVerification(String value);

    String getValue();

    long getCreationTime();

    long getEndTime();
}
