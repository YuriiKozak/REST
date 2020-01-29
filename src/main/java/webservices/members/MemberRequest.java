package webservices.members;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import webservices.constants.BaseRequest;
import webservices.constants.BaseUrls;
import webservices.constants.RequestHeaders;
import webservices.utils.Log;

import java.util.HashMap;
import java.util.Map;

import static webservices.constants.LogMessages.SENDING_REQUEST;

public class MemberRequest implements RequestHeaders, BaseRequest, BaseUrls {
    public static final String FULL_NAME = "full_name";

    private static void setBaseURI() {
        RestAssured.baseURI = membersURI;
    }

    public static Response getMembersRequest() {
        Log.info(String.format(SENDING_REQUEST, GET));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request.get();
    }

    public static Response postMemberRequest(Member member) {
        Log.info(String.format(SENDING_REQUEST, POST));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        MemberRequest memberRequest = new MemberRequest(member);
        request.body(memberRequest.getRequestBody());
        return request.post();
    }

    public static Response putMemberRequest(Member member) {
        Log.info(String.format(SENDING_REQUEST, PUT));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        MemberRequest memberRequest = new MemberRequest(member);
        request.body(memberRequest.getRequestBody());
        return request.put();
    }

    public static Response deleteMemberRequest(Member member) {
        Log.info(String.format(SENDING_REQUEST, DELETE));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        MemberRequest memberRequest = new MemberRequest(member);
        request.body(memberRequest.getRequestBody());
        return request.delete();
    }

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public MemberRequest(Member member) {
        setId(member.getId());
        setFullName(member.getFullName());
    }

    public void setId(String id) {
        requestParams.put(ID, id);
    }

    public void setFullName(String fullName) {
        requestParams.put(FULL_NAME, fullName);
    }
}
