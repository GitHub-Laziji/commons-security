package org.laziji.commons.security.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.laziji.commons.security.exception.FormErrorException;
import org.laziji.commons.security.form.Form;

@Aspect
public class FormAspectInterceptor {

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        for (Object arg : point.getArgs()) {
            if (arg instanceof Form) {
                Form form = (Form) arg;
                if (!form.verification()) {
                    throw new FormErrorException();
                }
            }
        }
        return point.proceed();
    }
}
