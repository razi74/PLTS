package plts;

import plts.model.ParkingLot;

public class PLTSEngine {

	private ParkingLot parkingLot;
	private volatile static PLTSEngine uniqueInstance;

	public static PLTSEngine getInstance(int initialSize) {
		if (uniqueInstance == null) {
			synchronized (PLTSEngine.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new PLTSEngine(initialSize);
				}
			}
		}
		return uniqueInstance;
	}

	private PLTSEngine(int initialSize) {
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
