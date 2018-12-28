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

    private Set<String> allowHeaders = new HashSet<>();

    private String accessControlAllowHeaders;

    private Integer maxAge = 86400;

    private boolean allowCredentials = false;

    private boolean token = false;


    public CrossDomainInterceptor() {
        addAllowMethod(RequestMethod.OPTIONS);

        addAllowHeader("X-Requested-With");
        addAllowHeader("Content-Type");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        RequestMethod method = RequestMethod.parse(request.getMethod());
        if (method == null || !allowMethods.contains(method)) {
            return false;
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", accessControlAllowHeaders);
        response.setHeader("Access-Control-Allow-Methods", accessControlAllowMethods);
        if (maxAge != null && maxAge > 0) {
            response.setHeader("Access-Control-Max-Age", maxAge.toString());
        }
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
        if (allowMethods.contains(method)) {
            return;
        }
        allowMethods.add(method);
        if (accessControlAllowMethods == null) {
            accessControlAllowMethods = method.toString();
            return;
        }
        accessControlAllowMethods += "," + method.toString();
    }

    public void addAllowHeader(String header) {
        if (header == null) {
            return;
        }
        header = header.toLowerCase();
        if (allowHeaders.contains(header)) {
            return;
        }
        allowHeaders.add(header);
        if (accessControlAllowHeaders == null) {
            accessControlAllowHeaders = header;
            return;
        }
        accessControlAllowHeaders += "," + header;
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

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
}
