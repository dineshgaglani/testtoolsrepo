/**
 * 
 */
package com.yahoo.weather.client.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Image {
	
	/**
	 * The title of the image, for example "Yahoo! Weather".
	 */
	@XmlElement
	protected String title;
	
	/**
	 * The URL of Yahoo! Weather.
	 */
	@XmlElement
	protected String link;
	
	/**
	 * The URL of the image.
	 */
	@XmlElement
	protected String url;
	
	/**
	 * The width of the image, in pixels.
	 */
	@XmlElement
	protected int width;
	
	/**
	 * The height of the image, in pixels.
	 */
	@XmlElement
	protected int height;

	public Image()
	{}
	
	/**
	 * @param title
	 * @param link
	 * @param url
	 * @param width
	 * @param height
	 */
	public Image(String title, String link, String url, int width,
			int height) {
		this.title = title;
		this.link = link;
		this.url = url;
		this.width = width;
		this.height = height;
	}

	/**
	 * Return the title of the image, for example "Yahoo! Weather".
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the URL of Yahoo! Weather.
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Returns the URL of the image.
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns the width of the image, in pixels.
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the image, in pixels.
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Image [title=");
		builder.append(title);
		builder.append(", link=");
		builder.append(link);
		builder.append(", url=");
		builder.append(url);
		builder.append(", width=");
		builder.append(width);
		builder.append(", height=");
		builder.append(height);
		builder.append("]");
		return builder.toString();
	}
}
