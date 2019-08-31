package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;
import com.tpm.entity.Department;

public interface DepartmentDao extends BaseDao<Department>{

	Department getbyname(String departmentname);

	int findCountbyCollege(College college);

	List<Department> findTbyCollege(int begin, int pageSize, College college);

	int finddepartmentCount(College college);

	List<Department> finddepartmentT(int begin, int pageSize, College college);

	List<Department> findTbyCollege(College college);

	List<Department> getone(String departmentid);



	


}
