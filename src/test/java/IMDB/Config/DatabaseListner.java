package IMDB.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import DataModels.Movies;

public class DatabaseListner {

	private static Connection connect() {
		Connection conn = null;
		try {
			// db parameters
			String url = "jdbc:sqlite:C:/sqlite3/imdb.sqlite";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void insert(List<Movies> allMovies) {
		String sql = "INSERT INTO main.Movies(Rank_Title,Rating) VALUES (?1,?2)";
		Connection connect = null;
		try {
			connect = connect();
			PreparedStatement pstmt = connect.prepareStatement(sql);

			for (int i = 0; i < allMovies.size(); i++) {
				pstmt.setString(1, allMovies.get(i).rank_Title);
				pstmt.setString(2, allMovies.get(i).rating);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connect != null) {
					connect.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
