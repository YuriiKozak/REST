package gorest.co.in.comments;

import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;

import static gorest.co.in.comments.RequestBody.*;

public class Comment {
    private String id;
    private String postId;
    private String name;
    private String email;
    private String body;

    public Comment() {
    }

    public Comment createRandomComment() {
        Comment comment = new Comment();
        comment.setPostId("777");
        comment.setName("new comment name");
        comment.setEmail(new Utils().randomEmail);
        comment.setBody("new comment body");
        return comment;
    }

    public Comment returnCommentFromResponse(Response response) {
        JSONObject jsonResult = Utils.jsonObject(response)
                .getJSONArray("result").getJSONObject(0);

        Comment comment = new Comment();
        comment.setId(jsonResult.get(ID).toString());
        comment.setPostId(jsonResult.get(POST_ID).toString());
        comment.setName(jsonResult.get(NAME).toString());
        comment.setEmail(jsonResult.get(EMAIL).toString());
        comment.setBody(jsonResult.get(BODY).toString());
        return comment;
    }

    public void verifyComments(Comment actualComment, Comment expectedComment) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualComment.getId())
                .as("Id is incorrect.")
                .isEqualTo(expectedComment.getId());
        softAssertions.assertThat(actualComment.getPostId())
                .as("Post id is incorrect.")
                .isEqualTo(expectedComment.getPostId());
        softAssertions.assertThat(actualComment.getName())
                .as("Name is incorrect.")
                .isEqualTo(expectedComment.getName());
        softAssertions.assertThat(actualComment.getEmail())
                .as("Email is incorrect.")
                .isEqualTo(expectedComment.getEmail());
        softAssertions.assertThat(actualComment.getBody())
                .as("Body is incorrect.")
                .isEqualTo(expectedComment.getBody());
        softAssertions.assertAll();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private Comment(Builder builder) {
        this.id = builder.id;
        this.postId = builder.userId;
        this.name = builder.name;
        this.name = builder.email;
        this.body = builder.body;
    }

    static class Builder {
        private String id;
        private String userId;
        private String name;
        private String email;
        private String body;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}
