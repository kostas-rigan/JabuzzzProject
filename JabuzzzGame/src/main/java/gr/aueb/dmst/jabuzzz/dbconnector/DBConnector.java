package main.java.gr.aueb.dmst.jabuzzz.dbconnector;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public String[] selectQuestion() {
		String sql = "SELECT Description, Right_answer, Wrong_answer1, Wrong_answer2, Wrong_answer3, Wrong_answer4 FROM Questions WHETR";

		try(
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			ResultSetMetaData metaData = rs.getMetaData();
			int numberOfColumns = metaData.getColumnCount();

			String[] Question = new String[6];
			// loop through the result set
			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					System.out.printf("%-8s\t", rs.getObject(i));
				}
				System.out.println();
			}
			return Question;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
