package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteJDBC {

	public static void main(String[] args) {
		
		Connection connect = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection("jdbc:sqlite:Test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
	}

}
