package jdbcExercises;

public class Pet {
	private String name, colour, breed;
	private int id, age;

	public Pet(String name, String colour, String breed, int id, int age) {
		super();
		this.name = name;
		this.colour = colour;
		this.breed = breed;
		this.id = id;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", colour=" + colour + ", breed=" + breed + ", age=" + age + "]";
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

}
