package gorest.co.in.albums;

import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static gorest.co.in.constants.BaseRequest.*;
import static gorest.co.in.albums.RequestBody.*;

public class RequestBuilder implements BaseUrls {
    public static Response postAlbumRequest(Album album) {
        RestAssured.baseURI = albumsURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        RequestBody requestBody = new RequestBody(album);
        request.body(requestBody.getRequestBody());
        return request.post();
    }

    public static Response getAlbumRequest(Album album) {
        RestAssured.baseURI = albumsURI;
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(USER_ID, album.getUserId())
                .get();
    }

    public static Response deleteAlbumRequest(Album album) {
        RestAssured.baseURI = albumsURI;
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        return request.delete(album.getId());
    }
}
