package tests.api.movies;

import base.helper.movies.MovieHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateMovie {

    private MovieHelper movie;

    @BeforeTest
    public void startWith(){
        movie = new MovieHelper();
    }

    @Test
    public void testUpdateMovie(){
        Response updatedMovie = movie.updateMovie();
        int modifiedCount = updatedMovie.jsonPath().getInt("modifiedCount");
        Assert.assertNotEquals(modifiedCount,0,"Error : modifiedcount is 0 --> Put call Not done ");
    }
}
