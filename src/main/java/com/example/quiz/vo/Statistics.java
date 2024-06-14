package com.example.quiz.vo;

public class Statistics {//一題的統計
	
	private int qId;
	
	private String qTital;
	
	private boolean necessary;
	
	private String option;
	
	private int count;

	public Statistics() {
		super();
	}

	public Statistics(int qId, String qTital, boolean necessary, String option, int count) {
		super();
		this.qId = qId;
		this.qTital = qTital;
		this.necessary = necessary;
		this.option = option;
		this.count = count;
	}

	public int getqId() {
		return qId;
	}

	public String getqTital() {
		return qTital;
	}

	public boolean isNecessary() {
		return necessary;
	}

	public String getOption() {
		return option;
	}

	public int getCount() {
		return count;
	}

	
}
