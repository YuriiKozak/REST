package gorest.co.in.albums;

import gorest.co.in.constants.AssertionMessages;
import gorest.co.in.constants.StatusCodes;
import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.albums.RequestBody.*;
import static gorest.co.in.albums.ResponseBody.*;

public class AlbumTests implements AssertionMessages {

    private Album album = new Album().createRandomAlbum();

    @Test(priority = 1)
    public void createRandomAlbum() {
        Response response = RequestBuilder.postAlbumRequest(album);

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

    @Test(priority = 2, dependsOnMethods={"createRandomAlbum"})
    public void verifyRandomlyCreatedAlbum() {
        Response response = RequestBuilder.getAlbumRequest(album);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = Utils.jsonObject(response);

        JSONObject jsonResult = jsonObject.getJSONArray(RESULT).getJSONObject(0);

        album.setId(jsonResult.get(ID).toString());

        album.verifyAlbums(album.returnAlbumFromResponse(response), album);

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

    @Test(priority = 3, dependsOnMethods={"verifyRandomlyCreatedAlbum"})
    public void deleteRandomlyCreatedAlbum() {
        Response response = RequestBuilder.deleteAlbumRequest(album);

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