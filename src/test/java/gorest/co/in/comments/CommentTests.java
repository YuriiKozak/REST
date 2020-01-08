package gorest.co.in.comments;

import gorest.co.in.constants.AssertionMessages;
import gorest.co.in.utils.Utils;
import gorest.co.in.constants.StatusCodes;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static gorest.co.in.comments.CommentRequest.*;
import static gorest.co.in.comments.CommentResponse.*;

public class CommentTests implements AssertionMessages {

    private Comment comment = new Comment().createRandomComment();

    @Test
    public void createRandomComment() {
        Response response = postCommentRequest(comment);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.FOUND.getCode());

        JSONObject jsonObject = (JSONObject) Utils.jsonObject(response).get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.CREATED.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("A resource was successfully created in response to a POST request. " +
                        "The Location header contains the URL pointing to the newly created resource.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }

    @Test
    public void verifyRandomlyCreatedComment() {
        Response response = getCommentRequest(comment);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = Utils.jsonObject(response);

        JSONObject jsonResult = jsonObject.getJSONArray(RESULT).getJSONObject(0);

        comment.setId(jsonResult.get(ID).toString());
        comment.setEmail(jsonResult.get(EMAIL).toString());

        comment.verifyComments(comment.returnCommentFromResponse(response), comment);

        JSONObject json_meta = (JSONObject) jsonObject.get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(json_meta.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.OK.getCode());
        softAssertions.assertThat(json_meta.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("OK. Everything worked as expected.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }

    @Test
    public void deleteRandomlyCreatedComment() {
        Response response = deleteCommentRequest(comment);

        Assertions.assertThat(response.getStatusCode())
                .as(WRONG_RESPONSE_STATUS_CODE)
                .isEqualTo(StatusCodes.OK.getCode());

        JSONObject jsonObject = (JSONObject) Utils.jsonObject(response).get(META);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonObject.get(CODE))
                .as(WRONG_RESPONSE_CODE)
                .isEqualTo(StatusCodes.NO_CONTENT.getCode());
        softAssertions.assertThat(jsonObject.get(MESSAGE))
                .as(WRONG_RESPONSE_MESSAGE)
                .isEqualTo("The request was handled successfully and the response contains no body content.");
        softAssertions.assertAll();

        Utils.printResponse(response);
    }
}