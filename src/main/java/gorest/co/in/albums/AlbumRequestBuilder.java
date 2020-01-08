package gorest.co.in.albums;

import gorest.co.in.constants.BaseUrls;
import gorest.co.in.headers.RequestHeader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static gorest.co.in.albums.AlbumRequest.*;

public class AlbumRequestBuilder implements BaseUrls {
    private static void setBaseURI() {
        RestAssured.baseURI = albumsURI;
    }

    public static Response postAlbumRequest(Album album) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        AlbumRequest albumRequest = new AlbumRequest(album);
        request.body(albumRequest.getRequestBody());
        return request.post();
    }

    public static Response getAlbumRequest(Album album) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        return request
                .when()
                .queryParam(ACCESS_TOKEN, RequestHeader.accessToken)
                .queryParam(USER_ID, album.getUserId())
                .get();
    }

    public static Response deleteAlbumRequest(Album album) {
        setBaseURI();
        RequestSpecification request = RestAssured.given();
        request.headers(RequestHeader.getHeaders());
        return request.delete(album.getId());
    }
}
