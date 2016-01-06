package com.jurin_n.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.jurin_n.domain.model.identity.AuthenticationService;
import com.jurin_n.domain.model.identity.user.UserDescriptor;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	@Inject private AuthenticationService auth;
	
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HashMap<String,String> map = new HashMap<>();
		map.put("Authorization", httpRequest.getHeader("Authorization"));
		map.put("Date", "dummy");//TODO:Dateヘッダ取得するように変更。
		
		UserDescriptor userDescriptor = auth.authenticateFromHeader(map);

		if(userDescriptor!=null){
			//認証で取得できたユーザ情報をリクエストスコープに格納
			httpRequest.setAttribute("userDescriptor", userDescriptor);
			
			//次のフィルターに遷移
			chain.doFilter(request, response);
		}else{
			//認証エラーレスポンス
			// TODO:JacksonつかってJSONレスポンスにする
			response.getOutputStream().write(new String("Authenticationエラー").getBytes());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
