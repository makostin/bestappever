package com.mmf.business.domain.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: svetlana.voyteh
 * Date: 21.03.13
 */
public class TimeAdapter extends XmlAdapter<String, Date>{

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }
}
