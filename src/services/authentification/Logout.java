package services.authentification;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.UserTools;

public class Logout{

	public static JSONObject logout(String key, boolean status){
		
		if (key == null)
			return services.ErrorJSON.serviceRefused("parametre manquant", -1);
		try {
			if(UserTools.keyVerified(key)){
				JSONObject retour = new JSONObject();
				int r = tools.UserTools.logout(key,false);

				retour.put("Déconnecté", r);
				return retour;
			}else{
				return services.ErrorJSON.serviceRefused("Clé non vérifiée", -1);
			}
		} catch (SQLException e) {
			return services.ErrorJSON.serviceRefused("Logout Erreur SQL", 1000);
		} catch (JSONException e) {
			return services.ErrorJSON.serviceRefused("Logout Erreur JSON", 100);
		}
	}
}
