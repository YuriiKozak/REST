package gorest.co.in;

import io.restassured.response.Response;
import org.json.JSONObject;

import static gorest.co.in.RequestBody.*;

public class User {
    private String website;
    private String address;
    private String gender;
    private String phone;
    private String dob;
    private String lastName;
    private String id;
    private String firstName;
    private String email;
    private String status;

    public static final String randomEmail = System.currentTimeMillis() + "@gmail.com";

    public User() {}

    public User createRandomUser() {
        User user = new User();
        user.setWebsite("https://gorest.co.in/");
        user.setAddress("USA");
        user.setGender("male");
        user.setPhone("777.555.333");
        user.setDob("1988-06-03");
        user.setLastName("Doe");
        user.setFirstName("John");
        user.setEmail(randomEmail);
        user.setStatus("active");
        return user;
    }

    public static User returnUserFromResponse(Response response) {
        JSONObject jsonResult = Utils.jsonObject(response)
                .getJSONArray("result").getJSONObject(0);

        User user = new User();
        user.setWebsite(jsonResult.get(WEBSITE).toString());
        user.setAddress(jsonResult.get(ADDRESS).toString());
        user.setGender(jsonResult.get(GENDER).toString());
        user.setPhone(jsonResult.get(PHONE).toString());
        user.setDob(jsonResult.get(DOB).toString());
        user.setLastName(jsonResult.get(LAST_NAME).toString());
        user.setId(jsonResult.get(ID).toString());
        user.setFirstName(jsonResult.get(FIRST_NAME).toString());
        user.setEmail(jsonResult.get(EMAIL).toString());
        user.setStatus(jsonResult.get(STATUS).toString());
        return user;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private User(Builder builder) {
        this.website = builder.website;
        this.address = builder.address;
        this.gender = builder.gender;
        this.phone = builder.phone;
        this.dob = builder.dob;
        this.lastName = builder.lastName;
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.email = builder.email;
        this.status = builder.status;
    }

    static class Builder {
        private String website;
        private String address;
        private String gender;
        private String phone;
        private String dob;
        private String lastName;
        private String id;
        private String firstName;
        private String email;
        private String status;

        public Builder setWebsite(String website) {
            this.website = website;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setDob(String dob) {
            this.dob = dob;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
