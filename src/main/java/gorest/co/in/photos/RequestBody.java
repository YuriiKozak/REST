package gorest.co.in.photos;

import gorest.co.in.constants.BaseRequest;
import org.json.JSONObject;

import java.util.*;

public class RequestBody implements BaseRequest {
    public static final String ALBUM_ID = "album_id";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String THUMBNAIL = "thumbnail";

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public RequestBody(Photo photo) {
        setAlbumId(photo.getAlbumId());
        setTitle(photo.getTitle());
        setUrl(photo.getUrl());
        setThumbnail(photo.getThumbnail());
    }

    public RequestBody setAlbumId(String albumId) {
        requestParams.put(ALBUM_ID, albumId);
        return this;
    }

    public RequestBody setTitle(String title) {
        requestParams.put(TITLE, title);
        return this;
    }

    public RequestBody setUrl(String url) {
        requestParams.put(URL, url);
        return this;
    }

    public RequestBody setThumbnail(String thumbnail) {
        requestParams.put(THUMBNAIL, thumbnail);
        return this;
    }
}
