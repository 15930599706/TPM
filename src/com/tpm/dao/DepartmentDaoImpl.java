package com.tpm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
@SuppressWarnings("all")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

	public Department getbyname(String departmentname) {
		List<Department> delist = (List<Department>)this.getHibernateTemplate().
				find("from Department where departmentCname = ?", departmentname);
		if(delist.size() != 0){
			return delist.get(0);
		}else{
			return null;
		}
	}

	public int findCountbyCollege(College college) {
		List<Department> delist = (List<Department>)this.getHibernateTemplate().
				find("from Department where college = ?", college);
		if(delist.size() != 0){
			return delist.size();
		}else{
			return 0;
		}
	}

	public List<Department> findTbyCollege(int begin, int pageSize,
			College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class).add(Restrictions.eq("college",college));
		List<Department> delist = (List<Department>)this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return delist;
	}

	public int finddepartmentCount(College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		if(college != null){
			if(college.getCollegeid() != null && !"-1".equals(college.getCollegeid()) && !"".equals(college.getCollegeid())){
				criteria.add(Restrictions.eq("college", college));
			}
		}
		List<Department> delist = (List<Department>)this.getHibernateTemplate().findByCriteria(criteria);
		if(delist.size() != 0){
			return delist.size();
		}else{
			return 0;
		}
	}

	public List<Department> finddepartmentT(int begin, int pageSize,
			College college) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		if(college != null){
			if(college.getCollegeid() != null && !"-1".equals(college.getCollegeid()) && !"".equals(college.getCollegeid())){
				criteria.add(Restrictions.eq("college", college));
			}
		}
		return (List<Department>)this.getHibernateTemplate().findByCriteria(criteria,begin,pageSize);
	}

	public List<Department> findTbyCollege(College college) {
		List<Department> delist = (List<Department>)this.getHibernateTemplate().
				find("from Department where college = ?", college);
		if(delist.size() != 0){
			return delist;
		}else{
			return null;
		}
	}

	public List<Department> getone(String departmentid) {
		List<Department> delist = (List<Department>)this.getHibernateTemplate().
				find("from Department where departmentid = ?", departmentid);
		return delist;
	}

	

	

	

	
}
