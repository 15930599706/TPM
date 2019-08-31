package com.tpm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpm.dao.QnaireAndStuDao;
import com.tpm.entity.Student;

/**
 * Servlet implementation class ShowPublishedQnaireStudentServlet
 */
@WebServlet("/ShowPublishedQnaireStudentServlet")
public class ShowPublishedQnaireStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPublishedQnaireStudentServlet() {
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
		String Quesnaireid = request.getParameter("QnaireID");
//		String Quesnaireid = "17";
		String page = request.getParameter("page");
		int ipage = Integer.parseInt(page);
		QnaireAndStuDao qasDao = new QnaireAndStuDao();
		ArrayList<Student> StudentList = new ArrayList<Student>();
		int QuestionnaireId = Integer.parseInt(Quesnaireid);
		int all_page = qasDao.getnumofstuofQnaire(QuestionnaireId);
		StudentList = qasDao.getAllStudent(QuestionnaireId,ipage);
		request.setAttribute("StudentList", StudentList);
		request.setAttribute("tail_page", (all_page/10)+1);
		request.setAttribute("current_page", page);
		request.setAttribute("QuesnaireId", Quesnaireid);
		request.getRequestDispatcher("/admin/questionNaire/Adminstrator/showPerPaperStu.jsp").forward(request, response);
	}

}
