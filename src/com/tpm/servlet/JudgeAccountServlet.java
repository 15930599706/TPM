package com.tpm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tpm.dao.StudentDao;
import com.tpm.entity.Student;

/**
 * Servlet implementation class JudgeAccountServlet
 */
@WebServlet("/JudgeAccountServlet")
public class JudgeAccountServlet extends HttpServlet {
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
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject = new JSONObject();

		String account = request.getParameter("phoneNum");
		StudentDao studentDao= new StudentDao();
		
		if (studentDao.register(account)) {
			jsonObject.put("status", 0);
            jsonArray.put(jsonObject);
		}else {
			//账号已被注册
			jsonObject.put("status", 1);
            jsonArray.put(jsonObject);
		}
		response.getWriter().print(jsonArray.toString());
	}

}
