package test;

import java.sql.SQLException;

import org.json.JSONObject;

import services.authentification.Logout;
import tools.UserTools;

public class LogoutTest {

	public static void main(String[] args) {		
		JSONObject r;
		r = Logout.logout(UserTools.getKey("login"), false);
		System.out.println(r.toString());
		

	}

}
