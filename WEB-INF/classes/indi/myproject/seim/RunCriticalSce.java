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
 * Servlet implementation class RunCriticalSce
 */
@WebServlet("/RunCriticalSce")
public class RunCriticalSce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    public String RunSingleSce() {
        // TODO Auto-generated constructor stub
        String singlestr = "";
		try {
			File file = new File(CatchIP.fPath + "JSON/originalset_c.json");		
			FileReader fr = new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String data;
			data = br.readLine();
			String str;
			while((str= br.readLine()) != null){
				data += str;
			}
			JSONTokener jsonParser = new JSONTokener(data);
			JSONObject jsonO;
			try {
				jsonO = (JSONObject) jsonParser.nextValue();
				for(int i = 0; i < jsonO.getJSONArray("Blocks").length(); i++) {
					singlestr += jsonO.getJSONArray("Blocks").getJSONObject(i).getString("BMP") + " ";
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return singlestr;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter mreturn = response.getWriter();
		String event = request.getParameter("eve");
		String ifrundo = null;
		String ifrunover = null;
		if(event.equals("do")) {
			Runtime mrun = Runtime.getRuntime();
			Process pro = null;
			try {
				String bmps_s = RunSingleSce();
	        	pro = mrun.exec("cmd /c start D:/seim_run/Debug/seim.exe model_zhuxi_FCM_2015_storm 8 88 " + bmps_s);
//				pro = mrun.exec("cmd /c start D:/protest.exe");
	        	ifrundo = "true";
				pro.waitFor();
				ifrunover = "true";
				
	        } catch (Exception e) {
	       }
		} 
		JSONArray ifrun = new JSONArray();
		ifrun.put(ifrundo).put(ifrunover);
		
		mreturn.write(ifrun.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
