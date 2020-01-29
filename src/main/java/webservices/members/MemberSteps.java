package webservices.members;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import webservices.utils.*;

import static webservices.members.MemberRequest.*;
import static webservices.members.MemberResponse.*;

public class MemberSteps extends Member {
    public static Member returnMemberFromResponse(Response response) {
        Log.info("Return Member From Response.");
        JSONObject jsonResult = JsonObject.jsonObject(response).getJSONArray(RESULT).getJSONObject(0);

        return new Builder()
                .setId(jsonResult.get(ID).toString())
                .setFullName(jsonResult.get(FULL_NAME).toString())
                .build();
    }

    public static void verifyMembers(Member actualMember, Member expectedMember) {
        Log.info("Verify Members.");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualMember.getId())
                .as("Id is incorrect.")
                .isEqualTo(expectedMember.getId());
        softAssertions.assertThat(actualMember.getFullName())
                .as("Full name is incorrect.")
                .isEqualTo(expectedMember.getFullName());
        softAssertions.assertAll();
    }
}
