package plts.command;

import java.util.Map;

import plts.PLTSEngine;
import plts.model.Car;
import plts.model.Ticket;
import plts.util.Constants;

public class ParkCommand implements InputCommand {

	public ParkCommand() {
		
	}

	@Override
	public Map<String,Object> execute(Map<String,Object> inputmap){
		final String line =(String) inputmap.get(Constants.INPUTLINE.value());
		final String outputprepend =(String) inputmap.get(Constants.OUTPUTPREPENDLINE.value());
		final PLTSEngine engine = (PLTSEngine) inputmap.get(Constants.ENGINE.value());
		final String[] result = line.split(Constants.SPACE.value());
		if (result.length != 3) {
			//System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
			inputmap.put(Constants.RETURNSTR.value(),Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
			return inputmap;
		} else {
			final Car car = new Car();
			car.setRegistrationNumber(result[1]);
			car.setColour(result[2]);
			if (engine == null) {				
				inputmap.put(Constants.RETURNSTR.value(),Constants.CREATE_PARKING_LOT.value());
				return inputmap;
			} else {
				Ticket ticket = engine.getParkingLot().fillSlot(car);
				if (ticket == null) {
					System.out.println(Constants.SORRY_PARKING_LOT_IS_FULL.value());
					inputmap.put(Constants.RETURNSTR.value(),Constants.SORRY_PARKING_LOT_IS_FULL.value());
				} else {
					StringBuilder str = new StringBuilder(Constants.ALLOCATED_SLOT_NUMBER.value())
							.append(ticket.getSlot().getSlotNumber());
					if(outputprepend!=null)
					{
						System.out.println(outputprepend);			
					}
					System.out.println(str);
					inputmap.put(Constants.RETURNSTR.value(), str.toString());
				}
			}
		}
		return inputmap;
	}
}