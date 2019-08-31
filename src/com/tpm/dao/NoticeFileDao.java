package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Notice;
import com.tpm.entity.NoticeFile;

public interface NoticeFileDao extends BaseDao<NoticeFile>{

	List<NoticeFile> OrderfindT(int begin, int pageSize);

	Integer getcountbyname(String uploadFileName);

	

}
