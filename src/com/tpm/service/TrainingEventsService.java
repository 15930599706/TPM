package com.tpm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.ApplicationMainTainOfPTDao;
import com.tpm.dao.ApplicationTrainingEventsDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.MainTainOfPTDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.dao.TrainingEventsDao;
import com.tpm.entity.ApplicationMainTainOfPT;
import com.tpm.entity.ApplicationTrainingEvents;
import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPT;
import com.tpm.entity.Professional;
import com.tpm.entity.TrainingEvents;
@Transactional
public class TrainingEventsService {
	private TrainingEventsDao trainingEventsDao;
	private DepartmentDao departmentDao;
	private MainTainOfPTDao mainTainOfPTDao;
	private ApplicationTrainingEventsDao applicationTrainingEventsDao;
	private ApplicationMainTainOfPTDao applicationMainTainOfPTDao;
	private ProfessionalDao professionalDao;
	public void setApplicationMainTainOfPTDao(
			ApplicationMainTainOfPTDao applicationMainTainOfPTDao) {
		this.applicationMainTainOfPTDao = applicationMainTainOfPTDao;
	}
	public void setProfessionalDao(ProfessionalDao professionalDao) {
		this.professionalDao = professionalDao;
	}
	public void setApplicationTrainingEventsDao(
			ApplicationTrainingEventsDao applicationTrainingEventsDao) {
		this.applicationTrainingEventsDao = applicationTrainingEventsDao;
	}
	public void setMainTainOfPTDao(MainTainOfPTDao mainTainOfPTDao) {
		this.mainTainOfPTDao = mainTainOfPTDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public void setTrainingEventsDao(TrainingEventsDao trainingEventsDao) {
		this.trainingEventsDao = trainingEventsDao;
	}
	private TrainingEvents TrainingEventscode(Integer count) {
		TrainingEvents trainingEvents = new TrainingEvents();
		String[] code = {"B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(int i=0;i<code.length;i++){
			if(count == i){
				trainingEvents.setTrainingEventscode(code[i]);
			}
		}
		if(count >= code.length){
			count = count - code.length;
			trainingEvents.setTrainingEventscode("TE"+count);
		}
		return trainingEvents;
	}
	public void addTrainingEvents(String departmentid, String trainingEventsname) {
		Department department = departmentDao.get(departmentid);
		List<TrainingEvents> trainingEventslist = trainingEventsDao.getbydepartmentnototal(department);
		Integer count = 0;
		if(trainingEventslist != null){
			count = trainingEventslist.size();
		}
		TrainingEvents trainingEvents = TrainingEventscode(count);
		trainingEvents.setDepartment(department);
		trainingEvents.setTrainingEventsname(trainingEventsname);
		trainingEvents.setTotaltag(0);
		trainingEventsDao.add(trainingEvents);
	}
	public void delTrainingEvents(String departmentid, Integer trainingEventsid) {
		Department department = departmentDao.get(departmentid);
		TrainingEvents trainingEvents = trainingEventsDao.get(trainingEventsid);
		String trainingEventscode = trainingEvents.getTrainingEventscode();//将删除事件的编码
		List<TrainingEvents> trainingEventslist = trainingEventsDao.getbydepartmentnototal(department);//所有可编辑事件
		Integer count = 0;//事件数
		if(trainingEventslist != null){
			count = trainingEventslist.size();
		}
		List<MainTainOfPT> newmainTainOfPTlist = mainTainOfPTDao.findbydepartment(department);//所有学期
		for(int i=0;i<newmainTainOfPTlist.size();i++){
			MainTainOfPT mainTainOfPT = newmainTainOfPTlist.get(i);
			if(mainTainOfPT.getWeek1().equals(trainingEventscode))mainTainOfPT.setWeek1("");
			if(mainTainOfPT.getWeek2().equals(trainingEventscode))mainTainOfPT.setWeek2("");
			if(mainTainOfPT.getWeek3().equals(trainingEventscode))mainTainOfPT.setWeek3("");
			if(mainTainOfPT.getWeek4().equals(trainingEventscode))mainTainOfPT.setWeek4("");
			if(mainTainOfPT.getWeek5().equals(trainingEventscode))mainTainOfPT.setWeek5("");
			if(mainTainOfPT.getWeek6().equals(trainingEventscode))mainTainOfPT.setWeek6("");
			if(mainTainOfPT.getWeek7().equals(trainingEventscode))mainTainOfPT.setWeek7("");
			if(mainTainOfPT.getWeek8().equals(trainingEventscode))mainTainOfPT.setWeek8("");
			if(mainTainOfPT.getWeek9().equals(trainingEventscode))mainTainOfPT.setWeek9("");
			if(mainTainOfPT.getWeek10().equals(trainingEventscode))mainTainOfPT.setWeek10("");
			if(mainTainOfPT.getWeek11().equals(trainingEventscode))mainTainOfPT.setWeek11("");
			if(mainTainOfPT.getWeek12().equals(trainingEventscode))mainTainOfPT.setWeek12("");
			if(mainTainOfPT.getWeek13().equals(trainingEventscode))mainTainOfPT.setWeek13("");
			if(mainTainOfPT.getWeek14().equals(trainingEventscode))mainTainOfPT.setWeek14("");
			if(mainTainOfPT.getWeek15().equals(trainingEventscode))mainTainOfPT.setWeek15("");
			if(mainTainOfPT.getWeek16().equals(trainingEventscode))mainTainOfPT.setWeek16("");
			if(mainTainOfPT.getWeek17().equals(trainingEventscode))mainTainOfPT.setWeek17("");
			if(mainTainOfPT.getWeek18().equals(trainingEventscode))mainTainOfPT.setWeek18("");
			if(mainTainOfPT.getWeek19().equals(trainingEventscode))mainTainOfPT.setWeek19("");
			if(mainTainOfPT.getWeek20().equals(trainingEventscode))mainTainOfPT.setWeek20("");
			mainTainOfPTDao.update(mainTainOfPT);
		}
		String[] code = {"B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		Integer rightcount = 0;
		Integer flag = 0;
		for(int i=0;i<code.length;i++){
			if(trainingEventscode.equals(code[i])){
				rightcount = i;
				flag = 1;
			}
		}
		if(trainingEventslist.size() > code.length){
			if(flag == 0){
				for(int i=code.length;i<trainingEventslist.size();i++){
					Integer num = i - code.length;
					String ccode = "TE"+num;
					if(trainingEventscode.equals(ccode)){
						rightcount = i;
					}
				}
			}
		}
		if(rightcount+1 == trainingEventslist.size()){
			trainingEventsDao.delete(trainingEvents);
		}
		else{
			for(int i=rightcount+1;i<trainingEventslist.size();i++){
				TrainingEvents trainingEvents2 = trainingEventslist.get(i);
				trainingEvents2.setTrainingEventscode(code[i-1]);
				trainingEventsDao.update(trainingEvents2);
				for(int j=0;j<newmainTainOfPTlist.size();j++){
					MainTainOfPT mainTainOfPT = newmainTainOfPTlist.get(j);
					if(mainTainOfPT.getWeek1().equals(code[i]))mainTainOfPT.setWeek1(code[i-1]);
					if(mainTainOfPT.getWeek2().equals(code[i]))mainTainOfPT.setWeek2(code[i-1]);
					if(mainTainOfPT.getWeek3().equals(code[i]))mainTainOfPT.setWeek3(code[i-1]);
					if(mainTainOfPT.getWeek4().equals(code[i]))mainTainOfPT.setWeek4(code[i-1]);
					if(mainTainOfPT.getWeek5().equals(code[i]))mainTainOfPT.setWeek5(code[i-1]);
					if(mainTainOfPT.getWeek6().equals(code[i]))mainTainOfPT.setWeek6(code[i-1]);
					if(mainTainOfPT.getWeek7().equals(code[i]))mainTainOfPT.setWeek7(code[i-1]);
					if(mainTainOfPT.getWeek8().equals(code[i]))mainTainOfPT.setWeek8(code[i-1]);
					if(mainTainOfPT.getWeek9().equals(code[i]))mainTainOfPT.setWeek9(code[i-1]);
					if(mainTainOfPT.getWeek10().equals(code[i]))mainTainOfPT.setWeek10(code[i-1]);
					if(mainTainOfPT.getWeek11().equals(code[i]))mainTainOfPT.setWeek11(code[i-1]);
					if(mainTainOfPT.getWeek12().equals(code[i]))mainTainOfPT.setWeek12(code[i-1]);
					if(mainTainOfPT.getWeek13().equals(code[i]))mainTainOfPT.setWeek13(code[i-1]);
					if(mainTainOfPT.getWeek14().equals(code[i]))mainTainOfPT.setWeek14(code[i-1]);
					if(mainTainOfPT.getWeek15().equals(code[i]))mainTainOfPT.setWeek15(code[i-1]);
					if(mainTainOfPT.getWeek16().equals(code[i]))mainTainOfPT.setWeek16(code[i-1]);
					if(mainTainOfPT.getWeek17().equals(code[i]))mainTainOfPT.setWeek17(code[i-1]);
					if(mainTainOfPT.getWeek18().equals(code[i]))mainTainOfPT.setWeek18(code[i-1]);
					if(mainTainOfPT.getWeek19().equals(code[i]))mainTainOfPT.setWeek19(code[i-1]);
					if(mainTainOfPT.getWeek20().equals(code[i]))mainTainOfPT.setWeek20(code[i-1]);
					mainTainOfPTDao.update(mainTainOfPT);
				}
				trainingEventsDao.delete(trainingEvents);
			}	
		}
		
	}
	public void addTraingEventsNew(String departmentid,String trainingEventsname, List<String> professionalList) {
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		List<Professional> newProfessionalList = new ArrayList<Professional>();
		for(int j=0;j<professionalList.size();j++)
		{
			Professional professional = professionalDao.get(professionalList.get(j));
			newProfessionalList.add(professional);
		}
		ServletActionContext.getRequest().setAttribute("professionalList", newProfessionalList);
		List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(newProfessionalList.get(0));
		Integer count = 0;
		if(applicationTrainingEventslist != null){
			count = applicationTrainingEventslist.size();
		}
		TrainingEvents trainingEvents = TrainingEventscode(count);
		trainingEvents.setDepartment(department);
		trainingEvents.setTrainingEventsname(trainingEventsname);
		trainingEvents.setTotaltag(0);
		trainingEventsDao.add(trainingEvents);
		for(int i=0;i<newProfessionalList.size();i++)
		{
			ApplicationTrainingEvents applicationTrainingEvents = new ApplicationTrainingEvents();
			applicationTrainingEvents.setTrainingEvents(trainingEvents);
			applicationTrainingEvents.setDepartment(department);
			applicationTrainingEvents.setProfessional(newProfessionalList.get(i));
			applicationTrainingEventsDao.add(applicationTrainingEvents);
		}
	}
	public void delTrainingEventsNew(String departmentid,Integer trainingEventsid, List<String> professionalList) {
		Department department = departmentDao.get(departmentid);
		ServletActionContext.getRequest().setAttribute("department", department);
		TrainingEvents trainingEvents = trainingEventsDao.get(trainingEventsid);
		String trainingEventscode = trainingEvents.getTrainingEventscode();//将删除事件的编码
		List<Professional> newProfessionalList = new ArrayList<Professional>();
		for(int j=0;j<professionalList.size();j++)
		{
			Professional professional = professionalDao.get(professionalList.get(j));
			newProfessionalList.add(professional);
		}
		ServletActionContext.getRequest().setAttribute("professionalList", newProfessionalList);
		List<ApplicationTrainingEvents> applicationTrainingEventslist = applicationTrainingEventsDao.getbyProfessionalnototal(newProfessionalList.get(0));
		List<TrainingEvents> trainingEventslist = new ArrayList<TrainingEvents>();
		if(applicationTrainingEventslist != null ){//所有可编辑事件
			for(int i=0;i<applicationTrainingEventslist.size();i++)
			trainingEventslist.add(applicationTrainingEventslist.get(i).getTrainingEvents());
		}
		Integer count = 0;//事件数
		if(trainingEventslist != null){
			count = trainingEventslist.size();
		}
		
		List<ApplicationMainTainOfPT> applicationMainTainOfPTlist = applicationMainTainOfPTDao.findbyProfessional(newProfessionalList.get(0));
		List<MainTainOfPT> newmainTainOfPTlist = new ArrayList<MainTainOfPT>();//所有学期
		for(int i=0;i<applicationMainTainOfPTlist.size();i++)
		{
			newmainTainOfPTlist.add(applicationMainTainOfPTlist.get(i).getMainTainOfPT());
		}
		
		for(int i=0;i<newmainTainOfPTlist.size();i++){
			MainTainOfPT mainTainOfPT = newmainTainOfPTlist.get(i);
			if(mainTainOfPT.getWeek1().equals(trainingEventscode))mainTainOfPT.setWeek1("");
			if(mainTainOfPT.getWeek2().equals(trainingEventscode))mainTainOfPT.setWeek2("");
			if(mainTainOfPT.getWeek3().equals(trainingEventscode))mainTainOfPT.setWeek3("");
			if(mainTainOfPT.getWeek4().equals(trainingEventscode))mainTainOfPT.setWeek4("");
			if(mainTainOfPT.getWeek5().equals(trainingEventscode))mainTainOfPT.setWeek5("");
			if(mainTainOfPT.getWeek6().equals(trainingEventscode))mainTainOfPT.setWeek6("");
			if(mainTainOfPT.getWeek7().equals(trainingEventscode))mainTainOfPT.setWeek7("");
			if(mainTainOfPT.getWeek8().equals(trainingEventscode))mainTainOfPT.setWeek8("");
			if(mainTainOfPT.getWeek9().equals(trainingEventscode))mainTainOfPT.setWeek9("");
			if(mainTainOfPT.getWeek10().equals(trainingEventscode))mainTainOfPT.setWeek10("");
			if(mainTainOfPT.getWeek11().equals(trainingEventscode))mainTainOfPT.setWeek11("");
			if(mainTainOfPT.getWeek12().equals(trainingEventscode))mainTainOfPT.setWeek12("");
			if(mainTainOfPT.getWeek13().equals(trainingEventscode))mainTainOfPT.setWeek13("");
			if(mainTainOfPT.getWeek14().equals(trainingEventscode))mainTainOfPT.setWeek14("");
			if(mainTainOfPT.getWeek15().equals(trainingEventscode))mainTainOfPT.setWeek15("");
			if(mainTainOfPT.getWeek16().equals(trainingEventscode))mainTainOfPT.setWeek16("");
			if(mainTainOfPT.getWeek17().equals(trainingEventscode))mainTainOfPT.setWeek17("");
			if(mainTainOfPT.getWeek18().equals(trainingEventscode))mainTainOfPT.setWeek18("");
			if(mainTainOfPT.getWeek19().equals(trainingEventscode))mainTainOfPT.setWeek19("");
			if(mainTainOfPT.getWeek20().equals(trainingEventscode))mainTainOfPT.setWeek20("");
			mainTainOfPTDao.update(mainTainOfPT);
		}
		String[] code = {"B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		Integer rightcount = 0;
		Integer flag = 0;
		for(int i=0;i<code.length;i++){
			if(trainingEventscode.equals(code[i])){
				rightcount = i;
				flag = 1;
			}
		}
		if(trainingEventslist.size() > code.length){
			if(flag == 0){
				for(int i=code.length;i<trainingEventslist.size();i++){
					Integer num = i - code.length;
					String ccode = "TE"+num;
					if(trainingEventscode.equals(ccode)){
						rightcount = i;
					}
				}
			}
		}
		if(rightcount+1 == trainingEventslist.size()){
			trainingEventsDao.delete(trainingEvents);
		}
		else{
			for(int i=rightcount+1;i<trainingEventslist.size();i++){
				TrainingEvents trainingEvents2 = trainingEventslist.get(i);
				trainingEvents2.setTrainingEventscode(code[i-1]);
				trainingEventsDao.update(trainingEvents2);
				for(int j=0;j<newmainTainOfPTlist.size();j++){
					MainTainOfPT mainTainOfPT = newmainTainOfPTlist.get(j);
					if(mainTainOfPT.getWeek1().equals(code[i]))mainTainOfPT.setWeek1(code[i-1]);
					if(mainTainOfPT.getWeek2().equals(code[i]))mainTainOfPT.setWeek2(code[i-1]);
					if(mainTainOfPT.getWeek3().equals(code[i]))mainTainOfPT.setWeek3(code[i-1]);
					if(mainTainOfPT.getWeek4().equals(code[i]))mainTainOfPT.setWeek4(code[i-1]);
					if(mainTainOfPT.getWeek5().equals(code[i]))mainTainOfPT.setWeek5(code[i-1]);
					if(mainTainOfPT.getWeek6().equals(code[i]))mainTainOfPT.setWeek6(code[i-1]);
					if(mainTainOfPT.getWeek7().equals(code[i]))mainTainOfPT.setWeek7(code[i-1]);
					if(mainTainOfPT.getWeek8().equals(code[i]))mainTainOfPT.setWeek8(code[i-1]);
					if(mainTainOfPT.getWeek9().equals(code[i]))mainTainOfPT.setWeek9(code[i-1]);
					if(mainTainOfPT.getWeek10().equals(code[i]))mainTainOfPT.setWeek10(code[i-1]);
					if(mainTainOfPT.getWeek11().equals(code[i]))mainTainOfPT.setWeek11(code[i-1]);
					if(mainTainOfPT.getWeek12().equals(code[i]))mainTainOfPT.setWeek12(code[i-1]);
					if(mainTainOfPT.getWeek13().equals(code[i]))mainTainOfPT.setWeek13(code[i-1]);
					if(mainTainOfPT.getWeek14().equals(code[i]))mainTainOfPT.setWeek14(code[i-1]);
					if(mainTainOfPT.getWeek15().equals(code[i]))mainTainOfPT.setWeek15(code[i-1]);
					if(mainTainOfPT.getWeek16().equals(code[i]))mainTainOfPT.setWeek16(code[i-1]);
					if(mainTainOfPT.getWeek17().equals(code[i]))mainTainOfPT.setWeek17(code[i-1]);
					if(mainTainOfPT.getWeek18().equals(code[i]))mainTainOfPT.setWeek18(code[i-1]);
					if(mainTainOfPT.getWeek19().equals(code[i]))mainTainOfPT.setWeek19(code[i-1]);
					if(mainTainOfPT.getWeek20().equals(code[i]))mainTainOfPT.setWeek20(code[i-1]);
					mainTainOfPTDao.update(mainTainOfPT);
				}
				trainingEventsDao.delete(trainingEvents);
			}	
		}
		
	}
	
}
