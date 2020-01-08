package gorest.co.in.posts;

import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static gorest.co.in.posts.PostRequestBody.*;

public class PostRequestBuilder implements BaseUrls {
    private static void setBaseURI() {
        RestAssured.baseURI = postsURI;
    }

    public static Response postPostRequest(Post post) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        PostRequestBody postRequestBody = new PostRequestBody(post);
        request.body(postRequestBody.getRequestBody());
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
}
