package webservices.utils;

import com.github.javafaker.Faker;

public class Utils {
    public String randomFullName = new Faker().name().fullName();
}
