package com.tpm.dao;

import java.util.List;

import com.tpm.entity.NatureOfCourse;
@SuppressWarnings("all")
public class NatureOfCourseDaoImpl extends BaseDaoImpl<NatureOfCourse> implements NatureOfCourseDao {

	public NatureOfCourse getbyname(String natureOfCoursename) {
		List<NatureOfCourse> nalist = (List<NatureOfCourse>)this.getHibernateTemplate().
				find("from NatureOfCourse where natureOfCoursename=?", natureOfCoursename);
		if(nalist.size() != 0){
			return nalist.get(0);
		}else{
			return null;
		}
	}

	public List<NatureOfCourse> getDepartment() {
		List<NatureOfCourse> nalist = (List<NatureOfCourse>)this.getHibernateTemplate().
				find("from NatureOfCourse where natureOfCoursefor=?", "院");
		return nalist;
	}

	public List<NatureOfCourse> getAll() {
		List<NatureOfCourse> natureOfCourselist = (List<NatureOfCourse>)this.getHibernateTemplate().
				find("from NatureOfCourse" );
		return natureOfCourselist;
	}

}
