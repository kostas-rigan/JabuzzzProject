package gr.aueb.dmst.jabuzzz.dbconnector;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbconnector {

		private Connection connect() {
			
			String url = "jdbc:sqlite:src/resources/DATA_BASE.sqlite";
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url);
				System.out.println("connected");
			}catch (SQLException e){
				System.out.println(e.getMessage());
			}
			return conn;
		}
}