package com.tpm.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.tpm.dao.CollegeDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.entity.College;
import com.tpm.entity.Department;
import com.tpm.entity.PageBean;
import com.tpm.entity.Professional;
import com.tpm.entity.User;

@Transactional
public class ProfessionalService {
	private ProfessionalDao professionalDao;
	public void setProfessionalDao(ProfessionalDao professionalDao) {
		this.professionalDao = professionalDao;
	}
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	private CollegeDao collegeDao;
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	
	public void importProfessionalExcel(String path) {
		boolean flag=true; 
		 try {  //上传的是xls的文件
			    //文件流指向excel文件  //
			 	FileInputStream fin=new FileInputStream(path);  
			    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
				HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importProExcel(totalRow,sheet);
		 }
		 catch (FileNotFoundException e)
		 {  
			    flag=false;  
			    e.printStackTrace();  
		  } 
		 catch(IOException ex)
		  {  
			    flag=false;  
			    ex.printStackTrace();  
		  }catch(Exception ea) {
			  try{//上传的是xlsx的文件
			  	FileInputStream fin=new FileInputStream(path);
			  	XSSFWorkbook workbook = new XSSFWorkbook(fin);
			  	XSSFSheet sheet = workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importProExcel(totalRow,sheet);
			  }catch (FileNotFoundException e)
				 {  
				    flag=false;  
				    e.printStackTrace();  
			  } 
			 catch(IOException ex)
			  {  
				    flag=false;  
				    ex.printStackTrace();  
			  }
			 catch(NullPointerException nullPoint){
				 	flag=false;  
				 	nullPoint.printStackTrace();  
			  }
			 catch(OLE2NotOfficeXmlFileException notXml){
				 	flag=false;  
				 	notXml.printStackTrace();  
			 }
			 catch(POIXMLException isDocx){
				  	flag=false;  
				  	isDocx.printStackTrace(); 
		  }
			    
		  }
		 if(flag){
			 ActionContext context=ActionContext.getContext();			
				context.put("msg", "导入成功");	
		 }else{
			 ActionContext context=ActionContext.getContext();			
				context.put("msg", "请导入正确格式的Excel文件");
		 }
	}
	public void exportProfessionalModelExcel() {
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("专业方向信息模板.xls".getBytes("gb2312"),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}
	public void exportProfessionalExcel() {
		try{
			List<Professional> prlist = professionalDao.findAll();
				HttpServletResponse response = ServletActionContext.getResponse();  
                //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	                //2.2 设置以下载方式打开文件  
	            response.setHeader("Content-Disposition", "attachment;filename="+new String("专业方向信息表.xls".getBytes("gb2312"),"ISO-8859-1") );
	            OutputStream outputStream = response.getOutputStream();
	            if(prlist != null){
	            	ExportExcel(prlist,outputStream);
	            }else{
	            	ExportNoExcel(outputStream);
	            }
	            if(outputStream != null){
	            	outputStream.close();  
	            }
			
		} catch (Exception e) {  
            e.printStackTrace();  
        }
	}
	public PageBean kck(Integer currentpage, User user) {
		return pagebean(currentpage,user);
	}
	private PageBean pagebean(Integer currentpage, User user) {
		PageBean pageBean = new PageBean();
		int totalCount = 0;
		List<Professional> professlist=new ArrayList<Professional>();
		if(!user.getCollege().getCollegeid().equals("-1")){
			if(!user.getDepartment().getDepartmentid().equals("-1"))
			{
				Department department=departmentDao.get(user.getDepartment().getDepartmentid());
				professlist=professionalDao.findbydepartment(department);
				if(professlist!=null){
					totalCount=professlist.size();
				}
			}else{
				College college=collegeDao.get(user.getCollege().getCollegeid());
				List<Department> departlist=departmentDao.findTbyCollege(college);
				if(departlist != null && departlist.size()!=0)
				{
					for(int i=0;i<departlist.size();i++){
						List<Professional> professlist2=professionalDao.findbydepartment(departlist.get(i));
						if(professlist2!=null){
							for(int j=0;j<professlist2.size();j++)
							{
								professlist.add(professlist2.get(j));
							}
						}
					}
					if(professlist!=null){
						totalCount=professlist.size();
					}
				}
				
			}
		}
		else{
			totalCount = professionalDao.findCount();
		}
		
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
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<Professional> professionallist=new ArrayList<Professional>();
		if(!user.getCollege().getCollegeid().equals("-1")){
			
				if(professlist!=null){
					if(currentpage != totalPage){
						professionallist=professlist.subList(begin, (begin+pageSize));
					}
					else{
						if((begin>=0)&&(begin!=totalCount)){
							professionallist=professlist.subList(begin,totalCount);
						}
					}
				}
		}
		else{
			professionallist = professionalDao.findT(begin, pageSize);
		}
		
		pageBean.setProfessionallist(professionallist);
		List<College> collegelist=collegeDao.findAll();
		pageBean.setCollegelist(collegelist);
		return pageBean;
	}
	
	
	
	
	
	

	
	private PageBean pagebean(Integer currentpage) {
		PageBean pageBean = new PageBean();
		int totalCount = professionalDao.findCount();
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
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<Professional> professionallist = professionalDao.findT(begin, pageSize);
		pageBean.setProfessionallist(professionallist);
		return pageBean;
	}
	private void ExportNoExcel(OutputStream outputStream)  throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 3500);
        sheet.setColumnWidth((short) 1, (short) 6000);
        sheet.setColumnWidth((short) 2, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("专业方向代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("专业方向名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("专业代码");  
        cell.setCellStyle(style1);  
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private void ExportExcel(List<Professional> prlist,OutputStream outputStream)  throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 3500);
        sheet.setColumnWidth((short) 1, (short) 3500);
        sheet.setColumnWidth((short) 2, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("专业方向代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("专业方向名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("专业代码");  
        cell.setCellStyle(style1); 
        for(int i=0;i<prlist.size();i++){
        	row = sheet.createRow((int) i+1);
            Professional professional = prlist.get(i);
            cell = row.createCell((short) 0);  
            cell.setCellValue(professional.getProfessionalid());  
            cell.setCellStyle(style2);  
      
            cell = row.createCell((short) 1);  
            cell.setCellValue(professional.getProfessionalname());  
            cell.setCellStyle(style2);  
      
            cell = row.createCell((short) 2);  
            cell.setCellValue(professional.getDepartment().getDepartmentid());  
            cell.setCellStyle(style2); 
        }
        
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private void ExportExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 3500);
        sheet.setColumnWidth((short) 1, (short) 3500);
        sheet.setColumnWidth((short) 2, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("专业方向代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("专业方向名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("专业代码");  
        cell.setCellStyle(style1); 
        
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        cell = row.createCell((short) 0);  
        cell.setCellValue("201701039");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("测试方向");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("0103");  
        cell.setCellStyle(style2); 
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private boolean importProExcel(int totalRow, XSSFSheet sheet) {
		XSSFRow row = null;//对应excel的行  
	    XSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++){
		    	Professional professional = new Professional();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	professional.setProfessionalid(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	professional.setProfessionalname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	String dpartmentid = cell.getRichStringCellValue().toString();
		    	Department department = departmentDao.get(dpartmentid);
		    	if(department == null){
		    		continue;
		    	}else{
			    	professional.setDepartment(department);
			    	Professional newprofessional = professionalDao.get(professional.getProfessionalid());
			    	if(newprofessional == null){
			    		professionalDao.add(professional);
			    	}else{
			    		newprofessional.setProfessionalname(professional.getProfessionalname());
			    		newprofessional.setDepartment(professional.getDepartment());
			    		professionalDao.update(newprofessional);
			    	}
		    	}
		    }return true;
	    }else{
	    	return false;
	    }
	}
	private void changetostring(XSSFCell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
	}
	private boolean checkExcel(XSSFSheet sheet) {
		XSSFRow row=sheet.getRow(0);
		if(row.getCell(0).getRichStringCellValue().toString().equals("专业方向代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("专业方向名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("专业代码")){
			return true;
		}else{return false;}
	}
	private boolean importProExcel(int totalRow, HSSFSheet sheet) {
		HSSFRow row = null;//对应excel的行  
	    HSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++){
		    	Professional professional = new Professional();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	professional.setProfessionalid(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	professional.setProfessionalname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	String dpartmentid = cell.getRichStringCellValue().toString();
		    	Department department = departmentDao.get(dpartmentid);
		    	if(department == null){
		    		continue;
		    	}else{
			    	professional.setDepartment(department);
			    	Professional newprofessional = professionalDao.get(professional.getProfessionalid());
			    	if(newprofessional == null){
			    		professionalDao.add(professional);
			    	}else{
			    		newprofessional.setProfessionalname(professional.getProfessionalname());
			    		newprofessional.setDepartment(professional.getDepartment());
			    		professionalDao.update(newprofessional);
			    	}
		    	}
		    }return true;
	    }else{
	    	return false;
	    }
	}
	private void changetostring(HSSFCell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
	}
	private boolean checkExcel(HSSFSheet sheet) {
		HSSFRow row=sheet.getRow(0);
		if(row.getCell(0).getRichStringCellValue().toString().equals("专业方向代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("专业方向名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("专业代码")){
			return true;
		}else{return false;}
	}

	//删除专业方向
	public void deleteProfessional(Professional professional) {
		Professional newProfessional = professionalDao.get(professional.getProfessionalid());
		if(newProfessional != null)
		{
			professionalDao.delete(newProfessional);
			ServletActionContext.getRequest().setAttribute("msg", newProfessional.getProfessionalname()+"专业方向删除成功!");
			ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
		}
		else
		{
			ServletActionContext.getRequest().setAttribute("msg", "该专业方向已不存在!");
			ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
		}
	}

	public void toaddProDirPage() {
		List<College> collegelist = collegeDao.findAll();
		ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
	}

	//添加专业方向
	public String addProDir(Professional professional) {
		Professional newProfessional = professionalDao.get(professional.getProfessionalid());
		if(newProfessional != null)
		{
			ServletActionContext.getRequest().setAttribute("msg", "专业方向编码"+professional.getProfessionalid()+"已存在，添加失败!");
			ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
			return "failure";
		}
		else
		{
			Department newDepartment = departmentDao.get(professional.getDepartment().getDepartmentid());
			if(newDepartment == null)
			{
				ServletActionContext.getRequest().setAttribute("msg", "所属专业不存在，添加失败!");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "failure";
			}
			else
			{
				professional.setDepartment(newDepartment);
				professionalDao.add(professional);
				ServletActionContext.getRequest().setAttribute("msg", "添加成功!");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "success";
			}
		}
	}
	
}
