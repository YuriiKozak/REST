package gorest.co.in.utils;

import io.restassured.response.Response;
import org.json.JSONObject;

public class Utils {
    public String randomEmail = System.currentTimeMillis() + "@gmail.com";

    public JSONObject jsonObject(Response response) {
        return new JSONObject(response.getBody().asString());
    }

    public void printResponse(Response response) {
        System.out.println(jsonObject(response).toString(5));
    }
}
