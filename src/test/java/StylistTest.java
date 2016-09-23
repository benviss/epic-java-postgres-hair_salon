import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;
import org.sql2o.*;

public class StylistTest {

  @Before
  public void initialize() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String clearStylistList = "DELETE FROM stylists *;";
      con.createQuery(clearStylistList)
      .executeUpdate();
    }
  }

  @Test
  public void Stylist_instantiatesCorrectly_true() {
   Stylist testStylist = new Stylist("Jon Doe");
   assertTrue(testStylist instanceof Stylist);
  }

  @Test
  public void Stylist_StoresStyists_true() {
   Stylist testStylist = new Stylist("Jon Doe");
   testStylist.save();
   assertTrue((Stylist.all().size() > 0));
  }
}
