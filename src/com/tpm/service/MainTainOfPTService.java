package com.tpm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.mapping.Array;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.tpm.dao.ApplicationMainTainOfPTDao;
import com.tpm.dao.ApplicationTrainingEventsDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.MainTainOfPTDao;
import com.tpm.dao.MainTainOfPTTagDao;
import com.tpm.dao.PTBasicInformationDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.dao.TrainingEventsDao;
import com.tpm.entity.ApplicationMainTainOfPT;
import com.tpm.entity.ApplicationTrainingEvents;
import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPT;
import com.tpm.entity.MainTainOfPTTag;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.Professional;
import com.tpm.entity.Topology;
import com.tpm.entity.TopologyTag;
import com.tpm.entity.TrainingEvents;
@Transactional
public class MainTainOfPTService {
	private MainTainOfPTDao mainTainOfPTDao;
	private DepartmentDao departmentDao;
	private PTBasicInformationDao ptBasicInformationDao;
	private TrainingEventsDao trainingEventsDao;
	private ProfessionalDao professionalDao;
	private MainTainOfPTTagDao mainTainOfPTTagDao;
	private ApplicationMainTainOfPTDao applicationMainTainOfPTDao;
	private ApplicationTrainingEventsDao applicationTrainingEventsDao;
	public void setApplicationTrainingEventsDao(
			ApplicationTrainingEventsDao applicationTrainingEventsDao) {
		this.applicationTrainingEventsDao = applicationTrainingEventsDao;
	}
	public void setApplicationMainTainOfPTDao(
			ApplicationMainTainOfPTDao applicationMainTainOfPTDao) {
		this.applicationMainTainOfPTDao = applicationMainTainOfPTDao;
	}
	public void setMainTainOfPTTagDao(MainTainOfPTTagDao mainTainOfPTTagDao) {
		this.mainTainOfPTTagDao = mainTainOfPTTagDao;
	}
	public void setProfessionalDao(ProfessionalDao professionalDao) {
		this.professionalDao = professionalDao;
	}
	public void setTrainingEventsDao(TrainingEventsDao trainingEventsDao) {
		this.trainingEventsDao = trainingEventsDao;
	}
	public void setPtBasicInformationDao(PTBasicInformationDao ptBasicInformationDao) {
		this.ptBasicInformationDao = ptBasicInformationDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void setMainTainOfPTDao(MainTainOfPTDao mainTainOfPTDao) {
		this.mainTainOfPTDao = mainTainOfPTDao;
	}
	public boolean checkpyxx(String departmentid) {
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		PTBasicInformation ptBasicInformation = ptBasicInformationDao.getbydepartment(department);
		if(ptBasicInformation == null){
			ActionContext context=ActionContext.getContext();			
			context.put("msg", "您需要先填写培养计划基本信息！");
			return false;
		}
		else
		{
			return true;
		}
	}
	public void bjap(String departmentid) {
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		PTBasicInformation ptBasicInformation = ptBasicInformationDao.getbydepartment(department);
		List<MainTainOfPT> mainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
			if(mainTainOfPTlist == null || mainTainOfPTlist.size() == 0){
				if(ptBasicInformation.getLearningTime().equals("四年")){
					for(int i=0;i<8;i++){
						MainTainOfPT mainTainOfPT = new MainTainOfPT();
						mainTainOfPT.setDepartment(department);
						mainTainOfPT.setWeek1("");
						mainTainOfPT.setWeek2("");
						mainTainOfPT.setWeek3("");
						mainTainOfPT.setWeek4("");
						mainTainOfPT.setWeek5("");
						mainTainOfPT.setWeek6("");
						mainTainOfPT.setWeek7("");
						mainTainOfPT.setWeek8("");
						mainTainOfPT.setWeek9("");
						mainTainOfPT.setWeek10("");
						mainTainOfPT.setWeek11("");
						mainTainOfPT.setWeek12("");
						mainTainOfPT.setWeek13("");
						mainTainOfPT.setWeek14("");
						mainTainOfPT.setWeek15("");
						mainTainOfPT.setWeek16("");
						mainTainOfPT.setWeek17("");
						mainTainOfPT.setWeek18("");
						mainTainOfPT.setWeek19("");
						mainTainOfPT.setWeek20("");
						mainTainOfPT.setSemester(i+1);
						mainTainOfPTDao.add(mainTainOfPT);
					}
				}
				if(ptBasicInformation.getLearningTime().equals("五年")){
					for(int i=0;i<10;i++){
						MainTainOfPT mainTainOfPT = new MainTainOfPT();
						mainTainOfPT.setDepartment(department);
						mainTainOfPT.setWeek1("");
						mainTainOfPT.setWeek2("");
						mainTainOfPT.setWeek3("");
						mainTainOfPT.setWeek4("");
						mainTainOfPT.setWeek5("");
						mainTainOfPT.setWeek6("");
						mainTainOfPT.setWeek7("");
						mainTainOfPT.setWeek8("");
						mainTainOfPT.setWeek9("");
						mainTainOfPT.setWeek10("");
						mainTainOfPT.setWeek11("");
						mainTainOfPT.setWeek12("");
						mainTainOfPT.setWeek13("");
						mainTainOfPT.setWeek14("");
						mainTainOfPT.setWeek15("");
						mainTainOfPT.setWeek16("");
						mainTainOfPT.setWeek17("");
						mainTainOfPT.setWeek18("");
						mainTainOfPT.setWeek19("");
						mainTainOfPT.setWeek20("");
						mainTainOfPT.setSemester(i+1);
						mainTainOfPTDao.add(mainTainOfPT);
					}
				}
				List<TrainingEvents> trainingEventslist = trainingEventsDao.getbydepartment(department);
				if(trainingEventslist != null ){
					for(int i =0;i<trainingEventslist.size();i++){
						trainingEventsDao.delete(trainingEventslist.get(i));
					}
				}
				TrainingEvents ntrainingEvents = new TrainingEvents();
				ntrainingEvents.setDepartment(department);
				ntrainingEvents.setTrainingEventsname("理论教学");
				ntrainingEvents.setTrainingEventscode("A");
				ntrainingEvents.setTotaltag(2);
				trainingEventsDao.add(ntrainingEvents);
				TrainingEvents trainingEvents = new TrainingEvents();
				trainingEvents.setDepartment(department);
				trainingEvents.setTrainingEventsname("小计");
				trainingEvents.setTotaltag(1);
				trainingEventsDao.add(trainingEvents);
				
			}else{
				if(ptBasicInformation.getLearningTime().equals("四年")){
					List<MainTainOfPT> newmainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
					if(newmainTainOfPTlist.size()==10){
						mainTainOfPTDao.delete(newmainTainOfPTlist.get(8));
						mainTainOfPTDao.delete(newmainTainOfPTlist.get(9));
					}
				}
				if(ptBasicInformation.getLearningTime().equals("五年")){
					List<MainTainOfPT> newmainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
					if(newmainTainOfPTlist.size()==8){
						for(int i=8;i<10;i++){
							MainTainOfPT mainTainOfPT = new MainTainOfPT();
							mainTainOfPT.setDepartment(department);
							mainTainOfPT.setWeek1("");
							mainTainOfPT.setWeek2("");
							mainTainOfPT.setWeek3("");
							mainTainOfPT.setWeek4("");
							mainTainOfPT.setWeek5("");
							mainTainOfPT.setWeek6("");
							mainTainOfPT.setWeek7("");
							mainTainOfPT.setWeek8("");
							mainTainOfPT.setWeek9("");
							mainTainOfPT.setWeek10("");
							mainTainOfPT.setWeek11("");
							mainTainOfPT.setWeek12("");
							mainTainOfPT.setWeek13("");
							mainTainOfPT.setWeek14("");
							mainTainOfPT.setWeek15("");
							mainTainOfPT.setWeek16("");
							mainTainOfPT.setWeek17("");
							mainTainOfPT.setWeek18("");
							mainTainOfPT.setWeek19("");
							mainTainOfPT.setWeek20("");
							mainTainOfPT.setSemester(i+1);
							mainTainOfPTDao.add(mainTainOfPT);
						}
					}
				}
			}
			List<MainTainOfPT> newmainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
			List<TrainingEvents> newtrainingEventslist = trainingEventsDao.getbydepartmentnototal(department);
			TrainingEvents newTrainingEvents = trainingEventsDao.gettotal(department);
			TrainingEvents liTrainingEvents = trainingEventsDao.gettotalli(department);
			ActionContext context=ActionContext.getContext();			
			context.put("mainTainOfPTlist", newmainTainOfPTlist);//学期
			context.put("trainingEventslist", newtrainingEventslist);//可删除
			context.put("trainingEvents", newTrainingEvents);
			context.put("liTrainingEvents", liTrainingEvents);
	}
	
	public void bjapNew(String departmentid, List<String> professionalListNew) {
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Professional> newProfessionalList = new ArrayList<Professional>();
		for(int j=0;j<professionalListNew.size();j++)
		{
			Professional professional = professionalDao.get(professionalListNew.get(j));
			newProfessionalList.add(professional);
		}
		ServletActionContext.getRequest().setAttribute("professionalList", newProfessionalList);
		
		//在新编写前，若数据库中存在该专业方向的记录，则删除
		for(int i=0;i<newProfessionalList.size();i++)
		{
			List<ApplicationMainTainOfPT> newapplicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionalList.get(i));
			if(newapplicationMainTainOfPTlist != null)
			{
				for(int j=0;j<newapplicationMainTainOfPTlist.size();j++)
				{
					mainTainOfPTDao.delete(newapplicationMainTainOfPTlist.get(j).getMainTainOfPT());
				}
			}
			
			List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(newProfessionalList.get(i));
			if(applicationTrainingEventslist != null)
			{
				for(int j=0;j<applicationTrainingEventslist.size();j++)
				{
					trainingEventsDao.delete(applicationTrainingEventslist.get(j).getTrainingEvents());
				}
			}
			
			//从应用表查理论教学
			ApplicationTrainingEvents liapplicationTrainingEvents = applicationTrainingEventsDao.gettotalli(newProfessionalList.get(i));
			if(liapplicationTrainingEvents != null)
			{
				trainingEventsDao.delete(liapplicationTrainingEvents.getTrainingEvents());
			}
			//从应用表查小计	
			ApplicationTrainingEvents totalapplicationTrainingEvents = applicationTrainingEventsDao.gettotal(newProfessionalList.get(i));
			if(totalapplicationTrainingEvents != null)
			{
				trainingEventsDao.delete(totalapplicationTrainingEvents.getTrainingEvents());
			}
			
		}
		
		
		PTBasicInformation ptBasicInformation = ptBasicInformationDao.getbydepartment(department);
		List<ApplicationMainTainOfPT> newapplicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionalList.get(0));
		if(newapplicationMainTainOfPTlist == null || newapplicationMainTainOfPTlist.size() == 0){
			if(ptBasicInformation.getLearningTime().equals("四年")){
				for(int i=0;i<8;i++){
					MainTainOfPT mainTainOfPT = new MainTainOfPT();
					mainTainOfPT.setDepartment(department);
					mainTainOfPT.setWeek1("");
					mainTainOfPT.setWeek2("");
					mainTainOfPT.setWeek3("");
					mainTainOfPT.setWeek4("");
					mainTainOfPT.setWeek5("");
					mainTainOfPT.setWeek6("");
					mainTainOfPT.setWeek7("");
					mainTainOfPT.setWeek8("");
					mainTainOfPT.setWeek9("");
					mainTainOfPT.setWeek10("");
					mainTainOfPT.setWeek11("");
					mainTainOfPT.setWeek12("");
					mainTainOfPT.setWeek13("");
					mainTainOfPT.setWeek14("");
					mainTainOfPT.setWeek15("");
					mainTainOfPT.setWeek16("");
					mainTainOfPT.setWeek17("");
					mainTainOfPT.setWeek18("");
					mainTainOfPT.setWeek19("");
					mainTainOfPT.setWeek20("");
					mainTainOfPT.setSemester(i+1);
					mainTainOfPTDao.add(mainTainOfPT);
					for(int j=0;j<newProfessionalList.size();j++)
					{
						ApplicationMainTainOfPT applicationMainTainOfPT = new ApplicationMainTainOfPT();
						applicationMainTainOfPT.setMainTainOfPT(mainTainOfPT);
						applicationMainTainOfPT.setDepartment(department);
						applicationMainTainOfPT.setProfessional(newProfessionalList.get(j));
						applicationMainTainOfPTDao.add(applicationMainTainOfPT);
					}
				}
			}
			if(ptBasicInformation.getLearningTime().equals("五年")){
				for(int i=0;i<10;i++){
					MainTainOfPT mainTainOfPT = new MainTainOfPT();
					mainTainOfPT.setDepartment(department);
					mainTainOfPT.setWeek1("");
					mainTainOfPT.setWeek2("");
					mainTainOfPT.setWeek3("");
					mainTainOfPT.setWeek4("");
					mainTainOfPT.setWeek5("");
					mainTainOfPT.setWeek6("");
					mainTainOfPT.setWeek7("");
					mainTainOfPT.setWeek8("");
					mainTainOfPT.setWeek9("");
					mainTainOfPT.setWeek10("");
					mainTainOfPT.setWeek11("");
					mainTainOfPT.setWeek12("");
					mainTainOfPT.setWeek13("");
					mainTainOfPT.setWeek14("");
					mainTainOfPT.setWeek15("");
					mainTainOfPT.setWeek16("");
					mainTainOfPT.setWeek17("");
					mainTainOfPT.setWeek18("");
					mainTainOfPT.setWeek19("");
					mainTainOfPT.setWeek20("");
					mainTainOfPT.setSemester(i+1);
					mainTainOfPTDao.add(mainTainOfPT);
					for(int j=0;j<newProfessionalList.size();j++)
					{
						ApplicationMainTainOfPT applicationMainTainOfPT = new ApplicationMainTainOfPT();
						applicationMainTainOfPT.setMainTainOfPT(mainTainOfPT);
						applicationMainTainOfPT.setDepartment(department);
						applicationMainTainOfPT.setProfessional(newProfessionalList.get(j));
						applicationMainTainOfPTDao.add(applicationMainTainOfPT);
					}
				}
			}
			//添加理论教学
			TrainingEvents ntrainingEvents = new TrainingEvents();
			ntrainingEvents.setDepartment(department);
			ntrainingEvents.setTrainingEventsname("理论教学");
			ntrainingEvents.setTrainingEventscode("A");
			ntrainingEvents.setTotaltag(2);
			trainingEventsDao.add(ntrainingEvents);
			for(int j=0;j<newProfessionalList.size();j++)
			{
				ApplicationTrainingEvents applicationTrainingEvents = new ApplicationTrainingEvents();
				applicationTrainingEvents.setTrainingEvents(ntrainingEvents);
				applicationTrainingEvents.setDepartment(department);
				applicationTrainingEvents.setProfessional(newProfessionalList.get(j));
				applicationTrainingEventsDao.add(applicationTrainingEvents);
			}
			//添加小计				
			TrainingEvents trainingEvents = new TrainingEvents();
			trainingEvents.setDepartment(department);
			trainingEvents.setTrainingEventsname("小计");
			trainingEvents.setTotaltag(1);
			trainingEventsDao.add(trainingEvents);
			for(int j=0;j<newProfessionalList.size();j++)
			{
				ApplicationTrainingEvents applicationTrainingEvents = new ApplicationTrainingEvents();
				applicationTrainingEvents.setTrainingEvents(trainingEvents);
				applicationTrainingEvents.setDepartment(department);
				applicationTrainingEvents.setProfessional(newProfessionalList.get(j));
				applicationTrainingEventsDao.add(applicationTrainingEvents);
			}
		}
			//从应用表查总体安排
			List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionalList.get(0));
			List<MainTainOfPT> newmainTainOfPTlist = new ArrayList<MainTainOfPT>();
			for(int i=0;i<applicationMainTainOfPTlist.size();i++)
			{
				newmainTainOfPTlist.add(applicationMainTainOfPTlist.get(i).getMainTainOfPT());
			}
			//从应用表查培养事件
			List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(newProfessionalList.get(0));
			List<TrainingEvents> newtrainingEventslist = new ArrayList<TrainingEvents>();
			if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0){
				for(int i=0;i<applicationTrainingEventslist.size();i++){
					newtrainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
				}
			}
			//从应用表查理论教学
			ApplicationTrainingEvents liapplicationTrainingEvents = applicationTrainingEventsDao.gettotalli(newProfessionalList.get(0));
			TrainingEvents liTrainingEvents = liapplicationTrainingEvents.getTrainingEvents();
			//从应用表查小计	
			ApplicationTrainingEvents totalapplicationTrainingEvents = applicationTrainingEventsDao.gettotal(newProfessionalList.get(0));
			TrainingEvents newTrainingEvents = totalapplicationTrainingEvents.getTrainingEvents();
			
			ActionContext context=ActionContext.getContext();			
			context.put("mainTainOfPTlist", newmainTainOfPTlist);//学期
			context.put("trainingEventslist", newtrainingEventslist);//可删除
			context.put("trainingEvents", newTrainingEvents);
			context.put("liTrainingEvents", liTrainingEvents);
			
	}
	
	public String bjapTag(String departmentid) {
		Department department = departmentDao.get(departmentid);
		List<Professional> professionallist = professionalDao.findbydepartment(department);
		if(professionallist == null){
			return "noProfessional";
		}
		else
		{
			List<MainTainOfPTTag> mainTainOfPTTaglist = mainTainOfPTTagDao.findbydepartment(department);
			if(mainTainOfPTTaglist == null)
			{
				ServletActionContext.getRequest().setAttribute("department", department);
				return "chancetag";
			}
			else
			{
				if(mainTainOfPTTaglist.get(0).getTag().equals("0"))
				{
					return "havaProfessionalOne";
				}
				else
				{
					return "havaProfessionalMore";
				}
			}
		}
	}
	
	public void chancetag(String departmentid, String tag) {	//选择填写总体安排表方式
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		MainTainOfPTTag mainTainOfPTTag = new MainTainOfPTTag();
		mainTainOfPTTag.setDepartment(department);
		mainTainOfPTTag.setTag(tag);
		mainTainOfPTTagDao.add(mainTainOfPTTag);
	}
	
	public void changetag(String departmentid) {	//更改填写总体安排表方式
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		List<MainTainOfPTTag> mainTainOfPTTaglist = mainTainOfPTTagDao.findbydepartment(department);
		if(mainTainOfPTTaglist.get(0).getTag().equals("0")){
			mainTainOfPTTaglist.get(0).setTag("1");
		}else{
			mainTainOfPTTaglist.get(0).setTag("0");
		}
		mainTainOfPTTagDao.update(mainTainOfPTTaglist.get(0));
		List<MainTainOfPT> mainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
		if(mainTainOfPTlist != null){
			for(int i=0;i<mainTainOfPTlist.size();i++){
				mainTainOfPTDao.delete(mainTainOfPTlist.get(i));
			}
		}
		List<TrainingEvents> trainingEventslist = trainingEventsDao.getbydepartment(department);
		if(trainingEventslist != null){
			for(int i=0;i<trainingEventslist.size();i++){
				trainingEventsDao.delete(trainingEventslist.get(i));
			}
		}
		
	}
	
	public void updatemainTainOfPT(List<String> week1, List<String> week2,
			List<String> week3, List<String> week4, List<String> week5,
			List<String> week6, List<String> week7, List<String> week8,
			List<String> week9, List<String> week10, List<String> week11,
			List<String> week12, List<String> week13, List<String> week14,
			List<String> week15, List<String> week16, List<String> week17,
			List<String> week18, List<String> week19, List<String> week20,
			List<String> semester1, List<String> semester2,
			List<String> semester3, List<String> semester4,
			List<String> semester5, List<String> semester6,
			List<String> semester7, List<String> semester8,
			List<String> semester9, List<String> semester10, String departmentid) {
		Department department = departmentDao.get(departmentid);
		List<MainTainOfPT> mainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
		List<String> countA = new ArrayList<String>();
		for(int i=0; i<week1.size();i++){
			MainTainOfPT mainTainOfPT = mainTainOfPTlist.get(i);
			Integer count = 0;
			if(week1.get(i).equals("A")){
				count++;
			}
			if(week2.get(i).equals("A")){
				count++;
			}
			if(week3.get(i).equals("A")){
				count++;
			}
			if(week4.get(i).equals("A")){
				count++;
			}
			if(week5.get(i).equals("A")){
				count++;
			}
			if(week6.get(i).equals("A")){
				count++;
			}
			if(week7.get(i).equals("A")){
				count++;
			}
			if(week8.get(i).equals("A")){
				count++;
			}
			if(week9.get(i).equals("A")){
				count++;
			}
			if(week10.get(i).equals("A")){
				count++;
			}
			if(week11.get(i).equals("A")){
				count++;
			}
			if(week12.get(i).equals("A")){
				count++;
			}
			if(week13.get(i).equals("A")){
				count++;
			}
			if(week14.get(i).equals("A")){
				count++;
			}
			if(week15.get(i).equals("A")){
				count++;
			}
			if(week16.get(i).equals("A")){
				count++;
			}
			if(week17.get(i).equals("A")){
				count++;
			}
			if(week18.get(i).equals("A")){
				count++;
			}
			if(week19.get(i).equals("A")){
				count++;
			}
			if(week20.get(i).equals("A")){
				count++;
			}
			String counta = Integer.toString(count);
			countA.add(counta);
			mainTainOfPT.setWeek1(week1.get(i));
			mainTainOfPT.setWeek2(week2.get(i));
			mainTainOfPT.setWeek3(week3.get(i));
			mainTainOfPT.setWeek4(week4.get(i));
			mainTainOfPT.setWeek5(week5.get(i));
			mainTainOfPT.setWeek6(week6.get(i));
			mainTainOfPT.setWeek7(week7.get(i));
			mainTainOfPT.setWeek8(week8.get(i));
			mainTainOfPT.setWeek9(week9.get(i));
			mainTainOfPT.setWeek10(week10.get(i));
			mainTainOfPT.setWeek11(week11.get(i));
			mainTainOfPT.setWeek12(week12.get(i));
			mainTainOfPT.setWeek13(week13.get(i));
			mainTainOfPT.setWeek14(week14.get(i));
			mainTainOfPT.setWeek15(week15.get(i));
			mainTainOfPT.setWeek16(week16.get(i));
			mainTainOfPT.setWeek17(week17.get(i));
			mainTainOfPT.setWeek18(week18.get(i));
			mainTainOfPT.setWeek19(week19.get(i));
			mainTainOfPT.setWeek20(week20.get(i));
			mainTainOfPTDao.update(mainTainOfPT);
		}
		List<TrainingEvents> trainingEventslist = trainingEventsDao.getbydepartmentnototal(department);
		
		for(int i=0;i<semester1.size();i++){
			if(i==0){
				TrainingEvents trainingEvents = trainingEventsDao.gettotalli(department);
				trainingEvents.setSemester1(countA.get(0));
				trainingEvents.setSemester2(countA.get(1));
				trainingEvents.setSemester3(countA.get(2));
				trainingEvents.setSemester4(countA.get(3));
				trainingEvents.setSemester5(countA.get(4));
				trainingEvents.setSemester6(countA.get(5));
				trainingEvents.setSemester7(countA.get(6));
				trainingEvents.setSemester8(countA.get(7));
				if(semester9!=null){
					trainingEvents.setSemester9(countA.get(8));
					trainingEvents.setSemester10(countA.get(9));
				}
				trainingEventsDao.update(trainingEvents);
			}else{
				if(i == semester1.size()-1){
					TrainingEvents trainingEvents = trainingEventsDao.gettotal(department);
					trainingEvents.setSemester1(semester1.get(i));
					trainingEvents.setSemester2(semester2.get(i));
					trainingEvents.setSemester3(semester3.get(i));
					trainingEvents.setSemester4(semester4.get(i));
					trainingEvents.setSemester5(semester5.get(i));
					trainingEvents.setSemester6(semester6.get(i));
					trainingEvents.setSemester7(semester7.get(i));
					trainingEvents.setSemester8(semester8.get(i));
					if(semester9!=null){
						trainingEvents.setSemester9(semester9.get(i));
						trainingEvents.setSemester10(semester10.get(i));
					}
					trainingEventsDao.update(trainingEvents);
				}else{
					TrainingEvents trainingEvents = trainingEventslist.get(i-1);
					trainingEvents.setSemester1(semester1.get(i));
					trainingEvents.setSemester2(semester2.get(i));
					trainingEvents.setSemester3(semester3.get(i));
					trainingEvents.setSemester4(semester4.get(i));
					trainingEvents.setSemester5(semester5.get(i));
					trainingEvents.setSemester6(semester6.get(i));
					trainingEvents.setSemester7(semester7.get(i));
					trainingEvents.setSemester8(semester8.get(i));
					if(semester9!=null){
						trainingEvents.setSemester9(semester9.get(i));
						trainingEvents.setSemester10(semester10.get(i));
					}
					trainingEventsDao.update(trainingEvents);
				}
			}
		}
		ActionContext context=ActionContext.getContext();			
		context.put("msg", "保存成功！");
	}
	
	
	public String checkbjap(String departmentid) {
		Department department = departmentDao.get(departmentid);
		PTBasicInformation ptBasicInformation = ptBasicInformationDao.getbydepartment(department);
		List<MainTainOfPT> mainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
		if(ptBasicInformation == null){
			ActionContext context=ActionContext.getContext();			
			context.put("msg", department.getDepartmentCname()+"还未填写该信息！");
			return "noxx";
		}else{
			if(mainTainOfPTlist == null || mainTainOfPTlist.size() == 0){
				ActionContext context=ActionContext.getContext();
				context.put("msg", department.getDepartmentCname()+"还未填写该信息！");
				return "noxx";
			}
			
			List<Professional> professionalList = professionalDao.findbydepartment(department);
			if(professionalList == null || professionalList.size() ==0)
			{
				List<MainTainOfPT> newmainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
				List<TrainingEvents> newtrainingEventslist = trainingEventsDao.getbydepartmentnototal(department);
				TrainingEvents newTrainingEvents = trainingEventsDao.gettotal(department);
				TrainingEvents liTrainingEvents = trainingEventsDao.gettotalli(department);
				ActionContext context=ActionContext.getContext();			
				context.put("mainTainOfPTlist", newmainTainOfPTlist);//学期
				context.put("trainingEventslist", newtrainingEventslist);//可删除
				context.put("trainingEvents", newTrainingEvents);
				context.put("liTrainingEvents", liTrainingEvents);
				return "0";
			}
			else
			{
				List<MainTainOfPTTag> mainTainOfPTTaglist = mainTainOfPTTagDao.findbydepartment(department);
				if(mainTainOfPTTaglist == null || mainTainOfPTTaglist.size() ==0)
				{
					ActionContext context=ActionContext.getContext();
					context.put("msg", department.getDepartmentCname()+"还未选择填写总体安排表的方式！");
					return "noxx";
				}
				if(mainTainOfPTTaglist.get(0).getTag().equals("0"))
				{
					List<MainTainOfPT> newmainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
					List<TrainingEvents> newtrainingEventslist = trainingEventsDao.getbydepartmentnototal(department);
					TrainingEvents newTrainingEvents = trainingEventsDao.gettotal(department);
					TrainingEvents liTrainingEvents = trainingEventsDao.gettotalli(department);
					ActionContext context=ActionContext.getContext();			
					context.put("mainTainOfPTlist", newmainTainOfPTlist);//学期
					context.put("trainingEventslist", newtrainingEventslist);//可删除
					context.put("trainingEvents", newTrainingEvents);
					context.put("liTrainingEvents", liTrainingEvents);
					return "0";
				}
				else
				{
					bjapMore(departmentid,null);
					return "1";
				}
			}

		}
	}
	public void updatemainTainOfPTNew(List<String> week1, List<String> week2,
			List<String> week3, List<String> week4, List<String> week5,
			List<String> week6, List<String> week7, List<String> week8,
			List<String> week9, List<String> week10, List<String> week11,
			List<String> week12, List<String> week13, List<String> week14,
			List<String> week15, List<String> week16, List<String> week17,
			List<String> week18, List<String> week19, List<String> week20,
			List<String> semester1, List<String> semester2,
			List<String> semester3, List<String> semester4,
			List<String> semester5, List<String> semester6,
			List<String> semester7, List<String> semester8,
			List<String> semester9, List<String> semester10,
			String departmentid, List<String> professionalList) {
		// TODO Auto-generated method stub
		Department department = departmentDao.get(departmentid);
		List<Professional> newProfessionalList = new ArrayList<Professional>();
		for(int j=0;j<professionalList.size();j++)
		{
			Professional professional = professionalDao.get(professionalList.get(j));
			newProfessionalList.add(professional);
		}
		List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionalList.get(0));
		List<MainTainOfPT> mainTainOfPTlist = new ArrayList<MainTainOfPT>();
		for(int i=0;i<applicationMainTainOfPTlist.size();i++)
		{
			mainTainOfPTlist.add(applicationMainTainOfPTlist.get(i).getMainTainOfPT());
		}		
