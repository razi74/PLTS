package plts.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import plts.util.Constants;

public class ParkingLot {

	private Integer numberOfSlots;
	private List<Slot> slotList;

	public ParkingLot(Integer numberOfLots) {
		super();
		this.numberOfSlots = numberOfLots;
		this.slotList = new LinkedList<>();
		for (int i = 0; i < numberOfLots; i++) {
			Slot slot = new Slot((long) (i + 1));
			this.slotList.add(slot);
		}
	}

	public Ticket fillSlot(Vehicle vehicle) {

		if (slotList != null) {
			for (Slot slot : slotList) {
				if (slot.isAvailable()) {
					slot.setAvailable(false);
					slot.setVehicle(vehicle);
					Ticket ticket = new Ticket();
					ticket.setSlot(slot);
					ticket.setInTimestamp(Instant.now());
					SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIMESTAMPSTRING.value());
					Date date = new Date();
					ticket.setTicketNumber(sdf.format(date));
					return ticket;
				}
			}
		}
		return null;
	}

	public boolean clearSlot(Slot slot) {
		if (slotList != null) {
			for (Slot slotObj : slotList) {
				if (slot.getSlotNumber().equals(slotObj.getSlotNumber())) {
					slotObj.setAvailable(true);
					slotObj.setVehicle(null);
					return slotObj.isAvailable();
				}
			}
		}
		return false;
	}

	public ParkingLot() {
	}

	public Integer getNumberOfLots() {
		return numberOfSlots;
	}

	public List<Slot> getSlot() {
		return slotList;
	}

	public void setSlot(List<Slot> slot) {
		this.slotList = slot;
	}

	@Override
	public String toString() {
		return "ParkingLot [numberOfSlots=" + numberOfSlots + ", slot=" + slotList + "]";
	}

}
