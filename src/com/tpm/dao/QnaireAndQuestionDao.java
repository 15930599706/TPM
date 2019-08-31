package com.tpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import com.tpm.entity.Question;
import com.tpm.entity.Questionnaire;

public class QnaireAndQuestionDao {

	public void addQuestion(int QnaireID, int TiID, int shifoubida) { //第三个参数是是否必答
		PreparedStatement preparedStatement=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="insert into 问卷题目联系表 (题号,问卷ID,是否必答)";
		str_sql+="values(?,?,?)";
		try {
			preparedStatement=conn.prepareStatement(str_sql);
			preparedStatement.setInt(1, TiID);
			preparedStatement.setInt(2,QnaireID );
			preparedStatement.setInt(3, shifoubida);
			preparedStatement.execute();
			 
		}
		 catch (SQLException e) {
			// TODO 自动生成的 catch 块 
			 e.printStackTrace();
		}finally {
			try{
				if(preparedStatement!=null)
					preparedStatement.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public ArrayList<Question> getQuestion(int QnaireID) {
		ArrayList<Question>questionsList=new ArrayList<Question>();
		
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 问卷题目联系表 inner join 题库表  on 问卷题目联系表.题号 = 题库表.题号 where 问卷ID = "+QnaireID;
			rs=ps.executeQuery(str_sql);
			
			while(rs.next()) {
				Question question=new Question();
				question.setTihao(rs.getInt("题号"));
				question.setBiaoti(rs.getString("标题"));
				question.setTishineirong(rs.getString("提示内容"));
				question.setLeixing(rs.getString("类型"));
				question.setShifoubida(rs.getInt("是否必答"));
				questionsList.add(question);
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
		 return questionsList;
	}
	
	
	
	
}
