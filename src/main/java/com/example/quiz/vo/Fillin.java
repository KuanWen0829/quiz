package com.example.quiz.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fillin {
	
	//question_id
	
	@JsonProperty("question_id")
	private int qId; 
	
	private String question;
	
	//多個選項用分號(;)串接
	private String options;

	//多個答案用分號(;)串接
	private String answer;

	private String type;

	private boolean necessary;

	public Fillin() {
		super();
	}
	public Fillin(int qId, String question,String answer, String type, boolean necessary) {
		super();
		this.qId = qId;
		this.question = question;
		this.answer = answer;
		this.type = type;
		this.necessary = necessary;
	}
	
	

	public Fillin(int qId, String question, String options, String answer, String type, boolean necessary) {
		super();
		this.qId = qId;
		this.question = question;
		this.options = options;
		this.answer = answer;
		this.type = type;
		this.necessary = necessary;
	}
	public int getqId() {
		return qId;
	}

	
	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public String getType() {
		return type;
	}

	public String getOptions() {
		return options;
	}
	public boolean isNecessary() {
		return necessary;
	}
	
	
}
