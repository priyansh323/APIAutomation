package tests.api.movies;

import base.helper.movies.MovieHelper;
import base.pojo.MoviePojo;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllMovies {

    private MovieHelper movie;

    @BeforeTest
    public void startWith(){
        movie = new MovieHelper();
    }

    @Test
    public void testGetAllMovies(){
        List<MoviePojo> allMovies = movie.getAllMovies();
        Assert.assertNotNull(allMovies,"Movies List is not Empty");

    }
}
