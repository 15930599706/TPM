package com.tpm.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.AbilityAndTeachObjDao;
import com.tpm.dao.AbilityAndTeachObj_CourseDesignDao;
import com.tpm.dao.AbilityAndTeachObj_FieldWorkDao;
import com.tpm.dao.AbilityAndTeachObj_GraDao;
import com.tpm.dao.AbilityAndTeachObj_InnerExperimentDao;
import com.tpm.dao.ApplicationSyllabusDao;
import com.tpm.dao.ApplicationSyllabus_CourseDesignDao;
import com.tpm.dao.ApplicationSyllabus_FieldWorkDao;
import com.tpm.dao.ApplicationSyllabus_GraDao;
import com.tpm.dao.ApplicationSyllabus_InnerExperimentDao;
import com.tpm.dao.CollegeDao;
import com.tpm.dao.ContentTheoDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.CurriculumMatrixDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.DiscussContentDao;
import com.tpm.dao.DistributeHourRelateExperProjectDao;
import com.tpm.dao.DistributeHour_CourseDesignDao;
import com.tpm.dao.DistributeHour_GraDao;
import com.tpm.dao.DistributeHour_InnerExperimentDao;
import com.tpm.dao.DistributeHour_TheoDao;
import com.tpm.dao.ExperimentContentDao;
import com.tpm.dao.ExperimentDao;
import com.tpm.dao.ExpermentProjectDao;
import com.tpm.dao.IndexSelectDao;
import com.tpm.dao.IndexSelect_CourseDesignDao;
import com.tpm.dao.IndexSelect_FieldWorkDao;
import com.tpm.dao.IndexSelect_GraDao;
import com.tpm.dao.IndexSelect_InnerExperimentDao;
import com.tpm.dao.OtherContentDao;
import com.tpm.dao.PracticeBookDao;
import com.tpm.dao.PracticeBooks_CourseDesignDao;
import com.tpm.dao.PracticeBooks_InnerExperimentDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.dao.SyllabusDao;
import com.tpm.dao.Syllabus_CourseDesignDao;
import com.tpm.dao.Syllabus_FieldWorkDao;
import com.tpm.dao.Syllabus_GraDao;
import com.tpm.dao.Syllabus_InnerExperimentDao;
import com.tpm.dao.TeachObjDao;
import com.tpm.dao.TeachObj_CourseDesignDao;
import com.tpm.dao.TeachObj_FieldWorkDao;
import com.tpm.dao.TeachObj_GraDao;
import com.tpm.dao.TeachObj_InnerExperimentDao;
import com.tpm.dao.TextBooksDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.dao.ThreeProjectDao;
import com.tpm.dao.UserDao;
import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.entity.AbilityAndTeachObj_CourseDesign;
import com.tpm.entity.AbilityAndTeachObj_FieldWork;
import com.tpm.entity.AbilityAndTeachObj_Gra;
import com.tpm.entity.AbilityAndTeachObj_InnerExperiment;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_CourseDesign;
import com.tpm.entity.ApplicationSyllabus_FieldWork;
import com.tpm.entity.ApplicationSyllabus_Gra;
import com.tpm.entity.ApplicationSyllabus_InnerExperiment;
import com.tpm.entity.College;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.Department;
import com.tpm.entity.DiscussContent;
import com.tpm.entity.DistributeHourRelateExperProject;
import com.tpm.entity.DistributeHour_CourseDesign;
import com.tpm.entity.DistributeHour_Gra;
import com.tpm.entity.DistributeHour_InnerExperiment;
import com.tpm.entity.DistributeHour_Theo;
import com.tpm.entity.Experiment;
import com.tpm.entity.ExperimentContent;
import com.tpm.entity.ExpermentProject;
import com.tpm.entity.IndexSelect;
import com.tpm.entity.IndexSelect_CourseDesign;
import com.tpm.entity.IndexSelect_FieldWork;
import com.tpm.entity.IndexSelect_Gra;
import com.tpm.entity.IndexSelect_InnerExperiment;
import com.tpm.entity.OtherContent;
import com.tpm.entity.PageBean;
import com.tpm.entity.PracticeBook;
import com.tpm.entity.PracticeBooks_CourseDesign;
import com.tpm.entity.PracticeBooks_InnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.Syllabus;
import com.tpm.entity.Syllabus_CourseDesign;
import com.tpm.entity.Syllabus_FieldWork;
import com.tpm.entity.Syllabus_Gra;
import com.tpm.entity.Syllabus_InnerExperiment;
import com.tpm.entity.TeachObj;
import com.tpm.entity.TeachObj_CourseDesign;
import com.tpm.entity.TeachObj_FieldWork;
import com.tpm.entity.TeachObj_Gra;
import com.tpm.entity.TeachObj_InnerExperiment;
import com.tpm.entity.TextBooks;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.ThreeProject;
import com.tpm.entity.User;

@Transactional
public class PracticePlanService {
	private PracticePlanDao practicePlanDao;
	private CurriculumDao curriculumDao;
	private DepartmentDao departmentDao;
	private ProfessionalDao professionalDao;
	private UserDao userDao;
	private CollegeDao collegeDao;
	private SyllabusDao syllabusDao;
	private ApplicationSyllabusDao applicationSyllabusDao;
	private Syllabus_FieldWorkDao syllabus_FieldWorkDao;
	private ApplicationSyllabus_FieldWorkDao applicationSyllabus_FieldWorkDao;
	private Syllabus_CourseDesignDao syllabus_CourseDesignDao;
	private ApplicationSyllabus_CourseDesignDao applicationSyllabus_CourseDesignDao;
	private Syllabus_GraDao syllabus_GraDao;
	private ApplicationSyllabus_GraDao applicationSyllabus_GraDao;
	private Syllabus_InnerExperimentDao syllabus_InnerExperimentDao;
	private ApplicationSyllabus_InnerExperimentDao applicationSyllabus_InnerExperimentDao;
	private IndexSelect_FieldWorkDao indexSelect_FieldWorkDao;
	private TeachObj_FieldWorkDao teachObj_FieldWorkDao;
	private AbilityAndTeachObj_FieldWorkDao abilityAndTeachObj_FieldWorkDao;
	private IndexSelect_CourseDesignDao indexSelect_CourseDesignDao;
	private TeachObj_CourseDesignDao teachObj_CourseDesignDao;
	private AbilityAndTeachObj_CourseDesignDao abilityAndTeachObj_CourseDesignDao;
	private IndexSelect_InnerExperimentDao indexSelect_InnerExperimentDao;
	private TeachObj_InnerExperimentDao teachObj_InnerExperimentDao;
	private AbilityAndTeachObj_InnerExperimentDao abilityAndTeachObj_InnerExperimentDao;
	private IndexSelect_GraDao indexSelect_GraDao;
	private TeachObj_GraDao teachObj_GraDao;
	private AbilityAndTeachObj_GraDao abilityAndTeachObj_GraDao;
	private PracticeBookDao practiceBookDao;
	private PracticeBooks_CourseDesignDao practiceBooks_CourseDesignDao;
	private ExpermentProjectDao expermentProjectDao;
	private PracticeBooks_InnerExperimentDao practiceBooks_InnerExperimentDao;
	
	private DistributeHour_CourseDesignDao distributeHour_CourseDesignDao;
	private DistributeHour_InnerExperimentDao distributeHour_InnerExperimentDao;
	private DistributeHour_GraDao distributeHour_GraDao;
	
	private CurriculumMatrixDao curriculumMatrixDao;
	
	private AbilityAndTeachObjDao abilityAndTeachObjDao;
	private IndexSelectDao indexSelectDao;
	private TeachObjDao teachObjDao;
	private ContentTheoDao contentTheoDao;
	private ExperimentContentDao experimentContentDao;
	private DiscussContentDao discussContentDao;
	private ThreeProjectDao threeProjectDao;
	private TextBooksDao textBooksDao;
	private OtherContentDao otherContentDao;
	private DistributeHour_TheoDao distributeHour_TheoDao;
	private TheoreticalPlanDao theoreticalPlanDao;
	private DistributeHourRelateExperProjectDao practicerelateDao;
	
