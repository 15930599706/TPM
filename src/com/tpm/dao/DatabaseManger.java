package com.tpm.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManger {
	
	private static Connection conn=null;
	
	//连接数据库后一定要记得去1、关闭数据库。2、出现异常后一定要关闭数据库
	public static Connection getconnect() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");				
			conn=DriverManager.getConnection(Config.sql_url+"tpm?useSSL=false&serverTimezone=UTC",Config.sql_user,Config.sql_psw);
		}catch(Exception e){
			
		}			
		return conn;
	}
	
	public static void doclose(){
		try{
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			
		}		
	}
}