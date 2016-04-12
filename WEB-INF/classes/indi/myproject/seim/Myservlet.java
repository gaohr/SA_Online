package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONStringer;
import org.json.JSONTokener;
/**
 * Servlet implementation class Myservlet
 */
@WebServlet("/Myservlet")
public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Myservlet() {
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
		String objectid = request.getParameter("ObjectID");
		String bmp = request.getParameter("BMP");
		
        //读写json
        JSONObject jsonObj  = new JSONObject();
        try {
			jsonObj.put("ObjectID", objectid);
			jsonObj.put("BMP", bmp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		writer.write("SettedObject:" + jsonObj.toString());
        File forld = new File(CatchIP.fPath + "data/BMPsetting");
		File[] list =forld.listFiles();
		int filecount = 0;
		for(File files:list){
			if(files.isFile()){
			    String filename = files.getName();
				String filenamesub = filename.substring(0,8);
				if(filenamesub.equals("SetBMPss")){
				    filecount++;
			    }
			}
		}
		
		File file = new File(CatchIP.fPath + "data/BMPsetting/SetBMPss_" + filecount + ".json");
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
					 JSONArray bmps = jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs");
					 String block = jsonO.getJSONArray("Blocks").getJSONObject(i).getString("ObjectID");												
						if(block.equals(objectid)){
							JSONObject addbmp = new JSONObject("{'BMP':" + "\'" + bmp + "\'" + "}");
							boolean putbmp = true;
							for(int j = 0; j < bmps.length(); j++){//删除已存在对象要素
								Boolean has = bmps.getJSONObject(j).has("BMP");
								if(has){
								    String bmpvalue = bmps.getJSONObject(j).getString("BMP");
							        if(bmpvalue.equals(bmp)){
									    bmps.getJSONObject(j).remove("BMP");
									    putbmp = false;
								    } 
								}
							}
							if(putbmp){
							    bmps.put(addbmp);
							}
						}
					}
				
				String newfile = CatchIP.fPath + "data/BMPsetting/SetBMPss_" + filecount + ".json";				 
				File newTextFile = new File(newfile);
				FileWriter fw = new FileWriter(newTextFile);
				fw.write(jsonO.toString());
				fw.close();
				writer.write(jsonO.toString());
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
