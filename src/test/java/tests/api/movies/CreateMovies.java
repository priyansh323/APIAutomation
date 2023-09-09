package tests.api.movies;

import base.helper.movies.MovieHelper;
import base.pojo.MoviePojo;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CreateMovies {

    private MovieHelper movie;

    @BeforeTest
    public void startWith(){
        movie = new MovieHelper();
    }

    @Test
    public void testCreateMovie() throws IOException {
        Response createdMovie = movie.createMovie();
        String name = createdMovie.jsonPath().getString("name");
        Assert.assertNotNull(name,"Error : name is Null ");
    }
}
