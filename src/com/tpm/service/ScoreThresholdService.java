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
import com.tpm.dao.AvePerThresholdDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.ScoreThresholdDao;
import com.tpm.entity.AvePerThreshold;
import com.tpm.entity.Department;
import com.tpm.entity.ScoreThreshold;

@Transactional
public class ScoreThresholdService {
	private ScoreThresholdDao scoreThresholdDao;
	private DepartmentDao departmentDao;
	private AvePerThresholdDao avePerThresholdDao;
	public void setAvePerThresholdDao(AvePerThresholdDao avePerThresholdDao) {
		this.avePerThresholdDao = avePerThresholdDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void setScoreThresholdDao(ScoreThresholdDao scoreThresholdDao) {
		this.scoreThresholdDao = scoreThresholdDao;
	}

	public void importScoreThresholdExcel(String path) {
		boolean flag=true; 
		 try {  //上传的是xls的文件
			    //文件流指向excel文件  //
			 	FileInputStream fin=new FileInputStream(path);  
			    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
				HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importscoExcel(totalRow,sheet);
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
			    flag = importscoExcel(totalRow,sheet);
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

	private boolean importscoExcel(int totalRow, XSSFSheet sheet) {
		XSSFRow row = null;//对应excel的行  
	    XSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
	    	List<Department> departmentlist = departmentDao.findAll();
	    	for(int i=0;i<departmentlist.size();i++){
	    		ScoreThreshold scoreThreshold = scoreThresholdDao.findbydepartment(departmentlist.get(i));
	    		if(scoreThreshold == null){
	    			ScoreThreshold scoreThreshold2 = new ScoreThreshold();
	    			scoreThreshold2.setDepartment(departmentlist.get(i));
	    			scoreThresholdDao.add(scoreThreshold2);
	    		}
	    	}
	    	ScoreThreshold scoreThreshold = scoreThresholdDao.findbydepartmentnull();
	    	if(scoreThreshold == null){
	    		ScoreThreshold scoreThreshold2 = new ScoreThreshold();
	    		scoreThreshold2.setScore("180");
	    		scoreThresholdDao.add(scoreThreshold2);
	    	}
	    	for(int i=1;i<=totalRow;i++){
	    		row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	Department newDepartment = departmentDao.getbyname(cell.getRichStringCellValue().toString());
		    	if(newDepartment != null){
		    		ScoreThreshold newScoreThreshold = scoreThresholdDao.findbydepartment(newDepartment);
		    		cell=row.getCell(1);
			    	changetostring(cell);
			    	newScoreThreshold.setScore(cell.getRichStringCellValue().toString());
			    	scoreThresholdDao.update(newScoreThreshold);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("专业")
				&&row.getCell(1).getRichStringCellValue().toString().equals("总学分")){
			return true;
		}else{return false;}
	}

	private boolean importscoExcel(int totalRow, HSSFSheet sheet) {
		HSSFRow row = null;//对应excel的行  
	    HSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
	    	List<Department> departmentlist = departmentDao.findAll();
	    	for(int i=0;i<departmentlist.size();i++){
	    		ScoreThreshold scoreThreshold = scoreThresholdDao.findbydepartment(departmentlist.get(i));
	    		if(scoreThreshold == null){
	    			ScoreThreshold scoreThreshold2 = new ScoreThreshold();
	    			scoreThreshold2.setDepartment(departmentlist.get(i));
	    			scoreThresholdDao.add(scoreThreshold2);
	    		}
	    	}
	    	ScoreThreshold scoreThreshold = scoreThresholdDao.findbydepartmentnull();
	    	if(scoreThreshold == null){
	    		ScoreThreshold scoreThreshold2 = new ScoreThreshold();
	    		scoreThreshold2.setScore("180");
	    		scoreThresholdDao.add(scoreThreshold2);
	    	}
	    	for(int i=1;i<=totalRow;i++){
	    		row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	Department newDepartment = departmentDao.getbyname(cell.getRichStringCellValue().toString());
		    	if(newDepartment != null){
		    		ScoreThreshold newScoreThreshold = scoreThresholdDao.findbydepartment(newDepartment);
		    		cell=row.getCell(1);
			    	changetostring(cell);
			    	newScoreThreshold.setScore(cell.getRichStringCellValue().toString());
			    	scoreThresholdDao.update(newScoreThreshold);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("专业")
				&&row.getCell(1).getRichStringCellValue().toString().equals("总学分")){
			return true;
		}else{return false;}
	}

	public void exportScoreThresholdExcel() {
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("专业总学分表.xls".getBytes("gb2312"),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}

	private void ExportExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 6000);
        sheet.setColumnWidth((short) 1, (short) 2000);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("专业");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("总学分");  
        cell.setCellStyle(style1); 
        
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        cell = row.createCell((short) 0);  
        cell.setCellValue("金属材料工程");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("186");  
        cell.setCellStyle(style2);
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}

	//设置专业要求总学分
	public void setTotalCredit(ScoreThreshold scoreThreshold) {
		Department department = departmentDao.get(scoreThreshold.getDepartment().getDepartmentid());
		ScoreThreshold newSoreThreshold = scoreThresholdDao.findbydepartment(department);
		if(newSoreThreshold == null){
			scoreThresholdDao.add(scoreThreshold);
		}
		else{
			newSoreThreshold.setScore(scoreThreshold.getScore());
			scoreThresholdDao.update(newSoreThreshold);
		}
	}

	//设置平均周学时
	public void setAvePerThreshold(String weekHour) {
		if(weekHour != null && !weekHour.equals("")){
			AvePerThreshold avePerThreshold = avePerThresholdDao.findAvePer();
			if(avePerThreshold != null){
				avePerThreshold.setWeekHour(Float.valueOf(weekHour));
				avePerThresholdDao.update(avePerThreshold);
			}
			else{
				AvePerThreshold newavePerThreshold = new AvePerThreshold();
				newavePerThreshold.setWeekHour(Float.valueOf(weekHour));
				avePerThresholdDao.add(newavePerThreshold);
			}
		}
		
	}

}
