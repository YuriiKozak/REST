package gorest.co.in;

import io.restassured.response.Response;
import org.json.JSONObject;

public class Utils {
    public static JSONObject jsonObject(Response response) {
        return new JSONObject(response.getBody().asString());
    }

    public static void printResponse(Response response) {
        System.out.println(jsonObject(response).toString(5));
    }
}
