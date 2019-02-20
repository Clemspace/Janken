package tool;

import java.sql.*;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import db.Database;

public class UserTools {
	
	public static boolean userExists(String login) throws SQLException{
		Connection c = Database.getMySQLConnection();
				
		String query = "SELECT * FROM users WHERE login='"+login+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
			return true;
		
		st.close();
		c.close();
		return false;
		
	}

	public static boolean checkPassword(String login, String password) throws SQLException {
		Connection c = Database.getMySQLConnection();
				
		String query = "SELECT password FROM users WHERE login='"+login+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		
		System.out.println(password+rs.getString("password").toString());
		boolean r = rs.getString("password").toString().equals(password);
		return r;
	}

	public static int getIdUser(String login) throws SQLException {
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT id FROM users WHERE login='"+login+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		return rs.getInt(0);
	}

	public static int insereConnexion(int id_user, boolean b) throws SQLException {
		Connection c = Database.getMySQLConnection();
		GregorianCalendar date = new GregorianCalendar();
		date.getTime();
		String query = "INSERT INTO connections VALUE(null,'"+id_user+"','"+date+"','"+b+"');";
		java.sql.Statement st = c.createStatement();
		st.executeUpdate(query);
		
		query = "SELECT conn_id FROM users WHERE times='"+date+"'and user_id'"+id_user+"';";
		st = c.createStatement();
		ResultSet rs = st.executeQuery(query);

		return rs.getInt(0);
	}

	public static String insereUser(int id_user, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static int insereNouvelUser(String login, String password, String nom, String prenom) throws SQLException{
		int retour = 0;
		Connection c = Database.getMySQLConnection();
		String query = "INSERT INTO users VALUE(null,'"+login+"','"+password+"','"+nom+"','"+prenom+"');";
		java.sql.Statement st = c.createStatement();
		retour = st.executeUpdate(query);
		st.close();
		c.close();
		return retour;
	}
}
