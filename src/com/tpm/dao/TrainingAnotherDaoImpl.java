package com.tpm.dao;
import java.util.List;

import com.tpm.entity.TrainingAnother;
public class TrainingAnotherDaoImpl extends BaseDaoImpl<TrainingAnother> implements
		TrainingAnotherDao {

	public TrainingAnother getbyTrainingAnother(TrainingAnother trainingAnother) {

		List<TrainingAnother> trainingAnotherlist;
		trainingAnotherlist=this.getHibernateTemplate().find("from TrainingAnother where department=?",trainingAnother.getDepartment());
		if(trainingAnotherlist.size() != 0)
			return trainingAnotherlist.get(0);
		else return null;
	}

}
