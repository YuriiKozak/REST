package mailservice.mails;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import mailservice.utils.Log;

import static mailservice.mails.MailRequest.*;

public class MailServiceTests {
    @Test
    public void mailsTest() {
        Response getResponse = getMailsRequest();
        String getResponseString = getResponse.getBody().asString();
        Log.info(getResponseString);

        Response postResponse = postMailRequest(new Mail().createRandomMail());
        String postResponseString = postResponse.getBody().asString();
        Log.info(postResponseString);

        Response getResponse2 = getMailsRequest();
        String getResponseString2 = getResponse2.getBody().asString();
        Log.info(getResponseString2);
    }
}
