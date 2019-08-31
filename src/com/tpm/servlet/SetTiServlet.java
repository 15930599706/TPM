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
import com.tpm.entity.Choose_card;
import com.tpm.entity.File_question;
import com.tpm.entity.Fill_card;
import com.tpm.entity.Question;
import com.tpm.entity.Suoyin;





/**
 * Servlet implementation class SetTiServlet
 */
@WebServlet("/SetTiServlet")
public class SetTiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
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
		response.setCharacterEncoding("utf-8");
		//int num = Integer.parseInt(request.getParameter("num"));
		
	//	for(int count=0;count<num;count++) {
			//这应该改成链表了吧
			String leixing =request.getParameter("queType");
			String biaoti = request.getParameter("caption");
			String tishineirong = request.getParameter("tipText");
			String shifoubida = request.getParameter("ansType"); //这个是题的是否必答
			String []zhibiaodian = request.getParameterValues("index_value_arr");
			
			int ishifoubida;		//转换成int的是否必答
				
				if (shifoubida.equals("yes")) {
					ishifoubida=1;
				}
				else {
					ishifoubida=0;
				}
				Question tilei=new Question();
				tilei.setBiaoti(biaoti);
				tilei.setLeixing(leixing);
				tilei.setTishineirong(tishineirong);
				tilei.setShifoubida(ishifoubida);
				QuestionDao tikubiao=new QuestionDao();
				int id =tikubiao.addTi(tilei);   //向题库表中增加题
				for(int i = 0;i<zhibiaodian.length;i++)
				{
					int izhibiaodian = Integer.parseInt(zhibiaodian[i]);
					SuoyinDao suoyinbiao = new SuoyinDao();
					Suoyin suoyin=new Suoyin();
					suoyin.setTihao(id);
					suoyin.setZhibiaodian(izhibiaodian);
					suoyinbiao.addSy(suoyin);  //向索引中添加
				}
		if (leixing.equals("多选题")||leixing.equals("单选题")) {
	
			try {
				String []fenzhi= request.getParameterValues("chose_score_array");	
				String []xuanxiang_neirong= request.getParameterValues("chose_text_array");
				if (fenzhi!=null&&xuanxiang_neirong!=null) {
				float[] a=new float[fenzhi.length];	//a是选项的分值
				
				ChooseCardDao tableDuoxuanti=new ChooseCardDao();
				if (leixing.equals("单选题")) {
					for(int i=0;i<fenzhi.length;i++){
						a[i]=Integer.parseInt(fenzhi[i]);      //a:[4,5,6] 
						tableDuoxuanti.addXzt(id,xuanxiang_neirong[i],a[i]);
						}
				}else {
					for(int i=1;i<fenzhi.length;i++){
						a[i]=Integer.parseInt(fenzhi[i]);      //a:[4,5,6] 
						tableDuoxuanti.addXzt(id,xuanxiang_neirong[i],a[i]);
						}
				}
				
				}else{  
	                System.out.println("获取数组参数失败!");  
	            }
					
			} catch (NumberFormatException e) {
				// TODO 自动生成的 catch 块
				//e.printStackTrace();
			}
		}
		
		if (leixing.equals("多项填空题")) {
			String neirong =biaoti;
			System.out.println(neirong);
			 
			//以下是添加填空卡表
			String []min= request.getParameterValues("minSize");
			String []max= request.getParameterValues("maxSize");
			FillCardDao tableTiankongka=new FillCardDao();
			if (min!=null&&max!=null) {
				for(int i=1;i<min.length;i++) {
					int iminlength=Integer.parseInt(min[i]);
					int imaxlength=Integer.parseInt(max[i]);
					Fill_card tkk = new Fill_card();
					tkk.setDxnum(id);//多项填空题编号
					tkk.setMaxlength(imaxlength);
					tkk.setMinlength(iminlength);
					tkk.setBitian(1);
					tkk.setXianding(1);
					tableTiankongka.addTiankongka(tkk);
				}
				
			}else {
				System.out.println("获取数组参数失败!");  
			}
		}
	
		if (leixing.equals("文件上传题")) {
			//String maxspace=request.getParameter(""); //没参数，写的是不超过4M
			//int imaxspace= Integer.parseInt(maxspace);
			FileQuestionDao tableWenjianshangchuanti=new FileQuestionDao();
			File_question wj = new File_question();
			wj.setMaxspace(4);
			wj.setTihao(id);
			tableWenjianshangchuanti.addWenjianshangchuanti(wj);
		}	
		
		//}
		///文件尾
	}

}
