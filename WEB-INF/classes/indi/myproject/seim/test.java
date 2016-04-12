package indi.myproject.seim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
/*获取IP地址*/
public class test
{
	
	public static void main(String[] args) throws FileNotFoundException, JSONException 
	{
//		displaytext();
		File file = new File("D://Data Files//EasyGeoC//workplace-luna-cn//webproject//WebContent//JSON//fieldjson.geojson");
        FileReader fr = new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
       try {
		String data = br.readLine();
		String s;
		while((s= br.readLine()) != null){
			data += s;
		}
		 JSONTokener jsonParser = new JSONTokener(data);
		 JSONObject jsonO;
		 JSONObject area = new JSONObject();
		try {
			jsonO = (JSONObject) jsonParser.nextValue();
			 for(int i = 0; i < jsonO.getJSONArray("features").length(); i++){
				String ID = jsonO.getJSONArray("features").getJSONObject(i).getJSONObject("properties").getString("ObjectID");
				String oArea = jsonO.getJSONArray("features").getJSONObject(i).getJSONObject("properties").getString("Area");
				area.put(ID, oArea);				
			}
			 System.out.println(area);
			 String newfile = "D://Data Files//EasyGeoC//workplace-luna-cn//webproject//WebContent//JSON//objectArea.json";				 
			 File newTextFile = new File(newfile);
			 FileWriter fw = new FileWriter(newTextFile);
			 fw.write(area.toString());
			 fw.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void displaytext() {
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader bw = new BufferedReader(new FileReader(new File ("D:/Data Files/EasyGeoC/workplace-luna-cn/webproject/WebContent/data/output/all_pop.out")));
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
    	      BigDecimal text1_tr = new BigDecimal(text1[i]); 
    		  BigDecimal text2_tr = new BigDecimal(text2[i]); 
			  try {
  				  for(int j = 1; j < abc.length - 6; j++){
  					  JSONObject Block = new JSONObject();
  					  Block.put("BMP", abc[j + 6]).put("ObjectID", j);
  					  bmps.put(Block);
  				  }
  				  String point = "p" + (int)Math.floor(Double.valueOf(text2_tr.toPlainString()));
  				  allbmps.put(point, bmps);
  				if(point.equals("p454697")) {
  					System.out.println(bmps);
  				}
//  				System.out.println(bmps);
  				  allpoints.put("Blocks", allbmps);
  				System.out.println("p" + (int)Math.floor(Double.valueOf(text2_tr.toPlainString())));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
//		return allpoints.toString();
	}
}
