package test;

import org.json.JSONObject;

import services.commentaires.Timeline;
import tools.UserTools;

public class TimelineTest {
	public static void main(String[] args) {
		JSONObject o = Timeline.timeline(UserTools.getKey("test"));
		System.out.println(o.toString());
	}
}