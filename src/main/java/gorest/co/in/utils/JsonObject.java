package gorest.co.in.utils;

import io.restassured.response.Response;
import org.json.JSONObject;

import static gorest.co.in.constants.BaseResponse.*;

public class JsonObject {
    public String randomEmail = System.currentTimeMillis() + "@gmail.com";
    public String randomUrl = "https://random.com/" + System.currentTimeMillis();

    public static JSONObject jsonObject(Response response) {
        return new JSONObject(response.getBody().asString());
    }

    public static JSONObject jsonObjectMeta(Response response) {
        Log.info("Getting " + META + " from Response.");
        return (JSONObject) jsonObject(response).get(META);
    }

    public static JSONObject jsonObjectResult(Response response) {
        Log.info("Getting " + RESULT + " from Response.");
        return jsonObject(response).getJSONArray(RESULT).getJSONObject(0);
    }
}
