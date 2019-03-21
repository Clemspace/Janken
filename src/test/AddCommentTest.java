package test;

import org.json.JSONObject;

import services.commentaires.AddComment;

public class AddCommentTest {
	public static void main(String[] args) {
		JSONObject o = AddComment.addComment("Anthylnem","lol");
		System.out.println(o.toString());
	}
}