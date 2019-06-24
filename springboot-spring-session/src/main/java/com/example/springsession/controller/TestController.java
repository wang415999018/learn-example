package com.example.springsession.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("/test")
    public  Map<String, Object> test(HttpServletRequest request){
        Map<String, Object> sessionIdMap = new HashMap<String, Object>();
        //获取sessionId
        String strSessionId = request.getSession().getId();
        //request.getSession().setAttribute("name","张三");
        int iPort = request.getServerPort();
        sessionIdMap.put("服务器端口：", iPort);
        sessionIdMap.put("sessionId：", strSessionId);
        sessionIdMap.put("name",request.getSession().getAttribute("name"));
        return sessionIdMap;
    }

}
