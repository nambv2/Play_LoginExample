package controllers;

import java.util.Map;

import models.LoginModels;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    //View login page
    public static Result index() {
        return ok(index.render());
    }
    
    //view information of employee page
    public static Result infor() {
      String account = session("account");
      System.out.println("Account is: "+account+"--"+LoginModels.GetInfo(account).toString());
      return ok(login.render(account,LoginModels.GetInfo(account)));
    }
    
    //logout account
    public static Result logout(){
      session().remove("account");
      return redirect(routes.Application.index());
    }
    
    public static Result admin(){
      String account = session("account");
      return ok(admin.render(account,LoginModels.GetAllInfo(account)));
    }
    
    public static Result edit(){
      String account = session("account");
      return ok(edit.render(account,LoginModels.edit(account),LoginModels.getSubject()));
    }
    
    public static Result login() {
      DynamicForm form = Form.form().bindFromRequest();
      String account = form.get("account");
      String passWord = form.get("passWord");
      Boolean checkFlag = LoginModels.CheckLogin(account, passWord);
      System.out.println("checkFlag: "+checkFlag);
      if("admin".equals(account)){
        if(checkFlag == true){
          session("account", account);
          return redirect(routes.Application.admin());
        } else {
          return redirect(routes.Application.index());
        }
      }
      else if(checkFlag == true){
        session("account", account);
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
      String passWord = dynamicForm.get("passWord");
      String account = dynamicForm.get("account");
      String address = dynamicForm.get("address");
      String sex = dynamicForm.get("sex");
      String birthday = dynamicForm.get("birthday");
      String subject = dynamicForm.get("subject");
      System.out.println("value is: "+account+" "+address+" "+sex+" "+birthday+" "+subject);
      LoginModels.Signup(account, address, birthday, sex, subject,passWord);
      return redirect(routes.Application.index());
    }
    
    public static Result editdone(){
      System.out.println("I'm here");
      String account = session("account");
      DynamicForm form = Form.form().bindFromRequest();
      String address = form.get("address");
      String birthday = form.get("birthday");
      String sex = form.get("sex");
      String subject = form.get("subject");
      System.out.println("new address: "+address);
      LoginModels.saveNewInfor(account, address, birthday, sex, subject);
      return redirect(routes.Application.infor());
    }
}
