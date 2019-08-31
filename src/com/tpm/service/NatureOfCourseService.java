package com.tpm.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

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
import com.tpm.dao.NatureOfCourseDao;
import com.tpm.entity.NatureOfCourse;
@Transactional
public class NatureOfCourseService {
	private NatureOfCourseDao natureOfCourseDao;

	public void setNatureOfCourseDao(NatureOfCourseDao natureOfCourseDao) {
		this.natureOfCourseDao = natureOfCourseDao;
	}

	public void importNatureOfCourseExcel(String path) {
		boolean flag=true; 
		 try {  //上传的是xls的文件
			    //文件流指向excel文件  //
			 	FileInputStream fin=new FileInputStream(path);  
			    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
				HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
				int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
				flag = importNOfCExcel(totalRow,sheet);
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
			    flag = importNOfCExcel(totalRow,sheet);
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
	public void exportNatureOfCourseModelExcel() {
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("课程性质信息模板.xls".getBytes("gb2312"),"ISO-8859-1") );
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
        sheet.setColumnWidth((short) 0, (short) 3500);
        sheet.setColumnWidth((short) 1, (short) 3500);
        sheet.setColumnWidth((short) 2, (short) 1200);
        sheet.setColumnWidth((short) 3, (short) 1200);
        sheet.setColumnWidth((short) 4, (short) 4600);
        sheet.setColumnWidth((short) 4, (short) 2900);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("课程性质代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("课程性质名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("简称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("对应");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("是否校公选课性质");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("对应选必修");  
        cell.setCellStyle(style1);
        
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        cell = row.createCell((short) 0);  
        cell.setCellValue("09");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("公共选修课");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("公选");  
        cell.setCellStyle(style2); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("校");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("是");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("选");  
        cell.setCellStyle(style2);
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private boolean importNOfCExcel(int totalRow, XSSFSheet sheet) {
		XSSFRow row = null;
		XSSFCell cell = null;
		 boolean ok =  checkExcel(sheet);
		 if(ok){
			    for(int i=1;i<=totalRow;i++)
			    {
			    	NatureOfCourse natureOfCourse = new NatureOfCourse();
			    	row=sheet.getRow(i);
			    	cell=row.getCell(0);
			    	changetostring(cell);
			    	natureOfCourse.setNatureOfCourseid(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(1);
			    	changetostring(cell);
			    	natureOfCourse.setNatureOfCoursename(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(2);
			    	changetostring(cell);
			    	natureOfCourse.setNatureOfCourseeasyname(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(3);
			    	changetostring(cell);
			    	natureOfCourse.setNatureOfCoursefor(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(4);
			    	changetostring(cell);
			    	natureOfCourse.setIsForSchoolChoose(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(5);
			    	changetostring(cell);
			    	natureOfCourse.setChooseOrMust(cell.getRichStringCellValue().toString());
			    	NatureOfCourse newnatureOfCourse = natureOfCourseDao.get(natureOfCourse.getNatureOfCourseid());
			    	if(newnatureOfCourse == null){
			    		natureOfCourseDao.add(natureOfCourse);
			    	}else{
			    		newnatureOfCourse.setNatureOfCoursename(natureOfCourse.getNatureOfCoursename());
			    		newnatureOfCourse.setNatureOfCourseeasyname(natureOfCourse.getNatureOfCourseeasyname());
			    		newnatureOfCourse.setNatureOfCoursefor(natureOfCourse.getNatureOfCoursefor());
			    		newnatureOfCourse.setIsForSchoolChoose(natureOfCourse.getIsForSchoolChoose());
			    		newnatureOfCourse.setChooseOrMust(natureOfCourse.getChooseOrMust());
			    		natureOfCourseDao.update(newnatureOfCourse);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("课程性质代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("课程性质名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("简称")
				&&row.getCell(3).getRichStringCellValue().toString().equals("对应")
				&&row.getCell(4).getRichStringCellValue().toString().equals("是否校公选课性质")
				&&row.getCell(5).getRichStringCellValue().toString().equals("对应选必修")){
			return true;
		}else{return false;}
	}
	private boolean importNOfCExcel(int totalRow, HSSFSheet sheet) {
		HSSFRow row = null;
		HSSFCell cell = null;
		 boolean ok =  checkExcel(sheet);
		 if(ok){
			    for(int i=1;i<=totalRow;i++)
			    {
			    	NatureOfCourse natureOfCourse = new NatureOfCourse();
			    	row=sheet.getRow(i);
			    	cell=row.getCell(0);
			    	changetostring(cell);
			    	natureOfCourse.setNatureOfCourseid(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(1);
			    	changetostring(cell);
			    	natureOfCourse.setNatureOfCoursename(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(2);
			    	changetostring(cell);
			    	natureOfCourse.setNatureOfCourseeasyname(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(3);
			    	changetostring(cell);
			    	natureOfCourse.setNatureOfCoursefor(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(4);
			    	changetostring(cell);
			    	natureOfCourse.setIsForSchoolChoose(cell.getRichStringCellValue().toString());
			    	cell=row.getCell(5);
			    	changetostring(cell);
			    	natureOfCourse.setChooseOrMust(cell.getRichStringCellValue().toString());
			    	NatureOfCourse newnatureOfCourse = natureOfCourseDao.get(natureOfCourse.getNatureOfCourseid());
			    	if(newnatureOfCourse == null){
			    		natureOfCourseDao.add(natureOfCourse);
			    	}else{
			    		newnatureOfCourse.setNatureOfCoursename(natureOfCourse.getNatureOfCoursename());
			    		newnatureOfCourse.setNatureOfCourseeasyname(natureOfCourse.getNatureOfCourseeasyname());
			    		newnatureOfCourse.setNatureOfCoursefor(natureOfCourse.getNatureOfCoursefor());
			    		newnatureOfCourse.setIsForSchoolChoose(natureOfCourse.getIsForSchoolChoose());
			    		newnatureOfCourse.setChooseOrMust(natureOfCourse.getChooseOrMust());
			    		natureOfCourseDao.update(newnatureOfCourse);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("课程性质代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("课程性质名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("简称")
				&&row.getCell(3).getRichStringCellValue().toString().equals("对应")
				&&row.getCell(4).getRichStringCellValue().toString().equals("是否校公选课性质")
				&&row.getCell(5).getRichStringCellValue().toString().equals("对应选必修")){
			return true;
		}else{return false;}
	}

	
}
