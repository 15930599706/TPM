package com.tpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tpm.entity.Question;
import com.tpm.entity.Suoyin;


public class SuoyinDao {
	
	public void  addSy(Suoyin suoyin) {
		PreparedStatement preparedStatement=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="insert into 索引表 (题号,指标点)";
		str_sql+="values(?,?)";
		try {
			preparedStatement=conn.prepareStatement(str_sql);
			preparedStatement.setInt(1, suoyin.getTihao());
			preparedStatement.setInt(2, suoyin.getZhibiaodian());
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try{
				if(preparedStatement!=null)preparedStatement.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				
			}
			}
	}
	public void deleSy(int tihao) {
		
		Statement ss=null;
		try{
			Connection conn=DatabaseManger.getconnect();
			ss=conn.createStatement();
			String str_sql="delete from 索引表 where 题号= '"+tihao+"'";
			ss.execute(str_sql);
		}catch(Exception e){
			
		}finally {
			try{
				if(ss!=null)ss.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				
			}
		}
		
	}
	public ArrayList<Question> getSy(int zhibiaodian,int compage) {
		ArrayList<Question> list = new ArrayList<Question>();
		Statement stmt=null;
		int Startcompage = (compage-1)*10;
		ResultSet rs=null;
		try {
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String sql="select* from 索引表  natural join 题库表 where 指标点= "+zhibiaodian+" limit "+Startcompage+",10";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Question ti = new Question();
				ti.setTihao(rs.getInt(1));
				ti.setBiaoti(rs.getString(3));
				ti.setTishineirong(rs.getString(4));
				ti.setLeixing(rs.getString(5));
				list.add(ti);
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
		 return list;		
	}
	public int  numOfTi(String zhibiaodian) {

		int num=10;

		Statement stmt=null;
		ResultSet rs=null;
		try{
		Connection conn=DatabaseManger.getconnect();
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
		int izhibiaodian = Integer.parseInt(zhibiaodian);
		String str_sql="select COUNT(*) from 索引表  where 指标点= "+izhibiaodian;	
		rs=stmt.executeQuery(str_sql);
		rs.next();
		num=rs.getInt(1);
		int yushu=num%10;
		if(yushu>0){
			num=(num/10)+1;
		}else{
			num=num/10;
		}
		}catch(Exception e){
		e.printStackTrace();
		}finally {
		try{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		DatabaseManger.doclose();
		}catch(Exception e){

		}
		}
		return num;

		}

}
