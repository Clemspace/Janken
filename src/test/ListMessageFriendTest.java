package test;

import org.json.JSONObject;

import services.commentaires.ListMessageFriend;
import tools.UserTools;

public class ListMessageFriendTest {
	
	public static void main(String[] args) {
		JSONObject o = ListMessageFriend.listMessageFriend(UserTools.getKey("Anthylnem"));
		System.out.println(o.toString());
	}
}