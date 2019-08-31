package com.tpm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.DepartmentDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.dao.TopologyDao;
import com.tpm.dao.TopologyTagDao;
import com.tpm.dao.UserDao;
import com.tpm.entity.Department;
import com.tpm.entity.Professional;
import com.tpm.entity.Topology;
import com.tpm.entity.TopologyTag;

@Transactional
public class TopologyService {
	private TopologyDao topologyDao;
	private UserDao userDao;
	private DepartmentDao departmentDao;
	private ProfessionalDao professionalDao;
	private TopologyTagDao topologyTagDao;
	
	public void setTopologyTagDao(TopologyTagDao topologyTagDao) {
		this.topologyTagDao = topologyTagDao;
	}
	public void setProfessionalDao(ProfessionalDao professionalDao) {
		this.professionalDao = professionalDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setTopologyDao(TopologyDao topologyDao) {
		this.topologyDao = topologyDao;
	}


	public String ViewTopology(Topology topology) {
		Department department = departmentDao.get(topology.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("department", department);
		Topology newtopology = topologyDao.getbydepartment(department);
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
		if(newtopology != null){
			
		}else{
			ServletActionContext.getRequest().setAttribute("msg", department.getDepartmentCname()+"还未上传主干课拓扑图，请及时上传！");
		}
		return "ViewTopology";
	}
	public String ViewTopologytwo(Topology topology) {
		Department department = departmentDao.get(topology.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("department", department);
		Topology newtopology = topologyDao.getbydepartment(department);
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
		if(newtopology != null){
			
		}else{
			ServletActionContext.getRequest().setAttribute("msg", department.getDepartmentCname()+"还未上传主干课拓扑图，请及时上传！");
		}
		return "ViewTopologytwo";
	}
	public void addtopology(Topology topology) {
		topologyDao.add(topology);
	}
	public Integer getcountbyname(String hash_name) {
		return topologyDao.getcountbyname(hash_name);
	}
	public Topology gettopologybydepartment(Topology topology) {
		Department department = departmentDao.get(topology.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("department", department);
		Topology newtopology = topologyDao.getbydepartment(department);
		return newtopology;
	}
	public void deltopology(Topology topology) {
		topologyDao.delete(topology);
	}
	public String tototoViewTopology(Topology topology) {
		Department department = departmentDao.get(topology.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Professional> professionallist = professionalDao.findbydepartment(department);
		if(professionallist == null){
			return ViewTopology(topology);
		}else{
			List<TopologyTag> topolofyTaglist = topologyTagDao.findbydepartment(department);
			if(topolofyTaglist == null){
				return "chancetag";
			}else{
				if(topolofyTaglist.get(0).getTag().equals("0")){
					return ViewTopologytwo(topology);
				}else{
					ServletActionContext.getRequest().setAttribute("professionallist", professionallist);
					return "ViewTopologythree";
				}
			}
		}
	}
	public void changetag(Topology topology) {
		Department department = departmentDao.get(topology.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("department", department);
		List<TopologyTag> topolofyTaglist = topologyTagDao.findbydepartment(department);
		if(topolofyTaglist.get(0).getTag().equals("0")){
			topolofyTaglist.get(0).setTag("1");
		}else{
			topolofyTaglist.get(0).setTag("0");
		}
		topologyTagDao.update(topolofyTaglist.get(0));
		List<Topology> topologielist = topologyDao.findbydepartment(department);
		if(topologielist != null){
			for(int i=0;i<topologielist.size();i++){
				topologyDao.delete(topologielist.get(i));
			}
		}
	}
	public void chancetag(Topology topology, String tag) {
		Department department = departmentDao.get(topology.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("department", department);
		TopologyTag topologyTag = new TopologyTag();
		topologyTag.setDepartment(department);
		topologyTag.setTag(tag);
		topologyTagDao.add(topologyTag);
	}
	public void ontopology(Topology topology) {
		Department department = departmentDao.get(topology.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Topology> topologielist = topologyDao.findbydepartment(department);
		List<Professional> professionallist = professionalDao.findbydepartment(department);
		List<Professional> professionallistyes = new ArrayList<Professional>();
		if(topologielist==null){
			
		}else{
			for(int i=0;i<topologielist.size();i++){
				for(int j=professionallist.size()-1;j>=0;j--){
					if(topologielist.get(i).getProfessional()==professionallist.get(j)){
						professionallistyes.add(professionallist.get(j));//有记录的
						professionallist.remove(j);//无记录的
						continue;
					}
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("professionallistyes", professionallistyes);
		ServletActionContext.getRequest().setAttribute("professionallistno", professionallist);
	}
	public void findtopo(Topology topology) {
		Professional professional = professionalDao.get(topology.getProfessional().getProfessionalid());
		Department department = professional.getDepartment();
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Professional> professionallist = professionalDao.findbydepartment(department);
		Topology newtopology =topologyDao.getbyprofessional(professional);
		ServletActionContext.getRequest().setAttribute("pid", professional);
		ServletActionContext.getRequest().setAttribute("professionallist", professionallist);
		if(newtopology!= null){
			ServletActionContext.getRequest().setAttribute("hf", "hf");
		}
	}
	public Topology gettopologybypo(Topology topology) {
		Professional professional = professionalDao.get(topology.getProfessional().getProfessionalid());
		Department department = professional.getDepartment();
		ServletActionContext.getRequest().setAttribute("department", department);
		Topology newtopology =topologyDao.getbyprofessional(professional);
		return newtopology;
	}
	public List<Topology> gettopologybypath(String topologypath) {
		return topologyDao.gettopologybypath(topologypath);
	}
	public Professional getprofessional(String string) {
		return professionalDao.get(string);
	}
	
	
}
