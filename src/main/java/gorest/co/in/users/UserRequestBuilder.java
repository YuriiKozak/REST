package gorest.co.in.users;

import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static gorest.co.in.users.UserRequest.*;

public class UserRequestBuilder implements BaseUrls {
    private static void setBaseURI() {
        RestAssured.baseURI = usersURI;
    }

    public static Response postUserRequest(User user) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        UserRequest userRequest = new UserRequest(user);
        request.body(userRequest.getRequestBody());
        return request.post();
    }

    public static Response getUserRequest(User user) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(EMAIL, user.getEmail())
                .get();
    }

    public static Response deleteUserRequest(User user) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        return request.delete(user.getId());
    }
}
