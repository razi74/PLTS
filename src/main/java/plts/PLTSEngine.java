package plts;

import plts.model.ParkingLot;

public class PLTSEngine {

	private ParkingLot parkingLot;

	public PLTSEngine(int initialSize) {
		parkingLot = new ParkingLot(initialSize);
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	@Override
	public String toString() {
		return "PLTSEngine [parkingLot=" + parkingLot + "]";
	}

}
