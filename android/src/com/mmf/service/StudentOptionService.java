package com.mmf.service;

import com.mmf.R;
import com.mmf.db.DaoLayerException;
import com.mmf.db.DaoObjectsFactory;
import com.mmf.db.dao.StudentOptionDao;
import com.mmf.db.model.StudentOption;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 2/12/12
 */
public class StudentOptionService {

    private static final String URL = "http://192.168.1.99:8080/service/authenticate?wsdl";
    private static final String SOAP_ACTION = "";
    private static final String METHOD_AUTHENTICATE = "authenticate";
    private static final String NAMESPACE = "http://authenticate";
    private SoapObject resultRequestSOAP = null;


    private final StudentOptionDao studentOptionDao = DaoObjectsFactory.getInstance().getStudentOptionDao();

    public StudentOptionService() {
    }

    public boolean isCurrentExist() throws BusinessLayerException {
        return getCurrent() != null;
    }

    public StudentOption getCurrent() throws BusinessLayerException {
        try {
            return studentOptionDao.getCurrent();
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }


    public void removeCurrent() throws BusinessLayerException {
        try{
            StudentOption studentOption = studentOptionDao.getCurrent();
            if (studentOption != null){
                studentOption.setCurrent(false);
                studentOptionDao.update(studentOption);
            }

        } catch (DaoLayerException dle){
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void setCurrent(int course, String group) throws BusinessLayerException {
        try{
            StudentOption studentOption = studentOptionDao.getCurrent();
            if (studentOption != null){
                studentOption.setCurrent(false);
                studentOptionDao.update(studentOption);
            }

            studentOption = studentOptionDao.get(course, group);
            studentOption.setCurrent(true);
            studentOptionDao.update(studentOption);
        } catch (DaoLayerException dle){
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public boolean isLessonsUpdate(int week) throws BusinessLayerException {
        try {
            return studentOptionDao.isLessonsUpdate(week);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void setCurrentWeek(int week) throws BusinessLayerException {
        try {
            StudentOption studentOption = studentOptionDao.getCurrent();
            studentOption.setWeek(week);
            studentOptionDao.update(studentOption);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }


//    public List<StudentOption> getDownloaded() throws BusinessLayerException {
//        try {
//            return studentOptionDao.getDownloaded();
//        } catch (DaoLayerException dle) {
//            Logger.getInstance().error(dle);
//            throw new BusinessLayerException(dle);
//        }
//    }

    public boolean isLogin() throws BusinessLayerException {
        try {
            return studentOptionDao.isLogin();
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void login(String login, String password) throws BusinessLayerException {
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_AUTHENTICATE);
            // Add the input required by web service
            request.addProperty("login", login);
            request.addProperty("password", password);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            // Make the soap call.
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the SoapResult from the envelope body.
            resultRequestSOAP = (SoapObject) envelope.bodyIn;
            if(resultRequestSOAP.getPropertyCount() == 0){
                throw new DaoLayerException(StringUtils.getStringByResource(R.string.validate_messages_incorrect_login_or_password));
            }
            SoapObject userObject = (SoapObject) resultRequestSOAP.getProperty(0);
            int course = (Integer.parseInt(userObject.getProperty("course").toString()));
            String group = (userObject.getProperty("subGroup").toString());
            String user = (userObject.getProperty("name").toString());

            StudentOption studentOption = studentOptionDao.get(course, group);
            studentOption.setName(user);
            studentOption.setLogin(true);
            studentOptionDao.update(studentOption);

        } catch (XmlPullParserException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        } catch (IOException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void logout() throws BusinessLayerException {
        try {
            StudentOption studentOption = studentOptionDao.getLogin();
            studentOption.setName("");
            studentOption.setLogin(false);
            studentOptionDao.update(studentOption);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }
}
