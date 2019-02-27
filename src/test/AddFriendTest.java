package test;

import java.sql.SQLException;

import org.json.JSONObject;

import services.amis.AddFriend;
import services.authentification.Login;
import services.authentification.Logout;
import tools.UserTools;

public class AddFriendTest {

	public static void main(String[] args) {		
		
			Login.login("Clemspace","password");
			JSONObject r;
			try {
				r = AddFriend.addFriend(UserTools.getKey("Clemspace"), "Anthylnem");
				System.out.println(r.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 	
			
	}
}
