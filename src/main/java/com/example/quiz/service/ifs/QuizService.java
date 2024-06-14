package com.example.quiz.service.ifs;

import com.example.quiz.vo.BasicRes;
import com.example.quiz.vo.CreateOrUpdateReq;
import com.example.quiz.vo.DeleteReq;
import com.example.quiz.vo.SearchReq;
import com.example.quiz.vo.SearchRes;
//Service介面
public interface QuizService {
	//"定義"方法 : 
	//權限:public 回傳值型態:BasicRes 方法名稱:createOrUpdate (資料型態 有意義的變數名稱)
	public BasicRes createOrUpdate(CreateOrUpdateReq req);
	
	public SearchRes search(SearchReq req);
	
	public BasicRes delete(DeleteReq req);
}
