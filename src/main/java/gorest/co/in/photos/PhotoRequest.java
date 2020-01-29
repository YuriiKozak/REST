package gorest.co.in.photos;

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

public class PhotoRequest implements RequestHeaders, BaseRequest, BaseUrls {
    public static final String ALBUM_ID = "album_id";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String THUMBNAIL = "thumbnail";

    private static void setBaseURI() {
        RestAssured.baseURI = photosURI;
    }

    public static Response postPhotoRequest(Photo photo) {
        Log.info(String.format(SENDING_REQUEST, POST));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        PhotoRequest photoRequest = new PhotoRequest(photo);
        request.body(photoRequest.getRequestBody());
        return request.post();
    }

    public static Response getPhotoRequest(Photo photo) {
        Log.info(String.format(SENDING_REQUEST, GET));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, accessToken)
                .queryParam(ALBUM_ID, photo.getAlbumId())
                .get();
    }

    public static Response patchPhotoRequest(Photo photo) {
        Log.info(String.format(SENDING_REQUEST, PATCH));
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeaders.getHeaders());
        PhotoRequest photoRequest = new PhotoRequest(photo);
        request.body(photoRequest.getRequestBody());
        return request.patch(photo.getId());
    }

    public static Response deletePhotoRequest(Photo photo) {
        Log.info(String.format(SENDING_REQUEST, DELETE));
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

    public void setAlbumId(String albumId) {
        requestParams.put(ALBUM_ID, albumId);
    }

    public void setTitle(String title) {
        requestParams.put(TITLE, title);
    }

    public void setUrl(String url) {
        requestParams.put(URL, url);
    }

    public void setThumbnail(String thumbnail) {
        requestParams.put(THUMBNAIL, thumbnail);
    }
}
