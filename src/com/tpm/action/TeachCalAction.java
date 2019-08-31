package com.tpm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.tpm.entity.TeachCal;
import com.tpm.service.TeachCalService;

public class TeachCalAction extends ActionSupport implements ModelDriven<TeachCal>{
	private TeachCal teachCal=new TeachCal();
	private TeachCalService teachCalService;
	private Integer currentpage;
	private File upload;
	private String uploadFileName;
	
	public TeachCal getModel() {
		return teachCal;
	}
	
	public void setTeachCalService(TeachCalService teachCalService) {
		this.teachCalService = teachCalService;
	}
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	Calendar calendar = Calendar.getInstance();
	
	public Integer getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}

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
	private Integer getcurrentpage(Integer currentpage) {
		if(currentpage == null||currentpage == 0){
			currentpage=1;
		}
		return currentpage;
	}
	public String toTeachCalPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean=teachCalService.findByTeachCalFirstPage(teachCal,currentpage);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		ServletActionContext.getRequest().setAttribute("newcurrentpage", currentpage);
		//if(!"".equals(teachCal.getUser().getCollege().getCollegeid()))
		//{
			ServletActionContext.getRequest().setAttribute("newusercollegeid", teachCal.getUser().getCollege().getCollegeid());
		//}
		//if(!"".equals(teachCal.getUser().getDepartment().getDepartmentid()))
		//{
			ServletActionContext.getRequest().setAttribute("newuserdepartid", teachCal.getUser().getDepartment().getDepartmentid());
		//}
		return "toTeachCalPage";
	}
	public String addteachCal() throws IOException{
		if(upload!=null)
		{
			String hash_name = "";
			String prefix=uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
			Integer count = teachCalService.getCountByName(uploadFileName);
			if(count == 0)
			{
				hash_name = String.valueOf(uploadFileName.hashCode())+"."+prefix;
			}
			else
			{
				hash_name = String.valueOf(uploadFileName.hashCode())+"("+count+")."+prefix;
			}
			File desfile=new File(System.getProperty("user.dir")+"/TPM/WebRoot/TeachCalFile/"+hash_name);
			FileUtils.copyFile(upload, desfile);
			
			teachCal.setTeachCalfilename(uploadFileName);
			teachCal.setTeachCalfilepath(hash_name);
		//	teachCal.setTeachCalfiletime(dateFormat.format(calendar.getTime()));
			teachCal.setTeachCalfiletime(dateFormat.format(new Date()));
			teachCalService.addteachCalFile(teachCal);
		}
		ServletActionContext.getRequest().setAttribute("tag", "toTeachCalPage");
		ServletActionContext.getRequest().setAttribute("msg", "上传成功！");
		return "addteachCal";
	}
	public String delTeachCalPage()
	{
		teachCalService.delTeachCalPage(teachCal.getTeachCalfileid());
		ServletActionContext.getRequest().setAttribute("tag", "toTeachCalPage");
		ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
		return "delTeachCalPage";
	}
	public void downLoadFile() throws IOException
	{
		TeachCal teachCal1 = teachCalService.get(teachCal.getTeachCalfileid());
		String realPath = System.getProperty("user.dir")+"/TPM/WebRoot/TeachCalFile/"+teachCal1.getTeachCalfilepath();
		HttpServletResponse response = ServletActionContext.getResponse();//UTF-8
	//	response.setHeader("content-disposition", "attachment;filename="+new String(teachCal1.getTeachCalfilepath().getBytes(),"ISO-8859-1"));
		response.setHeader("content-disposition", "attachment;filename="+new String(teachCal1.getTeachCalfilename().getBytes("gb2312"),"ISO-8859-1"));
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
	
	public void downSample() throws IOException
	{
		String realPath = System.getProperty("user.dir")+"/TPM/WebRoot/TeachCalFile/TeachCal.doc";
		HttpServletResponse response = ServletActionContext.getResponse();//UTF-8
		String name="教学日历模板.doc";
		response.addHeader("Content-Disposition", "attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1") );
		FileInputStream in = new FileInputStream(realPath);  
        int len = 0;  
        byte[] buffer = new byte[1024];//构建一个字符缓冲区  
        OutputStream out = response.getOutputStream();  
        while((len = in.read(buffer)) > 0)
        {  
            out.write(buffer, 0, len);  
        }
        in.close(); 
        out.flush();  
        out.close();
	}
	
}
