package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Notice;

public interface NoticeDao extends BaseDao<Notice>{

	List<Notice> OrderfindT(int begin, int pageSize);

}
