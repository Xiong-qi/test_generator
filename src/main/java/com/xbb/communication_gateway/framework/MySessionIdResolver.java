package com.xbb.communication_gateway.framework;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;
import com.xbb.communication_gateway.kit.StringKit;


/**
 * 重写spring session   
 * sessionId解析方法实现token功能
 * @author xubinbin
 */
public class MySessionIdResolver implements HttpSessionIdResolver {

    private static final String WRITTEN_SESSION_ID_ATTR = CookieHttpSessionIdResolver.class.getName().concat(".WRITTEN_SESSION_ID_ATTR");
    private CookieSerializer cookieSerializer = new DefaultCookieSerializer();

    public MySessionIdResolver() {
    }

    public List<String> resolveSessionIds(HttpServletRequest request) {
    		String sessionId = request.getHeader("SESSION");
    		if (StringKit.isEmpty(sessionId)) {
    			sessionId = request.getParameter("SESSION");
    		}
        if (!StringKit.isEmpty(sessionId)){
        		List<String> matchingCookieValues = new ArrayList<String>();
            //matchingCookieValues.add(base64Decode(sessionId));
            matchingCookieValues.add(sessionId);
            return matchingCookieValues;
        }
        else{
            return this.cookieSerializer.readCookieValues(request);
        }
    }

    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        if (!sessionId.equals(request.getAttribute(WRITTEN_SESSION_ID_ATTR))) {
            request.setAttribute(WRITTEN_SESSION_ID_ATTR, sessionId);
            this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, sessionId));
        }
    }

    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, ""));
    }

    public void setCookieSerializer(CookieSerializer cookieSerializer) {
        if (cookieSerializer == null) {
            throw new IllegalArgumentException("cookieSerializer cannot be null");
        } else {
            this.cookieSerializer = cookieSerializer;
        }
    }

    public String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
            return new String(decodedCookieBytes);
        }
        catch (Exception e) {
            return null;
        }
    }

}
