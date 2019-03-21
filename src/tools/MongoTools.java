package tools;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import db.Database;

public class MongoTools {
	public static List<Document> findComment(String query, String friend) throws UnknownHostException {
		MongoCollection<Document> collection = Database.getMongoCollection("messages");
		
		List<Document> results = new ArrayList<>();
		Document queryDocument = new Document();
		queryDocument.put("auteur", friend);
		queryDocument.put("texte", query);
		MongoCursor<Document> cursor = collection.find(queryDocument).iterator();
		while(cursor.hasNext()) {
			results.add(cursor.next());
		}
		return results;
	}
}
