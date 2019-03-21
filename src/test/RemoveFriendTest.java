package test;

import java.sql.SQLException;

import org.json.JSONObject;

import services.amis.RemoveFriend;
import tools.UserTools;

public class RemoveFriendTest {

	public static void main(String[] args) {
		JSONObject r;
		r = RemoveFriend.removeFriend(UserTools.getKey("Clemspace"), "Anthylnem");
		System.out.println(r.toString());
	}

}
