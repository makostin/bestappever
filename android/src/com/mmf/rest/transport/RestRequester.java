package com.mmf.rest.transport;


import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mmf.db.model.Schedule;
import com.mmf.rest.deserializer.InitialDataDeserializer;
import com.mmf.rest.deserializer.ScheduleDeserializer;
import com.mmf.rest.domain.InitialData;
import com.mmf.rest.exceptions.RestException;
import com.mmf.rest.exceptions.UnexpectedResponseCodeException;
import com.mmf.prefs.CredentialsPrefs;
import com.mmf.util.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class RestRequester {

	private static final String REST_API = "rest/bsu/mmf/";
	private static final String TAG = "RestRequester";

	public static final String SERVER_HTTP_DEV = "http://192.168.0.2:8080/";
//	public static final String SERVER_HTTP_DEV = "http://192.168.1.90:8080/";
//	public static final String SERVER_HTTP_DEV = "http://127.0.0.1:8080/";


    private static InputStreamReader getReader(String apiUrl) throws InvalidCredentialsException, RestException {
        InputStreamReader inputStreamReader = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(SERVER_HTTP_DEV + apiUrl);
            request.setHeader(new BasicScheme().authenticate(new UsernamePasswordCredentials(CredentialsPrefs.LoginDefault.get(), CredentialsPrefs.PasswordDefault.get()), request));
            request.setHeader("Content-type", "application/json");
            request.setHeader("Accept-Encoding", "utf-8");
            HttpResponse response = httpClient.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == HttpStatus.SC_UNAUTHORIZED) {
                throw new InvalidCredentialsException("You unauthorized to use service");
            }
            if (responseCode != HttpStatus.SC_OK) {
                throw new UnexpectedResponseCodeException(responseCode);
            }
            HttpEntity entity = response.getEntity();
            inputStreamReader = new InputStreamReader(entity.getContent());
            return inputStreamReader;
        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage());
            throw new RestException(e);
        } catch (ClientProtocolException e) {
            Log.e(TAG, e.getMessage());
            throw new RestException(e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            throw new RestException(e);
        } catch (AuthenticationException e) {
            Log.e(TAG, e.getMessage());
            throw new RestException(e);
        }
    }

    public static boolean login(String login, String password) throws InvalidCredentialsException, RestException {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(SERVER_HTTP_DEV + REST_API + "login");
            request.setHeader(new BasicScheme().authenticate(new UsernamePasswordCredentials(login, password), request));
            request.setHeader("Content-type", "application/json");
            request.setHeader("Accept-Encoding", "utf-8");
            HttpResponse response = httpClient.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == HttpStatus.SC_UNAUTHORIZED) {
                throw new InvalidCredentialsException("You unauthorized to use service");
            }
            if (responseCode != HttpStatus.SC_OK) {
                throw new UnexpectedResponseCodeException(responseCode);
            }
            return true;
        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage());
            throw new RestException(e);
        } catch (ClientProtocolException e) {
            Log.e(TAG, e.getMessage());
            throw new RestException(e);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            throw new RestException(e);
        } catch (AuthenticationException e) {
            Log.e(TAG, e.getMessage());
            throw new RestException(e);
        }
    }

    public static List<Schedule> gesSchedule(int course, int group, String subGroup) throws RestException, InvalidCredentialsException {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        InputStreamReader inputStreamReader = null;
        try{
            StringBuilder params = new StringBuilder("?course=");
            params.append(course);
            params.append("&group=");
            params.append(group);
            params.append("&subGroup=");
            params.append(subGroup);
            inputStreamReader = getReader(REST_API + "schedule" + params.toString());
            if(inputStreamReader != null){
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(List.class, new ScheduleDeserializer());
                Gson gson = gsonBuilder.create();
                Type listType = new TypeToken<List<Schedule>>() {}.getType();
                scheduleList = gson.fromJson(inputStreamReader, listType);
            }
            return scheduleList;
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    Logger.getInstance().error(e);
                }
            }
        }
    }

    public static List<Schedule> gesSchedule(long lecturerId) throws RestException, InvalidCredentialsException {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        InputStreamReader inputStreamReader = null;
        try{
            StringBuilder params = new StringBuilder("?lecturerId=");
            params.append(lecturerId);
            inputStreamReader = getReader(REST_API + "schedule" + params.toString());
            if(inputStreamReader != null){
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(List.class, new ScheduleDeserializer());
                Gson gson = gsonBuilder.create();
                Type listType = new TypeToken<List<Schedule>>() {}.getType();
                scheduleList = gson.fromJson(inputStreamReader, listType);
            }
            return scheduleList;
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    Logger.getInstance().error(e);
                }
            }
        }
    }

    public static InitialData getInitialData() throws InvalidCredentialsException, RestException {
        InitialData data = new InitialData();
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = getReader(REST_API + "initialData");
			if (inputStreamReader != null) {
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.registerTypeAdapter(List.class, new InitialDataDeserializer());
				Gson gson = gsonBuilder.create();
				Type listType = new TypeToken<List<InitialData>>() {}.getType();
                data = gson.fromJson(inputStreamReader, listType);
			}
			return data;
		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					Logger.getInstance().error(e);
				}
			}
		}
    }

}
