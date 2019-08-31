package com.tpm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.tpm.dao.TableGetAbilityList;
/**
 * Servlet implementation class GetAbilityListServlet
 */
@WebServlet("/GetAbilityListServlet")
public class GetAbilityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		//request.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		String XiID = request.getParameter("对应系");
		PrintWriter out = response.getWriter();
		JSONArray jsonArray =TableGetAbilityList.getAbilitylist(XiID);
		out.print(jsonArray.toString());

	}

}
