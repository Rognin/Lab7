package basic;

import java.io.Serializable;

public class Person implements Serializable {
	/**can't be a null, can't be empty*/
	private String name;
	/**must be greater than 0*/
	private int height;
	/**can't be a null, must be greater than 0*/
	private long weight;
	/**can't be a null*/
	private String passportID;
	/**can't be a null*/
	private Color eyeColor;

	public Person(String name, int height, long weight, String passportID, Color eyeColor) {
		super();
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.passportID = passportID;
		this.eyeColor = eyeColor;
	}

	@Override
	public String toString() {
		return "Person {" +
				"name = '" + name + '\'' +
				", height = " + height +
				", weight = " + weight +
				", passportID = '" + passportID + '\'' +
				", eyeColor = " + eyeColor +
				"}";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public String getPassportID() {
		return passportID;
	}

	public void setPassportID(String passportID) {
		this.passportID = passportID;
	}

	public Color getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(Color eyeColor) {
		this.eyeColor = eyeColor;
	}
}