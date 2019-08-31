package com.tpm.service;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.examples.Borders;
import org.apache.struts2.ServletActionContext;

import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.field.RtfPageNumber;
import com.lowagie.text.rtf.field.RtfTotalPageNumber;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;
import com.lowagie.text.rtf.style.RtfFont;
import com.tpm.action.PracticeBook_CourseDesignAction;
import com.tpm.dao.AbilityDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.ExportWordDao;
import com.tpm.entity.Ability;
import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.entity.AbilityAndTeachObj_CourseDesign;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.AbilityAndTeachObj_Gra;
import com.tpm.entity.AbilityAndTeachObj_InnerExperiment;
import com.tpm.entity.AbilityAndTeachObj_TheoInnerExperiment;
import com.tpm.entity.Analysis;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.BaseCourseDesign;
import com.tpm.entity.BaseExperiment;
import com.tpm.entity.BaseExperiment_TheoInnerExperiment;
import com.tpm.entity.BasePractice;
import com.tpm.entity.BaseTheo;
import com.tpm.entity.ConCourseDesign;
import com.tpm.entity.ContentGra;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.DiscussContent;
import com.tpm.entity.DistributeHour_CourseDesign;
import com.tpm.entity.DistributeHour_Gra;
import com.tpm.entity.DistributeHour_InnerExperiment;
import com.tpm.entity.DistributeHour_Theo;
import com.tpm.entity.DistributeHour_TheoInnerExperiment;
import com.tpm.entity.ExperimentContent;
import com.tpm.entity.ExpermentProject;
import com.tpm.entity.ExpermentProject_Theo;
import com.tpm.entity.FieldContent;
import com.tpm.entity.FieldWork;
import com.tpm.entity.IndexSelect;
import com.tpm.entity.IndexSelect_CourseDesign;
import com.tpm.entity.IndexSelect_FieldWork;
import com.tpm.entity.IndexSelect_Gra;
import com.tpm.entity.IndexSelect_InnerExperiment;
import com.tpm.entity.IndexSelect_TheoInnerExperiment;
import com.tpm.entity.OtherContent;
import com.tpm.entity.PracticeBook;
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.PracticeBooks_InnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TeaMethodTheo;
import com.tpm.entity.TeachObj;
import com.tpm.entity.TeachObj_CourseDesign;
import com.tpm.entity.TeachObj_FieldWork;
import com.tpm.entity.TeachObj_Gra;
import com.tpm.entity.TeachObj_InnerExperiment;
import com.tpm.entity.TeachObj_TheoInnerExperiment;
import com.tpm.entity.TextBooks;
import com.tpm.entity.TextBooks_InnerExperiment;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.ThreeProject;
import com.tpm.entity.WayCourseDesign;

public class ExportWordService {
	private ExportWordDao exportWordDao;
	private CurriculumDao curriculumDao;
	private AbilityDao abilityDao;
	
	public void setAbilityDao(AbilityDao abilityDao) {
		this.abilityDao = abilityDao;
	}
	public void setCurriculumDao(CurriculumDao curriculumDao) {
		
		this.curriculumDao = curriculumDao;
	}
	public void setExportWordDao(ExportWordDao exportWordDao) {
		this.exportWordDao = exportWordDao;
	}
	/*黑体 三号*/
	public Paragraph Tabletitle(String title) throws Exception{//设置标题类型 ，宋16加粗   教学安排
		RtfFont titleFont = new RtfFont("黑 体", 16, Font.NORMAL,Color.BLACK);//这个构造方法里面：第一个参数是字体类型，第二个参数是字体大小，第三个参数是字体格式是加粗还是正常，第四个参数是字体的颜色	
		Paragraph tabletitle=new Paragraph(title,titleFont );
		tabletitle.setAlignment(Element.ALIGN_CENTER);//居中
		tabletitle.setSpacingAfter(10);  //段后距离
		tabletitle.setSpacingBefore(10);//段前距离
		tabletitle.setLeading(1);//行间距
		return tabletitle;
	}
	public Paragraph luokuan(String title) throws Exception{//设置落款类型 ，宋12加粗   
		RtfFont titleFont = new RtfFont("宋体", 12, Font.BOLD,Color.BLACK);//这个构造方法里面：第一个参数是字体类型，第二个参数是字体大小，第三个参数是字体格式是加粗还是正常，第四个参数是字体的颜色	
		Paragraph tabletitle=new Paragraph(title,titleFont );
		tabletitle.setAlignment(Element.ALIGN_JUSTIFIED);//居中
		tabletitle.setSpacingAfter(10);  //段后距离
		tabletitle.setSpacingBefore(10);//段前距离
		tabletitle.setLeading(1);//行间距
		return tabletitle;
	}
	public Paragraph twotitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("黑 体", 12, Font.NORMAL, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		//title.IndentationLeft=2;
		title.setSpacingAfter(5);  //段前距离
		title.setSpacingBefore(5);//段后距离
		title.setLeading(1);//设置行间距
		//title.setFirstLineIndent(20);
		return title;
	}
/*	public Paragraph twotitle(String title) throws Exception{//设置标题类型 ，宋12   教学安排
		RtfFont titleFont = new RtfFont("黑 体", 12, Font.NORMAL,Color.BLACK);//这个构造方法里面：第一个参数是字体类型，第二个参数是字体大小，第三个参数是字体格式是加粗还是正常，第四个参数是字体的颜色	
		Paragraph tabletitle=new Paragraph(title,titleFont );
		tabletitle.setAlignment(Element.ALIGN_LEFT);//居中
		tabletitle.setSpacingAfter(8);  //段前距离
		tabletitle.setSpacingBefore(8);//段后距离
		tabletitle.setLeading(1);//行间距
	//	tabletitle.setFirstLineIndent(20);
		return tabletitle;
	}*/
	public Paragraph HFourtitle(String title) throws Exception{//设置标题类型 ，宋12   教学安排
		RtfFont titleFont = new RtfFont("黑 体", 12, Font.NORMAL,Color.BLACK);//这个构造方法里面：第一个参数是字体类型，第二个参数是字体大小，第三个参数是字体格式是加粗还是正常，第四个参数是字体的颜色	
		Paragraph tabletitle=new Paragraph(title,titleFont );
		tabletitle.setAlignment(Element.ALIGN_LEFT);//居中
		tabletitle.setSpacingAfter(8);  //段前距离
		tabletitle.setSpacingBefore(8);//段后距离
		tabletitle.setLeading(1);//行间距
		return tabletitle;
	}
	/*小四  宋体*/
	public Paragraph SFourtitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("宋体", 11, Font.NORMAL, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setFirstLineIndent(20);
		title.setSpacingBefore(5);//段后距离
		title.setLeading(1);//设置行间距
		return title;
	}
	/*小四  宋体 加粗*/
	public Paragraph SFourBtitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("宋体", 12, Font.BOLD, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setSpacingBefore(5);//段后距离
		title.setLeading(1);//设置行间距
		return title;
	}
	/*五号  宋体 */
	public Paragraph SFivetitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("宋体", 11, Font.NORMAL, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		//title.IndentationLeft=2;
		title.setSpacingAfter(0);  //段前距离
		title.setSpacingBefore(0);//段后距离
		title.setLeading(18);//设置行间距
		title.setFirstLineIndent(20);
		return title;
	}
	/*五号  宋体 加粗 */
	public Paragraph SFiveBtitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("宋体", 11, Font.BOLD, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setSpacingBefore(5);//段后距离
		title.setLeading(1);//设置行间距
		return title;
	}
	public Paragraph SFiveBtitle1(String para){
		Paragraph title=new Paragraph(para, new RtfFont("宋体", 11, Font.BOLD, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setFirstLineIndent(20);
		title.setSpacingBefore(5);//段后距离
		title.setLeading(1);//设置行间距
		return title;
	}
	/*五号  黑体*/
	public Paragraph HFivetitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("黑 体", 11, Font.NORMAL, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setSpacingBefore(5);//段后距离
		title.setLeading(1);//设置行间距
		return title;
	}
	/*11号  新罗马体*/
	public Paragraph timeNewRome(String para){
		Paragraph title=new Paragraph(para, new RtfFont("Times New Roman", 11, Font.NORMAL, Color.black));
		
		return title;
	}
	/*五号  黑体 加粗*/
	public Paragraph HFiveBtitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("黑 体", 11, Font.BOLD, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setSpacingBefore(5);//段后距离
		title.setLeading(18);//设置行间距
		return title;
	}
	/*小四号  黑体*/
	public Paragraph HFourBtitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("黑 体", 12, Font.BOLD, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setSpacingBefore(5);//段后距离
		title.setLeading(1);//设置行间距
		return title;
	}
	
	
	public Paragraph settitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("黑 体",11, Font.NORMAL, Color.BLACK));
		
/*		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setSpacingBefore(5);//段后距离*/
		title.setLeading(1);//设置行间距
		return title;
	}
	
	private boolean isInteger(String str) {  
	    if (null == str || "".equals(str)) {  
	        return false;  
	    }  
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	    return pattern.matcher(str).matches();  
	} 
	
	
	public Table table(int x,int y,float[] widths) throws Exception{//表格设置
		Table table = new Table(x, y);//11列11行
		table.setBorderWidthTop(10);
		table.setBorderWidthBottom(10);
		table.setBorderWidthLeft(10);
		table.setBorderWidthRight(10);
		table.setOffset(1f);//消除段落和表格之间的空行
		table.setAutoFillEmptyCells(true);
		table.setWidths(widths);//设置表格的总宽度
		table.setWidth(100);
		return table;
	}
	
	
	
	
	public Cell TeachCellHead(String s,int x,int y,int z) throws Exception{//合并行，列，对齐方式，写入表头的内容，宋12加粗
		RtfFont contextFont1 = new RtfFont("宋体", 11, Font.BOLD,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER);teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		teach.setRowspan(x);// 当前单元格占两行,纵向跨度
		teach.setColspan(y);//合并列
		return teach;
	}
	public Cell TeachCellHead1(String s,int z) throws Exception{//合并行，列，对齐方式，写入表头的内容，宋12加粗
		RtfFont contextFont1 = new RtfFont("宋体", 11, Font.NORMAL,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER);teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		
		return teach;
	}
	
