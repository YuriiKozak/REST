package gorest.co.in.constants;

public interface AssertionMessages {
    String WRONG_RESPONSE_CODE = "Wrong response code.";
    String WRONG_RESPONSE_MESSAGE = "Wrong response message.";

    String OK_EVERYTHING_WORKED_AS_EXPECTED = "OK. Everything worked as expected.";
    String THE_REQUEST_WAS_HANDLED_SUCCESSFULLY =
            "The request was handled successfully and the response contains no body content.";
    String A_RESOURCE_WAS_SUCCESSFULLY_CREATED = "A resource was successfully created in response " +
            "to a POST request. The Location header contains the URL pointing to the newly created resource.";
}
