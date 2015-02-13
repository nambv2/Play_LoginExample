/**
 * 
 */
package models;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

/**
 * @author NamBV2
 *
 */
public class LoginModels {
    
  public static String CheckLogin(String userName,String passWord){
    BasicDBObject query = new BasicDBObject();
    String account = "";
    query.put("userName", userName);
    try {
      DBCursor dbCursor = ConfigDB.Database().find(query);
      while(dbCursor.hasNext()){
        account = dbCursor.next().get("userName").toString();
      }
      if(userName.equals(account)){
        return "true";
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return "false";
  }
}
