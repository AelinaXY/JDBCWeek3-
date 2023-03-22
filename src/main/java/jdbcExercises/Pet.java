package jdbcExercises;

public class Pet {
	private String name, colour, breed;
	private int id, age, ownerId;

	public Pet(int id, String name, int age, String colour, String breed) {
		super();
		this.name = name;
		this.colour = colour;
		this.breed = breed;
		this.id = id;
		this.age = age;
	}

	public Pet(int id, String name, int age, String colour, String breed, int ownerId) {
		super();
		this.name = name;
		this.colour = colour;
		this.breed = breed;
		this.id = id;
		this.age = age;
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", age=" + age + ", colour=" + colour + ", breed=" + breed
				+ ", ownerId=" + ownerId + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
