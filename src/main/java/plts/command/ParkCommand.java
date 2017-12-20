package plts.command;

import java.util.Map;

import plts.PLTSEngine;
import plts.model.Car;
import plts.model.Ticket;
import plts.util.Constants;

public class ParkCommand implements InputCommand {

	public ParkCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String,Object> execute(Map<String,Object> inputmap){
		final String line =(String) inputmap.get(Constants.INPUTLINE.value());
		final String outputprepend =(String) inputmap.get(Constants.OUTPUTPREPENDLINE.value());
		final PLTSEngine engine = (PLTSEngine) inputmap.get(Constants.ENGINE.value());
		final String[] result = line.split(" ");
		if (result.length != 3) {
			System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
		} else {
			final Car car = new Car();
			car.setRegistrationNumber(result[1]);
			car.setColour(result[2]);
			if (engine == null) {
				System.out.println(Constants.CREATE_PARKING_LOT.value());
			} else {
				Ticket ticket = engine.getParkingLot().fillSlot(car);
				if (ticket == null) {
					System.out.println("Sorry, parking lot is full");
				} else {
					StringBuilder str = new StringBuilder("Allocated slot number: ")
							.append(ticket.getSlot().getSlotNumber());
					if(outputprepend!=null)
					{
						System.out.println(outputprepend);			
					}
					System.out.println(str);
				}
			}
		}
		return null;
	}
}
