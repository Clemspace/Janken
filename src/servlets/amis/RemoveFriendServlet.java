package servlets.amis;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import tools.UserTools;

public class RemoveFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		try {
			String user_login = req.getParameter("user_login");
			String friend_login = req.getParameter("friend_login");
			
			String key;
			
			key = UserTools.getKey(user_login);
			JSONObject o = services.amis.RemoveFriend.removeFriend(key,friend_login);
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println(o.toString());
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
