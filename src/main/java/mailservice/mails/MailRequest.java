package mailservice.mails;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mailservice.constants.*;
import mailservice.utils.Log;
import org.json.JSONObject;

import java.util.*;

import static mailservice.constants.ILogMessages.*;

public class MailRequest implements IRequestHeaders, IBaseRequest, IBaseUrls {
    public static final String SUBJECT = "subject";
    public static final String EMAIL = "email";
    public static final String BODY = "body";

    private static void setBaseURI() {
        RestAssured.baseURI = mailsURI;
    }

    public static Response getMailsRequest() {
        Log.info(String.format(SENDING_REQUEST, GET));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request.get();
    }

    public static Response getMailsRequest(String paramName, String paramValue) {
        Log.info(String.format(SENDING_REQUEST, GET));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request.queryParam(paramName, paramValue).get();
    }

    public static Response postMailRequest(Mail mail) {
        Log.info(String.format(SENDING_REQUEST, POST));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
//        request.headers(RequestHeaders.getHeaders());
        MailRequest mailRequest = new MailRequest(mail);
        request.body(mailRequest.getRequestBody());
        return request.post();
    }

    public static Response deleteMailRequest(Mail mail) {
        Log.info(String.format(SENDING_REQUEST, DELETE));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
//        request.headers(RequestHeaders.getHeaders());
        MailRequest mailRequest = new MailRequest(mail);
        request.body(mailRequest.getRequestBody());
        return request.delete();
    }

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public MailRequest(Mail mail) {
        setId(mail.getId());
        setSubject(mail.getSubject());
        setEmail(mail.getEmail());
        setBody(mail.getBody());
    }

    public void setId(String id) {
        requestParams.put(ID, id);
    }

    public void setSubject(String subject) {
        requestParams.put(SUBJECT, subject);
    }

    public void setEmail(String email) {
        requestParams.put(EMAIL, email);
    }

    public void setBody(String body) {
        requestParams.put(BODY, body);
    }
}
