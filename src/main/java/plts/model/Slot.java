package plts.model;

public final class Slot {
	
	public Slot(Long slotNumber) {
		super();
		this.slotNumber = slotNumber;
	}
	private Long slotNumber;
	private boolean available = true;
	private Vehicle vehicle;
	
	public Long getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(Long slotNumber) {
		this.slotNumber = slotNumber;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "Slot [slotNumber=" + slotNumber + ", available=" + available + "]";
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
