package com.tpm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.NoticeFile;
import com.tpm.entity.PageBean;
import com.tpm.service.NoticeFileService;

public class NoticeFileAction extends ActionSupport implements ModelDriven<NoticeFile>{
	private NoticeFile noticeFile = new NoticeFile();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	Calendar calendar = Calendar.getInstance();
	private Integer currentpage;
	private File upload;
	private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public NoticeFile getModel() {
		return noticeFile;
	}
	private NoticeFileService noticeFileService;
	public void setNoticeFileService(NoticeFileService noticeFileService) {
		this.noticeFileService = noticeFileService;
	}
	
	public String tozyxzPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = noticeFileService.zyxz(currentpage);
		setAttr(pageBean);
		return "tozyxzPage";
	}
	public String addnoticeFile() throws IOException{
		if(upload!=null){
			String hash_name = "";
			String prefix=uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
			Integer count = noticeFileService.getcountbyname(uploadFileName);
			if(count == 0){
				hash_name = String.valueOf(uploadFileName.hashCode())+"."+prefix;
			}else{
				hash_name = String.valueOf(uploadFileName.hashCode())+"("+count+")."+prefix;
			}
			File desfile=new File(System.getProperty("user.dir")+"/TPM/WebRoot/NoticeFile/"+hash_name);
			FileUtils.copyFile(upload, desfile);
			noticeFile.setNoticefilename(uploadFileName);
			noticeFile.setNoticefilepath(hash_name);
		//	noticeFile.setNoticefiletime(dateFormat.format(calendar.getTime()));
			noticeFile.setNoticefiletime(dateFormat.format(new Date()));
			noticeFileService.addnoticeFile(noticeFile);
		}
		return "addnoticeFile";
	}
	public String delnoticeFile(){
		NoticeFile newNoticeFile = noticeFileService.get(noticeFile.getNoticefileid());
		File folder = new File(System.getProperty("user.dir")+"/TPM/WebRoot/NoticeFile/");
		File[] files = folder.listFiles();
		for(File file:files){
			if(file.getName().equals(newNoticeFile.getNoticefilepath())){
				file.delete();
			}
		}
		noticeFileService.del(newNoticeFile);
		return "delnoticeFile";
	}
	public void downLoadNoticeFile() throws IOException{
		NoticeFile newNoticeFile = noticeFileService.get(noticeFile.getNoticefileid());
		String realPath = System.getProperty("user.dir")+"/TPM/WebRoot/NoticeFile/"+newNoticeFile.getNoticefilepath();
		HttpServletResponse response = ServletActionContext.getResponse();//UTF-8	("UTF-8"),"UTF-8"  "gb2312"
	//	response.setHeader("content-disposition", "attachment;filename="+new String(newNoticeFile.getNoticefilepath().getBytes(),"ISO-8859-1"));
		response.setHeader("content-disposition", "attachment;filename="+new String(newNoticeFile.getNoticefilename().getBytes("gb2312"),"ISO-8859-1"));
		FileInputStream in = new FileInputStream(realPath);  
        int len = 0;  
        byte[] buffer = new byte[1024];//构建一个字符缓冲区  
        OutputStream out = response.getOutputStream();  
        while((len = in.read(buffer)) > 0){  
            out.write(buffer, 0, len);  
        }
        in.close(); 
        out.flush();  
        out.close(); 
	}
	public String tocheckzyxzPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = noticeFileService.checkzyxz(currentpage);
		setAttr(pageBean);
		return "tocheckzyxzPage";
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
