package plts.model;

public abstract class Vehicle {
	
	private String registrationNumber;
	private String colour;
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	@Override
	public String toString() {
		return "Vehicle [registrationNumber=" + registrationNumber + ", colour=" + colour + "]";
	}
}
