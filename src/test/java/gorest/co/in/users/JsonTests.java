package gorest.co.in.users;

import com.google.gson.Gson;
import gorest.co.in.utils.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.*;

import static gorest.co.in.users.UserResponse.*;
import static gorest.co.in.users.UserRequest.*;

public class JsonTests {
    @Test
    public void getUserJson() {
        User randomUser = new User().createRandomUser();
        postUserRequest(randomUser);

        Response postResponse = getUserRequest(randomUser);
        JSONObject jsonResult = JsonObject.jsonObjectResult(postResponse);
        randomUser.setId(jsonResult.get(ID).toString());
        JSONObject jsonLinks = jsonResult.getJSONObject(LINKS);
        randomUser.setLinks(new Links(new Edit(jsonLinks.getJSONObject(EDIT).get("href").toString()),
                new Self(jsonLinks.getJSONObject(SELF).get("href").toString()),
                new Avatar(jsonLinks.getJSONObject(AVATAR).get("href").toString())));
        UserSteps.verifyUsers(UserSteps.returnUserFromResponse(postResponse), randomUser);

        Response response = getUserRequest(randomUser.getId());
        Log.info(response);

        Gson gson = new Gson();
        String result = JsonObject.jsonObject(response).getJSONObject(RESULT).toString();
        User user = gson.fromJson(result, User.class);

        Log.info(user.getWebsite());
        Log.info(user.getAddress());
        Log.info(user.getGender());
        Log.info(user.getPhone());
        Log.info(user.getDob());
        Log.info(user.getLastName());
        Log.info(user.getId());
        Log.info(user.getFirstName());
        Log.info(user.getEmail());
        Log.info(user.getStatus());

        Log.info(user.getLinks().getEdit().getHref());
        Log.info(user.getLinks().getSelf().getHref());
        Log.info(user.getLinks().getAvatar().getHref());

        HashMap<String, Object> hashMap = new HashMap<>(Objects.requireNonNull(JsonToMap.jsonToMap(result)));
        Log.info(hashMap.toString());
    }
}
