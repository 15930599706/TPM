package com.tpm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.AbilityDao;
import com.tpm.dao.AnalysisDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.CurriculumMatrixDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.NatureOfCourseDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.Department;
import com.tpm.entity.NatureOfCourse;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.ProfessionalLegalize;
import com.tpm.entity.TheoreticalLesson;
@Transactional
public class CurriculumMatrixService {
	private CurriculumMatrixDao curriculumMatrixDao;

	public void setCurriculumMatrixDao(CurriculumMatrixDao curriculumMatrixDao) {
		this.curriculumMatrixDao = curriculumMatrixDao;
	}
	private CurriculumDao curriculumDao;
	
	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	private TheoreticalPlanDao theoreticalPlanDao;
	private PracticePlanDao practicePlanDao;
	private AbilityDao abilityDao;
	private AnalysisDao analysisDao;
	private NatureOfCourseDao natureOfCourseDao;
	private ProfessionalDao professionalDao;
	public void setProfessionalDao(ProfessionalDao professionalDao) {
		this.professionalDao = professionalDao;
	}
	public void setNatureOfCourseDao(NatureOfCourseDao natureOfCourseDao) {
		this.natureOfCourseDao = natureOfCourseDao;
	}
	public void setAnalysisDao(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}
	public void setAbilityDao(AbilityDao abilityDao) {
		this.abilityDao = abilityDao;
	}
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	public List<Curriculum> findcurriculumbydepartment(String departmentid) {
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Professional> professionalList = professionalDao.findbydepartment(department);
		List<TheoreticalLesson> theoreticalLessonlist = theoreticalPlanDao.findTheoreticalLessonDepartment(department);
		List<PracticeLesson> practiceLessonlist = practicePlanDao.findpracticeLessonDepartment(department);
		List<Curriculum> curriculumlist = new ArrayList<Curriculum>();
		List<NatureOfCourse> natureOfCourseList = natureOfCourseDao.getAll();
		
			if(professionalList == null || professionalList.size() ==0)
			{
				if(theoreticalLessonlist != null)
				{
					for(int k=0;k<natureOfCourseList.size();k++)
					{
						for(int m=0;m<theoreticalLessonlist.size();m++)				
						{
							//非专业必修课时不用加是否认证判断
							if(theoreticalLessonlist.get(m).getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals(natureOfCourseList.get(k).getNatureOfCourseid()) && !natureOfCourseList.get(k).getNatureOfCourseid().equals("12"))
							{	
								if(!curriculumlist.contains(theoreticalLessonlist.get(m).getCurriculum()))
								{
									curriculumlist.add(theoreticalLessonlist.get(m).getCurriculum());
								}
							}
							//专业必修课需要加认证判断
							if(theoreticalLessonlist.get(m).getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals(natureOfCourseList.get(k).getNatureOfCourseid()) && natureOfCourseList.get(k).getNatureOfCourseid().equals("12"))
							{	
								if(theoreticalLessonlist.get(m).getRenzheng() == null || theoreticalLessonlist.get(m).getRenzheng().equals("是"))
								{
									if(!curriculumlist.contains(theoreticalLessonlist.get(m).getCurriculum()))
									{
										curriculumlist.add(theoreticalLessonlist.get(m).getCurriculum());
									}
								}
								
							}
						}
					}
				}
				if(practiceLessonlist != null)
				{
					for(int k=0;k<natureOfCourseList.size();k++)
					{
						for(int m=0;m<practiceLessonlist.size();m++)
						{	
							//非专业必修课时不用加是否认证判断
							if(practiceLessonlist.get(m).getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals(natureOfCourseList.get(k).getNatureOfCourseid()) && !natureOfCourseList.get(k).getNatureOfCourseid().equals("12"))
							{
								if(!curriculumlist.contains(practiceLessonlist.get(m).getCurriculum()))
								{
									curriculumlist.add(practiceLessonlist.get(m).getCurriculum());
								}
							}
							//专业必修课需要加认证判断
							if(practiceLessonlist.get(m).getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals(natureOfCourseList.get(k).getNatureOfCourseid()) && natureOfCourseList.get(k).getNatureOfCourseid().equals("12"))
							{
								if(practiceLessonlist.get(m).getRenzheng() == null || practiceLessonlist.get(m).getRenzheng().equals("是"))
								{
									if(!curriculumlist.contains(practiceLessonlist.get(m).getCurriculum()))
									{
										curriculumlist.add(practiceLessonlist.get(m).getCurriculum());
									}
								}
								
							}
						}
					}
				}
			}
			
			//分方向
			if(professionalList != null && professionalList.size() !=0)
			{
				if(theoreticalLessonlist != null)
				{
					for(int k=0;k<natureOfCourseList.size();k++)
					{
						for(int m=0;m<theoreticalLessonlist.size();m++)				
						{
							if(theoreticalLessonlist.get(m).getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals(natureOfCourseList.get(k).getNatureOfCourseid()) && !natureOfCourseList.get(k).getNatureOfCourseid().equals("12"))
							{
								if(!curriculumlist.contains(theoreticalLessonlist.get(m).getCurriculum()))
								{
									curriculumlist.add(theoreticalLessonlist.get(m).getCurriculum());
								}
							}
						}
					}
				}
				if(practiceLessonlist != null)
				{
					for(int k=0;k<natureOfCourseList.size();k++)
					{
						for(int m=0;m<practiceLessonlist.size();m++)
						{
							if(practiceLessonlist.get(m).getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals(natureOfCourseList.get(k).getNatureOfCourseid()) && !natureOfCourseList.get(k).getNatureOfCourseid().equals("12"))
							{
								if(!curriculumlist.contains(practiceLessonlist.get(m).getCurriculum()))
								{
									curriculumlist.add(practiceLessonlist.get(m).getCurriculum());
								}
							}
						}
					}
				}
				
			}

		

		return curriculumlist;
	}
	//分专业方向时查询每个专业方向的专业选修课
	public List<List<Curriculum>> findcurriculumbydepartment_pro(String departmentid) {
		Department department = departmentDao.get(departmentid);
		List<Professional> professionalList = professionalDao.findbydepartment(department);
		List<List<TheoreticalLesson>> theoreticalLessonlist_pro = theoreticalPlanDao.findTheoreticalLessonProfessional(professionalList);
		List<List<PracticeLesson> > practiceLesson_pro = practicePlanDao.findpracticeLessonProfessional(professionalList);
		List<List<Curriculum>> curriculumlist_pro = new ArrayList<List<Curriculum>>();//存储有专业方向的课程
		
		for(int i=0;i<theoreticalLessonlist_pro.size();i++)
		{
			List<Curriculum> curriculumlist = new ArrayList<Curriculum>();
			for(int j=0;j<theoreticalLessonlist_pro.get(i).size();j++)
			{
				curriculumlist.add(theoreticalLessonlist_pro.get(i).get(j).getCurriculum());
			}
			for(int j=0;j<practiceLesson_pro.get(i).size();j++)
			{
				curriculumlist.add(practiceLesson_pro.get(i).get(j).getCurriculum());
			}
			curriculumlist_pro.add(curriculumlist);
		}
		
		return curriculumlist_pro;
	}
	
