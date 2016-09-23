import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;
import org.sql2o.*;

public class ClientTest {

  @Before
  public void initialize() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String clearClientList = "DELETE FROM clients *;";
      con.createQuery(clearClientList).executeUpdate();
    }
  }

  @Test
  public void Client_instantiatesCorrectly_true() {
   Client testClient = new Client("Jon Doe", 1);
   assertTrue(testClient instanceof Client);
  }
  

}
