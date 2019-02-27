package services.authentification;

import java.sql.SQLException;

import org.json.JSONObject;

public class Logout{

	public static JSONObject logout(int id_user, boolean status){
		
		if (id_user==null)
			return services.ErrorJSON.serviceRefused("parametre manquant", -3);
		try {
			
			if(UserTools.keyVerified(id_user)){
				JSONObject retour = new JSONObject();
				int conn_id = tools.UserTools.logout(id_user,false);
					
					
				}
				
				
				return retour;
			}else{
				return services.ErrorJSON.serviceRefused(" ", 3);
			}
		} catch (SQLException e) {
			return services.ErrorJSON.serviceRefused("Erreur SQL", -1);
		}
	}
}
