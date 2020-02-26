package mailservice.utils;

import io.restassured.response.Response;
import org.json.JSONObject;

public class JsonObject {
    public static JSONObject jsonObject(Response response) {
        return new JSONObject(response.getBody().asString());
    }
}
