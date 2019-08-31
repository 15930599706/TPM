package com.tpm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpm.dao.QnaireAndQuestionDao;
import com.tpm.dao.QuestionnaireDao;
import com.tpm.entity.Questionnaire;
import com.tpm.entity.User;

/**
 * Servlet implementation class CreateQuestionnairServlet
 */
@WebServlet("/CreateQuestionnairServlet")
public class CreateQuestionnairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qlist=request.getParameter("qlist");
		System.out.println(qlist);
		String title=request.getParameter("title");//问卷标题
		String tlist=request.getParameter("tlist");
		String []questionArr=qlist.split(",");		//题号列表
		String []typeArr=tlist.split(",");			//题目是否必答			
		Questionnaire questionnaire =new Questionnaire();
		User admin=(User) request.getSession().getAttribute("user");
		questionnaire.setAdminID(admin.getTnum());		
		questionnaire.setName(title);
		QuestionnaireDao questionnaireDao = new QuestionnaireDao();
		int QnaireID=questionnaireDao.addQuestionnaire(questionnaire); //向问卷表中添加
		System.out.println(questionnaire.getName());
		System.out.println(QnaireID+"aaaaaaaaaaa");
		//这是往问卷题目联系表中插入，
		
		int lengthOfti=typeArr.length;		
		int tihao[]=new int[lengthOfti];	//这是要转换成int
		for(int i=0;i<lengthOfti;i++)
		{
			int status=1;
			QnaireAndQuestionDao qnaireAndQ= new QnaireAndQuestionDao();
			tihao[i]=Integer.parseInt(questionArr[i]);
			if(typeArr[i].equals("yes")){
				status=1;
			}else{
				status=0;
			}			
			qnaireAndQ.addQuestion(QnaireID,tihao[i],status);
			
		}	
		response.getWriter().print("success");
	}

}
