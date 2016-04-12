package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class ResetSingleSce
 */
@WebServlet("/ResetSingleSce")
public class ResetSingleSce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetSingleSce() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = CatchIP.fPath + "JSON/originalset.json";
		File singlefile = new File(path);
        FileReader sfr = new FileReader(singlefile);
        BufferedReader sbr=new BufferedReader(sfr);
		String sdata = sbr.readLine();
		String str;
		while((str= sbr.readLine()) != null){
			sdata += str;
		}
		JSONTokener jsonParser = new JSONTokener(sdata);
		JSONObject jsonO;
		try {
			jsonO = (JSONObject) jsonParser.nextValue();
			for(int i = 0; i < jsonO.getJSONArray("Blocks").length(); i++){
		    	JSONObject bmps = jsonO.getJSONArray("Blocks").getJSONObject(i);
		    	bmps.put("BMP", 0);
		    }
			File newFile = new File(path);
			FileWriter sfw = new FileWriter(newFile);
			sfw.write(jsonO.toString());
			sfw.close();
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
