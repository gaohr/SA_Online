package user;

import indi.myproject.seim.CatchIP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		String username = request.getParameter("username");          //获取username所对应的value
        String password = request.getParameter("password");           //获取password所对应的value
        String confirmpassword = request.getParameter("confirmpassword");
        String phonenumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();
        // 向请求端发回反馈信息
        String path = CatchIP.userPath + "userInfo.json";
        File file = new File(path);
        FileReader fr  = new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String data = br.readLine();
		String s;
		while((s= br.readLine()) != null){
			data += s;
		}
		JSONTokener jsonParser = new JSONTokener(data);
		JSONObject jsonO;
		Boolean repeat = false;
		Boolean same = password.equals(confirmpassword);
		try {
			jsonO = (JSONObject) jsonParser.nextValue();
			for(int i = 0; i < jsonO.getJSONArray("users").length(); i++){
				String uname = jsonO.getJSONArray("users").getJSONObject(i).getString("name");
				//判断用户名是否已存在
				if(username.equals(uname)) {
					repeat = true;
					break;
				}
			}
			if(!repeat && same && username != "") {
				JSONObject oneuser = new JSONObject();
				oneuser.put("name", username).put("password", password).put("tel", phonenumber).put("email", email);
				jsonO.getJSONArray("users").put(oneuser);
				//System.out.println(oneuser);
				createFolder(CatchIP.userPath + "userFolders/" + username);
				File TextFile = new File( CatchIP.userPath + "userInfo.json");
				FileWriter fw = new FileWriter(TextFile);
				fw.write(jsonO.toString());
				fw.close();
//				response.sendRedirect("/SA_Online/html/index.html");
//		        response.getOutputStream().flush();
//		        response.getOutputStream().close();
				out.println("<p style='color:#393;font-size:24px;text-align:center;padding:2em'>注册成功！</p>");
				out.println("<p style='color:#333;text-align:center'><span id='sec' style='font-size:20px;padding:5px;color:#e40'>3</span>秒后自动跳转至首页。</p>");
				out.println("<script>setTimeout(\"javascript:clearInterval(mytime);window.open('http://159.226.110.183:8080/SA_Online/html/')\",3000);"
				           +"function changeSec(){document.getElementById('sec').innerText = parseInt(document.getElementById('sec').innerText) - 1;}var mytime = setInterval('changeSec()',1000);</script>");
				out.flush();
				out.close();
			} else {
				out.println("<p style='color:red;text-align:center;padding:2em'>用户名重复注册或两次输入密码不匹配！</p>");
				out.println("<a style='float:center;margin-left:48%;padding:1em;border:1px solid #999;border-radius:5px;text-decoration:none;background:rgba(255,255,255,0.2)' href='html/Register.html'>返回</a>");
				out.flush();
				out.close();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//创建用户文件夹
	public void createFolder(String folderPath) {
        try {
            File newFilePath = new File(folderPath);
            newFilePath.mkdir();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }

}
