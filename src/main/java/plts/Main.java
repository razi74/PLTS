package plts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import plts.command.CreatePLCommand;
import plts.command.FetchDetailsCommand;
import plts.command.InputCommand;
import plts.command.LeaveCommand;
import plts.command.ParkCommand;
import plts.command.StatusCommand;
import plts.util.Constants;

public class Main {
	
	public  Map<String,Object> execute(String[] args)
	{
		Map<String, InputCommand> commandMap = new HashMap<>(7);
		commandMap.put(Constants.CREATE_PARKING_LOT.value(), new CreatePLCommand());
		commandMap.put(Constants.PARK.value(), new ParkCommand());
		commandMap.put(Constants.LEAVE.value(), new LeaveCommand());
		commandMap.put(Constants.STATUS.value(), new StatusCommand());
		FetchDetailsCommand fetchcmd = new FetchDetailsCommand();
		commandMap.put(Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.value(), fetchcmd);
		commandMap.put(Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.value(), fetchcmd);
		commandMap.put(Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.value(), fetchcmd);
		Map<String,Object> map = null ;
		PLTSEngine engine = null;
		String line;
		if (args != null && args.length == 0) {
			Scanner scanner = new Scanner(System.in);
			try {
				do {
					System.out.println();
					System.out.println("Input: ");
					line = scanner.nextLine();
					if (line != null && !line.isEmpty()) {
						final String[] result = line.split(" ");
						if (result.length > 0 && !result[0].equals("exit")) {
							InputCommand icmd = commandMap.get(result[0]);
							if(icmd==null)
							{
								map = new HashMap<>();
								map.put(Constants.RETURNSTR.value(),Constants.INVALID_COMMAND.value());
								return map;
							}
							Map<String,Object> inputmap = new HashMap<>(3);
							inputmap.put(Constants.INPUTLINE.value(),line);
							inputmap.put(Constants.OUTPUTPREPENDLINE.value(), Constants.OUTPUT.value());
						    inputmap.put(Constants.ENGINE.value(),engine);
						    map = icmd.execute(inputmap);
						    if(map!=null) {
						    	engine = (PLTSEngine) map.get(Constants.ENGINE.value());
						    }
						} else {
							System.exit(0);
						}
					} else {
						System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
					}
				} while (true);

			} catch (Exception e) {
				System.out.println("Error ::" + e.getMessage());
				e.printStackTrace();
			} finally {
				if (scanner != null)
					scanner.close();
			}

		} else if (args != null && args.length == 1) {// fileinput mode
			try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
				while ((line = br.readLine()) != null) {
					if (line != null && !line.isEmpty()) {
						final String[] result = line.split(" ");
						if (result.length > 0 && !result[0].equals("exit")) {
							InputCommand icmd = commandMap.get(result[0]);
							if(icmd==null)
							{
								map = new HashMap<>();
								map.put(Constants.RETURNSTR.value(),Constants.INVALID_COMMAND.value());
								return map;
							}
							Map<String,Object> inputmap = new HashMap<>(3);
							inputmap.put(Constants.INPUTLINE.value(),line);
							inputmap.put(Constants.OUTPUTPREPENDLINE.value(), null);
						    inputmap.put(Constants.ENGINE.value(),engine);
						    map = icmd.execute(inputmap);
						    if(map!=null) {
						    	engine = (PLTSEngine) map.get(Constants.ENGINE.value());
						    }
						}
					} else {
						System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (args != null && args.length > 1) {
			System.out.println(Constants.INVALID_NUMBER_OF_ARGUMENTS.value());
		}
		return map;	
	}
	public static void main(String[] args) {
		Main main = new Main();
		main.execute(args);		
	}
}