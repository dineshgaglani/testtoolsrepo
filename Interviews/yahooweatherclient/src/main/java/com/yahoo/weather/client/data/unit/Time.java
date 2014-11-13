/**
 * 
 */
package com.yahoo.weather.client.data.unit;

public class Time {
	
	protected int hours;
	protected int minutes;
	protected TimeConvention convention;
	
	/**
	 * @param hours
	 * @param minutes
	 * @param convention
	 */
	public Time(int hours, int minutes, TimeConvention convention) {
		this.hours = hours;
		this.minutes = minutes;
		this.convention = convention;
	}

	/**
	 * Returns the hours.
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Returns the minutes.
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Returns the time convention.
	 * @return the convention
	 */
	public TimeConvention getConvention() {
		return convention;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Time [hours=");
		builder.append(hours);
		builder.append(", minutes=");
		builder.append(minutes);
		builder.append(", convention=");
		builder.append(convention);
		builder.append("]");
		return builder.toString();
	}
}
