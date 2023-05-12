package kr.ac.ers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ers/center")
public class MainCenterController {
	
	@GetMapping
	public String main() {
		return "ers/center/main";
	}
}
