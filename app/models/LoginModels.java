/**
 * 
 */
package models;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 * @author NamBV2
 *
 */
public class LoginModels {

  public static Boolean CheckLogin(String userName, String passWord) {
    BasicDBObject query = new BasicDBObject();
    String account = "";
    query.put("account", userName);
    try {
      DBCursor dbCursor = ConfigDB.Database().find(query);
      while (dbCursor.hasNext()) {
        account = dbCursor.next().get("account").toString();
        System.out.println("Account is: "+account);
        if (userName.equals(account)) {
          return true;
        }
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static List<String> getSubject() {
    List<String> subject = new ArrayList<String>();
    try {
      MongoClient mongo = new MongoClient("localhost", 27017);
      DB db = mongo.getDB("LoginDB");
      DBCollection table = db.getCollection("Subject");
      DBCursor cursor = table.find();
      while (cursor.hasNext()) {
        subject.add(cursor.next().get("name").toString());
      }
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return subject;

  }

  public static void Signup(String account, String address, String birthDay,
      String sexOption, String subject) {
    BasicDBObject basicDBObject = new BasicDBObject();
    basicDBObject.put("account", account);
    basicDBObject.put("address", address);
    basicDBObject.put("birthday", birthDay);
    basicDBObject.put("sex", sexOption);
    basicDBObject.put("subject", subject);
    try {
      ConfigDB.Database().insert(basicDBObject);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }
  
  public static List<String> GetInfo(String account){
    List<String> info = new ArrayList<String>();
    BasicDBObject query = new BasicDBObject();
    query.put("account", account);
    try {
      DBCursor cursor = ConfigDB.Database().find(query);
      while(cursor.hasNext()){
        System.out.println(cursor.next());
      }
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return info;
  }
}
