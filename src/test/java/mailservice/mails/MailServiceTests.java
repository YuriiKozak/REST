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

        Response getResponse2 = getMailsRequest(SUBJECT, mail.getSubject());
        String getResponseString2 = getResponse2.getBody().asString();
        Log.info(getResponseString2);

        Response getResponse3 = getMailsRequest(EMAIL, mail.getEmail());
        String getResponseString3 = getResponse3.getBody().asString();
        Log.info(getResponseString3);

        Response getResponse4 = getMailsRequest();
        String getResponseString4 = getResponse4.getBody().asString();
        Log.info(getResponseString4);

        Response deleteResponse = deleteMailRequest(mail);
        String deleteResponseString = deleteResponse.getBody().asString();
        Log.info(deleteResponseString);

        Response getResponse5 = getMailsRequest();
        String getResponseString5 = getResponse5.getBody().asString();
        Log.info(getResponseString5);
    }
}