	public void setPracticerelateDao(
			DistributeHourRelateExperProjectDao practicerelateDao) {
		this.practicerelateDao = practicerelateDao;
	}
	public void setAbilityAndTeachObjDao(AbilityAndTeachObjDao abilityAndTeachObjDao) {
		this.abilityAndTeachObjDao = abilityAndTeachObjDao;
	}
	public void setIndexSelectDao(IndexSelectDao indexSelectDao) {
		this.indexSelectDao = indexSelectDao;
	}
	public void setTeachObjDao(TeachObjDao teachObjDao) {
		this.teachObjDao = teachObjDao;
	}
	public void setContentTheoDao(ContentTheoDao contentTheoDao) {
		this.contentTheoDao = contentTheoDao;
	}
	public void setExperimentContentDao(ExperimentContentDao experimentContentDao) {
		this.experimentContentDao = experimentContentDao;
	}
	public void setDiscussContentDao(DiscussContentDao discussContentDao) {
		this.discussContentDao = discussContentDao;
	}
	public void setThreeProjectDao(ThreeProjectDao threeProjectDao) {
		this.threeProjectDao = threeProjectDao;
	}
	public void setTextBooksDao(TextBooksDao textBooksDao) {
		this.textBooksDao = textBooksDao;
	}
	public void setOtherContentDao(OtherContentDao otherContentDao) {
		this.otherContentDao = otherContentDao;
	}
	public void setDistributeHour_TheoDao(
			DistributeHour_TheoDao distributeHour_TheoDao) {
		this.distributeHour_TheoDao = distributeHour_TheoDao;
	}
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}
	public void setCurriculumMatrixDao(CurriculumMatrixDao curriculumMatrixDao) {
		this.curriculumMatrixDao = curriculumMatrixDao;
	}
	public void setDistributeHour_CourseDesignDao(
			DistributeHour_CourseDesignDao distributeHour_CourseDesignDao) {
		this.distributeHour_CourseDesignDao = distributeHour_CourseDesignDao;
	}
	public void setDistributeHour_InnerExperimentDao(
			DistributeHour_InnerExperimentDao distributeHour_InnerExperimentDao) {
		this.distributeHour_InnerExperimentDao = distributeHour_InnerExperimentDao;
	}
	public void setDistributeHour_GraDao(DistributeHour_GraDao distributeHour_GraDao) {
		this.distributeHour_GraDao = distributeHour_GraDao;
	}
	public void setPracticeBookDao(PracticeBookDao practiceBookDao) {
		this.practiceBookDao = practiceBookDao;
	}
	public void setPracticeBooks_CourseDesignDao(
			PracticeBooks_CourseDesignDao practiceBooks_CourseDesignDao) {
		this.practiceBooks_CourseDesignDao = practiceBooks_CourseDesignDao;
	}
	public void setExpermentProjectDao(ExpermentProjectDao expermentProjectDao) {
		this.expermentProjectDao = expermentProjectDao;
	}
	public void setPracticeBooks_InnerExperimentDao(
			PracticeBooks_InnerExperimentDao practiceBooks_InnerExperimentDao) {
		this.practiceBooks_InnerExperimentDao = practiceBooks_InnerExperimentDao;
	}
	public void setIndexSelect_FieldWorkDao(
			IndexSelect_FieldWorkDao indexSelect_FieldWorkDao) {
		this.indexSelect_FieldWorkDao = indexSelect_FieldWorkDao;
	}
	public void setTeachObj_FieldWorkDao(TeachObj_FieldWorkDao teachObj_FieldWorkDao) {
		this.teachObj_FieldWorkDao = teachObj_FieldWorkDao;
	}
	public void setAbilityAndTeachObj_FieldWorkDao(
			AbilityAndTeachObj_FieldWorkDao abilityAndTeachObj_FieldWorkDao) {
		this.abilityAndTeachObj_FieldWorkDao = abilityAndTeachObj_FieldWorkDao;
	}
	public void setIndexSelect_CourseDesignDao(
			IndexSelect_CourseDesignDao indexSelect_CourseDesignDao) {
		this.indexSelect_CourseDesignDao = indexSelect_CourseDesignDao;
	}
	public void setTeachObj_CourseDesignDao(
			TeachObj_CourseDesignDao teachObj_CourseDesignDao) {
		this.teachObj_CourseDesignDao = teachObj_CourseDesignDao;
	}
	public void setAbilityAndTeachObj_CourseDesignDao(
			AbilityAndTeachObj_CourseDesignDao abilityAndTeachObj_CourseDesignDao) {
		this.abilityAndTeachObj_CourseDesignDao = abilityAndTeachObj_CourseDesignDao;
	}
	public void setIndexSelect_InnerExperimentDao(
			IndexSelect_InnerExperimentDao indexSelect_InnerExperimentDao) {
		this.indexSelect_InnerExperimentDao = indexSelect_InnerExperimentDao;
	}
	public void setTeachObj_InnerExperimentDao(
			TeachObj_InnerExperimentDao teachObj_InnerExperimentDao) {
		this.teachObj_InnerExperimentDao = teachObj_InnerExperimentDao;
	}
	public void setAbilityAndTeachObj_InnerExperimentDao(
			AbilityAndTeachObj_InnerExperimentDao abilityAndTeachObj_InnerExperimentDao) {
		this.abilityAndTeachObj_InnerExperimentDao = abilityAndTeachObj_InnerExperimentDao;
	}
	public void setIndexSelect_GraDao(IndexSelect_GraDao indexSelect_GraDao) {
		this.indexSelect_GraDao = indexSelect_GraDao;
	}
	public void setTeachObj_GraDao(TeachObj_GraDao teachObj_GraDao) {
		this.teachObj_GraDao = teachObj_GraDao;
	}
	public void setAbilityAndTeachObj_GraDao(
			AbilityAndTeachObj_GraDao abilityAndTeachObj_GraDao) {
		this.abilityAndTeachObj_GraDao = abilityAndTeachObj_GraDao;
	}
	public void setSyllabus_InnerExperimentDao(
			Syllabus_InnerExperimentDao syllabus_InnerExperimentDao) {
		this.syllabus_InnerExperimentDao = syllabus_InnerExperimentDao;
	}
	public void setApplicationSyllabus_InnerExperimentDao(
			ApplicationSyllabus_InnerExperimentDao applicationSyllabus_InnerExperimentDao) {
		this.applicationSyllabus_InnerExperimentDao = applicationSyllabus_InnerExperimentDao;
	}
	public void setSyllabusDao(SyllabusDao syllabusDao) {
		this.syllabusDao = syllabusDao;
	}
	public void setSyllabus_GraDao(Syllabus_GraDao syllabus_GraDao) {
		this.syllabus_GraDao = syllabus_GraDao;
	}
	public void setApplicationSyllabus_GraDao(
			ApplicationSyllabus_GraDao applicationSyllabus_GraDao) {
		this.applicationSyllabus_GraDao = applicationSyllabus_GraDao;
	}
	public void setSyllabus_CourseDesignDao(
			Syllabus_CourseDesignDao syllabus_CourseDesignDao) {
		this.syllabus_CourseDesignDao = syllabus_CourseDesignDao;
	}
	public void setApplicationSyllabus_CourseDesignDao(
			ApplicationSyllabus_CourseDesignDao applicationSyllabus_CourseDesignDao) {
		this.applicationSyllabus_CourseDesignDao = applicationSyllabus_CourseDesignDao;
	}
	public void setSyllabus_FieldWorkDao(Syllabus_FieldWorkDao syllabus_FieldWorkDao) {
		this.syllabus_FieldWorkDao = syllabus_FieldWorkDao;
	}
	public void setApplicationSyllabus_FieldWorkDao(
			ApplicationSyllabus_FieldWorkDao applicationSyllabus_FieldWorkDao) {
		this.applicationSyllabus_FieldWorkDao = applicationSyllabus_FieldWorkDao;
	}
	public void setApplicationSyllabusDao(
			ApplicationSyllabusDao applicationSyllabusDao) {
		this.applicationSyllabusDao = applicationSyllabusDao;
	}
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	
	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public void setProfessionalDao(ProfessionalDao professionalDao) {
		this.professionalDao = professionalDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}

	private User user;	
	public void setUser(User user) {
		this.user = user;
	}

	public void findUserInfo(String tnum){	//根据登陆用户id获得用户信息
		user = findUserById(tnum);
		ServletActionContext.getRequest().setAttribute("user", user);			
	}

	private List<College> collegelist;		//接收第一个下拉框数据
	private List<Department> departmentlist; //声明系列表
	public void searchCollegeList(){	//查询所有学院列表
		collegelist = collegeDao.findAll();
		ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
	}
	
	public void tojzsjkPage(String tnum, String collegeid, String departmentid){	//跳转到集中实践课页面
		findUserInfo(tnum);
		if(user.getAdminlevel() == 5){	//为校管理员时，返回学院List
			collegelist = collegeDao.findAll();
			if(collegeid != null && collegeid !="" && departmentid != null && departmentid != "")
			{
				College college = collegeDao.get(collegeid);
				Department department = departmentDao.get(departmentid); 
				ServletActionContext.getRequest().setAttribute("xueyuan", college.getCollegeid());		//返回所选的学院
				ServletActionContext.getRequest().setAttribute("xi", department.getDepartmentid());		//返回所选择的系	
				List<Professional> professionalList = findProfessional(department.getDepartmentid());	
				if(professionalList == null || professionalList.size() == 0 )	//如果该专业不分方向，在进入实践课页面时，要返回所选择的计划列表
				{							
					List<PracticeLesson> practiceLessonList = findpracticeLessonDepartment(department);
					ServletActionContext.getRequest().setAttribute("practiceLessonList", practiceLessonList);
					
					float publicTotalCredit = 0;	//公共教育平台总学分
					float professionalTotalCredit = 0;	//专业教育平台总学分
					float totalCredit = 0;	//所选的总学分
					String publicProportion;	//公共教育平台占比
					String professionalProportion;	//专业教育平台占比
					for(int i=0;i<practiceLessonList.size();i++)
					{
						if(practiceLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("公共教育平台"))
						{
							publicTotalCredit = publicTotalCredit + Float.parseFloat(practiceLessonList.get(i).getCurriculum().getCredit());
						}
						if(practiceLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("专业教育平台"))
						{
							professionalTotalCredit = professionalTotalCredit + Float.parseFloat(practiceLessonList.get(i).getCurriculum().getCredit());
						}
					}
					
					//学分分别四舍五入最多取两位小数
					publicTotalCredit = (float)(Math.round(publicTotalCredit*100))/100;
					professionalTotalCredit = (float)(Math.round(professionalTotalCredit*100))/100;
					totalCredit = publicTotalCredit + professionalTotalCredit;
					//求学分百分比，最多保留两位小数
					NumberFormat num = NumberFormat.getPercentInstance(); //获取格式化对象
					num.setMaximumFractionDigits(2); //设置百分数精确度2即保留两位小数,小数点后最多为2位
					publicProportion = num.format(publicTotalCredit/totalCredit);
					professionalProportion = num.format(professionalTotalCredit/totalCredit);
					
					ServletActionContext.getRequest().setAttribute("publicTotalCredit", publicTotalCredit);		
					ServletActionContext.getRequest().setAttribute("professionalTotalCredit", professionalTotalCredit);	
					ServletActionContext.getRequest().setAttribute("totalCredit", totalCredit);	
					ServletActionContext.getRequest().setAttribute("publicProportion", publicProportion);	
					ServletActionContext.getRequest().setAttribute("professionalProportion", professionalProportion);				
				}	
			}
		}
		if(user.getAdminlevel() == 3){	//为院管理员时，返回该管理员所属学院，存入List中
			collegelist = findCollegeById(user.getCollege().getCollegeid());
		}
		if(user.getAdminlevel() == 1){	//为系管理员时，返回管理员所在的学院和系，分别存入List中			
			collegelist = findCollegeById(user.getCollege().getCollegeid());
			departmentlist = findDepartmentById(user.getDepartment().getDepartmentid());
			List<Professional> professionalList = findProfessional(user.getDepartment().getDepartmentid());	
			if(professionalList == null || professionalList.size() == 0 )	//如果该专业不分方向，在进入实践课页面时，要返回所选择的计划列表
			{							
				List<PracticeLesson> practiceLessonList = findpracticeLessonDepartment(departmentlist.get(0));
				ServletActionContext.getRequest().setAttribute("practiceLessonList", practiceLessonList);
				
				float publicTotalCredit = 0;	//公共教育平台总学分
				float professionalTotalCredit = 0;	//专业教育平台总学分
				float totalCredit = 0;	//所选的总学分
				String publicProportion;	//公共教育平台占比
				String professionalProportion;	//专业教育平台占比
				for(int i=0;i<practiceLessonList.size();i++)
				{
					if(practiceLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("公共教育平台"))
					{
						publicTotalCredit = publicTotalCredit + Float.parseFloat(practiceLessonList.get(i).getCurriculum().getCredit());
					}
					if(practiceLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("专业教育平台"))
					{
						professionalTotalCredit = professionalTotalCredit + Float.parseFloat(practiceLessonList.get(i).getCurriculum().getCredit());
					}
				}
				totalCredit = publicTotalCredit + professionalTotalCredit;
				NumberFormat num = NumberFormat.getPercentInstance(); //获取格式化对象
				num.setMaximumFractionDigits(2); //设置百分数精确度2即保留两位小数,小数点后最多为2位
				publicProportion = num.format(publicTotalCredit/totalCredit);
				professionalProportion = num.format(professionalTotalCredit/totalCredit);
				ServletActionContext.getRequest().setAttribute("publicTotalCredit", publicTotalCredit);		
				ServletActionContext.getRequest().setAttribute("professionalTotalCredit", professionalTotalCredit);	
				ServletActionContext.getRequest().setAttribute("totalCredit", totalCredit);	
				ServletActionContext.getRequest().setAttribute("publicProportion", publicProportion);	
				ServletActionContext.getRequest().setAttribute("professionalProportion", professionalProportion);				
			}					
		}
		ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
		ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);	
	}
	
	public void jzsjkResult(PracticeLesson practiceLesson, String tnum){	//集中实践课页面的返回
		findUserInfo(tnum);
		if(user.getAdminlevel() == 5){
			collegelist = collegeDao.findAll();
			ServletActionContext.getRequest().setAttribute("xueyuan", practiceLesson.getDepartment().getCollege().getCollegeid());		//返回所选的学院
			ServletActionContext.getRequest().setAttribute("xi", practiceLesson.getDepartment().getDepartmentid());		//返回所选择的系								
		}
		if(user.getAdminlevel() == 3){
			collegelist = findCollegeById(user.getCollege().getCollegeid());
			ServletActionContext.getRequest().setAttribute("xi", practiceLesson.getDepartment().getDepartmentid());		//返回所选择的系		
		}
		if(user.getAdminlevel() == 1){
			collegelist = findCollegeById(user.getCollege().getCollegeid());
			departmentlist = findDepartmentById(user.getDepartment().getDepartmentid());										
		}
		if(practiceLesson.getProfessional() != null && !"".equals(practiceLesson.getProfessional().getProfessionalid())){
			ServletActionContext.getRequest().setAttribute("fangxiang", practiceLesson.getProfessional().getProfessionalid());		//返回所选择的专业方向												
		}
		List<PracticeLesson> practiceLessonList = findPracticeLesson(practiceLesson);
		ServletActionContext.getRequest().setAttribute("practiceLessonList", practiceLessonList);	
		float publicTotalCredit = 0;	//公共教育平台总学分
		float professionalTotalCredit = 0;	//专业教育平台总学分
		float totalCredit = 0;	//所选的总学分
		String publicProportion;
		String professionalProportion;
		for(int i=0;i<practiceLessonList.size();i++)
		{
			if(practiceLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("公共教育平台"))
			{
				publicTotalCredit = publicTotalCredit + Float.parseFloat(practiceLessonList.get(i).getCurriculum().getCredit());
			}
			if(practiceLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("专业教育平台"))
			{
				professionalTotalCredit = professionalTotalCredit + Float.parseFloat(practiceLessonList.get(i).getCurriculum().getCredit());
			}
		}
		
		//学分分别四舍五入最多取两位小数
		publicTotalCredit = (float)(Math.round(publicTotalCredit*100))/100;
		professionalTotalCredit = (float)(Math.round(professionalTotalCredit*100))/100;
		totalCredit = publicTotalCredit + professionalTotalCredit;
		//求学分百分比，最多保留两位小数
		NumberFormat num = NumberFormat.getPercentInstance(); //获取格式化对象
		num.setMaximumFractionDigits(2); //设置百分数精确度2即保留两位小数,小数点后最多为2位
		publicProportion = num.format(publicTotalCredit/totalCredit);
		professionalProportion = num.format(professionalTotalCredit/totalCredit);
		
		ServletActionContext.getRequest().setAttribute("publicTotalCredit", publicTotalCredit);		
		ServletActionContext.getRequest().setAttribute("professionalTotalCredit", professionalTotalCredit);	
		ServletActionContext.getRequest().setAttribute("totalCredit", totalCredit);		
		ServletActionContext.getRequest().setAttribute("publicProportion", publicProportion);	
		ServletActionContext.getRequest().setAttribute("professionalProportion", professionalProportion);
		
		ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
		ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
		ServletActionContext.getRequest().setAttribute("pingtai", practiceLesson.getCurriculum().getCurriculumpingtai());					
		ServletActionContext.getRequest().setAttribute("xingzhi", practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid());	
		ServletActionContext.getRequest().setAttribute("leibie", practiceLesson.getCurriculum().getCourseLei());
		ServletActionContext.getRequest().setAttribute("guishu", practiceLesson.getCurriculum().getCourseCategory());	
		ServletActionContext.getRequest().setAttribute("xueqi", practiceLesson.getXueqi());				
	}
	
	public void totjsjkPage(String collegeid, String departmentid){ //跳转到添加实践课页面	
		searchCollegeList();
		
		College college = collegeDao.get(collegeid);
		Department department = departmentDao.get(departmentid); 
		List<Curriculum> curriculumList = findAllPracticeLesson(college,department);
		List<Professional> professionalList = findProfessional(department.getDepartmentid());				
		
		Integer professionalListCount = professionalList.size();		
		if(professionalListCount>0){		//如果分专业方向，则查询每个课程中没有选择该课程的专业方向
			List<List<Professional>> unselectedProfessionalList = new ArrayList<List<Professional>>();		
			for(int i=0;i<curriculumList.size();i++){
				List<Professional> innerProfessionalList = findUnselectedProfessionalList(department.getDepartmentid(),curriculumList.get(i).getCurriculumid());
				if(innerProfessionalList.size() <= 0){	//如果课程的未选专业方向数目是0，这说明所有专业方向已选，在显示的课程列表中删除该课程
					curriculumList.remove(curriculumList.get(i));
					i--;	//***由于remove，导致size值减一，进行i--操作与之相对应
				}
				else{
					unselectedProfessionalList.add(innerProfessionalList);	//如果有专业方向未选该课，返回这些专业方向，为List形式
				}				
			}
			ServletActionContext.getRequest().setAttribute("unselectedProfessionalList", unselectedProfessionalList);
		}				
		Integer curriculumListCount = curriculumList.size();
		ServletActionContext.getRequest().setAttribute("first", "yes");
		ServletActionContext.getRequest().setAttribute("xueyuan", college.getCollegeid());
		ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
		ServletActionContext.getRequest().setAttribute("professionalListCount", professionalListCount);
		ServletActionContext.getRequest().setAttribute("curriculumListCount", curriculumListCount);
		ServletActionContext.getRequest().setAttribute("curriculumList", curriculumList);	
		ServletActionContext.getRequest().setAttribute("department", department);
		ServletActionContext.getRequest().setAttribute("college", college);
	}
	
	public void tjsjkResult(PracticeLesson practiceLesson, String collegeid, String departmentid){	//添加实践课页面的返回
		College college = collegeDao.get(collegeid);
		Department department = departmentDao.get(departmentid); 
		
		List<Curriculum> curriculumList = findCurriculum(practiceLesson);
		List<Professional> professionalList = findProfessional(department.getDepartmentid());
		Integer professionalListCount = professionalList.size();
		if(professionalListCount>0){		
			List<List<Professional>> unselectedProfessionalList = new ArrayList<List<Professional>>();		
			for(int i=0;i<curriculumList.size();i++){
				List<Professional> innerProfessionalList = findUnselectedProfessionalList(department.getDepartmentid(),curriculumList.get(i).getCurriculumid());				
				if(innerProfessionalList == null || innerProfessionalList.size() == 0){
					curriculumList.remove(curriculumList.get(i));
					i--;
				}
				else{
					unselectedProfessionalList.add(innerProfessionalList);
				}
			}
			ServletActionContext.getRequest().setAttribute("unselectedProfessionalList", unselectedProfessionalList);
		}									
		Integer curriculumListCount = curriculumList.size();
		ServletActionContext.getRequest().setAttribute("professionalListCount", professionalListCount);
		ServletActionContext.getRequest().setAttribute("curriculumListCount", curriculumListCount);
		ServletActionContext.getRequest().setAttribute("curriculumList", curriculumList);		
		searchCollegeList();
		ServletActionContext.getRequest().setAttribute("xueyuan", practiceLesson.getCurriculum().getCollege().getCollegeid());		//返回所选的学院
		ServletActionContext.getRequest().setAttribute("pingtai", practiceLesson.getCurriculum().getCurriculumpingtai());					
		ServletActionContext.getRequest().setAttribute("xingzhi", practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid());									
		ServletActionContext.getRequest().setAttribute("mingcheng", practiceLesson.getCurriculum().getCurriculumCname());					
		ServletActionContext.getRequest().setAttribute("daima", practiceLesson.getCurriculum().getCurriculumid());	
		ServletActionContext.getRequest().setAttribute("leibie", practiceLesson.getCurriculum().getCourseLei());
		ServletActionContext.getRequest().setAttribute("guishu", practiceLesson.getCurriculum().getCourseCategory());	
		ServletActionContext.getRequest().setAttribute("department", department);
		ServletActionContext.getRequest().setAttribute("college", college);
	}
	
	public void sjkAddResult(String data,String departmentid){		
		try {
			String result = URLDecoder.decode(data,"UTF-8");
			try {
				JSONArray jsonArray = new JSONArray(result);
				int iSize = jsonArray.length();				
				Department department = departmentDao.get(departmentid); 
				for (int i = 0; i < iSize; i++) 
					{
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						String curriculumid =jsonObj.get("curriculumid").toString();
						String kewaishiyan = jsonObj.get("kewaishiyan").toString();
						String kewaishangji = jsonObj.get("kewaishangji").toString();
						String xueqi =jsonObj.get("xueqi").toString();
						String qizhizhou =jsonObj.get("qizhizhou").toString();
						String zuzhixingshi =jsonObj.get("zuzhixingshi").toString();
						String isxueweike = jsonObj.get("isxueweike").toString();
						String jizhongshijian =jsonObj.get("jizhongshijian").toString();
						String professionalId =jsonObj.get("zhuanyefangxiang").toString();						
						String beizhu =jsonObj.get("beizhu").toString();					
					
						if(professionalId.equals("-1")) {	//	若不分专业方向，直接插入该记录
							PracticeLesson SelectPracticeLesson = new PracticeLesson();	//声明对象
							Curriculum curriculum = findSelectLesson(curriculumid);
							SelectPracticeLesson.setCurriculum(curriculum);
							SelectPracticeLesson.setHoursOfOutExp(kewaishiyan);
							SelectPracticeLesson.setHoursOfOutCom(kewaishangji);
							SelectPracticeLesson.setXueqi(xueqi);
							SelectPracticeLesson.setQizhizhou(qizhizhou);
							SelectPracticeLesson.setZuzhixingshi(zuzhixingshi);
							SelectPracticeLesson.setIsxueweike(isxueweike);
							SelectPracticeLesson.setIdallpracticeLesson(jizhongshijian);
							SelectPracticeLesson.setBeizhu(beizhu);				
							SelectPracticeLesson.setDepartment(department);
							Professional professional = findprofessionalById(professionalId);
							SelectPracticeLesson.setProfessional(professional);
							addSelectLesson(SelectPracticeLesson);	//插入实践课				
						}
						else{
							JSONArray proArr = jsonObj.getJSONArray("zhuanyefangxiang");
							int proSize = proArr.length();
							for(int j = 0; j < proSize; j++) {
								PracticeLesson SelectPracticeLesson = new PracticeLesson();	//声明对象
								Curriculum curriculum = findSelectLesson(curriculumid);
								SelectPracticeLesson.setCurriculum(curriculum);
								SelectPracticeLesson.setHoursOfOutExp(kewaishiyan);
								SelectPracticeLesson.setHoursOfOutCom(kewaishangji);
								SelectPracticeLesson.setXueqi(xueqi);
								SelectPracticeLesson.setQizhizhou(qizhizhou);
								SelectPracticeLesson.setZuzhixingshi(zuzhixingshi);
								SelectPracticeLesson.setIsxueweike(isxueweike);
								SelectPracticeLesson.setIdallpracticeLesson(jizhongshijian);
								SelectPracticeLesson.setBeizhu(beizhu);				
								SelectPracticeLesson.setDepartment(department);
								Professional professional = findprofessionalById(proArr.get(j).toString());
								SelectPracticeLesson.setProfessional(professional);
								addSelectLesson(SelectPracticeLesson);	//插入实践课				
							}
						
						}
					}
				}
			catch (JSONException e) {
				e.printStackTrace();
			}			
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};		
	}
	
	//下载实践课培养计划
	public void downPracticePlan(PracticeLesson practiceLesson) {
		try{
			List<PracticeLesson> practiceLessonList = practicePlanDao.findSelePraLessonByDepart(practiceLesson.getDepartment().getDepartmentid(),practiceLesson.getProfessional().getProfessionalid());			
				HttpServletResponse response = ServletActionContext.getResponse();  
				Department department = departmentDao.get(practiceLesson.getDepartment().getDepartmentid());
				Professional professional = professionalDao.get(practiceLesson.getProfessional().getProfessionalid());
	            //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	            if(practiceLesson.getProfessional().getProfessionalid().equals("-1")){
	            	response.setHeader("Content-Disposition", "attachment;filename="+new String((department.getDepartmentCname()+"专业实践课.xls").getBytes("gb2312"),"ISO-8859-1") );
	            }
	            else{
	            	response.setHeader("Content-Disposition", "attachment;filename="+new String((department.getDepartmentCname()+"专业"+professional.getProfessionalname()+"方向实践课.xls").getBytes("gb2312"),"ISO-8859-1") );
	            }
	            OutputStream outputStream = response.getOutputStream();
	            if(practiceLessonList != null){
	            	ExportPracticePlanExcel(practiceLessonList,outputStream);
	            }else{
	            	ExportsjExcel(outputStream);
	            }
	            if(outputStream != null){
	            	outputStream.close();  
	            }
			
		} catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}
	
	
	//下载全部实践课培养计划
	public void downPracticePlan_all() {
		try{
			
			HttpServletResponse response = ServletActionContext.getResponse();  
            //2.1 设置响应类型  
            response.setContentType("application/x-execl");  
            //2.2 设置以下载方式打开文件  
            response.setHeader("Content-Disposition", "attachment;filename="+new String(("全校所选实践课.xls").getBytes("gb2312"),"ISO-8859-1") );
            OutputStream outputStream = response.getOutputStream();
            
            HSSFWorkbook wb = new HSSFWorkbook();  
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
            HSSFSheet sheet = wb.createSheet("sheet1");  
            sheet.setColumnWidth((short) 0, (short) 3500);
            sheet.setColumnWidth((short) 1, (short) 3000);
            sheet.setColumnWidth((short) 2, (short) 2300);
            sheet.setColumnWidth((short) 3, (short) 3500);
            sheet.setColumnWidth((short) 4, (short) 3000);
            sheet.setColumnWidth((short) 5, (short) 6000);
            sheet.setColumnWidth((short) 6, (short) 6500);
            sheet.setColumnWidth((short) 7, (short) 1500);
            sheet.setColumnWidth((short) 8, (short) 2000);
            sheet.setColumnWidth((short) 9, (short) 1500);
            sheet.setColumnWidth((short) 10, (short) 2300);
            sheet.setColumnWidth((short) 11, (short) 3000);
            sheet.setColumnWidth((short) 12, (short) 2300);
            sheet.setColumnWidth((short) 13, (short) 3500);
            sheet.setColumnWidth((short) 14, (short) 4500);
            sheet.setColumnWidth((short) 15, (short) 5000);
            sheet.setColumnWidth((short) 16, (short) 5000);
            sheet.setColumnWidth((short) 17, (short) 5000);
            
            HSSFRow row = sheet.createRow((int) 0);  
            // 第四步，创建单元格，并设置值表头 设置表头居中  
            HSSFCellStyle style1 = wb.createCellStyle();  
            style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
            style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
            style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
            style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
            style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
            
            HSSFCell cell = row.createCell((short) 0);  
            cell.setCellValue("课程平台");  
            cell.setCellStyle(style1);  
      
            cell = row.createCell((short) 1);  
            cell.setCellValue("课程性质");  
            cell.setCellStyle(style1);  
      
            cell = row.createCell((short) 2);  
            cell.setCellValue("课程类别");  
            cell.setCellStyle(style1);  
            
            cell = row.createCell((short) 3);  
            cell.setCellValue("课程归属");  
            cell.setCellStyle(style1);  
            
            cell = row.createCell((short) 4);  
            cell.setCellValue("课程编码");  
            cell.setCellStyle(style1); 
            
            cell = row.createCell((short) 5);  
            cell.setCellValue("课程名称");  
            cell.setCellStyle(style1); 
            
            cell = row.createCell((short) 6);  
            cell.setCellValue("英文课程名称");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 7);  
            cell.setCellValue("学分");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 8);  
            cell.setCellValue("周学时");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 9);  
            cell.setCellValue("学期");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 10);  
            cell.setCellValue("起止周");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 11);  
            cell.setCellValue("专业方向");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 12);  
            cell.setCellValue("组织形式");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 13);  
            cell.setCellValue("是否集中实践");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 14);  
	        cell.setCellValue("授课学院");  
	        cell.setCellStyle(style1);
            
            cell = row.createCell((short) 15);  
            cell.setCellValue("备注");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 16);  
            cell.setCellValue("选课学院");  
            cell.setCellStyle(style1);
            
            cell = row.createCell((short) 17);  
            cell.setCellValue("选课专业");  
            cell.setCellStyle(style1);
			
			int flag = 0;
			List<College> collegeList = collegeDao.findAll();
			if(collegeList != null && collegeList.size() !=0)
			{
				for(int m=0;m<collegeList.size();m++)
				{
					List<Department> departmentList = departmentDao.findTbyCollege(collegeList.get(m));
					
					if(departmentList != null && departmentList.size() != 0)
					{
						for(int k=0;k<departmentList.size();k++)
						{
							List<Professional> professionalList = professionalDao.findbydepartment(departmentList.get(k));				
							if(professionalList != null && professionalList.size() !=0 )
							{
								for(int j=0;j<professionalList.size();j++)
								{
									List<PracticeLesson> practiceLessonList = practicePlanDao.findSelePraLessonByDepart(departmentList.get(k).getDepartmentid(),professionalList.get(j).getProfessionalid());
									
									 for(int i=0;i<practiceLessonList.size();i++)
									 {
								    	row = sheet.createRow(flag+i+1);
								        
								    	PracticeLesson practiceLesson = practiceLessonList.get(i);
								        cell = row.createCell((short) 0);  
								        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumpingtai());  
								        cell.setCellStyle(style1);  
								  
								        cell = row.createCell((short) 1);  
								        cell.setCellValue(practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename());  
								        cell.setCellStyle(style1);  
								  
								        cell = row.createCell((short) 2);  
								        cell.setCellValue(practiceLesson.getCurriculum().getCourseLei());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 3);  
								        cell.setCellValue(practiceLesson.getCurriculum().getCourseCategory());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 4);  
								        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumid());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 5);  
								        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumCname());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 6);  
								        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumEname());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 7);  
								        cell.setCellValue(practiceLesson.getCurriculum().getCredit());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 8);  
								        cell.setCellValue(practiceLesson.getCurriculum().getHoursOfWeek());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 9);  
								        cell.setCellValue(practiceLesson.getXueqi());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 10);  
								        cell.setCellValue(practiceLesson.getQizhizhou());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 11);  
								        if(practiceLesson.getProfessional() != null)
								        {
								        	cell.setCellValue(practiceLesson.getProfessional().getProfessionalname());
								        }
								        else
								        {
								        	cell.setCellValue("不区分");
								        }
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 12);  
								        cell.setCellValue(practiceLesson.getZuzhixingshi());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 13);  
								        cell.setCellValue(practiceLesson.getIdallpracticeLesson());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 14);  
								        cell.setCellValue(practiceLesson.getCurriculum().getCollege().getCollegeCname());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 15);  
								        cell.setCellValue(practiceLesson.getBeizhu());  
								        cell.setCellStyle(style1); 
								        
							        	cell = row.createCell((short) 16);  
									    cell.setCellValue(professionalList.get(j).getDepartment().getCollege().getCollegeCname());  
									    cell.setCellStyle(style1); 
									        
									    cell = row.createCell((short) 17);  
									    cell.setCellValue(professionalList.get(j).getDepartment().getDepartmentCname());  
									    cell.setCellStyle(style1);
								       
									    }
									
									 flag = flag + practiceLessonList.size();
								}					
							}
							else
							{
								List<PracticeLesson> practiceLessonList = practicePlanDao.findSelePraLessonByDepart(departmentList.get(k).getDepartmentid(),"-1");
								 
								for(int i=0;i<practiceLessonList.size();i++)
								{
							    	row = sheet.createRow(flag+i+1);
							        
							    	PracticeLesson practiceLesson = practiceLessonList.get(i);
							        cell = row.createCell((short) 0);  
							        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumpingtai());  
							        cell.setCellStyle(style1);  
							  
							        cell = row.createCell((short) 1);  
							        cell.setCellValue(practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename());  
							        cell.setCellStyle(style1);  
							  
							        cell = row.createCell((short) 2);  
							        cell.setCellValue(practiceLesson.getCurriculum().getCourseLei());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 3);  
							        cell.setCellValue(practiceLesson.getCurriculum().getCourseCategory());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 4);  
							        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumid());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 5);  
							        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumCname());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 6);  
							        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumEname());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 7);  
							        cell.setCellValue(practiceLesson.getCurriculum().getCredit());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 8);  
							        cell.setCellValue(practiceLesson.getCurriculum().getHoursOfWeek());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 9);  
							        cell.setCellValue(practiceLesson.getXueqi());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 10);  
							        cell.setCellValue(practiceLesson.getQizhizhou());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 11);  
							        if(practiceLesson.getProfessional() != null)
							        {
							        	cell.setCellValue(practiceLesson.getProfessional().getProfessionalname());
							        }
							        else
							        {
							        	cell.setCellValue("不区分");
							        }
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 12);  
							        cell.setCellValue(practiceLesson.getZuzhixingshi());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 13);  
							        cell.setCellValue(practiceLesson.getIdallpracticeLesson());  
							        cell.setCellStyle(style1); 
							        
							        cell = row.createCell((short) 14);  
							        cell.setCellValue(practiceLesson.getCurriculum().getCollege().getCollegeCname());  
							        cell.setCellStyle(style1);
							        
							        cell = row.createCell((short) 15);  
							        cell.setCellValue(practiceLesson.getBeizhu());  
							        cell.setCellStyle(style1); 
							        
						        	cell = row.createCell((short) 16);  
								    cell.setCellValue(departmentList.get(k).getCollege().getCollegeCname());  
								    cell.setCellStyle(style1); 
								        
								    cell = row.createCell((short) 17);  
								    cell.setCellValue(departmentList.get(k).getDepartmentCname());  
								    cell.setCellStyle(style1);
							       
								  }
								
								flag = flag + practiceLessonList.size();
							}
						}								
					}
				}
			}
					
					
			 wb.write(outputStream);
		     outputStream.flush();  
		     outputStream.close();
		     
            if(outputStream != null){
            	outputStream.close();  
            }
			
		} catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}
	
	
	private void ExportPracticePlanExcel(List<PracticeLesson> practiceLessonList, OutputStream outputStream) throws IOException{	//生成实践课计划
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("sheet1");  
        sheet.setColumnWidth((short) 0, (short) 3500);
        sheet.setColumnWidth((short) 1, (short) 3000);
        sheet.setColumnWidth((short) 2, (short) 2300);
        sheet.setColumnWidth((short) 3, (short) 3500);
        sheet.setColumnWidth((short) 4, (short) 3000);
        sheet.setColumnWidth((short) 5, (short) 6000);
        sheet.setColumnWidth((short) 6, (short) 6500);
        sheet.setColumnWidth((short) 7, (short) 1500);
        sheet.setColumnWidth((short) 8, (short) 2000);
        sheet.setColumnWidth((short) 9, (short) 1500);
        sheet.setColumnWidth((short) 10, (short) 2300);
        sheet.setColumnWidth((short) 11, (short) 3000);
        sheet.setColumnWidth((short) 12, (short) 2300);
        sheet.setColumnWidth((short) 13, (short) 3500);
        sheet.setColumnWidth((short) 14, (short) 4500);
        sheet.setColumnWidth((short) 15, (short) 5000);
        
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("课程平台");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("课程性质");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("课程类别");  
        cell.setCellStyle(style1);  
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("课程归属");  
        cell.setCellStyle(style1);  
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("课程编码");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("课程名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("英文课程名称");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("学分");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("周学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("学期");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("起止周");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("专业方向");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("组织形式");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("是否集中实践");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("授课学院");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 15);  
        cell.setCellValue("备注");  
        cell.setCellStyle(style1);
        
        
        for(int i=0;i<practiceLessonList.size();i++){
	    	row = sheet.createRow((int) i+1);
	        
	    	PracticeLesson practiceLesson = practiceLessonList.get(i);
	        cell = row.createCell((short) 0);  
	        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumpingtai());  
	        cell.setCellStyle(style1);  
	  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue(practiceLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename());  
	        cell.setCellStyle(style1);  
	  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue(practiceLesson.getCurriculum().getCourseLei());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 3);  
	        cell.setCellValue(practiceLesson.getCurriculum().getCourseCategory());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 4);  
	        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumid());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 5);  
	        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumCname());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 6);  
	        cell.setCellValue(practiceLesson.getCurriculum().getCurriculumEname());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 7);  
	        cell.setCellValue(practiceLesson.getCurriculum().getCredit());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 8);  
	        cell.setCellValue(practiceLesson.getCurriculum().getHoursOfWeek());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 9);  
	        cell.setCellValue(practiceLesson.getXueqi());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 10);  
	        cell.setCellValue(practiceLesson.getQizhizhou());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 11);  
	        if(practiceLesson.getProfessional() != null)
	        {
	        	cell.setCellValue(practiceLesson.getProfessional().getProfessionalname());
	        }
	        else
	        {
	        	cell.setCellValue("不区分");
	        }
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 12);  
	        cell.setCellValue(practiceLesson.getZuzhixingshi());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 13);  
	        cell.setCellValue(practiceLesson.getIdallpracticeLesson());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 14);  
	        cell.setCellValue(practiceLesson.getCurriculum().getCollege().getCollegeCname());  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 15);  
	        cell.setCellValue(practiceLesson.getBeizhu());  
	        cell.setCellStyle(style1); 
	        
	    }
        
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}

	// 多条件查询集中实践课
	public List<PracticeLesson> findPracticeLesson(PracticeLesson practiceLesson) {
		return practicePlanDao.findPracticeLesson(practiceLesson);
	}

	//多条件查询课程
	public List<Curriculum> findCurriculum(PracticeLesson practiceLesson) {
		return practicePlanDao.findCurriculum(practiceLesson);
	}

	//根据系id查询系
	public Department finddepartment(String departmentID) {
		return departmentDao.get(departmentID);
	}

	//根据课程id查询课程
	public Curriculum findSelectLesson(String curriculumId) {
		return curriculumDao.get(curriculumId);
	}

	//插入选定的实践课
	public void addSelectLesson(PracticeLesson selectPracticeLesson) {
		practicePlanDao.addSelectLesson(selectPracticeLesson);		
	}
	
	//删除实践课
	public void delPracticeLessonByid(Integer practiceLessonid) {
		PracticeLesson practiceLesson = practicePlanDao.get(practiceLessonid);
		practicePlanDao.delete(practiceLesson);		
		ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
	}
	//删除多个实践课
	public void delsjkMore(List<String> deletepracticeLessonList) {
		if(deletepracticeLessonList != null && deletepracticeLessonList.size() !=0)
		{
			PracticeLesson practiceLesson = practicePlanDao.get(Integer.valueOf(deletepracticeLessonList.get(0)));
			ServletActionContext.getRequest().setAttribute("departmentid", practiceLesson.getDepartment().getDepartmentid());
			ServletActionContext.getRequest().setAttribute("collegeid", practiceLesson.getDepartment().getCollege().getCollegeid());	
			
			for(int i=0;i<deletepracticeLessonList.size();i++)
			{
				PracticeLesson newpracticeLesson = practicePlanDao.get(Integer.valueOf(deletepracticeLessonList.get(i)));
				if(newpracticeLesson != null)
				{
					practicePlanDao.delete(newpracticeLesson);
				}
			}
			ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
		}
		
	}

	//根据实践课id查询实践课
	public PracticeLesson findPracticeLessonByid(Integer practiceLessonid) {
		return practicePlanDao.get(practiceLessonid);
	}

	//编辑实践课
	public void sjkUpdate(PracticeLesson practiceLesson) {
		PracticeLesson newpracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
		newpracticeLesson.setHoursOfOutExp(practiceLesson.getHoursOfOutExp());
		newpracticeLesson.setHoursOfOutCom(practiceLesson.getHoursOfOutCom());
		newpracticeLesson.setXueqi(practiceLesson.getXueqi());
		newpracticeLesson.setQizhizhou(practiceLesson.getQizhizhou());
		newpracticeLesson.setZuzhixingshi(practiceLesson.getZuzhixingshi());
		newpracticeLesson.setIsxueweike(practiceLesson.getIsxueweike());
		newpracticeLesson.setIdallpracticeLesson(practiceLesson.getIdallpracticeLesson());
		newpracticeLesson.setBeizhu(practiceLesson.getBeizhu());
		practicePlanDao.update(newpracticeLesson);
		ServletActionContext.getRequest().setAttribute("msg", "修改成功！");
	}

	//查询所有实践课，用于统计学分
	public List<PracticeLesson> findAllPracticeLessonList() {
		return practicePlanDao.findAll();
	}

	//根据用户id查找用户
	public User findUserById(String tnum) {
		return userDao.get(tnum);
	}

	//根据学院id获得学院
	public List<College> findCollegeById(String collegeid) {
		return collegeDao.getone(collegeid);
	}

	//根据系id获得系
	public List<Department> findDepartmentById(String departmentid) {
		return departmentDao.getone(departmentid);
	}

	//查询对应系的全部实践课
	public List<PracticeLesson> findpracticeLessonDepartment(
			Department department) {
		return practicePlanDao.findpracticeLessonDepartment(department);
	}

	//根据课程id查询课程
	public Curriculum findSeleceLesson(String curriculumid) {
		return curriculumDao.get(curriculumid);
	}

	//删除对应的实践课
	public void delete(PracticeLesson practiceLesson) {
		practicePlanDao.delete(practiceLesson);
	}

	//查询所有实践课	
	public List<Curriculum> findAllPracticeLesson(College college,Department department) {
		return practicePlanDao.findAllPracticeLesson(college,department);
	}

	public void downPracticeTemplate(){	//下载实践课模板
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("集中实践课模板.xls".getBytes(),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportsjExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}
	private void ExportsjExcel(OutputStream outputStream) throws IOException{//生成实践课Excel
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	    HSSFSheet sheet = wb.createSheet("sheet1");  
        sheet.setColumnWidth((short) 0, (short) 3500);
        sheet.setColumnWidth((short) 1, (short) 3000);
        sheet.setColumnWidth((short) 2, (short) 2300);
        sheet.setColumnWidth((short) 3, (short) 3500);
        sheet.setColumnWidth((short) 4, (short) 3000);
        sheet.setColumnWidth((short) 5, (short) 6000);
        sheet.setColumnWidth((short) 6, (short) 6500);
        sheet.setColumnWidth((short) 7, (short) 1500);
        sheet.setColumnWidth((short) 8, (short) 2000);
        sheet.setColumnWidth((short) 9, (short) 1500);
        sheet.setColumnWidth((short) 10, (short) 2300);
        sheet.setColumnWidth((short) 11, (short) 3000);
        sheet.setColumnWidth((short) 12, (short) 2300);
        sheet.setColumnWidth((short) 13, (short) 3500);
        sheet.setColumnWidth((short) 14, (short) 4500);
        sheet.setColumnWidth((short) 15, (short) 5000);
        
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("课程平台");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("课程性质");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("课程类别");  
        cell.setCellStyle(style1);  
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("课程归属");  
        cell.setCellStyle(style1);  
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("课程编码");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 5);  
        cell.setCellValue("课程名称");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 6);  
        cell.setCellValue("英文课程名称");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 7);  
        cell.setCellValue("学分");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 8);  
        cell.setCellValue("周学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("学期");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("起止周");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("专业方向");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("组织形式");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("是否集中实践");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("授课学院");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 15);  
        cell.setCellValue("备注");  
        cell.setCellStyle(style1);
                
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	
	

	public void toPracLesPage(PracticeLesson practiceLesson) {
		/*User user = userDao.get(practiceLesson.getTeacher().getTnum());
		List[] practiceLessonlist = practicePlanDao.getbyuser(user);
		ServletActionContext.getRequest().setAttribute("practiceLessonlist1", practiceLessonlist[0]);	
		ServletActionContext.getRequest().setAttribute("practiceLessonlist2", practiceLessonlist[1]);
		ServletActionContext.getRequest().setAttribute("practiceLessonlist3", practiceLessonlist[2]);
		ServletActionContext.getRequest().setAttribute("practiceLessonlist4", practiceLessonlist[3]);
*/	}
	

	
	
	
	public PageBean findtheolen(Integer currentpage,
			PracticeLesson practiceLesson, String departmenttag) {
		PageBean pageBean=new PageBean();
		//在pageBean中设置college使得条件查询可执行
		List<College> listcollege=new ArrayList<College>();
		College college=findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid()).get(0);
		listcollege.add(college);
		pageBean.setCollegelist(listcollege);
		//找到该学院所开课程在理论课列表中的所有相关记录
		List<List<PracticeLesson>> theolenlist1=findtheolenlist(practiceLesson,departmenttag);
		List<List<PracticeLesson>> theolenlist=new ArrayList<List<PracticeLesson>>();
		
		//根据条件进行判断
		//只有学院的时候显示所有
		if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
			theolenlist=theolenlist1;
		}else{
		//当搜索条件添加了课程名称时显示相关的记录
			if(departmenttag.equals("1")){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getTeachDepartment()!=null){
						if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
						{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
						{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
						}
						else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
								{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
											theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
									{
										theolenlist.add(theolenlist1.get(i));
									}
								}
						else
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
					
				}
			}
			else if(departmenttag.equals("-1"))
			{
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getTeachDepartment()==null){
						if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
									theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else{
							theolenlist.add(theolenlist1.get(i));
						}
					}	
				}
		}
		else{
			if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&
					practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
							.equals(practiceLesson.getCurriculum().getCurriculumid())&&
							theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())){
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else{
				if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())
						&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
								.equals(practiceLesson.getCurriculum().getCurriculumCname())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else if(practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())
						&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
								.equals(practiceLesson.getCurriculum().getCurriculumid())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
			}
		}
		
		}

		int totalCount=theolenlist.size();
		pageBean.setTotalCount(totalCount);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		int begin = (currentpage-1)*pageSize;
		
		//为防止溢出 还需加一个约束条件？？？？？？？？？？
		List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
		if(currentpage != totalPage){
			sslist=theolenlist.subList(begin, (begin+pageSize));
		}
		else{
			if((begin>=0)&&(begin!=totalCount)){
				sslist=theolenlist.subList(begin,totalCount);
			}
		}
	
		List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
		
		for(int i=0;i<sslist.size();i++){
			curriculumlist.add(sslist.get(i).get(0).getCurriculum());
		}
		
		//System.out.println("************"+curriculumlist.get(0).getCurriculumEname());
		
		pageBean.setCurriculumlist(curriculumlist);
		pageBean.setPracticeLessonlist(sslist);
		return pageBean;
	}
	public List<List<PracticeLesson>> findtheolenlist(PracticeLesson practiceLesson, String departmenttag){
		//总的分页理论课列表
				List<List<PracticeLesson>> theolen=new ArrayList<List<PracticeLesson>>();
				//找到学院开设的课程
				List<Curriculum> list=practicePlanDao.findcurrbycollege(practiceLesson.getCurriculum().getCollege());
				//List<Curriculum> listcurr=new ArrayList<Curriculum>();
				for(int i=0;i<list.size();i++){
					//找到了课1对应的列表
					List<PracticeLesson> theolenlist=practicePlanDao.findtheolenbycurr(list.get(i));
					List<PracticeLesson> theolenlist1=new ArrayList<PracticeLesson>();
					List<PracticeLesson> theolenlist2=new ArrayList<PracticeLesson>();
					//遍历课1的列表
					for(int j=0;j<theolenlist.size();j++){
						if(theolenlist.get(j).getTeachDepartment()!=null ||(theolenlist.get(j).getTeachDepartment()==null && theolenlist.get(j).getTeacher()!=null)){	
							//已分配课1放到theolenlist1
							theolenlist1.add(theolenlist.get(j));
						}
						else{
							//未分配课1放到theolenlist2
							//theolenlist.get(j).setDepartmenttag("-1");
							//this.getHibernateTemplate().update(theolenlist.get(j));
							theolenlist2.add(theolenlist.get(j));
						}
					}
					if(theolenlist1.size()!=0){
						theolen.add(theolenlist1);
					}
					if(theolenlist2.size()!=0){
						theolen.add(theolenlist2);
					}
				}
				//System.out.println(theolen.get(0).get(0).getCurriculum().getCurriculumCname());
				
				return theolen;
		
	}
	public Curriculum findcurrbyId(String curriculumid) {
		return practicePlanDao.findcurrbyId(curriculumid);
	}
	public List<Department> finddepartbycollege(College college) {
		return practicePlanDao.finddepartbycollege(college);
	}
	public List<Professional> findchoosedepartbydepartId(List<String> depart) {
		List<Professional> choosedepartlist=new ArrayList<Professional>();
		for(int i=0;i<depart.size();i++){
			Professional professional=new Professional();
			System.out.println("123456");
			if((professionalDao.get(depart.get(i)))!=null)
			{
				choosedepartlist.add(professionalDao.get(depart.get(i)));
			}
			else
			{
				Department department=departmentDao.get(depart.get(i));
				professional.setDepartment(department);
				choosedepartlist.add(professional);
			}
		}
		return choosedepartlist;
	}
	public void goupdatedepartment(PracticeLesson practiceLesson,
			List<String> newchoosedepartlist) {
		PracticeLesson practiceLen=new PracticeLesson(); 
		for(int i=0;i<newchoosedepartlist.size();i++){
			Curriculum curriculum=curriculumDao.get(practiceLesson.getCurriculum().getCurriculumid());
			if((professionalDao.get(newchoosedepartlist.get(i)))!=null)
			{
				practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(newchoosedepartlist.get(i)));
			}
			else
			{
				Department department=departmentDao.get(newchoosedepartlist.get(i));
				practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,department);
			}
			if(practiceLen.getTeachDepartment() == null ||(practiceLen.getTeachDepartment().getDepartmentid()!=(practiceLesson.getTeachDepartment().getDepartmentid()))){
				practiceLen.setTeachDepartment(departmentDao.get(practiceLesson.getTeachDepartment().getDepartmentid()));
				practiceLen.setTeacher(null);
			}
			practicePlanDao.update(practiceLen);
		}
	}
	public PageBean findtheolendirectuser(Integer currentpage,
			PracticeLesson practiceLesson, String departmenttag) {
			PageBean pageBean=new PageBean();
			//在pageBean中设置college使得条件查询可执行
			List<College> listcollege=new ArrayList<College>();
			College college=findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid()).get(0);
			listcollege.add(college);
			pageBean.setCollegelist(listcollege);
			
			List<List<PracticeLesson>> theolenlist1=findtheolenlistdirectuser(practiceLesson,departmenttag);
			List<List<PracticeLesson>> theolenlist=new ArrayList<List<PracticeLesson>>();
				
			//根据条件进行判断
			//只有学院的时候显示所有
			if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
				theolenlist=theolenlist1;
			}else{
			//当搜索条件添加了课程名称时显示相关的记录
				if(departmenttag.equals("1")){
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getTeacher()!=null){
							if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										theolenlist.add(theolenlist1.get(i));
									}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
							else
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
						
					}
				}
				else if(departmenttag.equals("-1"))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getTeacher()==null){
							if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else{
								theolenlist.add(theolenlist1.get(i));
							}
						}	
					}
			}
			else{
				if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&
						practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())){
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
								.equals(practiceLesson.getCurriculum().getCurriculumid())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else{
					if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())
							&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
					{
						for(int i=0;i<theolenlist1.size();i++){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
									.equals(practiceLesson.getCurriculum().getCurriculumCname())){
								theolenlist.add(theolenlist1.get(i));
							}	
						}
					}
					else if(practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())
							&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
					{
						for(int i=0;i<theolenlist1.size();i++){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
									.equals(practiceLesson.getCurriculum().getCurriculumid())){
								theolenlist.add(theolenlist1.get(i));
							}	
						}
					}
				}
			}
			
			}

			int totalCount=theolenlist.size();
			pageBean.setTotalCount(totalCount);
			int pageSize = 10;
			pageBean.setPageSize(pageSize);
			int totalPage = 0;
			if(totalCount%pageSize == 0){
				totalPage = totalCount/pageSize;
			}else{
				totalPage = totalCount/pageSize+1;
			}
			pageBean.setTotalPage(totalPage);
			if(currentpage < 1){
				currentpage = 1;
			}
			if(currentpage > totalPage){
				currentpage = totalPage;
			}
			pageBean.setCurrentpage(currentpage);
			int begin = (currentpage-1)*pageSize;
			
			//为防止溢出 还需加一个约束条件？？？？？？？？？？
			List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
			if(currentpage != totalPage){
				sslist=theolenlist.subList(begin, (begin+pageSize));
			}
			else{
				if((begin>=0)&&(begin!=totalCount)){
					sslist=theolenlist.subList(begin,totalCount);
				}
			}
		
			List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
			
			for(int i=0;i<sslist.size();i++){
				curriculumlist.add(sslist.get(i).get(0).getCurriculum());
			}
			
			//System.out.println("************"+curriculumlist.get(0).getCurriculumEname());
			
			pageBean.setCurriculumlist(curriculumlist);
			pageBean.setPracticeLessonlist(sslist);
			return pageBean;
		}
		public List<List<PracticeLesson>> findtheolenlistdirectuser(PracticeLesson practiceLesson, String departmenttag)
		{
			List<List<PracticeLesson>> theolen=new ArrayList<List<PracticeLesson>>();
			//找到学院开设的课程
			List<Curriculum> list=practicePlanDao.findcurrbycollege(practiceLesson.getCurriculum().getCollege());
			
			for(int i=0;i<list.size();i++){
				//找到了课1对应的列表
				List<PracticeLesson> theolenlist=practicePlanDao.findtheolenbycurr(list.get(i));
				List<PracticeLesson> theolenlist1=new ArrayList<PracticeLesson>();
				List<PracticeLesson> theolenlist2=new ArrayList<PracticeLesson>();
				//遍历课1的列表
				for(int j=0;j<theolenlist.size();j++){
					if(theolenlist.get(j).getTeacher()!=null){	
						//已分配课1放到theolenlist1
						theolenlist1.add(theolenlist.get(j));
					}
					else{
						//未分配课1放到theolenlist2
						//theolenlist.get(j).setDepartmenttag("-1");
						//this.getHibernateTemplate().update(theolenlist.get(j));
						theolenlist2.add(theolenlist.get(j));
					}
				}
				if(theolenlist1.size()!=0){
					theolen.add(theolenlist1);
				}
				if(theolenlist2.size()!=0){
					theolen.add(theolenlist2);
				}
			}
			//System.out.println(theolen.get(0).get(0).getCurriculum().getCurriculumCname());
			return theolen;
			
		}
		public PageBean findUserByCollege(Integer currentpage,PracticeLesson practiceLesson, College college) {
			PageBean pageBean=new PageBean();
			List<User> userlist=new ArrayList<User>();
			List<User> userlist1=userDao.findUserByCollege(college);
			if(practiceLesson.getTeacher() != null)
			{	
				if(practiceLesson.getTeacher().getTnum() != null && !"".equals(practiceLesson.getTeacher().getTnum()) && practiceLesson.getTeacher().getUsername() != null && !"".equals(practiceLesson.getTeacher().getUsername()))
				{
					for(int i=0;i<userlist1.size();i++){
						if(userlist1.get(i).getTnum().equals(practiceLesson.getTeacher().getTnum())&&
								userlist1.get(i).getUsername().equals(practiceLesson.getTeacher().getUsername()))
						{
							userlist.add(userlist1.get(i));
						}
					}
				}
				else if(!"".equals(practiceLesson.getTeacher().getTnum()) && "".equals(practiceLesson.getTeacher().getUsername()))
				{
					for(int i=0;i<userlist1.size();i++){
						if(userlist1.get(i).getTnum().equals(practiceLesson.getTeacher().getTnum()))
						{
							userlist.add(userlist1.get(i));
						}
					}
				}
				else if("".equals(practiceLesson.getTeacher().getTnum()) && !"".equals(practiceLesson.getTeacher().getUsername()))
				{
					for(int i=0;i<userlist1.size();i++){
						if(userlist1.get(i).getUsername().equals(practiceLesson.getTeacher().getUsername()))
						{
							userlist.add(userlist1.get(i));
						}
					}
				}
				else
				{
					userlist=userlist1;
				}
			}
			else
			{
				userlist=userlist1;
			}
			int totalCount=userlist.size();
			pageBean.setTotalCount(totalCount);
			int pageSize = 10;
			pageBean.setPageSize(pageSize);
			int totalPage = 0;
			if(totalCount%pageSize == 0){
				totalPage = totalCount/pageSize;
			}else{
				totalPage = totalCount/pageSize+1;
			}
			pageBean.setTotalPage(totalPage);
			if(currentpage < 1){
				currentpage = 1;
			}
			if(currentpage > totalPage){
				currentpage = totalPage;
			}
			pageBean.setCurrentpage(currentpage);
			int begin = (currentpage-1)*pageSize;
			
			//为防止溢出 还需加一个约束条件？？？？？？？？？？
			List<User> sslist=new ArrayList<User>();
			if(currentpage != totalPage){
				sslist=userlist.subList(begin, (begin+pageSize));
			}
			else{
				if((begin>=0)&&(begin!=totalCount)){
					sslist=userlist.subList(begin,totalCount);
				}
			}
			pageBean.setUserlist(sslist);
			return pageBean;
		}
		public void updateTheolenByCollegeDirectUser(PracticeLesson practiceLesson, List<String> depart) 
		{
			PracticeLesson practiceLen=new PracticeLesson(); 
			for(int i=0;i<depart.size();i++){
				Curriculum curriculum=curriculumDao.get(practiceLesson.getCurriculum().getCurriculumid());
				if((professionalDao.get(depart.get(i)))!=null)
				{
					practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
				}
				else
				{
					Department department=departmentDao.get(depart.get(i));
					practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,department);
				}
				practiceLen.setTeachDepartment(null);
				practiceLen.setTeacher(userDao.get(practiceLesson.getTeacher().getTnum()));
				practicePlanDao.update(practiceLen);
			}
		}
		public PageBean findtheolenByDepartToUser(Integer currentpage,PracticeLesson practiceLesson, String usertag) {
			PageBean pageBean=new PageBean();
			//在pageBean中设置college使得条件查询可执行
			List<College> listcollege=new ArrayList<College>();
			College college=findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid()).get(0);
			listcollege.add(college);
			pageBean.setCollegelist(listcollege);
			
			
			List<Department> listdepart=new ArrayList<Department>();
			listdepart.add(departmentDao.get(practiceLesson.getTeachDepartment().getDepartmentid()));
			pageBean.setDepartmentlist(listdepart);
			
			practiceLesson.setTeachDepartment(listdepart.get(0));
			
			List<List<PracticeLesson>> theolenlist1=findtheolenlistByDepartUser(practiceLesson,usertag);
			List<List<PracticeLesson>> theolenlist=new ArrayList<List<PracticeLesson>>();
				
			//根据条件进行判断
			//只有学院的时候显示所有
			if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())&&usertag.equals("0")){
				theolenlist=theolenlist1;
			}else{
			//当搜索条件添加了课程名称时显示相关的记录
				if(usertag.equals("1")){
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getTeacher()!=null){
							if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										theolenlist.add(theolenlist1.get(i));
									}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
							else
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
						
					}
				}
				else if(usertag.equals("-1"))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getTeacher()==null){
							if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else{
								theolenlist.add(theolenlist1.get(i));
							}
						}	
					}
			}
			else{
				if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&
						practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())){
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
								.equals(practiceLesson.getCurriculum().getCurriculumid())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else{
					if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())
							&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
					{
						for(int i=0;i<theolenlist1.size();i++){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
									.equals(practiceLesson.getCurriculum().getCurriculumCname())){
								theolenlist.add(theolenlist1.get(i));
							}	
						}
					}
					else if(practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())
							&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
					{
						for(int i=0;i<theolenlist1.size();i++){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
									.equals(practiceLesson.getCurriculum().getCurriculumid())){
								theolenlist.add(theolenlist1.get(i));
							}	
						}
					}
				}
			}
			
			}

			int totalCount=theolenlist.size();
			pageBean.setTotalCount(totalCount);
			int pageSize = 10;
			pageBean.setPageSize(pageSize);
			int totalPage = 0;
			if(totalCount%pageSize == 0){
				totalPage = totalCount/pageSize;
			}else{
				totalPage = totalCount/pageSize+1;
			}
			pageBean.setTotalPage(totalPage);
			if(currentpage < 1){
				currentpage = 1;
			}
			if(currentpage > totalPage){
				currentpage = totalPage;
			}
			pageBean.setCurrentpage(currentpage);
			int begin = (currentpage-1)*pageSize;
			
			//为防止溢出 还需加一个约束条件？？？？？？？？？？
			List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
			if(currentpage != totalPage){
				sslist=theolenlist.subList(begin, (begin+pageSize));
			}
			else{
				if((begin>=0)&&(begin!=totalCount)){
					sslist=theolenlist.subList(begin,totalCount);
				}
			}
		
			List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
			
			for(int i=0;i<sslist.size();i++){
				curriculumlist.add(sslist.get(i).get(0).getCurriculum());
			}
			
			//System.out.println("************"+curriculumlist.get(0).getCurriculumEname());
			
			pageBean.setCurriculumlist(curriculumlist);
			pageBean.setPracticeLessonlist(sslist);
			return pageBean;
		}
		public List<List<PracticeLesson>> findtheolenlistByDepartUser(PracticeLesson practiceLesson, String usertag){
			List<List<PracticeLesson>> theolen=new ArrayList<List<PracticeLesson>>();
			//找到当前任课系的所有理论课记录
			List<PracticeLesson> list=practicePlanDao.findTheolenByTeachDepartment(practiceLesson.getTeachDepartment());
			List<Curriculum> listcurr=new ArrayList<Curriculum>();
			//获得理论课记录所有课程存入listcurr
			for(int i=0;i<list.size();i++)
			{
				listcurr.add(list.get(i).getCurriculum());
			 }	
			//为listcurr去重
			for(int i=0;i<(listcurr.size()-1);i++)
			{
				for(int j=(listcurr.size()-1);j>i;j--)
				{
					if(listcurr.get(j).equals(listcurr.get(i))){
						listcurr.remove(j);
					}
				}
			}
			for(int i=0;i<listcurr.size();i++){
				List<PracticeLesson> theolenlist1=new ArrayList<PracticeLesson>();
				List<PracticeLesson> theolenlist2=new ArrayList<PracticeLesson>();
				for(int j=0;j<list.size();j++){
					if(list.get(j).getCurriculum().equals(listcurr.get(i))&&list.get(j).getTeacher()!=null)
					{
						theolenlist1.add(list.get(j));
					}
					if(list.get(j).getCurriculum().equals(listcurr.get(i))&&list.get(j).getTeacher()==null)
					{
						theolenlist2.add(list.get(j));
					}
				}
				if(theolenlist1.size()!=0){
					theolen.add(theolenlist1);
				}
				if(theolenlist2.size()!=0){
					theolen.add(theolenlist2);
				}
			}
			return theolen;
		}
		public PageBean findUserByDepart(Integer currentpage,PracticeLesson practiceLesson, List<String> depart)
		{
			PageBean pageBean=new PageBean();
			College college=collegeDao.get(practiceLesson.getCurriculum().getCollege().getCollegeid());
			List<College> listcollege=new ArrayList<College>();
			listcollege.add(college);
			pageBean.setCollegelist(listcollege);
			
			Department depart1=departmentDao.get(practiceLesson.getTeachDepartment().getDepartmentid());
			List<Department> listdepart=new ArrayList<Department>();
			listdepart.add(depart1);
			pageBean.setDepartmentlist(listdepart);
			
			List<User> listuser1=userDao.findUserByDepart(depart1);
			List<User> listuser=new ArrayList<User>();
			for(int i=0;i<listuser1.size();i++){
				if(practiceLesson.getTeacher()!=null&&(!"".equals(practiceLesson.getTeacher().getTnum()))||!"".equals(practiceLesson.getTeacher().getUsername()))
				{
					if(practiceLesson.getTeacher().getTnum()!=null&&!"".equals(practiceLesson.getTeacher().getTnum())&&
							practiceLesson.getTeacher().getUsername()!=null&&!"".equals(practiceLesson.getTeacher().getUsername()))
					{
						if(practiceLesson.getTeacher().getTnum().equals(listuser1.get(i).getTnum())
								&&practiceLesson.getTeacher().getUsername().equals(listuser1.get(i).getUsername()))
						{
							listuser.add(listuser1.get(i));
						}
					}
					else if(!"".equals(practiceLesson.getTeacher().getTnum()) && "".equals(practiceLesson.getTeacher().getUsername()))
					{
						if(practiceLesson.getTeacher().getTnum().equals(listuser1.get(i).getTnum()))
						{
							listuser.add(listuser1.get(i));
						}
					}
					else if("".equals(practiceLesson.getTeacher().getTnum()) && !"".equals(practiceLesson.getTeacher().getUsername()))
					{
						if(practiceLesson.getTeacher().getUsername().equals(listuser1.get(i).getUsername()))
						{
							listuser.add(listuser1.get(i));
						}
					}else{
						listuser=listuser1;
					}
				}
				else
				{
					listuser=listuser1;
				}
			}
			int totalCount=listuser.size();
			pageBean.setTotalCount(totalCount);
			int pageSize = 10;
			pageBean.setPageSize(pageSize);
			int totalPage = 0;
			if(totalCount%pageSize == 0){
				totalPage = totalCount/pageSize;
			}else{
				totalPage = totalCount/pageSize+1;
			}
			pageBean.setTotalPage(totalPage);
			if(currentpage < 1){
				currentpage = 1;
			}
			if(currentpage > totalPage){
				currentpage = totalPage;
			}
			pageBean.setCurrentpage(currentpage);
			int begin = (currentpage-1)*pageSize;
			
			//为防止溢出 还需加一个约束条件？？？？？？？？？？
			List<User> sslist=new ArrayList<User>();
			if(currentpage != totalPage){
				sslist=listuser.subList(begin, (begin+pageSize));
			}
			else{
				if((begin>=0)&&(begin!=totalCount)){
					sslist=listuser.subList(begin,totalCount);
				}
			}
			pageBean.setUserlist(sslist);
			
			List<Professional> listprofess=new ArrayList<Professional>();
			for(int i=0;i<depart.size();i++){
				if(professionalDao.get(depart.get(i))!=null)
				{
					listprofess.add(professionalDao.get(depart.get(i)));
				}
				else{
					Professional professional=new Professional();
					professional.setDepartment(departmentDao.get(depart.get(i)));
					listprofess.add(professional);
				}
			}
			
			pageBean.setProfessionallist(listprofess);
			return pageBean;
		}
		public void updateTheolenByDepartToUser(PracticeLesson practiceLesson, List<String> depart) {
			PracticeLesson practiceLen=new PracticeLesson(); 
			for(int i=0;i<depart.size();i++){
				Curriculum curriculum=curriculumDao.get(practiceLesson.getCurriculum().getCurriculumid());
				if((professionalDao.get(depart.get(i)))!=null)
				{
					practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
				}
				else
				{
					Department department=departmentDao.get(depart.get(i));
					practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,department);
				}
				//theoreticalLen.setTeachDepartment(null);
				practiceLen.setTeacher(userDao.get(practiceLesson.getTeacher().getTnum()));
				practicePlanDao.update(practiceLen);
			}
			
		}

		//查询对应专业的专业方向
		public List<Professional> findProfessional(String departmentId) {
			return practicePlanDao.findProfessional(departmentId);
		}

		//查询没有选curriculumid的专业
		public List<Professional> findUnselectedProfessionalList(
				String departmentid, String curriculumid) {
			return practicePlanDao.findUnselectedProfessionalList(departmentid,curriculumid);
		}

		//根据专业方向id查询记录
		public Professional findprofessionalById(String professionalId) {
			return professionalDao.get(professionalId);
		}

		public PageBean findAllTheolen(Integer currentpage,PracticeLesson practiceLesson)
		{
			PageBean pageBean=new PageBean();
			College college=collegeDao.get(practiceLesson.getCurriculum().getCollege().getCollegeid());
			List<College> listcollege=collegeDao.findAll();
			pageBean.setCollegelist(listcollege);
			List<List<PracticeLesson>> theolenlist=new ArrayList<List<PracticeLesson>>();
			List<Curriculum> listcurr=new ArrayList<Curriculum>();
			List<PracticeLesson> listtheolen=new ArrayList<PracticeLesson>();
			if(!practiceLesson.getCurriculum().getCollege().getCollegeid().equals("-1")&&"".equals(practiceLesson.getCurriculum().getCurriculumid())
					&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
			{
				listcurr=curriculumDao.findCurrByCollege(college); 
			}
			else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())
					&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
			{
				List<Curriculum> list22=curriculumDao.findCurrByCurrcid(practiceLesson.getCurriculum().getCurriculumid());
				if(list22.size()!=0)
				{
					for(int i=0;i<list22.size();i++){
						listcurr.add(list22.get(i));
					}
				}
				
			}
			//**************************************************************
			else if("".equals(practiceLesson.getCurriculum().getCurriculumid())
					&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
			{
				List<Curriculum> list22=curriculumDao.findCurrByCurrcname(practiceLesson.getCurriculum().getCurriculumCname());
				if(list22.size()!=0)
				{
						for(int i=0;i<list22.size();i++){
						listcurr.add(list22.get(i));
					}
				}
			}

			else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())
					&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
			{	
				List<Curriculum> culist = curriculumDao.findCurrByCurrcname(practiceLesson.getCurriculum().getCurriculumCname());
				Curriculum curr1=null;
				if(culist.size()!=0){
					curr1 = culist.get(0);
				}
				Curriculum curr2=curriculumDao.get(practiceLesson.getCurriculum().getCurriculumid());
				if(curr1 != null){
					if(curr1 == curr2)
					{
						listcurr.add(curr1);
					}
				}
				
			}
			else
			{
				listcurr=curriculumDao.findAllwithout();
			}
			List<Curriculum> listcurr2=new ArrayList<Curriculum>();
			for(int i=0;i<listcurr.size();i++){
				listtheolen=practicePlanDao.findtheolenbycurr(listcurr.get(i));
				if(listtheolen.size()!=0)
				{	
					listcurr2.add(listcurr.get(i));
					theolenlist.add(listtheolen);
				}
				else
				{
					if("实践课".equals(listcurr.get(i).getCourseLei()))
					{
						PracticeLesson theolen=new PracticeLesson();
						theolen.setCurriculum(listcurr.get(i));
						listtheolen.add(theolen);
						listcurr2.add(listcurr.get(i));
						theolenlist.add(listtheolen);
					}
					else
					{
						
					}
				}
			}
			int totalCount=theolenlist.size();
			pageBean.setTotalCount(totalCount);
			int pageSize = 10;
			pageBean.setPageSize(pageSize);
			int totalPage = 0;
			if(totalCount%pageSize == 0){
				totalPage = totalCount/pageSize;
			}else{
				totalPage = totalCount/pageSize+1;
			}
			pageBean.setTotalPage(totalPage);
			if(currentpage < 1){
				currentpage = 1;
			}
			if(currentpage > totalPage){
				currentpage = totalPage;
			}
			pageBean.setCurrentpage(currentpage);
			int begin = (currentpage-1)*pageSize;
			
			//为防止溢出 还需加一个约束条件？？？？？？？？？？
			List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
			List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
			
			if(currentpage != totalPage){
				sslist=theolenlist.subList(begin, (begin+pageSize));
				curriculumlist=listcurr2.subList(begin, (begin+pageSize));
			}
			else{
				if((begin>=0)&&(begin!=totalCount)){
					sslist=theolenlist.subList(begin,totalCount);
					curriculumlist=listcurr2.subList(begin, totalCount);
				}
			}
			
			pageBean.setCurriculumlist(curriculumlist);
			pageBean.setPracticeLessonlist(sslist);
			return pageBean;
		}
		
		
		//----------------------------实习---------------------------------
		
		//到编辑大纲实践课——实习验证页面
		public void toCheckPracLes_FieldWorkPage(PracticeLesson practiceLesson) {
			User user = userDao.get(practiceLesson.getTeacher().getTnum());
			String zuzhixingshi = "实习";
			List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbyuser(user,zuzhixingshi);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson pracLess = allPracticeLessonlist.get(0);
				String cid = pracLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allPracticeLessonlist.size();ti++)
				{
					if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						practiceLessonList.add(allPracticeLessonlist.get(ti));
						allPracticeLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(practiceLessonList);
			}
			
			
			List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbyuser(user,zuzhixingshi);
			List<ApplicationSyllabus_FieldWork> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac(newallPracticeLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson pracLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus_FieldWork appSyll = applicationSyllabusList.get(pk);
						if(pracLess.getProfessional() == null){
							if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(pracLess);
								professionalList.get(pi).remove(pracLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
									newhaveprofessionalList.add(pracLess);
									professionalList.get(pi).remove(pracLess);
									pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
						PracticeLesson prac = haveprofessionalList.get(ri).get(0);
						String syid =practicePlanDao.findSyllabusidByPrac_FieldWork(prac);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(practicePlanDao.findSyllabusidByPrac_FieldWork(haveprofessionalList.get(ri).get(rj))))
							{
								newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newPracticeLesson);
					}
				}
			}
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyuser(user,newprac,zuzhixingshi);//该老师所有应选大纲
		
				while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
					List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson pracLess = newallPracticeLesson.get(0);
					String cid = pracLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallPracticeLesson.size();ti++)
					{
						if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							practiceLessonList.add(newallPracticeLesson.get(ti));
							newallPracticeLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(practiceLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = practicePlanDao.findSyllabusidByPrac_FieldWork(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus_FieldWork> appSyllaList = new ArrayList<ApplicationSyllabus_FieldWork>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findAppByPrac_FieldWork(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_FieldWork(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
							{
								newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
								j--;
							}
						}
					}
				}
				}
			}
			
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
			
		}
		
		//跳转到院、校管理员查看大纲页面
		public void toCheckPracLesFieldWorkPageAdmin(String tnum,PracticeLesson practiceLesson) {
			// TODO Auto-generated method stub
			findUserInfo(tnum);
			if(user.getAdminlevel() == 5)
			{
				collegelist = collegeDao.findAll();
			}
			else
			{
				collegelist = findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid());
			}
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
			
			String zuzhixingshi = "实习";
			List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbycollegeAndsearch(practiceLesson,zuzhixingshi);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson pracLess = allPracticeLessonlist.get(0);
				String cid = pracLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allPracticeLessonlist.size();ti++)
				{
					if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						practiceLessonList.add(allPracticeLessonlist.get(ti));
						allPracticeLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(practiceLessonList);
			}
			
			
			List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbycollegeAndsearch(practiceLesson,zuzhixingshi);
			List<ApplicationSyllabus_FieldWork> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac(newallPracticeLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson pracLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus_FieldWork appSyll = applicationSyllabusList.get(pk);
						if(pracLess.getProfessional() == null){
							if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(pracLess);
								professionalList.get(pi).remove(pracLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
									newhaveprofessionalList.add(pracLess);
									professionalList.get(pi).remove(pracLess);
									pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
						PracticeLesson prac = haveprofessionalList.get(ri).get(0);
						String syid =practicePlanDao.findSyllabusidByPrac_FieldWork(prac);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(practicePlanDao.findSyllabusidByPrac_FieldWork(haveprofessionalList.get(ri).get(rj))))
							{
								newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newPracticeLesson);
					}
				}
			}
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyCurriculumAndDepartment(newprac,zuzhixingshi);//该老师所有应选大纲
		
				while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
					List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson pracLess = newallPracticeLesson.get(0);
					String cid = pracLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallPracticeLesson.size();ti++)
					{
						if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							practiceLessonList.add(newallPracticeLesson.get(ti));
							newallPracticeLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(practiceLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = practicePlanDao.findSyllabusidByPrac_FieldWork(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus_FieldWork> appSyllaList = new ArrayList<ApplicationSyllabus_FieldWork>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findAppByPrac_FieldWork(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_FieldWork(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
							{
								newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
								j--;
							}
						}
					}
				}
				}
			}
			
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
		}
		
		public Integer syllabus_FieldWorkId;
		//未编辑大纲到编辑实践课——实习大纲页面
		public String toPracLesFieldWorkPage(PracticeLesson practiceLesson,List<String> selectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			List<Professional> professionalList = new ArrayList<Professional>();
			if(selectProfessional != null && selectProfessional.size() != 0)
			{
				for(int i=0;i<selectProfessional.size();i++)
				{
					Professional professional = professionalDao.get(selectProfessional.get(i));
					professionalList.add(professional);
				}
			}
			
			//判断由于用户操作失误，在向数据库写新大纲前若数据库中存在，则先删除再写
			ApplicationSyllabus_FieldWork newapplicationSyllabus_FieldWork = new ApplicationSyllabus_FieldWork();
			if(professionalList != null && professionalList.size() != 0)
			{
				for(int i=0;i<professionalList.size();i++)
				{
					//不同
					newapplicationSyllabus_FieldWork = applicationSyllabus_FieldWorkDao.findappByPracAndPro(newPracticeLesson,professionalList.get(i));
					if(newapplicationSyllabus_FieldWork != null)
					{
						syllabus_FieldWorkDao.delete(newapplicationSyllabus_FieldWork.getSyllabus_FieldWork());
					}
				}
			}
			else
			{
				newapplicationSyllabus_FieldWork = applicationSyllabusDao.findAppByPrac_FieldWork_E(newPracticeLesson);
				if(newapplicationSyllabus_FieldWork != null)
				{
					syllabus_FieldWorkDao.delete(newapplicationSyllabus_FieldWork.getSyllabus_FieldWork());
				}
			}
			
			Syllabus_FieldWork syllabus_FieldWork = new Syllabus_FieldWork();
			syllabus_FieldWorkDao.add(syllabus_FieldWork);
			syllabus_FieldWorkId = syllabus_FieldWork.getSyllabus_FieldWorkID();
			
			if(professionalList != null && professionalList.size() != 0)
			{
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				for(int i=0;i<selectProfessional.size();i++){
					Professional professional = professionalDao.get(selectProfessional.get(i));
					Syllabus_FieldWork newsyllabus = syllabus_FieldWorkDao.get(syllabus_FieldWorkId);
					ApplicationSyllabus_FieldWork applicationSyllabus_FieldWork = new ApplicationSyllabus_FieldWork();
					applicationSyllabus_FieldWork.setSyllabus_FieldWork(newsyllabus);
					applicationSyllabus_FieldWork.setCurriculum(newPracticeLesson.getCurriculum());
					applicationSyllabus_FieldWork.setDepartment(newPracticeLesson.getDepartment());
					applicationSyllabus_FieldWork.setProfessional(professional);
					applicationSyllabus_FieldWorkDao.add(applicationSyllabus_FieldWork);
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			else{
				Syllabus_FieldWork newsyllabus = syllabus_FieldWorkDao.get(syllabus_FieldWorkId);
				ApplicationSyllabus_FieldWork applicationSyllabus_FieldWork = new ApplicationSyllabus_FieldWork();
				applicationSyllabus_FieldWork.setSyllabus_FieldWork(newsyllabus);
				applicationSyllabus_FieldWork.setCurriculum(newPracticeLesson.getCurriculum());
				applicationSyllabus_FieldWork.setDepartment(newPracticeLesson.getDepartment());
				applicationSyllabus_FieldWorkDao.add(applicationSyllabus_FieldWork);
				ServletActionContext.getRequest().setAttribute("count", 0);
			}
			ServletActionContext.getRequest().setAttribute("newPracticeLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabus_FieldWork.getSyllabus_FieldWorkID());
			return "yes";
		}
		
		//未编辑大纲到复制实践课——实习大纲页面
		public void toPracLesFieldWorkPageCopy(PracticeLesson practiceLesson,List<String> selectProfessional, String syllabusId_Copy) {
			toPracLesFieldWorkPage(practiceLesson,selectProfessional);
			//syllabus_FieldWorkId新建的大纲Id
			//syllabusId_Copy传过来的选择的要复制的大纲Id
			Syllabus_FieldWork copysyllabus = syllabus_FieldWorkDao.get(Integer.valueOf(syllabusId_Copy));
			Syllabus_FieldWork newsyllabus = syllabus_FieldWorkDao.get(Integer.valueOf(syllabus_FieldWorkId));
			/*****************************实习大纲大纲一对一基本信息***********************************************************/
			newsyllabus.setFieldWork(copysyllabus.getFieldWork());
			newsyllabus.setFieldContent(copysyllabus.getFieldContent());
			syllabus_FieldWorkDao.update(newsyllabus);
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
		/*	List<ApplicationSyllabus_FieldWork> newApplicationSyllabus_FieldWorkList =  applicationSyllabus_FieldWorkDao.findapplicationSyllabus_FieldWorkBySylid(String.valueOf(syllabus_FieldWorkId));
			List<ApplicationSyllabus_FieldWork> copyApplicationSyllabus_FieldWorkList =  applicationSyllabus_FieldWorkDao.findapplicationSyllabus_FieldWorkBySylid(String.valueOf(syllabusId_Copy));
			if(newApplicationSyllabus_FieldWorkList != null && newApplicationSyllabus_FieldWorkList.size() != 0 && copyApplicationSyllabus_FieldWorkList != null && copyApplicationSyllabus_FieldWorkList.size() !=0)
			{
				if(newApplicationSyllabus_FieldWorkList.get(0).getDepartment() == copyApplicationSyllabus_FieldWorkList.get(0).getDepartment())
				{//相同专业才进行复制
					//指标点选择表复制
					List<IndexSelect_FieldWork> indexSelect_FieldWorkList = indexSelect_FieldWorkDao.findIndexSelect_FieldWorkListBySyllabusid(syllabusId_Copy);
					if(indexSelect_FieldWorkList != null && indexSelect_FieldWorkList.size() != 0)
					{
						for(int i=0;i<indexSelect_FieldWorkList.size();i++)
						{
							IndexSelect_FieldWork indexSelect_FieldWork = indexSelect_FieldWorkList.get(i);
							IndexSelect_FieldWork newindexSelect_FieldWork = new IndexSelect_FieldWork();
							newindexSelect_FieldWork.setAbility(indexSelect_FieldWork.getAbility());
							newindexSelect_FieldWork.setAnalisis(indexSelect_FieldWork.getAnalisis());
							newindexSelect_FieldWork.setSyllabus_FieldWorkid(String.valueOf(syllabus_FieldWorkId));
							indexSelect_FieldWorkDao.add(newindexSelect_FieldWork);
						}
					}
					//教学目标表复制
					List<TeachObj_FieldWork> teachObj_FieldWorkList = teachObj_FieldWorkDao.findTeachObj_FieldWorkListBySyllabusid(syllabusId_Copy);
					List<TeachObj_FieldWork> newTeachObj_FieldWorkList = new ArrayList<TeachObj_FieldWork>();
					if(teachObj_FieldWorkList != null && teachObj_FieldWorkList.size() != 0)
					{
						for(int i=0;i<teachObj_FieldWorkList.size();i++)
						{
							TeachObj_FieldWork teachObj_FieldWork = teachObj_FieldWorkList.get(i);
							TeachObj_FieldWork newteachObj_FieldWork = new TeachObj_FieldWork();
							newteachObj_FieldWork.setTeachObjContent_FieldWork(teachObj_FieldWork.getTeachObjContent_FieldWork());
							newteachObj_FieldWork.setSyllabus_FieldWorkid(String.valueOf(syllabus_FieldWorkId));
							teachObj_FieldWorkDao.add(newteachObj_FieldWork);
							newTeachObj_FieldWorkList.add(newteachObj_FieldWork);
						}
					}
					//毕业要求与教学目标对应关系表复制
					List<AbilityAndTeachObj_FieldWork> abilityAndTeachObj_FieldWorkList = abilityAndTeachObj_FieldWorkDao.findAbiAndTeachListBySyllabusid(syllabusId_Copy);
					if(abilityAndTeachObj_FieldWorkList != null && abilityAndTeachObj_FieldWorkList.size() !=0)
					{
						for(int i=0;i<abilityAndTeachObj_FieldWorkList.size();i++)
						{
							AbilityAndTeachObj_FieldWork abilityAndTeachObj_FieldWork = abilityAndTeachObj_FieldWorkList.get(i);
							AbilityAndTeachObj_FieldWork newabilityAndTeachObj_FieldWork = new AbilityAndTeachObj_FieldWork();
							newabilityAndTeachObj_FieldWork.setAbility(abilityAndTeachObj_FieldWork.getAbility());
							newabilityAndTeachObj_FieldWork.setTeachObj_FieldWork(newTeachObj_FieldWorkList.get(teachObj_FieldWorkList.indexOf(abilityAndTeachObj_FieldWork.getTeachObj_FieldWork())));
							newabilityAndTeachObj_FieldWork.setSyllabus_FieldWorkid(String.valueOf(syllabus_FieldWorkId));
							abilityAndTeachObj_FieldWorkDao.add(newabilityAndTeachObj_FieldWork);
						}
					}
				}
			}*/
			
		}
		
		//已编辑大纲到编辑实践课——实习大纲页面
		public String toHavePracLesFieldWorkPage(String syllabusId,PracticeLesson practiceLesson,List<String> haveselectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			if(haveselectProfessional != null && haveselectProfessional.size() != 0){
				List<Professional> professionalList = new ArrayList<Professional>();
				for(int i=0;i<haveselectProfessional.size();i++){//将勾选的专业方向id查到实体放入List
					Professional professional = professionalDao.get(haveselectProfessional.get(i));
					professionalList.add(professional);
				}
				
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				//
				List<ApplicationSyllabus_FieldWork> applicationSyllabus_FieldWorkList = applicationSyllabus_FieldWorkDao.findapplicationSyllabus_FieldWorkBySylid(syllabusId);//通过大纲id查询该大纲的所有应用专业
				List<Professional> professional = new ArrayList<Professional>();
				if(applicationSyllabus_FieldWorkList != null && applicationSyllabus_FieldWorkList.size() != 0){
					for(int i=0;i<applicationSyllabus_FieldWorkList.size();i++){
						professional.add(applicationSyllabus_FieldWorkList.get(i).getProfessional());
					}
				}
				
				for(int i=0;i<professionalList.size();i++){
					if(professional.contains(professionalList.get(i))){
						professional.remove(professionalList.get(i));
					}
					else{
						ApplicationSyllabus_FieldWork applicationSyllabus_FieldWork = new ApplicationSyllabus_FieldWork();
						Syllabus_FieldWork syllabus_FieldWork = syllabus_FieldWorkDao.get(Integer.valueOf(syllabusId));
						applicationSyllabus_FieldWork.setSyllabus_FieldWork(syllabus_FieldWork);
						applicationSyllabus_FieldWork.setCurriculum(newPracticeLesson.getCurriculum());
						applicationSyllabus_FieldWork.setDepartment(newPracticeLesson.getDepartment());
						applicationSyllabus_FieldWork.setProfessional(professionalList.get(i));
						applicationSyllabus_FieldWorkDao.add(applicationSyllabus_FieldWork);
					}
				}
				
				if(professional != null && professional.size() != 0){
					for(int i=0;i<professional.size();i++){
						ApplicationSyllabus_FieldWork applicationSyllabus_FieldWork =  applicationSyllabus_FieldWorkDao.findappByPracAndPro(newPracticeLesson,professional.get(i));
						applicationSyllabus_FieldWorkDao.delete(applicationSyllabus_FieldWork);
					}
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			ServletActionContext.getRequest().setAttribute("newPracticeLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			return "yes";
		}
		
		//删除实践课大纲——实习
		public void deletePracSyllabusFieldWork(String syllabusId) {
			
			/*****************************实习大纲大纲一对一基本信息***********************************************************/
			//大纲表删除
			Syllabus_FieldWork newsyllabus = syllabus_FieldWorkDao.get(Integer.valueOf(syllabusId));
			if(newsyllabus != null)
			{
				syllabus_FieldWorkDao.delete(newsyllabus);
			}
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
			//删除对应关系表
			List<ApplicationSyllabus_FieldWork> copyApplicationSyllabus_FieldWorkList =  applicationSyllabus_FieldWorkDao.findapplicationSyllabus_FieldWorkBySylid(String.valueOf(syllabusId));
			if(copyApplicationSyllabus_FieldWorkList != null && copyApplicationSyllabus_FieldWorkList.size() !=0)
			{
				for(int i=0;i<copyApplicationSyllabus_FieldWorkList.size();i++)
				{
					applicationSyllabus_FieldWorkDao.delete(copyApplicationSyllabus_FieldWorkList.get(i));
				}
			}	
			//毕业要求与教学目标对应关系表删除
			List<AbilityAndTeachObj_FieldWork> abilityAndTeachObj_FieldWorkList = abilityAndTeachObj_FieldWorkDao.findAbiAndTeachListBySyllabusid(syllabusId);
			if(abilityAndTeachObj_FieldWorkList != null && abilityAndTeachObj_FieldWorkList.size() !=0)
			{
				for(int i=0;i<abilityAndTeachObj_FieldWorkList.size();i++)
				{
					abilityAndTeachObj_FieldWorkDao.delete(abilityAndTeachObj_FieldWorkList.get(i));
				}
			}
			//指标点选择表删除
			List<IndexSelect_FieldWork> indexSelect_FieldWorkList = indexSelect_FieldWorkDao.findIndexSelect_FieldWorkListBySyllabusid(syllabusId);
			if(indexSelect_FieldWorkList != null && indexSelect_FieldWorkList.size() != 0)
			{
				for(int i=0;i<indexSelect_FieldWorkList.size();i++)
				{
					indexSelect_FieldWorkDao.delete(indexSelect_FieldWorkList.get(i));
				}
			}
			//教学目标表删除
			List<TeachObj_FieldWork> teachObj_FieldWorkList = teachObj_FieldWorkDao.findTeachObj_FieldWorkListBySyllabusid(syllabusId);
			if(teachObj_FieldWorkList != null && teachObj_FieldWorkList.size() != 0)
			{
				for(int i=0;i<teachObj_FieldWorkList.size();i++)
				{
					teachObj_FieldWorkDao.delete(teachObj_FieldWorkList.get(i));
				}
			}

			ServletActionContext.getRequest().setAttribute("tag", "toCheckPracLesFieldWorkPage");
			ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
		}
			
		
		
		//----------------------------课程设计---------------------------------
		//到编辑大纲实践课——课程设计验证页面
		public void toCheckPracLesCourseDesignPage(PracticeLesson practiceLesson) {
			User user = userDao.get(practiceLesson.getTeacher().getTnum());
			String zuzhixingshi = "课程设计（学年论文）";
			List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbyuser(user,zuzhixingshi);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson pracLess = allPracticeLessonlist.get(0);
				String cid = pracLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allPracticeLessonlist.size();ti++)
				{
					if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						practiceLessonList.add(allPracticeLessonlist.get(ti));
						allPracticeLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(practiceLessonList);
			}
			
			
			List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbyuser(user,zuzhixingshi);
			List<ApplicationSyllabus_CourseDesign> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_CourseDesign(newallPracticeLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson pracLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus_CourseDesign appSyll = applicationSyllabusList.get(pk);
						if(pracLess.getProfessional() == null){
							if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(pracLess);
								professionalList.get(pi).remove(pracLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
									newhaveprofessionalList.add(pracLess);
									professionalList.get(pi).remove(pracLess);
									pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
						PracticeLesson prac = haveprofessionalList.get(ri).get(0);
						String syid =practicePlanDao.findSyllabusidByPrac_CourseDesign(prac);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(practicePlanDao.findSyllabusidByPrac_CourseDesign(haveprofessionalList.get(ri).get(rj))))
							{
								newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newPracticeLesson);
					}
				}
			}
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyuser(user,newprac,zuzhixingshi);//该老师所有应选大纲
		
				while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
					List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson pracLess = newallPracticeLesson.get(0);
					String cid = pracLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallPracticeLesson.size();ti++)
					{
						if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							practiceLessonList.add(newallPracticeLesson.get(ti));
							newallPracticeLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(practiceLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = practicePlanDao.findSyllabusidByPrac_CourseDesign(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
		//--------剔除已完成大纲的专业方向
		List<ApplicationSyllabus_CourseDesign> appSyllaList = new ArrayList<ApplicationSyllabus_CourseDesign>();
		for(int i=0;i<newprofessionalList.size();i++){
			appSyllaList = applicationSyllabusDao.findAppByPrac_CourseDesign(newprofessionalList.get(i).get(0));
			if(appSyllaList != null && appSyllaList.size() != 0){
					
			for(int k=0;k<appSyllaList.size();k++)
			{
				for(int j=0;j<newprofessionalList.get(i).size();j++)
				{	
					if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
					{
						if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_CourseDesign(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
						{
							newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
							j--;
						}
					}
				}
			}
			}
		}
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
			
		}
		
		//跳转到院、校管理员查看大纲页面
		public void toCheckCourseDesignPageAdmin(String tnum,PracticeLesson practiceLesson) {
			// TODO Auto-generated method stub
			findUserInfo(tnum);
			if(user.getAdminlevel() == 5)
			{
				collegelist = collegeDao.findAll();
			}
			else
			{
				collegelist = findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid());
			}
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
			String zuzhixingshi = "课程设计（学年论文）";
			List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbycollegeAndsearch(practiceLesson,zuzhixingshi);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson pracLess = allPracticeLessonlist.get(0);
				String cid = pracLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allPracticeLessonlist.size();ti++)
				{
					if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						practiceLessonList.add(allPracticeLessonlist.get(ti));
						allPracticeLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(practiceLessonList);
			}
			
			
			List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbycollegeAndsearch(practiceLesson,zuzhixingshi);
			List<ApplicationSyllabus_CourseDesign> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_CourseDesign(newallPracticeLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson pracLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus_CourseDesign appSyll = applicationSyllabusList.get(pk);
						if(pracLess.getProfessional() == null){
							if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(pracLess);
								professionalList.get(pi).remove(pracLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
									newhaveprofessionalList.add(pracLess);
									professionalList.get(pi).remove(pracLess);
									pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
						PracticeLesson prac = haveprofessionalList.get(ri).get(0);
						String syid =practicePlanDao.findSyllabusidByPrac_CourseDesign(prac);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(practicePlanDao.findSyllabusidByPrac_CourseDesign(haveprofessionalList.get(ri).get(rj))))
							{
								newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newPracticeLesson);
					}
				}
			}
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyCurriculumAndDepartment(newprac,zuzhixingshi);//该老师所有应选大纲
		
				while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
					List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson pracLess = newallPracticeLesson.get(0);
					String cid = pracLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallPracticeLesson.size();ti++)
					{
						if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							practiceLessonList.add(newallPracticeLesson.get(ti));
							newallPracticeLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(practiceLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = practicePlanDao.findSyllabusidByPrac_CourseDesign(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
		//--------剔除已完成大纲的专业方向
		List<ApplicationSyllabus_CourseDesign> appSyllaList = new ArrayList<ApplicationSyllabus_CourseDesign>();
		for(int i=0;i<newprofessionalList.size();i++){
			appSyllaList = applicationSyllabusDao.findAppByPrac_CourseDesign(newprofessionalList.get(i).get(0));
			if(appSyllaList != null && appSyllaList.size() != 0){
					
			for(int k=0;k<appSyllaList.size();k++)
			{
				for(int j=0;j<newprofessionalList.get(i).size();j++)
				{	
					if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
					{
						if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_CourseDesign(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
						{
							newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
							j--;
						}
					}
				}
			}
			}
		}
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
			
		}

		public Integer syllabus_CourseDesignId;
		//未编辑大纲到编辑实践课——课程设计大纲页面
		public String toPracLesCourseDesignPage(PracticeLesson practiceLesson,List<String> selectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			List<Professional> professionalList = new ArrayList<Professional>();
			if(selectProfessional != null && selectProfessional.size() != 0)
			{
				for(int i=0;i<selectProfessional.size();i++)
				{
					Professional professional = professionalDao.get(selectProfessional.get(i));
					professionalList.add(professional);
				}
			}
			
			//判断由于用户操作失误，在向数据库写新大纲前若数据库中存在，则先删除再写
			ApplicationSyllabus_CourseDesign newapplicationSyllabus_CourseDesign = new ApplicationSyllabus_CourseDesign();
			if(professionalList != null && professionalList.size() != 0)
			{
				for(int i=0;i<professionalList.size();i++)
				{
					newapplicationSyllabus_CourseDesign = applicationSyllabus_CourseDesignDao.findappByPracAndPro(newPracticeLesson,professionalList.get(i));
					if(newapplicationSyllabus_CourseDesign != null)
					{
						syllabus_CourseDesignDao.delete(newapplicationSyllabus_CourseDesign.getSyllabus_CourseDesign());
					}
				}
			}
			else
			{
				newapplicationSyllabus_CourseDesign = applicationSyllabusDao.findAppByPrac_CourseDesign_E(newPracticeLesson);
				if(newapplicationSyllabus_CourseDesign != null)
				{
					syllabus_CourseDesignDao.delete(newapplicationSyllabus_CourseDesign.getSyllabus_CourseDesign());
				}
			}
			
			Syllabus_CourseDesign syllabus_CourseDesign = new Syllabus_CourseDesign();
			syllabus_CourseDesignDao.add(syllabus_CourseDesign);
			syllabus_CourseDesignId = syllabus_CourseDesign.getSyllabus_CourseDesignid();
			if(professionalList != null && professionalList.size() != 0)
			{
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				
				for(int i=0;i<selectProfessional.size();i++){
					Professional professional = professionalDao.get(selectProfessional.get(i));
					Syllabus_CourseDesign newsyllabus = syllabus_CourseDesignDao.get(syllabus_CourseDesignId);
					ApplicationSyllabus_CourseDesign applicationSyllabus_CourseDesign = new ApplicationSyllabus_CourseDesign();
					applicationSyllabus_CourseDesign.setSyllabus_CourseDesign(newsyllabus);
					applicationSyllabus_CourseDesign.setCurriculum(newPracticeLesson.getCurriculum());
					applicationSyllabus_CourseDesign.setDepartment(newPracticeLesson.getDepartment());
					applicationSyllabus_CourseDesign.setProfessional(professional);
					applicationSyllabus_CourseDesignDao.add(applicationSyllabus_CourseDesign);
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			else{
				Syllabus_CourseDesign newsyllabus = syllabus_CourseDesignDao.get(syllabus_CourseDesignId);
				ApplicationSyllabus_CourseDesign applicationSyllabus_CourseDesign = new ApplicationSyllabus_CourseDesign();
				applicationSyllabus_CourseDesign.setSyllabus_CourseDesign(newsyllabus);
				applicationSyllabus_CourseDesign.setCurriculum(newPracticeLesson.getCurriculum());
				applicationSyllabus_CourseDesign.setDepartment(newPracticeLesson.getDepartment());
				applicationSyllabus_CourseDesignDao.add(applicationSyllabus_CourseDesign);
				ServletActionContext.getRequest().setAttribute("count", 0);
			}
			
			ServletActionContext.getRequest().setAttribute("newPracticeLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabus_CourseDesign.getSyllabus_CourseDesignid());
			return "yes";
		}
		
		//未编辑大纲到复制实践课——课程设计大纲页面
		public void toPracLesCourseDesignPageCopy(PracticeLesson practiceLesson, List<String> selectProfessional,String syllabusId_Copy) {
			toPracLesCourseDesignPage(practiceLesson,selectProfessional);
			//syllabus_CourseDesignId新建的大纲Id
			//syllabusId_Copy传过来的选择的要复制的大纲Id
			Syllabus_CourseDesign copysyllabus = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId_Copy));
			Syllabus_CourseDesign newsyllabus = syllabus_CourseDesignDao.get(Integer.valueOf(syllabus_CourseDesignId));
			
			/****************************课程设计（学年论文）大纲表一对一关系-基本信息*************************************/
			newsyllabus.setBaseCourseDesign(copysyllabus.getBaseCourseDesign());
			newsyllabus.setConCourseDesign(copysyllabus.getConCourseDesign());
			newsyllabus.setWayCourseDesign(copysyllabus.getWayCourseDesign());
			syllabus_CourseDesignDao.update(newsyllabus);
			/****************************课程设计（学年论文）大纲表一对多关系-教材信息*************************************/
			List<PracticeBooks_CourseDesign> practiceBooks_CourseDesignlist = practiceBooks_CourseDesignDao.getbyPracticeBooks_CourseDesignlist(syllabusId_Copy);
			if(practiceBooks_CourseDesignlist != null && practiceBooks_CourseDesignlist.size() != 0)
			{
				for(int di=0;di<practiceBooks_CourseDesignlist.size();di++)
				{
					PracticeBooks_CourseDesign practiceBooks_CourseDesign = practiceBooks_CourseDesignlist.get(di);
					PracticeBooks_CourseDesign newpracticeBooks_CourseDesign = new PracticeBooks_CourseDesign();
					newpracticeBooks_CourseDesign.setName(practiceBooks_CourseDesign.getName());
					newpracticeBooks_CourseDesign.setSyllabus_CourseDesignid(String.valueOf(syllabus_CourseDesignId));
					practiceBooks_CourseDesignDao.add(newpracticeBooks_CourseDesign);
				}
			}			
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
		/*	List<ApplicationSyllabus_CourseDesign> newApplicationSyllabus_CourseDesignList =  applicationSyllabus_CourseDesignDao.findapplicationSyllabus_CourseDesignBySylid(String.valueOf(syllabus_CourseDesignId));
			List<ApplicationSyllabus_CourseDesign> copyApplicationSyllabus_CourseDesignList =  applicationSyllabus_CourseDesignDao.findapplicationSyllabus_CourseDesignBySylid(String.valueOf(syllabusId_Copy));
			if(newApplicationSyllabus_CourseDesignList != null && newApplicationSyllabus_CourseDesignList.size() != 0 && copyApplicationSyllabus_CourseDesignList != null && copyApplicationSyllabus_CourseDesignList.size() !=0)
			{
				if(newApplicationSyllabus_CourseDesignList.get(0).getDepartment() == copyApplicationSyllabus_CourseDesignList.get(0).getDepartment())
				{//相同专业才进行复制
					//指标点选择表复制
					List<IndexSelect_CourseDesign> indexSelect_CourseDesignList = indexSelect_CourseDesignDao.findIndexSelect_CourseDesignListBySyllabusid(syllabusId_Copy);
					if(indexSelect_CourseDesignList != null && indexSelect_CourseDesignList.size() != 0)
					{
						for(int i=0;i<indexSelect_CourseDesignList.size();i++)
						{
							IndexSelect_CourseDesign indexSelect_CourseDesign = indexSelect_CourseDesignList.get(i);
							IndexSelect_CourseDesign newindexSelect_CourseDesign = new IndexSelect_CourseDesign();
							newindexSelect_CourseDesign.setAbility(indexSelect_CourseDesign.getAbility());
							newindexSelect_CourseDesign.setAnalisis(indexSelect_CourseDesign.getAnalisis());
							newindexSelect_CourseDesign.setSyllabus_CourseDesignid(String.valueOf(syllabus_CourseDesignId));
							indexSelect_CourseDesignDao.add(newindexSelect_CourseDesign);
						}
					}
					//教学目标表复制
					List<TeachObj_CourseDesign> teachObj_CourseDesignList = teachObj_CourseDesignDao.findTeachObj_CourseDesignListBySyllabusid(syllabusId_Copy);
					List<TeachObj_CourseDesign> newTeachObj_CourseDesignList = new ArrayList<TeachObj_CourseDesign>();
					if(teachObj_CourseDesignList != null && teachObj_CourseDesignList.size() != 0)
					{
						for(int i=0;i<teachObj_CourseDesignList.size();i++)
						{
							TeachObj_CourseDesign teachObj_CourseDesign = teachObj_CourseDesignList.get(i);
							TeachObj_CourseDesign newteachObj_CourseDesign = new TeachObj_CourseDesign();
							newteachObj_CourseDesign.setTeachObjContent_CourseDesign(teachObj_CourseDesign.getTeachObjContent_CourseDesign());
							newteachObj_CourseDesign.setSyllabus_CourseDesignid(String.valueOf(syllabus_CourseDesignId));
							teachObj_CourseDesignDao.add(newteachObj_CourseDesign);
							newTeachObj_CourseDesignList.add(newteachObj_CourseDesign);
						}
					}
					//毕业要求与教学目标对应关系表复制
					List<AbilityAndTeachObj_CourseDesign> abilityAndTeachObj_CourseDesignList = abilityAndTeachObj_CourseDesignDao.findAbiAndTeachListBySyllabusid(syllabusId_Copy);
					if(abilityAndTeachObj_CourseDesignList != null && abilityAndTeachObj_CourseDesignList.size() !=0)
					{
						for(int i=0;i<abilityAndTeachObj_CourseDesignList.size();i++)
						{
							AbilityAndTeachObj_CourseDesign abilityAndTeachObj_CourseDesign = abilityAndTeachObj_CourseDesignList.get(i);
							AbilityAndTeachObj_CourseDesign newabilityAndTeachObj_CourseDesign = new AbilityAndTeachObj_CourseDesign();
							newabilityAndTeachObj_CourseDesign.setAbility(abilityAndTeachObj_CourseDesign.getAbility());
							newabilityAndTeachObj_CourseDesign.setTeachObj_CourseDesign(newTeachObj_CourseDesignList.get(teachObj_CourseDesignList.indexOf(abilityAndTeachObj_CourseDesign.getTeachObj_CourseDesign())));
							newabilityAndTeachObj_CourseDesign.setSyllabus_CourseDesignid(String.valueOf(syllabus_CourseDesignId));
							abilityAndTeachObj_CourseDesignDao.add(newabilityAndTeachObj_CourseDesign);
						}
					}
				}
			}*/
			/****************************课程设计（学年论文）大纲表一对多关系-教学安排*************************************/
			List<DistributeHour_CourseDesign> distributeHour_CourseDesignlist = distributeHour_CourseDesignDao.getbydistributeHour_CourseDesign(syllabusId_Copy);
			if(distributeHour_CourseDesignlist != null && distributeHour_CourseDesignlist.size() != 0)
			{
				for(int di=0;di<distributeHour_CourseDesignlist.size();di++)
				{
					DistributeHour_CourseDesign distributeHour_CourseDesign = distributeHour_CourseDesignlist.get(di);
					DistributeHour_CourseDesign newdistributeHour_CourseDesign = new DistributeHour_CourseDesign();
					newdistributeHour_CourseDesign.setName(distributeHour_CourseDesign.getName());
					newdistributeHour_CourseDesign.setTime(distributeHour_CourseDesign.getTime());
					newdistributeHour_CourseDesign.setSyllabusid(String.valueOf(syllabus_CourseDesignId));
					distributeHour_CourseDesignDao.add(newdistributeHour_CourseDesign);
				}
			}
			
		}
		
		//已编辑大纲到编辑实践课——课程设计大纲页面
		public String toHavePracLesCourseDesignPage(String syllabusId,PracticeLesson practiceLesson,List<String> haveselectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			
			if(haveselectProfessional != null && haveselectProfessional.size() != 0){
				List<Professional> professionalList = new ArrayList<Professional>();
				for(int i=0;i<haveselectProfessional.size();i++){//将勾选的专业方向id查到实体放入List
					Professional professional = professionalDao.get(haveselectProfessional.get(i));
					professionalList.add(professional);
				}
				
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				//
				List<ApplicationSyllabus_CourseDesign> applicationSyllabus_CourseDesignList = applicationSyllabus_CourseDesignDao.findapplicationSyllabus_CourseDesignBySylid(syllabusId);//通过大纲id查询该大纲的所有应用专业
				List<Professional> professional = new ArrayList<Professional>();
				if(applicationSyllabus_CourseDesignList != null && applicationSyllabus_CourseDesignList.size() != 0){
					for(int i=0;i<applicationSyllabus_CourseDesignList.size();i++){
						professional.add(applicationSyllabus_CourseDesignList.get(i).getProfessional());
					}
				}
				
				for(int i=0;i<professionalList.size();i++){
					if(professional.contains(professionalList.get(i))){
						professional.remove(professionalList.get(i));
					}
					else{
						ApplicationSyllabus_CourseDesign applicationSyllabus_CourseDesign = new ApplicationSyllabus_CourseDesign();
						Syllabus_CourseDesign syllabus_CourseDesign = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
						applicationSyllabus_CourseDesign.setSyllabus_CourseDesign(syllabus_CourseDesign);
						applicationSyllabus_CourseDesign.setCurriculum(newPracticeLesson.getCurriculum());
						applicationSyllabus_CourseDesign.setDepartment(newPracticeLesson.getDepartment());
						applicationSyllabus_CourseDesign.setProfessional(professionalList.get(i));
						applicationSyllabus_CourseDesignDao.add(applicationSyllabus_CourseDesign);
					}
				}
				
				if(professional != null && professional.size() != 0){
					for(int i=0;i<professional.size();i++){
						ApplicationSyllabus_CourseDesign applicationSyllabus_CourseDesign =  applicationSyllabus_CourseDesignDao.findappByPracAndPro(newPracticeLesson,professional.get(i));
						applicationSyllabus_CourseDesignDao.delete(applicationSyllabus_CourseDesign);
					}
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			
			ServletActionContext.getRequest().setAttribute("newPracticeLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			return "yes";
		}
		
		//删除实践课大纲——课设
		public void deletePracSyllabusCourseDesign(String syllabusId) {
			/****************************课程设计（学年论文）大纲表一对一关系-基本信息*************************************/
			Syllabus_CourseDesign copysyllabus = syllabus_CourseDesignDao.get(Integer.valueOf(syllabusId));
			if(copysyllabus != null)
			{
				syllabus_CourseDesignDao.delete(copysyllabus);
			}
			/****************************课程设计（学年论文）大纲表一对多关系-教材信息*************************************/
			List<PracticeBooks_CourseDesign> practiceBooks_CourseDesignlist = practiceBooks_CourseDesignDao.getbyPracticeBooks_CourseDesignlist(syllabusId);
			if(practiceBooks_CourseDesignlist != null && practiceBooks_CourseDesignlist.size() != 0)
			{
				for(int di=0;di<practiceBooks_CourseDesignlist.size();di++)
				{
					practiceBooks_CourseDesignDao.delete(practiceBooks_CourseDesignlist.get(di));
				}
			}			
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
			//删除对应关系表
			List<ApplicationSyllabus_CourseDesign> copyApplicationSyllabus_CourseDesignList =  applicationSyllabus_CourseDesignDao.findapplicationSyllabus_CourseDesignBySylid(String.valueOf(syllabusId));
			if(copyApplicationSyllabus_CourseDesignList != null && copyApplicationSyllabus_CourseDesignList.size() !=0)
			{
				for(int i=0;i<copyApplicationSyllabus_CourseDesignList.size();i++)
				{
					applicationSyllabus_CourseDesignDao.delete(copyApplicationSyllabus_CourseDesignList.get(i));
				}
			}
			//毕业要求与教学目标对应关系表删除
			List<AbilityAndTeachObj_CourseDesign> abilityAndTeachObj_CourseDesignList = abilityAndTeachObj_CourseDesignDao.findAbiAndTeachListBySyllabusid(syllabusId);
			if(abilityAndTeachObj_CourseDesignList != null && abilityAndTeachObj_CourseDesignList.size() !=0)
			{
				for(int i=0;i<abilityAndTeachObj_CourseDesignList.size();i++)
				{
					abilityAndTeachObj_CourseDesignDao.delete(abilityAndTeachObj_CourseDesignList.get(i));
				}
			}
			//指标点选择表删除
			List<IndexSelect_CourseDesign> indexSelect_CourseDesignList = indexSelect_CourseDesignDao.findIndexSelect_CourseDesignListBySyllabusid(syllabusId);
			if(indexSelect_CourseDesignList != null && indexSelect_CourseDesignList.size() != 0)
			{
				for(int i=0;i<indexSelect_CourseDesignList.size();i++)
				{
					indexSelect_CourseDesignDao.delete(indexSelect_CourseDesignList.get(i));
				}
			}
			//教学目标表删除
			List<TeachObj_CourseDesign> teachObj_CourseDesignList = teachObj_CourseDesignDao.findTeachObj_CourseDesignListBySyllabusid(syllabusId);
			if(teachObj_CourseDesignList != null && teachObj_CourseDesignList.size() != 0)
			{
				for(int i=0;i<teachObj_CourseDesignList.size();i++)
				{
					teachObj_CourseDesignDao.delete(teachObj_CourseDesignList.get(i));
				}
			}
			
			/****************************课程设计（学年论文）大纲表一对多关系-教学安排*************************************/
			List<DistributeHour_CourseDesign> distributeHour_CourseDesignlist = distributeHour_CourseDesignDao.getbydistributeHour_CourseDesign(syllabusId);
			if(distributeHour_CourseDesignlist != null && distributeHour_CourseDesignlist.size() != 0)
			{
				for(int di=0;di<distributeHour_CourseDesignlist.size();di++)
				{
					distributeHour_CourseDesignDao.delete(distributeHour_CourseDesignlist.get(di));
				}
			}
			ServletActionContext.getRequest().setAttribute("tag", "toCheckPracLesCourseDesignPage");
			ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
		}
			
		
		//----------------------------实验---------------------------------
		//到编辑大纲实践课——实验验证页面
		public void toCheckPracLesInnerExperimentPage(PracticeLesson practiceLesson) {
			User user = userDao.get(practiceLesson.getTeacher().getTnum());
			String zuzhixingshi = "实验";
			List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbyuser(user,zuzhixingshi);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson pracLess = allPracticeLessonlist.get(0);
				String cid = pracLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allPracticeLessonlist.size();ti++)
				{
					if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						practiceLessonList.add(allPracticeLessonlist.get(ti));
						allPracticeLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(practiceLessonList);
			}
			
			List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbyuser(user,zuzhixingshi);
			List<ApplicationSyllabus_InnerExperiment> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_InnerExperiment(newallPracticeLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson pracLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus_InnerExperiment appSyll = applicationSyllabusList.get(pk);
						if(pracLess.getProfessional() == null){
							if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(pracLess);
								professionalList.get(pi).remove(pracLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
									newhaveprofessionalList.add(pracLess);
									professionalList.get(pi).remove(pracLess);
									pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
						PracticeLesson prac = haveprofessionalList.get(ri).get(0);
						String syid =practicePlanDao.findSyllabusidByPrac_InnerExperiment(prac);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(practicePlanDao.findSyllabusidByPrac_InnerExperiment(haveprofessionalList.get(ri).get(rj))))
							{
								newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newPracticeLesson);
					}
				}
			}
			
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyuser(user,newprac,zuzhixingshi);//该老师所有应选大纲
		
				while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
					List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson pracLess = newallPracticeLesson.get(0);
					String cid = pracLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallPracticeLesson.size();ti++)
					{
						if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							practiceLessonList.add(newallPracticeLesson.get(ti));
							newallPracticeLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(practiceLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = practicePlanDao.findSyllabusidByPrac_InnerExperiment(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus_InnerExperiment> appSyllaList = new ArrayList<ApplicationSyllabus_InnerExperiment>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findAppByPrac_InnerExperiment(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_InnerExperiment(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
							{
								newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
								j--;
							}
						}
					}
				}
				}
			}
			
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
			
		}
		
		//跳转到院、校管理员查看大纲页面
		public void toCheckInnerExperimentPageAdmin(String tnum,PracticeLesson practiceLesson) {
			// TODO Auto-generated method stub
			findUserInfo(tnum);
			if(user.getAdminlevel() == 5)
			{
				collegelist = collegeDao.findAll();
			}
			else
			{
				collegelist = findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid());
			}
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
			String zuzhixingshi = "实验";
			List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbycollegeAndsearch(practiceLesson,zuzhixingshi);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson pracLess = allPracticeLessonlist.get(0);
				String cid = pracLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allPracticeLessonlist.size();ti++)
				{
					if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						practiceLessonList.add(allPracticeLessonlist.get(ti));
						allPracticeLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(practiceLessonList);
			}
			
			List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbycollegeAndsearch(practiceLesson,zuzhixingshi);
			List<ApplicationSyllabus_InnerExperiment> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_InnerExperiment(newallPracticeLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson pracLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus_InnerExperiment appSyll = applicationSyllabusList.get(pk);
						if(pracLess.getProfessional() == null){
							if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(pracLess);
								professionalList.get(pi).remove(pracLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
									newhaveprofessionalList.add(pracLess);
									professionalList.get(pi).remove(pracLess);
									pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
						PracticeLesson prac = haveprofessionalList.get(ri).get(0);
						String syid =practicePlanDao.findSyllabusidByPrac_InnerExperiment(prac);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(practicePlanDao.findSyllabusidByPrac_InnerExperiment(haveprofessionalList.get(ri).get(rj))))
							{
								newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newPracticeLesson);
					}
				}
			}
			
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyCurriculumAndDepartment(newprac,zuzhixingshi);//该老师所有应选大纲
		
				while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
					List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson pracLess = newallPracticeLesson.get(0);
					String cid = pracLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallPracticeLesson.size();ti++)
					{
						if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							practiceLessonList.add(newallPracticeLesson.get(ti));
							newallPracticeLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(practiceLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = practicePlanDao.findSyllabusidByPrac_InnerExperiment(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus_InnerExperiment> appSyllaList = new ArrayList<ApplicationSyllabus_InnerExperiment>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findAppByPrac_InnerExperiment(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_InnerExperiment(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
							{
								newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
								j--;
							}
						}
					}
				}
				}
			}
			
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
		}
		public Integer syllabus_InnerExperimentId;
		//未编辑大纲到编辑实践课——实验大纲页面
		public String toPracLesInnerExperimentPage(PracticeLesson practiceLesson,List<String> selectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			List<Professional> professionalList = new ArrayList<Professional>();
			if(selectProfessional != null && selectProfessional.size() != 0)
			{
				for(int i=0;i<selectProfessional.size();i++)
				{
					Professional professional = professionalDao.get(selectProfessional.get(i));
					professionalList.add(professional);
				}
			}
			
			//判断由于用户操作失误，在向数据库写新大纲前若数据库中存在，则先删除再写
			ApplicationSyllabus_InnerExperiment newapplicationSyllabus_InnerExperiment = new ApplicationSyllabus_InnerExperiment();
			if(professionalList != null && professionalList.size() != 0)
			{
				for(int i=0;i<professionalList.size();i++)
				{
					newapplicationSyllabus_InnerExperiment = applicationSyllabus_InnerExperimentDao.findappByPracAndPro(newPracticeLesson,professionalList.get(i));
					if(newapplicationSyllabus_InnerExperiment != null)
					{
						syllabus_InnerExperimentDao.delete(newapplicationSyllabus_InnerExperiment.getSyllabus_InnerExperiment());
					}
				}
			}
			else
			{
				newapplicationSyllabus_InnerExperiment = applicationSyllabusDao.findAppByPrac_InnerExperiment_E(newPracticeLesson);
				if(newapplicationSyllabus_InnerExperiment != null)
				{
					syllabus_InnerExperimentDao.delete(newapplicationSyllabus_InnerExperiment.getSyllabus_InnerExperiment());
				}
			}
			
			Syllabus_InnerExperiment syllabus_InnerExperiment = new Syllabus_InnerExperiment();
			syllabus_InnerExperimentDao.add(syllabus_InnerExperiment);
			syllabus_InnerExperimentId = syllabus_InnerExperiment.getSyllabus_InnerExperimentid();
			
			if(professionalList != null && professionalList.size() != 0){
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				for(int i=0;i<selectProfessional.size();i++){
					Professional professional = professionalDao.get(selectProfessional.get(i));
					Syllabus_InnerExperiment newsyllabus = syllabus_InnerExperimentDao.get(syllabus_InnerExperimentId);
					ApplicationSyllabus_InnerExperiment applicationSyllabus_InnerExperiment = new ApplicationSyllabus_InnerExperiment();
					applicationSyllabus_InnerExperiment.setSyllabus_InnerExperiment(newsyllabus);
					applicationSyllabus_InnerExperiment.setCurriculum(newPracticeLesson.getCurriculum());
					applicationSyllabus_InnerExperiment.setDepartment(newPracticeLesson.getDepartment());
					applicationSyllabus_InnerExperiment.setProfessional(professional);
					applicationSyllabus_InnerExperimentDao.add(applicationSyllabus_InnerExperiment);
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			else{
				Syllabus_InnerExperiment newsyllabus = syllabus_InnerExperimentDao.get(syllabus_InnerExperimentId);
				ApplicationSyllabus_InnerExperiment applicationSyllabus_InnerExperiment = new ApplicationSyllabus_InnerExperiment();
				applicationSyllabus_InnerExperiment.setSyllabus_InnerExperiment(newsyllabus);
				applicationSyllabus_InnerExperiment.setCurriculum(newPracticeLesson.getCurriculum());
				applicationSyllabus_InnerExperiment.setDepartment(newPracticeLesson.getDepartment());
				applicationSyllabus_InnerExperimentDao.add(applicationSyllabus_InnerExperiment);
				ServletActionContext.getRequest().setAttribute("count", 0);
			}
			
			ServletActionContext.getRequest().setAttribute("newPracticeLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabus_InnerExperiment.getSyllabus_InnerExperimentid());
			return "yes";
		}
		//未编辑大纲到复制实践课——实验大纲页面
		public void toPracLesInnerExperimentPageCopy(PracticeLesson practiceLesson, List<String> selectProfessional,String syllabusId_Copy) {
			toPracLesInnerExperimentPage(practiceLesson,selectProfessional);
			//syllabus_InnerExperimentId新建的大纲Id
			//syllabusId_Copy传过来的选择的要复制的大纲Id
			//Syllabus copysyllabus = syllabusDao.get(Integer.valueOf(syllabusId_Copy));
			//Syllabus newsyllabus = syllabusDao.get(Integer.valueOf(syllabus_InnerExperimentId));
			
			/****************************实验大纲表一对多关系-实验项目*************************************/
			List<ExpermentProject> expermentProjectlist = expermentProjectDao.getbyExpermentProject(syllabusId_Copy);
			List<ExpermentProject> expermentProjectId = new ArrayList<ExpermentProject>();
			if(expermentProjectlist != null && expermentProjectlist.size() != 0)
			{
				for(int di=0;di<expermentProjectlist.size();di++)
				{
					ExpermentProject expermentProject = expermentProjectlist.get(di);
					ExpermentProject newexpermentProject = new ExpermentProject();
					newexpermentProject.setEquipment(expermentProject.getEquipment());
					newexpermentProject.setName(expermentProject.getName());
					newexpermentProject.setNum(expermentProject.getNum());
					newexpermentProject.setRequest(expermentProject.getRequest());
					newexpermentProject.setTheory(expermentProject.getTheory());
					newexpermentProject.setTime(expermentProject.getTime());
					newexpermentProject.setSyllabus_InnerExperimentid(String.valueOf(syllabus_InnerExperimentId));
					expermentProjectDao.add(newexpermentProject);
					expermentProjectId.add(newexpermentProject);
				}
			}
			/****************************实验大纲表一对多关系-教材*************************************/
			List<PracticeBooks_InnerExperiment> practiceBooks_InnerExperimentlist = practiceBooks_InnerExperimentDao.getbyPracticeBooks_CourseDesignlist(syllabusId_Copy);
			if(practiceBooks_InnerExperimentlist != null && practiceBooks_InnerExperimentlist.size() != 0)
			{
				for(int di=0;di<practiceBooks_InnerExperimentlist.size();di++)
				{
					PracticeBooks_InnerExperiment practiceBooks_InnerExperiment = practiceBooks_InnerExperimentlist.get(di);
					PracticeBooks_InnerExperiment newpracticeBooks_InnerExperiment = new PracticeBooks_InnerExperiment();
					newpracticeBooks_InnerExperiment.setName(practiceBooks_InnerExperiment.getName());
					newpracticeBooks_InnerExperiment.setSyllabus_InnerExperimentid(String.valueOf(syllabus_InnerExperimentId));
					practiceBooks_InnerExperimentDao.add(newpracticeBooks_InnerExperiment);
				}
			}
			
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
		/*	List<ApplicationSyllabus_InnerExperiment> newApplicationSyllabus_InnerExperimentList =  applicationSyllabus_InnerExperimentDao.findapplicationSyllabus_InnerExperimentBySylid(String.valueOf(syllabus_InnerExperimentId));
			List<ApplicationSyllabus_InnerExperiment> copyApplicationSyllabus_InnerExperimentList =  applicationSyllabus_InnerExperimentDao.findapplicationSyllabus_InnerExperimentBySylid(String.valueOf(syllabusId_Copy));
			if(newApplicationSyllabus_InnerExperimentList != null && newApplicationSyllabus_InnerExperimentList.size() != 0 && copyApplicationSyllabus_InnerExperimentList != null && copyApplicationSyllabus_InnerExperimentList.size() !=0)
			{
				if(newApplicationSyllabus_InnerExperimentList.get(0).getDepartment() == copyApplicationSyllabus_InnerExperimentList.get(0).getDepartment())
				{//相同专业才进行复制
					//指标点选择表复制
					List<IndexSelect_InnerExperiment> indexSelect_InnerExperimentList = indexSelect_InnerExperimentDao.findIndexSelect_InnerExperimentListBySyllabusid(syllabusId_Copy);
					if(indexSelect_InnerExperimentList != null && indexSelect_InnerExperimentList.size() != 0)
					{
						for(int i=0;i<indexSelect_InnerExperimentList.size();i++)
						{
							IndexSelect_InnerExperiment indexSelect_InnerExperiment = indexSelect_InnerExperimentList.get(i);
							IndexSelect_InnerExperiment newindexSelect_InnerExperiment = new IndexSelect_InnerExperiment();
							newindexSelect_InnerExperiment.setAbility(indexSelect_InnerExperiment.getAbility());
							newindexSelect_InnerExperiment.setAnalisis(indexSelect_InnerExperiment.getAnalisis());
							newindexSelect_InnerExperiment.setSyllabus_InnerExperimentid(String.valueOf(syllabus_InnerExperimentId));
							indexSelect_InnerExperimentDao.add(newindexSelect_InnerExperiment);
						}
					}
					//教学目标表复制
					List<TeachObj_InnerExperiment> teachObj_InnerExperimentList = teachObj_InnerExperimentDao.findTeachObj_InnerExperimentListBySyllabusid(syllabusId_Copy);
					List<TeachObj_InnerExperiment> newTeachObj_InnerExperimentList = new ArrayList<TeachObj_InnerExperiment>();
					if(teachObj_InnerExperimentList != null && teachObj_InnerExperimentList.size() != 0)
					{
						for(int i=0;i<teachObj_InnerExperimentList.size();i++)
						{
							TeachObj_InnerExperiment teachObj_InnerExperiment = teachObj_InnerExperimentList.get(i);
							TeachObj_InnerExperiment newteachObj_InnerExperiment = new TeachObj_InnerExperiment();
							newteachObj_InnerExperiment.setTeachObjContent_InnerExperiment(teachObj_InnerExperiment.getTeachObjContent_InnerExperiment());
							newteachObj_InnerExperiment.setSyllabus_InnerExperimentid(String.valueOf(syllabus_InnerExperimentId));
							teachObj_InnerExperimentDao.add(newteachObj_InnerExperiment);
							newTeachObj_InnerExperimentList.add(newteachObj_InnerExperiment);
						}
					}
					//毕业要求与教学目标对应关系表复制
					List<AbilityAndTeachObj_InnerExperiment> abilityAndTeachObj_InnerExperimentList = abilityAndTeachObj_InnerExperimentDao.findAbiAndTeachListBySyllabusid(syllabusId_Copy);
					if(abilityAndTeachObj_InnerExperimentList != null && abilityAndTeachObj_InnerExperimentList.size() !=0)
					{
						for(int i=0;i<abilityAndTeachObj_InnerExperimentList.size();i++)
						{
							AbilityAndTeachObj_InnerExperiment abilityAndTeachObj_InnerExperiment = abilityAndTeachObj_InnerExperimentList.get(i);
							AbilityAndTeachObj_InnerExperiment newabilityAndTeachObj_InnerExperiment = new AbilityAndTeachObj_InnerExperiment();
							newabilityAndTeachObj_InnerExperiment.setAbility(abilityAndTeachObj_InnerExperiment.getAbility());
							newabilityAndTeachObj_InnerExperiment.setTeachObj_InnerExperiment(newTeachObj_InnerExperimentList.get(teachObj_InnerExperimentList.indexOf(abilityAndTeachObj_InnerExperiment.getTeachObj_InnerExperiment())));
							newabilityAndTeachObj_InnerExperiment.setSyllabus_InnerExperimentid(String.valueOf(syllabus_InnerExperimentId));
							abilityAndTeachObj_InnerExperimentDao.add(newabilityAndTeachObj_InnerExperiment);
						}
					}
				}
			}*/
			/********************************实验大纲表一对多关系-教学安排****************************************/
			List<DistributeHour_InnerExperiment> distributeHour_InnerExperimentlist = distributeHour_InnerExperimentDao.getbydistributeHour_InnerExperiment(syllabusId_Copy);
			List<DistributeHour_InnerExperiment> distributeHour_InnerExperimentsId = new ArrayList<DistributeHour_InnerExperiment>();
			if(distributeHour_InnerExperimentlist != null && distributeHour_InnerExperimentlist.size() != 0)
			{
				for(int di=0;di<distributeHour_InnerExperimentlist.size();di++)
				{
					DistributeHour_InnerExperiment distributeHour_InnerExperiment = distributeHour_InnerExperimentlist.get(di);
					DistributeHour_InnerExperiment newdistributeHour_InnerExperiment = new DistributeHour_InnerExperiment();
					newdistributeHour_InnerExperiment.setExp(distributeHour_InnerExperiment.getExp());
					newdistributeHour_InnerExperiment.setHoursOfClass(distributeHour_InnerExperiment.getHoursOfClass());
					newdistributeHour_InnerExperiment.setHoursOfExp(distributeHour_InnerExperiment.getHoursOfExp());
					newdistributeHour_InnerExperiment.setName(distributeHour_InnerExperiment.getName());
					newdistributeHour_InnerExperiment.setSyllabusid(String.valueOf(syllabus_InnerExperimentId));
					distributeHour_InnerExperimentDao.add(newdistributeHour_InnerExperiment);
					distributeHour_InnerExperimentsId.add(newdistributeHour_InnerExperiment);
				}
			}
			//将复制到实践课课内实验大纲的学时分配和实验内容关联
			for (int i = 0; i < distributeHour_InnerExperimentsId.size(); i++) {
				DistributeHourRelateExperProject practice_relate = new DistributeHourRelateExperProject();
				practice_relate.setInnerExperiment(distributeHour_InnerExperimentsId.get(i));
				practice_relate.setExpermentProject(expermentProjectId.get(i));
				practicerelateDao.add(practice_relate);
			}
		}
		//已编辑大纲到编辑实践课——实验大纲页面
		public String toHavePracLesInnerExperimentPage(String syllabusId,PracticeLesson practiceLesson,List<String> haveselectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			
			if(haveselectProfessional != null && haveselectProfessional.size() != 0){
				List<Professional> professionalList = new ArrayList<Professional>();
				for(int i=0;i<haveselectProfessional.size();i++){//将勾选的专业方向id查到实体放入List
					Professional professional = professionalDao.get(haveselectProfessional.get(i));
					professionalList.add(professional);
				}
				
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				
				//
				List<ApplicationSyllabus_InnerExperiment> applicationSyllabus_InnerExperimentList = applicationSyllabus_InnerExperimentDao.findapplicationSyllabus_InnerExperimentBySylid(syllabusId);//通过大纲id查询该大纲的所有应用专业
				List<Professional> professional = new ArrayList<Professional>();
				if(applicationSyllabus_InnerExperimentList != null && applicationSyllabus_InnerExperimentList.size() != 0){
					for(int i=0;i<applicationSyllabus_InnerExperimentList.size();i++){
						professional.add(applicationSyllabus_InnerExperimentList.get(i).getProfessional());
					}
				}
				
				for(int i=0;i<professionalList.size();i++){
					if(professional.contains(professionalList.get(i))){
						professional.remove(professionalList.get(i));
					}
					else{
						ApplicationSyllabus_InnerExperiment applicationSyllabus_InnerExperiment = new ApplicationSyllabus_InnerExperiment();
						Syllabus_InnerExperiment syllabus_InnerExperiment = syllabus_InnerExperimentDao.get(Integer.valueOf(syllabusId));
						applicationSyllabus_InnerExperiment.setSyllabus_InnerExperiment(syllabus_InnerExperiment);
						applicationSyllabus_InnerExperiment.setCurriculum(newPracticeLesson.getCurriculum());
						applicationSyllabus_InnerExperiment.setDepartment(newPracticeLesson.getDepartment());
						applicationSyllabus_InnerExperiment.setProfessional(professionalList.get(i));
						applicationSyllabus_InnerExperimentDao.add(applicationSyllabus_InnerExperiment);
					}
				}
				
				if(professional != null && professional.size() != 0){
					for(int i=0;i<professional.size();i++){
						ApplicationSyllabus_InnerExperiment applicationSyllabus_InnerExperiment =  applicationSyllabus_InnerExperimentDao.findappByPracAndPro(newPracticeLesson,professional.get(i));
						applicationSyllabus_InnerExperimentDao.delete(applicationSyllabus_InnerExperiment);
					}
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			ServletActionContext.getRequest().setAttribute("newPracticeLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			return "yes";
		}
		//删除实践课大纲——实验
		public void deletePracSyllabusInnerExperiment(String syllabusId) {
			Syllabus copysyllabus = syllabusDao.get(Integer.valueOf(syllabusId));
			if(copysyllabus != null)
			{
				syllabusDao.delete(copysyllabus);
			}
			/****************************实验大纲表一对多关系-实验项目*************************************/
			List<ExpermentProject> expermentProjectlist = expermentProjectDao.getbyExpermentProject(syllabusId);
			if(expermentProjectlist != null && expermentProjectlist.size() != 0)
			{
				for(int di=0;di<expermentProjectlist.size();di++)
				{
					expermentProjectDao.delete(expermentProjectlist.get(di));
				}
			}
			/****************************实验大纲表一对多关系-教材*************************************/
			List<PracticeBooks_InnerExperiment> practiceBooks_InnerExperimentlist = practiceBooks_InnerExperimentDao.getbyPracticeBooks_CourseDesignlist(syllabusId);
			if(practiceBooks_InnerExperimentlist != null && practiceBooks_InnerExperimentlist.size() != 0)
			{
				for(int di=0;di<practiceBooks_InnerExperimentlist.size();di++)
				{
					practiceBooks_InnerExperimentDao.delete(practiceBooks_InnerExperimentlist.get(di));
				}
			}
			
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
			//删除对应关系表
			List<ApplicationSyllabus_InnerExperiment> copyApplicationSyllabus_InnerExperimentList =  applicationSyllabus_InnerExperimentDao.findapplicationSyllabus_InnerExperimentBySylid(String.valueOf(syllabusId));
			if(copyApplicationSyllabus_InnerExperimentList != null && copyApplicationSyllabus_InnerExperimentList.size() !=0)
			{
				for(int i=0;i<copyApplicationSyllabus_InnerExperimentList.size();i++)
				{
					applicationSyllabus_InnerExperimentDao.delete(copyApplicationSyllabus_InnerExperimentList.get(i));
				}
			}
			//毕业要求与教学目标对应关系表删除
			List<AbilityAndTeachObj_InnerExperiment> abilityAndTeachObj_InnerExperimentList = abilityAndTeachObj_InnerExperimentDao.findAbiAndTeachListBySyllabusid(syllabusId);
			if(abilityAndTeachObj_InnerExperimentList != null && abilityAndTeachObj_InnerExperimentList.size() !=0)
			{
				for(int i=0;i<abilityAndTeachObj_InnerExperimentList.size();i++)
				{
					abilityAndTeachObj_InnerExperimentDao.delete(abilityAndTeachObj_InnerExperimentList.get(i));
				}
			}
			//指标点选择表删除
			List<IndexSelect_InnerExperiment> indexSelect_InnerExperimentList = indexSelect_InnerExperimentDao.findIndexSelect_InnerExperimentListBySyllabusid(syllabusId);
			if(indexSelect_InnerExperimentList != null && indexSelect_InnerExperimentList.size() != 0)
			{
				for(int i=0;i<indexSelect_InnerExperimentList.size();i++)
				{
					indexSelect_InnerExperimentDao.delete(indexSelect_InnerExperimentList.get(i));
				}
			}
			//教学目标表删除
			List<TeachObj_InnerExperiment> teachObj_InnerExperimentList = teachObj_InnerExperimentDao.findTeachObj_InnerExperimentListBySyllabusid(syllabusId);
			if(teachObj_InnerExperimentList != null && teachObj_InnerExperimentList.size() != 0)
			{
				for(int i=0;i<teachObj_InnerExperimentList.size();i++)
				{
					teachObj_InnerExperimentDao.delete(teachObj_InnerExperimentList.get(i));
				}
			}
			/********************************实验大纲表一对多关系-教学安排****************************************/
			List<DistributeHour_InnerExperiment> distributeHour_InnerExperimentlist = distributeHour_InnerExperimentDao.getbydistributeHour_InnerExperiment(syllabusId);
			if(distributeHour_InnerExperimentlist != null && distributeHour_InnerExperimentlist.size() != 0)
			{
				for(int di=0;di<distributeHour_InnerExperimentlist.size();di++)
				{
					distributeHour_InnerExperimentDao.delete(distributeHour_InnerExperimentlist.get(di));
				}
			}
		
			ServletActionContext.getRequest().setAttribute("tag", "toCheckPracLesInnerExperimentPage");
			ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
		}
			
			
		
		//----------------------------毕业设计---------------------------------
		//到编辑大纲实践课——毕业设计验证页面
		public void toCheckPracLesGraduationProjectPage(PracticeLesson practiceLesson) {
			User user = userDao.get(practiceLesson.getTeacher().getTnum());
			String zuzhixingshi = "毕业设计";
			List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbyuser(user,zuzhixingshi);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson pracLess = allPracticeLessonlist.get(0);
				String cid = pracLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allPracticeLessonlist.size();ti++)
				{
					if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						practiceLessonList.add(allPracticeLessonlist.get(ti));
						allPracticeLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(practiceLessonList);
			}
			
			List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbyuser(user,zuzhixingshi);
			List<ApplicationSyllabus_Gra> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_Gra(newallPracticeLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson pracLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus_Gra appSyll = applicationSyllabusList.get(pk);
						if(pracLess.getProfessional() == null){
							if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(pracLess);
								professionalList.get(pi).remove(pracLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
									newhaveprofessionalList.add(pracLess);
									professionalList.get(pi).remove(pracLess);
									pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
						PracticeLesson prac = haveprofessionalList.get(ri).get(0);
						String syid =practicePlanDao.findSyllabusidByPrac_Gra(prac);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(practicePlanDao.findSyllabusidByPrac_Gra(haveprofessionalList.get(ri).get(rj))))
							{
								newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newPracticeLesson);
					}
				}
			}
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyuser(user,newprac,zuzhixingshi);//该老师所有应选大纲
		
				while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
					List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson pracLess = newallPracticeLesson.get(0);
					String cid = pracLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallPracticeLesson.size();ti++)
					{
						if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							practiceLessonList.add(newallPracticeLesson.get(ti));
							newallPracticeLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(practiceLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = practicePlanDao.findSyllabusidByPrac_Gra(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus_Gra> appSyllaList = new ArrayList<ApplicationSyllabus_Gra>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findAppByPrac_Gra(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_Gra(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
							{
								newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
								j--;
							}
						}
					}
				}
				}
			}
			
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
		}
		
		//跳转到院、校管理员查看大纲页面
		public void toCheckGraduationProjectPageAdmin(String tnum,PracticeLesson practiceLesson) {
			// TODO Auto-generated method stub
			findUserInfo(tnum);
			if(user.getAdminlevel() == 5)
			{
				collegelist = collegeDao.findAll();
			}
			else
			{
				collegelist = findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid());
			}
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
			String zuzhixingshi = "毕业设计";
			List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbycollegeAndsearch(practiceLesson,zuzhixingshi);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson pracLess = allPracticeLessonlist.get(0);
				String cid = pracLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allPracticeLessonlist.size();ti++)
				{
					if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						practiceLessonList.add(allPracticeLessonlist.get(ti));
						allPracticeLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(practiceLessonList);
			}
			
			List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbycollegeAndsearch(practiceLesson,zuzhixingshi);
			List<ApplicationSyllabus_Gra> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_Gra(newallPracticeLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson pracLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus_Gra appSyll = applicationSyllabusList.get(pk);
						if(pracLess.getProfessional() == null){
							if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(pracLess);
								professionalList.get(pi).remove(pracLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
									newhaveprofessionalList.add(pracLess);
									professionalList.get(pi).remove(pracLess);
									pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
						PracticeLesson prac = haveprofessionalList.get(ri).get(0);
						String syid =practicePlanDao.findSyllabusidByPrac_Gra(prac);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(practicePlanDao.findSyllabusidByPrac_Gra(haveprofessionalList.get(ri).get(rj))))
							{
								newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newPracticeLesson);
					}
				}
			}
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyCurriculumAndDepartment(newprac,zuzhixingshi);//该老师所有应选大纲
		
				while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
					List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson pracLess = newallPracticeLesson.get(0);
					String cid = pracLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallPracticeLesson.size();ti++)
					{
						if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							practiceLessonList.add(newallPracticeLesson.get(ti));
							newallPracticeLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(practiceLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = practicePlanDao.findSyllabusidByPrac_Gra(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus_Gra> appSyllaList = new ArrayList<ApplicationSyllabus_Gra>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findAppByPrac_Gra(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_Gra(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
							{
								newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
								j--;
							}
						}
					}
				}
				}
			}
			
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
		}
		
		//未编辑大纲到编辑实践课——毕业设计大纲页面
		public Integer syllabus_GraId;
		public String toPracLesGraduationProjectPage(PracticeLesson practiceLesson, List<String> selectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			List<Professional> professionalList = new ArrayList<Professional>();
			if(selectProfessional != null && selectProfessional.size() != 0)
			{
				for(int i=0;i<selectProfessional.size();i++)
				{
					Professional professional = professionalDao.get(selectProfessional.get(i));
					professionalList.add(professional);
				}
			}
			
			//判断由于用户操作失误，在向数据库写新大纲前若数据库中存在，则先删除再写
			ApplicationSyllabus_Gra newapplicationSyllabus_Gra = new ApplicationSyllabus_Gra();
			if(professionalList != null && professionalList.size() != 0)
			{
				for(int i=0;i<professionalList.size();i++)
				{
					newapplicationSyllabus_Gra = applicationSyllabus_GraDao.findappByPracAndPro(newPracticeLesson,professionalList.get(i));
					if(newapplicationSyllabus_Gra != null)
					{
						syllabus_GraDao.delete(newapplicationSyllabus_Gra.getSyllabus_Gra());
					}
				}
			}
			else
			{
				newapplicationSyllabus_Gra = applicationSyllabusDao.findAppByPrac_Gra_E(newPracticeLesson);
				if(newapplicationSyllabus_Gra != null)
				{
					syllabus_GraDao.delete(newapplicationSyllabus_Gra.getSyllabus_Gra());
				}
			}
			
			Syllabus_Gra syllabus_Gra = new Syllabus_Gra();
			syllabus_GraDao.add(syllabus_Gra);
			syllabus_GraId = syllabus_Gra.getSyllabus_Graid();
			
			if(professionalList != null && professionalList.size() != 0){
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				for(int i=0;i<selectProfessional.size();i++){
					Professional professional = professionalDao.get(selectProfessional.get(i));
					Syllabus_Gra newsyllabus = syllabus_GraDao.get(syllabus_GraId);
					ApplicationSyllabus_Gra applicationSyllabus_Gra = new ApplicationSyllabus_Gra();
					applicationSyllabus_Gra.setSyllabus_Gra(newsyllabus);
					applicationSyllabus_Gra.setCurriculum(newPracticeLesson.getCurriculum());
					applicationSyllabus_Gra.setDepartment(newPracticeLesson.getDepartment());
					applicationSyllabus_Gra.setProfessional(professional);
					applicationSyllabus_GraDao.add(applicationSyllabus_Gra);
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			else{
				Syllabus_Gra newsyllabus = syllabus_GraDao.get(syllabus_GraId);
				ApplicationSyllabus_Gra applicationSyllabus_Gra = new ApplicationSyllabus_Gra();
				applicationSyllabus_Gra.setSyllabus_Gra(newsyllabus);
				applicationSyllabus_Gra.setCurriculum(newPracticeLesson.getCurriculum());
				applicationSyllabus_Gra.setDepartment(newPracticeLesson.getDepartment());
				applicationSyllabus_GraDao.add(applicationSyllabus_Gra);;
				ServletActionContext.getRequest().setAttribute("count", 0);
			}
			ServletActionContext.getRequest().setAttribute("newPracticeLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabus_Gra.getSyllabus_Graid());
			return "yes";
		}
		
		//未编辑大纲到复制实践课——毕业设计大纲页面
		public void toPracLesGraduationProjectPageCopy(PracticeLesson practiceLesson, List<String> selectProfessional,String syllabusId_Copy) {
			toPracLesGraduationProjectPage(practiceLesson,selectProfessional);
			//syllabus_GraId新建的大纲Id
			//syllabusId_Copy传过来的选择的要复制的大纲Id
			Syllabus_Gra copysyllabus = syllabus_GraDao.get(Integer.valueOf(syllabusId_Copy));
			Syllabus_Gra newsyllabus = syllabus_GraDao.get(Integer.valueOf(syllabus_GraId));
			
			/**********************************毕业设计大纲一对一 基本信息*********************************************************/
			newsyllabus.setBasePractice(copysyllabus.getBasePractice());
			newsyllabus.setContentGra(copysyllabus.getContentGra());
			syllabus_GraDao.update(newsyllabus);
			/**********************************毕业设计大纲一对多教材信息*********************************************************/
			List<PracticeBook> practiceBooklist = practiceBookDao.getbytextBooks(String.valueOf(syllabus_GraId));
			if(practiceBooklist != null && practiceBooklist.size() != 0)
			{
				for(int di=0;di<practiceBooklist.size();di++)
				{
					PracticeBook practiceBook = practiceBooklist.get(di);
					PracticeBook newpracticeBook = new PracticeBook();
					newpracticeBook.setName(practiceBook.getName());
					newpracticeBook.setSyllabus_Graid(String.valueOf(syllabus_GraId));
					practiceBookDao.add(newpracticeBook);
				}
			}
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
		/*	List<ApplicationSyllabus_Gra> newApplicationSyllabus_GraList =  applicationSyllabus_GraDao.findapplicationSyllabus_GraBySylid(String.valueOf(syllabus_GraId));
			List<ApplicationSyllabus_Gra> copyApplicationSyllabus_GraList =  applicationSyllabus_GraDao.findapplicationSyllabus_GraBySylid(String.valueOf(syllabusId_Copy));
			if(newApplicationSyllabus_GraList != null && newApplicationSyllabus_GraList.size() != 0 && copyApplicationSyllabus_GraList != null && copyApplicationSyllabus_GraList.size() !=0)
			{
				if(newApplicationSyllabus_GraList.get(0).getDepartment() == copyApplicationSyllabus_GraList.get(0).getDepartment())
				{//相同专业才进行复制
					//指标点选择表复制
					List<IndexSelect_Gra> indexSelect_GraList = indexSelect_GraDao.findIndexSelect_GraListBySyllabusid(syllabusId_Copy);
					if(indexSelect_GraList != null && indexSelect_GraList.size() != 0)
					{
						for(int i=0;i<indexSelect_GraList.size();i++)
						{
							IndexSelect_Gra indexSelect_Gra = indexSelect_GraList.get(i);
							IndexSelect_Gra newindexSelect_Gra = new IndexSelect_Gra();
							newindexSelect_Gra.setAbility(indexSelect_Gra.getAbility());
							newindexSelect_Gra.setAnalisis(indexSelect_Gra.getAnalisis());
							newindexSelect_Gra.setSyllabus_Graid(String.valueOf(syllabus_GraId));
							indexSelect_GraDao.add(newindexSelect_Gra);
						}
					}
					//教学目标表复制
					List<TeachObj_Gra> teachObj_GraList = teachObj_GraDao.findTeachObj_GraListBySyllabusid(syllabusId_Copy);
					List<TeachObj_Gra> newTeachObj_GraList = new ArrayList<TeachObj_Gra>();
					if(teachObj_GraList != null && teachObj_GraList.size() != 0)
					{
						for(int i=0;i<teachObj_GraList.size();i++)
						{
							TeachObj_Gra teachObj_Gra = teachObj_GraList.get(i);
							TeachObj_Gra newteachObj_Gra = new TeachObj_Gra();
							newteachObj_Gra.setTeachObjContent_Gra(teachObj_Gra.getTeachObjContent_Gra());
							newteachObj_Gra.setSyllabus_Graid(String.valueOf(syllabus_GraId));
							teachObj_GraDao.add(newteachObj_Gra);
							newTeachObj_GraList.add(newteachObj_Gra);
						}
					}
					//毕业要求与教学目标对应关系表复制
					List<AbilityAndTeachObj_Gra> abilityAndTeachObj_GraList = abilityAndTeachObj_GraDao.findAbiAndTeachListBySyllabusid(syllabusId_Copy);
					if(abilityAndTeachObj_GraList != null && abilityAndTeachObj_GraList.size() !=0)
					{
						for(int i=0;i<abilityAndTeachObj_GraList.size();i++)
						{
							AbilityAndTeachObj_Gra abilityAndTeachObj_Gra = abilityAndTeachObj_GraList.get(i);
							AbilityAndTeachObj_Gra newabilityAndTeachObj_Gra = new AbilityAndTeachObj_Gra();
							newabilityAndTeachObj_Gra.setAbility(abilityAndTeachObj_Gra.getAbility());
							newabilityAndTeachObj_Gra.setTeachObj_Gra(newTeachObj_GraList.get(teachObj_GraList.indexOf(abilityAndTeachObj_Gra.getTeachObj_Gra())));
							newabilityAndTeachObj_Gra.setSyllabus_Graid(String.valueOf(syllabus_GraId));
							abilityAndTeachObj_GraDao.add(newabilityAndTeachObj_Gra);
						}
					}
				}
			}*/
			/********************************毕业设计大纲表一对多关系-教学安排****************************************/
			List<DistributeHour_Gra> distributeHour_Gralist = distributeHour_GraDao.getbyDistributeHour_Gra(syllabusId_Copy);
			if(distributeHour_Gralist != null && distributeHour_Gralist.size() != 0)
			{
				for(int di=0;di<distributeHour_Gralist.size();di++)
				{
					DistributeHour_Gra distributeHour_Gra = distributeHour_Gralist.get(di);
					DistributeHour_Gra newdistributeHour_Gra = new DistributeHour_Gra();
					newdistributeHour_Gra.setName(distributeHour_Gra.getName());
					newdistributeHour_Gra.setTime(distributeHour_Gra.getTime());
					newdistributeHour_Gra.setSyllabusid(String.valueOf(syllabus_GraId));
					distributeHour_GraDao.add(newdistributeHour_Gra);
				}
			}
		}
		
		//已编辑大纲到编辑实践课——毕业设计大纲页面
		public String toHavePracLesGraduationProjectPage(String syllabusId,PracticeLesson practiceLesson,List<String> haveselectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			
			if(haveselectProfessional != null && haveselectProfessional.size() != 0){
				List<Professional> professionalList = new ArrayList<Professional>();
				for(int i=0;i<haveselectProfessional.size();i++){//将勾选的专业方向id查到实体放入List
					Professional professional = professionalDao.get(haveselectProfessional.get(i));
					professionalList.add(professional);
				}
				
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				//
				List<ApplicationSyllabus_Gra> applicationSyllabus_GraList = applicationSyllabus_GraDao.findapplicationSyllabus_GraBySylid(syllabusId);//通过大纲id查询该大纲的所有应用专业
				List<Professional> professional = new ArrayList<Professional>();
				if(applicationSyllabus_GraList != null && applicationSyllabus_GraList.size() != 0){
					for(int i=0;i<applicationSyllabus_GraList.size();i++){
						professional.add(applicationSyllabus_GraList.get(i).getProfessional());
					}
				}
				
				for(int i=0;i<professionalList.size();i++){
					if(professional.contains(professionalList.get(i))){
						professional.remove(professionalList.get(i));
					}
					else{
						ApplicationSyllabus_Gra applicationSyllabus_Gra = new ApplicationSyllabus_Gra();
						Syllabus_Gra syllabus_Gra = syllabus_GraDao.get(Integer.valueOf(syllabusId));
						applicationSyllabus_Gra.setSyllabus_Gra(syllabus_Gra);
						applicationSyllabus_Gra.setCurriculum(newPracticeLesson.getCurriculum());
						applicationSyllabus_Gra.setDepartment(newPracticeLesson.getDepartment());
						applicationSyllabus_Gra.setProfessional(professionalList.get(i));
						applicationSyllabus_GraDao.add(applicationSyllabus_Gra);
					}
				}
				
				if(professional != null && professional.size() != 0){
					for(int i=0;i<professional.size();i++){
						ApplicationSyllabus_Gra applicationSyllabus_Gra =  applicationSyllabus_GraDao.findappByPracAndPro(newPracticeLesson,professional.get(i));
						applicationSyllabus_GraDao.delete(applicationSyllabus_Gra);
					}
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			ServletActionContext.getRequest().setAttribute("newPracticeLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			return "yes";
		}
		//删除实践课大纲——毕业设计
		public void deletePracSyllabusGraduationProject(String syllabusId) {
			/**********************************毕业设计大纲一对一 基本信息*********************************************************/
			Syllabus_Gra copysyllabus = syllabus_GraDao.get(Integer.valueOf(syllabusId));
			if(copysyllabus != null)
			{
				syllabus_GraDao.delete(copysyllabus);
			}
			/**********************************毕业设计大纲一对多教材信息*********************************************************/
			List<PracticeBook> practiceBooklist = practiceBookDao.getbytextBooks(String.valueOf(syllabusId));
			if(practiceBooklist != null && practiceBooklist.size() != 0)
			{
				for(int di=0;di<practiceBooklist.size();di++)
				{
					practiceBookDao.delete(practiceBooklist.get(di));
				}
			}
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
			//删除对应关系表
			List<ApplicationSyllabus_Gra> copyApplicationSyllabus_GraList =  applicationSyllabus_GraDao.findapplicationSyllabus_GraBySylid(String.valueOf(syllabusId));
			if(copyApplicationSyllabus_GraList != null && copyApplicationSyllabus_GraList.size() !=0)
			{
				for(int i=0;i<copyApplicationSyllabus_GraList.size();i++)
				{
					applicationSyllabus_GraDao.delete(copyApplicationSyllabus_GraList.get(i));
				}
			}
			//毕业要求与教学目标对应关系表删除
			List<AbilityAndTeachObj_Gra> abilityAndTeachObj_GraList = abilityAndTeachObj_GraDao.findAbiAndTeachListBySyllabusid(syllabusId);
			if(abilityAndTeachObj_GraList != null && abilityAndTeachObj_GraList.size() !=0)
			{
				for(int i=0;i<abilityAndTeachObj_GraList.size();i++)
				{
					abilityAndTeachObj_GraDao.delete(abilityAndTeachObj_GraList.get(i));
				}
			}
			//指标点选择表删除
			List<IndexSelect_Gra> indexSelect_GraList = indexSelect_GraDao.findIndexSelect_GraListBySyllabusid(syllabusId);
			if(indexSelect_GraList != null && indexSelect_GraList.size() != 0)
			{
				for(int i=0;i<indexSelect_GraList.size();i++)
				{
					indexSelect_GraDao.delete(indexSelect_GraList.get(i));
				}
			}
			//教学目标表删除
			List<TeachObj_Gra> teachObj_GraList = teachObj_GraDao.findTeachObj_GraListBySyllabusid(syllabusId);
			if(teachObj_GraList != null && teachObj_GraList.size() != 0)
			{
				for(int i=0;i<teachObj_GraList.size();i++)
				{
					teachObj_GraDao.delete(teachObj_GraList.get(i));
				}
			}
			/********************************毕业设计大纲表一对多关系-教学安排****************************************/
			List<DistributeHour_Gra> distributeHour_Gralist = distributeHour_GraDao.getbyDistributeHour_Gra(syllabusId);
			if(distributeHour_Gralist != null && distributeHour_Gralist.size() != 0)
			{
				for(int di=0;di<distributeHour_Gralist.size();di++)
				{
					distributeHour_GraDao.delete(distributeHour_Gralist.get(di));
				}
			}
			
			ServletActionContext.getRequest().setAttribute("tag", "toCheckPracLesGraduationProjectPage");
			ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
		}
		public void setid(PracticeLesson practiceLesson) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			ServletActionContext.getRequest().setAttribute("departmentid", newPracticeLesson.getDepartment().getDepartmentid());
			ServletActionContext.getRequest().setAttribute("collegeid", newPracticeLesson.getDepartment().getCollege().getCollegeid());			
		}

		public void findtheolen2(Integer currentpage,PracticeLesson practiceLesson, String departmenttag) {
			//在pageBean中设置college使得条件查询可执行
			//找到学院开设的课程
			List<Curriculum> list=practicePlanDao.findcurrbycollege(practiceLesson.getCurriculum().getCollege());
			for(int i=0;i<list.size();i++){
				//找到了课1对应的列表
				List<PracticeLesson> theolenlist = practicePlanDao.findtheolenbycurr(list.get(i));
				if(theolenlist.size()==0){
					
				}
				else if(theolenlist.size()==1){
					//判断是否未分配
					if(theolenlist.get(0).getTeachDepartment()==null && theolenlist.get(0).getTeacher()==null && theolenlist.get(0).getExperiment()==null && theolenlist.get(0).getExperimenter()==null){
						PracticeLesson theoreticalLen=theolenlist.get(0);
						theoreticalLen.setTeachDepartment(theolenlist.get(0).getDepartment());
						practicePlanDao.update(theoreticalLen);
					}
				}
				else{
					int flag=0;
					for(int k=1;k<theolenlist.size();k++){
						if(!theolenlist.get(k).getDepartment().equals(theolenlist.get(0).getDepartment())){
							flag=1;
							break;
						}
					}
					if(flag==0){
						for(int k=0;k<theolenlist.size();k++){
							//判断是否未分配
							if(theolenlist.get(k).getTeachDepartment()==null && theolenlist.get(0).getTeacher()==null && theolenlist.get(0).getExperiment()==null && theolenlist.get(0).getExperimenter()==null){
								PracticeLesson theoreticalLen=theolenlist.get(k);
								theoreticalLen.setTeachDepartment(theoreticalLen.getDepartment());
								practicePlanDao.update(theoreticalLen);
							}
						}
					}
				}
			}
		}
		//其他大纲
		/**********************其他，未完成跳到编辑页面************************/
		public Integer syllabusId;
		public String toPracLesOtherPage(PracticeLesson practiceLesson,List<String> selectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			List<Professional> professionalList = new ArrayList<Professional>();
			if(selectProfessional != null && selectProfessional.size() != 0)
			{
				for(int i=0;i<selectProfessional.size();i++)
				{
					Professional professional = professionalDao.get(selectProfessional.get(i));
					professionalList.add(professional);
				}
			}
			
			//判断由于用户操作失误，在向数据库写新大纲前若数据库中存在，则先删除再写
			ApplicationSyllabus newapplicationSyllabus = new ApplicationSyllabus();
			if(professionalList != null && professionalList.size() != 0)
			{
				for(int i=0;i<professionalList.size();i++)
				{
					newapplicationSyllabus = applicationSyllabusDao.findappByPracAndPro(newPracticeLesson,professionalList.get(i));
					if(newapplicationSyllabus != null)
					{
						syllabusDao.delete(newapplicationSyllabus.getSyllabus());
					}
				}
			}
			else
			{
				newapplicationSyllabus = applicationSyllabusDao.findAppByPrac_E(newPracticeLesson);
				if(newapplicationSyllabus != null)
				{
					syllabusDao.delete(newapplicationSyllabus.getSyllabus());
				}
			}
			
			Syllabus syllabus = new Syllabus();
			syllabusDao.add(syllabus);
			syllabusId = syllabus.getSyllabusid();
			
			if(professionalList != null && professionalList.size() != 0){
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				for(int i=0;i<selectProfessional.size();i++){
					Professional professional = professionalDao.get(selectProfessional.get(i));
					Syllabus newsyllabus = syllabusDao.get(syllabusId);
					ApplicationSyllabus applicationSyllabus = new ApplicationSyllabus();
					applicationSyllabus.setSyllabus(newsyllabus);
					applicationSyllabus.setCurriculum(newPracticeLesson.getCurriculum());
					applicationSyllabus.setDepartment(newPracticeLesson.getDepartment());
					applicationSyllabus.setProfessional(professional);
					applicationSyllabusDao.add(applicationSyllabus);
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			else{
				Syllabus newsyllabus = syllabusDao.get(syllabusId);
				ApplicationSyllabus applicationSyllabus = new ApplicationSyllabus();
				applicationSyllabus.setSyllabus(newsyllabus);
				applicationSyllabus.setCurriculum(newPracticeLesson.getCurriculum());
				applicationSyllabus.setDepartment(newPracticeLesson.getDepartment());
				applicationSyllabusDao.add(applicationSyllabus);
				ServletActionContext.getRequest().setAttribute("count", 0);
			}
			ServletActionContext.getRequest().setAttribute("newTheoreticalLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabus.getSyllabusid());
			return "yes";
			
		}
		public String toHavePracLesOtherPage(String syllabusId2,PracticeLesson practiceLesson,List<String> haveselectProfessional) {
			PracticeLesson newPracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			
			if(haveselectProfessional != null && haveselectProfessional.size() != 0){
				List<Professional> professionalList = new ArrayList<Professional>();
				for(int i=0;i<haveselectProfessional.size();i++){//将勾选的专业方向id查到实体放入List
					Professional professional = professionalDao.get(haveselectProfessional.get(i));
					professionalList.add(professional);
				}
				
				//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
				if(newPracticeLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
				{
					List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(0));
					for(int i=1;i<professionalList.size();i++)
					{
						List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newPracticeLesson.getCurriculum(),professionalList.get(i));
						//数量不同则两个矩阵必不同
						if(curriculumMatrixs != null  && curriculumMatrixs_List != null && curriculumMatrixs.size() == curriculumMatrixs_List.size())
						{
							for(int j=0;j<curriculumMatrixs.size();j++)
							{
								int tag = 0;	//设置标志位，如果验证项与被验证项不同，则还为零，直接返回
								for(int k=0;k<curriculumMatrixs_List.size();k++)
								{
									if(curriculumMatrixs.get(j).getAbility() == curriculumMatrixs_List.get(k).getAbility() && curriculumMatrixs.get(j).getCount() == curriculumMatrixs_List.get(k).getCount())
									{
										tag = 1;
										curriculumMatrixs_List.remove(curriculumMatrixs_List.get(k));
										break;
									}
								}
								if(tag == 0)
								{
									return "no";
								}
							}
						}
						else
						{
							return "no";
						}
					}
				}
				
				//
				List<ApplicationSyllabus> applicationSyllabusList = applicationSyllabusDao.findapplicationSyllabusBySylid(syllabusId2);//通过大纲id查询该大纲的所有应用专业
				List<Professional> professional = new ArrayList<Professional>();
				if(applicationSyllabusList != null && applicationSyllabusList.size() != 0){
					for(int i=0;i<applicationSyllabusList.size();i++){
						professional.add(applicationSyllabusList.get(i).getProfessional());
					}
				}
				
				for(int i=0;i<professionalList.size();i++){
					if(professional.contains(professionalList.get(i))){
						professional.remove(professionalList.get(i));
					}
					else{
						ApplicationSyllabus applicationSyllabus = new ApplicationSyllabus();
						Syllabus syllabus = syllabusDao.get(Integer.valueOf(syllabusId));
						applicationSyllabus.setSyllabus(syllabus);
						applicationSyllabus.setCurriculum(newPracticeLesson.getCurriculum());
						applicationSyllabus.setDepartment(newPracticeLesson.getDepartment());
						applicationSyllabus.setProfessional(professionalList.get(i));
						applicationSyllabusDao.add(applicationSyllabus);
					}
				}
				
				if(professional != null && professional.size() != 0){
					for(int i=0;i<professional.size();i++){
						ApplicationSyllabus applicationSyllabus =  applicationSyllabusDao.findappByThenAndProOther(newPracticeLesson,professional.get(i));
						applicationSyllabusDao.delete(applicationSyllabus);
					}
				}
				Integer count = professionalList.size();
				ServletActionContext.getRequest().setAttribute("count", count);
				ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			}
			
			ServletActionContext.getRequest().setAttribute("newTheoreticalLesson", newPracticeLesson);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			return "yes";
		}
		public void deletePracSyllabusOther(String syllabusId) {
			/****************************大纲表一对一关系*************************************/
			Syllabus copysyllabus = syllabusDao.get(Integer.valueOf(syllabusId));
			if(copysyllabus != null)
			{
				syllabusDao.delete(copysyllabus);
			}
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
			//大纲应用专业表删除
			List<ApplicationSyllabus> copyApplicationSyllabusList =  applicationSyllabusDao.findapplicationSyllabusBySylid(String.valueOf(syllabusId));
			if(copyApplicationSyllabusList != null && copyApplicationSyllabusList.size() !=0)
			{
				for(int i=0;i<copyApplicationSyllabusList.size();i++)
				{
					applicationSyllabusDao.delete(copyApplicationSyllabusList.get(i));
				}
			}	
			//毕业要求与教学目标对应关系表删除
			List<AbilityAndTeachObj> abilityAndTeachObjList = abilityAndTeachObjDao.findAbiAndTeachListBySyllabusid(syllabusId);
			if(abilityAndTeachObjList != null && abilityAndTeachObjList.size() !=0)
			{
				for(int i=0;i<abilityAndTeachObjList.size();i++)
				{
					abilityAndTeachObjDao.delete(abilityAndTeachObjList.get(i));
				}
			}
			//指标点选择表删除
			List<IndexSelect> indexSelectList = indexSelectDao.findIndexSelectListBySyllabusid(syllabusId);
			if(indexSelectList != null && indexSelectList.size() != 0)
			{
				for(int i=0;i<indexSelectList.size();i++)
				{
					indexSelectDao.delete(indexSelectList.get(i));
				}
			}
			//教学目标表删除
			List<TeachObj> teachObjList = teachObjDao.findTeachObjListBySyllabusid(syllabusId);
			if(teachObjList != null && teachObjList.size() != 0)
			{
				for(int i=0;i<teachObjList.size();i++)
				{
					teachObjDao.delete(teachObjList.get(i));
				}
			}
			/****************************大纲表一对多关系-课程内容*************************************/
			List<ContentTheo> contentTheolist = contentTheoDao.getbycontentTheo(syllabusId);
			if(contentTheolist != null && contentTheolist.size() != 0)
			{
				for(int ci=0;ci<contentTheolist.size();ci++)
				{
					contentTheoDao.delete(contentTheolist.get(ci));
					
				}
			}
			/****************************大纲表一对多关系-课程内容-编辑理论课实验内容*************************************/
			List<ExperimentContent> experimentContentlist = experimentContentDao.getbyexperimentContent(syllabusId);
			if(experimentContentlist != null && experimentContentlist.size() != 0)
			{
				for(int ei=0;ei<experimentContentlist.size();ei++)
				{
					experimentContentDao.delete(experimentContentlist.get(ei));
				}
			}
			/****************************大纲表一对多关系-课程内容-编辑理论课讨论课内容*************************************/
			List<DiscussContent> discussContentlist=discussContentDao.getbydiscussContent(syllabusId);
			if(discussContentlist != null && discussContentlist.size() != 0)
			{
				for(int di=0;di<discussContentlist.size();di++)
				{
					discussContentDao.delete(discussContentlist.get(di));
				}
			}
			/****************************大纲表一对多关系-课程内容-编辑理论课三级项目内容*************************************/
			List<ThreeProject> ThreeProjectlist = threeProjectDao.getbyThreeProject(syllabusId);
			if(ThreeProjectlist != null && ThreeProjectlist.size() != 0)
			{
				for(int di=0;di<ThreeProjectlist.size();di++)
				{
					threeProjectDao.delete(ThreeProjectlist.get(di));
				}
			}
			/****************************大纲表一对多关系-课程内容-编辑理论课其他项目内容*************************************/
			List<OtherContent> OtherContentlist = otherContentDao.getbyOtherContent(syllabusId);
			if(OtherContentlist != null && OtherContentlist.size() != 0)
			{
				for(int di=0;di<OtherContentlist.size();di++)
				{
					otherContentDao.delete(OtherContentlist.get(di));
				}
			}
			/****************************大纲表一对多关系-编辑理论课教材*************************************/
			List<TextBooks> TextBookslist = textBooksDao.getbytextBooks(syllabusId);
			if(TextBookslist != null && TextBookslist.size() != 0)
			{
				for(int di=0;di<TextBookslist.size();di++)
				{
					textBooksDao.delete(TextBookslist.get(di));
				}
			}
			/****************************大纲表一对多关系-学时分配*************************************/
			List<DistributeHour_Theo> DistributeHour_TheoList = distributeHour_TheoDao.getbyDistributeHour_Theo(syllabusId);
			if(DistributeHour_TheoList != null && DistributeHour_TheoList.size() != 0)
			{
				for(int i=0;i<DistributeHour_TheoList.size();i++)
				{
					distributeHour_TheoDao.delete(DistributeHour_TheoList.get(i));
				}
			}
			ServletActionContext.getRequest().setAttribute("tag", "toCheckPracLesOtherPage");
			ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
			
		}
		public void toPracLesOtherPageCopy(PracticeLesson practiceLesson,List<String> selectProfessional, String syllabusId_Copy) {
			toPracLesOtherPage(practiceLesson,selectProfessional);
			//syllabusId新建的大纲Id
			//syllabusId_Copy传过来的选择的要复制的大纲Id
			Syllabus copysyllabus = syllabusDao.get(Integer.valueOf(syllabusId_Copy));
			Syllabus newsyllabus = syllabusDao.get(Integer.valueOf(syllabusId));
			
			/****************************大纲表一对一关系*************************************/
			newsyllabus.setBaseTheo(copysyllabus.getBaseTheo());
			newsyllabus.setTeaMethodTheo(copysyllabus.getTeaMethodTheo());
			syllabusDao.update(newsyllabus);
			
			
			/****************************大纲表一对多关系-教学目标及对应关系*************************************/
/*			List<ApplicationSyllabus> newApplicationSyllabusList =  applicationSyllabusDao.findapplicationSyllabusBySylid(String.valueOf(syllabusId));
			List<ApplicationSyllabus> copyApplicationSyllabusList =  applicationSyllabusDao.findapplicationSyllabusBySylid(String.valueOf(syllabusId_Copy));
			if(newApplicationSyllabusList != null && newApplicationSyllabusList.size() != 0 && copyApplicationSyllabusList != null && copyApplicationSyllabusList.size() !=0)
			{
				if(newApplicationSyllabusList.get(0).getDepartment() == copyApplicationSyllabusList.get(0).getDepartment())
				{//相同专业才进行复制
					//指标点选择表复制
					List<IndexSelect> indexSelectList = indexSelectDao.findIndexSelectListBySyllabusid(syllabusId_Copy);
					if(indexSelectList != null && indexSelectList.size() != 0)
					{
						for(int i=0;i<indexSelectList.size();i++)
						{
							IndexSelect indexSelect = indexSelectList.get(i);
							IndexSelect newindexSelect = new IndexSelect();
							newindexSelect.setAbility(indexSelect.getAbility());
							newindexSelect.setAnalisis(indexSelect.getAnalisis());
							newindexSelect.setSyllabusID(String.valueOf(syllabusId));
							indexSelectDao.add(newindexSelect);
						}
					}
					//教学目标表复制
					List<TeachObj> teachObjList = teachObjDao.findTeachObjListBySyllabusid(syllabusId_Copy);
					List<TeachObj> newTeachObjList = new ArrayList<TeachObj>();
					if(teachObjList != null && teachObjList.size() != 0)
					{
						for(int i=0;i<teachObjList.size();i++)
						{
							TeachObj teachObj = teachObjList.get(i);
							TeachObj newteachObj = new TeachObj();
							newteachObj.setTeachObjContent(teachObj.getTeachObjContent());
							newteachObj.setSyllabusID(String.valueOf(syllabusId));
							teachObjDao.add(newteachObj);
							newTeachObjList.add(newteachObj);
						}
					}
					//毕业要求与教学目标对应关系表复制
					List<AbilityAndTeachObj> abilityAndTeachObjList = abilityAndTeachObjDao.findAbiAndTeachListBySyllabusid(syllabusId_Copy);
					if(abilityAndTeachObjList != null && abilityAndTeachObjList.size() !=0)
					{
						for(int i=0;i<abilityAndTeachObjList.size();i++)
						{
							AbilityAndTeachObj abilityAndTeachObj = abilityAndTeachObjList.get(i);
							AbilityAndTeachObj newabilityAndTeachObj = new AbilityAndTeachObj();
							newabilityAndTeachObj.setAbility(abilityAndTeachObj.getAbility());
							
							newabilityAndTeachObj.setTeachObj(newTeachObjList.get(teachObjList.indexOf(abilityAndTeachObj.getTeachObj())));
							
							newabilityAndTeachObj.setSyllabusID(String.valueOf(syllabusId));
							abilityAndTeachObjDao.add(newabilityAndTeachObj);
						}
					}
				}
			}*/
			
			
			/****************************大纲表一对多关系-课程内容*************************************/
			List<ContentTheo> contentTheolist = contentTheoDao.getbycontentTheo(syllabusId_Copy);
			if(contentTheolist != null && contentTheolist.size() != 0)
			{
				for(int ci=0;ci<contentTheolist.size();ci++)
				{
					ContentTheo contentTheo = contentTheolist.get(ci);
					ContentTheo newcontentTheo = new ContentTheo();
					newcontentTheo.setAim(contentTheo.getAim());
					newcontentTheo.setContent(contentTheo.getContent());
//					newcontentTheo.setEmphasis(contentTheo.getEmphasis());
					newcontentTheo.setName(contentTheo.getName());
//					newcontentTheo.setNodus(contentTheo.getNodus());
					newcontentTheo.setNum(contentTheo.getNum());
					newcontentTheo.setTime(contentTheo.getTime());
					newcontentTheo.setSyllabusID(String.valueOf(syllabusId));
					contentTheoDao.add(newcontentTheo);
					
				}
			}
			/****************************大纲表一对多关系-课程内容-编辑理论课实验内容*************************************/
			List<ExperimentContent> experimentContentlist = experimentContentDao.getbyexperimentContent(syllabusId_Copy);
			if(experimentContentlist != null && experimentContentlist.size() != 0)
			{
				for(int ei=0;ei<experimentContentlist.size();ei++)
				{
					ExperimentContent experimentContent = experimentContentlist.get(ei);
					ExperimentContent newexperimentContent = new ExperimentContent();
					newexperimentContent.setAim(experimentContent.getAim());
					newexperimentContent.setContent(experimentContent.getContent());
					newexperimentContent.setNum(experimentContent.getNum());
					newexperimentContent.setSyllabusID(String.valueOf(syllabusId));
					experimentContentDao.add(newexperimentContent);
				}
			}
			/****************************大纲表一对多关系-课程内容-编辑理论课讨论课内容*************************************/
			List<DiscussContent> discussContentlist=discussContentDao.getbydiscussContent(syllabusId_Copy);
			if(discussContentlist != null && discussContentlist.size() != 0)
			{
				for(int di=0;di<discussContentlist.size();di++)
				{
					DiscussContent discussContent = discussContentlist.get(di);
					DiscussContent newdiscussContent = new DiscussContent();
					newdiscussContent.setAim(discussContent.getAim());
					newdiscussContent.setContent(discussContent.getContent());
					newdiscussContent.setImplementation(discussContent.getImplementation());
					newdiscussContent.setNum(discussContent.getNum());
					newdiscussContent.setRequest(discussContent.getRequest());
					newdiscussContent.setSyllabusID(String.valueOf(syllabusId));
					discussContentDao.add(newdiscussContent);
				}
			}
			/****************************大纲表一对多关系-课程内容-编辑理论课三级项目内容*************************************/
			List<ThreeProject> ThreeProjectlist = threeProjectDao.getbyThreeProject(syllabusId_Copy);
			if(ThreeProjectlist != null && ThreeProjectlist.size() != 0)
			{
				for(int di=0;di<ThreeProjectlist.size();di++)
				{
					ThreeProject threeProject = ThreeProjectlist.get(di);
					ThreeProject newthreeProject = new ThreeProject();
					newthreeProject.setAim(threeProject.getAim());
					newthreeProject.setContent(threeProject.getContent());
					newthreeProject.setImplementation(threeProject.getImplementation());
					newthreeProject.setNum(threeProject.getNum());
					newthreeProject.setRequest(threeProject.getSchedule());
					newthreeProject.setSchedule(threeProject.getSchedule());
					newthreeProject.setSyllabusID(String.valueOf(syllabusId));
					threeProjectDao.add(newthreeProject);
				}
			}
			/****************************大纲表一对多关系-课程内容-编辑理论课其他项目内容*************************************/
			List<OtherContent> OtherContentlist = otherContentDao.getbyOtherContent(syllabusId_Copy);
			if(OtherContentlist != null && OtherContentlist.size() != 0)
			{
				for(int di=0;di<OtherContentlist.size();di++)
				{
					OtherContent otherContent = OtherContentlist.get(di);
					OtherContent newotherContent = new OtherContent();
					newotherContent.setAim(otherContent.getAim());
					newotherContent.setContent(otherContent.getContent());
					newotherContent.setImplementation(otherContent.getImplementation());
					newotherContent.setName(otherContent.getName());
					newotherContent.setNum(otherContent.getNum());
					newotherContent.setRequest(otherContent.getRequest());
					newotherContent.setSchedule(otherContent.getSchedule());
					newotherContent.setSyllabusID(String.valueOf(syllabusId));
					otherContentDao.add(newotherContent);
				}
			}
			/****************************大纲表一对多关系-编辑理论课教材*************************************/
			List<TextBooks> TextBookslist = textBooksDao.getbytextBooks(syllabusId_Copy);
			if(TextBookslist != null && TextBookslist.size() != 0)
			{
				for(int di=0;di<TextBookslist.size();di++)
				{
					TextBooks textBooks = TextBookslist.get(di);
					TextBooks newtextBooks = new TextBooks();
					newtextBooks.setName(textBooks.getName());
					newtextBooks.setSyllabusID(String.valueOf(syllabusId));
					textBooksDao.add(newtextBooks);
				}
			}
			
			/****************************大纲表一对多关系-学时分配*************************************/
			List<DistributeHour_Theo> DistributeHour_TheoList = distributeHour_TheoDao.getbyDistributeHour_Theo(syllabusId_Copy);
			if(DistributeHour_TheoList != null && DistributeHour_TheoList.size() != 0)
			{
				for(int i=0;i<DistributeHour_TheoList.size();i++)
				{
					DistributeHour_Theo distributeHour_Theo = DistributeHour_TheoList.get(i);
					DistributeHour_Theo newDistributeHour_Theo = new DistributeHour_Theo();
					newDistributeHour_Theo.setHoursOfClass(distributeHour_Theo.getHoursOfClass());
					newDistributeHour_Theo.setHoursOfCom(distributeHour_Theo.getHoursOfCom());
					newDistributeHour_Theo.setHoursOfExp(distributeHour_Theo.getHoursOfExp());
					newDistributeHour_Theo.setName(distributeHour_Theo.getName());
					newDistributeHour_Theo.setSyllabusid(String.valueOf(syllabusId));
					distributeHour_TheoDao.add(newDistributeHour_Theo);
				}
			}
			
		}
		public void toCheckOtherPageAdmin(String tnum,PracticeLesson theoreticalLesson) {
			findUserInfo(tnum);
			if(user.getAdminlevel() == 5)
			{
				collegelist = collegeDao.findAll();
			}
			else
			{
				collegelist = findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid());
			}
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
			
			List<PracticeLesson> allTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearchOther(theoreticalLesson);//该老师所有应选大纲
			List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			
			while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
				List<PracticeLesson> theoreticalLessonList = new ArrayList<PracticeLesson>();
				PracticeLesson theoLess = allTheoreticalLessonlist.get(0);
				String cid = theoLess.getCurriculum().getCurriculumid();
				for(int ti=0;ti<allTheoreticalLessonlist.size();ti++)
				{
					if(cid.equals(allTheoreticalLessonlist.get(ti).getCurriculum().getCurriculumid()) && theoLess.getDepartment().getDepartmentid().equals(allTheoreticalLessonlist.get(ti).getDepartment().getDepartmentid()))
					{
						theoreticalLessonList.add(allTheoreticalLessonlist.get(ti));
						allTheoreticalLessonlist.remove(ti);
						ti--;
					}
				}
				professionalList.add(theoreticalLessonList);
			}
			
			
			List<PracticeLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearchOther(theoreticalLesson);
			List<ApplicationSyllabus> applicationSyllabusList = applicationSyllabusDao.findPracticeLessonhaveSyllabusList(newallTheoreticalLessonlist);//应用大纲的专业/方向

			List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				
				List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					PracticeLesson theoLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList.size();pk++)
					{
						ApplicationSyllabus appSyll = applicationSyllabusList.get(pk);
						if(theoLess.getProfessional() == null){
							if(theoLess.getCurriculum() == appSyll.getCurriculum() &&theoLess.getDepartment() == appSyll.getDepartment()){
								newhaveprofessionalList.add(theoLess);
								professionalList.get(pi).remove(theoLess);
								pj--;
							}
						}
						else{
							if(appSyll.getProfessional() != null){
								if(theoLess.getCurriculum() == appSyll.getCurriculum() && theoLess.getDepartment() == appSyll.getDepartment() && theoLess.getProfessional() == appSyll.getProfessional() ){
									//&& theoreticalPlanDao.findSyllabusidByTheo(theoLess) == String.valueOf(appSyll.getSyllabus().getSyllabusid())
										newhaveprofessionalList.add(theoLess);
										professionalList.get(pi).remove(theoLess);
										pj--;
								}
							}
						}
					}
				}
				if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
				haveprofessionalList.add(newhaveprofessionalList);
			}
			
			List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
			if(professionalList != null && professionalList.size() != 0){
				for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
					if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
						professionalList.remove(qi);
					}
				}
				for(int ri=0;ri<professionalList.size();ri++){
					if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
						professionalList.remove(professionalList.get(ri));
						ri--;
					}
					else{
						syllabusList.add(professionalList.get(ri).get(0));
					}
				}
			}
			List<Integer> count = new ArrayList<Integer>();
			if(professionalList != null && professionalList.size() != 0){
				for(int si=0;si<professionalList.size();si++){
					if(professionalList.get(si).size() == 1)
					{
						String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							count.add(professionalList.get(si).size());
						}
						else{
							count.add(-1);
						}
					}
					else{
						count.add(professionalList.get(si).size());
					}
						
					}
					
				}
			
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
			List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
			
			
			//分开不同大纲ID
			List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<PracticeLesson> newTheoreticalLesson = new ArrayList<PracticeLesson>();
						PracticeLesson theo = haveprofessionalList.get(ri).get(0);
						String syid =theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(theo);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(haveprofessionalList.get(ri).get(rj))))
							{
								newTheoreticalLesson.add(haveprofessionalList.get(ri).get(rj));
								haveprofessionalList.get(ri).remove(rj);
								rj--;
							}
						}
						newhaveprofessionalList.add(newTheoreticalLesson);
					}
				}
			}
			
			
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int ri=0;ri<newhaveprofessionalList.size();ri++){
					haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
				}
			}
			List<Integer> haveCount = new ArrayList<Integer>();
			if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
				for(int si=0;si<newhaveprofessionalList.size();si++){
					if(newhaveprofessionalList.get(si).size() == 1)
					{
						String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
						List<Professional> professionalList1 = findProfessional(departmentid);		
						Integer professionalListCount = professionalList1.size();		
						if(professionalListCount>0){
							haveCount.add(newhaveprofessionalList.get(si).size());
						}
						else{
							haveCount.add(-1);
						}
					}
					else{
						haveCount.add(newhaveprofessionalList.get(si).size());
					}
						
					}
					
				}
		
			List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				PracticeLesson newtheo = newhaveprofessionalList.get(hi).get(0);
				List<PracticeLesson> newallTheoreticalLesson = theoreticalPlanDao.getbyCurriculumAndDepartmentOther(newtheo);//该老师所有应选大纲
		
				while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
					List<PracticeLesson> theoreticalLessonList = new ArrayList<PracticeLesson>();
					PracticeLesson theoLess = newallTheoreticalLesson.get(0);
					String cid = theoLess.getCurriculum().getCurriculumid();
					for(int ti=0;ti<newallTheoreticalLesson.size();ti++)
					{
						if(cid.equals(newallTheoreticalLesson.get(ti).getCurriculum().getCurriculumid()))
						{
							theoreticalLessonList.add(newallTheoreticalLesson.get(ti));
							newallTheoreticalLesson.remove(ti);
							ti--;
						}
					}
					newprofessionalList.add(theoreticalLessonList);
				}
				
			}
			
			List<String> syllabusId = new ArrayList<String>();
			if(haveSyllabusList != null && haveSyllabusList.size() != 0){
				for(int i=0;i<haveSyllabusList.size();i++){
					String syllabusid = theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus> appSyllaList = new ArrayList<ApplicationSyllabus>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findPracticeLessonAppByTheo(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
							{
								newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
								j--;
							}
						}
					}
				}
				}
			}
			
			
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
			
		}
		//实践课课表
		public PageBean findtheolen1(Integer currentpage,PracticeLesson practiceLesson, String departmenttag) {
			PageBean pageBean=new PageBean();
			//在pageBean中设置college使得条件查询可执行
			List<College> listcollege=new ArrayList<College>();
			College college=findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid()).get(0);
			listcollege.add(college);
			pageBean.setCollegelist(listcollege);
			//找到该学院所开课程在理论课列表中的所有相关记录
			List<List<PracticeLesson>> theolenlist1=findtheolenlist(practiceLesson,departmenttag);
			List<List<PracticeLesson>> theolenlist=new ArrayList<List<PracticeLesson>>();
			
			//根据条件进行判断
			//只有学院的时候显示所有
			if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
				theolenlist=theolenlist1;
			}else{
			//当搜索条件添加了课程名称时显示相关的记录
				if(departmenttag.equals("1")){
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getTeachDepartment()!=null){
							if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										theolenlist.add(theolenlist1.get(i));
									}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
							else
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
						
					}
				}
				else if(departmenttag.equals("-1"))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getTeachDepartment()==null){
							if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
							}
							else{
								theolenlist.add(theolenlist1.get(i));
							}
						}	
					}
			}
			else{
				if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&
						practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())){
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
								.equals(practiceLesson.getCurriculum().getCurriculumid())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else{
					if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())
							&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
					{
						for(int i=0;i<theolenlist1.size();i++){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
									.equals(practiceLesson.getCurriculum().getCurriculumCname())){
								theolenlist.add(theolenlist1.get(i));
							}	
						}
					}
					else if(practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())
							&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
					{
						for(int i=0;i<theolenlist1.size();i++){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
									.equals(practiceLesson.getCurriculum().getCurriculumid())){
								theolenlist.add(theolenlist1.get(i));
							}	
						}
					}
				}
			}
			
			}

			int totalCount=theolenlist.size();
			pageBean.setTotalCount(totalCount);
			
			List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
			sslist=theolenlist.subList(0, totalCount);
			List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
			for(int i=0;i<sslist.size();i++){
				curriculumlist.add(sslist.get(i).get(0).getCurriculum());
			}
			
			pageBean.setCurriculumlist(curriculumlist);
			pageBean.setPracticeLessonlist(sslist);
			return pageBean;
		}
		
		//下载实践课分配情况
		public void downpracticecourse(PageBean pageBean){//下载
			try{
					HttpServletResponse response = ServletActionContext.getResponse();  
			        //2.1 设置响应类型  
			        response.setContentType("application/x-execl");  
			        //2.2 设置以下载方式打开文件            
			        response.setHeader("Content-Disposition", "attachment;filename="+new String(("实践课分配课程.xls").getBytes("gb2312"),"ISO-8859-1") );
			       OutputStream outputStream = response.getOutputStream();
			          
					HSSFWorkbook wb = new HSSFWorkbook();  
				    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
				   HSSFSheet sheet = wb.createSheet("sheet1");  
				   sheet.setColumnWidth((short) 0, (short) 3500);
				   sheet.setColumnWidth((short) 1, (short) 8000);
				   sheet.setColumnWidth((short) 2, (short) 5000);
				   sheet.setColumnWidth((short) 3, (short) 3500);
				   sheet.setColumnWidth((short) 4, (short) 3000);
				   sheet.setColumnWidth((short) 5, (short) 15000);
				   sheet.setColumnWidth((short) 6, (short) 6500);
				        
				   HSSFRow row = sheet.createRow((int) 0);  
				   // 第四步，创建单元格，并设置值表头 设置表头居中  
				  HSSFCellStyle style1 = wb.createCellStyle();  
				  style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
				  style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
				  style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
				  style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
				  style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
				        
				  HSSFCell cell = row.createCell((short) 0);  
				  cell.setCellValue("课程代码");  
				  cell.setCellStyle(style1);  
				  
				  cell = row.createCell((short) 1);  
				  cell.setCellValue("课程中文名称");  
				  cell.setCellStyle(style1);  
				  
				 cell = row.createCell((short) 2);  
				 cell.setCellValue("课程性质");  
				 cell.setCellStyle(style1); 
				        
				 cell = row.createCell((short) 3);  
				 cell.setCellValue("课程类别");  
				 cell.setCellStyle(style1); 
				        
				 cell = row.createCell((short) 4);  
				 cell.setCellValue("开课学院");  
				 cell.setCellStyle(style1);
				        
				 cell = row.createCell((short) 5);  
				 cell.setCellValue("选课专业");  
				 cell.setCellStyle(style1);
				        
				 cell = row.createCell((short) 6);  
				 cell.setCellValue("分配情况");  
				 cell.setCellStyle(style1);
						
				
				List<List<PracticeLesson>>  practiceLesson = pageBean.getPracticeLessonlist();
				      int allrow = 0;
				      for(int i=0;i<practiceLesson.size();i++)
				      {
				    	   
				    	  for (int j =0;j<practiceLesson.get(i).size();j++)
				    	  {
				    		   Curriculum curriculum = practiceLesson.get(i).get(j).getCurriculum(); //获取课内实验列表
				    		  
				    		   PracticeLesson practicetical = practiceLesson.get(i).get(j);//获取每个课内实验分配的情况
				    		    		
				    		    row = sheet.createRow((int) allrow+1);

					            cell = row.createCell((short) 0);  
					            cell.setCellValue(curriculum.getCurriculumid());  
					            cell.setCellStyle(style1);  
					      
					            cell = row.createCell((short) 1);  
					            cell.setCellValue(curriculum.getCurriculumCname());  
					            cell.setCellStyle(style1);  
					      
					            cell = row.createCell((short) 2);  
					            cell.setCellValue(curriculum.getNatureOfCourse().getNatureOfCoursename());  
					            cell.setCellStyle(style1); 
					            
					            cell = row.createCell((short) 3);  
					            cell.setCellValue(curriculum.getCourseLei());  
					            cell.setCellStyle(style1); 
					            
					            cell = row.createCell((short) 4);  
					            cell.setCellValue(curriculum.getCollege().getCollegeCname());  
					            cell.setCellStyle(style1);
					            
					            cell = row.createCell((short) 5);  
					            if(practicetical.getProfessional()!= null){
					            	cell.setCellValue(practicetical.getDepartment().getDepartmentCname()+"("+practicetical.getProfessional().getProfessionalname()+")");  
					            }else{
					            	 cell.setCellValue(practicetical.getDepartment().getDepartmentCname()); 
					            }
					            cell.setCellStyle(style1);
					            
					            cell = row.createCell((short) 6);
					            if(practicetical.getTeachDepartment()!= null || practicetical.getTeacher() != null || practicetical.getExperiment() != null || practicetical.getExperimenter() != null){
					            	 if(practicetical.getTeachDepartment()!= null){
					            	      cell.setCellValue(practicetical.getTeachDepartment().getDepartmentCname());
					                 }
					            	 if(practicetical.getTeacher() != null){
					            	     cell.setCellValue(practicetical.getTeacher().getUsername()+"("+"老师"+")");
					                 }
					            	 if(practicetical.getExperiment() != null){
					            		 cell.setCellValue(practicetical.getExperiment().getExperimentname()); 
					            	 }
					            	 if(practicetical.getExperimenter() != null){
					            		 cell.setCellValue(practicetical.getExperimenter().getUsername()+"("+"实验员"+")"); 
					            	 }
					            }else{
					            	 cell.setCellValue("未分配");
					            }
					            cell.setCellStyle(style1);

					            
					          allrow = allrow +1;  
				    	  }	
				    	 
			    	  }				
						 wb.write(outputStream);
					     outputStream.flush();  
					     outputStream.close();

			          if(outputStream != null){
			          	outputStream.close();  
			          }
						
					} catch (Exception e) {  
				        e.printStackTrace();  
				    }
				}
		
		//-----------------------------------系管理下载实践课课表---------------------
				public PageBean findpractice(Integer currentpage,PracticeLesson practiceLesson, String usertag) {
					PageBean pageBean=new PageBean();
					//在pageBean中设置college使得条件查询可执行
					List<College> listcollege=new ArrayList<College>();
					College college=findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid()).get(0);
					listcollege.add(college);
					pageBean.setCollegelist(listcollege);
					
					
					List<Department> listdepart=new ArrayList<Department>();
					listdepart.add(departmentDao.get(practiceLesson.getTeachDepartment().getDepartmentid()));
					pageBean.setDepartmentlist(listdepart);
					
					practiceLesson.setTeachDepartment(listdepart.get(0));
					
					List<List<PracticeLesson>> theolenlist1=findtheolenlistByDepartUser(practiceLesson,usertag);
					List<List<PracticeLesson>> theolenlist=new ArrayList<List<PracticeLesson>>();
						
					//根据条件进行判断
					//只有学院的时候显示所有
					if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())&&usertag.equals("0")){
						theolenlist=theolenlist1;
					}else{
					//当搜索条件添加了课程名称时显示相关的记录
						if(usertag.equals("1")){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getTeacher()!=null){
									if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
									{
											if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
											{
												theolenlist.add(theolenlist1.get(i));
											}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
											{
												if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
														theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
												{
													theolenlist.add(theolenlist1.get(i));
												}
											}
									else
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
								
							}
						}
						else if(usertag.equals("-1"))
						{
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getTeacher()==null){
									if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else{
										theolenlist.add(theolenlist1.get(i));
									}
								}	
							}
					}
					else{
						if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&
								practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
										.equals(practiceLesson.getCurriculum().getCurriculumid())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())){
									theolenlist.add(theolenlist1.get(i));
								}	
							}
						}
						else{
							if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())
									&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								for(int i=0;i<theolenlist1.size();i++){
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
											.equals(practiceLesson.getCurriculum().getCurriculumCname())){
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
							else if(practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())
									&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								for(int i=0;i<theolenlist1.size();i++){
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
											.equals(practiceLesson.getCurriculum().getCurriculumid())){
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
						}
					}
					
					}

					int totalCount=theolenlist.size();
					pageBean.setTotalCount(totalCount);
					
					List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
					sslist=theolenlist.subList(0, totalCount);
					
				
					List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
					
					for(int i=0;i<sslist.size();i++){
						curriculumlist.add(sslist.get(i).get(0).getCurriculum());
					}
					
					
					pageBean.setCurriculumlist(curriculumlist);
					pageBean.setPracticeLessonlist(sslist);
					return pageBean;
				}
				//下载实践课分配情况
				public void teacherdownpracticecourse(PageBean pageBean){//下载
					try{
							HttpServletResponse response = ServletActionContext.getResponse();  
					        //2.1 设置响应类型  
					        response.setContentType("application/x-execl");  
					        //2.2 设置以下载方式打开文件            
					        response.setHeader("Content-Disposition", "attachment;filename="+new String(("实践课分配课程.xls").getBytes("gb2312"),"ISO-8859-1") );
					       OutputStream outputStream = response.getOutputStream();
					          
							HSSFWorkbook wb = new HSSFWorkbook();  
						    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
						   HSSFSheet sheet = wb.createSheet("sheet1");  
						   sheet.setColumnWidth((short) 0, (short) 3500);
						   sheet.setColumnWidth((short) 1, (short) 8000);
						   sheet.setColumnWidth((short) 2, (short) 5000);
						   sheet.setColumnWidth((short) 3, (short) 3500);
						   sheet.setColumnWidth((short) 4, (short) 3000);
						   sheet.setColumnWidth((short) 5, (short) 15000);
						   sheet.setColumnWidth((short) 6, (short) 6500);
						        
						   HSSFRow row = sheet.createRow((int) 0);  
						   // 第四步，创建单元格，并设置值表头 设置表头居中  
						  HSSFCellStyle style1 = wb.createCellStyle();  
						  style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
						  style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
						  style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
						  style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
						  style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
						        
						  HSSFCell cell = row.createCell((short) 0);  
						  cell.setCellValue("课程代码");  
						  cell.setCellStyle(style1);  
						  
						  cell = row.createCell((short) 1);  
						  cell.setCellValue("课程中文名称");  
						  cell.setCellStyle(style1);  
						  
						 cell = row.createCell((short) 2);  
						 cell.setCellValue("课程性质");  
						 cell.setCellStyle(style1); 
						        
						 cell = row.createCell((short) 3);  
						 cell.setCellValue("课程类别");  
						 cell.setCellStyle(style1); 
						        
						 cell = row.createCell((short) 4);  
						 cell.setCellValue("开课学院");  
						 cell.setCellStyle(style1);
						        
						 cell = row.createCell((short) 5);  
						 cell.setCellValue("选课专业");  
						 cell.setCellStyle(style1);
						        
						 cell = row.createCell((short) 6);  
						 cell.setCellValue("分配情况");  
						 cell.setCellStyle(style1);
								
						
						List<List<PracticeLesson>>  practiceLesson = pageBean.getPracticeLessonlist();
						      int allrow = 0;
						      for(int i=0;i<practiceLesson.size();i++)
						      {
						    	   
						    	  for (int j =0;j<practiceLesson.get(i).size();j++)
						    	  {
						    		   Curriculum curriculum = practiceLesson.get(i).get(j).getCurriculum(); //获取课内实验列表
						    		  
						    		   PracticeLesson practicetical = practiceLesson.get(i).get(j);//获取每个课内实验分配的情况
						    		    		
						    		    row = sheet.createRow((int) allrow+1);

							            cell = row.createCell((short) 0);  
							            cell.setCellValue(curriculum.getCurriculumid());  
							            cell.setCellStyle(style1);  
							      
							            cell = row.createCell((short) 1);  
							            cell.setCellValue(curriculum.getCurriculumCname());  
							            cell.setCellStyle(style1);  
							      
							            cell = row.createCell((short) 2);  
							            cell.setCellValue(curriculum.getNatureOfCourse().getNatureOfCoursename());  
							            cell.setCellStyle(style1); 
							            
							            cell = row.createCell((short) 3);  
							            cell.setCellValue(curriculum.getCourseLei());  
							            cell.setCellStyle(style1); 
							            
							            cell = row.createCell((short) 4);  
							            cell.setCellValue(curriculum.getCollege().getCollegeCname());  
							            cell.setCellStyle(style1);
							            
							            cell = row.createCell((short) 5);  
							            if(practicetical.getProfessional()!= null){
							            	cell.setCellValue(practicetical.getDepartment().getDepartmentCname()+"("+practicetical.getProfessional().getProfessionalname()+")");  
							            }else{
							            	 cell.setCellValue(practicetical.getDepartment().getDepartmentCname()); 
							            }
							            cell.setCellStyle(style1);
							            
							            cell = row.createCell((short) 6);
							            if(practicetical.getTeachDepartment()!= null || practicetical.getTeacher() != null){
							            	 if(practicetical.getTeachDepartment()!= null){
							            	      cell.setCellValue(practicetical.getTeachDepartment().getDepartmentCname());
							                 }
							            	 if(practicetical.getTeacher() != null){
							            	     cell.setCellValue(practicetical.getTeacher().getUsername());
							                 }
							            }else{
							            	 cell.setCellValue("未分配");
							            }
							            cell.setCellStyle(style1);
							            
							          allrow = allrow +1;  
						    	  }	
						    	 
					    	  }				
								 wb.write(outputStream);
							     outputStream.flush();  
							     outputStream.close();

					          if(outputStream != null){
					          	outputStream.close();  
					          }
								
							} catch (Exception e) {  
						        e.printStackTrace();  
						    }
						}

				public PageBean findExppraclen(Integer currentpage,	PracticeLesson practiceLesson, String departmenttag) {
					PageBean pageBean=new PageBean();
					List<College> listcollege=new ArrayList<College>();
					College college=findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid()).get(0);
					listcollege.add(college);
					pageBean.setCollegelist(listcollege);
					
					List<List<PracticeLesson>> praclenlist1=findpraclenExplist(practiceLesson,departmenttag);//查找实验课	
					
					List<List<PracticeLesson>> praclenlist=new ArrayList<List<PracticeLesson>>();
					if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
						praclenlist=praclenlist1;
					}else{
					//当搜索条件添加了课程名称时显示相关的记录
						if(departmenttag.equals("1")){
							for(int i=0;i<praclenlist1.size();i++){
								if(praclenlist1.get(i).get(0).getExperiment()!=null){
									if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											praclenlist.add(praclenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
									{
										if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
										{
											praclenlist.add(praclenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
												praclenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											praclenlist.add(praclenlist1.get(i));
										}
									}
									else
									{
										praclenlist.add(praclenlist1.get(i));
									}
								}
								
							}
						}
						else if(departmenttag.equals("-1"))
						{
							for(int i=0;i<praclenlist1.size();i++){
								if(praclenlist1.get(i).get(0).getExperiment()==null){
									if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
										if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											praclenlist.add(praclenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())){
										if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
										{
											praclenlist.add(praclenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
										if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
												praclenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											praclenlist.add(praclenlist1.get(i));
										}
									}
									else{
										praclenlist.add(praclenlist1.get(i));
									}
								}	
							}
					}
					else{
						if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&
								practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())){
							for(int i=0;i<praclenlist1.size();i++){
								if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumid()
										.equals(practiceLesson.getCurriculum().getCurriculumid())&&
										praclenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
								{
									praclenlist.add(praclenlist1.get(i));
								}	
							}
						}
						else{
							if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())
									&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								for(int i=0;i<praclenlist1.size();i++)
								{
									if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										praclenlist.add(praclenlist1.get(i));
									}	
								}
							}
							else if(practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())
									&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								for(int i=0;i<praclenlist1.size();i++)
								{
									if(praclenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
									{
										praclenlist.add(praclenlist1.get(i));
									}	
								}
							}
						}
					}
					
					}

					int totalCount=praclenlist.size();
					pageBean.setTotalCount(totalCount);
					int pageSize = 10;
					pageBean.setPageSize(pageSize);
					int totalPage = 0;
					if(totalCount%pageSize == 0){
						totalPage = totalCount/pageSize;
					}else{
						totalPage = totalCount/pageSize+1;
					}
					pageBean.setTotalPage(totalPage);
					if(currentpage < 1){
						currentpage = 1;
					}
					if(currentpage > totalPage){
						currentpage = totalPage;
					}
					pageBean.setCurrentpage(currentpage);
					int begin = (currentpage-1)*pageSize;
					
					//为防止溢出 还需加一个约束条件？？？？？？？？？？
					List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
					if(currentpage != totalPage){
						sslist=praclenlist.subList(begin, (begin+pageSize));
					}
					else{
						if((begin>=0)&&(begin!=totalCount)){
							sslist=praclenlist.subList(begin,totalCount);
						}
					}

					List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
					
					for(int i=0;i<sslist.size();i++){
						curriculumlist.add(sslist.get(i).get(0).getCurriculum());
					}
					
					//System.out.println("************"+curriculumlist.get(0).getCurriculumEname());
					
					pageBean.setCurriculumlist(curriculumlist);
					pageBean.setPracticeLessonlist(sslist);
					return pageBean;
				}
				//新增
				public List<List<PracticeLesson>> findpraclenExplist(PracticeLesson practiceLesson, String departmenttag){
					//总的分页理论课列表
					List<List<PracticeLesson>> theolen=new ArrayList<List<PracticeLesson>>();
					//找到学院开设的课程
					List<Curriculum> list=practicePlanDao.findcurrbycollege(practiceLesson.getCurriculum().getCollege());
					for(int i=0;i<list.size();i++){
						//找到了课1对应的列表
						List<PracticeLesson> praclenlist=practicePlanDao.findtheolenbycurr(list.get(i));
						
						List<PracticeLesson> praclenlist1=new ArrayList<PracticeLesson>();
						List<PracticeLesson> praclenlist2=new ArrayList<PracticeLesson>();
						//遍历课1的列表
						for(int j=0;j<praclenlist.size();j++){
							if(praclenlist.get(j).getTeachDepartment() == null && praclenlist.get(j).getTeacher() == null && praclenlist.get(j).getExperiment() == null && praclenlist.get(j).getExperimenter() == null){	
								//未分配课1放到theolenlist2
								praclenlist2.add(praclenlist.get(j));
							}
							else{
								//已分配课1放到theolenlist1
								praclenlist1.add(praclenlist.get(j));
							}
						}
						if(praclenlist1.size()!=0){
							theolen.add(praclenlist1);
						}
						if(praclenlist2.size()!=0){
							theolen.add(praclenlist2);
						}
					}
					return theolen;
					
				}

				private ExperimentDao experimentDao;
				public void setExperimentDao(ExperimentDao experimentDao) {
					this.experimentDao = experimentDao;
				}
				public List<Experiment> findAllExperiment() {
					List<Experiment> list = experimentDao.findAll();
				return list;
				}
				//新增
				public void prcgoupdateExpdepartment(PracticeLesson practiceLesson,List<String> newchoosedepartlist) {
					PracticeLesson practicalLen=new PracticeLesson(); 
					for(int i=0;i<newchoosedepartlist.size();i++){
						
						//获取课程列表
						Curriculum curriculum=curriculumDao.get(practiceLesson.getCurriculum().getCurriculumid());
						if((professionalDao.get(newchoosedepartlist.get(i)))!=null)
						{
							practicalLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(newchoosedepartlist.get(i)));
						}
						else
						{
							Department department=departmentDao.get(newchoosedepartlist.get(i));
							practicalLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,department);
						}
						//if(practicalLen.getExperiment() == null ||(practicalLen.getExperiment().getExperimentid()!=(practiceLesson.getExperiment().getExperimentid()))){
							practicalLen.setExperiment(experimentDao.get(practiceLesson.getExperiment().getExperimentid()));
							practicalLen.setExperimenter(null);
							practicalLen.setTeacher(null);
							practicalLen.setTeachDepartment(null);
						//}
						practicePlanDao.update(practicalLen);
					}
				}






				//新增
				public PageBean Expfindtheolendirectuser(Integer currentpage,PracticeLesson practiceLesson, String departmenttag){
					PageBean pageBean=new PageBean();
					List<College> listcollege=new ArrayList<College>();
					College college=findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid()).get(0);
					listcollege.add(college);
					pageBean.setCollegelist(listcollege);
					
					List<List<PracticeLesson>> theolenlist1=findtheolenExperlist(practiceLesson,departmenttag);
					
					List<List<PracticeLesson>> theolenlist=new ArrayList<List<PracticeLesson>>();
					if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
						theolenlist=theolenlist1;
					}else{
					//当搜索条件添加了课程名称时显示相关的记录
						if(departmenttag.equals("1")){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getExperimenter()!=null){
									if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else
									{
										theolenlist.add(theolenlist1.get(i));
									}
								}
								
							}
						}
						else if(departmenttag.equals("-1"))
						{
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getExperimenter()==null){
									if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else{
										theolenlist.add(theolenlist1.get(i));
									}
								}	
							}
					}
					else{
						if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&
								practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
										.equals(practiceLesson.getCurriculum().getCurriculumid())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}	
							}
						}
						else{
							if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())
									&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								for(int i=0;i<theolenlist1.size();i++)
								{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
									{
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
							else if(practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())
									&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								for(int i=0;i<theolenlist1.size();i++)
								{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
									{
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
						}
					}
					
					}

					int totalCount=theolenlist.size();
					pageBean.setTotalCount(totalCount);
					int pageSize = 10;
					pageBean.setPageSize(pageSize);
					int totalPage = 0;
					if(totalCount%pageSize == 0){
						totalPage = totalCount/pageSize;
					}else{
						totalPage = totalCount/pageSize+1;
					}
					pageBean.setTotalPage(totalPage);
					if(currentpage < 1){
						currentpage = 1;
					}
					if(currentpage > totalPage){
						currentpage = totalPage;
					}
					pageBean.setCurrentpage(currentpage);
					int begin = (currentpage-1)*pageSize;
					
					//为防止溢出 还需加一个约束条件？？？？？？？？？？
					List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
					if(currentpage != totalPage){
						sslist=theolenlist.subList(begin, (begin+pageSize));
					}
					else{
						if((begin>=0)&&(begin!=totalCount)){
							sslist=theolenlist.subList(begin,totalCount);
						}
					}

					List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
					
					for(int i=0;i<sslist.size();i++){
						curriculumlist.add(sslist.get(i).get(0).getCurriculum());
					}
					
					//System.out.println("************"+curriculumlist.get(0).getCurriculumEname());
					
					pageBean.setCurriculumlist(curriculumlist);
					pageBean.setPracticeLessonlist(sslist);
					return pageBean;
				}

				//新增
				private List<List<PracticeLesson>> findtheolenExperlist(PracticeLesson practiceLesson, String departmenttag) {
					List<List<PracticeLesson>> theolen=new ArrayList<List<PracticeLesson>>();
					List<Curriculum> list=practicePlanDao.findcurrbycollege(practiceLesson.getCurriculum().getCollege());
					
					for(int i=0;i<list.size();i++){
						//找到了课1对应的列表
						List<PracticeLesson> theolenlist=practicePlanDao.findtheolenbycurr(list.get(i));
						List<PracticeLesson> theolenlist1=new ArrayList<PracticeLesson>();
						List<PracticeLesson> theolenlist2=new ArrayList<PracticeLesson>();
						//遍历课1的列表
						for(int j=0;j<theolenlist.size();j++){
							if(theolenlist.get(j).getTeachDepartment() == null && theolenlist.get(j).getTeacher() == null && theolenlist.get(j).getExperiment() == null && theolenlist.get(j).getExperimenter() == null){
											theolenlist2.add(theolenlist.get(j));	
							}else{
								theolenlist1.add(theolenlist.get(j));
							}
						}
						if(theolenlist1.size()!=0){
							theolen.add(theolenlist1);
						}
						if(theolenlist2.size()!=0){
							theolen.add(theolenlist2);
						}
					}
					return theolen;
				}
				//新增
				public PageBean findExperimenter(Integer currentpage,PracticeLesson practiceLesson){
					PageBean pageBean=new PageBean();
					List<User> userlist=new ArrayList<User>();
					List<User> userlist1=userDao.findUserByExperimenter();
					if(practiceLesson.getExperimenter() != null)
					{	
						if(practiceLesson.getExperimenter().getTnum() != null && !"".equals(practiceLesson.getExperimenter().getTnum()) && practiceLesson.getExperimenter().getUsername() != null && !"".equals(practiceLesson.getExperimenter().getUsername()))
						{
							for(int i=0;i<userlist1.size();i++){
								if(userlist1.get(i).getTnum().equals(practiceLesson.getExperimenter().getTnum())&&
										userlist1.get(i).getUsername().equals(practiceLesson.getExperimenter().getUsername()))
								{
									userlist.add(userlist1.get(i));
								}
							}
						}
						else if(!"".equals(practiceLesson.getExperimenter().getTnum()) && "".equals(practiceLesson.getExperimenter().getUsername()))
						{
							for(int i=0;i<userlist1.size();i++){
								if(userlist1.get(i).getTnum().equals(practiceLesson.getExperimenter().getTnum()))
								{
									userlist.add(userlist1.get(i));
								}
							}
						}
						else if("".equals(practiceLesson.getExperimenter().getTnum()) && !"".equals(practiceLesson.getExperimenter().getUsername()))
						{
							for(int i=0;i<userlist1.size();i++){
								if(userlist1.get(i).getUsername().equals(practiceLesson.getExperimenter().getUsername()))
								{
									userlist.add(userlist1.get(i));
								}
							}
						}
						else
						{
							userlist=userlist1;
						}
					}
					else
					{
						userlist=userlist1;
					}
					int totalCount=userlist.size();
					pageBean.setTotalCount(totalCount);
					int pageSize = 10;
					pageBean.setPageSize(pageSize);
					int totalPage = 0;
					if(totalCount%pageSize == 0){
						totalPage = totalCount/pageSize;
					}else{
						totalPage = totalCount/pageSize+1;
					}
					pageBean.setTotalPage(totalPage);
					if(currentpage < 1){
						currentpage = 1;
					}
					if(currentpage > totalPage){
						currentpage = totalPage;
					}
					pageBean.setCurrentpage(currentpage);
					int begin = (currentpage-1)*pageSize;
					List<User> sslist=new ArrayList<User>();
					if(currentpage != totalPage){
						sslist=userlist.subList(begin, (begin+pageSize));
					}
					else{
						if((begin>=0)&&(begin!=totalCount)){
							sslist=userlist.subList(begin,totalCount);
						}
					}
					pageBean.setUserlist(sslist);
					return pageBean;
				}

				//新增
				public void updateTheolenByCollegeDirectUser1(PracticeLesson practiceLesson, List<String> depart) 
				{
					PracticeLesson practiceLen=new PracticeLesson(); 
					for(int i=0;i<depart.size();i++){
						Curriculum curriculum=curriculumDao.get(practiceLesson.getCurriculum().getCurriculumid());
						if((professionalDao.get(depart.get(i)))!=null)
						{
							practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
						}
						else
						{
							Department department=departmentDao.get(depart.get(i));
							practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,department);
						}
						practiceLen.setTeachDepartment(null);
						practiceLen.setExperiment(null);
						practiceLen.setTeacher(null);
						practiceLen.setExperimenter(userDao.get(practiceLesson.getExperimenter().getTnum()));
						practicePlanDao.update(practiceLen);
					}
				}

	//--------------------------------实验室主任分配实践课到实验员      李艳李婧 2018-07-29--------------------------------------

	public PageBean proExpfindtheolendirectuser(Integer currentpage,PracticeLesson practiceLesson, String departmenttag){
		PageBean pageBean=new PageBean();
		List<College> listcollege=new ArrayList<College>();
		College college=findCollegeById(practiceLesson.getCurriculum().getCollege().getCollegeid()).get(0);
		listcollege.add(college);
		pageBean.setCollegelist(listcollege);
		
		List<List<PracticeLesson>> theolenlist1=findtheolenExperlist1(practiceLesson,departmenttag);
		
		List<List<PracticeLesson>> theolenlist=new ArrayList<List<PracticeLesson>>();
		if(practiceLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
			theolenlist=theolenlist1;
		}else{
		//当搜索条件添加了课程名称时显示相关的记录
			if(departmenttag.equals("1")){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getExperimenter()!=null){
						if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
						{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
						{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
						{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
									theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}	
				}
			}
			else if(departmenttag.equals("-1"))
			{
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getExperimenter()==null){
						if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&"".equals(practiceLesson.getCurriculum().getCurriculumid())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(practiceLesson.getCurriculum().getCurriculumid())&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname())&&
									theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else{
							theolenlist.add(theolenlist1.get(i));
						}
					}	
				}
		}
		else{
			if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())&&
					practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
							.equals(practiceLesson.getCurriculum().getCurriculumid())&&
							theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
					{
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else{
				if(practiceLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumCname())
						&&"".equals(practiceLesson.getCurriculum().getCurriculumid()))
				{
					for(int i=0;i<theolenlist1.size();i++)
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(practiceLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else if(practiceLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(practiceLesson.getCurriculum().getCurriculumid())
						&&"".equals(practiceLesson.getCurriculum().getCurriculumCname()))
				{
					for(int i=0;i<theolenlist1.size();i++)
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(practiceLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
			}
		}
		
		}

		int totalCount=theolenlist.size();
		pageBean.setTotalCount(totalCount);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		pageBean.setTotalPage(totalPage);
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		int begin = (currentpage-1)*pageSize;
		
		//为防止溢出 还需加一个约束条件？？？？？？？？？？
		List<List<PracticeLesson>> sslist=new ArrayList<List<PracticeLesson>>();
		if(currentpage != totalPage){
			sslist=theolenlist.subList(begin, (begin+pageSize));
		}
		else{
			if((begin>=0)&&(begin!=totalCount)){
				sslist=theolenlist.subList(begin,totalCount);
			}
		}

		List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
		
		for(int i=0;i<sslist.size();i++){
			curriculumlist.add(sslist.get(i).get(0).getCurriculum());
		}
		
		//System.out.println("************"+curriculumlist.get(0).getCurriculumEname());
		
		pageBean.setCurriculumlist(curriculumlist);
		pageBean.setPracticeLessonlist(sslist);
		return pageBean;
	}
	private List<List<PracticeLesson>> findtheolenExperlist1(PracticeLesson practiceLesson, String departmenttag) {
		List<List<PracticeLesson>> theolen=new ArrayList<List<PracticeLesson>>();
		List<Curriculum> list=practicePlanDao.findcurrbycollege(practiceLesson.getCurriculum().getCollege());
		
		for(int i=0;i<list.size();i++){
			//找到了课1对应的列表
			List<PracticeLesson> theolenlist=practicePlanDao.findtheolenbycurr1(list.get(i));
			List<PracticeLesson> theolenlist1=new ArrayList<PracticeLesson>();
			List<PracticeLesson> theolenlist2=new ArrayList<PracticeLesson>();
			//遍历课1的列表
			for(int j=0;j<theolenlist.size();j++){
				if(theolenlist.get(j).getExperimenter() == null){
								theolenlist2.add(theolenlist.get(j));	
				}else{
					theolenlist1.add(theolenlist.get(j));
				}
			}
			if(theolenlist1.size()!=0){
				theolen.add(theolenlist1);
			}
			if(theolenlist2.size()!=0){
				theolen.add(theolenlist2);
			}
		}
		return theolen;
	}
	public void updateTheolenByCollegeUser(PracticeLesson practiceLesson, List<String> depart) 
	{
		PracticeLesson practiceLen=new PracticeLesson(); 
		for(int i=0;i<depart.size();i++){
			Curriculum curriculum=curriculumDao.get(practiceLesson.getCurriculum().getCurriculumid());
			if((professionalDao.get(depart.get(i)))!=null)
			{
				practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
			}
			else
			{
				Department department=departmentDao.get(depart.get(i));
				practiceLen=practicePlanDao.findTheoByCurrAndDepart(curriculum,department);
			}
			practiceLen.setExperimenter(userDao.get(practiceLesson.getExperimenter().getTnum()));
			practicePlanDao.update(practiceLen);
		}
	}	

	//实践课实习是否
		public void upLoadFlag(PracticeLesson practiceLesson){
			PracticeLesson newpracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			newpracticeLesson.setWorkload(practiceLesson.getWorkload());
			practicePlanDao.update(newpracticeLesson);
		}
	
	
	//实践课课程设计是否
	public void upLoadFlag1(PracticeLesson practiceLesson){
		PracticeLesson newpracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
		newpracticeLesson.setWorkload1(practiceLesson.getWorkload1());
		practicePlanDao.update(newpracticeLesson);
	}
	
	//实践课实验是否
		public void upLoadFlag2(PracticeLesson practiceLesson){
			PracticeLesson newpracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
			newpracticeLesson.setWorkload2(practiceLesson.getWorkload2());
			practicePlanDao.update(newpracticeLesson);
		}
		
	
		
       //实践毕业设计是否
				public void upLoadFlag3(PracticeLesson practiceLesson){
					PracticeLesson newpracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
					newpracticeLesson.setWorkload3(practiceLesson.getWorkload3());
					practicePlanDao.update(newpracticeLesson);
				}
				
	  //实践其他是否
				public void upLoadFlag4(PracticeLesson practiceLesson){
					PracticeLesson newpracticeLesson = practicePlanDao.get(practiceLesson.getPracticeLessonid());
					newpracticeLesson.setWorkload4(practiceLesson.getWorkload4());
					practicePlanDao.update(newpracticeLesson);
				}
				//复制上面方法1
				public void toCheckPracLesFieldWorkPageAdmin1(String tnum,PracticeLesson practiceLesson) {
					// TODO Auto-generated method stub
					findUserInfo(tnum);
					collegelist = collegeDao.findAll();
					ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
					String zuzhixingshi = "实习";
					List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbycollegeAndsearch0(practiceLesson,zuzhixingshi);//该老师所有应选大纲
					List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					
					while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
						List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
						PracticeLesson pracLess = allPracticeLessonlist.get(0);
						String cid = pracLess.getCurriculum().getCurriculumid();
						for(int ti=0;ti<allPracticeLessonlist.size();ti++)
						{
							if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
							{
								practiceLessonList.add(allPracticeLessonlist.get(ti));
								allPracticeLessonlist.remove(ti);
								ti--;
							}
						}
						professionalList.add(practiceLessonList);
					}
					
					
					List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbycollegeAndsearch0(practiceLesson,zuzhixingshi);
					List<ApplicationSyllabus_FieldWork> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac(newallPracticeLessonlist);//应用大纲的专业/方向

					List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					for(int pi = 0;pi<professionalList.size();pi++)
					{
						List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
						for(int pj=0;pj<professionalList.get(pi).size();pj++)
						{
							PracticeLesson pracLess = professionalList.get(pi).get(pj);
							for(int pk=0;pk<applicationSyllabusList.size();pk++)
							{
								ApplicationSyllabus_FieldWork appSyll = applicationSyllabusList.get(pk);
								if(pracLess.getProfessional() == null){
									if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
										newhaveprofessionalList.add(pracLess);
										professionalList.get(pi).remove(pracLess);
										pj--;
									}
								}
								else{
									if(appSyll.getProfessional() != null){
										if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
											newhaveprofessionalList.add(pracLess);
											professionalList.get(pi).remove(pracLess);
											pj--;
										}
									}
								}
							}
						}
						if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
						haveprofessionalList.add(newhaveprofessionalList);
					}
					
					List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
					if(professionalList != null && professionalList.size() != 0){
						for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
							if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
								professionalList.remove(qi);
							}
						}
						for(int ri=0;ri<professionalList.size();ri++){
							if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
								professionalList.remove(professionalList.get(ri));
								ri--;
							}
							else{
								syllabusList.add(professionalList.get(ri).get(0));
							}
						}
					}
					List<Integer> count = new ArrayList<Integer>();
					if(professionalList != null && professionalList.size() != 0){
						for(int si=0;si<professionalList.size();si++){
							if(professionalList.get(si).size() == 1)
							{
								String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									count.add(professionalList.get(si).size());
								}
								else{
									count.add(-1);
								}
							}
							else{
								count.add(professionalList.get(si).size());
							}
								
							}
							
						}
					
					ServletActionContext.getRequest().setAttribute("count", count);
					ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
					ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
					
					List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
					
					//分开不同大纲ID
					List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					
					if(haveprofessionalList != null && haveprofessionalList.size() != 0)
					{
						for(int ri=0;ri<haveprofessionalList.size();ri++)
						{
							while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
							{		
								List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
								PracticeLesson prac = haveprofessionalList.get(ri).get(0);
								String syid =practicePlanDao.findSyllabusidByPrac_FieldWork(prac);
								for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
								{
									if(syid.equals(practicePlanDao.findSyllabusidByPrac_FieldWork(haveprofessionalList.get(ri).get(rj))))
									{
										newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
										haveprofessionalList.get(ri).remove(rj);
										rj--;
									}
								}
								newhaveprofessionalList.add(newPracticeLesson);
							}
						}
					}
					
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int ri=0;ri<newhaveprofessionalList.size();ri++){
							haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
						}
					}
					List<Integer> haveCount = new ArrayList<Integer>();
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int si=0;si<newhaveprofessionalList.size();si++){
							if(newhaveprofessionalList.get(si).size() == 1)
							{
								String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									haveCount.add(newhaveprofessionalList.get(si).size());
								}
								else{
									haveCount.add(-1);
								}
							}
							else{
								haveCount.add(newhaveprofessionalList.get(si).size());
							}
								
							}
							
						}
				
					List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					for(int hi=0;hi<newhaveprofessionalList.size();hi++)
					{
						PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
						List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyCurriculumAndDepartment(newprac,zuzhixingshi);//该老师所有应选大纲
				
						while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
							List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
							PracticeLesson pracLess = newallPracticeLesson.get(0);
							String cid = pracLess.getCurriculum().getCurriculumid();
							for(int ti=0;ti<newallPracticeLesson.size();ti++)
							{
								if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
								{
									practiceLessonList.add(newallPracticeLesson.get(ti));
									newallPracticeLesson.remove(ti);
									ti--;
								}
							}
							newprofessionalList.add(practiceLessonList);
						}
						
					}
					
					List<String> syllabusId = new ArrayList<String>();
					if(haveSyllabusList != null && haveSyllabusList.size() != 0){
						for(int i=0;i<haveSyllabusList.size();i++){
							String syllabusid = practicePlanDao.findSyllabusidByPrac_FieldWork(haveSyllabusList.get(i));
							syllabusId.add(syllabusid);
						}
					}
					
					
					//--------剔除已完成大纲的专业方向
					List<ApplicationSyllabus_FieldWork> appSyllaList = new ArrayList<ApplicationSyllabus_FieldWork>();
					for(int i=0;i<newprofessionalList.size();i++){
						appSyllaList = applicationSyllabusDao.findAppByPrac_FieldWork(newprofessionalList.get(i).get(0));
						if(appSyllaList != null && appSyllaList.size() != 0){
								
						for(int k=0;k<appSyllaList.size();k++)
						{
							for(int j=0;j<newprofessionalList.get(i).size();j++)
							{	
								if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
								{
									if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_FieldWork(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
									{
										newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
										j--;
									}
								}
							}
						}
						}
					}
					
					ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
					ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
					ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
					ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
					ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
				}
				//复制以上方法
				
				public void toCheckCourseDesignPageAdmin1(String tnum,PracticeLesson practiceLesson) {
					// TODO Auto-generated method stub
					findUserInfo(tnum);
					collegelist = collegeDao.findAll();
					ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
					String zuzhixingshi = "课程设计（学年论文）";
					List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbycollegeAndsearch1(practiceLesson,zuzhixingshi);//该老师所有应选大纲
					List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					
					while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
						List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
						PracticeLesson pracLess = allPracticeLessonlist.get(0);
						String cid = pracLess.getCurriculum().getCurriculumid();
						for(int ti=0;ti<allPracticeLessonlist.size();ti++)
						{
							if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
							{
								practiceLessonList.add(allPracticeLessonlist.get(ti));
								allPracticeLessonlist.remove(ti);
								ti--;
							}
						}
						professionalList.add(practiceLessonList);
					}
					
					
					List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbycollegeAndsearch1(practiceLesson,zuzhixingshi);
					List<ApplicationSyllabus_CourseDesign> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_CourseDesign(newallPracticeLessonlist);//应用大纲的专业/方向

					List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					for(int pi = 0;pi<professionalList.size();pi++)
					{
						List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
						for(int pj=0;pj<professionalList.get(pi).size();pj++)
						{
							PracticeLesson pracLess = professionalList.get(pi).get(pj);
							for(int pk=0;pk<applicationSyllabusList.size();pk++)
							{
								ApplicationSyllabus_CourseDesign appSyll = applicationSyllabusList.get(pk);
								if(pracLess.getProfessional() == null){
									if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
										newhaveprofessionalList.add(pracLess);
										professionalList.get(pi).remove(pracLess);
										pj--;
									}
								}
								else{
									if(appSyll.getProfessional() != null){
										if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
											newhaveprofessionalList.add(pracLess);
											professionalList.get(pi).remove(pracLess);
											pj--;
										}
									}
								}
							}
						}
						if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
						haveprofessionalList.add(newhaveprofessionalList);
					}
					
					List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
					if(professionalList != null && professionalList.size() != 0){
						for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
							if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
								professionalList.remove(qi);
							}
						}
						for(int ri=0;ri<professionalList.size();ri++){
							if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
								professionalList.remove(professionalList.get(ri));
								ri--;
							}
							else{
								syllabusList.add(professionalList.get(ri).get(0));
							}
						}
					}
					List<Integer> count = new ArrayList<Integer>();
					if(professionalList != null && professionalList.size() != 0){
						for(int si=0;si<professionalList.size();si++){
							if(professionalList.get(si).size() == 1)
							{
								String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									count.add(professionalList.get(si).size());
								}
								else{
									count.add(-1);
								}
							}
							else{
								count.add(professionalList.get(si).size());
							}
								
							}
							
						}
					
					ServletActionContext.getRequest().setAttribute("count", count);
					ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
					ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
					
					List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
					
					//分开不同大纲ID
					List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					
					if(haveprofessionalList != null && haveprofessionalList.size() != 0)
					{
						for(int ri=0;ri<haveprofessionalList.size();ri++)
						{
							while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
							{		
								List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
								PracticeLesson prac = haveprofessionalList.get(ri).get(0);
								String syid =practicePlanDao.findSyllabusidByPrac_CourseDesign(prac);
								for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
								{
									if(syid.equals(practicePlanDao.findSyllabusidByPrac_CourseDesign(haveprofessionalList.get(ri).get(rj))))
									{
										newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
										haveprofessionalList.get(ri).remove(rj);
										rj--;
									}
								}
								newhaveprofessionalList.add(newPracticeLesson);
							}
						}
					}
					
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int ri=0;ri<newhaveprofessionalList.size();ri++){
							haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
						}
					}
					List<Integer> haveCount = new ArrayList<Integer>();
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int si=0;si<newhaveprofessionalList.size();si++){
							if(newhaveprofessionalList.get(si).size() == 1)
							{
								String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									haveCount.add(newhaveprofessionalList.get(si).size());
								}
								else{
									haveCount.add(-1);
								}
							}
							else{
								haveCount.add(newhaveprofessionalList.get(si).size());
							}
								
							}
							
						}
				
					List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					for(int hi=0;hi<newhaveprofessionalList.size();hi++)
					{
						PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
						List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyCurriculumAndDepartment(newprac,zuzhixingshi);//该老师所有应选大纲
				
						while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
							List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
							PracticeLesson pracLess = newallPracticeLesson.get(0);
							String cid = pracLess.getCurriculum().getCurriculumid();
							for(int ti=0;ti<newallPracticeLesson.size();ti++)
							{
								if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
								{
									practiceLessonList.add(newallPracticeLesson.get(ti));
									newallPracticeLesson.remove(ti);
									ti--;
								}
							}
							newprofessionalList.add(practiceLessonList);
						}
						
					}
					
					List<String> syllabusId = new ArrayList<String>();
					if(haveSyllabusList != null && haveSyllabusList.size() != 0){
						for(int i=0;i<haveSyllabusList.size();i++){
							String syllabusid = practicePlanDao.findSyllabusidByPrac_CourseDesign(haveSyllabusList.get(i));
							syllabusId.add(syllabusid);
						}
					}
					
				//--------剔除已完成大纲的专业方向
				List<ApplicationSyllabus_CourseDesign> appSyllaList = new ArrayList<ApplicationSyllabus_CourseDesign>();
				for(int i=0;i<newprofessionalList.size();i++){
					appSyllaList = applicationSyllabusDao.findAppByPrac_CourseDesign(newprofessionalList.get(i).get(0));
					if(appSyllaList != null && appSyllaList.size() != 0){
							
					for(int k=0;k<appSyllaList.size();k++)
					{
						for(int j=0;j<newprofessionalList.get(i).size();j++)
						{	
							if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
							{
								if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_CourseDesign(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
								{
									newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
									j--;
								}
							}
						}
					}
					}
				}
					ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
					ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
					ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
					ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
					ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
					
				}
				
				//复制以上方法
				public void toCheckGraduationProjectPageAdmin1(String tnum,PracticeLesson practiceLesson) {
					// TODO Auto-generated method stub
					findUserInfo(tnum);
					collegelist = collegeDao.findAll();
					ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
					String zuzhixingshi = "毕业设计";
					List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbycollegeAndsearch3(practiceLesson,zuzhixingshi);//该老师所有应选大纲
					List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					
					while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
						List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
						PracticeLesson pracLess = allPracticeLessonlist.get(0);
						String cid = pracLess.getCurriculum().getCurriculumid();
						for(int ti=0;ti<allPracticeLessonlist.size();ti++)
						{
							if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
							{
								practiceLessonList.add(allPracticeLessonlist.get(ti));
								allPracticeLessonlist.remove(ti);
								ti--;
							}
						}
						professionalList.add(practiceLessonList);
					}
					
					List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbycollegeAndsearch3(practiceLesson,zuzhixingshi);
					List<ApplicationSyllabus_Gra> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_Gra(newallPracticeLessonlist);//应用大纲的专业/方向

					List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					for(int pi = 0;pi<professionalList.size();pi++)
					{
						List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
						for(int pj=0;pj<professionalList.get(pi).size();pj++)
						{
							PracticeLesson pracLess = professionalList.get(pi).get(pj);
							for(int pk=0;pk<applicationSyllabusList.size();pk++)
							{
								ApplicationSyllabus_Gra appSyll = applicationSyllabusList.get(pk);
								if(pracLess.getProfessional() == null){
									if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
										newhaveprofessionalList.add(pracLess);
										professionalList.get(pi).remove(pracLess);
										pj--;
									}
								}
								else{
									if(appSyll.getProfessional() != null){
										if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
											newhaveprofessionalList.add(pracLess);
											professionalList.get(pi).remove(pracLess);
											pj--;
										}
									}
								}
							}
						}
						if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
						haveprofessionalList.add(newhaveprofessionalList);
					}
					
					List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
					if(professionalList != null && professionalList.size() != 0){
						for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
							if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
								professionalList.remove(qi);
							}
						}
						for(int ri=0;ri<professionalList.size();ri++){
							if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
								professionalList.remove(professionalList.get(ri));
								ri--;
							}
							else{
								syllabusList.add(professionalList.get(ri).get(0));
							}
						}
					}
					List<Integer> count = new ArrayList<Integer>();
					if(professionalList != null && professionalList.size() != 0){
						for(int si=0;si<professionalList.size();si++){
							if(professionalList.get(si).size() == 1)
							{
								String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									count.add(professionalList.get(si).size());
								}
								else{
									count.add(-1);
								}
							}
							else{
								count.add(professionalList.get(si).size());
							}
								
							}
							
						}
					
					ServletActionContext.getRequest().setAttribute("count", count);
					ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
					ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
					List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
					
					//分开不同大纲ID
					List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					
					if(haveprofessionalList != null && haveprofessionalList.size() != 0)
					{
						for(int ri=0;ri<haveprofessionalList.size();ri++)
						{
							while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
							{		
								List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
								PracticeLesson prac = haveprofessionalList.get(ri).get(0);
								String syid =practicePlanDao.findSyllabusidByPrac_Gra(prac);
								for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
								{
									if(syid.equals(practicePlanDao.findSyllabusidByPrac_Gra(haveprofessionalList.get(ri).get(rj))))
									{
										newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
										haveprofessionalList.get(ri).remove(rj);
										rj--;
									}
								}
								newhaveprofessionalList.add(newPracticeLesson);
							}
						}
					}
					
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int ri=0;ri<newhaveprofessionalList.size();ri++){
							haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
						}
					}
					List<Integer> haveCount = new ArrayList<Integer>();
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int si=0;si<newhaveprofessionalList.size();si++){
							if(newhaveprofessionalList.get(si).size() == 1)
							{
								String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									haveCount.add(newhaveprofessionalList.get(si).size());
								}
								else{
									haveCount.add(-1);
								}
							}
							else{
								haveCount.add(newhaveprofessionalList.get(si).size());
							}
								
							}
							
						}
				
					List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					for(int hi=0;hi<newhaveprofessionalList.size();hi++)
					{
						PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
						List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyCurriculumAndDepartment(newprac,zuzhixingshi);//该老师所有应选大纲
				
						while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
							List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
							PracticeLesson pracLess = newallPracticeLesson.get(0);
							String cid = pracLess.getCurriculum().getCurriculumid();
							for(int ti=0;ti<newallPracticeLesson.size();ti++)
							{
								if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
								{
									practiceLessonList.add(newallPracticeLesson.get(ti));
									newallPracticeLesson.remove(ti);
									ti--;
								}
							}
							newprofessionalList.add(practiceLessonList);
						}
						
					}
					
					List<String> syllabusId = new ArrayList<String>();
					if(haveSyllabusList != null && haveSyllabusList.size() != 0){
						for(int i=0;i<haveSyllabusList.size();i++){
							String syllabusid = practicePlanDao.findSyllabusidByPrac_Gra(haveSyllabusList.get(i));
							syllabusId.add(syllabusid);
						}
					}
					
					//--------剔除已完成大纲的专业方向
					List<ApplicationSyllabus_Gra> appSyllaList = new ArrayList<ApplicationSyllabus_Gra>();
					for(int i=0;i<newprofessionalList.size();i++){
						appSyllaList = applicationSyllabusDao.findAppByPrac_Gra(newprofessionalList.get(i).get(0));
						if(appSyllaList != null && appSyllaList.size() != 0){
								
						for(int k=0;k<appSyllaList.size();k++)
						{
							for(int j=0;j<newprofessionalList.get(i).size();j++)
							{	
								if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
								{
									if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_Gra(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
									{
										newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
										j--;
									}
								}
							}
						}
						}
					}
					
					ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
					ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
					ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
					ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
					ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
				}

				//复制以上内容
				public void toCheckOtherPageAdmin1(String tnum,PracticeLesson theoreticalLesson) {
					findUserInfo(tnum);
						collegelist = collegeDao.findAll();
					
					ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
					
					List<PracticeLesson> allTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearchOther(theoreticalLesson);//该老师所有应选大纲
					List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					
					while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
						List<PracticeLesson> theoreticalLessonList = new ArrayList<PracticeLesson>();
						PracticeLesson theoLess = allTheoreticalLessonlist.get(0);
						String cid = theoLess.getCurriculum().getCurriculumid();
						for(int ti=0;ti<allTheoreticalLessonlist.size();ti++)
						{
							if(cid.equals(allTheoreticalLessonlist.get(ti).getCurriculum().getCurriculumid()) && theoLess.getDepartment().getDepartmentid().equals(allTheoreticalLessonlist.get(ti).getDepartment().getDepartmentid()))
							{
								theoreticalLessonList.add(allTheoreticalLessonlist.get(ti));
								allTheoreticalLessonlist.remove(ti);
								ti--;
							}
						}
						professionalList.add(theoreticalLessonList);
					}
					
					
					List<PracticeLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearchOther(theoreticalLesson);
					List<ApplicationSyllabus> applicationSyllabusList = applicationSyllabusDao.findPracticeLessonhaveSyllabusList(newallTheoreticalLessonlist);//应用大纲的专业/方向

					List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					for(int pi = 0;pi<professionalList.size();pi++)
					{
						
						List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
						for(int pj=0;pj<professionalList.get(pi).size();pj++)
						{
							PracticeLesson theoLess = professionalList.get(pi).get(pj);
							for(int pk=0;pk<applicationSyllabusList.size();pk++)
							{
								ApplicationSyllabus appSyll = applicationSyllabusList.get(pk);
								if(theoLess.getProfessional() == null){
									if(theoLess.getCurriculum() == appSyll.getCurriculum() &&theoLess.getDepartment() == appSyll.getDepartment()){
										newhaveprofessionalList.add(theoLess);
										professionalList.get(pi).remove(theoLess);
										pj--;
									}
								}
								else{
									if(appSyll.getProfessional() != null){
										if(theoLess.getCurriculum() == appSyll.getCurriculum() && theoLess.getDepartment() == appSyll.getDepartment() && theoLess.getProfessional() == appSyll.getProfessional() ){
											//&& theoreticalPlanDao.findSyllabusidByTheo(theoLess) == String.valueOf(appSyll.getSyllabus().getSyllabusid())
												newhaveprofessionalList.add(theoLess);
												professionalList.get(pi).remove(theoLess);
												pj--;
										}
									}
								}
							}
						}
						if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
						haveprofessionalList.add(newhaveprofessionalList);
					}
					
					List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
					if(professionalList != null && professionalList.size() != 0){
						for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
							if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
								professionalList.remove(qi);
							}
						}
						for(int ri=0;ri<professionalList.size();ri++){
							if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
								professionalList.remove(professionalList.get(ri));
								ri--;
							}
							else{
								syllabusList.add(professionalList.get(ri).get(0));
							}
						}
					}
					List<Integer> count = new ArrayList<Integer>();
					if(professionalList != null && professionalList.size() != 0){
						for(int si=0;si<professionalList.size();si++){
							if(professionalList.get(si).size() == 1)
							{
								String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									count.add(professionalList.get(si).size());
								}
								else{
									count.add(-1);
								}
							}
							else{
								count.add(professionalList.get(si).size());
							}
								
							}
							
						}
					
					ServletActionContext.getRequest().setAttribute("count", count);
					ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
					ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
					List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
					
					
					//分开不同大纲ID
					List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					
					if(haveprofessionalList != null && haveprofessionalList.size() != 0)
					{
						for(int ri=0;ri<haveprofessionalList.size();ri++)
						{
							while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
							{		
								List<PracticeLesson> newTheoreticalLesson = new ArrayList<PracticeLesson>();
								PracticeLesson theo = haveprofessionalList.get(ri).get(0);
								String syid =theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(theo);
								for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
								{
									if(syid.equals(theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(haveprofessionalList.get(ri).get(rj))))
									{
										newTheoreticalLesson.add(haveprofessionalList.get(ri).get(rj));
										haveprofessionalList.get(ri).remove(rj);
										rj--;
									}
								}
								newhaveprofessionalList.add(newTheoreticalLesson);
							}
						}
					}
					
					
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int ri=0;ri<newhaveprofessionalList.size();ri++){
							haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
						}
					}
					List<Integer> haveCount = new ArrayList<Integer>();
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int si=0;si<newhaveprofessionalList.size();si++){
							if(newhaveprofessionalList.get(si).size() == 1)
							{
								String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									haveCount.add(newhaveprofessionalList.get(si).size());
								}
								else{
									haveCount.add(-1);
								}
							}
							else{
								haveCount.add(newhaveprofessionalList.get(si).size());
							}
								
							}
							
						}
				
					List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					for(int hi=0;hi<newhaveprofessionalList.size();hi++)
					{
						PracticeLesson newtheo = newhaveprofessionalList.get(hi).get(0);
						List<PracticeLesson> newallTheoreticalLesson = theoreticalPlanDao.getbyCurriculumAndDepartmentOther(newtheo);//该老师所有应选大纲
				
						while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
							List<PracticeLesson> theoreticalLessonList = new ArrayList<PracticeLesson>();
							PracticeLesson theoLess = newallTheoreticalLesson.get(0);
							String cid = theoLess.getCurriculum().getCurriculumid();
							for(int ti=0;ti<newallTheoreticalLesson.size();ti++)
							{
								if(cid.equals(newallTheoreticalLesson.get(ti).getCurriculum().getCurriculumid()))
								{
									theoreticalLessonList.add(newallTheoreticalLesson.get(ti));
									newallTheoreticalLesson.remove(ti);
									ti--;
								}
							}
							newprofessionalList.add(theoreticalLessonList);
						}
						
					}
					
					List<String> syllabusId = new ArrayList<String>();
					if(haveSyllabusList != null && haveSyllabusList.size() != 0){
						for(int i=0;i<haveSyllabusList.size();i++){
							String syllabusid = theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(haveSyllabusList.get(i));
							syllabusId.add(syllabusid);
						}
					}
					
					
					//--------剔除已完成大纲的专业方向
					List<ApplicationSyllabus> appSyllaList = new ArrayList<ApplicationSyllabus>();
					for(int i=0;i<newprofessionalList.size();i++){
						appSyllaList = applicationSyllabusDao.findPracticeLessonAppByTheo(newprofessionalList.get(i).get(0));
						if(appSyllaList != null && appSyllaList.size() != 0){
								
						for(int k=0;k<appSyllaList.size();k++)
						{
							for(int j=0;j<newprofessionalList.get(i).size();j++)
							{	
								if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
								{
									if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
									{
										newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
										j--;
									}
								}
							}
						}
						}
					}
					
					
					ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
					ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
					ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
					ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
					ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
					
				}
				//新方法输入
				
				public void toCheckInnerExperimentPageAdmin1(String tnum,PracticeLesson practiceLesson) {
					// TODO Auto-generated method stub
					findUserInfo(tnum);
					
						collegelist = collegeDao.findAll();
					
					ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
					String zuzhixingshi = "实验";
					List<PracticeLesson> allPracticeLessonlist = practicePlanDao.getbycollegeAndsearch2(practiceLesson,zuzhixingshi);//该老师所有应选大纲
					List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					
					while(allPracticeLessonlist != null && allPracticeLessonlist.size() != 0){//生成professionalList
						List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
						PracticeLesson pracLess = allPracticeLessonlist.get(0);
						String cid = pracLess.getCurriculum().getCurriculumid();
						for(int ti=0;ti<allPracticeLessonlist.size();ti++)
						{
							if(cid.equals(allPracticeLessonlist.get(ti).getCurriculum().getCurriculumid()) && pracLess.getDepartment().getDepartmentid().equals(allPracticeLessonlist.get(ti).getDepartment().getDepartmentid()))
							{
								practiceLessonList.add(allPracticeLessonlist.get(ti));
								allPracticeLessonlist.remove(ti);
								ti--;
							}
						}
						professionalList.add(practiceLessonList);
					}
					
					List<PracticeLesson> newallPracticeLessonlist = practicePlanDao.getbycollegeAndsearch2(practiceLesson,zuzhixingshi);
					List<ApplicationSyllabus_InnerExperiment> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusListPrac_InnerExperiment(newallPracticeLessonlist);//应用大纲的专业/方向

					List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					for(int pi = 0;pi<professionalList.size();pi++)
					{
						List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
						for(int pj=0;pj<professionalList.get(pi).size();pj++)
						{
							PracticeLesson pracLess = professionalList.get(pi).get(pj);
							for(int pk=0;pk<applicationSyllabusList.size();pk++)
							{
								ApplicationSyllabus_InnerExperiment appSyll = applicationSyllabusList.get(pk);
								if(pracLess.getProfessional() == null){
									if(pracLess.getCurriculum() == appSyll.getCurriculum() &&pracLess.getDepartment() == appSyll.getDepartment()){
										newhaveprofessionalList.add(pracLess);
										professionalList.get(pi).remove(pracLess);
										pj--;
									}
								}
								else{
									if(appSyll.getProfessional() != null){
										if(pracLess.getCurriculum() == appSyll.getCurriculum() && pracLess.getDepartment() == appSyll.getDepartment() && pracLess.getProfessional() == appSyll.getProfessional()){
											newhaveprofessionalList.add(pracLess);
											professionalList.get(pi).remove(pracLess);
											pj--;
										}
									}
								}
							}
						}
						if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
						haveprofessionalList.add(newhaveprofessionalList);
					}
					
					List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
					if(professionalList != null && professionalList.size() != 0){
						for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
							if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
								professionalList.remove(qi);
							}
						}
						for(int ri=0;ri<professionalList.size();ri++){
							if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
								professionalList.remove(professionalList.get(ri));
								ri--;
							}
							else{
								syllabusList.add(professionalList.get(ri).get(0));
							}
						}
					}
					List<Integer> count = new ArrayList<Integer>();
					if(professionalList != null && professionalList.size() != 0){
						for(int si=0;si<professionalList.size();si++){
							if(professionalList.get(si).size() == 1)
							{
								String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									count.add(professionalList.get(si).size());
								}
								else{
									count.add(-1);
								}
							}
							else{
								count.add(professionalList.get(si).size());
							}
								
							}
							
						}
					
					ServletActionContext.getRequest().setAttribute("count", count);
					ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
					ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
					
					List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
					
					//分开不同大纲ID
					List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					
					if(haveprofessionalList != null && haveprofessionalList.size() != 0)
					{
						for(int ri=0;ri<haveprofessionalList.size();ri++)
						{
							while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
							{		
								List<PracticeLesson> newPracticeLesson = new ArrayList<PracticeLesson>();
								PracticeLesson prac = haveprofessionalList.get(ri).get(0);
								String syid =practicePlanDao.findSyllabusidByPrac_InnerExperiment(prac);
								for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
								{
									if(syid.equals(practicePlanDao.findSyllabusidByPrac_InnerExperiment(haveprofessionalList.get(ri).get(rj))))
									{
										newPracticeLesson.add(haveprofessionalList.get(ri).get(rj));
										haveprofessionalList.get(ri).remove(rj);
										rj--;
									}
								}
								newhaveprofessionalList.add(newPracticeLesson);
							}
						}
					}
					
					
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int ri=0;ri<newhaveprofessionalList.size();ri++){
							haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
						}
					}
					List<Integer> haveCount = new ArrayList<Integer>();
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int si=0;si<newhaveprofessionalList.size();si++){
							if(newhaveprofessionalList.get(si).size() == 1)
							{
								String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									haveCount.add(newhaveprofessionalList.get(si).size());
								}
								else{
									haveCount.add(-1);
								}
							}
							else{
								haveCount.add(newhaveprofessionalList.get(si).size());
							}
								
							}
							
						}
				
					List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					for(int hi=0;hi<newhaveprofessionalList.size();hi++)
					{
						PracticeLesson newprac = newhaveprofessionalList.get(hi).get(0);
						List<PracticeLesson> newallPracticeLesson = practicePlanDao.getbyCurriculumAndDepartment(newprac,zuzhixingshi);//该老师所有应选大纲
				
						while(newallPracticeLesson != null && newallPracticeLesson.size() != 0){//生成professionalList
							List<PracticeLesson> practiceLessonList = new ArrayList<PracticeLesson>();
							PracticeLesson pracLess = newallPracticeLesson.get(0);
							String cid = pracLess.getCurriculum().getCurriculumid();
							for(int ti=0;ti<newallPracticeLesson.size();ti++)
							{
								if(cid.equals(newallPracticeLesson.get(ti).getCurriculum().getCurriculumid()))
								{
									practiceLessonList.add(newallPracticeLesson.get(ti));
									newallPracticeLesson.remove(ti);
									ti--;
								}
							}
							newprofessionalList.add(practiceLessonList);
						}
						
					}
					
					List<String> syllabusId = new ArrayList<String>();
					if(haveSyllabusList != null && haveSyllabusList.size() != 0){
						for(int i=0;i<haveSyllabusList.size();i++){
							String syllabusid = practicePlanDao.findSyllabusidByPrac_InnerExperiment(haveSyllabusList.get(i));
							syllabusId.add(syllabusid);
						}
					}
					
					//--------剔除已完成大纲的专业方向
					List<ApplicationSyllabus_InnerExperiment> appSyllaList = new ArrayList<ApplicationSyllabus_InnerExperiment>();
					for(int i=0;i<newprofessionalList.size();i++){
						appSyllaList = applicationSyllabusDao.findAppByPrac_InnerExperiment(newprofessionalList.get(i).get(0));
						if(appSyllaList != null && appSyllaList.size() != 0){
								
						for(int k=0;k<appSyllaList.size();k++)
						{
							for(int j=0;j<newprofessionalList.get(i).size();j++)
							{	
								if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
								{
									if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !practicePlanDao.findSyllabusidByPrac_InnerExperiment(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
									{
										newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
										j--;
									}
								}
							}
						}
						}
					}
					
					ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
					ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
					ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
					ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
					ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
				}
				public void toCheckPracticeOther(PracticeLesson practiceLesson) {//理论课大纲状态页面
					User user = userDao.get(practiceLesson.getTeacher().getTnum());
					List<PracticeLesson> allTheoreticalLessonlist = theoreticalPlanDao.getPracticeLessonbyuser(user);//该老师所有应选大纲
					List<List<PracticeLesson>> professionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					
					while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
						List<PracticeLesson> theoreticalLessonList = new ArrayList<PracticeLesson>();
						PracticeLesson theoLess = allTheoreticalLessonlist.get(0);
						String cid = theoLess.getCurriculum().getCurriculumid();
						for(int ti=0;ti<allTheoreticalLessonlist.size();ti++)
						{
							if(cid.equals(allTheoreticalLessonlist.get(ti).getCurriculum().getCurriculumid()) && theoLess.getDepartment().getDepartmentid().equals(allTheoreticalLessonlist.get(ti).getDepartment().getDepartmentid()))
							{
								theoreticalLessonList.add(allTheoreticalLessonlist.get(ti));
								allTheoreticalLessonlist.remove(ti);
								ti--;
							}
						}
						professionalList.add(theoreticalLessonList);
					}
					
					
					List<PracticeLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getPracticeLessonbyuser(user);
					List<ApplicationSyllabus> applicationSyllabusList = applicationSyllabusDao.findPracticeLessonhaveSyllabusList(newallTheoreticalLessonlist);//应用大纲的专业/方向

					List<List<PracticeLesson>> haveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					for(int pi = 0;pi<professionalList.size();pi++)
					{
						
						List<PracticeLesson> newhaveprofessionalList = new ArrayList<PracticeLesson>();
						for(int pj=0;pj<professionalList.get(pi).size();pj++)
						{
							PracticeLesson theoLess = professionalList.get(pi).get(pj);
							for(int pk=0;pk<applicationSyllabusList.size();pk++)
							{
								ApplicationSyllabus appSyll = applicationSyllabusList.get(pk);
								if(theoLess.getProfessional() == null){
									if(theoLess.getCurriculum() == appSyll.getCurriculum() &&theoLess.getDepartment() == appSyll.getDepartment()){
										newhaveprofessionalList.add(theoLess);
										professionalList.get(pi).remove(theoLess);
										pj--;
									}
								}
								else{
									if(appSyll.getProfessional() != null){
										if(theoLess.getCurriculum() == appSyll.getCurriculum() && theoLess.getDepartment() == appSyll.getDepartment() && theoLess.getProfessional() == appSyll.getProfessional() ){
											//&& theoreticalPlanDao.findSyllabusidByTheo(theoLess) == String.valueOf(appSyll.getSyllabus().getSyllabusid())
												newhaveprofessionalList.add(theoLess);
												professionalList.get(pi).remove(theoLess);
												pj--;
										}
									}
								}
							}
						}
						if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0)
						haveprofessionalList.add(newhaveprofessionalList);
					}
					
					List<PracticeLesson> syllabusList = new ArrayList<PracticeLesson>();//合并
					if(professionalList != null && professionalList.size() != 0){
						for(int qi=0;qi<professionalList.size();qi++){//删除嵌套List中size=0的项
							if(professionalList.get(qi) == null || professionalList.get(qi).size() == 0){
								professionalList.remove(qi);
							}
						}
						for(int ri=0;ri<professionalList.size();ri++){
							if(professionalList.get(ri) == null || professionalList.get(ri).size() == 0){
								professionalList.remove(professionalList.get(ri));
								ri--;
							}
							else{
								syllabusList.add(professionalList.get(ri).get(0));
							}
						}
					}
					List<Integer> count = new ArrayList<Integer>();
					if(professionalList != null && professionalList.size() != 0){
						for(int si=0;si<professionalList.size();si++){
							if(professionalList.get(si).size() == 1)
							{
								String departmentid = professionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									count.add(professionalList.get(si).size());
								}
								else{
									count.add(-1);
								}
							}
							else{
								count.add(professionalList.get(si).size());
							}
								
							}
							
						}
					
					ServletActionContext.getRequest().setAttribute("count", count);
					ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);
					ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
					List<PracticeLesson> haveSyllabusList = new ArrayList<PracticeLesson>();//合并
					
					
					//分开不同大纲ID
					List<List<PracticeLesson>> newhaveprofessionalList = new ArrayList<List<PracticeLesson>>();//已经编写的大纲
					
					if(haveprofessionalList != null && haveprofessionalList.size() != 0)
					{
						for(int ri=0;ri<haveprofessionalList.size();ri++)
						{
							while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
							{		
								List<PracticeLesson> newTheoreticalLesson = new ArrayList<PracticeLesson>();
								PracticeLesson theo = haveprofessionalList.get(ri).get(0);
								String syid =theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(theo);
								for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
								{
									if(syid.equals(theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(haveprofessionalList.get(ri).get(rj))))
									{
										newTheoreticalLesson.add(haveprofessionalList.get(ri).get(rj));
										haveprofessionalList.get(ri).remove(rj);
										rj--;
									}
								}
								newhaveprofessionalList.add(newTheoreticalLesson);
							}
						}
					}
					
					
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int ri=0;ri<newhaveprofessionalList.size();ri++){
							haveSyllabusList.add(newhaveprofessionalList.get(ri).get(0));
						}
					}
					List<Integer> haveCount = new ArrayList<Integer>();
					if(newhaveprofessionalList != null && newhaveprofessionalList.size() != 0){
						for(int si=0;si<newhaveprofessionalList.size();si++){
							if(newhaveprofessionalList.get(si).size() == 1)
							{
								String departmentid = newhaveprofessionalList.get(si).get(0).getDepartment().getDepartmentid();
								List<Professional> professionalList1 = findProfessional(departmentid);		
								Integer professionalListCount = professionalList1.size();		
								if(professionalListCount>0){
									haveCount.add(newhaveprofessionalList.get(si).size());
								}
								else{
									haveCount.add(-1);
								}
							}
							else{
								haveCount.add(newhaveprofessionalList.get(si).size());
							}
								
							}
							
						}
				
					List<List<PracticeLesson>> newprofessionalList = new ArrayList<List<PracticeLesson>>();//定义该老师所有未编写大纲
					for(int hi=0;hi<newhaveprofessionalList.size();hi++)
					{
						PracticeLesson newtheo = newhaveprofessionalList.get(hi).get(0);
						List<PracticeLesson> newallTheoreticalLesson = theoreticalPlanDao.getPracticeLessonbyuser(user,newtheo);//该老师所有应选大纲
				
						while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
							List<PracticeLesson> theoreticalLessonList = new ArrayList<PracticeLesson>();
							PracticeLesson theoLess = newallTheoreticalLesson.get(0);
							String cid = theoLess.getCurriculum().getCurriculumid();
							for(int ti=0;ti<newallTheoreticalLesson.size();ti++)
							{
								if(cid.equals(newallTheoreticalLesson.get(ti).getCurriculum().getCurriculumid()))
								{
									theoreticalLessonList.add(newallTheoreticalLesson.get(ti));
									newallTheoreticalLesson.remove(ti);
									ti--;
								}
							}
							newprofessionalList.add(theoreticalLessonList);
						}
						
					}
					
					List<String> syllabusId = new ArrayList<String>();
					if(haveSyllabusList != null && haveSyllabusList.size() != 0){
						for(int i=0;i<haveSyllabusList.size();i++){
							String syllabusid = theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(haveSyllabusList.get(i));
							syllabusId.add(syllabusid);
						}
					}
					
					
					//--------剔除已完成大纲的专业方向
					List<ApplicationSyllabus> appSyllaList = new ArrayList<ApplicationSyllabus>();
					for(int i=0;i<newprofessionalList.size();i++){
						appSyllaList = applicationSyllabusDao.findPracticeLessonAppByTheo(newprofessionalList.get(i).get(0));
						if(appSyllaList != null && appSyllaList.size() != 0){
								
						for(int k=0;k<appSyllaList.size();k++)
						{
							for(int j=0;j<newprofessionalList.get(i).size();j++)
							{	
								if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
								{
									if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findPracticeLessonSyllabusidByTheo(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
									{
										newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
										j--;
									}
								}
							}
						}
						}
					}
					
					
					ServletActionContext.getRequest().setAttribute("haveCount", haveCount);
					ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
					ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
					ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);
					ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);
				}



	
}
