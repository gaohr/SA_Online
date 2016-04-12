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

/**
 * Servlet implementation class ReadPol_Sed
 */
@WebServlet("/ReadPol_Sed")
public class ReadPol_Sed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List ReadPol_Sed() {
        // TODO Auto-generated constructor stub
    	List<String> list = new ArrayList<String>();
		try {
			String filePath = "D:/seim_run/Debug/model_zhuxi_FCM_2015_storm/output/Pol_Sed.txt";
			BufferedReader bw = new BufferedReader(new FileReader(new File (filePath)));
			String line = null;
				while((line = bw.readLine())!=null){
					list.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return list;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter res = response.getWriter();
		
		List resultcon = ReadPol_Sed();
//    	String info = (String) resultcon.get(0);
    	String Pol_Sed = (String)resultcon.get(1);
    	
    	res.write(Pol_Sed);
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
