package com.testemeta.meta.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.h2.security.auth.DefaultAuthenticator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.testemeta.meta.model.GitFile;

@Service("APIService")
public class APIServiceImp implements APIService {

	@Override
	public JSONArray getFiles(String str,Map<String, GitFile> mapFiles) throws IOException, ParseException {

		JSONArray json = getJsonArray(str);
		int sizeJSON = json.size();
		
		for (int i = 0 ; i < sizeJSON ;i++) {
			if(((JSONObject) json.get(i)).get("type").equals("dir") || ((JSONObject) json.get(i)).get("content") == null) {
				getFiles(((JSONObject) json.get(i)).get("url").toString(),mapFiles);
			}else {
				for(int j = 0 ; j < sizeJSON; j++) {
					JSONObject jsonObj = (JSONObject) json.get(j);
					byte[] data = Base64.getMimeDecoder().decode(jsonObj.get("content").toString());
					String[] lines = new String(data).toString().split("\r\n|\r|\n");
					int rowNumber = lines.length;
					if(jsonObj.get("name").toString().contains(".")) {
					String fileType = jsonObj.get("name").toString().split("\\.")[1];
					
					if(mapFiles.containsKey(fileType)) {
						GitFile values = mapFiles.get(fileType);
						values.setCountFile(values.getCountFile() + 1);
						values.setRowNumbers(values.getRowNumbers() + rowNumber);
						mapFiles.put(fileType, values);
						
					}else {
						GitFile values = new GitFile();
						values.setCountFile(1);
						values.setRowNumbers(rowNumber);
						values.setExtension(fileType);
						mapFiles.put(fileType, values);
					}
				}
				}
			}
		}
		
		
		List<GitFile> list = new ArrayList<GitFile>(mapFiles.values());
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		for(GitFile iter : list){
		    String extension = iter.getExtension();
		    String countFile = String.valueOf(iter.getCountFile());
		    String rowNumbers = String.valueOf(iter.getRowNumbers());
		    JSONObject o = new JSONObject();
		    o.put("extension", extension);
		    o.put("count", countFile);
		    o.put("lines", rowNumbers);
		    array.add(o);
		}
		
		return array;
		
	}
	
	public JSONArray getJsonArray(String str) throws IOException, ParseException {
		URL url = new URL(str);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		String userpass = "patrickestrela4@hotmail.com" + ":" + "E$trela1994";  
		String basicAuth = "Basic :" + new String(Base64.getEncoder().encode(userpass.getBytes()));  
		con.setRequestProperty ("Authorization", basicAuth);  
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		JSONParser parser = new JSONParser();
		
		Object jsonObj = parser.parse(response.toString());
		
		if(jsonObj instanceof JSONArray) {
		
			return (JSONArray) parser.parse(response.toString());
		}else {
			JSONArray json = new JSONArray();
			json.add(jsonObj);
			return json;
		}
	}

}
