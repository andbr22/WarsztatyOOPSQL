package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Solution {
	private int id;
	private int user_id;
	private int excercise_id;
	private Date created;
	private Date updated;
	private String description;
	
	//******************
	//***Constructors***
	//******************	
	public Solution(Date created, Date updated, String description) {
		this.created = created;
		this.updated = updated;
		this.description = description;
	}
	public Solution() {
	}
	//***END Constructors***
	//**********************	
	
	//*********************
	//***Getters Setters***
	//*********************
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getExcercise_id() {
		return excercise_id;
	}
	public void setExcercise_id(int excercise_id) {
		this.excercise_id = excercise_id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
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
	public static Solution[] loadAllSolutions(Connection con) throws SQLException{
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solutions";
		PreparedStatement preparedStatement;
		preparedStatement = con.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loaded = new Solution();
			loaded.id = resultSet.getInt("id");
			loaded.excercise_id = resultSet.getInt("excercise_id");
			loaded.user_id = resultSet.getInt("user_id");
			loaded.description = resultSet.getString("description");
			loaded.created = resultSet.getDate("created");
			loaded.updated = resultSet.getDate("updated");
			solutions.add(loaded);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
	
	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solutions where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loaded = new Solution();
			loaded.id = resultSet.getInt("id");
			loaded.description = resultSet.getString("description");
			loaded.excercise_id = resultSet.getInt("excercise_id");
			loaded.user_id = resultSet.getInt("user_id");
			loaded.updated = resultSet.getDate("updated");
			loaded.created = resultSet.getDate("created");
			return loaded;
		}
		return null;
	}
	
	public void delete(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM solutions WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}
	
	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO solutions (description, excercise_id, user_id, created, updated) VALUES (?, ?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.description);
			preparedStatement.setInt(2, this.excercise_id);
			preparedStatement.setInt(3, this.user_id);
			preparedStatement.setDate(4, new java.sql.Date(this.created.getTime()));
			preparedStatement.setDate(5, new java.sql.Date(this.updated.getTime()));
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE solutions SET description=?, excercise_id=?, user_id=?, created=?, updated=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, this.description);
			preparedStatement.setInt(2, this.excercise_id);
			preparedStatement.setInt(3, this.user_id);
			preparedStatement.setDate(4, new java.sql.Date(this.created.getTime()));
			preparedStatement.setDate(5, new java.sql.Date(this.updated.getTime()));
			preparedStatement.setInt(6, this.id);
			preparedStatement.executeUpdate();
		}
	}
	
	public static Solution[] loadAllByExerciseId(Connection con, int id) throws SQLException{
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String query = "SELECT * FROM solutions WHERE excercise_id= ?";
		PreparedStatement ps;
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Solution loaded = new Solution();
			loaded.id = rs.getInt("id");
			loaded.excercise_id = rs.getInt("excercise_id");
			loaded.user_id = rs.getInt("user_id");
			loaded.description = rs.getString("description");
			loaded.created = rs.getDate("created");
			loaded.updated = rs.getDate("updated");
			solutions.add(loaded);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
	
	public static Solution[] loadAllByUserId(Connection con, int id) throws SQLException{
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String query = "SELECT * FROM solutions WHERE user_id= ?";
		PreparedStatement ps;
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Solution loaded = new Solution();
			loaded.id = rs.getInt("id");
			loaded.excercise_id = rs.getInt("excercise_id");
			loaded.user_id = rs.getInt("user_id");
			loaded.description = rs.getString("description");
			loaded.created = rs.getDate("created");
			loaded.updated = rs.getDate("updated");
			solutions.add(loaded);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
	
	//***END Metody***
	//****************
	@Override
	public String toString() {
		return "Solution [id=" + id + ", user_id=" + user_id + ", excercise_id=" + excercise_id + ", created=" + created
				+ ", updated=" + updated + ", description=" + description + "]";
	}
}
