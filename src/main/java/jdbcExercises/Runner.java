package jdbcExercises;

public class Runner {

	public static void main(String[] args) {

		PetDAO petDAO = new PetDAO("jdbc:mysql://localhost:3306/petexercise", "root", "pass");

		System.out.println(petDAO.createPetRecord("testName2", 59025, "testColour2", "testBreed2"));

		System.out.println(petDAO.readPetDatabase().toString());

		System.out.println(petDAO.updatePetRecord(2, new Pet(2, "testName2", 18, "testColour2", "testBreed2")));

		System.out.println(petDAO.readPetDatabase().toString());
	}
}
