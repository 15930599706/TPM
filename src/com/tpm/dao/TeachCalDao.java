package com.tpm.dao;

import java.util.List;

import com.tpm.entity.NoticeFile;
import com.tpm.entity.TeachCal;
import com.tpm.entity.User;

public interface TeachCalDao extends BaseDao<TeachCal> 
{

	Integer getCountByName(String uploadFileName);

	List<TeachCal> findByUser(User teacher);

	List<TeachCal> findByUsersDepart(User teacher);

	List<TeachCal> findByUsersCollege(User teacher);
	

}
