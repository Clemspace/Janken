package services.amis;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import services.ErrorJSON;
import tools.FriendTools;
import tools.UserTools;

public class ListFriend {
	
public static JSONObject listFriend(String login){
		try {
			if(login.isEmpty()) 
				return ErrorJSON.serviceRefused("Argument missing", -1);
			
			int id = tools.UserTools.getIdUser(login);
			
			List<Integer> idFriends = FriendTools.listFriends(id);
			
			JSONObject o = new JSONObject();
			for(Integer i : idFriends) {
				o.append("friend", tools.UserTools.getLogin(i));
			}
			
			return o;
			
		}catch (SQLException e){
			return ErrorJSON.serviceRefused("Erreur SQL", 1000);
		}catch (JSONException e){
			return ErrorJSON.serviceRefused("Erreur JSON", 100);
		}
	}

}
