package org.laziji.commons.security.web;


import java.util.Map;
import java.util.TreeMap;

public enum RequestMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;

    private static final Map<String, RequestMethod> methodMap = new TreeMap<>();

    static {
        for (RequestMethod requestMethod : RequestMethod.values()) {
            methodMap.put(requestMethod.toString(), requestMethod);
        }
    }

    public static RequestMethod match(String method) {
        if (method == null) {
            return null;
        }
        return methodMap.get(method.toUpperCase());
    }
}