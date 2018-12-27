package org.laziji.commons.security.interceptor;

import org.laziji.commons.security.web.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class CrossDomainInterceptor implements HandlerInterceptor {

    private static final String TOKEN_KEY = "token";

    private Set<RequestMethod> allowMethods = new HashSet<>();

    private String accessControlAllowMethods;

    private boolean allowCredentials = false;

    private boolean token = false;


    public CrossDomainInterceptor() {
        allowMethods.add(RequestMethod.OPTIONS);
        accessControlAllowMethods = "OPTIONS";
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        RequestMethod method = RequestMethod.parse(request.getMethod());
        if (method == null || !allowMethods.contains(method)) {
            return false;
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Methods", accessControlAllowMethods);
        if (allowCredentials) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }
        if (token) {
            response.setHeader("Access-Control-Expose-Headers", TOKEN_KEY);
            HttpSession session = request.getSession();
            response.setHeader(TOKEN_KEY, session.getId());
        }
        return method != RequestMethod.OPTIONS;
    }

    public void addAllowMethod(RequestMethod method) {
        if (method == null) {
            return;
        }
        if (!allowMethods.contains(method)) {
            allowMethods.add(method);
            accessControlAllowMethods += "," + method.toString();
        }
    }

    public boolean isAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }
}
