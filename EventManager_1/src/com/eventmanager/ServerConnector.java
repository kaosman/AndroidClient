package com.eventmanager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class ServerConnector {

	public String login(String username, String password) {

		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL("http://10.0.2.2:8888/login");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");

			connection.setDoInput(true);
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

			String credentials = "<credentials>" + "<username>" + username
					+ "</username>" + "<password>" + password + "</password>"
					+ "</credentials>";
			wr.writeBytes(credentials);
			wr.flush();
			wr.close();

			InputStream is = connection.getInputStream();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}
	}
	
	public void createEvent(){
		
	}
	
	public void createGroup(){
		
	}
	
	public void createUser(){
		
	}
	
	public InputStream getEventDescription(String eventName){
		
		URL url;
		HttpURLConnection connection = null;
		
		try {
			
			url = new URL("http://10.0.2.2:8888/getEventDescription");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");

			connection.setDoInput(true);
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

			wr.writeBytes(eventName);
			wr.flush();
			wr.close();

			InputStream is = connection.getInputStream();
			
			return is;
			/*BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();*/

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}
	
	
	}
	public InputStream getGroupMembers(String groupName){

		URL url;
		HttpURLConnection connection = null;
		
		try {
			
			url = new URL("http://10.0.2.2:8888/getGroupDescription");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");

			connection.setDoInput(true);
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

			wr.writeBytes(groupName);
			wr.flush();
			wr.close();

			InputStream is = connection.getInputStream();
			
			return is;
			/*BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();*/

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}
	
	}
}
