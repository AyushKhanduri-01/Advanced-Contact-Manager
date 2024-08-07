package com.acm.acm.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;


//! user to delete one time use session value. (Used in login and registration page)
@Component
public class SessionRemove {
    public  static void removeSession(){
        try{
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("messageFail");
            session.removeAttribute("messageSuccess");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
