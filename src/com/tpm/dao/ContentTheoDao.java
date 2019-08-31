package com.tpm.dao;

import java.util.List;

import com.tpm.entity.ContentTheo;

public interface ContentTheoDao extends BaseDao<ContentTheo>{

	List<ContentTheo> getbycontentTheo(String syllabusId);
}
