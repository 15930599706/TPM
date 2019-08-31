package com.tpm.dao;

import java.util.List;

import com.tpm.entity.College;
import com.tpm.entity.Department;
import com.tpm.entity.MainTainOfPTTag;
import com.tpm.entity.PTBasicInformation;
import com.tpm.entity.PracticeLesson;
import com.tpm.entity.Ability;
import com.tpm.entity.Professional;
import com.tpm.entity.ScoreThreshold;
import com.tpm.entity.TheoreticalLesson;
import com.tpm.entity.MainTainOfPT;//总体安排
import com.tpm.entity.Topology;
import com.tpm.entity.TrainingAnother;
import com.tpm.entity.TrainingEvents;//培养事件

public interface PTBasicInformationDao extends BaseDao<PTBasicInformation>{

	PTBasicInformation getbydepartment(Department department);
	
	
	List<PTBasicInformation> findprofession1(String pid);
	List<MainTainOfPT> findprofession2(String pid);
	List<TrainingEvents> findprofession3(String pid);
	List[] findprofession4(String pid);
	List[] findprofession5(String pid);
	List[][][] findprofession6(String pid);
	List[][] findprofession7(String pid);
	List<TheoreticalLesson> findprofession8(String pid);

	List<PTBasicInformation> findcollege1();



	List<Ability> findAbility(String collegeid);


	List[] findProElec1(String pid);//专业选修课模块1 必选
	
	List[] findProElec2(String pid);//专业选修课模块2不是必选

	List<List<TheoreticalLesson>> findxueweike(String pid);


	List<Department> findCollegeProNum(String cid);


	List<College> findcollegenum();




	List<Topology> findpicture1(String pid);


	List<Topology> findpicture2(String pid);


	List<ScoreThreshold> findscore(String pid);


	TrainingAnother findgraduation(String pid);


	List<Professional> findProNum(String pid);


	List<TheoreticalLesson> findgongbixuankelist(String pid);


	Department findDepartmentName(String pid);


	MainTainOfPTTag findMainTainOfPTTag(String pid);


	List<TrainingEvents> findprofession3_lilunke(String pid);


	List<List<List<TheoreticalLesson>>> findProElec1_IS(String pid);


	List<List<PracticeLesson>> findqibaxueqiList(String pid);


	List<List<PracticeLesson>> findxueweike_shijian(String pid);











	




}
