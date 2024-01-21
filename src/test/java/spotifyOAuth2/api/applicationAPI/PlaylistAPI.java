package spotifyOAuth2.api.applicationAPI;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import spotifyOAuth2.api.RestResource;
import spotifyOAuth2.pojo.Playlist;
import spotifyOAuth2.utils.ConfigLoader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static spotifyOAuth2.api.Routes.PLAYLISTS;
import static spotifyOAuth2.api.Routes.USERS;
import static spotifyOAuth2.api.applicationAPI.TokenManager.getNewAccessToken;

public class PlaylistAPI {
//    static String  accessToken ="BQBPsTEo95LCKkBV9hm7xqu91EtXeBd44OLqqvXuw1PQcpZdDWNdOxYqQtwrU_v7Q25wZt0w935sKnIXdPT_u1k5C0EPuECECtyXfsELsfc_v7c4O31LT4OXeZV_rZP38DBsyQEwMEf2k33s5ErnC2ck9LSGKrfbh6lK2on7dk9J563qKRFP2QV8HKjdiZFTsO0E6AYGGlKZNnFGE4d91hs5H8h8LZ7zuMmL5sSw1RWQJD15Z9TFoeFC1Ufozz-qersvcD2JAPyI";
    static String  accessToken = getNewAccessToken();
    @Step
    public static Response post(Playlist requestPlaylist)  {
        return RestResource.post(requestPlaylist,accessToken,USERS+"/"+ConfigLoader.getInstance().getUserID()+PLAYLISTS);
    }

    @Step
    public static Response post(String accessToken,Playlist requestPlaylist)  {
        return RestResource.post(requestPlaylist,accessToken,USERS+"/"+ConfigLoader.getInstance().getUserID()+PLAYLISTS);


    }

    @Step
    public static Response update(String playlistID, Playlist requestPlaylist) {
        return RestResource.update(requestPlaylist,accessToken,USERS+"/"+ConfigLoader.getInstance().getUserID()+PLAYLISTS+"/{playlistID}",playlistID);

    }


    @Step
    public static Response get(String playlistID) {
        return RestResource.get(accessToken,PLAYLISTS+"/{playlistID}",playlistID);
    }

}
