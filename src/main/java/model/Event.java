package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Event {
	
	private int eventId;
	private String eventName;
	private String location;
	private String date;
	private String time;
	@Override
	public String toString() {
		return "eventId=" + eventId + " 	eventName=" + eventName + "		location=" + location + " 	date=" + date
				+ " 	time=" + time ;
	}
}




