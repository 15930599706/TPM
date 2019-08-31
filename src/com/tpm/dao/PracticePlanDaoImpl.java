package com.tpm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.User;

public class PracticePlanDaoImpl extends BaseDaoImpl<PracticeLesson> implements PracticePlanDao {

	// 多条件查询集中实践课
	public List<PracticeLesson> findPracticeLesson(PracticeLesson practiceLesson) {
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
		//查询对应专业的实践课，若有专业方向，查询该专业方向的实践课
		//因为查的是ID，可以不用进行将关联属性进行引用
		if(practiceLesson.getProfessional() !=null && !"".equals(practiceLesson.getProfessional().getProfessionalid())){				
			criteria.add(Restrictions.eq("professional.professionalid", practiceLesson.getProfessional().getProfessionalid()));			
		}
		else{
			criteria.add(Restrictions.eq("department.departmentid", practiceLesson.getDepartment().getDepartmentid()));
		}
		//添加课程平台的条件查询
		criteria.createAlias("curriculum","curriculum");	//离线查询的情况下，当进行级联查询时，查询其关联对象的属性时必须添加此行引用，当查询的是ID时可以不用			
		criteria.add(Restrictions.isNotNull("curriculum.natureOfCourse"));
		if(practiceLesson.getCurriculum().getCurriculumpingtai()!=null && !"".equals(practiceLesson.getCurriculum().getCurriculumpingtai())){
			criteria.add(Restrictions.eq("curriculum.curriculumpingtai", practiceLesson.getCurriculum().getCurriculumpingtai()));
		}
		//添加课程性质的条件查询
		if(practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid()!=null && !"".equals(practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid())){
			criteria.add(Restrictions.eq("curriculum.natureOfCourse.natureOfCourseid", practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid()));
		}
/*		//添加课程类别的条件查询
		if(practiceLesson.getCurriculum().getCourseLei()!=null && !"".equals(practiceLesson.getCurriculum().getCourseLei())){
			criteria.add(Restrictions.eq("curriculum.courseLei", practiceLesson.getCurriculum().getCourseLei()));
		}*/
		if(practiceLesson.getCurriculum().getCourseCategory()!=null && !"".equals(practiceLesson.getCurriculum().getCourseCategory())){
			criteria.add(Restrictions.eq("curriculum.courseCategory", practiceLesson.getCurriculum().getCourseCategory()));
		}
		//添加学期的条件查询
		if(practiceLesson.getXueqi()!=null && !"".equals(practiceLesson. getXueqi())){
			//内部属性，可以不用设置引用进行查询
			criteria.add(Restrictions.eq("xueqi", practiceLesson.getXueqi()));
		}
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	//多条件查询课程，用于选择实践课培养计划
	public List<Curriculum> findCurriculum(PracticeLesson practiceLesson) {
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
	 	criteria.add(Restrictions.eq("courseLei", "实践课")).add(Restrictions.and(Restrictions.isNotNull("credit"), Restrictions.ne("credit", ""))).add(Restrictions.or(Restrictions.eq("newcurriculum", "0"), Restrictions.and(Restrictions.eq("newcurriculum", "1"), Restrictions.eq("college", practiceLesson.getDepartment().getCollege())))).add(Restrictions.isNotNull("natureOfCourse"));	
	 	//查询对应开课学院的全部课程
		if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null && !"-1".equals(practiceLesson.getCurriculum().getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college.collegeid", practiceLesson.getCurriculum().getCollege().getCollegeid()));
		}
		//添加课程平台的条件查询
		if(practiceLesson.getCurriculum().getCurriculumpingtai()!=null && !"".equals(practiceLesson.getCurriculum().getCurriculumpingtai())){
			criteria.add(Restrictions.eq("curriculumpingtai", practiceLesson.getCurriculum().getCurriculumpingtai()));
		}
		//添加课程性质的条件查询
		if(practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid()!=null && !"".equals(practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid())){
			criteria.add(Restrictions.eq("natureOfCourse.natureOfCourseid", practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid()));
		}
		//添加课程归属的条件查询
		if(practiceLesson.getCurriculum().getCourseCategory()!=null && !"".equals(practiceLesson.getCurriculum().getCourseCategory())){
			criteria.add(Restrictions.eq("courseCategory", practiceLesson.getCurriculum().getCourseCategory()));
		}
		//添加课程代码的检索
		if(practiceLesson.getCurriculum().getCurriculumid()!=null && !"".equals(practiceLesson.getCurriculum().getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", practiceLesson.getCurriculum().getCurriculumid()));
		}
		//添加课程名称的检索
		if(practiceLesson.getCurriculum().getCurriculumCname()!=null && !"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+practiceLesson.getCurriculum().getCurriculumCname()+"%"));
		}
		List<Curriculum> curriculumList = this.getHibernateTemplate().findByCriteria(criteria);
		List<Professional> professionalList = (List<Professional>)findProfessional(practiceLesson.getDepartment().getDepartmentid());
		if(professionalList.size() == 0){
			List<PracticeLesson> selectPracticeLesson = this.getHibernateTemplate().find("from PracticeLesson where department=?",practiceLesson.getDepartment());
			for(int i=0;i<selectPracticeLesson.size();i++){
				if(curriculumList.contains(selectPracticeLesson.get(i).getCurriculum())){
					curriculumList.remove(selectPracticeLesson.get(i).getCurriculum());
				}
			}
		}
		return curriculumList;
	}

	//插入选择的实践课
	public void addSelectLesson(PracticeLesson selectPracticeLesson) {
		List<PracticeLesson> list = new ArrayList<PracticeLesson>();
		List<Professional> professionalList = this.getHibernateTemplate().find("from Professional where department=?",selectPracticeLesson.getDepartment());
		if(professionalList.size() > 0){	//如果分专业方向，插入时检索该专业方向是否有选这门课			
			list = this.getHibernateTemplate().find("from PracticeLesson where curriculum=? and department=? and professional=?",selectPracticeLesson.getCurriculum(),selectPracticeLesson.getDepartment(),selectPracticeLesson.getProfessional());
		}
		else{	//如果不分专业方向，插入时检索该专业是否有选这门课
			list = this.getHibernateTemplate().find("from PracticeLesson where curriculum=? and department=?",selectPracticeLesson.getCurriculum(),selectPracticeLesson.getDepartment());			
		}
		if(list ==null || list.size() == 0)	//如果没有选择，则插入到理论课表
		{
			this.getHibernateTemplate().save(selectPracticeLesson);
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

	//查询对应系的全部实践课
	public List<PracticeLesson> findpracticeLessonDepartment(Department department) {
		return this.getHibernateTemplate().find("from PracticeLesson where department=?",department);
	}
	//查询对应专业方向的所有专业必修课
	public List<List<PracticeLesson>> findpracticeLessonProfessional(List<Professional> professionalList) {
		List<List<PracticeLesson>> practiceLessonList = new ArrayList<List<PracticeLesson>>();
		for(int i=0;i<professionalList.size();i++)
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("professional",professionalList.get(i))).add(Restrictions.eq("curriculum.natureOfCourse.natureOfCourseid", "12")).add(Restrictions.or(Restrictions.eq("renzheng", "是"), Restrictions.isNull("renzheng")));
			List<PracticeLesson> newpracticeLessonList = this.getHibernateTemplate().findByCriteria(criteria);		
			practiceLessonList.add(newpracticeLessonList);
		}
		
		return practiceLessonList;
	}
	public List<List<PracticeLesson>> findpracticeLessonProfessional_Pro(List<Professional> professionalList) {
		List<List<PracticeLesson>> practiceLessonList = new ArrayList<List<PracticeLesson>>();
		for(int i=0;i<professionalList.size();i++)
		{
			List<PracticeLesson> newpracticeLessonList = this.getHibernateTemplate().find("from PracticeLesson where professional=? and curriculum.natureOfCourse.natureOfCourseid=?",professionalList.get(i),"12");
			practiceLessonList.add(newpracticeLessonList);
		}
		
		return practiceLessonList;
	}
	//查询对应专业管理员所属学院的实践课，用于首次进入显示
	public List<Curriculum> findAllPracticeLesson(College college,Department department) {
		//创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
//	 	criteria.add(Restrictions.eq("courseLei", "实践课")).add(Restrictions.or(Restrictions.eq("newcurriculum", "0"), Restrictions.and(Restrictions.eq("newcurriculum", "1"), Restrictions.eq("college", user.getCollege()))));	
		criteria.add(Restrictions.eq("courseLei", "实践课")).add(Restrictions.and(Restrictions.isNotNull("credit"), Restrictions.ne("credit", ""))).add(Restrictions.eq("college", college)).add(Restrictions.isNotNull("natureOfCourse"));	

		List<Curriculum> curriculumList = this.getHibernateTemplate().findByCriteria(criteria);
	 	List<Professional> professionalList = findProfessional(department.getDepartmentid());
	 	if(professionalList.size() == 0){
	 		List<PracticeLesson> selectPracticeLesson = this.getHibernateTemplate().find("from PracticeLesson where department=?",department);
			for(int i=0;i<selectPracticeLesson.size();i++){
				if(curriculumList.contains(selectPracticeLesson.get(i).getCurriculum())){
					curriculumList.remove(selectPracticeLesson.get(i).getCurriculum());
				}
			}
		}
		return curriculumList;
	}
	
	
	

	public List<PracticeLesson> getbyuser(User user,String zuzhixingshi){
			DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
			criteria.add(Restrictions.or(Restrictions.eq("teacher", user), Restrictions.eq("experimenter", user)));
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("zuzhixingshi", zuzhixingshi));
			List<PracticeLesson> newPracList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return newPracList;
	}
	
	public List<PracticeLesson> findPracticebyuser(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
		criteria.add(Restrictions.eq("teacher", user));
		List<PracticeLesson> PraList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return PraList;
	}

	public List<PracticeLesson> findPracticebydepartment(Department newDepartment) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
		criteria.add(Restrictions.eq("teachDepartment", newDepartment));
		List<PracticeLesson> PraList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return PraList;
	}
	
