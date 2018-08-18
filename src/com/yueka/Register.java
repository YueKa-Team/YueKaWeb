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

public class Register extends HttpServlet{
	
	/**
	 * 安卓约咖注册服务器端
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
		String identid = request.getParameter("identid");
		String userid = request.getParameter("studyid");
		String phone = request.getParameter("phone");
		String userpwd = request.getParameter("password");
		String sex;
		int flag = (identid.charAt(14)-'0')%2;
		if(flag == 1){
			sex = "男";
		}else{
			sex = "女";
		}
		System.out.println("userid:" + userid + "  userpwd:" + userpwd +"正在试图注册");
		
		
		try {
			response.setCharacterEncoding("utf-8");
			DBConnection b = new DBConnection();
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			b.execute("insert into users (useridentify,usersex,userid,userpwd,userphone) values ('" + identid 
					+ "','" + sex + "','" + userid + "','" + userpwd + "','" + phone + "' );");
			System.out.println(userid + "注册成功！");
			obj.put("state", "success");
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
