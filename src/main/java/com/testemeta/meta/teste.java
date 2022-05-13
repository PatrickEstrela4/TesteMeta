package com.testemeta.meta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.Base64Utils;

import com.testemeta.meta.model.GitFile;

import sun.nio.cs.ISO_8859_1;


public class teste {

	public static void main(String[] args) throws IOException, ParseException {
/*		URL url = new URL("https://api.github.com/repos/PatrickEstrela4/PAA_trab1/contents/build.xml?ref=master");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		System.out.println(response.toString());
		
		JSONParser parser = new JSONParser();
		
		//JSONArray json = (JSONArray) ;
		JSONObject json = (JSONObject) parser.parse(response.toString());
		
		//System.out.println(json.get("content").toString());
		
		byte ptext[] =json.get("content").toString().getBytes("UTF-8");
		
		byte[] data = Base64.getMimeDecoder().decode(json.get("content").toString());
		
		   String[] lines = new String(data).toString().split("\r\n|\r|\n");
		
		System.out.println(json.get("name").toString());
		System.out.println(Arrays.toString(json.get("name").toString().split("\\.")));*/
		
	/*	
		GitFile values = new GitFile();
		values.setCountFile(1);
		values.setRowNumbers(2);
		values.setExtension("java");
		GitFile values2 = new GitFile();
		values2.setCountFile(1);
		values2.setRowNumbers(2);
		values2.setExtension("java");
		
		Map<String, GitFile> mapFiles = new HashMap<String, GitFile>() ;
		
		mapFiles.put("1", values2);
		mapFiles.put("2", values);
		
		JSONParser parser = new JSONParser();
		JSONObject obj =  (JSONObject) parser.parse(JSONObject.toJSONString(mapFiles));
		
		System.out.println(obj.toString());*/
		
		
		
	    JSONObject o = new JSONObject();
	    o.put("extension", "a");
	    o.put("count", "b");
	    o.put("lines", "c");
	    
	    System.out.println(o);

	}

}
