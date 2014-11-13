/**
 *
 */
package com.yahoo.weather.client.data.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerAdapter extends XmlAdapter<String, Integer> {

    protected Logger logger = LoggerFactory.getLogger(IntegerAdapter.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String marshal(Integer v) throws Exception {
        return String.valueOf(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer unmarshal(String v) throws Exception {
    	if (v == null || v.isEmpty()) return null;
    	return Integer.parseInt(v);
    }

}
