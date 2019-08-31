package com.tpm.service;

import java.awt.Color;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import com.tpm.dao.ExportCurriculumMatrixDao;
import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.Department;
import com.tpm.entity.Professional;

public class ExportCurriculumMatrixService {
	private ExportCurriculumMatrixDao exportCurriculumMatrixDao;

	public void setExportCurriculumMatrixDao(
			ExportCurriculumMatrixDao exportCurriculumMatrixDao) {
		this.exportCurriculumMatrixDao = exportCurriculumMatrixDao;
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
	public Cell TeachCellContentChinese1(String s,int x,int y,int z) throws Exception{//写入表格的内容中文，宋10.5（五号）正常
		RtfFont contextFont1 = new RtfFont("Times New Roman", 12, Font.BOLD,Color.BLACK);
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
	public Paragraph TabletitlePicture(String title) throws Exception{//设置标题类型 ，宋12  
		RtfFont titleFont = new RtfFont("宋体", 12, Font.NORMAL,Color.BLACK);//这个构造方法里面：第一个参数是字体类型，第二个参数是字体大小，第三个参数是字体格式是加粗还是正常，第四个参数是字体的颜色	
		Paragraph tabletitle=new Paragraph(title,titleFont );
		tabletitle.setAlignment(Element.ALIGN_LEFT);//居中
		tabletitle.setSpacingAfter(0);  //段前距离
		tabletitle.setSpacingBefore(0);//段后距离
	//	tabletitle.setLeading(1);//行间距
		return tabletitle;
	}
	public void exportCurriculumMatrix(String departmentID, List<Curriculum> curriculumlist, List<List<Curriculum>> curriculumlist_pro,
			List<Professional> professionalList, List<Ability> abilitielist, List<List<Analysis>> anlist, 
			List<List<List<CurriculumMatrix>>> curriculumMatrixlist, List<List<List<List<CurriculumMatrix>>>> curriculumMatrixlist_pro) throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();
		OutputStream outputStream=response.getOutputStream();
		response.setContentType("application/msword");
		
		Department departmentname = exportCurriculumMatrixDao.findDepartmentName(departmentID);
		String name=departmentname.getDepartmentCname()+"专业课程矩阵.doc";
		String name1=departmentname.getDepartmentCname()+"专业课程矩阵";
		response.addHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("gb2312"),"iso-8859-1"));
		Document document = new Document(PageSize.A4,71,71,71,71);
		RtfWriter2.getInstance(document,outputStream);
		document.open();
		document.setPageSize(PageSize.A4.rotate());
		document.add(TabletitlePicture("一、"+name1));
		int curriculumlistNum=curriculumlist.size();
		int curriculumlist_proNum=0;
		int anlistNum=0;
		for(int i=0;i<curriculumlist_pro.size();i++)
		{
			curriculumlist_proNum +=curriculumlist_pro.get(i).size();
		}
		for(int i=0;i<anlist.size();i++)
		{
			anlistNum += anlist.get(i).size();
		}
		int sumRow=curriculumlistNum+curriculumlist_proNum+2;//矩阵的行数
		int sumColumn=anlistNum+2;//矩阵的列数
		float[] widths=new float[sumColumn];//矩阵每格的宽度
		widths[0]=4;widths[1]=10;
		for(int i=2;i<sumColumn;i++)widths[i]=86/sumColumn;
		Table matrix=table(sumColumn, sumRow,widths);//列，行
		
		matrix.addCell(TeachCellContentChinese("课程名称",2,2,3));
		for(int i=0;i<abilitielist.size();i++)//对应的能力个数
		{
			int j=i;
			matrix.addCell(TeachCellContentChinese1(String.valueOf(j+1),1,anlist.get(i).size(),3));
		}
		for(int i=0;i<anlist.size();i++)//对应指标分解点
		{
			for(int j=0;j<anlist.get(i).size();j++)
			{
				int z=j;
				matrix.addCell(TeachCellContentRoman(String.valueOf(z+1),3));
			}
		}
		/***生成公共选修、必选和专业必选的课程矩阵***/
		for(int i=0;i<curriculumlist.size();i++)
		{
			matrix.addCell(TeachCellContentChinese(curriculumlist.get(i).getCurriculumCname(),1,2,3));
			for(int j=0;j<curriculumMatrixlist.get(i).size();j++)
			{
				for(int x=0;x<curriculumMatrixlist.get(i).get(j).size();x++)
				{
						String value=curriculumMatrixlist.get(i).get(j).get(x).getScore();
						if(value.equals("K"))
							matrix.addCell(TeachCellContentRoman("",3));
						else
							matrix.addCell(TeachCellContentRoman(value,3));
				}
			}
		}
		/*1、若是没有方向此处不显示
		 *2、若是有方向按照方向显示每个方向的专业选修课
		 * 生成专业选修的课程矩阵*/
		
		if(professionalList != null && professionalList.size() !=0)
		{
			for(int ip=0;ip<professionalList.size();ip++)
			{
				matrix.addCell(TeachCellContentChinese(professionalList.get(ip).getProfessionalname(),curriculumlist_pro.get(ip).size(),1,3));
				for(int i=0;i<curriculumlist_pro.get(ip).size();i++)
				{
					matrix.addCell(TeachCellContentChinese(curriculumlist_pro.get(ip).get(i).getCurriculumCname(),1,1,3));
					for(int j=0;j<curriculumMatrixlist_pro.get(ip).get(i).size();j++)
					{
						for(int x=0;x<curriculumMatrixlist_pro.get(ip).get(i).get(j).size();x++)
						{
								String value=curriculumMatrixlist_pro.get(ip).get(i).get(j).get(x).getScore();
								if(value.equals("K"))
									matrix.addCell(TeachCellContentRoman("",3));
								else
									matrix.addCell(TeachCellContentRoman(value,3));
						}
					}
				}
			}
		}
		
		document.add(matrix);
		
		
		
		
		document.close();
		outputStream.flush();
	}
	
}
