package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static Connection con;
	
	public static Connection getConnection() throws SQLException{
		if (con == null){
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/warsztaty?useSSL=false", "root","coderslab");
		}else
			return con;
	}
}