	public List<Professional> findProfessionbydepartment(String departmentid) {
		Department department = departmentDao.get(departmentid);
		List<Professional> professionalList = professionalDao.findbydepartment(department);
		return professionalList;
	}
	
	
	public List<Ability> findabilitielist(String departmentid) {
		Department department = departmentDao.get(departmentid);
		List<Ability> abilitielist = abilityDao.getbydepartment(department);
		return abilitielist;
	}
	public List<List<Analysis>> findanalysisbyability(List<Ability> abilitielist) {
		List<List<Analysis>> lists = new ArrayList<List<Analysis>>();
		for(int i=0;i<abilitielist.size();i++){
			List<Analysis> analysis = analysisDao.getbyability(abilitielist.get(i));
			lists.add(analysis);
		}
		return lists;
	}
	public List<List<List<CurriculumMatrix>>> findcurriculumMatrixbyall(List<Curriculum> curriculumlist, List<Ability> abilitielist,List<List<Analysis>> anlist) {
		List<List<List<CurriculumMatrix>>> lists = new ArrayList<List<List<CurriculumMatrix>>>();
		for(int i=0;i<curriculumlist.size();i++)
		{
			List<List<CurriculumMatrix>> curriculumMatrixlist = new ArrayList<List<CurriculumMatrix>>();
			for(int j=0;j<abilitielist.size();j++)
			{
				List<CurriculumMatrix> newCurriculumMatrixs = new ArrayList<CurriculumMatrix>();
				List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandab(curriculumlist.get(i),abilitielist.get(j));
				for(Integer k=0;k<anlist.get(j).size();k++)
				{
					boolean flag = true;
					if(curriculumMatrixs != null)
					{
						for(int l=0;l<curriculumMatrixs.size();l++)
						{
							if(curriculumMatrixs.get(l).getCount()==(k+1)){
								flag = false;
								newCurriculumMatrixs.add(curriculumMatrixs.get(l));
								curriculumMatrixs.remove(l);
							}
						}
					}
					if(flag)
					{
						CurriculumMatrix curriculumMatrix = new CurriculumMatrix();
						curriculumMatrix.setScore("K");
						newCurriculumMatrixs.add(curriculumMatrix);
					}
				}
				curriculumMatrixlist.add(newCurriculumMatrixs);
				if(curriculumMatrixs != null)
				{
					for(int z=0;z<curriculumMatrixs.size();z++)
					{
						curriculumMatrixDao.delete(curriculumMatrixs.get(z));
					}
				}
			}
			lists.add(curriculumMatrixlist);
		}
		return lists;
	}
	
