package gorest.co.in.photos;

import gorest.co.in.constants.BaseRequest;
import gorest.co.in.constants.BaseUrls;
import gorest.co.in.constants.RequestHeaders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.*;

public class PhotoRequest implements RequestHeaders, BaseRequest, BaseUrls {
    public static final String ALBUM_ID = "album_id";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String THUMBNAIL = "thumbnail";

    private static void setBaseURI() {
        RestAssured.baseURI = photosURI;
    }

    public static Response postPhotoRequest(Photo photo) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        PhotoRequest photoRequest = new PhotoRequest(photo);
        request.body(photoRequest.getRequestBody());
        return request.post();
    }

    public static Response getPhotoRequest(Photo photo) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, accessToken)
                .queryParam(ALBUM_ID, photo.getAlbumId())
                .get();
    }

    public static Response deletePhotoRequest(Photo photo) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        return request.delete(photo.getId());
    }

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public PhotoRequest(Photo photo) {
        setAlbumId(photo.getAlbumId());
        setTitle(photo.getTitle());
        setUrl(photo.getUrl());
        setThumbnail(photo.getThumbnail());
    }

    public PhotoRequest setAlbumId(String albumId) {
        requestParams.put(ALBUM_ID, albumId);
        return this;
    }

    public PhotoRequest setTitle(String title) {
        requestParams.put(TITLE, title);
        return this;
    }

    public PhotoRequest setUrl(String url) {
        requestParams.put(URL, url);
        return this;
    }

    public PhotoRequest setThumbnail(String thumbnail) {
        requestParams.put(THUMBNAIL, thumbnail);
        return this;
    }
}
