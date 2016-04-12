package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * Servlet implementation class EvaluationSource
 */
@WebServlet("/EvaluationSource")
public class EvaluationSource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public static String displayCon(String filename) {
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader bw = new BufferedReader(new FileReader(new File (CatchIP.fPath + "data/output/" + filename)));
			String line = null;
			try {
				while((line = bw.readLine())!=null){
					list.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String firstsentence = list.get(0).toString();
		int datalength = list.size();
    	String lastsentence = list.get(list.size() - 1).toString();
    	String strr = lastsentence.trim();
    	String[] abc = strr.split("[\\p{Space}]+");
    	String text1 = abc[1];
    	String text2 = abc[2];
    	      
		return "<b>Describe</b>:" + firstsentence + "<br><b>Numbers</b>:<b style=\"font-size:15px;color:#00aaff\">" + (datalength - 1) + "</b><br><b>Cum.Gen<b/>:<b style=\"font-size:15px;color:#00aaff\">" + text1 + "</b><br><b>Index<b/>:<b style=\"font-size:15px;color:#00aaff\">" + text2 + "</b>";
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writetext = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8"); 
		String event = request.getParameter("content");
		
		File forld1 = new File(CatchIP.fPath + "data/output");
		File[] flist1 =forld1.listFiles();
		for(File files:flist1){
			if(files.isFile()){
				String filename = files.getName();
				if(event.equals(filename)){
				    writetext.write(displayCon(filename)); 
				}
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
