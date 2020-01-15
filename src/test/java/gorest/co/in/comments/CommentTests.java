package gorest.co.in.comments;

import gorest.co.in.utils.Log;
import gorest.co.in.utils.Utils;
import gorest.co.in.constants.StatusCodes;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.constants.AssertionMessages.*;
import static gorest.co.in.comments.CommentRequest.*;
import static gorest.co.in.comments.CommentResponse.*;

public class CommentTests {
    private Comment comment = new Comment().createRandomComment();

    @Test
    public void createRandomComment() {
        Response response = postCommentRequest(comment);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.FOUND.getCode());

        JSONObject jsonObject = Utils.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.CREATED.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(A_RESOURCE_WAS_SUCCESSFULLY_CREATED);
        softAssertions.assertAll();
    }

    @Test
    public void verifyRandomlyCreatedComment() {
        Response response = getCommentRequest(comment);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonResult = Utils.jsonObjectResult(response);

        comment.setId(jsonResult.get(ID).toString());
        comment.setEmail(jsonResult.get(EMAIL).toString());

        comment.verifyComments(comment.returnCommentFromResponse(response), comment);

        JSONObject json_meta = Utils.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(OK_EVERYTHING_WORKED_AS_EXPECTED);
        softAssertions.assertAll();
    }

    @Test
    public void updateRandomlyCreatedComment() {
        comment.setName("updated comment name");
        comment.setBody("updated comment body");

        Response response = patchCommentRequest(comment);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = Utils.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(OK_EVERYTHING_WORKED_AS_EXPECTED);
        softAssertions.assertAll();
    }

    @Test
    public void verifyRandomlyUpdatedComment() {
        Response response = getCommentRequest(comment);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        comment.verifyComments(comment.returnCommentFromResponse(response), comment);

        JSONObject json_meta = Utils.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(OK_EVERYTHING_WORKED_AS_EXPECTED);
        softAssertions.assertAll();
    }

    @Test
    public void deleteRandomlyCreatedComment() {
        Response response = deleteCommentRequest(comment);
        Log.info(response);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = Utils.jsonObjectMeta(response);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.NO_CONTENT.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo(THE_REQUEST_WAS_HANDLED_SUCCESSFULLY);
        softAssertions.assertAll();
    }
}