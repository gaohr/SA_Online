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
 * Servlet implementation class ResetBMP
 */
@WebServlet("/ResetBMP")
public class ResetBMP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetBMP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		
		File file = new File(CatchIP.fPath + "JSON/SetBMPs.json");
        FileReader fr = new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
		String data = br.readLine();
		String s;
		while((s= br.readLine()) != null){
			data += s;
		}
		 JSONTokener jsonParser = new JSONTokener(data);
		 JSONObject jsonO;
			try {
				jsonO = (JSONObject) jsonParser.nextValue();
				
				File forld = new File(CatchIP.fPath + "data/BMPsetting");
				File[] list =forld.listFiles();
				int sfilecount = 0;
				int rfilecount = 0;
				for(File files:list){
					if(files.isFile()){
					    String filename = files.getName();
						String filenamesub = filename.substring(0,8);
						if(filenamesub.equals("SetBMPss")){
						    sfilecount++;
					    }
						if(filenamesub.equals("SetBMPsr")){
						    rfilecount++;
					    }
					}
				}
				if(type.equals("single")) {
					String newBMPsfile = CatchIP.fPath + "data/BMPsetting/SetBMPss_" + (sfilecount + 1) + ".json";				 
					File newTextFile = new File(newBMPsfile);
					FileWriter fw = new FileWriter(newTextFile);
					fw.write(jsonO.toString());
					fw.close();
				}
				if(type.equals("rule")) {
					String newBMPsfile = CatchIP.fPath + "data/BMPsetting/SetBMPsr_" + (rfilecount + 1) + ".json";				 
					File newTextFile = new File(newBMPsfile);
					FileWriter fw = new FileWriter(newTextFile);
					fw.write(jsonO.toString());
					fw.close();
				}
				
				File singlefile = new File(CatchIP.fPath + "JSON/originalset.json");
		        FileReader sfr = new FileReader(singlefile);
		        BufferedReader sbr=new BufferedReader(sfr);
				String sdata = sbr.readLine();
				String str;
				while((str= sbr.readLine()) != null){
					sdata += str;
				}
				JSONTokener jsonParser2 = new JSONTokener(sdata);
				JSONObject jsonO2;
				jsonO2 = (JSONObject) jsonParser2.nextValue();
				for(int i = 0; i < jsonO2.getJSONArray("Blocks").length(); i++){
				    	JSONObject bmps = jsonO2.getJSONArray("Blocks").getJSONObject(i);
				    	bmps.put("BMP", 0);
				}
				String newBMPfile = CatchIP.fPath + "JSON/originalset.json";				 
				File newFile = new File(newBMPfile);
				FileWriter sfw = new FileWriter(newFile);
				sfw.write(jsonO2.toString());
				sfw.close();	
				writer.write(jsonO2.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
