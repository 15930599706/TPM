package com.tpm.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.Notice;
import com.tpm.entity.PageBean;
import com.tpm.service.NoticeService;

public class NoticeAction extends ActionSupport implements ModelDriven<Notice>{
	private Notice notice = new Notice();
	private Integer currentpage;
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public Notice getModel() {
		return notice;
	}
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	public String totzfbPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = noticeService.tzfb(currentpage);
		setAttr(pageBean);
		return "totzfbPage";
	}
	public String totjxwPage(){
		return "totjxwPage";
	}
	public String addnotice(){
		noticeService.addnotice(notice);
		return "addnotice";
	}
	public String shownotice(){
		noticeService.shownotice(notice);
		return "shownotice";
	}
	public String changenotice(){
		noticeService.shownotice(notice);
		return "changenotice";
	}
	public String updatenotice(){
		noticeService.updatenotice(notice);
		return "updatenotice";
	}
	public String delnotice(){
		noticeService.delnotice(notice);
		return "delnotice";
	}
	public String tochecktzfbPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = noticeService.checktzfb(currentpage);
		setAttr(pageBean);
		return "tochecktzfbPage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void setAttr(PageBean pageBean) {
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	}
	private Integer getcurrentpage(Integer currentpage) {
		if(currentpage == null||currentpage == 0){
			currentpage=1;
		}
		return currentpage;
	}
}
