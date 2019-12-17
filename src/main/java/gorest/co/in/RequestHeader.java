package gorest.co.in;

import java.util.*;

public class RequestHeader {
    public static Map<String, String> getHeaders(String accessToken) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        return headers;
    }}
