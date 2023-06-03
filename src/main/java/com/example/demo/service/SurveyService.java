package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Survey;

@Service
public interface SurveyService {
	
	void save(Survey survey) ;
	
	List<Survey> getAll();
}