package gorest.co.in.photos;

import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static gorest.co.in.photos.PhotoRequestBody.*;

public class PhotoRequestBuilder implements BaseUrls {
    private static void setBaseURI() {
        RestAssured.baseURI = photosURI;
    }

    public static Response postPhotoRequest(Photo photo) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        PhotoRequestBody photoRequestBody = new PhotoRequestBody(photo);
        request.body(photoRequestBody.getRequestBody());
        return request.post();
    }

    public static Response getPhotoRequest(Photo photo) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(ALBUM_ID, photo.getAlbumId())
                .get();
    }

    public static Response deletePhotoRequest(Photo photo) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        return request.delete(photo.getId());
    }
}
