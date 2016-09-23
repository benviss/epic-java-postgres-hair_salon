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

  get("/client/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("client",Client.find(Integer.parseInt(request.params(":id"))));
    model.put("template", "templates/client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylist/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int temp = Integer.parseInt(request.params(":id"));
    model.put("clients",Client.findClients(temp));
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

  post("/clients/new-client", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String clientName = request.queryParams("name");
    int stylistId = Integer.parseInt(request.queryParams("stylist"));
    Client newClient = new Client(clientName, stylistId);
    newClient.save();
    String url = String.format("/stylist/%d",stylistId);
    response.redirect(url);

    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylist/:id/clients/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String clientName = request.queryParams("name");
    int stylistId = Integer.parseInt(request.params(":id"));
    Client newClient = new Client(clientName, stylistId);
    newClient.save();
    String url = String.format("/stylist/%d",stylistId);
    response.redirect(url);

    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("stylist/:id/clients/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int stylist = Integer.parseInt(request.params(":id"));
    model.put("stylist", Stylist.find(stylist));
    model.put("template", "templates/new-stylist-client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/clients/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/new-client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/deleteStylists", (request,response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Stylist.delete();
    String url = String.format("/");
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylist/:id/update-stylist-info", (request,response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int stylistId = Integer.parseInt(request.params(":id"));
    model.put("stylist", Stylist.find(stylistId));
    model.put("template", "templates/update-stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/client/:id/update-client-info", (request,response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int clientId = Integer.parseInt(request.params(":id"));
    model.put("client", Client.find(clientId));
    model.put("template", "templates/update-client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylist/:id/update", (request,response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int stylistId = Integer.parseInt(request.params(":id"));
    String newName = request.queryParams("name");
    Stylist temp = Stylist.find(stylistId);
    temp.setName(newName);
    temp.update();
    String url = String.format("/stylist/%d", stylistId);
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/client/:id/update", (request,response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int clientId = Integer.parseInt(request.params(":id"));
    String newName = request.queryParams("name");
    Client temp = Client.find(clientId);
    temp.setName(newName);
    temp.update();
    String url = String.format("/client/%d", clientId);
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylist/:id/delete", (request,response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int stylistId = Integer.parseInt(request.params(":id"));
    Stylist.deleteSingle(stylistId);
    String url = String.format("/");
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/client/:id/delete", (request,response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    int clientId = Integer.parseInt(request.params(":id"));
    Client.deleteSingle(clientId);
    String url = String.format("/");
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/deleteClients", (request,response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Client.delete();
    String url = String.format("/");
    response.redirect(url);
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());





  }
}
