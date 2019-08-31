package com.tpm.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.Ability;
import com.tpm.entity.Analysis;
import com.tpm.entity.PageBean;
import com.tpm.service.AbilityService;
import com.tpm.service.AnalysisService;
import com.tpm.service.PPTrainingConceptService;

public class AnalysisAction extends ActionSupport implements ModelDriven<Analysis>{
	private Analysis analysis = new Analysis();
	private String departmentid;
	private String data;
	private String tnum;
	private String collegeid;
	private Integer currentpage;
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	public String getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public Analysis getModel() {
		return analysis;
	}
	private AnalysisService analysisService;
	private AbilityService abilityService;
	private PPTrainingConceptService ppTrainingConceptService;
	public void setPpTrainingConceptService(
			PPTrainingConceptService ppTrainingConceptService) {
		this.ppTrainingConceptService = ppTrainingConceptService;
	}
	public void setAbilityService(AbilityService abilityService) {
		this.abilityService = abilityService;
	}
	public void setAnalysisService(AnalysisService analysisService) {
		this.analysisService = analysisService;
	}
	
	
	public String tobyyqPage(){
		Integer count = analysisService.byyq(departmentid);
		if(count == 0){
			ServletActionContext.getRequest().setAttribute("tag", "topyxxPage");
			ServletActionContext.getRequest().setAttribute("msg", "您需要先填写完整毕业生应获得的知识和能力！");
			return "noAbility";
		}else{
			return "tobyyqPage";
		}
	}
	public String tobjbyPage(){
		Integer count = analysisService.byyq(departmentid);
		if(count == 0){
			ServletActionContext.getRequest().setAttribute("tag", "tobyyqPage");
			ServletActionContext.getRequest().setAttribute("msg", "该信息尚未填写");
			return "noAbility";
		}else{
			return "tobjbyPage";
		}
	}
	public String tobyyq(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = ppTrainingConceptService.zypy(tnum,currentpage,collegeid);
		ServletActionContext.getRequest().setAttribute("collegeid", collegeid);
		setAttr(pageBean);
		return "tobyyq";
	}
	public void addanalysis(){
		try {
			String date = URLDecoder.decode(data,"UTF-8");
			JSONArray ja= new JSONArray(date);
				for(int i=0;i<ja.length();i++){
					 Iterator<?> items=ja.getJSONObject(i).keys();
					 Analysis newAnalysis = new Analysis();
					 while(items.hasNext()){
						 String keyName=items.next().toString();
						 Ability ability = new Ability();
						 if(keyName.equals("title")){
							 ability = abilityService.getbytitle(ja.getJSONObject(i).getString(keyName),departmentid);
							 List<Analysis> analysislist = analysisService.getbyability(ability);
							 if(analysislist != null && analysislist.size() != 0){
								 for(int k=0;k<analysislist.size();k++){
									 analysisService.del(analysislist.get(k));
								 }
							 }
							 newAnalysis.setAbility(ability);
							 System.out.print(newAnalysis.getAbility().getAbilityid()+"2132111111111100000000000000000000000000000000000");
						 }if(keyName.equals("point_arr")){
							 System.out.print(newAnalysis+"000000000000000000000000000000000000000000000000000000000");
							 String[] arr = ja.getJSONObject(i).getString(keyName).substring(2, ja.getJSONObject(i).getString(keyName).length()-2).split("\",\"");
							 for(int j=0;j<arr.length;j++){
								 Analysis newanalysis123 = new Analysis();
								 String analysiscontent = arr[j].replaceAll("\\\\\\\"","\""); 
								 newanalysis123.setAbility(newAnalysis.getAbility());
								// System.out.print(newanalysis123.getAbility().getAbilityid()+"2132111111111100000000000000000000000000000000000");
								 newanalysis123.setAnalysiscontent(analysiscontent);
								 analysisService.addanalysis(newanalysis123);
							 }
						 }
					 }
				}
			//System.out.println(ja);
			//JSONObject jsonObj = JSONObject.fromObject(date); 
			//String title = jsonObj.getString("tltle"); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		};
		
	}
	
	
	
	
	
	
	
	private void setAttr(PageBean pageBean) {
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	}
	private Integer getcurrentpage(Integer currentpage) {
		if(currentpage == null||currentpage == 0){
			currentpage=1;
		}
		return currentpage;
	}

}
