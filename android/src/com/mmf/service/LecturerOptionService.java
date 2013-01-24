package com.mmf.service;

import com.mmf.R;
import com.mmf.db.DaoLayerException;
import com.mmf.db.DaoObjectsFactory;
import com.mmf.db.dao.LecturerOptionDao;
import com.mmf.db.model.LecturerOption;
import com.mmf.db.model.StudentOption;
import com.mmf.util.Logger;
import com.mmf.util.StringUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 4/2/12
 */
public class LecturerOptionService {

    private static final String URL = "http://192.168.1.99:8080/service/lecturers?wsdl";
    private static final String SOAP_ACTION = "";
    private static final String METHOD_AUTHENTICATE = "getAllLecturers";
    private static final String NAMESPACE = "http://lecturers";
    private SoapObject resultRequestSOAP = null;

    private final LecturerOptionDao lecturerOptionDao = DaoObjectsFactory.getInstance().getLecturerOptionDao();

    public boolean isCurrentExist() throws BusinessLayerException {
        return getCurrent() != null;
    }

    public LecturerOption getCurrent() throws BusinessLayerException {
        try {
            return lecturerOptionDao.getCurrent();
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void setCurrent(String department, String lecturer) throws BusinessLayerException {
        try{
            LecturerOption lecturerOption = lecturerOptionDao.getCurrent();
            if (lecturerOption != null){
                lecturerOption.setCurrent(false);
                lecturerOptionDao.update(lecturerOption);
            }

            lecturerOption = lecturerOptionDao.get(department, lecturer);
            lecturerOption.setCurrent(true);
            lecturerOptionDao.update(lecturerOption);
        } catch (DaoLayerException dle){
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void removeCurrent() throws BusinessLayerException {
        try{
            LecturerOption lecturerOption = lecturerOptionDao.getCurrent();
            if (lecturerOption != null){
                lecturerOption.setCurrent(false);
                lecturerOptionDao.update(lecturerOption);
            }

        } catch (DaoLayerException dle){
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void setAllLecturers() throws BusinessLayerException {
        LecturerOption lecturer;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_AUTHENTICATE);
            // Add the input required by web service
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            // Make the soap call.
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the SoapResult from the envelope body.
            resultRequestSOAP = (SoapObject) envelope.bodyIn;

            int count = resultRequestSOAP.getPropertyCount();
            for (int i=0; i < count; i++){
                SoapObject userObject = (SoapObject) resultRequestSOAP.getProperty(i);
                Long id = (Long.parseLong(userObject.getProperty("id").toString()));
                String name = (userObject.getProperty("name").toString());
                String department = (userObject.getProperty("department").toString());

                lecturer = new LecturerOption();
                lecturer.setLecturerId(id);
                lecturer.setName(name);
                lecturer.setDepartment(department);
                lecturer.setCurrent(false);
                lecturer.setDownload(false);
                lecturer.setLogin(false);
                lecturer.setWeek(0);

                lecturerOptionDao.insert(lecturer);
            }


        } catch (XmlPullParserException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        } catch (IOException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        } catch (DaoLayerException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        }
    }

    public String[] getAllDepartments() throws BusinessLayerException {
        try {
            List<String> departmentList = lecturerOptionDao.getAllDepartments();
            return departmentList.toArray(new String[departmentList.size()]);
        } catch (DaoLayerException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        }
    }

    public String[] getLecturersOfDepartment(String department) throws BusinessLayerException {
        try {
            List<String> lecturerList = lecturerOptionDao.getLecturersOfDepartment(department);
            return lecturerList.toArray(new String[lecturerList.size()]);
        } catch (DaoLayerException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        }
    }

    public boolean isEmpty() throws BusinessLayerException {
        try {
            return lecturerOptionDao.isEmpty();
        } catch (DaoLayerException e) {
            Logger.getInstance().error(e);
            throw new BusinessLayerException(e);
        }
    }

    public boolean isLessonsUpdate(int currentWeek) throws BusinessLayerException {
        try {
            return lecturerOptionDao.isLessonsUpdate(currentWeek);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }

    public void setCurrentWeek(int currentWeek) throws BusinessLayerException {
        try {
            LecturerOption lecturerOption = lecturerOptionDao.getCurrent();
            lecturerOption.setWeek(currentWeek);
            lecturerOptionDao.update(lecturerOption);
        } catch (DaoLayerException dle) {
            Logger.getInstance().error(dle);
            throw new BusinessLayerException(dle);
        }
    }
}
