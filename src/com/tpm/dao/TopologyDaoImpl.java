package com.tpm.dao;

import java.util.List;

import com.tpm.entity.Department;
import com.tpm.entity.Professional;
import com.tpm.entity.Topology;
@SuppressWarnings("all")
public class TopologyDaoImpl extends BaseDaoImpl<Topology> implements TopologyDao {

	public Topology getbydepartment(Department department) {
		List<Topology> topologylist = (List<Topology>)this.getHibernateTemplate().
				find("from Topology where department = ?", department);
		if(topologylist.size() != 0){
			return topologylist.get(0);
		}else{
			return null;
		}
	}
	public Integer getcountbyname(String hash_name) {
		List<Topology> topologylist = (List<Topology>)this.getHibernateTemplate().
				find("from Topology where topologypath = ?", hash_name);
		if(topologylist.size() != 0){
			return topologylist.size();
		}else{
			return 0;
		}
	}
	public List<Topology> findbydepartment(Department department) {
		List<Topology> topologylist = (List<Topology>)this.getHibernateTemplate().
				find("from Topology where department = ?", department);
		if(topologylist.size() != 0){
			return topologylist;
		}else{
			return null;
		}
	}
	public Topology getbyprofessional(Professional professional) {
		List<Topology> topologylist = (List<Topology>)this.getHibernateTemplate().
				find("from Topology where professional = ?", professional);
		if(topologylist.size() != 0){
			return topologylist.get(0);
		}else{
			return null;
		}
	}
	public List<Topology> gettopologybypath(String topologypath) {
		List<Topology> topologylist = (List<Topology>)this.getHibernateTemplate().
				find("from Topology where topologypath = ?", topologypath);
		if(topologylist.size() != 0){
			return topologylist;
		}else{
			return null;
		}
	}

	
}
