package com.tpm.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tpm.dao.Zhibiaofenjiebiao;
import com.tpm.entity.Zhibiaofenjie;


/**
 * Servlet implementation class GetZhibiaoServlet
 */
@WebServlet("/GetZhibiaoServlet")
public class GetZhibiaoServlet extends HttpServlet {
	 
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
		response.setContentType("text/json; charset=utf-8");
		JSONArray jsonArray =new JSONArray();
		
		String Id = request.getParameter("ID");
		
		Zhibiaofenjiebiao zhibiaofenjiebiao=new Zhibiaofenjiebiao();
		ArrayList<Zhibiaofenjie> list=zhibiaofenjiebiao.getzhibiao(Integer.parseInt(Id));

		int jjj=0;
		while(jjj<list.size())
		{			
			JSONObject jsonObject=new JSONObject();
			try {
				jsonObject.put("指标分解ID",list.get(jjj).getFenjieid());
				jsonObject.put("指标分解内容", list.get(jjj).getNeirong());
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				// TODO 自动生成的 catch 块 
			}
			jjj++;
			
		}
		response.getWriter().print(jsonArray.toString());	
		
	}

}
