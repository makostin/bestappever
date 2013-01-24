package com.mmf.soap.object;

import org.ksoap2.serialization.SoapObject;

/**
 * @author svetlana.voyteh
 * @date: 4/10/12
 */
public class BaseSoap{

    public static final String ID = "id";

    private SoapObject soapObject;
    
    public BaseSoap(SoapObject soapObject) {
        this.soapObject = soapObject;
    }

    protected String getString(String property){
        return soapObject.getProperty(property).toString();
    }
    
    protected int getInt(String property){
        return Integer.parseInt(soapObject.getProperty(property).toString());
    }

    protected Long getLong(String property){
        return Long.parseLong(soapObject.getProperty(property).toString());
    }
}
