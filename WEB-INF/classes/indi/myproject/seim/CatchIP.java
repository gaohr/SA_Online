package indi.myproject.seim;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CatchIP
 */
@WebServlet("/CatchIP")
public class CatchIP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String fPath = "C:/Program Files (x86)/Apache Software Foundation/Tomcat 6.0/webapps/SA_Online/";
//	public static String fPath = "D:/Data Files/EasyGeoC/SA_Online-1.0/SA_Online/WebContent/";

	public static String userPath = "C:/Program Files (x86)/Apache Software Foundation/Tomcat 6.0/webapps/SA_Online/data/users/";
//	public static String userPath = "D:/Data Files/EasyGeoC/SA_Online-1.0/SA_Online/WebContent/data/users/";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter catchip = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8"); 
		
		CatchIP hlIP = new CatchIP();
		String HLip = hlIP.catchLocalIP().getHostAddress();
		catchip.write(HLip);
	}
	
	public InetAddress catchLocalIP() {
		InetAddress LocalIP = null;
		try {
			LocalIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {}
		return LocalIP;
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
