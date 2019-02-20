package service.authentification;

import org.json.JSONException;
import org.json.JSONObject;
import java.sql.SQLException;

public class Login {
	
	public static JSONObject login(String login, String password) {
		try {
			if(login == null || password == null) return service.ErrorJSON.serviceRefused("Wrong Argument",-1);
			
			boolean is_user = tool.UserTools.userExists(login);
			if(!is_user) return service.ErrorJSON.serviceRefused("Unknown User"+login,1);
			
			boolean password_ok = tool.UserTools.checkPassword(login,password);
			if(!password_ok) return service.ErrorJSON.serviceRefused("Incorrect Password",2);
			
			int id_user = tool.UserTools.getIdUser(login);
			
			JSONObject retour = new JSONObject();
			
			int conn_id = tool.UserTools.insereConnexion(id_user,false);
			retour.put("conn_id",conn_id);
			
			return retour;
		} catch(JSONException e) {
			return service.ErrorJSON.serviceRefused( e.getMessage(),100);
		} catch(SQLException e) {
			return service.ErrorJSON.serviceRefused( e.getMessage(),1000);
		}
	}
}
