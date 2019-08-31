package com.tpm.dao;

import java.util.List;

import com.tpm.entity.OtherContent;

public interface OtherContentDao extends BaseDao<OtherContent> {

	List<OtherContent> getbyOtherContent(String syllabusId);

}
