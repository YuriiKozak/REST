package mailservice.mails;

import io.restassured.response.Response;
import mailservice.utils.*;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;

import static mailservice.mails.MailResponse.*;
import static mailservice.mails.MailRequest.*;

public class MailSteps extends Mail {
    public static Mail returnMailFromResponse(Response response) {
        Log.info("Return Mail From Response.");
        JSONObject jsonResult = JsonObject.jsonObject(response).getJSONArray(RESULT).getJSONObject(0);

        return new Builder()
                .setId(jsonResult.get(ID).toString())
                .setSubject(jsonResult.get(SUBJECT).toString())
                .setEmail(jsonResult.get(EMAIL).toString())
                .setBody(jsonResult.get(BODY).toString())
                .build();
    }

    public static void verifyMails(Mail actualMail, Mail expectedMail) {
        Log.info("Verify Mails.");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualMail.getId())
                .as("Id is incorrect.")
                .isEqualTo(expectedMail.getId());
        softAssertions.assertThat(actualMail.getSubject())
                .as("Subject is incorrect.")
                .isEqualTo(expectedMail.getSubject());
        softAssertions.assertThat(actualMail.getEmail())
                .as("Email is incorrect.")
                .isEqualTo(expectedMail.getEmail());
        softAssertions.assertThat(actualMail.getBody())
                .as("Body is incorrect.")
                .isEqualTo(expectedMail.getBody());
        softAssertions.assertAll();
    }
}
