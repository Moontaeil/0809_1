package kr.co.softsoldesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.service.TopMenuService;

@Controller
public class MainController {

	@GetMapping("/main")
	private String main() {
	
		return "main";
	}
}
