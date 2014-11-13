/**
 * 
 */
package com.yahoo.weather.client.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rss {

	@XmlElement
	protected Channel channel;
	
	public Rss()
	{}

	/**
	 * Returns the channel.
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}
	
}
