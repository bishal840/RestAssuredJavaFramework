package spotifyOAuth2.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import spotifyOAuth2.api.applicationAPI.PlaylistAPI;
import spotifyOAuth2.pojo.ErrorRoot;
import spotifyOAuth2.pojo.Playlist;
import spotifyOAuth2.utils.FakerUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlayListsTest {

    String expired_accessToken = "BQCUVm1-nhtNA14T6mevyaWu5ojo5ZQruv7W77eRB_nH9m97MojCx-gH9i8MzPPUmFc-KtofqqMjlEdu9VoAdSbCKCw8b8-2jldBzpDjKmwq14kmpMoMYm0-2L2pV_PkQs3CxTMQZbWRsMvnaaW3XJuXGywqGtfj7FGiy1yyc7DSCshILlvgUIkMIz7tELr1mRV_M3uI29azov3x5oQEnGvjRAUlISXpnR_JCY2sm_wsKUUKbdy8EpRPII6nl1e4EA6fVg4m5NLv";
    String playlistID="";

    @Story("Create Playlist")
    @Link(value = "https://www.atlassian.com/",name = "JIRA LINK")
    @Description("Use expired Access Token to Create Playlist and validate Error")
    @Test(priority = 4, description = "CreatePlaylist_expiredAccessToken")
    public void shouldNOTBeAbleToCreatePlaylist_expiredAccessToken()
    {
        Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);
        Response response = PlaylistAPI.post(expired_accessToken,requestPlaylist);


        ErrorRoot errorRoot=response.as(ErrorRoot.class);

        assertThat(response.statusCode(),equalTo(401));
        assertError(errorRoot,401,"The access token expired");
    }

    @Story("Create Playlist")
    @Link(value = "https://www.atlassian.com/",name = "JIRA LINK")
    @Description("Try to Create Playlist without name and validate Error")
    @Test(priority = 3,description = "CreatePlaylist_noName")
    public void shouldNOTBeAbleToCreatePlaylist_noName()
    {
        Playlist requestPlaylist = Playlist.builder().description("New Playlist POJO description")._public(false).build();


        Response response = PlaylistAPI.post(requestPlaylist);

        ErrorRoot errorRoot =response.as(ErrorRoot.class);

        assertThat(response.statusCode(),equalTo(400));
        assertError(errorRoot,400,"Missing required field: name");

    }

    @Story("Create Playlist")
    @Link(value = "https://www.atlassian.com/",name = "JIRA LINK")
    @Description("Create Playlist with Valid data")
    @Test(priority = 0, description = "Create Playlist")
    public void shouldBeAbleToCreatePlaylist()
    {

        Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);

        Response response = PlaylistAPI.post(requestPlaylist);


        Playlist responsePlaylist= response.as(Playlist.class);
        assertThat(response.statusCode(),equalTo(201));
        assertPlayList(requestPlaylist,responsePlaylist);


        playlistID =responsePlaylist.getId();

        System.out.println("ID:"+playlistID);

    }
    @Link(value = "https://www.atlassian.com/",name = "JIRA LINK")
    @Description("Get Playlist details using Playlist ID")
    @Test(priority = 1,description = "Get Playlist")
    public void shouldBeAbleToGetPlaylist()
    {

        Response response = PlaylistAPI.get(playlistID);

        assertThat(response.statusCode(),equalTo(200));
        Playlist responsePlaylist =response.as(Playlist.class);
        assertThat(playlistID,equalTo(responsePlaylist.getId()));

    }

    @Link(value = "https://www.atlassian.com/",name = "JIRA LINK")
    @Description("Update Playlist with Valid data")
    @Test(priority = 2,description = "Update Playlist")
    public void shouldBeAbleToUpdatePlaylist()
    {

        Playlist requestPlaylist = playlistBuilder("Updated Playlist POJO","Updated Playlist POJO description",false);

        Response response =PlaylistAPI.update(playlistID,requestPlaylist);
        assertThat(response.statusCode(),equalTo(200));

    }


    @Step
    public Playlist playlistBuilder(String name, String description, Boolean _public)
    {
        return Playlist.builder().name(name).description(description)._public(_public).build();
    }

    @Step
    public void assertPlayList(Playlist requestPlaylist, Playlist responsePlaylist)
    {
        assertThat(requestPlaylist.getName(),equalTo(responsePlaylist.getName()));
        assertThat(requestPlaylist.getDescription(),equalTo(responsePlaylist.getDescription()));
        assertThat(requestPlaylist.get_public(),equalTo(responsePlaylist.get_public()));
    }

    @Step
    public void assertError(ErrorRoot errorRoot, int expectedStatuscode, String expectedMessage)
    {
        assertThat(errorRoot.getError().getMessage(),equalTo(expectedMessage));
        assertThat(errorRoot.getError().getStatus(),equalTo(expectedStatuscode));
    }

}
