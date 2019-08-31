package com.tpm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tpm.dao.AllAnswersDao;
import com.tpm.dao.QnaireAndQuestionDao;
import com.tpm.dao.QnaireAndStuDao;
import com.tpm.dao.QuestionDao;
import com.tpm.entity.Answer;
import com.tpm.entity.Question;

/**
 * Servlet implementation class AnalysisEmotionServlet
 */
@WebServlet("/AnalysisEmotionServlet")
public class AnalysisEmotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalysisEmotionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置APPID/AK/SK
			// 新建一个AipNlp,初始化完成后建议单例使用,避免重复获取access_token：
			// AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
			AipNlp client = getInstance();
			//AipNlp client2 = getInstance();
	 
		//	System.out.println("" + client.equals(client2));	//检验单例使用
	 
			// 可选：设置网络连接参数
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
	 
			// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
			// client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
			// client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理
	 
			// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
			// 也可以直接通过jvm启动参数设置此环境变量
			// System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
	 
			// 调用接口
			//String text = "百度是一家高科技公司";
			// 传入可选参数调用接口
			HashMap<String, Object> options = new HashMap<String, Object>();
			// 情感倾向分析
			//String Id="59";
			//String tinum="106";
			String Id= request.getParameter("QnaireID");
			String tinum=request.getParameter("Tihao");
			int QnaireID=Integer.parseInt(Id);
			//QnaireAndQuestionDao qnaireAndQuestionDao= new QnaireAndQuestionDao();
		
				int tihao=Integer.parseInt(tinum);
				//QuestionDao questionDao = new QuestionDao();
				//Question question = questionDao.getTi(tihao);
				QnaireAndStuDao qnaireAndStuDao= new QnaireAndStuDao();
				int NumOfStu = qnaireAndStuDao.getnumofstuofQnaire(QnaireID);
				if (NumOfStu ==0) {
					NumOfStu=1000;
				}
				ArrayList<Answer> arrlist= new ArrayList<Answer>();
				AllAnswersDao allAnswersDao=new AllAnswersDao();
				arrlist=allAnswersDao.getAllAns(QnaireID, tihao);
				
			int numofAns= arrlist.size();
			
			double [] shuju = new double[numofAns];
			int []canshu = new int[5];
			
			int ig=0;
			JSONArray jsonArray =new JSONArray();
			for (Answer answer : arrlist) {
				JSONObject res = client.sentimentClassify(answer.getAnswer(), options);
				JsonParser parser = new JsonParser();
			     JsonObject object = (JsonObject)parser.parse(res.toString());
			     JsonArray array = object.get("items").getAsJsonArray();
			     JsonObject suJsonObject = array.get(0).getAsJsonObject();
				 double shu = Double.valueOf(suJsonObject.get("positive_prob").toString());
				 shu = (double) Math.round(shu * 100) / 100;
				 shuju[ig]=shu;
				 ig++;
			}
			/*for (String string : wenben) {
				JSONObject res = client.sentimentClassify(string, options);
				//System.out.println(res.toString(2));
				 JsonParser parser = new JsonParser();
			     JsonObject object = (JsonObject)parser.parse(res.toString());
			     JsonArray array = object.get("items").getAsJsonArray();
			     JsonObject suJsonObject = array.get(0).getAsJsonObject();
				 System.out.println(suJsonObject.get("positive_prob"));
				 double shu = Double.valueOf(suJsonObject.get("positive_prob").toString());
				 shu = (double) Math.round(shu * 100) / 100;
				 shuju[ig]=shu;
				 ig++;
				 System.out.println(shu);
			}*/
			for (double d : shuju) {
				if (d>0&&d<=0.2) {
					canshu[0]++;
				}
				if (d>0.2&&d<=0.4) {
					canshu[1]++;
				}
				if (d>0.4&&d<=0.6) {
					canshu[2]++;
				}
				if (d>0.6&&d<=0.8) {
					canshu[3]++;
				}
				if (d>0.8&&d<=1) {
					canshu[4]++;
				}
			}
			for (int i : canshu) {
				//jsonObject.put("name", );
				JSONObject jsonObject2=new JSONObject();
				jsonObject2.put("num", i);
				jsonArray.put(jsonObject2);
				
			}
			//循环结束
			//迭代map
			response.getWriter().print(jsonArray.toString());	
	}
	public static final String APP_ID = "15597361";
	public static final String API_KEY = "2y6lFF1oNsmN8kG3pq6TxRsD";
	public static final String SECRET_KEY = "qhH4i7Lo1bvTb53q0izldOXgvS1n2FMv";
 
	private static AipNlp instance = null;
	public static synchronized AipNlp getInstance() {
		if (instance == null) {
			instance = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
		} 
		return instance;
	}

}
