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

  @Test
  public void Client_StoresStyists_true() {
   Client testClient = new Client("Jon Doe", 1);
   testClient.save();
   assertTrue((Client.all().size() > 0));
  }

  @Test
  public void getId_ClientSaveSetsId_true() {
    Client testClient = new Client("Jon Doe", 1);
    testClient.save();
    assertTrue(testClient.getId() > 0);
  }

  @Test
  public void getName_FindsCorrectName_true() {
    Client testClient = new Client("Jon Doe", 1);
    assertTrue(testClient.getName().equals("Jon Doe"));
  }

  @Test
  public void setName_StoresNameProperly_true() {
    Client testClient = new Client("Jon Doe", 1);
    testClient.setName("James");
    assertEquals("James",testClient.getName());
  }

  @Test
  public void Client_FindsClient_true() {
   Client testClient = new Client("Jon Doe", 1);
   testClient.save();
   System.out.println(testClient.getId());
   assertTrue(testClient.equals(Client.find(testClient.getId())));
  }

  @Test
  public void all_FindsAllClientsAndIsCorrectInfo_true() {
    Client testClient = new Client("Jon Doe", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void delete_remvoesAllClients() {
    Client testClient = new Client("Jon Doe", 1);
    testClient.save();
    Client.delete();
    assertTrue(Client.all().size() == 0);
  }

  @Test
  public void deleteSingle_DeletesSingleClientCorrectly_true() {
    Client testClient = new Client("Jon Doe", 1);
    testClient.save();
    Client.deleteSingle(testClient.getId());
    assertTrue(Client.all().size() == 0);
  }

  @Test
  public void update_updatesClientInfoCorrectly_true() {
    Client testClient = new Client("Jon Doe", 1);
    testClient.save();
    testClient.setName("James");
    testClient.update();
    assertTrue(Client.all().get(0).equals(testClient));
  }


}