	public Cell TeachCellContentChinese(String s,int x,int y,int z) throws Exception{//写入表格的内容中文，宋10.5（五号）正常
		RtfFont contextFont1 = new RtfFont("宋体", (float)10.5, Font.NORMAL,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		teach.setRowspan(x);// 当前单元格占两行,纵向跨度
		teach.setColspan(y);
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_LEFT);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_LEFT); 
				teach.setVerticalAlignment(Element.ALIGN_LEFT);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		 case 5:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 6:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 7:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
			teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		}
		return teach;
	}
	public Cell TeachCellContentChinesee(String s,int x,int y,int z) throws Exception{//写入表格的内容中文，宋10.5（五号）正常
		RtfFont contextFont1 = new RtfFont("宋体", (float)10.5, Font.NORMAL,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		teach.setRowspan(x);// 当前单元格占两行,纵向跨度
		teach.setColspan(y);
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_LEFT); 
				teach.setVerticalAlignment(Element.ALIGN_LEFT);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		 case 5:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 6:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		}
		return teach;
	}
	public Cell TeachCellContentRoman(String s,int z) throws Exception{//写入表格的内容Roman，宋10.5（五号）正常，1.水平居中，2，垂直居中3.水平并且垂直居中，4.靠左
		RtfFont contextFont1 = new RtfFont("Times New Roman", (float)10.5, Font.NORMAL,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
				teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		return teach;
	}
	public Cell TeachCellContentRoman1(String s,int z) throws Exception{//写入表格的内容Roman，宋11（五号）正常，1.水平居中，2，垂直居中3.水平并且垂直居中，4.靠左
		RtfFont contextFont1 = new RtfFont("Times New Roman", 11, Font.NORMAL,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
				teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		return teach;
	}
	public Cell heiFont(String s,int z) throws Exception{//写入表格的内容Roman，宋11（五号）正常，1.水平居中，2，垂直居中3.水平并且垂直居中，4.靠左
		RtfFont contextFont1 = new RtfFont("黑 体", 11, Font.NORMAL,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
				teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		return teach;
	}
	
	public Cell TeachCellContentRoman1(String s,int x,int y,int z) throws Exception{//写入表格的内容Roman，宋10.5（五号）正常，1.水平居中，2，垂直居中3.水平并且垂直居中，4.靠左
		RtfFont contextFont1 = new RtfFont("Times New Roman", (float)10.5, Font.NORMAL,Color.BLACK);//为计算学分和学时的总和
		Cell teach = new Cell(new Phrase(String.valueOf(s),contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
				teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		teach.setRowspan(x);// 当前单元格占两行,纵向跨度
		teach.setColspan(y);//合并列
		return teach;
	}
	 
	
	
	
	public Table GraRequest(List<List<IndexSelect>> newindexselectlist, List<List<AbilityAndTeachObj>> newabilityAndTeachObjlist, List<TeachObj> teachObjlist)throws Exception{
		float[] widths={30,50,20};
		int count=0;
		for(int i = 0;i<newindexselectlist.size();i++)
		{
			List<IndexSelect> newindexSelect= newindexselectlist.get(i);
			for(int j=0;j<newindexSelect.size();j++)
			{
				count ++;
			}
		}
		Table gr=table(3, count+1,widths);
		gr.setOffset(1);
		gr.addCell(TeachCellHead("毕业要求",1,1,3));
		gr.addCell(TeachCellHead("指标点",1,1,3));
		gr.addCell(TeachCellHead("课程教学目标",1,1,3));
		for(int gi =0;gi<newindexselectlist.size();gi++)
		{
			List<IndexSelect> indexSelectlist = newindexselectlist.get(gi);
			List<AbilityAndTeachObj> ADTlist = newabilityAndTeachObjlist.get(gi);
			Ability str= indexSelectlist.get(0).getAbility();
			List<Analysis> analysislsit = exportWordDao.findGraid(str);//指标分解表中查询毕业生能力对应的所有字段
			String teaobj ="" ;
			List<Ability> abilityList = abilityDao.getbydepartment(indexSelectlist.get(0).getAbility().getDepartment());
			int index = abilityList.indexOf(indexSelectlist.get(0).getAbility());	
			for(int tej=0;tej<ADTlist.size();tej++)
				{
					int m=0;
					for(int tex=0;tex<teachObjlist.size();tex++)
					{
						if(ADTlist.get(tej).getTeachObj() == teachObjlist.get(tex))
						{
							m = tex +1;
						}
					}
					
					teaobj =teaobj+ (String)("教学目标"+String.valueOf(m)+"\n");
				}
			String name= String.valueOf(index+1)+"、"+indexSelectlist.get(0).getAbility().getAbilityname()+": "+indexSelectlist.get(0).getAbility().getAbilitycontent();	
			gr.addCell(TeachCellContentChinese(name,indexSelectlist.size(),1,1));
			for(int is=0;is<indexSelectlist.size();is++)
			{
				IndexSelect indexSelect = indexSelectlist.get(is);
				int k=0;
				for(int z=0;z<analysislsit.size();z++)
				{
					if(indexSelect.getAnalisis() == analysislsit.get(z))
						k = z+1;
				}
				String zhibiao  = String.valueOf(index+1)+"-"+k+indexSelect.getAnalisis().getAnalysiscontent();
			gr.addCell(TeachCellContentChinese(zhibiao,1,1,1));
			if(is == 0)
				gr.addCell(TeachCellContentChinese(teaobj,indexSelectlist.size(),1,7));
			else continue;
			}
		}
		
		return gr;
	}
	public Table GraRequest_Gra(List<List<IndexSelect_Gra>> newindexselectlist, List<List<AbilityAndTeachObj_Gra>> newabilityAndTeachObjlist, List<TeachObj_Gra> teachObjlist)throws Exception{
		float[] widths={30,50,20};
		int count=0;
		for(int i = 0;i<newindexselectlist.size();i++)
		{
			List<IndexSelect_Gra> newindexSelect= newindexselectlist.get(i);
			for(int j=0;j<newindexSelect.size();j++)
			{
				count ++;
			}
		}
		Table gr=table(3, count+1,widths);
		gr.addCell(TeachCellHead("毕业要求",1,1,3));
		gr.addCell(TeachCellHead("指标点",1,1,3));
		gr.addCell(TeachCellHead("课程教学目标",1,1,3));
		for(int gi =0;gi<newindexselectlist.size();gi++)
		{
			List<IndexSelect_Gra> indexSelectlist = newindexselectlist.get(gi);
			List<AbilityAndTeachObj_Gra> ADTlist = newabilityAndTeachObjlist.get(gi);
			Ability str= indexSelectlist.get(0).getAbility();
			List<Analysis> analysislsit = exportWordDao.findGraid(str);//指标分解表中查询毕业生能力对应的所有字段
			List<Ability> abilityList = abilityDao.getbydepartment(indexSelectlist.get(0).getAbility().getDepartment());
			int index = abilityList.indexOf(indexSelectlist.get(0).getAbility());	
			String teaobj ="" ;
				for(int tej=0;tej<ADTlist.size();tej++)
				{
					int m=0;
					for(int tex=0;tex<teachObjlist.size();tex++)
					{
						if(ADTlist.get(tej).getTeachObj_Gra() == teachObjlist.get(tex))
						{
							m = tex +1;
						}
					}
					teaobj =teaobj+ (String)("教学目标"+String.valueOf(m)+"\n");
				}
			String name= String.valueOf(index+1)+"、"+indexSelectlist.get(0).getAbility().getAbilityname()+indexSelectlist.get(0).getAbility().getAbilitycontent();
			gr.addCell(TeachCellContentChinesee(name,indexSelectlist.size(),1,1));
			for(int is=0;is<indexSelectlist.size();is++)
			{
				IndexSelect_Gra indexSelect = indexSelectlist.get(is);
				int k=0;
				for(int z=0;z<analysislsit.size();z++)
				{
					if(indexSelect.getAnalisis() == analysislsit.get(z))
						k = z+1;
				}
				String zhibiao  = String.valueOf(index+1)+"-"+k+indexSelect.getAnalisis().getAnalysiscontent();
			gr.addCell(TeachCellContentChinese(zhibiao,1,1,1));
			if(is == 0)
				gr.addCell(TeachCellContentChinese(teaobj,indexSelectlist.size(),1,6));
			else continue;
			}
		}
		
		return gr;
	}	
	public Table GraRequest_FieldWork(List<List<IndexSelect_FieldWork>> newindexselectlist, List<List<AbilityAndTeachObj_FieldWork>> newabilityAndTeachObjlist, List<TeachObj_FieldWork> teachObjlist)throws Exception{
		float[] widths={30,50,20};
		int count=0;
		for(int i = 0;i<newindexselectlist.size();i++)
		{
			List<IndexSelect_FieldWork> newindexSelect= newindexselectlist.get(i);
			for(int j=0;j<newindexSelect.size();j++)
			{
				count ++;
			}
		}
		Table gr=table(3, count+1,widths);
		gr.addCell(TeachCellHead("毕业要求",1,1,3));
		gr.addCell(TeachCellHead("指标点",1,1,3));
		gr.addCell(TeachCellHead("课程教学目标",1,1,3));
		for(int gi =0;gi<newindexselectlist.size();gi++)
		{
			List<IndexSelect_FieldWork> indexSelectlist = newindexselectlist.get(gi);
			List<AbilityAndTeachObj_FieldWork> ADTlist = newabilityAndTeachObjlist.get(gi);
			Ability str= indexSelectlist.get(0).getAbility();
			List<Analysis> analysislsit = exportWordDao.findGraid(str);//指标分解表中查询毕业生能力对应的所有字段
			List<Ability> abilityList = abilityDao.getbydepartment(indexSelectlist.get(0).getAbility().getDepartment());
			int index = abilityList.indexOf(indexSelectlist.get(0).getAbility());	
			String teaobj ="" ;
				for(int tej=0;tej<ADTlist.size();tej++)
				{
					int m=0;
					for(int tex=0;tex<teachObjlist.size();tex++)
					{
						if(ADTlist.get(tej).getTeachObj_FieldWork() == teachObjlist.get(tex))
						{
							m = tex +1;
						}
					}
					teaobj =teaobj+ (String)("教学目标"+String.valueOf(m)+"\n");
				}
			String name= String.valueOf(index+1)+"、"+indexSelectlist.get(0).getAbility().getAbilityname()+indexSelectlist.get(0).getAbility().getAbilitycontent();
			gr.addCell(TeachCellContentChinese(name,indexSelectlist.size(),1,1));
			for(int is=0;is<indexSelectlist.size();is++)
			{
				IndexSelect_FieldWork indexSelect = indexSelectlist.get(is);
				int k=0;
				for(int z=0;z<analysislsit.size();z++)
				{
					if(indexSelect.getAnalisis() == analysislsit.get(z))
						k = z+1;
				}
				String zhibiao  = String.valueOf(index+1)+"-"+k+indexSelect.getAnalisis().getAnalysiscontent();
			gr.addCell(TeachCellContentChinese(zhibiao,1,1,1));
			if(is == 0)
				gr.addCell(TeachCellContentChinese(teaobj,indexSelectlist.size(),1,2));
			else continue;
			}
		}
		
		return gr;
	}	
	public Table GraRequest_CourseDesign(List<List<IndexSelect_CourseDesign>> newindexselectlist, List<List<AbilityAndTeachObj_CourseDesign>> newabilityAndTeachObjlist, List<TeachObj_CourseDesign> teachObjlist)throws Exception{
		float[] widths={30,50,20};
		int count=0;
		for(int i = 0;i<newindexselectlist.size();i++)
		{
			List<IndexSelect_CourseDesign> newindexSelect= newindexselectlist.get(i);
			for(int j=0;j<newindexSelect.size();j++)
			{
				count ++;
			}
		}
		Table gr=table(3, count+1,widths);
		gr.addCell(TeachCellHead("毕业要求",1,1,3));
		gr.addCell(TeachCellHead("指标点",1,1,3));
		gr.addCell(TeachCellHead("课程教学目标",1,1,3));
		for(int gi =0;gi<newindexselectlist.size();gi++)
		{
			List<IndexSelect_CourseDesign> indexSelectlist = newindexselectlist.get(gi);
			List<AbilityAndTeachObj_CourseDesign> ADTlist = newabilityAndTeachObjlist.get(gi);
			Ability str= indexSelectlist.get(0).getAbility();
			List<Analysis> analysislsit = exportWordDao.findGraid(str);//指标分解表中查询毕业生能力对应的所有字段
			List<Ability> abilityList = abilityDao.getbydepartment(indexSelectlist.get(0).getAbility().getDepartment());
			int index = abilityList.indexOf(indexSelectlist.get(0).getAbility());	
			String teaobj ="" ;
				for(int tej=0;tej<ADTlist.size();tej++)
				{
					int m=0;
					for(int tex=0;tex<teachObjlist.size();tex++)
					{
						if(ADTlist.get(tej).getTeachObj_CourseDesign() == teachObjlist.get(tex))
						{
							m = tex +1;
						}
					}
					teaobj =teaobj+ (String)("教学目标"+String.valueOf(m)+"\n");
				}
			String name= String.valueOf(gi+1)+"、"+indexSelectlist.get(0).getAbility().getAbilityname()+indexSelectlist.get(0).getAbility().getAbilitycontent();
			gr.addCell(TeachCellContentChinese(name,indexSelectlist.size(),1,1));
			for(int is=0;is<indexSelectlist.size();is++)
			{
				IndexSelect_CourseDesign indexSelect = indexSelectlist.get(is);
				int k=0;
				for(int z=0;z<analysislsit.size();z++)
				{
					if(indexSelect.getAnalisis() == analysislsit.get(z))
						k = z+1;
				}
				String zhibiao  = String.valueOf(gi+1)+"-"+k+indexSelect.getAnalisis().getAnalysiscontent();
			gr.addCell(TeachCellContentChinese(zhibiao,1,1,1));
			if(is == 0)
				gr.addCell(TeachCellContentChinese(teaobj,indexSelectlist.size(),1,2));
			else continue;
			}
		}
		
		return gr;
	}	

	public Table GraRequest_InnerExperiment(List<List<IndexSelect_InnerExperiment>> newindexselectlist, List<List<AbilityAndTeachObj_InnerExperiment>> newabilityAndTeachObjlist, List<TeachObj_InnerExperiment> teachObjlist)throws Exception{
		float[] widths={30,50,20};
		int count=0;
		for(int i = 0;i<newindexselectlist.size();i++)
		{
			List<IndexSelect_InnerExperiment> newindexSelect= newindexselectlist.get(i);
			for(int j=0;j<newindexSelect.size();j++)
			{
				count ++;
			}
		}
		Table gr=table(3, count+1,widths);
		gr.addCell(TeachCellHead("毕业要求",1,1,3));
		gr.addCell(TeachCellHead("指标点",1,1,3));
		gr.addCell(TeachCellHead("课程教学目标",1,1,3));
		for(int gi =0;gi<newindexselectlist.size();gi++)
		{
			List<IndexSelect_InnerExperiment> indexSelectlist = newindexselectlist.get(gi);
			List<AbilityAndTeachObj_InnerExperiment> ADTlist = newabilityAndTeachObjlist.get(gi);
			Ability str= indexSelectlist.get(0).getAbility();
			List<Analysis> analysislsit = exportWordDao.findGraid(str);//指标分解表中查询毕业生能力对应的所有字段
			List<Ability> abilityList = abilityDao.getbydepartment(indexSelectlist.get(0).getAbility().getDepartment());
			int index = abilityList.indexOf(indexSelectlist.get(0).getAbility());	
			String teaobj ="" ;
				for(int tej=0;tej<ADTlist.size();tej++)
				{
					int m=0;
					for(int tex=0;tex<teachObjlist.size();tex++)
					{
						if(ADTlist.get(tej).getTeachObj_InnerExperiment() == teachObjlist.get(tex))
						{
							m = tex +1;
						}
					}
					teaobj =teaobj+ (String)("教学目标"+String.valueOf(m)+"\n");
				}
			String name= String.valueOf(index+1)+"、"+indexSelectlist.get(0).getAbility().getAbilityname()+indexSelectlist.get(0).getAbility().getAbilitycontent();
			gr.addCell(TeachCellContentChinese(name,indexSelectlist.size(),1,1));
			for(int is=0;is<indexSelectlist.size();is++)
			{
				IndexSelect_InnerExperiment indexSelect = indexSelectlist.get(is);
				int k=0;
				for(int z=0;z<analysislsit.size();z++)
				{
					if(indexSelect.getAnalisis() == analysislsit.get(z))
						k = z+1;
				}
				String zhibiao  = String.valueOf(index+1)+"-"+k+indexSelect.getAnalisis().getAnalysiscontent();
			gr.addCell(TeachCellContentChinese(zhibiao,1,1,1));
			if(is == 0)
				gr.addCell(TeachCellContentChinese(teaobj,indexSelectlist.size(),1,2));
			else continue;
			}
		}
		
		return gr;
	}	

	public Table GraRequest_TheoInnerExperiment(List<List<IndexSelect_TheoInnerExperiment>> newindexselectlist, List<List<AbilityAndTeachObj_TheoInnerExperiment>> newabilityAndTeachObjlist, List<TeachObj_TheoInnerExperiment> teachObjlist)throws Exception{
		
		float[] widths={30,50,20};
		int count=0;
		for(int i = 0;i<newindexselectlist.size();i++)
		{
			List<IndexSelect_TheoInnerExperiment> newindexSelect= newindexselectlist.get(i);
			for(int j=0;j<newindexSelect.size();j++)
			{
				count ++;
			}
		}
		Table gr=table(3, count+1,widths);
		gr.addCell(TeachCellHead("毕业要求",1,1,3));
		gr.addCell(TeachCellHead("指标点",1,1,3));
		gr.addCell(TeachCellHead("课程教学目标",1,1,3));
		for(int gi =0;gi<newindexselectlist.size();gi++)
		{
			List<IndexSelect_TheoInnerExperiment> indexSelectlist = newindexselectlist.get(gi);
			List<AbilityAndTeachObj_TheoInnerExperiment> ADTlist = newabilityAndTeachObjlist.get(gi);
			Ability str= indexSelectlist.get(0).getAbility();
			List<Analysis> analysislsit = exportWordDao.findGraid(str);//指标分解表中查询毕业生能力对应的所有字段
			List<Ability> abilityList = abilityDao.getbydepartment(indexSelectlist.get(0).getAbility().getDepartment());
			int index = abilityList.indexOf(indexSelectlist.get(0).getAbility());	
			String teaobj ="" ;
				for(int tej=0;tej<ADTlist.size();tej++)
				{
					int m=0;
					for(int tex=0;tex<teachObjlist.size();tex++)
					{
						if(ADTlist.get(tej).getTeachObj_TheoInnerExperiment() == teachObjlist.get(tex))
						{
							m = tex +1;
						}
					}
					teaobj =teaobj+ (String)("教学目标"+String.valueOf(m)+"\n");
				}
			String name= String.valueOf(index+1)+"、"+indexSelectlist.get(0).getAbility().getAbilityname()+indexSelectlist.get(0).getAbility().getAbilitycontent();
			gr.addCell(TeachCellContentChinese(name,indexSelectlist.size(),1,1));
			for(int is=0;is<indexSelectlist.size();is++)
			{
				IndexSelect_TheoInnerExperiment indexSelect = indexSelectlist.get(is);
				int k=0;
				for(int z=0;z<analysislsit.size();z++)
				{
					if(indexSelect.getAnalisis() == analysislsit.get(z))
						k = z+1;
				}
				String zhibiao  = String.valueOf(index+1)+"-"+k+indexSelect.getAnalisis().getAnalysiscontent();
			gr.addCell(TeachCellContentChinese(zhibiao,1,1,1));
			if(is == 0)
				gr.addCell(TeachCellContentChinese(teaobj,indexSelectlist.size(),1,2));
			else continue;
			}
		}
		
		return gr;
	}	

	/************************理论课大纲
	 * @param syllabusid 
	 * @param curriculumId *****************************/
	public void exportTheo(String theoid, String syllabusid, String curriculumId) throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		Document document = new Document(PageSize.A4,71,71,71,71);//左右上下
		RtfWriter2.getInstance(document,outputStream);
		Paragraph paraheader = new Paragraph();
        Font Font2 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);  
        paraheader.setFont(new Font(Font2));
        Paragraph parafooter = new Paragraph();
    			RtfFont Font1 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);
    			parafooter.setFont(new Font(Font1));
    			RtfPageNumber number = new RtfPageNumber();
    			System.out.print(number);
    		  
    		    RtfHeaderFooter footer = new RtfHeaderFooter(parafooter);
    		    footer.setAlignment(Element.ALIGN_CENTER);
    		    footer.setAlignment(1);
    		    footer.setBorderColor(Color.red);  
                footer.setBorder(Rectangle.BOX);  
    		    document.setFooter(footer);	 
    		    Curriculum newCurriculum = curriculumDao.get(curriculumId);
    if(newCurriculum.getCourseLei().equals("理论课"))
    	{	
			  TheoreticalLesson theoreticalLesson1=exportWordDao.findTheo(theoid);//查出课程信息
			  String Titlename = theoreticalLesson1.getCurriculum().getCurriculumCname();
			  HeaderFooter header=new HeaderFooter(new Phrase(Titlename+"课程教学大纲",Font2),false); 
			  header.setBorder(1);	
		      header.setBorder(Rectangle.BOTTOM); 
			  header.setBorderColor(Color.BLACK); 
			  header.setBorder(1);
			  header.setAlignment(1);
			  header.setAlignment(Element.ALIGN_CENTER);
			  document.setHeader(header);  			
	
	          document.open();
	          //TheoreticalLesson theoreticalLesson=exportWordDao.findTheo(theoid);//查出课程信息
			  //String Titlename = theoreticalLesson.getCurriculum().getCurriculumCname();
				String name=Titlename+"课程教学大纲.doc";
				response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
				document.add(Tabletitle(Titlename + "课程教学大纲"));
		
				  
				float widths1[] = {11,40,12,25};
				Table table =table(4,3,widths1); 
				Font timesNewRome=new RtfFont("Times New Roman",11, Font.NORMAL,Color.BLACK);
				Font timesNewRomeBold=new RtfFont("Times New Roman",11, Font.BOLD,Color.BLACK);
				Font songFont = new RtfFont("宋体", 11, Font.NORMAL,Color.BLACK);
				Font heiFont = new RtfFont("黑 体", 11, Font.NORMAL,Color.BLACK);
				Font heiFontBold = new RtfFont("黑 体", 11, Font.BOLD,Color.BLACK);
			  Font fontChinese = new RtfFont("黑 体",11,Font.NORMAL,Color.BLACK);
				table.setBorderWidthTop(0);
						
			    	table.setTop(0);
				 table.setPadding(0);    
			     table.setSpacing(0);  
			
				 table.setWidth(100);
			 
			     table.setAlignment(Element.ALIGN_LEFT);//居中  
			     table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中 
			  Cell cellOne = new Cell(new Phrase("英文名称：",fontChinese));
			 
			  Cell cellTwo = new Cell(new Phrase(theoreticalLesson1.getCurriculum().getCurriculumEname(),timesNewRome));
			
			  Cell cellThree = new Cell(new Phrase("课程编码：",fontChinese));
				
			  Cell cellFour = new Cell(new Phrase(theoreticalLesson1.getCurriculum().getCurriculumid(),timesNewRome));
			  Cell cellFive = new Cell(new Phrase("学    时：",fontChinese));
				Cell cellSix = new Cell(new Phrase(theoreticalLesson1.getCurriculum().getHoursOfALL(),timesNewRome));
				Cell cellSeven = new Cell(new Phrase("学    分：",fontChinese));
				Cell cellEight = new Cell(new Phrase(theoreticalLesson1.getCurriculum().getCredit(),timesNewRome));
				Cell cellNine = new Cell(new Phrase("课程性质：",fontChinese));
				Cell cellTen = new Cell(new Phrase(theoreticalLesson1.getCurriculum().getNatureOfCourse().getNatureOfCoursename(),songFont));
				Cell cellEleven = new Cell(new Phrase("课程类别：",fontChinese));
				Cell cellTwelve = new Cell(new Phrase(theoreticalLesson1.getCurriculum().getCourseLei(),songFont));
				  cellOne.setBorderColor(Color.white); 
				  cellTwo.setBorderColor(Color.white); 
				  cellThree.setBorderColor(Color.white); 
				  cellFour.setBorderColor(Color.white); 
				  cellFive.setBorderColor(Color.WHITE); 
				  cellSix.setBorderColor(Color.WHITE); 
				  cellSeven.setBorderColor(Color.WHITE); 
				  cellEight.setBorderColor(Color.WHITE); 
				  cellNine.setBorderColor(Color.WHITE); 
				  cellTen.setBorderColor(Color.WHITE); 
				  cellEleven.setBorderColor(Color.WHITE); 
				  cellTwelve.setBorderColor(Color.WHITE); 
				 
				
				table.addCell(cellOne);
				table.addCell(cellTwo);
				table.addCell(cellThree);
				table.addCell(cellFour);
				table.addCell(cellFive);
				table.addCell(cellSix);
				table.addCell(cellSeven);
				table.addCell(cellEight);
				table.addCell(cellNine);
				table.addCell(cellTen);
				table.addCell(cellEleven);
				table.addCell(cellTwelve);

			     document.add(table);  
			    

				/******理论课基本信息********/
				List<ApplicationSyllabus> applicationSyllabus=exportWordDao.findApplicationSyllabus(syllabusid);//查询查大纲基本信息
				String departmentname = "";
				if(applicationSyllabus.get(0).getProfessional() != null)
				{
					for(int ai=0;ai<applicationSyllabus.size();ai++)
					{
						if(ai != applicationSyllabus.size()-1)
							departmentname += applicationSyllabus.get(ai).getProfessional().getProfessionalname()+"、";
						else departmentname += applicationSyllabus.get(ai).getProfessional().getProfessionalname();
					}
				}
				else departmentname += applicationSyllabus.get(0).getDepartment().getDepartmentCname();
				BaseTheo baseTheo = applicationSyllabus.get(0).getSyllabus().getBaseTheo();
				if(baseTheo != null)
				{
					Paragraph largeText4 = new Paragraph();
					Chunk chunk13=new Chunk("先修课程：",heiFont);
					Chunk chunk14=new Chunk(baseTheo.getFirstcurriculum(),songFont);
					largeText4.add(chunk13);
					largeText4.add(chunk14);
					document.add(largeText4);
					
					Paragraph largeText5 = new Paragraph();
					Chunk chunk15=new Chunk("开课学期：",heiFont);
					Chunk chunk0=new Chunk("第",songFont);
					Chunk chunk16=new Chunk(theoreticalLesson1.getXueqi(),timesNewRome);
					
					Chunk chunk17=new Chunk("学期 ",songFont);
					largeText5.add(chunk15);
					largeText5.add(chunk0);
					largeText5.add(chunk16);
					largeText5.add(chunk17);
					document.add(largeText5);
					/*document.add(settitle("先修课程："+baseTheo.getFirstcurriculum()));
					document.add(settitle("开课学期：第"+theoreticalLesson.getXueqi()+"学期 "));*/
				}
				else document.add(SFourtitle("理论课基本信息不完整"));
				Paragraph largeText6 = new Paragraph();
				Chunk chunk18=new Chunk("适用专业：",heiFont);
				Chunk chunk19=new Chunk(departmentname,songFont);
				largeText6.add(chunk18);
				largeText6.add(chunk19);
				document.add(largeText6);
				//document.add(settitle("适用专业："+departmentname));
				
				TeaMethodTheo teaMethodTheo = applicationSyllabus.get(0).getSyllabus().getTeaMethodTheo();//查出教学方法与评定
				
				
				/********一、课程教学目标*******/
				document.add(HFourtitle("一、课程教学目标"));
				List<TeachObj> teachObjlist = exportWordDao.findTeachObj(syllabusid);
				if(teachObjlist != null && teachObjlist.size() != 0)
				{
					for(int ti=0;ti<teachObjlist.size();ti++)
					{
						TeachObj  teachObj= teachObjlist.get(ti);
						if(!teachObj.getTeachObjContent().equals(""))
							document.add(SFivetitle(ti+1+"、"+teachObj.getTeachObjContent()));
						else document.add(SFivetitle(""));
					}
				}
				else document.add(SFivetitle(""));

				
				/********二、课程教学目标与毕业要求的对应关系*****/
				//document.add(HFourtitle("二、课程教学目标与毕业要求的对应关系"));
				document.add(twotitle("二、课程教学目标与毕业要求的对应关系"));
				List<IndexSelect> indexselectlist = exportWordDao.findIndexSelect(syllabusid);
				List<AbilityAndTeachObj> abilityAndTeachObjlist = exportWordDao.findAbilityAndTeachObj(syllabusid);
				
				List<List<IndexSelect>> newindexselectlist = new ArrayList<List<IndexSelect>>();
				List<List<AbilityAndTeachObj>> newabilityAndTeachObjlist = new ArrayList<List<AbilityAndTeachObj>>();
				if(indexselectlist != null && indexselectlist.size() != 0 && abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
				{

					while(indexselectlist != null && indexselectlist.size() != 0 )
					{
						List<IndexSelect> StrIndex = new ArrayList<IndexSelect>();
						IndexSelect indexSelect = indexselectlist.get(0);
						for(int i=0;i<indexselectlist.size();i++)
						{
							if(indexSelect.getAbility().getAbilityid() == indexselectlist.get(i).getAbility().getAbilityid())
							{
								//String indexselectlistAndindex = String.valueOf(i+1)+"、"+indexselectlist.get(i);
								StrIndex.add(indexselectlist.get(i));
								indexselectlist.remove(i); 
								i--;
							}
						}
						newindexselectlist.add(StrIndex);
					}
					
				
					while(abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
					{
						List<AbilityAndTeachObj> StrAbandTea = new ArrayList<AbilityAndTeachObj>();
						AbilityAndTeachObj abilityAndTeachObj = abilityAndTeachObjlist.get(0);
						for(int i=0;i<abilityAndTeachObjlist.size();i++)
						{
							if(abilityAndTeachObj.getAbility() == abilityAndTeachObjlist.get(i).getAbility())
							{
								StrAbandTea.add(abilityAndTeachObjlist.get(i));
								abilityAndTeachObjlist.remove(i);
								i--;
							}
						}
						newabilityAndTeachObjlist.add(StrAbandTea);
					}
					document.add(GraRequest(newindexselectlist,newabilityAndTeachObjlist,teachObjlist));
				}
				else document.add(SFivetitle(""));
				
				/**************理论课基本内容************/
				document.add(HFourtitle("三、课程的基本内容"));
				/**************3.1 理论教学内容************/
				
				Paragraph largeText29 = new Paragraph();
				Chunk chunk62=new Chunk("3.1 ",timesNewRomeBold);
				Chunk chunk63=new Chunk("理论教学",heiFontBold);
					
				largeText29.add(chunk62);
				largeText29.add(chunk63);
				document.add(largeText29);
				
				
				//document.add(HFiveBtitle("3.1 理论教学"));
				List<ContentTheo> contentTheolist = exportWordDao.findContentTheo(syllabusid);//查出课程内容信息
				if(contentTheolist != null && contentTheolist.size() != 0)
				{
					for(int cti=0;cti<contentTheolist.size();cti++)
					{
						ContentTheo contentTheo = contentTheolist.get(cti);
						if(contentTheo != null)
						{
							document.add(SFiveBtitle1(String.valueOf(cti+1)+"、"+contentTheo.getName()+"（支撑教学目标"+contentTheo.getNum()+"）"));
							document.add(SFiveBtitle1("教学目标:"));
							document.add(SFivetitle(contentTheo.getAim()));
							document.add(SFiveBtitle1("本单元主要内容："));
							document.add(SFivetitle(contentTheo.getContent()));
		/*					document.add(SFiveBtitle("本单元重点："));
							document.add(SFivetitle(contentTheo.getEmphasis()));
							document.add(SFiveBtitle("本单元难点："));
							document.add(SFivetitle(contentTheo.getNodus()));*/
						}
						else document.add(SFourtitle(("没有填写"+String.valueOf(cti+1)+"、"+"课程的基本内容")));
					}
				}
				else document.add(SFourtitle("无"));
				
				/**************3.2 课内实验内容************/
				Paragraph largeText28 = new Paragraph();
				Chunk chunk60=new Chunk("3.2 ",timesNewRomeBold);
				Chunk chunk61=new Chunk("课内实验内容",heiFontBold);
					
				largeText28.add(chunk60);
				largeText28.add(chunk61);
				document.add(largeText28);
				
				//document.add(HFiveBtitle("3.2 课内实验内容"));
				List<ExperimentContent> expermentContentlist = exportWordDao.findexpermentContent(syllabusid);
				if(expermentContentlist != null && expermentContentlist.size() != 0)
				{
					for(int ei=0;ei<expermentContentlist.size();ei++)
					{
						ExperimentContent experimentContent = expermentContentlist.get(ei);
						if(experimentContent != null)
						{
							document.add(SFiveBtitle(String.valueOf(ei+1)+"、课内实验"+"  (支撑教学目标"+experimentContent.getNum()+")"));
							document.add(SFiveBtitle("教学目标:"));
							document.add(SFivetitle(experimentContent.getAim()));
							document.add(SFiveBtitle("主要内容:"));
							document.add(SFivetitle(experimentContent.getContent()));
						}
						else document.add(SFivetitle(("没有填写"+String.valueOf(ei+1)+"、"+"课程的课内实验内容")));
					}
				}
				else document.add(SFourtitle("无"));
				
				
				/**************3.3 讨论课内容************/
				Paragraph largeText27 = new Paragraph();
				Chunk chunk58=new Chunk("3.3 ",timesNewRomeBold);
				Chunk chunk59=new Chunk("讨论课内容",heiFontBold);
					
				largeText27.add(chunk58);
				largeText27.add(chunk59);
				document.add(largeText27);
				
				//document.add(HFiveBtitle("3.3 讨论课内容"));
				List<DiscussContent> discussContentlist = exportWordDao.findDiscussContent(syllabusid);
				if(discussContentlist != null && discussContentlist.size() != 0)
				{
					for(int di=0;di<discussContentlist.size();di++)
					{
						DiscussContent discussContent = discussContentlist.get(di);
						if(discussContent != null)
						{
							document.add(SFiveBtitle(String.valueOf(di+1)+"、讨论课"+"  (支撑教学目标"+discussContent.getNum()+")"));
							document.add(SFiveBtitle("讨论课目标:"));
							document.add(SFivetitle(discussContent.getAim()));
							document.add(SFiveBtitle("讨论课内容:"));
							document.add(SFivetitle(discussContent.getContent()));
							document.add(SFiveBtitle("讨论课的实施:"));
							document.add(SFivetitle(discussContent.getImplementation()));
							document.add(SFiveBtitle("讨论课的要求与成绩评定:"));
							document.add(SFivetitle(discussContent.getRequest()));
						}
						else document.add(SFourtitle(("没有填写"+String.valueOf(di+1)+"、"+"讨论课内容")));
					}
				}
				else document.add(SFourtitle("无"));
				
				/**************3.4 三级项目内容************/
				Paragraph largeText26 = new Paragraph();
				Chunk chunk56=new Chunk("3.4 ",timesNewRomeBold);
				Chunk chunk57=new Chunk(" 三级项目内容",heiFontBold);
					
				largeText26.add(chunk56);
				largeText26.add(chunk57);
				document.add(largeText26);
				
				//document.add(HFiveBtitle("3.4 三级项目内容"));
				List<ThreeProject> threeProjectlist = exportWordDao.findThreeProject(syllabusid);
				if(threeProjectlist != null && threeProjectlist.size() != 0)
				{
					for(int ti=0;ti<threeProjectlist.size();ti++)
					{
						ThreeProject threeProject = threeProjectlist.get(ti);
						if(threeProject != null)
						{
							document.add(SFiveBtitle(String.valueOf(ti+1)+"、三级项目"+"  (支撑教学目标"+threeProject.getNum()+")"));
							document.add(SFiveBtitle("三级项目目标:"));
							document.add(SFivetitle(threeProject.getAim()));
							document.add(SFiveBtitle("三级项目内容:"));
							document.add(SFivetitle(threeProject.getContent()));
							document.add(SFiveBtitle("三级项目的实施:"));
							document.add(SFivetitle(threeProject.getImplementation()));
							document.add(SFiveBtitle("进程安排:"));
							document.add(SFivetitle(threeProject.getSchedule()));
							document.add(SFiveBtitle("要求和成绩评定:"));
							document.add(SFivetitle(threeProject.getRequest()));
						}
						else document.add(SFourtitle(("没有填写"+String.valueOf(ti+1)+"、"+"三级项目内容内容")));
					}
				}
				else document.add(SFourtitle("无"));
				
				/**************3.5 课程其他内容************/
				Paragraph largeText25 = new Paragraph();
				Chunk chunk54=new Chunk("3.5 ",timesNewRomeBold);
				Chunk chunk55=new Chunk("课程其他内容",heiFontBold);
					
				largeText25.add(chunk54);
				largeText25.add(chunk55);
				document.add(largeText25);
				//document.add(HFiveBtitle("3.5 课程其他内容"));
				List<OtherContent> otherContentlist = exportWordDao.findOtherContent(syllabusid);
				if(otherContentlist != null && otherContentlist.size() != 0)
				{
					for(int oi=0;oi<otherContentlist.size();oi++)
					{
						OtherContent otherContent = otherContentlist.get(oi);
						if(otherContent != null)
						{
							document.add(SFiveBtitle(String.valueOf(oi+1)+otherContent.getName()+"  (支撑教学目标"+otherContent.getNum()+")"));
							document.add(SFiveBtitle("教学目标:"));
							document.add(SFivetitle(otherContent.getAim()));
							document.add(SFiveBtitle("教学内容:"));
							document.add(SFivetitle(otherContent.getContent()));
							document.add(SFiveBtitle("课程的实施:"));
							document.add(SFivetitle(otherContent.getImplementation()));
							document.add(SFiveBtitle("课程的进程安排:"));
							document.add(SFourtitle(otherContent.getSchedule()));
							document.add(SFivetitle("课程的要求和成绩评定:"));
							document.add(SFourtitle(otherContent.getRequest()));
						}
						else document.add(SFourtitle(("没有填写"+String.valueOf(oi+1)+"、"+"课程其他内容")));
					}
				}
				else document.add(SFourtitle(""));
				
				/***理论课教学安排****/
				document.add(HFourtitle("四、教学安排"));
				if(baseTheo != null && !baseTheo.getPlan().equals(""))
					document.add(SFivetitle(baseTheo.getPlan()));
				else document.add(SFourtitle("没有填写教学安排"));
				
				document.add(SFivetitle("建议学时分配如下表："));
				List<DistributeHour_Theo> distributeHour_Theolist = exportWordDao.findDistributeHour_Theo(syllabusid);
				float[] widths={8,57,10,15,10};
				Table ss = table(5,distributeHour_Theolist.size()+1,widths);
				ss.addCell(TeachCellHead("讲        课       内        容",2,2,3));
				ss.addCell(TeachCellHead("学   时",1,3,3));
				ss.addCell(TeachCellHead("讲课",1,1,3));
				ss.addCell(TeachCellHead("实验（践）",1,1,3));
				ss.addCell(TeachCellHead("上机",1,1,3));
				int i =1,totalhour=0;
				for(int si=0;si<distributeHour_Theolist.size();si++)
				{
					DistributeHour_Theo distributeHour_Theo = distributeHour_Theolist.get(si);
					ss.addCell(TeachCellContentRoman(String.valueOf(i++),3));// 序号
				//	ss.addCell(TeachCellContentChinese(String.valueOf(i++),1,1,3));// 序号
					ss.addCell(TeachCellContentChinese(distributeHour_Theo.getName(),1,1,3));//课程名称
					ss.addCell(TeachCellContentRoman(distributeHour_Theo.getHoursOfClass(),3));//讲课学时
					ss.addCell(TeachCellContentRoman(distributeHour_Theo.getHoursOfExp(),3));//实验学时
					ss.addCell(TeachCellContentRoman(distributeHour_Theo.getHoursOfCom(),3));//上机学时
					if(distributeHour_Theo.getHoursOfClass().equals(""))
					{
						distributeHour_Theo.setHoursOfClass("0");
					}
					if(distributeHour_Theo.getHoursOfExp().equals(""))
					{
						distributeHour_Theo.setHoursOfExp("0");
					}
					if(distributeHour_Theo.getHoursOfCom().equals(""))
					{
						distributeHour_Theo.setHoursOfCom("0");
					}
					if (!isInteger(distributeHour_Theo.getHoursOfClass()))
					{
						totalhour += Float.parseFloat(distributeHour_Theo.getHoursOfClass())+Float.parseFloat(distributeHour_Theo.getHoursOfExp())+Float.parseFloat(distributeHour_Theo.getHoursOfCom());
					}else if(!isInteger(distributeHour_Theo.getHoursOfExp())){
					
						totalhour += Float.parseFloat(distributeHour_Theo.getHoursOfClass())+Float.parseFloat(distributeHour_Theo.getHoursOfExp())+Float.parseFloat(distributeHour_Theo.getHoursOfCom());
					}else if (!isInteger(distributeHour_Theo.getHoursOfCom())) {
			
						totalhour += Float.parseFloat(distributeHour_Theo.getHoursOfClass())+Float.parseFloat(distributeHour_Theo.getHoursOfExp())+Float.parseFloat(distributeHour_Theo.getHoursOfCom());
					}else{
	
						totalhour += Integer.valueOf(distributeHour_Theo.getHoursOfClass())+Integer.valueOf(distributeHour_Theo.getHoursOfExp())+Integer.valueOf(distributeHour_Theo.getHoursOfCom());
					
					}
				}
				ss.addCell(TeachCellContentChinese("合计",1,2,7));
				ss.addCell(TeachCellContentRoman1(String.valueOf(totalhour),1,3,3));
				totalhour=0;
				document.add(ss);
				/**************五、教学方法************/
				document.add(HFourtitle("五、教学方法"));
				if(teaMethodTheo != null && !teaMethodTheo.getTeaMethod().equals(""))
					document.add(SFivetitle(teaMethodTheo.getTeaMethod().replace("\r\n","\n")));
				else document.add(SFourtitle("没有填写教学方法"));
				
				/**************六、教学目标达成度评价************/
				document.add(HFourtitle("六、教学目标达成度评价"));
				if(teaMethodTheo != null && !teaMethodTheo.getTeaok().equals(""))
					document.add(SFivetitle(teaMethodTheo.getTeaok().replace("\r\n","\n")));
				else document.add(SFourtitle("没有填写教学目标达成度评价"));
				
				/**************七、成绩评定************/
				document.add(HFourtitle("七、成绩评定"));
				if(teaMethodTheo != null && !teaMethodTheo.getScoreok().equals(""))
					document.add(SFivetitle(teaMethodTheo.getScoreok().replace("\r\n","\n")));
				else document.add(SFivetitle("没有填写成绩评定"));
				
				/**************八、教材信息************/
				document.add(HFourtitle("八、教材信息"));
				List<TextBooks> bookInfolist = exportWordDao.findbookInfo(syllabusid);
				if(bookInfolist != null && bookInfolist.size() != 0)
				{
					for(int bi=0;bi<bookInfolist.size();bi++)
					{
						TextBooks bookInfo = bookInfolist.get(bi);
						if(bookInfo != null && !bookInfo.getName().equals(""))
						{
							document.add(SFivetitle(String.valueOf(bi+1)+"、"+bookInfo.getName()));
						}
						else document.add(SFourtitle("没有填写教材"));
					}
				}
				else document.add(SFourtitle("没有填写教材"));
				document.add(SFourtitle(" "));
				document.add(SFourtitle(" "));
				document.add(SFourtitle(" "));
				document.add(luokuan("                                             制定人："+theoreticalLesson1.getTeacher().getUsername()));
				document.add(luokuan("                                             审定人："));
				document.add(luokuan("                                             批准者："));
				document.add(luokuan("                                             年      月       日       "));
		
    }else {
	    	PracticeLesson theoreticalLesson=exportWordDao.findOther(theoid);
			String Titlename = theoreticalLesson.getCurriculum().getCurriculumCname();
		    HeaderFooter header=new HeaderFooter(new Phrase(Titlename+"课程教学大纲",Font2),false); 
		    header.setBorder(1);
		    header.setBorder(Rectangle.BOTTOM); 
		    header.setBorderColor(Color.BLACK); 
		    header.setBorder(1);
		    header.setAlignment(1);
			header.setAlignment(Element.ALIGN_CENTER);
		    document.setHeader(header);  			
	
		    document.open();
		    /*PracticeLesson theoreticalLesson=exportWordDao.findOther(theoid);
			String Titlename = theoreticalLesson.getCurriculum().getCurriculumCname();*/
			String name=Titlename+"课程教学大纲.doc";
			response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
			document.add(Tabletitle(Titlename + "课程教学大纲"));
			float widths1[] = {12,40,12,25};
			Table table =table(4,3,widths1); 
			Font timesNewRome=new RtfFont("Times New Roman",11, Font.NORMAL,Color.BLACK);
			Font timesNewRomeBold=new RtfFont("Times New Roman",11, Font.BOLD,Color.BLACK);
			Font songFont = new RtfFont("宋体", 11, Font.NORMAL,Color.BLACK);
			Font heiFont = new RtfFont("黑 体", 11, Font.NORMAL,Color.BLACK);
			Font heiFontBold = new RtfFont("黑 体", 11, Font.BOLD,Color.BLACK);
		  Font fontChinese = new RtfFont("黑 体",11,Font.NORMAL,Color.BLACK);
			table.setBorderWidthTop(0);
					
		    	table.setTop(0);
			 table.setPadding(0);    
		     table.setSpacing(0);  
		
			 table.setWidth(100);
		 
		     table.setAlignment(Element.ALIGN_LEFT);//居中  
		     table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中 
		  Cell cellOne = new Cell(new Phrase("英文名称：",fontChinese));
		 
		  Cell cellTwo = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumEname(),timesNewRome));
		
		  Cell cellThree = new Cell(new Phrase("课程编码：",fontChinese));
			
		  Cell cellFour = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumid(),timesNewRome));
		  Cell cellFive = new Cell(new Phrase("学    时：",fontChinese));
			Cell cellSix = new Cell(new Phrase(theoreticalLesson.getCurriculum().getHoursOfALL(),timesNewRome));
			Cell cellSeven = new Cell(new Phrase("学    分：",fontChinese));
			Cell cellEight = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCredit(),timesNewRome));
			Cell cellNine = new Cell(new Phrase("课程性质：",fontChinese));
			Cell cellTen = new Cell(new Phrase(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename(),songFont));
			Cell cellEleven = new Cell(new Phrase("课程类别：",fontChinese));
			Cell cellTwelve = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCourseLei(),songFont));
			  cellOne.setBorderColor(Color.white); 
			  cellTwo.setBorderColor(Color.white); 
			  cellThree.setBorderColor(Color.white); 
			  cellFour.setBorderColor(Color.white); 
			  cellFive.setBorderColor(Color.WHITE); 
			  cellSix.setBorderColor(Color.WHITE); 
			  cellSeven.setBorderColor(Color.WHITE); 
			  cellEight.setBorderColor(Color.WHITE); 
			  cellNine.setBorderColor(Color.WHITE); 
			  cellTen.setBorderColor(Color.WHITE); 
			  cellEleven.setBorderColor(Color.WHITE); 
			  cellTwelve.setBorderColor(Color.WHITE); 
			 
			
			table.addCell(cellOne);
			table.addCell(cellTwo);
			table.addCell(cellThree);
			table.addCell(cellFour);
			table.addCell(cellFive);
			table.addCell(cellSix);
			table.addCell(cellSeven);
			table.addCell(cellEight);
			table.addCell(cellNine);
			table.addCell(cellTen);
			table.addCell(cellEleven);
			table.addCell(cellTwelve);
			
		     document.add(table);  
			
			/******理论课基本信息********/
			List<ApplicationSyllabus> applicationSyllabus=exportWordDao.findApplicationSyllabus(syllabusid);//查询查大纲基本信息
			String departmentname = "";
			if(applicationSyllabus.get(0).getProfessional() != null)
			{
				for(int ai=0;ai<applicationSyllabus.size();ai++)
				{
					if(ai != applicationSyllabus.size()-1)
						departmentname += applicationSyllabus.get(ai).getProfessional().getProfessionalname()+"、";
					else departmentname += applicationSyllabus.get(ai).getProfessional().getProfessionalname();
				}
			}
			else departmentname += applicationSyllabus.get(0).getDepartment().getDepartmentCname();
			BaseTheo baseTheo = applicationSyllabus.get(0).getSyllabus().getBaseTheo();
			if(baseTheo != null)
			{
				Paragraph largeText4 = new Paragraph();
				Chunk chunk13=new Chunk("先修课程：",heiFont);
				Chunk chunk14=new Chunk(baseTheo.getFirstcurriculum(),songFont);
				largeText4.add(chunk13);
				largeText4.add(chunk14);
				document.add(largeText4);
				Paragraph largeText5 = new Paragraph();
				Chunk chunk15=new Chunk("开课学期：",heiFont);
				Chunk chunk0=new Chunk("第",songFont);
				Chunk chunk16=new Chunk(theoreticalLesson.getXueqi(),timesNewRome);
				
				Chunk chunk17=new Chunk("学期 ",songFont);
				largeText5.add(chunk15);
				largeText5.add(chunk0);
				largeText5.add(chunk16);
				largeText5.add(chunk17);
				document.add(largeText5);
				
			}
			else document.add(SFourtitle("理论课基本信息不完整"));
			Paragraph largeText6 = new Paragraph();
			Chunk chunk18=new Chunk("适用专业：",heiFont);
			Chunk chunk19=new Chunk(departmentname,songFont);
			largeText6.add(chunk18);
			largeText6.add(chunk19);
			document.add(largeText6);
			//document.add(settitle("适用专业："+departmentname));
			


		
			
			TeaMethodTheo teaMethodTheo = applicationSyllabus.get(0).getSyllabus().getTeaMethodTheo();//查出教学方法与评定
			
			
			/********一、课程教学目标*******/
			document.add(HFourtitle("一、课程教学目标"));
			List<TeachObj> teachObjlist = exportWordDao.findTeachObj(syllabusid);
			if(teachObjlist != null && teachObjlist.size() != 0)
			{
				for(int ti=0;ti<teachObjlist.size();ti++)
				{
					TeachObj  teachObj= teachObjlist.get(ti);
					if(!teachObj.getTeachObjContent().equals(""))
						document.add(SFivetitle(ti+1+"、"+teachObj.getTeachObjContent()));
					else document.add(SFivetitle(""));
				}
			}
			else document.add(SFivetitle(""));

			
			/********二、课程教学目标与毕业要求的对应关系*****/
			document.add(HFourtitle("二、课程教学目标与毕业要求的对应关系"));
			//document.add(SFivetitle("二、课程教学目标与毕业要求的对应关系"));
			List<IndexSelect> indexselectlist = exportWordDao.findIndexSelect(syllabusid);
			List<AbilityAndTeachObj> abilityAndTeachObjlist = exportWordDao.findAbilityAndTeachObj(syllabusid);
			
			List<List<IndexSelect>> newindexselectlist = new ArrayList<List<IndexSelect>>();
			List<List<AbilityAndTeachObj>> newabilityAndTeachObjlist = new ArrayList<List<AbilityAndTeachObj>>();
			if(indexselectlist != null && indexselectlist.size() != 0 && abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
			{

				while(indexselectlist != null && indexselectlist.size() != 0 )
				{
					List<IndexSelect> StrIndex = new ArrayList<IndexSelect>();
					IndexSelect indexSelect = indexselectlist.get(0);
					for(int i=0;i<indexselectlist.size();i++)
					{
						if(indexSelect.getAbility().getAbilityid() == indexselectlist.get(i).getAbility().getAbilityid())
						{
							StrIndex.add(indexselectlist.get(i));
							indexselectlist.remove(i);
							i--;
						}
					}
					newindexselectlist.add(StrIndex);
				}
				
			
				while(abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
				{
					List<AbilityAndTeachObj> StrAbandTea = new ArrayList<AbilityAndTeachObj>();
					AbilityAndTeachObj abilityAndTeachObj = abilityAndTeachObjlist.get(0);
					for(int i=0;i<abilityAndTeachObjlist.size();i++)
					{
						if(abilityAndTeachObj.getAbility() == abilityAndTeachObjlist.get(i).getAbility())
						{
							StrAbandTea.add(abilityAndTeachObjlist.get(i));
							abilityAndTeachObjlist.remove(i);
							i--;
						}
					}
					newabilityAndTeachObjlist.add(StrAbandTea);
				}
				document.add(GraRequest(newindexselectlist,newabilityAndTeachObjlist,teachObjlist));
			}
			else document.add(SFivetitle(""));
			
			/**************理论课基本内容************/
			document.add(HFourtitle("三、课程的基本内容"));
			/**************3.1 理论教学内容************/
			
			Paragraph largeText24 = new Paragraph();
			Chunk chunk52=new Chunk("3.1 ",timesNewRomeBold);
			Chunk chunk53=new Chunk("理论教学",heiFontBold);
				
			
			largeText24.add(chunk52);
			largeText24.add(chunk53);
			document.add(largeText24);
			//document.add(HFiveBtitle("3.1 理论教学"));
			List<ContentTheo> contentTheolist = exportWordDao.findContentTheo(syllabusid);//查出课程内容信息
			if(contentTheolist != null && contentTheolist.size() != 0)
			{
				for(int cti=0;cti<contentTheolist.size();cti++)
				{
					ContentTheo contentTheo = contentTheolist.get(cti);
					if(contentTheo != null)
					{
						document.add(SFiveBtitle(String.valueOf(cti+1)+"、"+contentTheo.getName()+"（支撑教学目标"+contentTheo.getNum()+"）"));
						document.add(SFiveBtitle("教学目标:"));
						document.add(SFivetitle(contentTheo.getAim().replace("\r\n","\n")));
						document.add(SFiveBtitle("本单元主要内容："));
						document.add(SFivetitle(contentTheo.getContent().replace("\r\n","\n")));
	/*					document.add(SFiveBtitle("本单元重点："));
						document.add(SFivetitle(contentTheo.getEmphasis()));
						document.add(SFiveBtitle("本单元难点："));
						document.add(SFivetitle(contentTheo.getNodus()));*/
					}
					else document.add(SFourtitle(("没有填写"+String.valueOf(cti+1)+"、"+"课程的基本内容")));
				}
			}
			else document.add(SFourtitle("没有填写课程的理论教学内容"));
			
			/**************3.2 课内实验内容************/
		
			Paragraph largeText23 = new Paragraph();
			Chunk chunk50=new Chunk("3.2 ",timesNewRomeBold);
			Chunk chunk51=new Chunk("课内实验内容",heiFontBold);
				
			
			largeText23.add(chunk50);
			largeText23.add(chunk51);
			document.add(largeText23);
			//document.add(HFiveBtitle("3.2 课内实验内容"));
			List<ExperimentContent> expermentContentlist = exportWordDao.findexpermentContent(syllabusid);
			if(expermentContentlist != null && expermentContentlist.size() != 0)
			{
				for(int ei=0;ei<expermentContentlist.size();ei++)
				{
					ExperimentContent experimentContent = expermentContentlist.get(ei);
					if(experimentContent != null)
					{
						document.add(SFiveBtitle(String.valueOf(ei+1)+"、课内实验  (支撑教学目标"+experimentContent.getNum()+")"));
						document.add(SFiveBtitle("教学目标:"));
						document.add(SFivetitle(experimentContent.getAim().replace("\r\n","\n")));
						document.add(SFiveBtitle("主要内容:"));
						document.add(SFivetitle(experimentContent.getContent().replace("\r\n","\n")));
					}
					else document.add(SFivetitle(("没有填写"+String.valueOf(ei+1)+"、"+"课程的课内实验内容")));
				}
			}
			else document.add(SFourtitle("无"));
			
			
			/**************3.3 讨论课内容************/
			Paragraph largeText22 = new Paragraph();
			Chunk chunk48=new Chunk("3.3 ",timesNewRomeBold);
			Chunk chunk49=new Chunk("讨论课内容",heiFontBold);
				
			
			largeText22.add(chunk48);
			largeText22.add(chunk49);
			document.add(largeText22);
			
			//document.add(HFiveBtitle("3.3 讨论课内容"));
			List<DiscussContent> discussContentlist = exportWordDao.findDiscussContent(syllabusid);
			if(discussContentlist != null && discussContentlist.size() != 0)
			{
				for(int di=0;di<discussContentlist.size();di++)
				{
					DiscussContent discussContent = discussContentlist.get(di);
					if(discussContent != null)
					{
						document.add(SFiveBtitle(String.valueOf(di+1)+"讨论课"+"  (支撑教学目标"+discussContent.getNum()+")"));
						document.add(SFiveBtitle("讨论课目标:"));
						document.add(SFivetitle(discussContent.getAim().replace("\r\n","\n")));
						document.add(SFiveBtitle("讨论课内容:"));
						document.add(SFivetitle(discussContent.getContent().replace("\r\n","\n")));
						document.add(SFiveBtitle("讨论课的实施:"));
						document.add(SFivetitle(discussContent.getImplementation().replace("\r\n","\n")));
						document.add(SFiveBtitle("讨论课的要求与成绩评定:"));
						document.add(SFivetitle(discussContent.getRequest()));
					}
					else document.add(SFourtitle(("没有填写"+String.valueOf(di+1)+"、"+"讨论课内容")));
				}
			}
			else document.add(SFourtitle("无"));
			
			/**************3.4 三级项目内容************/
			Paragraph largeText21 = new Paragraph();
			Chunk chunk46=new Chunk("3.4 ",timesNewRomeBold);
			Chunk chunk47=new Chunk("三级项目内容",heiFontBold);
				
			
			largeText21.add(chunk46);
			largeText21.add(chunk47);
			document.add(largeText21);
			
			//document.add(HFiveBtitle("3.4 三级项目内容"));
			List<ThreeProject> threeProjectlist = exportWordDao.findThreeProject(syllabusid);
			if(threeProjectlist != null && threeProjectlist.size() != 0)
			{
				for(int ti=0;ti<threeProjectlist.size();ti++)
				{
					ThreeProject threeProject = threeProjectlist.get(ti);
					if(threeProject != null)
					{
						document.add(SFiveBtitle("三级项目"+String.valueOf(ti+1)+"  (支撑教学目标"+threeProject.getNum()+")"));
						document.add(SFiveBtitle("三级项目目标:"));
						document.add(SFivetitle(threeProject.getAim().replace("\r\n","\n")));
						document.add(SFiveBtitle("三级项目内容:"));
						document.add(SFivetitle(threeProject.getContent().replace("\r\n","\n")));
						document.add(SFiveBtitle("三级项目的实施:"));
						document.add(SFivetitle(threeProject.getImplementation().replace("\r\n","\n")));
						document.add(SFiveBtitle("进程安排:"));
						document.add(SFivetitle(threeProject.getSchedule().replace("\r\n","\n")));
						document.add(SFiveBtitle("要求和成绩评定:"));
						document.add(SFivetitle(threeProject.getRequest().replace("\r\n","\n")));
					}
					else document.add(SFourtitle(("没有填写"+String.valueOf(ti+1)+"、"+"三级项目内容内容")));
				}
			}
			else document.add(SFourtitle("无"));
			
			/**************3.5 课程其他内容************/
			
			Paragraph largeText20 = new Paragraph();
			Chunk chunk44=new Chunk("3.5 ",timesNewRomeBold);
			Chunk chunk45=new Chunk("课程其他内容",heiFontBold);
				
			
			largeText20.add(chunk44);
			largeText20.add(chunk45);
			document.add(largeText20);
			//document.add(HFiveBtitle("3.5 课程其他内容"));
			List<OtherContent> otherContentlist = exportWordDao.findOtherContent(syllabusid);
			if(otherContentlist != null && otherContentlist.size() != 0)
			{
				for(int oi=0;oi<otherContentlist.size();oi++)
				{
					OtherContent otherContent = otherContentlist.get(oi);
					if(otherContent != null)
					{
						document.add(SFiveBtitle(String.valueOf(oi+1)+otherContent.getName()+"  (支撑教学目标"+otherContent.getNum()+")"));
						document.add(SFiveBtitle("教学目标:"));
						document.add(SFivetitle(otherContent.getAim().replace("\r\n","\n")));
						document.add(SFiveBtitle("教学内容:"));
						document.add(SFivetitle(otherContent.getContent().replace("\r\n","\n")));
						document.add(SFiveBtitle("课程的实施:"));
						document.add(SFivetitle(otherContent.getImplementation().replace("\r\n","\n")));
						document.add(SFiveBtitle("课程的进程安排:"));
						document.add(SFourtitle(otherContent.getSchedule().replace("\r\n","\n")));
						document.add(SFivetitle("课程的要求和成绩评定:"));
						document.add(SFourtitle(otherContent.getRequest().replace("\r\n","\n")));
					}
					else document.add(SFourtitle(("没有填写"+String.valueOf(oi+1)+"、"+"课程其他内容")));
				}
			}
			else document.add(SFourtitle(""));
			
			/***理论课教学安排****/
			document.add(HFourtitle("四、教学安排"));
			if(baseTheo != null && !baseTheo.getPlan().equals(""))
				document.add(SFivetitle(baseTheo.getPlan()));
			else document.add(SFourtitle("没有填写教学安排"));
			
			document.add(SFivetitle("建议学时分配如下表："));
			List<DistributeHour_Theo> distributeHour_Theolist = exportWordDao.findDistributeHour_Theo(syllabusid);
			float[] widths={8,57,10,15,10};
			Table ss = table(5,distributeHour_Theolist.size()+1,widths);
			ss.addCell(TeachCellHead("讲        课       内        容",2,2,3));
			ss.addCell(TeachCellHead("学   时",1,3,3));
			ss.addCell(TeachCellHead("讲课",1,1,3));
			ss.addCell(TeachCellHead("实验（践）",1,1,3));
			ss.addCell(TeachCellHead("上机",1,1,3));
			int i =1,totalhour=0;
			for(int si=0;si<distributeHour_Theolist.size();si++)
			{
				DistributeHour_Theo distributeHour_Theo = distributeHour_Theolist.get(si);
				ss.addCell(TeachCellContentChinese(String.valueOf(i++),1,1,3));// 序号
				ss.addCell(TeachCellContentChinese(distributeHour_Theo.getName(),1,1,3));//课程名称
				ss.addCell(TeachCellContentRoman(distributeHour_Theo.getHoursOfClass(),3));//讲课学时
				ss.addCell(TeachCellContentRoman(distributeHour_Theo.getHoursOfExp(),3));//实验学时
				ss.addCell(TeachCellContentRoman(distributeHour_Theo.getHoursOfCom(),3));//上机学时
				if(distributeHour_Theo.getHoursOfClass().equals(""))
				{
					distributeHour_Theo.setHoursOfClass("0");
				}
				if(distributeHour_Theo.getHoursOfExp().equals(""))
				{
					distributeHour_Theo.setHoursOfExp("0");
				}
				if(distributeHour_Theo.getHoursOfCom().equals(""))
				{
					distributeHour_Theo.setHoursOfCom("0");
				}
				totalhour += Integer.valueOf(distributeHour_Theo.getHoursOfClass())+Integer.valueOf(distributeHour_Theo.getHoursOfExp())+Integer.valueOf(distributeHour_Theo.getHoursOfCom());
			}
			ss.addCell(TeachCellContentChinese("合计",1,2,3));
			ss.addCell(TeachCellContentRoman1(String.valueOf(totalhour),1,3,3));
			totalhour=0;
			document.add(ss);
			/**************五、教学方法************/
			document.add(HFourtitle("五、教学方法"));
			if(teaMethodTheo != null && !teaMethodTheo.getTeaMethod().equals(""))
				document.add(SFivetitle(teaMethodTheo.getTeaMethod().replace("\r\n","\n")));
			else document.add(SFourtitle("没有填写教学方法"));
			
			/**************六、教学目标达成度评价************/
			document.add(HFourtitle("六、教学目标达成度评价"));
			if(teaMethodTheo != null && !teaMethodTheo.getTeaok().equals(""))
				document.add(SFivetitle(teaMethodTheo.getTeaok().replace("\r\n","\n")));
			else document.add(SFourtitle("没有填写教学目标达成度评价"));
			
			/**************七、成绩评定************/
			document.add(HFourtitle("七、成绩评定"));
			if(teaMethodTheo != null && !teaMethodTheo.getScoreok().equals(""))
				document.add(SFivetitle(teaMethodTheo.getScoreok().replace("\r\n","\n")));
			else document.add(SFivetitle("没有填写成绩评定"));
			
			/**************八、教材信息************/
			document.add(HFourtitle("八、教材信息"));
			List<TextBooks> bookInfolist = exportWordDao.findbookInfo(syllabusid);
			if(bookInfolist != null && bookInfolist.size() != 0)
			{
				for(int bi=0;bi<bookInfolist.size();bi++)
				{
					TextBooks bookInfo = bookInfolist.get(bi);
					if(bookInfo != null && !bookInfo.getName().equals(""))
					{
						document.add(SFivetitle(String.valueOf(bi+1)+"、"+bookInfo.getName()));
					}
					else document.add(SFourtitle("没有填写教材"));
				}
			}
			else document.add(SFourtitle("没有填写教材"));
			document.add(SFourtitle(" "));
			document.add(SFourtitle(" "));
			document.add(SFourtitle(" "));
			document.add(luokuan("                                             制定人："+theoreticalLesson.getTeacher().getUsername()));
			document.add(luokuan("                                             审定人："));
			document.add(luokuan("                                             批准者："));
			document.add(luokuan("                                             年      月       日       "));
			
	}		
	
    document.close();
	outputStream.flush();
	
}
	/********************毕业设计**********************/
	public void exportGra(String pracid,String syllabusid) throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);
		 Paragraph paraheader = new Paragraph();
	        
	        Font Font2 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);  
	        paraheader.setFont(new Font(Font2));

		    
	        HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        
	        header.setBorder(Rectangle.BOTTOM); 
	        header.setBorderColor(Color.red); 
	        header.setAlignment(1);
		    header.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header);  			
		
		 Paragraph parafooter = new Paragraph();
			
			RtfFont Font1 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);
			parafooter.setFont(new Font(Font1));
			//parafooter.add(new Phrase("第"));
			parafooter.add(new RtfPageNumber());
			//parafooter.add(new Phrase("页  共"));
			//parafooter.add(new RtfTotalPageNumber());
			//parafooter.add(new Phrase("页"));
		  
		    RtfHeaderFooter footer = new RtfHeaderFooter(parafooter);
		    footer.setAlignment(Element.ALIGN_CENTER);
		    footer.setAlignment(1);
		    footer.setBorderColor(Color.red);  
            footer.setBorder(Rectangle.BOX);  
		    document.setFooter(footer);	 
		    PracticeLesson theoreticalLesson2=exportWordDao.findPrac(pracid);//查出课程信息
	        String Titlename1 = theoreticalLesson2.getCurriculum().getCurriculumCname();
	      //  HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        HeaderFooter header1=new HeaderFooter(new Phrase(Titlename1+"课程教学大纲",Font2),false); 
	        
	        header1.setBorder(Rectangle.BOTTOM); 
	        header1.setBorderColor(Color.BLACK); 
	        header1.setAlignment(1);
		    header1.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header1);  			

		    document.open();
		PracticeLesson theoreticalLesson=exportWordDao.findPrac(pracid);//查出课程信息
		
		
		String Titlename = theoreticalLesson.getCurriculum().getCurriculumCname();
		String name=Titlename+"大纲.doc";
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		document.add(Tabletitle(Titlename + "大纲"));
		
		
		float widths1[] = {12,40,12,25};
		Table table =table(4,3,widths1); 
		Font timesNewRome=new RtfFont("Times New Roman",11, Font.NORMAL,Color.BLACK);
		Font timesNewRomeBold=new RtfFont("Times New Roman",11, Font.BOLD,Color.BLACK);
		Font songFont = new RtfFont("宋体", 11, Font.NORMAL,Color.BLACK);
		Font heiFont = new RtfFont("黑 体", 11, Font.NORMAL,Color.BLACK);
		Font heiFontBold = new RtfFont("黑 体", 11, Font.BOLD,Color.BLACK);
		Font fontChinese = new RtfFont("黑 体",11,Font.NORMAL,Color.BLACK);
		table.setBorderWidthTop(0);
				
			table.setTop(0);
		 table.setPadding(0);    
		 table.setSpacing(0);  

		 table.setWidth(100);

		 table.setAlignment(Element.ALIGN_LEFT);//居中  
		 table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中 
		Cell cellOne = new Cell(new Phrase("英文名称：",fontChinese));

		Cell cellTwo = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumEname(),timesNewRome));

		Cell cellThree = new Cell(new Phrase("课程编码：",fontChinese));

		Cell cellFour = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumid(),timesNewRome));
		Cell cellFive = new Cell(new Phrase("学    时：",fontChinese));
		Cell cellSix = new Cell(new Phrase(theoreticalLesson.getCurriculum().getHoursOfALL(),timesNewRome));
		Cell cellSeven = new Cell(new Phrase("学    分：",fontChinese));
		Cell cellEight = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCredit(),timesNewRome));
		Cell cellNine = new Cell(new Phrase("课程性质：",fontChinese));
		Cell cellTen = new Cell(new Phrase(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename(),songFont));
		Cell cellEleven = new Cell(new Phrase("课程类别：",fontChinese));
		Cell cellTwelve = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCourseLei(),songFont));
		  cellOne.setBorderColor(Color.white); 
		  cellTwo.setBorderColor(Color.white); 
		  cellThree.setBorderColor(Color.white); 
		  cellFour.setBorderColor(Color.white); 
		  cellFive.setBorderColor(Color.WHITE); 
		  cellSix.setBorderColor(Color.WHITE); 
		  cellSeven.setBorderColor(Color.WHITE); 
		  cellEight.setBorderColor(Color.WHITE); 
		  cellNine.setBorderColor(Color.WHITE); 
		  cellTen.setBorderColor(Color.WHITE); 
		  cellEleven.setBorderColor(Color.WHITE); 
		  cellTwelve.setBorderColor(Color.WHITE); 
		 

		table.addCell(cellOne);
		table.addCell(cellTwo);
		table.addCell(cellThree);
		table.addCell(cellFour);
		table.addCell(cellFive);
		table.addCell(cellSix);
		table.addCell(cellSeven);
		table.addCell(cellEight);
		table.addCell(cellNine);
		table.addCell(cellTen);
		table.addCell(cellEleven);
		table.addCell(cellTwelve);

		 document.add(table);  
		
	
		List<ApplicationSyllabus_Gra> applicationSyllabus_Gra = exportWordDao.findApplicationSyllabus_Gra(syllabusid);//查出毕业设计基本信息
		
		String departmentname = "";
		if(applicationSyllabus_Gra.get(0).getProfessional() != null)
		{
			for(int ai=0;ai<applicationSyllabus_Gra.size();ai++)
			{
				if(ai != applicationSyllabus_Gra.size()-1)
					departmentname += applicationSyllabus_Gra.get(ai).getProfessional().getProfessionalname()+"、";
				else departmentname += applicationSyllabus_Gra.get(ai).getProfessional().getProfessionalname();
			}
		}
		else departmentname += applicationSyllabus_Gra.get(0).getDepartment().getDepartmentCname();
		BasePractice basePractice = applicationSyllabus_Gra.get(0).getSyllabus_Gra().getBasePractice();
		
		if(basePractice != null)
		{
			Paragraph largeText6 = new Paragraph();
			Chunk chunk18=new Chunk("适用专业：",heiFont);
			Chunk chunk19=new Chunk(departmentname,songFont);
			largeText6.add(chunk18);
			largeText6.add(chunk19);
			document.add(largeText6);
			
		}
		else document.add(settitle("无"));	
			

		/**************一、毕业设计（论文）的教学目标***************/
		document.add(HFourtitle("一、毕业设计（论文）的教学目标"));
		List<TeachObj_Gra> teachObjlist = exportWordDao.findTeachObj_Gra(syllabusid);
		if(teachObjlist != null && teachObjlist.size() != 0)
		{
			for(int ti=0;ti<teachObjlist.size();ti++)
			{
				TeachObj_Gra  teachObj= teachObjlist.get(ti);
				if(!teachObj.getTeachObjContent_Gra().equals(""))
					document.add(SFivetitle(ti+1+"、"+teachObj.getTeachObjContent_Gra()));
				else document.add(SFivetitle(""));
			}
		}
		else document.add(SFivetitle(""));

		
		/**************二、毕业设计（论文）的教学目标与毕业要求的对应关系***************/
		document.add(HFourtitle("二、毕业设计（论文）的教学目标与毕业要求的对应关"));
		List<IndexSelect_Gra> indexselectlist = exportWordDao.findIndexSelect_Gra(syllabusid);
		List<AbilityAndTeachObj_Gra> abilityAndTeachObjlist = exportWordDao.findAbilityAndTeachObj_Gra(syllabusid);
		
		List<List<IndexSelect_Gra>> newindexselectlist = new ArrayList<List<IndexSelect_Gra>>();
		List<List<AbilityAndTeachObj_Gra>> newabilityAndTeachObjlist = new ArrayList<List<AbilityAndTeachObj_Gra>>();
		if(indexselectlist != null && indexselectlist.size() != 0 && abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
		{

			while(indexselectlist != null && indexselectlist.size() != 0 )
			{
				List<IndexSelect_Gra> StrIndex = new ArrayList<IndexSelect_Gra>();
				IndexSelect_Gra indexSelect = indexselectlist.get(0);
				for(int i=0;i<indexselectlist.size();i++)
				{
					if(indexSelect.getAbility().getAbilityid() == indexselectlist.get(i).getAbility().getAbilityid())
					{
						StrIndex.add(indexselectlist.get(i));
						indexselectlist.remove(i);
						i--;
					}
				}
				newindexselectlist.add(StrIndex);
			}
			
		
			while(abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
			{
				List<AbilityAndTeachObj_Gra> StrAbandTea = new ArrayList<AbilityAndTeachObj_Gra>();
				AbilityAndTeachObj_Gra abilityAndTeachObj = abilityAndTeachObjlist.get(0);
				for(int i=0;i<abilityAndTeachObjlist.size();i++)
				{
					if(abilityAndTeachObj.getAbility() == abilityAndTeachObjlist.get(i).getAbility())
					{
						StrAbandTea.add(abilityAndTeachObjlist.get(i));
						abilityAndTeachObjlist.remove(i);
						i--;
					}
				}
				newabilityAndTeachObjlist.add(StrAbandTea);
			}
			document.add(GraRequest_Gra(newindexselectlist,newabilityAndTeachObjlist,teachObjlist));
		}
		else document.add(SFivetitle(""));
		
		
		/**************三、毕业设计（论文）的选题原则***************/
		document.add(HFourtitle("三、毕业设计（论文）的选题原则"));
		if(basePractice != null && basePractice.getTopic() != null)
		{
			document.add(SFivetitle(basePractice.getTopic()));
		}
		else document.add(SFivetitle("没有填写选题原则"));
		
		/**************四、毕业设计（论文）的内容及要求***************/
		
		ContentGra contentGralist = applicationSyllabus_Gra.get(0).getSyllabus_Gra().getContentGra();//查出毕业设计基本信息
		document.add(HFourtitle("四、毕业设计（论文）的内容及要求"));
		if(contentGralist != null)
		{
			if(contentGralist.getContent() != null)
			{
				
				Paragraph largeText18 = new Paragraph();
				Chunk chunk42=new Chunk("4.1 ",timesNewRomeBold);
				Chunk chunk43=new Chunk("毕业设计（论文）的基本内容",heiFontBold);
					
				
				largeText18.add(chunk42);
				largeText18.add(chunk43);
				document.add(largeText18);
				//document.add(SFivetitle("4.1 毕业设计（论文）的基本内容"));
				document.add(SFivetitle(contentGralist.getContent().replace("\r\n","\n")));
			}
			else document.add(SFivetitle("没有填写毕业设计（论文）的内容"));
			
			if(contentGralist.getRequest() != null)
			{
				Paragraph largeText18 = new Paragraph();
				Chunk chunk42=new Chunk("4.2 ",timesNewRomeBold);
				Chunk chunk43=new Chunk("毕业设计（论文）的基本要求",heiFontBold);
					
				
				largeText18.add(chunk42);
				largeText18.add(chunk43);
				document.add(largeText18);
				
				//document.add(SFivetitle("4.2 毕业设计（论文）的基本要求"));
				document.add(SFivetitle(contentGralist.getRequest()));
			}
			else 
				
			{	Paragraph largeText18 = new Paragraph();
			Chunk chunk42=new Chunk("4.2 ",timesNewRomeBold);
			Chunk chunk43=new Chunk("毕业设计（论文）的基本要求",heiFontBold);
				
			
			largeText18.add(chunk42);
			largeText18.add(chunk43);
			document.add(largeText18);
				//document.add(SFivetitle("4.2 毕业设计（论文）的基本要求"));
		}}
		else document.add(SFivetitle("没有填写毕业设计（论文）的内容及要求"));
		
		/**************五、毕业设计（论文）的进度安排***************/
		document.add(HFourtitle("五、毕业设计（论文）的进度安排"));
		if(basePractice != null && basePractice.getSchedule() != null)
		{
			document.add(SFivetitle(basePractice.getSchedule()));
		}
		else document.add(SFivetitle("没有填写毕业设计（论文）的进度安排"));
		List<DistributeHour_Gra> distributeHour_Gralist = exportWordDao.findDistributeHour_Gra(syllabusid);
		float[] widths={8,62,30};
		Table ss = table(3,distributeHour_Gralist.size()+1,widths);
		ss.addCell(TeachCellHead("工     作     内      容",1,2,3));
		ss.addCell(TeachCellHead("时  间",1,1,3));

		int i =1;
		for(int si=0;si<distributeHour_Gralist.size();si++)
		{
			DistributeHour_Gra distributeHour_Gra = distributeHour_Gralist.get(si);
			ss.addCell(TeachCellContentChinese(String.valueOf(i++),1,1,3));// 序号
			ss.addCell(TeachCellContentChinese(distributeHour_Gra.getName(),1,1,3));//课程名称
			ss.addCell(TeachCellContentRoman(distributeHour_Gra.getTime(),3));//讲课学时
		}
		ss.addCell(TeachCellContentChinese("合计",1,2,3));
		ss.addCell(TeachCellContentRoman1("17周",1,3,3));
		document.add(ss);
		
		/**************六、成绩考核与评定***************/
		document.add(HFourtitle("六、成绩考核与评定"));
		if(basePractice != null && basePractice.getAssess() != null)
		{
			document.add(SFivetitle(basePractice.getAssess()));
		}
		else document.add(SFivetitle("没有填写毕业设计（论文）的成绩考核与评定"));
		
		/**************七、毕业设计（论文）参考资料***************/
		document.add(HFourtitle("七、毕业设计（论文）参考资料"));
		List<PracticeBook> bookInfolist = exportWordDao.findPracticeBook(syllabusid);
		if(bookInfolist != null && bookInfolist.size() != 0)
		{
			for(int bi=0;bi<bookInfolist.size();bi++)
			{
				PracticeBook bookInfo = bookInfolist.get(bi);
				if(bookInfo != null && !bookInfo.getName().equals(""))
				{
					document.add(SFivetitle(String.valueOf(bi+1)+"、"+bookInfo.getName()));
				}
				else document.add(SFivetitle("没有填写教材"));
			}
		}
		else document.add(SFourtitle("没有填写教材"));
		
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		document.add(luokuan("                                             制定人："+theoreticalLesson2.getTeacher().getUsername()));
		document.add(luokuan("                                             审定人："));
		document.add(luokuan("                                             批准者："));
		document.add(luokuan("                                             年      月       日       "));
		document.close();
		outputStream.flush();
	}
	
	
	/**********************实习大纲
	 * @param syllabusid ***********************************/
	public void exportFieldwork(String fieldid, String syllabusid) throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);		
		
		 Paragraph paraheader = new Paragraph();
	        
	        Font Font2 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);  
	        paraheader.setFont(new Font(Font2));

		    
	        HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        
	        header.setBorder(Rectangle.BOTTOM); 
	        header.setBorderColor(Color.red); 
	        header.setAlignment(1);
		    header.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header);  			
		Paragraph parafooter = new Paragraph();
			
			RtfFont Font1 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);
			parafooter.setFont(new Font(Font1));
			//parafooter.add(new Phrase("第"));
			parafooter.add(new RtfPageNumber());
			//parafooter.add(new Phrase("页  共"));
			//parafooter.add(new RtfTotalPageNumber());
			//parafooter.add(new Phrase("页"));
		  
		    RtfHeaderFooter footer = new RtfHeaderFooter(parafooter);
		    footer.setAlignment(Element.ALIGN_CENTER);
		    footer.setAlignment(1);
		    footer.setBorderColor(Color.red);  
            footer.setBorder(Rectangle.BOX);  
		    document.setFooter(footer);	 
		    PracticeLesson theoreticalLesson3=exportWordDao.findField(fieldid);//查出课程信息
	        String Titlename1 = theoreticalLesson3.getCurriculum().getCurriculumCname();
	      //  HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        HeaderFooter header3=new HeaderFooter(new Phrase(Titlename1+"课程教学大纲",Font2),false); 
	        
	        header3.setBorder(Rectangle.BOTTOM); 
	        header3.setBorderColor(Color.BLACK); 
	    
	        header3.setAlignment(1);
		    header3.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header3);  		
		    document.open();
		PracticeLesson theoreticalLesson=exportWordDao.findField(fieldid);//查出课程信息

		
		
		String Titlename = theoreticalLesson.getCurriculum().getCurriculumCname();
		String name=Titlename+"实习大纲.doc";
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		document.add(Tabletitle(Titlename + "大纲"));
		/********************课程基本信息*************************/	
		
		
		float widths1[] = {12,40,12,25};
		Table table =table(4,3,widths1); 
		Font timesNewRome=new RtfFont("Times New Roman",11, Font.NORMAL,Color.BLACK);
		Font timesNewRomeBold=new RtfFont("Times New Roman",11, Font.BOLD,Color.BLACK);
		Font songFont = new RtfFont("宋体", 11, Font.NORMAL,Color.BLACK);
		Font heiFont = new RtfFont("黑 体", 11, Font.NORMAL,Color.BLACK);
		Font heiFontBold = new RtfFont("黑 体", 11, Font.BOLD,Color.BLACK);
		Font fontChinese = new RtfFont("黑 体",11,Font.NORMAL,Color.BLACK);
		table.setBorderWidthTop(0);
				
			table.setTop(0);
		 table.setPadding(0);    
		 table.setSpacing(0);  

		 table.setWidth(100);

		 table.setAlignment(Element.ALIGN_LEFT);//居中  
		 table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中 
		Cell cellOne = new Cell(new Phrase("英文名称：",fontChinese));

		Cell cellTwo = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumEname(),timesNewRome));

		Cell cellThree = new Cell(new Phrase("课程编码：",fontChinese));

		Cell cellFour = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumid(),timesNewRome));
		Cell cellFive = new Cell(new Phrase("学    时：",fontChinese));
		Cell cellSix = new Cell(new Phrase(theoreticalLesson.getCurriculum().getHoursOfALL(),timesNewRome));
		Cell cellSeven = new Cell(new Phrase("学    分：",fontChinese));
		Cell cellEight = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCredit(),timesNewRome));
		Cell cellNine = new Cell(new Phrase("课程性质：",fontChinese));
		Cell cellTen = new Cell(new Phrase(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename(),songFont));
		Cell cellEleven = new Cell(new Phrase("课程类别：",fontChinese));
		Cell cellTwelve = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCourseLei(),songFont));
		  cellOne.setBorderColor(Color.white); 
		  cellTwo.setBorderColor(Color.white); 
		  cellThree.setBorderColor(Color.white); 
		  cellFour.setBorderColor(Color.white); 
		  cellFive.setBorderColor(Color.WHITE); 
		  cellSix.setBorderColor(Color.WHITE); 
		  cellSeven.setBorderColor(Color.WHITE); 
		  cellEight.setBorderColor(Color.WHITE); 
		  cellNine.setBorderColor(Color.WHITE); 
		  cellTen.setBorderColor(Color.WHITE); 
		  cellEleven.setBorderColor(Color.WHITE); 
		  cellTwelve.setBorderColor(Color.WHITE); 
		 

		table.addCell(cellOne);
		table.addCell(cellTwo);
		table.addCell(cellThree);
		table.addCell(cellFour);
		table.addCell(cellFive);
		table.addCell(cellSix);
		table.addCell(cellSeven);
		table.addCell(cellEight);
		table.addCell(cellNine);
		table.addCell(cellTen);
		table.addCell(cellEleven);
		table.addCell(cellTwelve);

		 document.add(table);  
		/******************基本信息*************************/
		List<ApplicationSyllabus_FieldWork> applicationSyllabus_FieldWorklist = exportWordDao.findApplicationSyllabus_FieldWork(syllabusid);
		FieldWork fieldWork = applicationSyllabus_FieldWorklist.get(0).getSyllabus_FieldWork().getFieldWork();//查出毕业设计基本信息
		

		String departmentname = "";
		if(applicationSyllabus_FieldWorklist.get(0).getProfessional() != null)
		{
			for(int ai=0;ai<applicationSyllabus_FieldWorklist.size();ai++)
			{
				if(ai != applicationSyllabus_FieldWorklist.size()-1)
					departmentname += applicationSyllabus_FieldWorklist.get(ai).getProfessional().getProfessionalname()+"、";
				else departmentname += applicationSyllabus_FieldWorklist.get(ai).getProfessional().getProfessionalname();
			}
		}
		else departmentname += applicationSyllabus_FieldWorklist.get(0).getDepartment().getDepartmentCname();
		
		if(fieldWork != null)
		{   Paragraph largeText6 = new Paragraph();
		   Chunk chunk18=new Chunk("适用专业：",heiFont);
		   Chunk chunk19=new Chunk(departmentname,songFont);
		   largeText6.add(chunk18);
		   largeText6.add(chunk19);
		   document.add(largeText6);
			
			if(!fieldWork.getMaptheo().equals("")){
				Paragraph largeText7 = new Paragraph();
				   Chunk chunk20=new Chunk("对应理论课：",heiFont);
				   Chunk chunk21=new Chunk(fieldWork.getMaptheo(),songFont);
				   largeText7.add(chunk20);
				   largeText7.add(chunk21);
				   document.add(largeText7);}
					
				//document.add(HFivetitle("对应理论课："+fieldWork.getMaptheo()));}
			else document.add(settitle("无"));
		}
		else document.add(settitle("无"));
		
		
		/******************一、本课程的教学目标*************************/
		document.add(HFourtitle("一、本课程的教学目标"));
		List<TeachObj_FieldWork> teachObjlist = exportWordDao.findTeachObj_FieldWork(syllabusid);
		if(teachObjlist != null && teachObjlist.size() != 0)
		{
			for(int ti=0;ti<teachObjlist.size();ti++)
			{
				TeachObj_FieldWork  teachObj= teachObjlist.get(ti);
				if(!teachObj.getTeachObjContent_FieldWork().equals(""))
					document.add(SFivetitle(ti+1+"、"+teachObj.getTeachObjContent_FieldWork()));
				else document.add(SFivetitle(""));
			}
		}
		else document.add(SFivetitle(""));

		
		/******************二、本课程的教学目标与毕业要求的对应关系*************************/
		document.add(HFourtitle("二、本课程的教学目标与毕业要求的对应关系"));
		List<IndexSelect_FieldWork> indexselectlist = exportWordDao.findIndexSelect_FieldWork(syllabusid);
		List<AbilityAndTeachObj_FieldWork> abilityAndTeachObjlist = exportWordDao.findAbilityAndTeachObj_FieldWork(syllabusid);
		
		List<List<IndexSelect_FieldWork>> newindexselectlist = new ArrayList<List<IndexSelect_FieldWork>>();
		List<List<AbilityAndTeachObj_FieldWork>> newabilityAndTeachObjlist = new ArrayList<List<AbilityAndTeachObj_FieldWork>>();
		if(indexselectlist != null && indexselectlist.size() != 0 && abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
		{

			while(indexselectlist != null && indexselectlist.size() != 0 )
			{
				List<IndexSelect_FieldWork> StrIndex = new ArrayList<IndexSelect_FieldWork>();
				IndexSelect_FieldWork indexSelect = indexselectlist.get(0);
				for(int i=0;i<indexselectlist.size();i++)
				{
					if(indexSelect.getAbility().getAbilityid() == indexselectlist.get(i).getAbility().getAbilityid())
					{
						StrIndex.add(indexselectlist.get(i));
						indexselectlist.remove(i);
						i--;
					}
				}
				newindexselectlist.add(StrIndex);
			}
			
		
			while(abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
			{
				List<AbilityAndTeachObj_FieldWork> StrAbandTea = new ArrayList<AbilityAndTeachObj_FieldWork>();
				AbilityAndTeachObj_FieldWork abilityAndTeachObj = abilityAndTeachObjlist.get(0);
				for(int i=0;i<abilityAndTeachObjlist.size();i++)
				{
					if(abilityAndTeachObj.getAbility() == abilityAndTeachObjlist.get(i).getAbility())
					{
						StrAbandTea.add(abilityAndTeachObjlist.get(i));
						abilityAndTeachObjlist.remove(i);
						i--;
					}
				}
				newabilityAndTeachObjlist.add(StrAbandTea);
			}
			document.add(GraRequest_FieldWork(newindexselectlist,newabilityAndTeachObjlist,teachObjlist));
		}
		else document.add(SFivetitle(""));
		
		
		
		/******************三、本课程的教学内容及要求	*************************/
		FieldContent fieldContent= applicationSyllabus_FieldWorklist.get(0).getSyllabus_FieldWork().getFieldContent();
		document.add(HFourtitle("三、本课程的教学内容及要求"));
		if(fieldContent != null)
		{
			Paragraph largeText18 = new Paragraph();
			Chunk chunk42=new Chunk("3.1 ",timesNewRomeBold);
			Chunk chunk43=new Chunk("教学内容",heiFontBold);
				
			
			largeText18.add(chunk42);
			largeText18.add(chunk43);
			document.add(largeText18);
			
			//document.add(SFivetitle("3.1教学内容"));
			if(!fieldContent.getContent().equals(""))
				document.add(SFivetitle(fieldContent.getContent().replace("\r\n","\n")));
			else document.add(SFivetitle(""));
			
			Paragraph largeText17 = new Paragraph();
			Chunk chunk40=new Chunk("3.2 ",timesNewRomeBold);
			Chunk chunk41=new Chunk("教学要求",heiFontBold);
				
			
			largeText17.add(chunk40);
			largeText17.add(chunk41);
			document.add(largeText17);
			//document.add(SFivetitle("3.2教学要求"));
			if(!fieldContent.getRequest().equals(""))
				document.add(SFivetitle(fieldContent.getRequest().replace("\r\n","\n")));
			else document.add(SFivetitle(""));
			Paragraph largeText16 = new Paragraph();
			Chunk chunk38=new Chunk("3.3 ",timesNewRomeBold);
			Chunk chunk39=new Chunk("教学安排",heiFontBold);
				
			
			largeText16.add(chunk38);
			largeText16.add(chunk39);
			document.add(largeText16);
			//document.add(SFivetitle("3.3教学安排"));
			if(!fieldContent.getSchedule().equals(""))
				document.add(SFivetitle(fieldContent.getSchedule().replace("\r\n","\n")));
			else document.add(SFivetitle(""));
			 Paragraph largeText15 = new Paragraph();
				Chunk chunk36=new Chunk("3.4 ",timesNewRomeBold);
				Chunk chunk37=new Chunk("教学方式",heiFontBold);
					
				
				largeText15.add(chunk36);
				largeText15.add(chunk37);
				document.add(largeText15);
			//document.add(SFivetitle("3.4教学方式"));
			if(!fieldContent.getWay().equals(""))
				document.add(SFivetitle(fieldContent.getWay()));
			else document.add(SFivetitle(""));
			
		}
		else document.add(HFivetitle("没有填写本课程的教学内容及要求"));
		
		/******************四、学时分配	*************************/
		document.add(HFourtitle("四、学时分配"));
		if(fieldWork != null && !fieldWork.getDistribPer().equals(""))
			document.add(SFivetitle(fieldWork.getDistribPer()));
		else document.add(SFivetitle(""));

		/******************五、成绩考核与评定	*************************/
		document.add(HFourtitle("五、成绩考核与评定"));
		if(fieldWork != null && !fieldWork.getAssess().equals(""))
			document.add(SFivetitle(fieldWork.getAssess().replace("\r\n","\n")));
		else document.add(SFivetitle(""));
		
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		document.add(luokuan("                                             制定人："+theoreticalLesson3.getTeacher().getUsername()));
		document.add(luokuan("                                             审定人："));
		document.add(luokuan("                                             批准者："));
		document.add(luokuan("                                             年      月       日       "));
		document.close();
		outputStream.flush();
	}
	
	/********************
	 * 课程设计**************************/
	public void exportCourseDesign(String courseid, String syllabusid) throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);
         
		 Paragraph paraheader = new Paragraph();
	        
	        Font Font2 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);  
	        paraheader.setFont(new Font(Font2));

		    
	        HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        
	        header.setBorder(Rectangle.BOTTOM); 
	        header.setBorderColor(Color.red); 
	        header.setAlignment(1);
		    header.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header);  			
		Paragraph parafooter = new Paragraph();
			
			RtfFont Font1 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);
			parafooter.setFont(new Font(Font1));
			//parafooter.add(new Phrase("第"));
			parafooter.add(new RtfPageNumber());
			//parafooter.add(new Phrase("页  共"));
			//parafooter.add(new RtfTotalPageNumber());
			//parafooter.add(new Phrase("页"));
		  
		    RtfHeaderFooter footer = new RtfHeaderFooter(parafooter);
		    footer.setAlignment(Element.ALIGN_CENTER);
		    footer.setAlignment(1);
		    footer.setBorderColor(Color.red);  
            footer.setBorder(Rectangle.BOX);  
		    document.setFooter(footer);	 
		    PracticeLesson theoreticalLesson4=exportWordDao.findCourseDesign(courseid);//查出课程信息
	        String Titlename1 = theoreticalLesson4.getCurriculum().getCurriculumCname();
	      //  HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        HeaderFooter header4=new HeaderFooter(new Phrase(Titlename1+"课程教学大纲",Font2),false); 
	        
	        header4.setBorder(Rectangle.BOTTOM); 
	        header4.setBorderColor(Color.BLACK); 
	    
	        header4.setAlignment(1);
		    header4.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header4);  	
		    document.open();
		PracticeLesson theoreticalLesson=exportWordDao.findCourseDesign(courseid);//查出课程信息
		
		
		String Titlename = theoreticalLesson.getCurriculum().getCurriculumCname();
		String name=Titlename+"课程设计（学年论文）.doc";
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		document.add(Tabletitle(Titlename + "课程设计（学年论文）大纲"));
		/********************课程基本信息*************************/	
		
		float widths1[] = {12,40,12,25};
		Table table =table(4,3,widths1); 
		Font timesNewRome=new RtfFont("Times New Roman",11, Font.NORMAL,Color.BLACK);
		Font timesNewRomeBold=new RtfFont("Times New Roman",11, Font.BOLD,Color.BLACK);
		Font songFont = new RtfFont("宋体", 11, Font.NORMAL,Color.BLACK);
		Font heiFont = new RtfFont("黑 体", 11, Font.NORMAL,Color.BLACK);
		Font heiFontBold = new RtfFont("黑 体", 11, Font.BOLD,Color.BLACK);
		Font fontChinese = new RtfFont("黑 体",11,Font.NORMAL,Color.BLACK);
		table.setBorderWidthTop(0);
				
			table.setTop(0);
		 table.setPadding(0);    
		 table.setSpacing(0);  

		 table.setWidth(100);

		 table.setAlignment(Element.ALIGN_LEFT);//居中  
		 table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中 
		Cell cellOne = new Cell(new Phrase("英文名称：",fontChinese));

		Cell cellTwo = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumEname(),timesNewRome));

		Cell cellThree = new Cell(new Phrase("课程编码：",fontChinese));

		Cell cellFour = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumid(),timesNewRome));
		Cell cellFive = new Cell(new Phrase("学    时：",fontChinese));
		Cell cellSix = new Cell(new Phrase(theoreticalLesson.getCurriculum().getHoursOfALL(),timesNewRome));
		Cell cellSeven = new Cell(new Phrase("学    分：",fontChinese));
		Cell cellEight = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCredit(),timesNewRome));
		Cell cellNine = new Cell(new Phrase("课程性质：",fontChinese));
		Cell cellTen = new Cell(new Phrase(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename(),songFont));
		Cell cellEleven = new Cell(new Phrase("课程类别：",fontChinese));
		Cell cellTwelve = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCourseLei(),songFont));
		  cellOne.setBorderColor(Color.white); 
		  cellTwo.setBorderColor(Color.white); 
		  cellThree.setBorderColor(Color.white); 
		  cellFour.setBorderColor(Color.white); 
		  cellFive.setBorderColor(Color.WHITE); 
		  cellSix.setBorderColor(Color.WHITE); 
		  cellSeven.setBorderColor(Color.WHITE); 
		  cellEight.setBorderColor(Color.WHITE); 
		  cellNine.setBorderColor(Color.WHITE); 
		  cellTen.setBorderColor(Color.WHITE); 
		  cellEleven.setBorderColor(Color.WHITE); 
		  cellTwelve.setBorderColor(Color.WHITE); 
		 

		table.addCell(cellOne);
		table.addCell(cellTwo);
		table.addCell(cellThree);
		table.addCell(cellFour);
		table.addCell(cellFive);
		table.addCell(cellSix);
		table.addCell(cellSeven);
		table.addCell(cellEight);
		table.addCell(cellNine);
		table.addCell(cellTen);
		table.addCell(cellEleven);
		table.addCell(cellTwelve);

		 document.add(table);  
		 Paragraph largeText5 = new Paragraph();
		Chunk chunk15=new Chunk("开课学期：",heiFont);
		Chunk chunk0=new Chunk("第",songFont);
		Chunk chunk16=new Chunk(theoreticalLesson.getXueqi(),timesNewRome);
			
		Chunk chunk17=new Chunk("学期 ",songFont);
		largeText5.add(chunk15);
		largeText5.add(chunk0);
		largeText5.add(chunk16);
		largeText5.add(chunk17);
		document.add(largeText5);
		//document.add(settitle("英文名称："+theoreticalLesson.getCurriculum().getCurriculumEname()+"          课程编码："+theoreticalLesson.getCurriculum().getCurriculumid()));
		//document.add(settitle("学   时："+theoreticalLesson.getCurriculum().getHoursOfALL()+"          	   学分："+theoreticalLesson.getCurriculum().getCredit()));
		//document.add(settitle("课程性质："+theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename()+"          	   课程类别："+theoreticalLesson.getCurriculum().getCourseLei()));
		//document.add(settitle("开课学期："+theoreticalLesson.getXueqi()));
		
		/******************基本信息*************************/
		List<ApplicationSyllabus_CourseDesign> applicationSyllabus_CourseDesignlist = exportWordDao.findApplicationSyllabus_CourseDesig(syllabusid);
		BaseCourseDesign baseCourseDesign = applicationSyllabus_CourseDesignlist.get(0).getSyllabus_CourseDesign().getBaseCourseDesign();
		
		String departmentname = "";
		if(applicationSyllabus_CourseDesignlist.get(0).getProfessional() != null)
		{
			for(int ai=0;ai<applicationSyllabus_CourseDesignlist.size();ai++)
			{
				if(ai != applicationSyllabus_CourseDesignlist.size()-1)
					departmentname += applicationSyllabus_CourseDesignlist.get(ai).getProfessional().getProfessionalname()+"、";
				else departmentname += applicationSyllabus_CourseDesignlist.get(ai).getProfessional().getProfessionalname();
			}
		}
		else departmentname += applicationSyllabus_CourseDesignlist.get(0).getDepartment().getDepartmentCname();
		
		Paragraph largeText6 = new Paragraph();
		Chunk chunk18=new Chunk("适用专业：",heiFont);
		Chunk chunk19=new Chunk(departmentname,songFont);
		largeText6.add(chunk18);
		largeText6.add(chunk19);
		document.add(largeText6);
		//document.add(HFivetitle("适用专业："+departmentname));	
		
		
		/******************一、本课程的教学目标*************************/
		document.add(HFourtitle("一、本课程的教学目标"));
		List<TeachObj_CourseDesign> teachObjlist = exportWordDao.findTeachObj_CourseDesign(syllabusid);
		if(teachObjlist != null && teachObjlist.size() != 0)
		{
			for(int ti=0;ti<teachObjlist.size();ti++)
			{
				TeachObj_CourseDesign  teachObj= teachObjlist.get(ti);
				if(!teachObj.getTeachObjContent_CourseDesign().equals(""))
					document.add(SFivetitle(ti+1+"、"+teachObj.getTeachObjContent_CourseDesign()));
				else document.add(SFivetitle(""));
			}
		}
		else document.add(SFivetitle(""));

		
		/******************二、本课程的教学目标与毕业要求的对应关系*************************/
		document.add(HFourtitle("二、本课程的教学目标与毕业要求的对应关系"));
		List<IndexSelect_CourseDesign> indexselectlist = exportWordDao.findIndexSelect_CourseDesign(syllabusid);
		List<AbilityAndTeachObj_CourseDesign> abilityAndTeachObjlist = exportWordDao.findAbilityAndTeachObj_CourseDesign(syllabusid);
		
		List<List<IndexSelect_CourseDesign>> newindexselectlist = new ArrayList<List<IndexSelect_CourseDesign>>();
		List<List<AbilityAndTeachObj_CourseDesign>> newabilityAndTeachObjlist = new ArrayList<List<AbilityAndTeachObj_CourseDesign>>();
		if(indexselectlist != null && indexselectlist.size() != 0 && abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
		{

			while(indexselectlist != null && indexselectlist.size() != 0 )
			{
				List<IndexSelect_CourseDesign> StrIndex = new ArrayList<IndexSelect_CourseDesign>();
				IndexSelect_CourseDesign indexSelect = indexselectlist.get(0);
				for(int i=0;i<indexselectlist.size();i++)
				{
					if(indexSelect.getAbility().getAbilityid() == indexselectlist.get(i).getAbility().getAbilityid())
					{
						StrIndex.add(indexselectlist.get(i));
						indexselectlist.remove(i);
						i--;
					}
				}
				newindexselectlist.add(StrIndex);
			}
			
		
			while(abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
			{
				List<AbilityAndTeachObj_CourseDesign> StrAbandTea = new ArrayList<AbilityAndTeachObj_CourseDesign>();
				AbilityAndTeachObj_CourseDesign abilityAndTeachObj = abilityAndTeachObjlist.get(0);
				for(int i=0;i<abilityAndTeachObjlist.size();i++)
				{
					if(abilityAndTeachObj.getAbility() == abilityAndTeachObjlist.get(i).getAbility())
					{
						StrAbandTea.add(abilityAndTeachObjlist.get(i));
						abilityAndTeachObjlist.remove(i);
						i--;
					}
				}
				newabilityAndTeachObjlist.add(StrAbandTea);
			}
			document.add(GraRequest_CourseDesign(newindexselectlist,newabilityAndTeachObjlist,teachObjlist));
		}
		else document.add(SFivetitle(""));
		
		/******************三、本课程设计（论文）的内容及要求*************************/
		document.add(HFourtitle("三、本课程设计（论文）的内容及要求"));
		ConCourseDesign conCourseDesign =applicationSyllabus_CourseDesignlist.get(0).getSyllabus_CourseDesign().getConCourseDesign();
		
		 Paragraph largeText14 = new Paragraph();
			Chunk chunk34=new Chunk("3.1 ",timesNewRomeBold);
			Chunk chunk35=new Chunk("课程设计（论文）的基本要求",heiFontBold);
				
			
			largeText14.add(chunk34);
			largeText14.add(chunk35);
			document.add(largeText14);
		
		//document.add(SFivetitle("3.1 课程设计（论文）的基本内容"));
		if(conCourseDesign != null && !conCourseDesign.getContent().equals(""))
			document.add(SFivetitle(conCourseDesign.getContent().replace("\r\n","\n")));
		else document.add(SFivetitle("无"));
		
		 Paragraph largeText13 = new Paragraph();
			Chunk chunk32=new Chunk("3.2 ",timesNewRomeBold);
			Chunk chunk33=new Chunk("课程设计（论文）的基本要求",heiFontBold);
				
			
			largeText13.add(chunk32);
			largeText13.add(chunk33);
			document.add(largeText13);
		
		//document.add(SFivetitle("3.2 课程设计（论文）的基本要求"));
		if(conCourseDesign != null && !conCourseDesign.getRequest().equals(""))
			document.add(SFivetitle(conCourseDesign.getRequest().replace("\r\n","\n")));
		else document.add(SFivetitle("无"));
		
		/******************四、课程设计（论文）进度安排*************************/
		document.add(HFourtitle("四、课程设计（论文）进度安排"));
		if(baseCourseDesign != null && !baseCourseDesign.getSchedule().equals(""))
			document.add(SFivetitle(baseCourseDesign.getSchedule()));
		else document.add(SFivetitle("无"));
		List<DistributeHour_CourseDesign> distributeHour_Gralist = exportWordDao.findDistributeHour_CourseDesign(syllabusid);
		float[] widths={8,62,30};
		Table ss = table(3,distributeHour_Gralist.size(),widths);
		ss.addCell(TeachCellHead("设计内容",1,2,3));
		ss.addCell(TeachCellHead("时间",1,1,3));

		int i =1;
		for(int si=0;si<distributeHour_Gralist.size();si++)
		{
			DistributeHour_CourseDesign distributeHour_Gra = distributeHour_Gralist.get(si);
			ss.addCell(TeachCellContentChinese(String.valueOf(i++),1,1,3));// 序号
			ss.addCell(TeachCellContentChinese(distributeHour_Gra.getName(),1,1,3));//课程名称
			ss.addCell(TeachCellContentRoman(distributeHour_Gra.getTime(),3));//讲课学时
		}
//		ss.addCell(TeachCellContentChinese("合计",1,2,3));
//		ss.addCell(TeachCellContentRoman1("17周",1,3,3));
		document.add(ss);
		
		/******************五、成绩考核与评定*************************/
		document.add(HFourtitle("五、成绩考核与评定"));
		WayCourseDesign wayCourseDesign = applicationSyllabus_CourseDesignlist.get(0).getSyllabus_CourseDesign().getWayCourseDesign();
		 Paragraph largeText12 = new Paragraph();
			Chunk chunk30=new Chunk("5.1 ",timesNewRomeBold);
			Chunk chunk31=new Chunk("教学目标达成度评价",heiFontBold);
				
			
			largeText12.add(chunk30);
			largeText12.add(chunk31);
			document.add(largeText12);
		//document.add(SFivetitle("5.1教学目标达成度评价"));
		if(wayCourseDesign != null && !wayCourseDesign.getAim().equals(""))
			document.add(SFivetitle(wayCourseDesign.getAim().replace("\r\n","\n")));
		else document.add(SFivetitle("教学目标达成度评价"));
		
		 Paragraph largeText11 = new Paragraph();
			Chunk chunk28=new Chunk("5.2 ",timesNewRomeBold);
			Chunk chunk29=new Chunk("成绩考核与评定",heiFontBold);
				
			
			largeText11.add(chunk28);
			largeText11.add(chunk29);
			document.add(largeText11);
		
		//document.add(SFivetitle("5.2成绩考核与评定"));
		if(wayCourseDesign != null && !wayCourseDesign.getAssess().equals(""))
			document.add(SFivetitle(wayCourseDesign.getAssess().replace("\r\n","\n")));
		else document.add(SFivetitle("成绩考核与评定"));
		
		
		/******************六、设计（论文）参考资料*************************/
		document.add(HFourtitle("六、设计（论文）参考资料"));
		List<PracticeBooks_CourseDesign> bookInfolist = exportWordDao.findPracticeBooks_CourseDesign(syllabusid);
		if(bookInfolist != null && bookInfolist.size() != 0)
		{
			for(int bi=0;bi<bookInfolist.size();bi++)
			{
				PracticeBooks_CourseDesign bookInfo = bookInfolist.get(bi);
				if(bookInfo != null && !bookInfo.getName().equals(""))
				{
					document.add(SFivetitle(String.valueOf(bi+1)+"、"+bookInfo.getName()));
				}
				else document.add(SFivetitle("没有填写教材"));
			}
		}
		else document.add(SFourtitle("没有填写教材"));
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		document.add(luokuan("                                             制定人："+theoreticalLesson4.getTeacher().getUsername()));
		document.add(luokuan("                                             审定人："));
		document.add(luokuan("                                             批准者："));
		document.add(luokuan("                                             年      月       日       "));
		document.close();
		outputStream.flush();
	}
	
	/*********************课内实验
	 * @param syllabusid *******************/
	public void exportBaseExperiment(String expeid, String syllabusid) throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);		

		 Paragraph paraheader = new Paragraph();
	        
	        Font Font2 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);  
	        paraheader.setFont(new Font(Font2));

		    
	        HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        
	        header.setBorder(Rectangle.BOTTOM); 
	        header.setBorderColor(Color.red); 
	        header.setAlignment(1);
		    header.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header);  			
		Paragraph parafooter = new Paragraph();
			
			RtfFont Font1 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);
			parafooter.setFont(new Font(Font1));
			//parafooter.add(new Phrase("第"));
			parafooter.add(new RtfPageNumber());
			//parafooter.add(new Phrase("页  共"));
			//parafooter.add(new RtfTotalPageNumber());
			//parafooter.add(new Phrase("页"));
		  
		    RtfHeaderFooter footer = new RtfHeaderFooter(parafooter);
		    footer.setAlignment(Element.ALIGN_CENTER);
		    footer.setAlignment(1);
		    footer.setBorderColor(Color.red);  
		    footer.setBorder(Rectangle.BOX);  
		    document.setFooter(footer);	 
		
		    PracticeLesson theoreticalLesson5=exportWordDao.findCourseDesign(expeid);//查出课程信息
	        String Titlename1 = theoreticalLesson5.getCurriculum().getCurriculumCname();
	      //  HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        HeaderFooter header5=new HeaderFooter(new Phrase(Titlename1+"课程教学大纲",Font2),false); 
	        
	        header5.setBorder(Rectangle.BOTTOM); 
	        header5.setBorderColor(Color.BLACK); 
	    
	        header5.setAlignment(1);
		    header5.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header5);  	
		document.open();
		PracticeLesson theoreticalLesson=exportWordDao.findCourseDesign(expeid);//查出课程信息

		
		
		String Titlename = theoreticalLesson.getCurriculum().getCurriculumCname();
		String name=Titlename+"课程实验教学大纲.doc";
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		document.add(Tabletitle(Titlename + "课程实验教学大纲"));
		/********************课程基本信息*************************/	
		
		float widths1[] = {12,40,12,25};
		Table table =table(4,3,widths1); 
		Font timesNewRome=new RtfFont("Times New Roman",11, Font.NORMAL,Color.BLACK);
		Font timesNewRomeBold=new RtfFont("Times New Roman",11, Font.BOLD,Color.BLACK);
		Font songFont = new RtfFont("宋体", 11, Font.NORMAL,Color.BLACK);
		Font heiFont = new RtfFont("黑 体", 11, Font.NORMAL,Color.BLACK);
		Font heiFontBold = new RtfFont("黑 体", 11, Font.BOLD,Color.BLACK);
		Font fontChinese = new RtfFont("黑 体",11,Font.NORMAL,Color.BLACK);
		table.setBorderWidthTop(0);
				
			table.setTop(0);
		 table.setPadding(0);    
		 table.setSpacing(0);  

		 table.setWidth(100);

		 table.setAlignment(Element.ALIGN_LEFT);//居中  
		 table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中 
		Cell cellOne = new Cell(new Phrase("英文名称：",fontChinese));

		Cell cellTwo = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumEname(),timesNewRome));

		Cell cellThree = new Cell(new Phrase("课程编码：",fontChinese));

		Cell cellFour = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumid(),timesNewRome));
		Cell cellFive = new Cell(new Phrase("学    时：",fontChinese));
		Cell cellSix = new Cell(new Phrase(theoreticalLesson.getCurriculum().getHoursOfALL(),timesNewRome));
		Cell cellSeven = new Cell(new Phrase("学    分：",fontChinese));
		Cell cellEight = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCredit(),timesNewRome));
		Cell cellNine = new Cell(new Phrase("课程性质：",fontChinese));
		Cell cellTen = new Cell(new Phrase(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename(),songFont));
		Cell cellEleven = new Cell(new Phrase("课程类别：",fontChinese));
		Cell cellTwelve = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCourseLei(),songFont));
		  cellOne.setBorderColor(Color.white); 
		  cellTwo.setBorderColor(Color.white); 
		  cellThree.setBorderColor(Color.white); 
		  cellFour.setBorderColor(Color.white); 
		  cellFive.setBorderColor(Color.WHITE); 
		  cellSix.setBorderColor(Color.WHITE); 
		  cellSeven.setBorderColor(Color.WHITE); 
		  cellEight.setBorderColor(Color.WHITE); 
		  cellNine.setBorderColor(Color.WHITE); 
		  cellTen.setBorderColor(Color.WHITE); 
		  cellEleven.setBorderColor(Color.WHITE); 
		  cellTwelve.setBorderColor(Color.WHITE); 
		 

		table.addCell(cellOne);
		table.addCell(cellTwo);
		table.addCell(cellThree);
		table.addCell(cellFour);
		table.addCell(cellFive);
		table.addCell(cellSix);
		table.addCell(cellSeven);
		table.addCell(cellEight);
		table.addCell(cellNine);
		table.addCell(cellTen);
		table.addCell(cellEleven);
		table.addCell(cellTwelve);

		 document.add(table);  
		 Paragraph largeText5 = new Paragraph();
		Chunk chunk15=new Chunk("开课学期：",heiFont);
		Chunk chunk0=new Chunk("第",songFont);
		Chunk chunk16=new Chunk(theoreticalLesson.getXueqi(),timesNewRome);
			
		Chunk chunk17=new Chunk("学期 ",songFont);
		largeText5.add(chunk15);
		largeText5.add(chunk0);
		largeText5.add(chunk16);
		largeText5.add(chunk17);
		document.add(largeText5);
		
		
		
		/*document.add(settitle("英文名称："+theoreticalLesson.getCurriculum().getCurriculumEname()+"          课程编码："+theoreticalLesson.getCurriculum().getCurriculumid()));
		document.add(settitle("学   时："+theoreticalLesson.getCurriculum().getHoursOfALL()+"          	   学分："+theoreticalLesson.getCurriculum().getCredit()));
		document.add(settitle("课程性质："+theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename()+"          	   课程类别："+theoreticalLesson.getCurriculum().getCourseLei()));
		document.add(settitle("开课学期："+theoreticalLesson.getXueqi()));
		*/
		
		/**********************基本信息************************/
		List<ApplicationSyllabus_InnerExperiment> applicationSyllabus_InnerExperimentlsit = exportWordDao.findApplicationSyllabus_InnerExperiment(syllabusid);
		BaseExperiment baseExperiment = applicationSyllabus_InnerExperimentlsit.get(0).getSyllabus_InnerExperiment().getBaseExperiment();

		
		
		String departmentname = "";
		if(applicationSyllabus_InnerExperimentlsit.get(0).getProfessional() != null)
		{
			for(int ai=0;ai<applicationSyllabus_InnerExperimentlsit.size();ai++)
			{
				if(ai != applicationSyllabus_InnerExperimentlsit.size()-1)
					departmentname += applicationSyllabus_InnerExperimentlsit.get(ai).getProfessional().getProfessionalname()+"、";
				else departmentname += applicationSyllabus_InnerExperimentlsit.get(ai).getProfessional().getProfessionalname();
			}
		}
		else departmentname += applicationSyllabus_InnerExperimentlsit.get(0).getDepartment().getDepartmentCname();
		Paragraph largeText6 = new Paragraph();
		Chunk chunk18=new Chunk("适用专业：",heiFont);
		Chunk chunk19=new Chunk(departmentname,songFont);
		largeText6.add(chunk18);
		largeText6.add(chunk19);
		document.add(largeText6);
		//document.add(HFivetitle("适用专业："+departmentname));	
		
		
		/**********************一、本实验的教学目标************************/
		document.add(HFourtitle("一、本实验的教学目标"));
		List<TeachObj_InnerExperiment> teachObjlist = exportWordDao.findTeachObj_InnerExperiment(syllabusid);
		if(teachObjlist != null && teachObjlist.size() != 0)
		{
			for(int ti=0;ti<teachObjlist.size();ti++)
			{
				TeachObj_InnerExperiment  teachObj= teachObjlist.get(ti);
				if(!teachObj.getTeachObjContent_InnerExperiment().equals(""))
					document.add(SFivetitle(ti+1+"、"+teachObj.getTeachObjContent_InnerExperiment()));
				else document.add(SFivetitle(""));
			}
		}
		else document.add(SFivetitle(""));

		
		/**********************二、本实验的教学目标与毕业要求的对应关系************************/
		document.add(HFourtitle("二、本实验的教学目标与毕业要求的对应关系"));
		List<IndexSelect_InnerExperiment> indexselectlist = exportWordDao.findIndexSelect_InnerExperiment(syllabusid);
		List<AbilityAndTeachObj_InnerExperiment> abilityAndTeachObjlist = exportWordDao.findAbilityAndTeachObj_InnerExperiment(syllabusid);
		
		List<List<IndexSelect_InnerExperiment>> newindexselectlist = new ArrayList<List<IndexSelect_InnerExperiment>>();
		List<List<AbilityAndTeachObj_InnerExperiment>> newabilityAndTeachObjlist = new ArrayList<List<AbilityAndTeachObj_InnerExperiment>>();
		if(indexselectlist != null && indexselectlist.size() != 0 && abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
		{

			while(indexselectlist != null && indexselectlist.size() != 0 )
			{
				List<IndexSelect_InnerExperiment> StrIndex = new ArrayList<IndexSelect_InnerExperiment>();
				IndexSelect_InnerExperiment indexSelect = indexselectlist.get(0);
				for(int i=0;i<indexselectlist.size();i++)
				{
					if(indexSelect.getAbility().getAbilityid() == indexselectlist.get(i).getAbility().getAbilityid())
					{
						StrIndex.add(indexselectlist.get(i));
						indexselectlist.remove(i);
						i--;
					}
				}
				newindexselectlist.add(StrIndex);
			}
			
		
			while(abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
			{
				List<AbilityAndTeachObj_InnerExperiment> StrAbandTea = new ArrayList<AbilityAndTeachObj_InnerExperiment>();
				AbilityAndTeachObj_InnerExperiment abilityAndTeachObj = abilityAndTeachObjlist.get(0);
				for(int i=0;i<abilityAndTeachObjlist.size();i++)
				{
					if(abilityAndTeachObj.getAbility() == abilityAndTeachObjlist.get(i).getAbility())
					{
						StrAbandTea.add(abilityAndTeachObjlist.get(i));
						abilityAndTeachObjlist.remove(i);
						i--;
					}
				}
				newabilityAndTeachObjlist.add(StrAbandTea);
			}
			document.add(GraRequest_InnerExperiment(newindexselectlist,newabilityAndTeachObjlist,teachObjlist));
		}
		else document.add(SFivetitle(""));
		
		
		
		/**********************三、本实验的基本内容************************/
		document.add(HFourtitle("三、本实验的基本内容"));
		 Paragraph largeText10 = new Paragraph();
			Chunk chunk26=new Chunk("3.1 ",timesNewRomeBold);
			Chunk chunk27=new Chunk("实验项目及学时分配表",heiFontBold);
				
			
			largeText10.add(chunk26);
			largeText10.add(chunk27);
			document.add(largeText10);
		//document.add(SFivetitle("3.1  实验项目及学时分配表"));

		document.add(SFivetitle(baseExperiment.getSchedule()));
	
		List<DistributeHour_InnerExperiment> distributeHour_InnerExperimentlist = exportWordDao.findDistributeHour_InnerExperiment(syllabusid);
		float[] widths={8,52,10,10,20};
		Table ss = table(5,distributeHour_InnerExperimentlist.size()+1,widths);
		ss.addCell(TeachCellHead("项目名称",2,2,3));
		ss.addCell(TeachCellHead("学时",1,2,3));
		ss.addCell(TeachCellHead("实验类型",2,1,3));
		ss.addCell(TeachCellHead("实验（践）",1,1,3));
		ss.addCell(TeachCellHead("上机",1,1,3));
		int i =1,totalhour=0;
		for(int si=0;si<distributeHour_InnerExperimentlist.size();si++)
		{
			DistributeHour_InnerExperiment distributeHour_InnerExperiment = distributeHour_InnerExperimentlist.get(si);
			ss.addCell(TeachCellContentChinese(String.valueOf(i++),1,1,3));// 序号
			ss.addCell(TeachCellContentChinese(distributeHour_InnerExperiment.getName(),1,1,3));//课程名称
			ss.addCell(TeachCellContentRoman(distributeHour_InnerExperiment.getHoursOfClass(),3));//讲课学时
			ss.addCell(TeachCellContentRoman(distributeHour_InnerExperiment.getHoursOfExp(),3));//实验学时
			ss.addCell(TeachCellContentRoman(distributeHour_InnerExperiment.getExp(),3));//上机学时
			if (distributeHour_InnerExperiment.getHoursOfClass().equals("")) {
				distributeHour_InnerExperiment.setHoursOfClass("0");
			}
			if (distributeHour_InnerExperiment.getHoursOfExp().equals("")) {
				distributeHour_InnerExperiment.setHoursOfExp("0");
			}
			if (distributeHour_InnerExperiment.getExp().equals("")) {
				distributeHour_InnerExperiment.setExp("0");
			}
			totalhour += Integer.valueOf(distributeHour_InnerExperiment.getHoursOfClass())+Integer.valueOf(distributeHour_InnerExperiment.getHoursOfExp());
		}
		ss.addCell(TeachCellContentChinese("合计",1,2,3));
		ss.addCell(TeachCellContentRoman1(String.valueOf(totalhour),1,3,3));
		totalhour=0;
		document.add(ss);
		 Paragraph largeText9 = new Paragraph();
			Chunk chunk24=new Chunk("3.2 ",timesNewRomeBold);
			Chunk chunk25=new Chunk("实验内容及要求",heiFontBold);
				
			largeText9.add(chunk24);
			largeText9.add(chunk25);
			document.add(largeText9);
		//document.add(SFivetitle("3.2 实验内容及要求"));
		
		List<ExpermentProject> otherContentlist = exportWordDao.findExpermentProject(syllabusid);//课内实验内容
		if(otherContentlist != null && otherContentlist.size() != 0)
		{
			for(int oi=0;oi<otherContentlist.size();oi++)
			{
				ExpermentProject otherContent = otherContentlist.get(oi);
				if(otherContent != null)
				{
					document.add(SFiveBtitle(String.valueOf(oi+1)+otherContent.getName()+"  (支撑教学目标"+otherContent.getNum()+")"));
					document.add(SFiveBtitle("实验设备及材料:"));
					document.add(SFivetitle(otherContent.getEquipment().replace("\r\n","\n")));
					document.add(SFiveBtitle("实验原理:"));
					document.add(SFivetitle(otherContent.getTheory().replace("\r\n","\n")));
					document.add(SFiveBtitle("教学要求:"));
					document.add(SFivetitle(otherContent.getRequest().replace("\r\n","\n")));
				}
				else document.add(SFourtitle(("没有填写"+String.valueOf(oi+1)+"、"+"实验课程内容")));
			}
		}
		else document.add(SFourtitle(""));
		
		
		/*********************五、成绩考核与评定************************/
		document.add(HFourtitle("四、成绩考核与评定"));

		if(baseExperiment != null && !baseExperiment.getAssess().equals(""))
			document.add(SFivetitle(baseExperiment.getAssess().replace("\r\n","\n")));
		else document.add(SFourtitle("没有填写教学安排"));
		
		/*********************五、参考资料************************/
		document.add(HFourtitle("五、参考资料"));
		List<PracticeBooks_InnerExperiment> bookInfolist = exportWordDao.findPracticeBooks_InnerExperiment(syllabusid);
		if(bookInfolist != null && bookInfolist.size() != 0)
		{
			for(int bi=0;bi<bookInfolist.size();bi++)
			{
				PracticeBooks_InnerExperiment bookInfo = bookInfolist.get(bi);
				if(bookInfo != null && !bookInfo.getName().equals(""))
				{
					document.add(SFivetitle(String.valueOf(bi+1)+"、"+bookInfo.getName()));
				}
				else document.add(SFivetitle("没有填写教材"));
			}
		}
		else document.add(SFourtitle("没有填写教材"));
		
		
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		if(theoreticalLesson5.getTeacher() == null){
			document.add(luokuan("                                             制定人："+theoreticalLesson5.getExperimenter().getUsername()));
		}else {
			document.add(luokuan("                                             制定人："+theoreticalLesson5.getTeacher().getUsername()));
		}
		document.add(luokuan("                                             审定人："));
		document.add(luokuan("                                             批准者："));
		document.add(luokuan("                                             年      月       日       "));
		document.close();
		outputStream.flush();
	}
	
	
	/********************理论课课内实验**************************/
	public void exportTheoInnerExperiment(String theoexpeid, String syllabusid) throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);
		 Paragraph paraheader = new Paragraph();
	        
	        Font Font2 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);  
	        paraheader.setFont(new Font(Font2));

		    
	        HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        
	        header.setBorder(Rectangle.BOTTOM); 
	        header.setBorderColor(Color.red); 
	        header.setAlignment(1);
		    header.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header);  			
		 
		 Paragraph parafooter = new Paragraph();
			
			RtfFont Font1 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);
			parafooter.setFont(new Font(Font1));
			//parafooter.add(new Phrase("第"));
			parafooter.add(new RtfPageNumber());
			//parafooter.add(new Phrase("页  共"));
			//parafooter.add(new RtfTotalPageNumber());
			//parafooter.add(new Phrase("页"));
		  
		    RtfHeaderFooter footer = new RtfHeaderFooter(parafooter);
		    footer.setAlignment(Element.ALIGN_CENTER);
		    footer.setAlignment(1);
		    footer.setBorderColor(Color.red);  
		    footer.setBorder(Rectangle.BOX);  
		    document.setFooter(footer);	 
			TheoreticalLesson theoreticalLesson6=exportWordDao.findTheo(theoexpeid);//查出课程信息
	        String Titlename1 = theoreticalLesson6.getCurriculum().getCurriculumCname();
	      //  HeaderFooter header=new HeaderFooter(new Phrase("教学大纲",Font2),false); 
	        HeaderFooter header6=new HeaderFooter(new Phrase(Titlename1+"课程教学大纲",Font2),false); 
	        
	        header6.setBorder(Rectangle.BOTTOM); 
	        header6.setBorderColor(Color.BLACK); 
	    
	        header6.setAlignment(1);
		    header6.setAlignment(Element.ALIGN_CENTER);
	        document.setHeader(header6);  	
	document.open();
		TheoreticalLesson theoreticalLesson=exportWordDao.findTheo(theoexpeid);//查出课程信息

		
		
		String Titlename = theoreticalLesson.getCurriculum().getCurriculumCname();
		String name=Titlename+"课程实验教学大纲.doc";
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		document.add(Tabletitle(Titlename + "课程实验教学大纲"));
		/********************课程基本信息*************************/	
		float widths1[] = {12,40,12,25};
		Table table =table(4,3,widths1); 
		Font timesNewRome=new RtfFont("Times New Roman",11, Font.NORMAL,Color.BLACK);
		Font timesNewRomeBold=new RtfFont("Times New Roman",11, Font.BOLD,Color.BLACK);
		Font songFont = new RtfFont("宋体", 11, Font.NORMAL,Color.BLACK);
		Font heiFont = new RtfFont("黑 体", 11, Font.NORMAL,Color.BLACK);
		Font heiFontBold = new RtfFont("黑 体", 11, Font.BOLD,Color.BLACK);
		Font fontChinese = new RtfFont("黑 体",11,Font.NORMAL,Color.BLACK);
		table.setBorderWidthTop(0);
				
			table.setTop(0);
		 table.setPadding(0);    
		 table.setSpacing(0);  

		 table.setWidth(100);

		 table.setAlignment(Element.ALIGN_LEFT);//居中  
		 table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中 
		Cell cellOne = new Cell(new Phrase("英文名称：",fontChinese));

		Cell cellTwo = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumEname(),timesNewRome));

		Cell cellThree = new Cell(new Phrase("课程编码：",fontChinese));

		Cell cellFour = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCurriculumid(),timesNewRome));
		Cell cellFive = new Cell(new Phrase("学    时：",fontChinese));
		Cell cellSix = new Cell(new Phrase(theoreticalLesson.getCurriculum().getHoursOfALL(),timesNewRome));
		Cell cellSeven = new Cell(new Phrase("学    分：",fontChinese));
		Cell cellEight = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCredit(),timesNewRome));
		Cell cellNine = new Cell(new Phrase("课程性质：",fontChinese));
		Cell cellTen = new Cell(new Phrase(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename(),songFont));
		Cell cellEleven = new Cell(new Phrase("课程类别：",fontChinese));
		Cell cellTwelve = new Cell(new Phrase(theoreticalLesson.getCurriculum().getCourseLei(),songFont));
		  cellOne.setBorderColor(Color.white); 
		  cellTwo.setBorderColor(Color.white); 
		  cellThree.setBorderColor(Color.white); 
		  cellFour.setBorderColor(Color.white); 
		  cellFive.setBorderColor(Color.WHITE); 
		  cellSix.setBorderColor(Color.WHITE); 
		  cellSeven.setBorderColor(Color.WHITE); 
		  cellEight.setBorderColor(Color.WHITE); 
		  cellNine.setBorderColor(Color.WHITE); 
		  cellTen.setBorderColor(Color.WHITE); 
		  cellEleven.setBorderColor(Color.WHITE); 
		  cellTwelve.setBorderColor(Color.WHITE); 
		 

		table.addCell(cellOne);
		table.addCell(cellTwo);
		table.addCell(cellThree);
		table.addCell(cellFour);
		table.addCell(cellFive);
		table.addCell(cellSix);
		table.addCell(cellSeven);
		table.addCell(cellEight);
		table.addCell(cellNine);
		table.addCell(cellTen);
		table.addCell(cellEleven);
		table.addCell(cellTwelve);

		 document.add(table);  
		 Paragraph largeText5 = new Paragraph();
		Chunk chunk15=new Chunk("开课学期：",heiFont);
		Chunk chunk0=new Chunk("第",songFont);
		Chunk chunk16=new Chunk(theoreticalLesson.getXueqi(),timesNewRome);
			
		Chunk chunk17=new Chunk("学期 ",songFont);
		largeText5.add(chunk15);
		largeText5.add(chunk0);
		largeText5.add(chunk16);
		largeText5.add(chunk17);
		document.add(largeText5);
		
		
		/*document.add(settitle("英文名称："+theoreticalLesson.getCurriculum().getCurriculumEname()+"          课程编码："+theoreticalLesson.getCurriculum().getCurriculumid()));
		document.add(settitle("学   时："+theoreticalLesson.getCurriculum().getHoursOfALL()+"          	   学分："+theoreticalLesson.getCurriculum().getCredit()));
		document.add(settitle("课程性质："+theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename()+"          	   课程类别："+theoreticalLesson.getCurriculum().getCourseLei()));
		document.add(settitle("开课学期："+theoreticalLesson.getXueqi()));*/
		
		
		/**********************基本信息************************/
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabus_InnerExperimentlsit = exportWordDao.findApplicationSyllabus_TheoInnerExperiment(syllabusid);
		
		BaseExperiment_TheoInnerExperiment baseExperiment_TheoInnerExperiment = applicationSyllabus_InnerExperimentlsit.get(0).getSyllabus_TheoInnerExperiment().getBaseExperiment_TheoInnerExperiment();
		
				String departmentname = "";
		if(applicationSyllabus_InnerExperimentlsit.get(0).getProfessional() != null)
		{
			for(int ai=0;ai<applicationSyllabus_InnerExperimentlsit.size();ai++)
			{
				if(ai != applicationSyllabus_InnerExperimentlsit.size()-1)
					departmentname += applicationSyllabus_InnerExperimentlsit.get(ai).getProfessional().getProfessionalname()+"、";
				else departmentname += applicationSyllabus_InnerExperimentlsit.get(ai).getProfessional().getProfessionalname();
			}
		}
		else departmentname += applicationSyllabus_InnerExperimentlsit.get(0).getDepartment().getDepartmentCname();
		
		Paragraph largeText6 = new Paragraph();
		Chunk chunk18=new Chunk("适用专业：",heiFont);
		Chunk chunk19=new Chunk(departmentname,songFont);
		largeText6.add(chunk18);
		largeText6.add(chunk19);
		document.add(largeText6);
		
		//document.add(HFivetitle("适用专业："+departmentname));	
		
		
		/**********************一、本实验的教学目标************************/
		document.add(HFourtitle("一、本实验的教学目标"));
		List<TeachObj_TheoInnerExperiment> teachObjlist = exportWordDao.findTeachObj_TheoInnerExperiment(syllabusid);

		if(teachObjlist != null && teachObjlist.size() != 0)
		{
			for(int ti=0;ti<teachObjlist.size();ti++)
			{
				TeachObj_TheoInnerExperiment  teachObj= teachObjlist.get(ti);
				if(!teachObj.getTeachObjContent_TheoInnerExperiment().equals(""))
					document.add(SFivetitle(ti+1+"、"+teachObj.getTeachObjContent_TheoInnerExperiment()));
				else document.add(SFivetitle(""));
			}
		}
		else document.add(SFivetitle(""));

		
		/**********************二、本实验的教学目标与毕业要求的对应关系************************/
		document.add(HFourtitle("二、本实验的教学目标与毕业要求的对应关系"));
		List<IndexSelect_TheoInnerExperiment> indexselectlist = exportWordDao.findIndexSelect_TheoInnerExperiment(syllabusid);
		List<AbilityAndTeachObj_TheoInnerExperiment> abilityAndTeachObjlist = exportWordDao.findAbilityAndTeachObj_TheoInnerExperiment(syllabusid);
		
		List<List<IndexSelect_TheoInnerExperiment>> newindexselectlist = new ArrayList<List<IndexSelect_TheoInnerExperiment>>();
		List<List<AbilityAndTeachObj_TheoInnerExperiment>> newabilityAndTeachObjlist = new ArrayList<List<AbilityAndTeachObj_TheoInnerExperiment>>();
		if(indexselectlist != null && indexselectlist.size() != 0 && abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
		{

			while(indexselectlist != null && indexselectlist.size() != 0 )
			{
				List<IndexSelect_TheoInnerExperiment> StrIndex = new ArrayList<IndexSelect_TheoInnerExperiment>();
				IndexSelect_TheoInnerExperiment indexSelect = indexselectlist.get(0);
				for(int i=0;i<indexselectlist.size();i++)
				{
					if(indexSelect.getAbility().getAbilityid() == indexselectlist.get(i).getAbility().getAbilityid())
					{
						StrIndex.add(indexselectlist.get(i));
						indexselectlist.remove(i);
						i--;
					}
				}
				newindexselectlist.add(StrIndex);
			}
			
		
			while(abilityAndTeachObjlist != null && abilityAndTeachObjlist.size() != 0)
			{
				List<AbilityAndTeachObj_TheoInnerExperiment> StrAbandTea = new ArrayList<AbilityAndTeachObj_TheoInnerExperiment>();
				AbilityAndTeachObj_TheoInnerExperiment abilityAndTeachObj = abilityAndTeachObjlist.get(0);
				for(int i=0;i<abilityAndTeachObjlist.size();i++)
				{
					if(abilityAndTeachObj.getAbility() == abilityAndTeachObjlist.get(i).getAbility())
					{
						StrAbandTea.add(abilityAndTeachObjlist.get(i));
						abilityAndTeachObjlist.remove(i);
						i--;
					}
				}
				newabilityAndTeachObjlist.add(StrAbandTea);
			}
			document.add(GraRequest_TheoInnerExperiment(newindexselectlist,newabilityAndTeachObjlist,teachObjlist));
		}
		else document.add(SFivetitle(""));
		
		
		
		/**********************三、本实验的基本内容************************/
		
		document.add(HFourtitle("三、本实验的基本内容"));
		 Paragraph largeText7 = new Paragraph();
			Chunk chunk20=new Chunk("3.1 ",timesNewRomeBold);
			Chunk chunk21=new Chunk("实验项目及学时分配表",heiFontBold);
				
			
			largeText5.add(chunk20);
			largeText5.add(chunk21);
			document.add(largeText7);
		//document.add(SFivetitle("3.1  实验项目及学时分配表"));
		//document.add(SFivetitle(baseExperiment_TheoInnerExperiment.getSchedule()));

		List<DistributeHour_TheoInnerExperiment> distributeHour_TheoInnerExperimentlist = exportWordDao.findDistributeHour_TheoInnerExperiment(syllabusid);
		float[] widths={8,52,10,10,20};
		Table ss = table(5,distributeHour_TheoInnerExperimentlist.size()+1,widths);
		ss.addCell(TeachCellHead("项目名称",2,2,3));
		ss.addCell(TeachCellHead("学时",1,2,3));
		ss.addCell(TeachCellHead("实验类型",2,1,3));
		ss.addCell(TeachCellHead("实验（践）",1,1,3));
		ss.addCell(TeachCellHead("上机",1,1,3));
		int i =1,totalhour=0;
		for(int si=0;si<distributeHour_TheoInnerExperimentlist.size();si++)
		{
			DistributeHour_TheoInnerExperiment distributeHour_InnerExperiment = distributeHour_TheoInnerExperimentlist.get(si);
			ss.addCell(TeachCellContentChinese(String.valueOf(i++),1,1,3));// 序号
			ss.addCell(TeachCellContentChinese(distributeHour_InnerExperiment.getName(),1,1,3));//课程名称
			ss.addCell(TeachCellContentRoman(distributeHour_InnerExperiment.getHoursOfClass(),3));//讲课学时
			ss.addCell(TeachCellContentRoman(distributeHour_InnerExperiment.getHoursOfExp(),3));//实验学时
			ss.addCell(TeachCellContentRoman(distributeHour_InnerExperiment.getExp(),3));//上机学时
			if(distributeHour_InnerExperiment.getHoursOfExp() == null || distributeHour_InnerExperiment.getHoursOfExp().equals("")){
				totalhour += Integer.valueOf(distributeHour_InnerExperiment.getHoursOfClass());
			}else if(distributeHour_InnerExperiment.getHoursOfClass() == null ||distributeHour_InnerExperiment.getHoursOfClass().equals("")){
				totalhour += Integer.valueOf(Integer.valueOf(distributeHour_InnerExperiment.getHoursOfExp()));
			}else{
				totalhour += Integer.valueOf(distributeHour_InnerExperiment.getHoursOfClass())+Integer.valueOf(distributeHour_InnerExperiment.getHoursOfExp());
			}

		//	totalhour += Integer.valueOf(distributeHour_InnerExperiment.getHoursOfClass())+Integer.valueOf(distributeHour_InnerExperiment.getHoursOfExp());
		}
		ss.addCell(TeachCellContentChinese("合计",1,2,3));
		ss.addCell(TeachCellContentRoman1(String.valueOf(totalhour),1,3,3));
		totalhour=0;
		document.add(ss);
		 Paragraph largeText8 = new Paragraph();
			Chunk chunk22=new Chunk("3.2 ",timesNewRomeBold);
			Chunk chunk23=new Chunk("实验内容及要求",heiFontBold);
				
			
			largeText8.add(chunk22);
			largeText8.add(chunk23);
			document.add(largeText8);
	//	document.add(SFivetitle("3.2 实验内容及要求"));
		
		List<ExpermentProject_Theo> otherContentlist = exportWordDao.findExpermentProject_Theo(syllabusid);//课内实验内容
		if(otherContentlist != null && otherContentlist.size() != 0)
		{
			for(int oi=0;oi<otherContentlist.size();oi++)
			{
				ExpermentProject_Theo otherContent = otherContentlist.get(oi);
				if(otherContent != null)
				{
					document.add(SFiveBtitle(String.valueOf(oi+1)+otherContent.getName()+"  (支撑教学目标"+otherContent.getNum()+")"));
					document.add(SFiveBtitle("实验设备及材料:"));
					if(otherContent.getEquipment() != null){
						document.add(SFivetitle(otherContent.getEquipment().replace("\r\n","\n")));
					}
					document.add(SFiveBtitle("实验原理:"));
					if(otherContent.getTheory() != null){
						document.add(SFivetitle(otherContent.getTheory().replace("\r\n","\n")));
					}
					document.add(SFiveBtitle("教学要求:"));
					if(otherContent.getRequest() != null){
						document.add(SFivetitle(otherContent.getRequest().replace("\r\n","\n")));
					}
				}
				else document.add(SFourtitle(("没有填写"+String.valueOf(oi+1)+"、"+"实验课程内容")));
			}
		}
		else document.add(SFourtitle(""));
		
		/*********************四、成绩考核与评定************************/
		if(baseExperiment_TheoInnerExperiment != null && !baseExperiment_TheoInnerExperiment.getAssess().equals(""))
			document.add(SFivetitle(baseExperiment_TheoInnerExperiment.getAssess()));
		else document.add(SFourtitle("没有填写教学安排"));
		
		/*********************五、参考资料************************/
		document.add(HFourtitle("五、参考资料"));
		List<TextBooks_InnerExperiment> bookInfolist = exportWordDao.findPracticeTextBooks_InnerExperiment(syllabusid);
		if(bookInfolist != null && bookInfolist.size() != 0)
		{
			for(int bi=0;bi<bookInfolist.size();bi++)
			{
				TextBooks_InnerExperiment bookInfo = bookInfolist.get(bi);
				if(bookInfo != null && !bookInfo.getName().equals(""))
				{
					document.add(SFivetitle(String.valueOf(bi+1)+"、"+bookInfo.getName()));
				}
				else document.add(SFivetitle("没有填写教材"));
			}
		}
		else document.add(SFourtitle("没有填写教材"));
		
		
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		document.add(SFourtitle(" "));
		if(theoreticalLesson6.getCteacher() == null){
			document.add(luokuan("                                             制定人："+theoreticalLesson6.getExperimenter().getUsername()));
		}else {
			document.add(luokuan("                                             制定人："+theoreticalLesson6.getCteacher().getUsername()));
		}
		document.add(luokuan("                                             审定人："));
		document.add(luokuan("                                             批准者："));
		document.add(luokuan("                                             年      月       日       "));
		document.close();
		outputStream.flush();
		
	}

}

