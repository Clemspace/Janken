package commentaires;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
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
			GregorianCalendar calendar = new java.util.GregorianCalendar();
			Date dod = calendar.getTime();
			Document comment = new Document();
			MongoCollection<Document> coll = Database.getMongoCollection("messages");
			BasicDBObject author = new BasicDBObject();
			int id_user;
			id_user = UserTools.getIdUser(key);

			String login = UserTools.getLogin(id_user);
			author.put("id", id_user);
			author.put("login", login);
			
			comment.put("auteur", author);
			comment.put("texte", text);
			comment.put("date", dod);
			coll.insertOne(comment);
			
			BasicDBObject query_msg = new BasicDBObject();
			query_msg.put("author", author);
			query_msg.put("comment", comment);
			
			return new JSONObject().put("OK",1);
			
		} catch (UnknownHostException u) {
			return ErrorJSON.serviceRefused("UnknownHostException", 0);
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("JSONException", 100);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused("JSONException", 1000);
		}
	}

}
