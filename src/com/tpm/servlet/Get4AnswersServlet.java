package com.tpm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hankcs.hanlp.HanLP;
import com.sun.org.apache.xerces.internal.util.HTTPInputSource;
import com.tpm.dao.AllAnswersDao;
import com.tpm.dao.QnaireAndQuestionDao;
import com.tpm.dao.QnaireAndStuDao;
import com.tpm.dao.QuestionDao;
import com.tpm.entity.Answer;
import com.tpm.entity.Question;

/**
 * Servlet implementation class GetAnswersServlet
 */
@WebServlet("/Get4AnswersServlet")
public class Get4AnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Get4AnswersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/json; charset=utf-8");
		//int QnaireID=11;//60
		//int tihao=106;//126
		String Id= request.getParameter("QnaireID");
		int QnaireID=Integer.parseInt(Id);

		QnaireAndQuestionDao qnaireAndQuestionDao= new QnaireAndQuestionDao();
		ArrayList<Question> questions=qnaireAndQuestionDao.getQuestion(QnaireID);
		JSONArray jsonArray =new JSONArray();
		for (Question que : questions) {
			int tihao=que.getTihao();
			QuestionDao questionDao = new QuestionDao();
			Question question = questionDao.getTi(tihao);
			QnaireAndStuDao qnaireAndStuDao= new QnaireAndStuDao();
			int NumOfStu = qnaireAndStuDao.getnumofstuofQnaire(QnaireID);
			ArrayList<Answer> arrlist= new ArrayList<Answer>();
			AllAnswersDao allAnswersDao=new AllAnswersDao();
			arrlist=allAnswersDao.getAllAns(QnaireID, tihao);
			//JSONArray jsonArray =new JSONArray();
			
			//JSONObject jsonObject = new JSONObject();
			if (question.getLeixing().equals("单选题")||
					question.getLeixing().equals("多选题")||question.getLeixing().equals("多项填空题")) {
			//创建map  key保存字符串   value 保存出现的次数
			///
				String[] arr=new String[arrlist.size()];
				int jishu=0;
				for (Answer answer : arrlist) {
					arr[jishu]=answer.getAnswer();
					jishu++;
				}
				Map<String,Integer> map = new HashMap<String, Integer>();

				for (int i = 0; i < arr.length; i++) {//循环数组

					if(map.containsKey(arr[i])){//判断如果key中已存在该字符串
						map.put(arr[i], map.get(arr[i])+1);//value值 加一次（多出现一次）
					}else{
						map.put(arr[i], 1);//如果该字符串没有出现 map新保存一组数据  出现次数为1次
						}
				}
				//循环结束
				//迭代map
				Set<String> set = map.keySet();
				Iterator<String>  it = set.iterator();//iterator迭代器	
				JSONObject jstype= new JSONObject();
				jstype.put("tiID", tihao);
				jstype.put("type", question.getLeixing());
				jstype.put("Allnum", NumOfStu);
				//jstype.put("geshu", map.size());
				if (question.getLeixing().equals("多项填空题")) {
					jstype.put("geshu", arr.length);
				}else {
					jstype.put("geshu", map.size());
				}

				jsonArray.put(jstype);
				/////
				
			int bianliang=1;
			int tongji=0;
			int shuliang=arrlist.size()/NumOfStu;
			if(question.getLeixing().equals("多项填空题")) {
				
			for(int i=0;i<arr.length;i++) {
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("dijige", bianliang);
					jsonObject.put("name",arr[tongji] );//key 改为arr[tongji]
					jsonObject.put("value", 1);
					//double a=map.get(arr[tongji]); //这里是类型转换
					//jsonObject.put("baifenbi", (a/NumOfStu)*100+"%");
					jsonArray.put(jsonObject);
					} catch (JSONException e) {
					// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				bianliang++;
				tongji++;
				if (tongji==arr.length) {
					break;
				}
				if (bianliang>shuliang) {
					bianliang=1;
				}
			}
			}else {
				while (it.hasNext()) {
					String key = (String) it.next();
					JSONObject jsonObject=new JSONObject();
						try {
							jsonObject.put("name",key );
							jsonObject.put("value", map.get(key));
							//double a=map.get(arr[tongji]); //这里是类型转换
							//jsonObject.put("baifenbi", (a/NumOfStu)*100+"%");
							jsonArray.put(jsonObject);
							} catch (JSONException e) {
							// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
					}
			}
			
			
		
			/*while (it.hasNext()) {
				
			String key = (String) it.next();
			JSONObject jsonObject=new JSONObject();
			if (question.getLeixing().equals("多项填空题")) {
				try {
					jsonObject.put("dijige", bianliang);
					jsonObject.put("name",arr[tongji] );//key 改为arr[tongji]
					jsonObject.put("value", map.get(arr[tongji]));
					//double a=map.get(arr[tongji]); //这里是类型转换
					//jsonObject.put("baifenbi", (a/NumOfStu)*100+"%");
					jsonArray.put(jsonObject);
					} catch (JSONException e) {
					// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				bianliang++;
				
				if (bianliang>shuliang) {
					bianliang=1;
				}
			} 
			else {
				try {
					jsonObject.put("name",key );
					jsonObject.put("value", map.get(key));
					//double a=map.get(arr[tongji]); //这里是类型转换
					//jsonObject.put("baifenbi", (a/NumOfStu)*100+"%");
					jsonArray.put(jsonObject);
					} catch (JSONException e) {
					// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			} 
			tongji++;
			if (tongji==arr.length) {
				break;
			}
		}*/
			}else if (question.getLeixing().equals("单项填空题")) {
				String[] arr=new String[arrlist.size()];
				ArrayList<List<String>> Allkeyword = new ArrayList<List<String>>();
				int jishu=0;
				for (Answer answer : arrlist) {
					arr[jishu]=answer.getAnswer();
					List<String> keywordList =HanLP.extractKeyword(answer.getAnswer(), 20);
					//下面注释是分短语
					//List<String> phraseList = HanLP.extractPhrase(answer.getAnswer(), 5);
					//System.out.println(phraseList);
					//Allkeyword.add(phraseList);
					Allkeyword.add(keywordList);
					jishu++;
				}
				Map<String,Integer> map = new HashMap<String, Integer>();
				for (List<String> list : Allkeyword) {
					for (String string : list) {
						if(map.containsKey(string)){//判断如果key中已存在该字符串
							map.put(string, map.get(string)+1);//value值 加一次（多出现一次）
							}else{
							map.put(string, 1);//如果该字符串没有出现 map新保存一组数据  出现次数为1次
							}
					}
				}
				Set<String> set = map.keySet();
				Iterator<String>  it = set.iterator();//iterator迭代器
				JSONObject jstype= new JSONObject();
				jstype.put("tiID", tihao);
				jstype.put("type", question.getLeixing());
				jstype.put("Allnum", NumOfStu);
				jstype.put("geshu", map.size());
				jsonArray.put(jstype);
				
				
				while (it.hasNext()) {
				String key = (String) it.next();
				JSONObject jsonObject=new JSONObject();
				try {
					jsonObject.put("name",key );
					jsonObject.put("value", map.get(key));
					jsonArray.put(jsonObject);
				} catch (JSONException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

				} 
			}
			else if (question.getLeixing().equals("文件上传题")) {
				JSONObject jstype= new JSONObject();
				jstype.put("tiID", tihao);
				jstype.put("type", question.getLeixing());
				jstype.put("Allnum", NumOfStu);
				jstype.put("geshu", 0);
				jsonArray.put(jstype);

			//jsonArray.put(jsonObject);
			//totaljson.put(jsonArray);
		}
			else if(question.getLeixing().equals("多项填空题")) {
				
			}
	}
		response.getWriter().print(jsonArray.toString());
		//response.getWriter().print(totaljson.toString());
		// 根据题号获取题的类型
		
	}
}
