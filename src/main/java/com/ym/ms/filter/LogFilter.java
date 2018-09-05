package com.ym.ms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;

/**
 * 日志拦截器
 *
 * @author sys
 * @date 9.5
 */
@Order(1)
@WebFilter(filterName = "Slf4jFilter", urlPatterns = "/*")
public class LogFilter implements Filter {
	private static final String SESSION_TOKEN = "sessionToken";//=log_id
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		this.insertIntoMDC(req, resp);
//		this.insertLogId(req, resp);
		try {
			chain.doFilter(req, resp);
		} finally {
			this.clearMDC();
		}
	}

	
	/*private void insertLogId(ServletRequest request, ServletResponse resp) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) resp;
		String logId = String.valueOf(new Date().getTime());
		if (req.getHeader("log_id") == null || req.getHeader("log_id").length() == 0) {
			logger.error("set log_id:" + logId);
		} else {
			logId = req.getHeader("log_id");// 从请求端获取log_id
			logger.error("get log_id from request:" + logId);
		}

		MDC.put("log_id", logId);
		res.setHeader("log_id", logId);
	}*/

	void insertIntoMDC(ServletRequest request, ServletResponse resp) {
		MDC.put("req.remoteHost", request.getRemoteHost());
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			MDC.put("req.requestURI", httpServletRequest.getRequestURI());
			StringBuffer requestURL = httpServletRequest.getRequestURL();
			if (requestURL != null) {
				MDC.put("req.requestURL", requestURL.toString());
			}

			MDC.put("req.method", httpServletRequest.getMethod());
			MDC.put("req.queryString", httpServletRequest.getQueryString());
			MDC.put("req.userAgent", httpServletRequest.getHeader("User-Agent"));
			MDC.put("req.xForwardedFor", httpServletRequest.getHeader("X-Forwarded-For"));
			String sesseionToken = httpServletRequest.getHeader(SESSION_TOKEN);
			if (StringUtils.isEmpty(sesseionToken)) {
				sesseionToken = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			}
			MDC.put(SESSION_TOKEN, sesseionToken);
		}
	}

	void clearMDC() {
		MDC.remove("req.remoteHost");
		MDC.remove("req.requestURI");
		MDC.remove("req.queryString");
		MDC.remove("req.requestURL");
		MDC.remove("req.method");
		MDC.remove("req.userAgent");
		MDC.remove("req.xForwardedFor");
		MDC.remove(SESSION_TOKEN);
		MDC.remove("log_id");
	}

	@Override
	public void destroy() {
	}
}