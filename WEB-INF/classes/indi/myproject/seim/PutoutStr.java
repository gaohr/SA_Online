package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class PutoutStr
 */
@WebServlet("/PutoutStr")
public class PutoutStr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	void writeContent(String path) throws JSONException, IOException{
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
		try {
			jsonO = (JSONObject) jsonParser.nextValue();
			String arr[][] = new String[jsonO.getJSONArray("Blocks").length()][];
			List<String[]> list = new ArrayList<String[]>();
			for(int i = 0; i < jsonO.getJSONArray("Blocks").length(); i++){
				String[] arr1 = new String[6];
				for(int j = 0; j < jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs").length(); j++){
					Boolean has = jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs").getJSONObject(j).has("BMP");
					if(has) {
						arr1[j] = jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs").getJSONObject(j).getString("BMP");					
					}
				}
				arr[i] = arr1;
				list.add(arr[i]);
			}
			zuhe(list, list.get(0), "");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writejson = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8"); 
		String event = request.getParameter("content");
		
		if(event.equals("gettxt")){
			String textname = request.getParameter("textname");
			try {
				File forld = new File(CatchIP.fPath + "data/BMPs");
				File[] list =forld.listFiles();
				for(File files:list){
					if(files.isFile()){
						files.delete();
					}
				}
				writeContent(CatchIP.fPath + "data/BMPsetting/" + textname + ".json");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(event.equals("displaytxt")){
			File forld = new File(CatchIP.fPath + "data/BMPs");
			File[] list =forld.listFiles();
			int filescount = 0;
			String fname = "<ul>";
			for(File files:list){
				if(files.isFile()){
					String filename = files.getName();
					fname += "<li><a href=\"#\">" + filename + "</a></li>";
						filescount++;
				}
			}
			fname += "</ul>";
			String fcnum = "Number of single scenario:<b>" + Integer.toString(filescount) + "</b><br>" + fname;
			writejson.write(fcnum);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public static void zuhe(List<String[]> list, String[] arr, String str) {
        for (int i = 0; i < list.size(); i++) {
            //取得当前的数组
            if (i == list.indexOf(arr)) {
                //迭代数组，此处length为数组定义长度，而需要判断的是实数长度！！！
            	for (int j = 0; j < arr.length; j++) {
            		if(arr[j] != null) {
                	    String outstr = arr[j];
                        outstr = str + outstr;
                        if (i < list.size() - 1) {
                            zuhe(list, list.get(i + 1), outstr);
                        } else if (i == list.size() - 1) {
                        	File forld = new File(CatchIP.fPath + "data/BMPs");
                			File[] flist =forld.listFiles();
                			int filecount = 0;
                			for(File files:flist){
                				if(files.isFile()){
                				    String filename = files.getName();
                					String filenamesub = filename.substring(0,10);
//                					System.out.println(filenamesub);
                					if(filenamesub.equals("BMPSetting")){
                					    filecount++;
                				    }
                				}
                			}
                			
                			//写入txt
                			String newfile = CatchIP.fPath + "data/BMPs/BMPSetting_" + (filecount) + ".txt";				 
                			File newTxtFile = new File(newfile);
                			FileWriter fw;
                			try {
                				fw = new FileWriter(newTxtFile);
                				fw.write(outstr);
                				fw.close();
                			} catch (IOException e) {
                				// TODO Auto-generated catch block
                				e.printStackTrace();
                			}
                        }
                    }
                }
            }
        }
     }
	
	public void createFile(String path,String filename) throws IOException{
        File file=new File(path+"/"+filename);
        if(!file.exists())
            file.createNewFile();
    }

}
