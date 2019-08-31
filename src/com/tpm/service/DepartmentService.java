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
import com.tpm.entity.College;
import com.tpm.entity.Department;
import com.tpm.entity.PageBean;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.User;
import com.tpm.dao.CollegeDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.dao.UserDao;
@Transactional
public class DepartmentService {
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	private CollegeDao collegeDao;
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	private TheoreticalPlanDao theoreticalPlanDao;
	private PracticePlanDao practicePlanDao;
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	public void importDepartmentExcel(String path) {
		boolean flag=true; 
		try {  //上传的是xls的文件
		    //文件流指向excel文件  //
		 	FileInputStream fin=new FileInputStream(path);  
		    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
			HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
		    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
		    flag = importDepExcel(totalRow,sheet);
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
			    flag = importDepExcel(totalRow,sheet);
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
	public void exportDepartmentModelExcel() {
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("系信息模板.xls".getBytes("gb2312"),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}
	public void exportDepartmentExcel() {
		try{
			List<Department> delist = departmentDao.findAll();
				HttpServletResponse response = ServletActionContext.getResponse();  
                //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	                //2.2 设置以下载方式打开文件  
	            response.setHeader("Content-Disposition", "attachment;filename="+new String("系信息表.xls".getBytes("gb2312"),"ISO-8859-1") );
	            OutputStream outputStream = response.getOutputStream();
	            if(delist != null){
	            	ExportExcel(delist,outputStream);
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
	
	
	
	
	
	
	
	
	
	private void ExportNoExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 2300);
        sheet.setColumnWidth((short) 1, (short) 8000);
        sheet.setColumnWidth((short) 2, (short) 10000);
        sheet.setColumnWidth((short) 3, (short) 3500);
        sheet.setColumnWidth((short) 4, (short) 1200);
        sheet.setColumnWidth((short) 5, (short) 2300);
        sheet.setColumnWidth((short) 6, (short) 1200);
        sheet.setColumnWidth((short) 7, (short) 2300);
        sheet.setColumnWidth((short) 8, (short) 4600);
        sheet.setColumnWidth((short) 9, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("专业代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("专业名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("专业英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("所属学院代码");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("学制");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("学位");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("层次");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("学科类别");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("国家统编专业代码");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("可选类别");  
        cell.setCellStyle(style1);
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private void ExportExcel(List<Department> delist, OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 2300);
        sheet.setColumnWidth((short) 1, (short) 2300);
        sheet.setColumnWidth((short) 2, (short) 3500);
        sheet.setColumnWidth((short) 3, (short) 3500);
        sheet.setColumnWidth((short) 4, (short) 1200);
        sheet.setColumnWidth((short) 5, (short) 1200);
        sheet.setColumnWidth((short) 6, (short) 1200);
        sheet.setColumnWidth((short) 7, (short) 2300);
        sheet.setColumnWidth((short) 8, (short) 4600);
        sheet.setColumnWidth((short) 9, (short) 2300);
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
        cell.setCellValue("专业代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("专业名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("专业英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("所属学院代码");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("学制");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("学位");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("层次");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("学科类别");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("国家统编专业代码");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("可选类别");  
        cell.setCellStyle(style1);
        for(int i=0;i<delist.size();i++){
        	row = sheet.createRow((int) i+1);
            
        	Department department = delist.get(i);
            cell = row.createCell((short) 0);  
            cell.setCellValue(department.getDepartmentid());  
            cell.setCellStyle(style2);  
      
            cell = row.createCell((short) 1);  
            cell.setCellValue(department.getDepartmentCname());  
            cell.setCellStyle(style2);  
      
            cell = row.createCell((short) 2);  
            cell.setCellValue(department.getDepartmentEname());  
            cell.setCellStyle(style2); 
            
            cell = row.createCell((short) 3);  
            cell.setCellValue(department.getCollege().getCollegeid());  
            cell.setCellStyle(style2); 
            
            cell = row.createCell((short) 4);  
            cell.setCellValue(department.getLearningTime());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 5);  
            cell.setCellValue(department.getDegree());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 6);  
            cell.setCellValue(department.getLevel());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 7);  
            cell.setCellValue(department.getSubjectCategory());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 8);  
            cell.setCellValue(department.getDepartmentidOfCountry());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 9);  
            cell.setCellValue(department.getCategoryOfSelect());  
            cell.setCellStyle(style2);
        }
        
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private void ExportExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 2300);
        sheet.setColumnWidth((short) 1, (short) 2300);
        sheet.setColumnWidth((short) 2, (short) 3500);
        sheet.setColumnWidth((short) 3, (short) 3500);
        sheet.setColumnWidth((short) 4, (short) 1200);
        sheet.setColumnWidth((short) 5, (short) 1200);
        sheet.setColumnWidth((short) 6, (short) 1200);
        sheet.setColumnWidth((short) 7, (short) 2300);
        sheet.setColumnWidth((short) 8, (short) 4600);
        sheet.setColumnWidth((short) 9, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("专业代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("专业名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("专业英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("所属学院代码");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("学制");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("学位");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("层次");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("学科类别");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("国家统编专业代码");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("可选类别");  
        cell.setCellStyle(style1);
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        cell = row.createCell((short) 0);  
        cell.setCellValue("0103");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("包装工程");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("Packing Engineering");  
        cell.setCellStyle(style2); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("01");  
        cell.setCellStyle(style2); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("4");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("工学学士");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("本科");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("工学");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("080301");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("ACDEFG");  
        cell.setCellStyle(style2);
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private boolean importDepExcel(int totalRow, XSSFSheet sheet) {
		XSSFRow row = null;
		XSSFCell cell = null;
		boolean ok =  checkExcel(sheet);
		if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	Department department = new Department();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	department.setDepartmentid(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	department.setDepartmentCname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	department.setDepartmentEname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(4);
		    	changetostring(cell);
		    	department.setLearningTime(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(5);
		    	changetostring(cell);
		    	department.setDegree(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(6);
		    	changetostring(cell);
		    	department.setLevel(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(7);
		    	changetostring(cell);
		    	department.setSubjectCategory(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(8);
		    	changetostring(cell);
		    	department.setDepartmentidOfCountry(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(9);
		    	changetostring(cell);
		    	department.setCategoryOfSelect(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(3);
		    	changetostring(cell);
		    	String collegeid = cell.getRichStringCellValue().toString();
		    	College college = collegeDao.get(collegeid);
		    	if(college == null){
		    		collegeid = "00";
		    		college = collegeDao.get(collegeid);
		    	}
		    		department.setCollege(college);
		    		Department newdepartment = departmentDao.get(department.getDepartmentid());
		    		if(newdepartment == null){
		    			departmentDao.add(department);
		    		}else{
		    			newdepartment.setDepartmentCname(department.getDepartmentCname());
		    			newdepartment.setDepartmentEname(department.getDepartmentEname());
		    			newdepartment.setLearningTime(department.getLearningTime());
		    			newdepartment.setDegree(department.getDegree());
		    			newdepartment.setLevel(department.getLevel());
		    			newdepartment.setSubjectCategory(department.getSubjectCategory());
		    			newdepartment.setDepartmentidOfCountry(department.getDepartmentidOfCountry());
		    			newdepartment.setCategoryOfSelect(department.getCategoryOfSelect());
		    			newdepartment.setCollege(department.getCollege());
		    			departmentDao.update(newdepartment);
		    		}
		    			    	
		    }
		    return true;
	    }else{
	    	return false;
	    }
	}
	private void changetostring(XSSFCell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
	}
	private boolean checkExcel(XSSFSheet sheet) {
		XSSFRow row=sheet.getRow(0);
		if(row.getCell(0).getRichStringCellValue().toString().equals("专业代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("专业名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("专业英文名称")
				&&row.getCell(3).getRichStringCellValue().toString().equals("所属学院代码")
				&&row.getCell(4).getRichStringCellValue().toString().equals("学制")
				&&row.getCell(5).getRichStringCellValue().toString().equals("学位")
				&&row.getCell(6).getRichStringCellValue().toString().equals("层次")
				&&row.getCell(7).getRichStringCellValue().toString().equals("学科类别")
				&&row.getCell(8).getRichStringCellValue().toString().equals("国家统编专业代码")
				&&row.getCell(9).getRichStringCellValue().toString().equals("可选类别")){
			return true;
		}else{return false;}
	}
	private boolean importDepExcel(int totalRow, HSSFSheet sheet) {
		HSSFRow row = null;//对应excel的行  
	    HSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	Department department = new Department();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	department.setDepartmentid(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	department.setDepartmentCname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	department.setDepartmentEname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(4);
		    	changetostring(cell);
		    	department.setLearningTime(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(5);
		    	changetostring(cell);
		    	department.setDegree(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(6);
		    	changetostring(cell);
		    	department.setLevel(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(7);
		    	changetostring(cell);
		    	department.setSubjectCategory(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(8);
		    	changetostring(cell);
		    	department.setDepartmentidOfCountry(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(9);
		    	changetostring(cell);
		    	department.setCategoryOfSelect(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(3);
		    	changetostring(cell);
		    	String collegeid = cell.getRichStringCellValue().toString();
		    	College college = collegeDao.get(collegeid);
		    	if(college == null){
		    		collegeid = "00";
		    		college = collegeDao.get(collegeid);
		    	}
		    		department.setCollege(college);
		    		Department newdepartment = departmentDao.get(department.getDepartmentid());
		    		if(newdepartment == null){
		    			departmentDao.add(department);
		    		}else{
		    			newdepartment.setDepartmentCname(department.getDepartmentCname());
		    			newdepartment.setDepartmentEname(department.getDepartmentEname());
		    			newdepartment.setLearningTime(department.getLearningTime());
		    			newdepartment.setDegree(department.getDegree());
		    			newdepartment.setLevel(department.getLevel());
		    			newdepartment.setSubjectCategory(department.getSubjectCategory());
		    			newdepartment.setDepartmentidOfCountry(department.getDepartmentidOfCountry());
		    			newdepartment.setCategoryOfSelect(department.getCategoryOfSelect());
		    			newdepartment.setCollege(department.getCollege());
		    			departmentDao.update(newdepartment);
		    		}
		    			    	
		    }
		    return true;
	    }else{
	    	return false;
	    }
	}
	private void changetostring(HSSFCell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
	}
	private boolean checkExcel(HSSFSheet sheet) {
		HSSFRow row=sheet.getRow(0);
		if(row.getCell(0).getRichStringCellValue().toString().equals("专业代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("专业名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("专业英文名称")
				&&row.getCell(3).getRichStringCellValue().toString().equals("所属学院代码")
				&&row.getCell(4).getRichStringCellValue().toString().equals("学制")
				&&row.getCell(5).getRichStringCellValue().toString().equals("学位")
				&&row.getCell(6).getRichStringCellValue().toString().equals("层次")
				&&row.getCell(7).getRichStringCellValue().toString().equals("学科类别")
				&&row.getCell(8).getRichStringCellValue().toString().equals("国家统编专业代码")
				&&row.getCell(9).getRichStringCellValue().toString().equals("可选类别")){
			return true;
		}else{return false;}
	}
	public PageBean zybm(Integer currentpage, User user) {
		return pagebean(currentpage,user);
	}
	
	
	
	private PageBean pagebean(Integer currentpage, User user) {
		PageBean pageBean = new PageBean();
		
		//***************************************
		int totalCount=0;
		List<Department> departlist=new ArrayList<Department>();
		if(!user.getCollege().getCollegeid().equals("-1"))
		{
			College college=collegeDao.get(user.getCollege().getCollegeid());
			departlist=departmentDao.findTbyCollege(college);
			if(departlist!=null)
			{
				totalCount=departlist.size();
			}
		}
		else
		{
			totalCount = departmentDao.findCount();
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
		
		List<Department> departmentlist=new ArrayList<Department>();
		//******************************
		if(!user.getCollege().getCollegeid().equals("-1")){
			if(departlist!=null)
			{
				if(currentpage != totalPage){
					departmentlist=departlist.subList(begin, (begin+pageSize));
				}
				else{
					if((begin>=0)&&(begin!=totalCount)){
						departmentlist=departlist.subList(begin,totalCount);
					}
				}
			}
			
		}
		else{
			departmentlist = departmentDao.findT(begin, pageSize);
		}
		pageBean.setDepartmentlist(departmentlist);
		
		//*********************************************
		List<College> collegelist=collegeDao.findAll();
		pageBean.setCollegelist(collegelist);
		
		return pageBean;
	}
	//删除专业
	public String deleteDepartment(Department department) {
		Department newDepartment = departmentDao.get(department.getDepartmentid());
		if(newDepartment != null)
		{
			List<TheoreticalLesson> theoreticalLessonlist = theoreticalPlanDao.getbydepartment(newDepartment);
			List<PracticeLesson> practiceLessonlist = practicePlanDao.findPracticebydepartment(newDepartment);
			if(theoreticalLessonlist.size() == 0 && practiceLessonlist.size() == 0)
			{
				List<User> userlist = userDao.findUserByDepart(newDepartment);
				if(userlist != null && userlist.size() != 0)	//先将该专业下的老师的专业属性设置为空
				{
					for(int i=0;i<userlist.size();i++)
					{
						userlist.get(i).setDepartment(null);
						userDao.update(userlist.get(i));
					}
				}
				departmentDao.delete(newDepartment);
				ServletActionContext.getRequest().setAttribute("msg", newDepartment.getDepartmentCname()+"专业删除成功!");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "success";
			}
			else
			{
				ServletActionContext.getRequest().setAttribute("msg", newDepartment.getDepartmentCname()+"专业删除失败，因为该专业已被分配大纲");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "failure";
			}
			
		}
		else
		{
			ServletActionContext.getRequest().setAttribute("msg", "该专业已不存在!");
			ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
			return "success";
		}
	}
	public void toaddProPage() {
		List<College> collegeList = collegeDao.findAll();
		ServletActionContext.getRequest().setAttribute("collegeList", collegeList);
		
	}
	//添加专业
	public String addPro(Department department) {
		Department newDepartment = departmentDao.get(department.getDepartmentid());
		if(newDepartment != null)
		{
			ServletActionContext.getRequest().setAttribute("msg", "专业编码"+department.getDepartmentid()+"已存在，添加失败!");
			ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
			return "failure";
		}
		else
		{
			College newCollege = collegeDao.get(department.getCollege().getCollegeid());
			if(newCollege == null)
			{
				ServletActionContext.getRequest().setAttribute("msg", "所属学院不存在，添加失败!");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "failure";
			}
			else
			{
				department.setCollege(newCollege);
				departmentDao.add(department);
				ServletActionContext.getRequest().setAttribute("msg", "添加成功!");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "success";
			}
		}
	}
	
}
