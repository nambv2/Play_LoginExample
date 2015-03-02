/**
 * 
 */
package models;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;




import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


/**
 * @author NamBV2
 *
 */
public class LoginModels {

  // check login
  public static Boolean CheckLogin(String account, String passWord) {
    BasicDBObject query = new BasicDBObject();
    DBObject info = null;
    query.put("account", account);
    try {
      DBCursor dbCursor = ConfigDB.Database().find(query);
      while (dbCursor.hasNext()) {
        info = dbCursor.next();
      }
      System.out.println("Account: " + info.get("account") + "and pass is:"
          + info.get("password"));
      if (account.equals(info.get("account"))
          && (passWord.equals(info.get("password")))) {
        return true;
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return false;
  }

  // Get item subject from database
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

  // Sign up a new account
  public static void Signup(String account, String address, String birthday,
      String sex, String subject, String passWord) {
    BasicDBObject basicDBObject = new BasicDBObject();
    basicDBObject.put("account", account);
    basicDBObject.put("password", passWord);
    basicDBObject.put("address", address);
    basicDBObject.put("birthday", birthday);
    basicDBObject.put("sex", sex);
    basicDBObject.put("subject", subject);
    try {
      ConfigDB.Database().insert(basicDBObject);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  // Get information of employee
  public static Map<String, String> GetInfo(String account) {
    Map<String, String> info = new HashMap<String, String>();
    String sexOption = "";
    BasicDBObject query = new BasicDBObject();
    query.put("account", account);
    DBObject dbObject = null;
    try {
      DBCursor cursor = ConfigDB.Database().find(query);
      while (cursor.hasNext()) {
        dbObject = cursor.next();
      }
      System.out.println("Address is: " + dbObject.get("address").toString());
      info.put("address", dbObject.get("address").toString());
      info.put("birthday", dbObject.get("birthday").toString());
      if ("on".equals(dbObject.get("sex").toString())) {
        sexOption = "Male";
      } else {
        sexOption = "Female";
      }
      info.put("sex", sexOption);
      info.put("subject", dbObject.get("subject").toString());
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return info;
  }

  // Get current the information before edit
  public static Map<String, String> edit(String account) {

    Map<String, String> info = new HashMap<>();
    BasicDBObject query = new BasicDBObject();
    query.put("account", account);
    DBObject object = null;
    try {
      DBCursor cursor = ConfigDB.Database().find(query);
      while (cursor.hasNext()) {
        object = cursor.next();
      }
      info.put("address", object.get("address").toString());
      info.put("birthday", object.get("birthday").toString());
      info.put("sex", object.get("sex").toString());
      info.put("subject", object.get("subject").toString());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return info;
  }

  // Edit information
  public static void saveNewInfor(String account, String address,
      String birthday, String sex, String subject) {
    System.out.println("I'm here2");
    System.out.println("new address: " + address);
    BasicDBObject query = new BasicDBObject().append("account", account);
    BasicDBObject object = new BasicDBObject();
    object.append("$set", new BasicDBObject().append("address", address).append("birthday", birthday).append("sex", sex)
        .append("subject", subject));
    try {
      ConfigDB.Database().update(query, object);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  
//get all information from database
  public static Map<Integer,Map<String,String>> GetAllInfo(String account) {
    String sexOption = "";
    int key = 0;
    Map<Integer,Map<String,String>> info = new HashMap<Integer, Map<String,String>>();
    Map<String, String> acc;
    List<String> list;
    try {
      DBCursor cursor = ConfigDB.Database().find();
      DBObject object = null;
      while(cursor.hasNext()){
        object = cursor.next();
        acc = new HashMap<String, String>();
        System.out.println("value: "+object.toString());
        acc.put("account", object.get("account").toString());
        acc.put("address", object.get("address").toString());
        acc.put("birthday", object.get("birthday").toString());
        if ("on".equals(object.get("sex").toString())) {
          sexOption = "Male";
        } else {
          sexOption = "Female";
        }
        acc.put("sex", sexOption);
        acc.put("subject", object.get("subject").toString());
        
        info.put(key, acc);
        key ++;        
      }
      System.out.println("Size is: "+info.size());
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return info;
  }
}
