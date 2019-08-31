package com.tpm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tpm.dao.AbilityDao;
import com.tpm.dao.AnalysisDao;
import com.tpm.dao.ExportDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
import com.tpm.entity.Department;



public class ExportService {
	private ExportDao exportDao;
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}


	public void setAbilityDao(AbilityDao abilityDao) {
		this.abilityDao = abilityDao;
	}


	private AbilityDao abilityDao;
	private AnalysisDao analysisDao;
	
	public void setAnalysisDao(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}


	public void setExportDao(ExportDao exportDao) {
		this.exportDao = exportDao;
	}


	public void down(String departmentid) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();  
		Department department = departmentDao.get(departmentid);
		List<Ability> abilitylist = abilityDao.getbydepartment(department);
		List<Map<String, Object>> oldList=new ArrayList<Map<String,Object>>();
        Map<String, Object> dataMap = new HashMap<String, Object>(); 
        String title =department.getDepartmentCname();
        dataMap.put("titlename",title); 
        for (int i=0;i<abilitylist.size();i++){
        	  Map<String, Object> abilitymap = new HashMap<String, Object>();
        List<Analysis> analysilist = analysisDao.getbyability(abilitylist.get(i));
        abilitymap.put("name", abilitylist.get(i).getAbilityname());
        abilitymap.put("num", i+1+"、");
        abilitymap.put("i", i+1+"-1");
        if (analysilist!=null){
        String e =analysilist.get(0).getAnalysiscontent();
    	String f =e.replace("\\n", "");
    	String g =f.replace("\\t", "");
    	String h =g.replace(" ", "");
        abilitymap.put("context1", h);}
        else{
        }
        abilitymap.put("context", abilitylist.get(i).getAbilitycontent());
        oldList.add(abilitymap);
        dataMap.put("ability",oldList); 
        List<Map<String, Object>> newsList=new ArrayList<Map<String,Object>>(); 
        if (analysilist!=null){
        for(int j=1;j<analysilist.size()+1;j++){
        	Map<String, Object> analysismap = new HashMap<String, Object>();
        	if(j<analysilist.size()){
        	String a =analysilist.get(j).getAnalysiscontent();
        	a=a.replace("\\t","");
        	String d = a.replaceAll("[\\n\\t]*", "");
        	analysismap.put("context",d);
    		newsList.add(analysismap);}
        	else{
        		newsList.add(analysismap);
        	}
        	dataMap.put("analysilist"+i,newsList);
        }}
        else{
        	dataMap.put("analysilist"+i,newsList);
        }
        }
   
        
        
        // 通过循环将表单参数放入键值对映射中  
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
            file = exportDao.createDoc(dataMap);  
            fin = new FileInputStream(file); 
            response.setContentType("application/msword");  
            //2.2 设置以下载方式打开文件  
            String name=title+"专业毕业要求及指标点分解.doc";
    		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
      //  response.addHeader("Content-Disposition", "attachment;filename=tiltle.doc");
        response.setCharacterEncoding("utf-8");
     //   OutputStream outputStream = response.getOutputStream();
       
            out = response.getOutputStream();  
            byte[] buffer = new byte[2024];  // 缓冲区  
            int bytesToRead = -1;  
            // 通过循环将读入的Word文件的内容输出到浏览器中  
            while((bytesToRead = fin.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);  
            }  
        } finally {  
            if(fin != null)
            	fin.close();  
            if(out != null) 
            	out.close();  
            if(file != null) 
            	file.delete(); // 删除临时文件  
          
    	}// TODO Auto-generated method stub
		
	}

}
