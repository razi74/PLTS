package plts.command;

import java.util.Map;

import plts.PLTSEngine;
import plts.util.Constants;

public class CreatePLCommand implements InputCommand {

	public CreatePLCommand() {

	}

	@Override
	public Map<String, Object> execute(Map<String, Object> inputmap) {

		PLTSEngine engine;
		StringBuilder str = new StringBuilder(Constants.CREATED_A_PARKING_LOT_WITH.value());
		final String line = (String) inputmap.get(Constants.INPUTLINE.value());
		final String outputprepend = (String) inputmap.get(Constants.OUTPUTPREPENDLINE.value());
		String[] result = line.split(Constants.SPACE.value());
		if (result.length > 2) {
			//System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
			inputmap.put(Constants.RETURNSTR.value(),Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
			return inputmap;
		} 
		try {
			int initSize = Integer.valueOf(result[1]);
			if (initSize < 0) {
				inputmap.put(Constants.RETURNSTR.value(), Constants.INVALID_COMMAND.value());
				return inputmap;
			}
			engine = new PLTSEngine(initSize);
			if (engine != null && engine.getParkingLot() != null) {
				str.append(initSize).append(" slots");
				if (outputprepend != null) {
					System.out.println();
					System.out.println(outputprepend);
				}
				System.out.println(str);
			}
			inputmap.put(Constants.ENGINE.value(), engine);
			inputmap.put(Constants.RETURNSTR.value(), str.toString());
		} catch (Exception e) {
			// e.printStackTrace();
			inputmap.put(Constants.RETURNSTR.value(), Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
		}
		return inputmap;
	}
}
