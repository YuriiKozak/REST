package gorest.co.in;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
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

        printResponse(response);
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

        String emailFromJson = jsonObject(response)
                .getJSONArray("result").getJSONObject(0).get(EMAIL).toString();

        String idFromJson = jsonObject(response)
                .getJSONArray("result").getJSONObject(0).get(ID).toString();

        user.setId(idFromJson);

        Assertions.assertThat(emailFromJson)
                .as("Wrong email.")
                .isEqualTo(user.getEmail());

        printResponse(response);
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

        printResponse(deleteResponse);

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

        JSONArray jsonArray = jsonObject(response).getJSONArray("result");

        Assertions.assertThat(jsonArray)
                .as("JSONArray is not empty.")
                .isEmpty();

        printResponse(response);
    }
}