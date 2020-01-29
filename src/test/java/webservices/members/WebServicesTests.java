package webservices.members;

import webservices.utils.Log;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import webservices.utils.Utils;

import static webservices.members.MemberRequest.*;

public class WebServicesTests {
    @Test
    public void membersTest() {
        Response getResponse = getMembersRequest();
        String getResponseString = getResponse.getBody().asString();
        Log.info(getResponseString);

        Response postResponse = postMemberRequest(new Member().createRandomMember());
        String postResponseString = postResponse.getBody().asString();
        Log.info(postResponseString);

        Response getResponse2 = getMembersRequest();
        String getResponseString2 = getResponse2.getBody().asString();
        Log.info(getResponseString2);

        Response putResponse = putMemberRequest(new Member.
                Builder().setId("2").setFullName(new Utils().randomFullName).build());
        String putResponseString = putResponse.getBody().asString();
        Log.info(putResponseString);

        Response getResponse3 = getMembersRequest();
        String getResponseString3 = getResponse3.getBody().asString();
        Log.info(getResponseString3);
    }
}
