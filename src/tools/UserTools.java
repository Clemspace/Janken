package tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import db.Database;
import services.ErrorJSON;

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
	public static boolean userExists(int id) throws SQLException{
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT * FROM users WHERE id='"+id+"';";
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
		Connection c = Database.getMySQLConnection();
		
		String query="SELECT user_id FROM connections WHERE conn_key='"+key+"' AND expired=FALSE;";
		
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		rs.next();
		int ret=rs.getInt("user_id");
		rs.close();
		st.close();
		c.close();
		return ret;	
	}

	public static String insereConnexion(int id_user, boolean b) throws SQLException {
		Connection c = Database.getMySQLConnection();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		PreparedStatement query = c.prepareStatement("INSERT INTO connections VALUE(?,?,?,?,false)");
		
		String key = UUID.randomUUID().toString();
		
		query.setString(1, key);
		query.setInt(2,id_user);
		query.setTimestamp(3, now);
		query.setBoolean(4, false);

		query.executeUpdate();
		
		PreparedStatement query1 = c.prepareStatement("SELECT conn_key FROM connections WHERE times = ? and user_id = ?");
		query1.setTimestamp(1, now);
		query1.setInt(2, id_user);
		
		ResultSet rs = query1.executeQuery();
		rs.next();
		
		return rs.getString(1);
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

	public static boolean keyVerified(String key) throws SQLException {
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT user_id FROM connections WHERE expired=false and conn_key=+'"+key+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		/*while(rs.next()) {
			int user_id = rs.getInt("user_id");
			System.out.println(user_id);
		}*/
		return rs.next();
	}
	
	public static int logout(String key, boolean state) throws SQLException {
		
		Connection c = Database.getMySQLConnection();
		String query = "Update connections SET expired=1 WHERE conn_key='"+key+"';";
		java.sql.Statement st= c.createStatement();
		st.executeUpdate(query);
		st.close();
		c.close();
		return 1;
	}

	public static String getKey(String login){
		try {
			Connection c = Database.getMySQLConnection();
			
			int user_id = getIdUser(login); 
			String query = "SELECT conn_key FROM connections WHERE expired=false and user_id='"+user_id+"';";
			java.sql.Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(login+" n'a pas de key", 1000).toString();
		}
	}
	public static int getIdFromKey(String key) throws SQLException {
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT user_id FROM connections WHERE expired=false and conn_key=+'"+key+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		return rs.getInt(1);
	}

	public static String getLogin(int id_user) throws SQLException {
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT login FROM users WHERE id='"+id_user+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();

		return rs.getString(1);
	}
	public static boolean userConnected(String key) throws SQLException {
		Connection c = Database.getMySQLConnection();
		String query = "SELECT * FROM connections WHERE conn_key='"+key+"' and expired=false;";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
			return true;
		
		st.close();
		c.close();
		return false;
	}
	
	public static JSONObject getNomPrenom(String login) throws SQLException, JSONException {
		Connection c = Database.getMySQLConnection();
		String query = "SELECT nom,prenom FROM users WHERE login='"+login+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		JSONObject o = new JSONObject();
		while(rs.next()) {
			o.put("nom",rs.getString("nom"));
			o.put("prenom",rs.getString("prenom"));
		}
		
		st.close();
		c.close();
		return o;
	}
	
}
