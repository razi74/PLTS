package plts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import plts.model.Car;
import plts.model.Slot;
import plts.model.Ticket;
import plts.util.Constants;

public class Main {

	public static void main(String[] args) {
		PLTSEngine engine = null;
		String line;
		if (args != null && args.length == 0) {
			Scanner scanner = new Scanner(System.in);
			try {
				do {
					System.out.println("Input: ");
					line = scanner.nextLine();
					if (line.startsWith(Constants.CREATE_PARKING_LOT.value())) {
						engine = doCPL(line,Constants.OUTPUT.value());
					} else if (line.startsWith(Constants.PARK.value())) {
						doPark(engine, line,Constants.OUTPUT.value());
					} else if (line.startsWith(Constants.LEAVE.value())) {
						doLeave(engine, line,Constants.OUTPUT.value());
					} else if (line.equals(Constants.STATUS.value())) {
						doStatus(engine,Constants.OUTPUT.value());
					} else if (line.startsWith(Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.value())) {
						printDetails(line, engine, Constants.EMPTY.value(),
								Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.value(),Constants.OUTPUT.value());
					} else if (line.startsWith(Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.value())) {
						printDetails(line, engine, Constants.EMPTY.value(),
								Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.value(),Constants.OUTPUT.value());
					} else if (line.startsWith(Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.value())) {
						printDetails(line, engine, Constants.NOTFOUND.value(),
								Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.value(),Constants.OUTPUT.value());
					}

				} while (!line.startsWith("exit"));

			} catch (Exception e) {
				System.out.println("Error ::" + e.getMessage());
				e.printStackTrace();
			} finally {
				if (scanner != null)
					scanner.close();
			}

		} else if (args != null && args.length == 1) {
			// System.out.println(args[0]);
			try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

				while ((line = br.readLine()) != null) {

					if (line.startsWith(Constants.CREATE_PARKING_LOT.value())) {
						engine = doCPL(line,null);
					} else if (line.startsWith(Constants.PARK.value())) {
						doPark(engine, line,null);
					} else if (line.startsWith(Constants.LEAVE.value())) {
						doLeave(engine, line,null);
					} else if (line.equals(Constants.STATUS.value())) {
						doStatus(engine,null);
					} else if (line.startsWith(Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.value())) {
						printDetails(line, engine, Constants.EMPTY.value(),
								Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.value(),null);
					} else if (line.startsWith(Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.value())) {
						printDetails(line, engine, Constants.EMPTY.value(),
								Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.value(),null);
					} else if (line.startsWith(Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.value())) {
						printDetails(line, engine, Constants.NOTFOUND.value(),
								Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.value(),null);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (args != null && args.length > 1) {
			System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
		}
	}

	private static void doStatus(PLTSEngine engine, String outputprepend) {
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
	}

	private static void doLeave(PLTSEngine engine, String line, String outputprepend) {
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
	}

	private static void doPark(PLTSEngine engine, String line, String outputprepend) {		
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
	}

	private static PLTSEngine doCPL(String line, String outputprepend) {
		PLTSEngine engine;
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
		return engine;
	}

	private static void printDetails(String line, PLTSEngine engine, String msg, String cmdType, String outputprepend) {
		final String[] result = line.split(" ");
		if (engine != null && engine.getParkingLot() != null) {
			List<Slot> list = engine.getParkingLot().getSlot();
			if (list != null && !list.isEmpty()) {
				StringBuilder str = new StringBuilder(20);
				for (Slot slot : list) {
					if (!slot.isAvailable()) {

						if (cmdType.equals(Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.value())) {
							if (slot.getVehicle().getColour().equals(result[1])) {
								str.append(slot.getVehicle().getRegistrationNumber()).append(", ");
							}

						} else if (cmdType.equals(Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.value())) {

							if (slot.getVehicle().getColour().equals(result[1])) {
								str.append(slot.getSlotNumber()).append(", ");
							}

						} else if (cmdType.equals(Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.value())) {

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
					System.out.println(msg);
				}
			}
		}
	}
}