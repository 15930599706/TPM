package com.tpm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.NoticeFileDao;
import com.tpm.entity.NoticeFile;
import com.tpm.entity.PageBean;
@Transactional
public class NoticeFileService {
	private NoticeFileDao noticeFileDao;
	public void setNoticeFileDao(NoticeFileDao noticeFileDao) {
		this.noticeFileDao = noticeFileDao;
	}
	public PageBean zyxz(Integer currentpage) {
		return pagebean(currentpage);
	}
	public void addnoticeFile(NoticeFile noticeFile) {
		noticeFileDao.add(noticeFile);
	}
	public NoticeFile get(Integer noticefileid) {
		return noticeFileDao.get(noticefileid);
	}
	public void del(NoticeFile newNoticeFile) {
		noticeFileDao.delete(newNoticeFile);
	}
	public PageBean checkzyxz(Integer currentpage) {
		return checkpagebean(currentpage);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private PageBean checkpagebean(Integer currentpage) {
		PageBean pageBean = new PageBean();		
		int totalCount = noticeFileDao.findCount();
		
		int pageSize = 20;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		if(totalPage == 0){
			currentpage = 1;
		}
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		int begin = totalCount-(currentpage-1)*pageSize;
		List<NoticeFile> noticeFilelist = noticeFileDao.OrderfindT(begin, pageSize);//倒序排列
		pageBean.setNoticeFilelist(noticeFilelist);
		return pageBean;
	}
	private PageBean pagebean(Integer currentpage) {
		PageBean pageBean = new PageBean();		
		int totalCount = noticeFileDao.findCount();		
		
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		if(totalPage == 0){
			currentpage = 1;
		}
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		int begin = totalCount-(currentpage-1)*pageSize;
		List<NoticeFile> noticeFilelist = noticeFileDao.OrderfindT(begin, pageSize);//倒序排列
		pageBean.setNoticeFilelist(noticeFilelist);
		return pageBean;
	}
	public Integer getcountbyname(String uploadFileName) {
		return noticeFileDao.getcountbyname(uploadFileName);
	}
	
	
	
	
	
	
	
}
