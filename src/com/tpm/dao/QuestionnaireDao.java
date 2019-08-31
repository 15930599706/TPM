package com.tpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tpm.entity.Questionnaire;

public class QuestionnaireDao {
	
	public static void delById(int id) {
		PreparedStatement preparedStatement=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="delete from 问卷表 where 问卷ID="+id;
		try {
			preparedStatement=conn.prepareStatement(str_sql); 
            preparedStatement.execute();
		}
		 catch (SQLException e) {
		}finally {
			try{
				if(preparedStatement!=null)
					preparedStatement.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				
			}
		}
	}
	
	
	public Questionnaire selectByid(int id) {
		Questionnaire questionnaire=null;
		PreparedStatement preparedStatement=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="select * from 问卷表 where 问卷ID="+id;
		try {
			preparedStatement=conn.prepareStatement(str_sql); 
			ResultSet rs=null;
			rs=preparedStatement.executeQuery(str_sql);			
			if(rs.next()){
				questionnaire=new Questionnaire();
				questionnaire.setID(rs.getInt("问卷ID"));
				questionnaire.setName(rs.getString("问卷名称"));
				questionnaire.setAdminID(rs.getString("管理员ID"));
			}
			//return rs.getInt(1);
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
		return questionnaire;
	}
	public int  addQuestionnaire(Questionnaire Qnaire) {	
		int id;
		PreparedStatement preparedStatement=null;
		PreparedStatement ps=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="insert into 问卷表 (问卷名称,管理员ID)";
		str_sql+="values(?,?)";
		String str="select  LAST_INSERT_ID()";
		try {
			preparedStatement=conn.prepareStatement(str_sql);
			 
			//preparedStatement.setInt(1, Qnaire.getID());
			preparedStatement.setString(1, Qnaire.getName());
			preparedStatement.setString(2, Qnaire.getAdminID());		 
			preparedStatement.execute();
			ps=conn.prepareStatement(str);
			ResultSet rs=null;
			rs=ps.executeQuery(str);
			if(rs.next()){
					id=rs.getInt(1);
					return id;
					}
			//return rs.getInt(1);
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
		return 0; 
	}
	public void addPublishedQuestionnaire(int QuestionaireId,String Grade,String CurrentTime)
	{
		PreparedStatement preparedStatement=null;
		Connection conn = DatabaseManger.getconnect();
		String str_sql="insert into 发布问卷表(问卷ID,年级,时间)";
		str_sql+="values(?,?,?)";
		try {
			preparedStatement=conn.prepareStatement(str_sql);
			 
			//preparedStatement.setInt(1, Qnaire.getID());
			preparedStatement.setInt(1, QuestionaireId);
			preparedStatement.setString(2,Grade);
			preparedStatement.setString(3, CurrentTime);
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
	public ArrayList<Questionnaire> getPublishedQnaire(String AdminId,int page)
	{
		ArrayList<Questionnaire> questionnaireList = new ArrayList<Questionnaire>();
		Statement ps = null;
		ResultSet rs = null;
		Connection conn = DatabaseManger.getconnect();
		int cpage = (page-1)*10;
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 问卷表 natural join 发布问卷表  where 管理员ID='"+AdminId+"'"+" limit "+cpage+" ,10";
			rs=ps.executeQuery(str_sql);
			while(rs.next()) {
				Questionnaire questionnaire=new Questionnaire();
				questionnaire.setID(rs.getInt("问卷ID"));
				questionnaire.setName(rs.getString("问卷名称"));
				questionnaire.setStarttime(rs.getString("时间"));
				questionnaire.setAdminID(rs.getString("管理员ID"));
				questionnaire.setGrade(rs.getString("年级"));
				questionnaireList.add(questionnaire);			
				}
		}catch (SQLException e) {
			
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
		return 	questionnaireList;
	}
	public ArrayList<Questionnaire> getQnaireInfo(int status,String stuID) {
		
		ArrayList<Questionnaire> questionnairesList=new ArrayList<Questionnaire>();
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 问卷学生联系表  natural join 问卷表 where 问卷状态 = "+status+" and 学生ID='"+stuID+"'";
			rs=ps.executeQuery(str_sql);
			
			while(rs.next()) {
				Questionnaire questionnaire=new Questionnaire();
				questionnaire.setID(rs.getInt("问卷ID"));
				questionnaire.setName(rs.getString("问卷名称"));
				questionnaire.setStatus(rs.getInt("问卷状态"));
				questionnaire.setStarttime(rs.getString("开始时间"));
				questionnaire.setEndtime(rs.getString("终止时间"));
				questionnaire.setAdminID(rs.getString("管理员ID"));
				questionnairesList.add(questionnaire);
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
		return questionnairesList;	
	}
	public ArrayList<Questionnaire> getAllQnaireInfo(String stuID) {
		
		ArrayList<Questionnaire> questionnairesList=new ArrayList<Questionnaire>();
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 问卷学生联系表  natural join 问卷表 where  学生ID='"+stuID+"'";
			rs=ps.executeQuery(str_sql);
			
			while(rs.next()) {
				Questionnaire questionnaire=new Questionnaire();
				questionnaire.setID(rs.getInt("问卷ID"));
				questionnaire.setName(rs.getString("问卷名称"));
				questionnaire.setStatus(rs.getInt("问卷状态"));
				questionnaire.setStarttime(rs.getString("开始时间"));
				questionnaire.setEndtime(rs.getString("终止时间"));
				questionnaire.setAdminID(rs.getString("管理员ID"));
				questionnairesList.add(questionnaire);
				
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
		return questionnairesList;		
	}
	public ArrayList<Questionnaire> getAdminAllQnaireInfo(String AdminID) {
		
		ArrayList<Questionnaire> questionnairesList=new ArrayList<Questionnaire>();
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 问卷表 where  管理员ID='"+AdminID+"'";
			rs=ps.executeQuery(str_sql);
			
			while(rs.next()) {
				Questionnaire questionnaire=new Questionnaire();
				questionnaire.setID(rs.getInt("问卷ID"));
				questionnaire.setName(rs.getString("问卷名称"));
				questionnaire.setAdminID(rs.getString("管理员ID"));
				questionnairesList.add(questionnaire);
				
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
		return questionnairesList;		
	}
	public int getnumofQnaire(String adminID) {
		int num=-1;
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select count(*) from 问卷表 where  管理员ID='"+adminID+"'";
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
public ArrayList<Questionnaire> getAdminAllQnaireInfo(String AdminID,int page) {
		
		ArrayList<Questionnaire> questionnairesList=new ArrayList<Questionnaire>();
		Statement ps=null;
		ResultSet rs=null;
		Connection conn = DatabaseManger.getconnect();
		int cpage = (page-1)*10;
		try {
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select * from 问卷表 where  管理员ID='"+AdminID+"' limit "+cpage+" ,10";
			rs=ps.executeQuery(str_sql);
			
			while(rs.next()) {
				Questionnaire questionnaire=new Questionnaire();
				questionnaire.setID(rs.getInt("问卷ID"));
				questionnaire.setName(rs.getString("问卷名称"));
				questionnaire.setAdminID(rs.getString("管理员ID"));
				questionnairesList.add(questionnaire);
				
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
		return questionnairesList;		
	}

public ArrayList<Questionnaire> FenyeGetStuQniaire(int status,int page,String stuID) {
	ArrayList<Questionnaire> questionnairesList=new ArrayList<Questionnaire>();
	Statement ps=null;
	ResultSet rs=null;
	Connection conn = DatabaseManger.getconnect();
	int cpage = (page-1)*10;
	try {
		ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
		String str_sql="select * from 问卷学生联系表 natural join 问卷表  where  学生ID='"+stuID+"' and 问卷状态="+status+" limit "+cpage+" ,10";
		rs=ps.executeQuery(str_sql);
		
		while(rs.next()) {
			Questionnaire questionnaire=new Questionnaire();
			questionnaire.setID(rs.getInt("问卷ID"));
			questionnaire.setName(rs.getString("问卷名称"));
			questionnaire.setStatus(rs.getInt("问卷状态"));
			questionnaire.setStarttime(rs.getString("开始时间"));
			questionnaire.setEndtime(rs.getString("终止时间"));
			questionnaire.setAdminID(rs.getString("学生ID"));
			questionnairesList.add(questionnaire);
			
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
	return questionnairesList;	
	
}
public ArrayList<Questionnaire> FenyeGetAllStuQnaire(int page,String StuID) {
	ArrayList<Questionnaire> questionnairesList=new ArrayList<Questionnaire>();
	Statement ps=null;
	ResultSet rs=null;
	Connection conn = DatabaseManger.getconnect();
	int cpage = (page-1)*10;
	try {
		ps=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
		String str_sql="select * from 问卷学生联系表 natural join 问卷表 where  学生ID='"+StuID+"' limit "+cpage+" ,10";
		rs=ps.executeQuery(str_sql);
		
		while(rs.next()) {
			Questionnaire questionnaire=new Questionnaire();
			questionnaire.setID(rs.getInt("问卷ID"));
			questionnaire.setName(rs.getString("问卷名称"));
			questionnaire.setStatus(rs.getInt("问卷状态"));
			questionnaire.setStarttime(rs.getString("开始时间"));
			questionnaire.setEndtime(rs.getString("终止时间"));
			questionnaire.setAdminID(rs.getString("学生ID"));
			questionnairesList.add(questionnaire);
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
	return questionnairesList;
}
}
