package test;

import org.json.JSONObject;

import services.authentification.Login;

public class LoginTest {
	public static void main(String[] args) {
		JSONObject r = Login.login("Anthylnem","password");
		System.out.println(r.toString());
	}
}
