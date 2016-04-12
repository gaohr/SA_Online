package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
 * Servlet implementation class SingleSceContent
 */
@WebServlet(description = "Read single scenario content", urlPatterns = { "/SingleSceContent" })
public class SingleSceContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    	public String ReadContent(String path) throws JSONException, IOException{
    			File file = new File(path);
    	        FileReader fr;
    			fr = new FileReader(file);
    			@SuppressWarnings("resource")
				BufferedReader br=new BufferedReader(fr);
    			String data = br.readLine();
    			String str;
    			while((str= br.readLine()) != null){
    				data += str;
    			}
    	        return (data);
            }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter readtext = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		String textname = request.getParameter("textname");
		try {
			String textcontent = ReadContent(CatchIP.fPath + "data/singlescenario/" + textname + ".txt");
			JSONObject Blocks = new JSONObject();
			JSONArray bmps = new JSONArray();
			String[] sfbmp = textcontent.split(" ");
			for(int i = 1; i < sfbmp.length + 1; i++){
				JSONObject Block = new JSONObject();
			    Block.put("BMP", sfbmp[i - 1]).put("ObjectID", i);
			    bmps.put(Block);
			}
			Blocks.put("Blocks", bmps);
			readtext.write(Blocks.toString());
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
