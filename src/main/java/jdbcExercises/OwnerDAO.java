package jdbcExercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAO {
	private String url, username, password;

	public OwnerDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;

		System.out.println("Connecting database...");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public int createOwnerRecord(Owner ownerObject) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! - createOwnerRecord");
			try (PreparedStatement stmnt = connection
					.prepareStatement("INSERT INTO owner (ownerName, ownerAge) VALUES (?, ?)");) {
				stmnt.setString(1, ownerObject.getName());
				stmnt.setInt(2, ownerObject.getAge());
				return stmnt.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database! - createOwnerRecord", e);
		}

		return 0;
	}

	public int updateOwnerRecord(int idToEdit, Owner ownerObject) {

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! - updateOwnerRecord");
			try (PreparedStatement stmnt = connection
					.prepareStatement("UPDATE owner SET ownerName = ?, ownerAge = ? WHERE ownerId = ?");) {
				stmnt.setString(1, ownerObject.getName());
				stmnt.setInt(2, ownerObject.getAge());
				stmnt.setInt(3, idToEdit);
				return stmnt.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database! - updateOwnerRecord", e);
		}

		return 0;

	}

	public List<Owner> readOwnerDatabase() {
		List<Owner> owners = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! - readOwnerDatabase");

			try (Statement stmnt = connection.createStatement();
					ResultSet results = stmnt.executeQuery("SELECT * FROM owner");) {
				while (results.next()) {
					int id = results.getInt(1);
					String name = results.getString("ownerName");
					int age = results.getInt("ownerAge");
					owners.add(new Owner(id, name, age));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database! - readOwnerDatabase", e);
		}

		return owners;
	}

	public int deleteOwnerDatabaseRecord(int idToDelete) {

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! - deleteOwnerDatabaseRecord");

			try (PreparedStatement stmnt = connection.prepareStatement("DELETE FROM owner WHERE ownerId=?");) {
				stmnt.setInt(1, idToDelete);
				return stmnt.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database! - deleteOwnerDatabaseRecord", e);
		}

		return 0;
	}

}
