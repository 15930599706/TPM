package com.tpm.dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONException;
import org.json.JSONObject;

import com.hankcs.hanlp.HanLP;
import com.tpm.dao.AllAnswersDao;
import com.tpm.dao.ExcelDao;
import com.tpm.dao.QnaireAndQuestionDao;
import com.tpm.dao.QnaireAndStuDao;
import com.tpm.dao.QuestionDao;
import com.tpm.entity.Answer;
import com.tpm.entity.ExcelItem;
import com.tpm.entity.Question;

/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档
 * 
 * @version v1.0
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcel<T> {
	public static final String FILE_SEPARATOR = System.getProperties()
			.getProperty("file.separator");

	public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out) {
		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out, String pattern) {
		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers,
			Collection<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeightInPoints(30);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text=null;
			if(i!=4)text = new HSSFRichTextString("");
			else text = new HSSFRichTextString("工程专业学生表现程度");	
			cell.setCellValue(text);
		}		
		sheet.addMergedRegion(new CellRangeAddress(0,0,4,8));
		
		row = sheet.createRow(1);
		row.setHeight((short) 380);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 1;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			row.setHeight((short) 380);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
								1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(
								bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = value.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getMyExcel(Integer QnaireID,String imagesPath, String docsPath, String excelName ) {
		
		// 测试学生
		ExportExcel<ExcelItem> ex = new ExportExcel<ExcelItem>();
		String[] headers = { "问题编号","考察能力名", "指标点", "样本数", "非常满意", "基本满意","一般满意","基本不满意","非常不满意" };
		List<ExcelItem> dataset = new ArrayList<ExcelItem>();
		QnaireAndQuestionDao qnaireAndQuestionDao= new QnaireAndQuestionDao();
		ArrayList<Question> questions=qnaireAndQuestionDao.getQuestion(QnaireID);
		for (Question que : questions) {
			int tihao=que.getTihao();
			QuestionDao questionDao = new QuestionDao();
			Question question = questionDao.getTi(tihao);
			QnaireAndStuDao qnaireAndStuDao= new QnaireAndStuDao();
			int NumOfStu = qnaireAndStuDao.getnumofstuofQnaire(QnaireID);
			ArrayList<Answer> arrlist= new ArrayList<Answer>();
			AllAnswersDao allAnswersDao=new AllAnswersDao();
			arrlist=allAnswersDao.getAllAns(QnaireID, tihao);
			ExcelDao excelDao = new ExcelDao();
			String NameOfAbility = excelDao.getNameOfAbility(tihao);
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			arrayList = excelDao.getZhiBiaoDian(tihao);
			String zhibiaodian ="";
			for (Integer integer : arrayList) {
				zhibiaodian+=integer+",";
			}
			zhibiaodian = zhibiaodian.substring(0,zhibiaodian.length()-1);
			if (question.getLeixing().equals("单选题")) {
			//创建map  key保存字符串   value 保存出现的次数
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
				int []shuliang = {0,0,0,0,0};
				Set<String> set = map.keySet();
				Iterator<String>  it = set.iterator();//iterator迭代器	
				//jstype.put("Allnum", NumOfStu);   //这里是每道题有多少人做
				int temo=0;
				while (it.hasNext()) {
					String key = (String) it.next();
						try {
							shuliang[temo]=map.get(arr[temo]);
							} catch (JSONException e) {
							// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
						temo++;
					}
		dataset.add(new ExcelItem(tihao,NameOfAbility,zhibiaodian,NumOfStu,shuliang[0],shuliang[1],shuliang[2],shuliang[3],shuliang[4]));
		}
	}
		try {	
			OutputStream out = new FileOutputStream(docsPath + FILE_SEPARATOR+ excelName);		
			ex.exportExcel(headers, dataset, out);			
			out.close();			
			JOptionPane.showMessageDialog(null, "导出成功!");//前端显示导出成功
			System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
