package spotifyOAuth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import spotifyOAuth2.utils.ConfigLoader;

import static io.restassured.RestAssured.given;

public class Specs {

    public static RequestSpecification getRequestSpec()
    {
        String base_uri="";
        try {
            String env = System.getProperty("ENV");
            base_uri=ConfigLoader.getInstance().getBaseURI(env);
            System.out.println("BASE URI:"+base_uri);
        }catch (Exception e)
        {
            base_uri = ConfigLoader.getInstance().getBaseURI("QA");
        }
        return given().baseUri(base_uri).basePath(Routes.BASE_PATH)
                .filter(new AllureRestAssured())         // adds the request and response details in Allure report
                .contentType(ContentType.JSON).log().all();

    }

    public static ResponseSpecification getReponseSpec()
    {
        return RestAssured.expect().contentType(ContentType.JSON).log().all();

    }
}
