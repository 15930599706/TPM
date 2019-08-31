package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import com.tpm.entity.NoticeFile;
@SuppressWarnings("all")
public class NoticeFileDaoImpl extends BaseDaoImpl<NoticeFile> implements NoticeFileDao {

	public List<NoticeFile> OrderfindT(int begin, int pageSize) {
		List<NoticeFile> nflist = findAll();
		List<NoticeFile> newnflist = new ArrayList<NoticeFile>();
		if(begin > pageSize){
			for(int i=begin-1,j=0;j<pageSize;j++,i--){
				newnflist.add(nflist.get(i));
			}
		}else{
			for(int i=begin-1;i>=0;i--){
				newnflist.add(nflist.get(i));
			}
		}
		return newnflist;
	}
	public Integer getcountbyname(String uploadFileName) {
		List<NoticeFile> noticeFilelist = (List<NoticeFile>)this.getHibernateTemplate().
				find("from NoticeFile where noticefilename = ?", uploadFileName);
		if(noticeFilelist.size() != 0){
			return noticeFilelist.size();
		}else{
			return 0;
		}
	}

	

}
