package gorest.co.in.posts;

import gorest.co.in.BaseRequestBody;
import org.json.JSONObject;

import java.util.*;

public class RequestBody extends BaseRequestBody {
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";
    public static final String BODY = "body";

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public RequestBody(Post post) {
        setUserId(post.getUserId());
        setTitle(post.getTitle());
        setBody(post.getBody());
    }

    public RequestBody setUserId(String userId) {
        requestParams.put(USER_ID, userId);
        return this;
    }

    public RequestBody setTitle(String title) {
        requestParams.put(TITLE, title);
        return this;
    }

    public RequestBody setBody(String body) {
        requestParams.put(BODY, body);
        return this;
    }
}
