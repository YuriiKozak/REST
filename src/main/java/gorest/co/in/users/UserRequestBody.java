package gorest.co.in.users;

import gorest.co.in.constants.BaseRequest;
import org.json.JSONObject;

import java.util.*;

public class UserRequestBody implements BaseRequest {
    public static final String WEBSITE = "website";
    public static final String ADDRESS = "address";
    public static final String GENDER = "gender";
    public static final String PHONE = "phone";
    public static final String DOB = "dob";
    public static final String LAST_NAME = "last_name";
    public static final String FIRST_NAME = "first_name";
    public static final String EMAIL = "email";
    public static final String STATUS = "status";

    private Map<String, String> requestParams = new HashMap<>();

    public String getRequestBody() {
        return new JSONObject(requestParams).toString();
    }

    public UserRequestBody(User user) {
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

    public UserRequestBody setWebsite(String website) {
        requestParams.put(WEBSITE, website);
        return this;
    }

    public UserRequestBody setAddress(String address) {
        requestParams.put(ADDRESS, address);
        return this;
    }

    public UserRequestBody setGender(String gender) {
        requestParams.put(GENDER, gender);
        return this;
    }

    public UserRequestBody setPhone(String phone) {
        requestParams.put(PHONE, phone);
        return this;
    }

    public UserRequestBody setDob(String dob) {
        requestParams.put(DOB, dob);
        return this;
    }

    public UserRequestBody setLastName(String lastName) {
        requestParams.put(LAST_NAME, lastName);
        return this;
    }

    public UserRequestBody setFirstName(String firstName) {
        requestParams.put(FIRST_NAME, firstName);
        return this;
    }

    public UserRequestBody setEmail(String email) {
        requestParams.put(EMAIL, email);
        return this;
    }

    public UserRequestBody setStatus(String status) {
        requestParams.put(STATUS, status);
        return this;
    }
}
