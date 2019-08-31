package com.tpm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tpm.dao.QnaireAndStuDao;
import com.tpm.dao.QuestionnaireDao;
import com.tpm.entity.Questionnaire;
import com.tpm.entity.User;

/**
 * Servlet implementation class AdminGetQnaireServlet
 */
@WebServlet("/AdminGetQnaireServlet")
public class AdminGetQnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String QuestionnaireStatus = request.getParameter("QuestionnaireStatus");
		String status = request.getParameter("status");
		String AdminID="";
		User admin=(User) request.getSession().getAttribute("user");
		AdminID=admin.getTnum();
		String ipage = request.getParameter("page");
		int page = Integer.parseInt(ipage);
		//int page=1;
		int num=0;
		QnaireAndStuDao qnaireAndStuDao= new QnaireAndStuDao();
		ArrayList<String >allGrade=qnaireAndStuDao.getAllGrade();
		QuestionnaireDao questionnaireDao=new QuestionnaireDao();
		ArrayList<Questionnaire>questionnairesList= new ArrayList<Questionnaire>();
		if(!AdminID.equals("")) {
			if (QuestionnaireStatus.equals("3")) {
				 num=questionnaireDao.getnumofQnaire(AdminID);
				questionnairesList = questionnaireDao.getAdminAllQnaireInfo(AdminID,page);
			}
			request.setAttribute("questionnaireList", questionnairesList);
			request.setAttribute("current_page", page);
			request.setAttribute("tail_page",num );
			request.setAttribute("allGrade", allGrade);
		
			//	request.setAttribute("questionnaireList", questionnairesList);
			//request.getRequestDispatcher("/admin/questionNaire/Adminstrator/PublishQnaire.jsp").forward(request, response);
			if (status.equals("Admin")) {
				request.getRequestDispatcher("/admin/questionNaire/Adminstrator/PublishQnaire.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/admin/questionNaire/AnalyzeQnaire/showExpert.jsp").forward(request, response);
			}

		
		
		
		}

		
	}
}


