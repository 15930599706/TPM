package com.tpm.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Array;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.User;

public class TheoreticalPlanDaoImpl extends BaseDaoImpl<TheoreticalLesson> implements TheoreticalPlanDao {

	//多条件查询理论课
	public List<TheoreticalLesson> findTheoreticalLesson(TheoreticalLesson theoreticalLesson) {
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		//查询对应专业的理论课，若有专业方向，则查询该专业方向的理论课
		//因为查的是ID，可以不用进行将关联属性进行引用
		if(theoreticalLesson.getProfessional() !=null && !"".equals(theoreticalLesson.getProfessional().getProfessionalid())){				
			criteria.add(Restrictions.eq("professional.professionalid", theoreticalLesson.getProfessional().getProfessionalid()));			
		}
		else{
			criteria.add(Restrictions.eq("department.departmentid", theoreticalLesson.getDepartment().getDepartmentid()));
		}						
		//添加课程平台的条件查询
		criteria.createAlias("curriculum","curriculum");	//离线查询的情况下，当进行级联查询时，查询其关联对象的属性时必须添加此行引用，当查询的是ID时可以不用
		criteria.add(Restrictions.isNotNull("curriculum.natureOfCourse"));
		if(theoreticalLesson.getCurriculum().getCurriculumpingtai()!=null && !"".equals(theoreticalLesson.getCurriculum().getCurriculumpingtai())){
			criteria.add(Restrictions.eq("curriculum.curriculumpingtai", theoreticalLesson.getCurriculum().getCurriculumpingtai()));
		}
		//添加课程性质的条件查询
		if(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid()!=null && !"".equals(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid())){
			criteria.add(Restrictions.eq("curriculum.natureOfCourse.natureOfCourseid", theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid()));
		}
		//添加课程类别的条件查询
		if(theoreticalLesson.getCurriculum().getCourseLei()!=null && !"".equals(theoreticalLesson.getCurriculum().getCourseLei())){
			criteria.add(Restrictions.eq("curriculum.courseLei", theoreticalLesson.getCurriculum().getCourseLei()));
		}
		//添加课程归属的条件查询
		if(theoreticalLesson.getCurriculum().getCourseCategory()!=null && !"".equals(theoreticalLesson.getCurriculum().getCourseCategory())){
			criteria.add(Restrictions.eq("curriculum.courseCategory", theoreticalLesson.getCurriculum().getCourseCategory()));
		}
		//添加学期的条件查询
		if(theoreticalLesson.getXueqi()!=null && !"".equals(theoreticalLesson. getXueqi())){
			//内部属性，可以不用设置引用进行查询
			criteria.add(Restrictions.eq("xueqi", theoreticalLesson.getXueqi()));
		}
		return (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
	}

	//多条件查询课程，用于选择理论课培养计划
	public List<Curriculum> findCurriculum(TheoreticalLesson theoreticalLesson) {
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.ne("courseLei", "实践课")).add(Restrictions.and(Restrictions.isNotNull("credit"), Restrictions.ne("credit", ""))).add(Restrictions.or(Restrictions.eq("newcurriculum", "0"), Restrictions.and(Restrictions.eq("newcurriculum", "1"), Restrictions.eq("college", theoreticalLesson.getDepartment().getCollege())))).add(Restrictions.isNotNull("natureOfCourse"));
		//查询对应开课学院的全部课程
		if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null && !"-1".equals(theoreticalLesson.getCurriculum().getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college.collegeid", theoreticalLesson.getCurriculum().getCollege().getCollegeid()));
		}
		//添加课程平台的条件查询
		if(theoreticalLesson.getCurriculum().getCurriculumpingtai()!=null && !"".equals(theoreticalLesson.getCurriculum().getCurriculumpingtai())){
				criteria.add(Restrictions.eq("curriculumpingtai", theoreticalLesson.getCurriculum().getCurriculumpingtai()));
		}
		//添加课程性质的条件查询
		if(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid()!=null && !"".equals(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid())){
			criteria.add(Restrictions.eq("natureOfCourse.natureOfCourseid", theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid()));
		}
		//添加课程类别的条件查询
		if(theoreticalLesson.getCurriculum().getCourseLei()!=null && !"".equals(theoreticalLesson.getCurriculum().getCourseLei())){
			criteria.add(Restrictions.eq("courseLei", theoreticalLesson.getCurriculum().getCourseLei()));
		}
		//添加课程归属的条件查询
		if(theoreticalLesson.getCurriculum().getCourseCategory()!=null && !"".equals(theoreticalLesson.getCurriculum().getCourseCategory())){
			criteria.add(Restrictions.eq("courseCategory", theoreticalLesson.getCurriculum().getCourseCategory()));
		}
		//添加课程代码的检索
		if(theoreticalLesson.getCurriculum().getCurriculumid()!=null && !"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", theoreticalLesson.getCurriculum().getCurriculumid()));
		}
		//添加课程名称的检索
		if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null && !"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+theoreticalLesson.getCurriculum().getCurriculumCname()+"%"));
		}
		List<Curriculum> curriculumList = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		List<Professional> professionalList = (List<Professional>)findProfessional(theoreticalLesson.getDepartment().getDepartmentid());
		if(professionalList.size() == 0){
		List<TheoreticalLesson> selectTheoreticalLesson = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=?",theoreticalLesson.getDepartment());	//查询所有选择了的理论课
			for(int i=0;i<selectTheoreticalLesson.size();i++){	//若该理论课已经加入了培养计划，则不会再显示在查询结果中
				if(curriculumList.contains(selectTheoreticalLesson.get(i).getCurriculum())){
					curriculumList.remove(selectTheoreticalLesson.get(i).getCurriculum());
				}
			}
		}
		return curriculumList;
	}
	
	//查询对应系管理员的专业方向
	public List<Professional> findProfessional(String departmentId) {
		Department department = this.getHibernateTemplate().get(Department.class,departmentId);		
		return (List<Professional>)this.getHibernateTemplate().find("from Professional where department=?",department);
	}

	//插入选择的理论课	
	public void addSelectLesson(TheoreticalLesson SelectTheoreticalLesson) {
		List<TheoreticalLesson> list = new ArrayList<TheoreticalLesson>();
		List<Professional> professionalList = this.getHibernateTemplate().find("from Professional where department=?",SelectTheoreticalLesson.getDepartment());
		if(professionalList.size() > 0){	//如果分专业方向，插入时检索该专业方向是否有选这门课			
			list = this.getHibernateTemplate().find("from TheoreticalLesson where curriculum=? and department=? and professional=?",SelectTheoreticalLesson.getCurriculum(),SelectTheoreticalLesson.getDepartment(),SelectTheoreticalLesson.getProfessional());
		}
		else{	//如果不分专业方向，插入时检索该专业是否有选这门课
			list = this.getHibernateTemplate().find("from TheoreticalLesson where curriculum=? and department=?",SelectTheoreticalLesson.getCurriculum(),SelectTheoreticalLesson.getDepartment());			
		}
		if(list ==null || list.size() == 0)	//如果没有选择，则插入到理论课表
		{
			this.getHibernateTemplate().save(SelectTheoreticalLesson);
/*			HttpServletResponse response = ServletActionContext.getResponse();
			String result = "{\"err\": false, \"message\": \"test\"}";
			response.setContentType("text/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(result);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/		
		}	
	}

	//查询对应系的所有理论课
	public List<TheoreticalLesson> findTheoreticalLessonDepartment(
			Department department) {
		return this.getHibernateTemplate().find("from TheoreticalLesson where department=?",department);
	}
	//查询对应专业方向的所有专业必修课
	public List<List<TheoreticalLesson>> findTheoreticalLessonProfessional(List<Professional> professionalList) {
		List<List<TheoreticalLesson>> theoreticalLessonList = new ArrayList<List<TheoreticalLesson>>();
		for(int i=0;i<professionalList.size();i++)
		{
			/*List<TheoreticalLesson> newtheoreticalLessonList = this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and curriculum.natureOfCourse.natureOfCourseid=?",professionalList.get(i),"12");
			theoreticalLessonList.add(newtheoreticalLessonList);*/
			
			DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("professional",professionalList.get(i))).add(Restrictions.eq("curriculum.natureOfCourse.natureOfCourseid", "12")).add(Restrictions.or(Restrictions.eq("renzheng", "是"), Restrictions.isNull("renzheng")));
			List<TheoreticalLesson> newtheoreticalLessonList = this.getHibernateTemplate().findByCriteria(criteria);		
			theoreticalLessonList.add(newtheoreticalLessonList);
		}
		
		return theoreticalLessonList;
	}
	
	public List<List<TheoreticalLesson>> findTheoreticalLessonProfessional_Pro(List<Professional> professionalList) {
		List<List<TheoreticalLesson>> theoreticalLessonList = new ArrayList<List<TheoreticalLesson>>();
		for(int i=0;i<professionalList.size();i++)
		{
			List<TheoreticalLesson> newtheoreticalLessonList = this.getHibernateTemplate().find("from TheoreticalLesson where professional=? and curriculum.natureOfCourse.natureOfCourseid=?",professionalList.get(i),"12");
			theoreticalLessonList.add(newtheoreticalLessonList);
		}
		
		return theoreticalLessonList;
	}

	//查询对应专业管理员所属学院的理论课，用于首次进入显示
	public List<Curriculum> findAllTheoreticalLesson(College college,Department department) {
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
//		criteria.add(Restrictions.ne("courseLei", "实践课")).add(Restrictions.or(Restrictions.eq("newcurriculum", "0"), Restrictions.and(Restrictions.eq("newcurriculum", "1"), Restrictions.eq("college", user.getCollege()))));	
		criteria.add(Restrictions.ne("courseLei", "实践课")).add(Restrictions.and(Restrictions.isNotNull("credit"), Restrictions.ne("credit", ""))).add(Restrictions.eq("college", college)).add(Restrictions.isNotNull("natureOfCourse"));
		
		List<Curriculum> curriculumList = this.getHibernateTemplate().findByCriteria(criteria);		
		List<Professional> professionalList = findProfessional(department.getDepartmentid());
		if(professionalList.size() == 0){
			List<TheoreticalLesson> selectTheoreticalLesson = this.getHibernateTemplate().find("from TheoreticalLesson where department=?",department);
			for(int i=0;i<selectTheoreticalLesson.size();i++){
				if(curriculumList.contains(selectTheoreticalLesson.get(i).getCurriculum())){
					curriculumList.remove(selectTheoreticalLesson.get(i).getCurriculum());
				}
			}
		}
		return curriculumList;
	}

	//查询没有选curriculumid的专业
	public List<Professional> findUnselectedProfessionalList(String departmentid, String curriculumid) {
		Department department = this.getHibernateTemplate().get(Department.class,departmentid);			
		List<Professional> professionalList = (List<Professional>)this.getHibernateTemplate().find("from Professional where department=?",department);
		Curriculum curriculum = this.getHibernateTemplate().get(Curriculum.class, curriculumid);
		List<TheoreticalLesson> selectedTheoreticalLessonList = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where curriculum=? and department=?",curriculum,department);
		for(int i=0;i<selectedTheoreticalLessonList.size();i++)
		{
			professionalList.remove(selectedTheoreticalLessonList.get(i).getProfessional());
		}
		return professionalList;
	}
	
	//************************************************************
	
	public List<Curriculum> findcurrbycollege(College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.eq("college", college));
		criteria.add(Restrictions.isNotNull("natureOfCourse"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
	}

	public List<TheoreticalLesson> findtheolenbycurr(Curriculum curriculum) {
		return (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where curriculum=?", curriculum);
	}

	public Curriculum findcurrbyId(String curriculumid) {
		List<Curriculum> list=this.getHibernateTemplate().find("from Curriculum where curriculumid=?",curriculumid);
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Department> finddepartbycollege(College college) {
		return (List<Department>)this.getHibernateTemplate().find("from Department where college=?", college);
	}

	public Department findchoosedepart(String string) {
		return (Department) (this.getHibernateTemplate().find("from Department where departmentid=?", string)).get(0);
	}

	public TheoreticalLesson findTheoByCurrAndDepart(Curriculum curriculum,
			Professional professional) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.eq("curriculum", curriculum));
		criteria.add(Restrictions.eq("professional", professional));
		List<TheoreticalLesson> list = (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return list.get(0);
	}

	public TheoreticalLesson findTheoByCurrAndDepart(Curriculum curriculum,
			Department department) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.eq("curriculum", curriculum));
		criteria.add(Restrictions.eq("department", department));
		List<TheoreticalLesson> list = (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return list.get(0);
	}

	public List<TheoreticalLesson> findTheolenByTeachDepartment(Department teachDepartment) {
		return (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where teachDepartment=?", teachDepartment);
	}
	
	
	
	public List<TheoreticalLesson> getbyuser(User user) {//查询该该老师的所有被分配课程
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.eq("teacher", user));
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.ne("curriculum.courseLei", "实践课"));
		List<TheoreticalLesson> TheoList= (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return TheoList;
	}

	public List<TheoreticalLesson> getbyexperimenter(User user) {//查询该实验员的所有被分配课程
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.eq("experimenter", user));
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.ne("curriculum.courseLei", "实践课"));
		List<TheoreticalLesson> TheoList= (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return TheoList;
	}
	
	public List<TheoreticalLesson> getbydepartment(Department newDepartment) {//查询该专业的所有被分配课程
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.eq("teachDepartment", newDepartment));
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.ne("curriculum.courseLei", "实践课"));
		List<TheoreticalLesson> TheoList= (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return TheoList;
	}
	
	public List<TheoreticalLesson> getbyexperiment(Experiment newExperiment) {//查询该实验室的所有被分配课程
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.eq("experiment", newExperiment));
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.ne("curriculum.courseLei", "实践课"));
		List<TheoreticalLesson> TheoList= (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return TheoList;
	}
	
	//查询专业培养计划
	public List<TheoreticalLesson> findSeleTheoLessonByDepart(String zhuanye,String zhuanyefangxiang) {
		Department department = this.getHibernateTemplate().get(Department.class,zhuanye);		
		List<Professional> professionalList = (List<Professional>)this.getHibernateTemplate().find("from Professional where department=?",department);
		List<TheoreticalLesson> selectTheoreticalLesson;
		if(professionalList.size() == 0){
			selectTheoreticalLesson = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=?",department);
		}
		else{
			Professional professional = this.getHibernateTemplate().get(Professional.class, zhuanyefangxiang);
			selectTheoreticalLesson = (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where department=? and professional=?",department,professional);
		}
		return selectTheoreticalLesson;
	}

	public List<TheoreticalLesson> getbyuser(User user,TheoreticalLesson newtheo) {
		List<TheoreticalLesson> TheoLsit = this.getHibernateTemplate().find("from TheoreticalLesson where teacher=? and curriculum=? and department=?",user,newtheo.getCurriculum(),newtheo.getDepartment());
		if(TheoLsit != null && TheoLsit.size() != 0) return TheoLsit;
		else return null;
	}

	//根据理论课查询大纲id
	public String findSyllabusidByTheo(TheoreticalLesson theoreticalLesson) {
		List<ApplicationSyllabus> applicationSyllabusList = new ArrayList<ApplicationSyllabus>();
		if(theoreticalLesson.getProfessional() == null){
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment());
		}
		else{
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=? and professional=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment(),theoreticalLesson.getProfessional());
		}
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0){
			return String.valueOf(applicationSyllabusList.get(0).getSyllabus().getSyllabusid());

		}
		else{
			return null;
		}
	}

	public List<TheoreticalLesson> getbyuser_HaveHourOfExp(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.or(Restrictions.eq("experimenter", user),Restrictions.eq("cteacher", user)));
	//	criteria.createAlias("curriculum","curriculum");
	//	criteria.add(Restrictions.isNotNull("curriculum.hoursOfExp")).add(Restrictions.ne("curriculum.hoursOfExp","0")).add(Restrictions.ne("curriculum.hoursOfExp",""));
		List<TheoreticalLesson> list = (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
	public List<TheoreticalLesson> getbyuser_HaveHourOfExp(User user,TheoreticalLesson newtheo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.or(Restrictions.eq("experimenter", user),Restrictions.eq("cteacher", user)));
		criteria.add(Restrictions.eq("curriculum",newtheo.getCurriculum())).add(Restrictions.eq("department", newtheo.getDepartment()));
		
		
		//List<TheoreticalLesson> TheoLsit;
		List<TheoreticalLesson> TheoLsit = (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		
//		if(newtheo.getCteacher() == null){
//			TheoLsit = this.getHibernateTemplate().find("from TheoreticalLesson where experimenter=? and curriculum=? and department=? ",user,newtheo.getCurriculum(),newtheo.getDepartment());
//		}else{
//			TheoLsit = this.getHibernateTemplate().find("from TheoreticalLesson where cteacher=? and curriculum=? and department=? ",user,newtheo.getCurriculum(),newtheo.getDepartment());
//		}
		if(TheoLsit != null && TheoLsit.size() != 0) return TheoLsit;
		else return null;
	}

	
	public String findSyllabusidByTheo_InnerExperiment(TheoreticalLesson theoreticalLesson) {
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabusList = new ArrayList<ApplicationSyllabus_TheoInnerExperiment>();
		if(theoreticalLesson.getProfessional() == null){
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where curriculum=? and department=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment());
		}
		else{
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_TheoInnerExperiment where curriculum=? and department=? and professional=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment(),theoreticalLesson.getProfessional());
		}
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0){
			return String.valueOf(applicationSyllabusList.get(0).getSyllabus_TheoInnerExperiment().getSyllabus_TheoInnerExperimentid());
		}
		else{
			return null;
		}
	}

	public List<TheoreticalLesson> getbycollegeAndsearch(TheoreticalLesson theoreticalLesson) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.eq("curriculum.college.collegeid", theoreticalLesson.getCurriculum().getCollege().getCollegeid()));
		criteria.add(Restrictions.ne("curriculum.courseLei", "实践课"));
		if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumid() != null && !theoreticalLesson.getCurriculum().getCurriculumid().equals(""))
		{
			criteria.add(Restrictions.like("curriculum.curriculumid", "%"+theoreticalLesson.getCurriculum().getCurriculumid()+"%"));
		}
		if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumCname() != null && !theoreticalLesson.getCurriculum().getCurriculumCname().equals(""))
		{
			criteria.add(Restrictions.like("curriculum.curriculumCname", "%"+theoreticalLesson.getCurriculum().getCurriculumCname()+"%"));
		}
		List<TheoreticalLesson> TheoList= (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return TheoList;
	}
	//判断理论课是否可以被下载
		public List<TheoreticalLesson> getbycollegeAndsearch0(TheoreticalLesson theoreticalLesson) {
			// TODO Auto-generated method stub
			DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
			
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("curriculum.college.collegeid", theoreticalLesson.getCurriculum().getCollege().getCollegeid()));
			criteria.add(Restrictions.ne("curriculum.courseLei", "实践课"));
			criteria.add(Restrictions.eq("isDow", "是"));
			
			if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumid() != null && !theoreticalLesson.getCurriculum().getCurriculumid().equals(""))
			{
				criteria.add(Restrictions.like("curriculum.curriculumid", "%"+theoreticalLesson.getCurriculum().getCurriculumid()+"%"));
			}
			if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumCname() != null && !theoreticalLesson.getCurriculum().getCurriculumCname().equals(""))
			{
				criteria.add(Restrictions.like("curriculum.curriculumCname", "%"+theoreticalLesson.getCurriculum().getCurriculumCname()+"%"));
			}
			
			List<TheoreticalLesson> TheoList= (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
			return TheoList;
		}


	public List<TheoreticalLesson> getbyCurriculumAndDepartment(TheoreticalLesson newtheo) {
		List<TheoreticalLesson> TheoLsit = this.getHibernateTemplate().find("from TheoreticalLesson where curriculum=? and department=?",newtheo.getCurriculum(),newtheo.getDepartment());
		if(TheoLsit != null && TheoLsit.size() != 0) return TheoLsit;
		else return null;
	}
	
	public List<TheoreticalLesson> getbycollegeAndsearch_HaveHourOfExp(TheoreticalLesson theoreticalLesson){
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.eq("curriculum.college.collegeid", theoreticalLesson.getCurriculum().getCollege().getCollegeid()));
		criteria.add(Restrictions.isNotNull("curriculum.hoursOfExp")).add(Restrictions.ne("curriculum.hoursOfExp","0")).add(Restrictions.ne("curriculum.hoursOfExp",""));

		if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumid() != null && !theoreticalLesson.getCurriculum().getCurriculumid().equals(""))
		{
			criteria.add(Restrictions.like("curriculum.curriculumid", "%"+theoreticalLesson.getCurriculum().getCurriculumid()+"%"));
		}
		if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumCname() != null && !theoreticalLesson.getCurriculum().getCurriculumCname().equals(""))
		{
			criteria.add(Restrictions.like("curriculum.curriculumCname", "%"+theoreticalLesson.getCurriculum().getCurriculumCname()+"%"));
		}
		List<TheoreticalLesson> list = (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
	public List<TheoreticalLesson> findTheoreticalLessonProfessional_Department(
			String departmentid) {
		return this.getHibernateTemplate().find("from TheoreticalLesson where department.departmentid=? and curriculum.natureOfCourse.natureOfCourseid=?",departmentid,"12");
	}
	public TheoreticalLesson findTheoByProAndCur(Curriculum curriculumNature,
			String string) {
		List<TheoreticalLesson> theoreticalLessonList = this.getHibernateTemplate().find("from TheoreticalLesson where curriculum=? and professional.professionalid=?",curriculumNature,string);
		if(theoreticalLessonList != null && theoreticalLessonList.size() != 0)
			return theoreticalLessonList.get(0);
		else return null;
	}

	public TheoreticalLesson findTheoByDepAndCur(Curriculum curriculumNature,
			String string) {
		List<TheoreticalLesson> theoreticalLessonList = this.getHibernateTemplate().find("from TheoreticalLesson where curriculum=? and department.departmentid=?",curriculumNature,string);
		if(theoreticalLessonList != null && theoreticalLessonList.size() != 0)
			return theoreticalLessonList.get(0);
		else return null;
	}
	
	//其他
	public List<PracticeLesson> getPracticeLessonbyuser(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
		criteria.add(Restrictions.eq("teacher", user));
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.eq("zuzhixingshi", "其他"));
		List<PracticeLesson> newPracList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
	return newPracList;
	}
	
	public String findPracticeLessonSyllabusidByTheo(PracticeLesson theoreticalLesson) {
		List<ApplicationSyllabus> applicationSyllabusList = new ArrayList<ApplicationSyllabus>();
		if(theoreticalLesson.getProfessional() == null){
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment());
		}
		else{
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus where curriculum=? and department=? and professional=?",theoreticalLesson.getCurriculum(),theoreticalLesson.getDepartment(),theoreticalLesson.getProfessional());
		}
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0){
			return String.valueOf(applicationSyllabusList.get(0).getSyllabus().getSyllabusid());

		}
		else{
			return null;
		}
	}

	public List<PracticeLesson> getPracticeLessonbyuser(User user,PracticeLesson newtheo) {
		List<PracticeLesson> TheoLsit = this.getHibernateTemplate().find("from PracticeLesson where teacher=? and curriculum=? and department=? and zuzhixingshi=?",user,newtheo.getCurriculum(),newtheo.getDepartment(),"其他");
		if(TheoLsit != null && TheoLsit.size() != 0) return TheoLsit;
		else return null;
	}
	
	public List<PracticeLesson> getbycollegeAndsearchOther(PracticeLesson theoreticalLesson) {

			// TODO Auto-generated method stub
			DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
			
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("curriculum.college.collegeid", theoreticalLesson.getCurriculum().getCollege().getCollegeid()));
			criteria.add(Restrictions.eq("curriculum.courseLei", "实践课"));
			criteria.add(Restrictions.eq("zuzhixingshi", "其他"));
			if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumid() != null && !theoreticalLesson.getCurriculum().getCurriculumid().equals(""))
			{
				criteria.add(Restrictions.like("curriculum.curriculumid", "%"+theoreticalLesson.getCurriculum().getCurriculumid()+"%"));
			}
			if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumCname() != null && !theoreticalLesson.getCurriculum().getCurriculumCname().equals(""))
			{
				criteria.add(Restrictions.like("curriculum.curriculumCname", "%"+theoreticalLesson.getCurriculum().getCurriculumCname()+"%"));
			}
			List<PracticeLesson> TheoList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
			return TheoList;
	}
	
	public List<PracticeLesson> getbyCurriculumAndDepartmentOther(
			PracticeLesson newtheo) {
		List<PracticeLesson> PracLsit = this.getHibernateTemplate().find("from PracticeLesson where curriculum=? and department=? and zuzhixingshi=?",newtheo.getCurriculum(),newtheo.getDepartment(),"其他");
		if(PracLsit != null && PracLsit.size() != 0) 
			return PracLsit;
		else 
			return null;
	}
	
	public List<Curriculum> findcurrbycollegeExp(College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.eq("college", college));
		criteria.add(Restrictions.isNotNull("natureOfCourse"));
		criteria.add(Restrictions.isNotNull("hoursOfExp")).add(Restrictions.ne("hoursOfExp","0")).add(Restrictions.ne("hoursOfExp",""));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
	}

