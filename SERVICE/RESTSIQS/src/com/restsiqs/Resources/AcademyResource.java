package com.restsiqs.Resources;

import java.util.HashMap;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.restsiqs.Services.AcademyService;
import com.restsiqs.Utils.JsonUtils;

/*
 * This class is the public interface for this system.It mainly deal with academy table's work.
 * API:
 * HOST: http://hostname:<port>/RESTSIQS/
 * FUNCTIONS:
 * 		Add:                  |POST /academy
 * 		Delete:               |DELETE /academy/{id}
 * 		Update:               |PUT /academy/{id}
 * 		QueryAll:             |GET /academy/academylist
 * 		Query by primary key: |GET /academy?id=12345678
 * 		
 * Created by devouty on 2015/10/13.
 * 
 */

@RequestMapping("academy")
@Controller
public class AcademyResource {
	@Autowired
	private AcademyService academyService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	// @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ModelAndView getAll() {
		System.out.println(academyService.findAll());
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", academyService.findAll());
		mv.addObject("message", "mes");
		return mv;
	}

	@RequestMapping(value = "/test")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String test() {
		String s = "111111111111111111111111111111111111111111111";
		System.out.println(s);
		HashMap<String, String> hm = new HashMap<String, String>();
		JSONObject obj = new JsonUtils(academyService.findAll())
				.getJsonObject();

		return obj.toJSONString();
	}

}
