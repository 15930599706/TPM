package com.tpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang.ObjectUtils.Null;

import com.tpm.entity.Student;

public class StudentDao {
	public void addStu(Student stu) {	
		PreparedStatement preparedStatement=null;
		PreparedStatement ps=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="insert into 学生信息表 (学生ID,密码,姓名,性别,系号,年级,公司姓名,学号)";
		str_sql+="values(?,?,?,?,?,?,?,?)";
		try {
			preparedStatement=conn.prepareStatement(str_sql);
			preparedStatement.setString(1, stu.getID());
			preparedStatement.setString(2, stu.getPwd());
			preparedStatement.setString(3, stu.getName());
			preparedStatement.setString(4, stu.getSex());
			preparedStatement.setString(5, stu.getXihao());
			preparedStatement.setString(6, stu.getGrade());
			preparedStatement.setString(7, stu.getCompany());
			preparedStatement.setString(8, stu.getXuehao());
			preparedStatement.execute();
		}
		 catch (SQLException e) {
			// TODO 自动生成的 catch 块 
		}finally {
			try{
				if(preparedStatement!=null)
					preparedStatement.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				
			}
		}
		
	}
	public Student getStuinfo(String ID) {
		Student student=new Student();
		Statement stmt=null;
		ResultSet rs=null;
		try{
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 学生信息表 where 学生ID = '"+ID+"'";
			rs=stmt.executeQuery(str_sql);
			rs.next();
			student.setID(rs.getString(1));
			student.setPwd(rs.getString(2));
			student.setName(rs.getString(3));
			student.setSex(rs.getString(4));
			student.setXihao(rs.getString(5));
			student.setGrade(rs.getString(6));
			student.setCompany(rs.getString(7));
			student.setXuehao(rs.getString(8));
		}catch(Exception e){
			
		}finally {
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				
			}
		}
		return student;
		
	}
	public ArrayList<String> getAllStuID(String xihao,String gradehao) {
		ArrayList<String> iDList=new ArrayList<String>();
		Statement stmt=null;
		ResultSet rs=null;
		try{
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select 学生ID from 学生信息表 where 系号= '"+xihao+"' and 年级= '"+gradehao+"'";
			rs=stmt.executeQuery(str_sql);
			while(rs.next()) {
				iDList.add(rs.getString(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	
		return iDList;
	}
	public boolean register (String account) {
		/*Student student =new Student();
		StudentDao stu =new StudentDao();
		student=stu.getStuinfo(account);
		if (student.equals(null)) {
			return true;
		}else {
			return false;
		}*/
		//Student student=new Student();
		Statement stmt=null;
		ResultSet rs=null;
		try{
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 学生信息表 where 学生ID = '"+account+"'";
			rs=stmt.executeQuery(str_sql);
			
			if (rs.next()) {
				return false;
			}else {
				return true;
			}
			
			
		}catch(Exception e){
			
		}finally {
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				
			}
		}
		return true;
	}
	
}
