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
import ro.ubb.cinema.service.ClientService;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-clients-data.xml")
public class ClientServiceTests {
    @Autowired
    ClientService clientService;

    @Test
    public void testGetAllClients_CorrectData_ShouldReturnCorrectValue() {
        try {
            assertEquals(clientService.getAllClients().size(), 3, "Invalid clients database!");
        } catch (Exception e) {
            fail("Function threw exception! " + e.getMessage());
        }
    }

    @Test
    public void testFilterClientsByLastName_CorrectData_ShouldReturnCorrectValue() {
        try {
            assertEquals(clientService.filterClientsByLastName("ada").get(0).getClientFirstName(), "ada",
                    "Cinema not found");
        } catch (Exception e) {
            fail("Function threw exception! " + e.getMessage());
        }
    }
}