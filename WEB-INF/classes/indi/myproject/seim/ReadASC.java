package indi.myproject.seim;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadASC
 */
@WebServlet("/ReadASC")
public class ReadASC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    public String[] ReadASC() {
        // TODO Auto-generated constructor stub
    	File forld = new File("D:/seim_run/Debug/model_zhuxi_FCM_2015_storm/output");
		File[] list =forld.listFiles();
		String ascFileStr = "";
		for(File files:list){
			if(files.isFile()){
				String filename = files.getName();
				String type = filename.substring(filename.lastIndexOf(".") + 1);
				if(type.equals("asc")){
//					System.out.println(filename);
					ascFileStr += filename + ",";
			    }
			}
		}
		String nameArr[] = ascFileStr.split(",");
		return nameArr;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter res = response.getWriter();
		
		String ascfiles[] = ReadASC();
		String fpath = "D:/seim_run/Debug/model_zhuxi_FCM_2015_storm/output/";
		String filelist = "<ul>";
		for(int i = 0; i < ascfiles.length; i++) {
			String filename = ascfiles[i];
			String subfilename = filename.substring(0, filename.length()-4);
			filelist += "<li><a id=\"" + subfilename + "\" onclick=\"displayASC('" + fpath + "', '" + filename + "')\" href=\"###\">" + filename + "</a></li>";
		}
    	res.write(filelist);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
