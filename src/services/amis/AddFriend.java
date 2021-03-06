package services.amis;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import services.ErrorJSON;
import tools.FriendTools;
import tools.UserTools;

public class AddFriend {
	
public static JSONObject addFriend(String key, String friend_login){
		
		try{
			if(key == null || friend_login == null) 
				return ErrorJSON.serviceRefused("Argument missing", -1);
			
			if(!UserTools.keyVerified(key))
				return ErrorJSON.serviceRefused("Non connecté!!!", -1);
			
			int idA=UserTools.getIdFromKey(key);
			
			int friend_id=UserTools.getIdUser(friend_login);

			if(key==null||friend_login==null)
				return ErrorJSON.serviceRefused("Paramètres manquants", -1);
			
			if(idA == friend_id)
				return ErrorJSON.serviceRefused("Amis similaires", 5);
		
			if(UserTools.userExists(idA)){
				if(UserTools.userExists(friend_login)){
					if(FriendTools.isFriend(idA, friend_id)){
						return ErrorJSON.serviceAccepted("Vous avez déjà ajouté "+friend_login+" en ami.");
					}else{
						FriendTools.addFriend(idA, friend_id);
						return ErrorJSON.serviceAccepted("Vous avez ajouté "+friend_login+" en ami.");
					}
				}else{
					System.out.println("Ami imaginaire");
					return ErrorJSON.serviceRefused("Ami imaginaire",6);
					
				}
			}else{
				System.out.println("Vous n'existez pas");
				return ErrorJSON.serviceRefused("Vous n'existez pas", 7);
			}
		
		}catch (SQLException e){
			return ErrorJSON.serviceRefused("Erreur SQL", 1000);
		}catch (JSONException e){
			return ErrorJSON.serviceRefused("Erreur JSON", 100);
		}
	}

}
