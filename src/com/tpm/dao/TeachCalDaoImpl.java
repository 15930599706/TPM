package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Notice;
import com.tpm.entity.NoticeFile;
import com.tpm.entity.TeachCal;
import com.tpm.entity.User;

public class TeachCalDaoImpl extends BaseDaoImpl<TeachCal> implements TeachCalDao  {

	public Integer getCountByName(String uploadFileName) {
		List<TeachCal> teachCalFilelist = (List<TeachCal>)this.getHibernateTemplate().
				find("from TeachCal where teachCalfilename = ?", uploadFileName);
		if(teachCalFilelist.size() != 0){
			return teachCalFilelist.size();
		}else{
			return 0;
		}
	}

	public List<TeachCal> findByUser(User teacher) {
		return (List<TeachCal>)this.getHibernateTemplate().find("from TeachCal where user=?", teacher);
	}

	public List<TeachCal> findByUsersDepart(User teacher) {
		List<TeachCal> listTeachCal=this.getHibernateTemplate().find("from TeachCal where user.department=?", teacher.getDepartment());
		return listTeachCal;
	}

	public List<TeachCal> findByUsersCollege(User teacher) {
		List<TeachCal> listTeachCal=this.getHibernateTemplate().find("from TeachCal where user.college=?", teacher.getCollege());
		return listTeachCal;
	}
	
	
}
