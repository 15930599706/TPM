package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.User;

public interface CurriculumDao extends BaseDao<Curriculum>{

	int findcurriculumCount(Curriculum curriculum);

	List<Curriculum> findcurriculumT(int begin, int pageSize,
			Curriculum curriculum);

	int findcurriculumnewCount(Curriculum curriculum);

	List<Curriculum> findcurriculumnewT(int begin, int pageSize,
			Curriculum curriculum);

	int findcurriculumnewCount(Curriculum curriculum, College college);

	List<Curriculum> findcurriculumnewT(int begin, int pageSize,
			Curriculum curriculum, College college);

	int findcurriculumCounttag(Curriculum curriculum, String departmenttag);

	List<Curriculum> findcurriculumTtag(int begin, int pageSize,
			Curriculum curriculum, String departmenttag);

	int findcurriculumCounttaguser(Curriculum curriculum, String usertag);

/*
	int findcurriculumCounttaguserdepartment(Curriculum curriculum,
			String usertag);

	List<Curriculum> findcurriculumTtaguserdepartment(int begin, int pageSize,
			Curriculum curriculum, String usertag);*/

	List<Curriculum> findcurriculumTtaguser(int begin, int pageSize,
			Curriculum curriculum, String usertag);

	List<Curriculum> getbyuser(User user);

	List<Curriculum> getbyuserne(User user);
	
	List<Curriculum> findCurrByCollege(College college);

	List<Curriculum> findCurrByCurrcname(String curriculumCname);
	
	List<Curriculum> findCurrByCurrcid(String curriculumid);

	Curriculum getbyall(Curriculum curriculum);

	List<Curriculum> findAllwithout();

	Curriculum findCurriculumNature(String curriculumId);

}
