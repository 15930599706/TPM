package com.tpm.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import org.hibernate.mapping.Array;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.tpm.dao.AbilityAndTeachObjDao;
import com.tpm.dao.AbilityAndTeachObj_TheoInnerExperimentDao;
import com.tpm.dao.ApplicationSyllabusDao;
import com.tpm.dao.ApplicationSyllabus_TheoInnerExperimentDao;
import com.tpm.dao.BaseExpTheoInnerRelateExpProjectTheoDao;
import com.tpm.dao.CollegeDao;
import com.tpm.dao.ContentTheoDao;
import com.tpm.dao.CurriculumDao;
import com.tpm.dao.CurriculumMatrixDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.DiscussContentDao;
import com.tpm.dao.DistributeHour_TheoDao;
import com.tpm.dao.DistributeHour_TheoInnerExperimentDao;
import com.tpm.dao.ExperimentContentDao;
import com.tpm.dao.ExperimentDao;
import com.tpm.dao.ExpermentProject_InnerExperimentDao;
import com.tpm.dao.IndexSelectDao;
import com.tpm.dao.IndexSelect_TheoInnerExperimentDao;
import com.tpm.dao.OtherContentDao;
import com.tpm.dao.ProfessionalDao;
import com.tpm.dao.SyllabusDao;
import com.tpm.dao.Syllabus_TheoInnerExperimentDao;
import com.tpm.dao.TeachObjDao;
import com.tpm.dao.TeachObj_TheoInnerExperimentDao;
import com.tpm.dao.TextBooksDao;
import com.tpm.dao.TextBooks_InnerExperimentDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.dao.ThreeProjectDao;
import com.tpm.dao.UserDao;
import com.tpm.entity.AbilityAndTeachObj;
import com.tpm.entity.AbilityAndTeachObj_TheoInnerExperiment;
import com.tpm.entity.ApplicationSyllabus;
import com.tpm.entity.ApplicationSyllabus_TheoInnerExperiment;
import com.tpm.entity.BaseExpTheoInnerRelateExpProjectTheo;
import com.tpm.entity.College;
import com.tpm.entity.ContentTheo;
import com.tpm.entity.Curriculum;
import com.tpm.entity.CurriculumMatrix;
import com.tpm.entity.Department;
import com.tpm.entity.DiscussContent;
import com.tpm.entity.DistributeHour_Theo;
import com.tpm.entity.DistributeHour_TheoInnerExperiment;
import com.tpm.entity.Experiment;
import com.tpm.entity.ExperimentContent;
import com.tpm.entity.ExpermentProject;
import com.tpm.entity.ExpermentProject_Theo;
import com.tpm.entity.IndexSelect;
import com.tpm.entity.IndexSelect_TheoInnerExperiment;
import com.tpm.entity.OtherContent;
import com.tpm.entity.PageBean;
import com.tpm.entity.PracticeBooks_InnerExperiment;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Professional;
import com.tpm.entity.Syllabus;
import com.tpm.entity.Syllabus_InnerExperiment;
import com.tpm.entity.Syllabus_TheoInnerExperiment;
import com.tpm.entity.TeachObj;
import com.tpm.entity.TeachObj_TheoInnerExperiment;
import com.tpm.entity.TextBooks;
import com.tpm.entity.TextBooks_InnerExperiment;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.ThreeProject;
import com.tpm.entity.User;

@Transactional
public class TheoreticalPlanService {
	private TheoreticalPlanDao theoreticalPlanDao;
	private CurriculumDao curriculumDao;
	private DepartmentDao departmentDao;
	private ProfessionalDao professionalDao;
	private UserDao userDao;
	private CollegeDao collegeDao;
	private SyllabusDao syllabusDao;
	private ApplicationSyllabusDao applicationSyllabusDao;
	
	private ContentTheoDao contentTheoDao;
	private ExperimentContentDao experimentContentDao;
	private DiscussContentDao discussContentDao;
	private ThreeProjectDao threeProjectDao;
	private TextBooksDao textBooksDao;
	private OtherContentDao otherContentDao;
	private BaseExpTheoInnerRelateExpProjectTheoDao relateDao;
	
	private IndexSelectDao indexSelectDao;
	private TeachObjDao teachObjDao;
	private AbilityAndTeachObjDao abilityAndTeachObjDao;
	
	private Syllabus_TheoInnerExperimentDao syllabus_TheoInnerExperimentDao;
	private ApplicationSyllabus_TheoInnerExperimentDao applicationSyllabus_TheoInnerExperimentDao;
	private IndexSelect_TheoInnerExperimentDao indexSelect_TheoInnerExperimentDao;
	private TeachObj_TheoInnerExperimentDao teachObj_TheoInnerExperimentDao;
	private AbilityAndTeachObj_TheoInnerExperimentDao abilityAndTeachObj_TheoInnerExperimentDao;
	private ExpermentProject_InnerExperimentDao expermentProject_InnerExperimentDao;
	private TextBooks_InnerExperimentDao textBooks_InnerExperimentDao;
	private DistributeHour_TheoDao distributeHour_TheoDao;
	private DistributeHour_TheoInnerExperimentDao distributeHour_TheoInnerExperimentDao;
	
	private CurriculumMatrixDao curriculumMatrixDao;
	private ExperimentDao experimentDao;
	public void setRelateDao(BaseExpTheoInnerRelateExpProjectTheoDao relateDao) {
		this.relateDao = relateDao;
	}
	public void setExperimentDao(ExperimentDao experimentDao) {
		this.experimentDao = experimentDao;
	}
	public void setCurriculumMatrixDao(CurriculumMatrixDao curriculumMatrixDao) {
		this.curriculumMatrixDao = curriculumMatrixDao;
	}
	public void setDistributeHour_TheoInnerExperimentDao(
			DistributeHour_TheoInnerExperimentDao distributeHour_TheoInnerExperimentDao) {
		this.distributeHour_TheoInnerExperimentDao = distributeHour_TheoInnerExperimentDao;
	}
	public void setDistributeHour_TheoDao(
			DistributeHour_TheoDao distributeHour_TheoDao) {
		this.distributeHour_TheoDao = distributeHour_TheoDao;
	}
	public void setExpermentProject_InnerExperimentDao(
			ExpermentProject_InnerExperimentDao expermentProject_InnerExperimentDao) {
		this.expermentProject_InnerExperimentDao = expermentProject_InnerExperimentDao;
	}
	public void setTextBooks_InnerExperimentDao(
			TextBooks_InnerExperimentDao textBooks_InnerExperimentDao) {
		this.textBooks_InnerExperimentDao = textBooks_InnerExperimentDao;
	}
	public void setIndexSelect_TheoInnerExperimentDao(
			IndexSelect_TheoInnerExperimentDao indexSelect_TheoInnerExperimentDao) {
		this.indexSelect_TheoInnerExperimentDao = indexSelect_TheoInnerExperimentDao;
	}
	public void setTeachObj_TheoInnerExperimentDao(
			TeachObj_TheoInnerExperimentDao teachObj_TheoInnerExperimentDao) {
		this.teachObj_TheoInnerExperimentDao = teachObj_TheoInnerExperimentDao;
	}
	public void setAbilityAndTeachObj_TheoInnerExperimentDao(
			AbilityAndTeachObj_TheoInnerExperimentDao abilityAndTeachObj_TheoInnerExperimentDao) {
		this.abilityAndTeachObj_TheoInnerExperimentDao = abilityAndTeachObj_TheoInnerExperimentDao;
	}
	public void setSyllabus_TheoInnerExperimentDao(
			Syllabus_TheoInnerExperimentDao syllabus_TheoInnerExperimentDao) {
		this.syllabus_TheoInnerExperimentDao = syllabus_TheoInnerExperimentDao;
	}
	public void setApplicationSyllabus_TheoInnerExperimentDao(
			ApplicationSyllabus_TheoInnerExperimentDao applicationSyllabus_TheoInnerExperimentDao) {
		this.applicationSyllabus_TheoInnerExperimentDao = applicationSyllabus_TheoInnerExperimentDao;
	}
	public void setAbilityAndTeachObjDao(AbilityAndTeachObjDao abilityAndTeachObjDao) {
		this.abilityAndTeachObjDao = abilityAndTeachObjDao;
	}
	public void setTeachObjDao(TeachObjDao teachObjDao) {
		this.teachObjDao = teachObjDao;
	}
	public void setIndexSelectDao(IndexSelectDao indexSelectDao) {
		this.indexSelectDao = indexSelectDao;
	}
	public void setOtherContentDao(OtherContentDao otherContentDao) {
		this.otherContentDao = otherContentDao;
	}
	public void setTextBooksDao(TextBooksDao textBooksDao) {
		this.textBooksDao = textBooksDao;
	}
	public void setThreeProjectDao(ThreeProjectDao threeProjectDao) {
		this.threeProjectDao = threeProjectDao;
	}
	public void setDiscussContentDao(DiscussContentDao discussContentDao) {
		this.discussContentDao = discussContentDao;
	}
	public void setExperimentContentDao(ExperimentContentDao experimentContentDao) {
		this.experimentContentDao = experimentContentDao;
	}
	public void setContentTheoDao(ContentTheoDao contentTheoDao) {
		this.contentTheoDao = contentTheoDao;
	}
	public void setApplicationSyllabusDao(
			ApplicationSyllabusDao applicationSyllabusDao) {
		this.applicationSyllabusDao = applicationSyllabusDao;
	}
	public void setSyllabusDao(SyllabusDao syllabusDao) {
		this.syllabusDao = syllabusDao;
	}
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
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
	
	public void tollkPage(String tnum, String collegeid, String departmentid){	//跳转到理论课页面
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
				
				if(professionalList == null || professionalList.size() == 0)	//如果该专业不分方向，在进入理论课页面时，要返回所选择的计划列表
				{				
					List<TheoreticalLesson> theoreticalLessonList = findTheoreticalLessonDepartment(department);
					ServletActionContext.getRequest().setAttribute("theoreticalLessonList", theoreticalLessonList);
					
					float publicTotalCredit = 0;	//公共教育平台总学分
					float professionalTotalCredit = 0;	//专业教育平台总学分
					float totalCredit = 0;	//所选的总学分
					String publicProportion;	//公共教育平台占比
					String professionalProportion;	//专业教育平台占比
					for(int i=0;i<theoreticalLessonList.size();i++)	//计算计划中的各学分
					{
						if(theoreticalLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("公共教育平台"))
						{
							publicTotalCredit = publicTotalCredit + Float.parseFloat(theoreticalLessonList.get(i).getCurriculum().getCredit());
						}
						if(theoreticalLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("专业教育平台"))
						{
							professionalTotalCredit = professionalTotalCredit + Float.parseFloat(theoreticalLessonList.get(i).getCurriculum().getCredit());
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
			
			if(professionalList == null || professionalList.size() == 0)	//如果该专业不分方向，在进入理论课页面时，要返回所选择的计划列表
			{				
				List<TheoreticalLesson> theoreticalLessonList = findTheoreticalLessonDepartment(departmentlist.get(0));
				ServletActionContext.getRequest().setAttribute("theoreticalLessonList", theoreticalLessonList);
				
				float publicTotalCredit = 0;	//公共教育平台总学分
				float professionalTotalCredit = 0;	//专业教育平台总学分
				float totalCredit = 0;	//所选的总学分
				String publicProportion;	//公共教育平台占比
				String professionalProportion;	//专业教育平台占比
				for(int i=0;i<theoreticalLessonList.size();i++)	//计算计划中的各学分
				{
					if(theoreticalLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("公共教育平台"))
					{
						publicTotalCredit = publicTotalCredit + Float.parseFloat(theoreticalLessonList.get(i).getCurriculum().getCredit());
					}
					if(theoreticalLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("专业教育平台"))
					{
						professionalTotalCredit = professionalTotalCredit + Float.parseFloat(theoreticalLessonList.get(i).getCurriculum().getCredit());
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
	
	public void llkResult(TheoreticalLesson theoreticalLesson, String tnum){//理论课查询结果
		findUserInfo(tnum);
		if(user.getAdminlevel() == 5){
			collegelist = collegeDao.findAll();
			ServletActionContext.getRequest().setAttribute("xueyuan", theoreticalLesson.getDepartment().getCollege().getCollegeid());		//返回所选的学院
			ServletActionContext.getRequest().setAttribute("xi", theoreticalLesson.getDepartment().getDepartmentid());		//返回所选择的系								
		}
		if(user.getAdminlevel() == 3){
			collegelist = findCollegeById(user.getCollege().getCollegeid());
			ServletActionContext.getRequest().setAttribute("xi", theoreticalLesson.getDepartment().getDepartmentid());		//返回所选择的系								
		}
		if(user.getAdminlevel() == 1){
			collegelist = findCollegeById(user.getCollege().getCollegeid());
			departmentlist = findDepartmentById(user.getDepartment().getDepartmentid());								
		}
		if(theoreticalLesson.getProfessional()!= null && !"".equals(theoreticalLesson.getProfessional().getProfessionalid())){
			ServletActionContext.getRequest().setAttribute("fangxiang", theoreticalLesson.getProfessional().getProfessionalid());		//返回所选择的专业方向											
		}
		List<TheoreticalLesson> theoreticalLessonList = findTheoreticalLesson(theoreticalLesson);
		ServletActionContext.getRequest().setAttribute("theoreticalLessonList", theoreticalLessonList);
		
		float publicTotalCredit = 0;//公共教育平台总学分
		float professionalTotalCredit = 0;//专业教育平台总学分
		float totalCredit = 0;//所选的总学分
		String publicProportion;
		String professionalProportion;
		for(int i=0;i<theoreticalLessonList.size();i++)
		{
			if(theoreticalLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("公共教育平台"))
			{
				publicTotalCredit = publicTotalCredit + Float.parseFloat(theoreticalLessonList.get(i).getCurriculum().getCredit());
			}
			if(theoreticalLessonList.get(i).getCurriculum().getCurriculumpingtai().equals("专业教育平台"))
			{
				professionalTotalCredit = professionalTotalCredit + Float.parseFloat(theoreticalLessonList.get(i).getCurriculum().getCredit());
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
		ServletActionContext.getRequest().setAttribute("pingtai", theoreticalLesson.getCurriculum().getCurriculumpingtai());					
		ServletActionContext.getRequest().setAttribute("xingzhi", theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid());					
		ServletActionContext.getRequest().setAttribute("leibie", theoreticalLesson.getCurriculum().getCourseLei());					
		ServletActionContext.getRequest().setAttribute("guishu", theoreticalLesson.getCurriculum().getCourseCategory());					
		ServletActionContext.getRequest().setAttribute("xueqi", theoreticalLesson.getXueqi());									
	}
	
	public void totjllkPage(String collegeid,String departmentid){ //跳转到添加理论课页面，查询该学院所有课程
		searchCollegeList();
		
		College college = collegeDao.get(collegeid);
		Department department = departmentDao.get(departmentid); 
		List<Curriculum> curriculumList = findAllTheoreticalLesson(college,department);	
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
		ServletActionContext.getRequest().setAttribute("curriculumListCount", curriculumListCount);
		ServletActionContext.getRequest().setAttribute("professionalListCount", professionalListCount);
		ServletActionContext.getRequest().setAttribute("curriculumList", curriculumList);
		ServletActionContext.getRequest().setAttribute("department", department);
		ServletActionContext.getRequest().setAttribute("college", college);
	}
	
	public void tjllkResult(TheoreticalLesson theoreticalLesson, String collegeid, String departmentid){		//同上，tollkPage()
		College college = collegeDao.get(collegeid);
		Department department = departmentDao.get(departmentid); 
		
		List<Curriculum> curriculumList = findCurriculum(theoreticalLesson);	
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
		ServletActionContext.getRequest().setAttribute("xueyuan", theoreticalLesson.getCurriculum().getCollege().getCollegeid());		//返回所选的学院
		ServletActionContext.getRequest().setAttribute("pingtai", theoreticalLesson.getCurriculum().getCurriculumpingtai());					
		ServletActionContext.getRequest().setAttribute("xingzhi", theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid());		
		ServletActionContext.getRequest().setAttribute("leibie", theoreticalLesson.getCurriculum().getCourseLei());					
		ServletActionContext.getRequest().setAttribute("guishu", theoreticalLesson.getCurriculum().getCourseCategory());					
		ServletActionContext.getRequest().setAttribute("mingcheng", theoreticalLesson.getCurriculum().getCurriculumCname());					
		ServletActionContext.getRequest().setAttribute("daima", theoreticalLesson.getCurriculum().getCurriculumid());	
		ServletActionContext.getRequest().setAttribute("department", department);
		ServletActionContext.getRequest().setAttribute("college", college);
	}
	
	public void llkAddResult(String data,String departmentid){
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
						String isxueweike =jsonObj.get("isxueweike").toString();
						String isjiaogai =jsonObj.get("isjiaogai").toString();
						String isbixuan = jsonObj.get("isbixuan").toString();
						String professionalId =jsonObj.get("zhuanyefangxiang").toString();
						String beizhu =jsonObj.get("beizhu").toString();	
						
						if(professionalId.equals("-1")) {	//	若不分专业方向，直接插入该记录
							TheoreticalLesson SelectTheoreticalLesson = new TheoreticalLesson();	//声明对象
							Curriculum curriculum = findSelectLesson(curriculumid);
							SelectTheoreticalLesson.setCurriculum(curriculum);
							SelectTheoreticalLesson.setHoursOfOutExp(kewaishiyan);
							SelectTheoreticalLesson.setHoursOfOutCom(kewaishangji);
							SelectTheoreticalLesson.setXueqi(xueqi);
							SelectTheoreticalLesson.setIsxueweike(isxueweike);
							SelectTheoreticalLesson.setIsjiaogai(isjiaogai);
							SelectTheoreticalLesson.setIsbixuan(isbixuan);
							SelectTheoreticalLesson.setRemark(beizhu);				
							SelectTheoreticalLesson.setDepartment(department);	
							Professional professional = findprofessionalById(professionalId);
							SelectTheoreticalLesson.setProfessional(professional);
							addSelectLesson(SelectTheoreticalLesson);	//插入理论课
						}else { 		//若分专业方向，对每个选择的专业方向都插入记录
							JSONArray proArr = jsonObj.getJSONArray("zhuanyefangxiang");
							int proSize = proArr.length();
							for(int j = 0; j < proSize; j++) {
								TheoreticalLesson SelectTheoreticalLesson = new TheoreticalLesson();	//声明对象
								Curriculum curriculum = findSelectLesson(curriculumid);
								SelectTheoreticalLesson.setCurriculum(curriculum);
								SelectTheoreticalLesson.setHoursOfOutExp(kewaishiyan);
								SelectTheoreticalLesson.setHoursOfOutCom(kewaishangji);
								SelectTheoreticalLesson.setXueqi(xueqi);
								SelectTheoreticalLesson.setIsxueweike(isxueweike);
								SelectTheoreticalLesson.setIsjiaogai(isjiaogai);
								SelectTheoreticalLesson.setIsbixuan(isbixuan);
								SelectTheoreticalLesson.setRemark(beizhu);				
								SelectTheoreticalLesson.setDepartment(department);
								Professional professional = findprofessionalById(proArr.get(j).toString());
								SelectTheoreticalLesson.setProfessional(professional);
								addSelectLesson(SelectTheoreticalLesson);	//插入理论课
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
	
	//下载理论课培养计划
	public void downTheoreticalPlan(TheoreticalLesson theoreticalLesson){//下载理论课培养计划
		try{
			List<TheoreticalLesson> theoreticalLessonList = theoreticalPlanDao.findSeleTheoLessonByDepart(theoreticalLesson.getDepartment().getDepartmentid(),theoreticalLesson.getProfessional().getProfessionalid());			
			HttpServletResponse response = ServletActionContext.getResponse();  
				Department department = departmentDao.get(theoreticalLesson.getDepartment().getDepartmentid());
				Professional professional = professionalDao.get(theoreticalLesson.getProfessional().getProfessionalid());
	            //2.1 设置响应类型  
	            response.setContentType("application/x-execl");  
	                //2.2 设置以下载方式打开文件  
	            if(theoreticalLesson.getProfessional().getProfessionalid().equals("-1")){
	            	response.setHeader("Content-Disposition", "attachment;filename="+new String((department.getDepartmentCname()+"专业理论课.xls").getBytes("gb2312"),"ISO-8859-1") );
	            }
	            else{
	            	response.setHeader("Content-Disposition", "attachment;filename="+new String((department.getDepartmentCname()+"专业"+professional.getProfessionalname()+"方向理论课.xls").getBytes("gb2312"),"ISO-8859-1") );
	            }
	            OutputStream outputStream = response.getOutputStream();
	            if(theoreticalLessonList != null){
	            	ExportTheoreticalPlanExcel(theoreticalLessonList,outputStream);
	            }else{
	            	ExportExcel(outputStream);
	            }
	            if(outputStream != null){
	            	outputStream.close();  
	            }
			
		} catch (Exception e) {  
	        e.printStackTrace();  
	    }
	}
	
	//下载全部理论课培养计划
	public void downTheoreticalPlan_all(){//下载全部理论课培养计划
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
            //2.1 设置响应类型  
            response.setContentType("application/x-execl");  
            //2.2 设置以下载方式打开文件            
            response.setHeader("Content-Disposition", "attachment;filename="+new String(("全校所选理论课.xls").getBytes("gb2312"),"ISO-8859-1") );
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
	        sheet.setColumnWidth((short) 8, (short) 1500);
	        sheet.setColumnWidth((short) 9, (short) 2000);
	        sheet.setColumnWidth((short) 10, (short) 2000);
	        sheet.setColumnWidth((short) 11, (short) 2000);
	        sheet.setColumnWidth((short) 12, (short) 2300);
	        sheet.setColumnWidth((short) 13, (short) 1200);
	        sheet.setColumnWidth((short) 14, (short) 1800);
	        sheet.setColumnWidth((short) 15, (short) 3000);
	        sheet.setColumnWidth((short) 16, (short) 2900);
	        sheet.setColumnWidth((short) 17, (short) 2300);
	        sheet.setColumnWidth((short) 18, (short) 2300);
	        sheet.setColumnWidth((short) 19, (short) 4500);
	        sheet.setColumnWidth((short) 20, (short) 5000);
	        sheet.setColumnWidth((short) 21, (short) 5000);
	        sheet.setColumnWidth((short) 22, (short) 5000);
	        
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
	        cell.setCellValue("总学时");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 9);  
	        cell.setCellValue("讲课");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 10);  
	        cell.setCellValue("上机");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 11);  
	        cell.setCellValue("实验");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 12);  
	        cell.setCellValue("其他实践");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 13);  
	        cell.setCellValue("学期");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 14);  
	        cell.setCellValue("周学时");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 15);  
	        cell.setCellValue("专业方向");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 16);  
	        cell.setCellValue("是否学位课");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 17);  
	        cell.setCellValue("是否教改");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 18);  
	        cell.setCellValue("是否必选");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 19);  
	        cell.setCellValue("授课学院");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 20);  
	        cell.setCellValue("备注");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 21);  
	        cell.setCellValue("选课学院");  
	        cell.setCellStyle(style1);
	        
