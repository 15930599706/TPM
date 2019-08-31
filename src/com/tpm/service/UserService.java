package com.tpm.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.tpm.entity.AvePerThreshold;
import com.tpm.entity.College;
import com.tpm.entity.Curriculum;
import com.tpm.entity.Department;
import com.tpm.entity.Experiment;
import com.tpm.entity.ExperimentLog;
import com.tpm.entity.Notice;
import com.tpm.entity.PageBean;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.dao.AvePerThresholdDao;
import com.tpm.dao.CollegeDao;
import com.tpm.dao.DepartmentDao;
import com.tpm.dao.ExperimentDao;
import com.tpm.dao.ExperimentLogDao;
import com.tpm.dao.NoticeDao;
import com.tpm.dao.NoticeFileDao;
import com.tpm.dao.PracticePlanDao;
import com.tpm.dao.TheoreticalPlanDao;
import com.tpm.dao.UserDao;
import com.tpm.entity.User;

@Transactional
public class UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	private CollegeDao collegeDao;
	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	private TheoreticalPlanDao theoreticalPlanDao;
	public void setTheoreticalPlanDao(TheoreticalPlanDao theoreticalPlanDao) {
		this.theoreticalPlanDao = theoreticalPlanDao;
	}
	private PracticePlanDao practicePlanDao;
	public void setPracticePlanDao(PracticePlanDao practicePlanDao) {
		this.practicePlanDao = practicePlanDao;
	}
	private AvePerThresholdDao avePerThresholdDao;
	public void setAvePerThresholdDao(AvePerThresholdDao avePerThresholdDao) {
		this.avePerThresholdDao = avePerThresholdDao;
	}
	private ExperimentDao experimentDao;
	public void setExperimentDao(ExperimentDao experimentDao) {
		this.experimentDao = experimentDao;
	}
	private ExperimentLogDao experimentLogDao;
	public void setExperimentLogDao(ExperimentLogDao experimentLogDao) {
		this.experimentLogDao = experimentLogDao;
	}

	public String login(User user) {
		if(user.getTnum().equals("请输入您的职工号")){
			ActionContext context=ActionContext.getContext();			
			context.put("error", "职工号不能为空");			
			return "login";
		}else if(user.getPassword().equals("请输入您的密码")){
			ActionContext context=ActionContext.getContext();			
			context.put("error", "密码不能为空");
			return "login";
		}else{
			User userExist = userDao.login(user);
			if(userExist != null){//登录成功
				//使用session保持你的登录状态
				HttpServletRequest request = ServletActionContext.getRequest();
				request.getSession().setAttribute("user", userExist);
				return "loginsuccess";
			}else{//登录失败
				ActionContext context=ActionContext.getContext();			
					context.put("error", "请输入正确的职工号和密码");			
				return "login";
			}
		}
	}

	public void editpwd(User user, String oldpwd, String newpwd) {
		User usernew = userDao.get(user.getTnum());
		if(usernew.getPassword().equals(oldpwd)){
			usernew.setPassword(newpwd);
			userDao.update(usernew);
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("user", usernew);
			ActionContext context=ActionContext.getContext();			
			context.put("msg", "修改成功！");
		}else{
			ActionContext context=ActionContext.getContext();			
			context.put("msg", "原密码错误！");
		}
	}



	public void importUserExcel(String path) {
		boolean flag=true; 
		 try {  //上传的是xls的文件
			    //文件流指向excel文件  //
			 	FileInputStream fin=new FileInputStream(path);  
			    HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄  
				HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importUExcel(totalRow,sheet);
		 }
		 catch (FileNotFoundException e)
		 {  
			    flag=false;  
			    e.printStackTrace();  
		  } 
		 catch(IOException ex)
		  {  
			    flag=false;  
			    ex.printStackTrace();  
		  }catch(Exception ea) {
			  try{//上传的是xlsx的文件
			  	FileInputStream fin=new FileInputStream(path);
			  	XSSFWorkbook workbook = new XSSFWorkbook(fin);
			  	XSSFSheet sheet = workbook.getSheetAt(0);//得到工作表  
			    int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
			    flag = importUExcel(totalRow,sheet);
			  }catch (FileNotFoundException e)
				 {  
				    flag=false;  
				    e.printStackTrace();  
			  } 
			 catch(IOException ex)
			  {  
				    flag=false;  
				    ex.printStackTrace();  
			  }
			 catch(NullPointerException nullPoint){
				 	flag=false;  
				 	nullPoint.printStackTrace();  
			  }
			 catch(OLE2NotOfficeXmlFileException notXml){
				 	flag=false;  
				 	notXml.printStackTrace();  
			 }
			 catch(POIXMLException isDocx){
				  	flag=false;  
				  	isDocx.printStackTrace(); 
		  }
			    
		  }
		 if(flag){
			 ActionContext context=ActionContext.getContext();			
				context.put("msg", "导入成功");	
		 }else{
			 ActionContext context=ActionContext.getContext();			
				context.put("msg", "请导入正确格式的Excel文件");
		 }
	}
	public void exportUserModelExcel() {
		try{
			HttpServletResponse response = ServletActionContext.getResponse();  
	        //2.1 设置响应类型  
	        response.setContentType("application/x-execl");  
	            //2.2 设置以下载方式打开文件  
	        response.setHeader("Content-Disposition", "attachment;filename="+new String("用户信息模板.xls".getBytes("gb2312"),"ISO-8859-1") );
	        OutputStream outputStream = response.getOutputStream();
	        ExportExcel(outputStream);
	        if(outputStream != null){
	        	outputStream.close();  
	        }}catch (Exception e) {  
	            e.printStackTrace();  
	        }
	}


	
	
	
	
	
	
	
	
	
	
	

	private void ExportExcel(OutputStream outputStream) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth((short) 0, (short) 1800);
        sheet.setColumnWidth((short) 1, (short) 1200);
        sheet.setColumnWidth((short) 2, (short) 1200);
        sheet.setColumnWidth((short) 3, (short) 3500);
        sheet.setColumnWidth((short) 4, (short) 2900);
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("职工号");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("姓名");  
        cell.setCellStyle(style1);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("性别");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("部门（学院）");  
        cell.setCellStyle(style1); 
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("科室（系）");  
        cell.setCellStyle(style1);
        
        row = sheet.createRow((int) 1);
        HSSFCellStyle style2 = wb.createCellStyle();  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
