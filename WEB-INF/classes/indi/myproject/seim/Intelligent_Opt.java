package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * Servlet implementation class Intelligent_Opt
 */
@WebServlet("/Intelligent_Opt")
public class Intelligent_Opt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter mreturn = response.getWriter();
		String event = request.getParameter("eve");
		String pop = request.getParameter("pop");
		String gen = request.getParameter("gen");
		
		File file = new File("D:/ensga2_serial/Debug/zhuxi_P/data/GADefFile.txt");		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String textcon[] = new String[44];
		int i = 0;
		String str;
		while((str= br.readLine()) != null){
			textcon[i] = str;
			i++;
		}
		textcon[3] = pop + "	80	80	//Initial, min and max population sizes (multiple of 4)  400";
		textcon[6] = gen + "			//Maximum generations per run  250";
		textcon[7] = Integer.parseInt(pop) * Integer.parseInt(gen) + "			//Maximum number of function evaluations";
		
		//将修改参数后的数据写入文件
		String newfile = "D:/ensga2_serial/Debug/zhuxi_P/data/GADefFile.txt";				 
		File newTextFile = new File(newfile);
		FileWriter fw = new FileWriter(newTextFile);
		for(int j = 0; j < textcon.length - 1; j++ ){
			fw.write(textcon[j] + "\r\n");
		}
		fw.close();

		String ifrundo = null;
		Runtime mrun = Runtime.getRuntime();
		Process pro = null;
		try {
	        pro = mrun.exec("cmd /c start D:/ensga2_serial/Debug/OptBMPs.exe D:\\ensga2_serial\\Debug\\zhuxi_P");
//			pro = mrun.exec("cmd /c start D:/protest.exe");
	        ifrundo = "true";
			pro.waitFor();
	    } catch (Exception e) {
	    }
		JSONArray ifrun = new JSONArray();
		ifrun.put(ifrundo);
		
		mreturn.write(ifrun.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
