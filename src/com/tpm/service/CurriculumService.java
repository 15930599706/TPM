package com.tpm.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
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
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.NatureOfCourse;
import com.tpm.entity.PageBean;
import com.tpm.entity.User;
import com.tpm.dao.CollegeDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.NatureOfCourseDao;
import com.tpm.dao.UserDao;

@Transactional
public class CurriculumService {
	private CurriculumDao curriculumDao;
	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}
	private CollegeDao collegeDao;
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	private NatureOfCourseDao natureOfCourseDao;
	public void setNatureOfCourseDao(NatureOfCourseDao natureOfCourseDao) {
		this.natureOfCourseDao = natureOfCourseDao;
	}
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void importCurriculumExcel(String path) {
		boolean flag=true; 
		 try {  //上传的是xls的文件
			    //文件流指向excel文件  //
			 	FileInputStream fin=new FileInputStream(path);  
			    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
				HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importCuExcel(totalRow,sheet);
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
			    flag = importCuExcel(totalRow,sheet);
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
	public void exportCurriculumModelExcel() {
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("课程信息模板.xls".getBytes("gb2312"),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}
	public void exportCurriculumExcel() {
		try{
			List<Curriculum> culist = curriculumDao.findAll();
				HttpServletResponse response = ServletActionContext.getResponse();  
                //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	                //2.2 设置以下载方式打开文件  
	            response.setHeader("Content-Disposition", "attachment;filename="+new String("课程信息表.xls".getBytes("gb2312"),"ISO-8859-1") );
	            OutputStream outputStream = response.getOutputStream();
	            if(culist != null){
	            	ExportExcel(culist,outputStream);
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
	public PageBean findcurriculum(Integer currentpage, Curriculum curriculum) {
		return pagebean(currentpage,curriculum);
	}
	public void kcadd(Curriculum curriculum, String tnum) {
		User user = userDao.get(tnum);
		if(user.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			List<NatureOfCourse> natureOfCourselist = natureOfCourseDao.findAll();
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
			ServletActionContext.getRequest().setAttribute("natureOfCourselist", natureOfCourselist);
		}else{
			List<NatureOfCourse> natureOfCourselist = natureOfCourseDao.getDepartment();
			List<College> collegelist = collegeDao.getone( user.getCollege().getCollegeid());
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
			ServletActionContext.getRequest().setAttribute("natureOfCourselist", natureOfCourselist);
		}
	}
	public void addcurriculum(Curriculum curriculum, String tnum) {
		String curriculumid = curriculumidrule(curriculum);
		if(curriculumid.equals("noway")){
			ServletActionContext.getRequest().setAttribute("msg", "课程库已满，请清理不需要的课程后再进行添加！");
		}else{
			curriculum.setCurriculumid(curriculumid);
			User user = userDao.get(tnum);
			if(user.getAdminlevel() == 5){
				curriculum.setNewcurriculum("0");
			}
			curriculumDao.add(curriculum);
			ServletActionContext.getRequest().setAttribute("msg", "新建成功！课程ID为"+curriculumid);
		}
	}
	public PageBean findcurriculumnew(Integer currentpage,
			Curriculum curriculum, User user) {
		return pagebeannew(currentpage,curriculum,user);
	}
	public void updatecurriculum(Curriculum curriculum) {
		curriculumDao.update(curriculum);
		ServletActionContext.getRequest().setAttribute("msg", "更改成功！课程ID为"+curriculum.getCurriculumid());
	}
	public void kcupdate(Curriculum curriculum) {
		Curriculum newcurriculum = curriculumDao.get(curriculum.getCurriculumid());
		ServletActionContext.getRequest().setAttribute("curriculum", newcurriculum);
	}
	public void kcdel(Curriculum curriculum) {
		Curriculum newcurriculum = curriculumDao.get(curriculum.getCurriculumid());
		curriculumDao.delete(newcurriculum);
		ServletActionContext.getRequest().setAttribute("msg", "课程"+curriculum.getCurriculumid()+"已删除！");
	}
	public void kcadddel(Curriculum curriculum) {
		Curriculum newcurriculum = curriculumDao.get(curriculum.getCurriculumid());
		curriculumDao.delete(newcurriculum);
	}
	public void kcupdateadd(Curriculum curriculum) {
		Curriculum newcurriculum = curriculumDao.get(curriculum.getCurriculumid());
		ServletActionContext.getRequest().setAttribute("curriculum", newcurriculum);
		ServletActionContext.getRequest().setAttribute("tag", "no");
	}
	
	
	
	
	
	
	
	
	
	
	
	private PageBean pagebeannew(Integer currentpage, Curriculum curriculum,
			User user) {
		PageBean pageBean = new PageBean();
		int totalCount = 0;
		if(user.getAdminlevel() == 5){
			totalCount = curriculumDao.findcurriculumCount(curriculum);
		}else{
			totalCount = curriculumDao.findcurriculumnewCount(curriculum,user.getCollege());
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
		if(user.getAdminlevel() == 5){
			List<Curriculum> curriculumlist = curriculumDao.findcurriculumT(begin, pageSize,curriculum);
			pageBean.setCurriculumlist(curriculumlist);
		}else{
			List<Curriculum> curriculumlist = curriculumDao.findcurriculumnewT(begin, pageSize,curriculum,user.getCollege());
			pageBean.setCurriculumlist(curriculumlist);
		}
		return pageBean;
	}
	private PageBean pagebean(Integer currentpage, Curriculum curriculum) {
		PageBean pageBean = new PageBean();
		int totalCount = curriculumDao.findcurriculumCount(curriculum);
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
		List<Curriculum> curriculumlist = curriculumDao.findcurriculumT(begin, pageSize,curriculum);
		pageBean.setCurriculumlist(curriculumlist);
		return pageBean;
	}
	private void ExportNoExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        sheet.setColumnWidth((short) 0, (short) 2300);
        sheet.setColumnWidth((short) 1, (short) 3500);
        sheet.setColumnWidth((short) 2, (short) 3500);
        sheet.setColumnWidth((short) 3, (short) 1200);
        sheet.setColumnWidth((short) 4, (short) 2300);
        sheet.setColumnWidth((short) 5, (short) 1800);
        sheet.setColumnWidth((short) 6, (short) 1800);
        sheet.setColumnWidth((short) 7, (short) 2300);
        sheet.setColumnWidth((short) 8, (short) 2300);
        sheet.setColumnWidth((short) 9, (short) 2300);
        sheet.setColumnWidth((short) 10, (short) 2300);
        sheet.setColumnWidth((short) 11, (short) 2300);
        sheet.setColumnWidth((short) 12, (short) 3500);
        sheet.setColumnWidth((short) 13, (short) 2300);
        sheet.setColumnWidth((short) 14, (short) 2300);
        sheet.setColumnWidth((short) 15, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("课程代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("课程中文名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("课程英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("学分");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("开课部门");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("总学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("周学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("课程简介");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("课程类别");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("讲课学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("实验学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("上机学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("课程实践学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("课程性质");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("课程归属");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 15);  
        cell.setCellValue("课程平台");  
        cell.setCellStyle(style1);
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private void ExportExcel(List<Curriculum> culist, OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        sheet.setColumnWidth((short) 0, (short) 2300);
        sheet.setColumnWidth((short) 1, (short) 8000);
        sheet.setColumnWidth((short) 2, (short) 10000);
        sheet.setColumnWidth((short) 3, (short) 1200);
        sheet.setColumnWidth((short) 4, (short) 2300);
        sheet.setColumnWidth((short) 5, (short) 1800);
        sheet.setColumnWidth((short) 6, (short) 1800);
        sheet.setColumnWidth((short) 7, (short) 2300);
        sheet.setColumnWidth((short) 8, (short) 2300);
        sheet.setColumnWidth((short) 9, (short) 2300);
        sheet.setColumnWidth((short) 10, (short) 2300);
        sheet.setColumnWidth((short) 11, (short) 2300);
        sheet.setColumnWidth((short) 12, (short) 3500);
        sheet.setColumnWidth((short) 13, (short) 4600);
        sheet.setColumnWidth((short) 14, (short) 4600);
        sheet.setColumnWidth((short) 15, (short) 4600);
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
        cell.setCellValue("课程代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("课程中文名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("课程英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("学分");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("开课部门");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("总学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("周学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("课程简介");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("课程类别");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("讲课学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("实验学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("上机学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("课程实践学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("课程性质");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("课程归属");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 15);  
        cell.setCellValue("课程平台");  
        cell.setCellStyle(style1);
        
        for(int i=0;i<culist.size();i++){
        	row = sheet.createRow((int) i+1);
            Curriculum curriculum =culist.get(i);
            cell = row.createCell((short) 0);  
            cell.setCellValue(curriculum.getCurriculumid());  
            cell.setCellStyle(style2);  
      
            cell = row.createCell((short) 1);  
            cell.setCellValue(curriculum.getCurriculumCname());  
            cell.setCellStyle(style2);  
      
            cell = row.createCell((short) 2);  
            cell.setCellValue(curriculum.getCurriculumEname());  
            cell.setCellStyle(style2); 
            
            cell = row.createCell((short) 3);  
            cell.setCellValue(curriculum.getCredit());  
            cell.setCellStyle(style2); 
            
            cell = row.createCell((short) 4);  
            cell.setCellValue(curriculum.getCollege().getCollegeid());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 5);  
            cell.setCellValue(curriculum.getHoursOfALL());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 6);  
            cell.setCellValue(curriculum.getHoursOfWeek());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 7);  
            cell.setCellValue(curriculum.getCourseIntroduction());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 8);  
            cell.setCellValue(curriculum.getCourseLei());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 9);  
            cell.setCellValue(curriculum.getHoursOfClass());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 10);  
            cell.setCellValue(curriculum.getHoursOfExp());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 11);  
            cell.setCellValue(curriculum.getHoursOfCom());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 12);  
            cell.setCellValue(curriculum.getHoursOfPractice());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 13);  
            if(curriculum.getNatureOfCourse() != null)
            {
            	cell.setCellValue(curriculum.getNatureOfCourse().getNatureOfCoursename());
            }
            else
            {
            	cell.setCellValue("");
            }
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 14);  
            cell.setCellValue(curriculum.getCourseCategory());  
            cell.setCellStyle(style2);
            
            cell = row.createCell((short) 15);  
            cell.setCellValue(curriculum.getCurriculumpingtai());  
            cell.setCellStyle(style2);
        }
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private void ExportExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        sheet.setColumnWidth((short) 0, (short) 2300);
        sheet.setColumnWidth((short) 1, (short) 3500);
        sheet.setColumnWidth((short) 2, (short) 3500);
        sheet.setColumnWidth((short) 3, (short) 1200);
        sheet.setColumnWidth((short) 4, (short) 2300);
        sheet.setColumnWidth((short) 5, (short) 1800);
        sheet.setColumnWidth((short) 6, (short) 1800);
        sheet.setColumnWidth((short) 7, (short) 2300);
        sheet.setColumnWidth((short) 8, (short) 2300);
        sheet.setColumnWidth((short) 9, (short) 2300);
        sheet.setColumnWidth((short) 10, (short) 2300);
        sheet.setColumnWidth((short) 11, (short) 2300);
        sheet.setColumnWidth((short) 12, (short) 3500);
        sheet.setColumnWidth((short) 13, (short) 2300);
        sheet.setColumnWidth((short) 14, (short) 2300);
        sheet.setColumnWidth((short) 15, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("课程代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("课程中文名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("课程英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("学分");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("开课部门");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("总学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("周学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("课程简介");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("课程类别");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("讲课学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("实验学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("上机学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("课程实践学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("课程性质");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("课程归属");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 15);  
        cell.setCellValue("课程平台");  
        cell.setCellStyle(style1);
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        cell = row.createCell((short) 0);  
        cell.setCellValue("01110031");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("测试课程");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("Test Curriculum");  
        cell.setCellStyle(style2); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("2.0");  
        cell.setCellStyle(style2); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("01");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("32");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("4");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("这是一个用于展示如何填写课程表的课程");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("理论课");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("30");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("2");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("公共选修课");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("科学与技术类");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 15);  
        cell.setCellValue("专业教育平台");  
        cell.setCellStyle(style2);
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private boolean importCuExcel(int totalRow, XSSFSheet sheet) {
		XSSFRow row = null;//对应excel的行  
	    XSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	Curriculum curriculum = new Curriculum();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	curriculum.setCurriculumid(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	curriculum.setCurriculumCname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	curriculum.setCurriculumEname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(3);
		    	changetostring(cell);
		    	curriculum.setCredit(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(5);
		    	changetostring(cell);
		    	curriculum.setHoursOfALL(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(6);
		    	changetostring(cell);
		    	curriculum.setHoursOfWeek(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(7);
		    	changetostring(cell);
		    	curriculum.setCourseIntroduction(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(8);
		    	changetostring(cell);
		    	curriculum.setCourseLei(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(9);
		    	changetostring(cell);
		    	curriculum.setHoursOfClass(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(10);
		    	changetostring(cell);
		    	curriculum.setHoursOfExp(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(11);
		    	changetostring(cell);
		    	curriculum.setHoursOfCom(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(12);
		    	changetostring(cell);
		    	curriculum.setHoursOfPractice(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(14);
		    	changetostring(cell);
		    	curriculum.setCourseCategory(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(15);
		    	changetostring(cell);
		    	curriculum.setCurriculumpingtai(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(4);
		    	changetostring(cell);
		    	String collegeid = cell.getRichStringCellValue().toString();
		    	College college = collegeDao.get(collegeid);
		    	cell=row.getCell(13);
		    	changetostring(cell);
		    	String natureOfCoursename = cell.getRichStringCellValue().toString();
		    	NatureOfCourse natureOfCourse = natureOfCourseDao.getbyname(natureOfCoursename);
		    	if(college == null){
		    		continue;
		    	}else{
		    		curriculum.setCollege(college);
		    		curriculum.setNatureOfCourse(natureOfCourse);
		    		curriculum.setNewcurriculum("0");
		    		Curriculum newcurriculum = curriculumDao.get(curriculum.getCurriculumid());
		    		if(newcurriculum == null){
		    			Curriculum findcurriculum = curriculumDao.getbyall(curriculum);
		    			if(findcurriculum!=null){
		    				curriculumDao.delete(findcurriculum);
		    			}
		    			curriculumDao.add(curriculum);
		    		}else{
		    			newcurriculum.setCurriculumCname(curriculum.getCurriculumCname());
		    			newcurriculum.setCurriculumEname(curriculum.getCurriculumEname());
		    			newcurriculum.setCredit(curriculum.getCredit());
		    			newcurriculum.setHoursOfALL(curriculum.getHoursOfALL());
		    			newcurriculum.setHoursOfClass(curriculum.getHoursOfClass());
		    			newcurriculum.setHoursOfCom(curriculum.getHoursOfCom());
		    			newcurriculum.setHoursOfExp(curriculum.getHoursOfExp());
		    			newcurriculum.setHoursOfPractice(curriculum.getHoursOfPractice());
		    			newcurriculum.setHoursOfWeek(curriculum.getHoursOfWeek());
		    			newcurriculum.setCourseLei(curriculum.getCourseLei());
		    			newcurriculum.setCourseCategory(curriculum.getCourseCategory());
		    			newcurriculum.setCourseIntroduction(curriculum.getCourseIntroduction());
		    			newcurriculum.setNatureOfCourse(curriculum.getNatureOfCourse());
		    			newcurriculum.setCollege(curriculum.getCollege());
		    			newcurriculum.setCurriculumpingtai(curriculum.getCurriculumpingtai());
		    			newcurriculum.setNewcurriculum(curriculum.getNewcurriculum());
		    			curriculumDao.update(newcurriculum);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("课程代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("课程中文名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("课程英文名称")
				&&row.getCell(3).getRichStringCellValue().toString().equals("学分")
				&&row.getCell(4).getRichStringCellValue().toString().equals("开课部门")
				&&row.getCell(5).getRichStringCellValue().toString().equals("总学时")
				&&row.getCell(6).getRichStringCellValue().toString().equals("周学时")
				&&row.getCell(7).getRichStringCellValue().toString().equals("课程简介")
				&&row.getCell(8).getRichStringCellValue().toString().equals("课程类别")
				&&row.getCell(9).getRichStringCellValue().toString().equals("讲课学时")
				&&row.getCell(10).getRichStringCellValue().toString().equals("实验学时")
				&&row.getCell(11).getRichStringCellValue().toString().equals("上机学时")
				&&row.getCell(12).getRichStringCellValue().toString().equals("课程实践学时")
				&&row.getCell(13).getRichStringCellValue().toString().equals("课程性质")
				&&row.getCell(14).getRichStringCellValue().toString().equals("课程归属")
				&&row.getCell(15).getRichStringCellValue().toString().equals("课程平台")){
			return true;
		}else{return false;}
	}
	private boolean importCuExcel(int totalRow, HSSFSheet sheet) {
		HSSFRow row = null;//对应excel的行  
	    HSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	Curriculum curriculum = new Curriculum();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	curriculum.setCurriculumid(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	curriculum.setCurriculumCname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	curriculum.setCurriculumEname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(3);
		    	changetostring(cell);
		    	curriculum.setCredit(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(5);
		    	changetostring(cell);
		    	curriculum.setHoursOfALL(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(6);
		    	changetostring(cell);
		    	curriculum.setHoursOfWeek(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(7);
		    	changetostring(cell);
		    	curriculum.setCourseIntroduction(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(8);
		    	changetostring(cell);
		    	curriculum.setCourseLei(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(9);
		    	changetostring(cell);
		    	curriculum.setHoursOfClass(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(10);
		    	changetostring(cell);
		    	curriculum.setHoursOfExp(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(11);
		    	changetostring(cell);
		    	curriculum.setHoursOfCom(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(12);
		    	changetostring(cell);
		    	curriculum.setHoursOfPractice(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(14);
		    	changetostring(cell);
		    	curriculum.setCourseCategory(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(15);
		    	changetostring(cell);
		    	curriculum.setCurriculumpingtai(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(4);
		    	changetostring(cell);
		    	String collegeid = cell.getRichStringCellValue().toString();
		    	College college = collegeDao.get(collegeid);
		    	cell=row.getCell(13);
		    	changetostring(cell);
		    	String natureOfCoursename = cell.getRichStringCellValue().toString();
		    	NatureOfCourse natureOfCourse = natureOfCourseDao.getbyname(natureOfCoursename);
		    	if(college == null){
		    		continue;
		    	}else{
		    		curriculum.setCollege(college);
		    		curriculum.setNatureOfCourse(natureOfCourse);
		    		curriculum.setNewcurriculum("0");
		    		Curriculum newcurriculum = curriculumDao.get(curriculum.getCurriculumid());
		    		if(newcurriculum == null){
		    			Curriculum findcurriculum = curriculumDao.getbyall(curriculum);
		    			if(findcurriculum!=null){
		    				curriculumDao.delete(findcurriculum);
		    			}
		    			curriculumDao.add(curriculum);
		    		}else{
		    			newcurriculum.setCurriculumCname(curriculum.getCurriculumCname());
		    			newcurriculum.setCurriculumEname(curriculum.getCurriculumEname());
		    			newcurriculum.setCredit(curriculum.getCredit());
		    			newcurriculum.setHoursOfALL(curriculum.getHoursOfALL());
		    			newcurriculum.setHoursOfClass(curriculum.getHoursOfClass());
		    			newcurriculum.setHoursOfCom(curriculum.getHoursOfCom());
		    			newcurriculum.setHoursOfExp(curriculum.getHoursOfExp());
		    			newcurriculum.setHoursOfPractice(curriculum.getHoursOfPractice());
		    			newcurriculum.setHoursOfWeek(curriculum.getHoursOfWeek());
		    			newcurriculum.setCourseLei(curriculum.getCourseLei());
		    			newcurriculum.setCourseCategory(curriculum.getCourseCategory());
		    			newcurriculum.setCourseIntroduction(curriculum.getCourseIntroduction());
		    			newcurriculum.setNatureOfCourse(curriculum.getNatureOfCourse());
		    			newcurriculum.setCollege(curriculum.getCollege());
		    			newcurriculum.setCurriculumpingtai(curriculum.getCurriculumpingtai());
		    			newcurriculum.setNewcurriculum(curriculum.getNewcurriculum());
		    			curriculumDao.update(newcurriculum);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("课程代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("课程中文名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("课程英文名称")
				&&row.getCell(3).getRichStringCellValue().toString().equals("学分")
				&&row.getCell(4).getRichStringCellValue().toString().equals("开课部门")
				&&row.getCell(5).getRichStringCellValue().toString().equals("总学时")
				&&row.getCell(6).getRichStringCellValue().toString().equals("周学时")
				&&row.getCell(7).getRichStringCellValue().toString().equals("课程简介")
				&&row.getCell(8).getRichStringCellValue().toString().equals("课程类别")
				&&row.getCell(9).getRichStringCellValue().toString().equals("讲课学时")
				&&row.getCell(10).getRichStringCellValue().toString().equals("实验学时")
				&&row.getCell(11).getRichStringCellValue().toString().equals("上机学时")
				&&row.getCell(12).getRichStringCellValue().toString().equals("课程实践学时")
				&&row.getCell(13).getRichStringCellValue().toString().equals("课程性质")
				&&row.getCell(14).getRichStringCellValue().toString().equals("课程归属")
				&&row.getCell(15).getRichStringCellValue().toString().equals("课程平台")){
			return true;
		}else{return false;}
	}
	private String curriculumidrule(Curriculum curriculum) {
		String curriculumid = curriculum.getCollege().getCollegeid()+1;
		if(curriculum.getCurriculumpingtai().equals("公共教育平台")){
			curriculumid = curriculumid+"G";
		}else{
			curriculumid = curriculumid+"S";
		}
		/*NatureOfCourse natureOfCourse = natureOfCourseDao.get(curriculum.getNatureOfCourse().getNatureOfCourseid());
		if(natureOfCourse.getChooseOrMust().equals("必")){
			curriculumid = curriculumid+"1";
		}else{
			curriculumid = curriculumid+"2";
		}*/
	//	curriculumid = curriculumid+"0";
		for(int i=0;i<10000;i++){
			String daima = "";
			if(i<10){
				daima = "000";
				String si = String.valueOf(i);
				daima = daima + si;
			}
			if(i>9&&i<100){
				daima = "00";
				String si = String.valueOf(i);;
				daima = daima + si;
			}if(i>99&&i<1000){
				daima = "0";
				String si = String.valueOf(i);;
				daima = daima + si;
			}if(i>999){
				daima = String.valueOf(i);
			}
			String curriculumid2 = curriculumid+daima;
			Curriculum newcurriculum = curriculumDao.get(curriculumid2);
			if(newcurriculum != null){
				continue;
			}else{
				return curriculumid2;
			}
		}
		return "noway";
	}
	public PageBean findcurriculum(Integer currentpage, Curriculum curriculum,
			String departmenttag) {
		return pagebean(currentpage,curriculum,departmenttag);
	}
	private PageBean pagebean(Integer currentpage, Curriculum curriculum,
			String departmenttag) {
		PageBean pageBean = new PageBean();
		int totalCount = curriculumDao.findcurriculumCounttag(curriculum,departmenttag);
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
		List<Curriculum> curriculumlist = curriculumDao.findcurriculumTtag(begin, pageSize,curriculum,departmenttag);
		pageBean.setCurriculumlist(curriculumlist);
		return pageBean;
	}
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void gogivedepartment(Curriculum curriculum) {
		College college = collegeDao.get(curriculum.getCollege().getCollegeid());
		List<Department> departmentlist = departmentDao.findTbyCollege(college);
		Curriculum newCurriculum = curriculumDao.get(curriculum.getCurriculumid());
		ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
		ServletActionContext.getRequest().setAttribute("Curriculum", newCurriculum);
	}
/*	public void goupdatedepartment(Curriculum curriculum) {
		Curriculum newCurriculum = curriculumDao.get(curriculum.getCurriculumid());
		Department department = departmentDao.get(curriculum.getDepartment().getDepartmentid());
		newCurriculum.setDepartment(department);
		curriculumDao.update(newCurriculum);
		ServletActionContext.getRequest().setAttribute("msg", "修改成功！");
	}*/
/*	public PageBean findcurriculumuser(Integer currentpage,
			Curriculum curriculum, String usertag) {
		return pagebeanuser(currentpage,curriculum,usertag);
	}*/
/*	private PageBean pagebeanuser(Integer currentpage, Curriculum curriculum,
			String usertag) {
		PageBean pageBean = new PageBean();
		int totalCount = curriculumDao.findcurriculumCounttaguser(curriculum,usertag);
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
		List<Curriculum> curriculumlist = curriculumDao.findcurriculumTtaguser(begin, pageSize,curriculum,usertag);
		pageBean.setCurriculumlist(curriculumlist);
		return pageBean;
	}*/
/*	public void kctouser(Curriculum curriculum) {
		Curriculum newCurriculum = curriculumDao.get(curriculum.getCurriculumid());
		User user = userDao.get(curriculum.getUser().getTnum());
		newCurriculum.setUser(user);
		curriculumDao.update(newCurriculum);
		ServletActionContext.getRequest().setAttribute("msg", "已将"+newCurriculum.getCurriculumCname()+"分配给"+user.getUsername()+"老师！");
	}*/
/*	public PageBean kctouserpagecollege(Integer currentpage,
			Curriculum curriculum) {
		return pagebeanuser(currentpage,curriculum);
	}*/
/*	private PageBean pagebeanuser(Integer currentpage, Curriculum curriculum) {
		PageBean pageBean = new PageBean();
		int totalCount = userDao.findcurriculumCounttaguser(curriculum);
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
		List<User> userlist = userDao.findcurriculumTtaguser(begin, pageSize,curriculum);
		pageBean.setUserlist(userlist);
		return pageBean;
	}*/
	public Curriculum findone(String curriculumid) {
		Curriculum curriculum = curriculumDao.get(curriculumid);
		return curriculum;
	}
/*	public PageBean findcurriculumuserdepartment(Integer currentpage,
			Curriculum curriculum, String usertag) {
		return pagebeanuserdepartment(currentpage,curriculum,usertag);
	}
	private PageBean pagebeanuserdepartment(Integer currentpage,
			Curriculum curriculum, String usertag) {
		PageBean pageBean = new PageBean();
		int totalCount = curriculumDao.findcurriculumCounttaguserdepartment(curriculum,usertag);
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
		List<Curriculum> curriculumlist = curriculumDao.findcurriculumTtaguserdepartment(begin, pageSize,curriculum,usertag);
		pageBean.setCurriculumlist(curriculumlist);
		return pageBean;
	}
	public PageBean kctouserpagedepartment(Integer currentpage,
			Curriculum curriculum) {
		return pagebeanuserdeparment(currentpage,curriculum);
	}*/
/*	private PageBean pagebeanuserdeparment(Integer currentpage,
			Curriculum curriculum) {
		PageBean pageBean = new PageBean();
		int totalCount = userDao.findcurriculumCounttaguserdepartment(curriculum);
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
		List<User> userlist = userDao.findcurriculumTtaguserdepartment(begin, pageSize,curriculum);
		pageBean.setUserlist(userlist);
		return pageBean;
	}*/
	public void TheoLes(Curriculum curriculum, String tnum) {
		User user = userDao.get(tnum);
		List<Curriculum> curriculumlist = curriculumDao.getbyuser(user);
		ServletActionContext.getRequest().setAttribute("curriculumlist", curriculumlist);
	}
	public void PracLes(Curriculum curriculum, String tnum) {
		User user = userDao.get(tnum);
		List<Curriculum> curriculumlist = curriculumDao.getbyuserne(user);
		ServletActionContext.getRequest().setAttribute("curriculumlist", curriculumlist);
	}
	
	
	
	
	
	
	
}