	public List<Curriculum> findcurrbycollege(College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.eq("college", college));
		criteria.add(Restrictions.isNotNull("natureOfCourse"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
	}
	public List<PracticeLesson> findtheolenbycurr(Curriculum curriculum) {
		return (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where curriculum=?", curriculum);
	}
	public Curriculum findcurrbyId(String curriculumid) {
		List<Curriculum> list=this.getHibernateTemplate().find("from Curriculum where curriculumid=?",curriculumid);
		return list.get(0);
	}
	public List<Department> finddepartbycollege(College college) {
		return (List<Department>)this.getHibernateTemplate().find("from Department where college=?", college);
	}

	public Department findchoosedepart(String string) {
		return (Department) (this.getHibernateTemplate().find("from Department where departmentid=?", string)).get(0);
	}
	public PracticeLesson findTheoByCurrAndDepart(Curriculum curriculum,
			Professional professional) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
		criteria.add(Restrictions.eq("curriculum", curriculum));
		criteria.add(Restrictions.eq("professional", professional));
		List<PracticeLesson> list = (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return list.get(0);
	}
	public PracticeLesson findTheoByCurrAndDepart(Curriculum curriculum,
			Department department) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
		criteria.add(Restrictions.eq("curriculum", curriculum));
		criteria.add(Restrictions.eq("department", department));
		List<PracticeLesson> list = (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return list.get(0);
	}
	public List<PracticeLesson> findTheolenByTeachDepartment(Department teachDepartment) {
		return (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where teachDepartment=?", teachDepartment);
	}

	//查询对应系管理员的专业方向
	public List<Professional> findProfessional(String departmentId) {
		Department department = this.getHibernateTemplate().get(Department.class,departmentId);		
		return (List<Professional>)this.getHibernateTemplate().find("from Professional where department=?",department);
		}

	//查询没有选curriculumid的专业
	public List<Professional> findUnselectedProfessionalList(
		String departmentid, String curriculumid) {
		Department department = this.getHibernateTemplate().get(Department.class,departmentid);			
		List<Professional> professionalList = (List<Professional>)this.getHibernateTemplate().find("from Professional where department=?",department);
		Curriculum curriculum = this.getHibernateTemplate().get(Curriculum.class, curriculumid);
		List<PracticeLesson> selectedPracticeLessonList = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where curriculum=? and department=?",curriculum,department);
		for(int i=0;i<selectedPracticeLessonList.size();i++)
		{
			professionalList.remove(selectedPracticeLessonList.get(i).getProfessional());
		}
		return professionalList;
	}

	//查询实践课培养计划
	public List<PracticeLesson> findSelePraLessonByDepart(String zhuanye,
			String zhuanyefangxiang) {
		Department department = this.getHibernateTemplate().get(Department.class,zhuanye);		
		List<Professional> professionalList = (List<Professional>)this.getHibernateTemplate().find("from Professional where department=?",department);
		List<PracticeLesson> selectPracticeLesson;
		if(professionalList.size() == 0){
			selectPracticeLesson = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=?",department);
		}
		else{
			Professional professional = this.getHibernateTemplate().get(Professional.class, zhuanyefangxiang);
			selectPracticeLesson = (List<PracticeLesson>)this.getHibernateTemplate().find("from PracticeLesson where department=? and professional=?",department,professional);
		}
		return selectPracticeLesson;
	}

	public List<PracticeLesson> getbyuser(User user, PracticeLesson newprac,String zuzhixingshi) {
		List<PracticeLesson> PracLsit = this.getHibernateTemplate().find("from PracticeLesson where teacher=? and curriculum=? and department=? and zuzhixingshi=?",user,newprac.getCurriculum(),newprac.getDepartment(),zuzhixingshi);
		if(PracLsit != null && PracLsit.size() != 0) 
			return PracLsit;
		else 
			return null;
	}

	//根据实践课实习查询大纲id
	public String findSyllabusidByPrac_FieldWork(PracticeLesson practiceLesson) {
		List<ApplicationSyllabus_FieldWork> applicationSyllabusList = new ArrayList<ApplicationSyllabus_FieldWork>();
		if(practiceLesson.getProfessional() == null){
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
		}
		else{
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_FieldWork where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),practiceLesson.getProfessional());
		}
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0){
			return String.valueOf(applicationSyllabusList.get(0).getSyllabus_FieldWork().getSyllabus_FieldWorkID());
		}
		else{
			return null;
		}
	}
	//根据实践课课程设计查询大纲id
	public String findSyllabusidByPrac_CourseDesign(PracticeLesson practiceLesson) {
		List<ApplicationSyllabus_CourseDesign> applicationSyllabusList = new ArrayList<ApplicationSyllabus_CourseDesign>();
		if(practiceLesson.getProfessional() == null){
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
		}
		else{
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_CourseDesign where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),practiceLesson.getProfessional());
		}
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0){
			return String.valueOf(applicationSyllabusList.get(0).getSyllabus_CourseDesign().getSyllabus_CourseDesignid());
		}
		else{
			return null;
		}
	}
	
	//根据实践课课程设计查询大纲id
	public String findSyllabusidByPrac_InnerExperiment(PracticeLesson practiceLesson) {
		List<ApplicationSyllabus_InnerExperiment> applicationSyllabusList = new ArrayList<ApplicationSyllabus_InnerExperiment>();
		if(practiceLesson.getProfessional() == null){
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
		}
		else{
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_InnerExperiment where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),practiceLesson.getProfessional());
		}
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0){
			return String.valueOf(applicationSyllabusList.get(0).getSyllabus_InnerExperiment().getSyllabus_InnerExperimentid());
		}
		else{
			return null;
		}
	}
	
	//根据实践课毕业设计查询大纲id
	public String findSyllabusidByPrac_Gra(PracticeLesson practiceLesson) {
		List<ApplicationSyllabus_Gra> applicationSyllabusList = new ArrayList<ApplicationSyllabus_Gra>();
		if(practiceLesson.getProfessional() == null){
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where curriculum=? and department=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment());
		}
		else{
			applicationSyllabusList = this.getHibernateTemplate().find("from ApplicationSyllabus_Gra where curriculum=? and department=? and professional=?",practiceLesson.getCurriculum(),practiceLesson.getDepartment(),practiceLesson.getProfessional());
		}
		if(applicationSyllabusList != null && applicationSyllabusList.size() != 0){
			return String.valueOf(applicationSyllabusList.get(0).getSyllabus_Gra().getSyllabus_Graid());
		}
		else{
			return null;
		}
	}

	//管理员查看大纲编写情况
	public List<PracticeLesson> getbycollegeAndsearch(PracticeLesson practiceLesson, String zuzhixingshi) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
		criteria.createAlias("curriculum","curriculum");
		criteria.add(Restrictions.eq("curriculum.college.collegeid", practiceLesson.getCurriculum().getCollege().getCollegeid()));
		criteria.add(Restrictions.eq("zuzhixingshi", zuzhixingshi));
		if(practiceLesson.getCurriculum() != null && practiceLesson.getCurriculum().getCurriculumid() != null && !practiceLesson.getCurriculum().getCurriculumid().equals(""))
		{
			criteria.add(Restrictions.like("curriculum.curriculumid", "%"+practiceLesson.getCurriculum().getCurriculumid()+"%"));
		}
		if(practiceLesson.getCurriculum() != null && practiceLesson.getCurriculum().getCurriculumCname() != null && !practiceLesson.getCurriculum().getCurriculumCname().equals(""))
		{
			criteria.add(Restrictions.like("curriculum.curriculumCname", "%"+practiceLesson.getCurriculum().getCurriculumCname()+"%"));
		}
		List<PracticeLesson> newPracList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
		return newPracList;
	}

	public List<PracticeLesson> getbyCurriculumAndDepartment(PracticeLesson newprac, String zuzhixingshi) {
		List<PracticeLesson> PracLsit = this.getHibernateTemplate().find("from PracticeLesson where curriculum=? and department=? and zuzhixingshi=?",newprac.getCurriculum(),newprac.getDepartment(),zuzhixingshi);
		if(PracLsit != null && PracLsit.size() != 0) 
			return PracLsit;
		else 
			return null;
	}
	public List<PracticeLesson> findpracticeLessonProfessional_Department(
			String departmentid) {	
		return this.getHibernateTemplate().find("from PracticeLesson where department.departmentid=? and curriculum.natureOfCourse.natureOfCourseid=?",departmentid,"12");
	}

	public PracticeLesson findTheoByProAndCur(Curriculum curriculumNature,
			String string) {
		List<PracticeLesson> theoreticalLessonList = this.getHibernateTemplate().find("from PracticeLesson where curriculum=? and professional.professionalid=?",curriculumNature,string);
		if(theoreticalLessonList != null && theoreticalLessonList.size() != 0)
			return theoreticalLessonList.get(0);
		else return null;
	}

	public PracticeLesson findTheoByDepAndCur(Curriculum curriculumNature,
			String string) {
		List<PracticeLesson> theoreticalLessonList = this.getHibernateTemplate().find("from PracticeLesson where curriculum=? and department.departmentid=?",curriculumNature,string);
		if(theoreticalLessonList != null && theoreticalLessonList.size() != 0)
			return theoreticalLessonList.get(0);
		else return null;
	}
	/*****************实验室主任分配实践课到实验员    李艳李婧 2018-07-29*********************/
	public List<PracticeLesson> findtheolenbycurr1(Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
		criteria.add(Restrictions.eq("curriculum", curriculum));
		criteria.add(Restrictions.isNotNull("experiment"));
		return (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
}
	//加判断管理员查看大纲编写情况  实习
		public List<PracticeLesson> getbycollegeAndsearch0(PracticeLesson practiceLesson, String zuzhixingshi) {
			DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("curriculum.college.collegeid", practiceLesson.getCurriculum().getCollege().getCollegeid()));
			criteria.add(Restrictions.eq("workload", "是"));	
			List<PracticeLesson> newPracList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
			return newPracList;
		}

		//加判断管理员查看大纲编写情况  课程设计
		public List<PracticeLesson> getbycollegeAndsearch1(PracticeLesson practiceLesson, String zuzhixingshi) {
			DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("curriculum.college.collegeid", practiceLesson.getCurriculum().getCollege().getCollegeid()));
			criteria.add(Restrictions.eq("workload1", "是"));
			List<PracticeLesson> newPracList1= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
			return newPracList1;
		}
		
		//加判断管理员查看大纲编写情况  实验
		public List<PracticeLesson> getbycollegeAndsearch2(PracticeLesson practiceLesson, String zuzhixingshi) {
			DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("curriculum.college.collegeid", practiceLesson.getCurriculum().getCollege().getCollegeid()));
			criteria.add(Restrictions.eq("workload2", "是"));	
			List<PracticeLesson> newPracList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
			return newPracList;
		}
		//加判断管理员查看大纲编写情况  毕业设计
		public List<PracticeLesson> getbycollegeAndsearch3(PracticeLesson practiceLesson, String zuzhixingshi) {
			DetachedCriteria criteria = DetachedCriteria.forClass(PracticeLesson.class);
			criteria.createAlias("curriculum","curriculum");
			criteria.add(Restrictions.eq("curriculum.college.collegeid", practiceLesson.getCurriculum().getCollege().getCollegeid()));
			criteria.add(Restrictions.eq("workload3", "是"));	
			List<PracticeLesson> newPracList= (List<PracticeLesson>)this.getHibernateTemplate().findByCriteria(criteria);
			return newPracList;
		}

}
	
	
