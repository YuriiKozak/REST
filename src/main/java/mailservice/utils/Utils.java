package mailservice.utils;

import com.github.javafaker.Faker;

public class Utils {
    public String randomFullName = new Faker().name().fullName();
    public String randomSubject = new Faker().cat().name();
    public String randomEmail = new Faker().internet().emailAddress();
    public String randomBody = new Faker().cat().registry();
}
