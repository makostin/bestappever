package com.mmf.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * @author svetlana.voyteh
 * @date: 4/10/12
 */
public class RequestSoap extends SoapObject{
    
    private String soapAction;
    private SoapObject resultRequestSOAP; 
    private HttpTransportSE androidHttpTransport;

    public RequestSoap(String namespace, String methodName, String url, String soapAction) {
        super(namespace, methodName);
        androidHttpTransport = new HttpTransportSE(url);
        this.soapAction = soapAction;
    }
    
    public void request(String[] properties, Object[] values) throws RequestSoapException, IOException, XmlPullParserException {
        if (properties.length != values.length)
            throw new RequestSoapException("Incorrect parameters.");
        
        for(int i=0; i < properties.length; i++){
            addProperty(properties[i], values[i]);
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(this);
        androidHttpTransport.call(soapAction, envelope);
        resultRequestSOAP = (SoapObject) envelope.bodyIn;
    }
    
    public int getResultCount(){
        return resultRequestSOAP.getPropertyCount();
    }

    public SoapObject getResult(int index){
        return (SoapObject) resultRequestSOAP.getProperty(index);
    }
}
