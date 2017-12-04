package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Excercise {
	private int id;
	private String title;
	private String description;
	
	//******************
	//***Constructors***
	//******************	
	public Excercise(String title, String description) {
		this.title = title;
		this.description = description;
	}
	public Excercise() {
	}
	//***END Constructors***
	//**********************	
	
	//*********************
	//***Getters Setters***
	//*********************
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	//***END Getters Setters***
	//*************************
	
	//************
	//***Metody***
	//************
	public static Excercise[] loadAllExcercises(Connection con) throws SQLException{
		ArrayList<Excercise> exe = new ArrayList<Excercise>();
		String sql = "SELECT * FROM excercises";
		PreparedStatement preparedStatement;
		preparedStatement = con.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Excercise loaded = new Excercise();
			loaded.id = resultSet.getInt("id");
			loaded.title = resultSet.getString("title");
			loaded.description = resultSet.getString("description");
			exe.add(loaded);
		}
		Excercise[] gArray = new Excercise[exe.size()];
		gArray = exe.toArray(gArray);
		return gArray;
	}
	
	static public Excercise loadExcerciseById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM	excercises where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Excercise loaded = new Excercise();
			loaded.id = resultSet.getInt("id");
			loaded.title = resultSet.getString("title");
			loaded.description = resultSet.getString("description");
			return loaded;
		}
		return null;
	}
	
	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM excercises WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}
	
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO excercises (title, description) VALUES (?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE excercises SET title=?, description=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.setInt(3, this.id);
			preparedStatement.executeUpdate();
		}
	}
	
	public static Excercise[] loadAllByUserId(Connection con, int id) throws SQLException{
		ArrayList<Excercise> exe = new ArrayList<Excercise>();
		String sql = "SELECT * FROM excercises JOIN solutions on excercises.id=solutions.excercise_id where solutions.user_id = ?";
		PreparedStatement preparedStatement;
		preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Excercise loaded = new Excercise();
			loaded.id = resultSet.getInt("id");
			loaded.title = resultSet.getString("title");
			loaded.description = resultSet.getString("description");
			exe.add(loaded);
		}
		Excercise[] gArray = new Excercise[exe.size()];
		gArray = exe.toArray(gArray);
		return gArray;
	}
	//***END Metody***
	//****************
	
	@Override
	public String toString() {
		return "Excercise [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	
}
