package gorest.co.in.photos;

import gorest.co.in.constants.AssertionMessages;
import gorest.co.in.constants.BaseUrls;
import gorest.co.in.constants.StatusCodes;
import gorest.co.in.headers.RequestHeader;
import gorest.co.in.utils.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.photos.RequestBody.*;
import static gorest.co.in.photos.ResponseBody.*;

public class PhotoTests implements BaseUrls, AssertionMessages {

    private Photo photo = new Photo().createRandomPhoto();

    @Test(priority = 1)
    public void postPhotoTest() {
        RestAssured.baseURI = photosURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());

        RequestBody requestBody = new RequestBody(photo);
        request.body(requestBody.getRequestBody());

        Response response = request.post();

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.FOUND.getCode());

        JSONObject jsonObject = (JSONObject) Utils.jsonObject(response).get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.CREATED.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("A resource was successfully created in response to a POST request. " +
                        "The Location header contains the URL pointing to the newly created resource.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }

    @Test(priority = 2, dependsOnMethods={"postPhotoTest"})
    public void getPhotoTest() {
        RestAssured.baseURI = photosURI;
        RequestSpecification request = RestAssured.given();

        Response response = request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(ALBUM_ID, photo.getAlbumId())
                .get();

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = Utils.jsonObject(response);

        JSONObject jsonResult = jsonObject.getJSONArray(RESULT).getJSONObject(0);

        photo.setId(jsonResult.get(ID).toString());
        photo.setUrl(jsonResult.get(URL).toString());
        photo.setThumbnail(jsonResult.get(THUMBNAIL).toString());

        photo.verifyPhotos(photo.returnPhotoFromResponse(response), photo);

        JSONObject json_meta = (JSONObject) jsonObject.get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("OK. Everything worked as expected.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }

    @Test(priority = 3, dependsOnMethods={"getPhotoTest"})
    public void deletePhotoTest() {
        RestAssured.baseURI = photosURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());

        Response response = request.delete(photo.getId());

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = (JSONObject) Utils.jsonObject(response).get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.NO_CONTENT.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("The request was handled successfully and the response contains no body content.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }
}