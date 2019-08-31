package com.tpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tpm.entity.Student;


public class QnaireAndStuDao {
	public void addQnaireAndStu(int QnaireID,String StuID,String UseTime,String Starttime,String Endtime) {
		PreparedStatement preparedStatement=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="insert into 问卷学生联系表 (问卷ID,学生ID,问卷状态,答题时间,开始时间,终止时间)";
		str_sql+="values(?,?,?,?,?,?)";
		try {
			preparedStatement=conn.prepareStatement(str_sql);
			preparedStatement.setInt(1, QnaireID);
			preparedStatement.setString(2, StuID);
			preparedStatement.setInt(3, 0);
			preparedStatement.setString(4, UseTime);
			preparedStatement.setString(5, Starttime);
			preparedStatement.setString(6, Endtime);
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
	public int getnumofstatusQnaire(String stuID,int status) {
		int num=-1;
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select count(*) from 问卷学生联系表 where  学生ID='"+stuID+"' and 问卷状态= "+"'"+status+" '";
			rs=ps.executeQuery(str_sql);
			
			rs.next();
			num=rs.getInt(1);
			int yushu=num%10;
			if (yushu>0) {
				num=(num/10)+1;
			}else {
				num=num/10;
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
		return num;
	}
	public ArrayList<Student> getAllStudent(int QuenaireId,int page) {
		ArrayList<Student> allStudentList= new ArrayList<Student>();
		Statement stmt=null;
		ResultSet rs=null;
		int cpage = (page-1)*10;
		try {
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String sql="select * from 学生信息表 natural join 问卷学生联系表 where 问卷状态=1 and 问卷ID= "+QuenaireId+" limit "+cpage+" ,10";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Student stu = new Student();
				stu.setName(rs.getString("姓名"));
				stu.setID(rs.getString("学生ID"));
				stu.setGrade(rs.getString("年级"));
				stu.setSex(rs.getString("性别"));
				stu.setXihao(rs.getString("系号"));
				allStudentList.add(stu);
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
		return allStudentList;
	}

	public void updateQnaireAndStu(int QnaireID,String StuID,String Usetime) {
		PreparedStatement preparedStatement=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="update 问卷学生联系表 set 问卷状态=1 ,答题时间= "+Usetime+" where 问卷ID= "+QnaireID+" and 学生ID= "+StuID;
		try {
			preparedStatement=conn.prepareStatement(str_sql);
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
	public ArrayList<String> getAllGrade() {
		ArrayList<String> allGradeList= new ArrayList<String>();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String sql="select  年级 from 学生信息表 GROUP BY 年级 ";
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				allGradeList.add(rs.getString(1));
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
		return allGradeList;
	}
	public int getnumofQnaire(String stuID) {
		int num=-1;
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select count(*) from 问卷学生联系表 where  学生ID='"+stuID+"'";
			rs=ps.executeQuery(str_sql);
			
			rs.next();
			num=rs.getInt(1);
			int yushu=num%10;
			if (yushu>0) {
				num=(num/10)+1;
			}else {
				num=num/10;
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
		return num;
	}
	public int getnumofstuofQnaire(int QnaireID) {
		int num=-1;
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select count(*) from 问卷学生联系表 where  问卷ID='"+QnaireID+"'and 问卷状态= 1" ;
			rs=ps.executeQuery(str_sql);
			rs.next();
			num=rs.getInt(1);
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
		return num;
	}

}