	public List<List<List<List<CurriculumMatrix>>>> findcurriculumMatrixbyall_pro(
			List<List<Curriculum>> curriculumlist_pro,
			List<Ability> abilitielist, List<List<Analysis>> anlist, List<Professional> professionalList) {
		// TODO Auto-generated method stub
		List<List<List<List<CurriculumMatrix>>>> curriculumMatrixlist_pro = new ArrayList<List<List<List<CurriculumMatrix>>>>();
		for(int m=0;m<curriculumlist_pro.size();m++)
		{
			List<List<List<CurriculumMatrix>>> lists = new ArrayList<List<List<CurriculumMatrix>>>();
			for(int i=0;i<curriculumlist_pro.get(m).size();i++){
				List<List<CurriculumMatrix>> curriculumMatrixlist = new ArrayList<List<CurriculumMatrix>>();
				for(int j=0;j<abilitielist.size();j++){
					List<CurriculumMatrix> newCurriculumMatrixs = new ArrayList<CurriculumMatrix>();
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandab_pro(curriculumlist_pro.get(m).get(i),abilitielist.get(j),professionalList.get(m));
					for(Integer k=0;k<anlist.get(j).size();k++){
						boolean flag = true;
						if(curriculumMatrixs != null){
							for(int l=0;l<curriculumMatrixs.size();l++){
								if(curriculumMatrixs.get(l).getCount()==(k+1)){
									flag = false;
									newCurriculumMatrixs.add(curriculumMatrixs.get(l));
									curriculumMatrixs.remove(l);
								}
							}
						}
						if(flag){
							CurriculumMatrix curriculumMatrix = new CurriculumMatrix();
							curriculumMatrix.setScore("K");
							newCurriculumMatrixs.add(curriculumMatrix);
						}
					}
					curriculumMatrixlist.add(newCurriculumMatrixs);
					if(curriculumMatrixs != null){
						for(int z=0;z<curriculumMatrixs.size();z++){
							curriculumMatrixDao.delete(curriculumMatrixs.get(z));
						}
					}
				}
				lists.add(curriculumMatrixlist);
			}
			curriculumMatrixlist_pro.add(lists);
		}
		
		return curriculumMatrixlist_pro;
	}		
	
	public void add(String string, Curriculum curriculum, Ability ability, int i) {
		CurriculumMatrix curriculumMatrix = curriculumMatrixDao.getbyall(curriculum,ability,i);
		if(curriculumMatrix != null)
		{
			curriculumMatrix.setScore(string);
			curriculumMatrixDao.update(curriculumMatrix);
		}
		else
		{
			curriculumMatrix = new CurriculumMatrix();
			curriculumMatrix.setAbility(ability);
			curriculumMatrix.setCurriculum(curriculum);
			curriculumMatrix.setCount(i);
			curriculumMatrix.setScore(string);
			curriculumMatrixDao.add(curriculumMatrix);
		}				
	}
	
