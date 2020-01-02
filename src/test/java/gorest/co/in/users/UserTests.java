package gorest.co.in.users;

import gorest.co.in.base.BaseTest;
import gorest.co.in.constants.StatusCodes;
import gorest.co.in.utils.Utils;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.constants.AssertionMessages.*;
import static gorest.co.in.users.RequestBody.*;
import static gorest.co.in.response.BaseResponseBody.*;

public class UserTests extends BaseTest {

    //this does not belong here, please implement it in proper builder pattern
    User user = new User().createRandomUser();
    Utils utils = new Utils();

    @Test(priority = 1)
    public void postUserTest() {
        RestAssured.baseURI = usersURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());

        RequestBody requestBody = new RequestBody(user);
        request.body(requestBody.getRequestBody());

        Response response = request.post();

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.FOUND.getCode());

        JSONObject jsonObject = (JSONObject) utils.jsonObject(response).get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.CREATED.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("A resource was successfully created in response to a POST request. " +
                        "The Location header contains the URL pointing to the newly created resource.");
        softAssertions.assertAll();

        utils.printResponse(response);
    }

    @Test(priority = 2, dependsOnMethods={"postUserTest"})//why is this depending on something?
    public void getUserTest() {
        RestAssured.baseURI = usersURI;
        RequestSpecification request = RestAssured.given();//code duplication

        Response response = request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(EMAIL, user.getEmail())
                .get();

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = utils.jsonObject(response);

        JSONObject jsonResult = utils.jsonObject(response)
                .getJSONArray(RESULT).getJSONObject(0);

        user.setId(jsonResult.get(ID).toString());

        user.verifyUsers(user.returnUserFromResponse(response), user);

        JSONObject json_meta = (JSONObject) jsonObject.get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("OK. Everything worked as expected.");
        softAssertions.assertAll();

        utils.printResponse(response);
    }

    @Test(priority = 3, dependsOnMethods={"postUserTest"})
    public void deleteUserTest() {
        RestAssured.baseURI = usersURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());

        Response response = request.delete(user.getId());

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = (JSONObject) utils.jsonObject(response).get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.NO_CONTENT.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("The request was handled successfully and the response contains no body content.");
        softAssertions.assertAll();

        utils.printResponse(response);
    }
}