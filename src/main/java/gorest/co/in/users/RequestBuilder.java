package gorest.co.in.users;

import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static gorest.co.in.constants.BaseRequest.*;
import static gorest.co.in.users.RequestBody.*;

public class RequestBuilder implements BaseUrls {
    public static Response postUserRequest(User user) {
        RestAssured.baseURI = usersURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        RequestBody requestBody = new RequestBody(user);
        request.body(requestBody.getRequestBody());
        return request.post();
    }

    public static Response getUserRequest(User user) {
        RestAssured.baseURI = usersURI;
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(EMAIL, user.getEmail())
                .get();
    }

    public static Response deleteUserRequest(User user) {
        RestAssured.baseURI = usersURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        return request.delete(user.getId());
    }
}
