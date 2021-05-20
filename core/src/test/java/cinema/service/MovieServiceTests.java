package cinema.service;

import cinema.ITConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import ro.ubb.cinema.service.MovieService;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-movies-data.xml")
public class MovieServiceTests {
    @Autowired
    MovieService movieService;

    @Test
    public void testGetAllMovies_CorrectData_ShouldReturnCorrectValue() {
        try {
            assertEquals(movieService.getAllMovies().size(), 1, "Invalid movies database!");
        } catch (Exception e) {
            fail("Function threw exception! " + e.getMessage());
        }
    }

    @Test
    public void testFilterMoviesByName_CorrectData_ShouldReturnCorrectValue() {
        try {
            assertEquals(movieService.filterMoviesByName("Avengers").get(0).getGenre(), "SF",
                    "Movie not found");
        } catch (Exception e) {
            fail("Function threw exception! " + e.getMessage());
        }
    }
}