//		List<MainTainOfPT> mainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);
		
		List<String> countA = new ArrayList<String>();
		for(int i=0; i<week1.size();i++){
			MainTainOfPT mainTainOfPT = mainTainOfPTlist.get(i);
			Integer count = 0;
			if(week1.get(i).equals("A")){
				count++;
			}
			if(week2.get(i).equals("A")){
				count++;
			}
			if(week3.get(i).equals("A")){
				count++;
			}
			if(week4.get(i).equals("A")){
				count++;
			}
			if(week5.get(i).equals("A")){
				count++;
			}
			if(week6.get(i).equals("A")){
				count++;
			}
			if(week7.get(i).equals("A")){
				count++;
			}
			if(week8.get(i).equals("A")){
				count++;
			}
			if(week9.get(i).equals("A")){
				count++;
			}
			if(week10.get(i).equals("A")){
				count++;
			}
			if(week11.get(i).equals("A")){
				count++;
			}
			if(week12.get(i).equals("A")){
				count++;
			}
			if(week13.get(i).equals("A")){
				count++;
			}
			if(week14.get(i).equals("A")){
				count++;
			}
			if(week15.get(i).equals("A")){
				count++;
			}
			if(week16.get(i).equals("A")){
				count++;
			}
			if(week17.get(i).equals("A")){
				count++;
			}
			if(week18.get(i).equals("A")){
				count++;
			}
			if(week19.get(i).equals("A")){
				count++;
			}
			if(week20.get(i).equals("A")){
				count++;
			}
			String counta = Integer.toString(count);
			countA.add(counta);
			mainTainOfPT.setWeek1(week1.get(i));
			mainTainOfPT.setWeek2(week2.get(i));
			mainTainOfPT.setWeek3(week3.get(i));
			mainTainOfPT.setWeek4(week4.get(i));
			mainTainOfPT.setWeek5(week5.get(i));
			mainTainOfPT.setWeek6(week6.get(i));
			mainTainOfPT.setWeek7(week7.get(i));
			mainTainOfPT.setWeek8(week8.get(i));
			mainTainOfPT.setWeek9(week9.get(i));
			mainTainOfPT.setWeek10(week10.get(i));
			mainTainOfPT.setWeek11(week11.get(i));
			mainTainOfPT.setWeek12(week12.get(i));
			mainTainOfPT.setWeek13(week13.get(i));
			mainTainOfPT.setWeek14(week14.get(i));
			mainTainOfPT.setWeek15(week15.get(i));
			mainTainOfPT.setWeek16(week16.get(i));
			mainTainOfPT.setWeek17(week17.get(i));
			mainTainOfPT.setWeek18(week18.get(i));
			mainTainOfPT.setWeek19(week19.get(i));
			mainTainOfPT.setWeek20(week20.get(i));
			mainTainOfPTDao.update(mainTainOfPT);
		}
		
		List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(newProfessionalList.get(0));
		List<TrainingEvents> trainingEventslist = new ArrayList<TrainingEvents>();
		if(applicationTrainingEventslist != null ){
			for(int i=0;i<applicationTrainingEventslist.size();i++)
				trainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
		}
