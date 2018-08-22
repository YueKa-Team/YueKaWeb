package com.yueka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.yueka.DBConnection;

public class Log extends HttpServlet{
	
	/**
	 * 安卓约咖用户登录log
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
		String sex = "";
		String phone = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		try {
			response.setCharacterEncoding("utf-8");
			DBConnection b = new DBConnection();
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			ResultSet rs = b.executeQuery("select * from users where userid ='"+ userid + "'");
			if(rs.next()){
				sex = rs.getString(3);
				phone = rs.getString(6);
				obj.put("state", "success");
				obj.put("sex", sex);
				obj.put("id", userid);
				obj.put("phone", phone);
			}else{
				obj.put("state", "false");
			}
			b.execute("insert into log (user,sex,phone,time) values ('" + userid 
					+ "','" + sex + "','" + phone + "','" + time + "' );");
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
