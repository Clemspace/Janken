package test;

import java.sql.SQLException;

import org.json.JSONObject;

import services.amis.AddFriend;
import tools.UserTools;

public class AddFriendTest {

	public static void main(String[] args) {		
			JSONObject r;
			r = AddFriend.addFriend(UserTools.getKey("Clemspace"), "Anthylnem");
			System.out.println(r.toString());		
	}
}
