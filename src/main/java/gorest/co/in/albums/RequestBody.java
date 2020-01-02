package gorest.co.in.albums;

import gorest.co.in.request.BaseRequestBody;
import org.json.JSONObject;

import java.util.*;

public class RequestBody extends BaseRequestBody {
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public RequestBody(Album album) {
        setUserId(album.getUserId());
        setTitle(album.getTitle());
    }

    public RequestBody setUserId(String userId) {
        requestParams.put(USER_ID, userId);
        return this;
    }

    public RequestBody setTitle(String title) {
        requestParams.put(TITLE, title);
        return this;
    }
}
