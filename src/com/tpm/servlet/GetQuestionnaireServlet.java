package com.tpm.servlet;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpm.dao.QnaireAndStuDao;
import com.tpm.dao.QuestionnaireDao;
import com.tpm.entity.Question;
import com.tpm.entity.Questionnaire;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Servlet implementation class GetQuestionnaireServlet
 */
@WebServlet("/GetQuestionnaireServlet")
public class GetQuestionnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession(); 
//		String StuID = session.getAttribute("StuID").toString();
		String QuestionnaireStatus = request.getParameter("QuestionnaireStatus");
		String StuID=(String) request.getSession().getAttribute("StuID");;
		String AdminID="";
		int page=Integer.parseInt(request.getParameter("cpage"));
		int num=0;
		QnaireAndStuDao qnaireAndStuDao = new QnaireAndStuDao();
		ArrayList<String>allGrade=qnaireAndStuDao.getAllGrade();
		QuestionnaireDao questionnaireDao=new QuestionnaireDao();
		ArrayList<Questionnaire>questionnairesList= new ArrayList<Questionnaire>();
		if(!AdminID.equals("")) {                                                 //查老师所有问卷
			if (QuestionnaireStatus.equals("3")) {
				num = questionnaireDao.getnumofQnaire(AdminID);
				//questionnairesList = questionnaireDao.getAdminAllQnaireInfo(AdminID);
				
			}
				request.setAttribute("questionnaireList", questionnairesList);
				request.setAttribute("current_page", page);
				request.setAttribute("tail_page",num );
				request.setAttribute("allGrade", allGrade);
			//System.out.println(questionnairesList.get(1).getName());
			request.getRequestDispatcher("/admin/questionNaire/Adminstrator/PublishQnaire.jsp").forward(request, response);
			//System.out.println(questionnairesList.get(1).getName()+"aaaaaaaa");
		}
		else{                                                                      //查对应学生的所有问卷
			if (QuestionnaireStatus.equals("3")) {
				num=qnaireAndStuDao.getnumofQnaire(StuID);
				//questionnairesList = questionnaireDao.getAllQnaireInfo(StuID);
				questionnairesList = questionnaireDao.FenyeGetAllStuQnaire(page, StuID);
			}else if(QuestionnaireStatus.equals("1")||QuestionnaireStatus.equals("0")) {
				num = qnaireAndStuDao.getnumofstatusQnaire(StuID, Integer.parseInt(QuestionnaireStatus));
				//questionnairesList = questionnaireDao.getQnaireInfo(Integer.parseInt(QuestionnaireStatus),StuID);
				questionnairesList = questionnaireDao.FenyeGetStuQniaire(Integer.parseInt(QuestionnaireStatus), page, StuID);
			}
		request.setAttribute("QuestionnaireStatus", QuestionnaireStatus);
		request.setAttribute("questionnaireList", questionnairesList);
		request.setAttribute("current_page", page);
		request.setAttribute("tail_page",num );
		request.getRequestDispatcher("/admin/questionNaire/student/doneQuestionnairList.jsp").forward(request, response);
	}
	}
}
