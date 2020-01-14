package gorest.co.in.albums;

import gorest.co.in.constants.StatusCodes;
import gorest.co.in.utils.Log;
import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.constants.AssertionMessages.*;
import static gorest.co.in.albums.AlbumRequest.*;
import static gorest.co.in.albums.AlbumResponse.*;

public class AlbumTests {
    private Album album = new Album().createRandomAlbum();

    @Test
    public void createRandomAlbum() {
        Response response = postAlbumRequest(album);
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
                .isEqualTo(A_RESOURCE_WAS_SUCCESSFULLY_CREATED);
        softAssertions.assertAll();
    }

    @Test
    public void verifyRandomlyCreatedAlbum() {
        Response response = getAlbumRequest(album);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonResult = Utils.jsonObjectResult(response);

        album.setId(jsonResult.get(ID).toString());

        album.verifyAlbums(album.returnAlbumFromResponse(response), album);

        JSONObject json_meta = Utils.jsonObjectMeta(response);

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
    public void deleteRandomlyCreatedAlbum() {
        Response response = deleteAlbumRequest(album);
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
                .isEqualTo(THE_REQUEST_WAS_HANDLED_SUCCESSFULLY);
        softAssertions.assertAll();
    }
}