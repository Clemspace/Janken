package db;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.bson.Document;

public class Database {
	private DataSource dataSource;
	
	public Database(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env"+jndiname);
		} catch (NamingException e) {
			throw new SQLException(jndiname + "is missing in JNDI! :"+e.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public static Connection getMySQLConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost/janken";
		return (DriverManager.getConnection(url,"root","root"));
	}
	
	public static MongoCollection<Document> getMongoCollection(String nom_collection) throws UnknownHostException{
		com.mongodb.client.MongoClient m = MongoClients.create(); 
				//new Mongo(DBStatic.mongo_host);
		
		MongoDatabase db = m.getDatabase(DBStatic.mongo_db);
		return db.getCollection(nom_collection);
	}
}
