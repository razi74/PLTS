package plts;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import plts.util.Constants;

public class MainTest {
	private static Main main =null;
	
	@BeforeClass
	public static void init() throws IOException {
		main = new Main();
	}

	@Test
	public void testCreateParkingLot() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_CPL.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.CREATED_A_PARKING_LOT_WITH.value()+"10 slots");
		assertEquals(((PLTSEngine)map.get(Constants.ENGINE.value())).getParkingLot().getNumberOfLots(),Integer.valueOf("10"));
	}
	
	@Test
	public void testCreateParkingLotNegative() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_CPL2.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_COMMAND.value());		
	}
	
	@Test
	public void testCreateParkingLotInvalidCommand() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_CPL3.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_COMMAND.value());		
	}
	
	@Test
	public void testCreateParkingLotInvalidCommandLength() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_CPL4.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_NUMBER_OF_ARGUMENTS.value());		
	}
	
	@Test
	public void testPark() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Park.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.ALLOCATED_SLOT_NUMBER.value()+"6");
		assertEquals(((PLTSEngine)map.get(Constants.ENGINE.value())).getParkingLot().getNumberOfLots(),Integer.valueOf("10"));
	}
	
	@Test
	public void testParkWithoutPL() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Park2.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.CREATE_PARKING_LOT.value());		
	}
	
	@Test
	public void testParkInvalidCommand() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Park3.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_COMMAND.value());		
	}
	
	@Test
	public void testParkInvalidCommandLength() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Park4.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_NUMBER_OF_ARGUMENTS.value());		
	}
	
	@Test
	public void testParkPLFull() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Park5.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.SORRY_PARKING_LOT_IS_FULL.value());	
		assertEquals(((PLTSEngine)map.get(Constants.ENGINE.value())).getParkingLot().getNumberOfLots(),Integer.valueOf("2"));
	}
	@Test
	public void testLeave() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Leave.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.ALLOCATED_SLOT_NUMBER.value()+"1");
		assertEquals(((PLTSEngine)map.get(Constants.ENGINE.value())).getParkingLot().getNumberOfLots(),Integer.valueOf("2"));
	}
	
	@Test
	public void testLeaveInvalidCommand() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Leave2.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_COMMAND.value());
	}
	
	@Test
	public void testLeaveInvalidCommandLength() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Leave3.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_NUMBER_OF_ARGUMENTS.value());		
	}
	
	@Test
	public void testLeaveInvalidCommandNegative() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Leave4.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_COMMAND.value());			
	}
	
	@Test
	public void testLeaveDoesntExist() {
		File resourcesDirectory = new File("src/test/resources");
		String temp =resourcesDirectory.getAbsolutePath();
		String [] args = new String[1];
		args[0] = temp+"\\test_Leave5.txt";
		Map<String,Object> map = main.execute(args);
		assertEquals(map.get(Constants.RETURNSTR.value()),Constants.INVALID_COMMAND.value());		
	}
}
