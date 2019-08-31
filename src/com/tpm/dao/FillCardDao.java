package com.tpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tpm.entity.Fill_card;




public class FillCardDao {
	public void addTiankongka(Fill_card t) {
		String sql="insert into 填空卡表(题号,最小长度,最大长度,是否限定,是否必填) values(?,?,?,?,?)";
		Connection conn = DatabaseManger.getconnect();
		PreparedStatement ps=null;
		try {
		    ps = conn.prepareStatement(sql);
			//给问号设定值
			ps.setInt(1,t.getDxnum());
			ps.setInt(2,t.getMinlength());
			ps.setInt(3, t.getMaxlength());
			ps.setInt(4, t.getXianding());
			ps.setInt(5, t.getBitian());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(ps!=null)ps.close();
				DatabaseManger.doclose();
			}catch(Exception e){
			}
		}
	}
	public void deleteTiankongka(int t) {
		String sql="delete from 填空卡表 where 题号 = ?"; //改过
	    Connection conn=DatabaseManger.getconnect();
	    PreparedStatement ps=null;
	    try {
	    	ps = conn.prepareStatement(sql);
	    	ps.setInt(1, t);
	    	ps.executeUpdate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
	public ArrayList<Fill_card> getTiankongka(int t) {
		
		ArrayList<Fill_card>list =new ArrayList<Fill_card>();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Connection conn=DatabaseManger.getconnect();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//设置结果集可以滚动
			String sql="select*from 填空卡表 where 题号='"+t+"'"; //改过
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Fill_card ti=new Fill_card();
			  ti.setDxnum(rs.getInt(2));
			  ti.setMinlength(rs.getInt(3));
			  ti.setMaxlength(rs.getInt(4));
			  ti.setXianding(rs.getInt(5));
			  ti.setBitian(rs.getInt(6));
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
}

