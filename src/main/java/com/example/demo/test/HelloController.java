package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.FileUtil;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RefreshScope
@Controller
@RequestMapping("/demo")
public class HelloController {
	
	@Value("${spring.datasource.url}")
	private String url;

	@RequestMapping("/url")
	@ResponseBody
	public String from() {
		return this.url;
	}
	
	@RequestMapping(value = "/get1", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject Hello1(@RequestParam(value = "code") String code111, @RequestParam(value = "name") String name111){
		JSONObject res = new JSONObject();
		res.put("code", code111);
		res.put("name", name111);
		return res;
	}

	@RequestMapping(value = "/get2", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject Hello2(String code, String name){
		JSONObject res = new JSONObject();
		System.out.println("+++++++++++++name:"+name);
		res.put("code", code);
		res.put("name", name);
		return res;
	}

	@RequestMapping(value = "/get3", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject Hello3(@RequestParam(value = "code") String code, @RequestParam(value = "name") String name){
		JSONObject res = new JSONObject();
		res.put("code", code);
		res.put("name", name);
		return res;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject PostHello(@RequestBody User user){
		JSONObject res = new JSONObject();
		res.put("HelloWorld", user);
		return res;
	}

	@RequestMapping(value = "/post2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject PostHelloJson(@RequestBody User user){
		JSONObject res = new JSONObject();
		res.put("HelloWorld", user);
		return res;
	}

	@RequestMapping(value = "/post3", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject PostList(@RequestParam("idList[]") List<String> user){
		for(int i = 0; i<user.size(); ++i){
			System.out.println("user:"+user.get(i));
		}
		JSONObject res = new JSONObject();
		res.put("HelloWorld", user);
		return res;
	}

	@RequestMapping(value = "/post4", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject PostMap(@RequestParam Map<String, String> params){
		System.out.println("postmap");
		for(String key: params.keySet()){
			System.out.println("key= "+ key + " and value= " + params.get(key));
		}
		JSONObject res = new JSONObject();
		res.put("id", params.get("params[id]"));
		res.put("code", params.get("params[code]"));
		res.put("name", params.get("params[name]"));
		return res;
	}

	@RequestMapping(value = "/post5", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject PostJson(@RequestBody Map<String, String> params){
		System.out.println("PostJson");
		for(String key: params.keySet()){
			System.out.println("key= "+ key + " and value= " + params.get(key));
		}
		JSONObject res = new JSONObject();
		res.put("id", params.get("id"));
		res.put("code", params.get("code"));
		res.put("name", params.get("name"));
		return res;
	}

	@RequestMapping(value = "/postFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject postFile(@RequestParam("file") MultipartFile file) throws Exception{

		String name = file.getOriginalFilename();
		FileUtil.saveFile(name, file.getBytes());
		JSONObject res = new JSONObject();
		res.put("postFile", "ok");
		return res;
	}

	@RequestMapping(value = "/postParam", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject PostAddParams(@RequestParam(value = "urlParam") String urlParam, @RequestParam(value = "normalParam") String normalParam){

		JSONObject res = new JSONObject();
		res.put("urlParam", urlParam);
		res.put("normalParam", normalParam);
//		res.put("name", params.get("name"));
		return res;
	}

}
