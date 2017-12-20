package plts;

import java.util.List;
import java.util.Map;

import plts.model.Slot;
import plts.util.Constants;

public class FetchDetailsCommand implements InputCommand {

	public FetchDetailsCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String,Object> execute(Map<String,Object> inputmap) {
		final String line =(String) inputmap.get(Constants.INPUTLINE.value());
		final String outputprepend =(String) inputmap.get(Constants.OUTPUTPREPENDLINE.value());
		final PLTSEngine engine = (PLTSEngine) inputmap.get(Constants.ENGINE.value());
		final String[] result = line.split(" ");
		if (engine != null && engine.getParkingLot() != null) {
			List<Slot> list = engine.getParkingLot().getSlot();
			if (list != null && !list.isEmpty()) {
				StringBuilder str = new StringBuilder(20);
				for (Slot slot : list) {
					if (!slot.isAvailable()) {

						if (result[0].equals(Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.value())) {
							if (slot.getVehicle().getColour().equals(result[1])) {
								str.append(slot.getVehicle().getRegistrationNumber()).append(", ");
							}

						} else if (result[0].equals(Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.value())) {

							if (slot.getVehicle().getColour().equals(result[1])) {
								str.append(slot.getSlotNumber()).append(", ");
							}

						} else if (result[0].equals(Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.value())) {

							if (slot.getVehicle().getRegistrationNumber().equals(result[1])) {
								str.append(slot.getSlotNumber()).append(", ");
							}

						}
					}
				}
				if (str.length() > 0) {
					if(outputprepend!=null)
					{
						System.out.println(outputprepend);			
					}
					System.out.println(str.substring(0, str.length() - 2));
				} else {
					System.out.println(Constants.NOTFOUND.value());
				}
			}
		}
	return null;
	}
}
