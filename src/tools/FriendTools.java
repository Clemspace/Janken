package tools;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

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
	public static void removeFriend(int idA, int friend_id) throws SQLException {
		Connection c = Database.getMySQLConnection();
		String query = "DELETE FROM friends WHERE from_id='"+idA+"' and to_id='"+friend_id+"';";
		java.sql.Statement st = c.createStatement();
		st.executeUpdate(query);
		
		st.close();
		c.close();
	}
	public static int[] listFriends(int idUser) throws SQLException,JSONException{
		Connection c = Database.getMySQLConnection();
		String query = "Select to_id FROM friends WHERE from_id='"+idUser+"'';";
		java.sql.Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		Array array = rs.getArray(0);
		int[]response = (int[])array.getArray();
		
		rs.close();
		st.close();
		c.close();
		return response;
	}
}
