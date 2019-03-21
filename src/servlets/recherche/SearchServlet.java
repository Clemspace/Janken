package servlets.recherche;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tools.UserTools;

public class SearchServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		String login = req.getParameter("login");
		String query = req.getParameter("query");
		String friend = req.getParameter("friend");
		
		String key = UserTools.getKey(login);
		JSONObject o = services.recherche.Search.search(key,query,friend);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(o.toString());
		
	}
}
