package com.tpm.action;
import java.io.IOException;


import com.opensymphony.xwork2.ActionSupport;
import com.tpm.service.ExportService;
/** 
 * Servlet implementation class MyServlet 
 */   

@SuppressWarnings("serial")
public class ExportAction extends ActionSupport {
	


	/**
	 * 
	 */
	private ExportService exportService;
	private String departmentid;
	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	//
//	private ExportService exportService;
//	//private static final long serialVersionUID = 1L;
//	
//	public void setExportService(ExportService exportService) {
//		this.setExportService(exportService);
//	
//	
//	}
//	public String hahah() throws IOException{//下载理论课模板
//		exportService.down();
//		return "ok";
//		
//	}
	public void hahah()throws IOException{
		exportService.down(departmentid);
//		return "ok";
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
}




