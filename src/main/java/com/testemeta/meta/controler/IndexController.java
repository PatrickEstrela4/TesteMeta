package com.testemeta.meta.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index.html")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/index.js")
	public String indexJS() {
		return "index.js";
	}
}
