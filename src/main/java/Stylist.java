import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;

  public Stylist(String name) {
    this.name = name;
  }

  public void setName(String _name) {
    this.name = _name;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists;";
      return con.createQuery(sql)
      .executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int _stylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id=:id;";
      return con.createQuery(sql)
      .addParameter("id", _stylistId)
      .executeAndFetchFirst(Stylist.class);
    }
  }

  @Override
   public boolean equals(Object otherStylist) {
     if(!(otherStylist instanceof Stylist)) {
       return false;
     } else {
       Stylist newStylist = (Stylist) otherStylist;
       return this.getName().equals(newStylist.getName()) && this.getId() == newStylist.getId();
     }
   }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists *;";
      con.createQuery(sql)
      .executeUpdate();
    }
  }

  public static void deleteSingle(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("id", _id)
      .executeUpdate();
    }
  }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET (name) = (:name) WHERE id=:id;";
      con.createQuery(sql)
      .addParameter("name", this.name)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }
}
