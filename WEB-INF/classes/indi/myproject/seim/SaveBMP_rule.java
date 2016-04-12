package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
 * Servlet implementation class SaveBMP_rule
 */
@WebServlet("/SaveBMP_rule")
public class SaveBMP_rule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
private void SaveBMP_rule() {
    	
		File forld = new File(CatchIP.fPath + "data/singlescenario");
		File[] list =forld.listFiles();
		int filecount = 0;
		for(File files:list){
			if(files.isFile()){
			    String filename = files.getName();
				String filenamesub = filename.substring(0,16);
				if(filenamesub.equals("singlescenario_r")){
				    filecount++;
			    }
			}
		}
		    String singlestr = "";
			try {
				File file = new File(CatchIP.fPath + "JSON/originalset.json");		
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
				
				String newfile = CatchIP.fPath + "data/singlescenario/singlescenario_r" + (filecount + 1) + ".txt";				 
				File newTextFile = new File(newfile);
				FileWriter fw = new FileWriter(newTextFile);
				fw.write(singlestr);
				fw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		String fids = request.getParameter("fids");
		String bmps = request.getParameter("bmps");
		String save = request.getParameter("save");

		if(save.equals("undosave")) {
			String[] sffids = fids.split(",");
			String[] sfbmps = bmps.split(",");
			int sfbmpslen = sfbmps.length - 1;
		File file = new File(CatchIP.fPath + "JSON/originalset.json");
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
				for(int i = 0; i < jsonO.getJSONArray("Blocks").length(); i++){
					 JSONObject BMPs = jsonO.getJSONArray("Blocks").getJSONObject(i);
					 String ObjectID = BMPs.getString("ObjectID");
					 for(int j = 0; j < sffids.length; j++) {
						 if(ObjectID.equals(sffids[j])) {
							 BMPs.put("BMP",sfbmps[sfbmpslen]);
						 }
					 }
				}
				String newfile = CatchIP.fPath + "JSON/originalset.json";				 
				File newTextFile = new File(newfile);
				FileWriter fw = new FileWriter(newTextFile);
				fw.write(jsonO.toString());
				fw.close();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			if(save.equals("dosave")) {
				SaveBMP_rule();
				try {
					SaveBMP.resetoriginal(CatchIP.fPath + "JSON/originalset.json");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