/*        cell = row.createCell((short) 0);  
        cell.setCellValue("123456");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 1);  
        cell.setCellValue("测试");  
        cell.setCellStyle(style2);  
  
        cell = row.createCell((short) 2);  
        cell.setCellValue("男");  
        cell.setCellStyle(style2); 
        
        cell = row.createCell((short) 3);  
        cell.setCellValue("机械工程学院");  
        cell.setCellStyle(style2);
        
        cell = row.createCell((short) 4);  
        cell.setCellValue("包装工程");  
        cell.setCellStyle(style2);*/
        wb.write(outputStream);
        outputStream.flush();  
        outputStream.close();
	}
	private boolean importUExcel(int totalRow, XSSFSheet sheet) {
		XSSFRow row = null;//对应excel的行  
	    XSSFCell cell = null;//对应excel的列
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	User user = new User();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	user.setTnum(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	user.setUsername(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	user.setSex(cell.getRichStringCellValue().toString());
		    	user.setPassword("123456");
		    	user.setAdminlevel(0);
		    	cell=row.getCell(3);
		    	changetostring(cell);
		    	String collegename = cell.getRichStringCellValue().toString();
		    	College college = collegeDao.getbyname(collegename);
		    	if(college == null){
		    		college = collegeDao.get("00");
		    	}else{
			    	cell=row.getCell(4);
			    	changetostring(cell);
			    	String departmentname = cell.getRichStringCellValue().toString();
			    	Department department = departmentDao.getbyname(departmentname);
			    	if(department != null){
				    	if(college == department.getCollege()){
				    		user.setDepartment(department);
				    	}
			    	}
			    }
		    	user.setCollege(college);
		    	User newuser = userDao.get(user.getTnum());
	    		if(newuser == null){
	    			userDao.add(user);
	    		}else{
	    			newuser.setUsername(user.getUsername());
	    			newuser.setSex(user.getSex());
	    			if(newuser.getAdminlevel() != 5)
	    			{
	    				newuser.setAdminlevel(user.getAdminlevel());
	    			}
	    			newuser.setCollege(user.getCollege());
	    			newuser.setDepartment(user.getDepartment());
	    			userDao.update(newuser);
	    		}
		    }
		    return true;
	    }else{
	    	return false;
	    }
	}
	private void changetostring(XSSFCell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
	}
	private boolean checkExcel(XSSFSheet sheet) {
		XSSFRow row=sheet.getRow(0);
		if(row.getCell(0).getRichStringCellValue().toString().equals("职工号")
				&&row.getCell(1).getRichStringCellValue().toString().equals("姓名")
				&&row.getCell(2).getRichStringCellValue().toString().equals("性别")
				&&row.getCell(3).getRichStringCellValue().toString().equals("部门（学院）")
				&&row.getCell(4).getRichStringCellValue().toString().equals("科室（系）")){
			return true;
		}else{return false;}
	}
	private boolean importUExcel(int totalRow, HSSFSheet sheet) {
		HSSFRow row = null;//对应excel的行  
	    HSSFCell cell = null;//对应excel的列   
	    boolean ok =  checkExcel(sheet);
	    if(ok){
		    for(int i=1;i<=totalRow;i++)
		    {
		    	User user = new User();
		    	row=sheet.getRow(i);
		    	cell=row.getCell(0);
		    	changetostring(cell);
		    	user.setTnum(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(1);
		    	changetostring(cell);
		    	user.setUsername(cell.getRichStringCellValue().toString());
		    	cell=row.getCell(2);
		    	changetostring(cell);
		    	user.setSex(cell.getRichStringCellValue().toString());
		    	user.setPassword("123456");
		    	user.setAdminlevel(0);
		    	cell=row.getCell(3);
		    	changetostring(cell);
		    	String collegename = cell.getRichStringCellValue().toString();
		    	College college = collegeDao.getbyname(collegename);
		    	if(college == null){
		    		college = collegeDao.get("00");
		    	}else{
			    	cell=row.getCell(4);
			    	changetostring(cell);
			    	String departmentname = cell.getRichStringCellValue().toString();
			    	Department department = departmentDao.getbyname(departmentname);
			    	if(department != null){
				    	if(college == department.getCollege()){
				    		user.setDepartment(department);
				    	}
			    	}
			    }
		    	user.setCollege(college);
		    	User newuser = userDao.get(user.getTnum());
	    		if(newuser == null){
	    			userDao.add(user);
	    		}else{
	    			newuser.setUsername(user.getUsername());
	    			newuser.setSex(user.getSex());
	    			if(newuser.getAdminlevel() != 5)
	    			{
	    				newuser.setAdminlevel(user.getAdminlevel());
	    			}
	    			newuser.setCollege(user.getCollege());
	    			newuser.setDepartment(user.getDepartment());
	    			userDao.update(newuser);
	    		}
		    }
		    return true;
	    }else{
	    	return false;
	    }
	}
	private void changetostring(HSSFCell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
	}
	private boolean checkExcel(HSSFSheet sheet) {
		HSSFRow row=sheet.getRow(0);
		if(row.getCell(0).getRichStringCellValue().toString().equals("职工号")
				&&row.getCell(1).getRichStringCellValue().toString().equals("姓名")
				&&row.getCell(2).getRichStringCellValue().toString().equals("性别")
				&&row.getCell(3).getRichStringCellValue().toString().equals("部门（学院）")
				&&row.getCell(4).getRichStringCellValue().toString().equals("科室（系）")){
			return true;
		}else{return false;}
	}



	public User getuser(String tnum) {
		return userDao.get(tnum);
	}
	public void tjdl(User user) {
		User newuser = userDao.get(user.getTnum());
		if(newuser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
		}
		if(newuser.getAdminlevel() == 3){
			College college = collegeDao.get(newuser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			List<Department> departmentlist = departmentDao.findTbyCollege( college);
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
			ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
		}
		if(newuser.getAdminlevel() == 1){
			College college = collegeDao.get(newuser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			Department department = departmentDao.get(newuser.getDepartment().getDepartmentid());
			List<Department> departmentlist = departmentDao.getone(department.getDepartmentid());
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
			ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
		}
	}
	public Integer adduser(User user) {
		Integer count = 0;
		User newUser = userDao.get(user.getTnum());
		if(newUser == null){
			userDao.add(user);
		}else{
			count = 1;
		}
		return count;
	}
	public void changeuser(User user) {
		userDao.update(user);
	}
	public String deluser(User user) {
		List<TheoreticalLesson> theoreticalLessonlist = theoreticalPlanDao.getbyuser(user);
		List<TheoreticalLesson> theoreticalLessonlist_experiment = theoreticalPlanDao.getbyexperimenter(user);//实验员
		List<PracticeLesson> practiceLessonlist = practicePlanDao.findPracticebyuser(user);
		if(theoreticalLessonlist.size() == 0 && practiceLessonlist.size() == 0 && theoreticalLessonlist_experiment.size() == 0){
			userDao.delete(user);
			return "yes";
		}
		else{
			return "no";
		}
			
/*			HttpServletResponse response = ServletActionContext.getResponse();
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
	}
	public void toshcooladmin(User user) {
		User newUser = userDao.get(user.getTnum());
		newUser.setAdminlevel(5);
		userDao.update(newUser);
	}
	public void tocollegeadmin(User user) {
		User newUser = userDao.get(user.getTnum());
		newUser.setAdminlevel(3);
		userDao.update(newUser);
	}
	public void todepartmentadmin(User user) {
		User newUser = userDao.get(user.getTnum());
		newUser.setAdminlevel(1);
		userDao.update(newUser);
	}
	public void toeasyuser(User user) {
		User newUser = userDao.get(user.getTnum());
		newUser.setAdminlevel(0);
		userDao.update(newUser);
	}
	public PageBean dlzh(User user, Integer currentpage) {
		User newUser = userDao.get(user.getTnum());
		return pagebean(currentpage,user,newUser);
	}
	private PageBean pagebean(Integer currentpage, User user, User newuser) {
		PageBean pageBean = new PageBean();
		if(newuser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		if(newuser.getAdminlevel() == 3){
			College college = collegeDao.get(newuser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			List<Department> departmentlist = departmentDao.findTbyCollege( college);
			pageBean.setCollegelist(collegelist);
			pageBean.setDepartmentlist(departmentlist);
		}
		if(newuser.getAdminlevel() == 1){
			College college = collegeDao.get(newuser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			Department department = departmentDao.get(newuser.getDepartment().getDepartmentid());
			List<Department> departmentlist = departmentDao.getone(department.getDepartmentid());
			pageBean.setCollegelist(collegelist);
			pageBean.setDepartmentlist(departmentlist);
		}
		int totalCount = userDao.finduserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.finduserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}
	public void bjdl(User user) {
		User newuser = userDao.get(user.getTnum());
		if(user.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
			if(newuser.getDepartment() != null && newuser.getDepartment().getDepartmentid() != null && !("").equals(newuser.getDepartment().getDepartmentid())){
				List<Department> departmentlist = departmentDao.findTbyCollege(newuser.getCollege());
				ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
			}
		}
		if(user.getAdminlevel() == 3){
			College college = collegeDao.get(newuser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			List<Department> departmentlist = departmentDao.findTbyCollege( college);
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
			ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
		}
		if(user.getAdminlevel() == 1){
			College college = collegeDao.get(newuser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			Department department = departmentDao.get(newuser.getDepartment().getDepartmentid());
			List<Department> departmentlist = departmentDao.getone(department.getDepartmentid());
			ServletActionContext.getRequest().setAttribute("collegelist", collegelist);
			ServletActionContext.getRequest().setAttribute("departmentlist", departmentlist);
		}
		ServletActionContext.getRequest().setAttribute("users", newuser);
	}
	public void updateuser(User user) {
		User newuser = userDao.get(user.getTnum());
		newuser.setUsername(user.getUsername());
		newuser.setSex(user.getSex());
		newuser.setCollege(user.getCollege());
		if(user.getDepartment() == null || user.getDepartment().getDepartmentid().equals("-1"))
		{
			newuser.setDepartment(null);
		}
		else
		{
			newuser.setDepartment(user.getDepartment());
		}
		
		newuser.setAdminlevel(user.getAdminlevel());
		userDao.update(newuser);		
	}
	public PageBean xxgl(User user, Integer currentpage) {
		return pagebeanxxgl(currentpage,user);
	}
	private PageBean pagebeanxxgl(Integer currentpage, User user) {
		PageBean pageBean = new PageBean();
		List<College> collegelist = collegeDao.findAll();
		pageBean.setCollegelist(collegelist);
		int totalCount = userDao.findxxgluserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findxxgluserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}
	public PageBean tjxx(User user, Integer currentpage) {
		return pagebeantjxx(currentpage,user);
	}
	private PageBean pagebeantjxx(Integer currentpage, User user) {
		PageBean pageBean = new PageBean();
		List<College> collegelist = collegeDao.findAll();
		pageBean.setCollegelist(collegelist);
		int totalCount = userDao.findtjxxuserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findtjxxuserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}
	public PageBean xygl(User user, Integer currentpage) {	//学院员管理
		User newUser = userDao.get(user.getTnum());
		return pagebeanxygl(currentpage,user,newUser);
	}
	private PageBean pagebeanxygl(Integer currentpage, User user, User newUser) {
		PageBean pageBean = new PageBean();
		if(newUser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		if(newUser.getAdminlevel() == 3){
			College college = collegeDao.get(newUser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			List<Department> departmentlist = departmentDao.findTbyCollege( college);
			pageBean.setCollegelist(collegelist);
			pageBean.setDepartmentlist(departmentlist);
		}
		int totalCount = userDao.findxygluserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findxygluserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}
	public PageBean tjxy(User user, Integer currentpage) {
		User newUser = userDao.get(user.getTnum());
		return pagebeantjxy(currentpage,user,newUser);
	}
	private PageBean pagebeantjxy(Integer currentpage, User user, User newUser) {
		PageBean pageBean = new PageBean();
		if(newUser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		if(newUser.getAdminlevel() == 3){
			College college = collegeDao.get(newUser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			List<Department> departmentlist = departmentDao.findTbyCollege( college);
			pageBean.setCollegelist(collegelist);
			pageBean.setDepartmentlist(departmentlist);
		}
		int totalCount = userDao.findtjxyuserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findtjxyuserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}
	
	public PageBean xgl(User user, Integer currentpage) {
		User newUser = userDao.get(user.getTnum());
		return pagebeanxgl(currentpage,user,newUser);
	}
	private PageBean pagebeanxgl(Integer currentpage, User user, User newUser) {
		PageBean pageBean = new PageBean();
		if(newUser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		if(newUser.getAdminlevel() == 3){
			College college = collegeDao.get(newUser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			List<Department> departmentlist = departmentDao.findTbyCollege( college);
			pageBean.setCollegelist(collegelist);
			pageBean.setDepartmentlist(departmentlist);
		}
		if(newUser.getAdminlevel() == 1){
			College college = collegeDao.get(newUser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			Department department = departmentDao.get(newUser.getDepartment().getDepartmentid());
			List<Department> departmentlist = departmentDao.getone(department.getDepartmentid());
			pageBean.setCollegelist(collegelist);
			pageBean.setDepartmentlist(departmentlist);
		}
		int totalCount = userDao.findxgluserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findxgluserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}
	public PageBean tjx(User user, Integer currentpage) {
		User newUser = userDao.get(user.getTnum());
		return pagebeantjx(currentpage,user,newUser);
	}
	private PageBean pagebeantjx(Integer currentpage, User user, User newUser) {
		PageBean pageBean = new PageBean();
		if(newUser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		if(newUser.getAdminlevel() == 3){
			College college = collegeDao.get(newUser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			List<Department> departmentlist = departmentDao.findTbyCollege( college);
			pageBean.setCollegelist(collegelist);
			pageBean.setDepartmentlist(departmentlist);
		}
		if(newUser.getAdminlevel() == 1){
			College college = collegeDao.get(newUser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
			Department department = departmentDao.get(newUser.getDepartment().getDepartmentid());
			List<Department> departmentlist = departmentDao.getone(department.getDepartmentid());
			pageBean.setCollegelist(collegelist);
			pageBean.setDepartmentlist(departmentlist);
		}
		int totalCount = userDao.findtjxuserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findtjxuserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}
	
	/*********实验室主任****************************/
	public PageBean syszrgl(User user, Integer currentpage) {		//实验室主任管理
		User newUser = userDao.get(user.getTnum());
		return pagebeansyszr(currentpage,user,newUser);
	}
	private PageBean pagebeansyszr(Integer currentpage, User user, User newUser) {
		PageBean pageBean = new PageBean();
		if(newUser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		if(newUser.getAdminlevel() == 3){
			College college = collegeDao.get(newUser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
		//	List<Department> departmentlist = departmentDao.findTbyCollege( college);
			pageBean.setCollegelist(collegelist);
		//	pageBean.setDepartmentlist(departmentlist);
		}
		int totalCount = userDao.findsyszruserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findsyszruserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/*
	 方法1
	Calendar calendar = Calendar.getInstance();
	String time1 = dateFormat.format(calendar.getTime());
	方法2
	String time2 = dateFormat.format(new Date());
	*/
	
	public void toeasyexperimentAdmin(String modifyid, User user) {//删除实验主任
		User modify = userDao.get(modifyid);
		User newUser = userDao.get(user.getTnum());
		
		ExperimentLog experimentLog = new ExperimentLog();
		experimentLog.setUser_modify(modify);//修改者
		experimentLog.setUser_modified(newUser);//被修改者
		experimentLog.setExperiment_before(newUser.getExperiment());//修改前所在实验室
		experimentLog.setExperimenterlevel_before(newUser.getExperimenterlevel());//修改前实验员级别
		experimentLog.setOperate("删除");//修改操作
	//	experimentLog.setTime(dateFormat.format(calendar.getTime()));//操作时间
		experimentLog.setTime(dateFormat.format(new Date()));//操作时间
		
		experimentLog.setSign("3");//操作标记
		experimentLogDao.add(experimentLog);
		
		newUser.setExperiment(null);
		newUser.setExperimenterlevel(null);	
		userDao.update(newUser);	
	}
	
	public PageBean tjsyszr(User user, Integer currentpage) {//添加实验室主任
	//	User newUser = userDao.get(user.getTnum());
		Experiment experiment = experimentDao.get(user.getExperiment().getExperimentid());
		ServletActionContext.getRequest().setAttribute("experiment", experiment);
		return pagebeantjsyszr(currentpage,user);
	}
	private PageBean pagebeantjsyszr(Integer currentpage, User user) {
		PageBean pageBean = new PageBean();
		
		College college = collegeDao.get(user.getCollege().getCollegeid());
		List<College> collegelist = collegeDao.getone(college.getCollegeid());
		
		pageBean.setCollegelist(collegelist);
		
		
		int totalCount = userDao.findtjsyszruserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findtjsyszruserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}

	public void toexperimentadmin(String modifyid, User user) {	//设置为实验室主任
		User modify = userDao.get(modifyid);
		User newUser = userDao.get(user.getTnum());
		Experiment experiment = experimentDao.get(user.getExperiment().getExperimentid());
		
		
		ExperimentLog experimentLog = new ExperimentLog();
		experimentLog.setUser_modify(modify);//修改者
		experimentLog.setUser_modified(newUser);//被修改者
		experimentLog.setExperiment_before(newUser.getExperiment());//修改前所在实验室
		experimentLog.setExperimenterlevel_before(newUser.getExperimenterlevel());//修改前实验员级别
		
		experimentLog.setExperiment_after(experiment);//修改后所在实验室
		experimentLog.setExperimenterlevel_after(3);//修改后实验员级别
		
		experimentLog.setOperate("修改");//修改操作
	//	experimentLog.setTime(dateFormat.format(calendar.getTime()));//操作时间
		experimentLog.setTime(dateFormat.format(new Date()));//操作时间
		experimentLog.setSign("3");//操作标记
		experimentLogDao.add(experimentLog);
				
		
		newUser.setExperiment(experiment);
		newUser.setExperimenterlevel(3);
		userDao.update(newUser);
		
	}
	public PageBean tosysrzAdmin(Integer currentpage) {	//查看实验室主任修改日志
		PageBean pageBean = new PageBean();
		
		/*College college = collegeDao.get(user.getCollege().getCollegeid());
		List<College> collegelist = collegeDao.getone(college.getCollegeid());		
		pageBean.setCollegelist(collegelist);*/
				
		int totalCount = experimentLogDao.findsysrzAdminCount();
		int pageSize = 20;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<ExperimentLog> experimentLoglist = experimentLogDao.findsysrzAdminT(begin, pageSize);
		pageBean.setExperimentLogList(experimentLoglist);
		return pageBean;
		
	}
	/*********实验室主任****************************/
	/*********实验员****************************/
	public PageBean syygl(User user, Integer currentpage) {		//实验员管理
		User newUser = userDao.get(user.getTnum());
		return pagebeansyy(currentpage,user,newUser);
	}
	private PageBean pagebeansyy(Integer currentpage, User user, User newUser) {
		PageBean pageBean = new PageBean();
		if(newUser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		if(newUser.getAdminlevel() == 3){
			College college = collegeDao.get(newUser.getCollege().getCollegeid());
			List<College> collegelist = collegeDao.getone(college.getCollegeid());
		//	List<Department> departmentlist = departmentDao.findTbyCollege( college);
			pageBean.setCollegelist(collegelist);
		//	pageBean.setDepartmentlist(departmentlist);
		}
		int totalCount = userDao.findsyyuserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findsyyuserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}

	public void toeasyexperimenter(String modifyid, User user) {//删除实验员
		User modify = userDao.get(modifyid);
		User newUser = userDao.get(user.getTnum());
		
		ExperimentLog experimentLog = new ExperimentLog();
		experimentLog.setUser_modify(modify);//修改者
		experimentLog.setUser_modified(newUser);//被修改者
		experimentLog.setExperiment_before(newUser.getExperiment());//修改前所在实验室
		experimentLog.setExperimenterlevel_before(newUser.getExperimenterlevel());//修改前实验员级别
		experimentLog.setOperate("删除");//修改操作
	//	experimentLog.setTime(dateFormat.format(calendar.getTime()));//操作时间
		experimentLog.setTime(dateFormat.format(new Date()));//操作时间
		experimentLog.setSign("1");//操作标记
		experimentLogDao.add(experimentLog);
		
		newUser.setExperiment(null);
		newUser.setExperimenterlevel(null);
		userDao.update(newUser);	
	}
	public PageBean tjsyy(User user, Integer currentpage) {//添加实验员
	//	User newUser = userDao.get(user.getTnum());
		Experiment experiment = experimentDao.get(user.getExperiment().getExperimentid());
		ServletActionContext.getRequest().setAttribute("experiment", experiment);
		return pagebeantjsyy(currentpage,user);
	}
	private PageBean pagebeantjsyy(Integer currentpage, User user) {
		PageBean pageBean = new PageBean();
		
		College college = collegeDao.get(user.getCollege().getCollegeid());
		List<College> collegelist = collegeDao.getone(college.getCollegeid());
		pageBean.setCollegelist(collegelist);
		int totalCount = userDao.findtjsyyuserCount(user);
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount%pageSize == 0){
			totalPage = totalCount/pageSize;
		}else{
			totalPage = totalCount/pageSize+1;
		}
		if(currentpage < 1){
			currentpage = 1;
		}
		if(currentpage > totalPage){
			currentpage = totalPage;
		}
		pageBean.setCurrentpage(currentpage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		int begin = (currentpage-1)*pageSize;
		List<User> userlist = userDao.findtjsyyuserT(begin, pageSize,user);
		pageBean.setUserlist(userlist);
		return pageBean;
	}

	public void toexperimenter(String modifyid, User user) {	//设置为实验员
		User modify = userDao.get(modifyid);
		User newUser = userDao.get(user.getTnum());
		Experiment experiment = experimentDao.get(user.getExperiment().getExperimentid());
		
		
		ExperimentLog experimentLog = new ExperimentLog();
		experimentLog.setUser_modify(modify);//修改者
		experimentLog.setUser_modified(newUser);//被修改者
		experimentLog.setExperiment_before(newUser.getExperiment());//修改前所在实验室
		experimentLog.setExperimenterlevel_before(newUser.getExperimenterlevel());//修改前实验员级别
		
		experimentLog.setExperiment_after(experiment);//修改后所在实验室
		experimentLog.setExperimenterlevel_after(1);//修改后实验员级别
		
		experimentLog.setOperate("修改");//修改操作
	//	experimentLog.setTime(dateFormat.format(calendar.getTime()));//操作时间
		experimentLog.setTime(dateFormat.format(new Date()));//操作时间
		experimentLog.setSign("1");//操作标记
		experimentLogDao.add(experimentLog);
		
		
		newUser.setExperiment(experiment);
		newUser.setExperimenterlevel(1);
		userDao.update(newUser);		
	}
	
	
	/*********实验员****************************/
	
	
	public List<Department> finddepartment(String departmentid) {
		
		return departmentDao.getone(departmentid);
	}

	public PageBean togetInPage(User user) {
		User newUser = userDao.get(user.getTnum());
		PageBean pageBean = new PageBean();
		if(newUser.getAdminlevel() == 5){
			List<College> collegelist = collegeDao.findAll();
			pageBean.setCollegelist(collegelist);
		}
		//平均周学时
		AvePerThreshold avePerThreshold = avePerThresholdDao.findAvePer();
		if(avePerThreshold != null){
			ServletActionContext.getRequest().setAttribute("weekHour", avePerThreshold.getWeekHour());
		}
		return pageBean;
	}
	
	public void initPassword(User user,String initpwd) {

		// TODO Auto-generated method stub
		//获取当前要修改的对象
		User inituser = userDao.get(user.getTnum());		
		//设置要修改的属性
		initpwd = "123456";
		inituser.setPassword(initpwd);
		userDao.update(inituser);
		ActionContext context=ActionContext.getContext();			
		context.put("msg", "密码重置成功！");
		ServletActionContext.getRequest().setAttribute("tag", "init_todlzhPage");
	}






}
