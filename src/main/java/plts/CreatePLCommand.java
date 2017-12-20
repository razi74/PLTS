package plts;

import java.util.Map;

import plts.util.Constants;

public class CreatePLCommand implements InputCommand {

	public CreatePLCommand() {
		
	}

	@Override
	public Map<String,Object> execute(Map<String,Object> inputmap) {
		
		PLTSEngine engine;
		final String line =(String) inputmap.get(Constants.INPUTLINE.value());
		final String outputprepend =(String) inputmap.get(Constants.OUTPUTPREPENDLINE.value());
		char initSizeChar = line.charAt(line.length() - 1);
		int initSize = Character.getNumericValue(initSizeChar);
		engine = new PLTSEngine(initSize);
		if (engine != null && engine.getParkingLot() != null) {
			StringBuilder str = new StringBuilder("Created a parking lot with ").append(initSize).append(" slots");
			if(outputprepend!=null)
			{
				System.out.println(outputprepend);			
			}
			System.out.println(str);
		}
		inputmap.put(Constants.ENGINE.value(), engine);
		return inputmap;
	}
}
