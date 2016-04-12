package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Server;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class JsonObj {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String dataDir = ServletActionContext.getServletContext().getRealPath("");
		System.out.println();
		
//		File file = new File("D:/ensga2_serial/Debug/zhuxi_P/data/GADefFile.txt");		
//		FileReader fr = new FileReader(file);
//		BufferedReader br = new BufferedReader(fr);
//		String textcon[] = new String[44];
//		int i = 0;
//		String str;
//		while((str= br.readLine()) != null){
//			textcon[i] = str;
//			i++;
//		}
//		textcon[3] = 10 + "	80	80	//Initial, min and max population sizes (multiple of 4)  400";
//		textcon[6] = 40 + "			//Maximum generations per run  250";
//		textcon[7] = 400 + "			//Maximum number of function evaluations";
		
		//将修改参数后的数据写入文件
//		String newfile = "D:/ensga2_serial/Debug/zhuxi_P/data/GADefFile_1.txt";				 
//		File newTextFile = new File(newfile);
//		FileWriter fw = new FileWriter(newTextFile);
//		for(int j = 0; j < textcon.length - 1; j++ ){
//			fw.write(textcon[j] + "\r\n");
//			System.out.println(textcon[j]);
//		}
//		fw.close();
		
//		 File forld = new File("D:/seim_run/Debug/model_zhuxi_FCM_2015_storm/output");
//			File[] list =forld.listFiles();
//			for(File files:list){
//				if(files.isFile()){
//					String filename = files.getName();
//					String filepath = files.getPath();
//					String type = filename.substring(filename.lastIndexOf(".") + 1);
//					if(type.equals("asc")){
//						System.out.println(filename);
//						System.out.println(filepath);
//				    }
//				}
//			}
//		List<String> list = new ArrayList<String>();
//		String Qcon[] = null;
//		try {
//			String filePath = "D:/seim_run/Debug/model_zhuxi_FCM_2015_storm/output/1_Q_OUTLET.txt";
//			BufferedReader bw = new BufferedReader(new FileReader(new File (filePath)));
//			String line = null;
//			try {
//				while((line = bw.readLine())!=null){
//					list.add(line);
//				}
//				String arr[] = new String[list.size()];
//				String date[] = new String[list.size()];
//				String Q_outlet[] = new String[list.size()];
//				for(int i = 0;i < list.size(); i++){
//		    		  arr[i] = list.get(i).toString();
//		    		  String strr = arr[i].trim();
//		    	      String[] singleline = strr.split("[\\p{Space}]+");
//		    	      date[i] = singleline[0] + " " + singleline[1];
//		    	      Q_outlet[i] = singleline[2];
//					System.out.println(Q_outlet[i]);
//				}
//				Qcon = Q_outlet;
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}


		
//		Runtime run = Runtime.getRuntime();
//		Process pro = null;
//		try {
////        	pro = run.exec("cmd /c start D:/seim_run/Debug/seim.exe model_zhuxi_FCM_2015_storm 8 88 0 0 0 0 1 4 4 0 6 0 0 0 2 4 0 6 4 3 3 1 4 0 0 1 0 0 0 0 0 4 0 3 1 2 2 3 1 6 1 0 0 1 0 0 0 1 6 1 0 1 6 6 1 0 0 0 1 6 1 6 6 1 0 0 0 1 0 1 1 1 1 2 4 2 5 5 2 6 1 4 0 0 1 0 0 0 1 0");
//			pro = run.exec("cmd.exe /c start D:/protest.exe");
//			pro.waitFor();
//            System.out.println("Successful!!");
//        } catch (Exception e) {
//            System.out.println("Error!");
//       }
	

//		JSONObject blocks = new JSONObject();
//		JSONArray bmps = new JSONArray();
//		
//		for(int i = 1; i < 89; i++){
//			JSONObject block = new JSONObject();
//		    try {
//		    	block.put("BMP", 0).put("ObjectID", i);
//		    	bmps.put(block);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		    try {
//		    	
//		    	blocks.put("blocks", bmps);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		    System.out.println(bmps.length() + blocks.toString());
	    
		
		  
//		PrintWriter out = new PrintWriter(new FileOutputStream("1.txt"));
//		out.println(jsonStringer.toString());
//		    out.close();
		
