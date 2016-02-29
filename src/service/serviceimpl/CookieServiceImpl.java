package service.serviceimpl;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookieProvider;
import service.CookieService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by wn13 on 2016/2/14.
 */
public class CookieServiceImpl implements CookieService {
    @Override
    public void addCookie(String name, String value) {
        String val= null;
        try {
            val = URLEncoder.encode(value,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(name, val);
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*365);
        ServletActionContext.getResponse().addCookie(cookie);
    }

    @Override
    public String getCookie(String name) {
        HttpServletRequest request = ServletActionContext.getRequest();
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(name))
            {
                try {
                    return URLDecoder.decode(cookie.getValue(),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

//    @Override
//    public void delCookie(String name) {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        Cookie[] cookies = request.getCookies();
//        for(Cookie cookie : cookies) {
//            if(cookie.getName().equals(name)) {
//                Cookie c=new Cookie(name,"");
//                c.setDomain("127.0.0.1");
//                cookie.setPath("/");
//                cookie.setMaxAge(0);
//                ServletActionContext.getResponse().addCookie(c);
//            }
//        }
//    }


}
