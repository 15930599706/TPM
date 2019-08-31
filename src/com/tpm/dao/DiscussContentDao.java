package com.tpm.dao;

import java.util.List;

import com.tpm.entity.DiscussContent;

public interface DiscussContentDao extends BaseDao<DiscussContent> {

	List<DiscussContent> getbydiscussContent(String syllabusId);

}
