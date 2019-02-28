package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import db.Database;

public class FriendTools {
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
		String query = "INSERT INTO friends VALUE('"+idA+"','"+friend_id+"','"+now+"');";
		java.sql.Statement st = c.createStatement();
		st.executeUpdate(query);
		
		st.close();
		c.close();		
	
	}
}
