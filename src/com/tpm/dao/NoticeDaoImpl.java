package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.Notice;

public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao {

	public List<Notice> OrderfindT(int begin, int pageSize) {
		List<Notice> nolist = findAll();
		List<Notice> newnolist = new ArrayList<Notice>();
		if(begin > pageSize){
			for(int i=begin-1,j=0;j<pageSize;j++,i--){
				newnolist.add(nolist.get(i));
			}
		}else{
			for(int i=begin-1;i>=0;i--){
				newnolist.add(nolist.get(i));
			}
		}
		return newnolist;
	}

}
