package gorest.co.in.posts;

import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static gorest.co.in.constants.BaseRequest.*;
import static gorest.co.in.posts.RequestBody.*;

public class RequestBuilder implements BaseUrls {
    public static Response postPostRequest(Post post) {
        RestAssured.baseURI = postsURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        RequestBody requestBody = new RequestBody(post);
        request.body(requestBody.getRequestBody());
        return request.post();
    }

    public static Response getPostRequest(Post post) {
        RestAssured.baseURI = postsURI;
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(USER_ID, post.getUserId())
                .get();
    }

    public static Response deletePostRequest(Post post) {
        RestAssured.baseURI = postsURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        return request.delete(post.getId());
    }
}
