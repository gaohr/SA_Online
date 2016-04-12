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
 * Servlet implementation class DeleteFile
 */
@WebServlet("/DeleteFile")
public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void deletefile( String path, String name) {
    	File forld = new File(path);
		File[] list =forld.listFiles();
		for(File files:list){
			if(files.isFile() && files.exists()){
				String filename = files.getName();
			    int dot = filename.lastIndexOf('.'); 
				String filenamesubs = filename.substring(0,dot);
			    if(name.equals(filenamesubs)) {
			    	files.delete();
			    }
			}
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		if(type.equals("text")) {
			//System.out.println(name);
			deletefile(CatchIP.fPath + "data/singlescenario/", name);
		}
		if(type.equals("file")) {
			deletefile(CatchIP.fPath + "data/BMPsetting/", name);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
