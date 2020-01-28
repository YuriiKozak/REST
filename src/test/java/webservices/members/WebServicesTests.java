package webservices.members;

import webservices.utils.Log;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static webservices.members.MemberRequest.*;

public class WebServicesTests {
    @Test
    public void getMembers() {
        Response getResponse = getMembersRequest();
        String getResponseString = getResponse.getBody().asString();
        Log.info(getResponseString);

        Response postResponse = postMemberRequest(new Member().createRandomMember());
        String postResponseString = postResponse.getBody().asString();
        Log.info(postResponseString);

        Response getResponse2 = getMembersRequest();
        String getResponseString2 = getResponse2.getBody().asString();
        Log.info(getResponseString2);
    }
}
