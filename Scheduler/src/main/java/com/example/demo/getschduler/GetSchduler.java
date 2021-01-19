package com.example.demo.getschduler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GetSchduler {
	
	
	//@Scheduled(fixedRate = 6000000)
	public static void MyGETRequest() throws IOException, ParseException {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
          Date now = new Date();
          String strDate = sdf.format(now);
          System.out.println("Fixed Rate scheduler:: " + strDate);
          String inline = "";
       try {
    	   
    	   URL url = new URL("https://api.first.org/data/v1/countries");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			

			if(responseCode != 200)
			throw new RuntimeException("HttpResponseCode: " +responseCode);
			
			
			else
			{
				//Scanner functionality will read the JSON data from the stream
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
				inline+=sc.nextLine();
				}
				System.out.println("\nJSON Response in String format");
				System.out.println(inline);
				//Close the stream when reading the data has been finished
				sc.close();
			}

			//JSONParser reads the data from string object and break each data into key value pairs
			JSONParser parse = new JSONParser();
			//Type caste the parsed json data in json object
			JSONObject jsonResponse = (JSONObject)parse.parse(inline);
			//Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
			JSONObject jsonData = (JSONObject) jsonResponse.get("data");

			Set<String> jsonDataKeySet = jsonData.keySet();
			Iterator<String> keysetItr =   jsonDataKeySet.iterator();

			while(keysetItr.hasNext()) {
			String currKey = (String)keysetItr.next();
			JSONObject currObj =  (JSONObject)jsonData.get(currKey);
			String currCountry = (String)currObj.get("country");
			String currRegion = (String)currObj.get("region");
			
			System.out.println("ID="+currKey+" Country="+currCountry+" Region="+currRegion);
			}
			
			connection.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}

