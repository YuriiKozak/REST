package webservices.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RequestHeaders {
    static Map<String, String> getHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
