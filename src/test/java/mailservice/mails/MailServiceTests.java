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

        Mail mail = new Mail().createRandomMail();
        Response postResponse = postMailRequest(mail);
        String postResponseString = postResponse.getBody().asString();
        Log.info(postResponseString);

        Response getResponse2 = getMailsRequest(mail);
        String getResponseString2 = getResponse2.getBody().asString();
        Log.info(getResponseString2);

        Response deleteResponse = deleteMailRequest(mail);
        String deleteResponseString = deleteResponse.getBody().asString();
        Log.info(deleteResponseString);

        Response getResponse3 = getMailsRequest();
        String getResponseString3 = getResponse3.getBody().asString();
        Log.info(getResponseString3);
    }
}
