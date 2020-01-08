package gorest.co.in.posts;

import gorest.co.in.constants.BaseRequest;
import org.json.JSONObject;

import java.util.*;

public class PostRequestBody implements BaseRequest {
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";
    public static final String BODY = "body";

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public PostRequestBody(Post post) {
        setUserId(post.getUserId());
        setTitle(post.getTitle());
        setBody(post.getBody());
    }

    public PostRequestBody setUserId(String userId) {
        requestParams.put(USER_ID, userId);
        return this;
    }

    public PostRequestBody setTitle(String title) {
        requestParams.put(TITLE, title);
        return this;
    }

    public PostRequestBody setBody(String body) {
        requestParams.put(BODY, body);
        return this;
    }
}
