package com.example.quiz.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Quiz;

@Repository //把此介面交由 Spring Boot 託管成資料處理類
public interface QuizDao extends JpaRepository<Quiz, Integer>{
	//繼承 JpaRepository 可以透過定義方法而不需要實作就可以操作資料
		//JpaRepository<Quiz, Integer>:Quiz是指要操作的哪個 Entity(class)
		//							 :Integer是指該 Entity中有加@Id的屬性的資料型態，只能是大寫型態
	
	public List<Quiz> findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String name,
			LocalDate start,LocalDate end);
	
	
}
