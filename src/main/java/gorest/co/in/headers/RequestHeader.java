package gorest.co.in.headers;

import java.util.*;

public class RequestHeader {
    public static final String accessToken = "JGgmQ8VG-eoe1SZdazOBE-74obh8CPyXrWwI";

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        return headers;
    }
}
