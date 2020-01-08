package gorest.co.in.utils;

import io.restassured.response.Response;
import org.json.JSONObject;

public class Utils {
    public String randomEmail = System.currentTimeMillis() + "@gmail.com";
    public String randomUrl = "https://random.com/" + System.currentTimeMillis();

    public static JSONObject jsonObject(Response response) {
        return new JSONObject(response.getBody().asString());
    }

    public static void printResponse(Response response) {
        System.out.println(jsonObject(response).toString(5));
    }
}
