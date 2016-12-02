package com.ycc.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycc.domain.User;
import com.ycc.factory.BasicFactory;
import com.ycc.service.UserService;
import com.ycc.utils.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		try{
			//1.校验验证码
			String valistr1 = request.getParameter("valistr");
			String valistr2 = (String) request.getSession().getAttribute("valistr");
			if(valistr1 == null || valistr2 == null || !valistr1.equals(valistr2)){
				request.setAttribute("msg", "<font color='red'>验证码不正确!</font>");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
				return;
			}
			//2.封装数据*校验数据
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			user.setPassword(MD5Utils.md5(user.getPassword()));
			//3.调用Service注册用户
			service.regist(user);

			//4.回到主页
			response.getWriter().write("注册成功,请到邮箱中进行激活...");
			response.setHeader("Refresh", "3;url=/index.jsp");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
