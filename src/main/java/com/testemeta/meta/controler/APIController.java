package com.testemeta.meta.controler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testemeta.meta.model.GitFile;
import com.testemeta.meta.service.APIService;

@Controller
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class APIController {
	
	@Autowired
	APIService apiService;
	
	Map<String, GitFile> mapFiles = new HashMap<String, GitFile>();
	
	@PostMapping("/link")
	public ResponseEntity<JSONArray> getFiles(@RequestBody Map<String, String> params) throws IOException, ParseException {
		
		String realUrl = getRealUrl(params.get("link"));
		
		JSONArray teste = apiService.getFiles(realUrl,mapFiles);
		
		return new ResponseEntity<JSONArray>(teste, HttpStatus.OK);
	}

	private String getRealUrl(String str) {
		
		String repo = str.substring(str.indexOf("com/")+4);
		
		int index = repo.indexOf("/");
		
		String ouwner = repo.substring(0, index);
		String repoName = repo.substring(index+1,repo.length());
		
		String realUrl = "https://api.github.com/repos/"+ouwner+"/"+repoName+"/contents/";

		return realUrl;
	}

}
