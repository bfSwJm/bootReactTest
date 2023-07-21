package com.bf.ApiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.springframework.stereotype.Controller;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ApiTestController {

    @GetMapping("/userdata")
    public String apiDataController(HttpServletRequest request, Model model) {
        try {
            model.addAttribute("data", getUserInfoApi(request.getParameter("userid")));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "apiTest/userdata";
    }

    public Map<String, Object> getUserInfoApi (String userid) throws IOException {
        Map<String, Object> userInfoMap = new HashMap<>();

        String requestURL = "https://gw.bodyfriend.co.kr/api/user/erpInfo" + "?userId=" + userid;
        HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
        HttpGet getRequest = new HttpGet(requestURL); //메소드 URL 새성
        getRequest.setHeader("Accept", "application/json");
        getRequest.setHeader("Connection", "keep-alive");
        getRequest.setHeader("Content-Type", "application/json");

        HttpResponse response = client.execute(getRequest);
        ObjectMapper mapper = new ObjectMapper();

        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("### called getUserInfoApi [" + userid + "]");
            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = handler.handleResponse(response);
            if(body.indexOf("data") > -1){
                Map<String, Object> dataMap = mapper.readValue(body, Map.class);
                userInfoMap = (Map<String, Object>) dataMap.get("data");
            }
        }

        return userInfoMap;
    }

}

