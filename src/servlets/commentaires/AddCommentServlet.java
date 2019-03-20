package servlets.commentaires;

import java.io.IOException;
import java.io.PrintWriter;

import tools.UserTools;

public class AddCommentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		String login = req.getParameter("login");
		String text = req.getParameter("text");
		
		String key = UserTools.getKey(login);
		
		JSONObject o = services.commentaires.AddComment.addComment(key,text);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(o.toString());
	}
}
