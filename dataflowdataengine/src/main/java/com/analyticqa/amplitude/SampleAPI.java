package com.analyticqa.amplitude;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class SampleAPI {
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
	
	// Customer ID
    final String customerKey = "";
    // Customer secret
    final String customerSecret = "";

    // Concatenate customer key and customer secret and use base64 to encode the concatenated string
    String plainCredentials = customerKey + ":" + customerSecret;
    String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
    // Create authorization header
    String authorizationHeader = "Basic " + base64Credentials;

    HttpClient client = HttpClient.newHttpClient();

    // Create HTTP request object
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://amplitude.com/api/2/export?start=20150201T05&end=20150203T20"))
            .GET()
            .header("Authorization", authorizationHeader)
            .header("Content-Type", "application/json")
            .build();
    // Send HTTP request
    HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

    System.out.println(response);
	}
	
	public static String amplitudeResponse() throws Exception, InterruptedException {
		
		// Customer ID
	    final String customerKey = "";
	    // Customer secret
	    final String customerSecret = "";

	    // Concatenate customer key and customer secret and use base64 to encode the concatenated string
	    String plainCredentials = customerKey + ":" + customerSecret;
	    String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
	    // Create authorization header
	    String authorizationHeader = "Basic " + base64Credentials;

	    HttpClient client = HttpClient.newHttpClient();

	    // Create HTTP request object
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create("https://amplitude.com/api/2/export?start=20150201T05&end=20150203T20"))
	            .GET()
	            .header("Authorization", authorizationHeader)
	            .header("Content-Type", "application/json")
	            .build();
	    // Send HTTP request
	    HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

	    System.out.println(response);
		return response.toString();
	}
	
}
