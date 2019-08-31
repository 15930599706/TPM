package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.ExperimentLog;
import com.tpm.entity.User;
@SuppressWarnings("all")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User login(User user) {
		List<User> list = this.getHibernateTemplate().
				find("from User where tnum=? and password=?", user.getTnum(),user.getPassword());
		if(list !=null && list.size() != 0){
			User u=list.get(0);
			return u;
		}else{
			return null;
		}
	}

	public int finduserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}

	public List<User> finduserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findxxgluserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("adminlevel", 5));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}

	public List<User> findxxgluserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("adminlevel", 5));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findtjxxuserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.ne("adminlevel", 5));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}

	public List<User> findtjxxuserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.ne("adminlevel", 5));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}
	
	public int findxygluserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("adminlevel", 3));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}
	public List<User> findxygluserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("adminlevel", 3));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findtjxyuserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.ne("adminlevel", 5)).add(Restrictions.ne("adminlevel", 3));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}

	public List<User> findtjxyuserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.ne("adminlevel", 5)).add(Restrictions.ne("adminlevel", 3));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findxgluserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("adminlevel", 1));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}

	public List<User> findxgluserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("adminlevel", 1));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	public int findtjxuserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("adminlevel", 0));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}

	public List<User> findtjxuserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getDepartment().getDepartmentid() != null && !"-1".equals(user.getDepartment().getDepartmentid()) && !"".equals(user.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", user.getDepartment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("adminlevel", 0));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}
	
	/*********实验室主任****************************/
	public int findsyszruserCount(User user) {//检索实验室主任数
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getExperiment().getExperimentid() != null && !"-1".equals(user.getExperiment().getExperimentid()) && !"".equals(user.getExperiment().getExperimentid())){
			criteria.add(Restrictions.eq("experiment", user.getExperiment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("experimenterlevel", 3));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}
	public List<User> findsyszruserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getExperiment().getExperimentid() != null && !"-1".equals(user.getExperiment().getExperimentid()) && !"".equals(user.getExperiment().getExperimentid())){
			criteria.add(Restrictions.eq("experiment", user.getExperiment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("experimenterlevel", 3));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}
	public int findtjsyszruserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.or(Restrictions.isNull("experimenterlevel"), Restrictions.ne("experimenterlevel", 3)));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}


	public List<User> findtjsyszruserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
	//	criteria.add(Restrictions.ne("experimenterlevel", 3));
		criteria.add(Restrictions.or(Restrictions.isNull("experimenterlevel"), Restrictions.ne("experimenterlevel", 3)));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}
	/*********实验室主任****************************/
	
	/*********实验员****************************/
	public int findsyyuserCount(User user) {//检索实验员数
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getExperiment().getExperimentid() != null && !"-1".equals(user.getExperiment().getExperimentid()) && !"".equals(user.getExperiment().getExperimentid())){
			criteria.add(Restrictions.eq("experiment", user.getExperiment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("experimenterlevel", 1));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}
	public List<User> findsyyuserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getExperiment().getExperimentid() != null && !"-1".equals(user.getExperiment().getExperimentid()) && !"".equals(user.getExperiment().getExperimentid())){
			criteria.add(Restrictions.eq("experiment", user.getExperiment()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.eq("experimenterlevel", 1));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}
	public int findtjsyyuserCount(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
		criteria.add(Restrictions.or(Restrictions.isNull("experimenterlevel"), Restrictions.ne("experimenterlevel", 1)));
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}
	public List<User> findtjsyyuserT(int begin, int pageSize, User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(user.getCollege().getCollegeid() != null && !"-1".equals(user.getCollege().getCollegeid()) && !"".equals(user.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", user.getCollege()));
		}
		if(user.getUsername() != null &&  !"".equals(user.getUsername())){
			criteria.add(Restrictions.like("username", "%"+user.getUsername()+"%"));
		}
	//	criteria.add(Restrictions.ne("experimenterlevel", 3));
		criteria.add(Restrictions.or(Restrictions.isNull("experimenterlevel"), Restrictions.ne("experimenterlevel", 1)));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}
	/*********实验员****************************/

/*	public int findcurriculumCounttaguser(Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getUser().getUsername() != null &&  !"".equals(curriculum.getUser().getUsername())){
			criteria.add(Restrictions.like("username", "%"+curriculum.getUser().getUsername()+"%"));
		}
		if(curriculum.getUser().getTnum() != null &&  !"".equals(curriculum.getUser().getTnum())){
			criteria.add(Restrictions.eq("username", curriculum.getUser().getTnum()));
		}
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}*/

/*	public List<User> findcurriculumTtaguser(int begin, int pageSize,
			Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getUser().getUsername() != null &&  !"".equals(curriculum.getUser().getUsername())){
			criteria.add(Restrictions.like("username", "%"+curriculum.getUser().getUsername()+"%"));
		}
		if(curriculum.getUser().getTnum() != null &&  !"".equals(curriculum.getUser().getTnum())){
			criteria.add(Restrictions.eq("username", curriculum.getUser().getTnum()));
		}
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return userlist;
	}*/

/*	public int findcurriculumCounttaguserdepartment(Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getDepartment().getDepartmentid() != null &&  !"".equals(curriculum.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", curriculum.getDepartment()));
		}
		if(curriculum.getUser().getUsername() != null &&  !"".equals(curriculum.getUser().getUsername())){
			criteria.add(Restrictions.like("username", "%"+curriculum.getUser().getUsername()+"%"));
		}
		if(curriculum.getUser().getTnum() != null &&  !"".equals(curriculum.getUser().getTnum())){
			criteria.add(Restrictions.eq("username", curriculum.getUser().getTnum()));
		}
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
		if(userlist != null){
			return userlist.size();
		}else{
			return 0;
		}
	}*/

/*	public List<User> findcurriculumTtaguserdepartment(int begin, int pageSize,
			Curriculum curriculum) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		if(curriculum.getCollege().getCollegeid() != null && !"-1".equals(curriculum.getCollege().getCollegeid()) && !"".equals(curriculum.getCollege().getCollegeid())){
			criteria.add(Restrictions.eq("college", curriculum.getCollege()));
		}
		if(curriculum.getDepartment().getDepartmentid() != null &&  !"".equals(curriculum.getDepartment().getDepartmentid())){
			criteria.add(Restrictions.eq("department", curriculum.getDepartment()));
		}
		if(curriculum.getUser().getUsername() != null &&  !"".equals(curriculum.getUser().getUsername())){
			criteria.add(Restrictions.like("username", "%"+curriculum.getUser().getUsername()+"%"));
		}
		if(curriculum.getUser().getTnum() != null &&  !"".equals(curriculum.getUser().getTnum())){
			criteria.add(Restrictions.eq("username", curriculum.getUser().getTnum()));
		}
		List<User> userlist = (List<User>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return userlist;
	}
*/
	
	
	
	//***************************************************************
	public List<User> findUserByCollege(College college) {
		return (List<User>)this.getHibernateTemplate().find("from User where college=?", college);
	}

	public List<User> findUserByDepart(Department depart1) {
		return (List<User>)this.getHibernateTemplate().find("from User where department=?", depart1);
	}

	public int findcurriculumCounttaguser(Curriculum curriculum) {
		return 0;
	}

	public List<User> findcurriculumTtaguser(int begin, int pageSize,
			Curriculum curriculum) {
		return null;
	}

	public int findcurriculumCounttaguserdepartment(Curriculum curriculum) {
		return 0;
	}

	public List<User> findcurriculumTtaguserdepartment(int begin, int pageSize,
			Curriculum curriculum) {
		return null;
	}

	
	public List<User> findUserByExperimenter() {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.isNotNull("experiment"));
		List<User> list =this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
	public List<User> findExperimenterByExperiment(Experiment experiment){
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("experiment", experiment));
		return (List<User>)this.getHibernateTemplate().findByCriteria(criteria);
	}

	public List<User> findTeacherByDepart(Department depart1) {
		return (List<User>)this.getHibernateTemplate().find("from User where department=?", depart1);
	}










	

}
