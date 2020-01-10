package gorest.co.in.albums;

import gorest.co.in.users.UserRequest;
import gorest.co.in.users.User;
import gorest.co.in.utils.Log;
import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;

import static gorest.co.in.albums.AlbumRequest.*;
import static gorest.co.in.constants.BaseResponse.*;

public class Album {
    private String id;
    private String userId;
    private String title;

    public Album() {
    }

    public Album createRandomAlbum() {
        Log.info("Creating Random Album.");
        Response response = UserRequest.postUserRequest(new User().createRandomUser());
        String userId = Utils.jsonObject(response).getJSONObject(RESULT).get(ID).toString();

        return new Album.Builder()
                .setUserId(userId)
                .setTitle("new album title")
                .build();
    }

    public Album returnAlbumFromResponse(Response response) {
        JSONObject jsonResult = Utils.jsonObject(response)
                .getJSONArray("result").getJSONObject(0);

        return new Album.Builder()
                .setId(jsonResult.get(ID).toString())
                .setUserId(jsonResult.get(USER_ID).toString())
                .setTitle(jsonResult.get(TITLE).toString())
                .build();
    }

    public void verifyAlbums(Album actualAlbum, Album expectedAlbum) {
        Log.info("Verifying Albums.");
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

    public static class Builder {
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
