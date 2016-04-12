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
 * Servlet implementation class CriticalZone
 */
@WebServlet("/CriticalZone")
public class CriticalZone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriticalZone() {
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
		String sfids = request.getParameter("fids");
		String bmp = request.getParameter("bmp");
		String save = request.getParameter("save");
		
		String[] fids = sfids.split(",");
		File file = new File(CatchIP.fPath + "JSON/originalset_c.json");
		FileReader fr = new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String data = br.readLine();
		String s;
		String bmpstr = "";
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
				 for(int j = 0; j < fids.length; j++) {
					 if(ObjectID.equals(fids[j])) {
						 BMPs.put("BMP",bmp);
					 }
				 }
				 bmpstr += BMPs.getString("BMP") + " ";
			}
			//将情景配置写进文件
			String newfile = CatchIP.fPath + "JSON/originalset_c.json";				 
			File newTextFile = new File(newfile);
			FileWriter fw = new FileWriter(newTextFile);
			fw.write(jsonO.toString());
			fw.close();
			
			String textcon = "";
			String[] bmpcontent = bmpstr.split(" ");
			for(int i = 0; i < bmpcontent.length; i++) {
				int selectID = Integer.valueOf(bmpcontent[i]);
				switch(selectID) {
				case 0:textcon += "<p style=\"height:10px\">" + (i + 1) + "-0(无)</p>";break;
				case 1:textcon += "<p style=\"height:10px\">" + (i + 1) + "-1(低效林改造)</p>";break;
				case 2:textcon += "<p style=\"height:10px\">" + (i + 1) + "-2(等高草灌带)</p>";break;
				case 3:textcon += "<p style=\"height:10px\">" + (i + 1) + "-3(草灌乔混交)</p>";break;
				case 4:textcon += "<p style=\"height:10px\">" + (i + 1) + "-4(小穴播草)</p>";break;
				case 5:textcon += "<p style=\"height:10px\">" + (i + 1) + "-5(茶果园坡改梯)</p>";break;
				case 6:textcon += "<p style=\"height:10px\">" + (i + 1) + "-6(封禁)</p>";break;
				}
			}
			writer.write(textcon);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(save.equals("dosave")) {
			File forld = new File(CatchIP.fPath + "data/singlescenario");
			File[] list =forld.listFiles();
			int filecount = 0;
			for(File files:list){
				if(files.isFile()){
				    String filename = files.getName();
					String filenamesub = filename.substring(0,16);
					if(filenamesub.equals("singlescenario_c")){
					    filecount++;
				    }
				}
			}
			String newtxt = CatchIP.fPath + "data/singlescenario/singlescenario_c" + (filecount + 1) + ".txt";				 
			File newTextFile = new File(newtxt);
			FileWriter fw = new FileWriter(newTextFile);
			fw.write(bmpstr);
			fw.close();
					
			try {
				SaveBMP.resetoriginal(CatchIP.fPath + "JSON/originalset_c.json");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if(save.equals("reset")) {
			try {
				SaveBMP.resetoriginal(CatchIP.fPath + "JSON/originalset_c.json");
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
