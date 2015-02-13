package controllers;

import java.util.Map;

import models.LoginModels;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    public static Result login() {
      Map<String, String[]> params = request().body().asFormUrlEncoded();
      String userName = params.get("userName")[0];
      String passWord = params.get("passWord")[0];
      System.out.println("user name is: "+userName);
      return ok(login.render(LoginModels.CheckLogin(userName, passWord)));
    }
    public static Result signup(){
      return ok(signup.render());
    }
}
