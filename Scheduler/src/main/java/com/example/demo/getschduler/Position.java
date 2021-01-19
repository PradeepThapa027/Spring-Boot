package com.example.demo.getschduler;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;




@Component
public class Position {
	
	
	//@Scheduled(fixedRate = 6000000)
	public static Object myGetRequest() throws IOException, ParseException {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
          Date now = new Date();
          String strDate = sdf.format(now);
          System.out.println("Fixed Rate scheduler:: " + strDate);
          String inline = "";
       try {
    	   	
			  String DEFAULT_USER="readdata"; 
			  String DEFAULT_PASS="readdata"; 
			  HttpGet request = new HttpGet("http://13.232.194.90:8888/personnel/api/positions/?format=json");
			  
			  
			  String auth = DEFAULT_USER + ":" + DEFAULT_PASS; 
			  byte[] encodedAuth = Base64.encodeBase64( auth.getBytes(StandardCharsets.ISO_8859_1)); 
			  String authHeader = "Basic " + new String(encodedAuth);
			  request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			  
			  HttpClient client = HttpClientBuilder.create().build(); 
			  HttpResponse response = client.execute(request);
			  
			  //int statusCode = response.getStatusLine().getStatusCode(); 
			  String responeData = EntityUtils.toString(response.getEntity());
			  
			
           	  System.out.println(responeData);
           	  
  			JSONParser parse = new JSONParser();
  			JSONObject jsonResponse = (JSONObject)parse.parse(responeData);
  			//1.Json Object
  			//JSONObject jsonData = (JSONObject) jsonResponse.get("data");
  			
  			//2.Json array
  			JSONArray jsonArray = (JSONArray) jsonResponse.get("data");
  			
           	 for(int index=0; index< jsonArray.size(); index++) {
           		 
           		 JSONObject currObj = (JSONObject) jsonArray.get(index);
           		 //System.out.println("Id = "+currObj.get("id")+" Position = "+currObj.get("position_name"));
           		//System.out.println(currObj.get("position_name"));
           		 
           		System.out.println("Position Code = "+currObj.get("position_code")+" Position Name= "+currObj.get("position_name"));
           	 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return inline;
	}
	
	public static JSONArray positionDetails() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Rate scheduler:: " + strDate);
        String inline = "";
     try {
  	   	
			  String DEFAULT_USER="readdata"; 
			  String DEFAULT_PASS="readdata"; 
			  HttpPost request = new HttpPost("http://13.232.194.90:8888/personnel/api/positions/?format=json");
			  //HttpGet request = new HttpGet("http://13.232.194.90:8888/personnel/api/positions/?format=json");
			  
			  String auth = DEFAULT_USER + ":" + DEFAULT_PASS; 
			  byte[] encodedAuth = Base64.encodeBase64( auth.getBytes(StandardCharsets.ISO_8859_1)); 
			  String authHeader = "Basic " + new String(encodedAuth);
			  request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
			  
			  HttpClient client = HttpClientBuilder.create().build(); 
			  HttpResponse response = client.execute(request);
			  
			  //int statusCode = response.getStatusLine().getStatusCode(); 
			  String responeData = EntityUtils.toString(response.getEntity());
			  
			
         	  System.out.println(responeData);
         	  
			JSONParser parse = new JSONParser();
			JSONObject jsonResponse = (JSONObject)parse.parse(responeData);
			//1.Json Object
			//JSONObject jsonData = (JSONObject) jsonResponse.get("data");
			
			//2.Json array
			JSONArray jsonArray = (JSONArray) jsonResponse.get("data");
			return jsonArray;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return null;
		
	}
}
