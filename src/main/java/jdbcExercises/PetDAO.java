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

	public int createPetRecord(String name, int age, String colour, String breed) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!");
			try (PreparedStatement stmnt = connection
					.prepareStatement("INSERT INTO pet (petName, petAge, petColour, petBreed) VALUES (?, ?, ?, ?)");) {
				stmnt.setString(1, name);
				stmnt.setInt(2, age);
				stmnt.setString(3, colour);
				stmnt.setString(4, breed);
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
					pets.add(new Pet(name, colour, breed, id, age));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}

		return pets;
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
