package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ReadSED
 */
@WebServlet("/ReadSED")
public class ReadSED extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    public String[] ReadQ_Outlet() {
        // TODO Auto-generated constructor stub
        List<String> list = new ArrayList<String>();
		String line = null;
		String Qcon[] = null;
		try {
			String filePath = "D:/seim_run/Debug/model_zhuxi_FCM_2015_storm/output/1_SED.txt";
			BufferedReader bw = new BufferedReader(new FileReader(new File (filePath)));
			try {
				while((line = bw.readLine())!=null){
					list.add(line);
				}

				String arr[] = new String[list.size()];
				String date[] = new String[list.size()];
				String Q_outlet[] = new String[list.size()];
				for(int i = 0;i < list.size(); i++){
					arr[i] = list.get(i).toString();
		    		String strr = arr[i].trim();
		    	    String[] singleline = strr.split("[\\p{Space}]+");
		    	    date[i] = singleline[0] + " " + singleline[1];
		    	    Q_outlet[i] = singleline[2];
				}
				Qcon = Q_outlet;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Qcon;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter res = response.getWriter();
		
		String Qcon = "";
		String[] resultcon = ReadQ_Outlet();
		for(int i = 0; i < resultcon.length; i++) {
			Qcon += resultcon[i] + ",";
		}
    	res.write(Qcon);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
