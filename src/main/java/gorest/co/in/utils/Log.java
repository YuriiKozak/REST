package gorest.co.in.utils;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Log {
    private static Logger Log = LogManager.getLogger(Log.class.getName());

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }

    public static void info(Response response) {
        Log.info("Response:\n" + new JSONObject(response.getBody().asString()).toString(5));
    }
}
