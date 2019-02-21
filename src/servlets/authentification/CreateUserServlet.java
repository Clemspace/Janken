package servlets.authentification;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class CreateUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		
		JSONObject o = services.authentification.CreateUser.createUser(login,password,nom,prenom);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(o.toString());
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		doGet(req,resp);
	}
}