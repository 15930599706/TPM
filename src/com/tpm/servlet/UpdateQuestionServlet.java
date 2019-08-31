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
import com.tpm.entity.File_question;
import com.tpm.entity.Fill_card;
import com.tpm.entity.Question;

/**
 * Servlet implementation class UpdateQuestionServlet
 */
@WebServlet("/UpdateQuestionServlet")
public class UpdateQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuestionServlet() {
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
		String LeiXing =request.getParameter("queType");
		String BiaoTi = request.getParameter("caption");
		String TishiNeirong = request.getParameter("tipText");
		String ShifouBida = request.getParameter("ansType"); //这个是题的是否必答
		String TiHao  = request.getParameter("queNum");//
//		String LeiXing ="单选题";
//		String BiaoTi = "三傻";
//		String TishiNeirong = "三傻死了";
		//String ShifouBida = request.getParameter("ansType"); //这个是题的是否必答
//		String TiHao  = "136";//
		QuestionDao questionDao = new QuestionDao();
		Question question = new Question();
		//int shifoubida = Integer.parseInt(ShifouBida);
		int tihao = Integer.parseInt(TiHao);
		question.setBiaoti(BiaoTi);
		question.setTishineirong(TishiNeirong);
		
		//String []min= request.getParameterValues("minSize");
		//String []max= request.getParameterValues("maxSize");
		//for(int i=1;i<min.length;i++) {
		//	System.out.println("bbb  "+min[i]);
		//}
//		for (String string : max) {
//			System.out.println(string+"aaaaaaaa");
//		}
		
		
		
		//question.setShifoubida(shifoubida);
		question.setLeixing(LeiXing);
		question.setTihao(tihao);
		
		questionDao.update(question);
		System.out.println("abcdefgh");
		if(LeiXing.equals("单选题")||LeiXing.equals("多选题")) 
		{
			try {
				String []fenzhi= request.getParameterValues("chose_score_array");	
				String []xuanxiang_neirong= request.getParameterValues("chose_text_array");
				if (fenzhi!=null&&xuanxiang_neirong!=null) 
				{
					float[] a=new float[fenzhi.length];	//a是选项的分值
					ChooseCardDao tableDuoxuanti=new ChooseCardDao();
					tableDuoxuanti.deleteDanxuanxiang(tihao);
					if(LeiXing.equals("单选题"))
					{
						for(int i=0;i<fenzhi.length;i++)
						{
							a[i]=Integer.parseInt(fenzhi[i]);      //a:[4,5,6] 
							tableDuoxuanti.addXzt(tihao, xuanxiang_neirong[i], a[i]);
						}
					}else {
						for(int i=1;i<fenzhi.length;i++)
						{
							a[i]=Integer.parseInt(fenzhi[i]);      //a:[4,5,6] 
							tableDuoxuanti.addXzt(tihao, xuanxiang_neirong[i], a[i]);
						}
					}
					
				}
				else
				{  
		            System.out.println("获取数组参数失败!");  
		        }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(LeiXing.equals("多项填空题"))
		{
			try {
				String []min= request.getParameterValues("minSize");
				String []max= request.getParameterValues("maxSize");
				FillCardDao tableTiankongka=new FillCardDao();
				tableTiankongka.deleteTiankongka(tihao);
				if (min!=null&&max!=null) {
					for(int i=1;i<min.length;i++) {
						int iminlength=Integer.parseInt(min[i]);
						int imaxlength=Integer.parseInt(max[i]);
						Fill_card t =new Fill_card();
						t.setDxnum(tihao);
						t.setMinlength(iminlength);
						t.setMaxlength(imaxlength);
						tableTiankongka.addTiankongka(t);
					}
				}
				else 
				{
					System.out.println("获取数组参数失败!");  
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(LeiXing.equals("文件上传题"))
		{
			try {
				String maxspace=request.getParameter(""); //没参数，写的是不超过4M
				int imaxspace= Integer.parseInt(maxspace);
				FileQuestionDao tableWenjianshangchuanti=new FileQuestionDao();
				tableWenjianshangchuanti.deleteWenjianshangchuanti(tihao);
				File_question w=new File_question();
				w.setTihao(tihao);
				w.setMaxspace(4);
				tableWenjianshangchuanti.addWenjianshangchuanti(w);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
