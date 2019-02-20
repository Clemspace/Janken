package servlet.authentification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		
		JSONObject o = service.authentification.Login.login(login,password);
	}
}
