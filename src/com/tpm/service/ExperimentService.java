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
import com.tpm.dao.ExperimentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.dao.UserDao;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.NatureOfCourse;
import com.tpm.entity.PageBean;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.User;

@Transactional
public class ExperimentService {
	private ExperimentDao experimentDao;
	public void setExperimentDao(ExperimentDao experimentDao){
		this.experimentDao = experimentDao;
	}
	private CollegeDao collegeDao;
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	private UserDao userDao;
 	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
 	private TheoreticalPlanDao theoreticalPlanDao;
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}
	private PracticePlanDao practicePlanDao;
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}

	public void importExperimentExcel(String path) {	//导入实验室主文件
		boolean flag=true; 
		 try {  //上传的是xls的文件
			    //文件流指向excel文件  //
			 	FileInputStream fin=new FileInputStream(path);  
			    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
				HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importexperimentExcel(totalRow,sheet);
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
			    flag = importexperimentExcel(totalRow,sheet);
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
	
	private boolean importexperimentExcel(int totalRow, HSSFSheet sheet) {	//导入实验室xls文件
		HSSFRow row = null;//对应excel的行  
	    HSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	Experiment experiment = new Experiment();
		    	row=sheet.getRow(i);
		    	
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	experiment.setExperimentid(cell.getRichStringCellValue().toString());
		    	
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	experiment.setExperimentname(cell.getRichStringCellValue().toString());
		    	
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	String collegeid = cell.getRichStringCellValue().toString();
		    	College college = collegeDao.get(collegeid);

		    	if(college == null){
		    		continue;
		    	}else{
		    		experiment.setCollege(college);
		    		Experiment newexperiment = experimentDao.get(experiment.getExperimentid());
		    		if(newexperiment == null){
		    			experimentDao.add(experiment);
		    		}else{
		    			newexperiment.setExperimentname(experiment.getExperimentname());
		    			newexperiment.setCollege(experiment.getCollege());
		    			experimentDao.update(newexperiment);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("实验室编码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("所属学院代码")){
			return true;
		}else{return false;}
	}
	
	private boolean importexperimentExcel(int totalRow, XSSFSheet sheet) {	//导入xlsx文件
		XSSFRow row = null;//对应excel的行  
	    XSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	Experiment experiment = new Experiment();
		    	row=sheet.getRow(i);
		    	
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	experiment.setExperimentid(cell.getRichStringCellValue().toString());
		    	
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	experiment.setExperimentname(cell.getRichStringCellValue().toString());
		    	
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	String collegeid = cell.getRichStringCellValue().toString();
		    	College college = collegeDao.get(collegeid);

		    	if(college == null){
		    		continue;
		    	}else{
		    		experiment.setCollege(college);
		    		Experiment newexperiment = experimentDao.get(experiment.getExperimentid());
		    		if(newexperiment == null){
		    			experimentDao.add(experiment);
		    		}else{
		    			newexperiment.setExperimentname(experiment.getExperimentname());
		    			newexperiment.setCollege(experiment.getCollege());
		    			experimentDao.update(newexperiment);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("实验室编码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("所属学院代码")){
			return true;
		}else{return false;}
	}
	
	

	public void importExperimenterExcel(String path) {	//导入实验员主文件
		boolean flag=true; 
		 try {  //上传的是xls的文件
			    //文件流指向excel文件  //
			 	FileInputStream fin=new FileInputStream(path);  
			    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
				HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importexperimenterExcel(totalRow,sheet);
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
			    flag = importexperimenterExcel(totalRow,sheet);
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

	private boolean importexperimenterExcel(int totalRow, HSSFSheet sheet) { //导入实验员xls文件
		HSSFRow row = null;//对应excel的行  
	    HSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcelexperimenter(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	row=sheet.getRow(i);
		    	
		    	cell=row.getCell(0);
		    	changetostring(cell);	  
		    	String userid = cell.getRichStringCellValue().toString();
		    	User newuser = userDao.get(userid);
		    	   	
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	String experimentid = cell.getRichStringCellValue().toString();
		    	Experiment newexperiment = experimentDao.get(experimentid);
		    	
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	String experimenterLevel = cell.getRichStringCellValue().toString();
		    	List<String> useridList = new ArrayList<String>();
		    	List<String> experimentidList = new ArrayList<String>(); 

		    	if(newuser == null || newexperiment == null){
		    		useridList.add(userid);
		    		experimentidList.add(experimentid);
		    		continue;
		    	}else{
		    		newuser.setExperiment(newexperiment);
		    		if(experimenterLevel.equals("主任"))
		    		{
		    			newuser.setExperimenterlevel(3);
		    		}
		    		if(experimenterLevel.equals("实验员"))
		    		{
		    			newuser.setExperimenterlevel(1);
		    		}
	    			userDao.update(newuser);
		    	}
		    }return true;
	    }else{
	    	return false;
	    }
	}
	
	private boolean checkExcelexperimenter(HSSFSheet sheet) {
		HSSFRow row=sheet.getRow(0);
		if(row.getCell(0).getRichStringCellValue().toString().equals("工号")
				&&row.getCell(1).getRichStringCellValue().toString().equals("实验室编码")
				&&row.getCell(2).getRichStringCellValue().toString().equals("人员职责（实验员/主任）")){
			return true;
		}else{return false;}
	}

	private boolean importexperimenterExcel(int totalRow, XSSFSheet sheet) {//导入实验员xlsx文件
		XSSFRow row = null;//对应excel的行
	    XSSFCell cell = null;//对应excel的列
	    boolean ok =  checkExcelexperimenter(sheet);
	    if(ok){
	    	List<String> useridList = new ArrayList<String>();
	    	List<String> experimentidList = new ArrayList<String>(); 
		    for(int i=1;i<=totalRow;i++)
		    {
		    	row=sheet.getRow(i);
		    	
		    	cell=row.getCell(0);
		    	changetostring(cell);	  
		    	String userid = cell.getRichStringCellValue().toString();
		    	User newuser = userDao.get(userid);
		    	   	
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	String experimentid = cell.getRichStringCellValue().toString();
		    	Experiment newexperiment = experimentDao.get(experimentid);
		    	
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	String experimenterLevel = cell.getRichStringCellValue().toString();
		    	

		    	if(newuser == null || newexperiment == null){
		    		useridList.add(userid);
		    		experimentidList.add(experimentid);
		    		continue;
		    	}else{
		    		newuser.setExperiment(newexperiment);
		    		if(experimenterLevel.equals("主任"))
		    		{
		    			newuser.setExperimenterlevel(3);
		    		}
		    		if(experimenterLevel.equals("实验员"))
		    		{
		    			newuser.setExperimenterlevel(1);
		    		}
	    			userDao.update(newuser);
		    	}
		    }
		    return true;
	    }else{
	    	return false;
	    }
	}

	private boolean checkExcelexperimenter(XSSFSheet sheet) {
		XSSFRow row=sheet.getRow(0);
		if(row.getCell(0).getRichStringCellValue().toString().equals("工号")
				&&row.getCell(1).getRichStringCellValue().toString().equals("实验室编码")
				&&row.getCell(2).getRichStringCellValue().toString().equals("人员职责（实验员/主任）")){
			return true;
		}else{return false;}
	}

	public void exportExperimentModelExcel() {	//导出实验室模板
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("实验室表.xls".getBytes("gb2312"),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportExperimentModelExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}

	private void ExportExperimentModelExcel(OutputStream outputStream) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 4000);
        sheet.setColumnWidth((short) 1, (short) 6000);
        sheet.setColumnWidth((short) 2, (short) 4000);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("实验室编码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 2);  
        cell.setCellValue("所属学院代码");  
        cell.setCellStyle(style1); 
        
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        cell = row.createCell((short) 0);  
        cell.setCellValue("0101");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("冶金系实验室");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 2);  
        cell.setCellValue("01");  
        cell.setCellStyle(style2);
        
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
		
	}

	public void exportExperimenterModelExcel() {	//导出实验员模板
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("实验员表.xls".getBytes("gb2312"),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportExperimenterModelExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}

	private void ExportExperimenterModelExcel(OutputStream outputStream) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 3000);
        sheet.setColumnWidth((short) 1, (short) 3500);
        sheet.setColumnWidth((short) 2, (short) 6000);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("工号");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("实验室编码");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 2);  
        cell.setCellValue("人员职责（实验员/主任）");  
        cell.setCellStyle(style1); 
        
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        cell = row.createCell((short) 0);  
        cell.setCellValue("123456");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("0101");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 2);  
        cell.setCellValue("主任");  
        cell.setCellStyle(style2);
        
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
		
	}

	public PageBean sysbm(Integer currentpage, User user) {
		return pagebean(currentpage,user);
	}
	private PageBean pagebean(Integer currentpage, User user) {
		PageBean pageBean = new PageBean();
		
		//***************************************
		int totalCount=0;
		List<Experiment> experimentlist=new ArrayList<Experiment>();
		if(!user.getCollege().getCollegeid().equals("-1"))
		{
			College college=collegeDao.get(user.getCollege().getCollegeid());
			experimentlist=experimentDao.findbyCollege(college);
			if(experimentlist!=null)
			{
				totalCount=experimentlist.size();
			}
		}
		else
		{
			totalCount = experimentDao.findCount();
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
		
		List<Experiment> newexperiment=new ArrayList<Experiment>();
		//******************************
		if(!user.getCollege().getCollegeid().equals("-1")){
			if(experimentlist!=null)
			{
				if(currentpage != totalPage){
					newexperiment=experimentlist.subList(begin, (begin+pageSize));
				}
				else{
					if((begin>=0)&&(begin!=totalCount)){
						newexperiment=experimentlist.subList(begin,totalCount);
					}
				}
			}
			
		}
		else{
			newexperiment = experimentDao.findT(begin, pageSize);
		}
		pageBean.setExperimentlist(newexperiment);
		
		//*********************************************
		List<College> collegelist=collegeDao.findAll();
		pageBean.setCollegelist(collegelist);
		
		return pageBean;
	}

	public void toaddExperimentPage() {	//到添加实验室页面
		List<College> collegeList = collegeDao.findAll();
		ServletActionContext.getRequest().setAttribute("collegeList", collegeList);
		
	}

	public String addExp(Experiment experiment) {	//添加实验室
		Experiment newExperiment = experimentDao.get(experiment.getExperimentid());
		if(newExperiment != null)
		{
			ServletActionContext.getRequest().setAttribute("msg", "实验室编码"+experiment.getExperimentid()+"已存在，添加失败!");
			ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
			return "failure";
		}
		else
		{
			College newCollege = collegeDao.get(experiment.getCollege().getCollegeid());
			if(newCollege == null)
			{
				ServletActionContext.getRequest().setAttribute("msg", "所属学院不存在，添加失败!");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "failure";
			}
			else
			{
				experiment.setCollege(newCollege);
				experimentDao.add(experiment);
				ServletActionContext.getRequest().setAttribute("msg", "添加成功!");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "success";
			}
		}
	}

	public String deleteExperiment(Experiment experiment) {	//删除实验室	
		Experiment newExperiment = experimentDao.get(experiment.getExperimentid());
		if(newExperiment != null)
		{
			List<TheoreticalLesson> theoreticalLessonlist = theoreticalPlanDao.getbyexperiment(newExperiment);	//若该实验室被分配了理论大纲，则不能删除
			if(theoreticalLessonlist.size() == 0 )
			{
				List<User> userlist = userDao.findExperimenterByExperiment(newExperiment);
				if(userlist != null && userlist.size() != 0)	//先将该实验室下实验员的实验室和实验员级别属性设置为空
				{
					for(int i=0;i<userlist.size();i++)
					{
						userlist.get(i).setExperiment(null);
						userlist.get(i).setExperimenterlevel(null);
						userDao.update(userlist.get(i));
					}
				}
				experimentDao.delete(newExperiment);
				ServletActionContext.getRequest().setAttribute("msg", newExperiment.getExperimentname()+"删除成功!");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "success";
			}
			else
			{
				ServletActionContext.getRequest().setAttribute("msg", newExperiment.getExperimentname()+"删除失败，因为该实验室已被分配大纲");
				ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
				return "failure";
			}
			
		}
		else
		{
			ServletActionContext.getRequest().setAttribute("msg", "该实验室已不存在!");
			ServletActionContext.getRequest().setAttribute("tag", "togetInPage");
			return "success";
		}
	}
		
	
	
	

}
