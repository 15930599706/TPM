package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.User;
@SuppressWarnings("all")
public class CurriculumDaoImpl extends BaseDaoImpl<Curriculum> implements CurriculumDao {

	public int findcurriculumCount(Curriculum curriculum) {
		if((curriculum.getCollege().getCollegeid() == null || "-1".equals(curriculum.getCollege().getCollegeid()) || "".equals(curriculum.getCollege().getCollegeid())) && (curriculum.getCurriculumid() == null || "".equals(curriculum.getCurriculumid())) && (curriculum.getCurriculumCname() == null || "".equals(curriculum.getCurriculumCname())))
		{
			String hql = "select count(*) from Curriculum as curriculum";  
			return ((Number)getHibernateTemplate().iterate(hql).next()).intValue();  
		}
		else
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
			if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
				criteria.add(Restrictions.eq("college", curriculum.getCollege()));
			}
			if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
				criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
			}
			if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
				criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
			}
			List<Curriculum> culist = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
			if(culist != null){
				return culist.size();
			}else{
				return 0;
			}
		}
	}

	public List<Curriculum> findcurriculumT(int begin, int pageSize,Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findcurriculumnewCount(Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		criteria.add(Restrictions.eq("newcurriculum", "1"));
		List<Curriculum> culist = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		if(culist != null){
			return culist.size();
		}else{
			return 0;
		}
	}

	public List<Curriculum> findcurriculumnewT(int begin, int pageSize,
			Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		criteria.add(Restrictions.eq("newcurriculum", "1"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findcurriculumnewCount(Curriculum curriculum, College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(college != null && !"-1".equals(college) && !"".equals(college)){
			criteria.add(Restrictions.eq("college", college));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		criteria.add(Restrictions.eq("newcurriculum", "1"));
		List<Curriculum> culist = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		if(culist != null){
			return culist.size();
		}else{
			return 0;
		}
	}

	public List<Curriculum> findcurriculumnewT(int begin, int pageSize,
			Curriculum curriculum, College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(college != null && !"-1".equals(college) && !"".equals(college)){
			criteria.add(Restrictions.eq("college", college));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		criteria.add(Restrictions.eq("newcurriculum", "1"));
		List<Curriculum> culist = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findcurriculumCounttag(Curriculum curriculum,
			String departmenttag) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		criteria.add(Restrictions.isNull("user"));
		if(departmenttag.equals("0")){}
		if(departmenttag.equals("-1")){
			criteria.add(Restrictions.isNull("department"));
		}
		if(departmenttag.equals("1")){
			criteria.add(Restrictions.isNotNull("department"));
		}
		List<Curriculum> culist = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		if(culist != null){
			return culist.size();
		}else{
			return 0;
		}
	}

	public List<Curriculum> findcurriculumTtag(int begin, int pageSize,
			Curriculum curriculum, String departmenttag) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		criteria.add(Restrictions.isNull("user"));
		if(departmenttag.equals("0")){}
		if(departmenttag.equals("-1")){
			criteria.add(Restrictions.isNull("department"));
		}
		if(departmenttag.equals("1")){
			criteria.add(Restrictions.isNotNull("department"));
		}
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findcurriculumCounttaguser(Curriculum curriculum, String usertag) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		criteria.add(Restrictions.isNull("department"));
		if(usertag.equals("0")){}
		if(usertag.equals("-1")){
			criteria.add(Restrictions.isNull("user"));
		}
		if(usertag.equals("1")){
			criteria.add(Restrictions.isNotNull("user"));
		}
		List<Curriculum> culist = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		if(culist != null){
			return culist.size();
		}else{
			return 0;
		}
	}

	public List<Curriculum> findcurriculumTtaguser(int begin, int pageSize,
			Curriculum curriculum, String usertag) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		criteria.add(Restrictions.isNull("department"));
		if(usertag.equals("0")){}
		if(usertag.equals("-1")){
			criteria.add(Restrictions.isNull("user"));
		}
		if(usertag.equals("1")){
			criteria.add(Restrictions.isNotNull("user"));
		}
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}


	/*public int findcurriculumCounttaguserdepartment(Curriculum curriculum,
			String usertag) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getDepartment().getDepartmentid() != null && !"".equals(curriculum.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", curriculum.getDepartment()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		if(usertag.equals("0")){}
		if(usertag.equals("-1")){
			criteria.add(Restrictions.isNull("user"));
		}
		if(usertag.equals("1")){
			criteria.add(Restrictions.isNotNull("user"));
		}
		List<Curriculum> culist = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		if(culist != null){
			return culist.size();
		}else{
			return 0;
		}
	}

	public List<Curriculum> findcurriculumTtaguserdepartment(int begin,
			int pageSize, Curriculum curriculum, String usertag) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getDepartment().getDepartmentid() != null && !"".equals(curriculum.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", curriculum.getDepartment()));
		}
		if(curriculum.getCurriculumid() != null && !"".equals(curriculum.getCurriculumid())){
			criteria.add(Restrictions.eq("curriculumid", curriculum.getCurriculumid()));
		}
		if(curriculum.getCurriculumCname() != null && !"".equals(curriculum.getCurriculumCname())){
			criteria.add(Restrictions.like("curriculumCname", "%"+curriculum.getCurriculumCname()+"%"));
		}
		if(usertag.equals("0")){}
		if(usertag.equals("-1")){
			criteria.add(Restrictions.isNull("user"));
		}
		if(usertag.equals("1")){
			criteria.add(Restrictions.isNotNull("user"));
		}
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}*/

	public List<Curriculum> getbyuser(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.ne("courseLei", "实践课"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
	}

	public List<Curriculum> getbyuserne(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("courseLei", "实践课"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
	}
	
	
	//**********************************************************************
	public List<Curriculum> findCurrByCollege(College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.eq("college", college));
		criteria.add(Restrictions.isNotNull("natureOfCourse"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
	}


	public List<Curriculum> findCurrByCurrcname(String curriculumCname) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.isNotNull("natureOfCourse"));
		criteria.add(Restrictions.like("curriculumCname", curriculumCname+"%"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		//return (List<Curriculum>)this.getHibernateTemplate().find("from Curriculum where curriculumCname=?", curriculumCname);
	}

	public List<Curriculum> findCurrByCurrcid(String curriculumid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.isNotNull("natureOfCourse"));
		criteria.add(Restrictions.like("curriculumid", curriculumid+"%"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
	}

	public Curriculum getbyall(Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.eq("curriculumCname", curriculum.getCurriculumCname()));
		criteria.add(Restrictions.eq("curriculumEname", curriculum.getCurriculumEname()));
		criteria.add(Restrictions.eq("credit", curriculum.getCredit()));
		criteria.add(Restrictions.eq("hoursOfWeek", curriculum.getHoursOfWeek()));
		criteria.add(Restrictions.eq("hoursOfALL", curriculum.getHoursOfALL()));
		criteria.add(Restrictions.eq("hoursOfClass", curriculum.getHoursOfClass()));
		criteria.add(Restrictions.eq("hoursOfExp", curriculum.getHoursOfExp()));
		criteria.add(Restrictions.eq("hoursOfCom", curriculum.getHoursOfCom()));
		criteria.add(Restrictions.eq("hoursOfPractice", curriculum.getHoursOfPractice()));
		criteria.add(Restrictions.eq("courseLei", curriculum.getCourseLei()));
		criteria.add(Restrictions.eq("courseCategory", curriculum.getCourseCategory()));
		criteria.add(Restrictions.eq("natureOfCourse", curriculum.getNatureOfCourse()));
		criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		criteria.add(Restrictions.eq("curriculumpingtai", curriculum.getCurriculumpingtai()));
		List<Curriculum> curriculums = (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
		if(curriculums.size()!=0){
			return curriculums.get(0);
		}else{
			return null;
		}

	}

	public List<Curriculum> findAllwithout() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Curriculum.class);
		criteria.add(Restrictions.isNotNull("natureOfCourse"));
		return (List<Curriculum>)this.getHibernateTemplate().findByCriteria(criteria);
	}
	public Curriculum findCurriculumNature(String curriculumId) {
		List<Curriculum> curriculumNuature = this.getHibernateTemplate().find("from Curriculum where curriculumid=?",curriculumId);
		if(curriculumNuature != null && curriculumNuature.size() != 0)
			return curriculumNuature.get(0);
		else return null;
	}
	
}
