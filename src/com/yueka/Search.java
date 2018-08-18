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

public class Search extends HttpServlet{
	
	/**
	 * ！！！！搜寻数据库，仅为管理员使用！！！！！
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
		String SearchType = request.getParameter("SearchType");
		String SearchData = request.getParameter("SearchData");
		System.out.println("SearchType:" + SearchType + "  SearchData:" + SearchData);
		
		boolean flag = false;
		
		try {
			response.setCharacterEncoding("utf-8");
			DBConnection b = new DBConnection();
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			ResultSet rs = b.executeQuery("select * from users where "+ SearchType +" ='"+ SearchData + "'");
			int i = 0;
			while(rs.next()){
				obj.put("state", "success");
				flag = true;
				JSONObject obj1 = new JSONObject();
				obj1.put("useridentify", rs.getString(2));
				obj1.put("usersex", rs.getString(3));
				obj1.put("userid", rs.getString(4));
				obj1.put("userpwd", rs.getString(5));
				obj1.put("userphone", rs.getString(6));
				
				obj.put("data"+i++, obj1);
			}
			if(!flag){
				obj.put("state", "false");
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
