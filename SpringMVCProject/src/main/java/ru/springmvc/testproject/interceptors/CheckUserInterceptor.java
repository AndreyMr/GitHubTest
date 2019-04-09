package ru.springmvc.testproject.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ru.springmvc.testproject.objects.User;

public class CheckUserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		long starttime = System.currentTimeMillis();
		if (request.getRequestURI().contains("check-user")) {
			User user = (User) modelAndView.getModel().get("user");
			if (user == null || !user.isAdmin()) {
				long finishTime = System.currentTimeMillis() - starttime;
				modelAndView.addObject("finishTime", finishTime);
				// user.setTime(finishTime);
				response.sendRedirect(request.getContextPath() + "/failed");
			}

		}
	}

}
