package services.authentification;

import java.sql.SQLException;

import tools.UserTools;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;

public class CreateUser {
	
	public static JSONObject createUser(String login, String password, String nom, String prenom) {
		try {
			if(login == null || password == null || nom == null || prenom == null) {
				return ErrorJSON.serviceRefused("Argument Missing",-1);
			}
			boolean is_user = UserTools.userExists(login);
			if(is_user) return services.ErrorJSON.serviceRefused("Username Already Taken "+login,-1);
			JSONObject retour = new JSONObject();
			
			int i = UserTools.insereNouvelUser(login,password,nom,prenom);
			if(i == 0) retour.put("KO",i);
			else retour.put("OK",i);
			return retour;
			
		} catch(JSONException e) {
			return services.ErrorJSON.serviceRefused( e.getMessage(),100);
		} catch(SQLException e) {
			return services.ErrorJSON.serviceRefused( e.getMessage(),1000);
		}
	}

}
