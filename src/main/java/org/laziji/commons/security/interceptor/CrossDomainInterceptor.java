package org.laziji.commons.security.interceptor;

import org.laziji.commons.security.web.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class CrossDomainInterceptor implements HandlerInterceptor {

    private Set<String> allowMethods = new HashSet<>();

    private StringBuilder accessControlAllowMethods = new StringBuilder();

    private Set<String> allowHeaders = new HashSet<>();

    private StringBuilder accessControlAllowHeaders = new StringBuilder();

    private Set<String> exposeHeaders = new HashSet<>();

    private StringBuilder accessControlExposeHeaders = new StringBuilder();

    private Integer maxAge = 86400;

    private boolean allowCredentials = false;


    public CrossDomainInterceptor() {
        addAllowMethod(RequestMethod.OPTIONS);

        addAllowHeader("X-Requested-With");
        addAllowHeader("Content-Type");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        RequestMethod method = RequestMethod.match(request.getMethod());
        if (method == null || !allowMethods.contains(method.name())) {
            return false;
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", accessControlAllowHeaders.toString());
        response.setHeader("Access-Control-Allow-Methods", accessControlAllowMethods.toString());
        if (maxAge != null && maxAge > 0) {
            response.setHeader("Access-Control-Max-Age", maxAge.toString());
        }
        if (allowCredentials) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }
        return method != RequestMethod.OPTIONS;
    }

    public void addAllowMethod(RequestMethod method) {
        addHeaderValueItem(allowMethods, accessControlAllowMethods, method.name());
    }

    public void addAllowHeader(String header) {
        addHeaderValueItem(allowHeaders, accessControlAllowHeaders, header);
    }

    public void addExposeHeaders(String header) {
        addHeaderValueItem(exposeHeaders, accessControlExposeHeaders, header);
    }

    public boolean isAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    private void addHeaderValueItem(Set<String> itemSet, StringBuilder headerValue, String item) {
        if (item == null) {
            return;
        }
        item = item.toLowerCase();
        if (itemSet.contains(item)) {
            return;
        }
        itemSet.add(item);
        if (headerValue.length() == 0) {
            headerValue.append(item);
            return;
        }
        headerValue.append(',').append(item);
    }
}
