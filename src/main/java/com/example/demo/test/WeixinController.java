package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.HttpUtils;
import com.example.demo.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author zhangdongrun
 * @date 2017/11/28 下午5:02
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {

    @Value("${weixin.general}")
    private String weixinUrl;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/changeMenu")
    @ResponseBody
    public String changeMenu() {

        String url = weixinUrl + "cgi-bin/menu/create?access_token=";
        String token = "B807I0DiVCA2GFzGMGgkShI445DYzymBotlf7a3QBAF13IdJaDmbea6UvQ-rYAojZZCLY0m716f1_W2ppJQhbNC6qi8evCTi3UORI6xhenOdkC52jtF-Iw-FIBQQVZvRENYjAEAJSD";
        url = url + token;
        String req = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"miniprogram\",\"name\":\"wxa\",\"url\":\"http://mp.weixin.qq.com\",\"appid\":\"wx286b93c14bbf93aa\",\"pagepath\":\"pages/lunar/index\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
//        String res = HttpUtils.sendPost(url, req);
        String res = restTemplate.postForObject(url, req, String.class);
        return res;
    }

    @RequestMapping("/getIP")
    @ResponseBody
    public String getIP() {

        String url = weixinUrl + "cgi-bin/getcallbackip?access_token=" + "uHbW5N5GIgU9BkSQeBawkuCpCjTSu2IaZIGGmIohg6VuN7FfNKSHzcHIXfqk-g4Hg6ii90199n4qLevK1IlRwPhA1unx4kEvI_Q1WQy_ADoXmnRqhJ6hMsCTm81vWkwLMIHcACATPO";
        String req = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"miniprogram\",\"name\":\"wxa\",\"url\":\"http://mp.weixin.qq.com\",\"appid\":\"wx286b93c14bbf93aa\",\"pagepath\":\"pages/lunar/index\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
//        String res = HttpUtils.sendGet(url, "");
        String res = restTemplate.getForObject(url, String.class);
        return res;
    }

    @RequestMapping("/check")
    @ResponseBody
    public void check(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }

        out.close();
        out = null;
    }
}
