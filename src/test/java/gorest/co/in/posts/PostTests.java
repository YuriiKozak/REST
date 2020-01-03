package gorest.co.in.posts;

import gorest.co.in.constants.AssertionMessages;
import gorest.co.in.constants.StatusCodes;
import gorest.co.in.constants.BaseUrls;
import gorest.co.in.utils.Utils;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.posts.RequestBody.*;
import static gorest.co.in.posts.ResponseBody.*;

public class PostTests implements BaseUrls, AssertionMessages {

    Post post = new Post().createRandomPost();
    Utils utils = new Utils();

    @Test(priority = 1)
    public void postPostTest() {
        RestAssured.baseURI = postsURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());

        RequestBody requestBody = new RequestBody(post);
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

    @Test(priority = 2, dependsOnMethods={"postPostTest"})
    public void getPostTest() {
        RestAssured.baseURI = postsURI;
        RequestSpecification request = RestAssured.given();

        Response response = request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(USER_ID, post.getUserId())
                .get();

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = utils.jsonObject(response);

        JSONObject jsonResult = jsonObject.getJSONArray(RESULT).getJSONObject(0);

        post.setId(jsonResult.get(ID).toString());

        post.verifyPosts(post.returnPostFromResponse(response), post);

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

    @Test(priority = 3, dependsOnMethods={"getPostTest"})
    public void deletePostTest() {
        RestAssured.baseURI = postsURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());

        Response response = request.delete(post.getId());

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