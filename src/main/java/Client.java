import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private int stylist_id;

  public Client(String name, int _id) {
    this.name = name;
    this.stylist_id = _id;
  }

  public void setName(String _name) {
    this.name = _name;
  }

  public void save() {
    String sql = "INSERT INTO clients (name, stylist_id) VALUES (:name, :id);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  @Override
   public boolean equals(Object otherClient) {
     if(!(otherClient instanceof Client)) {
       return false;
     } else {
       Client newClient = (Client) otherClient;
       return this.getName().equals(newClient.getName()) && this.getId() == newClient.getId();
     }
   }


  public static List<Client> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients;";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static List<Client> findClients(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id=:id;";
      return con.createQuery(sql)
      .addParameter("id", _id)
      .executeAndFetch(Client.class);
    }
  }

  public static Client find(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id;";
      return con.createQuery(sql)
      .addParameter("id", _id)
      .executeAndFetchFirst(Client.class);
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStylist_id() {
    return stylist_id;
  }

  public static void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients *;";
      con.createQuery(sql)
      .executeUpdate();
    }
  }

  public static void deleteSingle(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("id", _id)
      .executeUpdate();
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET (name) = (:name) WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("name", this.name)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }
}
