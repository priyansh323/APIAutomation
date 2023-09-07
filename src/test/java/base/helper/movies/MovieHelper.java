package base.helper.movies;


import com.fasterxml.jackson.core.type.TypeReference;
import base.enpoints.MovieEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import base.pojo.MoviePojo;
import base.utils.DataManager;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.lang.reflect.Type;
import java.util.List;


public class MovieHelper {

    private static final String base_URI = DataManager.getInstance().getProp("BASE_URI");
    private static final String port = DataManager.getInstance().getProp("PORT");

    public MovieHelper(){
        RestAssured.baseURI = base_URI;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<MoviePojo> getAllMovies(){
        Response res = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .get(MovieEndpoints.GET_ALL_MOVIES)
                .andReturn();
        Assert.assertEquals(res.getStatusCode(), HttpStatus.SC_OK,"Status Code is 200");
        Type type = new TypeReference<List<MoviePojo>>() {}.getType();

        List<MoviePojo> allMovies = res.as(type);
        return allMovies;
    }
}
