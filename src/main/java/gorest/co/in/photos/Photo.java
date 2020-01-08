package gorest.co.in.photos;

import gorest.co.in.utils.Utils;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;

import static gorest.co.in.photos.RequestBody.*;

public class Photo {
    private String id;
    private String albumId;
    private String title;
    private String url;
    private String thumbnail;

    public Photo() {
    }

    public Photo createRandomPhoto() {
        return new Photo.Builder()
                .setAlbumId("777")
                .setTitle("new photo title")
                .setUrl(new Utils().randomUrl)
                .setThumbnail(new Utils().randomUrl)
                .build();
    }

    public Photo returnPhotoFromResponse(Response response) {
        JSONObject jsonResult = Utils.jsonObject(response)
                .getJSONArray("result").getJSONObject(0);

        return new Photo.Builder()
                .setId(jsonResult.get(ID).toString())
                .setAlbumId(jsonResult.get(ALBUM_ID).toString())
                .setTitle(jsonResult.get(TITLE).toString())
                .setUrl(jsonResult.get(URL).toString())
                .setThumbnail(jsonResult.get(THUMBNAIL).toString())
                .build();
    }

    public void verifyPhotos(Photo actualPhoto, Photo expectedPhoto) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPhoto.getId())
                .as("Id is incorrect.")
                .isEqualTo(expectedPhoto.getId());
        softAssertions.assertThat(actualPhoto.getAlbumId())
                .as("Album id is incorrect.")
                .isEqualTo(expectedPhoto.getAlbumId());
        softAssertions.assertThat(actualPhoto.getTitle())
                .as("Title is incorrect.")
                .isEqualTo(expectedPhoto.getTitle());
        softAssertions.assertThat(actualPhoto.getUrl())
                .as("Url is incorrect.")
                .isEqualTo(expectedPhoto.getUrl());
        softAssertions.assertThat(actualPhoto.getThumbnail())
                .as("Thumbnail is incorrect.")
                .isEqualTo(expectedPhoto.getThumbnail());
        softAssertions.assertAll();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    private Photo(Builder builder) {
        this.id = builder.id;
        this.albumId = builder.albumId;
        this.title = builder.title;
        this.url = builder.url;
        this.thumbnail = builder.thumbnail;
    }

    static class Builder {
        private String id;
        private String albumId;
        private String title;
        private String url;
        private String thumbnail;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setAlbumId(String albumId) {
            this.albumId = albumId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Photo build() {
            return new Photo(this);
        }
    }
}
