package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tpm.entity.Ability;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.PPTrainingConcept;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.ScoreThreshold;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.TrainingEvents;
import com.tpm.entity.User;
@SuppressWarnings("all")
public class ErrorDiscDaoImpl extends HibernateDaoSupport  implements ErrorDiscDao {

	public List<College> findAllCollege() {
		return this.getHibernateTemplate().find("from College");
	}
	
	public College findCollegeByid(String collegeid){
		return this.getHibernateTemplate().get(College.class, collegeid);		
	}

	List<Department> departmentList;
	//根据学院ID查询对应的专业
	public List<Department> findDepartmentByCollegeId(String collegeid){
		if(!"-1".equals(collegeid))
		{
			College college = this.getHibernateTemplate().get(College.class, collegeid);
			departmentList = this.getHibernateTemplate().find("from Department where college=?",college);
		}
		else
		{
			departmentList = this.getHibernateTemplate().find("from Department");
		}
		return departmentList;
	}
	//查询培养计划基本信息为空的专业
	public List<Department> findPTBasicInformationEmpty(String collegeid) {
		List<Department> basicInfoEmptyDepartmentList = new ArrayList<Department>();
		findDepartmentByCollegeId(collegeid);		
		for(int i=0;i<departmentList.size();i++)
		{
			List<PTBasicInformation> ptBasicInformation = this.getHibernateTemplate().find("from PTBasicInformation where department=?",departmentList.get(i));
			if(ptBasicInformation.size() == 0){
				basicInfoEmptyDepartmentList.add(departmentList.get(i));
			}
		}		
		return basicInfoEmptyDepartmentList;
	}
	//查询毕业生应获得的知识和能力为空的专业
	public List<Department> findAbilityEmpty(String collegeid) {
		List<Department> abilityEmptyDepartmentList = new ArrayList<Department>();
		findDepartmentByCollegeId(collegeid);		
		for(int i=0;i<departmentList.size();i++)
		{
			List<Ability> ability = this.getHibernateTemplate().find("from Ability where department=?",departmentList.get(i));
			if(ability.size() == 0){
				abilityEmptyDepartmentList.add(departmentList.get(i));
			}
			else{
				for(int j=0;j<ability.size();j++){
					if(ability.get(j).getAbilityname() == null || ability.get(j).getAbilitycontent() == null){
						abilityEmptyDepartmentList.add(departmentList.get(i));					
						break;
					}					
				}
			}
		}		
		return abilityEmptyDepartmentList;
	}
	//查询专业人才培养理念为空的专业
	public List<Department> findPPTrainingConceptEmpty(String collegeid) {
		List<Department> ppTrainConceptDepartmentList = new ArrayList<Department>();
		findDepartmentByCollegeId(collegeid);		
		for(int i=0;i<departmentList.size();i++)
		{
			List<PPTrainingConcept> ppTrainingConcept = this.getHibernateTemplate().find("from PPTrainingConcept where department=?",departmentList.get(i));
			if(ppTrainingConcept.size() == 0){
				ppTrainConceptDepartmentList.add(departmentList.get(i));
			}
		}		
		return ppTrainConceptDepartmentList;
	}
	
	//查询课程为空的课
	public List<Curriculum> findKeyCouEmpty(String collegeid) {
		//使用hibernate模板里面find方法实现
		//拼接hql语句
		String hql = "from Curriculum where (curriculumpingtai='' or natureOfCourse='' or courseLei='' or " +
					"(curriculumpingtai='公共教育平台' and courseCategory='') " +
					"or curriculumCname='' or credit='' or hoursOfALL='') ";
		// or department=null
		//创建list集合，如果值不为空，把值设置到list里面
		List<Object> p = new ArrayList<Object>();
		if(!"-1".equals(collegeid))
		{
			College college = findCollegeByid(collegeid);
			hql +=" and college=?";
			p.add(college);			
		}
		return (List<Curriculum>)this.getHibernateTemplate().find(hql,p.toArray());
		
	}

