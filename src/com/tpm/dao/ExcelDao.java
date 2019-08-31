package com.tpm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ExcelDao {
	public String getNameOfAbility(int num) {
		String name = "";
		Statement stmt=null;
		ResultSet rs=null;
		try{
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="SELECT 毕业生应获得的知识和能力名 FROM 毕业生应获得的知识和能力表  WHERE 毕业生应获得的知识和能力ID in(SELECT 对应能力 from 指标分解表 WHERE 指标分解ID "
					+ "IN(SELECT 指标点 FROM 索引表 WHERE 题号=  "+num+" ))";
			rs=stmt.executeQuery(str_sql);
			rs.next();
			name = rs.getString(1);
		}catch(Exception e){
			
		}finally {
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				
			}
		}
		return name;
	}
	public ArrayList<Integer> getZhiBiaoDian(int num) {
		ArrayList<Integer> arrayList=new ArrayList<>();
		Statement stmt=null;
		ResultSet rs=null;
		Connection conn=DatabaseManger.getconnect();
		try{
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String str_sql="select 指标点 from 索引表 where 题号= "+num;
			rs=stmt.executeQuery(str_sql);
			while (rs.next()) {
				arrayList.add(rs.getInt(1));
			}
		}catch(Exception e){
		}finally {
			try{
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				DatabaseManger.doclose();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return arrayList;
	}

}
