package test;

import org.json.JSONObject;


import services.amis.ListFriend;

public class ListFriendTest {

	public static void main(String[] args) {		
		JSONObject r;
		r = ListFriend.listFriend("test");
		System.out.println(r.toString());		
	}
}
