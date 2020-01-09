package gorest.co.in.constants;

import java.util.*;

public interface RequestHeaders {
    String accessToken = "JGgmQ8VG-eoe1SZdazOBE-74obh8CPyXrWwI";

    static Map<String, String> getHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        return headers;
    }
}
