package com.example.quiz.vo;

import java.time.LocalDate;
import java.util.Map;

public class StatisticsRes extends BasicRes{
	
	private String quizName;
	
	private LocalDate starDate;
	
	private LocalDate endDate;
	
	private Map<Integer, Map<String, Integer>> countMap;
	

	public StatisticsRes() {
		super();
	}

	public StatisticsRes(int statusCode, String message) {
		super(statusCode, message);
	}


	public StatisticsRes(int statusCode, String message, String quizName, LocalDate starDate, LocalDate endDate,
			Map<Integer, Map<String, Integer>> countMap) {
		super(statusCode, message);
		this.quizName = quizName;
		this.starDate = starDate;
		this.endDate = endDate;
		this.countMap = countMap;
	}

	
	public String getQuizName() {
		return quizName;
	}

	public LocalDate getStarDate() {
		return starDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
	
	public Map<Integer, Map<String, Integer>> getCountMap() {
		return countMap;
	}

	
	

}
