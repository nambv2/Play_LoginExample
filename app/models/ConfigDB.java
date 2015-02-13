package models;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * @author NamBV2
 *
 */
public class ConfigDB {
	private static final String dbName = "LoginDB";
	private static final String collectionName = "LoginDB";
	public static DBCollection Database() throws UnknownHostException{
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB(dbName);
		DBCollection collection = db.getCollection(collectionName);
		return collection;
	}
}
