package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Survey;

@Repository
public class SurveyDaoImpl implements SurveyDao {

	private final JdbcTemplate  jdbctemplete;
	
	//JdbcTempleteをインスタンス化
	@Autowired
	public SurveyDaoImpl(JdbcTemplate jdbctemplete) {
		this.jdbctemplete = jdbctemplete;
	}

	@Override
	public void updateSurvey(Survey survey) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = "INSERT INTO SURVEY(age, satisfaction, comment, created) VALUES(?, ?, ?, ?)";
		jdbctemplete.update(sql, survey.getAge(), survey.getSatisfaction(), survey.getComment(), survey.getCreated());
	}

	@Override
	public List<Survey> getAll() {
		// TODO 自動生成されたメソッド・スタブ
		String sql = "SELECT id, age, satisfaction, comment, created FROM SURVEY";
		List<Map<String,Object>> result = jdbctemplete.queryForList(sql);
		List<Survey> list = new ArrayList<Survey>();
		
		for(Map<String, Object> map : result) {
			Survey survey = new Survey();
			survey.setId((int)map.get("id"));
			survey.setAge((int)map.get("age"));
			survey.setSatisfaction((int)map.get("satisfaction"));
			survey.setComment((String)map.get("comment"));
			survey.setCreated(((Timestamp)map.get("created")).toLocalDateTime());
			list.add(survey);
		}
		return list;
	}

}
