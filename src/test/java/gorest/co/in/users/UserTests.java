package gorest.co.in.users;

import gorest.co.in.constants.StatusCodes;
import gorest.co.in.utils.*;
import io.restassured.response.Response;
import org.assertj.core.api.*;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.constants.AssertionMessages.*;
import static gorest.co.in.users.UserRequest.*;
import static gorest.co.in.users.UserResponse.*;

public class UserTests {
    private User user = new User().createRandomUser();

    @Test
    public void createRandomUser() {
        Response response = postUserRequest(user);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.FOUND.getCode());

        JSONObject jsonObject = JsonObject.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.CREATED.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(A_RESOURCE_WAS_SUCCESSFULLY_CREATED);
        softAssertions.assertAll();
    }

    @Test
    public void verifyRandomlyCreatedUser() {
        Response response = getUserRequest(user);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonResult = JsonObject.jsonObjectResult(response);
        user.setId(jsonResult.get(ID).toString());

        JSONObject jsonLinks = jsonResult.getJSONObject(LINKS);
        user.setLinks(new Links(new Edit(jsonLinks.getJSONObject(EDIT).get("href").toString()),
                new Self(jsonLinks.getJSONObject(SELF).get("href").toString()),
                new Avatar(jsonLinks.getJSONObject(AVATAR).get("href").toString())));

        UserSteps.verifyUsers(UserSteps.returnUserFromResponse(response), user);

        JSONObject json_meta = JsonObject.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(OK_EVERYTHING_WORKED_AS_EXPECTED);
        softAssertions.assertAll();
    }

    @Test
    public void updateRandomlyCreatedUser() {
        user.setEmail(new Utils().randomEmail);

        Response response = patchUserRequest(user);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = JsonObject.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(OK_EVERYTHING_WORKED_AS_EXPECTED);
        softAssertions.assertAll();
    }

    @Test
    public void verifyRandomlyUpdatedUser() {
        Response response = getUserRequest(user);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        UserSteps.verifyUsers(UserSteps.returnUserFromResponse(response), user);

        JSONObject json_meta = JsonObject.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(OK_EVERYTHING_WORKED_AS_EXPECTED);
        softAssertions.assertAll();
    }

    @Test
    public void deleteRandomlyCreatedUser() {
        Response response = deleteUserRequest(user);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = JsonObject.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.NO_CONTENT.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(THE_REQUEST_WAS_HANDLED_SUCCESSFULLY);
        softAssertions.assertAll();
    }
}