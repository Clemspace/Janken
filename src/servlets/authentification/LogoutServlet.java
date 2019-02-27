
package servlets.authentification;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.authentification.Logout;
import tools.UserTools;
import services.ErrorJSON;

public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
		Map<String, String[]> pars=requete.getParameterMap();
		
		String rep= ErrorJSON.serviceRefused("Erreur paramètres", -1).toString();
		if(pars.containsKey(conn_id))
		{
			int conn_id =requete.getParameter(conn_id);
			rep = services.authentification.Logout(conn_id, false);
			
			
		}
		reponse.setContentType("text/plain");
		PrintWriter out = reponse.getWriter();
		out.print(rep.toString());
	}
}