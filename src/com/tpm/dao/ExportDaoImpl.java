package com.tpm.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("deprecation")
public class ExportDaoImpl extends HibernateDaoSupport implements ExportDao {

	private static Configuration configuration = null;  
	   // private static Map<String, Template> allTemplates = null;  
	      
	    static {  
	        configuration = new Configuration();  
	        configuration.setDefaultEncoding("utf-8");  
	       // configuration.setClassForTemplateLoading(WordGenerator.class, "/cn/test/ftl");  
	       // Map<String, Object> allTemplates = new HashMap<String, Object>();   
	     //   Template t = null;
	        //allTemplates = new HashMap<>();   // Java 7 钻石语法  
//	        try {  
//	         //   allTemplates.put("test", configuration.getTemplate("test.ftl"));
//	            t = configuration.getTemplate("test.ftl", "utf-8");
//	        } catch (IOException e) {  
//	            e.printStackTrace();  
//	            throw new RuntimeException(e);  
//	        }  
	    }  
	  
//	    private WordGenerator() {  
//	        throw new AssertionError();  
//	    }  
	  
	    public File createDoc(Map<String, Object> dataMap) {  
	    	 configuration.setClassForTemplateLoading(ExportDaoImpl.class, "/com/tpm/ftl"); 
	    	 Template t = null;
	    	 try {  
	             //   allTemplates.put("test", configuration.getTemplate("test.ftl"));
	                t = configuration.getTemplate("zhibiaodian.ftl", "utf-8");
	            } catch (IOException e) {  
	                e.printStackTrace(); }
	        String name = "temp" + (int) (Math.random() * 100000) + ".doc";  
	        File f = new File(name);  
	      //  Template t = allTemplates.get(type);  
	        try {  
	            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
	            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
	            t.process(dataMap, w);  
	            w.close();  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	            throw new RuntimeException(ex);  
	        }  
	        return f;  
	   } }


