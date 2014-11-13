/**
 *
 */
package com.yahoo.weather.client.data.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 */
public class RFC822DateAdapter extends XmlAdapter<String, Date> {

    public static final SimpleDateFormat rfc822DateFormats[] = new SimpleDateFormat[]{
            new SimpleDateFormat("EEE, d MMM yy HH:mm:ss z", Locale.US),
            new SimpleDateFormat("EEE, d MMM yy HH:mm z", Locale.US),
            new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US),
            new SimpleDateFormat("EEE, d MMM yyyy HH:mm z", Locale.US),
            new SimpleDateFormat("d MMM yy HH:mm z", Locale.US),
            new SimpleDateFormat("d MMM yy HH:mm:ss z", Locale.US),
            new SimpleDateFormat("d MMM yyyy HH:mm z", Locale.US),
            new SimpleDateFormat("d MMM yyyy HH:mm:ss z", Locale.US),
            //exception to RFC 822 format used by Yahoo "Thu, 22 Dec 2011 1:50 pm CET"
            new SimpleDateFormat("EEE, d MMM yyyy HH:mm a z", Locale.US)};

    protected Logger logger = LoggerFactory.getLogger(RFC822DateAdapter.class);

    protected SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yy HH:mm:ss z", Locale.US);

    /**
     * {@inheritDoc}
     */
    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date unmarshal(String v) throws Exception {

        for (SimpleDateFormat format : rfc822DateFormats) {
            try {
                return format.parse(v);
            } catch (Exception e) {
            }//skipping exception
        }
        logger.warn("Unknown date format \"{}\"", v);
        return null;
    }

}
