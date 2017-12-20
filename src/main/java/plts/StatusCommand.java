package plts;

import java.util.List;
import java.util.Map;

import plts.model.Slot;
import plts.util.Constants;

public class StatusCommand implements InputCommand {

	public StatusCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String,Object> execute(Map<String,Object> inputmap) {
		
		final String outputprepend =(String) inputmap.get(Constants.OUTPUTPREPENDLINE.value());
		final PLTSEngine engine = (PLTSEngine) inputmap.get(Constants.ENGINE.value());
		if (engine != null && engine.getParkingLot() != null) {
			List<Slot> list = engine.getParkingLot().getSlot();
			if (list != null && !list.isEmpty()) {
				StringBuilder strHdr = new StringBuilder("Slot No.").append(Constants.TAB.value())
						.append("Registration No").append(Constants.TAB.value()).append("Colour");
				if(outputprepend!=null)
				{
					System.out.println(outputprepend);			
				}
				System.out.println(strHdr);
				for (Slot slot : list) {
					if (!slot.isAvailable()) {
						StringBuilder str = new StringBuilder(20).append(slot.getSlotNumber())
								.append(Constants.TAB.value()).append(slot.getVehicle().getRegistrationNumber())
								.append(Constants.TAB.value()).append(slot.getVehicle().getColour());
						System.out.println(str);
					}
				}
			}
		}
		return null;
	}
}
