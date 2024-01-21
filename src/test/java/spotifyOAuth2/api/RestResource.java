package spotifyOAuth2.api;

import io.restassured.response.Response;
import spotifyOAuth2.api.Specs;
import spotifyOAuth2.pojo.Playlist;

import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(Object payload, String token, String path) {
        return given(Specs.getRequestSpec()).body(payload).
                auth().oauth2(token).
                when().post(path).
                then().spec(Specs.getReponseSpec()).extract().response();
    }


    public static Response update( Object payload, String token,String path,String playlistID) {
        return Specs.getRequestSpec().body(payload).header("Authorization","Bearer "+token).
                pathParam("playlistID", playlistID).
                when().put(path).
                then().extract().response();
    }

    public static Response get(String token,String path,String playlistID) {
        return Specs.getRequestSpec().pathParam("playlistID", playlistID).header("Authorization","Bearer "+token).
                when().get(path).
                then().spec(Specs.getReponseSpec()).extract().response();
    }

}