//		List<TrainingEvents> trainingEventslist = trainingEventsDao.getbydepartmentnototal(department);
		
		for(int i=0;i<semester1.size();i++){
			if(i==0){
				//从应用表查理论教学
				ApplicationTrainingEvents liapplicationTrainingEvents = applicationTrainingEventsDao.gettotalli(newProfessionalList.get(0));
				TrainingEvents trainingEvents = liapplicationTrainingEvents.getTrainingEvents();
	//			TrainingEvents trainingEvents = trainingEventsDao.gettotalli(department);
				trainingEvents.setSemester1(countA.get(0));
				trainingEvents.setSemester2(countA.get(1));
				trainingEvents.setSemester3(countA.get(2));
				trainingEvents.setSemester4(countA.get(3));
				trainingEvents.setSemester5(countA.get(4));
				trainingEvents.setSemester6(countA.get(5));
				trainingEvents.setSemester7(countA.get(6));
				trainingEvents.setSemester8(countA.get(7));
				if(semester9!=null){
					trainingEvents.setSemester9(countA.get(8));
					trainingEvents.setSemester10(countA.get(9));
				}
				trainingEventsDao.update(trainingEvents);
			}else{
				if(i == semester1.size()-1){
					//从应用表查小计	
					ApplicationTrainingEvents totalapplicationTrainingEvents = applicationTrainingEventsDao.gettotal(newProfessionalList.get(0));
					TrainingEvents trainingEvents = totalapplicationTrainingEvents.getTrainingEvents();
	//				TrainingEvents trainingEvents = trainingEventsDao.gettotal(department);
					trainingEvents.setSemester1(semester1.get(i));
					trainingEvents.setSemester2(semester2.get(i));
					trainingEvents.setSemester3(semester3.get(i));
					trainingEvents.setSemester4(semester4.get(i));
					trainingEvents.setSemester5(semester5.get(i));
					trainingEvents.setSemester6(semester6.get(i));
					trainingEvents.setSemester7(semester7.get(i));
					trainingEvents.setSemester8(semester8.get(i));
					if(semester9!=null){
						trainingEvents.setSemester9(semester9.get(i));
						trainingEvents.setSemester10(semester10.get(i));
					}
					trainingEventsDao.update(trainingEvents);
				}else{
					TrainingEvents trainingEvents = trainingEventslist.get(i-1);
					trainingEvents.setSemester1(semester1.get(i));
					trainingEvents.setSemester2(semester2.get(i));
					trainingEvents.setSemester3(semester3.get(i));
					trainingEvents.setSemester4(semester4.get(i));
					trainingEvents.setSemester5(semester5.get(i));
					trainingEvents.setSemester6(semester6.get(i));
					trainingEvents.setSemester7(semester7.get(i));
					trainingEvents.setSemester8(semester8.get(i));
					if(semester9!=null){
						trainingEvents.setSemester9(semester9.get(i));
						trainingEvents.setSemester10(semester10.get(i));
					}
					trainingEventsDao.update(trainingEvents);
				}
			}
		}
		ActionContext context=ActionContext.getContext();			
		context.put("msg", "保存成功！");
	}
	
	public void bjapMore(String departmentid, List<String> professionalList2) {
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Professional> professionalList = professionalDao.findbydepartment(department);
		List<Professional> noProfessionalList = new ArrayList<Professional>();//没有录入总体安排的专业方向
		List<List<Professional>> haveProfessionalList = new ArrayList<List<Professional>>();//录入了总体安排的专业方向
		List<ApplicationMainTainOfPT> newApplicationMainTainOfPT = new ArrayList<ApplicationMainTainOfPT>();//存储录入了的方向的内容，用于判断是否相同
		for(int i=0;i<professionalList.size();i++){
			List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(professionalList.get(i));
			if(applicationMainTainOfPTlist == null)
			{
				noProfessionalList.add(professionalList.get(i));
			}
			else
			{
				newApplicationMainTainOfPT.add(applicationMainTainOfPTlist.get(0));
			}
		}
		//将相同总体安排表的专业方向放一起
		while(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() != 0){
			List<Professional> professionallist = new ArrayList<Professional>();
			professionallist.add(newApplicationMainTainOfPT.get(0).getProfessional());
			
			if(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() > 1){
				for(int i=1;i<newApplicationMainTainOfPT.size();i++){
					if(newApplicationMainTainOfPT.get(0).getMainTainOfPT() == newApplicationMainTainOfPT.get(i).getMainTainOfPT())
					{
						professionallist.add(newApplicationMainTainOfPT.get(i).getProfessional());
						newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(i));
						i--;
					}	
				}
			}
			newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(0));
			haveProfessionalList.add(professionallist);
		}
		ServletActionContext.getRequest().setAttribute("noProfessionalList", noProfessionalList);
		ServletActionContext.getRequest().setAttribute("haveProfessionalList", haveProfessionalList);
		
		
		if(professionalList2 != null && professionalList2.size() != 0){
			//先判断应用的是4年还是5年，与数据库中存储的条数进行比较，避免更改应用年份造成数据条数错误
			PTBasicInformation ptBasicInformation = ptBasicInformationDao.getbydepartment(department);
			List<Professional> newProfessionalList = new ArrayList<Professional>();
			for(int i=0;i<professionalList2.size();i++)
			{
				Professional professional = professionalDao.get(professionalList2.get(i));
				newProfessionalList.add(professional);
			}
			//从应用表查总体安排
			List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionalList.get(0));
			if(applicationMainTainOfPTlist != null && applicationMainTainOfPTlist.size() !=0)
			{
				//若不匹配，则对数据库中的条数进行更改
				if(ptBasicInformation.getLearningTime().equals("四年")){
					if(applicationMainTainOfPTlist.size()==10){
						mainTainOfPTDao.delete(applicationMainTainOfPTlist.get(8).getMainTainOfPT());
						mainTainOfPTDao.delete(applicationMainTainOfPTlist.get(9).getMainTainOfPT());
						applicationMainTainOfPTlist.remove(applicationMainTainOfPTlist.get(9));
						applicationMainTainOfPTlist.remove(applicationMainTainOfPTlist.get(8));
					}
				}
				if(ptBasicInformation.getLearningTime().equals("五年")){
					if(applicationMainTainOfPTlist.size()==8){
						for(int i=8;i<10;i++){
							MainTainOfPT mainTainOfPT = new MainTainOfPT();
							mainTainOfPT.setDepartment(department);
							mainTainOfPT.setWeek1("");
							mainTainOfPT.setWeek2("");
							mainTainOfPT.setWeek3("");
							mainTainOfPT.setWeek4("");
							mainTainOfPT.setWeek5("");
							mainTainOfPT.setWeek6("");
							mainTainOfPT.setWeek7("");
							mainTainOfPT.setWeek8("");
							mainTainOfPT.setWeek9("");
							mainTainOfPT.setWeek10("");
							mainTainOfPT.setWeek11("");
							mainTainOfPT.setWeek12("");
							mainTainOfPT.setWeek13("");
							mainTainOfPT.setWeek14("");
							mainTainOfPT.setWeek15("");
							mainTainOfPT.setWeek16("");
							mainTainOfPT.setWeek17("");
							mainTainOfPT.setWeek18("");
							mainTainOfPT.setWeek19("");
							mainTainOfPT.setWeek20("");
							mainTainOfPT.setSemester(i+1);
							mainTainOfPTDao.add(mainTainOfPT);
							
							for(int j=0;j<newProfessionalList.size();j++)
							{
								ApplicationMainTainOfPT applicationMainTainOfPT = new ApplicationMainTainOfPT();
								applicationMainTainOfPT.setMainTainOfPT(mainTainOfPT);
								applicationMainTainOfPT.setDepartment(department);
								applicationMainTainOfPT.setProfessional(newProfessionalList.get(j));
								applicationMainTainOfPTDao.add(applicationMainTainOfPT);
							}
							for(int j=0;j<2;j++)
							{
								ApplicationMainTainOfPT applicationMainTainOfPT = new ApplicationMainTainOfPT();
								applicationMainTainOfPT.setMainTainOfPT(mainTainOfPT);
								applicationMainTainOfPT.setDepartment(department);
								applicationMainTainOfPT.setProfessional(newProfessionalList.get(0));
								applicationMainTainOfPTlist.add(applicationMainTainOfPT);
							}
						}
					}
				}
				//查询数据，用于在More页面进行查看
				List<MainTainOfPT> newmainTainOfPTlist = new ArrayList<MainTainOfPT>();
				for(int i=0;i<applicationMainTainOfPTlist.size();i++)
				{
					newmainTainOfPTlist.add(applicationMainTainOfPTlist.get(i).getMainTainOfPT());
				}
				//从应用表查培养事件
				List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(newProfessionalList.get(0));
				List<TrainingEvents> newtrainingEventslist = new ArrayList<TrainingEvents>();
				if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0){
					for(int i=0;i<applicationTrainingEventslist.size();i++){
						newtrainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
					}
				}
				//从应用表查理论教学
				ApplicationTrainingEvents liapplicationTrainingEvents = applicationTrainingEventsDao.gettotalli(newProfessionalList.get(0));
				TrainingEvents liTrainingEvents = liapplicationTrainingEvents.getTrainingEvents();
				//从应用表查小计	
				ApplicationTrainingEvents totalapplicationTrainingEvents = applicationTrainingEventsDao.gettotal(newProfessionalList.get(0));
				TrainingEvents newTrainingEvents = totalapplicationTrainingEvents.getTrainingEvents();
				
				ActionContext context=ActionContext.getContext();			
				context.put("mainTainOfPTlist", newmainTainOfPTlist);//学期
				context.put("trainingEventslist", newtrainingEventslist);//可删除
				context.put("trainingEvents", newTrainingEvents);
				context.put("liTrainingEvents", liTrainingEvents);
				context.put("search", "yes");
				context.put("professionalList", newProfessionalList);
			}
		}
	}
	public void bjapdeletePro(String departmentid, List<String> professionalList) {
		Department department = departmentDao.get(departmentid);
		List<Professional> newProfessionalList = new ArrayList<Professional>();
		for(int j=0;j<professionalList.size();j++)
		{
			Professional professional = professionalDao.get(professionalList.get(j));
			newProfessionalList.add(professional);
		}
		
		//总体安排应用表中查总体安排表
		List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionalList.get(0));
		if(applicationMainTainOfPTlist != null){
			for(int i=0;i<applicationMainTainOfPTlist.size();i++)
			{
				mainTainOfPTDao.delete(applicationMainTainOfPTlist.get(i).getMainTainOfPT());
			}
		}
		
		//培养事件应用表中查添加的培养事件
		List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(newProfessionalList.get(0));
		if(applicationTrainingEventslist != null ){
			for(int i=0;i<applicationTrainingEventslist.size();i++)
				trainingEventsDao.delete(applicationTrainingEventslist.get(i).getTrainingEvents());
		}
		
		//从应用表查理论教学
		ApplicationTrainingEvents liapplicationTrainingEvents = applicationTrainingEventsDao.gettotalli(newProfessionalList.get(0));
		if(liapplicationTrainingEvents != null){
			trainingEventsDao.delete(liapplicationTrainingEvents.getTrainingEvents());
		}
		//从应用表查小计	
		ApplicationTrainingEvents totalapplicationTrainingEvents = applicationTrainingEventsDao.gettotal(newProfessionalList.get(0));
		if(totalapplicationTrainingEvents != null){
			trainingEventsDao.delete(totalapplicationTrainingEvents.getTrainingEvents());
		}
		
	}
	public void bjaptochangeProPage(String departmentid, List<String> professionalList) {
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Professional> newProfessionalList = new ArrayList<Professional>();
		for(int j=0;j<professionalList.size();j++)	//已经填写的专业方向
		{
			Professional professional = professionalDao.get(professionalList.get(j));
			newProfessionalList.add(professional);
		}
		List<Professional> professionallist = professionalDao.findbydepartment(department);	//全部专业方向
		ServletActionContext.getRequest().setAttribute("haveProfessionalList", newProfessionalList);
		ServletActionContext.getRequest().setAttribute("allProfessionallist", professionallist);
	}
	
	//修改专业方向
	public void bjapChangePro(String departmentid,List<String> professionalList, List<String> changePro) {
		List<Professional> innerProfessionalList = new ArrayList<Professional>();//未更改前数据库中存储的
		List<Professional> mixProfessionalList = new ArrayList<Professional>();//用于存储两个List的交集
		List<Professional> changeProfessionalList = new ArrayList<Professional>();//要更改为的专业方向
		for(int i=0;i<professionalList.size();i++)	//已经填写的专业方向
		{
			Professional professional = professionalDao.get(professionalList.get(i));
			innerProfessionalList.add(professional);
			mixProfessionalList.add(professional);
		}
		for(int i=0;i<changePro.size();i++)
		{
			Professional professional = professionalDao.get(changePro.get(i));
			changeProfessionalList.add(professional);
		}
		//更改前和更改后的两个List求交集，找到存储在数据库中不需要改变专业方向的,存储在mixProfessionalList中
		mixProfessionalList.retainAll(changeProfessionalList);
		//将要更改为的专业方向与交集的专业方向取差集，找到需要添加到数据库中的专业方向，存储在changeProfessionalList中
		changeProfessionalList.removeAll(mixProfessionalList);
		if(changeProfessionalList != null && changeProfessionalList.size() != 0)
		{
			//更改前的专业方向
			//从应用表中查询总体安排，复制
			List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(innerProfessionalList.get(0));
			//从应用表中查添加的培养事件,复制
			List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(innerProfessionalList.get(0));
			//从应用表查理论教学，复制
			ApplicationTrainingEvents liapplicationTrainingEvents = applicationTrainingEventsDao.gettotalli(innerProfessionalList.get(0));
			//从应用表查小计	，复制
			ApplicationTrainingEvents totalapplicationTrainingEvents = applicationTrainingEventsDao.gettotal(innerProfessionalList.get(0));
			for(int i=0;i<changeProfessionalList.size();i++)
			{
				//要更改为的专业方向
				//从应用表中查询总体安排，复制
				List<ApplicationMainTainOfPT> applicationMainTainOfPTlist_changePro = applicationMainTainOfPTDao.findbyProfessional(changeProfessionalList.get(i));
				//从应用表中查添加的培养事件,复制
				List<ApplicationTrainingEvents> applicationTrainingEventslist_changePro = applicationTrainingEventsDao.getbyProfessionalnototal(changeProfessionalList.get(i));
				//从应用表查理论教学，复制
				ApplicationTrainingEvents liapplicationTrainingEvents_changePro = applicationTrainingEventsDao.gettotalli(changeProfessionalList.get(i));
				//从应用表查小计	，复制
				ApplicationTrainingEvents totalapplicationTrainingEvents_changePro = applicationTrainingEventsDao.gettotal(changeProfessionalList.get(i));
				
				//被改动的总体安排表
				if(applicationMainTainOfPTlist_changePro != null && applicationMainTainOfPTlist_changePro.size() !=0)
				{
					if(applicationMainTainOfPTlist != null && applicationMainTainOfPTlist.size() != 0)
					{
						//若原存储的基本信息年份和要更改的年份一直，则对每项内容
						if(applicationMainTainOfPTlist.size() == applicationMainTainOfPTlist_changePro.size())
						{
							for(int j=0;j<applicationMainTainOfPTlist.size();j++)
							{
								applicationMainTainOfPTlist_changePro.get(j).setMainTainOfPT(applicationMainTainOfPTlist.get(j).getMainTainOfPT());
								applicationMainTainOfPTDao.update(applicationMainTainOfPTlist_changePro.get(j));
							}
						}
						//若不一致，多的则删，少的则创建对象加上
						else
						{
							//多的删
							if(applicationMainTainOfPTlist.size() == 8 && applicationMainTainOfPTlist_changePro.size() == 10)
							{
								for(int j=0;j<8;j++)
								{
									applicationMainTainOfPTlist_changePro.get(j).setMainTainOfPT(applicationMainTainOfPTlist.get(j).getMainTainOfPT());
									applicationMainTainOfPTDao.update(applicationMainTainOfPTlist_changePro.get(j));
								}
								applicationMainTainOfPTDao.delete(applicationMainTainOfPTlist_changePro.get(8));
								applicationMainTainOfPTDao.delete(applicationMainTainOfPTlist_changePro.get(9));
							}
							//少的加
							if(applicationMainTainOfPTlist.size() == 10 && applicationMainTainOfPTlist_changePro.size() == 8)
							{
								for(int j=0;j<8;j++)
								{
									applicationMainTainOfPTlist_changePro.get(j).setMainTainOfPT(applicationMainTainOfPTlist.get(j).getMainTainOfPT());
									applicationMainTainOfPTDao.update(applicationMainTainOfPTlist_changePro.get(j));
								}
								for(int j=8;j<10;j++)
								{
									ApplicationMainTainOfPT newApplicationMainTainOfPT = new ApplicationMainTainOfPT();
									newApplicationMainTainOfPT.setMainTainOfPT(applicationMainTainOfPTlist.get(j).getMainTainOfPT());
									newApplicationMainTainOfPT.setDepartment(applicationMainTainOfPTlist.get(j).getDepartment());
									newApplicationMainTainOfPT.setProfessional(changeProfessionalList.get(i));
									applicationMainTainOfPTDao.add(newApplicationMainTainOfPT);
								}
							}
								
						}
					}
					
					//更改新加的培养事件，数量一致则对每个更新，若少则update后再add少的，多则update后再delete多的
					if(applicationTrainingEventslist_changePro != null && applicationTrainingEventslist_changePro.size() !=0)
					{
						if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() != 0)
						{
							//数量一致
							if(applicationTrainingEventslist.size() == applicationTrainingEventslist_changePro.size())
							{
								for(int j=0;j<applicationTrainingEventslist.size();j++)
								{
									applicationTrainingEventslist_changePro.get(j).setTrainingEvents(applicationTrainingEventslist.get(j).getTrainingEvents());
									applicationTrainingEventsDao.update(applicationTrainingEventslist_changePro.get(j));
								}
							}
							//少的，先update，在add
							if(applicationTrainingEventslist.size() > applicationTrainingEventslist_changePro.size())
							{
								for(int j=0;j<applicationTrainingEventslist_changePro.size();j++)
								{
									applicationTrainingEventslist_changePro.get(j).setTrainingEvents(applicationTrainingEventslist.get(j).getTrainingEvents());
									applicationTrainingEventsDao.update(applicationTrainingEventslist_changePro.get(j));
								}
								for(int j=applicationTrainingEventslist_changePro.size();j<applicationTrainingEventslist.size();j++)
								{
									ApplicationTrainingEvents newApplicationTrainingEvents = new ApplicationTrainingEvents();
									newApplicationTrainingEvents.setTrainingEvents(applicationTrainingEventslist.get(j).getTrainingEvents());
									newApplicationTrainingEvents.setDepartment(applicationTrainingEventslist.get(j).getDepartment());
									newApplicationTrainingEvents.setProfessional(changeProfessionalList.get(i));
									applicationTrainingEventsDao.add(newApplicationTrainingEvents);
								}
							}
							//多的，先update后再delete
							if(applicationTrainingEventslist.size() < applicationTrainingEventslist_changePro.size())
							{
								for(int j=0;j<applicationTrainingEventslist.size();j++)
								{
									applicationTrainingEventslist_changePro.get(j).setTrainingEvents(applicationTrainingEventslist.get(j).getTrainingEvents());
									applicationTrainingEventsDao.update(applicationTrainingEventslist_changePro.get(j));
								}
								for(int j=applicationTrainingEventslist.size();j<applicationTrainingEventslist_changePro.size();j++)
								{
									applicationTrainingEventsDao.delete(applicationTrainingEventslist_changePro.get(j));
								}
							}
						}
						else
						{
							for(int j=0;j<applicationTrainingEventslist_changePro.size();j++)
							{
								applicationTrainingEventsDao.delete(applicationTrainingEventslist_changePro.get(j));
							}
						}
					}
					//若原先的为空，则直接创建
					else
					{
						if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() != 0)
						{
							for(int j=0;j<applicationTrainingEventslist.size();j++)
							{
								ApplicationTrainingEvents newApplicationTrainingEvents = new ApplicationTrainingEvents();
								newApplicationTrainingEvents.setTrainingEvents(applicationTrainingEventslist.get(j).getTrainingEvents());
								newApplicationTrainingEvents.setDepartment(applicationTrainingEventslist.get(j).getDepartment());
								newApplicationTrainingEvents.setProfessional(changeProfessionalList.get(i));
								applicationTrainingEventsDao.add(newApplicationTrainingEvents);
							}
						}
					}
					
					
					if(liapplicationTrainingEvents != null)
					{
						liapplicationTrainingEvents_changePro.setTrainingEvents(liapplicationTrainingEvents.getTrainingEvents());
						applicationTrainingEventsDao.update(liapplicationTrainingEvents_changePro);
					}
					if(totalapplicationTrainingEvents != null)
					{
						totalapplicationTrainingEvents_changePro.setTrainingEvents(totalapplicationTrainingEvents.getTrainingEvents());
						applicationTrainingEventsDao.update(totalapplicationTrainingEvents_changePro);
					}
				}
				//被改动的专业方向在总体安排表中没有信息，则直接创建新的
				else
				{
					if(applicationMainTainOfPTlist != null && applicationMainTainOfPTlist.size() != 0)
					{
						for(int j=0;j<applicationMainTainOfPTlist.size();j++)
						{
							ApplicationMainTainOfPT newApplicationMainTainOfPT = new ApplicationMainTainOfPT();
							newApplicationMainTainOfPT.setMainTainOfPT(applicationMainTainOfPTlist.get(j).getMainTainOfPT());
							newApplicationMainTainOfPT.setDepartment(applicationMainTainOfPTlist.get(j).getDepartment());
							newApplicationMainTainOfPT.setProfessional(changeProfessionalList.get(i));
							applicationMainTainOfPTDao.add(newApplicationMainTainOfPT);
						}
					}
					if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() != 0)
					{
						for(int j=0;j<applicationTrainingEventslist.size();j++)
						{
							ApplicationTrainingEvents newApplicationTrainingEvents = new ApplicationTrainingEvents();
							newApplicationTrainingEvents.setTrainingEvents(applicationTrainingEventslist.get(j).getTrainingEvents());
							newApplicationTrainingEvents.setDepartment(applicationTrainingEventslist.get(j).getDepartment());
							newApplicationTrainingEvents.setProfessional(changeProfessionalList.get(i));
							applicationTrainingEventsDao.add(newApplicationTrainingEvents);
						}
					}
					if(liapplicationTrainingEvents != null)
					{
						ApplicationTrainingEvents newApplicationTrainingEventsli = new ApplicationTrainingEvents();
						newApplicationTrainingEventsli.setTrainingEvents(liapplicationTrainingEvents.getTrainingEvents());
						newApplicationTrainingEventsli.setDepartment(liapplicationTrainingEvents.getDepartment());
						newApplicationTrainingEventsli.setProfessional(changeProfessionalList.get(i));
						applicationTrainingEventsDao.add(newApplicationTrainingEventsli);
					}
					if(totalapplicationTrainingEvents != null)
					{
						ApplicationTrainingEvents newApplicationTrainingEventstotal = new ApplicationTrainingEvents();
						newApplicationTrainingEventstotal.setTrainingEvents(totalapplicationTrainingEvents.getTrainingEvents());
						newApplicationTrainingEventstotal.setDepartment(totalapplicationTrainingEvents.getDepartment());
						newApplicationTrainingEventstotal.setProfessional(changeProfessionalList.get(i));
						applicationTrainingEventsDao.add(newApplicationTrainingEventstotal);
					}
				}
			}
		}
		
		//将原始专业方向与交集的专业方向取差集，找到需要从数据库中删除的专业方向，存储在innerProfessionalList中
		innerProfessionalList.removeAll(mixProfessionalList);
		if(innerProfessionalList != null && innerProfessionalList.size() != 0)
		{
			for(int i=0;i<innerProfessionalList.size();i++)
			{
				//从应用表中查询总体安排，删除
				List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(innerProfessionalList.get(i));
				if(applicationMainTainOfPTlist != null && applicationMainTainOfPTlist.size() != 0)
				{
					for(int j=0;j<applicationMainTainOfPTlist.size();j++)
					{
						applicationMainTainOfPTDao.delete(applicationMainTainOfPTlist.get(j));
					}
				}
				//从应用表中查添加的培养事件,删除
				List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(innerProfessionalList.get(i));
				if(applicationTrainingEventslist != null && applicationTrainingEventslist.size() !=0)
				{
					for(int j=0;j<applicationTrainingEventslist.size();j++)
					{
						applicationTrainingEventsDao.delete(applicationTrainingEventslist.get(j));
					}
				}
				//从应用表查理论教学，删除
				ApplicationTrainingEvents liapplicationTrainingEvents = applicationTrainingEventsDao.gettotalli(innerProfessionalList.get(i));
				if(liapplicationTrainingEvents != null)
				{
					applicationTrainingEventsDao.delete(liapplicationTrainingEvents);
				}
				//从应用表查小计	，删除
				ApplicationTrainingEvents totalapplicationTrainingEvents = applicationTrainingEventsDao.gettotal(innerProfessionalList.get(i));
				if(totalapplicationTrainingEvents != null)
				{
					applicationTrainingEventsDao.delete(totalapplicationTrainingEvents);
				}
			}
			
		}
		
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Professional> newprofessionalList = professionalDao.findbydepartment(department);
		List<Professional> noProfessionalList = new ArrayList<Professional>();//没有录入总体安排的专业方向
		List<List<Professional>> haveProfessionalList = new ArrayList<List<Professional>>();//录入了总体安排的专业方向
		List<ApplicationMainTainOfPT> newApplicationMainTainOfPT = new ArrayList<ApplicationMainTainOfPT>();//存储录入了的方向的内容，用于判断是否相同
		for(int i=0;i<newprofessionalList.size();i++){
			List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newprofessionalList.get(i));
			if(applicationMainTainOfPTlist == null)
			{
				noProfessionalList.add(newprofessionalList.get(i));
			}
			else
			{
				newApplicationMainTainOfPT.add(applicationMainTainOfPTlist.get(0));
			}
		}
		//将相同总体安排表的专业方向放一起
		while(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() != 0){
			List<Professional> professionallist = new ArrayList<Professional>();
			professionallist.add(newApplicationMainTainOfPT.get(0).getProfessional());
			
			if(newApplicationMainTainOfPT != null && newApplicationMainTainOfPT.size() > 1){
				for(int i=1;i<newApplicationMainTainOfPT.size();i++){
					if(newApplicationMainTainOfPT.get(0).getMainTainOfPT() == newApplicationMainTainOfPT.get(i).getMainTainOfPT())
					{
						professionallist.add(newApplicationMainTainOfPT.get(i).getProfessional());
						newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(i));
						i--;
					}	
				}
			}
			newApplicationMainTainOfPT.remove(newApplicationMainTainOfPT.get(0));
			haveProfessionalList.add(professionallist);
		}
		ServletActionContext.getRequest().setAttribute("noProfessionalList", noProfessionalList);
		ServletActionContext.getRequest().setAttribute("haveProfessionalList", haveProfessionalList);
		
	}




}
