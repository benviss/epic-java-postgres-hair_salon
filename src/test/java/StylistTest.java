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

  @Test
  public void getId_StylistSaveSetsId_true() {
    Stylist testStylist = new Stylist("Jon Doe");
    testStylist.save();
    assertTrue(testStylist.getId() > 0);
  }

  @Test
  public void getName_FindsCorrectName_true() {
    Stylist testStylist = new Stylist("Jon Doe");
    assertTrue(testStylist.getName().equals("Jon Doe"));
  }

  @Test
  public void setName_StoresNameProperly_true() {
    Stylist testStylist = new Stylist("Jon Doe");
    testStylist.setName("James");
    assertEquals("James",testStylist.getName());
  }

  @Test
  public void Stylist_FindsStylist_true() {
   Stylist testStylist = new Stylist("Jon Doe");
   testStylist.save();
   assertTrue(testStylist.equals(Stylist.find(testStylist.getId())));
  }

  @Test
  public void all_FindsAllStylistsAndIsCorrectInfo_true() {
    Stylist testStylist = new Stylist("Jon Doe");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void delete_remvoesAllStylists() {
    Stylist testStylist = new Stylist("Jon Doe");
    testStylist.save();
    Stylist.delete();
    assertTrue(Stylist.all().size() == 0);
  }

  @Test
  public void deleteSingle_DeletesSingleStylistCorrectly_true() {
    Stylist testStylist = new Stylist("Jon Doe");
    testStylist.save();
    Stylist.deleteSingle(testStylist.getId());
    assertTrue(Stylist.all().size() == 0);

  }

  @Test
  public void update_updatesStylistInfoCorrectly_true() {
    Stylist testStylist = new Stylist("Jon Doe");
    testStylist.save();
    testStylist.setName("James");
    testStylist.update();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }
}
