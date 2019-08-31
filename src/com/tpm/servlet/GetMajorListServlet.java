package com.tpm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import com.tpm.dao.TabelGetMajorList;
import com.tpm.entity.User;

@WebServlet("/GetMajorListServlet")
public class GetMajorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		User admin=(User) request.getSession().getAttribute("user");		
		
		JSONArray jsonArray =TabelGetMajorList.getMajorlistByCode(admin.getDepartment().getDepartmentid());
		out.print(jsonArray.toString());
	}

}
