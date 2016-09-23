import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Specialty {
  private int id;
  private String name;

  public Specialty(String name) {
    this.name = name;
  }
  public void save() {
    String sql = "INSERT INTO specialties (name) VALUES (:name);";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true).addParameter("name", this.name).executeUpdate().getKey();
    }
  }

  public static List<Specialty> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM specialties;";
      return con.createQuery(sql).executeAndFetch(Specialty.class);
    }
  }

  public int getId() {
    return id;
  }
}
