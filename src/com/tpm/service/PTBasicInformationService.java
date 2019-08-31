package com.tpm.service;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.AbilityDao;
import com.tpm.dao.ApplicationMainTainOfPTDao;
import com.tpm.dao.ApplicationTrainingEventsDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.PTBasicInformationDao;
import com.tpm.entity.Ability;
import com.tpm.entity.Department;
import com.tpm.entity.PTBasicInformation;


import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.field.RtfPageNumber;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;
import com.lowagie.text.rtf.style.RtfFont;
import com.opensymphony.xwork2.ActionContext;
import com.tpm.dao.PTBasicInformationDao;
import com.tpm.entity.ApplicationMainTainOfPT;
import com.tpm.entity.ApplicationTrainingEvents;
import com.tpm.entity.College;
import com.tpm.entity.MainTainOfPTTag;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.ScoreThreshold;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.Topology;
import com.tpm.entity.TrainingAnother;
import com.tpm.entity.TrainingEvents;
import com.tpm.entity.MainTainOfPT;
import com.tpm.entity.MainTainOfPT;//总体安排
import com.tpm.entity.TrainingEvents;//培养事件

import java.lang.Math;
@Transactional
public class PTBasicInformationService {
	private PTBasicInformationDao ptBasicInformationDao;
	private DepartmentDao departmentDao;
	private AbilityDao abilityDao;
	private ApplicationMainTainOfPTDao applicationMainTainOfPTDao;
	private ApplicationTrainingEventsDao applicationTrainingEventsDao;
	