	//专业选修课的添加
	public void add(String string, Curriculum curriculum, Ability ability,int i, Professional professional) {
		CurriculumMatrix curriculumMatrix = curriculumMatrixDao.getbyall(curriculum,ability,i,professional);
		if(curriculumMatrix != null)
		{
			curriculumMatrix.setScore(string);
			curriculumMatrixDao.update(curriculumMatrix);
		}
		else
		{
			curriculumMatrix = new CurriculumMatrix();
			curriculumMatrix.setAbility(ability);
			curriculumMatrix.setCurriculum(curriculum);
			curriculumMatrix.setCount(i);
			curriculumMatrix.setScore(string);
			curriculumMatrix.setProfessional(professional);
			curriculumMatrixDao.add(curriculumMatrix);
		}				
		
	}
	
	public void update(String string, Curriculum curriculum, Ability ability,int i) {
		CurriculumMatrix curriculumMatrix = curriculumMatrixDao.getbyall(curriculum,ability,i);
		curriculumMatrix.setScore(string);
		curriculumMatrixDao.update(curriculumMatrix);
		
	}
	
	//专业选修课的更新
	public void update(String string, Curriculum curriculum, Ability ability,int i, Professional professional) {
		CurriculumMatrix curriculumMatrix = curriculumMatrixDao.getbyall(curriculum,ability,i,professional);
		curriculumMatrix.setScore(string);
		curriculumMatrixDao.update(curriculumMatrix);
		
	}
	public void delete(Curriculum curriculum, Ability ability, int i) {
		CurriculumMatrix curriculumMatrix = curriculumMatrixDao.getbyall(curriculum,ability,i);
		curriculumMatrixDao.delete(curriculumMatrix);
	}
	//专业选修课的更新
	public void delete(Curriculum curriculum, Ability ability, int i,
			Professional professional) {
		CurriculumMatrix curriculumMatrix = curriculumMatrixDao.getbyall(curriculum,ability,i,professional);
		curriculumMatrixDao.delete(curriculumMatrix);
	}


