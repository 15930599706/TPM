package com.tpm.service;

import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.NoticeDao;
import com.tpm.entity.Notice;
import com.tpm.entity.PageBean;
@Transactional
public class NoticeService {
	private NoticeDao noticeDao;
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	Calendar calendar = Calendar.getInstance();
	public PageBean tzfb(Integer currentpage) {
		return pagebean(currentpage);
	}
	public void addnotice(Notice notice) {
	//	notice.setNoticetime(dateFormat.format(calendar.getTime()));
		notice.setNoticetime(dateFormat.format(new Date()));
		noticeDao.add(notice);	
	}
	public void shownotice(Notice notice) {
		Notice newnotice = noticeDao.get(notice.getNoticeid());
		ServletActionContext.getRequest().setAttribute("notice", newnotice);
	}
	public void updatenotice(Notice notice) {
	//	notice.setNoticetime(dateFormat.format(calendar.getTime()));
		notice.setNoticetime(dateFormat.format(new Date()));
		noticeDao.update(notice);
	}
	public void delnotice(Notice notice) {
		noticeDao.delete(notice);
	}
	public PageBean tzfb(int currentpage, PageBean pageBean) {
		return pagebean(currentpage,pageBean);
	}
	public PageBean checktzfb(Integer currentpage) {
		return pagebeancheck(currentpage);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private PageBean pagebeancheck(Integer currentpage) {
		PageBean pageBean = new PageBean();
		int totalCount = noticeDao.findCount();
		
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
		List<Notice> noticelist = noticeDao.OrderfindT(begin, pageSize);//倒序排列
		pageBean.setNoticelist(noticelist);
		return pageBean;
	}
	private PageBean pagebean(int currentpage, PageBean pageBean) {
		pageBean.setCurrentpage(currentpage);
		int totalCount = noticeDao.findCount();
		pageBean.setTotalCount(totalCount);
		
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
		List<Notice> noticelist = noticeDao.OrderfindT(begin, pageSize);//倒序排列
		pageBean.setNoticelist(noticelist);
		return pageBean;
	}
	private PageBean pagebean(Integer currentpage) {
		PageBean pageBean = new PageBean();
		int totalCount = noticeDao.findCount();
		
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
		List<Notice> noticelist = noticeDao.OrderfindT(begin, pageSize);//倒序排列
		pageBean.setNoticelist(noticelist);
		return pageBean;
	}
	
	
	
	
	
}
