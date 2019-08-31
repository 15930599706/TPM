package com.tpm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.prism.paint.Gradient;
import com.tpm.dao.StudentDao;
import com.tpm.entity.Student;

/**
 * Servlet implementation class StuLoginServlet
 */
@WebServlet("/StuLoginServlet")
public class StuLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject = new JSONObject();

		String account = request.getParameter("phoneNum");
		String pwd = request.getParameter("pwd");
		String status = request.getParameter("status");
		System.out.println(account);
		System.out.println(pwd);
		System.out.println(status);
//		String account ="1234567";
//		String pwd = "1234";
//		String status ="0";
		
		StudentDao studentDao= new StudentDao();
		Student student =new Student();
		student = studentDao.getStuinfo(account);
		System.out.println(student.getPwd());
		if (status.equals("0")) { //这是登陆
			if (student.getPwd().equals(pwd)) {
				HttpSession session = request.getSession();
	             session.setAttribute("StuID", account);
	             session.setAttribute("name", student.getName());
	             jsonObject.put("status", 0);
	             jsonArray.put(jsonObject);
				System.out.println("登录成功");
			}else {
				jsonObject.put("status", 1);
	             jsonArray.put(jsonObject);

				System.out.println("登陆失败");
			}
		}else { //这是注册
			if (studentDao.register(account)) { 
				String xihao=request.getParameter("Major");
				String name = request.getParameter("Name");			
				String sex =  request.getParameter("Sex");
				String grade = request.getParameter("grade");
				String company = request.getParameter("Company");
				String xuehao = request.getParameter("StuNum");
//				String xihao ="2001";
//				String name = "李昂";
//				String sex = "男";
//				String grade ="2018";
				student.setID(account);
				student.setName(name);
				student.setPwd(pwd);
				student.setXihao(xihao);
				student.setSex(sex);
				student.setGrade(grade);
				student.setCompany(company);
				student.setXuehao(xuehao);
				studentDao.addStu(student);
				  jsonObject.put("status", 0);
		             jsonArray.put(jsonObject);
			}else {
				//该账号已被注册
				jsonObject.put("status", 1);
	             jsonArray.put(jsonObject);
				System.out.println("注册失败");
			}
		}
		response.getWriter().print(jsonArray.toString());
	}

}