	public List<ProfessionalLegalize> find_HaveDepartmentTag(String departmentID) {
		Department department = departmentDao.get(departmentID);
		List<Professional> professionalList = professionalDao.findbydepartment(department);
		List<List<TheoreticalLesson>> theoreticalLessonlist_pro = theoreticalPlanDao.findTheoreticalLessonProfessional_Pro(professionalList);
		List<List<PracticeLesson> > practiceLesson_pro = practicePlanDao.findpracticeLessonProfessional_Pro(professionalList);
		List<ProfessionalLegalize> curriculumlist = new ArrayList<ProfessionalLegalize>();		
		for(int i=0;i<theoreticalLessonlist_pro.size();i++)
		{
			
			for(int j=0;j<theoreticalLessonlist_pro.get(i).size();j++)
			{
				ProfessionalLegalize professionalLegalize = new ProfessionalLegalize();
				professionalLegalize.setCurriculum(theoreticalLessonlist_pro.get(i).get(j).getCurriculum());
				professionalLegalize.setDepartment(theoreticalLessonlist_pro.get(i).get(j).getDepartment());
				professionalLegalize.setProfessional(theoreticalLessonlist_pro.get(i).get(j).getProfessional());
				professionalLegalize.setTag(theoreticalLessonlist_pro.get(i).get(j).getRenzheng());
				curriculumlist.add(professionalLegalize);
			}
			for(int j=0;j<practiceLesson_pro.get(i).size();j++)
			{
				ProfessionalLegalize professionalLegalize = new ProfessionalLegalize();
				professionalLegalize.setCurriculum(practiceLesson_pro.get(i).get(j).getCurriculum());
				professionalLegalize.setDepartment(practiceLesson_pro.get(i).get(j).getDepartment());
				professionalLegalize.setProfessional(practiceLesson_pro.get(i).get(j).getProfessional());
				professionalLegalize.setTag(practiceLesson_pro.get(i).get(j).getRenzheng());
				curriculumlist.add(professionalLegalize);
			}
		}
		
		return curriculumlist;
	}
	public List<ProfessionalLegalize> find_NoDepartmentTag(String departmentID) {
		List<TheoreticalLesson> theoreticalLessonlist_pro = theoreticalPlanDao.findTheoreticalLessonProfessional_Department(departmentID);
		List<PracticeLesson>  practiceLesson_pro = practicePlanDao.findpracticeLessonProfessional_Department(departmentID);
		List<ProfessionalLegalize> curriculumlist = new ArrayList<ProfessionalLegalize>();	
		for(int i = 0;i<theoreticalLessonlist_pro.size();i++)
		{
			ProfessionalLegalize professionalLegalize = new ProfessionalLegalize();
			professionalLegalize.setCurriculum(theoreticalLessonlist_pro.get(i).getCurriculum());
			professionalLegalize.setDepartment(theoreticalLessonlist_pro.get(i).getDepartment());
			professionalLegalize.setTag(theoreticalLessonlist_pro.get(i).getRenzheng());
			curriculumlist.add(professionalLegalize);
		}
		for(int j=0;j<practiceLesson_pro.size();j++)
		{
			ProfessionalLegalize professionalLegalize = new ProfessionalLegalize();
			professionalLegalize.setCurriculum(theoreticalLessonlist_pro.get(j).getCurriculum());
			professionalLegalize.setDepartment(theoreticalLessonlist_pro.get(j).getDepartment());
			professionalLegalize.setTag(theoreticalLessonlist_pro.get(j).getRenzheng());
			curriculumlist.add(professionalLegalize);
		}
		return curriculumlist;
	}
	public void modifyDetermine(List<String> departmentCnameID, List<String> professionalnameID, List<String> havecurriculumId, List<String> renzheng) {
		for(int i=0;i<havecurriculumId.size();i++)
		{
			if(!professionalnameID.get(0).equals(""))
			{
				String curriculumId = havecurriculumId.get(i);
				Curriculum curriculumNature = curriculumDao.findCurriculumNature(curriculumId);
				if(curriculumNature.getCourseLei().equals("理论课"))
				{
					TheoreticalLesson theoreticalLessonRenzheng = theoreticalPlanDao.findTheoByProAndCur(curriculumNature,professionalnameID.get(i));
					if(theoreticalLessonRenzheng.getRenzheng() == null || !theoreticalLessonRenzheng.getRenzheng().equals(renzheng.get(i)))
					{
						theoreticalLessonRenzheng.setRenzheng(renzheng.get(i));
						theoreticalPlanDao.update(theoreticalLessonRenzheng);
					}
				}
				else
				{
					PracticeLesson practiceLessonRenzheng = practicePlanDao.findTheoByProAndCur(curriculumNature,professionalnameID.get(i));
					if(practiceLessonRenzheng.getRenzheng() == null || !practiceLessonRenzheng.getRenzheng().equals(renzheng.get(i)))
					{
						practiceLessonRenzheng.setRenzheng(renzheng.get(i));
						practicePlanDao.update(practiceLessonRenzheng);
					}
		
				}
			}
			else
			{
				String curriculumId = havecurriculumId.get(i);
				Curriculum curriculumNature = curriculumDao.findCurriculumNature(curriculumId);
				if(curriculumNature.getCourseLei().equals("理论课"))
				{
					TheoreticalLesson theoreticalLessonRenzheng = theoreticalPlanDao.findTheoByDepAndCur(curriculumNature,departmentCnameID.get(i));
					if(theoreticalLessonRenzheng.getRenzheng() == null || !theoreticalLessonRenzheng.getRenzheng().equals(renzheng.get(i)))
					{
						theoreticalLessonRenzheng.setRenzheng(renzheng.get(i));
						theoreticalPlanDao.update(theoreticalLessonRenzheng);
					}
				}
				else
				{
					PracticeLesson practiceLessonRenzheng = practicePlanDao.findTheoByDepAndCur(curriculumNature,departmentCnameID.get(i));
					if(practiceLessonRenzheng.getRenzheng() == null || !practiceLessonRenzheng.getRenzheng().equals(renzheng.get(i)))
					{
						practiceLessonRenzheng.setRenzheng(renzheng.get(i));
						practicePlanDao.update(practiceLessonRenzheng);
					}
		
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("msg", "保存成功!");
		ServletActionContext.getRequest().setAttribute("tag", "tobjjzPage");
	}




}
