package tests.api.movies;

import base.helper.movies.MovieHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteMovie {

    private MovieHelper movie;

    @BeforeTest
    public void startWith(){
        movie = new MovieHelper();
    }

    @Test
    public void testDeleteMovie(){
        Response deletedMovie = movie.deleteMovie();
        System.out.println(deletedMovie);
    }
}
