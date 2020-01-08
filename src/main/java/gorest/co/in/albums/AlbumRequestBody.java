package gorest.co.in.albums;

import gorest.co.in.constants.BaseRequest;
import org.json.JSONObject;

import java.util.*;

public class AlbumRequestBody implements BaseRequest {
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public AlbumRequestBody(Album album) {
        setUserId(album.getUserId());
        setTitle(album.getTitle());
    }

    public AlbumRequestBody setUserId(String userId) {
        requestParams.put(USER_ID, userId);
        return this;
    }

    public AlbumRequestBody setTitle(String title) {
        requestParams.put(TITLE, title);
        return this;
    }
}
