package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.Ability;
import com.tpm.entity.College;
import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPTTag;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.ScoreThreshold;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.MainTainOfPT;//总体安排
import com.tpm.entity.Topology;
import com.tpm.entity.TopologyTag;
import com.tpm.entity.TrainingAnother;
import com.tpm.entity.TrainingEvents;//培养事件
@SuppressWarnings("all")
public class PTBasicInformationDaoImpl extends BaseDaoImpl<PTBasicInformation> implements PTBasicInformationDao {

	public PTBasicInformation getbydepartment(Department department) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PTBasicInformation.class).add(Restrictions.eq("department", department));
		List<PTBasicInformation> ptBasicInformationlist = this.getHibernateTemplate().findByCriteria(criteria);
		if(ptBasicInformationlist != null && ptBasicInformationlist.size() != 0){
			return ptBasicInformationlist.get(0);
		}else{
			return null;
		}
	}
	//专业
	
	int xuenian=0;
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
		public List<MainTainOfPT> findprofession2(String pid)
		{
			Department department = new Department();
			department.setDepartmentid(pid);
			List<MainTainOfPT> thelist = (List<MainTainOfPT>)this.getHibernateTemplate().find("from MainTainOfPT where department=?",department);
			return thelist;
		}
		public List<TrainingEvents> findprofession3(String pid)
		{
			Department department = new Department();
			department.setDepartmentid(pid);
			List<TrainingEvents> thelist = (List<TrainingEvents>)this.getHibernateTemplate().find("from TrainingEvents where department=?",department);
			return thelist;
		}
		public List<List<TheoreticalLesson>> findxueweike(String pid) {//查询所有专业的学位课
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			
			List<List<TheoreticalLesson>> newxueweike_lilunkeList = new ArrayList<List<TheoreticalLesson>>();
			if(listprofessional !=  null && listprofessional.size() != 0)
			{
				for(int i=0;i<listprofessional.size();i++)
				{
					List<TheoreticalLesson>  thelist = this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and isxueweike=?",listprofessional.get(i),"是");
					newxueweike_lilunkeList.add(thelist);
				}
			}
			else
			{
				List<TheoreticalLesson>  thelist = this.getHibernateTemplate().find("from TheoreticalLesson where department=? and isxueweike=?",department,"是");
				newxueweike_lilunkeList.add(thelist);
			}
			return newxueweike_lilunkeList;
		}
		public List<List<PracticeLesson>> findxueweike_shijian(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			List<List<PracticeLesson>> newxueweike_shijianList = new ArrayList<List<PracticeLesson>>();
			if(listprofessional !=  null && listprofessional.size() != 0)
			{
				for(int i=0;i<listprofessional.size();i++)
				{
					List<PracticeLesson>  thelist = this.getHibernateTemplate().find("from PracticeLesson where professional=? and isxueweike=?",listprofessional.get(i),"是");
					newxueweike_shijianList.add(thelist);
				}
			}
			else
			{
				List<PracticeLesson>  thelist = this.getHibernateTemplate().find("from PracticeLesson where department=? and isxueweike=?",department,"是");
				newxueweike_shijianList.add(thelist);
			}
			return newxueweike_shijianList;
		}
		public List[] findprofession4(String pid)
		{
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			//System.out.println(listprofessional.get(0).getProfessionalid());
			List[] thelis1 = new List[listprofessional.size()];
			List[] thelis2 = new List[1];
			for(int thei=0;thei<listprofessional.size();thei++)
			{
				List<TheoreticalLesson> thelist = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and curriculum.natureOfCourse.natureOfCoursename=?",listprofessional.get(thei),"专业必选课");
				thelis1[thei]=thelist;
			}
			if(thelis1.length <=0) //不分方向
			{
				thelis2[0] =(List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=? and curriculum.natureOfCourse.natureOfCoursename=?",department,"专业必选课");
				return thelis2;
			}
			else 
				return thelis1;//分方向
		}

		public List[] findProElec1(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			
			
			if(listprofessional != null && listprofessional.size() !=0)
			{List[] thelis1 = new List[listprofessional.size()];
				for(int thei=0;thei<listprofessional.size();thei++)
				{
					List<TheoreticalLesson> thelist = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and curriculum.natureOfCourse.natureOfCoursename=? and isbixuan=?",listprofessional.get(thei),"专业选修课","是");
					thelis1[thei]=thelist;
				}
				return thelis1;
			}
			else
			{	List[] thelis2 = new List[1];
				List<TheoreticalLesson> thelist2 = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=? and curriculum.natureOfCourse.natureOfCoursename=? and isbixuan=?",department,"专业选修课","是");
				thelis2[0]=thelist2;
				return thelis2;
			}

		}
		public List<List<List<TheoreticalLesson>>> findProElec1_IS(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			String[] xueqi={"1","2","3","4","5","6","7","8","9","10"};
			List<List<List<TheoreticalLesson>>> newProTheo = new ArrayList<List<List<TheoreticalLesson>>>();
			
	
			if(listprofessional != null && listprofessional.size() != 0)
			{
				
				for(int i=0;i<listprofessional.size();i++)
				{
					List<List<TheoreticalLesson>> ProTheo = new ArrayList<List<TheoreticalLesson>>();
					for(int j=0;j<xuenian*2;j++)
					{
						List<TheoreticalLesson> thelist2 = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and curriculum.natureOfCourse.natureOfCoursename=? and isbixuan=? and xueqi=?",listprofessional.get(i),"专业选修课","是",xueqi[j]);
						ProTheo.add(thelist2);
					}
					newProTheo.add(ProTheo);
				}
			}
			else
			{
				List<List<TheoreticalLesson>> ProTheo = new ArrayList<List<TheoreticalLesson>>();
				for(int j=0;j<xuenian*2;j++)
				{
					List<TheoreticalLesson> thelist2 = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=? and curriculum.natureOfCourse.natureOfCoursename=? and isbixuan=? and xueqi=?",department,"专业选修课","是",xueqi[j]);
					ProTheo.add(thelist2);
				}
				newProTheo.add(ProTheo);
			}
			return newProTheo;
		}
		public List[] findProElec2(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			
			
			if(listprofessional.size() !=0)
			{List[] thelis1 = new List[listprofessional.size()];
				for(int thei=0;thei<listprofessional.size();thei++)
				{
					List<TheoreticalLesson> thelist = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and curriculum.natureOfCourse.natureOfCoursename=? and (isbixuan IS NULL or isbixuan=? or isbixuan=?)",listprofessional.get(thei),"专业选修课","否","");
					thelis1[thei]=thelist;
				}
				return thelis1;
			}
			else
			{	List[] thelis2 = new List[1];
				List<TheoreticalLesson> thelist2 = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=? and curriculum.natureOfCourse.natureOfCoursename=? and (isbixuan IS NULL or isbixuan=? or isbixuan=?)",department,"专业选修课","否","");
				thelis2[0]=thelist2;
				return thelis2;
			}

		}
		public List[] findprofession5(String pid)//查询每个专业方向和专业的实践课
		{
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);

			listprofessional = null;//使其不分方向
			
			if(listprofessional != null && listprofessional.size() !=0)//专业方向实践课
			{
				List[] thelist=new List[listprofessional.size()];
				for(int x=0;x<listprofessional.size();x++)
				{
					List<PracticeLesson> thelist1 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where professional=?",listprofessional.get(0));
					thelist[x]=thelist1;
				}
				return thelist;
			}
			else//专业实践课
			{
				List[] thepraclist=new List[1];
				List<PracticeLesson> thelist1 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=?",department);
				thepraclist[0]=thelist1;
				return thepraclist;
			}

		}

		public List[][][] findprofession6(String pid){
			int i=0,j=0;
		
			String[] xueqi={"1","2","3","4","5","6","7","8","9","10"};
			String[] nature={"公共必选课","专业必选课"};
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			if(listprofessional.size() !=0)
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
			
			//listprofessional = null;//使其不分方向
			
			
			if(listprofessional != null && listprofessional.size() != 0)//分专业方向
			{
				List[][] practice =new List[listprofessional.size()][xuenian*2];
				for(int jg1=0;jg1<listprofessional.size();jg1++)
				{
					for(int jg=0;jg<xuenian*2;jg++)
					{
						List<PracticeLesson> thelistg1 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where professional=? and xueqi=? and zuzhixingshi !=?",listprofessional.get(jg1),xueqi[jg],"毕业设计");
						practice[jg1][jg]=thelistg1;
					}
				}
				return practice;	
			}
			else{//部分专业方向
				List[][] practice1 =new List[xuenian*2][1];
				for(int jg=0;jg<xuenian*2;jg++)
				{
					List<PracticeLesson> thelistg1 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=? and xueqi=? and zuzhixingshi !=?",department,xueqi[jg],"毕业设计");
					practice1[jg][0]=thelistg1;
				}
				return practice1;
			}

		}
		//查询毕业设计7、8学期的课
		public List<List<PracticeLesson>> findqibaxueqiList(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			List<List<PracticeLesson>> newProTheo = new ArrayList<List<PracticeLesson>>();
			if(xuenian == 4)
			{
				if(listprofessional != null && listprofessional.size() != 0)
				{
					for(int i=0;i<listprofessional.size();i++)
					{
						List<PracticeLesson> thelist2 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where professional=? and (xueqi=? or xueqi=? or xueqi=?) and zuzhixingshi=? ",listprofessional.get(i),"7","7、8","8","毕业设计");
						newProTheo.add(thelist2);
					}
				}
				else
				{
					List<PracticeLesson> thelist2 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=? and (xueqi=? or xueqi=? or xueqi=?) and zuzhixingshi=?",department,"7","7、8","8","毕业设计");
					newProTheo.add(thelist2);
				}
			}
			if(xuenian == 5)
			{
				if(listprofessional != null && listprofessional.size() != 0)
				{
					for(int i=0;i<listprofessional.size();i++)
					{
						List<PracticeLesson> thelist2 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where professional=? and (xueqi=? or xueqi=? or xueqi=?) and zuzhixingshi=? ",listprofessional.get(i),"9","9、10","10","毕业设计");
						newProTheo.add(thelist2);
					}
				}
				else
				{
					List<PracticeLesson> thelist2 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=? and (xueqi=? or xueqi=? or xueqi=?) and zuzhixingshi=?",department,"9","9、10","10","毕业设计");
					newProTheo.add(thelist2);
				}
			}
			return newProTheo;
		}
