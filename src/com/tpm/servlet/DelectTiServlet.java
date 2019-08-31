package com.tpm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tpm.dao.ChooseCardDao;
import com.tpm.dao.FileQuestionDao;
import com.tpm.dao.FillCardDao;
import com.tpm.dao.QuestionDao;
import com.tpm.dao.SuoyinDao;
import com.tpm.entity.Question;



/**
 * Servlet implementation class DelectTiServlet
 */
@WebServlet("/DelectTiServlet")
public class DelectTiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
		
		//设置题的类型，1：单项填空题，2：多项填空题，3：多选题，4：单选题，5：文件上传题
		String tihao=request.getParameter("queNum");
		String leixing = null;
		int itihao= Integer.parseInt(tihao);
		QuestionDao tikubiao=new QuestionDao();
		Question question= tikubiao.getTi(itihao);
		leixing=question.getLeixing();

		tikubiao.deleTi(itihao);  //删除题库表中的题
		SuoyinDao suoyinbiao=new SuoyinDao();
		suoyinbiao.deleSy(itihao);	//删除索引表的索引项
		
		// 删除对应题库的题
		if (leixing.equals("多项填空题")) {
			FillCardDao duoxinagtiankongti=new FillCardDao();
			duoxinagtiankongti.deleteTiankongka(itihao);
		}
		
		if (leixing.equals("单选题")||leixing.equals("多选题")) {
			ChooseCardDao xuanzetibiao=new ChooseCardDao();
			xuanzetibiao.deleteDanxuanxiang(itihao);
			
		}
		if (leixing.equals("文件题")) {
			FileQuestionDao tableWenjianshangchuanti=new FileQuestionDao();
			tableWenjianshangchuanti.deleteWenjianshangchuanti(itihao);
		}
	}

}
