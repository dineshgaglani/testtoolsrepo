/**
 * 
 */
package com.yahoo.weather.client.data.adapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.yahoo.weather.client.data.unit.Time;
import com.yahoo.weather.client.data.unit.TimeConvention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The time is a string in a local time format of "h:mm am/pm", for example "7:02 am" (string)
 */
public class TimeAdapter extends XmlAdapter<String, Time> {

	protected static final String TIME_PATTERN = "(\\d?\\d):(\\d\\d)\\s(am|pm)";
	protected static final Pattern PATTERN = Pattern.compile(TIME_PATTERN);

	protected Logger logger = LoggerFactory.getLogger(TimeAdapter.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Time unmarshal(String v) throws Exception {
		if (v != null) {

			Matcher matcher = PATTERN.matcher(v);
			if (matcher.groupCount()==3) {
				matcher.find();
				try {
					String hoursToken = matcher.group(1);
					int hours = Integer.parseInt(hoursToken);

					String minutesToken = matcher.group(2);
					int minutes = Integer.parseInt(minutesToken);

					String conventionToken = matcher.group(3);
					TimeConvention convention = TimeConvention.valueOf(conventionToken.toUpperCase());

					return new Time(hours, minutes, convention);
				} catch(NumberFormatException nfe)
				{
					logger.warn("Error converting time value "+v, nfe);
				}
			}
		}
		logger.warn("Unparsable time value \"{}\"", v);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String marshal(Time v) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(v.getHours());
		sb.append(':');
		sb.append(v.getMinutes());
		sb.append(' ');
		sb.append(v.getConvention().toString().toLowerCase());
		return sb.toString();
	}

}
