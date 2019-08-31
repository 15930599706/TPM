package com.tpm.service;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.lowagie.text.PageSize;
import com.lowagie.text.Table;
import com.tpm.dao.AvePerThresholdDao;
import com.tpm.dao.ErrorDiscDao;
import com.tpm.dao.PTBasicInformationDao;
import com.tpm.dao.UserDao;
import com.tpm.entity.Ability;
import com.tpm.entity.AvePer;
import com.tpm.entity.AvePerThreshold;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPT;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.ScoreThreshold;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.TotalCredit;
import com.tpm.entity.TrainingEvents;
import com.tpm.entity.User;

public class ErrorDiscService {
	private ErrorDiscDao errorDiscDao;
	private UserDao userDao;
	private AvePerThresholdDao avePerThresholdDao;
	public void setErrorDiscDao(ErrorDiscDao errorDiscDao) {
		this.errorDiscDao = errorDiscDao;
	}	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setAvePerThresholdDao(AvePerThresholdDao avePerThresholdDao) {
		this.avePerThresholdDao = avePerThresholdDao;
	}
	private Float weekHour;
	public Float getWeekHour() {
		return weekHour;
	}
	public void setWeekHour(Float weekHour) {
		this.weekHour = weekHour;
	}
	public Float setweekhour(){
		AvePerThreshold avePerThreshold = avePerThresholdDao.findAvePer();
		if(avePerThreshold != null){
			weekHour = avePerThreshold.getWeekHour();
			return weekHour;
		}
		else{
			return null;
		}
	}
	//	HttpServletRequest request = ServletActionContext.getRequest();	//放入域对象
	//查询所有学院
	public List<College> findAllCollege() {
		return errorDiscDao.findAllCollege();
	}
	//查询培养计划基本信息为空的专业
	public List<Department> findPTBasicInformationEmpty(String collegeid) {	
		return errorDiscDao.findPTBasicInformationEmpty(collegeid);
	}
	//查询毕业生应获得的知识和能力为空的专业
	public List<Department> findAbilityEmpty(String collegeid) {		
		return errorDiscDao.findAbilityEmpty(collegeid);
	}
	//查询专业人才培养理念为空的专业
	public List<Department> findPPTrainingConceptEmpty(String collegeid) {
		return errorDiscDao.findPPTrainingConceptEmpty(collegeid);
	}
	//查询关键课程为空的课
	public void findKeyCouEmpty(String collegeid) {
		List<Curriculum> keyCouEmptyList = errorDiscDao.findKeyCouEmpty(collegeid);	//根据选择的学院查找对应关键课程信息为空的课程
		Integer keyCouEmptyListCount = keyCouEmptyList.size();	//计算有多少条信息，用于List当为0时显示“没有”语句。
		ServletActionContext.getRequest().setAttribute("keyCouEmptyList", keyCouEmptyList);	
		ServletActionContext.getRequest().setAttribute("keyCouEmptyListCount", keyCouEmptyListCount);
		ServletActionContext.getRequest().setAttribute("xueyuan", collegeid);		
	}
	//查询学时学分对应错误的课
	public void findPreCreErr(String collegeid) {
		List<Curriculum> preCreErrList = errorDiscDao.findPreCreErr(collegeid);	//	根据选择的学院查找学时学分对应错误的课程
		Integer preCreErrListCount = preCreErrList.size();
		ServletActionContext.getRequest().setAttribute("preCreErrList", preCreErrList);
		ServletActionContext.getRequest().setAttribute("preCreErrListCount", preCreErrListCount);
		ServletActionContext.getRequest().setAttribute("xueyuan", collegeid);		
	}
	//查询专业总学分为空的专业
	public void findCreditEmpty(String collegeid) {
		List<ScoreThreshold> CreditEmptyList = errorDiscDao.findCreditEmpty(collegeid);
		Integer CreditEmptyListCount = CreditEmptyList.size();
		ServletActionContext.getRequest().setAttribute("CreditEmptyList", CreditEmptyList);	
		ServletActionContext.getRequest().setAttribute("CreditEmptyListCount", CreditEmptyListCount);
		ServletActionContext.getRequest().setAttribute("xueyuan", collegeid);		
		
	}
	
	public User findUserById(String tnum) {
		return userDao.get(tnum);
	}
	//根据学院id查询专业
	public List<Department> findDepartmentByCollegeId(String collegeid) {
		return errorDiscDao.findDepartmentByCollegeId(collegeid);
	}


	public void exportPreCreErrExcel(String collegeid) {	//导出学时学分对应错误课程
		try{
			List<Curriculum> preCreErrList = errorDiscDao.findPreCreErr(collegeid);
				HttpServletResponse response = ServletActionContext.getResponse();  
	            //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	                //2.2 设置以下载方式打开文件  
	            response.setHeader("Content-Disposition", "attachment;filename="+new String("学时学分对应错误课程.xls".getBytes(),"ISO-8859-1") );
	            OutputStream outputStream = response.getOutputStream();
	            if(preCreErrList != null){
	            	ExportPreCreErrExcel(preCreErrList,outputStream);
	            }else{
	            	ExportNoPreCreErrExcel(outputStream);
	            }
	            if(outputStream != null){
	            	outputStream.close();  
	            }
			
		} catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}

