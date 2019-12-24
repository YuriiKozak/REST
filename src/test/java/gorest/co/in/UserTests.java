package gorest.co.in;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.RequestBody.*;
import static gorest.co.in.ResponseBody.*;

public class UserTests extends BaseTest {

    //this does not belong here, please implement it in proper builder pattern
    User user = new User().createRandomUser();

    @Test(priority = 1)
    public void postUserTest() {
        RestAssured.baseURI = usersURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());

        RequestBody requestBody = new RequestBody(user);
        request.body(requestBody.getRequestBody());

        Response response = request.post();

        Assertions.assertThat(response.getStatusCode())
                .as("Wrong response status code.")
                .isEqualTo(StatusCodes.FOUND.getCode());

        JSONObject jsonObject = (JSONObject) Utils.jsonObject(response).get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as("Wrong response code.")
                .isEqualTo(StatusCodes.CREATED.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as("Wrong response message.")
                .isEqualTo("A resource was successfully created in response to a POST request. " +
                        "The Location header contains the URL pointing to the newly created resource.");
        softAssertions.assertAll();

        Utils.printResponse(response);
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
                .as("Wrong response status code.")
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = Utils.jsonObject(response);

        JSONObject jsonResult = Utils.jsonObject(response)
                .getJSONArray(RESULT).getJSONObject(0);

        user.setId(jsonResult.get(ID).toString());

        User.verifyUsers(User.returnUserFromResponse(response), user);

        JSONObject json_meta = (JSONObject) jsonObject.get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as("Wrong response code.")
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as("Wrong response message.")
                .isEqualTo("OK. Everything worked as expected.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }

    @Test(priority = 3, dependsOnMethods={"postUserTest"})
    public void deleteUserTest() {
        RestAssured.baseURI = usersURI;
        RequestSpecification deleteRequest = RestAssured.given();
        deleteRequest.headers(RequestHeader.getHeaders());

        Response deleteResponse = deleteRequest.delete(user.getId());

        Assertions.assertThat(deleteResponse.getStatusCode())
                .as("Wrong response status code.")
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject deleteJsonObject = (JSONObject) Utils.jsonObject(deleteResponse).get(META);

        SoftAssertions deleteSoftAssertions = new SoftAssertions();
        deleteSoftAssertions.assertThat(deleteJsonObject.get(CODE))
                .as("Wrong response code.")
                .isEqualTo(StatusCodes.NO_CONTENT.getCode());
        deleteSoftAssertions.assertThat(deleteJsonObject.get(MESSAGE))
                .as("Wrong response message.")
                .isEqualTo("The request was handled successfully and the response contains no body content.");
        deleteSoftAssertions.assertAll();

        Utils.printResponse(deleteResponse);

        //Verify User deleted:

        RequestSpecification request = RestAssured.given();

        Response response = request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(EMAIL, user.getEmail())
                .get();

        Assertions.assertThat(response.getStatusCode())
                .as("Wrong response status code.")
                .isEqualTo(StatusCodes.OK.getCode());

        JSONArray jsonArray = Utils.jsonObject(response).getJSONArray(RESULT);

        Assertions.assertThat(jsonArray)
                .as("JSONArray is not empty.")
                .isEmpty();

        JSONObject jsonObject = (JSONObject) Utils.jsonObject(response).get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as("Wrong response code.")
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as("Wrong response message.")
                .isEqualTo("OK. Everything worked as expected.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }
}