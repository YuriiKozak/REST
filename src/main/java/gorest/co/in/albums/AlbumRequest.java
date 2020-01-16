package gorest.co.in.albums;

import gorest.co.in.constants.BaseRequest;
import gorest.co.in.constants.BaseUrls;
import gorest.co.in.constants.RequestHeaders;
import gorest.co.in.utils.Log;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.*;

import static gorest.co.in.constants.LogMessages.*;

public class AlbumRequest implements RequestHeaders, BaseRequest, BaseUrls {
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";

    private static void setBaseURI() {
        RestAssured.baseURI = albumsURI;
    }

    public static Response postAlbumRequest(Album album) {
        Log.info(String.format(SENDING_REQUEST, POST));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        AlbumRequest albumRequest = new AlbumRequest(album);
        request.body(albumRequest.getRequestBody());
        return request.post();
    }

    public static Response getAlbumRequest(Album album) {
        Log.info(String.format(SENDING_REQUEST, GET));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, accessToken)
                .queryParam(USER_ID, album.getUserId())
                .get();
    }

    public static Response patchAlbumRequest(Album album) {
        Log.info(String.format(SENDING_REQUEST, PATCH));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        AlbumRequest albumRequest = new AlbumRequest(album);
        request.body(albumRequest.getRequestBody());
        return request.patch(album.getId());
    }

    public static Response deleteAlbumRequest(Album album) {
        Log.info(String.format(SENDING_REQUEST, DELETE));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        return request.delete(album.getId());
    }

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public AlbumRequest(Album album) {
        setUserId(album.getUserId());
        setTitle(album.getTitle());
    }

    public AlbumRequest setUserId(String userId) {
        requestParams.put(USER_ID, userId);
        return this;
    }

    public AlbumRequest setTitle(String title) {
        requestParams.put(TITLE, title);
        return this;
    }
}