	private void ExportPreCreErrExcel(List<Curriculum> preCreErrList, OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("sheet1");
	    sheet.setColumnWidth((short) 0, (short) 5500);
	    sheet.setColumnWidth((short) 1, (short) 3000);
	    sheet.setColumnWidth((short) 2, (short) 5000);
	    sheet.setColumnWidth((short) 3, (short) 2000);
	    sheet.setColumnWidth((short) 4, (short) 2000);
	    sheet.setColumnWidth((short) 5, (short) 2000);
	    sheet.setColumnWidth((short) 6, (short) 2000);
	    sheet.setColumnWidth((short) 7, (short) 2000);
	    sheet.setColumnWidth((short) 8, (short) 2000);
	    sheet.setColumnWidth((short) 9, (short) 5000);
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
	    cell.setCellValue("开课学院");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 1);  
	    cell.setCellValue("课程编码");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 2);  
	    cell.setCellValue("课程名称");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 3);  
	    cell.setCellValue("学分");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 4);  
	    cell.setCellValue("总学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 5);  
	    cell.setCellValue("讲课学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 6);  
	    cell.setCellValue("上机学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 7);  
	    cell.setCellValue("实验学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 8);  
	    cell.setCellValue("实践学时");  
	    cell.setCellStyle(style1);
	    
	    cell = row.createCell((short) 9);  
	    cell.setCellValue("问题说明");  
	    cell.setCellStyle(style1); 
	    
	    for(int i=0;i<preCreErrList.size();i++){
	    	row = sheet.createRow((int) i+1);
	        
	    	Curriculum curriculum = preCreErrList.get(i);
	        cell = row.createCell((short) 0);  
	        cell.setCellValue(curriculum.getCollege().getCollegeCname());  
	        cell.setCellStyle(style2);  
	  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue(curriculum.getCurriculumid());  
	        cell.setCellStyle(style2);  
	  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue(curriculum.getCurriculumCname());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 3);  
	        cell.setCellValue(curriculum.getCredit());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 4);  
	        cell.setCellValue(curriculum.getHoursOfALL());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 5);  
	        cell.setCellValue(curriculum.getHoursOfClass());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 6);  
	        cell.setCellValue(curriculum.getHoursOfCom());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 7);  
	        cell.setCellValue(curriculum.getHoursOfExp());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 8);  
	        cell.setCellValue(curriculum.getHoursOfPractice());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 9);  
	        cell.setCellValue("学时学分对应错误！");  
	        cell.setCellStyle(style2); 
	        
	    }
	    wb.write(outputStream);
	    outputStream.flush();  
	    outputStream.close();
	}
	
	private void ExportNoPreCreErrExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("sheet1");
	    sheet.setColumnWidth((short) 0, (short) 5500);
	    sheet.setColumnWidth((short) 1, (short) 3000);
	    sheet.setColumnWidth((short) 2, (short) 5000);
	    sheet.setColumnWidth((short) 3, (short) 2000);
	    sheet.setColumnWidth((short) 4, (short) 2000);
	    sheet.setColumnWidth((short) 5, (short) 2000);
	    sheet.setColumnWidth((short) 6, (short) 2000);
	    sheet.setColumnWidth((short) 7, (short) 2000);
	    sheet.setColumnWidth((short) 8, (short) 2000);
	    sheet.setColumnWidth((short) 9, (short) 5000);
	    HSSFRow row = sheet.createRow((int) 0);  
	    // 第四步，创建单元格，并设置值表头 设置表头居中  
	    HSSFCellStyle style1 = wb.createCellStyle();  
	    style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
	    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
	    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
	    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
	    
	    HSSFCell cell = row.createCell((short) 0);  
	    cell.setCellValue("开课学院");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 1);  
	    cell.setCellValue("课程编码");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 2);  
	    cell.setCellValue("课程名称");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 3);  
	    cell.setCellValue("学分");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 4);  
	    cell.setCellValue("总学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 5);  
	    cell.setCellValue("讲课学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 6);  
	    cell.setCellValue("上机学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 7);  
	    cell.setCellValue("实验学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 8);  
	    cell.setCellValue("实践学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 8);  
	    cell.setCellValue("问题说明");  
	    cell.setCellStyle(style1); 
	    
	    
	    wb.write(outputStream);
	    outputStream.flush();  
	    outputStream.close();
	}

	public void exportKeyCouEmptyExcel(String collegeid) {	//导出关键课程信息为空的课
		try{
			List<Curriculum> keyCouEmptyList = errorDiscDao.findKeyCouEmpty(collegeid);	//根据选择的学院查找对应关键课程信息为空的课程			
				HttpServletResponse response = ServletActionContext.getResponse();  
	            //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	                //2.2 设置以下载方式打开文件  
	            response.setHeader("Content-Disposition", "attachment;filename="+new String("关键课程信息为空课程.xls".getBytes(),"ISO-8859-1") );
	            OutputStream outputStream = response.getOutputStream();
	            if(keyCouEmptyList != null){
	            	ExportKeyCouEmptyExcel(keyCouEmptyList,outputStream);
	            }else{
	            	ExportNoKeyCouEmptyExcel(outputStream);
	            }
	            if(outputStream != null){
	            	outputStream.close();  
	            }
			
		} catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}

	private void ExportKeyCouEmptyExcel(List<Curriculum> keyCouEmptyList, OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("sheet1");
	    sheet.setColumnWidth((short) 0, (short) 5500);
	    sheet.setColumnWidth((short) 1, (short) 4000);
	    sheet.setColumnWidth((short) 2, (short) 4000);
	    sheet.setColumnWidth((short) 3, (short) 4000);
	    sheet.setColumnWidth((short) 4, (short) 4000);
	    sheet.setColumnWidth((short) 5, (short) 4500);
	    sheet.setColumnWidth((short) 6, (short) 2000);
	    sheet.setColumnWidth((short) 7, (short) 2000);
	    sheet.setColumnWidth((short) 8, (short) 5000);
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
	    cell.setCellValue("开课学院");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 1);  
	    cell.setCellValue("课程平台");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 2);  
	    cell.setCellValue("课程性质");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 3);  
	    cell.setCellValue("课程类别");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 4);  
	    cell.setCellValue("课程归属");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 5);  
	    cell.setCellValue("课程名");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 6);  
	    cell.setCellValue("学分");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 7);  
	    cell.setCellValue("总学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 8);  
	    cell.setCellValue("问题说明");  
	    cell.setCellStyle(style1);
	    
	    for(int i=0;i<keyCouEmptyList.size();i++){
	    	row = sheet.createRow((int) i+1);
	        
	    	Curriculum curriculum = keyCouEmptyList.get(i);
	        cell = row.createCell((short) 0);  
	        cell.setCellValue(curriculum.getCollege().getCollegeCname());  
	        cell.setCellStyle(style2);  
	  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue(curriculum.getCurriculumpingtai());  
	        cell.setCellStyle(style2);  
	  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue(curriculum.getNatureOfCourse().getNatureOfCoursename());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 3);  
	        cell.setCellValue(curriculum.getCourseLei());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 4);  
	        cell.setCellValue(curriculum.getCourseCategory());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 5);  
	        cell.setCellValue(curriculum.getCurriculumCname());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 6);  
	        cell.setCellValue(curriculum.getCredit());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 7);  
	        cell.setCellValue(curriculum.getHoursOfALL());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 8);  
	        cell.setCellValue("关键课程信息为空！");  
	        cell.setCellStyle(style2); 
	        
	    }
	    wb.write(outputStream);
	    outputStream.flush();  
	    outputStream.close();
	}
	
	private void ExportNoKeyCouEmptyExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("sheet1");
	    sheet.setColumnWidth((short) 0, (short) 5500);
	    sheet.setColumnWidth((short) 1, (short) 4000);
	    sheet.setColumnWidth((short) 2, (short) 4000);
	    sheet.setColumnWidth((short) 3, (short) 4000);
	    sheet.setColumnWidth((short) 4, (short) 4000);
	    sheet.setColumnWidth((short) 5, (short) 4500);
	    sheet.setColumnWidth((short) 6, (short) 2000);
	    sheet.setColumnWidth((short) 7, (short) 2000);
	    sheet.setColumnWidth((short) 8, (short) 5000);
	    HSSFRow row = sheet.createRow((int) 0);  
	    // 第四步，创建单元格，并设置值表头 设置表头居中  
	    HSSFCellStyle style1 = wb.createCellStyle();  
	    style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
	    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
	    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
	    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
	    
	    HSSFCell cell = row.createCell((short) 0);  
	    cell.setCellValue("开课学院");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 1);  
	    cell.setCellValue("课程平台");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 2);  
	    cell.setCellValue("课程性质");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 3);  
	    cell.setCellValue("课程类别");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 4);  
	    cell.setCellValue("课程归属");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 5);  
	    cell.setCellValue("课程名");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 6);  
	    cell.setCellValue("学分");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 7);  
	    cell.setCellValue("总学时");  
	    cell.setCellStyle(style1); 
	    
	    cell = row.createCell((short) 8);  
	    cell.setCellValue("问题说明");  
	    cell.setCellStyle(style1);	    
	    
	    wb.write(outputStream);
	    outputStream.flush();  
	    outputStream.close();
	}
	
	
	public void exportCreditEmptyExcel(String collegeid) {	//导出专业总学分为空专业
		try{
			List<ScoreThreshold> CreditEmptyList = errorDiscDao.findCreditEmpty(collegeid);
				//根据选择的学院查找对应专业总学分为空的专业			
				HttpServletResponse response = ServletActionContext.getResponse();  
	            //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	                //2.2 设置以下载方式打开文件  
	            response.setHeader("Content-Disposition", "attachment;filename="+new String("关键课程信息为空课程.xls".getBytes(),"ISO-8859-1") );
	            OutputStream outputStream = response.getOutputStream();
	            if(CreditEmptyList != null){
	            	ExportCreditEmptyExcel(CreditEmptyList,outputStream);
	            }else{
	            	ExportNoCreditEmptyExcel(outputStream);
	            }
	            if(outputStream != null){
	            	outputStream.close();  
	            }
			
		} catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}

	private void ExportCreditEmptyExcel(List<ScoreThreshold> creditEmptyList, OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("sheet1");
	    sheet.setColumnWidth((short) 0, (short) 6000);
	    sheet.setColumnWidth((short) 1, (short) 5000);
	    sheet.setColumnWidth((short) 2, (short) 5000);
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
	    cell.setCellValue("学院");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 1);  
	    cell.setCellValue("专业");  
	    cell.setCellStyle(style1);  
	    
	    cell = row.createCell((short) 2);  
	    cell.setCellValue("问题说明");  
	    cell.setCellStyle(style1);
	
	    
	    for(int i=0;i<creditEmptyList.size();i++){
	    	row = sheet.createRow((int) i+1);
	        
	    	ScoreThreshold scoreThreshold = creditEmptyList.get(i);
	        cell = row.createCell((short) 0);  
	        cell.setCellValue(scoreThreshold.getDepartment().getCollege().getCollegeCname());  
	        cell.setCellStyle(style2);  
 
	        cell = row.createCell((short) 1);  
	        cell.setCellValue(scoreThreshold.getDepartment().getDepartmentCname());  
	        cell.setCellStyle(style2); 
	        
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("专业总学分为空！");  
	        cell.setCellStyle(style2); 
	    }
	    wb.write(outputStream);
	    outputStream.flush();  
	    outputStream.close();
	}
	
	private void ExportNoCreditEmptyExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("sheet1");
	    sheet.setColumnWidth((short) 0, (short) 6000);
	    sheet.setColumnWidth((short) 1, (short) 5000);
	    sheet.setColumnWidth((short) 2, (short) 5000);
	    HSSFRow row = sheet.createRow((int) 0);  
	    // 第四步，创建单元格，并设置值表头 设置表头居中  
	    HSSFCellStyle style1 = wb.createCellStyle();  
	    style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
	    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
	    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
	    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
	    
	    HSSFCell cell = row.createCell((short) 0);  
	    cell.setCellValue("学院");  
	    cell.setCellStyle(style1);  
	
	    cell = row.createCell((short) 1);  
	    cell.setCellValue("专业");  
	    cell.setCellStyle(style1);  
	    
	    cell = row.createCell((short) 2);  
	    cell.setCellValue("问题说明");  
	    cell.setCellStyle(style1);	    
	    
	    wb.write(outputStream);
	    outputStream.flush();  
	    outputStream.close();
	}
	
	private PTBasicInformationDao ptBasicInformationDao;
	
	public void setPtBasicInformationDao(PTBasicInformationDao ptBasicInformationDao) {
		this.ptBasicInformationDao = ptBasicInformationDao;
	}
	public List<TotalCredit> findtotalcredit()
	{
		List<TotalCredit> totalCreditlist = new ArrayList<TotalCredit>();
		
		String[] collegenum ={"01","02","03","04","05","06","07","08","09","10","11","12","13","20"};
		for(int i=0;i < collegenum.length;i++)
		{
			College collegeid = new College();
			collegeid.setCollegeid(collegenum[i]);
			List<Department> departmentnum = ptBasicInformationDao.findCollegeProNum(collegenum[i]);
			for(int di=0;di<departmentnum.size();di++)
			{
				
				Department departmentnumid = new Department();
				departmentnumid = departmentnum.get(di);
				String pid = (String)departmentnumid.getDepartmentid();
				
				List<List<TheoreticalLesson>> lilunkelist = errorDiscDao.findlilunke(pid);//理论课
				List<List<PracticeLesson>> shijiankelist = errorDiscDao.findshijianke(pid);//实践课
				List<Professional> Professionalnum = errorDiscDao.findzhuanyenum(pid);
				List<ScoreThreshold> scorelist = ptBasicInformationDao.findscore(pid);//最低毕业学分学分
				

				if(lilunkelist != null && lilunkelist.size() != 1)
				{
					float lilunkescore = 0;
					float shijiankescore = 0;
					float totalscore = 0;
					for(int li = 0;li<lilunkelist.size();li++)
					{
						List<TheoreticalLesson> theoreticalLessonlsit = lilunkelist.get(li);
						for(int lj=0;lj<theoreticalLessonlsit.size();lj++)
						{
							TheoreticalLesson theoreticalLesson = theoreticalLessonlsit.get(lj);
							lilunkescore += Float.valueOf(theoreticalLesson.getCurriculum().getCredit());
						}

						List<PracticeLesson> practiceLessonlist = shijiankelist.get(li);
						for(int sj=0;sj<practiceLessonlist.size();sj++)
						{
							PracticeLesson practiceLesson = practiceLessonlist.get(sj);
							shijiankescore += Float.valueOf(practiceLesson.getCurriculum().getCredit());
						}
						BigDecimal b = new BigDecimal(shijiankescore);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						totalscore = lilunkescore+f1;
						TotalCredit totalCredit = new TotalCredit();
						ScoreThreshold score = scorelist.get(0);
						/*****************************应修学分不为null或者0的时候***************************************/
						if(score != null && score.getScore() != null && !score.getScore().equals("") && !score.getScore().equals("0"))
						{
							if(totalscore <Integer.valueOf(score.getScore()) || Integer.valueOf(score.getScore())== 0 || totalscore ==0)
							{
									totalCredit.setCollege(collegeid);
									totalCredit.setDepartment(departmentnumid);
									totalCredit.setProfessional(Professionalnum.get(li));//有方向的
									totalCredit.setTotalCredit(totalscore);
									totalCredit.setTheoTotalCre(lilunkescore);
									totalCredit.setPracTotal(f1);
									totalCredit.setShouldCre(Float.valueOf(score.getScore()));
									totalCreditlist.add(totalCredit);
							}
						}
						else
						{/************应修学分为null或者0的时候**********************/
								totalCredit.setCollege(collegeid);
								totalCredit.setDepartment(departmentnumid);
								totalCredit.setProfessional(Professionalnum.get(li));//有方向的
								totalCredit.setTotalCredit(totalscore);
								totalCredit.setTheoTotalCre(lilunkescore);
								totalCredit.setPracTotal(f1);
								totalCredit.setShouldCre(Float.valueOf(0));
								totalCreditlist.add(totalCredit);
						}

					}
				}
				else 
				{
					float lilunkescore = 0;
					float shijiankescore = 0;
					float totalscore = 0;
					List<TheoreticalLesson> theoreticalLessonlsit = lilunkelist.get(0);
					for(int li=0;li<theoreticalLessonlsit.size();li++)
					{
						TheoreticalLesson theoreticalLesson = theoreticalLessonlsit.get(li);
						lilunkescore += Float.valueOf(theoreticalLesson.getCurriculum().getCredit());
					}
					List<PracticeLesson> practiceLessonlist = shijiankelist.get(0);
					for(int sj=0;sj<practiceLessonlist.size();sj++)
					{
						PracticeLesson practiceLesson = practiceLessonlist.get(sj);
						shijiankescore += Float.valueOf(practiceLesson.getCurriculum().getCredit());
					}
					BigDecimal b = new BigDecimal(shijiankescore);  
					float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					totalscore = lilunkescore+f1;
					TotalCredit totalCredit = new TotalCredit();
					ScoreThreshold score = scorelist.get(0);
					if(score != null && score.getScore() != null && !score.getScore().equals("") && !score.getScore().equals("0"))
					{
						if(totalscore < Integer.valueOf(score.getScore()) || Integer.valueOf(score.getScore())== 0 || totalscore ==0)
						{
							totalCredit.setCollege(collegeid);
							totalCredit.setDepartment(departmentnumid);
							totalCredit.setTotalCredit(totalscore);
							totalCredit.setTheoTotalCre(lilunkescore);
							totalCredit.setPracTotal(f1);
							totalCredit.setShouldCre(Float.valueOf(score.getScore()));
							totalCreditlist.add(totalCredit);
						}
					}
					else
					{
						totalCredit.setCollege(collegeid);
						totalCredit.setDepartment(departmentnumid);
						totalCredit.setTotalCredit(totalscore);
						totalCredit.setTheoTotalCre(lilunkescore);
						totalCredit.setPracTotal(f1);
						totalCredit.setShouldCre(Float.valueOf(0));
						totalCreditlist.add(totalCredit);
					}
				}
			}
		}
		return totalCreditlist;
	}
		
	public List<TotalCredit> findxueyuancredit(String collegeid)
	{
		List<TotalCredit> totalCreditlist = new ArrayList<TotalCredit>();
		
			College college = new College();
			college.setCollegeid(collegeid);
			List<Department> departmentnum = ptBasicInformationDao.findCollegeProNum(collegeid);
			for(int di=0;di<departmentnum.size();di++)
			{
				
				Department departmentnumid = new Department();
				departmentnumid = departmentnum.get(di);
				String pid = (String)departmentnumid.getDepartmentid();
				
				List<List<TheoreticalLesson>> lilunkelist = errorDiscDao.findlilunke(pid);//理论课
				List<List<PracticeLesson>> shijiankelist = errorDiscDao.findshijianke(pid);//实践课
				List<Professional> Professionalnum = errorDiscDao.findzhuanyenum(pid);
				List<ScoreThreshold> scorelist = ptBasicInformationDao.findscore(pid);//最低毕业学分学分
				

				if(lilunkelist != null && lilunkelist.size() != 1)
				{
					float lilunkescore = 0;
					float shijiankescore = 0;
					float totalscore = 0;
					for(int li = 0;li<lilunkelist.size();li++)
					{
						List<TheoreticalLesson> theoreticalLessonlsit = lilunkelist.get(li);
						for(int lj=0;lj<theoreticalLessonlsit.size();lj++)
						{
							TheoreticalLesson theoreticalLesson = theoreticalLessonlsit.get(lj);
							lilunkescore += Float.valueOf(theoreticalLesson.getCurriculum().getCredit());
						}

						List<PracticeLesson> practiceLessonlist = shijiankelist.get(li);
						for(int sj=0;sj<practiceLessonlist.size();sj++)
						{
							PracticeLesson practiceLesson = practiceLessonlist.get(sj);
							shijiankescore += Float.valueOf(practiceLesson.getCurriculum().getCredit());
						}
						BigDecimal b = new BigDecimal(shijiankescore);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						totalscore = lilunkescore+f1;
						TotalCredit totalCredit = new TotalCredit();
						ScoreThreshold score = scorelist.get(0);
						/*****************************应修学分不为null或者0的时候***************************************/
						if(score != null && score.getScore() != null && !score.getScore().equals("") && !score.getScore().equals("0"))
						{
							if(totalscore <Integer.valueOf(score.getScore()) || Integer.valueOf(score.getScore())== 0 || totalscore ==0)
							{
									totalCredit.setCollege(college);
									totalCredit.setDepartment(departmentnumid);
									totalCredit.setProfessional(Professionalnum.get(li));//有方向的
									totalCredit.setTotalCredit(totalscore);
									totalCredit.setTheoTotalCre(lilunkescore);
									totalCredit.setPracTotal(f1);
									totalCredit.setShouldCre(Float.valueOf(score.getScore()));
									totalCreditlist.add(totalCredit);
							}
						}
						else
						{/************应修学分为null或者0的时候**********************/
								totalCredit.setCollege(college);
								totalCredit.setDepartment(departmentnumid);
								totalCredit.setProfessional(Professionalnum.get(li));//有方向的
								totalCredit.setTotalCredit(totalscore);
								totalCredit.setTheoTotalCre(lilunkescore);
								totalCredit.setPracTotal(f1);
								totalCredit.setShouldCre(Float.valueOf(0));
								totalCreditlist.add(totalCredit);
						}

					}
				}
				else 
				{
					float lilunkescore = 0;
					float shijiankescore = 0;
					float totalscore = 0;
					List<TheoreticalLesson> theoreticalLessonlsit = lilunkelist.get(0);
					for(int li=0;li<theoreticalLessonlsit.size();li++)
					{

						TheoreticalLesson theoreticalLesson = theoreticalLessonlsit.get(li);
						lilunkescore += Float.valueOf(theoreticalLesson.getCurriculum().getCredit());
					}
					List<PracticeLesson> practiceLessonlist = shijiankelist.get(0);
					for(int sj=0;sj<practiceLessonlist.size();sj++)
					{
						PracticeLesson practiceLesson = practiceLessonlist.get(sj);
						shijiankescore += Float.valueOf(practiceLesson.getCurriculum().getCredit());
					}
					BigDecimal b = new BigDecimal(shijiankescore);  
					float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					totalscore = lilunkescore+f1;
					TotalCredit totalCredit = new TotalCredit();
					ScoreThreshold score = scorelist.get(0);
					if(score != null && score.getScore() != null && !score.getScore().equals("") && !score.getScore().equals("0"))
					{
						if(totalscore < Integer.valueOf(score.getScore()) || Integer.valueOf(score.getScore())== 0 || totalscore ==0)
						{
							totalCredit.setCollege(college);
							totalCredit.setDepartment(departmentnumid);
							totalCredit.setTotalCredit(totalscore);
							totalCredit.setTheoTotalCre(lilunkescore);
							totalCredit.setPracTotal(f1);
							totalCredit.setShouldCre(Float.valueOf(score.getScore()));
							totalCreditlist.add(totalCredit);
						}
					}
					else
					{
						totalCredit.setCollege(college);
						totalCredit.setDepartment(departmentnumid);
						totalCredit.setTotalCredit(totalscore);
						totalCredit.setTheoTotalCre(lilunkescore);
						totalCredit.setPracTotal(f1);
						totalCredit.setShouldCre(Float.valueOf(0));
						totalCreditlist.add(totalCredit);
					}
				}
			}
		return totalCreditlist;
	}			
	public void findTotalCre(String collegeid){
		if(collegeid == "-1"){//查询 全校
			List<TotalCredit>  totalCreditlsit = findtotalcredit();
			ServletActionContext.getRequest().setAttribute("totalCreditlsit", totalCreditlsit);
		}
		else{//某个学院的
			List<TotalCredit>  totalCreditlsit = findxueyuancredit(collegeid);
			ServletActionContext.getRequest().setAttribute("totalCreditlsit", totalCreditlsit);
		}
	}
	public void findAvePer(String collegeid) {
		setweekhour();
		if(weekHour == null){
			weekHour = (float)15;
		}
		if(collegeid == "-1"){//查询 全校
			List<AvePer>  avePerlsit = findAvePerlsit();
			ServletActionContext.getRequest().setAttribute("avePerlsit", avePerlsit);
		}
		else{//某个学院的
			List<AvePer>  avePerlsit = findxueyuanhour(collegeid);
			ServletActionContext.getRequest().setAttribute("avePerlsit", avePerlsit);
		}
		
	}
	
	private List<AvePer> findxueyuanhour(String collegeid) {
		College college = new College();
		college.setCollegeid(collegeid);
		List<AvePer> AvePertlist = new ArrayList<AvePer>();
		List<Department> departmentnum = ptBasicInformationDao.findCollegeProNum(collegeid);
		for(int di=0;di<departmentnum.size();di++)
		{
			
			Department departmentnumid = new Department();
			departmentnumid = departmentnum.get(di);
			String pid = (String)departmentnumid.getDepartmentid();
			
			int xuenian = 0;
			PTBasicInformation ptBasicInformation = errorDiscDao.findPTBasicInformation(pid);
			if(ptBasicInformation != null)
			{
				
				
				if(ptBasicInformation.getLearningTime().equals("四年")) 
					xuenian = 4;
				if(ptBasicInformation.getLearningTime().equals("五年"))
					xuenian =5;
				List<List<TheoreticalLesson>> gexueqililunke = errorDiscDao.findgexueqililunke(pid,xuenian);
				List<TrainingEvents> peiyangshijian = errorDiscDao.findpeiyangshijian(pid);
				
				
				float[] gexuqiscore = new float[xuenian*2];
				for(int gi =0;gi<gexueqililunke.size();gi++)//计算每个学期的学分
				{
					List<TheoreticalLesson> newgexueqi = gexueqililunke.get(gi);//获得每个学期的课程的list
					for(int gj=0;gj<newgexueqi.size();gj++)//得到每个学期的课程
					{
						TheoreticalLesson Theo = newgexueqi.get(gj);
						gexuqiscore[gi] += Float.valueOf(Theo.getCurriculum().getCredit());
					}
				}
				
				/**********************获得理论教学学时数******************************/
				float[] wcount=new float[xuenian*2];
				if(peiyangshijian != null && peiyangshijian.size() != 1)
				{
					TrainingEvents week=peiyangshijian.get(0);
					if(week != null)
					{
						if(xuenian*2==8)
						{
							if(week.getSemester1() != null)
								wcount[0]=Integer.valueOf(week.getSemester1());
							else wcount[0]= 0;
							
							if(week.getSemester2() != null)
								wcount[1]=Integer.valueOf(week.getSemester2());
							else wcount[1]= 0;
							
							if(week.getSemester3() != null)
								wcount[2]=Integer.valueOf(week.getSemester3());
							else wcount[2]=0;
							
							if(week.getSemester4() != null)
								wcount[3]=Integer.valueOf(week.getSemester4());
							else wcount[3] = 0;
							
							if(week.getSemester5() != null)
								wcount[4]=Integer.valueOf(week.getSemester5());
							else wcount[4]=0;
							
							if(week.getSemester6() != null)
								wcount[5]=Integer.valueOf(week.getSemester6());
							else wcount[5] =0;
							
							if(week.getSemester7() != null)
								wcount[6]=Integer.valueOf(week.getSemester7());
							else wcount[6] =0;
							
							if(week.getSemester8() != null)
								wcount[7]=Integer.valueOf(week.getSemester8());
							else wcount[7] =0;
						}
						if(xuenian*2==10)
						{
							wcount[0]=Integer.valueOf(week.getSemester1());
							wcount[1]=Integer.valueOf(week.getSemester2());
							wcount[2]=Integer.valueOf(week.getSemester3());
							wcount[3]=Integer.valueOf(week.getSemester4());
							wcount[4]=Integer.valueOf(week.getSemester5());
							wcount[5]=Integer.valueOf(week.getSemester6());
							wcount[6]=Integer.valueOf(week.getSemester7());
							wcount[7]=Integer.valueOf(week.getSemester8());
							wcount[8]=Integer.valueOf(week.getSemester9());
							wcount[9]=Integer.valueOf(week.getSemester10());
						}
					}
					else
					{
						for(int fi=0;fi<xuenian*2;fi++)
						{
							wcount[fi] = 0;
						}
					}
				}

				
				
				if(xuenian*2 == 8)
				{
					AvePer avePer= new AvePer();
					if(wcount[0] != 0)
					{
						float weekhour =(float)gexuqiscore[0]*16/wcount[0];//周学时
						BigDecimal b = new BigDecimal(weekhour);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm0(f1);
					}
					else avePer.setTerm0(0);
					if(wcount[1] != 0)
					{
						float weekhour1 =(float)gexuqiscore[1]*16/wcount[1];//周学时
						BigDecimal b = new BigDecimal(weekhour1);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm1(f1);
					}
					else avePer.setTerm1(0);
					
					if(wcount[2] != 0)
					{
						float weekhour2 =(float)gexuqiscore[2]*16/wcount[2];//周学时
						BigDecimal b = new BigDecimal(weekhour2);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm2(f1);
					}
					else avePer.setTerm2(0);
					
					if(wcount[3] != 0)
					{
						float weekhour3 =(float)gexuqiscore[3]*16/wcount[3];//周学时
						BigDecimal b = new BigDecimal(weekhour3);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm3(f1);
					}
					else avePer.setTerm3(0);
					
					if(wcount[4] != 0)
					{
						float weekhour4 =(float)gexuqiscore[4]*16/wcount[4];//周学时
						BigDecimal b = new BigDecimal(weekhour4);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm4(f1);
					}
					else avePer.setTerm4(0);
					
					if(wcount[5] != 0)
					{
						float weekhour5 =(float)gexuqiscore[5]*16/wcount[5];//周学时
						BigDecimal b = new BigDecimal(weekhour5);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm5(f1);
					}
					else avePer.setTerm5(0);
					
					if(wcount[6] != 0)
					{
						float weekhour6 =(float)gexuqiscore[6]*16/wcount[6];//周学时
						BigDecimal b = new BigDecimal(weekhour6);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm6(f1);
					}
					else avePer.setTerm6(0);
					
					if(wcount[7] != 0)
					{
						float weekhour7 =(float)gexuqiscore[7]*16/wcount[7];//周学时
						BigDecimal b = new BigDecimal(weekhour7);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						 avePer.setTerm7(weekhour7);
					}
					else avePer.setTerm7(0);
					
					avePer.setCollege(college);
					avePer.setDepartment(departmentnumid);
					avePer.setWeekhour(String.valueOf(weekHour));
					
					AvePertlist.add(avePer);
				}
				
				if(xuenian*2 == 10)
				{
					AvePer avePer= new AvePer();
					if(wcount[0] != 0)
					{
						float weekhour =(float)gexuqiscore[0]*16/wcount[0];//周学时
						BigDecimal b = new BigDecimal(weekhour);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm0(f1);
					}
					else avePer.setTerm0(0);
					if(wcount[1] != 0)
					{
						float weekhour1 =(float)gexuqiscore[1]*16/wcount[1];//周学时
						BigDecimal b = new BigDecimal(weekhour1);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm1(f1);
					}
					else avePer.setTerm1(0);
					
					if(wcount[2] != 0)
					{
						float weekhour2 =(float)gexuqiscore[2]*16/wcount[2];//周学时
						BigDecimal b = new BigDecimal(weekhour2);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm2(f1);
					}
					else avePer.setTerm2(0);
					
					if(wcount[3] != 0)
					{
						float weekhour3 =(float)gexuqiscore[3]*16/wcount[3];//周学时
						BigDecimal b = new BigDecimal(weekhour3);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm3(f1);
					}
					else avePer.setTerm3(0);
					
					if(wcount[4] != 0)
					{
						float weekhour4 =(float)gexuqiscore[4]*16/wcount[4];//周学时
						BigDecimal b = new BigDecimal(weekhour4);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm4(f1);
					}
					else avePer.setTerm4(0);
					
					if(wcount[5] != 0)
					{
						float weekhour5 =(float)gexuqiscore[5]*16/wcount[5];//周学时
						BigDecimal b = new BigDecimal(weekhour5);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm5(f1);
					}
					else avePer.setTerm5(0);
					
					if(wcount[6] != 0)
					{
						float weekhour6 =(float)gexuqiscore[6]*16/wcount[6];//周学时
						BigDecimal b = new BigDecimal(weekhour6);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm6(f1);
					}
					else avePer.setTerm6(0);
					
					if(wcount[7] != 0)
					{
						float weekhour7 =(float)gexuqiscore[7]*16/wcount[7];//周学时
						BigDecimal b = new BigDecimal(weekhour7);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						 avePer.setTerm7(weekhour7);
					}
					else avePer.setTerm7(0);
					
					if(wcount[8] != 0)
					{
						float weekhour8 =(float)gexuqiscore[8]*16/wcount[8];//周学时
						BigDecimal b = new BigDecimal(weekhour8);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm8(f1);
					}
					else avePer.setTerm8(0);
					
					if(wcount[9] != 0)
					{
						float weekhour9 =(float)gexuqiscore[9]*16/wcount[9];//周学时
						BigDecimal b = new BigDecimal(weekhour9);  
						float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
						avePer.setTerm9(f1);
					}
					else avePer.setTerm9(0);
					
					avePer.setCollege(college);
					avePer.setDepartment(departmentnumid);
					avePer.setWeekhour(String.valueOf(weekHour));
					
					AvePertlist.add(avePer);
				}
				
			}
			else 
			{	
				AvePer avePer= new AvePer();
				avePer.setTerm0(0);
				avePer.setTerm1(0);
				avePer.setTerm2(0);
				avePer.setTerm3(0);
				avePer.setTerm4(0);
				avePer.setTerm5(0);
				avePer.setTerm6(0);
				avePer.setTerm7(0);
				avePer.setTerm8(0);
				avePer.setTerm9(0);
				avePer.setCollege(college);
				avePer.setDepartment(departmentnumid);
				avePer.setWeekhour(String.valueOf(weekHour));
				AvePertlist.add(avePer);
			}
		}
		return AvePertlist;
	}
	
	private List<AvePer> findAvePerlsit() {
		
		List<AvePer> AvePertlist = new ArrayList<AvePer>();
		
		String[] collegenum ={"01","02","03","04","05","06","07","08","09","10","11","12","13","20"};
		for(int i=0;i < collegenum.length;i++)
		{
			College collegeid = new College();
			collegeid.setCollegeid(collegenum[i]);
			List<Department> departmentnum = ptBasicInformationDao.findCollegeProNum(collegenum[i]);
			for(int di=0;di<departmentnum.size();di++)
			{
				
				Department departmentnumid = new Department();
				departmentnumid = departmentnum.get(di);
				String pid = (String)departmentnumid.getDepartmentid();
				
				int xuenian = 0;
				PTBasicInformation ptBasicInformation = errorDiscDao.findPTBasicInformation(pid);
				if(ptBasicInformation != null)
				{
					
					
					if(ptBasicInformation.getLearningTime().equals("四年")) 
						xuenian = 4;
					if(ptBasicInformation.getLearningTime().equals("五年"))
						xuenian =5;
					List<List<TheoreticalLesson>> gexueqililunke = errorDiscDao.findgexueqililunke(pid,xuenian);
					List<TrainingEvents> peiyangshijian = errorDiscDao.findpeiyangshijian(pid);
					
					
					float[] gexuqiscore = new float[xuenian*2];
					for(int gi =0;gi<gexueqililunke.size();gi++)//计算每个学期的学分
					{
						List<TheoreticalLesson> newgexueqi = gexueqililunke.get(gi);//获得每个学期的课程的list
						for(int gj=0;gj<newgexueqi.size();gj++)//得到每个学期的课程
						{
							TheoreticalLesson Theo = newgexueqi.get(gj);
							gexuqiscore[gi] += Float.valueOf(Theo.getCurriculum().getCredit());
						}
					}
					
					/**********************获得理论教学学时数******************************/
					float[] wcount=new float[xuenian*2];
					if(peiyangshijian != null && peiyangshijian.size() != 1)
					{
						TrainingEvents week=peiyangshijian.get(0);
						if(week != null)
						{
							if(xuenian*2==8)
							{
								if(week.getSemester1() != null)
									wcount[0]=Integer.valueOf(week.getSemester1());
								else wcount[0]= 0;
								
								if(week.getSemester2() != null)
									wcount[1]=Integer.valueOf(week.getSemester2());
								else wcount[1]= 0;
								
								if(week.getSemester3() != null)
									wcount[2]=Integer.valueOf(week.getSemester3());
								else wcount[2]=0;
								
								if(week.getSemester4() != null)
									wcount[3]=Integer.valueOf(week.getSemester4());
								else wcount[3] = 0;
								
								if(week.getSemester5() != null)
									wcount[4]=Integer.valueOf(week.getSemester5());
								else wcount[4]=0;
								
								if(week.getSemester6() != null)
									wcount[5]=Integer.valueOf(week.getSemester6());
								else wcount[5] =0;
								
								if(week.getSemester7() != null)
									wcount[6]=Integer.valueOf(week.getSemester7());
								else wcount[6] =0;
								
								if(week.getSemester8() != null)
									wcount[7]=Integer.valueOf(week.getSemester8());
								else wcount[7] =0;
							}
							if(xuenian*2==10)
							{
								if(week.getSemester1() != null)
									wcount[0]=Integer.valueOf(week.getSemester1());
								else wcount[0]= 0;
								
								if(week.getSemester2() != null)
									wcount[1]=Integer.valueOf(week.getSemester2());
								else wcount[1]= 0;
								
								if(week.getSemester3() != null)
									wcount[2]=Integer.valueOf(week.getSemester3());
								else wcount[2]=0;
								
								if(week.getSemester4() != null)
									wcount[3]=Integer.valueOf(week.getSemester4());
								else wcount[3] = 0;
								
								if(week.getSemester5() != null)
									wcount[4]=Integer.valueOf(week.getSemester5());
								else wcount[4]=0;
								
								if(week.getSemester6() != null)
									wcount[5]=Integer.valueOf(week.getSemester6());
								else wcount[5] =0;
								
								if(week.getSemester7() != null)
									wcount[6]=Integer.valueOf(week.getSemester7());
								else wcount[6] =0;
								
								if(week.getSemester8() != null)
									wcount[7]=Integer.valueOf(week.getSemester8());
								else wcount[7] =0;
								if(week.getSemester8() != null)
									wcount[8]=Integer.valueOf(week.getSemester9());
								else wcount[8]=0;
								if(week.getSemester9() != null)
									wcount[9]=Integer.valueOf(week.getSemester10());
								else  wcount[9]=0;
							}
						}
						else
						{
							for(int fi=0;fi<xuenian*2;fi++)
							{
								wcount[fi] = 0;
							}
						}
					}
	
					
					
					if(xuenian*2 == 8)
					{
						AvePer avePer= new AvePer();
						if(wcount[0] != 0)
						{
							float weekhour =(float)gexuqiscore[0]*16/wcount[0];//周学时
							BigDecimal b = new BigDecimal(weekhour);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm0(f1);
						}
						else avePer.setTerm0(0);
						if(wcount[1] != 0)
						{
							float weekhour1 =(float)gexuqiscore[1]*16/wcount[1];//周学时
							BigDecimal b = new BigDecimal(weekhour1);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm1(f1);
						}
						else avePer.setTerm1(0);
						
						if(wcount[2] != 0)
						{
							float weekhour2 =(float)gexuqiscore[2]*16/wcount[2];//周学时
							BigDecimal b = new BigDecimal(weekhour2);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm2(f1);
						}
						else avePer.setTerm2(0);
						
						if(wcount[3] != 0)
						{
							float weekhour3 =(float)gexuqiscore[3]*16/wcount[3];//周学时
							BigDecimal b = new BigDecimal(weekhour3);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm3(f1);
						}
						else avePer.setTerm3(0);
						
						if(wcount[4] != 0)
						{
							float weekhour4 =(float)gexuqiscore[4]*16/wcount[4];//周学时
							BigDecimal b = new BigDecimal(weekhour4);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm4(f1);
						}
						else avePer.setTerm4(0);
						
						if(wcount[5] != 0)
						{
							float weekhour5 =(float)gexuqiscore[5]*16/wcount[5];//周学时
							BigDecimal b = new BigDecimal(weekhour5);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm5(f1);
						}
						else avePer.setTerm5(0);
						
						if(wcount[6] != 0)
						{
							float weekhour6 =(float)gexuqiscore[6]*16/wcount[6];//周学时
							BigDecimal b = new BigDecimal(weekhour6);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm6(f1);
						}
						else avePer.setTerm6(0);
						
						if(wcount[7] != 0)
						{
							float weekhour7 =(float)gexuqiscore[7]*16/wcount[7];//周学时
							BigDecimal b = new BigDecimal(weekhour7);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							 avePer.setTerm7(weekhour7);
						}
						else avePer.setTerm7(0);
						
						avePer.setCollege(collegeid);
						avePer.setDepartment(departmentnumid);
						avePer.setWeekhour(String.valueOf(weekHour));;
						
						AvePertlist.add(avePer);
					}
					
					if(xuenian*2 == 10)
					{
						AvePer avePer= new AvePer();
						if(wcount[0] != 0)
						{
							float weekhour =(float)gexuqiscore[0]*16/wcount[0];//周学时
							BigDecimal b = new BigDecimal(weekhour);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm0(f1);
						}
						else avePer.setTerm0(0);
						if(wcount[1] != 0)
						{
							float weekhour1 =(float)gexuqiscore[1]*16/wcount[1];//周学时
							BigDecimal b = new BigDecimal(weekhour1);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm1(f1);
						}
						else avePer.setTerm1(0);
						
						if(wcount[2] != 0)
						{
							float weekhour2 =(float)gexuqiscore[2]*16/wcount[2];//周学时
							BigDecimal b = new BigDecimal(weekhour2);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm2(f1);
						}
						else avePer.setTerm2(0);
						
						if(wcount[3] != 0)
						{
							float weekhour3 =(float)gexuqiscore[3]*16/wcount[3];//周学时
							BigDecimal b = new BigDecimal(weekhour3);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm3(f1);
						}
						else avePer.setTerm3(0);
						
						if(wcount[4] != 0)
						{
							float weekhour4 =(float)gexuqiscore[4]*16/wcount[4];//周学时
							BigDecimal b = new BigDecimal(weekhour4);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm4(f1);
						}
						else avePer.setTerm4(0);
						
						if(wcount[5] != 0)
						{
							float weekhour5 =(float)gexuqiscore[5]*16/wcount[5];//周学时
							BigDecimal b = new BigDecimal(weekhour5);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm5(f1);
						}
						else avePer.setTerm5(0);
						
						if(wcount[6] != 0)
						{
							float weekhour6 =(float)gexuqiscore[6]*16/wcount[6];//周学时
							BigDecimal b = new BigDecimal(weekhour6);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm6(f1);
						}
						else avePer.setTerm6(0);
						
						if(wcount[7] != 0)
						{
							float weekhour7 =(float)gexuqiscore[7]*16/wcount[7];//周学时
							BigDecimal b = new BigDecimal(weekhour7);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							 avePer.setTerm7(weekhour7);
						}
						else avePer.setTerm7(0);
						
						if(wcount[8] != 0)
						{
							float weekhour8 =(float)gexuqiscore[8]*16/wcount[8];//周学时
							BigDecimal b = new BigDecimal(weekhour8);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm8(f1);
						}
						else avePer.setTerm8(0);
						
						if(wcount[9] != 0)
						{
							float weekhour9 =(float)gexuqiscore[9]*16/wcount[9];//周学时
							BigDecimal b = new BigDecimal(weekhour9);  
							float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
							avePer.setTerm9(f1);
						}
						else avePer.setTerm9(0);
						
						avePer.setCollege(collegeid);
						avePer.setDepartment(departmentnumid);
						avePer.setWeekhour(String.valueOf(weekHour));
						
						AvePertlist.add(avePer);
					}
					
				}
				else 
				{	
					AvePer avePer= new AvePer();
					avePer.setTerm0(0);
					avePer.setTerm1(0);
					avePer.setTerm2(0);
					avePer.setTerm3(0);
					avePer.setTerm4(0);
					avePer.setTerm5(0);
					avePer.setTerm6(0);
					avePer.setTerm7(0);
					avePer.setTerm8(0);
					avePer.setTerm9(0);
					avePer.setCollege(collegeid);
					avePer.setDepartment(departmentnumid);
					avePer.setWeekhour(String.valueOf(weekHour));
					AvePertlist.add(avePer);
				}
			}
		}
		return AvePertlist;
	}
}
