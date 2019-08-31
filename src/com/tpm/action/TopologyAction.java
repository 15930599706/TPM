package com.tpm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tpm.entity.PageBean;
import com.tpm.entity.Professional;
import com.tpm.entity.Topology;
import com.tpm.service.PPTrainingConceptService;
import com.tpm.service.TopologyService;

public class TopologyAction extends ActionSupport implements ModelDriven<Topology>{
	private Topology topology = new Topology();
	private String tnum;
	private String collegeid;
	private String tag;
	private List<String> pid; 
	public List<String> getPid() {
		return pid;
	}
	public void setPid(List<String> pid) {
		this.pid = pid;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	private Integer currentpage;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	Calendar calendar = Calendar.getInstance();
	private File image;
	private String imageFileName;
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	private PPTrainingConceptService ppTrainingConceptService;
	public void setPpTrainingConceptService(
			PPTrainingConceptService ppTrainingConceptService) {
		this.ppTrainingConceptService = ppTrainingConceptService;
	}
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
	public Topology getModel() {
		return topology;
	}
	private TopologyService topologyService;
	public void setTopologyService(TopologyService topologyService) {
		this.topologyService = topologyService;
	}
	public String tozgkpPage(){
		currentpage = getcurrentpage(currentpage);
		PageBean pageBean = ppTrainingConceptService.zypy(tnum,currentpage,collegeid);
		ServletActionContext.getRequest().setAttribute("collegeid", collegeid);
		setAttr(pageBean);
		return "tozgkpPage";
	}
	public String addtopology() throws IOException{
		if(image!=null){
			String hash_name = "";
			String prefix=imageFileName.substring(imageFileName.lastIndexOf(".")+1);
			Integer count = 0;
			for(int i=0;;i++){
				if(i == 0){
					hash_name = String.valueOf(imageFileName.hashCode())+"."+prefix;
				}else{
					hash_name = String.valueOf(imageFileName.hashCode())+"("+i+")."+prefix;
				}
				count = topologyService.getcountbyname(hash_name);
				if(count == 0)break;
			}
			File desfile=new File(System.getProperty("user.dir")+"/TPM/WebRoot/TopologyImage/"+hash_name);
			FileUtils.copyFile(image, desfile);
			topology.setTopologypath(hash_name);
	//		topology.setTopologytime(dateFormat.format(calendar.getTime()));
			topology.setTopologytime(dateFormat.format(new Date()));
			topologyService.addtopology(topology);
			ServletActionContext.getRequest().setAttribute("tag", "toViewTopologyPage");
			ServletActionContext.getRequest().setAttribute("msg", "上传成功！");
		}
		return "addtopology";
	}
	public String toViewTopologyPage(){
		String tag = topologyService.tototoViewTopology(topology);
		if(tag.equals("ViewTopology")){
			return "toViewTopologyPage";
		}else if(tag.equals("chancetag")){
			return "tochancetagPage";
		}else if(tag.equals("ViewTopologytwo")){
			return "ViewTopologytwo";
		}else{
			return "ViewTopologythree";
		}
	}
	public String revisetoViewTopologyPage(){
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
		return toViewTopologyPage();
	}
	public void showImage(){
		Topology newtopology = topologyService.gettopologybydepartment(topology);
		String path = System.getProperty("user.dir")+"/TPM/WebRoot/TopologyImage/"+newtopology.getTopologypath();
		FileInputStream is = null;
		OutputStream os = null;
		try{
			is=new FileInputStream(path);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.reset();
			os=response.getOutputStream();
			response.setContentType("image/jpg");
			int len = 0;  
	        byte[] buffer = new byte[1024 * 10];  
	        while ((len = is.read(buffer)) != -1){  
	               os.write(buffer,0,len); 
	   	         os.flush();
	            }  
				os.close();  
	            is.close();
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}
	public String updatetopology() throws IOException{
		if(image!=null){
			Topology newtopology = topologyService.gettopologybydepartment(topology);
			File folder = new File(System.getProperty("user.dir")+"/TPM/WebRoot/TopologyImage/");
			File[] files = folder.listFiles();
			for(File file:files){
				if(file.getName().equals(newtopology.getTopologypath())){
					file.delete();
				}
			}
			topologyService.deltopology(newtopology);
			String hash_name = "";
			String prefix=imageFileName.substring(imageFileName.lastIndexOf(".")+1);
			Integer count = 0;
			for(int i=0;;i++){
				if(i == 0){
					hash_name = String.valueOf(imageFileName.hashCode())+"."+prefix;
				}else{
					hash_name = String.valueOf(imageFileName.hashCode())+"("+i+")."+prefix;
				}
				count = topologyService.getcountbyname(hash_name);
				if(count == 0)break;
			}
			File desfile=new File(System.getProperty("user.dir")+"/TPM/WebRoot/TopologyImage/"+hash_name);
			FileUtils.copyFile(image, desfile);
			topology.setTopologypath(hash_name);
		//	topology.setTopologytime(dateFormat.format(calendar.getTime()));
			topology.setTopologytime(dateFormat.format(new Date()));
			topologyService.addtopology(topology);
			ServletActionContext.getRequest().setAttribute("tag", "toViewTopologyPage");
			ServletActionContext.getRequest().setAttribute("msg", "更新成功！");
		}
		return "updatetopology";
	}
	public String changetag(){
		topologyService.changetag(topology);
		return toViewTopologyPage();
	}
	public String revisechangetag(){
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
		return changetag();
	}
	public String chancetag(){
		topologyService.chancetag(topology,tag);
		return toViewTopologyPage();
	}
	public String revisechancetag(){
		String tag = "revise";
		ServletActionContext.getRequest().setAttribute("tag", tag);
		return chancetag();
	}
	
	public String ontopology(){
		topologyService.ontopology(topology);
		return "ontopology";
	}
	public String findtopo(){
		topologyService.findtopo(topology);
		return "findtopo";
	}
	public String addupdatetopology() throws IOException{
		if(pid.size()!=0){
			if(image!=null){
				for(int j=0;j<pid.size();j++){
						Topology newTopology = new Topology();
						Professional professional = new Professional();
						professional.setProfessionalid(pid.get(j));
						newTopology.setProfessional(professional);
						Topology newtopology = topologyService.gettopologybypo(newTopology);
						if(newtopology==null){
							continue;
						}else{
							List<Topology> topologielist = topologyService.gettopologybypath(newtopology.getTopologypath());
							if(topologielist!=null){
								if(topologielist.size()==1){
									File folder = new File(System.getProperty("user.dir")+"/TPM/WebRoot/TopologyImage/");
									File[] files = folder.listFiles();
									for(File file:files){
										if(file.getName().equals(newtopology.getTopologypath())){
											file.delete();
										}
									}
								}
							}
							topologyService.deltopology(newtopology);
						}
						
					}
				
			String hash_name = "";
			String prefix=imageFileName.substring(imageFileName.lastIndexOf(".")+1);
			Integer count = 0;
			for(int i=0;;i++){
				if(i == 0){
					hash_name = String.valueOf(imageFileName.hashCode())+"."+prefix;
				}else{
					hash_name = String.valueOf(imageFileName.hashCode())+"("+i+")."+prefix;
				}
				count = topologyService.getcountbyname(hash_name);
				if(count == 0)break;
			}
			File desfile=new File(System.getProperty("user.dir")+"/TPM/WebRoot/TopologyImage/"+hash_name);
			FileUtils.copyFile(image, desfile);
			for(int j=0;j<pid.size();j++){
				Topology newTopology = new Topology();
				Professional professional = topologyService.getprofessional(pid.get(j));;
				newTopology.setProfessional(professional);
				newTopology.setDepartment(professional.getDepartment());
				newTopology.setTopologypath(hash_name);
		//		newTopology.setTopologytime(dateFormat.format(calendar.getTime()));
				newTopology.setTopologytime(dateFormat.format(new Date()));
				topologyService.addtopology(newTopology);
			}
			ServletActionContext.getRequest().setAttribute("tag", "toViewTopologyPage");
			ServletActionContext.getRequest().setAttribute("msg", "上传成功！");
			}
		}
		return "addupdatetopology";
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
	
	
	public void showImagepp(){
		Topology newtopology = topologyService.gettopologybypo(topology);
		String path = System.getProperty("user.dir")+"/TPM/WebRoot/TopologyImage/"+newtopology.getTopologypath();
		FileInputStream is = null;
		OutputStream os = null;
		try{
			is=new FileInputStream(path);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.reset();
			os=response.getOutputStream();
			response.setContentType("image/jpg");
			int len = 0;  
	        byte[] buffer = new byte[1024 * 10];  
	        while ((len = is.read(buffer)) != -1){  
	               os.write(buffer,0,len); 
	   	         os.flush();
	            }  
				os.close();  
	            is.close();
		}catch(Exception e){
			e.printStackTrace(); 
		}
	}
}