	public void setApplicationTrainingEventsDao(
			ApplicationTrainingEventsDao applicationTrainingEventsDao) {
		this.applicationTrainingEventsDao = applicationTrainingEventsDao;
	}
	public void setAbilityDao(AbilityDao abilityDao) {
		this.abilityDao = abilityDao;
	}
	public void setApplicationMainTainOfPTDao(
			ApplicationMainTainOfPTDao applicationMainTainOfPTDao) {
		this.applicationMainTainOfPTDao = applicationMainTainOfPTDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void setPtBasicInformationDao(PTBasicInformationDao ptBasicInformationDao) {
		this.ptBasicInformationDao = ptBasicInformationDao;
	}
	public void bjpy(PTBasicInformation ptBasicInformation) {
		PTBasicInformation newptBasicInformation = ptBasicInformationDao.getbydepartment(ptBasicInformation.getDepartment());
		Department department = departmentDao.get(ptBasicInformation.getDepartment().getDepartmentid());
		List<Ability> abilitylist = abilityDao.getbydepartment(ptBasicInformation.getDepartment());
		ServletActionContext.getRequest().setAttribute("abilitylist", abilitylist);
		ServletActionContext.getRequest().setAttribute("ptBasicInformation", newptBasicInformation);
		ServletActionContext.getRequest().setAttribute("department", department);
	}

	public void updateptBasicInformation(PTBasicInformation ptBasicInformation) {
		if(ptBasicInformation.getPtBasicInformationid() != null && !"".equals(ptBasicInformation.getPtBasicInformationid())){
			ptBasicInformationDao.update(ptBasicInformation);
			List<Ability> abilitylist = abilityDao.getbydepartment(ptBasicInformation.getDepartment());
			if(abilitylist.size() != ptBasicInformation.getAbilityCount()){
				if(abilitylist.size() < ptBasicInformation.getAbilityCount()){
					for(int i=abilitylist.size();i<ptBasicInformation.getAbilityCount();i++){
						Ability ability = new Ability();
						ability.setDepartment(ptBasicInformation.getDepartment());
						abilityDao.add(ability);
					}
				}
				if(abilitylist.size() > ptBasicInformation.getAbilityCount()){
					for(int i=ptBasicInformation.getAbilityCount();i<abilitylist.size();i++){
						Ability ability = abilitylist.get(i);
						abilityDao.delete(ability);
					}
				}
			}
		}else{
			ptBasicInformationDao.add(ptBasicInformation);
			for(int i=0;i<ptBasicInformation.getAbilityCount();i++){
				Ability ability = new Ability();
				ability.setDepartment(ptBasicInformation.getDepartment());
				abilityDao.add(ability);
			}
		}
		List<Ability> newabilitylist = abilityDao.getbydepartment(ptBasicInformation.getDepartment());
		Department department = departmentDao.get(ptBasicInformation.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("abilitylist", newabilitylist);
		ServletActionContext.getRequest().setAttribute("department", department);
	}
	
	
	
	
	
	

		String Strmajor;
	public Paragraph setcontent(String para){
		
		Paragraph content = new Paragraph(para.replaceAll(" ", ""),new RtfFont("宋体",11, Font.NORMAL,Color.BLACK));
		content.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
		content.setFirstLineIndent(20);//首行缩进
		
		content.setLeading(1);//设置行间距
		return content;
	}
	public Paragraph settitle(String para){
		Paragraph title=new Paragraph(para, new RtfFont("宋体", (float) 11, Font.NORMAL, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(5);  //段前距离
		title.setSpacingBefore(5);//段后距离
		title.setLeading(18);//设置行间距
		title.setFirstLineIndent(20);//首行缩进
		return title;
	}
	public Paragraph settitle2(String para){
		Paragraph title=new Paragraph(para, new RtfFont("黑 体", 12, Font.NORMAL, Color.black));
		title.setAlignment(Element.ALIGN_LEFT);//靠左
		title.setSpacingAfter(8);  //段前距离
		title.setSpacingBefore(8);//段后距离
		title.setLeading(16);//设置行间距
		return title;
	}
	public Paragraph Tabletitle(String title) throws Exception{//设置标题类型 ，宋16加粗   教学安排
		RtfFont titleFont = new RtfFont("宋体", 16, Font.BOLD,Color.BLACK);//这个构造方法里面：第一个参数是字体类型，第二个参数是字体大小，第三个参数是字体格式是加粗还是正常，第四个参数是字体的颜色	
		Paragraph tabletitle=new Paragraph(title,titleFont );
		tabletitle.setAlignment(Element.ALIGN_CENTER);//居中
		tabletitle.setSpacingAfter(2);  //段前距离
		tabletitle.setSpacingBefore(2);//段后距离
	//	tabletitle.setLeading(1);//行间距
		return tabletitle;
	}
	
	
	public Paragraph Tabletitle2(String title) throws Exception{//设置标题类型 ，黑16加粗   教学安排
		RtfFont titleFont = new RtfFont("黑 体", 16, Font.NORMAL,Color.BLACK);//这个构造方法里面：第一个参数是字体类型，第二个参数是字体大小，第三个参数是字体格式是加粗还是正常，第四个参数是字体的颜色	
		Paragraph tabletitle=new Paragraph(title,titleFont );
		tabletitle.setAlignment(Element.ALIGN_CENTER);//居中
		tabletitle.setSpacingAfter(10);  //段前距离
		tabletitle.setSpacingBefore(10);//段后距离
	//	tabletitle.setLeading(1);//行间距
		return tabletitle;
	}
	
	
	public Paragraph TabletitlePicture(String title) throws Exception{//设置标题类型 ，宋16加粗   教学安排
		RtfFont titleFont = new RtfFont("宋体", 16, Font.BOLD,Color.BLACK);//这个构造方法里面：第一个参数是字体类型，第二个参数是字体大小，第三个参数是字体格式是加粗还是正常，第四个参数是字体的颜色	
		Paragraph tabletitle=new Paragraph(title,titleFont );
		tabletitle.setAlignment(Element.ALIGN_CENTER);//居中
		tabletitle.setSpacingAfter(0);  //段前距离
		tabletitle.setSpacingBefore(0);//段后距离
	//	tabletitle.setLeading(1);//行间距
		return tabletitle;
	}
	public Cell TeachCellContentTitleChinese(int x,int y,int z,String s) throws Exception{//合并行，列，对齐方式，写入的前表头内容，宋12正常
		RtfFont contextFont1 = new RtfFont("宋体", 12, Font.NORMAL,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
				teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		teach.setRowspan(x);// 当前单元格占两行,纵向跨度
		teach.setColspan(y);
		return teach;
	}
	public Cell TeachCellContentChinese(String s,int x,int y,int z) throws Exception{//写入表格的内容中文，宋10.5（五号）正常
		RtfFont contextFont1 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);
		Cell teach = new Cell(new Phrase(s,contextFont1));
		teach.setRowspan(x);// 当前单元格占两行,纵向跨度
		teach.setColspan(y);
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
				teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT);teach.setVerticalAlignment(Element.ALIGN_CENTER); break;
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
	public Cell TeachCellContentRoman1(float s,int z) throws Exception{//写入表格的内容Roman，宋10.5（五号）正常，1.水平居中，2，垂直居中3.水平并且垂直居中，4.靠左
		RtfFont contextFont1 = new RtfFont("Times New Roman", (float)10.5, Font.NORMAL,Color.BLACK);//为计算学分和学时的总和
		Cell teach = new Cell(new Phrase(String.valueOf(s),contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
				teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		return teach;
	}
	public Cell TeachCellContentRoman2(int s,int z) throws Exception{//写入表格的内容Roman，宋10.5（五号）正常，1.水平居中，2，垂直居中3.水平并且垂直居中，4.靠左
		RtfFont contextFont1 = new RtfFont("Times New Roman", (float)10.5, Font.NORMAL,Color.BLACK);//为计算学分和学时的总和
		Cell teach = new Cell(new Phrase(String.valueOf(s),contextFont1));
		switch(z){
		 case 1:teach.setHorizontalAlignment(Element.ALIGN_CENTER); break;
		 case 2:teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 3:teach.setHorizontalAlignment(Element.ALIGN_CENTER); 
				teach.setVerticalAlignment(Element.ALIGN_CENTER);break;
		 case 4:teach.setHorizontalAlignment(Element.ALIGN_LEFT); break;
		}
		return teach;
	}
	
	public Cell TeachCellHead1(String s,int x,int y,int z) throws Exception{//合并行，列，对齐方式，写入表头的内容，宋12加粗
		RtfFont contextFont1 = new RtfFont("Times New Roman", 10, Font.NORMAL,Color.BLACK);
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
	public Cell TeachCellHead(String s,int x,int y,int z) throws Exception{//合并行，列，对齐方式，写入表头的内容，宋12加粗
		RtfFont contextFont1 = new RtfFont("黑体", 12, Font.NORMAL,Color.BLACK);
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
	public Table TeachSchedule(List[] list,List<TheoreticalLesson> listprofessional,List[] professionlist51, List[] professionlist52, String pid) throws Exception{//生成教学安排表格
		int PublicCompulsoryCourse=0,ProfessionalBasicCourse=0;//用于计算单元格的合并，公共基础必修课，通识选修课，专业基础课，专业限选课，专业选修课
		int ProfessionalElectiveCours=0;
		int GeneralElectiveCourses=0,GeneralElectiveCourses1=0,GeneralElectiveCourses2=0,GeneralElectiveCourses3=0,GeneralElectiveCourses4=0;
		List<TheoreticalLesson> list1=new ArrayList<TheoreticalLesson>();
		//List<TheoreticalLesson> list2=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> list21=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> list22=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> list23=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> list24=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> list25=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> list3=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> list4=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> list5=new ArrayList<TheoreticalLesson>();
		List<String> department_gonggongxuanxie_ten = new ArrayList<String>(Arrays.asList("0104","1006","1305","0601","0602","0603","0604","0605","0606"));
		for(int pi=0;pi<listprofessional.size();pi++)
		{
				TheoreticalLesson rs1=listprofessional.get(pi);
				if(rs1.getCurriculum().getNatureOfCourse().getNatureOfCoursename().equals("公共必选课"))
				{
					PublicCompulsoryCourse++;
					list1.add(rs1);
				}
				/*if((rs1.getCurriculum().getNatureOfCourse().getNatureOfCoursename().equals("公共必选课")) && rs1.getCurriculum().getCourseCategory().equals("社会科学类"))//
				{
					GeneralElectiveCourses++;
					list21.add(rs1);
				}
				if((rs1.getCurriculum().getNatureOfCourse().getNatureOfCoursename().equals("公共必选课")) && rs1.getCurriculum().getCourseCategory().equals("文化与艺术类"))
				{
					GeneralElectiveCourses1++;
					list22.add(rs1);
				}
				if((rs1.getCurriculum().getNatureOfCourse().getNatureOfCoursename().equals("公共必选课")) && rs1.getCurriculum().getCourseCategory().equals("科学与技术类"))
				{
					GeneralElectiveCourses2++;
					list23.add(rs1);
				}	
				if((rs1.getCurriculum().getNatureOfCourse().getNatureOfCoursename().equals("公共必选课")) && rs1.getCurriculum().getCourseCategory().equals("数学与逻辑类"))
				{
					GeneralElectiveCourses3++;
					list24.add(rs1);
				}	
				if((rs1.getCurriculum().getNatureOfCourse().getNatureOfCoursename().equals("公共必选课")) && rs1.getCurriculum().getCourseCategory().equals("创业创新与发展类"))
				{
					GeneralElectiveCourses4++;
					list25.add(rs1);
				}	*/
		}
		int sumgongbirow = GeneralElectiveCourses+GeneralElectiveCourses1+GeneralElectiveCourses2+GeneralElectiveCourses3+GeneralElectiveCourses4;
		/*	List<TheoreticalLesson> zhuanyebixuanMode1 = new ArrayList<TheoreticalLesson>();
		for(int j=0;j<list[0].size();j++)
		{
			int fangxiangnum = 0;
			TheoreticalLesson everyTheoreticalLesson = (TheoreticalLesson) list[0].get(j);
			for(int x=0;x<list.length;x++) {
				List<TheoreticalLesson> havaTheoreticalLesson = list[x];
				for(int k=0;k<havaTheoreticalLesson.size();k++)
				{
					if(everyTheoreticalLesson.getCurriculum().getCurriculumid().equals(havaTheoreticalLesson.get(k).getCurriculum().getCurriculumid()))
					{
						fangxiangnum += 1;
					}
				}

			}
			if(fangxiangnum == list.length)
			{
				zhuanyebixuanMode1.add(everyTheoreticalLesson);
				for (int xi=0;xi<list.length;xi++)
				{
					for(int ri =0;ri<list[xi].size();ri++)
					{
						TheoreticalLesson findTheoreticalLesson = (TheoreticalLesson) list[xi].get(ri);
						if(everyTheoreticalLesson.getCurriculum().getCurriculumid().equals(findTheoreticalLesson.getCurriculum().getCurriculumid()))
						{
							list[xi].remove(findTheoreticalLesson);
							
						}
					}
				}
				j--;
			}
		}*/

		
/*		int rowcount=0,erowcount=0;
		int rowcount1=0;

		for(int i=0;i<list.length;i++)
		{
			if(list[i].size() != 0)
			{
				erowcount += list[i].size() + 1;
			}
	
		}*/
		
		int rowcount =list[0].size() +1;
		int PBcount =0,PBcount1 =0,PBcount2 =0;
		for(int x=0;x<professionlist51.length;x++)
		{
			if(professionlist51[x].size() != 0)
			{
				List<TheoreticalLesson> ProB =professionlist51[x];
				PBcount1 += ProB.size()+1;
			}

		}
		for(int x=0;x<professionlist52.length;x++)
		{
			if(professionlist52[x].size() != 0)
			{
				List<TheoreticalLesson> ProB =professionlist52[x];
				PBcount2 += ProB.size()+1;
			}

		}
		PBcount = PBcount1+PBcount2;
		int sum=sumgongbirow+rowcount+PBcount+9;
		float[] widths={4,3,3,5,10,38,6,5,5,5,6,5,5};
		Table ss=table(13,sum,widths);//列，行
		ss.enableBorderSide(20);//使表头连续
		Cell cell = new Cell(); 
		cell.setHeader(true);
		ss.addCell(TeachCellHead("课\n程\n平\n台",2,1,3));
		ss.addCell(TeachCellHead("课\n程\n类\n别",2,2,3));
		ss.addCell(TeachCellHead("序\n号",2,1,3));
		ss.addCell(TeachCellHead("课程编号",2,1,3));
		ss.addCell(TeachCellHead("课程名称",2,1,3));
		ss.addCell(TeachCellHead("\n学\n分",2,1,3));
		ss.addCell(TeachCellHead("学时数",1,4,3));
		ss.addCell(TeachCellHead("周\n学\n时",2,1,3));
		ss.addCell(TeachCellHead("开\n课\n学\n期",2,1,3));
		ss.addCell(TeachCellHead("总\n学\n时",1,1,3));
		ss.addCell(TeachCellHead("上\n机",1,1,3));
		ss.addCell(TeachCellHead("实\n验",1,1,3));
		ss.addCell(TeachCellHead("其他\n实践",1,1,3));
	//	ss.addCell(cell); 
		ss.endHeaders(); 

		
		//ss.addCell(TeachCellHead("共\n公\n共\n教\n育\n平\n台",sumgongbirow+7,1,3));
	//	ss.addCell(TeachCellHead("公\n共\n必\n选\n课",sumgongbirow+1,2,3));
		ss.addCell(TeachCellHead("公\n共\n教\n育\n平\n台",list1.size()+7,1,3));
		ss.addCell(TeachCellHead("公\n共\n必\n选\n课",list1.size()+1,2,3));
		
		float CommonScore=0,GeneralScore=0,ProfessionalBasicCourseScore1=0,ProfessionalBasicCourseScore2=0,ProfessionalElectiveCoursScore=0;//公共必修课总学分，通识课总学分，专业课学分
		int CommonPeriod=0,GeneralPeriod=0,ProfessionalBasicCoursePeriod1=0,ProfessionalBasicCoursePeriod2=0,ProfessionalElectiveCoursPeriod=0;//公共必修课总学时，通识课总学时，专业课学时
		int[] i=new int[]{1,1,1,1,1};
		
		if(list1 != null && list1.size()!= 0)
		{
			//ss.addCell(TeachCellHead("社\n会\n科\n学\n类",GeneralElectiveCourses,1,3));
			
			for(int si=0;si<list1.size();si++)
			{
				TheoreticalLesson rs1=list1.get(si);
				ss.addCell(TeachCellContentChinese(String.valueOf(i[0]++),1,1,3));// 序号
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCurriculumid(),3));//课程编码
			//	ss.addCell(TeachCellContentChinese(rs1.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				if(rs1.getIsxueweike().equals("是"))
					ss.addCell(TeachCellContentChinese(rs1.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
				else ss.addCell(TeachCellContentChinese(rs1.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCredit(),3));//学分
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfALL(),3));//总学时
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfCom(),3));//上机
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfExp(),3));//实验
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfPractice(),3));//实践
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfWeek(),3));//周学时
				ss.addCell(TeachCellContentRoman(rs1.getXueqi(),3));//开课学期
			}
		}
		
		/*if(GeneralElectiveCourses1 != 0)
		{
			ss.addCell(TeachCellHead("文\n化\n与\n艺\n术\n类",GeneralElectiveCourses1,1,3));
			
			for(int wi=0;wi<list22.size();wi++)
			{
				TheoreticalLesson rs1=list22.get(wi);
				ss.addCell(TeachCellContentChinese(String.valueOf(i[0]++),1,1,3));// 序号
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCurriculumid(),3));//课程编码
				ss.addCell(TeachCellContentChinese(rs1.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCredit(),3));//学分
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfALL(),3));//总学时
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfCom(),3));//上机
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfExp(),3));//实验
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfPractice(),3));//实践
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfWeek(),3));//周学时
				ss.addCell(TeachCellContentRoman(rs1.getXueqi(),3));//开课学期
			}
		}
		
		if(GeneralElectiveCourses3 != 0)
		{
			ss.addCell(TeachCellHead("数\n学\n与\n逻\n辑\n类",GeneralElectiveCourses3,1,3));
			
			for(int wi=0;wi<list24.size();wi++)
			{
				TheoreticalLesson rs1=list24.get(wi);
				ss.addCell(TeachCellContentChinese(String.valueOf(i[0]++),1,1,3));// 序号
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCurriculumid(),3));//课程编码
				ss.addCell(TeachCellContentChinese(rs1.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCredit(),3));//学分
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfALL(),3));//总学时
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfCom(),3));//上机
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfExp(),3));//实验
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfPractice(),3));//实践
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfWeek(),3));//周学时
				ss.addCell(TeachCellContentRoman(rs1.getXueqi(),3));//开课学期
			}
		}
		
		if(GeneralElectiveCourses4 != 0)
		{
			ss.addCell(TeachCellHead("科\n学\n与\n技\n术\n类",GeneralElectiveCourses4,1,3));
			
			for(int wi=0;wi<list25.size();wi++)
			{
				TheoreticalLesson rs1=list25.get(wi);
				ss.addCell(TeachCellContentChinese(String.valueOf(i[0]++),1,1,3));// 序号
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCurriculumid(),3));//课程编码
				ss.addCell(TeachCellContentChinese(rs1.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCredit(),3));//学分
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfALL(),3));//总学时
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfCom(),3));//上机
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfExp(),3));//实验
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfPractice(),3));//实践
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfWeek(),3));//周学时
				ss.addCell(TeachCellContentRoman(rs1.getXueqi(),3));//开课学期
			}
		}
		
		if(GeneralElectiveCourses2 != 0)
		{
			ss.addCell(TeachCellHead("创\n业\n创\n新\n与\n发\n展\n类",GeneralElectiveCourses2,1,3));
			
			for(int wi=0;wi<list23.size();wi++)
			{
				TheoreticalLesson rs1=list23.get(wi);
				ss.addCell(TeachCellContentChinese(String.valueOf(i[0]++),1,1,3));// 序号
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCurriculumid(),3));//课程编码
				ss.addCell(TeachCellContentChinese(rs1.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getCredit(),3));//学分
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfALL(),3));//总学时
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfCom(),3));//上机
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfExp(),3));//实验
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfPractice(),3));//实践
				ss.addCell(TeachCellContentRoman(rs1.getCurriculum().getHoursOfWeek(),3));//周学时
				ss.addCell(TeachCellContentRoman(rs1.getXueqi(),3));//开课学期
			}
		}*/

		for(int x=0;x<list1.size();x++)//公共必选课
		{
			TheoreticalLesson rs1=list1.get(x);
			
			if(rs1.getCurriculum().getCredit().equals(""))
				CommonScore =CommonScore+0;
			else
				CommonScore =CommonScore+Float.parseFloat(rs1.getCurriculum().getCredit());
			if(rs1.getCurriculum().getHoursOfALL().equals(""))
				CommonPeriod = CommonPeriod +0;
			else
				CommonPeriod =CommonPeriod+Integer.parseInt(rs1.getCurriculum().getHoursOfALL());

		}
		ss.addCell(TeachCellContentChinese("小计", 1,3,3));
		BigDecimal CommonScore_b = new BigDecimal(CommonScore); 
		CommonScore = CommonScore_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		ss.addCell(TeachCellContentRoman1(CommonScore, 3));
		ss.addCell(TeachCellContentRoman2(CommonPeriod, 3));
		for(int j=0;j<5;j++)ss.addCell("");
		if(department_gonggongxuanxie_ten.contains(pid))
		{
			ss.addCell(TeachCellHead("公\n共\n选\n修\n课",6,2,3));
			ss.addCell(TeachCellContentChinese("1",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("社会科学类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");//总学时不写所以空7个cell
			
			ss.addCell(TeachCellContentChinese("2",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("文化与艺术类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
			
			ss.addCell(TeachCellContentChinese("3",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("科学与技术类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
					
			ss.addCell(TeachCellContentChinese("4",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("数学与逻辑类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
			
			ss.addCell(TeachCellContentChinese("5",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("创业创新与发展类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
			
			ss.addCell(TeachCellContentChinese("小计", 1,3,3));
			ss.addCell(TeachCellContentRoman1(10,3));	//总学分
			ss.addCell(TeachCellContentRoman2(10*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
		}
		else
		{
			ss.addCell(TeachCellHead("公\n共\n选\n修\n课",6,2,3));
			ss.addCell(TeachCellContentChinese("1",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("社会科学类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");//总学时不写所以空7个cell
			
			ss.addCell(TeachCellContentChinese("2",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("文化与艺术类",1,2,4));
			ss.addCell(TeachCellContentRoman1(6,3));//总学时
			ss.addCell(TeachCellContentRoman2(6*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
			
			ss.addCell(TeachCellContentChinese("3",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("科学与技术类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
					
			ss.addCell(TeachCellContentChinese("4",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("数学与逻辑类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
			
			ss.addCell(TeachCellContentChinese("5",1,1,3));// 序号
			ss.addCell(TeachCellContentChinese("创业创新与发展类",1,2,4));
			ss.addCell(TeachCellContentRoman1(2,3));//总学时
			ss.addCell(TeachCellContentRoman2(2*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
			
			ss.addCell(TeachCellContentChinese("小计", 1,3,3));
			ss.addCell(TeachCellContentRoman1(14,3));	//总学分
			ss.addCell(TeachCellContentRoman2(14*16,3));//总学时
			for(int j=0;j<5;j++)ss.addCell("");
		}
		
		
		/******************专业必选课**********************************/
			ss.addCell(TeachCellHead("专\n业\n教\n育平\n台",rowcount+PBcount,1,3));//应该是加2，
			ss.addCell(TeachCellHead("专\n业\n必\n选\n课",list[0].size()+1,2,3));
			for(int m1 = 0;m1<list[0].size();m1++)
			{
				TheoreticalLesson rs3 =(TheoreticalLesson) list[0].get(m1);
				ss.addCell(TeachCellContentChinese(String.valueOf(i[2]++),1,1,3));// 序号
				ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getCurriculumid(),3));//课程编码
				if(rs3.getIsxueweike().equals("是"))
					ss.addCell(TeachCellContentChinese(rs3.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
				else ss.addCell(TeachCellContentChinese(rs3.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				ProfessionalBasicCourseScore1 =ProfessionalBasicCourseScore1+Float.parseFloat(rs3.getCurriculum().getCredit());
				ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getCredit(),3));//学分
				if(rs3.getCurriculum().getHoursOfALL().equals(""))
					ProfessionalBasicCoursePeriod1 = ProfessionalBasicCoursePeriod1 +0;
				else
					ProfessionalBasicCoursePeriod1 =ProfessionalBasicCoursePeriod1+Integer.parseInt(rs3.getCurriculum().getHoursOfALL());
				ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfALL(),3));//总学时
				ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfCom(),3));//上机
				ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfExp(),3));//实验（践）
				ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfPractice(),3));//实践
				ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfWeek(),3));//周学时
				ss.addCell(TeachCellContentRoman(rs3.getXueqi(),3));//开课学期
			}
			ss.addCell(TeachCellContentChinese("小计", 1,3,3));//开课学期
			
			BigDecimal ProfessionalBasicCourseScore1_b = new BigDecimal(ProfessionalBasicCourseScore1); 
			ProfessionalBasicCourseScore1 = ProfessionalBasicCourseScore1_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			ss.addCell(TeachCellContentRoman1(ProfessionalBasicCourseScore1, 3));
			ss.addCell(TeachCellContentRoman2(ProfessionalBasicCoursePeriod1, 3));
			for(int j=0;j<5;j++)ss.addCell("");
			/*
			if(erowcount != 0)
			{
				ss.addCell(TeachCellHead("专\n业\n必\n选\n课\n程\n模\n块\nⅡ",erowcount,1,3));
				for(int x2=0;x2<list.length;x2++){
					List ProBasers3=list[x2];
					int inum=1;
					if(ProBasers3 != null && ProBasers3.size() != 0)
					{
						TheoreticalLesson professname =(TheoreticalLesson)ProBasers3.get(0);
						ss.addCell(TeachCellHead(professname.getProfessional().getProfessionalname(),ProBasers3.size()+1,1,3));
						for(int x21=0;x21<ProBasers3.size();x21++)
						{
							TheoreticalLesson rs3 =(TheoreticalLesson) ProBasers3.get(x21);
							ss.addCell(TeachCellContentChinese(String.valueOf(inum++),1,1,3));// 序号
							ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getCurriculumid(),3));//课程编码
							if(rs3.getIsxueweike().equals("是"))
								ss.addCell(TeachCellContentChinese(rs3.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
							else ss.addCell(TeachCellContentChinese(rs3.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
							ProfessionalBasicCourseScore2 =ProfessionalBasicCourseScore2+Float.parseFloat(rs3.getCurriculum().getCredit());
							ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getCredit(),3));//学分
							if(rs3.getCurriculum().getHoursOfALL().equals(""))
								ProfessionalBasicCoursePeriod2 = ProfessionalBasicCoursePeriod2 +0;
							else
								ProfessionalBasicCoursePeriod2 =ProfessionalBasicCoursePeriod2+Integer.parseInt(rs3.getCurriculum().getHoursOfALL());
							ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfALL(),3));//总学时
							ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfCom(),3));//上机
							ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfExp(),3));//实验（践）
							ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfPractice(),3));//实践
							ss.addCell(TeachCellContentRoman(rs3.getCurriculum().getHoursOfWeek(),3));//周学时
							ss.addCell(TeachCellContentRoman(rs3.getXueqi(),3));//开课学期
						}
						ss.addCell(TeachCellContentChinese("小计", 1,3,3));//开课学期
						ss.addCell(TeachCellContentRoman1(ProfessionalBasicCourseScore2, 3));
						ss.addCell(TeachCellContentRoman2(ProfessionalBasicCoursePeriod2, 3));
						ProfessionalBasicCourseScore2=0;
						ProfessionalBasicCoursePeriod2=0;
						for(int j=0;j<5;j++)ss.addCell("");
					}
					
				}
			}*/
		/******************专业选修课模块1***************************/
		if(professionlist51[0].size() != 0)
		{
		if(professionlist51.length >1)//分方向 专业选修课
		{
			ss.addCell(TeachCellHead("专\n业\n选\n修\n课\n程\n模\n块\nⅠ",PBcount1,1,3));
			for(int x=0;x<professionlist51.length;x++)
			{
				List ProElec=professionlist51[x];
				if(ProElec != null && ProElec.size() != 0)
				{
				TheoreticalLesson professname =(TheoreticalLesson)ProElec.get(0);
				ss.addCell(TeachCellHead1(professname.getProfessional().getProfessionalname(),ProElec.size()+1,1,3));
				for(int x4=0;x4<ProElec.size();x4++){
					TheoreticalLesson rs5=(TheoreticalLesson) ProElec.get(x4);
					ss.addCell(TeachCellContentChinese(String.valueOf(i[4]++),1,1,3));// 序号
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getCurriculumid(),3));//课程编码
					if(rs5.getIsxueweike().equals("是"))
						ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
					else ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
					//ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
					ProfessionalElectiveCoursScore =ProfessionalElectiveCoursScore+Float.parseFloat(rs5.getCurriculum().getCredit());
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getCredit(),3));//学分
					
					if(rs5.getCurriculum().getHoursOfALL().equals(""))
						ProfessionalElectiveCoursPeriod = ProfessionalElectiveCoursPeriod +0;
					else
						ProfessionalElectiveCoursPeriod =ProfessionalElectiveCoursPeriod+Integer.parseInt(rs5.getCurriculum().getHoursOfALL());
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfALL(),3));//总学时
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfCom(),3));//上机
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfExp(),3));//实验
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfPractice(),3));//实践
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfWeek(),3));//周学时
					ss.addCell(TeachCellContentRoman(rs5.getXueqi(),3));//开课学期
				}
				i[4]=1;
				ss.addCell(TeachCellContentChinese("小计", 1,3,3));//开课学期
				BigDecimal ProfessionalElectiveCoursScore_b = new BigDecimal(ProfessionalElectiveCoursScore); 
				ProfessionalBasicCourseScore1 = ProfessionalElectiveCoursScore_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				ss.addCell(TeachCellContentRoman1(ProfessionalElectiveCoursScore, 3));
				ss.addCell(TeachCellContentRoman2(ProfessionalElectiveCoursPeriod, 3));
				ProfessionalElectiveCoursScore=0;ProfessionalElectiveCoursPeriod=0;
				for(int j=0;j<5;j++)ss.addCell("");
				}
			}

		}
		else//不分方向 专业没有方向的时候
		{	
			List<TheoreticalLesson> NOfen=professionlist51[0];
			ss.addCell(TeachCellHead("专\n业\n选\n修\n课\n程\n模\n块\nⅠ",NOfen.size(),2,3));
			for(int x4=0;x4<NOfen.size();x4++){
				TheoreticalLesson rs5=(TheoreticalLesson) NOfen.get(x4);
				ss.addCell(TeachCellContentChinese(String.valueOf(i[4]++),1,1,3));// 序号
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getCurriculumid(),3));//课程编码
				if(rs5.getIsxueweike().equals("是"))
					ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
				else ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				//ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				ProfessionalElectiveCoursScore =ProfessionalElectiveCoursScore+Float.parseFloat(rs5.getCurriculum().getCredit());
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getCredit(),3));//学分
				
				if(rs5.getCurriculum().getHoursOfALL().equals(""))
					ProfessionalElectiveCoursPeriod = ProfessionalElectiveCoursPeriod +0;
				else
					ProfessionalElectiveCoursPeriod =ProfessionalElectiveCoursPeriod+Integer.parseInt(rs5.getCurriculum().getHoursOfALL());
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfALL(),3));//总学时
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfCom(),3));//上机
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfExp(),3));//实验
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfPractice(),3));//实践
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfWeek(),3));//周学时
				ss.addCell(TeachCellContentRoman(rs5.getXueqi(),3));//开课学期
			}
			ss.addCell(TeachCellContentChinese("小计", 1,5,3));//开课学期
			BigDecimal ProfessionalElectiveCoursScore_b = new BigDecimal(ProfessionalElectiveCoursScore); 
			ProfessionalBasicCourseScore1 = ProfessionalElectiveCoursScore_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			ss.addCell(TeachCellContentRoman1(ProfessionalElectiveCoursScore, 3));
			ss.addCell(TeachCellContentRoman2(ProfessionalElectiveCoursPeriod, 3));
			ProfessionalElectiveCoursScore=0;ProfessionalElectiveCoursPeriod=0;
			for(int j=0;j<5;j++)ss.addCell("");
		}
		}
		
		/******************专业选修课模块2***************************/
		boolean flag = false;
		for (int j = 0; j < professionlist52.length; j++) {
			if (professionlist52[j].size() != 0) {
				flag = true;
				break;
			}
		}
		if(flag)
		{
		if(professionlist52.length >1)//分方向 专业选修课
		{
			ss.addCell(TeachCellHead("专\n业\n选\n修\n课\n程\n模\n块\nⅡ",PBcount2,1,3));
			for(int x=0;x<professionlist52.length;x++)
			{
				List ProElec=professionlist52[x];
				if(ProElec != null && ProElec.size() != 0)
				{								
				TheoreticalLesson professname =(TheoreticalLesson)ProElec.get(0);
				ss.addCell(TeachCellHead1(professname.getProfessional().getProfessionalname(),ProElec.size()+1,1,3));
				for(int x4=0;x4<ProElec.size();x4++){
					TheoreticalLesson rs5=(TheoreticalLesson) ProElec.get(x4);
					ss.addCell(TeachCellContentChinese(String.valueOf(i[4]++),1,1,3));// 序号
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getCurriculumid(),3));//课程编码
					if(rs5.getIsxueweike().equals("是"))
						ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
					else ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
					//ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
					ProfessionalElectiveCoursScore =ProfessionalElectiveCoursScore+Float.parseFloat(rs5.getCurriculum().getCredit());
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getCredit(),3));//学分
					
					if(rs5.getCurriculum().getHoursOfALL().equals(""))
						ProfessionalElectiveCoursPeriod = ProfessionalElectiveCoursPeriod +0;
					else
						ProfessionalElectiveCoursPeriod =ProfessionalElectiveCoursPeriod+Integer.parseInt(rs5.getCurriculum().getHoursOfALL());
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfALL(),3));//总学时
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfCom(),3));//上机
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfExp(),3));//实验
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfPractice(),3));//实践
					ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfWeek(),3));//周学时
					ss.addCell(TeachCellContentRoman(rs5.getXueqi(),3));//开课学期
				}
				i[4]=1;
				ss.addCell(TeachCellContentChinese("小计", 1,3,3));//开课学期
				BigDecimal ProfessionalElectiveCoursScore_b = new BigDecimal(ProfessionalElectiveCoursScore); 
				ProfessionalElectiveCoursScore = ProfessionalElectiveCoursScore_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				ss.addCell(TeachCellContentRoman1(ProfessionalElectiveCoursScore, 3));
				ss.addCell(TeachCellContentRoman2(ProfessionalElectiveCoursPeriod, 3));
				ProfessionalElectiveCoursScore=0;ProfessionalElectiveCoursPeriod=0;
				for(int j=0;j<5;j++)ss.addCell("");
				
				}
			}

		}
		else//不分方向 专业没有方向的时候
		{	
			List<TheoreticalLesson> NOfen=professionlist52[0];
			ss.addCell(TeachCellHead("专\n业\n选\n修\n课\n程\n模\n块\nⅡ",NOfen.size(),2,3));
			for(int x4=0;x4<NOfen.size();x4++){
				TheoreticalLesson rs5=(TheoreticalLesson) NOfen.get(x4);
				ss.addCell(TeachCellContentChinese(String.valueOf(i[4]++),1,1,3));// 序号
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getCurriculumid(),3));//课程编码
				if(rs5.getIsxueweike().equals("是"))
					ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
				else ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
			//	ss.addCell(TeachCellContentChinese(rs5.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				ProfessionalElectiveCoursScore =ProfessionalElectiveCoursScore+Float.parseFloat(rs5.getCurriculum().getCredit());
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getCredit(),3));//学分
				
				if(rs5.getCurriculum().getHoursOfALL().equals(""))
					ProfessionalElectiveCoursPeriod = ProfessionalElectiveCoursPeriod +0;
				else
					ProfessionalElectiveCoursPeriod =ProfessionalElectiveCoursPeriod+Integer.parseInt(rs5.getCurriculum().getHoursOfALL());
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfALL(),3));//总学时
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfCom(),3));//上机
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfExp(),3));//实验
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfPractice(),3));//实践
				ss.addCell(TeachCellContentRoman(rs5.getCurriculum().getHoursOfWeek(),3));//周学时
				ss.addCell(TeachCellContentRoman(rs5.getXueqi(),3));//开课学期
			}
			ss.addCell(TeachCellContentChinese("小计", 1,5,3));//开课学期
			BigDecimal ProfessionalElectiveCoursScore_b = new BigDecimal(ProfessionalElectiveCoursScore); 
			ProfessionalElectiveCoursScore = ProfessionalElectiveCoursScore_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			ss.addCell(TeachCellContentRoman1(ProfessionalElectiveCoursScore, 3));
			ss.addCell(TeachCellContentRoman2(ProfessionalElectiveCoursPeriod, 3));
			ProfessionalElectiveCoursScore=0;ProfessionalElectiveCoursPeriod=0;
			for(int j=0;j<5;j++)ss.addCell("");
		}
		}
		return ss;
	}
	
	public Table FocusPractice(List<PracticeLesson> list, List<Professional> proNum)throws Exception{
		int practice;
		practice=list.size();
		float[] widths={6,12,25,7,7,11,18,14};
		List<List<PracticeLesson>> prolist= new ArrayList<List<PracticeLesson>>();
		
		while(list != null && list.size() != 0)
		{
			List<PracticeLesson> professCour = new ArrayList<PracticeLesson>();
			String cid = list.get(0).getCurriculum().getCurriculumid();
			for(int px=0;px<list.size();px++)
			{
				if(cid.equals(list.get(px).getCurriculum().getCurriculumid()))
				{
					professCour.add(list.get(px));
					list.remove(px);
					px--;
				}
			}
			prolist.add(professCour);
		}
		Table pp=table(8, prolist.size()+2,widths);
		pp.addCell(TeachCellHead("序号",1,1,3));
		pp.addCell(TeachCellHead("课程编码",1,1,3));
		pp.addCell(TeachCellHead("项目名称",1,1,3));
		pp.addCell(TeachCellHead("学分",1,1,3));
		pp.addCell(TeachCellHead("学期",1,1,3));
		pp.addCell(TeachCellHead("起止周",1,1,3));
		pp.addCell(TeachCellHead("应用方向",1,1,3));
		pp.addCell(TeachCellHead("备注",1,1,3));
		int pn=1;
		float PracticeScore=0;

	
		for(int pl=0;pl<prolist.size();pl++)
		{
			List<PracticeLesson> newprofess = prolist.get(pl);
			if(newprofess.size() == proNum.size() || proNum.size()==0)
			{
				PracticeLesson rs = newprofess.get(0);
				pp.addCell(TeachCellContentChinese(String.valueOf(pn++), 1, 1, 3));// 序号
				pp.addCell(TeachCellContentRoman(rs.getCurriculum().getCurriculumid(), 3));// 课程编码
				if(rs.getIsxueweike() != null && !rs.getIsxueweike().equals("") && rs.getIsxueweike().equals("是"))
					pp.addCell(TeachCellContentChinese(rs.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
				else pp.addCell(TeachCellContentChinese(rs.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				PracticeScore = PracticeScore + Float.parseFloat(rs.getCurriculum().getCredit());
				pp.addCell(TeachCellContentRoman(rs.getCurriculum().getCredit(), 3));// 学分
				pp.addCell(TeachCellContentRoman(rs.getXueqi(), 3));// 学期
				pp.addCell(TeachCellContentRoman(rs.getQizhizhou(),3));// 起止周
				pp.addCell(TeachCellContentChinese("全部方向", 1, 1, 3));// 备注
				pp.addCell(TeachCellContentChinese(rs.getBeizhu(), 1, 1, 3));// 备注
			}
			else
			{
				String proname = "";
				for(int ni =0;ni<newprofess.size();ni++)
				{
					proname += newprofess.get(ni).getProfessional().getProfessionalname()+"、";
				}
				
				PracticeLesson rs = newprofess.get(0);
				pp.addCell(TeachCellContentChinese(String.valueOf(pn++), 1, 1, 3));// 序号
				pp.addCell(TeachCellContentRoman(rs.getCurriculum().getCurriculumid(), 3));// 课程编码
				if(rs.getIsxueweike() != null && !rs.getIsxueweike().equals("") && rs.getIsxueweike().equals("是"))
					pp.addCell(TeachCellContentChinese(rs.getCurriculum().getCurriculumCname()+"*",1,1,4));//课程名称
				else pp.addCell(TeachCellContentChinese(rs.getCurriculum().getCurriculumCname(),1,1,4));//课程名称
				
				PracticeScore = PracticeScore + Float.parseFloat(rs.getCurriculum().getCredit());
				pp.addCell(TeachCellContentRoman(rs.getCurriculum().getCredit(), 3));// 学分
				pp.addCell(TeachCellContentRoman(rs.getXueqi(), 3));// 学期
				pp.addCell(TeachCellContentRoman(rs.getQizhizhou(),3));// 起止周
				pp.addCell(TeachCellContentChinese(proname, 1, 1, 4));// 备注
				pp.addCell(TeachCellContentChinese(rs.getBeizhu(), 1, 1, 3));// 备注
			}
		}
		pp.addCell(TeachCellContentChinese("合计", 1,3,3));//开课学期
		BigDecimal PracticeScore_b = new BigDecimal(PracticeScore); 
		PracticeScore = PracticeScore_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		pp.addCell(TeachCellContentRoman1(PracticeScore, 3));

		for(int j=0;j<4;j++)pp.addCell("");
		return pp;
	}
	public Image picture(String path) throws Exception{
		String picturepath = System.getProperty("user.dir")+"/TPM/WebRoot/TopologyImage/"+path;
		Image image = Image.getInstance(picturepath);
		image.scaleAbsoluteHeight(410);
		image.scaleAbsoluteWidth(690);
		//Alignment = image.ALIGN_CENTER;
		return image;
	}
	public Table GeneralArrangement(List<MainTainOfPT> list,List<TrainingEvents> list1,PTBasicInformation word1)throws Exception{
		int ganum=0;
		ganum=list1.size();
		float[] widths=new float[ganum+22];
		for(int i=0;i<ganum+22;i++)widths[i]=100/ganum+22;
		Table ga=table(ganum+22, 10,widths);//列，行
		ga.addCell(TeachCellContentChinese("学\n年",1,1,3));
		ga.addCell(TeachCellContentChinese("学\n期",1,1,3));
		ga.addCell(TeachCellContentChinese("教学进度安排(周)",1,20,3));
		List<TrainingEvents> xiaojiList= new ArrayList<TrainingEvents>();
		for(int i=0;i<list1.size();i++)
		{
			TrainingEvents rs=list1.get(i);
			if(rs.getTrainingEventsname().equals("小计"))
			{
				xiaojiList.add(rs);
				list1.remove(rs);
				i--;
			}
			else{
			 ga.addCell(TeachCellContentChinese(rs.getTrainingEventsname(),1,1,3));
			}
		}
		list1.add(xiaojiList.get(0));
		ga.addCell(TeachCellContentChinese(xiaojiList.get(0).getTrainingEventsname(),1,1,3));
		ga.addCell(TeachCellContentChinese("",1,1,3));
		ga.addCell(TeachCellContentChinese("",1,1,3));
		for(int i=1;i<21;i++)ga.addCell(TeachCellContentRoman(String.valueOf(i),3));
		for(int i=0;i<list1.size()-1;i++)
		{
			ga.addCell(TeachCellContentRoman(String.valueOf((char)(i+65)),3));
		}
		ga.addCell(TeachCellContentChinese("",1,1,3));
		ga.addCell(TeachCellContentChinese("一",2,1,3));
		ga.addCell(TeachCellContentRoman("1",3));
		
	
			MainTainOfPT rs1=list.get(0);
			ga.addCell(TeachCellContentRoman(rs1.getWeek1(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek2(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek3(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek4(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek5(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek6(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek7(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek8(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek9(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek10(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek11(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek12(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek13(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek14(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek15(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek16(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek17(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek18(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek19(),3));
			ga.addCell(TeachCellContentRoman(rs1.getWeek20(),3));	
		for(int i = 0;i<list1.size();i++)
		{
			TrainingEvents rs2 = list1.get(i);
			ga.addCell(TeachCellContentRoman(rs2.getSemester1(),3));
		}
		ga.addCell(TeachCellContentRoman("2",3));
			MainTainOfPT rs3=list.get(1);
			ga.addCell(TeachCellContentRoman(rs3.getWeek1(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek2(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek3(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek4(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek5(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek6(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek7(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek8(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek9(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek10(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek11(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek12(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek13(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek14(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek15(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek16(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek17(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek18(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek19(),3));
			ga.addCell(TeachCellContentRoman(rs3.getWeek20(),3));
		for(int i = 0;i<list1.size();i++)
		{
			TrainingEvents rs2 = list1.get(i);
			ga.addCell(TeachCellContentRoman(rs2.getSemester2(),3));
		}
		ga.addCell(TeachCellContentChinese("二",2,1,3));
		ga.addCell(TeachCellContentRoman("3",3));

			MainTainOfPT rs4=list.get(2);
			ga.addCell(TeachCellContentRoman(rs4.getWeek1(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek2(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek3(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek4(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek5(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek6(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek7(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek8(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek9(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek10(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek11(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek12(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek13(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek14(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek15(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek16(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek17(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek18(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek19(),3));
			ga.addCell(TeachCellContentRoman(rs4.getWeek20(),3));

		for(int i = 0;i<list1.size();i++)
		{
			TrainingEvents rs2 = list1.get(i);
			ga.addCell(TeachCellContentRoman(rs2.getSemester3(),3));
		}
		ga.addCell(TeachCellContentRoman("4",3));

			MainTainOfPT rs5=list.get(3);
			ga.addCell(TeachCellContentRoman(rs5.getWeek1(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek2(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek3(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek4(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek5(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek6(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek7(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek8(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek9(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek10(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek11(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek12(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek13(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek14(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek15(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek16(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek17(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek18(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek19(),3));
			ga.addCell(TeachCellContentRoman(rs5.getWeek20(),3));

		for(int i = 0;i<list1.size();i++)
		{
			TrainingEvents rs2 = list1.get(i);
			ga.addCell(TeachCellContentRoman(rs2.getSemester4(),3));
		}
		ga.addCell(TeachCellContentChinese("三",2,1,3));
		ga.addCell(TeachCellContentRoman("5",3));

			MainTainOfPT rs6=list.get(4);
			ga.addCell(TeachCellContentRoman(rs6.getWeek1(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek2(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek3(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek4(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek5(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek6(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek7(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek8(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek9(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek10(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek11(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek12(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek13(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek14(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek15(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek16(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek17(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek18(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek19(),3));
			ga.addCell(TeachCellContentRoman(rs6.getWeek20(),3));	

		for(int i = 0;i<list1.size();i++)
		{
			TrainingEvents rs2 = list1.get(i);
			ga.addCell(TeachCellContentRoman(rs2.getSemester5(),3));
		}
		ga.addCell(TeachCellContentRoman("6",3));

			MainTainOfPT rs7=list.get(5);
			ga.addCell(TeachCellContentRoman(rs7.getWeek1(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek2(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek3(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek4(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek5(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek6(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek7(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek8(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek9(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek10(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek11(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek12(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek13(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek14(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek15(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek16(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek17(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek18(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek19(),3));
			ga.addCell(TeachCellContentRoman(rs7.getWeek20(),3));	

		for(int i = 0;i<list1.size();i++)
		{
			TrainingEvents rs2 = list1.get(i);
			ga.addCell(TeachCellContentRoman(rs2.getSemester6(),3));
		}
		ga.addCell(TeachCellContentChinese("四",2,1,3));
		ga.addCell(TeachCellContentRoman("7",3));

			MainTainOfPT rs8=list.get(6);
			ga.addCell(TeachCellContentRoman(rs8.getWeek1(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek2(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek3(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek4(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek5(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek6(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek7(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek8(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek9(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek10(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek11(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek12(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek13(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek14(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek15(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek16(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek17(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek18(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek19(),3));
			ga.addCell(TeachCellContentRoman(rs8.getWeek20(),3));	

		for(int i = 0;i<list1.size();i++)
		{
			TrainingEvents rs2 = list1.get(i);
			ga.addCell(TeachCellContentRoman(rs2.getSemester7(),3));
		}
		ga.addCell(TeachCellContentRoman("8",3));

			MainTainOfPT rs9=list.get(7);
			ga.addCell(TeachCellContentRoman(rs9.getWeek1(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek2(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek3(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek4(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek5(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek6(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek7(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek8(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek9(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek10(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek11(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek12(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek13(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek14(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek15(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek16(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek17(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek18(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek19(),3));
			ga.addCell(TeachCellContentRoman(rs9.getWeek20(),3));		

		for(int i = 0;i<list1.size();i++)
		{
			TrainingEvents rs2 = list1.get(i);
			ga.addCell(TeachCellContentRoman(rs2.getSemester8(),3));
		}
		if(word1.getLearningTime().equals("五年"))
		{
			ga.addCell(TeachCellContentChinese("五",2,1,3));
			ga.addCell(TeachCellContentRoman("9",3));
	
				MainTainOfPT rs10=list.get(8);
				ga.addCell(TeachCellContentRoman(rs10.getWeek1(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek2(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek3(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek4(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek5(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek6(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek7(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek8(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek9(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek10(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek11(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek12(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek13(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek14(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek15(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek16(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek17(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek18(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek19(),3));
				ga.addCell(TeachCellContentRoman(rs10.getWeek20(),3));	

			for(int i = 0;i<list1.size();i++)
			{
				TrainingEvents rs2 = list1.get(i);
				ga.addCell(TeachCellContentRoman(rs2.getSemester9(),3));
			}
			ga.addCell(TeachCellContentRoman("10",3));

				MainTainOfPT rs11=list.get(9);
				ga.addCell(TeachCellContentRoman(rs11.getWeek2(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek3(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek4(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek5(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek6(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek7(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek8(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek9(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek10(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek11(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek12(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek13(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek14(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek15(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek16(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek17(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek18(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek19(),3));
				ga.addCell(TeachCellContentRoman(rs11.getWeek20(),3));	
		
			for(int i = 0;i<list1.size();i++)
			{
				TrainingEvents rs2 = list1.get(i);
				ga.addCell(TeachCellContentRoman(rs2.getSemester10(),3));
			}
		}
		return ga;
	}
	public Table Graducation1(List[][] Gradlist,List<TrainingEvents> professionlist3, List listprac[],PTBasicInformation word1, ScoreThreshold score2, List<List<TheoreticalLesson>> proElec_IS_list, List<PracticeLesson> qibaxueqiList, String pid)throws Exception{
 		Table gg=new Table(10);
		int sumtable=xuenian*2;
		float[] widths4={3,10,13,6,6,6,6,6,6,6,6,6,6,6,8};
		float[] widths5={3,9,13,5,5,5,5,5,5,5,5,5,5,5,5,5,7};
		String[] tablehead = {"一","二","三","四","五","六","七","八","九","十"};
		List<String> department_gonggongxuanxie_ten = new ArrayList<String>(Arrays.asList("0104","1006","1305","0601","0602","0603","0604","0605","0606"));

		if(sumtable == 8)   gg=table(sumtable+7,10,widths4);
		if(sumtable == 10) gg =table(sumtable+7,10,widths5);
		gg.addCell(TeachCellHead("类别",2,3,3));//行，列
		gg.addCell(TeachCellHead("各学期规定学分数",1,sumtable,3));
		gg.addCell(TeachCellHead("最低分毕业要求",1,3,3));
		gg.addCell(TeachCellHead("备注",2,1,3));
		for(int th=0;th<sumtable;th++)
			gg.addCell(TeachCellHead(tablehead[th],1,1,3));
		gg.addCell(TeachCellHead("学时数",1,1,3));
		gg.addCell(TeachCellHead("学分数",1,1,3));
		gg.addCell(TeachCellHead("百分比",1,1,3));
		gg.addCell(TeachCellHead("理\n论\n课",6,1,3));
		gg.addCell(TeachCellHead("公共课平台",2,1,3));
		gg.addCell(TeachCellHead("公共必修课",1,1,3));//用的黑体

		float[] score=new float[sumtable]; 
		float[] sumscore=new float[5];
		float[] period=new float[sumtable];
		float[] sumperiod=new float[5];
		float[] f1=new float[5];
		
		float[] subperiod=new float[sumtable];
		float[] subscore=new float[sumtable];
		float subtotalperiod=0,subtotalscore=0;
		float zhuanyexuanxiukescore =0;
	
		float zhuanyexuanxiuScore=0;//公共选修课学分
		for(int gi=0;gi<2;gi++)//没有专业限选课
		{
			for(int gi1=0;gi1<sumtable;gi1++)
			{
				List<TheoreticalLesson> list6 = Gradlist[gi][gi1];
				for(int gi2=0;gi2<list6.size();gi2++)
				{
					TheoreticalLesson xf=list6.get(gi2);
					score[gi1] +=Float.valueOf(xf.getCurriculum().getCredit());
					if(xf.getCurriculum().getHoursOfALL().equals(""))period[gi1] +=0;
					else period[gi1] +=Float.valueOf(xf.getCurriculum().getHoursOfALL());
					
					if(xf.getCurriculum().getCredit().equals(""))subscore[gi1] += 0;
					else subscore[gi1] +=Float.valueOf(xf.getCurriculum().getCredit());
					
					if(xf.getCurriculum().getHoursOfALL().equals(""))subperiod[gi1] +=0;
					else subperiod[gi1] +=Float.valueOf(xf.getCurriculum().getHoursOfALL());
				}
				gg.addCell(TeachCellContentRoman1(score[gi1],3));
				sumscore[gi] +=score[gi1];
				sumperiod[gi] +=period[gi1];
				score[gi1]=0;
				period[gi1]=0;
			}
			gg.addCell(TeachCellContentRoman1(sumperiod[gi],3));
			BigDecimal sumscore_b = new BigDecimal(sumscore[gi]); 
			float sumscore_b_1 = sumscore_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(sumscore_b_1,3));
			
			float percentage = 0;
			if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
			{
				percentage=(float)(sumscore[gi]/Float.valueOf(score2.getScore()))*100;////计算每个课程性质的学分所占的百分比，score2.getScore() 是总学分
				BigDecimal b = new BigDecimal(percentage); 
				f1[gi] = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				gg.addCell(TeachCellContentRoman1(f1[gi],3));
				gg.addCell(TeachCellContentRoman("",3));
			}
			else
			{
				gg.addCell(TeachCellContentRoman1(0,3));
				gg.addCell(TeachCellContentRoman("",3));
			}

			
			if(gi == 0)
			{
				gg.addCell(TeachCellHead("公共选修课",1,1,3));//
				if(department_gonggongxuanxie_ten.contains(pid))
				{
					zhuanyexuanxiuScore=10;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[0] = subscore[0]+0;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[1] = subscore[1]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[2] = subscore[2]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[3] = subscore[3]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[4] = subscore[4]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[5] = subscore[5]+2;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[6] = subscore[6]+0;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[7] = subscore[7]+0;
				}
				else
				{
					zhuanyexuanxiuScore=14;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[0] = subscore[0]+0;
					gg.addCell(TeachCellContentRoman1(4,3));
					subscore[1] = subscore[1]+4;
					gg.addCell(TeachCellContentRoman1(4,3));
					subscore[2] = subscore[2]+4;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[3] = subscore[3]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[4] = subscore[4]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[5] = subscore[5]+2;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[6] = subscore[6]+0;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[7] = subscore[7]+0;
				}

				if(sumtable == 8)
				{
					gg.addCell(TeachCellContentRoman1(zhuanyexuanxiuScore*16,3));//学时数
					gg.addCell(TeachCellContentRoman1(zhuanyexuanxiuScore,3));//学时数
					float scorexuan = zhuanyexuanxiuScore;
					if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
					{
						percentage=(float)(scorexuan/Float.valueOf(score2.getScore()))*100;////计算学分的所占的百分比 209.5是总学分
						BigDecimal b = new BigDecimal(percentage); 
						float f1_xuan = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
						gg.addCell(TeachCellContentRoman1(f1_xuan,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
					else
					{
						gg.addCell(TeachCellContentRoman1(0,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
				}
				if(sumtable == 10)
				{
					
					gg.addCell(TeachCellContentRoman1(0,3));
					gg.addCell(TeachCellContentRoman1(0,3));
					gg.addCell(TeachCellContentRoman1(zhuanyexuanxiuScore*16,3));//学时数
					gg.addCell(TeachCellContentRoman1(zhuanyexuanxiuScore,3));//学时数
					float scorexuan = zhuanyexuanxiuScore;
					if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
					{
						percentage=(float)(scorexuan/Float.valueOf(score2.getScore()))*100;////计算学分的所占的百分比 209.5是总学分
						BigDecimal b = new BigDecimal(percentage); 
						float f1_xuan = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
						gg.addCell(TeachCellContentRoman1(f1_xuan,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
					else
					{
						gg.addCell(TeachCellContentRoman1(0,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
				}
			}
			
			
			if(gi==1)
			{
				float sumScoreNum =0;
				float qibaxueqiScore=0;
				float zhuanyexuanxiuke1_Score = 0;
				if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
				{
					//float zhuanyexuanxiuScore = 14;//公共选修课
					sumScoreNum = Float.valueOf(score2.getScore());
					float[] pracscore=new float[sumtable];
					float sumpracscore=0;
					//查询实践课总学分，这里包括了学期为8的毕业设计，如果将毕业设计分到第8学期，那么就不用计算7、8学期的毕业设计的学分
					for(int wi2=0;wi2<sumtable;wi2++)
					{
						List <PracticeLesson> praclist = listprac[wi2];
						for(int wi3=0;wi3<praclist.size();wi3++)
						{
							PracticeLesson praclist1 = praclist.get(wi3);
							pracscore[wi2] +=Float.valueOf(praclist1.getCurriculum().getCredit());
						}
						sumpracscore += pracscore[wi2];
					}
					//得到毕业实践课中的7、8学期的毕设学分
					if(qibaxueqiList != null && qibaxueqiList.size() != 0)
					{
						if(qibaxueqiList.get(0).getCurriculum().getCredit() == null && qibaxueqiList.get(0).getCurriculum().getCredit().equals(""))
							qibaxueqiScore=0;
						else
							qibaxueqiScore=Float.valueOf(qibaxueqiList.get(0).getCurriculum().getCredit());
					}
					//每个专业方向的专业选修课模块1 学分
					
					for(int i=0;i<proElec_IS_list.size();i++)
					{
						for(int j=0;j<proElec_IS_list.get(i).size();j++)
						{
							TheoreticalLesson proElec_IS_list_ke = proElec_IS_list.get(i).get(j);
							zhuanyexuanxiuke1_Score +=Float.valueOf(proElec_IS_list_ke.getCurriculum().getCredit());
						}
					}
					sumScoreNum = sumScoreNum -sumscore[0] - sumscore[1] - zhuanyexuanxiuScore - sumpracscore - qibaxueqiScore - zhuanyexuanxiuke1_Score;//专业选修课模块2=专业总学分-公共必选课-专业必选课-公共选修课-实践课-毕业设计-专业选修课模块1
				}
				else sumScoreNum=0;
				
				gg.addCell(TeachCellHead("专业选修课",1,1,3));
				//得到每个专业方向的专业选修课中模块1的个学期学分
				float[] sum_proElec_IS=new float[sumtable];
				for(int i=0;i<proElec_IS_list.size();i++)
				{
					for(int j=0;j<proElec_IS_list.get(i).size();j++)
					{
						TheoreticalLesson proElec_IS_list_ke = proElec_IS_list.get(i).get(j);
						sum_proElec_IS[i] +=Float.valueOf(proElec_IS_list_ke.getCurriculum().getCredit());
					}
					if(proElec_IS_list.size()==8)
					{
						if(i==5)
						{
							BigDecimal bsix = new BigDecimal(sumScoreNum/2); 
							float zhuansix = bsix.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
							sum_proElec_IS[i] += zhuansix;
							gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
						}
						else if(i==6)
						{
							BigDecimal bsix = new BigDecimal(sumScoreNum/2); 
							float zhuansix = bsix.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
							sum_proElec_IS[i] += zhuansix;
							gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
						}
						else gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
					}
					if(proElec_IS_list.size()==10)
					{
						if(i==7)
						{
							BigDecimal bsix = new BigDecimal(sumScoreNum/2); 
							float zhuansix = bsix.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
							sum_proElec_IS[i] += zhuansix;
							gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
						}
						else if(i==8)
						{
							BigDecimal bsix = new BigDecimal(sumScoreNum/2); 
							float zhuansix = bsix.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
							sum_proElec_IS[i] += zhuansix;
							gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
						}
						else gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
					}

				}
				float Sum_ProElec = 0;
				for(int i=0;i<sum_proElec_IS.length;i++)
				{
					Sum_ProElec += sum_proElec_IS[i];
					subscore[i] = subscore[i]+sum_proElec_IS[i];
				}

				gg.addCell(TeachCellContentRoman1(Sum_ProElec*16,3));//学时数
				gg.addCell(TeachCellContentRoman1(Sum_ProElec,3));//学分数
				float scorexuan = Sum_ProElec;
				if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
				{
					percentage=(float)(scorexuan/Float.valueOf(score2.getScore()))*100;////计算学分的所占的百分比 209.5是总学分
					BigDecimal b = new BigDecimal(percentage); 
					float f1_xuan = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
					gg.addCell(TeachCellContentRoman1(f1_xuan,3));
					gg.addCell(TeachCellContentRoman("",3));
				}
				else
				{
				
					gg.addCell(TeachCellContentRoman1(0,3));
					gg.addCell(TeachCellContentRoman("",3));
				}

				zhuanyexuanxiukescore =sumScoreNum+zhuanyexuanxiuke1_Score;
		}
			if(gi==0)
			{
				gg.addCell(TeachCellHead("专业课平台",2,1,3));
				gg.addCell(TeachCellHead("专业必修课",1,1,3));
			}
		

		}
		
		
		
		gg.addCell(TeachCellHead("小计",1,2,3));
		for(int ti=0;ti<sumtable;ti++)
		{
			BigDecimal bi = new BigDecimal(subscore[ti]); 
			float f1i = bi.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(f1i,3));
		}
		for(int ti1=0;ti1<5;ti1++)//需要改
		{
			subtotalscore += sumscore[ti1];
			subtotalperiod +=sumperiod[ti1];
		}
		subtotalscore = subtotalscore + zhuanyexuanxiuScore+zhuanyexuanxiukescore ;//计算小计中的总学分和总学时 加上选修课的学分
		subtotalperiod = subtotalperiod + zhuanyexuanxiuScore*16+zhuanyexuanxiukescore*16;
		BigDecimal biii = new BigDecimal(subtotalperiod); 
		float fiii = biii.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		gg.addCell(TeachCellContentRoman1(fiii,3));
		
		/*******将subtotalscore保留两位******/
		BigDecimal bi12 = new BigDecimal(subtotalscore); 
		float f12i = bi12.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		gg.addCell(TeachCellContentRoman1(f12i,3));
		
		float f2 = 0;
		if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
		{
			float percentage1=(float)(subtotalscore/Float.valueOf(score2.getScore()))*100;//学分占总学分的百分比
			BigDecimal b = new BigDecimal(percentage1); 
			f2 = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(f2,3));
			gg.addCell(TeachCellContentRoman("",3));
		}
		else 
		{
			gg.addCell(TeachCellContentRoman1(0,3));
			gg.addCell(TeachCellContentRoman("",3));
		}
		
		gg.addCell(TeachCellHead("周学时",1,2,3));
		float[] wcount=new float[sumtable];
		if(professionlist3 != null && professionlist3.size() !=0)
		{
			TrainingEvents week=professionlist3.get(0);
			if(sumtable==8)
			{
				if(week.getSemester1() != null && !week.getSemester1().equals(""))
					wcount[0]=Integer.valueOf(week.getSemester1());
				else wcount[0]=0;
				if(week.getSemester2() != null && !week.getSemester2().equals(""))
					wcount[1]=Integer.valueOf(week.getSemester2());
				else wcount[1]=0;
				if(week.getSemester3() != null && !week.getSemester3().equals(""))
					wcount[2]=Integer.valueOf(week.getSemester3());
				else wcount[2]=0;
				if(week.getSemester4() != null && !week.getSemester4().equals(""))
					wcount[3]=Integer.valueOf(week.getSemester4());
				else wcount[3]=0;
				if(week.getSemester5() != null && !week.getSemester5().equals(""))
					wcount[4]=Integer.valueOf(week.getSemester5());
				else wcount[4]=0;
				if(week.getSemester6() != null && !week.getSemester6().equals(""))
					wcount[5]=Integer.valueOf(week.getSemester6());
				else wcount[5]=0;
				if(week.getSemester7() != null && !week.getSemester7().equals(""))
					wcount[6]=Integer.valueOf(week.getSemester7());
				else wcount[6]=0;
				if(week.getSemester8() != null && !week.getSemester8().equals(""))
					wcount[7]=Integer.valueOf(week.getSemester8());
				else wcount[7]=0;
			}
			if(sumtable==10)
			{
				if(week.getSemester1() != null && !week.getSemester1().equals(""))
					wcount[0]=Integer.valueOf(week.getSemester1());
				else wcount[0]=0;
				if(week.getSemester2() != null && !week.getSemester2().equals(""))
					wcount[1]=Integer.valueOf(week.getSemester2());
				else wcount[1]=0;
				if(week.getSemester3() != null && !week.getSemester3().equals(""))
					wcount[2]=Integer.valueOf(week.getSemester3());
				else wcount[2]=0;
				if(week.getSemester4() != null && !week.getSemester4().equals(""))
					wcount[3]=Integer.valueOf(week.getSemester4());
				else wcount[3]=0;
				if(week.getSemester5() != null && !week.getSemester5().equals(""))
					wcount[4]=Integer.valueOf(week.getSemester5());
				else wcount[4]=0;
				if(week.getSemester6() != null && !week.getSemester6().equals(""))
					wcount[5]=Integer.valueOf(week.getSemester6());
				else wcount[5]=0;
				if(week.getSemester7() != null && !week.getSemester7().equals(""))
					wcount[6]=Integer.valueOf(week.getSemester7());
				else wcount[6]=0;
				if(week.getSemester8() != null && !week.getSemester8().equals(""))
					wcount[7]=Integer.valueOf(week.getSemester8());
				else wcount[7]=0;
				if(week.getSemester9() != null && !week.getSemester9().equals(""))
					wcount[8]=Integer.valueOf(week.getSemester9());
				else wcount[8]=0;
				if(week.getSemester10() != null && !week.getSemester10().equals(""))
					wcount[9]=Integer.valueOf(week.getSemester10());
				else wcount[9]=0;
			}
		}
		
	
		for(int wi=0;wi<sumtable;wi++)
		{
			
			if(wcount[wi] !=0)
			{
				float weekhour =(float)subscore[wi]*16/wcount[wi];//计算周学时
				BigDecimal wb = new BigDecimal(weekhour); 
				float f3 = wb.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				gg.addCell(TeachCellContentRoman1(f3,3));	
			}
			else gg.addCell(TeachCellContentRoman1(0,3));
		}
		for(int wi1=0;wi1<4;wi1++)
			gg.addCell(TeachCellContentRoman("",3));
	
		gg.addCell(TeachCellHead("集中实践性环节",1,3,3));
		float[] pracscore=new float[sumtable];
		float[] pracpperiod=new float[sumtable];
		float sumpracscore=0,sumpracpperiod=0;
		for(int wi2=0;wi2<sumtable;wi2++)
		{
			List <PracticeLesson> praclist = listprac[wi2];
			for(int wi3=0;wi3<praclist.size();wi3++)
			{
				PracticeLesson praclist1 = praclist.get(wi3);
				pracscore[wi2] +=Float.valueOf(praclist1.getCurriculum().getCredit());
				if(praclist1.getCurriculum().getHoursOfALL().equals("")) pracpperiod[wi2] +=0;
				else pracpperiod[wi2] +=Integer.valueOf(praclist1.getCurriculum().getHoursOfALL());
			}
			
			BigDecimal PracS = new BigDecimal(pracscore[wi2]); 
			float PracSco = PracS.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			if(qibaxueqiList != null && qibaxueqiList.size() != 0)
			{
				float scoreqibaxueqi = Float.valueOf(qibaxueqiList.get(0).getCurriculum().getCredit());
				if(wi2==sumtable-2 && scoreqibaxueqi == 17)
				{
					pracscore[wi2] = pracscore[wi2]+2;
					pracscore[sumtable-1]=15;
					gg.addCell(TeachCellContentRoman1(PracSco+2,3));//每个学期的集中实践课
				}
				else if(wi2==sumtable-2 && scoreqibaxueqi < 17)
				{
					pracscore[sumtable-1]=scoreqibaxueqi;
					gg.addCell(TeachCellContentRoman1(PracSco,3));
				}
				else gg.addCell(TeachCellContentRoman1(PracSco,3));//每个学期的集中实践课
			}
			else
			{
				gg.addCell(TeachCellContentRoman1(PracSco,3));//每个学期的集中实践课
			}
			sumpracscore += pracscore[wi2];
			sumpracpperiod += pracpperiod[wi2];
		}
		
		gg.addCell(TeachCellContentRoman("",3));
		gg.addCell(TeachCellContentRoman1(sumpracscore,3));
		float f4 = 0 ;
		if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
		{
			float pracpercentage =(float)(sumpracscore/ Float.valueOf(score2.getScore()))*100;//集中实践课所占的百分比
			BigDecimal pb = new BigDecimal(pracpercentage); 
			f4 = pb.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(f4,3));
			gg.addCell(TeachCellContentRoman("",3));
		}
		else
		{
			gg.addCell(TeachCellContentRoman("0",3));
			gg.addCell(TeachCellContentRoman("",3));
		}
		
		
		gg.addCell(TeachCellHead("累计",1,3,3));
		float totalscore=0,totalperiod=0;
		float[] total=new float[sumtable];
		for(int tj=0;tj<sumtable;tj++)
		{
			total[tj]=subscore[tj]+pracscore[tj];
			BigDecimal bj = new BigDecimal(total[tj]); 
			float f1j = bj.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(f1j,3));
		}
		totalperiod = subtotalperiod;
		totalscore = subtotalscore +sumpracscore;
		gg.addCell(TeachCellContentRoman1(totalperiod,3));
		
		BigDecimal bt = new BigDecimal(totalscore); 
		float f1t = bt.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		gg.addCell(TeachCellContentRoman1(f1t,3));
		BigDecimal tb = new BigDecimal((f2+f4)); 
		float f5 = tb.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();	
		gg.addCell(TeachCellContentRoman1(f5,3));
		gg.addCell(TeachCellContentRoman("",3));
		return gg;
	
}
	public Table Graducation2(List[][][] listbgs,List<TrainingEvents> professionlist3, List listprac[][],PTBasicInformation word1, ScoreThreshold score2, List<List<TheoreticalLesson>> proElec_IS_list, List<PracticeLesson> qibaxueqiList, String pid)throws Exception{
		int sumtable=xuenian*2;
		float[] widths4={3,10,13,6,6,6,6,6,6,6,6,6,6,6,8};
		float[] widths5={3,9,13,5,5,5,5,5,5,5,5,5,5,5,5,5,7};
		String[] tablehead = {"一","二","三","四","五","六","七","八","九","十"};
		Table gg=new Table(10);
		if(sumtable == 8)   gg=table(sumtable+7,10,widths4);
		if(sumtable == 10) gg =table(sumtable+7,10,widths5);
		gg.addCell(TeachCellHead("类别",2,3,3));//行，列
		gg.addCell(TeachCellHead("各学期规定学分数",1,sumtable,3));
		gg.addCell(TeachCellHead("最低分毕业要求",1,3,3));
		gg.addCell(TeachCellHead("备注",2,1,3));
		for(int th=0;th<sumtable;th++)
			gg.addCell(TeachCellHead(tablehead[th],1,1,3));
		gg.addCell(TeachCellHead("学时数",1,1,3));
		gg.addCell(TeachCellHead("学分数",1,1,3));
		gg.addCell(TeachCellHead("百分比",1,1,3));
		gg.addCell(TeachCellHead("理\n论\n课",6,1,3));
		gg.addCell(TeachCellHead("公共课平台",2,1,3));
		gg.addCell(TeachCellHead("公共必修课",1,1,3));//用的黑体
		
		List<String> department_gonggongxuanxie_ten = new ArrayList<String>(Arrays.asList("0104","1006","1305","0601","0602","0603","0604","0605","0606"));
		float zhuanyexuanxiuScore=0;//公共选修课学分
		
		float[] score=new float[sumtable]; 
		float[] sumscore=new float[5];
		float[] period=new float[sumtable];
		float[] sumperiod=new float[5];
		float[] f1=new float[5];
		
		float[] subperiod=new float[sumtable];
		float[] subscore=new float[sumtable];
		float subtotalperiod=0,subtotalscore=0;
		float zhuanyexuanxiukescore=0;
		for(int gi=0;gi<2;gi++)//
		{
			for(int gi1=0;gi1<sumtable;gi1++)
			{
				List<TheoreticalLesson> list6 = listbgs[gi][gi1][0];
				for(int gi2=0;gi2<list6.size();gi2++)
				{
					TheoreticalLesson xf=list6.get(gi2);
					score[gi1] +=Float.valueOf(xf.getCurriculum().getCredit());
					if(xf.getCurriculum().getHoursOfALL().equals(""))period[gi1] +=0;
					else period[gi1] +=Float.valueOf(xf.getCurriculum().getHoursOfALL());
					
					if(xf.getCurriculum().getCredit().equals(""))subscore[gi1] += 0;
					else subscore[gi1] +=Float.valueOf(xf.getCurriculum().getCredit());
					
					if(xf.getCurriculum().getHoursOfALL().equals(""))subperiod[gi1] +=0;
					else subperiod[gi1] +=Float.valueOf(xf.getCurriculum().getHoursOfALL());
				}
				gg.addCell(TeachCellContentRoman1(score[gi1],3));
				sumscore[gi] +=score[gi1];
				sumperiod[gi] +=period[gi1];
				score[gi1]=0;
				period[gi1]=0;
			}
			gg.addCell(TeachCellContentRoman1(sumperiod[gi],3));
			BigDecimal sumscore_b = new BigDecimal(sumscore[gi]); 
			float sumscore_b_1 = sumscore_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(sumscore_b_1,3));
			
			/*****************************计算学分占总学分的百分比********************************************/
			float percentage = 0;
			if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
			{
				percentage=(float)(sumscore[gi]/Float.valueOf(score2.getScore()))*100;////计算学分的所占的百分比 209.5是总学分
				BigDecimal b = new BigDecimal(percentage); 
				f1[0] = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				gg.addCell(TeachCellContentRoman1(f1[0],3));
				gg.addCell(TeachCellContentRoman("",3));
			}
			else
			{
				gg.addCell(TeachCellContentRoman1(0,3));
				gg.addCell(TeachCellContentRoman("",3));
			}
			if(gi == 0)
			{
				gg.addCell(TeachCellHead("公共选修课",1,1,3));//
				if(department_gonggongxuanxie_ten.contains(pid))
				{
					zhuanyexuanxiuScore=10;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[0] = subscore[0]+0;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[1] = subscore[1]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[2] = subscore[2]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[3] = subscore[3]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[4] = subscore[4]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[5] = subscore[5]+2;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[6] = subscore[6]+0;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[7] = subscore[7]+0;
				}
				else
				{
					zhuanyexuanxiuScore=14;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[0] = subscore[0]+0;
					gg.addCell(TeachCellContentRoman1(4,3));
					subscore[1] = subscore[1]+4;
					gg.addCell(TeachCellContentRoman1(4,3));
					subscore[2] = subscore[2]+4;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[3] = subscore[3]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[4] = subscore[4]+2;
					gg.addCell(TeachCellContentRoman1(2,3));
					subscore[5] = subscore[5]+2;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[6] = subscore[6]+0;
					gg.addCell(TeachCellContentRoman1(0,3));
					subscore[7] = subscore[7]+0;
				}
				if(sumtable == 8)
				{
					gg.addCell(TeachCellContentRoman1(zhuanyexuanxiuScore*16,3));//学时数
					gg.addCell(TeachCellContentRoman1(zhuanyexuanxiuScore,3));//学时数
					float scorexuan = zhuanyexuanxiuScore;
					if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
					{
						percentage=(float)(scorexuan/Float.valueOf(score2.getScore()))*100;////计算学分的所占的百分比 209.5是总学分
						BigDecimal b = new BigDecimal(percentage); 
						float f1_xuan = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
						gg.addCell(TeachCellContentRoman1(f1_xuan,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
					else
					{
						gg.addCell(TeachCellContentRoman1(0,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
				}
				if(sumtable == 10)
				{
					
					gg.addCell(TeachCellContentRoman1(0,3));
					gg.addCell(TeachCellContentRoman1(0,3));
					gg.addCell(TeachCellContentRoman1(zhuanyexuanxiuScore*16,3));//学时数
					gg.addCell(TeachCellContentRoman1(zhuanyexuanxiuScore,3));//学时数
					float scorexuan = zhuanyexuanxiuScore;
					if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
					{
						percentage=(float)(scorexuan/Float.valueOf(score2.getScore()))*100;////计算学分的所占的百分比 209.5是总学分
						BigDecimal b = new BigDecimal(percentage); 
						float f1_xuan = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
						gg.addCell(TeachCellContentRoman1(f1_xuan,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
					else
					{
						gg.addCell(TeachCellContentRoman1(0,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
				}
			}
			
			if(gi==1)
			{
				float sumScoreNum = 0;
				float qibaxueqiScore=0;
				float zhuanyexuanxiuke1_Score = 0;
				if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
				{
					sumScoreNum = Float.valueOf(score2.getScore());
					float[] pracscore=new float[sumtable];
					float sumpracscore=0;
					for(int wi2=0;wi2<sumtable;wi2++)
					{
						List <PracticeLesson> praclist = listprac[wi2][0];
						for(int wi3=0;wi3<praclist.size();wi3++)
						{
							PracticeLesson praclist1 = praclist.get(wi3);
							pracscore[wi2] +=Float.valueOf(praclist1.getCurriculum().getCredit());
						}
						sumpracscore += pracscore[wi2];
					}
					//得到毕业实践课中的7、8学期的毕设学分
					if(qibaxueqiList != null && qibaxueqiList.size() != 0)
					{
						if(qibaxueqiList.get(0).getCurriculum().getCredit() == null && qibaxueqiList.get(0).getCurriculum().getCredit().equals(""))
							qibaxueqiScore=0;
						else
							qibaxueqiScore=Float.valueOf(qibaxueqiList.get(0).getCurriculum().getCredit());
					}
					//每个专业方向的专业选修课模块1 学分
					
					for(int i=0;i<proElec_IS_list.size();i++)
					{
						for(int j=0;j<proElec_IS_list.get(i).size();j++)
						{
							TheoreticalLesson proElec_IS_list_ke = proElec_IS_list.get(i).get(j);
							zhuanyexuanxiuke1_Score +=Float.valueOf(proElec_IS_list_ke.getCurriculum().getCredit());
						}
					}
					sumScoreNum = sumScoreNum -sumscore[0] - sumscore[1] - zhuanyexuanxiuScore - sumpracscore - qibaxueqiScore - zhuanyexuanxiuke1_Score;//专业选修课模块2=专业总学分-公共必选课-专业必选课-公共选修课-实践课-毕业设计-专业选修课模块1
				}
				else sumScoreNum=0;
					gg.addCell(TeachCellHead("专业选修课",1,1,3));
					//得到每个专业方向的专业选修课中模块1的个学期学分
					float[] sum_proElec_IS=new float[sumtable];
					for(int i=0;i<proElec_IS_list.size();i++)
					{
						for(int j=0;j<proElec_IS_list.get(i).size();j++)
						{
							TheoreticalLesson proElec_IS_list_ke = proElec_IS_list.get(i).get(j);
							sum_proElec_IS[i] +=Float.valueOf(proElec_IS_list_ke.getCurriculum().getCredit());
						}
						if(proElec_IS_list.size()==8)
						{
							if(i==5)
							{
								BigDecimal bsix = new BigDecimal(sumScoreNum/2); 
								float zhuansix = bsix.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
								sum_proElec_IS[i] += zhuansix;
								gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
							}
							else if(i==6)
							{
								BigDecimal bsix = new BigDecimal(sumScoreNum/2); 
								float zhuansix = bsix.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
								sum_proElec_IS[i] += zhuansix;
								gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
							}
							else gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
						}
						if(proElec_IS_list.size()==10)
						{
							if(i==7)
							{
								BigDecimal bsix = new BigDecimal(sumScoreNum/2); 
								float zhuansix = bsix.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
								sum_proElec_IS[i] += zhuansix;
								gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
							}
							else if(i==8)
							{
								BigDecimal bsix = new BigDecimal(sumScoreNum/2); 
								float zhuansix = bsix.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
								sum_proElec_IS[i] += zhuansix;
								gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
							}
							else gg.addCell(TeachCellContentRoman1(sum_proElec_IS[i],3));
						}

					}
					float Sum_ProElec = 0;
					for(int i=0;i<sum_proElec_IS.length;i++)
					{
						Sum_ProElec += sum_proElec_IS[i];
						subscore[i] = subscore[i]+sum_proElec_IS[i];
					}
						
					gg.addCell(TeachCellContentRoman1(Sum_ProElec*16,3));//学时数
					gg.addCell(TeachCellContentRoman1(Sum_ProElec,3));//学分数
					float scorexuan = Sum_ProElec;
					if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
					{
						percentage=(float)(scorexuan/Float.valueOf(score2.getScore()))*100;////计算学分的所占的百分比 209.5是总学分
						BigDecimal b = new BigDecimal(percentage); 
						float f1_xuan = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
						gg.addCell(TeachCellContentRoman1(f1_xuan,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
					else
					{
					
						gg.addCell(TeachCellContentRoman1(0,3));
						gg.addCell(TeachCellContentRoman("",3));
					}
					
				zhuanyexuanxiukescore =sumScoreNum+zhuanyexuanxiuke1_Score;
			}
				if(gi==0)
				{
					gg.addCell(TeachCellHead("专业课平台",2,1,3));
					gg.addCell(TeachCellHead("专业必修课",1,1,3));
				}
			

				
				
		}
		gg.addCell(TeachCellHead("小计",1,2,3));
		for(int ti=0;ti<sumtable;ti++)
		{
			BigDecimal bi = new BigDecimal(subscore[ti]); 
			float f1i = bi.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(f1i,3));
			
		}

		
		for(int ti1=0;ti1<5;ti1++)//需要改
		{
			subtotalscore += sumscore[ti1];
			subtotalperiod +=sumperiod[ti1];
		}
		subtotalscore = subtotalscore + zhuanyexuanxiuScore+zhuanyexuanxiukescore;//计算小计中的总学分和总学时 加上选修课的学分
		subtotalperiod = subtotalperiod + zhuanyexuanxiuScore*16+zhuanyexuanxiukescore*16;
		BigDecimal bi = new BigDecimal(subtotalperiod); 
		float f = bi.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		gg.addCell(TeachCellContentRoman1(f,3));
		
		BigDecimal bi12 = new BigDecimal(subtotalscore); 
		float f12i = bi12.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		gg.addCell(TeachCellContentRoman1(f12i,3));
		
		float f2 = 0;
		if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
		{
			float percentage1=(float)(subtotalscore/Integer.valueOf(score2.getScore()))*100;//小计总学占总学分的百分比
			BigDecimal b = new BigDecimal(percentage1); 
			f2 = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(f2,3));
			gg.addCell(TeachCellContentRoman("",3));
		}
		else
		{
			gg.addCell(TeachCellContentRoman1(0,3));
			gg.addCell(TeachCellContentRoman("",3));
		}
		/*计算周学时*/
		gg.addCell(TeachCellHead("周学时",1,2,3));
		float[] wcount=new float[sumtable];
		TrainingEvents week=professionlist3.get(0);
		if(sumtable==8)
		{
			if(week.getSemester1() != null && !week.getSemester1().equals(""))
				wcount[0]=Integer.valueOf(week.getSemester1());
			else wcount[0]=0;
			if(week.getSemester2() != null && !week.getSemester2().equals(""))
				wcount[1]=Integer.valueOf(week.getSemester2());
			else wcount[1]=0;
			if(week.getSemester3() != null && !week.getSemester3().equals(""))
				wcount[2]=Integer.valueOf(week.getSemester3());
			else wcount[2]=0;
			if(week.getSemester4() != null && !week.getSemester4().equals(""))
				wcount[3]=Integer.valueOf(week.getSemester4());
			else wcount[3]=0;
			if(week.getSemester5() != null && !week.getSemester5().equals(""))
				wcount[4]=Integer.valueOf(week.getSemester5());
			else wcount[4]=0;
			if(week.getSemester6() != null && !week.getSemester6().equals(""))
				wcount[5]=Integer.valueOf(week.getSemester6());
			else wcount[5]=0;
			if(week.getSemester7() != null && !week.getSemester7().equals(""))
				wcount[6]=Integer.valueOf(week.getSemester7());
			else wcount[6]=0;
			if(week.getSemester8() != null && !week.getSemester8().equals(""))
				wcount[7]=Integer.valueOf(week.getSemester8());
			else wcount[7]=0;
		}
		if(sumtable==10)
		{
			if(week.getSemester1() != null && !week.getSemester1().equals(""))
				wcount[0]=Integer.valueOf(week.getSemester1());
			else wcount[0]=0;
			if(week.getSemester2() != null && !week.getSemester2().equals(""))
				wcount[1]=Integer.valueOf(week.getSemester2());
			else wcount[1]=0;
			if(week.getSemester3() != null && !week.getSemester3().equals(""))
				wcount[2]=Integer.valueOf(week.getSemester3());
			else wcount[2]=0;
			if(week.getSemester4() != null && !week.getSemester4().equals(""))
				wcount[3]=Integer.valueOf(week.getSemester4());
			else wcount[3]=0;
			if(week.getSemester5() != null && !week.getSemester5().equals(""))
				wcount[4]=Integer.valueOf(week.getSemester5());
			else wcount[4]=0;
			if(week.getSemester6() != null && !week.getSemester6().equals(""))
				wcount[5]=Integer.valueOf(week.getSemester6());
			else wcount[5]=0;
			if(week.getSemester7() != null && !week.getSemester7().equals(""))
				wcount[6]=Integer.valueOf(week.getSemester7());
			else wcount[6]=0;
			if(week.getSemester8() != null && !week.getSemester8().equals(""))
				wcount[7]=Integer.valueOf(week.getSemester8());
			else wcount[7]=0;
			if(week.getSemester9() != null && !week.getSemester9().equals(""))
				wcount[8]=Integer.valueOf(week.getSemester9());
			else wcount[8]=0;
			if(week.getSemester10() != null && !week.getSemester10().equals(""))
				wcount[9]=Integer.valueOf(week.getSemester10());
			else wcount[9]=0;
		}
	
		for(int wi=0;wi<sumtable;wi++)
		{
			
			if(wcount[wi] !=0)
			{
				float weekhour =(float)subscore[wi]*16/wcount[wi];
				BigDecimal wb = new BigDecimal(weekhour); 
				float f3 = wb.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				gg.addCell(TeachCellContentRoman1(f3,3));	
			}
			else gg.addCell(TeachCellContentRoman1(0,3));
		}
		for(int wi1=0;wi1<4;wi1++)
			gg.addCell(TeachCellContentRoman("",3));
		/* 计算集中实践环节的学分和学时*/
		gg.addCell(TeachCellHead("集中实践性环节",1,3,3));
		float[] pracscore=new float[sumtable];
		float[] pracpperiod=new float[sumtable];
		float sumpracscore=0,sumpracpperiod=0;
		for(int wi2=0;wi2<sumtable;wi2++)
		{
			List <PracticeLesson> praclist = listprac[wi2][0];
			for(int wi3=0;wi3<praclist.size();wi3++)
			{
				PracticeLesson praclist1 = praclist.get(wi3);
				pracscore[wi2] +=Float.valueOf(praclist1.getCurriculum().getCredit());
				if(praclist1.getCurriculum().getHoursOfALL().equals("")) pracpperiod[wi2] +=0;
				else pracpperiod[wi2] +=Integer.valueOf(praclist1.getCurriculum().getHoursOfALL());
			}
			
			BigDecimal PracS = new BigDecimal(pracscore[wi2]); 
			float PracSco = PracS.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			if(qibaxueqiList != null && qibaxueqiList.size() != 0)
			{
				float scoreqibaxueqi = Float.valueOf(qibaxueqiList.get(0).getCurriculum().getCredit());
				if(wi2==sumtable-2 && scoreqibaxueqi == 17)
				{
					pracscore[wi2] = pracscore[wi2]+2;
					pracscore[sumtable-1]=15;
					gg.addCell(TeachCellContentRoman1(PracSco+2,3));//每个学期的集中实践课
				}
				else if(wi2==sumtable-2 && scoreqibaxueqi < 17)
				{
					pracscore[sumtable-1]=scoreqibaxueqi;
					gg.addCell(TeachCellContentRoman1(PracSco,3));//每个学期的集中实践课
				}
				else gg.addCell(TeachCellContentRoman1(PracSco,3));//每个学期的集中实践课
			}
			else
			{
				gg.addCell(TeachCellContentRoman1(PracSco,3));//每个学期的集中实践课
			}
			sumpracscore += pracscore[wi2];
			sumpracpperiod += pracpperiod[wi2];
		}
		
		gg.addCell(TeachCellContentRoman("",3));
		gg.addCell(TeachCellContentRoman1(sumpracscore,3));
		float f4=0;
		if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
		{
			float pracpercentage =(float)(sumpracscore/Float.valueOf(score2.getScore()))*100;
			BigDecimal pb = new BigDecimal(pracpercentage); 
			f4 = pb.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(f4,3));
			gg.addCell(TeachCellContentRoman("",3));
		}
		else{
			gg.addCell(TeachCellContentRoman1(0,3));
			gg.addCell(TeachCellContentRoman("",3));
		}
		
		/*计算累计*/
		gg.addCell(TeachCellHead("累计",1,3,3));
		float totalscore=0,totalperiod=0;
		float[] total=new float[sumtable];
		for(int tj=0;tj<sumtable;tj++)
		{
			total[tj]=subscore[tj]+pracscore[tj];
			BigDecimal bj = new BigDecimal(total[tj]); 
			float f1j = bj.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			gg.addCell(TeachCellContentRoman1(f1j,3));
		}
		totalperiod = subtotalperiod;
		totalscore = subtotalscore +sumpracscore;
		BigDecimal bt2 = new BigDecimal(totalperiod); 
		float f1t2 = bt2.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		gg.addCell(TeachCellContentRoman1(f1t2,3));
		
		BigDecimal bt = new BigDecimal(totalscore); 
		float f1t = bt.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		gg.addCell(TeachCellContentRoman1(f1t,3));
		BigDecimal tb = new BigDecimal((f2+f4)); 
		float f5 = tb.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();	
		gg.addCell(TeachCellContentRoman1(f5,3));
		gg.addCell(TeachCellContentRoman("",3));
		
		return gg;

}
 	public Paragraph TrainingInstruction2(List[][][] professionlessonlist,List<TrainingEvents> professionlist3, List[][] professionpracticelist,PTBasicInformation word1,List<TheoreticalLesson> professionlist4, List<TheoreticalLesson> xueweikelist, List<PracticeLesson> xueweike_shijian, List<TheoreticalLesson> listprofessional, ScoreThreshold score2, List<List<TheoreticalLesson>> proElec_IS_list, List<PracticeLesson> qibaxueqiList, String pid) throws Exception {
		
		int sumtable=xuenian*2;
		float[] score=new float[sumtable]; 
		float[] sumscore=new float[5];
		float[] period=new float[sumtable];
		float[] sumperiod=new float[5];
		float[] f1=new float[5];
		
		float[] subperiod=new float[sumtable];
		float[] subscore=new float[sumtable];
		float subtotalperiod=0,subtotalscore=0;

		float f1_gongxuan = 0,f1_zhuanxuan = 0,sumScoreNum=0;
		float zhuanyexuanxiukescore = 0;
		float zhuanyexuanxiuScore = 0;//公共选修课
		List<String> department_gonggongxuanxie_ten = new ArrayList<String>(Arrays.asList("0104","1006","1305","0601","0602","0603","0604","0605","0606"));
		if(department_gonggongxuanxie_ten.contains(pid))
			zhuanyexuanxiuScore = 10;
		else
			zhuanyexuanxiuScore = 14;
		//各学期
		for(int gi=0;gi<2;gi++)
		{
			for(int gi1=0;gi1<sumtable;gi1++)
			{
				List<TheoreticalLesson> list6 = professionlessonlist[gi][gi1][0];
				for(int gi2=0;gi2<list6.size();gi2++)
				{
					TheoreticalLesson xf=list6.get(gi2);
					score[gi1] +=Float.valueOf(xf.getCurriculum().getCredit());
					if(xf.getCurriculum().getHoursOfALL().equals(""))period[gi1] +=0;
					else period[gi1] +=Float.valueOf(xf.getCurriculum().getHoursOfALL());
					
					if(xf.getCurriculum().getCredit().equals(""))subscore[gi1] += 0;
					else subscore[gi1] +=Float.valueOf(xf.getCurriculum().getCredit());
					
					if(xf.getCurriculum().getHoursOfALL().equals(""))subperiod[gi1] +=0;
					else subperiod[gi1] +=Float.valueOf(xf.getCurriculum().getHoursOfALL());
				}
				sumscore[gi] +=score[gi1];
				sumperiod[gi] +=period[gi1];
				score[gi1]=0;
				period[gi1]=0;
			}
			float percentage = 0;
			//计算学分的所占的百分比
			if(score2 != null && score2.getScore() != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
			{
				percentage=(float)(sumscore[gi]/Float.valueOf(score2.getScore()))*100;
				BigDecimal b = new BigDecimal(percentage); 
				f1[gi] = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			}
			else percentage=0;
			
			//计算专业选修课
			if(gi==1)
			{
				float qibaxueqiScore=0;
				float zhuanyexuanxiuke1_Score = 0;
				if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
				{
					sumScoreNum = Float.valueOf(score2.getScore());
					float[] pracscore=new float[sumtable];
					float sumpracscore=0;
					for(int wi2=0;wi2<sumtable;wi2++)
					{
						List <PracticeLesson> praclist = professionpracticelist[wi2][0];
						for(int wi3=0;wi3<praclist.size();wi3++)
						{
							PracticeLesson praclist1 = praclist.get(wi3);
							pracscore[wi2] +=Float.valueOf(praclist1.getCurriculum().getCredit());
						}
						sumpracscore += pracscore[wi2];
					}
					//得到毕业实践课中的7、8学期的毕设学分
					if(qibaxueqiList != null && qibaxueqiList.size() != 0)
					{
						if(qibaxueqiList.get(0).getCurriculum().getCredit() == null && qibaxueqiList.get(0).getCurriculum().getCredit().equals(""))
							qibaxueqiScore=0;
						else
							qibaxueqiScore=Float.valueOf(qibaxueqiList.get(0).getCurriculum().getCredit());
					}
					//每个专业方向的专业选修课模块1 学分
					
					for(int i=0;i<proElec_IS_list.size();i++)
					{
						for(int j=0;j<proElec_IS_list.get(i).size();j++)
						{
							TheoreticalLesson proElec_IS_list_ke = proElec_IS_list.get(i).get(j);
							zhuanyexuanxiuke1_Score +=Float.valueOf(proElec_IS_list_ke.getCurriculum().getCredit());
						}
					}
					//计算得到专业选修课模块1的课的学分
					sumScoreNum = sumScoreNum -sumscore[0] - sumscore[1] - zhuanyexuanxiuScore - sumpracscore - qibaxueqiScore - zhuanyexuanxiuke1_Score;//专业选修课模块2=专业总学分-公共必选课-专业必选课-公共选修课-实践课-毕业设计-专业选修课模块1
					BigDecimal bfive = new BigDecimal(sumScoreNum); 
					float zhuan1 = bfive.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
					sumScoreNum = zhuan1;
				}
				else sumScoreNum =0;
				zhuanyexuanxiukescore=sumScoreNum+zhuanyexuanxiuke1_Score;
			}


		}
		//小计
		for(int ti1=0;ti1<4;ti1++)
		{
			subtotalscore += sumscore[ti1];
			subtotalperiod +=sumperiod[ti1];
		}
		subtotalscore = subtotalscore + zhuanyexuanxiuScore+zhuanyexuanxiukescore;//计算小计中的总学分和总学时 加上选修课的学分
		subtotalperiod = subtotalperiod + zhuanyexuanxiuScore*16+zhuanyexuanxiukescore*16;
		
		//理论课占总学分的百分比
		float f2=0;
		if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
		{
			float percentage1=(float)(subtotalscore/Float.valueOf(score2.getScore()))*100;
			BigDecimal b = new BigDecimal(percentage1); 
			f2 = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		}
		else f2 = 0;
			
		//集中实践
		float[] pracscore=new float[sumtable];
		float[] pracpperiod=new float[sumtable];
		float sumpracscore=0,sumpracpperiod=0;
		for(int wi2=0;wi2<sumtable;wi2++)
		{
			List <PracticeLesson> praclist = professionpracticelist[wi2][0];
			for(int wi3=0;wi3<praclist.size();wi3++)
			{
				PracticeLesson praclist1 = praclist.get(wi3);
				pracscore[wi2] +=Float.valueOf(praclist1.getCurriculum().getCredit());
				if(praclist1.getCurriculum().getHoursOfALL().equals("")) pracpperiod[wi2] +=0;
				else pracpperiod[wi2] +=Integer.valueOf(praclist1.getCurriculum().getHoursOfALL());
			}
			
			BigDecimal PracS = new BigDecimal(pracscore[wi2]); 
			float PracSco = PracS.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			if(qibaxueqiList != null && qibaxueqiList.size() != 0)
			{
				float scoreqibaxueqi = Float.valueOf(qibaxueqiList.get(0).getCurriculum().getCredit());
				if(wi2==sumtable-2 && scoreqibaxueqi == 17)
				{
					pracscore[wi2] = pracscore[wi2]+2;
					pracscore[sumtable-1]=15;
				}
				else if(wi2==sumtable-2 && scoreqibaxueqi < 17)
				{
					pracscore[sumtable-1]=scoreqibaxueqi;
				}
			}
			sumpracscore += pracscore[wi2];
			sumpracpperiod += pracpperiod[wi2];
		}
		//计算实践课所占的总学分的百分比
		float f4 =0;
		if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
		{
			float pracpercentage =(float)(sumpracscore/Integer.valueOf(score2.getScore()))*100;
			BigDecimal pb = new BigDecimal(pracpercentage); 
			f4 = pb.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		//计算学位课的学分、学时和所占理论课的学时的百分比
		float xwescore =0,xwkperiod=0;//计算学位课 学分学时
		for(int xwk=0;xwk<xueweikelist.size();xwk++)
		{
			TheoreticalLesson xwklist = xueweikelist.get(xwk);
			xwescore += Float.valueOf(xwklist.getCurriculum().getCredit());
			xwkperiod += Float.valueOf(xwklist.getCurriculum().getHoursOfALL());
		}
		for(int shijianke_i=0;shijianke_i<xueweike_shijian.size();shijianke_i++)
		{
			if(xueweike_shijian.get(shijianke_i).getCurriculum().getCredit() == null || xueweike_shijian.get(shijianke_i).getCurriculum().getCredit().equals(""))
				xwescore += 0;
				
			else
				xwescore += Float.valueOf(xueweike_shijian.get(shijianke_i).getCurriculum().getCredit());
				
			if(xueweike_shijian.get(shijianke_i).getCurriculum().getHoursOfALL() == null || xueweike_shijian.get(shijianke_i).getCurriculum().getHoursOfALL().equals(""))
				xwkperiod += 0;
			else
				xwkperiod += Float.valueOf(xueweike_shijian.get(shijianke_i).getCurriculum().getHoursOfALL());
				

		}
		//学位课学分小数点后面保留1位
		BigDecimal xwescore_period_b = new BigDecimal(xwescore); 
		xwescore= xwescore_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		
		//理论课保留两位小数
		BigDecimal lilunke_period_b = new BigDecimal(subtotalscore); 
		subtotalscore= lilunke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		//基础必选课保留两位小数
		BigDecimal jichubixuanke_period_b = new BigDecimal(sumscore[0]); 
		sumscore[0]= jichubixuanke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		//公共必选课占总学时的百分比
				float gongbixuan_period_percentage1=(float)(sumperiod[0] / subtotalperiod)*100;
				BigDecimal gongbixuan_period_b = new BigDecimal(gongbixuan_period_percentage1); 
				float gongbixuan_period_f2 = gongbixuan_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				
				float kouzhangke_period_percentage1=(float)(zhuanyexuanxiuScore*16 / subtotalperiod)*100;//拓展课程中占理论课程学时的百分比
				BigDecimal kouzhangke_period_b = new BigDecimal(kouzhangke_period_percentage1); 
				float kouzhangke_period_f2 = kouzhangke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				
				float zhanyebixuanke_period_percentage1=(float)(sumperiod[1] / subtotalperiod)*100;//专业必选课中占理论课程学时的百分比
				BigDecimal zhanyebixuanke_period_b = new BigDecimal(zhanyebixuanke_period_percentage1); 
				float zhanyebixuanke_period_f2 = zhanyebixuanke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				
				float zhanyexuanxiuke_period_percentage1=(float)(sumScoreNum*16 / subtotalperiod)*100;//专业必选课中占理论课程学时的百分比
				BigDecimal zhanyexuanxiuke_period_b = new BigDecimal(zhanyexuanxiuke_period_percentage1); 
				float zhanyexuanxiuke_period_f2 = zhanyexuanxiuke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				
				float xueweikexueshi = 0;
				if (score2.getDepartment().getCollege().getCollegeCname().equals("体育学院"))
					xueweikexueshi = (float) (xwescore*16);
				else 
					xueweikexueshi = (float) (xwescore*16+4*20);
				float xuweike_period_percentage1=(float)(xueweikexueshi / subtotalperiod)*100;//学位课中占理论课程学时的百分比
				BigDecimal xuweike_period_b = new BigDecimal(xuweike_period_percentage1); 
				float xuweike_period_f2 = xuweike_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				Paragraph instruction=new Paragraph();
				instruction.add(setcontent("1、本专业最低毕业总学分为"+score2.getScore()+"。其中课内理论课程学分为"+subtotalscore+"，占总学分的"+f2+"%；集中实践性教学环节课程学分为"+sumpracscore+"，占总学分的"+f4+"%。"));
				instruction.add(setcontent("2、本专业培养计划安排课内理论总学时为"+subtotalperiod+"学时，主要由两类课程平台组成："));
				instruction.add(setcontent("  （1）公共基础教育平台课程：包括社会科学类、文化艺术类、数学与逻辑类、科学与技术类、创新创业与发展类等五大模块，分为基础必选课程和拓展课程。基础必选课"+listprofessional.size()+"门，"+sumperiod[0]+"学时，"+"计"+sumscore[0]+"学分，"+"占理论课程学时的"+gongbixuan_period_f2+"%。"
												+ "拓展课程，学生应该至少修读"+zhuanyexuanxiuScore*16+"学时，计"+zhuanyexuanxiuScore+"学分，理论课中学时"+kouzhangke_period_f2+"%，其中修读“文化与艺术”类不少于96学时，计6学分，“社会科学”类、“数学与逻辑”类、“科学与技术”类、“创业创新与发展”类每类不少于2学分。"));
				instruction.add(setcontent("  （2）专业教育平台课程：分为专业必选课和专业选修课程，其中专业必选课"+professionlist4.size()+"门，"+sumperiod[1]+"学时，"+"计"+sumscore[1]+"学分，"+"占理论课程学时的"+zhanyebixuanke_period_f2+"%。专业选修课程中，学生应该至少修读"+zhuanyexuanxiukescore*16 +"学时，计"+zhuanyexuanxiukescore+
									"学分，占理论课程学时的"+zhanyexuanxiuke_period_f2+"%。"));
				instruction.add(setcontent("3、本专业培养计划设置学位课"+(xueweikelist.size()+xueweike_shijian.size())+"门"+xueweikexueshi+"学时，计"+xwescore+"学分，占理论总学时"+xuweike_period_f2+"%。"));
				
				
				
			/*	Paragraph instruction=new Paragraph();
				instruction.add(setcontent("1、本专业最低毕业总学分为"+score2.getScore()+"。其中课内理论课程学分为"+subtotalscore+"，占总学分的"+f2+"；集中实践性教学环节课程学分为"+sumpracscore+"，占总学分的"+f4+"%。"));
				instruction.add(setcontent("2、本专业培养计划安排课内理论总学时为"+subtotalperiod+"学时，主要由两类课程平台组成："));
				instruction.add(setcontent("  （1）公共基础教育平台课程：包括社会科学类、文化艺术类、数学与逻辑类、科学与技术类、创新创业与发展类等五大模块，分为基础必选课程和扩展课程。基础必选课"+listprofessional.size()+"门，"+sumperiod[0]+"学时，"+"计"+sumscore[0]+"学分，"+"占理论课程学时的"+gongbixuan_period_f2+"%。"
												+ "扩展课程中，学生应该至少224学时，"+"计14学分，"+"占理论课中学时"+kouzhangke_period_f2+"%，其中修读“文化与艺术”类不少于96学时，计6学分，“社会科学”类、“数学与逻辑”类、“科学与技术”类、“创业创新与发展”类每类不少于2学分。"));
				instruction.add(setcontent("  （2）专业教育平台课程：分为专业必选课和专业选修课程，其中专业必选课"+professionlist4.size()+"门，"+sumperiod[1]+"学时，"+"计"+sumscore[1]+"学分，"+"占理论课程学时的"+zhanyebixuanke_period_f2+"%。专业选修课程中，学生应该至少修够"+zhuanyexuanxiukescore+"学分，占理论课程学时的"+zhanyexuanxiuke_period_f2+"%。"));
				instruction.add(setcontent("3、本专业培养计划设置学位课"+(xueweikelist.size()+xueweike_shijian.size())+"门"+xwkperiod+"学时，计"+xwescore+"学分，占理论总学时"+xuweike_period_f2+"%。"));
				*//*删除没有无关变量 代码优化*/

				return instruction;
	}
	public Paragraph TrainingInstruction(List[][] listbgs,List[] listprac,PTBasicInformation word1,List<TrainingEvents> professionlist3, List<TheoreticalLesson> professionlist4, List<TheoreticalLesson> isXWK, List<PracticeLesson> xueweike_shijian, List<TheoreticalLesson> listprofessional, ScoreThreshold score2, List<List<TheoreticalLesson>> proElec_IS_list, List<PracticeLesson> qibaxueqiList, String pid) throws Exception {
		
		int sumtable=xuenian*2;
		float[] score=new float[sumtable]; 
		float[] sumscore=new float[5];
		float[] period=new float[sumtable];
		float[] sumperiod=new float[5];
		float[] f1=new float[5];
		
		float[] subperiod=new float[sumtable];
		float[] subscore=new float[sumtable];
		float subtotalperiod=0,subtotalscore=0;

		float f1_gongxuan = 0,f1_zhuanxuan = 0,sumScoreNum =0;
		float zhuanyexuanxiukescore=0;
		float zhuanyexuanxiuScore = 0;//公共选修课
		List<String> department_gonggongxuanxie_ten = new ArrayList<String>(Arrays.asList("0104","1006","1305","0601","0602","0603","0604","0605","0606"));
		if(department_gonggongxuanxie_ten.contains(pid))
			zhuanyexuanxiuScore = 10;
		else
			zhuanyexuanxiuScore = 14;
		
		//各学期
		for(int gi=0;gi<2;gi++)//*******************************8需要更改
		{
			//计算公共必选课和专业必选课的各个学期的学分、学时，和公共必选课和专业必选课总学分和总学时
			for(int gi1=0;gi1<sumtable;gi1++)
			{
				List<TheoreticalLesson> list6 = listbgs[gi][gi1];
				for(int gi2=0;gi2<list6.size();gi2++)
				{
					TheoreticalLesson xf=list6.get(gi2);
					score[gi1] +=Float.valueOf(xf.getCurriculum().getCredit());
					if(xf.getCurriculum().getHoursOfALL().equals(""))period[gi1] +=0;
					else period[gi1] +=Float.valueOf(xf.getCurriculum().getHoursOfALL());
					
					if(xf.getCurriculum().getCredit().equals(""))subscore[gi1] += 0;
					else subscore[gi1] +=Float.valueOf(xf.getCurriculum().getCredit());
					
					if(xf.getCurriculum().getHoursOfALL().equals(""))subperiod[gi1] +=0;
					else subperiod[gi1] +=Float.valueOf(xf.getCurriculum().getHoursOfALL());
				}
				sumscore[gi] +=score[gi1];
				sumperiod[gi] +=period[gi1];
				score[gi1]=0;
				period[gi1]=0;
			}
			//计算学分的所占的百分比
			if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
			{
				float percentage = 0;
				percentage=(float)(sumscore[gi]/Float.valueOf(score2.getScore()))*100;
				BigDecimal b = new BigDecimal(percentage); 
				f1[gi] = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			}
			//计算专业选修课
				
				if(gi==1)
				{
					float qibaxueqiScore=0;
					float zhuanyexuanxiuke1_Score = 0;
					if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
					{
						sumScoreNum = Float.valueOf(score2.getScore());
						float[] pracscore=new float[sumtable];
						float sumpracscore=0;
						for(int wi2=0;wi2<sumtable;wi2++)
						{
							List <PracticeLesson> praclist = listprac[wi2];
							for(int wi3=0;wi3<praclist.size();wi3++)
							{
								PracticeLesson praclist1 = praclist.get(wi3);
								pracscore[wi2] +=Float.valueOf(praclist1.getCurriculum().getCredit());
							}
							sumpracscore += pracscore[wi2];
						}
						//得到毕业实践课中的7、8学期的毕设学分
						if(qibaxueqiList != null && qibaxueqiList.size() != 0)
						{
							if(qibaxueqiList.get(0).getCurriculum().getCredit() == null && qibaxueqiList.get(0).getCurriculum().getCredit().equals(""))
								qibaxueqiScore=0;
							else
								qibaxueqiScore=Float.valueOf(qibaxueqiList.get(0).getCurriculum().getCredit());
						}
						//每个专业方向的专业选修课模块1 学分
						
						for(int i=0;i<proElec_IS_list.size();i++)
						{
							for(int j=0;j<proElec_IS_list.get(i).size();j++)
							{
								TheoreticalLesson proElec_IS_list_ke = proElec_IS_list.get(i).get(j);
								zhuanyexuanxiuke1_Score +=Float.valueOf(proElec_IS_list_ke.getCurriculum().getCredit());
							}
						}
						//计算得到专业选修课模块1的课的学分
						sumScoreNum = sumScoreNum -sumscore[0] - sumscore[1] - zhuanyexuanxiuScore - sumpracscore - qibaxueqiScore - zhuanyexuanxiuke1_Score;//专业选修课模块2=专业总学分-公共必选课-专业必选课-公共选修课-实践课-毕业设计-专业选修课模块1
						BigDecimal bfive = new BigDecimal(sumScoreNum); 
						float zhuan1 = bfive.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
						sumScoreNum = zhuan1;
					}
					else sumScoreNum =0;
					zhuanyexuanxiukescore=sumScoreNum+zhuanyexuanxiuke1_Score;
				}

		}
		//小计
		for(int ti1=0;ti1<4;ti1++)
		{
			subtotalscore += sumscore[ti1];
			subtotalperiod +=sumperiod[ti1];
		}
		subtotalscore = subtotalscore + zhuanyexuanxiuScore+zhuanyexuanxiukescore;//计算小计中的总学分和总学时 加上选修课的学分
		subtotalperiod = subtotalperiod + zhuanyexuanxiuScore*16+zhuanyexuanxiukescore*16;

		
		//理论课占总学分的百分比
		float f2 =0;
		if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
		{
			float percentage1=(float)(subtotalscore/Float.valueOf(score2.getScore()))*100;
			BigDecimal b = new BigDecimal(percentage1); 
			f2 = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		}

		//集中实践
		float[] pracscore=new float[sumtable];
		float[] pracpperiod=new float[sumtable];
		float sumpracscore=0,sumpracpperiod=0;
		for(int wi2=0;wi2<sumtable;wi2++)
		{
			List <PracticeLesson> praclist = listprac[wi2];
			for(int wi3=0;wi3<praclist.size();wi3++)
			{
				PracticeLesson praclist1 = praclist.get(wi3);
				pracscore[wi2] +=Float.valueOf(praclist1.getCurriculum().getCredit());
				if(praclist1.getCurriculum().getHoursOfALL().equals("")) pracpperiod[wi2] +=0;
				else pracpperiod[wi2] +=Integer.valueOf(praclist1.getCurriculum().getHoursOfALL());
			}
			
			BigDecimal PracS = new BigDecimal(pracscore[wi2]); 
			float PracSco = PracS.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			if(qibaxueqiList != null && qibaxueqiList.size() != 0)
			{
				float scoreqibaxueqi = Float.valueOf(qibaxueqiList.get(0).getCurriculum().getCredit());
				if(wi2==sumtable-2 && scoreqibaxueqi == 17)
				{
					pracscore[wi2] = pracscore[wi2]+2;
					pracscore[sumtable-1]=15;
				}
				else if(wi2==sumtable-2 && scoreqibaxueqi < 17)
				{
					pracscore[sumtable-1]=scoreqibaxueqi;
				}
			}
			sumpracscore += pracscore[wi2];
			sumpracpperiod += pracpperiod[wi2];
		}
		//计算实践课所占的总学分的百分比
		float f4 =0;
		if(score2 != null && score2.getScore() != null && !score2.getScore().equals("") && !score2.getScore().equals("0"))
		{
			float pracpercentage =(float)(sumpracscore/Integer.valueOf(score2.getScore()))*100;
			BigDecimal pb = new BigDecimal(pracpercentage); 
			f4 = pb.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		//计算学位课的学分、学时和所占理论课的学时的百分比
		float xwescore =0,xwkperiod=0;
		for(int xwk=0;xwk<isXWK.size();xwk++)
		{
			TheoreticalLesson xwklist = isXWK.get(xwk);
			xwescore += Float.valueOf(xwklist.getCurriculum().getCredit());
			xwkperiod += Float.valueOf(xwklist.getCurriculum().getHoursOfALL());
		}
		for(int shijianke_i=0;shijianke_i<xueweike_shijian.size();shijianke_i++)
		{
			if(xueweike_shijian.get(shijianke_i).getCurriculum().getCredit() == null || xueweike_shijian.get(shijianke_i).getCurriculum().getCredit().equals(""))
				xwescore += 0;
				
			else
				xwescore += Float.valueOf(xueweike_shijian.get(shijianke_i).getCurriculum().getCredit());
				
			if(xueweike_shijian.get(shijianke_i).getCurriculum().getHoursOfALL() == null || xueweike_shijian.get(shijianke_i).getCurriculum().getHoursOfALL().equals(""))
				xwkperiod += 0;
			else
				xwkperiod += Float.valueOf(xueweike_shijian.get(shijianke_i).getCurriculum().getHoursOfALL());
				

		}
		//学位课学分小数点后面保留1位
		BigDecimal xwescore_period_b = new BigDecimal(xwescore); 
		xwescore= xwescore_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		
		//理论课保留两位小数
		BigDecimal lilunke_period_b = new BigDecimal(subtotalscore); 
		subtotalscore= lilunke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		//基础必选课保留两位小数
		BigDecimal jichubixuanke_period_b = new BigDecimal(sumscore[0]); 
		sumscore[0]= jichubixuanke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		//公共必选课占总学时的百分比
		float gongbixuan_period_percentage1=(float)(sumperiod[0] / subtotalperiod)*100;
		BigDecimal gongbixuan_period_b = new BigDecimal(gongbixuan_period_percentage1); 
		float gongbixuan_period_f2 = gongbixuan_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		
		float kouzhangke_period_percentage1=(float)(zhuanyexuanxiuScore*16 / subtotalperiod)*100;//拓展课程中占理论课程学时的百分比
		BigDecimal kouzhangke_period_b = new BigDecimal(kouzhangke_period_percentage1); 
		float kouzhangke_period_f2 = kouzhangke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		
		float zhanyebixuanke_period_percentage1=(float)(sumperiod[1] / subtotalperiod)*100;//专业必选课中占理论课程学时的百分比
		BigDecimal zhanyebixuanke_period_b = new BigDecimal(zhanyebixuanke_period_percentage1); 
		float zhanyebixuanke_period_f2 = zhanyebixuanke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		
		float zhanyexuanxiuke_period_percentage1=(float)(zhuanyexuanxiukescore*16 / subtotalperiod)*100;//专业选修课中占理论课程学时的百分比
		BigDecimal zhanyexuanxiuke_period_b = new BigDecimal(zhanyexuanxiuke_period_percentage1); 
		float zhanyexuanxiuke_period_f2 = zhanyexuanxiuke_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		float xueweikexueshi = 0;
		if (score2.getDepartment().getCollege().getCollegeCname().equals("体育学院"))
			xueweikexueshi = (float) (xwescore*16);
		else 
			xueweikexueshi = (float) (xwescore*16+4*20);
		float xuweike_period_percentage1=(float)(xueweikexueshi / subtotalperiod)*100;//学位课中占理论课程学时的百分比
		BigDecimal xuweike_period_b = new BigDecimal(xuweike_period_percentage1); 
		float xuweike_period_f2 = xuweike_period_b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
		
		Paragraph instruction=new Paragraph();
		instruction.add(setcontent("1、本专业最低毕业总学分为"+score2.getScore()+"。其中课内理论课程学分为"+subtotalscore+"，占总学分的"+f2+"%；集中实践性教学环节课程学分为"+sumpracscore+"，占总学分的"+f4+"%。"));
		instruction.add(setcontent("2、本专业培养计划安排课内理论总学时为"+subtotalperiod+"学时，主要由两类课程平台组成："));
		instruction.add(setcontent("  （1）公共基础教育平台课程：包括社会科学类、文化艺术类、数学与逻辑类、科学与技术类、创新创业与发展类等五大模块，分为基础必选课程和拓展课程。基础必选课"+listprofessional.size()+"门，"+sumperiod[0]+"学时，"+"计"+sumscore[0]+"学分，"+"占理论课程学时的"+gongbixuan_period_f2+"%。"
										+ "拓展课程，学生应该至少修读"+zhuanyexuanxiuScore*16+"学时，计"+zhuanyexuanxiuScore+"学分，占理论课中学时"+kouzhangke_period_f2+"%，其中修读“文化与艺术”类不少于96学时，计6学分，“社会科学”类、“数学与逻辑”类、“科学与技术”类、“创业创新与发展”类每类不少于2学分。"));
		instruction.add(setcontent("  （2）专业教育平台课程：分为专业必选课和专业选修课程，其中专业必选课"+professionlist4.size()+"门，"+sumperiod[1]+"学时，"+"计"+sumscore[1]+"学分，"+"占理论课程学时的"+zhanyebixuanke_period_f2+"%。专业选修课程中，学生应该至少修读"+zhuanyexuanxiukescore*16 +"学时，计"+zhuanyexuanxiukescore+
									"学分，占理论课程学时的"+zhanyexuanxiuke_period_f2+"%。"));
		instruction.add(setcontent("3、本专业培养计划设置学位课"+(isXWK.size()+xueweike_shijian.size())+"门"+xueweikexueshi+"学时，计"+xwescore+"学分，占理论总学时"+xuweike_period_f2+"%。"));
		/*删除没有无关变量 代码优化*/

		return instruction;
	}
	
	int xuenian=0;
	@SuppressWarnings("all")
	public void profession(String pid)throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream(); 
		response.setContentType("application/msword");
		
		Department departmentname = ptBasicInformationDao.findDepartmentName(pid);
		String name=departmentname.getDepartmentCname()+"专业培养计划.doc";
		
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);
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
	    List<PTBasicInformation> professionlist11=ptBasicInformationDao.findprofession1(pid);//查询出这个专业的培养计划基本信息	
		PTBasicInformation profession11 = null;
		if(professionlist11.size() ==0) ServletActionContext.getRequest().setAttribute("msg", "没有填写培养计划基本信息！");
		else profession11 =professionlist11.get(0);
	
	   String Titlename1 = profession11.getDepartment().getDepartmentCname();
		
	    /*PracticeLesson theoreticalLesson4=exportWordDao.findCourseDesign(courseid);//查出课程信息
        String Titlename1 = theoreticalLesson4.getCurriculum().getCurriculumCname();*/
	   Font Font2 = new RtfFont("宋体", 9, Font.NORMAL,Color.BLACK);  
     
        HeaderFooter header4=new HeaderFooter(new Phrase(Titlename1+"专业培养计划",Font2),false); 
        
        header4.setBorder(Rectangle.BOTTOM); 
        header4.setBorderColor(Color.BLACK); 
    
        header4.setAlignment(1);
	    header4.setAlignment(Element.ALIGN_CENTER);
        document.setHeader(header4);  	
		document.open();
		
		
		List<PTBasicInformation> professionlist=ptBasicInformationDao.findprofession1(pid);//查询出这个专业的培养计划基本信息	
		List<Ability> abilitylist = ptBasicInformationDao.findAbility(pid);
		MainTainOfPTTag professionTag = ptBasicInformationDao.findMainTainOfPTTag(pid);//查询总体安排标志表，判断该专业是否分方向
		List<MainTainOfPT> professionlist2 = ptBasicInformationDao.findprofession2(pid);//查询该专业的总体安排表
		List<TrainingEvents> professionlist3= ptBasicInformationDao.findprofession3(pid);//查询培养事件 
		List<TrainingEvents> professionlist_lilunke= ptBasicInformationDao.findprofession3_lilunke(pid);//查询培养事件 
		List professionlessonlist[][][]=ptBasicInformationDao.findprofession6(pid);//每个方向的公共必选课","公共选修课","专业必选课","专业选修课"在每个学期的课程
		List professionpracticelist[][] = ptBasicInformationDao.findprofession7(pid);//每个专业方向的在每个学期的集中实践课
		List[] professionlist4 = ptBasicInformationDao.findprofession4(pid);//专业必选课
		List<TheoreticalLesson> listprofessional = ptBasicInformationDao.findprofession8(pid);
		List<TheoreticalLesson> gongbixuankelist = ptBasicInformationDao.findgongbixuankelist(pid);//公共必选课

		List[] professionlist51 = ptBasicInformationDao.findProElec1(pid);//专业选修课模块1
		List<List<List<TheoreticalLesson>>> proElec_IS = ptBasicInformationDao.findProElec1_IS(pid);//查询各专业方向的专业选修课模块1中的课
		List[] professionlist52 = ptBasicInformationDao.findProElec2(pid);//专业选修课模块2
		List<List<TheoreticalLesson>> xueweike = ptBasicInformationDao.findxueweike(pid);//理论课中的学位课
		List<List<PracticeLesson>> xueweike_shijian = ptBasicInformationDao.findxueweike_shijian(pid);//查询实践课中的理论课
		List[] professionlist6 = ptBasicInformationDao.findprofession5(pid);//专业的实践课
		List<Professional> proNum = ptBasicInformationDao.findProNum(pid);//查询专业的专业方向个数
		List<ScoreThreshold> scorelist = ptBasicInformationDao.findscore(pid);//查询每个专业的总学分
		
		List<List<PracticeLesson>> qibaxueqiList = ptBasicInformationDao.findqibaxueqiList(pid);//查询7、8学期的毕业设计
		//List<List<PracticeLesson>> biyeshejiList = ptBasicInformationDao.findbiyeshejiList(pid);//查询7、8和第8学期的毕业设计
			if(professionlist != null)
			{
				document.setPageSize(PageSize.A4);
				PTBasicInformation changexuenian=professionlist.get(0);
				if(changexuenian.getLearningTime().equals("四年")) xuenian=4;
				if(changexuenian.getLearningTime().equals("五年")) xuenian=5;
				PTBasicInformation profession1 = null;
				if(professionlist.size() ==0) ServletActionContext.getRequest().setAttribute("msg", "没有填写培养计划基本信息！");
				else profession1 =professionlist.get(0);
				Chapter chapter1=new Chapter(1);
				//Paragraph document=new Paragraph();//列，行		
				Strmajor = profession1.getDepartment().getDepartmentCname();
				chapter1.add(Tabletitle2(Strmajor + "专业培养计划"));
				String EducationalSystem = "学    制：" + profession1.getLearningTime()
						+ "                                            启用年级 ："+ profession1.getEnableGrade()+"级";
				Paragraph study = new Paragraph(EducationalSystem, new RtfFont("黑 体",
						11, Font.NORMAL, Color.BLACK));
				chapter1.add(study);
				chapter1.add(settitle2("培养目标: "));
				chapter1.add(setcontent(profession1.getTrainingObjectives().replace("\r\n","\n")));
				chapter1.add(settitle2("培养要求: "));
				chapter1.add(setcontent(profession1.getTrainingRequirements()));
				chapter1.add(settitle2("毕业生应获得以下几个方面的知识和能力： "));
		
				for(int ai=0;ai<abilitylist.size();ai++)
				{
					Ability ability = abilitylist.get(ai);
					chapter1.add(settitle(String.valueOf(ai+1)+"、"+ability.getAbilityname()));
					if(ability.getAbilitycontent() != null)
						chapter1.add(setcontent(ability.getAbilitycontent()));
				}
		
				chapter1.add(settitle2("主干学科："));
				chapter1.add(setcontent(profession1.getMainCourses()));
				chapter1.add(settitle2("学位课："));
				chapter1.add(setcontent(profession1.getDegreeCourses().replace("\r\n","\n")));
				chapter1.add(settitle2("授予学位："));
				chapter1.add(setcontent(profession1.getDegree()));
						//document.newPage();
				document.add(chapter1);
				
				Chapter chapter2=new Chapter(2);
				if(proNum != null && proNum.size() != 0)
				{
					if( professionTag != null && professionTag.getTag().equals("0") && professionlist2 != null && professionlist2.size() != 0)
					{
						chapter2.add(Tabletitle("一、培养计划总体安排表"));
						chapter2.add(GeneralArrangement(professionlist2,professionlist3,profession1));//总体安排表、培养事件、学年
						document.setPageSize(PageSize.A4.rotate());
					}
					else if(professionTag != null && professionTag.getTag().equals("1")&& professionlist2 != null && professionlist2.size() != 0)
					{
						List<List<Professional>> haveProfessionalList = new ArrayList<List<Professional>>();//录入了总体安排的专业方向
						List<ApplicationMainTainOfPT> newApplicationMainTainOfPT = new ArrayList<ApplicationMainTainOfPT>();//存储录入了的方向的内容，用于判断是否相同
						for(int i=0;i<proNum.size();i++){
							List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(proNum.get(i));
							if(applicationMainTainOfPTlist != null)
							{
								newApplicationMainTainOfPT.add(applicationMainTainOfPTlist.get(0));
							}
						}
						//将相同总体安排表的专业方向放一起
						while(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() != 0)
						{
							List<Professional> professionallist = new ArrayList<Professional>();
							professionallist.add(newApplicationMainTainOfPT.get(0).getProfessional());
							
							if(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() > 1){
								for(int i=1;i<newApplicationMainTainOfPT.size();i++){
									if(newApplicationMainTainOfPT.get(0).getMainTainOfPT() == newApplicationMainTainOfPT.get(i).getMainTainOfPT())
									{
										professionallist.add(newApplicationMainTainOfPT.get(i).getProfessional());
										newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(i));
										i--;
									}	
								}
							}
							newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(0));
							haveProfessionalList.add(professionallist);
						}
						for(int zi=0;zi<haveProfessionalList.size();zi++)
						{
							List<Professional> newProfessionallist = haveProfessionalList.get(zi);
							String Professionalname ="";
							for(int zj=0;zj<newProfessionallist.size();zj++)
							{
								if(zj == (newProfessionallist.size()-1))
								{//不等于第一个和最后一个 则不加、
									Professionalname +=newProfessionallist.get(zj).getProfessionalname();
								}
								else
								{
									Professionalname +=newProfessionallist.get(zj).getProfessionalname()+"、";
								}
							}
							//从应用表查总体安排
							List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionallist.get(0));
							if(applicationMainTainOfPTlist != null && applicationMainTainOfPTlist.size() !=0)
							{
								List<MainTainOfPT> newmainTainOfPTlist = new ArrayList<MainTainOfPT>();
								for(int i=0;i<applicationMainTainOfPTlist.size();i++)
								{
									newmainTainOfPTlist.add(applicationMainTainOfPTlist.get(i).getMainTainOfPT());
								}
								//从应用表查培养事件
								List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalEvents(newProfessionallist.get(0));
								List<TrainingEvents> newtrainingEventslist = new ArrayList<TrainingEvents>();
								if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0){
									for(int i=0;i<applicationTrainingEventslist.size();i++){
										newtrainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
									}
								}
								chapter2.add(Tabletitle("一、"+Professionalname+"培养计划总体安排表"));
								chapter2.add(GeneralArrangement(newmainTainOfPTlist,newtrainingEventslist,profession1));//总体安排表、培养事件、学年
								document.setPageSize(PageSize.A4.rotate());
							}

						}
					}
				}
				else if(professionlist2 != null && professionlist2.size() != 0) {
					chapter2.add(Tabletitle("一、培养计划总体安排表"));
					chapter2.add(GeneralArrangement(professionlist2,professionlist3,profession1));//总体安排表、培养事件、学年
					document.setPageSize(PageSize.A4.rotate());
				}

			
				if(professionlessonlist[0][0][0].size() != 0 )
				{
					if(proNum != null && proNum.size() != 0)
					{
						if(professionTag != null && professionTag.getTag().equals("1"))
						{
							for(int gi3=0;gi3<professionlessonlist.length;gi3++)
							{
								List GraRverlist=professionlessonlist[gi3][0][0];
								List[][] Gradlist=professionlessonlist[gi3];
								List[] GradPrac=professionpracticelist[gi3];
								TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
								
								List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalEventslilunke(professname.getProfessional());
								List<TrainingEvents> newtrainingEventslist = new ArrayList<TrainingEvents>();
								if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0){
									for(int i=0;i<applicationTrainingEventslist.size();i++){
										newtrainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
									}
								}
								if(Gradlist !=null ||Gradlist.length !=0)
								{
									if(scorelist != null && scorelist.size() != 0)
									{
										ScoreThreshold score = scorelist.get(0);
										chapter2.add(Tabletitle("二、"+professname.getProfessional().getProfessionalname()+"毕业生学分要求"));
										chapter2.add(Graducation1(Gradlist,newtrainingEventslist,GradPrac,profession1,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
									}
								}
							}
						}
						else
						{
							for(int gi3=0;gi3<professionlessonlist.length;gi3++)
							{
								List GraRverlist=professionlessonlist[gi3][0][0];
								List[][] Gradlist=professionlessonlist[gi3];
								List[] GradPrac=professionpracticelist[gi3];
								TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
								
								if(Gradlist !=null ||Gradlist.length !=0)
								{
									if(scorelist != null && scorelist.size() != 0 && professionlist_lilunke != null && professionlist_lilunke.size() !=0)
									{
										ScoreThreshold score = scorelist.get(0);
										
										chapter2.add(Tabletitle("二、"+professname.getProfessional().getProfessionalname()+"毕业生学分要求"));
										chapter2.add(Graducation1(Gradlist,professionlist_lilunke,GradPrac,profession1,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
									}
								}
							}
						}
					}
					else
					{
						if(scorelist != null && scorelist.size() != 0 && professionlist_lilunke != null && professionlist_lilunke.size() !=0)
						{
							ScoreThreshold score = scorelist.get(0);
							chapter2.add(Tabletitle("二、毕业生学分要求"));
							//每个方向的公共必选课","公共选修课","专业必选课","专业选修课"在每个学期的课程；查询培养事件；每个专业方向的在每个学期的集中实践课；学年；学分
							chapter2.add(Graducation2(professionlessonlist,professionlist_lilunke,professionpracticelist,profession1,score,proElec_IS.get(0),qibaxueqiList.get(0),pid));
						}
						else chapter2.add(Tabletitle("二、该专业没有毕业学分要求"));
					}
				}
				else  chapter2.add(Tabletitle("二、该专业没有毕业学分要求"));
				document.setPageSize(PageSize.A4.rotate());
				document.add(chapter2);

			
		
				Chapter chapter4 = new Chapter(4);
				document.setPageSize(PageSize.A4);
				if(professionlist4[0].size() != 0 )
				{
					chapter4.add(Tabletitle("三、教学安排"));
					chapter4.add(TeachSchedule(professionlist4,listprofessional,professionlist51,professionlist52,pid));
					Paragraph study1=new Paragraph("1.周学时后有“/”的表示该课程要在前半学期开课，周学时前有“/”的表示该课程在后半学期开课。\n2.课程名称后有“*”表示该课程是学位课。\n3.“专业选修课程模块Ⅰ”为必选的专业选修课。",new RtfFont("Times New Roman", (float)10.5, Font.NORMAL,Color.BLACK));
					chapter4.add(study1);
				}
				else document.add(Tabletitle("没有填写该专业选课"));
		
				for(int x=0;x<professionlist6.length;x++)
				{
					List GraRverlist=professionlist6[x];
					if(professionlist6[x].size() !=0 && professionlist6.length != 1)
					{
						PracticeLesson professname =(PracticeLesson)GraRverlist.get(0);
						chapter4.add(Tabletitle("四、"+professname.getProfessional().getProfessionalname()+"集中实践性教学环节安排"));
						chapter4.add(FocusPractice(GraRverlist,proNum));
					}
					else {
						chapter4.add(Tabletitle("四、集中实践性教学环节安排"));
						chapter4.add(FocusPractice(GraRverlist,proNum));
					}
				}
				document.add(chapter4);
				
				Chapter chapter5 = new Chapter(5);
				
				if(professionlessonlist[0][0][0].size() != 0)
				{
					if(professionlessonlist[0][0].length !=1)//分方向的集中实践课
						for(int gi3=0;gi3<professionlessonlist.length;gi3++)
						{
							List GraRverlist=professionlessonlist[gi3][0][0];
							List[][] Gradlist=professionlessonlist[gi3];
							List[] GradPrac = professionpracticelist[gi3];
							List<TheoreticalLesson> professionlist41=professionlist4[gi3];
							List<TheoreticalLesson> isXWK = xueweike.get(gi3);
							if(Gradlist ==null ||Gradlist.length ==0)
								ServletActionContext.getRequest().setAttribute("msg", "填写信息不完整!");
							else
							{
								if(scorelist != null && scorelist.size() != 0)
								{
									ScoreThreshold score = scorelist.get(0);
									TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
									chapter5.add(Tabletitle("五、"+professname.getProfessional().getProfessionalname()+"专业培养计划说明"));
									chapter5.add(TrainingInstruction(Gradlist,GradPrac,profession1,professionlist3,professionlist41,isXWK,xueweike_shijian.get(gi3),gongbixuankelist,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
								}
								else chapter5.add(Tabletitle("五、该专业没有毕业总学分要求"));
								
							}
						}
					else //部分方向的集中实践课
					{
						if(scorelist != null && scorelist.size() != 0)
						{
							ScoreThreshold score = scorelist.get(0);
							chapter5.add(Tabletitle("五、专业培养计划说明"));
							List<TheoreticalLesson> xueweikelist=xueweike.get(0);
							chapter5.add(TrainingInstruction2(professionlessonlist,professionlist3,professionpracticelist,profession1,professionlist4[0],xueweikelist,xueweike_shijian.get(0),gongbixuankelist,score,proElec_IS.get(0),qibaxueqiList.get(0),pid));
						}
						else chapter5.add(Tabletitle("五、该专业没有毕业总学分要求"));
					
					}
				}
				else document.add(Tabletitle("该专业填写信息不完整"));

				
				
				String[] flage={"六","七","八","九"};
				int flagei=0;
				TrainingAnother otherInform = ptBasicInformationDao.findgraduation(pid);
				if(otherInform != null && !otherInform.getExtracurricular().equals(""))
				{
					chapter5.add(Tabletitle(flage[flagei]+"、课外安排与要求"));
					chapter5.add(setcontent(otherInform.getExtracurricular()));
					flagei++;
				}
				if(otherInform != null && !otherInform.getBusinessPractice().equals(""))
				{
					chapter5.add(Tabletitle(flage[flagei]+"、企业集中实践安排与要求"));
					chapter5.add(setcontent(otherInform.getBusinessPractice()));
					flagei++;
				}
				if(otherInform != null && !otherInform.getBusinessTeacher().equals(""))
				{
					chapter5.add(Tabletitle(flage[flagei]+"、企业教师授课安排与要求"));
					chapter5.add(setcontent(otherInform.getBusinessTeacher()));
					flagei++;
				}
				document.add(chapter5);
				
				Chapter chapter7 = new Chapter(7);
				document.setPageSize(PageSize.A4.rotate());
				List<Topology> picture1 =ptBasicInformationDao.findpicture1(pid);
				List<List<Topology>> newpicture = new ArrayList<List<Topology>>();
				if(picture1.size() != 0)//判断有没有拓扑图
				{
					if(picture1.size() != 1)//分方向
					{
						while(picture1 !=null && picture1.size()!=0)//每次取第一个值和和后面的值判断是否相同
						{
							List<Topology> SWPpic = new ArrayList<Topology>();
							String path = picture1.get(0).getTopologypath();
							for(int i=0;i<picture1.size();i++)
							{
								if(path.equals(picture1.get(i).getTopologypath()))
								{
									SWPpic.add(picture1.get(i));
									picture1.remove(i);
									i--;
								}
							}
							newpicture.add(SWPpic);
						}
						for(int i=0;i<newpicture.size();i++)//取出name
						{
							String picname ="";
								for(int j=0;j<newpicture.get(i).size();j++)
								{
									if(j == (newpicture.get(i).size()-1)){//不等于第一个和最后一个 则不加、
										picname +=newpicture.get(i).get(j).getProfessional().getProfessionalname();
									}
									else{
										picname +=newpicture.get(i).get(j).getProfessional().getProfessionalname()+"、";
									}
								}
								chapter7.add(TabletitlePicture(flage[flagei]+"、"+picname+"主干课拓扑图"));
								chapter7.add(picture(newpicture.get(i).get(0).getTopologypath()));
							}
					}
					else{//部分方向 
						chapter7.add(TabletitlePicture(flage[flagei]+"、"+picture1.get(0).getDepartment().getDepartmentCname()+"主干课拓扑图"));
						chapter7.add(picture(picture1.get(0).getTopologypath()));
					}
					document.add(chapter7);
				}
				else
				{
					document.add(Tabletitle("该专业没有上传拓扑图"));
				}
				flagei=0;
			}
			else document.add(Tabletitle("没有填写培养计划基本信息"));
			document.close();
			outputStream.flush();
		
	}
	@SuppressWarnings("all")
	public void college(String cid)throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		String name="全院培养计划.doc";
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		List<PTBasicInformation> collegelist=ptBasicInformationDao.findcollege1();
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);
		document.open();
		List<Department> departmentnum = ptBasicInformationDao.findCollegeProNum(cid);
		for(int di=0;di<departmentnum.size();di++)
		{
			Department departmentnumid = departmentnum.get(di);
			String pid = (String)departmentnumid.getDepartmentid();
			
			
			
			List<PTBasicInformation> professionlist=ptBasicInformationDao.findprofession1(pid);//查询出这个专业的培养计划基本信息	
			List<Ability> abilitylist = ptBasicInformationDao.findAbility(pid);
			MainTainOfPTTag professionTag = ptBasicInformationDao.findMainTainOfPTTag(pid);//查询总体安排标志表，判断该专业是否分方向
			List<MainTainOfPT> professionlist2 = ptBasicInformationDao.findprofession2(pid);//查询该专业的总体安排表
			List<TrainingEvents> professionlist3= ptBasicInformationDao.findprofession3(pid);//查询培养事件 
			List<TrainingEvents> professionlist_lilunke= ptBasicInformationDao.findprofession3_lilunke(pid);//查询培养事件 
			List professionlessonlist[][][]=ptBasicInformationDao.findprofession6(pid);//每个方向的公共必选课","公共选修课","专业必选课","专业选修课"在每个学期的课程
			List professionpracticelist[][] = ptBasicInformationDao.findprofession7(pid);//每个专业方向的在每个学期的集中实践课
			List[] professionlist4 = ptBasicInformationDao.findprofession4(pid);//专业必选课
			List<TheoreticalLesson> listprofessional = ptBasicInformationDao.findprofession8(pid);
			List<TheoreticalLesson> gongbixuankelist = ptBasicInformationDao.findgongbixuankelist(pid);//公共必选课

			List[] professionlist51 = ptBasicInformationDao.findProElec1(pid);//专业选修课模块1
			List<List<List<TheoreticalLesson>>> proElec_IS = ptBasicInformationDao.findProElec1_IS(pid);//查询各专业方向的专业选修课模块1中的课
			List[] professionlist52 = ptBasicInformationDao.findProElec2(pid);//专业选修课模块2
			List<List<TheoreticalLesson>> xueweike = ptBasicInformationDao.findxueweike(pid);//理论课中的学位课
			List<List<PracticeLesson>> xueweike_shijian = ptBasicInformationDao.findxueweike_shijian(pid);//查询实践课中的理论课
			List[] professionlist6 = ptBasicInformationDao.findprofession5(pid);//专业的实践课
			List<Professional> proNum = ptBasicInformationDao.findProNum(pid);//查询专业的专业方向个数
			List<ScoreThreshold> scorelist = ptBasicInformationDao.findscore(pid);//查询每个专业的总学分
			
			List<List<PracticeLesson>> qibaxueqiList = ptBasicInformationDao.findqibaxueqiList(pid);//查询7、8学期的毕业设计
			//List<List<PracticeLesson>> biyeshejiList = ptBasicInformationDao.findbiyeshejiList(pid);//查询7、8和第8学期的毕业设计
				if(professionlist != null)
				{
					document.setPageSize(PageSize.A4);
					PTBasicInformation changexuenian=professionlist.get(0);
					if(changexuenian.getLearningTime().equals("四年")) xuenian=4;
					if(changexuenian.getLearningTime().equals("五年")) xuenian=5;
					PTBasicInformation profession1 = null;
					if(professionlist.size() ==0) ServletActionContext.getRequest().setAttribute("msg", "没有填写培养计划基本信息！");
					else profession1 =professionlist.get(0);
					Chapter chapter1=new Chapter(1);
					//Paragraph document=new Paragraph();//列，行		
					Strmajor = profession1.getDepartment().getDepartmentCname();
					chapter1.add(Tabletitle(Strmajor + "专业培养计划"));
					
					String EducationalSystem = "学    制：" + profession1.getLearningTime()
							+ "                                            启用年级 ："+ profession1.getEnableGrade()+"级";
					Paragraph study = new Paragraph(EducationalSystem, new RtfFont("宋体",
							12, Font.BOLD, Color.BLACK));
					chapter1.add(study);
					chapter1.add(settitle2("培养目标: "));
					chapter1.add(setcontent(profession1.getTrainingObjectives().replace("\r\n","\n")));
					chapter1.add(settitle2("培养要求: "));
					chapter1.add(setcontent(profession1.getTrainingRequirements()));
					chapter1.add(settitle2("毕业生应获得以下几个方面的知识和能力： "));
			
					for(int ai=0;ai<abilitylist.size();ai++)
					{
						Ability ability = abilitylist.get(ai);
						chapter1.add(settitle(String.valueOf(ai+1)+"、"+ability.getAbilityname()));
						if(ability.getAbilitycontent() != null)
							chapter1.add(setcontent(ability.getAbilitycontent()));
					}
					chapter1.add(settitle2("主干学科："));
					chapter1.add(setcontent(profession1.getMainCourses()));
					chapter1.add(settitle2("学位课："));
					chapter1.add(setcontent(profession1.getDegreeCourses().replace("\r\n","\n")));
					chapter1.add(settitle2("授予学位："));
					chapter1.add(setcontent(profession1.getDegree()));
							//document.newPage();
					document.add(chapter1);
					
					Chapter chapter2=new Chapter(2);
					if(proNum != null && proNum.size() != 0)
					{
						if( professionTag != null && professionTag.getTag().equals("0") && professionlist2 != null && professionlist2.size() != 0)
						{
							chapter2.add(Tabletitle2("一、培养计划总体安排表"));
							chapter2.add(GeneralArrangement(professionlist2,professionlist3,profession1));//总体安排表、培养事件、学年
							document.setPageSize(PageSize.A4.rotate());
						}
						else if(professionTag != null && professionTag.getTag().equals("1")&& professionlist2 != null && professionlist2.size() != 0)
						{
							List<List<Professional>> haveProfessionalList = new ArrayList<List<Professional>>();//录入了总体安排的专业方向
							List<ApplicationMainTainOfPT> newApplicationMainTainOfPT = new ArrayList<ApplicationMainTainOfPT>();//存储录入了的方向的内容，用于判断是否相同
							for(int i=0;i<proNum.size();i++){
								List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(proNum.get(i));
								if(applicationMainTainOfPTlist != null)
								{
									newApplicationMainTainOfPT.add(applicationMainTainOfPTlist.get(0));
								}
							}
							//将相同总体安排表的专业方向放一起
							while(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() != 0)
							{
								List<Professional> professionallist = new ArrayList<Professional>();
								professionallist.add(newApplicationMainTainOfPT.get(0).getProfessional());
								
								if(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() > 1){
									for(int i=1;i<newApplicationMainTainOfPT.size();i++){
										if(newApplicationMainTainOfPT.get(0).getMainTainOfPT() == newApplicationMainTainOfPT.get(i).getMainTainOfPT())
										{
											professionallist.add(newApplicationMainTainOfPT.get(i).getProfessional());
											newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(i));
											i--;
										}	
									}
								}
								newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(0));
								haveProfessionalList.add(professionallist);
							}
							for(int zi=0;zi<haveProfessionalList.size();zi++)
							{
								List<Professional> newProfessionallist = haveProfessionalList.get(zi);
								String Professionalname ="";
								for(int zj=0;zj<newProfessionallist.size();zj++)
								{
									if(zj == (newProfessionallist.size()-1))
									{//不等于第一个和最后一个 则不加、
										Professionalname +=newProfessionallist.get(zj).getProfessionalname();
									}
									else
									{
										Professionalname +=newProfessionallist.get(zj).getProfessionalname()+"、";
									}
								}
								//从应用表查总体安排
								List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionallist.get(0));
								if(applicationMainTainOfPTlist != null && applicationMainTainOfPTlist.size() !=0)
								{
									List<MainTainOfPT> newmainTainOfPTlist = new ArrayList<MainTainOfPT>();
									for(int i=0;i<applicationMainTainOfPTlist.size();i++)
									{
										newmainTainOfPTlist.add(applicationMainTainOfPTlist.get(i).getMainTainOfPT());
									}
									//从应用表查培养事件
									List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalEvents(newProfessionallist.get(0));
									List<TrainingEvents> newtrainingEventslist = new ArrayList<TrainingEvents>();
									if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0){
										for(int i=0;i<applicationTrainingEventslist.size();i++){
											newtrainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
										}
									}
									chapter2.add(Tabletitle2("一、"+Professionalname+"培养计划总体安排表"));
									chapter2.add(GeneralArrangement(newmainTainOfPTlist,newtrainingEventslist,profession1));//总体安排表、培养事件、学年
									document.setPageSize(PageSize.A4.rotate());
								}

							}
						}
					}
					else if(professionlist2 != null && professionlist2.size() != 0) {
						chapter2.add(Tabletitle2("一、培养计划总体安排表"));
						chapter2.add(GeneralArrangement(professionlist2,professionlist3,profession1));//总体安排表、培养事件、学年
						document.setPageSize(PageSize.A4.rotate());
					}

				
					if(professionlessonlist[0][0][0].size() != 0 )
					{
						if(proNum != null && proNum.size() != 0)
						{
							if(professionTag != null && professionTag.getTag().equals("1"))
							{
								for(int gi3=0;gi3<professionlessonlist.length;gi3++)
								{
									List GraRverlist=professionlessonlist[gi3][0][0];
									List[][] Gradlist=professionlessonlist[gi3];
									List[] GradPrac=professionpracticelist[gi3];
									TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
									
									List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalEventslilunke(professname.getProfessional());
									List<TrainingEvents> newtrainingEventslist = new ArrayList<TrainingEvents>();
									if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0){
										for(int i=0;i<applicationTrainingEventslist.size();i++){
											newtrainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
										}
									}
									if(Gradlist !=null ||Gradlist.length !=0)
									{
										if(scorelist != null && scorelist.size() != 0)
										{
											ScoreThreshold score = scorelist.get(0);
											chapter2.add(Tabletitle2("二、"+professname.getProfessional().getProfessionalname()+"毕业生学分要求"));
											chapter2.add(Graducation1(Gradlist,newtrainingEventslist,GradPrac,profession1,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
										}
									}
								}
							}
							else
							{
								for(int gi3=0;gi3<professionlessonlist.length;gi3++)
								{
									List GraRverlist=professionlessonlist[gi3][0][0];
									List[][] Gradlist=professionlessonlist[gi3];
									List[] GradPrac=professionpracticelist[gi3];
									TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
									
									if(Gradlist !=null ||Gradlist.length !=0)
									{
										if(scorelist != null && scorelist.size() != 0 && professionlist_lilunke != null && professionlist_lilunke.size() !=0)
										{
											ScoreThreshold score = scorelist.get(0);
											
												chapter2.add(Tabletitle2("二、"+professname.getProfessional().getProfessionalname()+"毕业生学分要求"));
											chapter2.add(Graducation1(Gradlist,professionlist_lilunke,GradPrac,profession1,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
										}
									}
								}
							}
						}
						else
						{
							if(scorelist != null && scorelist.size() != 0 && professionlist_lilunke != null && professionlist_lilunke.size() !=0)
							{
								ScoreThreshold score = scorelist.get(0);
								chapter2.add(Tabletitle2("二、毕业生学分要求"));
								//每个方向的公共必选课","公共选修课","专业必选课","专业选修课"在每个学期的课程；查询培养事件；每个专业方向的在每个学期的集中实践课；学年；学分
								chapter2.add(Graducation2(professionlessonlist,professionlist_lilunke,professionpracticelist,profession1,score,proElec_IS.get(0),qibaxueqiList.get(0),pid));
							}
							else chapter2.add(Tabletitle2("二、该专业没有毕业学分要求"));
						}
					}
					else  chapter2.add(Tabletitle2("二、该专业没有毕业学分要求"));
					document.setPageSize(PageSize.A4.rotate());
					document.add(chapter2);
				
			
					Chapter chapter4 = new Chapter(4);
					document.setPageSize(PageSize.A4);
					if(professionlist4[0].size() != 0 )
					{
						chapter4.add(Tabletitle2("三、教学安排"));
						chapter4.add(TeachSchedule(professionlist4,listprofessional,professionlist51,professionlist52,pid));
						Paragraph study1=new Paragraph("1.周学时后有“/”的表示该课程要在前半学期开课，周学时前有“/”的表示该课程在后半学期开课。\n2.课程名称后有“*”表示该课程是学位课。\n3.“专业选修课程模块Ⅰ”为必选的专业选修课。",new RtfFont("Times New Roman", (float)10.5, Font.NORMAL,Color.BLACK));
						chapter4.add(study1);
					}
					else document.add(Tabletitle2("没有填写该专业选课"));
			
					for(int x=0;x<professionlist6.length;x++)
					{
						List GraRverlist=professionlist6[x];
						if(professionlist6[x].size() !=0 && professionlist6.length != 1)
						{
							PracticeLesson professname =(PracticeLesson)GraRverlist.get(0);
							chapter4.add(Tabletitle2("四、"+professname.getProfessional().getProfessionalname()+"集中实践性教学环节安排"));
							chapter4.add(FocusPractice(GraRverlist,proNum));
						}
						else {
							chapter4.add(Tabletitle2("四、集中实践性教学环节安排"));
							chapter4.add(FocusPractice(GraRverlist,proNum));
						}
					}
					document.add(chapter4);
					
					Chapter chapter5 = new Chapter(5);
					
					if(professionlessonlist[0][0][0].size() != 0)
					{
						if(professionlessonlist[0][0].length !=1)//分方向的集中实践课
							for(int gi3=0;gi3<professionlessonlist.length;gi3++)
							{
								List GraRverlist=professionlessonlist[gi3][0][0];
								List[][] Gradlist=professionlessonlist[gi3];
								List[] GradPrac = professionpracticelist[gi3];
								List<TheoreticalLesson> professionlist41=professionlist4[gi3];
								List<TheoreticalLesson> isXWK = xueweike.get(gi3);
								if(Gradlist ==null ||Gradlist.length ==0)
									ServletActionContext.getRequest().setAttribute("msg", "填写信息不完整!");
								else
								{
									if(scorelist != null && scorelist.size() != 0)
									{
										ScoreThreshold score = scorelist.get(0);
										TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
										chapter5.add(Tabletitle2("五、"+professname.getProfessional().getProfessionalname()+"专业培养计划说明"));
										chapter5.add(TrainingInstruction(Gradlist,GradPrac,profession1,professionlist3,professionlist41,isXWK,xueweike_shijian.get(gi3),gongbixuankelist,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
									}
									else chapter5.add(Tabletitle2("五、该专业没有毕业总学分要求"));
									
								}
							}
						else //部分方向的集中实践课
						{
							if(scorelist != null && scorelist.size() != 0)
							{
								ScoreThreshold score = scorelist.get(0);
								chapter5.add(Tabletitle2("五、专业培养计划说明"));
								List<TheoreticalLesson> xueweikelist=xueweike.get(0);
								chapter5.add(TrainingInstruction2(professionlessonlist,professionlist3,professionpracticelist,profession1,professionlist4[0],xueweikelist,xueweike_shijian.get(0),gongbixuankelist,score,proElec_IS.get(0),qibaxueqiList.get(0),pid));
							}
							else chapter5.add(Tabletitle2("五、该专业没有毕业总学分要求"));
						
						}
					}
					else document.add(Tabletitle("该专业填写信息不完整"));

					
					
					String[] flage={"六","七","八","九"};
					int flagei=0;
					TrainingAnother otherInform = ptBasicInformationDao.findgraduation(pid);
					if(otherInform != null && !otherInform.getExtracurricular().equals(""))
					{
						chapter5.add(Tabletitle2(flage[flagei]+"、课外安排与要求"));
						chapter5.add(setcontent(otherInform.getExtracurricular()));
						flagei++;
					}
					if(otherInform != null && !otherInform.getBusinessPractice().equals(""))
					{
						chapter5.add(Tabletitle2(flage[flagei]+"、企业集中实践安排与要求"));
						chapter5.add(setcontent(otherInform.getBusinessPractice()));
						flagei++;
					}
					if(otherInform != null && !otherInform.getBusinessTeacher().equals(""))
					{
						chapter5.add(Tabletitle2(flage[flagei]+"、企业教师授课安排与要求"));
						chapter5.add(setcontent(otherInform.getBusinessTeacher()));
						flagei++;
					}
					document.add(chapter5);
					
					Chapter chapter7 = new Chapter(7);
					document.setPageSize(PageSize.A4.rotate());
					List<Topology> picture1 =ptBasicInformationDao.findpicture1(pid);
					List<List<Topology>> newpicture = new ArrayList<List<Topology>>();
					if(picture1.size() != 0)//判断有没有拓扑图
					{
						if(picture1.size() != 1)//分方向
						{
							while(picture1 !=null && picture1.size()!=0)//每次取第一个值和和后面的值判断是否相同
							{
								List<Topology> SWPpic = new ArrayList<Topology>();
								String path = picture1.get(0).getTopologypath();
								for(int i=0;i<picture1.size();i++)
								{
									if(path.equals(picture1.get(i).getTopologypath()))
									{
										SWPpic.add(picture1.get(i));
										picture1.remove(i);
										i--;
									}
								}
								newpicture.add(SWPpic);
							}
							for(int i=0;i<newpicture.size();i++)//取出name
							{
								String picname ="";
									for(int j=0;j<newpicture.get(i).size();j++)
									{
										if(j == (newpicture.get(i).size()-1)){//不等于第一个和最后一个 则不加、
											picname +=newpicture.get(i).get(j).getProfessional().getProfessionalname();
										}
										else{
											picname +=newpicture.get(i).get(j).getProfessional().getProfessionalname()+"、";
										}
									}
									chapter7.add(TabletitlePicture(flage[flagei]+"、"+picname+"主干课拓扑图"));
									chapter7.add(picture(newpicture.get(i).get(0).getTopologypath()));
								}
						}
						else{//部分方向 
							chapter7.add(TabletitlePicture(flage[flagei]+"、"+picture1.get(0).getDepartment().getDepartmentCname()+"主干课拓扑图"));
							chapter7.add(picture(picture1.get(0).getTopologypath()));
						}
						document.add(chapter7);
					}
					else
					{
						document.add(Tabletitle2("该专业没有上传拓扑图"));
					}
					flagei=0;
				}
				else document.add(Tabletitle2("没有填写培养计划基本信息"));
		}
		document.close();
		outputStream.flush();
		
	}
	public void school() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		String name="全校培养计划.doc";
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);
		document.open();
		String[] collegenum ={"01","02","03","04","05","06","07","08","09","10","11","12","13","20"};
		for(int ci=0;ci < collegenum.length;ci++)
		{
			List<Department> departmentnum = ptBasicInformationDao.findCollegeProNum(collegenum[ci]);
			for(int di=0;di<departmentnum.size();di++)
			{
				Department departmentnumid = departmentnum.get(di);
				String pid = (String)departmentnumid.getDepartmentid();
				
				
				
				List<PTBasicInformation> professionlist=ptBasicInformationDao.findprofession1(pid);//查询出这个专业的培养计划基本信息	
				List<Ability> abilitylist = ptBasicInformationDao.findAbility(pid);
				MainTainOfPTTag professionTag = ptBasicInformationDao.findMainTainOfPTTag(pid);//查询总体安排标志表，判断该专业是否分方向
				List<MainTainOfPT> professionlist2 = ptBasicInformationDao.findprofession2(pid);//查询该专业的总体安排表
				List<TrainingEvents> professionlist3= ptBasicInformationDao.findprofession3(pid);//查询培养事件 
				List<TrainingEvents> professionlist_lilunke= ptBasicInformationDao.findprofession3_lilunke(pid);//查询培养事件 
				List professionlessonlist[][][]=ptBasicInformationDao.findprofession6(pid);//每个方向的公共必选课","公共选修课","专业必选课","专业选修课"在每个学期的课程
				List professionpracticelist[][] = ptBasicInformationDao.findprofession7(pid);//每个专业方向的在每个学期的集中实践课
				List[] professionlist4 = ptBasicInformationDao.findprofession4(pid);//专业必选课
				List<TheoreticalLesson> listprofessional = ptBasicInformationDao.findprofession8(pid);
				List<TheoreticalLesson> gongbixuankelist = ptBasicInformationDao.findgongbixuankelist(pid);//公共必选课

				List[] professionlist51 = ptBasicInformationDao.findProElec1(pid);//专业选修课模块1
				List<List<List<TheoreticalLesson>>> proElec_IS = ptBasicInformationDao.findProElec1_IS(pid);//查询各专业方向的专业选修课模块1中的课
				List[] professionlist52 = ptBasicInformationDao.findProElec2(pid);//专业选修课模块2
				List<List<TheoreticalLesson>> xueweike = ptBasicInformationDao.findxueweike(pid);//理论课中的学位课
				List<List<PracticeLesson>> xueweike_shijian = ptBasicInformationDao.findxueweike_shijian(pid);//查询实践课中的理论课
				List[] professionlist6 = ptBasicInformationDao.findprofession5(pid);//专业的实践课
				List<Professional> proNum = ptBasicInformationDao.findProNum(pid);//查询专业的专业方向个数
				List<ScoreThreshold> scorelist = ptBasicInformationDao.findscore(pid);//查询每个专业的总学分
				
				List<List<PracticeLesson>> qibaxueqiList = ptBasicInformationDao.findqibaxueqiList(pid);//查询7、8学期的毕业设计
				//List<List<PracticeLesson>> biyeshejiList = ptBasicInformationDao.findbiyeshejiList(pid);//查询7、8和第8学期的毕业设计
					if(professionlist != null)
					{
						document.setPageSize(PageSize.A4);
						PTBasicInformation changexuenian=professionlist.get(0);
						if(changexuenian.getLearningTime().equals("四年")) xuenian=4;
						if(changexuenian.getLearningTime().equals("五年")) xuenian=5;
						PTBasicInformation profession1 = null;
						if(professionlist.size() ==0) ServletActionContext.getRequest().setAttribute("msg", "没有填写培养计划基本信息！");
						else profession1 =professionlist.get(0);
						Chapter chapter1=new Chapter(1);
						//Paragraph document=new Paragraph();//列，行		
						Strmajor = profession1.getDepartment().getDepartmentCname();
						chapter1.add(Tabletitle2(Strmajor + "专业培养计划"));
						
						String EducationalSystem = "学    制：" + profession1.getLearningTime()
								+ "                                            启用年级 ："+ profession1.getEnableGrade()+"级";
						Paragraph study = new Paragraph(EducationalSystem, new RtfFont("宋体",
								12, Font.BOLD, Color.BLACK));
						chapter1.add(study);
						chapter1.add(settitle("培养目标: "));
						chapter1.add(setcontent(profession1.getTrainingObjectives()));
						chapter1.add(settitle("培养要求: "));
						chapter1.add(setcontent(profession1.getTrainingRequirements()));
						chapter1.add(settitle("毕业生应获得以下几个方面的知识和能力： "));
				
						for(int ai=0;ai<abilitylist.size();ai++)
						{
							Ability ability = abilitylist.get(ai);
							chapter1.add(settitle(String.valueOf(ai+1)+"、"+ability.getAbilityname()));
							if(ability.getAbilitycontent() != null)
								chapter1.add(setcontent(ability.getAbilitycontent()));
						}
				
						chapter1.add(settitle("主干学科："));
						chapter1.add(setcontent(profession1.getMainCourses()));
						chapter1.add(settitle("学位课："));
						chapter1.add(setcontent(profession1.getDegreeCourses()));
						chapter1.add(settitle("授予学位："));
						chapter1.add(setcontent(profession1.getDegree()));
								//document.newPage();
						document.add(chapter1);
						
						Chapter chapter2=new Chapter(2);
						if(proNum != null && proNum.size() != 0)
						{
							if( professionTag != null && professionTag.getTag().equals("0") && professionlist2 != null && professionlist2.size() != 0)
							{
								chapter2.add(Tabletitle2("一、培养计划总体安排表"));
								chapter2.add(GeneralArrangement(professionlist2,professionlist3,profession1));//总体安排表、培养事件、学年
								document.setPageSize(PageSize.A4.rotate());
							}
							else if(professionTag != null && professionTag.getTag().equals("1")&& professionlist2 != null && professionlist2.size() != 0)
							{
								List<List<Professional>> haveProfessionalList = new ArrayList<List<Professional>>();//录入了总体安排的专业方向
								List<ApplicationMainTainOfPT> newApplicationMainTainOfPT = new ArrayList<ApplicationMainTainOfPT>();//存储录入了的方向的内容，用于判断是否相同
								for(int i=0;i<proNum.size();i++){
									List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(proNum.get(i));
									if(applicationMainTainOfPTlist != null)
									{
										newApplicationMainTainOfPT.add(applicationMainTainOfPTlist.get(0));
									}
								}
								//将相同总体安排表的专业方向放一起
								while(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() != 0)
								{
									List<Professional> professionallist = new ArrayList<Professional>();
									professionallist.add(newApplicationMainTainOfPT.get(0).getProfessional());
									
									if(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() > 1){
										for(int i=1;i<newApplicationMainTainOfPT.size();i++){
											if(newApplicationMainTainOfPT.get(0).getMainTainOfPT() == newApplicationMainTainOfPT.get(i).getMainTainOfPT())
											{
												professionallist.add(newApplicationMainTainOfPT.get(i).getProfessional());
												newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(i));
												i--;
											}	
										}
									}
									newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(0));
									haveProfessionalList.add(professionallist);
								}
								for(int zi=0;zi<haveProfessionalList.size();zi++)
								{
									List<Professional> newProfessionallist = haveProfessionalList.get(zi);
									String Professionalname ="";
									for(int zj=0;zj<newProfessionallist.size();zj++)
									{
										if(zj == (newProfessionallist.size()-1))
										{//不等于第一个和最后一个 则不加、
											Professionalname +=newProfessionallist.get(zj).getProfessionalname();
										}
										else
										{
											Professionalname +=newProfessionallist.get(zj).getProfessionalname()+"、";
										}
									}
									//从应用表查总体安排
									List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionallist.get(0));
									if(applicationMainTainOfPTlist != null && applicationMainTainOfPTlist.size() !=0)
									{
										List<MainTainOfPT> newmainTainOfPTlist = new ArrayList<MainTainOfPT>();
										for(int i=0;i<applicationMainTainOfPTlist.size();i++)
										{
											newmainTainOfPTlist.add(applicationMainTainOfPTlist.get(i).getMainTainOfPT());
										}
										//从应用表查培养事件
										List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalEvents(newProfessionallist.get(0));
										List<TrainingEvents> newtrainingEventslist = new ArrayList<TrainingEvents>();
										if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0){
											for(int i=0;i<applicationTrainingEventslist.size();i++){
												newtrainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
											}
										}
										chapter2.add(Tabletitle2("一、"+Professionalname+"培养计划总体安排表"));
										chapter2.add(GeneralArrangement(newmainTainOfPTlist,newtrainingEventslist,profession1));//总体安排表、培养事件、学年
										document.setPageSize(PageSize.A4.rotate());
									}

								}
							}
						}
						else if(professionlist2 != null && professionlist2.size() != 0) {
							chapter2.add(Tabletitle2("一、培养计划总体安排表"));
							chapter2.add(GeneralArrangement(professionlist2,professionlist3,profession1));//总体安排表、培养事件、学年
							document.setPageSize(PageSize.A4.rotate());
						}

					
						if(professionlessonlist[0][0][0].size() != 0 )
						{
							if(proNum != null && proNum.size() != 0)
							{
								if(professionTag != null && professionTag.getTag().equals("1"))
								{
									for(int gi3=0;gi3<professionlessonlist.length;gi3++)
									{
										List GraRverlist=professionlessonlist[gi3][0][0];
										List[][] Gradlist=professionlessonlist[gi3];
										List[] GradPrac=professionpracticelist[gi3];
										TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
										
										List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalEventslilunke(professname.getProfessional());
										List<TrainingEvents> newtrainingEventslist = new ArrayList<TrainingEvents>();
										if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0){
											for(int i=0;i<applicationTrainingEventslist.size();i++){
												newtrainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
											}
										}
										if(Gradlist !=null ||Gradlist.length !=0)
										{
											if(scorelist != null && scorelist.size() != 0)
											{
												ScoreThreshold score = scorelist.get(0);
												chapter2.add(Tabletitle2("二、"+professname.getProfessional().getProfessionalname()+"毕业生学分要求"));
												chapter2.add(Graducation1(Gradlist,newtrainingEventslist,GradPrac,profession1,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
											}
										}
									}
								}
								else
								{
									for(int gi3=0;gi3<professionlessonlist.length;gi3++)
									{
										List GraRverlist=professionlessonlist[gi3][0][0];
										List[][] Gradlist=professionlessonlist[gi3];
										List[] GradPrac=professionpracticelist[gi3];
										TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
										
										if(Gradlist !=null ||Gradlist.length !=0)
										{
											if(scorelist != null && scorelist.size() != 0 && professionlist_lilunke != null && professionlist_lilunke.size() !=0)
											{
												ScoreThreshold score = scorelist.get(0);
												
												chapter2.add(Tabletitle2("二、"+professname.getProfessional().getProfessionalname()+"毕业生学分要求"));
												chapter2.add(Graducation1(Gradlist,professionlist_lilunke,GradPrac,profession1,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
											}
										}
									}
								}
							}
							else
							{
								if(scorelist != null && scorelist.size() != 0 && professionlist_lilunke != null && professionlist_lilunke.size() !=0)
								{
									ScoreThreshold score = scorelist.get(0);
									chapter2.add(Tabletitle2("二、毕业生学分要求"));
									//每个方向的公共必选课","公共选修课","专业必选课","专业选修课"在每个学期的课程；查询培养事件；每个专业方向的在每个学期的集中实践课；学年；学分
									chapter2.add(Graducation2(professionlessonlist,professionlist_lilunke,professionpracticelist,profession1,score,proElec_IS.get(0),qibaxueqiList.get(0),pid));
								}
								else chapter2.add(Tabletitle2("二、该专业没有毕业学分要求"));
							}
						}
						else  chapter2.add(Tabletitle2("二、该专业没有毕业学分要求"));
						document.setPageSize(PageSize.A4.rotate());
						document.add(chapter2);

					
				
						Chapter chapter4 = new Chapter(4);
						document.setPageSize(PageSize.A4);
						if(professionlist4[0].size() != 0 )
						{
							chapter4.add(Tabletitle2("三、教学安排"));
							chapter4.add(TeachSchedule(professionlist4,listprofessional,professionlist51,professionlist52,pid));
							Paragraph study1=new Paragraph("1.周学时后有“/”的表示该课程要在前半学期开课，周学时前有“/”的表示该课程在后半学期开课。\n2.课程名称后有“*”表示该课程是学位课。\n3.“专业选修课程模块Ⅰ”为必选的专业选修课。",new RtfFont("Times New Roman", (float)10.5, Font.NORMAL,Color.BLACK));
							chapter4.add(study1);
						}
						else document.add(Tabletitle2("没有填写该专业选课"));
				
						for(int x=0;x<professionlist6.length;x++)
						{
							List GraRverlist=professionlist6[x];
							if(professionlist6[x].size() !=0 && professionlist6.length != 1)
							{
								PracticeLesson professname =(PracticeLesson)GraRverlist.get(0);
								chapter4.add(Tabletitle2("四、"+professname.getProfessional().getProfessionalname()+"集中实践性教学环节安排"));
								chapter4.add(FocusPractice(GraRverlist,proNum));
							}
							else {
								chapter4.add(Tabletitle2("四、集中实践性教学环节安排"));
								chapter4.add(FocusPractice(GraRverlist,proNum));
							}
						}
						document.add(chapter4);
						
						Chapter chapter5 = new Chapter(5);
						
						if(professionlessonlist[0][0][0].size() != 0)
						{
							if(professionlessonlist[0][0].length !=1)//分方向的集中实践课
								for(int gi3=0;gi3<professionlessonlist.length;gi3++)
								{
									List GraRverlist=professionlessonlist[gi3][0][0];
									List[][] Gradlist=professionlessonlist[gi3];
									List[] GradPrac = professionpracticelist[gi3];
									List<TheoreticalLesson> professionlist41=professionlist4[gi3];
									List<TheoreticalLesson> isXWK = xueweike.get(gi3);
									if(Gradlist ==null ||Gradlist.length ==0)
										ServletActionContext.getRequest().setAttribute("msg", "填写信息不完整!");
									else
									{
										if(scorelist != null && scorelist.size() != 0)
										{
											ScoreThreshold score = scorelist.get(0);
											TheoreticalLesson professname =(TheoreticalLesson)GraRverlist.get(0);
											chapter5.add(Tabletitle2("五、"+professname.getProfessional().getProfessionalname()+"专业培养计划说明"));
											chapter5.add(TrainingInstruction(Gradlist,GradPrac,profession1,professionlist3,professionlist41,isXWK,xueweike_shijian.get(gi3),gongbixuankelist,score,proElec_IS.get(gi3),qibaxueqiList.get(gi3),pid));
										}
										else chapter5.add(Tabletitle2("五、该专业没有毕业总学分要求"));
										
									}
								}
							else //部分方向的集中实践课
							{
								if(scorelist != null && scorelist.size() != 0)
								{
									ScoreThreshold score = scorelist.get(0);
									chapter5.add(Tabletitle2("五、专业培养计划说明"));
									List<TheoreticalLesson> xueweikelist=xueweike.get(0);
									chapter5.add(TrainingInstruction2(professionlessonlist,professionlist3,professionpracticelist,profession1,professionlist4[0],xueweikelist,xueweike_shijian.get(0),gongbixuankelist,score,proElec_IS.get(0),qibaxueqiList.get(0),pid));
								}
								else chapter5.add(Tabletitle2("五、该专业没有毕业总学分要求"));
							
							}
						}
						else document.add(Tabletitle2("该专业填写信息不完整"));

						
						
						String[] flage={"六","七","八","九"};
						int flagei=0;
						TrainingAnother otherInform = ptBasicInformationDao.findgraduation(pid);
						if(otherInform != null && !otherInform.getExtracurricular().equals(""))
						{
							chapter5.add(Tabletitle2(flage[flagei]+"、课外安排与要求"));
							chapter5.add(setcontent(otherInform.getExtracurricular()));
							flagei++;
						}
						if(otherInform != null && !otherInform.getBusinessPractice().equals(""))
						{
							chapter5.add(Tabletitle2(flage[flagei]+"、企业集中实践安排与要求"));
							chapter5.add(setcontent(otherInform.getBusinessPractice()));
							flagei++;
						}
						if(otherInform != null && !otherInform.getBusinessTeacher().equals(""))
						{
							chapter5.add(Tabletitle2(flage[flagei]+"、企业教师授课安排与要求"));
							chapter5.add(setcontent(otherInform.getBusinessTeacher()));
							flagei++;
						}
						document.add(chapter5);
						
						Chapter chapter7 = new Chapter(7);
						document.setPageSize(PageSize.A4.rotate());
						List<Topology> picture1 =ptBasicInformationDao.findpicture1(pid);
						List<List<Topology>> newpicture = new ArrayList<List<Topology>>();
						if(picture1.size() != 0)//判断有没有拓扑图
						{
							if(picture1.size() != 1)//分方向
							{
								while(picture1 !=null && picture1.size()!=0)//每次取第一个值和和后面的值判断是否相同
								{
									List<Topology> SWPpic = new ArrayList<Topology>();
									String path = picture1.get(0).getTopologypath();
									for(int i=0;i<picture1.size();i++)
									{
										if(path.equals(picture1.get(i).getTopologypath()))
										{
											SWPpic.add(picture1.get(i));
											picture1.remove(i);
											i--;
										}
									}
									newpicture.add(SWPpic);
								}
								for(int i=0;i<newpicture.size();i++)//取出name
								{
									String picname ="";
										for(int j=0;j<newpicture.get(i).size();j++)
										{
											if(j == (newpicture.get(i).size()-1)){//不等于第一个和最后一个 则不加、
												picname +=newpicture.get(i).get(j).getProfessional().getProfessionalname();
											}
											else{
												picname +=newpicture.get(i).get(j).getProfessional().getProfessionalname()+"、";
											}
										}
										chapter7.add(TabletitlePicture(flage[flagei]+"、"+picname+"主干课拓扑图"));
										chapter7.add(picture(newpicture.get(i).get(0).getTopologypath()));
									}
							}
							else{//部分方向 
								chapter7.add(TabletitlePicture(flage[flagei]+"、"+picture1.get(0).getDepartment().getDepartmentCname()+"主干课拓扑图"));
								chapter7.add(picture(picture1.get(0).getTopologypath()));
							}
							document.add(chapter7);
						}
						else
						{
							document.add(Tabletitle2("该专业没有上传拓扑图"));
						}
						flagei=0;
					}
					else document.add(Tabletitle2("没有填写培养计划基本信息"));
			}
		}

	document.close();
	outputStream.flush();
	}
	
	
	public void revisebjpy(PTBasicInformation ptBasicInformation) {
			bjpy(ptBasicInformation);
			String tag = "revise";
			ServletActionContext.getRequest().setAttribute("tag", tag);
		}
		
	
}
	
