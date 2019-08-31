package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationTrainingEvents;
import com.tpm.entity.Professional;

public interface ApplicationTrainingEventsDao extends BaseDao<ApplicationTrainingEvents> {

	List<ApplicationTrainingEvents> findbyProfessional(Professional professional);

	ApplicationTrainingEvents gettotalli(Professional professional);

	ApplicationTrainingEvents gettotal(Professional professional);

	List<ApplicationTrainingEvents> getbyProfessionalnototal(
			Professional professional);

	List<ApplicationTrainingEvents> getbyProfessionalEvents(
			Professional professional);

	List<ApplicationTrainingEvents> getbyProfessionalEventslilunke(
			Professional professional);

}
