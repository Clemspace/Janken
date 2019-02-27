package db;

import java.sql.Connection;
import java.sql.SQLException;

public class UserTools {
	

	public static int insereNouvelUser(String login, String password, String nom, String prenom) throws SQLException{
		int retour = 0;
		try {
			Connection c = Database.getMySQLConnection();
				
			String query = "INSERT INTO login_table VALUE(null,"+login+","+password+","+nom+","+prenom;
			java.sql.Statement st = c.createStatement();
			retour = st.executeUpdate(query);
			
		} catch (SQLException e) {
			
		} finally {
			return retour;
		}
	}
	public static void logout(String key, boolean state) throws SQLException{
		Connection c = Database.getMySQLConnection();
		String query = "Update sessions SET expired=TRUE WHERE conn_id='"+key+"';";
		java.sql.Statement st= c.createStatement();
		st.executeUpdate(query);
		st.close();
		c.close();
		
	}
}
