package com.tpm.dao;


import java.util.List;

import com.tpm.entity.BaseExpTheoInnerRelateExpProjectTheo;

public interface BaseExpTheoInnerRelateExpProjectTheoDao extends BaseDao<BaseExpTheoInnerRelateExpProjectTheo>{

	public List<BaseExpTheoInnerRelateExpProjectTheo> findRelateByExpid(Integer expid);
}
