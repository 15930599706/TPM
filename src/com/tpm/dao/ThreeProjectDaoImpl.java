package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ContentTheo;
import com.tpm.entity.ThreeProject;

public class ThreeProjectDaoImpl extends BaseDaoImpl<ThreeProject> implements ThreeProjectDao {
	public List<ThreeProject> getbyThreeProject(String syllabusId)
	{
		List<ThreeProject> threeProjectlist;
		threeProjectlist = this.getHibernateTemplate().find("from ThreeProject where syllabusID=?", syllabusId);
		return threeProjectlist;
	}
}
