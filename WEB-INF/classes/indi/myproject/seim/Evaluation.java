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
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Evaluation
 */
@WebServlet("/Evaluation")
public class Evaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public static String displaytext(String filename) {
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
		String arr[] = new String[list.size()];
		String text1[] = new String[list.size()];
		String text2[] = new String[list.size()];
		String str_blocks[] = new String[list.size()];
		JSONObject allpoints = new JSONObject();
		JSONArray pointsArr = new JSONArray();
		JSONObject allbmps = new JSONObject();
    	for(int i = 2;i < list.size(); i++){
    		JSONArray bmps = new JSONArray();
    		  arr[i] = list.get(i).toString();
    		  String strr = arr[i].trim();
    	      String[] abc = strr.split("[\\p{Space}]+");
    	      text1[i] = abc[3];
    	      text2[i] = abc[4];
    	      for(int k = 7; k <= 94; k++) { //统计实施措施的地块
    	    	  str_blocks[i] += abc[k];
    	      }
    	      BigDecimal text1_tr = new BigDecimal(text1[i]); 
    		  BigDecimal text2_tr = new BigDecimal(text2[i]); 
    		  String text1_tr1 = Double.toString(Math.abs(Double.valueOf(text1_tr.toPlainString()).doubleValue()));
    		  JSONArray pointArr = new JSONArray();
			  pointArr.put(text2_tr.toPlainString()).put(text1_tr1);
			  pointsArr.put(pointArr);
			  try {
  				  for(int j = 1; j < abc.length - 6; j++){
  					  JSONObject Block = new JSONObject();
  					  Block.put("BMP", abc[j + 6]).put("ObjectID", j);
  					  bmps.put(Block);
  				  }
  				  allbmps.put("p" + (int)Math.floor(Double.valueOf(text2_tr.toPlainString())),bmps);
  				  allpoints.put("Blocks", allbmps).put("points", pointsArr);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
		return allpoints.toString();
	}
	public static String displaymap(String pointid,String filename) {
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
		String arr[] = new String[list.size()];
		String text2[] = new String[list.size()];
		JSONArray pointsArr = new JSONArray();
		//single：提取单一情景方案
		JSONArray single = new JSONArray();  
    	for(int i = 2;i < list.size(); i++){
    		JSONArray bmps = new JSONArray();
    		  arr[i] = list.get(i).toString();
    		  String strr = arr[i].trim();
    	      String[] abc = strr.split("[\\p{Space}]+");
    	      text2[i] = abc[4];
    		  BigDecimal text2_tr = new BigDecimal(text2[i]); 
    		  JSONArray pointArr = new JSONArray();
			  pointArr.put(text2_tr.toPlainString());
			  pointsArr.put(pointArr);
			  try {
  				  for(int j = 1; j < abc.length - 6; j++){
  					  JSONObject Block = new JSONObject();
  					  Block.put("BMP", abc[j + 6]).put("ObjectID", j);
  					  bmps.put(Block);
  				  }
  				  String point = "p" + (int)Math.floor(Double.valueOf(text2_tr.toPlainString()));
  				  if(point.equals(pointid)) {
  					single = bmps;
  				  }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
    	//System.out.println(single);
		return single.toString();
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
		String type = request.getParameter("type");
		String pointid = request.getParameter("id");
		
		
		File forld1 = new File(CatchIP.fPath + "data/output");
		File[] flist1 =forld1.listFiles();
		for(File files:flist1){
			if(files.isFile()){
				String filename = files.getName();
				if(type.equals("points")) {
					if(event.equals(filename)){
						writetext.write(displaytext(filename)); 
					}
				}
				if(type.equals("display")) {
					if(event.equals(filename)){
						writetext.write(displaymap(pointid,filename)); 
					}
				}
			}
		}
		
		if(event.equals("displayfile")){
			File forld = new File(CatchIP.fPath + "data/output");
			File[] flist =forld.listFiles();
			int filescount = 0;
			String fname = "<ul>";
			for(File files:flist){
				if(files.isFile()){
					String filename = files.getName();
					String subfilename = filename.substring(0, filename.length()-4);
					filescount++;
					fname += "<li><a id=\"" + subfilename + "\" onclick=\"displayplot(\'" + filename + "\')\" href=\"#\" class=\"outputfiles\">" + filename + "</a></li>";					
				}
			}
			fname += "</ul>";
			String fcnum = "Numbers of output files:<b style=\"font-size:16px;color:#00aaff\">" + Integer.toString(filescount) + "</b><br>" + fname;
			writetext.write(fcnum);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
