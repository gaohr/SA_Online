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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class RunScenarioSet
 */
@WebServlet("/RunScenarioSet")
public class RunScenarioSet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @return 
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
		String textname = request.getParameter("name");
		
		File file = new File(CatchIP.fPath + "data/singlescenario/" + textname + ".txt");		
		FileReader fr = new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String data;
		data = br.readLine();
		String str;
		while((str= br.readLine()) != null){
			data += str;
		}
		
		String ifrundo = null;
		if(event.equals("do")) {
			Runtime mrun = Runtime.getRuntime();
			Process pro = null;
			try {
	        	pro = mrun.exec("cmd /c start D:/seim_run/Debug/seim.exe model_zhuxi_FCM_2015_storm 8 88 " + data);
//				pro = mrun.exec("cmd /c start D:/protest.exe");
	        	ifrundo = "true";
				pro.waitFor();
				
	        } catch (Exception e) {
	       }
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
