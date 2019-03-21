package services.recherche;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.MongoTools;
import tools.UserTools;

public class Search {
	public static JSONObject search(String key, String query, String friend) {
		try {
			if(!UserTools.userConnected(key))
				return ErrorJSON.serviceRefused("Non connecté", 1000);
			List<Document> results = MongoTools.findComment(query, friend);
			
			return ErrorJSON.serviceAccepted("Résultats de la recherche : "+results);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused("Erreur SQL", 1000);
		} catch (UnknownHostException e) {
			return ErrorJSON.serviceRefused("UnknownHostException", 1000);
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("Erreur JSON", 100);
		}	
	}
}
