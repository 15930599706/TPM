package com.tpm.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tpm.entity.Department;

public class ExportCurriculumMatrixDaoImpl extends HibernateDaoSupport implements ExportCurriculumMatrixDao {

	public Department findDepartmentName(String departmentID) {
		List<Department> departmentlist = this.getHibernateTemplate().find("from Department where departmentid=?",departmentID) ;
		return departmentlist.get(0);
	}

}
