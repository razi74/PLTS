package plts.model;

import java.time.Instant;

public final class Ticket {
		
	private String ticketNumber;
	private Slot slot;
	private Instant inTimestamp;
	private Instant outTimestamp;
	
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public Slot getSlot() {
		return slot;
	}
	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	public Instant getInTimestamp() {
		return inTimestamp;
	}
	public void setInTimestamp(Instant inTimestamp) {
		this.inTimestamp = inTimestamp;
	}
	public Instant getOutTimestamp() {
		return outTimestamp;
	}
	public void setOutTimestamp(Instant outTimestamp) {
		this.outTimestamp = outTimestamp;
	}	
	
}
