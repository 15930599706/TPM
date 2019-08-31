package com.tpm.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
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
import com.tpm.entity.College;
import com.tpm.entity.Department;
import com.tpm.entity.PageBean;
@Transactional
public class CollegeService {
	private CollegeDao collegeDao;
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	public PageBean xybm(Integer currentpage) {
		return pagebean(currentpage);
	}
	private PageBean pagebean(Integer currentpage) {
		PageBean pageBean = new PageBean();
		int totalCount = collegeDao.findCount();
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
		List<College> collegelist = collegeDao.findT(begin, pageSize);
		pageBean.setCollegelist(collegelist);
		return pageBean;
	}
	public void importCollegeExcel(String path) {
		boolean flag=true; 
		 try {  //上传的是xls的文件
			    //文件流指向excel文件  //
			 	FileInputStream fin=new FileInputStream(path);  
			    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
				HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importColExcel(totalRow,sheet);
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
		  }
		 catch(Exception ea) {
			  try{//上传的是xlsx的文件
			  	FileInputStream fin=new FileInputStream(path);
			  	XSSFWorkbook workbook = new XSSFWorkbook(fin);
			  	XSSFSheet sheet = workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importColExcel(totalRow,sheet);
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
	public void exportCollegeModelExcel() {
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("学院信息表.xls".getBytes("gb2312"),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}
	public void exportCollegeExcel() {
		try{
			List<College> colist = collegeDao.findAll();
				HttpServletResponse response = ServletActionContext.getResponse();  
                //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	                //2.2 设置以下载方式打开文件  
	            response.setHeader("Content-Disposition", "attachment;filename="+new String("学院信息表.xls".getBytes("gb2312"),"ISO-8859-1") );
	            OutputStream outputStream = response.getOutputStream();
	            if(colist != null){
	            	ExportExcel(colist,outputStream);
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
	public PageBean getall(PageBean pageBean) {
		List<College> collegelist = collegeDao.findAll();
		pageBean.setCollegelist(collegelist);
		return pageBean;
	}
	
	private void ExportNoExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 2300);
        sheet.setColumnWidth((short) 1, (short) 6000);
        sheet.setColumnWidth((short) 2, (short) 8000);
        sheet.setColumnWidth((short) 3, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("学院代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("学院名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("学院英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("学院简称");  
        cell.setCellStyle(style1); 
        
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private void ExportExcel(List<College> colist, OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 2300);
        sheet.setColumnWidth((short) 1, (short) 3500);
        sheet.setColumnWidth((short) 2, (short) 8000);
        sheet.setColumnWidth((short) 3, (short) 2300);
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
        cell.setCellValue("学院代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("学院名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("学院英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("学院简称");  
        cell.setCellStyle(style1); 
        for(int i=0;i<colist.size();i++){
        	row = sheet.createRow((int) i+1);
            
        	College college = colist.get(i);
            cell = row.createCell((short) 0);  
            cell.setCellValue(college.getCollegeid());  
            cell.setCellStyle(style2);  
      
            cell = row.createCell((short) 1);  
            cell.setCellValue(college.getCollegeCname());  
            cell.setCellStyle(style2);  
      
            cell = row.createCell((short) 2);  
            cell.setCellValue(college.getCollegeEname());  
            cell.setCellStyle(style2); 
            
            cell = row.createCell((short) 3);  
            cell.setCellValue(college.getCollegeIntroduction());  
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
        sheet.setColumnWidth((short) 1, (short) 3500);
        sheet.setColumnWidth((short) 2, (short) 8000);
        sheet.setColumnWidth((short) 3, (short) 2300);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("学院代码");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("学院名称");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("学院英文名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("学院简称");  
        cell.setCellStyle(style1); 
        
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        cell = row.createCell((short) 0);  
        cell.setCellValue("01");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("机械工程学院");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("Mechanical Engineering");  
        cell.setCellStyle(style2); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("机械学院");  
        cell.setCellStyle(style2); 
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private boolean importColExcel(int totalRow, XSSFSheet sheet) {
		XSSFRow row = null;
		XSSFCell cell = null;
		boolean ok =  checkExcel(sheet);
		if(ok){
			 for(int i=1;i<=totalRow;i++){
				College college = new College();
			    row=sheet.getRow(i);
			    cell=row.getCell(0);
			    changetostring(cell);
			    college.setCollegeid(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	college.setCollegeCname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	college.setCollegeEname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(3);
		    	changetostring(cell);
		    	college.setCollegeIntroduction(cell.getRichStringCellValue().toString());
		    	College newcollege = collegeDao.get(college.getCollegeid());
		    	if(newcollege == null){
		    		collegeDao.add(college);
		    	}else{
		    		newcollege.setCollegeCname(college.getCollegeCname());
		    		newcollege.setCollegeEname(college.getCollegeEname());
		    		newcollege.setCollegeIntroduction(college.getCollegeIntroduction());
		    		collegeDao.update(newcollege);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("学院代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("学院名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("学院英文名称")
				&&row.getCell(3).getRichStringCellValue().toString().equals("学院简称")){
			return true;
		}else{return false;}
	}
	private boolean importColExcel(int totalRow, HSSFSheet sheet) {
		HSSFRow row = null;//对应excel的行  
	    HSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++){
		    	College college = new College();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	college.setCollegeid(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	college.setCollegeCname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	college.setCollegeEname(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(3);
		    	changetostring(cell);
		    	college.setCollegeIntroduction(cell.getRichStringCellValue().toString());
		    	College newcollege = collegeDao.get(college.getCollegeid());
		    	if(newcollege == null){
		    		collegeDao.add(college);
		    	}else{
		    		newcollege.setCollegeCname(college.getCollegeCname());
		    		newcollege.setCollegeEname(college.getCollegeEname());
		    		newcollege.setCollegeIntroduction(college.getCollegeIntroduction());
		    		collegeDao.update(newcollege);
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
		if(row.getCell(0).getRichStringCellValue().toString().equals("学院代码")
				&&row.getCell(1).getRichStringCellValue().toString().equals("学院名称")
				&&row.getCell(2).getRichStringCellValue().toString().equals("学院英文名称")
				&&row.getCell(3).getRichStringCellValue().toString().equals("学院简称")){
			return true;
		}else{return false;}
	}
	public List<College> getone(String collegeid) {
		return (List<College>)collegeDao.getone(collegeid);
	}
	public List<College> findAllCollege() {
		return collegeDao.findAll();
	}
	
	
	
}
