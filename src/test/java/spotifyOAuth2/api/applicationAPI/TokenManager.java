package spotifyOAuth2.api.applicationAPI;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import spotifyOAuth2.utils.ConfigLoader;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TokenManager {

    public static String accessToken;
    public synchronized static String getNewAccessToken()  {
        if(accessToken==null) {
            HashMap<String, String> formparams = new HashMap<>();
            formparams.put("grant_type", ConfigLoader.getInstance().getGrantType());
            formparams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
            formparams.put("client_id", ConfigLoader.getInstance().getClientID());
            formparams.put("client_secret", ConfigLoader.getInstance().getClientSecret());

            Response response = given().baseUri("https://accounts.spotify.com").contentType(ContentType.URLENC).
                    formParams(formparams)
                    .filter(new AllureRestAssured()).
                    when().post("/api/token").
                    then().extract().response();
            if (response.statusCode() != 200)
                throw new RuntimeException("ABORT!! New ACCESS TOKEN not generated.");
            accessToken = response.path("access_token");
        }

        return accessToken;
    }
}
