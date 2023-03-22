package jdbcExercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
	private String url, username, password;

	public PetDAO(String url, String username, String password) {
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

	public int createPetRecordOwner(Pet petCreate) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			try (PreparedStatement stmnt = connection.prepareStatement(
					"INSERT INTO pet (petName, petAge, petColour, petBreed, ownerId) VALUES (?, ?, ?, ?, ?)");) {
				stmnt.setString(1, petCreate.getName());
				stmnt.setInt(2, petCreate.getAge());
				stmnt.setString(3, petCreate.getColour());
				stmnt.setString(4, petCreate.getBreed());
				stmnt.setInt(5, petCreate.getOwnerId());
				return stmnt.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		return 0;
	}

	public int createPetRecordNoOwner(Pet petCreate) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			try (PreparedStatement stmnt = connection
					.prepareStatement("INSERT INTO pet (petName, petAge, petColour, petBreed) VALUES (?, ?, ?, ?)");) {
				stmnt.setString(1, petCreate.getName());
				stmnt.setInt(2, petCreate.getAge());
				stmnt.setString(3, petCreate.getColour());
				stmnt.setString(4, petCreate.getBreed());
				return stmnt.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		return 0;
	}

	public int updatePetRecord(int idToEdit, Pet petObject) {

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			try (PreparedStatement stmnt = connection.prepareStatement(
					"UPDATE pet SET petName = ?, petAge = ?, petColour = ?, petBreed = ? WHERE petId = ?");) {
				stmnt.setString(1, petObject.getName());
				stmnt.setInt(2, petObject.getAge());
				stmnt.setString(3, petObject.getColour());
				stmnt.setString(4, petObject.getBreed());
				stmnt.setInt(5, idToEdit);
				return stmnt.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		return 0;

	}

	public List<Pet> readPetDatabase() {
		List<Pet> pets = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");

			try (Statement stmnt = connection.createStatement();
					ResultSet results = stmnt.executeQuery("SELECT * FROM pet");) {
				while (results.next()) {
					int id = results.getInt(1);
					String name = results.getString("petName");
					String colour = results.getString("petColour");
					int age = results.getInt("petAge");
					String breed = results.getString("petBreed");
					int ownerId = results.getInt("ownerId");
					pets.add(new Pet(id, name, age, colour, breed, ownerId));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		return pets;
	}

	public int deletePetDatabase(int idToDelete) {

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");

			try (PreparedStatement stmnt = connection.prepareStatement("DELETE FROM pet WHERE petId=?");) {
				stmnt.setInt(1, idToDelete);
				return stmnt.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		return 0;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
