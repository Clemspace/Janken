package tools;

import java.sql.*;

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
	public static boolean userExists(int id) throws SQLException{
		Connection c = Database.getMySQLConnection();
				
		String query = "SELECT * FROM users WHERE user_id='"+id+"';";
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
		
		PreparedStatement query = c.prepareStatement("INSERT INTO connections VALUE(null,?,?,?,false)");
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
		return true;
		/*Connection c = Database.getMySQLConnection();
		
		String query = "SELECT user_id FROM connections WHERE expired=false and conn_id=UNHEX(+'"+key+"');";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		System.err.println("quoi");
		
		while(rs.next()) {
			int user_id = rs.getInt("user_id");
			System.out.println(user_id);
		}
		return rs.next();*/
		//System.out.println(rs.getInt("user_id"));
		
		//return rs.getString(1)!=null;
	}
	
	public static int logout(String key, boolean state) throws SQLException {
		
		Connection c = Database.getMySQLConnection();
		String query = "Update connections SET expired=true WHERE conn_id=UNHEX('"+key+"');";
		java.sql.Statement st= c.createStatement();
		st.executeUpdate(query);
		st.close();
		c.close();
		return 0;
	}

	public static String getKey(String login) throws SQLException {
		Connection c = Database.getMySQLConnection();
		
		int user_id = getIdUser(login); 
		String query = "SELECT HEX(conn_id) FROM connections WHERE expired=false and user_id='"+user_id+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		
		return rs.getString(1);
	}
	public static int getIdFromKey(String key) throws SQLException {
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT user_id FROM connections WHERE and conn_id=UNHEX(+'"+key+"');";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		
		return rs.getInt(1);
	}

	public static boolean isFriend(int from_id, int to_id) throws SQLException {
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT * FROM friends WHERE from_id='"+from_id+"' and to_id='"+to_id+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		boolean res =rs.next();
		st.close();
		c.close();
		
		return res;
	}
	public static void addFriend(int idA, int friend_id) throws SQLException {
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Connection c = Database.getMySQLConnection();
		String query = "INSERT INTO users VALUE('"+idA+"','"+friend_id+"','"+now+"');";
		java.sql.Statement st = c.createStatement();
		st.executeUpdate(query);
		st.close();
		c.close();		
	
	}
	public static String getLogin(int id_user) {
		Connection c = Database.getMySQLConnection();
		
		String query = "SELECT id FROM users WHERE login='"+login+"';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();

		return rs.getInt(1);
	}
	
}
