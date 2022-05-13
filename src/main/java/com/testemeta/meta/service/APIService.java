package com.testemeta.meta.service;

import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.testemeta.meta.model.GitFile;

public interface APIService {

	JSONArray getFiles(String str, Map<String, GitFile> mapFiles) throws IOException, ParseException;

}
