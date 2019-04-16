package test;

import org.json.JSONObject;

import services.recherche.ListPeople;
import tools.UserTools;

public class ListPeopleTest {
	public static void main(String[] args) {
		JSONObject o = ListPeople.listPeople(UserTools.getKey("Anthylnem"));
		System.out.println(o.toString());
	}
}