	        cell = row.createCell((short) 22);  
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
									List<TheoreticalLesson> theoreticalLessonList = theoreticalPlanDao.findSeleTheoLessonByDepart(departmentList.get(k).getDepartmentid(),professionalList.get(j).getProfessionalid());
									
									
									 for(int i=0;i<theoreticalLessonList.size();i++)
									 {
									    	row = sheet.createRow(flag+1+i);
									        
									    	TheoreticalLesson theoreticalLesson = theoreticalLessonList.get(i);
									        cell = row.createCell((short) 0);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumpingtai());  
									        cell.setCellStyle(style1);  
									  
									        cell = row.createCell((short) 1);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename());  
									        cell.setCellStyle(style1);  
									  
									        cell = row.createCell((short) 2);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getCourseLei());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 3);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getCourseCategory());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 4);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumid());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 5);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumCname());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 6);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumEname());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 7);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getCredit());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 8);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfALL());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 9);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfClass());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 10);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfCom());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 11);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfExp());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 12);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfPractice());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 13);  
									        cell.setCellValue(theoreticalLesson.getXueqi());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 14);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfWeek());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 15);  
									        if(theoreticalLesson.getProfessional() != null)
									        {
									        	cell.setCellValue(theoreticalLesson.getProfessional().getProfessionalname()); 
									        }
									        else
									        {
									        	cell.setCellValue("不区分"); 
									        }
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 16);  
									        cell.setCellValue(theoreticalLesson.getIsxueweike());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 17);  
									        cell.setCellValue(theoreticalLesson.getIsjiaogai());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 18);  
									        cell.setCellValue(theoreticalLesson.getIsbixuan());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 19);  
									        cell.setCellValue(theoreticalLesson.getCurriculum().getCollege().getCollegeCname());  
									        cell.setCellStyle(style1); 
									        
									        cell = row.createCell((short) 20);  
									        cell.setCellValue(theoreticalLesson.getRemark());  
									        cell.setCellStyle(style1);      
									        
			/*						        if(theoreticalLesson.getProfessional() != null)
									        {
									        	cell = row.createCell((short) 21);  
								       	        cell.setCellValue(theoreticalLesson.getProfessional().getDepartment().getCollege().getCollegeCname());  
								       	        cell.setCellStyle(style1); 
								       	        
								       	        cell = row.createCell((short) 22);  
								       	        cell.setCellValue(theoreticalLesson.getProfessional().getDepartment().getDepartmentCname());  
								       	        cell.setCellStyle(style1); 
									        }
									        else
									        {*/
								        	cell = row.createCell((short) 21);  
							       	        cell.setCellValue(professionalList.get(j).getDepartment().getCollege().getCollegeCname());  
							       	        cell.setCellStyle(style1); 
							       	        
							       	        cell = row.createCell((short) 22);  
							       	        cell.setCellValue(professionalList.get(j).getDepartment().getDepartmentCname());  
							       	        cell.setCellStyle(style1); 
									       // }
									       
									    }
									 flag = flag + theoreticalLessonList.size();
								}					
							}
							else
							{
								List<TheoreticalLesson> theoreticalLessonList = theoreticalPlanDao.findSeleTheoLessonByDepart(departmentList.get(k).getDepartmentid(),"-1");
								
								
								 for(int i=0;i<theoreticalLessonList.size();i++)
								 {
								    	row = sheet.createRow(flag+1+i);
								        
								    	TheoreticalLesson theoreticalLesson = theoreticalLessonList.get(i);
								        cell = row.createCell((short) 0);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumpingtai());  
								        cell.setCellStyle(style1);  
								  
								        cell = row.createCell((short) 1);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename());  
								        cell.setCellStyle(style1);  
								  
								        cell = row.createCell((short) 2);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getCourseLei());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 3);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getCourseCategory());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 4);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumid());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 5);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumCname());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 6);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumEname());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 7);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getCredit());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 8);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfALL());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 9);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfClass());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 10);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfCom());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 11);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfExp());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 12);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfPractice());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 13);  
								        cell.setCellValue(theoreticalLesson.getXueqi());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 14);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfWeek());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 15);  
								        if(theoreticalLesson.getProfessional() != null)
								        {
								        	cell.setCellValue(theoreticalLesson.getProfessional().getProfessionalname()); 
								        }
								        else
								        {
								        	cell.setCellValue("不区分"); 
								        }
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 16);  
								        cell.setCellValue(theoreticalLesson.getIsxueweike());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 17);  
								        cell.setCellValue(theoreticalLesson.getIsjiaogai());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 18);  
								        cell.setCellValue(theoreticalLesson.getIsbixuan());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 19);  
								        cell.setCellValue(theoreticalLesson.getCurriculum().getCollege().getCollegeCname());  
								        cell.setCellStyle(style1); 
								        
								        cell = row.createCell((short) 20);  
								        cell.setCellValue(theoreticalLesson.getRemark());  
								        cell.setCellStyle(style1);      
								        
		/*						        if(theoreticalLesson.getProfessional() != null)
								        {
								        	cell = row.createCell((short) 21);  
							       	        cell.setCellValue(theoreticalLesson.getProfessional().getDepartment().getCollege().getCollegeCname());  
							       	        cell.setCellStyle(style1); 
							       	        
							       	        cell = row.createCell((short) 22);  
							       	        cell.setCellValue(theoreticalLesson.getProfessional().getDepartment().getDepartmentCname());  
							       	        cell.setCellStyle(style1); 
								        }
								        else
								        {*/
							        	cell = row.createCell((short) 21);  
						       	        cell.setCellValue(departmentList.get(k).getCollege().getCollegeCname());  
						       	        cell.setCellStyle(style1); 
						       	        
						       	        cell = row.createCell((short) 22);  
						       	        cell.setCellValue(departmentList.get(k).getDepartmentCname());  
						       	        cell.setCellStyle(style1); 
								 //       }						  					        
								    }
								 flag = flag + theoreticalLessonList.size();
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
	
	private void ExportTheoreticalPlanExcel(List<TheoreticalLesson> theoreticalLessonList, OutputStream outputStream) throws IOException{//生成理论课Excel
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
        sheet.setColumnWidth((short) 8, (short) 1500);
        sheet.setColumnWidth((short) 9, (short) 2000);
        sheet.setColumnWidth((short) 10, (short) 2000);
        sheet.setColumnWidth((short) 11, (short) 2000);
        sheet.setColumnWidth((short) 12, (short) 2300);
        sheet.setColumnWidth((short) 13, (short) 1200);
        sheet.setColumnWidth((short) 14, (short) 1800);
        sheet.setColumnWidth((short) 15, (short) 3000);
        sheet.setColumnWidth((short) 16, (short) 2900);
        sheet.setColumnWidth((short) 17, (short) 2300);
        sheet.setColumnWidth((short) 18, (short) 2300);
        sheet.setColumnWidth((short) 19, (short) 4500);
        sheet.setColumnWidth((short) 20, (short) 5000);
        
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
        cell.setCellValue("总学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("讲课");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("上机");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("实验");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("其他实践");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("学期");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("周学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 15);  
        cell.setCellValue("专业方向");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 16);  
        cell.setCellValue("是否学位课");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 17);  
        cell.setCellValue("是否教改");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 18);  
        cell.setCellValue("是否必选");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 19);  
        cell.setCellValue("授课学院");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 20);  
        cell.setCellValue("备注");  
        cell.setCellStyle(style1);
        
        
        for(int i=0;i<theoreticalLessonList.size();i++){
	    	row = sheet.createRow((int) i+1);
	        
	    	TheoreticalLesson theoreticalLesson = theoreticalLessonList.get(i);
	        cell = row.createCell((short) 0);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumpingtai());  
	        cell.setCellStyle(style1);  
	  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCoursename());  
	        cell.setCellStyle(style1);  
	  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getCourseLei());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 3);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getCourseCategory());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 4);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumid());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 5);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumCname());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 6);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getCurriculumEname());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 7);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getCredit());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 8);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfALL());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 9);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfClass());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 10);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfCom());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 11);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfExp());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 12);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfPractice());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 13);  
	        cell.setCellValue(theoreticalLesson.getXueqi());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 14);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getHoursOfWeek());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 15);  
	        if(theoreticalLesson.getProfessional() != null)
	        {
	        	cell.setCellValue(theoreticalLesson.getProfessional().getProfessionalname()); 
	        }
	        else
	        {
	        	cell.setCellValue("不区分"); 
	        }
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 16);  
	        cell.setCellValue(theoreticalLesson.getIsxueweike());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 17);  
	        cell.setCellValue(theoreticalLesson.getIsjiaogai());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 18);  
	        cell.setCellValue(theoreticalLesson.getIsbixuan());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 19);  
	        cell.setCellValue(theoreticalLesson.getCurriculum().getCollege().getCollegeCname());  
	        cell.setCellStyle(style1); 
	        
	        cell = row.createCell((short) 20);  
	        cell.setCellValue(theoreticalLesson.getRemark());  
	        cell.setCellStyle(style1);      	        	       
	        
	    }
        
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	
	
	//多条件查询理论课
	public List<TheoreticalLesson> findTheoreticalLesson(TheoreticalLesson theoreticalLesson) {
		return theoreticalPlanDao.findTheoreticalLesson(theoreticalLesson);
	}

	//多条件查询课程，用户选择培养计划
	public List<Curriculum> findCurriculum(TheoreticalLesson theoreticalLesson) {
		return theoreticalPlanDao.findCurriculum(theoreticalLesson);
	}

	//查询对应专业的专业方向
	public List<Professional> findProfessional(String departmentId) {
		return theoreticalPlanDao.findProfessional(departmentId);
	}

	//根据课程id查询课程
	public Curriculum findSelectLesson(String daima) {
		return curriculumDao.get(daima);
	}

	//根据系id查询系
	public Department finddepartment(String zhuanye) {
		return departmentDao.get(zhuanye);
	}

	//插入选定的理论课
	public void addSelectLesson(TheoreticalLesson SelectTheoreticalLesson) {
		theoreticalPlanDao.addSelectLesson(SelectTheoreticalLesson);
	}

	//删除理论课
	public void delTheoreticalLessonByid(Integer theoreticalLessonid) {
		TheoreticalLesson theoreticalLesson = theoreticalPlanDao.get(theoreticalLessonid);
		theoreticalPlanDao.delete(theoreticalLesson);	
/*		HttpServletResponse response = ServletActionContext.getResponse();
		String result = "{\"err\": false, \"message\": \"删除成功\"}";
		response.setContentType("text/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	*/
		ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
	}

	//删除多个理论课
	public void delllkMore(List<String> deletetheoreticalLessonList) {
		if(deletetheoreticalLessonList != null && deletetheoreticalLessonList.size() !=0)
		{
			//设置域对象，用于返回
			TheoreticalLesson theoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(deletetheoreticalLessonList.get(0)));
			ServletActionContext.getRequest().setAttribute("departmentid", theoreticalLesson.getDepartment().getDepartmentid());
			ServletActionContext.getRequest().setAttribute("collegeid", theoreticalLesson.getDepartment().getCollege().getCollegeid());
			
			for(int i=0;i<deletetheoreticalLessonList.size();i++)
			{
				TheoreticalLesson newTheoreticalLesson = theoreticalPlanDao.get(Integer.valueOf(deletetheoreticalLessonList.get(i)));
				if(newTheoreticalLesson != null)
				{
					theoreticalPlanDao.delete(newTheoreticalLesson);
				}
			}
			ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
		}
		
	}
	
	//根据理论课id查询理论课
	public TheoreticalLesson findTheoreticalLessonByid(Integer theoreticalLessonid) {
		return theoreticalPlanDao.get(theoreticalLessonid);
	}

	//编辑理论课
	public void llkUpdate(TheoreticalLesson theoreticalLesson) {
		TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(theoreticalLesson.getTheoreticalLessonid());
		newtheoreticalLesson.setHoursOfOutExp(theoreticalLesson.getHoursOfOutExp());
		newtheoreticalLesson.setHoursOfOutCom(theoreticalLesson.getHoursOfOutCom());
		newtheoreticalLesson.setXueqi(theoreticalLesson.getXueqi());
		newtheoreticalLesson.setIsxueweike(theoreticalLesson.getIsxueweike());
		newtheoreticalLesson.setIsjiaogai(theoreticalLesson.getIsjiaogai());
		newtheoreticalLesson.setIsbixuan(theoreticalLesson.getIsbixuan());
		newtheoreticalLesson.setRemark(theoreticalLesson.getRemark());
		theoreticalPlanDao.update(newtheoreticalLesson);
		ServletActionContext.getRequest().setAttribute("msg", "修改成功！");
	}

	//查询所有理论课，用于统计学分
	public List<TheoreticalLesson> findAllTheoreticalLessonList() {
		return theoreticalPlanDao.findAll();
	}
	//根据专业方向id查询记录
	public Professional findprofessionalById(String professionalId) {
		return professionalDao.get(professionalId);
	}
	
	//根据用户id获得用户信息
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

	//查询对应系的全部理论课
	public List<TheoreticalLesson> findTheoreticalLessonDepartment(
			Department department) {
		return theoreticalPlanDao.findTheoreticalLessonDepartment(department);
	}
	public void delete(TheoreticalLesson theoreticalLesson) {
		theoreticalPlanDao.delete(theoreticalLesson);
		
	}
	
	//跳转到添加理论课页面，查询该学院所有理论课
	public List<Curriculum> findAllTheoreticalLesson(College college,Department department) {
		return theoreticalPlanDao.findAllTheoreticalLesson(college,department);
	}
	
	//查询没有选curriculumid的专业
	public List<Professional> findUnselectedProfessionalList(
			String departmentid, String curriculumid) {
		return theoreticalPlanDao.findUnselectedProfessionalList(departmentid,curriculumid);
	}
	
	public void downTheoreticalTemplate(){//下载理论课模板
		try{
		HttpServletResponse response = ServletActionContext.getResponse();  
        //2.1 设置响应类型  
        response.setContentType("application/x-execl");  
            //2.2 设置以下载方式打开文件  
        response.setHeader("Content-Disposition", "attachment;filename="+new String("理论课模板.xls".getBytes(),"ISO-8859-1") );
        OutputStream outputStream = response.getOutputStream();
        ExportExcel(outputStream);
        if(outputStream != null){
        	outputStream.close();  
        }}catch (Exception e) {  
            e.printStackTrace();  
        }
	}

	private void ExportExcel(OutputStream outputStream) throws IOException{	//生成理论课Excel
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
        sheet.setColumnWidth((short) 8, (short) 1500);
        sheet.setColumnWidth((short) 9, (short) 2000);
        sheet.setColumnWidth((short) 10, (short) 2000);
        sheet.setColumnWidth((short) 11, (short) 2000);
        sheet.setColumnWidth((short) 12, (short) 2300);
        sheet.setColumnWidth((short) 13, (short) 1200);
        sheet.setColumnWidth((short) 14, (short) 1800);
        sheet.setColumnWidth((short) 15, (short) 3000);
        sheet.setColumnWidth((short) 16, (short) 2900);
        sheet.setColumnWidth((short) 17, (short) 2300);
        sheet.setColumnWidth((short) 18, (short) 2300);
        sheet.setColumnWidth((short) 19, (short) 4500);
        sheet.setColumnWidth((short) 20, (short) 5000);
        
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
        cell.setCellValue("总学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 9);  
        cell.setCellValue("讲课");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 10);  
        cell.setCellValue("上机");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 11);  
        cell.setCellValue("实验");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 12);  
        cell.setCellValue("其他实践");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 13);  
        cell.setCellValue("学期");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 14);  
        cell.setCellValue("周学时");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 15);  
        cell.setCellValue("专业方向");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 16);  
        cell.setCellValue("是否学位课");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 17);  
        cell.setCellValue("是否教改");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 18);  
        cell.setCellValue("是否必选");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 19);  
        cell.setCellValue("授课学院");  
        cell.setCellStyle(style1);
        
        cell = row.createCell((short) 20);  
        cell.setCellValue("备注");  
        cell.setCellStyle(style1);
        
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	
	//--------------------------
	public PageBean findtheolen(Integer currentpage,TheoreticalLesson theoreticalLesson, String departmenttag) {
		PageBean pageBean=new PageBean();
		//在pageBean中设置college使得条件查询可执行
		List<College> listcollege=new ArrayList<College>();
		College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
		listcollege.add(college);
		pageBean.setCollegelist(listcollege);
		//找到该学院所开课程在理论课列表中的所有相关记录
		List<List<TheoreticalLesson>> theolenlist1=findtheolenlist(theoreticalLesson,departmenttag);
		List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
		
		//根据条件进行判断
		//只有学院的时候显示所有
		if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
			theolenlist=theolenlist1;
		}else{
		//当搜索条件添加了课程名称时显示相关的记录
			if(departmenttag.equals("1")){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getTeachDepartment()!=null){
						if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
								{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
											theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
						if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
									theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
					theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
							.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
							theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else{
				if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
						&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
								.equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
						&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
								.equals(theoreticalLesson.getCurriculum().getCurriculumid())){
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
		List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
		pageBean.setTheoreticalLessonlist(sslist);
		return pageBean;
	}
	
	public List<List<TheoreticalLesson>> findtheolenlist(TheoreticalLesson theoreticalLesson, String departmenttag){
		//总的分页理论课列表
				List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
				//找到学院开设的课程
				List<Curriculum> list=theoreticalPlanDao.findcurrbycollege(theoreticalLesson.getCurriculum().getCollege());
				//List<Curriculum> listcurr=new ArrayList<Curriculum>();
				for(int i=0;i<list.size();i++){
					//找到了课1对应的列表
					List<TheoreticalLesson> theolenlist=theoreticalPlanDao.findtheolenbycurr(list.get(i));
					List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
					List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
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
		return theoreticalPlanDao.findcurrbyId(curriculumid);
	}
	public List<Department> finddepartbycollege(College college) {
		return theoreticalPlanDao.finddepartbycollege(college);
	}
	@SuppressWarnings("null")
	public List<Professional> findchoosedepartbydepartId(List<String> depart) {
		List<Professional> choosedepartlist=new ArrayList<Professional>();
		for(int i=0;i<depart.size();i++){
			Professional professional=new Professional();
			/*System.out.println("123456");*/
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

	public void goupdatedepartment(TheoreticalLesson theoreticalLesson,List<String> newchoosedepartlist) {
		TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
		for(int i=0;i<newchoosedepartlist.size();i++){
			
			//获取课程列表
			Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
			if((professionalDao.get(newchoosedepartlist.get(i)))!=null)
			{
				theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(newchoosedepartlist.get(i)));
			}
			else
			{
				Department department=departmentDao.get(newchoosedepartlist.get(i));
				theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
			}
			if(theoreticalLen.getTeachDepartment() == null ||(theoreticalLen.getTeachDepartment().getDepartmentid()!=(theoreticalLesson.getTeachDepartment().getDepartmentid()))){
				theoreticalLen.setTeachDepartment(departmentDao.get(theoreticalLesson.getTeachDepartment().getDepartmentid()));
				theoreticalLen.setTeacher(null);
			}
			theoreticalPlanDao.update(theoreticalLen);
		}
	}
	
	public PageBean findtheolendirectuser(Integer currentpage,
		TheoreticalLesson theoreticalLesson, String departmenttag) {
		PageBean pageBean=new PageBean();
		//在pageBean中设置college使得条件查询可执行
		List<College> listcollege=new ArrayList<College>();
		College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
		listcollege.add(college);
		pageBean.setCollegelist(listcollege);
		
		List<List<TheoreticalLesson>> theolenlist1=findtheolenlistdirectuser(theoreticalLesson,departmenttag);
		List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
			
		//根据条件进行判断
		//只有学院的时候显示所有
		if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
			theolenlist=theolenlist1;
		}else{
		//当搜索条件添加了课程名称时显示相关的记录
			if(departmenttag.equals("1")){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getTeacher()!=null){
						if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
								{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
											theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
						if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
									theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
					theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
							.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
							theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else{
				if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
						&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
								.equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
						&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
								.equals(theoreticalLesson.getCurriculum().getCurriculumid())){
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
		List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
		pageBean.setTheoreticalLessonlist(sslist);
		return pageBean;
	}
	public List<List<TheoreticalLesson>> findtheolenlistdirectuser(TheoreticalLesson theoreticalLesson, String departmenttag){
		List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
		//找到学院开设的课程
		List<Curriculum> list=theoreticalPlanDao.findcurrbycollege(theoreticalLesson.getCurriculum().getCollege());
		
		for(int i=0;i<list.size();i++){
			//找到了课1对应的列表
			List<TheoreticalLesson> theolenlist=theoreticalPlanDao.findtheolenbycurr(list.get(i));
			List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
			List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
			//遍历课1的列表
			for(int j=0;j<theolenlist.size();j++){
				if(theolenlist.get(j).getTeacher()!=null || theolenlist.get(j).getTeachDepartment() !=null){	
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
	public PageBean findUserByCollege(Integer currentpage,TheoreticalLesson theoreticalLesson, College college) {
		PageBean pageBean=new PageBean();
		List<User> userlist=new ArrayList<User>();
		List<User> userlist1=userDao.findUserByCollege(college);
		if(theoreticalLesson.getTeacher() != null)
		{	
			if(theoreticalLesson.getTeacher().getTnum() != null && !"".equals(theoreticalLesson.getTeacher().getTnum()) && theoreticalLesson.getTeacher().getUsername() != null && !"".equals(theoreticalLesson.getTeacher().getUsername()))
			{
				for(int i=0;i<userlist1.size();i++){
					if(userlist1.get(i).getTnum().equals(theoreticalLesson.getTeacher().getTnum())&&
							userlist1.get(i).getUsername().equals(theoreticalLesson.getTeacher().getUsername()))
					{
						userlist.add(userlist1.get(i));
					}
				}
			}
			else if(!"".equals(theoreticalLesson.getTeacher().getTnum()) && "".equals(theoreticalLesson.getTeacher().getUsername()))
			{
				for(int i=0;i<userlist1.size();i++){
					if(userlist1.get(i).getTnum().equals(theoreticalLesson.getTeacher().getTnum()))
					{
						userlist.add(userlist1.get(i));
					}
				}
			}
			else if("".equals(theoreticalLesson.getTeacher().getTnum()) && !"".equals(theoreticalLesson.getTeacher().getUsername()))
			{
				for(int i=0;i<userlist1.size();i++){
					if(userlist1.get(i).getUsername().equals(theoreticalLesson.getTeacher().getUsername()))
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
/*	public void updateTheolenByCollegeDirectUser(TheoreticalLesson theoreticalLesson, List<String> depart) 
	{
		TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
		for(int i=0;i<depart.size();i++){
			Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
			if((professionalDao.get(depart.get(i)))!=null)
			{
				theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
			}
			else
			{
				Department department=departmentDao.get(depart.get(i));
				theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
			}
			theoreticalLen.setTeachDepartment(null);
			theoreticalLen.setTeacher(userDao.get(theoreticalLesson.getTeacher().getTnum()));
			theoreticalPlanDao.update(theoreticalLen);
		}
	}*/
	public PageBean findtheolenByDepartToUser(Integer currentpage,TheoreticalLesson theoreticalLesson, String usertag) {
		PageBean pageBean=new PageBean();
		//在pageBean中设置college使得条件查询可执行
		List<College> listcollege=new ArrayList<College>();
		College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
		listcollege.add(college);
		pageBean.setCollegelist(listcollege);
		
		
		List<Department> listdepart=new ArrayList<Department>();
		listdepart.add(departmentDao.get(theoreticalLesson.getTeachDepartment().getDepartmentid()));
		pageBean.setDepartmentlist(listdepart);
		
		theoreticalLesson.setTeachDepartment(listdepart.get(0));
		
		List<List<TheoreticalLesson>> theolenlist1=findtheolenlistByDepartUser(theoreticalLesson,usertag);
		List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
			
		//根据条件进行判断
		//只有学院的时候显示所有
		if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&usertag.equals("0")){
			theolenlist=theolenlist1;
		}else{
		//当搜索条件添加了课程名称时显示相关的记录
			if(usertag.equals("1")){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getTeacher()!=null){
						if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
								{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
											theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
						if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
									theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
					theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
							.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
							theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else{
				if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
						&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
								.equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
						&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
								.equals(theoreticalLesson.getCurriculum().getCurriculumid())){
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
		List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
		pageBean.setTheoreticalLessonlist(sslist);
		return pageBean;
	}
	public List<List<TheoreticalLesson>> findtheolenlistByDepartUser(TheoreticalLesson theoreticalLesson, String usertag){
		List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
		//找到当前任课系的所有理论课记录
		List<TheoreticalLesson> list=theoreticalPlanDao.findTheolenByTeachDepartment(theoreticalLesson.getTeachDepartment());
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
			List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
			List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
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
		//System.out.println(theolen.get(0).get(0).getCurriculum().getCurriculumCname());
		return theolen;
	}
	public PageBean findUserByDepart(Integer currentpage,TheoreticalLesson theoreticalLesson, List<String> depart)
	{
		PageBean pageBean=new PageBean();
		College college=collegeDao.get(theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		List<College> listcollege=new ArrayList<College>();
		listcollege.add(college);
		pageBean.setCollegelist(listcollege);
		
		Department depart1=departmentDao.get(theoreticalLesson.getTeachDepartment().getDepartmentid());
		List<Department> listdepart=new ArrayList<Department>();
		listdepart.add(depart1);
		pageBean.setDepartmentlist(listdepart);
		
		List<User> listuser1=userDao.findUserByDepart(depart1);
		List<User> listuser=new ArrayList<User>();
		for(int i=0;i<listuser1.size();i++){
			if(theoreticalLesson.getTeacher()!=null)
			{
				if(theoreticalLesson.getTeacher().getTnum()!=null&&!"".equals(theoreticalLesson.getTeacher().getTnum())&&
						theoreticalLesson.getTeacher().getUsername()!=null&&!"".equals(theoreticalLesson.getTeacher().getUsername()))
				{
					if(theoreticalLesson.getTeacher().getTnum().equals(listuser1.get(i).getTnum())
							&&theoreticalLesson.getTeacher().getUsername().equals(listuser1.get(i).getUsername()))
					{
						listuser.add(listuser1.get(i));
					}
				}
				else if(!"".equals(theoreticalLesson.getTeacher().getTnum()) && "".equals(theoreticalLesson.getTeacher().getUsername()))
				{
					if(theoreticalLesson.getTeacher().getTnum().equals(listuser1.get(i).getTnum()))
					{
						listuser.add(listuser1.get(i));
					}
				}
				else if("".equals(theoreticalLesson.getTeacher().getTnum()) && !"".equals(theoreticalLesson.getTeacher().getUsername()))
				{
					if(theoreticalLesson.getTeacher().getUsername().equals(listuser1.get(i).getUsername()))
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
	public void updateTheolenByDepartToUser(TheoreticalLesson theoreticalLesson, List<String> depart) {
		TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
		for(int i=0;i<depart.size();i++){
			Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
			if((professionalDao.get(depart.get(i)))!=null)
			{
				theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
			}
			else
			{
				Department department=departmentDao.get(depart.get(i));
				theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
			}
			theoreticalLen.setTeacher(userDao.get(theoreticalLesson.getTeacher().getTnum()));
			theoreticalPlanDao.update(theoreticalLen);
		}
		
	}
	
	
	//----------------------------理论课大纲--------------------------------------------
	public void TheoLes(TheoreticalLesson theoreticalLesson) {//理论课大纲状态页面
		User user = userDao.get(theoreticalLesson.getTeacher().getTnum());
		List<TheoreticalLesson> allTheoreticalLessonlist = theoreticalPlanDao.getbyuser(user);//该老师所有应选大纲
		List<List<TheoreticalLesson>> professionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
		
		while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
			List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
			TheoreticalLesson theoLess = allTheoreticalLessonlist.get(0);
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
		
		
		List<TheoreticalLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getbyuser(user);
		List<ApplicationSyllabus> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusList(newallTheoreticalLessonlist);//应用大纲的专业/方向

		List<List<TheoreticalLesson>> haveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
		for(int pi = 0;pi<professionalList.size();pi++)
		{
			
			List<TheoreticalLesson> newhaveprofessionalList = new ArrayList<TheoreticalLesson>();
			for(int pj=0;pj<professionalList.get(pi).size();pj++)
			{
				TheoreticalLesson theoLess = professionalList.get(pi).get(pj);
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
		
		List<TheoreticalLesson> syllabusList = new ArrayList<TheoreticalLesson>();//合并
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
		
		List<TheoreticalLesson> haveSyllabusList = new ArrayList<TheoreticalLesson>();//合并
		
		
		//分开不同大纲ID
		List<List<TheoreticalLesson>> newhaveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
		
		if(haveprofessionalList != null && haveprofessionalList.size() != 0)
		{
			for(int ri=0;ri<haveprofessionalList.size();ri++)
			{
				while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
				{		
					List<TheoreticalLesson> newTheoreticalLesson = new ArrayList<TheoreticalLesson>();
					TheoreticalLesson theo = haveprofessionalList.get(ri).get(0);
					String syid =theoreticalPlanDao.findSyllabusidByTheo(theo);
					for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
					{
						if(syid.equals(theoreticalPlanDao.findSyllabusidByTheo(haveprofessionalList.get(ri).get(rj))))
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
	
		List<List<TheoreticalLesson>> newprofessionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
		for(int hi=0;hi<newhaveprofessionalList.size();hi++)
		{
			TheoreticalLesson newtheo = newhaveprofessionalList.get(hi).get(0);
			List<TheoreticalLesson> newallTheoreticalLesson = theoreticalPlanDao.getbyuser(user,newtheo);//该老师所有应选大纲
	
			while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
				List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
				TheoreticalLesson theoLess = newallTheoreticalLesson.get(0);
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
				String syllabusid = theoreticalPlanDao.findSyllabusidByTheo(haveSyllabusList.get(i));
				syllabusId.add(syllabusid);
			}
		}
		
		
		//--------剔除已完成大纲的专业方向
		List<ApplicationSyllabus> appSyllaList = new ArrayList<ApplicationSyllabus>();
		for(int i=0;i<newprofessionalList.size();i++){
			appSyllaList = applicationSyllabusDao.findAppByTheo(newprofessionalList.get(i).get(0));
			if(appSyllaList != null && appSyllaList.size() != 0){
					
			for(int k=0;k<appSyllaList.size();k++)
			{
				for(int j=0;j<newprofessionalList.get(i).size();j++)
				{	
					if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
					{
						if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findSyllabusidByTheo(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
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
	public void toCheckTheoLesAdmin(String tnum, TheoreticalLesson theoreticalLesson) {
		// TODO Auto-generated method stub
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
		
		List<TheoreticalLesson> allTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearch(theoreticalLesson);//该老师所有应选大纲
		List<List<TheoreticalLesson>> professionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
		
		while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
			List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
			TheoreticalLesson theoLess = allTheoreticalLessonlist.get(0);
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
		
		
		List<TheoreticalLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearch(theoreticalLesson);
		List<ApplicationSyllabus> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusList(newallTheoreticalLessonlist);//应用大纲的专业/方向

		List<List<TheoreticalLesson>> haveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
		for(int pi = 0;pi<professionalList.size();pi++)
		{
			
			List<TheoreticalLesson> newhaveprofessionalList = new ArrayList<TheoreticalLesson>();
			for(int pj=0;pj<professionalList.get(pi).size();pj++)
			{
				TheoreticalLesson theoLess = professionalList.get(pi).get(pj);
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
		
		List<TheoreticalLesson> syllabusList = new ArrayList<TheoreticalLesson>();//合并
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
		
		ServletActionContext.getRequest().setAttribute("count", count);//专业方向数量
		ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);//理论课List
		ServletActionContext.getRequest().setAttribute("professionalList", professionalList);//所有未编写的大纲，嵌套
		
		List<TheoreticalLesson> haveSyllabusList = new ArrayList<TheoreticalLesson>();//合并
		
		
		//分开不同大纲ID
		List<List<TheoreticalLesson>> newhaveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
		
		if(haveprofessionalList != null && haveprofessionalList.size() != 0)
		{
			for(int ri=0;ri<haveprofessionalList.size();ri++)
			{
				while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
				{		
					List<TheoreticalLesson> newTheoreticalLesson = new ArrayList<TheoreticalLesson>();
					TheoreticalLesson theo = haveprofessionalList.get(ri).get(0);
					String syid =theoreticalPlanDao.findSyllabusidByTheo(theo);
					for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
					{
						if(syid.equals(theoreticalPlanDao.findSyllabusidByTheo(haveprofessionalList.get(ri).get(rj))))
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
	
		List<List<TheoreticalLesson>> newprofessionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
		for(int hi=0;hi<newhaveprofessionalList.size();hi++)
		{
			TheoreticalLesson newtheo = newhaveprofessionalList.get(hi).get(0);
			List<TheoreticalLesson> newallTheoreticalLesson = theoreticalPlanDao.getbyCurriculumAndDepartment(newtheo);//该老师所有应选大纲
	
			while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
				List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
				TheoreticalLesson theoLess = newallTheoreticalLesson.get(0);
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
				String syllabusid = theoreticalPlanDao.findSyllabusidByTheo(haveSyllabusList.get(i));
				syllabusId.add(syllabusid);
			}
		}
		
		
		//--------剔除已完成大纲的专业方向
		List<ApplicationSyllabus> appSyllaList = new ArrayList<ApplicationSyllabus>();
		for(int i=0;i<newprofessionalList.size();i++){
			appSyllaList = applicationSyllabusDao.findAppByTheo(newprofessionalList.get(i).get(0));
			if(appSyllaList != null && appSyllaList.size() != 0){
					
			for(int k=0;k<appSyllaList.size();k++)
			{
				for(int j=0;j<newprofessionalList.get(i).size();j++)
				{	
					if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
					{
						if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findSyllabusidByTheo(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
						{
							newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
							j--;
						}
					}
				}
			}
			}
		}
		
		
		ServletActionContext.getRequest().setAttribute("haveCount", haveCount);//已经编写大纲的专业方向数量
		ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);//已经编写大纲的专业方向
		ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);//剔除掉其他专业方向的专业方向List
		
	}
	
	
	//未编辑大纲到编辑理论课大纲页面
	public Integer syllabusId;
	public String TheoLes1(TheoreticalLesson theoreticalLesson, List<String> selectProfessional) {//未编辑大纲到编辑理论课大纲页面
		TheoreticalLesson newTheoreticalLesson = theoreticalPlanDao.get(theoreticalLesson.getTheoreticalLessonid());
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
				//不同
				newapplicationSyllabus = applicationSyllabusDao.findappByThenAndPro(newTheoreticalLesson,professionalList.get(i));
				if(newapplicationSyllabus != null)
				{
					syllabusDao.delete(newapplicationSyllabus.getSyllabus());
				}
			}
		}
		else
		{
			newapplicationSyllabus = applicationSyllabusDao.findAppByTheo_E(newTheoreticalLesson);
			if(newapplicationSyllabus != null)
			{
				syllabusDao.delete(newapplicationSyllabus.getSyllabus());
			}
		}
		Syllabus syllabus = new Syllabus();
		syllabusDao.add(syllabus);
		syllabusId = syllabus.getSyllabusid();

		//若分专业方向，则按专业方向向应用方向表写数据
		if(professionalList != null && professionalList.size() != 0)
		{
			//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
			if(newTheoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
			{
				List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newTheoreticalLesson.getCurriculum(),professionalList.get(0));
				for(int i=1;i<professionalList.size();i++)
				{
					List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newTheoreticalLesson.getCurriculum(),professionalList.get(i));
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
			
			for(int i=0;i<selectProfessional.size();i++)
			{
				Professional professional = professionalDao.get(selectProfessional.get(i));
				Syllabus newsyllabus = syllabusDao.get(syllabusId);
				ApplicationSyllabus applicationSyllabus = new ApplicationSyllabus();
				applicationSyllabus.setSyllabus(newsyllabus);
				applicationSyllabus.setCurriculum(newTheoreticalLesson.getCurriculum());
				applicationSyllabus.setDepartment(newTheoreticalLesson.getDepartment());
				applicationSyllabus.setProfessional(professional);
				applicationSyllabusDao.add(applicationSyllabus);
			}
			Integer count = professionalList.size();
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
		}
		//若不分方向，则向数据库写一个专业
		else{
			Syllabus newsyllabus = syllabusDao.get(syllabusId);
			ApplicationSyllabus applicationSyllabus = new ApplicationSyllabus();
			applicationSyllabus.setSyllabus(newsyllabus);
			applicationSyllabus.setCurriculum(newTheoreticalLesson.getCurriculum());
			applicationSyllabus.setDepartment(newTheoreticalLesson.getDepartment());
			applicationSyllabusDao.add(applicationSyllabus);
			ServletActionContext.getRequest().setAttribute("count", 0);
		}
		
		ServletActionContext.getRequest().setAttribute("newTheoreticalLesson", newTheoreticalLesson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabus.getSyllabusid());
		return "yes";
	}
	//未编写大纲到大纲编写页面——复制
	public void toTheoLesPage_Copy(TheoreticalLesson theoreticalLesson,List<String> selectProfessional, String syllabusId_Copy) {
		TheoLes1(theoreticalLesson,selectProfessional);
		//syllabusId新建的大纲Id
		//syllabusId_Copy传过来的选择的要复制的大纲Id
		Syllabus copysyllabus = syllabusDao.get(Integer.valueOf(syllabusId_Copy));
		Syllabus newsyllabus = syllabusDao.get(Integer.valueOf(syllabusId));
		
		/****************************大纲表一对一关系*************************************/
		newsyllabus.setBaseTheo(copysyllabus.getBaseTheo());
		newsyllabus.setTeaMethodTheo(copysyllabus.getTeaMethodTheo());
		syllabusDao.update(newsyllabus);
		
		
		/****************************大纲表一对多关系-教学目标及对应关系*************************************/
/*		List<ApplicationSyllabus> newApplicationSyllabusList =  applicationSyllabusDao.findapplicationSyllabusBySylid(String.valueOf(syllabusId));
		//判断不为专业选修课时才进行复制
		if(newApplicationSyllabusList.get(0).getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
		{
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
//				newcontentTheo.setEmphasis(contentTheo.getEmphasis());
				newcontentTheo.setName(contentTheo.getName());
//				newcontentTheo.setNodus(contentTheo.getNodus());
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
	
	//已编写大纲到大纲编写页面	
	public String TheoLes2(String syllabusId, TheoreticalLesson theoreticalLesson,List<String> haveselectProfessional) {//已编辑大纲到编辑理论课大纲页面
		TheoreticalLesson newTheoreticalLesson = theoreticalPlanDao.get(theoreticalLesson.getTheoreticalLessonid());
		
		if(haveselectProfessional != null && haveselectProfessional.size() != 0){
			List<Professional> professionalList = new ArrayList<Professional>();
			for(int i=0;i<haveselectProfessional.size();i++){//将勾选的专业方向id查到实体放入List
				Professional professional = professionalDao.get(haveselectProfessional.get(i));
				professionalList.add(professional);
			}
			
			//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
			if(newTheoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
			{
				List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newTheoreticalLesson.getCurriculum(),professionalList.get(0));
				for(int i=1;i<professionalList.size();i++)
				{
					List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newTheoreticalLesson.getCurriculum(),professionalList.get(i));
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
			
			List<ApplicationSyllabus> applicationSyllabusList = applicationSyllabusDao.findapplicationSyllabusBySylid(syllabusId);//通过大纲id查询该大纲的所有应用专业
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
					applicationSyllabus.setCurriculum(newTheoreticalLesson.getCurriculum());
					applicationSyllabus.setDepartment(newTheoreticalLesson.getDepartment());
					applicationSyllabus.setProfessional(professionalList.get(i));
					applicationSyllabusDao.add(applicationSyllabus);
				}
			}
			
			if(professional != null && professional.size() != 0){
				for(int i=0;i<professional.size();i++){
					ApplicationSyllabus applicationSyllabus =  applicationSyllabusDao.findappByThenAndPro(newTheoreticalLesson,professional.get(i));
					applicationSyllabusDao.delete(applicationSyllabus);
				}
			}
			Integer count = professionalList.size();
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
		}
		ServletActionContext.getRequest().setAttribute("newTheoreticalLesson", newTheoreticalLesson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		return "yes";
	}
	//删除大纲
	public void deleteTheoSyllabus(String syllabusId) {
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
		ServletActionContext.getRequest().setAttribute("tag", "toCheckTheoLes");
		ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
		
	}
	

	//-----------------------------理论课大纲——课内实验----------------------------------------
	//跳转到理论课大纲——课内实验验证页面
	public void toCheckTheoLesInnerExperiment(TheoreticalLesson theoreticalLesson) {
		//************************************
		User user = userDao.get(theoreticalLesson.getExperimenter().getTnum());
		List<TheoreticalLesson> allTheoreticalLessonlist = theoreticalPlanDao.getbyuser_HaveHourOfExp(user);//该老师所有应选大纲
		
		List<List<TheoreticalLesson>> professionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
		
		while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
			List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
			TheoreticalLesson theoLess = allTheoreticalLessonlist.get(0);
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
		
		
		List<TheoreticalLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getbyuser_HaveHourOfExp(user);
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabusList_TheoInnerExperiment = applicationSyllabusDao.findhaveSyllabus_TheoInnerExperimentList(newallTheoreticalLessonlist);//应用大纲的专业/方向

		List<List<TheoreticalLesson>> haveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
		for(int pi = 0;pi<professionalList.size();pi++)
		{
			
			List<TheoreticalLesson> newhaveprofessionalList = new ArrayList<TheoreticalLesson>();
			for(int pj=0;pj<professionalList.get(pi).size();pj++)
			{
				TheoreticalLesson theoLess = professionalList.get(pi).get(pj);
				for(int pk=0;pk<applicationSyllabusList_TheoInnerExperiment.size();pk++)
				{
					ApplicationSyllabus_TheoInnerExperiment appSyll = applicationSyllabusList_TheoInnerExperiment.get(pk);
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
		
		List<TheoreticalLesson> syllabusList = new ArrayList<TheoreticalLesson>();//合并
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
		
		List<TheoreticalLesson> haveSyllabusList = new ArrayList<TheoreticalLesson>();//合并
		
		
		//分开不同大纲ID
		List<List<TheoreticalLesson>> newhaveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
		
		if(haveprofessionalList != null && haveprofessionalList.size() != 0)
		{
			for(int ri=0;ri<haveprofessionalList.size();ri++)
			{
				while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
				{		
					List<TheoreticalLesson> newTheoreticalLesson = new ArrayList<TheoreticalLesson>();
					TheoreticalLesson theo = haveprofessionalList.get(ri).get(0);
					String syid =theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(theo);
					for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
					{
						if(syid.equals(theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(haveprofessionalList.get(ri).get(rj))))
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
	
		List<List<TheoreticalLesson>> newprofessionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
		for(int hi=0;hi<newhaveprofessionalList.size();hi++)
		{
			TheoreticalLesson newtheo = newhaveprofessionalList.get(hi).get(0);
			List<TheoreticalLesson> newallTheoreticalLesson = theoreticalPlanDao.getbyuser_HaveHourOfExp(user,newtheo);//该老师所有应选大纲
	
			while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
				List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
				TheoreticalLesson theoLess = newallTheoreticalLesson.get(0);
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
				String syllabusid = theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(haveSyllabusList.get(i));
				syllabusId.add(syllabusid);
			}
		}
		
		
		//--------剔除已完成大纲的专业方向
		List<ApplicationSyllabus_TheoInnerExperiment> appSyllaList = new ArrayList<ApplicationSyllabus_TheoInnerExperiment>();
		for(int i=0;i<newprofessionalList.size();i++){
			appSyllaList = applicationSyllabusDao.findAppByTheo_TheoInnerExperiment(newprofessionalList.get(i).get(0));
			if(appSyllaList != null && appSyllaList.size() != 0){
					
			for(int k=0;k<appSyllaList.size();k++)
			{
				for(int j=0;j<newprofessionalList.get(i).size();j++)
				{	
					if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
					{
						if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
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
	
	public void toCheckTheoLesInnerExperimentAdmin(String tnum,TheoreticalLesson theoreticalLesson) {
		// TODO Auto-generated method stub
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
		
		List<TheoreticalLesson> allTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearch_HaveHourOfExp(theoreticalLesson);//该老师所有应选大纲
		List<List<TheoreticalLesson>> professionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
		
		while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
			List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
			TheoreticalLesson theoLess = allTheoreticalLessonlist.get(0);
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
		
		
		List<TheoreticalLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearch_HaveHourOfExp(theoreticalLesson);
		List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabusList_TheoInnerExperiment = applicationSyllabusDao.findhaveSyllabus_TheoInnerExperimentList(newallTheoreticalLessonlist);//应用大纲的专业/方向

		List<List<TheoreticalLesson>> haveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
		for(int pi = 0;pi<professionalList.size();pi++)
		{
			
			List<TheoreticalLesson> newhaveprofessionalList = new ArrayList<TheoreticalLesson>();
			for(int pj=0;pj<professionalList.get(pi).size();pj++)
			{
				TheoreticalLesson theoLess = professionalList.get(pi).get(pj);
				for(int pk=0;pk<applicationSyllabusList_TheoInnerExperiment.size();pk++)
				{
					ApplicationSyllabus_TheoInnerExperiment appSyll = applicationSyllabusList_TheoInnerExperiment.get(pk);
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
		
		List<TheoreticalLesson> syllabusList = new ArrayList<TheoreticalLesson>();//合并
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
		
		List<TheoreticalLesson> haveSyllabusList = new ArrayList<TheoreticalLesson>();//合并
		
		
		//分开不同大纲ID
		List<List<TheoreticalLesson>> newhaveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
		
		if(haveprofessionalList != null && haveprofessionalList.size() != 0)
		{
			for(int ri=0;ri<haveprofessionalList.size();ri++)
			{
				while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
				{		
					List<TheoreticalLesson> newTheoreticalLesson = new ArrayList<TheoreticalLesson>();
					TheoreticalLesson theo = haveprofessionalList.get(ri).get(0);
					String syid =theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(theo);
					for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
					{
						if(syid.equals(theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(haveprofessionalList.get(ri).get(rj))))
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
	
		List<List<TheoreticalLesson>> newprofessionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
		for(int hi=0;hi<newhaveprofessionalList.size();hi++)
		{
			TheoreticalLesson newtheo = newhaveprofessionalList.get(hi).get(0);
			List<TheoreticalLesson> newallTheoreticalLesson = theoreticalPlanDao.getbyCurriculumAndDepartment(newtheo);//该老师所有应选大纲
	
			while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
				List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
				TheoreticalLesson theoLess = newallTheoreticalLesson.get(0);
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
				String syllabusid = theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(haveSyllabusList.get(i));
				syllabusId.add(syllabusid);
			}
		}
		
		
		//--------剔除已完成大纲的专业方向
		List<ApplicationSyllabus_TheoInnerExperiment> appSyllaList = new ArrayList<ApplicationSyllabus_TheoInnerExperiment>();
		for(int i=0;i<newprofessionalList.size();i++){
			appSyllaList = applicationSyllabusDao.findAppByTheo_TheoInnerExperiment(newprofessionalList.get(i).get(0));
			if(appSyllaList != null && appSyllaList.size() != 0){
					
			for(int k=0;k<appSyllaList.size();k++)
			{
				for(int j=0;j<newprofessionalList.get(i).size();j++)
				{	
					if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
					{
						if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
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
	
	
	
	public Integer syllabusId_TheoInnerExperiment;
	//未编写大纲到大纲——课内实验编写页面
	public String toTheoLesPageInnerExperiment(TheoreticalLesson theoreticalLesson, List<String> selectProfessional) {
		TheoreticalLesson newTheoreticalLesson = theoreticalPlanDao.get(theoreticalLesson.getTheoreticalLessonid());
		List<Professional> professionalList = new ArrayList<Professional>();
		
		if(selectProfessional != null && selectProfessional.size() != 0)
		{
			for(int i=0;i<selectProfessional.size();i++)
			{
				Professional professional = professionalDao.get(selectProfessional.get(i));
				professionalList.add(professional);
			}
		}
		
		//判断由于用于操作失误，在向数据库写新大纲前若数据库中存在，则先删除再写
		ApplicationSyllabus_TheoInnerExperiment newapplicationSyllabus_TheoInnerExperiment = new ApplicationSyllabus_TheoInnerExperiment();
		if(professionalList != null && professionalList.size() != 0)
		{
			for(int i=0;i<professionalList.size();i++)
			{
				newapplicationSyllabus_TheoInnerExperiment = applicationSyllabus_TheoInnerExperimentDao.findappByThenAndPro_TheoInnerExperiment(newTheoreticalLesson,professionalList.get(i));
				if(newapplicationSyllabus_TheoInnerExperiment != null)
				{
					syllabus_TheoInnerExperimentDao.delete(newapplicationSyllabus_TheoInnerExperiment.getSyllabus_TheoInnerExperiment());
				}
			}
		}
		else
		{
			newapplicationSyllabus_TheoInnerExperiment = applicationSyllabusDao.findAppByTheo_TheoInnerExperiment_E(newTheoreticalLesson);
			if(newapplicationSyllabus_TheoInnerExperiment != null)
			{
				syllabus_TheoInnerExperimentDao.delete(newapplicationSyllabus_TheoInnerExperiment.getSyllabus_TheoInnerExperiment());
			}
		}
		
		Syllabus_TheoInnerExperiment syllabus_TheoInnerExperiment = new Syllabus_TheoInnerExperiment();
		syllabus_TheoInnerExperimentDao.add(syllabus_TheoInnerExperiment);
		syllabusId_TheoInnerExperiment = syllabus_TheoInnerExperiment.getSyllabus_TheoInnerExperimentid();

		if(professionalList != null && professionalList.size() != 0){
			//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
			if(newTheoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
			{
				List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newTheoreticalLesson.getCurriculum(),professionalList.get(0));
				for(int i=1;i<professionalList.size();i++)
				{
					List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newTheoreticalLesson.getCurriculum(),professionalList.get(i));
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
				professionalList.add(professional);
			}
				for(int i=0;i<selectProfessional.size();i++){
					Professional professional = professionalDao.get(selectProfessional.get(i));
					Syllabus_TheoInnerExperiment newsyllabus_TheoInnerExperiment = syllabus_TheoInnerExperimentDao.get(syllabusId_TheoInnerExperiment);
					ApplicationSyllabus_TheoInnerExperiment applicationSyllabus_TheoInnerExperiment = new ApplicationSyllabus_TheoInnerExperiment();
					applicationSyllabus_TheoInnerExperiment.setSyllabus_TheoInnerExperiment(newsyllabus_TheoInnerExperiment);
					applicationSyllabus_TheoInnerExperiment.setCurriculum(newTheoreticalLesson.getCurriculum());
					applicationSyllabus_TheoInnerExperiment.setDepartment(newTheoreticalLesson.getDepartment());
					applicationSyllabus_TheoInnerExperiment.setProfessional(professional);
					applicationSyllabus_TheoInnerExperimentDao.add(applicationSyllabus_TheoInnerExperiment);
				}
			Integer count = professionalList.size();
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
		}
		else{
			Syllabus_TheoInnerExperiment newsyllabus_TheoInnerExperiment = syllabus_TheoInnerExperimentDao.get(syllabusId_TheoInnerExperiment);
			ApplicationSyllabus_TheoInnerExperiment applicationSyllabus_TheoInnerExperiment = new ApplicationSyllabus_TheoInnerExperiment();
			applicationSyllabus_TheoInnerExperiment.setSyllabus_TheoInnerExperiment(newsyllabus_TheoInnerExperiment);
			applicationSyllabus_TheoInnerExperiment.setCurriculum(newTheoreticalLesson.getCurriculum());
			applicationSyllabus_TheoInnerExperiment.setDepartment(newTheoreticalLesson.getDepartment());
			applicationSyllabus_TheoInnerExperimentDao.add(applicationSyllabus_TheoInnerExperiment);
			ServletActionContext.getRequest().setAttribute("count", 0);
		}
		
		ServletActionContext.getRequest().setAttribute("newTheoreticalLesson", newTheoreticalLesson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabus_TheoInnerExperiment.getSyllabus_TheoInnerExperimentid());
		return "yes";
	}
	//未编写大纲到大纲——课内实验编写页面——复制
	public void toTheoLesPageCopy(TheoreticalLesson theoreticalLesson,List<String> selectProfessional, String syllabusId_Copy) {
		toTheoLesPageInnerExperiment(theoreticalLesson,selectProfessional);
		//syllabusId_TheoInnerExperiment新建的大纲Id
		//syllabusId_Copy传过来的选择的要复制的大纲Id
	//	Syllabus_TheoInnerExperiment copysyllabus = syllabus_TheoInnerExperimentDao.get(Integer.valueOf(syllabusId_Copy));
	//	Syllabus_TheoInnerExperiment newsyllabus = syllabus_TheoInnerExperimentDao.get(Integer.valueOf(syllabusId_TheoInnerExperiment));
		
		/****************************大纲表一对多 ---理论课课内实验内容关系*************************************/
		List<ExpermentProject_Theo> expermentProjectlist = expermentProject_InnerExperimentDao.getbyExpermentProject_Theo(syllabusId_Copy);
		List<ExpermentProject_Theo> newexpermentProjectId = new ArrayList<ExpermentProject_Theo>();//复制出来的实验内容主键存进的list
		if(expermentProjectlist != null && expermentProjectlist.size() != 0)
		{
			
			for(int di=0;di<expermentProjectlist.size();di++)
			{
				ExpermentProject_Theo expermentProject = expermentProjectlist.get(di);
				ExpermentProject_Theo newexpermentProject = new ExpermentProject_Theo();
				newexpermentProject.setEquipment(expermentProject.getEquipment());
				newexpermentProject.setName(expermentProject.getName());
				newexpermentProject.setNum(expermentProject.getNum());
				newexpermentProject.setRequest(expermentProject.getRequest());
				newexpermentProject.setTheory(expermentProject.getTheory());
				newexpermentProject.setTime(expermentProject.getTime());
				newexpermentProject.setSyllabusid(String.valueOf(syllabusId_TheoInnerExperiment));
				expermentProject_InnerExperimentDao.add(newexpermentProject);
				//String a = String.valueOf(newexpermentProject.getExpermentProject_Theoid());
				newexpermentProjectId.add(newexpermentProject);
			}
			/*for (int i = 0; i < newexpermentProjectId.size(); i++) {
				System.out.println(newexpermentProjectId.get(i)+"  222222222222222222222222222222222222222222");
			}*/
		}
		/****************************大纲表一对多 ---理论课课内实验教材关系*************************************/
		List<TextBooks_InnerExperiment> practiceBooks_InnerExperimentlist = textBooks_InnerExperimentDao.getbytextBooks_InnerExperimentlist(syllabusId_Copy);
		if(practiceBooks_InnerExperimentlist != null && practiceBooks_InnerExperimentlist.size() != 0)
		{
			for(int di=0;di<practiceBooks_InnerExperimentlist.size();di++)
			{
				TextBooks_InnerExperiment practiceBooks_InnerExperiment = practiceBooks_InnerExperimentlist.get(di);
				TextBooks_InnerExperiment newpracticeBooks_InnerExperiment = new TextBooks_InnerExperiment();
				newpracticeBooks_InnerExperiment.setName(practiceBooks_InnerExperiment.getName());
				newpracticeBooks_InnerExperiment.setSyllabusID(String.valueOf(syllabusId_TheoInnerExperiment));
				textBooks_InnerExperimentDao.add(newpracticeBooks_InnerExperiment);
			}
		}
		/****************************大纲表一对多关系-教学目标及对应关系*************************************/
	/*	List<ApplicationSyllabus_TheoInnerExperiment> newApplicationSyllabus_TheoInnerExperimentList =  applicationSyllabus_TheoInnerExperimentDao.findapplicationSyllabusBySylid_TheoInnerExperiment(String.valueOf(syllabusId_TheoInnerExperiment));
		List<ApplicationSyllabus_TheoInnerExperiment> copyApplicationSyllabus_TheoInnerExperimentList =  applicationSyllabus_TheoInnerExperimentDao.findapplicationSyllabusBySylid_TheoInnerExperiment(String.valueOf(syllabusId_Copy));
		if(newApplicationSyllabus_TheoInnerExperimentList != null && newApplicationSyllabus_TheoInnerExperimentList.size() != 0 && copyApplicationSyllabus_TheoInnerExperimentList != null && copyApplicationSyllabus_TheoInnerExperimentList.size() !=0)
		{
			if(newApplicationSyllabus_TheoInnerExperimentList.get(0).getDepartment() == copyApplicationSyllabus_TheoInnerExperimentList.get(0).getDepartment())
			{//相同专业才进行复制
				//指标点选择表复制
				List<IndexSelect_TheoInnerExperiment> indexSelect_TheoInnerExperimentList = indexSelect_TheoInnerExperimentDao.findIndexSelectListBySyllabusid_TheoInnerExperiment(syllabusId_Copy);
				if(indexSelect_TheoInnerExperimentList != null && indexSelect_TheoInnerExperimentList.size() != 0)
				{
					for(int i=0;i<indexSelect_TheoInnerExperimentList.size();i++)
					{
						IndexSelect_TheoInnerExperiment indexSelect_TheoInnerExperiment = indexSelect_TheoInnerExperimentList.get(i);
						IndexSelect_TheoInnerExperiment newindexSelect_TheoInnerExperiment = new IndexSelect_TheoInnerExperiment();
						newindexSelect_TheoInnerExperiment.setAbility(indexSelect_TheoInnerExperiment.getAbility());
						newindexSelect_TheoInnerExperiment.setAnalisis(indexSelect_TheoInnerExperiment.getAnalisis());
						newindexSelect_TheoInnerExperiment.setSyllabusid(String.valueOf(syllabusId_TheoInnerExperiment));
						indexSelect_TheoInnerExperimentDao.add(newindexSelect_TheoInnerExperiment);
					}
				}
				//教学目标表复制
				List<TeachObj_TheoInnerExperiment> teachObj_TheoInnerExperimentList = teachObj_TheoInnerExperimentDao.findTeachObjListBySyllabusid_TheoInnerExperiment(syllabusId_Copy);
				List<TeachObj_TheoInnerExperiment> newTeachObjList_TheoInnerExperiment= new ArrayList<TeachObj_TheoInnerExperiment>();
				if(teachObj_TheoInnerExperimentList != null && teachObj_TheoInnerExperimentList.size() != 0)
				{
					for(int i=0;i<teachObj_TheoInnerExperimentList.size();i++)
					{
						TeachObj_TheoInnerExperiment teachObj_TheoInnerExperiment = teachObj_TheoInnerExperimentList.get(i);
						TeachObj_TheoInnerExperiment newteachObj_TheoInnerExperiment = new TeachObj_TheoInnerExperiment();
						newteachObj_TheoInnerExperiment.setTeachObjContent_TheoInnerExperiment(teachObj_TheoInnerExperiment.getTeachObjContent_TheoInnerExperiment());
						newteachObj_TheoInnerExperiment.setSyllabusid(String.valueOf(syllabusId_TheoInnerExperiment));
						teachObj_TheoInnerExperimentDao.add(newteachObj_TheoInnerExperiment);
						newTeachObjList_TheoInnerExperiment.add(newteachObj_TheoInnerExperiment);
					}
				}
				//毕业要求与教学目标对应关系表复制
				List<AbilityAndTeachObj_TheoInnerExperiment> abilityAndTeachObj_TheoInnerExperimentList = abilityAndTeachObj_TheoInnerExperimentDao.findAbiAndTeachListBySyllabusid_TheoInnerExperiment(syllabusId_Copy);
				if(abilityAndTeachObj_TheoInnerExperimentList != null && abilityAndTeachObj_TheoInnerExperimentList.size() !=0)
				{
					for(int i=0;i<abilityAndTeachObj_TheoInnerExperimentList.size();i++)
					{
						AbilityAndTeachObj_TheoInnerExperiment abilityAndTeachObj_TheoInnerExperiment = abilityAndTeachObj_TheoInnerExperimentList.get(i);
						AbilityAndTeachObj_TheoInnerExperiment newabilityAndTeachObj_TheoInnerExperiment = new AbilityAndTeachObj_TheoInnerExperiment();
						newabilityAndTeachObj_TheoInnerExperiment.setAbility(abilityAndTeachObj_TheoInnerExperiment.getAbility());
						newabilityAndTeachObj_TheoInnerExperiment.setTeachObj_TheoInnerExperiment(newTeachObjList_TheoInnerExperiment.get(teachObj_TheoInnerExperimentList.indexOf(abilityAndTeachObj_TheoInnerExperiment.getTeachObj_TheoInnerExperiment())));
						newabilityAndTeachObj_TheoInnerExperiment.setSyllabusid(String.valueOf(syllabusId_TheoInnerExperiment));
						abilityAndTeachObj_TheoInnerExperimentDao.add(newabilityAndTeachObj_TheoInnerExperiment);
					}
				}
			}
		}*/
		
		/****************************大纲表一对多关系-学时分配*************************************/
		List<DistributeHour_TheoInnerExperiment> DistributeHour_TheoInnerExperimentList = distributeHour_TheoInnerExperimentDao.getbyDistributeHour_TheoInnerExperiment(syllabusId_Copy);
		List<DistributeHour_TheoInnerExperiment> newdistributeHour_TheoInnerExperimentId = new ArrayList<DistributeHour_TheoInnerExperiment>();//复制出来的学时分配存进list
		if(DistributeHour_TheoInnerExperimentList != null && DistributeHour_TheoInnerExperimentList.size() != 0)
		{
			
			for(int i=0;i<DistributeHour_TheoInnerExperimentList.size();i++)
			{
				DistributeHour_TheoInnerExperiment distributeHour_TheoInnerExperiment = DistributeHour_TheoInnerExperimentList.get(i);
				DistributeHour_TheoInnerExperiment newdistributeHour_TheoInnerExperiment = new DistributeHour_TheoInnerExperiment();
				newdistributeHour_TheoInnerExperiment.setExp(distributeHour_TheoInnerExperiment.getExp());
				newdistributeHour_TheoInnerExperiment.setHoursOfClass(distributeHour_TheoInnerExperiment.getHoursOfClass());
				newdistributeHour_TheoInnerExperiment.setHoursOfExp(distributeHour_TheoInnerExperiment.getHoursOfExp());
				newdistributeHour_TheoInnerExperiment.setName(distributeHour_TheoInnerExperiment.getName());
				newdistributeHour_TheoInnerExperiment.setSyllabusid(String.valueOf(syllabusId_TheoInnerExperiment));
				distributeHour_TheoInnerExperimentDao.add(newdistributeHour_TheoInnerExperiment);
				//System.out.println(newdistributeHour_TheoInnerExperiment.getDistributeHour_TheoInnerExperimentid());
				//String a = String.valueOf(newdistributeHour_TheoInnerExperiment.getDistributeHour_TheoInnerExperimentid());
				
				newdistributeHour_TheoInnerExperimentId.add(newdistributeHour_TheoInnerExperiment);
				
			}
			/*for(int j=0;j<newdistributeHour_TheoInnerExperimentId.size();j++){
				System.out.println(newdistributeHour_TheoInnerExperimentId.get(j)+"11111111111111111111111111111111111111111111111111111");
			}*/
			
		}
		//将复制到学时分配和实验内容循环对应的存进关联表里
		
		for (int i = 0; i < newdistributeHour_TheoInnerExperimentId.size(); i++) {
			BaseExpTheoInnerRelateExpProjectTheo relate = new BaseExpTheoInnerRelateExpProjectTheo();
			relate.setTheoInnerExperiment(newdistributeHour_TheoInnerExperimentId.get(i));
			relate.setTheo(newexpermentProjectId.get(i));	
			relateDao.add(relate);
			/*System.out.println(newdistributeHour_TheoInnerExperimentId.get(i).getDistributeHour_TheoInnerExperimentid());
			System.out.println(newexpermentProjectId.get(i).getExpermentProject_Theoid());
			System.out.println(i);*/
		}
		
	}
	//已编写大纲到大纲——课内实验编写页面
	public String toupdateTheoLesPageInnerExperiment(String syllabusId,TheoreticalLesson theoreticalLesson,List<String> haveselectProfessional) {
		TheoreticalLesson newTheoreticalLesson = theoreticalPlanDao.get(theoreticalLesson.getTheoreticalLessonid());
		
		if(haveselectProfessional != null && haveselectProfessional.size() != 0){
			List<Professional> professionalList = new ArrayList<Professional>();
			for(int i=0;i<haveselectProfessional.size();i++){//将勾选的专业方向id查到实体放入List
				Professional professional = professionalDao.get(haveselectProfessional.get(i));
				professionalList.add(professional);
			}
			
			//若为专业选修课，判断该课程的所有专业方向的课程矩阵是否相同
			if(newTheoreticalLesson.getCurriculum().getNatureOfCourse().getNatureOfCourseid().equals("12"))
			{
				List<CurriculumMatrix> curriculumMatrixs = curriculumMatrixDao.findbycuandpro(newTheoreticalLesson.getCurriculum(),professionalList.get(0));
				for(int i=1;i<professionalList.size();i++)
				{
					List<CurriculumMatrix> curriculumMatrixs_List = curriculumMatrixDao.findbycuandpro(newTheoreticalLesson.getCurriculum(),professionalList.get(i));
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
			
			List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabus_TheoInnerExperimentList = applicationSyllabus_TheoInnerExperimentDao.findapplicationSyllabusBySylid_TheoInnerExperiment(syllabusId);//通过大纲id查询该大纲的所有应用专业
			List<Professional> professional = new ArrayList<Professional>();
			if(applicationSyllabus_TheoInnerExperimentList != null && applicationSyllabus_TheoInnerExperimentList.size() != 0){
				for(int i=0;i<applicationSyllabus_TheoInnerExperimentList.size();i++){
					professional.add(applicationSyllabus_TheoInnerExperimentList.get(i).getProfessional());
				}
			}
			
			for(int i=0;i<professionalList.size();i++){
				if(professional.contains(professionalList.get(i))){
					professional.remove(professionalList.get(i));
				}
				else{
					ApplicationSyllabus_TheoInnerExperiment applicationSyllabus_TheoInnerExperiment = new ApplicationSyllabus_TheoInnerExperiment();
					Syllabus_TheoInnerExperiment syllabus_TheoInnerExperiment = syllabus_TheoInnerExperimentDao.get(Integer.valueOf(syllabusId));
					applicationSyllabus_TheoInnerExperiment.setSyllabus_TheoInnerExperiment(syllabus_TheoInnerExperiment);
					applicationSyllabus_TheoInnerExperiment.setCurriculum(newTheoreticalLesson.getCurriculum());
					applicationSyllabus_TheoInnerExperiment.setDepartment(newTheoreticalLesson.getDepartment());
					applicationSyllabus_TheoInnerExperiment.setProfessional(professionalList.get(i));
					applicationSyllabus_TheoInnerExperimentDao.add(applicationSyllabus_TheoInnerExperiment);
				}
			}
			
			if(professional != null && professional.size() != 0){
				for(int i=0;i<professional.size();i++){
					ApplicationSyllabus_TheoInnerExperiment applicationSyllabus_TheoInnerExperiment =  applicationSyllabus_TheoInnerExperimentDao.findappByThenAndPro_TheoInnerExperiment(newTheoreticalLesson,professional.get(i));
					applicationSyllabus_TheoInnerExperimentDao.delete(applicationSyllabus_TheoInnerExperiment);
				}
			}
			Integer count = professionalList.size();
			ServletActionContext.getRequest().setAttribute("count", count);
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);
		}
		
		ServletActionContext.getRequest().setAttribute("newTheoreticalLesson", newTheoreticalLesson);
		ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
		return "yes";
	}
	//删除大纲——课内实验
	public void deleteTheoSyllabusInnerExperiment(String syllabusId) {
		/****************************大纲表一对一关系*************************************/
		Syllabus_TheoInnerExperiment copysyllabus_TheoInnerExperiment = syllabus_TheoInnerExperimentDao.get(Integer.valueOf(syllabusId));
		if(copysyllabus_TheoInnerExperiment != null)
		{
			syllabus_TheoInnerExperimentDao.delete(copysyllabus_TheoInnerExperiment);
		}
		/****************************大纲表一对多 ---理论课课内实验内容关系*************************************/
		List<ExpermentProject_Theo> expermentProjectlist = expermentProject_InnerExperimentDao.getbyExpermentProject_Theo(syllabusId);
		if(expermentProjectlist != null && expermentProjectlist.size() != 0)
		{
			for(int di=0;di<expermentProjectlist.size();di++)
			{
				expermentProject_InnerExperimentDao.delete(expermentProjectlist.get(di));
			}
		}
		/****************************大纲表一对多 ---理论课课内实验教材关系*************************************/
		List<TextBooks_InnerExperiment> practiceBooks_InnerExperimentlist = textBooks_InnerExperimentDao.getbytextBooks_InnerExperimentlist(syllabusId);
		if(practiceBooks_InnerExperimentlist != null && practiceBooks_InnerExperimentlist.size() != 0)
		{
			for(int di=0;di<practiceBooks_InnerExperimentlist.size();di++)
			{
				textBooks_InnerExperimentDao.delete(practiceBooks_InnerExperimentlist.get(di));
			}
		}
		/****************************大纲表一对多关系-教学目标及对应关系*************************************/
		List<ApplicationSyllabus_TheoInnerExperiment> copyApplicationSyllabus_TheoInnerExperimentList =  applicationSyllabus_TheoInnerExperimentDao.findapplicationSyllabusBySylid_TheoInnerExperiment(String.valueOf(syllabusId));
		if(copyApplicationSyllabus_TheoInnerExperimentList != null && copyApplicationSyllabus_TheoInnerExperimentList.size() !=0)
		{
			for(int i=0;i<copyApplicationSyllabus_TheoInnerExperimentList.size();i++){
				applicationSyllabus_TheoInnerExperimentDao.delete(copyApplicationSyllabus_TheoInnerExperimentList.get(i));
			}
		}
		//指标点选择表删除
		List<IndexSelect_TheoInnerExperiment> indexSelect_TheoInnerExperimentList = indexSelect_TheoInnerExperimentDao.findIndexSelectListBySyllabusid_TheoInnerExperiment(syllabusId);
		if(indexSelect_TheoInnerExperimentList != null && indexSelect_TheoInnerExperimentList.size() != 0)
		{
			for(int i=0;i<indexSelect_TheoInnerExperimentList.size();i++)
			{
				indexSelect_TheoInnerExperimentDao.delete(indexSelect_TheoInnerExperimentList.get(i));
			}
		}
		//教学目标表删除
		List<TeachObj_TheoInnerExperiment> teachObj_TheoInnerExperimentList = teachObj_TheoInnerExperimentDao.findTeachObjListBySyllabusid_TheoInnerExperiment(syllabusId);
		if(teachObj_TheoInnerExperimentList != null && teachObj_TheoInnerExperimentList.size() != 0)
		{
			for(int i=0;i<teachObj_TheoInnerExperimentList.size();i++)
			{
				teachObj_TheoInnerExperimentDao.delete(teachObj_TheoInnerExperimentList.get(i));
			}
		}
		//毕业要求与教学目标对应关系表删除
		List<AbilityAndTeachObj_TheoInnerExperiment> abilityAndTeachObj_TheoInnerExperimentList = abilityAndTeachObj_TheoInnerExperimentDao.findAbiAndTeachListBySyllabusid_TheoInnerExperiment(syllabusId);
		if(abilityAndTeachObj_TheoInnerExperimentList != null && abilityAndTeachObj_TheoInnerExperimentList.size() !=0)
		{
			for(int i=0;i<abilityAndTeachObj_TheoInnerExperimentList.size();i++)
			{
				abilityAndTeachObj_TheoInnerExperimentDao.delete(abilityAndTeachObj_TheoInnerExperimentList.get(i));
			}
		}
		/****************************大纲表一对多关系-学时分配*************************************/
		List<DistributeHour_TheoInnerExperiment> DistributeHour_TheoInnerExperimentList = distributeHour_TheoInnerExperimentDao.getbyDistributeHour_TheoInnerExperiment(syllabusId);
		if(DistributeHour_TheoInnerExperimentList != null && DistributeHour_TheoInnerExperimentList.size() != 0)
		{
			for(int i=0;i<DistributeHour_TheoInnerExperimentList.size();i++)
			{
				distributeHour_TheoInnerExperimentDao.delete(DistributeHour_TheoInnerExperimentList.get(i));
			}
		}
		
		ServletActionContext.getRequest().setAttribute("tag", "toCheckTheoLes_InnerExperiment");
		ServletActionContext.getRequest().setAttribute("msg", "删除成功！");
	}
		
		
	public PageBean findAllTheolen(Integer currentpage,TheoreticalLesson theoreticalLesson)
	{
		PageBean pageBean=new PageBean();
		College college=collegeDao.get(theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		List<College> listcollege=collegeDao.findAll();
		pageBean.setCollegelist(listcollege);
		List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
		List<Curriculum> listcurr=new ArrayList<Curriculum>();
		List<TheoreticalLesson> listtheolen=new ArrayList<TheoreticalLesson>();
		if(!theoreticalLesson.getCurriculum().getCollege().getCollegeid().equals("-1")&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
				&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
		{
			listcurr=curriculumDao.findCurrByCollege(college); 
		}
		//*****************************************************************
		else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
				&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
		{
			List<Curriculum> list22=curriculumDao.findCurrByCurrcid(theoreticalLesson.getCurriculum().getCurriculumid());
			if(list22.size()!=0)
			{
				for(int i=0;i<list22.size();i++){
					listcurr.add(list22.get(i));
				}
			}
			
		}
		//*******************************************************************
		else if("".equals(theoreticalLesson.getCurriculum().getCurriculumid())
				&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
		{
			List<Curriculum> list22=curriculumDao.findCurrByCurrcname(theoreticalLesson.getCurriculum().getCurriculumCname());
			if(list22.size()!=0)
			{
					for(int i=0;i<list22.size();i++){
					listcurr.add(list22.get(i));
				}
			}
		}


		else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
				&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
		{	
			List<Curriculum> culist = curriculumDao.findCurrByCurrcname(theoreticalLesson.getCurriculum().getCurriculumCname());
			Curriculum curr1=null;
			if(culist.size()!=0){
				curr1 = culist.get(0);
			}
			Curriculum curr2=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
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
			listtheolen=theoreticalPlanDao.findtheolenbycurr(listcurr.get(i));
			if(listtheolen.size()!=0)
			{
				theolenlist.add(listtheolen);
				listcurr2.add(listcurr.get(i));
			}
			else
			{
				if(!listcurr.get(i).getCourseLei().equals("实践课"))
				{
					TheoreticalLesson theolen=new TheoreticalLesson();
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
		List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
		pageBean.setTheoreticalLessonlist(sslist);
		return pageBean;
	}
	//跳转到生成培养计划页面
	public void toExportPlanPage(String tnum) {
		findUserInfo(tnum);
		if(user.getAdminlevel() == 5){	//为校管理员时，返回学院List
			collegelist = collegeDao.findAll();
		}
		if(user.getAdminlevel() == 3){	//为院管理员时，返回该管理员所属学院，存入List中
			collegelist = findCollegeById(user.getCollege().getCollegeid());	
		}
		if(user.getAdminlevel() == 1){	//为系管理员时，返回管理员所在的学院和系，分别存入List中			
			collegelist = findCollegeById(user.getCollege().getCollegeid());
			departmentlist = findDepartmentById(user.getDepartment().getDepartmentid());
			ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);	
		}					
		ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
	}
	public void setid(TheoreticalLesson theoreticalLesson) {
		TheoreticalLesson newTheoreticalLesson = theoreticalPlanDao.get(theoreticalLesson.getTheoreticalLessonid());
		ServletActionContext.getRequest().setAttribute("departmentid", newTheoreticalLesson.getDepartment().getDepartmentid());
		ServletActionContext.getRequest().setAttribute("collegeid", newTheoreticalLesson.getDepartment().getCollege().getCollegeid());
		
	}
	
	public void findtheolen2(Integer currentpage,TheoreticalLesson theoreticalLesson, String departmenttag) {
		//在pageBean中设置college使得条件查询可执行
		//找到学院开设的课程
		List<Curriculum> list=theoreticalPlanDao.findcurrbycollege(theoreticalLesson.getCurriculum().getCollege());
		for(int i=0;i<list.size();i++){
			//找到了课1对应的列表
			List<TheoreticalLesson> theolenlist = theoreticalPlanDao.findtheolenbycurr(list.get(i));
			if(theolenlist.size()==0){
				
			}
			else if(theolenlist.size()==1){
				//判断是否未分配
				if(theolenlist.get(0).getTeachDepartment()==null && theolenlist.get(0).getTeacher()==null){
					String id = theolenlist.get(0).getDepartment().getCollege().getCollegeid();
					if(theoreticalLesson.getCurriculum().getCollege().getCollegeid().equals(id))
					{
						TheoreticalLesson theoreticalLen=theolenlist.get(0);
						theoreticalLen.setTeachDepartment(theoreticalLen.getDepartment());
						theoreticalPlanDao.update(theoreticalLen);
					}
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
						if(theolenlist.get(k).getTeachDepartment()==null && theolenlist.get(0).getTeacher()==null){
							String id = theolenlist.get(k).getDepartment().getCollege().getCollegeid();
							if(theoreticalLesson.getCurriculum().getCollege().getCollegeid().equals(id))
							{
								TheoreticalLesson theoreticalLen=theolenlist.get(k);
								theoreticalLen.setTeachDepartment(theoreticalLen.getDepartment());
								theoreticalPlanDao.update(theoreticalLen);
							}
						}
					}
				}
			}
		}
	}

public PageBean findExptheolen(Integer currentpage,	TheoreticalLesson theoreticalLesson, String departmenttag) {
	PageBean pageBean=new PageBean();
	List<College> listcollege=new ArrayList<College>();
	College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
	listcollege.add(college);
	pageBean.setCollegelist(listcollege);
	List<List<TheoreticalLesson>> theolenlist1=findtheolenExplist(theoreticalLesson,departmenttag);
	List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
	if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
		theolenlist=theolenlist1;
	}else{
	//当搜索条件添加了课程名称时显示相关的记录
		if(departmenttag.equals("1")){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getExperiment()!=null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
				if(theolenlist1.get(i).get(0).getExperiment()==null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
		if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
				theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
						.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
						theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					theolenlist.add(theolenlist1.get(i));
				}	
			}
		}
		else{
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
	List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
	pageBean.setTheoreticalLessonlist(sslist);
	return pageBean;
}


private List<List<TheoreticalLesson>> findtheolenExplist(TheoreticalLesson theoreticalLesson, String departmenttag) {
	List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
	List<Curriculum> list=theoreticalPlanDao.findcurrbycollegeExp(theoreticalLesson.getCurriculum().getCollege());
	
	for(int i=0;i<list.size();i++){
		//找到了课1对应的列表
		List<TheoreticalLesson> theolenlist=theoreticalPlanDao.findtheolenbycurr(list.get(i));
		List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
		//遍历课1的列表
		for(int j=0;j<theolenlist.size();j++){
			/*if(theolenlist.get(j).getExperiment()!=null ||(theolenlist.get(j).getExperiment()==null && theolenlist.get(j).getExperimenter()!=null) ){	
				//已分配课1放到theolenlist1
				theolenlist1.add(theolenlist.get(j));
			}
			else{
				//未分配课1放到theolenlist2
				//theolenlist.get(j).setDepartmenttag("-1");
				//this.getHibernateTemplate().update(theolenlist.get(j));
				theolenlist2.add(theolenlist.get(j));
			}*/
			if(theolenlist.get(j).getCteachDepartment() == null && theolenlist.get(j).getCteacher() == null && theolenlist.get(j).getExperiment() == null && theolenlist.get(j).getExperimenter() == null){
				
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






public List<Experiment> findAllExperiment() {
List<Experiment> list = experimentDao.findAll();
return list;
}


public void goupdateExpdepart(TheoreticalLesson theoreticalLesson,List<String> newchoosedepartlist) {
	TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
	for(int i=0;i<newchoosedepartlist.size();i++){
		
		//获取课程列表
		Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
		if((professionalDao.get(newchoosedepartlist.get(i)))!=null)
		{
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(newchoosedepartlist.get(i)));
		}
		else
		{
			Department department=departmentDao.get(newchoosedepartlist.get(i));
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
		}
		if(theoreticalLen.getCteachDepartment() == null ||(theoreticalLen.getCteachDepartment().getDepartmentid()!=(theoreticalLesson.getCteachDepartment().getDepartmentid()))){
			theoreticalLen.setCteachDepartment(departmentDao.get(theoreticalLesson.getCteachDepartment().getDepartmentid()));
			theoreticalLen.setExperimenter(null);
			theoreticalLen.setCteacher(null);
			theoreticalLen.setExperiment(null);
		}
		theoreticalPlanDao.update(theoreticalLen);
	}
}

public PageBean Expfindtheolendirectuser(Integer currentpage,	TheoreticalLesson theoreticalLesson, String departmenttag){
	PageBean pageBean=new PageBean();
	List<College> listcollege=new ArrayList<College>();
	College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
	listcollege.add(college);
	pageBean.setCollegelist(listcollege);
	
	List<List<TheoreticalLesson>> theolenlist1=findtheolenExperlist(theoreticalLesson,departmenttag);
	
	List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
	if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
		theolenlist=theolenlist1;
	}else{
	//当搜索条件添加了课程名称时显示相关的记录
		if(departmenttag.equals("1")){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getExperimenter()!=null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
		if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
				theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
						.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
						theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					theolenlist.add(theolenlist1.get(i));
				}	
			}
		}
		else{
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
	List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
	pageBean.setTheoreticalLessonlist(sslist);
	return pageBean;
}


private List<List<TheoreticalLesson>> findtheolenExperlist(TheoreticalLesson theoreticalLesson, String departmenttag) {
	List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
	List<Curriculum> list=theoreticalPlanDao.findcurrbycollegeExp(theoreticalLesson.getCurriculum().getCollege());
	
	for(int i=0;i<list.size();i++){
		//找到了课1对应的列表
		List<TheoreticalLesson> theolenlist=theoreticalPlanDao.findtheolenbycurr(list.get(i));
		List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
		//遍历课1的列表
		for(int j=0;j<theolenlist.size();j++){
			/*if(theolenlist.get(j).getExperimenter()!=null){	
				//已分配课1放到theolenlist1
				theolenlist1.add(theolenlist.get(j));
			}
			else{
				//未分配课1放到theolenlist2
				//theolenlist.get(j).setDepartmenttag("-1");
				//this.getHibernateTemplate().update(theolenlist.get(j));
				theolenlist2.add(theolenlist.get(j));
			}*/
			if(theolenlist.get(j).getCteachDepartment() == null && theolenlist.get(j).getCteacher() == null && theolenlist.get(j).getExperiment() == null && theolenlist.get(j).getExperimenter() == null){
				
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

public PageBean findExperimenter(Integer currentpage,TheoreticalLesson theoreticalLesson){
	PageBean pageBean=new PageBean();
	List<User> userlist=new ArrayList<User>();
	List<User> userlist1=userDao.findUserByExperimenter();
	if(theoreticalLesson.getExperimenter() != null)
	{	
		if(theoreticalLesson.getExperimenter().getTnum() != null && !"".equals(theoreticalLesson.getExperimenter().getTnum()) && theoreticalLesson.getExperimenter().getUsername() != null && !"".equals(theoreticalLesson.getExperimenter().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getTnum().equals(theoreticalLesson.getExperimenter().getTnum())&&
						userlist1.get(i).getUsername().equals(theoreticalLesson.getExperimenter().getUsername()))
				{
					userlist.add(userlist1.get(i));
				}
			}
		}
		else if(!"".equals(theoreticalLesson.getExperimenter().getTnum()) && "".equals(theoreticalLesson.getExperimenter().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getTnum().equals(theoreticalLesson.getExperimenter().getTnum()))
				{
					userlist.add(userlist1.get(i));
				}
			}
		}
		else if("".equals(theoreticalLesson.getExperimenter().getTnum()) && !"".equals(theoreticalLesson.getExperimenter().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getUsername().equals(theoreticalLesson.getExperimenter().getUsername()))
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


/*把课内实验分配给实验员*/
public void updateTheolenByCollegeDirectUser(TheoreticalLesson theoreticalLesson, List<String> depart) 
{
	TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
	for(int i=0;i<depart.size();i++){
		Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
		if((professionalDao.get(depart.get(i)))!=null)
		{
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
		}
		else
		{
			Department department=departmentDao.get(depart.get(i));
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
		}
		
		if(theoreticalLen.getExperimenter() == null ||(theoreticalLen.getExperimenter().getTnum()!=(theoreticalLesson.getExperimenter().getTnum()))){
			theoreticalLen.setExperimenter(userDao.get(theoreticalLesson.getExperimenter().getTnum()));
			theoreticalLen.setCteacher(null);
			theoreticalLen.setExperiment(null);
			theoreticalLen.setCteachDepartment(null);
		}
		theoreticalPlanDao.update(theoreticalLen);
	}
}

/*把理论课分配给老师*/
public void updateTheolenByCollegeDirectUser1(TheoreticalLesson theoreticalLesson, List<String> depart) 
{
	TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
	for(int i=0;i<depart.size();i++){
		Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
		if((professionalDao.get(depart.get(i)))!=null)
		{
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
		}
		else
		{
			Department department=departmentDao.get(depart.get(i));
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
		}
		
		if(theoreticalLen.getExperimenter() == null ||(theoreticalLen.getExperimenter().getTnum()!=(theoreticalLesson.getExperimenter().getTnum()))){
			theoreticalLen.setExperimenter(userDao.get(theoreticalLesson.getExperimenter().getTnum()));
			theoreticalLen.setCteacher(null);
			theoreticalLen.setExperiment(null);
			theoreticalLen.setCteachDepartment(null);
		}
		theoreticalPlanDao.update(theoreticalLen);
	}
}


public PageBean findtheolenByExperierToUser(Integer currentpage,TheoreticalLesson theoreticalLesson, String usertag) {
	PageBean pageBean=new PageBean();
	List<College> listcollege=new ArrayList<College>();
/*		College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
	listcollege.add(college);*/
	listcollege=collegeDao.findAll();
	pageBean.setCollegelist(listcollege);
	
	List<Experiment> listdepart=new ArrayList<Experiment>();
	listdepart.add(experimentDao.get(theoreticalLesson.getExperiment().getExperimentid()));
	pageBean.setExperimentlist(listdepart);
	
	theoreticalLesson.setExperiment(listdepart.get(0));
	
	List<List<TheoreticalLesson>> theolenlist1=findtheolenlistByExperierUser(theoreticalLesson,usertag);
		List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
	
	if("".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&usertag.equals("0")){
		theolenlist=theolenlist1;
	}else{
	//当搜索条件添加了课程名称时显示相关的记录
		if(usertag.equals("1")){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getExperimenter()!=null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
					{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
				if(theolenlist1.get(i).get(0).getExperimenter()==null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
		if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
				theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
						.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
						theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
					theolenlist.add(theolenlist1.get(i));
				}	
			}
		}
		else{
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
			{
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
							.equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
			{
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
							.equals(theoreticalLesson.getCurriculum().getCurriculumid())){
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
	List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
	pageBean.setCurriculumlist(curriculumlist);
	pageBean.setTheoreticalLessonlist(sslist);
	return pageBean;
}

public List<List<TheoreticalLesson>> findtheolenlistByExperierUser(TheoreticalLesson theoreticalLesson, String usertag){
	List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
	List<TheoreticalLesson> list=new ArrayList<TheoreticalLesson>();
	//找到当前任课系的所有理论课记录
	if(theoreticalLesson.getCurriculum().getCollege().getCollegeid().equals("00")){
		list=theoreticalPlanDao.findTheolenByExperiment(theoreticalLesson.getExperiment());
	}
	else{
		College college = collegeDao.get(theoreticalLesson.getCurriculum().getCollege().getCollegeid());
		List<Curriculum> listcurr=theoreticalPlanDao.findcurrbycollege(college);
		for(int i = 0; i<listcurr.size();i++){
			List<TheoreticalLesson> list1=theoreticalPlanDao.findTheolenByExperimentAndCollege(theoreticalLesson.getExperiment(),listcurr.get(i));
			list.addAll(list1);
		}
	}
	
	List<Curriculum> listcurr=new ArrayList<Curriculum>();
	//获得理论课记录所有课程存入listcurr
	for(int i=0;i<list.size();i++)
	{
		listcurr.add(list.get(i).getCurriculum());
	 }
	//listcurr去除重复课程
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
		List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
		for(int j=0;j<list.size();j++){
			if(list.get(j).getCurriculum().equals(listcurr.get(i))&&list.get(j).getExperimenter()!=null)
			{
				theolenlist1.add(list.get(j));
			}
			if(list.get(j).getCurriculum().equals(listcurr.get(i))&&list.get(j).getExperimenter()==null)
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

public PageBean findExperimenterByDepart(Integer currentpage,TheoreticalLesson theoreticalLesson, String tnum){
	PageBean pageBean=new PageBean();
	List<User> userlist=new ArrayList<User>();
	Experiment experiment = userDao.get(tnum).getExperiment();
	List<User> userlist1=userDao.findExperimenterByExperiment(experiment);
	if(theoreticalLesson.getExperimenter() != null)
	{	
		if(theoreticalLesson.getExperimenter().getTnum() != null && !"".equals(theoreticalLesson.getExperimenter().getTnum()) && theoreticalLesson.getExperimenter().getUsername() != null && !"".equals(theoreticalLesson.getExperimenter().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getTnum().equals(theoreticalLesson.getExperimenter().getTnum())&&
						userlist1.get(i).getUsername().equals(theoreticalLesson.getExperimenter().getUsername()))
				{
					userlist.add(userlist1.get(i));
				}
			}
		}
		else if(!"".equals(theoreticalLesson.getExperimenter().getTnum()) && "".equals(theoreticalLesson.getExperimenter().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getTnum().equals(theoreticalLesson.getExperimenter().getTnum()))
				{
					userlist.add(userlist1.get(i));
				}
			}
		}
		else if("".equals(theoreticalLesson.getExperimenter().getTnum()) && !"".equals(theoreticalLesson.getExperimenter().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getUsername().equals(theoreticalLesson.getExperimenter().getUsername()))
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

public void updateTheolenByExperimentDirectUser(TheoreticalLesson theoreticalLesson, List<String> depart) 
{
	TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
	for(int i=0;i<depart.size();i++){
		Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
		if((professionalDao.get(depart.get(i)))!=null)
		{
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
		}
		else
		{
			Department department=departmentDao.get(depart.get(i));
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
		}
		if(theoreticalLesson.getExperimenter()!=null){
			if(theoreticalLesson.getExperimenter().getTnum()!=null){
				theoreticalLen.setExperimenter(userDao.get(theoreticalLesson.getExperimenter().getTnum()));
				}
			}
		}
		if(theoreticalLesson.getTeacher()!=null){
			if(theoreticalLesson.getTeacher().getTnum()!=null){
				theoreticalLen.setTeacher(userDao.get(theoreticalLesson.getTeacher().getTnum()));
			}
		}
		theoreticalPlanDao.update(theoreticalLen);
}






//新加的功能
//分配课内实验到专业
public PageBean tofindExptheolen(Integer currentpage,TheoreticalLesson theoreticalLesson, String departmenttag) {
	PageBean pageBean=new PageBean();
	
	List<College> listcollege=new ArrayList<College>();//获取学院列表
	College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
	listcollege.add(college);
	pageBean.setCollegelist(listcollege);
	
	List<List<TheoreticalLesson>> theolenlist1=tofindtheolenExplist(theoreticalLesson,departmenttag);//获取课程(课内实验分配给专业)
	
	List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
	if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
		theolenlist=theolenlist1;//没有按“搜索”键的时候，将获取的课程列表显示出来
	}else{
	//当搜索条件添加了课程名称时显示相关的记录  
		if(departmenttag.equals("1")){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCteachDepartment()!=null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
				if(theolenlist1.get(i).get(0).getCteachDepartment()==null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
		if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
				theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
						.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
						theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					theolenlist.add(theolenlist1.get(i));
				}	
			}
		}
		else{
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
	List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
	pageBean.setTheoreticalLessonlist(sslist);
	return pageBean;
}

private List<List<TheoreticalLesson>> tofindtheolenExplist(TheoreticalLesson theoreticalLesson, String departmenttag) {
	List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
	List<Curriculum> list=theoreticalPlanDao.findcurrbycollegeExp(theoreticalLesson.getCurriculum().getCollege());
	
	for(int i=0;i<list.size();i++){
		//找到了课1对应的列表
		List<TheoreticalLesson> theolenlist=theoreticalPlanDao.findtheolenbycurr(list.get(i));//获取全部课程
		List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
		//遍历课1的列表
		for(int j=0;j<theolenlist.size();j++){
			if(theolenlist.get(j).getCteachDepartment() == null && theolenlist.get(j).getCteacher() == null && theolenlist.get(j).getExperiment() == null && theolenlist.get(j).getExperimenter() == null){
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

//分配课内实验到老师
public PageBean toExpfindtheolendirectuser(Integer currentpage,	TheoreticalLesson theoreticalLesson, String departmenttag){
	PageBean pageBean=new PageBean();
	
	List<College> listcollege=new ArrayList<College>();//获取学院列表
	College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
	listcollege.add(college);
	pageBean.setCollegelist(listcollege);
	
	List<List<TheoreticalLesson>> theolenlist1=tofindtheolenExperlist(theoreticalLesson,departmenttag);//获取课内实验课（课内实验分配老师）
	
 	List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
	if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
		theolenlist=theolenlist1;
	}else{
	//当搜索条件添加了课程名称时显示相关的记录
		if(departmenttag.equals("1")){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCteacher()!=null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
				if(theolenlist1.get(i).get(0).getCteacher()==null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
		if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
				theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
						.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
						theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					theolenlist.add(theolenlist1.get(i));
				}	
			}
		}
		else{
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
	List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
	pageBean.setTheoreticalLessonlist(sslist);
	return pageBean;
}

//下载课内实验全部的分配情况
public PageBean toExpfindtheolendirectuser1(TheoreticalLesson theoreticalLesson, String departmenttag){
	PageBean pageBean=new PageBean();
	
	List<College> listcollege=new ArrayList<College>();//获取学院列表
	College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
	listcollege.add(college);
	pageBean.setCollegelist(listcollege);
	
	List<List<TheoreticalLesson>> theolenlist1=tofindtheolenExperlist(theoreticalLesson,departmenttag);//获取课内实验课（课内实验分配老师）
	
	List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
	if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
		theolenlist=theolenlist1;
	}else{
	//当搜索条件添加了课程名称时显示相关的记录
		if(departmenttag.equals("1")){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCteacher()!=null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
				if(theolenlist1.get(i).get(0).getCteacher()==null){
					if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							theolenlist.add(theolenlist1.get(i));
						}
					}
					else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
		if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
				theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
			for(int i=0;i<theolenlist1.size();i++){
				if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
						.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
						theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					theolenlist.add(theolenlist1.get(i));
				}	
			}
		}
		else{
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
					{
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
					&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
			{
				for(int i=0;i<theolenlist1.size();i++)
				{
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
	
	List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
	sslist=theolenlist.subList(0, totalCount);
	List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
	for(int i=0;i<sslist.size();i++){
		curriculumlist.add(sslist.get(i).get(0).getCurriculum());
	}
	
	pageBean.setCurriculumlist(curriculumlist);
	pageBean.setTheoreticalLessonlist(sslist);
	return pageBean;
}




private List<List<TheoreticalLesson>> tofindtheolenExperlist(TheoreticalLesson theoreticalLesson, String departmenttag) {
	List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
	List<Curriculum> list=theoreticalPlanDao.findcurrbycollegeExp(theoreticalLesson.getCurriculum().getCollege());//获取的全部课内课程
	
	for(int i=0;i<list.size();i++){
		//找到了课1对应的列表
		List<TheoreticalLesson> theolenlist=theoreticalPlanDao.findtheolenbycurr(list.get(i));
		List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
		List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
		//遍历课1的列表
		for(int j=0;j<theolenlist.size();j++){
			/*if(theolenlist.get(j).getCteacher()!=null){	
				//已分配课1放到theolenlist1
				theolenlist1.add(theolenlist.get(j));
			}
			else{
				//未分配课1放到theolenlist2
				//theolenlist.get(j).setDepartmenttag("-1");
				//this.getHibernateTemplate().update(theolenlist.get(j));
				theolenlist2.add(theolenlist.get(j));
			}*/
			if(theolenlist.get(j).getCteachDepartment() == null && theolenlist.get(j).getCteacher() == null && theolenlist.get(j).getExperiment() == null && theolenlist.get(j).getExperimenter() == null){
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

//以院管理员的身份找到老师的信息
public PageBean tofindUserByCollege(Integer currentpage,TheoreticalLesson theoreticalLesson, College college) {
	PageBean pageBean=new PageBean();
	List<User> userlist=new ArrayList<User>();
	List<User> userlist1=userDao.findUserByCollege(college);
	if(theoreticalLesson.getCteacher() != null)
	{	
		if(theoreticalLesson.getCteacher().getTnum() != null && !"".equals(theoreticalLesson.getCteacher().getTnum()) && theoreticalLesson.getCteacher().getUsername() != null && !"".equals(theoreticalLesson.getCteacher().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getTnum().equals(theoreticalLesson.getCteacher().getTnum())&&
						userlist1.get(i).getUsername().equals(theoreticalLesson.getCteacher().getUsername()))
				{
					userlist.add(userlist1.get(i));
				}
			}
		}
		else if(!"".equals(theoreticalLesson.getCteacher().getTnum()) && "".equals(theoreticalLesson.getCteacher().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getTnum().equals(theoreticalLesson.getCteacher().getTnum()))
				{
					userlist.add(userlist1.get(i));
				}
			}
		}
		else if("".equals(theoreticalLesson.getCteacher().getTnum()) && !"".equals(theoreticalLesson.getCteacher().getUsername()))
		{
			for(int i=0;i<userlist1.size();i++){
				if(userlist1.get(i).getUsername().equals(theoreticalLesson.getCteacher().getUsername()))
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


//把理论课分配给老师
public void toupdateTheolenByCollegeDirectUser(TheoreticalLesson theoreticalLesson, List<String> depart) 
{
	TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
	for(int i=0;i<depart.size();i++){
		Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
		if((professionalDao.get(depart.get(i)))!=null)
		{
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
		}
		else
		{
			Department department=departmentDao.get(depart.get(i));
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
		}
		if(theoreticalLen.getTeacher() == null ||(theoreticalLen.getTeacher().getTnum()!=(theoreticalLesson.getTeacher().getTnum()))){
			theoreticalLen.setTeacher(userDao.get(theoreticalLesson.getTeacher().getTnum()));
			theoreticalLen.setTeachDepartment(null);
		}
		
		theoreticalPlanDao.update(theoreticalLen);
	}
}
//新加功能
//把课内实验分配给专业(以院管理员身份进入)
public void goupdatedepart(TheoreticalLesson theoreticalLesson,List<String> newchoosedepartlist) {
	TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
	for(int i=0;i<newchoosedepartlist.size();i++){
		
		//获取课程列表
		Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
		if((professionalDao.get(newchoosedepartlist.get(i)))!=null)
		{
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(newchoosedepartlist.get(i)));
		}
		else
		{
			Department department=departmentDao.get(newchoosedepartlist.get(i));
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
		}
		if(theoreticalLen.getCteachDepartment() == null ||(theoreticalLen.getCteachDepartment().getDepartmentid()!=(theoreticalLesson.getCteachDepartment().getDepartmentid()))){
			theoreticalLen.setCteachDepartment(departmentDao.get(theoreticalLesson.getCteachDepartment().getDepartmentid()));
			theoreticalLen.setCteacher(null);
		}
		if(theoreticalLen.getExperiment() != null){
			theoreticalLen.setExperiment(null);
		}
		if(theoreticalLen.getExperimenter() != null){
			theoreticalLen.setExperimenter(null);
		}
		theoreticalPlanDao.update(theoreticalLen);
	}
}
//把课内实验分配给老师
public void toupdateTheolenByCollegeDirectUser1(TheoreticalLesson theoreticalLesson, List<String> depart) 
{
	TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
	for(int i=0;i<depart.size();i++){
		Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
		if((professionalDao.get(depart.get(i)))!=null)
		{
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
		}
		else
		{
			Department department=departmentDao.get(depart.get(i));
			theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
		}
		
		if(theoreticalLen.getCteacher() == null ||(theoreticalLen.getCteacher().getTnum()!=(theoreticalLesson.getCteacher().getTnum()))){
			theoreticalLen.setCteacher(userDao.get(theoreticalLesson.getCteacher().getTnum()));
			theoreticalLen.setCteachDepartment(null);
			theoreticalLen.setExperiment(null); 
			theoreticalLen.setExperimenter(null);
		}
		theoreticalPlanDao.update(theoreticalLen);	
	}
	
	
}


//下载课内实验课分配情况
	public void downInclassexperiment(PageBean pageBean){//下载
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
          //2.1 设置响应类型  
          response.setContentType("application/x-execl");  
          //2.2 设置以下载方式打开文件            
          response.setHeader("Content-Disposition", "attachment;filename="+new String(("课内实验分配课程.xls").getBytes("gb2312"),"ISO-8859-1") );
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
			
	        
	      List<List<TheoreticalLesson>> theoreticalLesson = pageBean.getTheoreticalLessonlist();
	      
	      int allrow = 0;
	      for(int i=0;i<theoreticalLesson.size();i++)
	      {
	    	   
	    	  for (int j =0;j<theoreticalLesson.get(i).size();j++)
	    	  {
	    		   Curriculum curriculum = theoreticalLesson.get(i).get(j).getCurriculum(); //获取课内实验列表
	    		  
	    		   TheoreticalLesson theoretical = theoreticalLesson.get(i).get(j);//获取每个课内实验分配的情况
	    		    		
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
		            if(theoretical.getProfessional()!= null){
		            	cell.setCellValue(theoretical.getDepartment().getDepartmentCname()+"("+theoretical.getProfessional().getProfessionalname()+")");  
		            }else{
		            	 cell.setCellValue(theoretical.getDepartment().getDepartmentCname()); 
		            }
		            cell.setCellStyle(style1);
		            
		            cell = row.createCell((short) 6);
		            if(theoretical.getCteacher() != null || theoretical.getCteachDepartment() != null || theoretical.getExperiment() != null || theoretical.getExperimenter() != null){
		            	 if(theoretical.getCteacher()!= null){
		            	      cell.setCellValue(theoretical.getCteacher().getUsername()+"("+"老师"+")");
		                 }
		            	 if(theoretical.getCteachDepartment() != null){
		            	     cell.setCellValue(theoretical.getCteachDepartment().getDepartmentCname());
		                 }
		            	 if(theoretical.getExperiment() != null){
		            	     cell.setCellValue(theoretical.getExperiment().getExperimentname());
		                 }
		            	 if(theoretical.getExperimenter() != null){
		            	      cell.setCellValue(theoretical.getExperimenter().getUsername()+"("+"实验员"+")");
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
	
	public PageBean findtheolen1(TheoreticalLesson theoreticalLesson, String departmenttag) {
		PageBean pageBean=new PageBean();
		//在pageBean中设置college使得条件查询可执行
		List<College> listcollege=new ArrayList<College>();
		College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
		listcollege.add(college);
		pageBean.setCollegelist(listcollege);
		//找到该学院所开课程在理论课列表中的所有相关记录
		List<List<TheoreticalLesson>> theolenlist1=findtheolenlist(theoreticalLesson,departmenttag);
		List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
		
		//根据条件进行判断
		//只有学院的时候显示所有
		if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&departmenttag.equals("0")){
			theolenlist=theolenlist1;
		}else{
		//当搜索条件添加了课程名称时显示相关的记录
			if(departmenttag.equals("1")){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getTeachDepartment()!=null){
						if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
						{
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
						{
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
								{
									theolenlist.add(theolenlist1.get(i));
								}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
								{
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
											theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
						if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								theolenlist.add(theolenlist1.get(i));
							}
						}
						else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
									theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
			if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
					theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
				for(int i=0;i<theolenlist1.size();i++){
					if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
							.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
							theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
						theolenlist.add(theolenlist1.get(i));
					}	
				}
			}
			else{
				if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
						&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
								.equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
				else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
						&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
				{
					for(int i=0;i<theolenlist1.size();i++){
						if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
								.equals(theoreticalLesson.getCurriculum().getCurriculumid())){
							theolenlist.add(theolenlist1.get(i));
						}	
					}
				}
			}
		  }
		}

		int totalCount=theolenlist.size();
		pageBean.setTotalCount(totalCount);
		
		List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
		sslist=theolenlist.subList(0, totalCount);
		List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
		for(int i=0;i<sslist.size();i++){
			curriculumlist.add(sslist.get(i).get(0).getCurriculum());
		}
		pageBean.setCurriculumlist(curriculumlist);
		pageBean.setTheoreticalLessonlist(sslist);
		return pageBean;
	}
	/*课内实验分配到实验室*/
	public void goupdateExpdepartment(TheoreticalLesson theoreticalLesson,List<String> newchoosedepartlist) {
		TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
		for(int i=0;i<newchoosedepartlist.size();i++){
			
			//获取课程列表
			Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
			if((professionalDao.get(newchoosedepartlist.get(i)))!=null)
			{
				theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(newchoosedepartlist.get(i)));
			}
			else
			{
				Department department=departmentDao.get(newchoosedepartlist.get(i));
				theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
			}
			if(theoreticalLen.getExperiment() == null ||(theoreticalLen.getExperiment().getExperimentid()!=(theoreticalLesson.getExperiment().getExperimentid()))){
				theoreticalLen.setExperiment(experimentDao.get(theoreticalLesson.getExperiment().getExperimentid()));
				theoreticalLen.setExperimenter(null);
				theoreticalLen.setCteacher(null);
				theoreticalLen.setCteachDepartment(null);
			}
			theoreticalPlanDao.update(theoreticalLen);
		}
	}
	//下载理论课分配情况
		public void downTheoryclass(PageBean pageBean){//下载
			try{
				HttpServletResponse response = ServletActionContext.getResponse();  
	          //2.1 设置响应类型  
	          response.setContentType("application/x-execl");  
	          //2.2 设置以下载方式打开文件            
	          response.setHeader("Content-Disposition", "attachment;filename="+new String(("理论课分配课程.xls").getBytes("gb2312"),"ISO-8859-1") );
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
				
		        
		      List<List<TheoreticalLesson>> theoreticalLesson = pageBean.getTheoreticalLessonlist();
		      
		      int allrow = 0;
		      for(int i=0;i<theoreticalLesson.size();i++)
		      {
		    	   
		    	  for (int j =0;j<theoreticalLesson.get(i).size();j++)
		    	  {
		    		   Curriculum curriculum = theoreticalLesson.get(i).get(j).getCurriculum(); //获取课内实验列表
		    		  
		    		   TheoreticalLesson theoretical = theoreticalLesson.get(i).get(j);//获取每个课内实验分配的情况
		    		    		
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
			            if(theoretical.getProfessional()!= null){
			            	cell.setCellValue(theoretical.getDepartment().getDepartmentCname()+"("+theoretical.getProfessional().getProfessionalname()+")");  
			            }else{
			            	 cell.setCellValue(theoretical.getDepartment().getDepartmentCname()); 
			            }
			            cell.setCellStyle(style1);
			            
			            cell = row.createCell((short) 6);
			            if(theoretical.getTeachDepartment()!= null || theoretical.getTeacher() != null){
			            	 if(theoretical.getTeachDepartment()!= null){
			            	      cell.setCellValue(theoretical.getTeachDepartment().getDepartmentCname());
			                 }
			            	 if(theoretical.getTeacher() != null){
			            	     cell.setCellValue(theoretical.getTeacher().getUsername());
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
		//------------------------------------------------系管理分配课内实验到老师--------------------------------------
				public PageBean findtheolenByDepartToTeacher(Integer currentpage,TheoreticalLesson theoreticalLesson, String usertag) {
					PageBean pageBean=new PageBean();
					//在pageBean中设置college使得条件查询可执行
					List<College> listcollege=new ArrayList<College>();
					College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
					listcollege.add(college);
					pageBean.setCollegelist(listcollege);
					
					
					List<Department> listdepart=new ArrayList<Department>();
					listdepart.add(departmentDao.get(theoreticalLesson.getTeachDepartment().getDepartmentid()));
					pageBean.setDepartmentlist(listdepart);
					
					theoreticalLesson.setTeachDepartment(listdepart.get(0));
					
					List<List<TheoreticalLesson>> theolenlist1=findtheolenlistByDepartTeacher(theoreticalLesson,usertag);
					List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
						
					//根据条件进行判断
					//只有学院的时候显示所有
					if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&usertag.equals("0")){
						theolenlist=theolenlist1;
					}else{
					//当搜索条件添加了课程名称时显示相关的记录
						if(usertag.equals("1")){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getCteacher()!=null){
									if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
									{
											if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
											{
												theolenlist.add(theolenlist1.get(i));
											}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
											{
												if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
														theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
								if(theolenlist1.get(i).get(0).getCteacher()==null){
									if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
						if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
										.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
									theolenlist.add(theolenlist1.get(i));
								}	
							}
						}
						else{
							if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
									&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								for(int i=0;i<theolenlist1.size();i++){
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
											.equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
							else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
									&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								for(int i=0;i<theolenlist1.size();i++){
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
											.equals(theoreticalLesson.getCurriculum().getCurriculumid())){
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
					List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
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
					
					pageBean.setCurriculumlist(curriculumlist);
					pageBean.setTheoreticalLessonlist(sslist);
					return pageBean;
				}
				public List<List<TheoreticalLesson>> findtheolenlistByDepartTeacher(TheoreticalLesson theoreticalLesson, String usertag){
					List<List<TheoreticalLesson>> theolen=new ArrayList<List<TheoreticalLesson>>();
					//找到当前任课系的所有理论课记录
					List<TheoreticalLesson> list=theoreticalPlanDao.findTheolenByTeacherDepartment(theoreticalLesson.getTeachDepartment());
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
						List<TheoreticalLesson> theolenlist1=new ArrayList<TheoreticalLesson>();
						List<TheoreticalLesson> theolenlist2=new ArrayList<TheoreticalLesson>();
						for(int j=0;j<list.size();j++){
							if(list.get(j).getCurriculum().equals(listcurr.get(i))&&list.get(j).getCteacher()!=null)
							{
								theolenlist1.add(list.get(j));
							}
							if(list.get(j).getCurriculum().equals(listcurr.get(i))&&list.get(j).getCteacher()==null)
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
					//System.out.println(theolen.get(0).get(0).getCurriculum().getCurriculumCname());
					return theolen;
				}


				public PageBean findTeacherByDepart(Integer currentpage,TheoreticalLesson theoreticalLesson, List<String> depart)
				{
					PageBean pageBean=new PageBean();
					College college=collegeDao.get(theoreticalLesson.getCurriculum().getCollege().getCollegeid());
					List<College> listcollege=new ArrayList<College>();
					listcollege.add(college);
					pageBean.setCollegelist(listcollege);
					Department depart1=departmentDao.get(theoreticalLesson.getCteachDepartment().getDepartmentid());
					List<Department> listdepart=new ArrayList<Department>();
					listdepart.add(depart1);
					pageBean.setDepartmentlist(listdepart);
					
				/*	List<User> listuser1=userDao.findUserByDepart(depart1);
					List<Department> listdepart=new ArrayList<Department>();
					listdepart.add(depart1);
					pageBean.setDepartmentlist(listdepart);*/
					
					List<User> listuser1=userDao.findTeacherByDepart(depart1);
					List<User> listuser=new ArrayList<User>();
					for(int i=0;i<listuser1.size();i++){
						if(theoreticalLesson.getTeacher()!=null)
						{
							if(theoreticalLesson.getTeacher().getTnum()!=null&&!"".equals(theoreticalLesson.getTeacher().getTnum())&&
									theoreticalLesson.getTeacher().getUsername()!=null&&!"".equals(theoreticalLesson.getTeacher().getUsername()))
							{
								if(theoreticalLesson.getTeacher().getTnum().equals(listuser1.get(i).getTnum())
										&&theoreticalLesson.getTeacher().getUsername().equals(listuser1.get(i).getUsername()))
								{
									listuser.add(listuser1.get(i));
								}
							}
							else if(!"".equals(theoreticalLesson.getTeacher().getTnum()) && "".equals(theoreticalLesson.getTeacher().getUsername()))
							{
								if(theoreticalLesson.getTeacher().getTnum().equals(listuser1.get(i).getTnum()))
								{
									listuser.add(listuser1.get(i));
								}
							}
							else if("".equals(theoreticalLesson.getTeacher().getTnum()) && !"".equals(theoreticalLesson.getTeacher().getUsername()))
							{
								if(theoreticalLesson.getTeacher().getUsername().equals(listuser1.get(i).getUsername()))
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

				
				public void updateTheolenByDepartToTeacher(TheoreticalLesson theoreticalLesson, List<String> depart) {
					TheoreticalLesson theoreticalLen=new TheoreticalLesson(); 
					for(int i=0;i<depart.size();i++){
						Curriculum curriculum=curriculumDao.get(theoreticalLesson.getCurriculum().getCurriculumid());
						if((professionalDao.get(depart.get(i)))!=null)
						{
							theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,professionalDao.get(depart.get(i)));
						}
						else
						{
							Department department=departmentDao.get(depart.get(i));
							theoreticalLen=theoreticalPlanDao.findTheoByCurrAndDepart(curriculum,department);
						}
						theoreticalLen.setCteacher(userDao.get(theoreticalLesson.getCteacher().getTnum()));
						theoreticalPlanDao.update(theoreticalLen);
					}
					
				}
		//下载实验课表
				public PageBean toExpfindtheolendirectteacher(Integer currentpage,TheoreticalLesson theoreticalLesson, String usertag) {
					PageBean pageBean=new PageBean();
					//在pageBean中设置college使得条件查询可执行
					List<College> listcollege=new ArrayList<College>();
					College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
					listcollege.add(college);
					pageBean.setCollegelist(listcollege);
					
					
					List<Department> listdepart=new ArrayList<Department>();
					listdepart.add(departmentDao.get(theoreticalLesson.getTeachDepartment().getDepartmentid()));
					pageBean.setDepartmentlist(listdepart);
					
					theoreticalLesson.setTeachDepartment(listdepart.get(0));
					
					List<List<TheoreticalLesson>> theolenlist1=findtheolenlistByDepartTeacher(theoreticalLesson,usertag);
					List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
						
					//根据条件进行判断
					//只有学院的时候显示所有
					if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&usertag.equals("0")){
						theolenlist=theolenlist1;
					}else{
					//当搜索条件添加了课程名称时显示相关的记录
						if(usertag.equals("1")){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getCteacher()!=null){
									if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
									{
											if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
											{
												theolenlist.add(theolenlist1.get(i));
											}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
											{
												if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
														theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
								if(theolenlist1.get(i).get(0).getCteacher()==null){
									if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
						if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
										.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
									theolenlist.add(theolenlist1.get(i));
								}	
							}
						}
						else{
							if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
									&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								for(int i=0;i<theolenlist1.size();i++){
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
											.equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
							else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
									&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								for(int i=0;i<theolenlist1.size();i++){
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
											.equals(theoreticalLesson.getCurriculum().getCurriculumid())){
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
						}
					}
					
					}

					int totalCount=theolenlist.size();
					pageBean.setTotalCount(totalCount);
					
					List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
					
					sslist=theolenlist.subList(0, totalCount);
					
					List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
					
					for(int i=0;i<sslist.size();i++){
						curriculumlist.add(sslist.get(i).get(0).getCurriculum());
					}
					
					pageBean.setCurriculumlist(curriculumlist);
					pageBean.setTheoreticalLessonlist(sslist);
					return pageBean;
				}
				//下载课内实验课分配情况
				public void downInexperiment(PageBean pageBean){//下载
					try{
						HttpServletResponse response = ServletActionContext.getResponse();  
			          //2.1 设置响应类型  
			          response.setContentType("application/x-execl");  
			          //2.2 设置以下载方式打开文件            
			          response.setHeader("Content-Disposition", "attachment;filename="+new String(("课内实验分配课程.xls").getBytes("gb2312"),"ISO-8859-1") );
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
						
				        
				      List<List<TheoreticalLesson>> theoreticalLesson = pageBean.getTheoreticalLessonlist();
				      
				      int allrow = 0;
				      for(int i=0;i<theoreticalLesson.size();i++)
				      {
				    	   
				    	  for (int j =0;j<theoreticalLesson.get(i).size();j++)
				    	  {
				    		   Curriculum curriculum = theoreticalLesson.get(i).get(j).getCurriculum(); //获取课内实验列表
				    		  
				    		   TheoreticalLesson theoretical = theoreticalLesson.get(i).get(j);//获取每个课内实验分配的情况
				    		    		
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
					            if(theoretical.getProfessional()!= null){
					            	cell.setCellValue(theoretical.getDepartment().getDepartmentCname()+"("+theoretical.getProfessional().getProfessionalname()+")");  
					            }else{
					            	 cell.setCellValue(theoretical.getDepartment().getDepartmentCname()); 
					            }
					            cell.setCellStyle(style1);
					            
					            cell = row.createCell((short) 6);
					            if(theoretical.getCteacher()!= null){
					            	      cell.setCellValue(theoretical.getCteacher().getUsername()+"("+"老师"+")");
					                 }
					            else{
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
		//--------------------------------------下载理论课表---------------------------------------
				public PageBean toExpfindtheolenteacher(Integer currentpage,TheoreticalLesson theoreticalLesson, String usertag) {
					PageBean pageBean=new PageBean();
					//在pageBean中设置college使得条件查询可执行
					List<College> listcollege=new ArrayList<College>();
					College college=findCollegeById(theoreticalLesson.getCurriculum().getCollege().getCollegeid()).get(0);
					listcollege.add(college);
					pageBean.setCollegelist(listcollege);
					
					
					List<Department> listdepart=new ArrayList<Department>();
					listdepart.add(departmentDao.get(theoreticalLesson.getTeachDepartment().getDepartmentid()));
					pageBean.setDepartmentlist(listdepart);
					
					theoreticalLesson.setTeachDepartment(listdepart.get(0));
					
					List<List<TheoreticalLesson>> theolenlist1=findtheolenlistByDepartUser(theoreticalLesson,usertag);
					List<List<TheoreticalLesson>> theolenlist=new ArrayList<List<TheoreticalLesson>>();
						
					//根据条件进行判断
					//只有学院的时候显示所有
					if(theoreticalLesson.getCurriculum().getCollege().getCollegeid()!=null&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&usertag.equals("0")){
						theolenlist=theolenlist1;
					}else{
					//当搜索条件添加了课程名称时显示相关的记录
						if(usertag.equals("1")){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getTeacher()!=null){
									if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
									{
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
									{
											if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
											{
												theolenlist.add(theolenlist1.get(i));
											}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
											{
												if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
														theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
									if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
										{
											theolenlist.add(theolenlist1.get(i));
										}
									}
									else if(!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
												theolenlist1.get(i).get(0).getCurriculum().getCurriculumid().equals(theoreticalLesson.getCurriculum().getCurriculumid()))
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
						if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())&&
								theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())){
							for(int i=0;i<theolenlist1.size();i++){
								if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
										.equals(theoreticalLesson.getCurriculum().getCurriculumid())&&
										theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname().equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
									theolenlist.add(theolenlist1.get(i));
								}	
							}
						}
						else{
							if(theoreticalLesson.getCurriculum().getCurriculumCname()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumCname())
									&&"".equals(theoreticalLesson.getCurriculum().getCurriculumid()))
							{
								for(int i=0;i<theolenlist1.size();i++){
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumCname()
											.equals(theoreticalLesson.getCurriculum().getCurriculumCname())){
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
							else if(theoreticalLesson.getCurriculum().getCurriculumid()!=null&&!"".equals(theoreticalLesson.getCurriculum().getCurriculumid())
									&&"".equals(theoreticalLesson.getCurriculum().getCurriculumCname()))
							{
								for(int i=0;i<theolenlist1.size();i++){
									if(theolenlist1.get(i).get(0).getCurriculum().getCurriculumid()
											.equals(theoreticalLesson.getCurriculum().getCurriculumid())){
										theolenlist.add(theolenlist1.get(i));
									}	
								}
							}
						}
					}
					
					}

					int totalCount=theolenlist.size();
					pageBean.setTotalCount(totalCount);
					
					List<List<TheoreticalLesson>> sslist=new ArrayList<List<TheoreticalLesson>>();
					
					sslist=theolenlist.subList(0, totalCount);
					
					List<Curriculum> curriculumlist=new ArrayList<Curriculum>();
					
					for(int i=0;i<sslist.size();i++){
						curriculumlist.add(sslist.get(i).get(0).getCurriculum());
					}			
					pageBean.setCurriculumlist(curriculumlist);
					pageBean.setTheoreticalLessonlist(sslist);
					return pageBean;
				}
				//下载理论课分配情况
						public void downIntheolen(PageBean pageBean){//下载
							try{
								HttpServletResponse response = ServletActionContext.getResponse();  
					          //2.1 设置响应类型  
					          response.setContentType("application/x-execl");  
					          //2.2 设置以下载方式打开文件            
					          response.setHeader("Content-Disposition", "attachment;filename="+new String(("理论课分配课程.xls").getBytes("gb2312"),"ISO-8859-1") );
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
								
						        
						      List<List<TheoreticalLesson>> theoreticalLesson = pageBean.getTheoreticalLessonlist();
						      
						      int allrow = 0;
						      for(int i=0;i<theoreticalLesson.size();i++)
						      {
						    	   
						    	  for (int j =0;j<theoreticalLesson.get(i).size();j++)
						    	  {
						    		   Curriculum curriculum = theoreticalLesson.get(i).get(j).getCurriculum(); //获取课内实验列表
						    		  
						    		   TheoreticalLesson theoretical = theoreticalLesson.get(i).get(j);//获取每个课内实验分配的情况
						    		    		
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
							            if(theoretical.getProfessional()!= null){
							            	cell.setCellValue(theoretical.getDepartment().getDepartmentCname()+"("+theoretical.getProfessional().getProfessionalname()+")");  
							            }else{
							            	 cell.setCellValue(theoretical.getDepartment().getDepartmentCname()); 
							            }
							            cell.setCellStyle(style1);
							            
							            cell = row.createCell((short) 6);
							            if(theoretical.getTeachDepartment()!= null || theoretical.getTeacher() != null){
							            	 if(theoretical.getTeachDepartment()!= null){
							            	      cell.setCellValue(theoretical.getTeachDepartment().getDepartmentCname());
							                 }
							            	 if(theoretical.getTeacher() != null){
							            	     cell.setCellValue(theoretical.getTeacher().getUsername());
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

	public void findtheolen3(Integer currentpage,
			TheoreticalLesson theoreticalLesson, String departmenttag) {
		// 在pageBean中设置college使得条件查询可执行
		// 找到学院开设的课程
		List<Curriculum> list = theoreticalPlanDao
				.findcurrbycollege(theoreticalLesson.getCurriculum()
						.getCollege());
		for (int i = 0; i < list.size(); i++) {
			// 找到了课1对应的列表
			List<TheoreticalLesson> theolenlist = theoreticalPlanDao
					.findtheolenbycurr(list.get(i));
			if (theolenlist.size() == 0) {

			} else if (theolenlist.size() == 1) {
				// 判断是否未分配
				if (theolenlist.get(0).getCteachDepartment() == null
						&& theolenlist.get(0).getCteacher() == null
						&& theolenlist.get(0).getExperiment() == null
						&& theolenlist.get(0).getExperimenter() == null) {
					String id = theolenlist.get(0).getDepartment().getCollege()
							.getCollegeid();
					if (theoreticalLesson.getCurriculum().getCollege()
							.getCollegeid().equals(id)) {
						TheoreticalLesson theoreticalLen = theolenlist.get(0);
						theoreticalLen.setCteachDepartment(theoreticalLen
								.getDepartment());
						theoreticalPlanDao.update(theoreticalLen);
					}
				}
			} else {
				int flag = 0;
				for (int k = 1; k < theolenlist.size(); k++) {
					if (!theolenlist.get(k).getDepartment()
							.equals(theolenlist.get(0).getDepartment())) {
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					for (int k = 0; k < theolenlist.size(); k++) {
						// 判断是否未分配
						if (theolenlist.get(k).getCteachDepartment() == null
								&& theolenlist.get(0).getCteacher() == null
								&& theolenlist.get(0).getExperiment() == null
								&& theolenlist.get(0).getExperimenter() == null) {
							String id = theolenlist.get(k).getDepartment()
									.getCollege().getCollegeid();
							if (theoreticalLesson.getCurriculum().getCollege()
									.getCollegeid().equals(id)) {
								TheoreticalLesson theoreticalLen = theolenlist
										.get(k);
								theoreticalLen
										.setCteachDepartment(theoreticalLen
												.getDepartment());
								theoreticalPlanDao.update(theoreticalLen);
							}
						}
					}
				}
			}
		}
	}
	//理论课大纲的是否标志
		public void upLoadFlag(TheoreticalLesson theoreticalLesson){
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(theoreticalLesson.getTheoreticalLessonid());
			newtheoreticalLesson.setIsDow(theoreticalLesson.getIsDow());
			theoreticalPlanDao.update(newtheoreticalLesson);
		}
		//课内实验大纲是否标志
		public void upLoadFlag1(TheoreticalLesson theoreticalLesson){
			TheoreticalLesson newtheoreticalLesson = theoreticalPlanDao.get(theoreticalLesson.getTheoreticalLessonid());
			newtheoreticalLesson.setIsDown(theoreticalLesson.getIsDown());
			theoreticalPlanDao.update(newtheoreticalLesson);
		}

		//备份以上方法
		public void toCheckTheoLesAdmin1(String tnum, TheoreticalLesson theoreticalLesson) {
			// TODO Auto-generated method stub
			findUserInfo(tnum);
			collegelist = collegeDao.findAll();
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
			
			List<TheoreticalLesson> allTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearch0(theoreticalLesson);//该老师所有应选大纲
			List<List<TheoreticalLesson>> professionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
			
			while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
				List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
				TheoreticalLesson theoLess = allTheoreticalLessonlist.get(0);
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
			
			
			List<TheoreticalLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearch0(theoreticalLesson);
			List<ApplicationSyllabus> applicationSyllabusList = applicationSyllabusDao.findhaveSyllabusList(newallTheoreticalLessonlist);//应用大纲的专业/方向

			List<List<TheoreticalLesson>> haveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				
				List<TheoreticalLesson> newhaveprofessionalList = new ArrayList<TheoreticalLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					TheoreticalLesson theoLess = professionalList.get(pi).get(pj);
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
			
			List<TheoreticalLesson> syllabusList = new ArrayList<TheoreticalLesson>();//合并
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
				
			
			ServletActionContext.getRequest().setAttribute("count", count);//专业方向数量
			ServletActionContext.getRequest().setAttribute("syllabusList", syllabusList);//理论课List
			ServletActionContext.getRequest().setAttribute("professionalList", professionalList);//所有未编写的大纲，嵌套
			
			List<TheoreticalLesson> haveSyllabusList = new ArrayList<TheoreticalLesson>();//合并
			
			
			//分开不同大纲ID
			List<List<TheoreticalLesson>> newhaveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<TheoreticalLesson> newTheoreticalLesson = new ArrayList<TheoreticalLesson>();
						TheoreticalLesson theo = haveprofessionalList.get(ri).get(0);
						String syid =theoreticalPlanDao.findSyllabusidByTheo(theo);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(theoreticalPlanDao.findSyllabusidByTheo(haveprofessionalList.get(ri).get(rj))))
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
		
			List<List<TheoreticalLesson>> newprofessionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				TheoreticalLesson newtheo = newhaveprofessionalList.get(hi).get(0);
				List<TheoreticalLesson> newallTheoreticalLesson = theoreticalPlanDao.getbyCurriculumAndDepartment(newtheo);//该老师所有应选大纲
		
				while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
					List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
					TheoreticalLesson theoLess = newallTheoreticalLesson.get(0);
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
					String syllabusid = theoreticalPlanDao.findSyllabusidByTheo(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus> appSyllaList = new ArrayList<ApplicationSyllabus>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findAppByTheo(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findSyllabusidByTheo(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
							{
								newprofessionalList.get(i).remove(newprofessionalList.get(i).get(j));
								j--;
							}
						}
					}
				}
				}
			}
			
			
			ServletActionContext.getRequest().setAttribute("haveCount", haveCount);//已经编写大纲的专业方向数量
			ServletActionContext.getRequest().setAttribute("haveSyllabusList", haveSyllabusList);
			ServletActionContext.getRequest().setAttribute("syllabusId", syllabusId);
			ServletActionContext.getRequest().setAttribute("haveprofessionalList", newhaveprofessionalList);//已经编写大纲的专业方向
			ServletActionContext.getRequest().setAttribute("newprofessionalList", newprofessionalList);//剔除掉其他专业方向的专业方向List
			
		}
		//备份以上内容
		public void toCheckTheoLesInnerExperimentAdmin1(String tnum,TheoreticalLesson theoreticalLesson) {
			// TODO Auto-generated method stub
			findUserInfo(tnum);
			collegelist = collegeDao.findAll();
				ServletActionContext.getRequest().setAttribute("collegelist", collegelist);	
			
			List<TheoreticalLesson> allTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearch_HaveHourOfExp0(theoreticalLesson);//该老师所有应选大纲
			List<List<TheoreticalLesson>> professionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
			
			while(allTheoreticalLessonlist != null && allTheoreticalLessonlist.size() != 0){//生成professionalList
				List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
				TheoreticalLesson theoLess = allTheoreticalLessonlist.get(0);
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
			
			
			List<TheoreticalLesson> newallTheoreticalLessonlist = theoreticalPlanDao.getbycollegeAndsearch_HaveHourOfExp0(theoreticalLesson);
			List<ApplicationSyllabus_TheoInnerExperiment> applicationSyllabusList_TheoInnerExperiment = applicationSyllabusDao.findhaveSyllabus_TheoInnerExperimentList(newallTheoreticalLessonlist);//应用大纲的专业/方向

			List<List<TheoreticalLesson>> haveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
			for(int pi = 0;pi<professionalList.size();pi++)
			{
				
				List<TheoreticalLesson> newhaveprofessionalList = new ArrayList<TheoreticalLesson>();
				for(int pj=0;pj<professionalList.get(pi).size();pj++)
				{
					TheoreticalLesson theoLess = professionalList.get(pi).get(pj);
					for(int pk=0;pk<applicationSyllabusList_TheoInnerExperiment.size();pk++)
					{
						ApplicationSyllabus_TheoInnerExperiment appSyll = applicationSyllabusList_TheoInnerExperiment.get(pk);
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
			
			List<TheoreticalLesson> syllabusList = new ArrayList<TheoreticalLesson>();//合并
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
			
			List<TheoreticalLesson> haveSyllabusList = new ArrayList<TheoreticalLesson>();//合并
			
			
			//分开不同大纲ID
			List<List<TheoreticalLesson>> newhaveprofessionalList = new ArrayList<List<TheoreticalLesson>>();//已经编写的大纲
			
			if(haveprofessionalList != null && haveprofessionalList.size() != 0)
			{
				for(int ri=0;ri<haveprofessionalList.size();ri++)
				{
					while(haveprofessionalList.get(ri) != null && haveprofessionalList.get(ri).size() != 0)
					{		
						List<TheoreticalLesson> newTheoreticalLesson = new ArrayList<TheoreticalLesson>();
						TheoreticalLesson theo = haveprofessionalList.get(ri).get(0);
						String syid =theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(theo);
						for(int rj=0;rj<haveprofessionalList.get(ri).size();rj++)
						{
							if(syid.equals(theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(haveprofessionalList.get(ri).get(rj))))
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
		
			List<List<TheoreticalLesson>> newprofessionalList = new ArrayList<List<TheoreticalLesson>>();//定义该老师所有未编写大纲
			for(int hi=0;hi<newhaveprofessionalList.size();hi++)
			{
				TheoreticalLesson newtheo = newhaveprofessionalList.get(hi).get(0);
				List<TheoreticalLesson> newallTheoreticalLesson = theoreticalPlanDao.getbyCurriculumAndDepartment(newtheo);//该老师所有应选大纲
		
				while(newallTheoreticalLesson != null && newallTheoreticalLesson.size() != 0){//生成professionalList
					List<TheoreticalLesson> theoreticalLessonList = new ArrayList<TheoreticalLesson>();
					TheoreticalLesson theoLess = newallTheoreticalLesson.get(0);
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
					String syllabusid = theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(haveSyllabusList.get(i));
					syllabusId.add(syllabusid);
				}
			}
			
			
			//--------剔除已完成大纲的专业方向
			List<ApplicationSyllabus_TheoInnerExperiment> appSyllaList = new ArrayList<ApplicationSyllabus_TheoInnerExperiment>();
			for(int i=0;i<newprofessionalList.size();i++){
				appSyllaList = applicationSyllabusDao.findAppByTheo_TheoInnerExperiment(newprofessionalList.get(i).get(0));
				if(appSyllaList != null && appSyllaList.size() != 0){
						
				for(int k=0;k<appSyllaList.size();k++)
				{
					for(int j=0;j<newprofessionalList.get(i).size();j++)
					{	
						if(newprofessionalList.get(i).get(j).getProfessional() != null && appSyllaList.get(k).getProfessional() != null)
						{
							if(newprofessionalList.get(i).get(j).getProfessional() == appSyllaList.get(k).getProfessional() && !theoreticalPlanDao.findSyllabusidByTheo_InnerExperiment(newprofessionalList.get(i).get(j)).equals(syllabusId.get(i)))
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

