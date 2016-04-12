package indi.myproject.seim;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class DisplayASC
 */
@WebServlet("/DisplayASC")

public class DisplayASC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayASC() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static GridFileHead ReadGridFileHead(String filePath){
	    GridFileHead gridUnit = new GridFileHead();
	        try{
	            DataInputStream in = new DataInputStream(new FileInputStream(filePath));
	            int fileSize = 512;
	            byte[] btData = new byte[fileSize];
	            in.read(btData);
	            String str = new String(btData);
	            String splitTab = "\t";
	            String []strData = str.split("\n");
	            int tabIndex = strData[0].indexOf(splitTab);
	            if(tabIndex == -1){
	                splitTab = " ";
	            }
	            String []tempData = strData[0].split(splitTab);
	            gridUnit.ncols = Integer.parseInt(tempData[tempData.length-1].trim());
	            tempData = strData[1].split(splitTab);
	            gridUnit.nrows = Integer.parseInt(tempData[tempData.length-1].trim());
	            tempData = strData[2].split(splitTab);
	            gridUnit.xllcorner = Double.parseDouble(tempData[tempData.length-1].trim());
	            tempData = strData[3].split(splitTab);
	            gridUnit.yllcorner = Double.parseDouble(tempData[tempData.length-1].trim());
	            tempData = strData[4].split(splitTab);
	            gridUnit.cellsize = Double.parseDouble(tempData[tempData.length-1].trim());
	            tempData = strData[5].split(splitTab);
	            gridUnit.NODATA_value = Integer.parseInt(tempData[tempData.length-1].trim());
	            in.close();
	        }
	        catch(IOException ex){
	        	ex.printStackTrace();
	        }
	        return gridUnit;
	    }

	////////////////////////////////////////////////////////////////////////////////
	//读取栅格数据到一个二维浮点数组
	////////////////////////////////////////////////////////////////////////////////
	public static double[][] readGridFileToDoubleArray2D(String filePath){
	    double[][] resultData=null;
	    try{
	        DataInputStream in = new DataInputStream(new FileInputStream(filePath));
	        int fileSize = in.available();
	        byte []btData = new byte[fileSize];
	        in.read(btData);
	        String str = new String(btData);
	        String splitTab = "\t";
	        String []strData = str.split("\n");
	        int tabIndex = strData[0].indexOf(splitTab);
	        if(tabIndex == -1){
	            splitTab = " ";
	        }
	        String []tempData = strData[0].split(splitTab);
	        int ncols = Integer.parseInt(tempData[tempData.length-1].trim());
	        tempData = strData[1].split(splitTab);
	        int nrows = Integer.parseInt(tempData[tempData.length-1].trim());
	        resultData = new double[nrows][ncols];
	        splitTab = "\t";
	        tabIndex = strData[6].indexOf(splitTab);
	        if(tabIndex == -1){
	            splitTab=" ";
	        }
	        for(int i = 0; i < nrows; i++){
	            tempData = strData[i + 6].split(splitTab);
	            for(int j = 0; j < ncols; j++){
	                resultData[i][j] = Double.parseDouble(tempData[j]);
	            }
	        }
	        in.close();
	    }catch(IOException ex){
	    	ex.printStackTrace();
	    }
	    //System.out.println(resultData[1][1]);
	    return resultData;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter res = response.getWriter();
		
		String filepath = request.getParameter("fp");
		String filename = request.getParameter("fn");
		
		GridFileHead gridHead = ReadGridFileHead(filepath + filename);
		
		double[][] ascFile = new double[gridHead.nrows][gridHead.ncols];
		ascFile = readGridFileToDoubleArray2D(filepath + filename);
		JSONObject ASC = new JSONObject();
		JSONArray ascCon = new JSONArray();
		for(int i = 0; i < gridHead.nrows; i++){
			for(int j = 0; j < gridHead.ncols; j++) {
				try {

					JSONObject ascCell = new JSONObject();
					ascCell.put("x", i).put("y", j).put("ascvalue", ascFile[i][j]);
					ascCon.put(ascCell);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			ASC.put("NCOLS", gridHead.ncols).put("NROWS", gridHead.nrows).put("XLLCENTER", gridHead.xllcorner).put("YLLCENTER", gridHead.yllcorner).put("CELLSIZE", gridHead.cellsize);
			ASC.put("value", ascCon);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.write(ASC.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
