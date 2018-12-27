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

    private static final Map<String, RequestMethod> MAPPING = new TreeMap<>();

    static {
        for (RequestMethod requestMethod : RequestMethod.values()) {
            MAPPING.put(requestMethod.toString(), requestMethod);
        }
    }

    public static RequestMethod parse(String method) {
        if (method == null) {
            return null;
        }
        return MAPPING.get(method.toUpperCase());
    }
}