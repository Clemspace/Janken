package services.commentaires;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import db.Database;
import services.ErrorJSON;
import tools.FriendTools;
import tools.UserTools;

public class Timeline {
	
	public static JSONObject timeline(String key) {
		
		try {
			if(key == null) 
				return ErrorJSON.serviceRefused("Argument Missing", -1);
			GregorianCalendar calendar = new java.util.GregorianCalendar();
			Date dod = calendar.getTime();
			MongoCollection<Document> coll = Database.getMongoCollection("messages");
			JSONObject retour = new JSONObject();
			int id_user = UserTools.getActiveConnId(key);
			System.out.println("test");

			String login = UserTools.getLogin(id_user);
			
			List<Integer> followed_ids = FriendTools.listFriends(id_user);
			followed_ids.add(id_user);
			BasicDBObject query = new BasicDBObject();

			query.put("id", new BasicDBObject("$in", followed_ids));
			//FindIterable<Document> msg = coll.find(query).iterator();
			FindIterable<Document> f = coll.find(query);
			MongoCursor<Document> cursor = f.iterator();
			//cursor.sort(new BasicDBObject("date",-1));
			
			Document doc = new Document();
			while (cursor.hasNext()) {
				doc = cursor.next();
				JSONObject post = new JSONObject();
				post.put("message", doc.getString("texte"));
				post.put("auteur", doc.getString("auteur"));
				post.put("date", doc.getDate("date"));
				retour.append("post", post);
			}
			return retour;
	
		} catch (UnknownHostException u) {
			return ErrorJSON.serviceRefused("UnknownHostException", 0);
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("JSONException", 100);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQLException1", 1000);
		}
	}


}