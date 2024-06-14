package com.example.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz.entity.Response;

public interface ResponseDao extends JpaRepository<Response, Integer>{

	public boolean existsByQuizIdAndPhone(int quizId,String phone);
	
	public List<Response> findByQuizId(int quizId);
}
