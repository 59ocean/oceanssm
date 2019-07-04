package com.ocean.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author chenhy
 * @date @time 2019/6/25 15:56
 */
public class BaseController {

	/**
	 * Convenience method to get the request
	 *
	 * @return current request
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
	}

	/**
	 * Convenience method to get the response
	 *
	 * @return current response
	 */
	protected HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
	}

	/**
	 * Convenience method to get the session. This will create a session if one
	 * doesn't exist.
	 *
	 * @return the session from the request (request.getSession()).
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
}
