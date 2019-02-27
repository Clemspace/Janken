package commentaires;

import java.util.Date;
import java.util.GregorianCalendar;

import com.mongodb.*;


import db.DBStatic;
import db.Database;
import db.UserTools;

public class AddComment {
		
	public static BasicDBObject addComment(String key, int id_message, String text) {
		
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		Date dod = calendar.getTime();
		BasicDBObject comment = new BasicDBObject();
		try {
			DBCollection coll = Database.getMyMongoDBConnection();
			//BasicDBObject comment = new BasicDBObject();
			BasicDBObject author = new BasicDBObject();
			int id_user = UserTools.getIdUser(key);
			String login = UserTools.getLogin(id_user);
			author.put("id", id_user);
			author.put("login", login);
			
			comment.put("auteur", author);
			comment.put("texte", text);
			comment.put("date", dod);
			
			BasicDBObject query_msg = new BasicDBObject();
			query_msg.put("id", id_message);
			DBCursor curs = coll.find(query_msg);
			
			DBObject o = null;
			if (curs.hasNext()) {
				o = curs.next();
			}
			
			ArrayList<DBObject> list_comments = (ArrayList<DBObject>) o.get("comments");
			int id = list_comments.size();
			comment.put("id", id);
			
			list_comments.add(comment);
			BasicDBObject replace = new BasicDBObject();
			replace.append("$set" , new BasicDBObject().append("comments", list_comments));
			coll.update(query_msg, replace);
			
		} catch (UnknownHostException u) {
			u.printStackTrace();
			return ServiceTools.serviceRefused("UnknownHostException", 0);
		} 
		
		return comment;
	}

}
