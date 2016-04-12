package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class ReadjsonContent
 */
@WebServlet("/ReadjsonContent")
public class ReadjsonContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
	public static class Content {
		String ReadContent(String path) throws JSONException, IOException{
			File file = new File(path);
	        FileReader fr;
			fr = new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String data = br.readLine();
			String s;
			while((s= br.readLine()) != null){
				data += s;
			}
			JSONTokener jsonParser = new JSONTokener(data);
			JSONObject jsonO;
			jsonO = (JSONObject) jsonParser.nextValue();
			String tbodytr = "";
			for(int i = 0; i < jsonO.getJSONArray("Blocks").length(); i++){
				String fid = jsonO.getJSONArray("Blocks").getJSONObject(i).getString("ObjectID");
				String bmpid;
				String bmpname = "";
				for(int j = 0; j < jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs").length();j++){
					Boolean has = jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs").getJSONObject(j).has("BMP");
					if(has) {
						bmpid =  jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs").getJSONObject(j).getString("BMP");
						int bmpid_int = Integer.valueOf(bmpid);
						switch(bmpid_int){
						case 0: bmpname += "0-无<br>"; break;
						case 1: bmpname += "1-低效林改造<br>"; break;
						case 2: bmpname += "2-等高草灌带<br>"; break;
						case 3: bmpname += "3-草灌乔混交<br>"; break;
						case 4: bmpname += "4-小穴播草<br>"; break;
						case 5: bmpname += "5-茶果园坡改梯<br>"; break;
						case 6: bmpname += "6-封禁<br>"; break;
						}
					}
				}
				tbodytr += "<tr><td><input type=\"radio\" name=\"select\" /></td><td>" + fid + "</td><td>" + bmpname + "</td></tr>";
			}
			
	        return (tbodytr);
       
        }
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter readjson = response.getWriter();
		String cont = request.getParameter("content");
		
		String tbodytr;
		Content add=new Content ();
		try {
		    tbodytr=add.ReadContent(CatchIP.fPath + "data/BMPsetting/" + cont +".json");
//			readjson.write("1" + "<br>" + sn);
			readjson.write(tbodytr);
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
