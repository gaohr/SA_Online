package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class GetUserInfo
 */
@WebServlet("/GetUserInfo")
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		String username = request.getParameter("un");
        PrintWriter result = response.getWriter();
        
        String path = "D:/Data files/EasyGeoC/SA_Online/SA_Online/WebContent/data/users/userInfo.json";
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
		JSONObject con = new JSONObject();
		try {
			jsonO = (JSONObject) jsonParser.nextValue();
			for(int i = 0; i < jsonO.getJSONArray("users").length(); i++){
				String uname = jsonO.getJSONArray("users").getJSONObject(i).getString("name");
				if(uname.equals(username)) {
					String password = jsonO.getJSONArray("users").getJSONObject(i).getString("password");
					String Tel = jsonO.getJSONArray("users").getJSONObject(i).getString("tel");
					String eMail = jsonO.getJSONArray("users").getJSONObject(i).getString("email");
					con.put("password", password);
					con.put("tel", Tel);
					con.put("email", eMail);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(con);
		result.write(con.toString());;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