//	        File file = new File("D://Data Files//EasyGeoC//workplace-luna//webproject//WebContent//JSON//SetBMPss_1.json");
//	        FileReader fr = new FileReader(file);
//	        BufferedReader br=new BufferedReader(fr);
//	       try {
//			String data = br.readLine();
//			String s;
//			while((s= br.readLine()) != null){
//				data += s;
//			}
//			 JSONTokener jsonParser = new JSONTokener(data);
//			 JSONObject jsonO;
//			try {
//				jsonO = (JSONObject) jsonParser.nextValue();
//				 System.out.println(jsonO);
//				 System.out.println(jsonO.getJSONArray("Blocks").length());
				 
				 
//				 for(int i = 0; i < jsonO.getJSONArray("Blocks").length(); i++){
//					 JSONArray bmps = jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs");
//					 String block = jsonO.getJSONArray("Blocks").getJSONObject(i).getString("ObjectID");												
//						if(block.equals("1")){
//							JSONObject addbmp = new JSONObject("{'BMP':" + "\'" + block + "\'" + "}");
//							JSONObject addbmp = new JSONObject("{'BMP':'1'}");
//							boolean putbmp = true;
//							for(int j = 0; j < bmps.length(); j++){//删除已存在对象要素
//								Boolean has = bmps.getJSONObject(j).has("BMP");
//								if(has){
//								    String bmpvalue = bmps.getJSONObject(j).getString("BMP");
//							        if(bmpvalue.equals("1")){
//									    bmps.getJSONObject(j).remove("BMP");
//									    putbmp = false;
//								    } 
//								}
//							}
//							if(putbmp){
//							    bmps.put(addbmp);
//							}
//						}
//					}

//				 File forld = new File("D://Data Files//EasyGeoC//workplace-luna//webproject//WebContent/JSON");
//					File[] list =forld.listFiles();
//					int filecount = 0;
//					JSONArray jsonArray = new JSONArray();
//			        System.out.println(jsonArray); 
//					for(File files:list){
//						if(files.isFile()){
//						    String filename = files.getName();
//							String filenamesub = filename.substring(0,8);
//							String filenamesubs = filename.substring(0,10);
//							if(filenamesub.equals("SetBMPss")){
//							    filecount++;
//						        jsonArray.put(filenamesubs);
//						    }
//						}
//					}
//				    System.out.println(jsonArray);
//				    
//				 String newfile = "D://Data Files//EasyGeoC//workplace-luna//webproject//WebContent//JSON//SetBMPss_" + filecount + ".json";				 
//				 File newTextFile = new File(newfile);
//				 FileWriter fw = new FileWriter(newTextFile);
//				 fw.write(jsonO.toString());
//				 fw.close();
//				 System.out.println("Number:" + filecount);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		File file = new File("D://Data Files//EasyGeoC//workplace-luna//webproject//WebContent//JSON//SetBMPss_1.json");
//	    FileReader fr;
//		fr = new FileReader(file);
//		BufferedReader br=new BufferedReader(fr);
//		String data = br.readLine();
//		String s;
//		while((s= br.readLine()) != null){
//			data += s;
//		}
//		JSONTokener jsonParser = new JSONTokener(data);
//		JSONObject jsonO;
//		try {
//			jsonO = (JSONObject) jsonParser.nextValue();
//			String arr[][] = new String[jsonO.getJSONArray("Blocks").length()][];
//			List<String[]> list = new ArrayList<String[]>();
//			for(int i = 0; i < jsonO.getJSONArray("Blocks").length(); i++){
//				String[] arr1 = new String[6];
//				for(int j = 0; j < jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs").length(); j++){
//					arr1[j] = jsonO.getJSONArray("Blocks").getJSONObject(i).getJSONArray("BMPs").getJSONObject(j).getString("BMP");					
//				}
//				arr[i] = arr1;
//				list.add(arr[i]);
//			}
//			zuhe(list, list.get(0), "");
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//	}
 
//	public static void zuhe(List<String[]> list, String[] arr, String str) {
//        for (int i = 0; i < list.size(); i++) {
//            //取得当前的数组
//            if (i == list.indexOf(arr)) {
//                //迭代数组，此处length为数组定义长度，而需要判断的是实数长度！！！
//            	for (int j = 0; j < arr.length; j++) {
//            		if(arr[j] != null) {
//                	    String outstr = arr[j];
//                        outstr = str + outstr;
//                        if (i < list.size() - 1) {
//                            zuhe(list, list.get(i + 1), outstr);
//                        } else if (i == list.size() - 1) {
//                        	File forld = new File("D://Data Files//EasyGeoC//workplace-luna//webproject//WebContent//data");
//                			File[] flist =forld.listFiles();
//                			int filecount = 0;
//                			for(File files:flist){
//                				if(files.isFile()){
//                				    String filename = files.getName();
//                					String filenamesub = filename.substring(0,10);
//                					System.out.println(filenamesub);
//                					if(filenamesub.equals("BMPSetting")){
//                					    filecount++;
//                				    }
//                				}
//                			}
//                			//写入txt
//                			String newfile = "D://Data Files//EasyGeoC//workplace-luna//webproject//WebContent//data//BMPSetting_" + (filecount) + ".txt";				 
//                			File newTxtFile = new File(newfile);
//                			FileWriter fw;
//                			try {
//                				fw = new FileWriter(newTxtFile);
//                				fw.write(outstr);
//                				fw.close();
//                			} catch (IOException e) {
//                				// TODO Auto-generated catch block
//                				e.printStackTrace();
//                			}
//                        }
//                    }
//                }
//            }
//        }
//     }
	}
}
