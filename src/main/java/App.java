import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylists/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/new-stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/clients/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/new-client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylist/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int temp = Integer.parseInt(request.params(":id"));
    model.put("stylist",Stylist.find(temp));
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylists/new-stylist", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String stylistName = request.queryParams("name");
    Stylist newStylist = new Stylist(stylistName);
    newStylist.save();
    String url = String.format("/");
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylists/new-stylist", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String stylistName = request.queryParams("name");
    Stylist newStylist = new Stylist(stylistName);
    newStylist.save();
    String url = String.format("/");
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());







  }
}
