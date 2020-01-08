package gorest.co.in.posts;

import gorest.co.in.users.UserRequest;
import gorest.co.in.users.User;
import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;

import static gorest.co.in.constants.BaseResponse.*;
import static gorest.co.in.posts.PostRequest.*;

public class Post {
    private String id;
    private String userId;
    private String title;
    private String body;

    public Post() {
    }

    public Post createRandomPost() {
        Response response = UserRequest.postUserRequest(new User().createRandomUser());
        String userId = Utils.jsonObject(response).getJSONObject(RESULT).get(ID).toString();

        return new Post.Builder()
                .setUserId(userId)
                .setTitle("new post title")
                .setBody("new post body")
                .build();
    }

    public Post returnPostFromResponse(Response response) {
        JSONObject jsonResult = Utils.jsonObject(response)
                .getJSONArray("result").getJSONObject(0);

        return new Post.Builder()
                .setId(jsonResult.get(ID).toString())
                .setUserId(jsonResult.get(USER_ID).toString())
                .setTitle(jsonResult.get(TITLE).toString())
                .setBody(jsonResult.get(BODY).toString())
                .build();
    }

    public void verifyPosts(Post actualPost, Post expectedPost) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPost.getId())
                .as("Id is incorrect.")
                .isEqualTo(expectedPost.getId());
        softAssertions.assertThat(actualPost.getUserId())
                .as("User id is incorrect.")
                .isEqualTo(expectedPost.getUserId());
        softAssertions.assertThat(actualPost.getTitle())
                .as("Title is incorrect.")
                .isEqualTo(expectedPost.getTitle());
        softAssertions.assertThat(actualPost.getBody())
                .as("Body is incorrect.")
                .isEqualTo(expectedPost.getBody());
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

    public String getBody() {
        return body;
    }

    public void setBody(String status) {
        this.body = status;
    }

    private Post(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.title = builder.title;
        this.body = builder.body;
    }

    public static class Builder {
        private String id;
        private String userId;
        private String title;
        private String body;

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

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