/*		public List<List<PracticeLesson>> findbiyeshejiList(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			List<List<PracticeLesson>> newProTheo = new ArrayList<List<PracticeLesson>>();
			
			if(xuenian == 4)
			{
				if(listprofessional != null && listprofessional.size() != 0)
				{
					for(int i=0;i<listprofessional.size();i++)
					{
						List<PracticeLesson> thelist2 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where professional=? and (xueqi=? or xueqi=?) and zuzhixingshi=? ",listprofessional.get(i),"7","7、8","8","毕业设计");
						newProTheo.add(thelist2);
					}
				}
				else
				{
					List<PracticeLesson> thelist2 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=? and (xueqi=? or xueqi=?) and zuzhixingshi=?",department,"7","7、8","8","毕业设计");
					newProTheo.add(thelist2);
				}
			}
			if(xuenian == 5)
			{
				if(listprofessional != null && listprofessional.size() != 0)
				{
					for(int i=0;i<listprofessional.size();i++)
					{
						List<PracticeLesson> thelist2 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where professional=? and (xueqi=? or xueqi=? or xueqi=?) and zuzhixingshi=? ",listprofessional.get(i),"9","9、10","10","毕业设计");
						newProTheo.add(thelist2);
					}
				}
				else
				{
					List<PracticeLesson> thelist2 = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=? and (xueqi=? or xueqi=? or xueqi=?) and zuzhixingshi=?",department,"9","9、10","10","毕业设计");
					newProTheo.add(thelist2);
				}
			}
			return newProTheo;
		}*/
		//全院
		int i=0;
		public List<PTBasicInformation> findcollege1()
		{
			String professionid= "01";
			College college = new College();
			college.setCollegeid(professionid);
			List<PTBasicInformation> thelist= (List<PTBasicInformation>)this.getHibernateTemplate().find("from PTBasicInformation where department.college=?",college);
			return thelist;
		}
		
		public List<Ability> findAbility(String collegeid) {
			
			Department department = new Department();
			department.setDepartmentid(collegeid);
			return this.getHibernateTemplate().find("from Ability where department=?",department);
		}
	
		
		public List<TheoreticalLesson> findprofession8(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			//listprofessional = null;
			if(listprofessional != null && listprofessional.size() !=0)
			{
				List<TheoreticalLesson> publeleclist = this.getHibernateTemplate().find("from TheoreticalLesson where professional=?",listprofessional.get(0));
				return publeleclist;
			}
			else return this.getHibernateTemplate().find("from TheoreticalLesson where department=?",department);
		}
		public List<Department> findCollegeProNum(String cid) {
	
			College college = new College();
			college.setCollegeid(cid);
			return (List<Department>)this.getHibernateTemplate().find("from Department where college=?",college);
		}
		public List<College> findcollegenum() {
			return (List<College>)this.getHibernateTemplate().find("from College ");
		}
		public List<Topology> findpicture1(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<TopologyTag> picture = this.getHibernateTemplate().find("from TopologyTag where department=?",department);
			if(picture.size() != 0 && picture.get(0).getTag().equals("1"))
				return this.getHibernateTemplate().find("from Topology where department=?",department);
			else return this.getHibernateTemplate().find("from Topology where department=?",department);
		}
		public List<Topology> findpicture2(String pid) {
			// TODO Auto-generated method stub
			return null;
		}
		public List<ScoreThreshold> findscore(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<ScoreThreshold> score = this.getHibernateTemplate().find("from ScoreThreshold where department=?",department);
			if(score != null && score.size() != 0)
			{
				return score;
			}
		
			else return null;
		}

		public TrainingAnother findgraduation(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<TrainingAnother> otherInfor = this.getHibernateTemplate().find("from TrainingAnother where department=?",department);
			if(otherInfor != null && otherInfor.size() != 0)
				return otherInfor.get(0);
			else return null;
		}
		public List<Professional> findProNum(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			return  this.getHibernateTemplate().find("from Professional where department=?",department);
			
		}
		public List<TheoreticalLesson> findgongbixuankelist(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<Professional> listprofessional = this.getHibernateTemplate().find("from Professional where department=?",department);
			//listprofessional = null;
			if(listprofessional != null && listprofessional.size() !=0)
			{
				List<TheoreticalLesson> publeleclist = this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and curriculum.natureOfCourse.natureOfCoursename=?",listprofessional.get(0),"公共必选课");
				return publeleclist;
			}
			else return this.getHibernateTemplate().find("from TheoreticalLesson where department=? and curriculum.natureOfCourse.natureOfCoursename=?",department,"公共必选课");
		}
		public Department findDepartmentName(String pid) {
			List<Department> departmentlist = this.getHibernateTemplate().find("from Department where departmentid=?",pid) ;
			return departmentlist.get(0);
		}
		public MainTainOfPTTag findMainTainOfPTTag(String pid) {
			List<MainTainOfPTTag> MainTainOfPTTaglist = this.getHibernateTemplate().find("from MainTainOfPTTag where department.departmentid=?",pid);
			if(MainTainOfPTTaglist != null && MainTainOfPTTaglist.size() !=0)
				return MainTainOfPTTaglist.get(0);
			else return null;
		}
		public List<TrainingEvents> findprofession3_lilunke(String pid) {
			Department department = new Department();
			department.setDepartmentid(pid);
			List<TrainingEvents> thelist = (List<TrainingEvents>)this.getHibernateTemplate().find("from TrainingEvents where department=? and totaltag=?",department,2);
			return thelist;
		}







	
}
