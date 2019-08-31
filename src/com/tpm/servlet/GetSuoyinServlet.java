package com.tpm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tpm.dao.ChooseCardDao;
import com.tpm.dao.FileQuestionDao;
import com.tpm.dao.FillCardDao;
import com.tpm.dao.QuestionDao;
import com.tpm.dao.SuoyinDao;
import com.tpm.entity.Choose_card;
import com.tpm.entity.File_question;
import com.tpm.entity.Fill_card;
import com.tpm.entity.Question;



/**
 * Servlet implementation class GetSuoyinServlet
 */
@WebServlet("/GetSuoyinServlet")
public class GetSuoyinServlet extends HttpServlet {
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
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		//设置题的类型，1：单项填空题，2：多项填空题，3：多选题，4：单选题，5：文件上传题
		 
		response.setContentType("text/json; charset=utf-8");
		int tihao=Integer.parseInt(request.getParameter("")); //获取传过来的题号		
		QuestionDao questionDao=new QuestionDao();	//
		Question question=new Question();		//创建一个题类
		question=questionDao.getTi(tihao);		//从数据库中获取题的信息
		String leixing=question.getLeixing();		//得到这个题的类型
			if (leixing.equals("单项填空题")||leixing.equals("多项填空题")) {
				FillCardDao fillCardDao = new FillCardDao();
				ArrayList<Fill_card> fill_cardsList=new ArrayList<Fill_card>();
				fill_cardsList=fillCardDao.getTiankongka(tihao);
				request.setAttribute("Question", question);
				request.setAttribute("fill_cardsList", fill_cardsList);
				request.getRequestDispatcher("/admin/questionNaire/questionManager/questionList.jsp").forward(request, response);
			}
			
			else if (leixing.equals("多选题")||leixing.equals("单选题")) {
				 ChooseCardDao chooseCardDao=new ChooseCardDao();
				 ArrayList<Choose_card> choose_cardList=new ArrayList<Choose_card>();
				 choose_cardList=chooseCardDao.getXzt(tihao);
				 request.setAttribute("Question", question);
				 request.setAttribute("choose_cardList", choose_cardList);
				 request.getRequestDispatcher("/admin/questionNaire/questionManager/questionList.jsp").forward(request, response);
			}
			
			else if(leixing.equals("文件上传题")){
				 File_question file_question=new File_question();
				 FileQuestionDao fileQuestionDao=new FileQuestionDao();
				 file_question=fileQuestionDao.getWenjianshangchuanti(tihao);	
				 request.setAttribute("Question", question);
				 request.setAttribute("file_question", file_question);
				 request.getRequestDispatcher("/admin/questionNaire/questionManager/questionList.jsp").forward(request, response);
			}			
		}		
	}
	