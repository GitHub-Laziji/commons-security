package org.laziji.commons.security.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.TreeSet;

public class HostInterceptor implements HandlerInterceptor {

    private String redirectHost;
    private Integer redirectPort;
    private Set<String> hostWhitelistSet = new TreeSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {

        if (redirectHost == null) {
            return true;
        }

        if (!"get".equals(request.getMethod().toLowerCase())) {
            return true;
        }

        String host = request.getHeader("host");
        if (hostWhitelistSet.contains(host)) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        StringBuilder url = new StringBuilder();
        if (request.isSecure()) {
            url.append("https://");
        } else {
            url.append("http://");
        }
        url.append(redirectHost);
        if (redirectPort != null && redirectPort != 80) {
            url.append(':').append(redirectPort);
        }
        url.append(request.getRequestURI());
        String queryString = request.getQueryString();
        if (queryString != null) {
            url.append('?').append(queryString);
        }
        response.setHeader("location", url.toString());
        return false;
    }

    public void addHostWhitelist(String host) {
        if (host == null) {
            return;
        }
        hostWhitelistSet.add(host);
    }

    public String getRedirectHost() {
        return redirectHost;
    }

    public void setRedirectHost(String redirectHost) {
        if (redirectHost == null) {
            return;
        }
        this.redirectHost = redirectHost;
        hostWhitelistSet.add(redirectHost);
    }

    public Integer getRedirectPort() {
        return redirectPort;
    }

    public void setRedirectPort(Integer redirectPort) {
        if (redirectPort == null || redirectPort < 1 || redirectPort > 65535) {
            return;
        }
        this.redirectPort = redirectPort;
    }
}
