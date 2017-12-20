package plts.util;

public enum Constants {
	
	TIMESTAMPSTRING("yyyyMMddHHmmssSSSSS"),	
	EMPTY(""),	
	SPACE(" "),
	TAB("\t"),
	NOTFOUND("Not found"),
	INTERACTIVEMODE("Interactive"),
	INPUTFILEMODE("InputFile"),
	SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number"),
	REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
	SLOT_NUMBERS_FOR_CARS_WITH_COLOUR( "slot_numbers_for_cars_with_colour"),
	STATUS( "status"),
	LEAVE("leave"),
	CREATE_PARKING_LOT ( "create_parking_lot"),
	PARK("park"),
	INVALID_NUMBER_OF_ARGUMENTS("Invalid number of arguments"),
	INVALID_COMMAND("Invalid command"),
	OUTPUT ("Output: "),
	INPUTLINE("inputline"),
	OUTPUTPREPENDLINE("outputprependline"),
	ENGINE("engine"),
	CREATED_A_PARKING_LOT_WITH ("Created a parking lot with "), 
	RETURNSTR("returnstr"),
	SORRY_PARKING_LOT_IS_FULL("Sorry, parking lot is full"),
	ALLOCATED_SLOT_NUMBER ("Allocated slot number: ");
	private String value;
	
		
	private Constants(String value)
	{
		this.value = value;
	}
	public String value()
	{
		return value;
	}
}
