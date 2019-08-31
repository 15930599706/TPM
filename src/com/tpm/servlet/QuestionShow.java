package com.tpm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpm.dao.ChooseCardDao;
import com.tpm.dao.FileQuestionDao;
import com.tpm.dao.FillCardDao;
import com.tpm.dao.QuestionDao;
import com.tpm.entity.*;

/**
 * Servlet implementation class QuestionPreview
 */
@WebServlet("/QuestionShow")
public class QuestionShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionId=request.getParameter("questionId");
		
		QuestionDao questionDao =new QuestionDao();
//		//测试时使用
//		questionId="105";//单选题
//		questionId="115";//多选题
//		questionId="118";//单选填空题
//		questionId="119";//多项填空题
//		questionId="120";//多项填空题
		Question question=questionDao.getTi(Integer.parseInt(questionId));
		
		if(question!=null){
			if(question.getLeixing().equals("单选题")){
				ChooseCardDao chooseCardDao=new ChooseCardDao();
				ArrayList<Choose_card> clist=chooseCardDao.getXzt(question.getTihao());
				request.setAttribute("clist", clist);
				request.setAttribute("question", question);
				request.getRequestDispatcher("/admin/questionNaire/questionManager/showsingle_choose.jsp").forward(request, response);
			}else if(question.getLeixing().equals("多选题")){
				ChooseCardDao chooseCardDao=new ChooseCardDao();
				ArrayList<Choose_card> clist=chooseCardDao.getXzt(question.getTihao());
				request.setAttribute("clist", clist);
				request.setAttribute("question", question);
				request.getRequestDispatcher("/admin/questionNaire/questionManager/showmult_choose.jsp").forward(request, response);
			}else if(question.getLeixing().equals("单项填空题")){
				request.setAttribute("question", question);
				request.getRequestDispatcher("/admin/questionNaire/questionManager/showsingle_fill.jsp").forward(request, response);
	
			}else if(question.getLeixing().equals("多项填空题")){						
				ArrayList<Fill_card>flist=new FillCardDao().getTiankongka(question.getTihao());
				request.setAttribute("question", question);		
				request.setAttribute("flist", flist);		
				request.getRequestDispatcher("/admin/questionNaire/questionManager/showmult_fill.jsp").forward(request, response);
			}else if(question.getLeixing().equals("文件上传题")){
					
				File_question file_question =new FileQuestionDao().getWenjianshangchuanti(question.getTihao());
				request.setAttribute("question", question);	
				request.setAttribute("file_question", file_question);	
				request.getRequestDispatcher("/admin/questionNaire/questionManager/showfile.jsp").forward(request, response);
			}
		}else{
			//该题为空
			//显示错误界面
		}
	}

}
