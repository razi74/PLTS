package plts.command;

import java.util.Map;

import plts.PLTSEngine;
import plts.model.Slot;
import plts.util.Constants;

public class LeaveCommand implements InputCommand {

	public LeaveCommand() {

	}

	@Override
	public Map<String, Object> execute(Map<String, Object> inputmap) {

		final String line = (String) inputmap.get(Constants.INPUTLINE.value());
		final String outputprepend = (String) inputmap.get(Constants.OUTPUTPREPENDLINE.value());
		final PLTSEngine engine = (PLTSEngine) inputmap.get(Constants.ENGINE.value());
		String[] result = line.split(Constants.SPACE.value());
		try {
			int initSize = Integer.valueOf(result[1]);
			if (initSize < 0) {
				inputmap.put(Constants.RETURNSTR.value(), Constants.INVALID_COMMAND.value());
				return inputmap;
			}
			Slot slot = new Slot((long) initSize);
			boolean cleared = engine.getParkingLot().clearSlot(slot);
			if (cleared) {
				StringBuilder str = new StringBuilder(Constants.SLOT_NUMBER.value()).append(slot.getSlotNumber()).append(Constants.IS_FREE);
				if (outputprepend != null) {
					System.out.println(outputprepend);
				}
				System.out.println(str);
				inputmap.put(Constants.ENGINE.value(), engine);
				inputmap.put(Constants.RETURNSTR.value(), str.toString());
			}
		} catch (Exception e) {
			// e.printStackTrace();
			inputmap.put(Constants.RETURNSTR.value(), Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
		}
		return inputmap;
	}
}
