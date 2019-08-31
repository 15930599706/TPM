package com.tpm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;


public class TabelGetMajorList {
	//获取当前老师的专业
	public static JSONArray getMajorlistByCode(String code) {
		JSONArray jsonArray =new JSONArray();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String sql="select*from 专业表 where 专业代码 = "+code;
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("专业代码", rs.getString(1));
				jsonObject.put("专业名称", rs.getString(2));
				jsonArray.put(jsonObject);				
			}
		}catch(Exception e) {
			}finally {
				try {
					if(rs!=null)rs.close();
					if(stmt!=null)stmt.close();
					DatabaseManger.doclose();
				}catch(Exception e) {
					
				}
			}
		 return jsonArray;		
	}
	
	public static JSONArray getMajorlist() {
		JSONArray jsonArray =new JSONArray();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String sql="select*from 专业表 ";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("专业代码", rs.getString(1));
				jsonObject.put("专业名称", rs.getString(2));
				jsonArray.put(jsonObject);				
			}
		}catch(Exception e) {
			}finally {
				try {
					if(rs!=null)rs.close();
					if(stmt!=null)stmt.close();
					DatabaseManger.doclose();
				}catch(Exception e) {
					
				}
			}
		 return jsonArray;		
	}
}
