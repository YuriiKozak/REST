package gorest.co.in.comments;

import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static gorest.co.in.comments.CommentRequestBody.*;

public class CommentRequestBuilder implements BaseUrls {
    private static void setBaseURI() {
        RestAssured.baseURI = commentsURI;
    }

    public static Response postCommentRequest(Comment comment) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        CommentRequestBody commentRequestBody = new CommentRequestBody(comment);
        request.body(commentRequestBody.getRequestBody());
        return request.post();
    }

    public static Response getCommentRequest(Comment comment) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(POST_ID, comment.getPostId())
                .get();
    }

    public static Response deleteCommentRequest(Comment comment) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        return request.delete(comment.getId());
    }
}
