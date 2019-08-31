package com.tpm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class TableGetAbilityList {
	public static JSONArray getAbilitylist(String num) {
		JSONArray jsonArray =new JSONArray();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String sql="select*from 毕业生应获得的知识和能力表 where 对应系='"+num+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("毕业生应获得的知识和能力ID", rs.getInt(1));
				jsonObject.put("毕业生应获得的知识和能力名", rs.getString(2));
				jsonObject.put("专业生应获得的知识和能力内容", rs.getString(3));
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
