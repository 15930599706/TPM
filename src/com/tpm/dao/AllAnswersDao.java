package com.tpm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.tpm.entity.Answer;

public class AllAnswersDao {
	public ArrayList<Answer>  getAllAns(int QnaireID,int tihao) {
		//JSONArray jsonArray =new JSONArray();
		ArrayList<Answer> AnswerList= new ArrayList<Answer>();
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 学生问卷题目联系表  where 问卷ID = "+QnaireID+" and 题号= "+tihao;
			rs=ps.executeQuery(str_sql);
			while(rs.next()) {
				//JSONObject jsonObject=new JSONObject();
				Answer answer=new Answer();
				answer.setAnswer(rs.getString("答案"));
				//jsonObject.put("name", rs.getString("答案"));
				//jsonArray.put(jsonObject);	
				answer.setTiID(tihao);
				AnswerList.add(answer);
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try{
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return AnswerList;
	}
}
