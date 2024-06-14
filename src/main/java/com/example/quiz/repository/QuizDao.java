package com.example.quiz.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.entity.Quiz;

@Repository //�⦹������� Spring Boot �U�ަ���ƳB�z��
public interface QuizDao extends JpaRepository<Quiz, Integer>{
	//�~�� JpaRepository �i�H�z�L�w�q��k�Ӥ��ݭn��@�N�i�H�ާ@���
		//JpaRepository<Quiz, Integer>:Quiz�O���n�ާ@������ Entity(class)
		//							 :Integer�O���� Entity�����[@Id���ݩʪ���ƫ��A�A�u��O�j�g���A
	
	public List<Quiz> findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String name,
			LocalDate start,LocalDate end);
	
	
}
