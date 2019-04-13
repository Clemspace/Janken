package test;

import org.json.JSONObject;

import services.commentaires.AddComment;
import tools.UserTools;

public class AddCommentTest {
	public static void main(String[] args) {
		JSONObject o = AddComment.addComment(UserTools.getKey("Anthylnem"),"Salut ça va les gens ?");
		System.out.println(o.toString());
	}
}