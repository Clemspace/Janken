package services.recherche;


import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;

import tools.UserTools;

public class ListPeople {
	public static JSONObject listPeople(String key) {
		try {
			if(key == null) 
				return ErrorJSON.serviceRefused("Argument missing", -1);
			
			if(!UserTools.userConnected(key))
				return ErrorJSON.serviceRefused("Non connecté", 1000);
			
			JSONObject people = UserTools.getPeople();
			
			return people;
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused("Erreur SQL", 1000);
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("Erreur JSON", 100);
		}	
	}
}
