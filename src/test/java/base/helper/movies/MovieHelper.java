package base.helper.movies;


import com.fasterxml.jackson.core.type.TypeReference;
import base.enpoints.MovieEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import base.pojo.MoviePojo;
import base.utils.DataManager;
import org.apache.http.HttpStatus;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;


public class MovieHelper {

    private static final String base_URI = DataManager.getInstance().getProp("BASE_URI");
    private static final String port = DataManager.getInstance().getProp("PORT");

    public MovieHelper() {
        RestAssured.baseURI = base_URI;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.useRelaxedHTTPSValidation();
    }

    public List<MoviePojo> getAllMovies() {
        Response res = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .get(MovieEndpoints.GET_ALL_MOVIES)
                .andReturn();
        Assert.assertEquals(res.getStatusCode(), HttpStatus.SC_OK, "Status Code is not 200");
        Type type = new TypeReference<List<MoviePojo>>() {
        }.getType();

        List<MoviePojo> allMovies = res.as(type);
        return allMovies;
    }

    public Response createMovie() throws IOException {

        String name = null, description = null, language = null, releaseDate = null;
        List<String> splitCast = null;

        InputStream file = new FileInputStream("C:\\Users\\Acer1\\IdeaProjects\\ApiAutomation\\src\\test\\java\\base\\testData\\MoviesTestData.xlsx");
        XSSFWorkbook mwb = new XSSFWorkbook(file);
        XSSFSheet sheet1 = mwb.getSheet("Sheet1");


        for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
            name = sheet1.getRow(i).getCell(0).getStringCellValue();
            description = sheet1.getRow(i).getCell(1).getStringCellValue();
            language = sheet1.getRow(i).getCell(2).getStringCellValue();
            String excelCast = sheet1.getRow(i).getCell(3).getStringCellValue();
            splitCast = List.of(excelCast.split(","));
            releaseDate = sheet1.getRow(i).getCell(4).getRawValue();
        }

        MoviePojo newMovie = new MoviePojo();
        newMovie.setName(name);
        newMovie.setDescription(description);
        newMovie.setLanguage(language);
        newMovie.setCast(splitCast);
        newMovie.setReleaseDate(releaseDate);

        Response res = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(newMovie)
                .post(MovieEndpoints.CREATE_MOVIE)
                .andReturn();

        Assert.assertEquals(res.getStatusCode(), HttpStatus.SC_CREATED, "Status Code is not 201");

        return res;
    }

    public  Response updateMovie(){

        MoviePojo updatedMovie = new MoviePojo();
        updatedMovie.setName("Patthan1");
        updatedMovie.setLanguage("Hindi");

        Response res = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id","64fc25f7d536b40c97b8f6c0")
                .when()
                .body(updatedMovie)
                .put(MovieEndpoints.UPDATE_MOVIES)
                .andReturn();

        Assert.assertEquals(res.getStatusCode(), HttpStatus.SC_OK, "Status Code is not 200");

        return res;
    }

    public  Response deleteMovie(){
        Response res = RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id","64fc25f7d536b40c97b8f6c0")
                .when()
                .delete(MovieEndpoints.DELETE_MOVIES)
                .andReturn();

        Assert.assertEquals(res.getStatusCode(), HttpStatus.SC_OK, "Status Code is not 200");

        return res;
    }
}
