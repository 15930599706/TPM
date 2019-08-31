package com.tpm.dao;
import java.util.List;
import com.tpm.entity.ThreeProject;
public interface ThreeProjectDao extends BaseDao<ThreeProject> {
	List<ThreeProject> getbyThreeProject(String syllabusId);
}
