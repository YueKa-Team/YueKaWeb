package com.yueka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.yueka.DBConnection;

public class Log extends HttpServlet{
	
	/**
	 * 安卓约咖登录服务器端
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		System.out.println("userid:" + userid +"登陆");
		
		boolean flag = false;
		
		try {
			response.setCharacterEncoding("utf-8");
			DBConnection b = new DBConnection();
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			ResultSet rs = b.executeQuery("select * from users where userid ='"+ userid + "'");
			if(rs.next()){
				String psd = rs.getString(5);
				String sex = rs.getString(3);
				String phone = rs.getString(6);
				if(psd.equals(userpwd)){
					obj.put("state", "success");
					obj.put("usersex", sex);
					obj.put("userphone", phone);
					flag = true;
				}else{
					obj.put("state", "false");
				}
			}else{
				obj.put("state", "false");
			}
			if(flag){
				System.out.println("用户登录成功");
			}else{
				System.out.println("用户登录失败");
			}
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public static void main(String[] args) {
	}

}
