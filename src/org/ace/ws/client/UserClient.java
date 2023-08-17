package org.ace.ws.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserClient {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/ggiwsp/ws/getBranchList");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		Gson gson = new GsonBuilder().create();
		// User user = new User();
		// user.setUsercode("abc");
		// user.setPassword("epl");
		// String requestMessage = gson.toJson(user);
		OutputStream outputStream = conn.getOutputStream();
		System.out.println("Request Message");
		// outputStream.write(requestMessage.getBytes());
		outputStream.flush();
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
		System.out.println("Response Message");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String output;
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
		conn.disconnect();
	}

}
