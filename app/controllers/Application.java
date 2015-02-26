package controllers;

import java.util.Map;

import models.LoginModels;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    public static Result infor() {
      return ok(login.render());
    }
    public static Result login() {
      DynamicForm form = Form.form().bindFromRequest();
      String userName = form.get("userName");
      String passWord = form.get("passWord");
      System.out.println("infor is: "+userName+" "+passWord);
      Boolean checkFlag = LoginModels.CheckLogin(userName, passWord);
      System.out.println("checkFlag: "+checkFlag);
      if(checkFlag == true){
        return redirect(routes.Application.infor());
      } else {
        return redirect(routes.Application.index());
      }
    }
    
    public static Result loadSubject(){
      return ok(views.html.signup.render(Integer.toString(LoginModels.getSubject().size()),LoginModels.getSubject()));
    }
    
    public static Result signupDone(){
      DynamicForm dynamicForm = Form.form().bindFromRequest();
      String account = dynamicForm.get("account");
      String address = dynamicForm.get("address");
      String sexOption = dynamicForm.get("sexOption");
      String birthDay = dynamicForm.get("birthDay");
      String subject = dynamicForm.get("subject");
      System.out.println("value is: "+account+" "+address+" "+sexOption+" "+birthDay+" "+subject);
      LoginModels.Signup(account, address, birthDay, sexOption, subject);
      return redirect(routes.Application.index());
    }
}
