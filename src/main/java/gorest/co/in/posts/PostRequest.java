package gorest.co.in.posts;

import gorest.co.in.constants.BaseRequest;
import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.*;

public class PostRequest implements BaseRequest, BaseUrls {
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";
    public static final String BODY = "body";

    private static void setBaseURI() {
        RestAssured.baseURI = postsURI;
    }

    public static Response postPostRequest(Post post) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        PostRequest postRequest = new PostRequest(post);
        request.body(postRequest.getRequestBody());
        return request.post();
    }

    public static Response getPostRequest(Post post) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(USER_ID, post.getUserId())
                .get();
    }

    public static Response deletePostRequest(Post post) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        return request.delete(post.getId());
    }

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public PostRequest(Post post) {
        setUserId(post.getUserId());
        setTitle(post.getTitle());
        setBody(post.getBody());
    }

    public PostRequest setUserId(String userId) {
        requestParams.put(USER_ID, userId);
        return this;
    }

    public PostRequest setTitle(String title) {
        requestParams.put(TITLE, title);
        return this;
    }

    public PostRequest setBody(String body) {
        requestParams.put(BODY, body);
        return this;
    }
}
