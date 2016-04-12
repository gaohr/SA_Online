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
 * Servlet implementation class RulesBased
 */
@WebServlet("/RulesBased")
public class RulesBased extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RulesBased() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		String fids = request.getParameter("fids");
		String bmps = request.getParameter("bmps");
		
		String[] sffids = fids.split(",");
		String[] sfbmps = bmps.split(",");
		
		 File forld = new File(CatchIP.fPath + "data/BMPsetting");
		 File[] list =forld.listFiles();
		 int filecount = 0;
		 for(File files:list){
			 if(files.isFile()){
				 String filename = files.getName();
				 String filenamesub = filename.substring(0,8);
				 if(filenamesub.equals("SetBMPsr")){
					 filecount++;
				 }
			 }
		 }
		 
		 File file = new File(CatchIP.fPath + "data/BMPsetting/SetBMPsr_" + filecount + ".json");
	        FileReader fr = new FileReader(file);
	        BufferedReader br=new BufferedReader(fr);
			String data = br.readLine();
			String str;
			while((str= br.readLine()) != null){
				data += str;
			}
			 JSONTokener jsonParser = new JSONTokener(data);
			 JSONObject jsonO;
				try {
					jsonO = (JSONObject) jsonParser.nextValue();
					 for(int i = 0; i < jsonO.getJSONArray("Blocks").length(); i++){
						 JSONArray BMPs = jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs");
						 String ObjectID = jsonO.getJSONArray("Blocks").getJSONObject(i).getString("ObjectID");
						 for(int j = 0; j < sffids.length; j++) {
							 if(ObjectID.equals(sffids[j])) {
								 for(int k = 0; k < sfbmps.length; k++) {
									 JSONObject addbmp = new JSONObject("{'BMP':" + "\'" + sfbmps[k] + "\'" + "}");
									 BMPs.put(addbmp);
								 }
							 }
						 }
					}
					
					String newfile = CatchIP.fPath + "data/BMPsetting/SetBMPsr_" + filecount + ".json";				 
					File newTextFile = new File(newfile);
					FileWriter fw = new FileWriter(newTextFile);
					fw.write(jsonO.toString());
					fw.close();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i = 0; i < sfbmps.length; i++) {
					String bmpname = "";
					int sfbmps_int = Integer.valueOf(sfbmps[i]);
					switch(sfbmps_int){
					case 0: bmpname += "BMP:0-无\n"; break;
					case 1: bmpname += "BMP:1-低效林改造\n"; break;
					case 2: bmpname += "BMP:2-等高草灌带\n"; break;
					case 3: bmpname += "BMP:3-草灌乔混交\n"; break;
					case 4: bmpname += "BMP:4-小穴播草\n"; break;
					case 5: bmpname += "BMP:5-茶果园坡改梯\n"; break;
					case 6: bmpname += "BMP:6-封禁\n"; break;
					}
					writer.write(bmpname);
				}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
