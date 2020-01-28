package webservices.members;

import webservices.utils.Log;
import webservices.utils.Utils;

public class Member {
    private String id;
    private String full_name;

    public Member() {
    }

    public Member createRandomMember() {
        Log.info("Creating Random Member.");
        return new Member.Builder()
                .setFullName(new Utils().randomFullName)
                .build();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    private Member(Builder builder) {
        this.id = builder.id;
        this.full_name = builder.full_name;
    }

    public static class Builder {
        private String id;
        private String full_name;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setFullName(String full_name) {
            this.full_name = full_name;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }
}
