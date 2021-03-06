package gorest.co.in.users;

import gorest.co.in.constants.*;
import gorest.co.in.utils.Log;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.*;

import static gorest.co.in.constants.LogMessages.*;

public class UserRequest implements RequestHeaders, BaseRequest, BaseUrls {
    public static final String WEBSITE = "website";
    public static final String ADDRESS = "address";
    public static final String GENDER = "gender";
    public static final String PHONE = "phone";
    public static final String LINKS = "_links";
    public static final String DOB = "dob";
    public static final String LAST_NAME = "last_name";
    public static final String FIRST_NAME = "first_name";
    public static final String EMAIL = "email";
    public static final String STATUS = "status";
    public static final String EDIT = "edit";
    public static final String SELF = "self";
    public static final String AVATAR = "avatar";

    private static void setBaseURI() {
        RestAssured.baseURI = usersURI;
    }

    public static Response postUserRequest(User user) {
        Log.info(String.format(SENDING_REQUEST, POST));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        UserRequest userRequest = new UserRequest(user);
        request.body(userRequest.getRequestBody());
        return request.post();
    }

    public static Response getUserRequest(User user) {
        Log.info(String.format(SENDING_REQUEST, GET));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, accessToken)
                .queryParam(EMAIL, user.getEmail())
                .get();
    }

    public static Response getUserRequest(String id) {
        Log.info(String.format(SENDING_REQUEST, GET));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, accessToken)
                .get(id);
    }

    public static Response patchUserRequest(User user) {
        Log.info(String.format(SENDING_REQUEST, PATCH));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        UserRequest userRequest = new UserRequest(user);
        request.body(userRequest.getRequestBody());
        return request.patch(user.getId());
    }

    public static Response deleteUserRequest(User user) {
        Log.info(String.format(SENDING_REQUEST, DELETE));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        return request.delete(user.getId());
    }

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public UserRequest(User user) {
        setWebsite(user.getWebsite());
        setAddress(user.getAddress());
        setGender(user.getGender());
        setPhone(user.getPhone());
        setDob(user.getDob());
        setLastName(user.getLastName());
        setFirstName(user.getFirstName());
        setEmail(user.getEmail());
        setStatus(user.getStatus());
        setAvatar(user.getLinks().getAvatar().getHref());
    }

    public void setWebsite(String website) {
        requestParams.put(WEBSITE, website);
    }

    public void setAddress(String address) {
        requestParams.put(ADDRESS, address);
    }

    public void setGender(String gender) {
        requestParams.put(GENDER, gender);
    }

    public void setPhone(String phone) {
        requestParams.put(PHONE, phone);
    }

    public void setDob(String dob) {
        requestParams.put(DOB, dob);
    }

    public void setLastName(String lastName) {
        requestParams.put(LAST_NAME, lastName);
    }

    public void setFirstName(String firstName) {
        requestParams.put(FIRST_NAME, firstName);
    }

    public void setEmail(String email) {
        requestParams.put(EMAIL, email);
    }

    public void setStatus(String status) {
        requestParams.put(STATUS, status);
    }

    public void setAvatar(String avatar) {
        requestParams.put(AVATAR, avatar);
    }
}
