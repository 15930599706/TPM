package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.User;

public interface UserDao extends BaseDao<User>{

	User login(User user);
	
//	int findusername(int id);

	int finduserCount(User user);

	List<User> finduserT(int begin, int pageSize, User user);

	int findxxgluserCount(User user);

	List<User> findxxgluserT(int begin, int pageSize, User user);

	int findtjxxuserCount(User user);

	List<User> findtjxxuserT(int begin, int pageSize, User user);

	int findxygluserCount(User user);

	List<User> findxygluserT(int begin, int pageSize, User user);

	int findtjxyuserCount(User user);

	List<User> findtjxyuserT(int begin, int pageSize, User user);

	int findxgluserCount(User user);

	List<User> findxgluserT(int begin, int pageSize, User user);

	int findtjxuserCount(User user);

	List<User> findtjxuserT(int begin, int pageSize, User user);

/*	int findcurriculumCounttaguser(Curriculum curriculum);

	List<User> findcurriculumTtaguser(int begin, int pageSize,
			Curriculum curriculum);

	int findcurriculumCounttaguserdepartment(Curriculum curriculum);

	List<User> findcurriculumTtaguserdepartment(int begin, int pageSize,
			Curriculum curriculum);*/
	
	
	//***********************
	List<User> findUserByCollege(College college);

	List<User> findUserByDepart(Department depart1);

	List<User> findUserByExperimenter();

	List<User> findExperimenterByExperiment(Experiment experiment);

	int findsyszruserCount(User user);

	List<User> findsyszruserT(int begin, int pageSize, User user);

	int findtjsyszruserCount(User user);

	List<User> findtjsyszruserT(int begin, int pageSize, User user);

	
	int findsyyuserCount(User user);

	List<User> findsyyuserT(int begin, int pageSize, User user);

	int findtjsyyuserCount(User user);

	List<User> findtjsyyuserT(int begin, int pageSize, User user);

	List<User> findTeacherByDepart(Department depart1);

	
}
 