package com.mmf.rest.transport;


import com.mmf.db.model.Department;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Schedule;
import com.mmf.db.model.Specialty;
import com.mmf.rest.exceptions.UnexpectedResponseCodeException;
import com.mmf.prefs.CredentialsPrefs;
import com.mmf.rest.transport.http.DataProvider;
import com.mmf.rest.transport.http.HttpDataProvider;
import com.mmf.util.Logger;
import org.apache.http.auth.InvalidCredentialsException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;

public class RestRequester {

	private static final String STATUS_OK = "200";
	private static final String PROJECT_KEY = "projectKey";
	private static final String DATE = "date";
	private static final String AND = "&";
	private static final String EQUALLY = "=";
	private static final String USER = "user";
	private static final String START_SYMBOL = "?";
	private static final String ACTION = "action";
	private static final String CAPTCHA = "captcha";
	private static final String GET_CAPTCHA = "GET_CAPTCHA";
	private static final String CHECK_REQUIRED = "CHECK_REQUIRED";
	private static final String RESET_LOGIN_COUNT = "RESET_LOGIN_COUNT";
	private static final String REST_API = "rest/api/2.0.alpha1/";
	private static final String REST_API_GET_ROLES = "secure/popups/UserProjectRoles.jspa";
	private static final String REST_API_GET_WORKLOGS = "secure/popups/Worklogs.jspa";
	private static final String REST_API_GET_CUSTOMER_ACTIVITIES = "secure/popups/CustomerActivity.jspa";
	private static final String REST_API_GET_SERVER_TIME = "secure/popups/ServerDate.jspa";
	private static final String REST_API_LOGIN_STATUS = "secure/popups/Captcha.jspa";

	private static final int BYTE_ARRAY_SIZE = 10000;

	private static final String TAG = "RestRequester";

	public static final String SERVER_HTTP_DEV = "https://jira.hiqo-solutions.us:8443/";

	// public static final String SERVER_HTTP_DEV =
	// "http://jira45.hiqo-solutions.com:8080/";

	// public static final String SERVER_HTTP_DEV =
	// "http://192.168.1.188:8080/";

	private static String getServerAddress() {
		return SERVER_HTTP_DEV;
	}

	private static DataProvider getDataProvider(String url) throws MalformedURLException {
		return new HttpDataProvider(url);
	}



//	public static void login(UserInfo userInfo)	throws InvalidCredentialsException, RestException {
//		ByteArrayOutputStream os = new ByteArrayOutputStream(10000);
//		try {
//
//			Gson gson = new Gson();
//			String requestData = gson.toJson(userInfo);
//			Log.i(TAG, requestData);
//			String serverUrl = String.format(getServerAddress() + "rest/auth/1/session");
//			Log.i(TAG, "URL: " + serverUrl);
//			DataProvider internetDataProvider = getDataProvider(serverUrl);
//			ByteArrayOutputStream stream = new ByteArrayOutputStream();
//			internetDataProvider.post(userInfo.getUsername(), userInfo.getPassword(), requestData, stream);
//			SessionInfo sessionInfo = gson.fromJson(stream.toString(), SessionInfo.class);
//			CredentialsPrefs.mSessionName.put(sessionInfo.getSession().getName());
//			CredentialsPrefs.mSessionId.put(sessionInfo.getSession().getValue());
//
//		} catch (MalformedURLException e) {
//			Logger.getInstance().error(e);
//			throw new RestException(e);
//		} catch (IOException e) {
//			if ("received authentication challenge is null".equalsIgnoreCase(e.getMessage())) {
//				throw new InvalidCredentialsException("Invalid credentials");
//			} else {
//				Logger.getInstance().warn(e);
//				throw new RestException(e);
//			}
//		} finally {
//			try {
//				os.close();
//			} catch (IOException e) {
//				Logger.getInstance().error(e);
//			}
//		}
//	}





//	public static Collection<IssueComponent> getIssueComponents(String issueKey)
//			throws RestException, InvalidCredentialsException {
//		Collection<IssueComponent> issueComponents = new ArrayList<IssueComponent>();
//		InputStreamReader inputStreamReader = null;
//		try {
//			inputStreamReader = getReader(REST_API + "issue/" + issueKey);
//			if (inputStreamReader != null) {
//				GsonBuilder gsonBuilder = new GsonBuilder();
//				gsonBuilder.registerTypeAdapter(Collection.class, new ComponentIssueTypeDeserializer());
//				Gson gson = gsonBuilder.create();
//				Type collectionType = new TypeToken<Collection<IssueComponent>>() {}.getType();
//				issueComponents = gson.fromJson(inputStreamReader, collectionType);
//			}
//			return issueComponents;
//		} catch (IOException e) {
//			Logger.getInstance().error(e);
//			throw new RestException(e);
//		} finally {
//			if (inputStreamReader != null) {
//				try {
//					inputStreamReader.close();
//				} catch (IOException e) {
//					Logger.getInstance().error(e);
//				}
//			}
//		}
//	}


	private static InputStreamReader getReader(String apiUrl) throws InvalidCredentialsException, IOException, UnexpectedResponseCodeException {
		ByteArrayOutputStream outputStream = null;
		InputStreamReader inputStreamReader = null;
		try {
			DataProvider internetDataProvider = getDataProvider(getServerAddress() + apiUrl);
			outputStream = new ByteArrayOutputStream(BYTE_ARRAY_SIZE);
			internetDataProvider.get(CredentialsPrefs.Login.get(),	CredentialsPrefs.Password.get(), outputStream);
			inputStreamReader = new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray()));
			return inputStreamReader;
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					Logger.getInstance().error(e);
				}
			}
		}
	}

    public static List<Schedule> gesSchedule(String course, String group, String subGroup) {
        // todo
        return null;
    }

    public static List<Specialty> getSpecialities() {
        // todo
        return null;
    }

    public static List<Department> getDepartments() {
        // todo
        return null;
    }

    public static List<Lecturer> getLecturers() {
        // todo
        return null;
    }
}
