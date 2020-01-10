package gorest.co.in.comments;

import gorest.co.in.constants.BaseRequest;
import gorest.co.in.constants.BaseUrls;
import gorest.co.in.constants.RequestHeaders;
import gorest.co.in.utils.Log;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.*;

import static gorest.co.in.constants.LogMessages.*;

public class CommentRequest implements RequestHeaders, BaseRequest, BaseUrls {
    public static final String POST_ID = "post_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String BODY = "body";

    private static void setBaseURI() {
        RestAssured.baseURI = commentsURI;
    }

    public static Response postCommentRequest(Comment comment) {
        Log.info(String.format(SENDING_REQUEST, POST));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        CommentRequest commentRequest = new CommentRequest(comment);
        request.body(commentRequest.getRequestBody());
        return request.post();
    }

    public static Response getCommentRequest(Comment comment) {
        Log.info(String.format(SENDING_REQUEST, GET));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, accessToken)
                .queryParam(POST_ID, comment.getPostId())
                .get();
    }

    public static Response deleteCommentRequest(Comment comment) {
        Log.info(String.format(SENDING_REQUEST, DELETE));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        return request.delete(comment.getId());
    }

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public CommentRequest(Comment comment) {
        setPostId(comment.getPostId());
        setName(comment.getName());
        setEmail(comment.getEmail());
        setBody(comment.getBody());
    }

    public CommentRequest setPostId(String postId) {
        requestParams.put(POST_ID, postId);
        return this;
    }

    public CommentRequest setName(String name) {
        requestParams.put(NAME, name);
        return this;
    }

    public CommentRequest setEmail(String email) {
        requestParams.put(EMAIL, email);
        return this;
    }

    public CommentRequest setBody(String body) {
        requestParams.put(BODY, body);
        return this;
    }
}
