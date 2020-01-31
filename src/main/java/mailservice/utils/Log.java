package mailservice.utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.*;
import org.json.JSONObject;

public class Log {
    private static Logger Log = LogManager.getLogger(mailservice.utils.Log.class.getName());

    @Step("{message}")
    public static void info(String message) {
        Log.info(message);
    }

    @Step("{message}")
    public static void warn(String message) {
        Log.warn(message);
    }

    @Step("{message}")
    public static void error(String message) {
        Log.error(message);
    }

    @Step("{message}")
    public static void fatal(String message) {
        Log.fatal(message);
    }

    @Step("{message}")
    public static void debug(String message) {
        Log.debug(message);
    }

    @Step()
    public static void info(Response response) {
        Log.info("Response:\n" + new JSONObject(response.getBody().asString()).toString(5));
    }
}
