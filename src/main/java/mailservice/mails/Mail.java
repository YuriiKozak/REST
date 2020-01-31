package mailservice.mails;

import mailservice.utils.*;

public class Mail {
    private String id;
    private String subject;
    private String email;
    private String body;

    public Mail() {
    }

    public Mail createRandomMail() {
        Log.info("Creating Random Mail.");
        return new Mail.Builder()
                .setSubject(new Utils().randomSubject)
                .setEmail(new Utils().randomEmail)
                .setBody(new Utils().randomBody)
                .build();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private Mail(Builder builder) {
        this.id = builder.id;
        this.subject = builder.subject;
        this.email = builder.email;
        this.body = builder.body;
    }

    public static class Builder {
        private String id;
        private String subject;
        private String email;
        private String body;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Mail build() {
            return new Mail(this);
        }
    }
}
