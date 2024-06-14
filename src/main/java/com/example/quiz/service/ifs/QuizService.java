package com.example.quiz.service.ifs;

import com.example.quiz.vo.BasicRes;
import com.example.quiz.vo.CreateOrUpdateReq;
import com.example.quiz.vo.DeleteReq;
import com.example.quiz.vo.SearchReq;
import com.example.quiz.vo.SearchRes;
//Service����
public interface QuizService {
	//"�w�q"��k : 
	//�v��:public �^�ǭȫ��A:BasicRes ��k�W��:createOrUpdate (��ƫ��A ���N�q���ܼƦW��)
	public BasicRes createOrUpdate(CreateOrUpdateReq req);
	
	public SearchRes search(SearchReq req);
	
	public BasicRes delete(DeleteReq req);
}
