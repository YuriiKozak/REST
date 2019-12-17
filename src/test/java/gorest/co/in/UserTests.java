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

public class UserTests extends BaseTest {
    User user = new User.Builder()
            .setFirstName("John")
            .setLastName("Doe")
            .setGender("male")
            .setEmail(randomEmail)
            .setStatus("active")
            .build();

    @Test(priority = 1)
    public void postUserTest() {
        RestAssured.baseURI = baseURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders(accessToken));

        RequestBody requestBody = new RequestBody()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setGender(user.getGender())
                .setEmail(user.getEmail())
                .setStatus(user.getStatus());

        request.body(requestBody.getRequestBody());

        Response response = request.post("/users");

        Assertions.assertThat(response.getStatusCode())
                .as("Wrong response status code.")
                .isEqualTo(302);

        JSONObject jsonObject = (JSONObject) Utils.jsonObject(response).get("_meta");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get("code"))
                .as("Wrong response code.")
                .isEqualTo(201);
        softAssertions.assertThat(jsonObject.get("message"))
                .as("Wrong response message.")
                .isEqualTo("A resource was successfully created in response to a POST request. " +
                        "The Location header contains the URL pointing to the newly created resource.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }

    @Test(priority = 2, dependsOnMethods={"postUserTest"})
    public void getUserTest() {
        RestAssured.baseURI = baseURI;
        RequestSpecification request = RestAssured.given();

        Response response = request
                .when()
                .queryParam(ACCESS_TOKEN, accessToken)
                .queryParam(EMAIL, user.getEmail())
                .get("/users");

        Assertions.assertThat(response.getStatusCode())
                .as("Wrong response status code.")
                .isEqualTo(200);

        JSONObject jsonObject = Utils.jsonObject(response);

        JSONObject jsonResult = Utils.jsonObject(response)
                .getJSONArray("result").getJSONObject(0);

        user.setId(jsonResult.get(ID).toString());

        Assertions.assertThat(jsonResult.get(EMAIL).toString())
                .as("Wrong email.")
                .isEqualTo(user.getEmail());

        JSONObject json_meta = (JSONObject) jsonObject.get("_meta");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get("code"))
                .as("Wrong response code.")
                .isEqualTo(200);
        softAssertions.assertThat(json_meta.get("message"))
                .as("Wrong response message.")
                .isEqualTo("OK. Everything worked as expected.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }

    @Test(priority = 3, dependsOnMethods={"postUserTest"})
    public void deleteUserTest() {
        RestAssured.baseURI = baseURI;
        RequestSpecification deleteRequest = RestAssured.given();
        deleteRequest.headers(RequestHeader.getHeaders(accessToken));

        Response deleteResponse = deleteRequest.delete("/users/" + user.getId());

        Assertions.assertThat(deleteResponse.getStatusCode())
                .as("Wrong response status code.")
                .isEqualTo(200);

        JSONObject deleteJsonObject = (JSONObject) Utils.jsonObject(deleteResponse).get("_meta");

        SoftAssertions deleteSoftAssertions = new SoftAssertions();
        deleteSoftAssertions.assertThat(deleteJsonObject.get("code"))
                .as("Wrong response code.")
                .isEqualTo(204);
        deleteSoftAssertions.assertThat(deleteJsonObject.get("message"))
                .as("Wrong response message.")
                .isEqualTo("The request was handled successfully and the response contains no body content.");
        deleteSoftAssertions.assertAll();

        Utils.printResponse(deleteResponse);

        //Verify User deleted:

        RequestSpecification request = RestAssured.given();

        Response response = request
                .when()
                .queryParam(ACCESS_TOKEN, accessToken)
                .queryParam(EMAIL, user.getEmail())
                .get("/users");

        Assertions.assertThat(response.getStatusCode())
                .as("Wrong response status code.")
                .isEqualTo(200);

        JSONArray jsonArray = Utils.jsonObject(response).getJSONArray("result");

        Assertions.assertThat(jsonArray)
                .as("JSONArray is not empty.")
                .isEmpty();

        JSONObject jsonObject = (JSONObject) Utils.jsonObject(response).get("_meta");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get("code"))
                .as("Wrong response code.")
                .isEqualTo(200);
        softAssertions.assertThat(jsonObject.get("message"))
                .as("Wrong response message.")
                .isEqualTo("OK. Everything worked as expected.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }
}