package com.example.quiz.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.quiz.constants.OptionType;
import com.example.quiz.constants.ResMessage;
import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizDao;
import com.example.quiz.service.ifs.QuizService;
import com.example.quiz.vo.BasicRes;
import com.example.quiz.vo.CreateOrUpdateReq;
import com.example.quiz.vo.DeleteReq;
import com.example.quiz.vo.Question;
import com.example.quiz.vo.SearchReq;
import com.example.quiz.vo.SearchRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service //�N�� class��spring boot �U��
public class QuizServiceImpl implements QuizService {  //Impl ��class�ئn����--> implements(��@) Service����
	
	@Autowired
	private QuizDao quizDao; //�w�q�@��QuizDao���O���ܼ�: quizDao
							 //�]��QuizDao�o�Ӥ����~�ӤFJpaRepository�A�ҥH���U�ӥu�n�� quizDao. �N��Ψϥ�JPA����k�Ӿާ@���

	//�s�W��@�Ŵ� �YService���w�q����k
	@Override
	public BasicRes createOrUpdate(CreateOrUpdateReq req) {
		// �ˬd�Ѽ�
		BasicRes checkResult = checkParams(req);
		// checkResult == null�ɡA��ܰѼ��ˬd�����T
		if(checkResult != null) {
			return checkResult;
		}
		// �]�� Quiz�� questions����ƫ��A�OString �ҥH�n�N req ��List<Question> �নString
		// �z�L ObjectMapper �i�H�⪫��(���O)�ন JSON�榡���r��
		ObjectMapper mapper = new ObjectMapper();
		try {
			String questionStr = mapper.writeValueAsString(req.getQuestionList());
			//�Yreq����id > 0�A��ܧ�s�w�s�b�s�����:�Y id = 0;�h��ܭn�s�W
			if(req.getId() > 0) {
				//�o��O
				//�H�U��ؤ覡�ܤ@
				//�ϥΤ覡1. �z�L findById�A�Y����ơA�N�|�^�Ǥ@�㵧���(�i���ƶq�|���j)
				//�ϥΤ覡2. �]���O�z�LexistsById �ӧP�_��ƬO�_�s�b�A�ҥH�^�Ǫ���ƥû����u�|�O�@��bit (0��1)
				
				//�覡 1. �z�L findById
//				Optional<Quiz> op = quizDao.findById(req.getId());
//				//�P�_���S�����
//				if(op.isEmpty()) { //op.isEmpty():��ܨS�����
//					return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(),
//						ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
//				}
//				Quiz quiz = op.get();
//				//�]�w�s��(�ȱqreq��)
//				//�Nreq�����s�ȳ]�w���ª�quiz���A���]�wid�C�]��id�@��
//				quiz.setName(req.getName());
//				quiz.setDescription(req.getDescription());
//				quiz.setStartDate(req.getStartDate());
//				quiz.setEndDate(req.getEndDate());
//				quiz.setQuestions(questionStr);
//				quiz.setPublished(req.isPublished());
				
				//�覡 2. �z�LexistsById: �^�Ǥ@��bit ����
				//�o��n�P�_�qreq�a�i�Ӫ�id�O�_�u�����s�b��DB��
				//�]��id���s�b���ܡA����{���X�I�sJPA��save��k�ɡA�|�ܦ��s�W
				boolean boo = quizDao.existsById(req.getId());
				if(!boo) {
					return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(),
							ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
				}
			}
			//=============================================
			
			//�W�z�@��qif�{���X�i�Y��H�U�o�q
			// �p�Gid > 0 ����id�S���s�b��DB��
//			if(req.getId() > 0 && !quizDao.existsById(req.getId())) {
//				return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(),
//						ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
//			}
			//=============================================================================
			
//			Quiz quiz = new Quiz(req.getName(),req.getDescription(),req.getStartDate(),
//					req.getEndDate(),questionStr,req.isPublished());
//			quizDao.save(quiz);
			// �]���ܼ�Quiz �u�Τ@���A �]���i�H�ΰΦW���O�覡���g(���ݭn�ܼƱ�)
				
			//new Quiz()���a�Jreq.getId()�OPK�A�b�I�ssave�ɡA�|���h�ˬdPK�O�_���s�b��DB���A
			//�Y�s�b-->��s ; ���s�b-->�s�W
			//req���S�������ɡA�w�]�O0�A�]��id����ƫ��A�Oint
			quizDao.save(new Quiz(req.getId(),req.getName(),req.getDescription(),req.getStartDate(),
					req.getEndDate(),questionStr,req.isPublished()));
			
		} catch (JsonProcessingException e) {
			return new BasicRes(ResMessage.JSON_PROCESSING_EXCEPTION.getCode(),
					ResMessage.JSON_PROCESSING_EXCEPTION.getMessage());
		}
		
		return new BasicRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage());
		
	}

	private BasicRes checkParams(CreateOrUpdateReq req) {
		// �ˬd�ݨ��Ѽ�
		// StringUtils.hasText(�r��) : �|�ˬd�r��O�_�� null�B�Ŧr��B�ťզr��A�Y�O�ŦX�䤤1�ءA�|�^false
		// �e���[����ĸ���ܤϦV���N��A�Y�r���ˬd���G�Ofalse���ܡA�N�|�i��if����@��
		// !StringUtils.hasText(account) ���P�� StringUtils.hasText(account)==false
		// ����ĸ� �S��ĸ�
		if (!StringUtils.hasText(req.getName())) {
			return new BasicRes(ResMessage.PARAM_QUIZ_NAME_ERROR.getCode(), ResMessage.PARAM_QUIZ_NAME_ERROR.getMessage());
		}

		if (!StringUtils.hasText(req.getDescription())) {
			return new BasicRes(ResMessage.PARAM_DESCRIPION_ERROR.getCode(),
					ResMessage.PARAM_DESCRIPION_ERROR.getMessage());
		}
		// 1.�}�l�ɶ�����p�󵥩��e�ɶ�
		// LocalDate.now(): ���o�t�η�e�ɶ�
        // req.getStartDate().isAfter(LocalDate.now()): �Y req �����}�l�ɶ����e�ɶ��ߡA�|�o�� true
        // !req.getStartDate().isAfter(LocalDate.now()): �e�����[��ĸ��A��ܷ|�o��ۤϪ����G�A�N�O�}�l�ɶ�
        //                                               �|����p���e�ɶ�
		if (req.getStartDate() == null || !req.getStartDate().isAfter(LocalDate.now())) {
			return new BasicRes(ResMessage.PARAM_START_DATE_ERROR.getCode(),
					ResMessage.PARAM_START_DATE_ERROR.getMessage());
		}
		
		// 1.�����ɶ�����p�󵥩��e�ɶ� 2.�����ɶ�����p��}�l�ɶ�
		//�{���X�����o��ɡA��ܶ}�l�ɶ��@�w�j�󵥩��e�ɶ�
		//�ҥH�����ˬd�����ɶ��ɡA�u�n�T�w�����ɶ�����p��}�l�ɶ��Y�i
		//�Ĥ@�ӱ���@�w�|�ŦX�A���ΦA�g�@��

		if (req.getEndDate() == null || !req.getEndDate().isAfter(LocalDate.now()) //
				|| req.getEndDate().isBefore(req.getStartDate())) {
			return new BasicRes(ResMessage.PARAM_END_DATE_ERROR.getCode(),
					ResMessage.PARAM_END_DATE_ERROR.getMessage());
		}
		// �ˬd���D�Ѽ�
		if (CollectionUtils.isEmpty(req.getQuestionList())) {
			return new BasicRes(ResMessage.PARAM_QUESTION_LIST_NOT_FOUND.getCode(),
					ResMessage.PARAM_QUESTION_LIST_NOT_FOUND.getMessage());
		}
		// �@�i�ݨ��i��|���h�Ӱ��D�A�ҥH�n�v���ˬd�C�@�D���Ѽ�
		for (Question item : req.getQuestionList()) {
			if (item.getId() <= 0) {
				return new BasicRes(ResMessage.PARAM_QUESTION_ID_ERROR.getCode(),
						ResMessage.PARAM_QUESTION_ID_ERROR.getMessage());
			}
			if (!StringUtils.hasText(item.getTitle())) {
				return new BasicRes(ResMessage.PARAM_TITLE_ERROR.getCode(),
						ResMessage.PARAM_TITLE_ERROR.getMessage());
			}
			
			if (!StringUtils.hasText(item.getType())) {
				return new BasicRes(ResMessage.PARAM_TYPE_ERROR.getCode(),
						ResMessage.PARAM_TYPE_ERROR.getMessage());

			}
			//�� option_type �O���Φh��ɡAoptions �N����O�Ŧr��
			//�� option_type �O��r�ɡAoptions ���\�O�Ŧr��
			//�H�U�����ˬd:�� option_type �O���Φh��ɡA�B options �O�Ŧr��A��^���~
			if(item.getType().equals(OptionType.SINGLE_CHOICE.getType())
					|| item.getType().equals(OptionType.MULTI_CHOICE.getType())) {
				if (!StringUtils.hasText(item.getOptions())) {
					return new BasicRes(ResMessage.PARAM_OPTIONS_ERROR.getCode(),
							ResMessage.PARAM_OPTIONS_ERROR.getMessage());
				}
			}
			
			//�H�U�O�W�z���if���X�ּg�k: (����1 || ����2) && ����3
			//					   �Ĥ@��if���� && �ĤG��if����
//			if((item.getType().equals(OptionType.SINGLE_CHOICE.getType())
//					|| item.getType().equals(OptionType.MULTI_CHOICE.getType()))
//					&& !StringUtils.hasText(item.getOptions())) {
//					return new BasicRes(ResMessage.PARAM_OPTIONS_ERROR.getCode(),
//							ResMessage.PARAM_OPTIONS_ERROR.getMessage());
//			}
			
		}
		return null;
	}

	@Override
	public SearchRes search(SearchReq req) {
		String name = req.getName();
		LocalDate start = req.getStartDate();
		LocalDate end = req.getEndDate();
		//���] name �O null �άO���ťժ��r��A�i�����S��J����ȡA��ܭn���o����
		//JPA�� containing��k�A����ȬO�Ŧr��ɡA�|�j�M����
		//�ҥH�n�� name ���ȬO null �Υ��ťզr��ɡA �ഫ���Ŧr��
		if(StringUtils.hasText(name)) {
			name = "";
		}
		if(start == null) {
			start = LocalDate.of(1970, 1, 1);
		}
		if(end == null) {
			end = LocalDate.of(2999, 12, 31);
		}
		return new SearchRes(ResMessage.SUCCESS.getCode(),
				ResMessage.SUCCESS.getMessage(), //
				quizDao.findByNameContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(name, start, end));
	}

	@Override
	public BasicRes delete(DeleteReq req) {
		//�Ѽ��ˬd ���]Empty���O�Ū� -->����R��
		if(!CollectionUtils.isEmpty(req.getIdList())) {
			//�R���ݨ�
			try {
				quizDao.deleteAllById(req.getIdList());
			} catch (Exception e) {
				//�� deleteAllById ��k���Aid���Ȥ��s�b�ɡAJPA�|����
				//�]���b�R�����e�AJPA�|���j�M�a�J��id�ȡA�Y�S���G�N�|����
				//����ڤW�]�S�R�������ơA�ҥH���ݭn��o�� Exception ���B�z
			}
			
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
	}
}
