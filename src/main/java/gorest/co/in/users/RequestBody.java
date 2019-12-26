package gorest.co.in.users;

import org.json.JSONObject;

import java.util.*;

public class RequestBody {
    public static final String WEBSITE = "website";
    public static final String ADDRESS = "address";
    public static final String GENDER = "gender";
    public static final String PHONE = "phone";
    public static final String DOB = "dob";
    public static final String LAST_NAME = "last_name";
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String EMAIL = "email";
    public static final String STATUS = "status";

    public static final String ACCESS_TOKEN = "access-token";

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public RequestBody(User user) {
        setWebsite(user.getWebsite());
        setAddress(user.getAddress());
        setGender(user.getGender());
        setPhone(user.getPhone());
        setDob(user.getDob());
        setLastName(user.getLastName());
        setFirstName(user.getFirstName());
        setEmail(user.getEmail());
        setStatus(user.getStatus());
    }

    public RequestBody setWebsite(String website) {
        requestParams.put(WEBSITE, website);
        return this;
    }

    public RequestBody setAddress(String address) {
        requestParams.put(ADDRESS, address);
        return this;
    }

    public RequestBody setGender(String gender) {
        requestParams.put(GENDER, gender);
        return this;
    }

    public RequestBody setPhone(String phone) {
        requestParams.put(PHONE, phone);
        return this;
    }

    public RequestBody setDob(String dob) {
        requestParams.put(DOB, dob);
        return this;
    }

    public RequestBody setLastName(String lastName) {
        requestParams.put(LAST_NAME, lastName);
        return this;
    }

    public RequestBody setFirstName(String firstName) {
        requestParams.put(FIRST_NAME, firstName);
        return this;
    }

    public RequestBody setEmail(String email) {
        requestParams.put(EMAIL, email);
        return this;
    }

    public RequestBody setStatus(String status) {
        requestParams.put(STATUS, status);
        return this;
    }
}
