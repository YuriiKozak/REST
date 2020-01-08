package gorest.co.in.photos;

import gorest.co.in.constants.BaseRequest;
import org.json.JSONObject;

import java.util.*;

public class PhotoRequest implements BaseRequest {
    public static final String ALBUM_ID = "album_id";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String THUMBNAIL = "thumbnail";

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
