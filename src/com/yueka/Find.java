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

public class Find extends HttpServlet{
	
	/**
	 * ！！！！查找是否有重复！！！！！
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
		String studyid = request.getParameter("studyid");
		String phone = request.getParameter("phone");
		System.out.println("find is ready");
		
		boolean flag = false;
		
		try {
			response.setCharacterEncoding("utf-8");
			DBConnection b = new DBConnection();
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			ResultSet rs1 = b.executeQuery("select * from users where useridentify ='"+ identid + "'");
			while(rs1.next()){
				flag = true;
			}
			
			ResultSet rs2 = b.executeQuery("select * from users where userid ='"+ studyid + "'");
			while(rs2.next()){
				flag = true;
			}
			
			ResultSet rs3 = b.executeQuery("select * from users where userphone ='"+ phone + "'");
			while(rs3.next()){
				flag = true;
			}
			if(flag){
				obj.put("state", "success");
			}else{
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
