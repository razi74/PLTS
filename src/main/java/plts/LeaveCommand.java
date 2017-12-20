package plts;

import java.util.Map;

import plts.model.Slot;
import plts.util.Constants;

public class LeaveCommand implements InputCommand {

	public LeaveCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String,Object> execute(Map<String,Object> inputmap) {
		
		final String line =(String) inputmap.get(Constants.INPUTLINE.value());
		final String outputprepend =(String) inputmap.get(Constants.OUTPUTPREPENDLINE.value());
		final PLTSEngine engine = (PLTSEngine) inputmap.get(Constants.ENGINE.value());
		char initSize = line.charAt(line.length() - 1);
		Slot slot = new Slot((long) Character.getNumericValue(initSize));
		boolean cleared = engine.getParkingLot().clearSlot(slot);
		if (cleared) {
			StringBuilder str = new StringBuilder("Slot number ").append(slot.getSlotNumber()).append(" is free");
			if(outputprepend!=null)
			{
				System.out.println(outputprepend);			
			}
			System.out.println(str);
		}
		return null;
	}
}
