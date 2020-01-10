package gorest.co.in.users;

import gorest.co.in.constants.StatusCodes;
import gorest.co.in.utils.Log;
import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
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

        JSONObject jsonObject = Utils.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.CREATED.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("A resource was successfully created in response to a POST request. " +
                        "The Location header contains the URL pointing to the newly created resource.");
        softAssertions.assertAll();
    }

    @Test
    public void verifyRandomlyCreatedUser() {
        Response response = getUserRequest(user);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonResult = Utils.jsonObjectResult(response);

        user.setId(jsonResult.get(ID).toString());

        user.verifyUsers(user.returnUserFromResponse(response), user);

        JSONObject json_meta = Utils.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("OK. Everything worked as expected.");
        softAssertions.assertAll();
    }

    @Test
    public void deleteRandomlyCreatedUser() {
        Response response = deleteUserRequest(user);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = Utils.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.NO_CONTENT.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("The request was handled successfully and the response contains no body content.");
        softAssertions.assertAll();
    }
}