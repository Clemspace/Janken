package servlets.amis;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tools.UserTools;

public class ListFriendServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		try {
			String login = req.getParameter("login");

			JSONObject o = services.amis.ListFriend.listFriend(login);
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(o.toString());
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