public List<TheoreticalLesson> findTheolenByExperiment(Experiment experiment){
		return (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where experiment=?", experiment);
	}

public List<TheoreticalLesson> findTheolenByExperimentAndCollege(Experiment experiment, Curriculum curriculum){
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.add(Restrictions.eq("curriculum", curriculum));
		criteria.add(Restrictions.eq("experiment", experiment));
		List<TheoreticalLesson> list=this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

//xinjia 系管理员分配课内实验到老师
public List<TheoreticalLesson> findTheolenByTeacherDepartment(Department cteachDepartment) {
	return (List<TheoreticalLesson>)this.getHibernateTemplate().find("from TheoreticalLesson where cteachDepartment=?", cteachDepartment);
}
//判断课内实验是否可以被下载
	public List<TheoreticalLesson> getbycollegeAndsearch_HaveHourOfExp0(TheoreticalLesson theoreticalLesson){
		DetachedCriteria criteria = DetachedCriteria.forClass(TheoreticalLesson.class);
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.eq("curriculum.college.collegeid", theoreticalLesson.getCurriculum().getCollege().getCollegeid()));
		criteria.add(Restrictions.isNotNull("curriculum.hoursOfExp")).add(Restrictions.ne("curriculum.hoursOfExp","0")).add(Restrictions.ne("curriculum.hoursOfExp",""));
		criteria.add(Restrictions.eq("isDown", "是"));
		if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumid() != null && !theoreticalLesson.getCurriculum().getCurriculumid().equals(""))
		{
			criteria.add(Restrictions.like("curriculum.curriculumid", "%"+theoreticalLesson.getCurriculum().getCurriculumid()+"%"));
		}
		if(theoreticalLesson.getCurriculum() != null && theoreticalLesson.getCurriculum().getCurriculumCname() != null && !theoreticalLesson.getCurriculum().getCurriculumCname().equals(""))
		{
			criteria.add(Restrictions.like("curriculum.curriculumCname", "%"+theoreticalLesson.getCurriculum().getCurriculumCname()+"%"));
		}
		List<TheoreticalLesson> list = (List<TheoreticalLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}





}
