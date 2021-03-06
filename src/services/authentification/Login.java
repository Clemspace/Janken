package services.authentification;

import org.json.JSONException;
import org.json.JSONObject;

import tools.UserTools;

import java.sql.SQLException;

public class Login {
	
	public static JSONObject login(String login, String password) {
		try {
			if(login.isEmpty() || password.isEmpty()) 
				return services.ErrorJSON.serviceRefused("Argument Missing",-1);
			
			boolean is_user = tools.UserTools.userExists(login);
			if(!is_user) return services.ErrorJSON.serviceRefused("Unknown User : "+login,1);
			
			boolean password_ok = tools.UserTools.checkPassword(login,password);
			if(!password_ok) return services.ErrorJSON.serviceRefused("Incorrect Password",2);
			
			int id_user = tools.UserTools.getIdUser(login);
			
			JSONObject retour = new JSONObject();
			
			String conn_key = tools.UserTools.insereConnexion(id_user,false);
			
			retour.put("id", id_user);
			retour.put("login", login);
			retour.put("conn_key",conn_key);
			retour.put("nomprenom",UserTools.getNomPrenom(login));
			
			return retour;
			
		} catch(JSONException e) {
			return services.ErrorJSON.serviceRefused( e.getMessage(),100);
		} catch(SQLException e) {
			return services.ErrorJSON.serviceRefused( e.getMessage(),1000);
		}
	}
}
