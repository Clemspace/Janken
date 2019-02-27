
package servlets.authentification;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tools.UserTools;
import services.ErrorJSON;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
		Map<String, String[]> pars=requete.getParameterMap();
		
		JSONObject rep= ErrorJSON.serviceRefused("Erreur paramètres", -1);

		if(pars.containsKey("login"))
		{
			String key;
			try {
				key = UserTools.getKey(requete.getParameter("login"));
				rep = services.authentification.Logout.logout(key, false);		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		reponse.setContentType("text/plain");
		PrintWriter out = reponse.getWriter();
		out.print(rep.toString());
	}
}