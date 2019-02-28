package servlets.authentification;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ErrorJSON;


public class AddCommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
		
		Map<String, String[]> pars=requete.getParameterMap(); // Recupere les param�tres de l'URL
		String rep=ErrorJSON.serviceRefused("Erreur param Servlet ", -100).toString();
		
		if(pars.containsKey("key") && pars.containsKey("text")){
			
			String key=requete.getParameter("key");
			String text=requete.getParameter("text");
			rep=services.commentaires.AddComment.addComment(key, text).toString();
		}
		
		reponse.setContentType("text/plain");
		PrintWriter out = reponse.getWriter();
		out.println(rep);
	}
}