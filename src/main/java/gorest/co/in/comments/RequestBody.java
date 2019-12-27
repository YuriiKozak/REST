package gorest.co.in.comments;

import gorest.co.in.BaseRequestBody;
import org.json.JSONObject;

import java.util.*;

public class RequestBody extends BaseRequestBody {
    public static final String POST_ID = "post_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String BODY = "body";

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public RequestBody(Comment comment) {
        setPostId(comment.getPostId());
        setName(comment.getName());
        setEmail(comment.getEmail());
        setBody(comment.getBody());
    }

    public RequestBody setPostId(String postId) {
        requestParams.put(POST_ID, postId);
        return this;
    }

    public RequestBody setName(String name) {
        requestParams.put(NAME, name);
        return this;
    }

    public RequestBody setEmail(String email) {
        requestParams.put(EMAIL, email);
        return this;
    }

    public RequestBody setBody(String body) {
        requestParams.put(BODY, body);
        return this;
    }
}
