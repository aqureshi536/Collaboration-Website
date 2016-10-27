package com.ahmad.viewmodel;

public class Calendar{
	private String eventAt;
	private String hour;
	private String min;
	public String getEventAt() {
		return eventAt;
	}
	public void setEventAt(String eventAt) {
		this.eventAt = eventAt;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	@Override
	public String toString() {
		return "Calendar [eventAt=" + eventAt + ", hour=" + hour + ", min=" + min + "]";
	}
	
	
	
	
}
