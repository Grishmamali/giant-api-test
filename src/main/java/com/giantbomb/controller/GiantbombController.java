package com.giantbomb.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giantbomb.model.Result;
import com.giantbomb.model.Root;

@Controller
public class GiantbombController {
    @Value("${spring.application.name}")
    String appName;

    private static final Logger LOGGER = Logger.getLogger(GiantbombController.class.getName());
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String submit(@RequestParam("search") String search, Model model ) {
       
    	try {
			Root root = getSearchedGames(search);
			if(root!=null)
				model.addAttribute("root", root);  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "search";
    }
    
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(@RequestParam("guid") String guid ,Model model ) {
       
    	try {
			Root game = getGameById(guid);
			if(game!=null) {
				Result result = game.results.get(0);
				model.addAttribute("game", result); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "checkout";
    }
    
    
private Root getGameById(String guid)  throws IOException{
	// Get 10th record data
	
	
    String baseUrl = "https://www.giantbomb.com/api/game/"+guid+"?api_key=8462b4ddca61da729043c79db2c50f63710cf411&format=json&field_list=genres,name";
    URL getUrl = new URL(baseUrl);
       
   HttpURLConnection conection = (HttpURLConnection) getUrl.openConnection();
   
   // Set request method
   conection.setRequestMethod("GET");
   conection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
   conection.setRequestProperty("Accept", "*/*");
   conection.setRequestProperty("Cache-Control", "no-cache");
   conection.setRequestProperty("Cookie", "device_view=full; sptg=%5B%5D");
   conection.setRequestProperty("User-Agent", "PostmanRuntime/7.29.2");
   

   // Getting response code
   int responseCode = conection.getResponseCode();

   // If responseCode is 200 means we get data successfully
   if (responseCode == HttpURLConnection.HTTP_OK) {
       BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
       StringBuffer jsonResponseData = new StringBuffer();
       String readLine = null;
       
       // Append response line by line
       while ((readLine = in.readLine()) != null) {
       	System.out.println("*******************"+readLine);
           jsonResponseData.append(readLine);
       } 
       
       in.close();
       // Print result in string format
       System.out.println(responseCode);
       System.out.println("******************"+jsonResponseData.toString());
       System.out.println("JSON String Data **** " + jsonResponseData.toString());
       ObjectMapper obj = new ObjectMapper();
       Root root = obj.readValue(jsonResponseData.toString(), Root.class);
       LOGGER.info("***************"+root);
       return root;
   } else {
       System.out.println(responseCode);
       return null;
   }

	}

public Root getSearchedGames(String search) throws IOException {
        
        // Get 10th record data
	
	
	     String baseUrl = "https://www.giantbomb.com/api/search/?api_key=8462b4ddca61da729043c79db2c50f63710cf411&format=json&query="+search+"&resources=game";
	     URL getUrl = new URL(baseUrl);
            
        HttpURLConnection conection = (HttpURLConnection) getUrl.openConnection();
        
        // Set request method
        conection.setRequestMethod("GET");
        conection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conection.setRequestProperty("Accept", "*/*");
        conection.setRequestProperty("Cache-Control", "no-cache");
        conection.setRequestProperty("Cookie", "device_view=full; sptg=%5B%5D");
        conection.setRequestProperty("User-Agent", "PostmanRuntime/7.29.2");
        

        // Getting response code
        int responseCode = conection.getResponseCode();

        // If responseCode is 200 means we get data successfully
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            
            // Append response line by line
            while ((readLine = in.readLine()) != null) {
            	System.out.println("*******************"+readLine);
                jsonResponseData.append(readLine);
            } 
            
            in.close();
            // Print result in string format
            System.out.println(responseCode);
            System.out.println("******************"+jsonResponseData.toString());
            System.out.println("JSON String Data **** " + jsonResponseData.toString());
            ObjectMapper obj = new ObjectMapper();
            Root root = obj.readValue(jsonResponseData.toString(), Root.class);
            LOGGER.info("***************"+root);
            return root;
        } else {
            System.out.println(responseCode);
            return null;
        }

    }

}