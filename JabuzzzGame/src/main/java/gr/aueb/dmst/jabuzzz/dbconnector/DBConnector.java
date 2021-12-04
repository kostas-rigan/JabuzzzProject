package main.java.gr.aueb.dmst.jabuzzz.dbconnector;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
		private Connection conn;
		public void connect() {
			
			String url = "jdbc:sqlite:src/main/resources/DATA_BASE.sqlite";
			
			try {
				conn = DriverManager.getConnection(url);
				System.out.println("connected");
			}catch (SQLException e){
				System.out.println(e.getMessage());
			}
			
		}
}
