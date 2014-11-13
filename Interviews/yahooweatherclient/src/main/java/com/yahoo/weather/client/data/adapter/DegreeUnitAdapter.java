/**
 * 
 */
package com.yahoo.weather.client.data.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.yahoo.weather.client.data.unit.DegreeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Degree unit adapter: f for Fahrenheit or c for Celsius (character)
 *
 */
public class DegreeUnitAdapter extends XmlAdapter<String, DegreeUnit> {

	protected static final String CELSIUS = "c";
	protected static final String FAHRENHEIT = "f";
	
	protected Logger logger = LoggerFactory.getLogger(DegreeUnitAdapter.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DegreeUnit unmarshal(String v) throws Exception {
		if (FAHRENHEIT.equalsIgnoreCase(v)) return DegreeUnit.FAHRENHEIT;
		if (CELSIUS.equalsIgnoreCase(v)) return DegreeUnit.CELSIUS;
		logger.warn("Unknown degree unit \"{}\"", v);		
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String marshal(DegreeUnit v) throws Exception {
		switch (v) {
			case CELSIUS: return CELSIUS;
			case FAHRENHEIT: return FAHRENHEIT;
			default: return "";
		}
	}

}