	//查询学时学分对应错误的课
	public List<Curriculum> findPreCreErr(String collegeid) {
		//使用hibernate模板里面find方法实现
		//拼接hql语句
		String hql = "from Curriculum where 1=1 ";
		// or department=null
		//创建list集合，如果值不为空，把值设置到list里面
		List<Object> p = new ArrayList<Object>();
		if(!"-1".equals(collegeid))
		{
			College college = findCollegeByid(collegeid);
			hql +=" and college=?";
			p.add(college);			
		}
		List<Curriculum> curriculumList = (List<Curriculum>)this.getHibernateTemplate().find(hql,p.toArray());
		List<Curriculum> preCreErrList = new ArrayList<Curriculum>();
		for(int i=0; i<curriculumList.size(); i++){
			if(curriculumList.get(i).getCredit()==null || "".equals(curriculumList.get(i).getCredit()) || curriculumList.get(i).getHoursOfALL()==null || "".equals(curriculumList.get(i).getHoursOfALL())){
				preCreErrList.add(curriculumList.get(i));
			}
			else if(curriculumList.get(i).getCourseLei().equals("体育课")){
					if((Float.parseFloat(curriculumList.get(i).getCredit()) != 0.6f) || (Float.parseFloat(curriculumList.get(i).getHoursOfALL()) != 36)){
						preCreErrList.add(curriculumList.get(i));
						}
			}
			else if(curriculumList.get(i).getCurriculumCname().equals("职业生涯规划与就业指导Ⅰ")){
				if((Float.parseFloat(curriculumList.get(i).getCredit()) != 0.25f) || (Float.parseFloat(curriculumList.get(i).getHoursOfALL()) != 8)){
					preCreErrList.add(curriculumList.get(i));
					}
			}
			else if(curriculumList.get(i).getCurriculumCname().equals("职业生涯规划与就业指导ⅠⅠ")){
				if((Float.parseFloat(curriculumList.get(i).getCredit()) != 0.75f) || (Float.parseFloat(curriculumList.get(i).getHoursOfALL()) != 24)){
					preCreErrList.add(curriculumList.get(i));
					}
			}
			//实践课1周/学分
			/*else if(curriculumList.get(i).getCourseLei().equals("实践课")  && !curriculumList.get(i).getCurriculumCname().contains("体测")){
					if(Float.parseFloat(curriculumList.get(i).getCredit())*16 != Float.parseFloat(curriculumList.get(i).getHoursOfALL())){
						preCreErrList.add(curriculumList.get(i));
						}
				}*/
			else if(curriculumList.get(i).getCurriculumCname().contains("体测")){
						if(Float.parseFloat(curriculumList.get(i).getCredit()) != 0.4f){
						preCreErrList.add(curriculumList.get(i));
						}
				}
			else if(Float.parseFloat(curriculumList.get(i).getCredit())*16 != Float.parseFloat(curriculumList.get(i).getHoursOfALL())){
					preCreErrList.add(curriculumList.get(i));
					}	
				else {
					if(curriculumList.get(i).getHoursOfClass() == null ||curriculumList.get(i).getHoursOfClass().equals("")){
						curriculumList.get(i).setHoursOfClass("0");
					}
					if(curriculumList.get(i).getHoursOfCom() == null || curriculumList.get(i).getHoursOfCom().equals("")){
						curriculumList.get(i).setHoursOfCom("0");
					}
					if(curriculumList.get(i).getHoursOfExp() == null || curriculumList.get(i).getHoursOfExp().equals("")){
						curriculumList.get(i).setHoursOfExp("0");
					}
					if(curriculumList.get(i).getHoursOfPractice() == null || curriculumList.get(i).getHoursOfPractice().equals("")){
						curriculumList.get(i).setHoursOfPractice("0");
					}								
					if((Float.parseFloat(curriculumList.get(i).getHoursOfClass())+Float.parseFloat(curriculumList.get(i).getHoursOfCom())+Float.parseFloat(curriculumList.get(i).getHoursOfExp())+Float.parseFloat(curriculumList.get(i).getHoursOfPractice())) != Float.parseFloat(curriculumList.get(i).getHoursOfALL())){
					preCreErrList.add(curriculumList.get(i));
					}	
				}
		}
		
		return preCreErrList;
	}

