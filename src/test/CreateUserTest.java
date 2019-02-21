package test;

import org.json.JSONObject;

import services.authentification.CreateUser;

public class CreateUserTest {

	public static void main(String[] args) {
		JSONObject r = CreateUser.createUser("login","password","Anthea","lol");
		System.out.println(r.toString());
	}

}
