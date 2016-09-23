import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Specialty {
  private int id;
  private String name;

  public Stylist(String name) {
    this.name = name;
  }

  public void save() {
    String sql = "INSERT INTO stylists (name) VALUES (:name);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists;";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public int getId() {
    return id;
  }

  public int getName() {
    return id;
  }
}