	//查询学分为空的专业
	public List<ScoreThreshold> findCreditEmpty(String collegeid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ScoreThreshold.class);
		criteria.createAlias("department","department");
		if(!"-1".equals(collegeid))
		{
			criteria.add(Restrictions.isNull("score")).add(Restrictions.eq("department.college.collegeid",collegeid));
		}
		else
		{
			criteria.add(Restrictions.isNull("score"));
		}
		return this.getHibernateTemplate().findByCriteria(criteria);	
	}
	
	int xuenian=4;
	public List<PTBasicInformation> findprofession1(String pid)
	{
		Department department = new Department();
		department.setDepartmentid(pid);
		List<PTBasicInformation> changexuenian=this.getHibernateTemplate().find("from PTBasicInformation where department=?",department);
		if(changexuenian.isEmpty())
			return null;
		else 
		{
			PTBasicInformation rs=changexuenian.get(0);
			if(rs.getLearningTime().equals("四年")) xuenian=4;
			if(rs.getLearningTime().equals("五年")) xuenian=5;
			return changexuenian;
		}

	}
	
	public List[][][] findprofession6(String pid){
		int i=0,j=0;
	
		String[] xueqi={"1","2","3","4","5","6","7","8","9","10"};
		String[] nature={"公共必选课","公共选修课","专业必选课","专业选修课"};
		Department department = new Department();
		department.setDepartmentid(pid);
		List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
		if(listprofessional != null && listprofessional.size() !=0)
		{
			List[][][] theore1 =new List[listprofessional.size()][nature.length][xuenian*2];
			for(int xg=0;xg<listprofessional.size();xg++)
			{
				for(int ig=0;ig<nature.length;ig++)
				{
					for(int jg=0;jg<xuenian*2;jg++)
					{
						List<TheoreticalLesson> thelistg1 = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and xueqi=? and curriculum.natureOfCourse.natureOfCoursename=?",listprofessional.get(xg),xueqi[jg],nature[ig]);
						theore1[xg][ig][jg]=thelistg1 ;
					}
				}	
			}
			return theore1 ;
		}
		else
		{
			List[][][] theore2 =new List[nature.length][xuenian*2][1];
			for(int ig=0;ig<nature.length;ig++)
			{
				for(int jg=0;jg<xuenian*2;jg++)
				{
					List<TheoreticalLesson> thelistg1 = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=? and xueqi=? and curriculum.natureOfCourse.natureOfCoursename=?",department,xueqi[jg],nature[ig]);
					theore2[ig][jg][0]=thelistg1 ;
				}
			}	
			return theore2 ;
		}
	}
	public List[][] findprofession7(String pid){
		int i=0,j=0;
		String[] xueqi={"1","2","3","4","5","6","7","8","9","10"};
		Department department = new Department();
		department.setDepartmentid(pid);
		List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
		//word1.setLearningTime("4");
		List[][] practice =new List[listprofessional.size()][xuenian*2];
		if(listprofessional.size() != 0)//分专业方向
		{
			for(int jg1=0;jg1<listprofessional.size();jg1++)
			{
				for(int jg=0;jg<xuenian*2;jg++)
				{
					List<PracticeLesson> thelistg1 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where professional=? and xueqi=?",listprofessional.get(jg1),xueqi[jg]);
					practice[jg1][jg]=thelistg1;
				}
			}
			return practice;	
		}
		else{//部分专业方向
			List[][] practice1 =new List[xuenian*2][1];
			for(int jg=0;jg<xuenian*2;jg++)
			{
				List<PracticeLesson> thelistg1 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=? and xueqi=?",department,xueqi[jg]);
				practice1[jg][0]=thelistg1;
			}
			return practice1;
		}

	}

	public List<List<TheoreticalLesson>> findlilunke(String pid) {//查询专业和方向的理论课
		Department department = new Department();
		department.setDepartmentid(pid);
		List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
		
		List<List<TheoreticalLesson>> newlilunkelist = new ArrayList<List<TheoreticalLesson>>();
		if(listprofessional != null && listprofessional.size() != 0)
		{
			for(int jg=0;jg<listprofessional.size();jg++)
			{
				List<TheoreticalLesson> thelistg1 = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where professional=?",listprofessional.get(jg));
				newlilunkelist.add(thelistg1);
			}
			return newlilunkelist;
		}
		else{
			List<TheoreticalLesson> thelistg1 = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=?",department);
			newlilunkelist.add(thelistg1);
			return newlilunkelist;
		}
	}

	public List<List<PracticeLesson>> findshijianke(String pid) {//查询方向和专业的实践课
		Department department = new Department();
		department.setDepartmentid(pid);
		List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
		
		List<List<PracticeLesson>> newlilunkelist = new ArrayList<List<PracticeLesson>>();
		if(listprofessional != null && listprofessional.size() != 0)
		{
			for(int jg=0;jg<listprofessional.size();jg++)
			{
				List<PracticeLesson> thelistg1 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where professional=?",listprofessional.get(jg));
				newlilunkelist.add(thelistg1);
			}
			return newlilunkelist;
		}
		else{
			List<PracticeLesson> thelistg1 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=?",department);
			newlilunkelist.add(thelistg1);
			return newlilunkelist;
		}
	}

	public List<Professional> findzhuanyenum(String pid) {
		Department department = new Department();
		department.setDepartmentid(pid);
		return this.getHibernateTemplate().find("from Professional where department=?",department);
	}


	public List<List<TheoreticalLesson>> findgexueqililunke(String pid,int xuenian) {
		Department department = new Department();
		department.setDepartmentid(pid);
		
		List<List<TheoreticalLesson>> Theolist = new ArrayList<List<TheoreticalLesson>>();
		String[] xueqi={"1","2","3","4","5","6","7","8","9","10"};
		List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
		if(listprofessional != null && listprofessional.size() != 0)
		{
			for(int i=0;i<xuenian*2;i++)
			{
				List<TheoreticalLesson> newThelist = new ArrayList<TheoreticalLesson>();
				newThelist = this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and xueqi=?",listprofessional.get(0),xueqi[i]);
				Theolist.add(newThelist);
			}
		}
		else
		{
			for(int i=0;i<xuenian*2;i++)
			{
				List<TheoreticalLesson> newThelist = new ArrayList<TheoreticalLesson>();
				newThelist = this.getHibernateTemplate().find("from TheoreticalLesson where department=? and xueqi=?",department,xueqi[i]);
				Theolist.add(newThelist);
			}
		}

		return Theolist;
	}

	public PTBasicInformation findPTBasicInformation(String pid) {
		Department department = new Department();
		department.setDepartmentid(pid);
		List<PTBasicInformation> ptBasicInformation = this.getHibernateTemplate().find("from PTBasicInformation where department=?",department);
		if(ptBasicInformation != null && ptBasicInformation.size() != 0)
			return ptBasicInformation.get(0);
		else return null;
	}

	public List<TrainingEvents> findpeiyangshijian(String pid) {
		Department department = new Department();
		department.setDepartmentid(pid);
		
		List<TrainingEvents> trainingEvents  = this.getHibernateTemplate().find("from TrainingEvents where department=?",department);
		if(trainingEvents != null && trainingEvents.size() != 0)
			return trainingEvents;
		else return null;
	}




}
