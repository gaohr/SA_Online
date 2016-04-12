package indi.myproject.seim;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Getfiles
 */
@WebServlet("/Getfiles")
public class Getfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
	public static String Getfilesn(String path,String subfolder, String filet) {
		  File file=new File(path);
		  File[] tempList = file.listFiles();
		  filet += "<tr><td colspan=\"2\"><i class=\"datafolder  icon-folder-open\"></i>field_data/" + subfolder + "("+tempList.length + ")</td></tr>";
		  for (int i = 0; i < tempList.length; i++) {
		   if (tempList[i].isFile()) {
			   filet += "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<i class=\"datafile icon-file-text\"></i><a href=\"\">" + tempList[i].getName() + "</a></td><td>../data/field_data" + subfolder + "</td></tr>";
		   }
		  }
		return filet;
	}
	public static String Getfoldern(String path,String subfolder, String foldert) {
		  File file=new File(path);
		  File[] tempList = file.listFiles();		 
		  for (int i = 0; i < tempList.length; i++) {
			   if (tempList[i].isDirectory()) {
				   subfolder = tempList[i].getName();
				   foldert = Getfilesn(path + "/" + subfolder,subfolder + "/","");
			   }
			  }
		return foldert;
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter getfiles = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8"); 
		
		getfiles.write(Getfilesn(CatchIP.fPath + "data/field_data","","") + Getfoldern(CatchIP.fPath + "data/field_data","",""));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
