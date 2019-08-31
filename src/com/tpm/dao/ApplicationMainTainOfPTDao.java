package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ApplicationMainTainOfPT;
import com.tpm.entity.Professional;

public interface ApplicationMainTainOfPTDao extends BaseDao<ApplicationMainTainOfPT> {

	List<ApplicationMainTainOfPT> findbyProfessional(Professional professional);

}
