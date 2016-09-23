import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int stylistId;

  public Client(String name, int _id) {
    this.name = name;
    this.stylistId = _id;
  }
  public void save() {
    String sql = "INSERT INTO clients (name) VALUES (:name, :id);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("id", this.stylistId)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients;";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylistId;
  }
}
