/**
 * 
 */
package com.yahoo.weather.client.data;

import com.yahoo.weather.client.data.adapter.RFC822DateAdapter;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement
public class Item {
	
	/**
	 * The forecast title and time, for example "Conditions for New York, NY at 1:51 pm EST".
	 */
	@XmlElement
	protected String title;
	
	/**
	 * The Yahoo! Weather URL for this forecast.
	 */
	@XmlElement
	protected String link;
	
	/**
	 * A simple summary of the current conditions and tomorrow's forecast, in HTML format, including a link to Yahoo! Weather for the full forecast.
	 */
	@XmlElement
	protected String description;
	
	/**
	 * Unique identifier for the forecast, made up of the location ID, the date, and the time.
	 */
	@XmlElement
	protected String guid;
	
	/**
	 * The date and time this forecast was posted.
	 */
	@XmlElement
	@XmlJavaTypeAdapter(RFC822DateAdapter.class)
	protected Date pubDate;
	
	/**
	 * The latitude of the location.
	 */
	@XmlElement(namespace="http://xml.weather.yahoo.com/ns/rss/1.0", name="lat")
	protected float geoLat;
	
	/**
	 * The longitude of the location.
	 */
	@XmlElement(namespace="http://www.w3.org/2003/01/geo/wgs84_pos#", name="long")
	protected float geoLong;
	
	/**
	 * The current weather conditions.
	 */
	@XmlElement(namespace="http://xml.weather.yahoo.com/ns/rss/1.0")
	protected Condition condition;
	
	/**
	 * The weather forecast for specific days.
	 */
	@XmlElement(namespace="http://xml.weather.yahoo.com/ns/rss/1.0", name="forecast")
	protected List<Forecast> forecasts;
	
	public Item()
	{}

	/**
	 * Returns the forecast title and time, for example "Conditions for New York, NY at 1:51 pm EST".
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the Yahoo! Weather URL for this forecast.
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Returns a simple summary of the current conditions and tomorrow's forecast, in HTML format, including a link to Yahoo! Weather for the full forecast.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns an unique identifier for the forecast, made up of the location ID, the date, and the time.
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Returns the date and time this forecast was posted.
	 * @return the pubDate
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * Returns the latitude of the location.
	 * @return the geoLat
	 */
	public float getGeoLat() {
		return geoLat;
	}

	/**
	 * Returns the longitude of the location.
	 * @return the geoLong
	 */
	public float getGeoLong() {
		return geoLong;
	}

	/**
	 * Returns the current weather conditions.
	 * @return the condition
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * Returns the weather forecast for specific days.
	 * @return the forecasts
	 */
	public List<Forecast> getForecasts() {
		return forecasts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [title=");
		builder.append(title);
		builder.append(", link=");
		builder.append(link);
		builder.append(", description=");
		builder.append(description);
		builder.append(", guid=");
		builder.append(guid);
		builder.append(", pubDate=");
		builder.append(pubDate);
		builder.append(", geoLat=");
		builder.append(geoLat);
		builder.append(", geoLong=");
		builder.append(geoLong);
		builder.append(", condition=");
		builder.append(condition);
		builder.append(", forecasts=");
		builder.append(forecasts);
		builder.append("]");
		return builder.toString();
	}
}
