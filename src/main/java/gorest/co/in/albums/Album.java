package gorest.co.in.albums;

import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;

import static gorest.co.in.albums.RequestBody.*;

public class Album {
    private String id;
    private String userId;
    private String title;

    public Album() {}

    public Album createRandomAlbum() {
        Album album = new Album();
        album.setUserId("1777");
        album.setTitle("new album title");
        return album;
    }

    public Album returnAlbumFromResponse(Response response) {
        Utils utils = new Utils();
        JSONObject jsonResult = utils.jsonObject(response)
                .getJSONArray("result").getJSONObject(0);

        Album album = new Album();
        album.setId(jsonResult.get(ID).toString());
        album.setUserId(jsonResult.get(USER_ID).toString());
        album.setTitle(jsonResult.get(TITLE).toString());
        return album;
    }

    public void verifyAlbums(Album actualAlbum, Album expectedAlbum) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualAlbum.getId())
                .as("Id is incorrect.")
                .isEqualTo(expectedAlbum.getId());
        softAssertions.assertThat(actualAlbum.getUserId())
                .as("User id is incorrect.")
                .isEqualTo(expectedAlbum.getUserId());
        softAssertions.assertThat(actualAlbum.getTitle())
                .as("Title is incorrect.")
                .isEqualTo(expectedAlbum.getTitle());
        softAssertions.assertAll();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String first_name) {
        this.userId = first_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String email) {
        this.title = email;
    }

    private Album(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.title = builder.title;
    }

    static class Builder {
        private String id;
        private String userId;
        private String title;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Album build() {
            return new Album(this);
        }
    }
}
