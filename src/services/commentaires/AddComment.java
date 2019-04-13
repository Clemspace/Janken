package services.commentaires;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import db.Database;
import tools.UserTools;
import services.ErrorJSON;

public class AddComment {
		
	public static JSONObject addComment(String key, String text) {
		try {
			if(key == null || text == null) 
				return ErrorJSON.serviceRefused("Argument Missing", -1);
			
			GregorianCalendar calendar = new java.util.GregorianCalendar();
			Date dod = calendar.getTime();
			Document comment = new Document();
			MongoCollection<Document> coll = Database.getMongoCollection("messages");
			BasicDBObject author = new BasicDBObject();
			
			int id_user = UserTools.getActiveConnId(key);
			System.out.println("test");

			String login = UserTools.getLogin(id_user);
			
			comment.put("id",id_user);
			comment.put("auteur", login);
			comment.put("texte", text);
			comment.put("date", dod);
			coll.insertOne(comment);
			
			BasicDBObject query_msg = new BasicDBObject();
			query_msg.put("author", author);
			query_msg.put("comment", comment);
			
			return new JSONObject().put("Message envoyé",1);
			
		} catch (UnknownHostException u) {
			return ErrorJSON.serviceRefused("UnknownHostException", 0);
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("JSONException", 100);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQLException1", 1000);
		}
	}

}
