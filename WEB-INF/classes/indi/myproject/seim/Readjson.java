package indi.myproject.seim;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Readjson
 */
@WebServlet("/Readjson")
public class Readjson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Readjson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter readjson = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		String cont = request.getParameter("content");
		if(cont.equals("do")){
		    File forld = new File(CatchIP.fPath + "data/BMPsetting");
			File[] list =forld.listFiles();
			int filecount = 0;
			JSONArray jsonfile = new JSONArray();
			JSONArray jsontext = new JSONArray();
			JSONObject jsonObjects = new JSONObject();
			for(File files:list){
				if(files.isFile()){
				    String filename = files.getName();
				    int dot = filename.lastIndexOf('.'); 
//					String filenamesub = filename.substring(0,8);
					String filenamesubs = filename.substring(0,dot);
					    filecount++;
					    jsonfile.put(filenamesubs);
				}
			}
			
			File txtforld = new File(CatchIP.fPath + "data/singlescenario");
			File[] txtlist = txtforld.listFiles();
			int textcount = 0;
			for(File files:txtlist){
				if(files.isFile()){
				    String filename = files.getName();
					int dot = filename.lastIndexOf('.'); 
					String filenamesubs = filename.substring(0,dot);
					    textcount++;
					    jsontext.put(filenamesubs);
				}
			}
			
			try {
				jsonObjects.put("files", jsonfile).put("texts", jsontext);
				JSONArray fc = new JSONArray();
				JSONArray tc = new JSONArray();
				fc.put(filecount);
				tc.put(textcount);
				jsonObjects.put("fnum", fc).put("tnum", tc);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    readjson.write(jsonObjects.toString());
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
