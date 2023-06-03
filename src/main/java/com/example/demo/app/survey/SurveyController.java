package com.example.demo.app.survey;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private SurveyService service;

	@Autowired
	public SurveyController(SurveyService service) {
		this.service = service;
	}

	@GetMapping("/index")
	public String index(Model model) {

		List<Survey> list = service.getAll();
		model.addAttribute("title", "Survey Index");
		model.addAttribute("surveyList", list);
		
		return "survey/index";
	}
	
	@GetMapping("/form")
	public String goform(SurveyForm surveyform, Model model) {
		
		model.addAttribute("title", "Survey Form");
		return "survey/form";
	}
	
	@PostMapping("/form")
	public String backform(SurveyForm surveyform, Model model) {
		
		model.addAttribute("title", "Survey Form");
		return "survey/form";
	}

	@PostMapping("/confirm")
	public String goConfirm(@Validated SurveyForm surveyform, 
			BindingResult res, 
			Model model) {

		//バリデーションチェックにかかった場合、form画面に遷移する。
		if(res.hasErrors()) {
			model.addAttribute("title", "Survey Form");	
			return "survey/form";

		}
		model.addAttribute("title", "Confirm Page");
		return "survey/confirm";
	}
	
	@PostMapping("/complete")
	public String goComplete(@Validated SurveyForm surveyform, 
			BindingResult res, 
			Model model,
			RedirectAttributes redirectattributes) {
		
		//バリデーションチェックにかかった場合、form画面に遷移する。
		if(res.hasErrors()) {
			model.addAttribute("title", "Survey Form");	
			return "survey/form";
		}
		
		Survey survey = new Survey();
		survey.setAge(surveyform.getAge());
		survey.setSatisfaction(surveyform.getSatisfaction());
		survey.setComment(surveyform.getComment());
		survey.setCreated(LocalDateTime.now());
		service.save(survey);
		
		redirectattributes.addFlashAttribute("complete", "Thank you for your kind cooperation!");
		return "redirect:/survey/form";
	}

}
