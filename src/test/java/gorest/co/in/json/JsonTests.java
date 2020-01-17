package gorest.co.in.json;

import com.google.gson.Gson;
import gorest.co.in.users.User;
import gorest.co.in.utils.Log;
import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.constants.BaseResponse.*;
import static gorest.co.in.users.UserRequest.*;

public class JsonTests {
    @Test
    public void getUserById() {
        User randomUser = new User().createRandomUser();
        postUserRequest(randomUser);

        Response postResponse = getUserRequest(randomUser);
        JSONObject jsonResult = Utils.jsonObjectResult(postResponse);
        randomUser.setId(jsonResult.get(ID).toString());
        JSONObject jsonLinks = jsonResult.getJSONObject(LINKS);
        randomUser.setEdit(jsonLinks.getJSONObject(EDIT).get("href").toString());
        randomUser.setSelf(jsonLinks.getJSONObject(SELF).get("href").toString());
        randomUser.verifyUsers(randomUser.returnUserFromResponse(postResponse), randomUser);

        Response response = getUserRequest(randomUser.getId());
        Log.info(response);

        Gson gson = new Gson();
        String result = Utils.jsonObject(response).getJSONObject(RESULT).toString();
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

        Log.info(user.getEdit());
        Log.info(user.getSelf());
        Log.info(user.getAvatar());
    }
}
