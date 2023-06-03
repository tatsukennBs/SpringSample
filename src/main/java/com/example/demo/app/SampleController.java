package com.example.demo.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Add annotations here
 */
@Controller
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	// 	//Add an annotation here 
	public SampleController(JdbcTemplate jdbcTemplate) {
	 		this.jdbcTemplate = jdbcTemplate;
	 }
		
		@GetMapping("/test")
		public String test(Model model) {
			
			String userIdParam = "1";
			String sql = "SELECT id,name,email FROM inquiry WHERE id =" + userIdParam;
			
			Map <String, Object> map = jdbcTemplate.queryForMap(sql);
			model.addAttribute("title", "Inquiry Form");
			model.addAttribute("name",map.get("name"));
			model.addAttribute("email",map.get("email"));
			
	//		htmlのファイルを返却する
			return "test";
		}
}
