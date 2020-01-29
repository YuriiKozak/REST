package gorest.co.in.users;

import gorest.co.in.utils.*;

public class User {
    private String website;
    private String address;
    private String gender;
    private String phone;
    private Links _links;
    private String dob;
    private String last_name;
    private String id;
    private String first_name;
    private String email;
    private String status;

    public User() {
    }

    public User createRandomUser() {
        Log.info("Creating Random User.");
        return new User.Builder()
                .setWebsite("https://gorest.co.in/website")
                .setAddress("USA")
                .setGender("male")
                .setPhone("777.555.333")
                .setDob("1988-06-03")
                .setLastName("Doe")
                .setFirstName("John")
                .setEmail(new Utils().randomEmail)
                .setStatus("active")
                .setLinks(new Links(new Edit(), new Self(),
                        new Avatar("https://gorest.co.in/avatar")))
                .build();
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
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
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

    public Links getLinks() {
        return _links;
    }

    public void setLinks(Links _links) {
        this._links = _links;
    }

    private User(Builder builder) {
        this.website = builder.website;
        this.address = builder.address;
        this.gender = builder.gender;
        this.phone = builder.phone;
        this.dob = builder.dob;
        this.last_name = builder.lastName;
        this.id = builder.id;
        this.first_name = builder.firstName;
        this.email = builder.email;
        this.status = builder.status;
        this._links = builder.links;
    }

    public static class Builder {
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
        private Links links;

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

        public Builder setLinks(Links links) {
            this.links = links;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

class Links {
    private Edit edit;
    private Self self;
    private Avatar avatar;

    public Links(Edit edit, Self self, Avatar avatar) {
        setEdit(edit);
        setSelf(self);
        setAvatar(avatar);
    }

    public Edit getEdit() {
        return edit;
    }

    public void setEdit(Edit edit) {
        this.edit = edit;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}

class Edit {
    private String href;

    public Edit(String href) {
        setHref(href);
    }

    public Edit() {
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

class Self {
    private String href;

    public Self(String href) {
        setHref(href);
    }

    public Self() {
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

class Avatar {
    private String href;

    public Avatar(String href) {
        setHref(href);
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
