package com.tpm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpm.dao.QnaireAndQuestionDao;
import com.tpm.dao.QnaireAndStuDao;
import com.tpm.dao.QuestionnaireDao;
import com.tpm.entity.Questionnaire;
import com.tpm.entity.User;

/**
 * Servlet implementation class ShowPublishedServlet
 */
@WebServlet("/ShowPublishedServlet")
public class ShowPublishedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPublishedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		String adminId = user.getTnum();
//	    String adminId = request.getParameter("adminId");
		String page = request.getParameter("page");
		System.out.println("页数:      "+page);
		QuestionnaireDao QuenaireDao = new QuestionnaireDao();
		int ipage = Integer.parseInt(page);
		ArrayList<Questionnaire> questionnaireList = new ArrayList<Questionnaire>();
		questionnaireList = QuenaireDao.getPublishedQnaire(adminId,ipage);
		int all_page = QuenaireDao.getnumofQnaire(adminId);
		request.setAttribute("QuestionnaireList",questionnaireList);
		request.setAttribute("tail_page", all_page/10+1);
		request.setAttribute("current_page", page);
		for (Questionnaire questionnaire : questionnaireList) {
			System.out.println("问卷名称:   "+questionnaire.getName());
		}
		request.getRequestDispatcher("/admin/questionNaire/Adminstrator/checkStuQnaire.jsp").forward(request, response);
	}

}
