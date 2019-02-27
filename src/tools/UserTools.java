package tools;

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

		return rs.getString("password").toString().equals(password);
	}

	public static int getIdUser(String login) throws SQLException {
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT id FROM users WHERE login='"+login+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();

		return rs.getInt(1);
	}
	
	public static int getActiveConnId(String key) throws SQLException {
		
		Connection conn = Database.getMySQLConnection();
		
		String query="SELECT id FROM sessions WHERE '"+key+"' AND expired=FALSE";
		
		Statement st=(Statement) conn.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		rs.next();
		int ret=rs.getInt("conn_id");
		rs.close();
		st.close();
		conn.close();
		return ret;
			
	}

	public static int insereConnexion(int id_user, boolean b) throws SQLException {
		Connection c = Database.getMySQLConnection();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		PreparedStatement query = c.prepareStatement("INSERT INTO connections VALUE(null,?,?,?)");
		query.setInt(1,id_user);
		query.setTimestamp(2, now);
		query.setBoolean(3, false);

		query.executeUpdate();
		
		PreparedStatement query1 = c.prepareStatement("SELECT conn_id FROM connections WHERE times = ? and user_id = ?");
		query1.setTimestamp(1, now);
		query1.setInt(2, id_user);
		
		ResultSet rs = query1.executeQuery();
		rs.next();
		
		return rs.getInt(1);
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
