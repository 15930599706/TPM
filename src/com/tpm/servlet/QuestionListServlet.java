package com.tpm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpm.dao.QuestionDao;
import com.tpm.dao.SuoyinDao;
import com.tpm.entity.Question;

/**
 * Servlet implementation class GetQuestionServlet
 */
@WebServlet("/QuestionListServlet")
public class QuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//?zhibiao=357&page=1&type=单选题
		String type=request.getParameter("type");
		String zhibiao=request.getParameter("zhibiao");
		String strPage=request.getParameter("page");
		System.out.println("page有要求"+strPage);
		int page=Integer.parseInt(strPage);		
		
		if(zhibiao==null){/*出错！*/}
		if(strPage!=null){Integer.parseInt(strPage);}
		
		 if (!type.equals("无要求")) {
			QuestionDao questionDao=new QuestionDao();
			int allPage=questionDao.numOfTi(zhibiao);
			ArrayList<Question>list=questionDao.fenyegetTi(page, type, zhibiao);
			request.setAttribute("question_list", list);
			request.setAttribute("current_page", page);
			request.setAttribute("tail_page",allPage );
			
			System.out.println("tail_page有要求"+allPage);
			
			request.getRequestDispatcher("/admin/questionNaire/questionManager/questionList.jsp").forward(request, response);
		 }
		 else {
			SuoyinDao ss= new SuoyinDao();
			ArrayList<Question> tiList =ss.getSy(Integer.parseInt(zhibiao),Integer.parseInt(strPage));	//获取指标点对应的题
			int AllCompage = ss.numOfTi(zhibiao);
			request.setAttribute("question_list", tiList);
			request.setAttribute("current_page", page);
			request.setAttribute("tail_page",AllCompage+"" );
			
			System.out.println("tail_page无要求"+AllCompage);
			
			request.getRequestDispatcher("/admin/questionNaire/questionManager/questionList.jsp").forward(request, response);
		}		
	}
}
