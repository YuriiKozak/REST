package gorest.co.in.users;

import gorest.co.in.utils.*;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;

import static gorest.co.in.users.UserResponse.*;
import static gorest.co.in.users.UserRequest.*;

public class UserSteps extends User {
    public static User returnUserFromResponse(Response response) {
        Log.info("Return User From Response.");
        JSONObject jsonResult = JsonObject.jsonObject(response).getJSONArray(RESULT).getJSONObject(0);
        JSONObject jsonLinks = jsonResult.getJSONObject(LINKS);

        return new User.Builder()
                .setWebsite(jsonResult.get(WEBSITE).toString())
                .setAddress(jsonResult.get(ADDRESS).toString())
                .setGender(jsonResult.get(GENDER).toString())
                .setPhone(jsonResult.get(PHONE).toString())
                .setDob(jsonResult.get(DOB).toString())
                .setLastName(jsonResult.get(LAST_NAME).toString())
                .setId(jsonResult.get(ID).toString())
                .setFirstName(jsonResult.get(FIRST_NAME).toString())
                .setEmail(jsonResult.get(EMAIL).toString())
                .setStatus(jsonResult.get(STATUS).toString())
                .setLinks(new Links(new Edit(jsonLinks.getJSONObject(EDIT).get("href").toString()),
                        new Self(jsonLinks.getJSONObject(SELF).get("href").toString()),
                        new Avatar(jsonLinks.getJSONObject(AVATAR).get("href").toString())))
                .build();
    }

    public static void verifyUsers(User actualUser, User expectedUser) {
        Log.info("Verify Users.");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualUser.getWebsite())
                .as("Website is incorrect.")
                .isEqualTo(expectedUser.getWebsite());
        softAssertions.assertThat(actualUser.getAddress())
                .as("Address is incorrect.")
                .isEqualTo(expectedUser.getAddress());
        softAssertions.assertThat(actualUser.getGender())
                .as("Gender is incorrect.")
                .isEqualTo(expectedUser.getGender());
        softAssertions.assertThat(actualUser.getPhone())
                .as("Phone is incorrect.")
                .isEqualTo(expectedUser.getPhone());
        softAssertions.assertThat(actualUser.getDob())
                .as("Dob is incorrect.")
                .isEqualTo(expectedUser.getDob());
        softAssertions.assertThat(actualUser.getLastName())
                .as("Last name is incorrect.")
                .isEqualTo(expectedUser.getLastName());
        softAssertions.assertThat(actualUser.getId())
                .as("Id is incorrect.")
                .isEqualTo(expectedUser.getId());
        softAssertions.assertThat(actualUser.getFirstName())
                .as("First name is incorrect.")
                .isEqualTo(expectedUser.getFirstName());
        softAssertions.assertThat(actualUser.getEmail())
                .as("Email is incorrect.")
                .isEqualTo(expectedUser.getEmail());
        softAssertions.assertThat(actualUser.getStatus())
                .as("Status is incorrect.")
                .isEqualTo(expectedUser.getStatus());
        softAssertions.assertThat(actualUser.getLinks().getEdit().getHref())
                .as("Edit is incorrect.")
                .isEqualTo(expectedUser.getLinks().getEdit().getHref());
        softAssertions.assertThat(actualUser.getLinks().getSelf().getHref())
                .as("Self is incorrect.")
                .isEqualTo(expectedUser.getLinks().getSelf().getHref());
        softAssertions.assertThat(actualUser.getLinks().getAvatar().getHref())
                .as("Avatar is incorrect.")
                .isEqualTo(expectedUser.getLinks().getAvatar().getHref());
        softAssertions.assertAll();
    }
}
