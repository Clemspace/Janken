package test;

import org.json.JSONObject;

import services.recherche.Search;
import tools.UserTools;

public class SearchTest {

	public static void main(String[] args) {
		JSONObject o = Search.search(UserTools.getKey("Anthylnem"),"lol","Anthylnem");
		System.out.println(o.toString());
	}

}
