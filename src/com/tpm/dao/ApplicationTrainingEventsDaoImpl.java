package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationTrainingEvents;
import com.tpm.entity.Professional;
import com.tpm.entity.TrainingEvents;

public class ApplicationTrainingEventsDaoImpl extends BaseDaoImpl<ApplicationTrainingEvents> implements
		ApplicationTrainingEventsDao {

	public List<ApplicationTrainingEvents> findbyProfessional(
			Professional professional) {
		List<ApplicationTrainingEvents> applicationTrainingEventsList = (List<ApplicationTrainingEvents>)this.getHibernateTemplate().find("from ApplicationTrainingEvents where professional=?", professional);
		if(applicationTrainingEventsList != null && applicationTrainingEventsList.size() != 0)
		{
			return applicationTrainingEventsList;
		}
		else{
			return null;
		}
	}

	public ApplicationTrainingEvents gettotalli(Professional professional) {
		List<ApplicationTrainingEvents> applicationTrainingEventslist = (List<ApplicationTrainingEvents>)this.getHibernateTemplate().
				find("from ApplicationTrainingEvents where professional=? and trainingEvents.totaltag=?", professional,2);
		if(applicationTrainingEventslist !=null && applicationTrainingEventslist.size() != 0){
			return applicationTrainingEventslist.get(0);
		}else{
			return null;
		}
	}

	public ApplicationTrainingEvents gettotal(Professional professional) {
		List<ApplicationTrainingEvents> applicationTrainingEventslist = (List<ApplicationTrainingEvents>)this.getHibernateTemplate().
				find("from ApplicationTrainingEvents where professional=? and trainingEvents.totaltag=?", professional,1);
		if(applicationTrainingEventslist !=null && applicationTrainingEventslist.size() != 0){
			return applicationTrainingEventslist.get(0);
		}else{
			return null;
		}
	}

	public List<ApplicationTrainingEvents> getbyProfessionalnototal(Professional professional) {
		List<ApplicationTrainingEvents> applicationTrainingEventslist = (List<ApplicationTrainingEvents>)this.getHibernateTemplate().
				find("from ApplicationTrainingEvents where professional=? and trainingEvents.totaltag=?", professional,0);
		if(applicationTrainingEventslist !=null && applicationTrainingEventslist.size() != 0){
			return applicationTrainingEventslist;
		}else{
			return null;
		}
	}
	public List<ApplicationTrainingEvents> getbyProfessionalEvents(
			Professional professional) {
		List<ApplicationTrainingEvents> applicationTrainingEventslist = (List<ApplicationTrainingEvents>)this.getHibernateTemplate().
				find("from ApplicationTrainingEvents where professional=?", professional);
		if(applicationTrainingEventslist !=null && applicationTrainingEventslist.size() != 0){
			return applicationTrainingEventslist;
		}else{
			return null;
		}
	}


	public List<ApplicationTrainingEvents> getbyProfessionalEventslilunke(
			Professional professional) {
		List<ApplicationTrainingEvents> applicationTrainingEventslist = (List<ApplicationTrainingEvents>)this.getHibernateTemplate().
				find("from ApplicationTrainingEvents where professional=? and trainingEvents.totaltag=?", professional,2);
		if(applicationTrainingEventslist !=null && applicationTrainingEventslist.size() != 0){
			return applicationTrainingEventslist;
		}else{
			return null;
		}
	}
}
