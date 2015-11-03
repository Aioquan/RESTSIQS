package com.restsiqs.Resources;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.restsiqs.Entities.Academy;
import com.restsiqs.Services.AcademyService;
import com.restsiqs.Utils.JsonUtils;
import com.restsiqs.Utils.URLAccepter;

/*
 * This class is the public interface for this system.It mainly deal with academy table's work.
 * API:
 * HOST: http://hostname:<port>/RESTSIQS/
 * FUNCTIONS:
 * 		Add:                  |POST /academy/academy
 * 		Delete:               |DELETE /academy/{id}
 * 		Update:               |PUT /academy/academy
 * 		FindAll:              |GET /academy/academylist
 * 		Find by primary key:  |GET /academy/{id}
 * 		
 * Created by devouty on 2015/10/13.
 * 
 */

@RequestMapping("academy")
@Controller
public class AcademyResource {
	@Autowired
	private AcademyService academyService;
	private static final Logger log = LoggerFactory
			.getLogger(AcademyResource.class);

	// Test for connection
	@RequestMapping(value = "/test")
	@ResponseBody
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String test() {
		String s = "111111111111111111111111111111111111111111111";
		System.out.println(s);
		JSONObject obj = new JsonUtils(academyService.findAll())
				.getJsonObject();
		return obj.toJSONString();
	}

	// Add
	@RequestMapping(value = "/academy/{pojo}", method = RequestMethod.POST)
	@ResponseBody
	public String add(@PathVariable String pojo) {
		log.debug("Starting add academy");

		try {
			pojo = URLAccepter.decrpt(pojo);
			Academy academy = new Gson().fromJson(pojo, Academy.class);
			academyService.save(academy);
			// obj = new JsonUtils(academy).getJsonObject();
			log.debug("Add successful");
			// return obj.toJSONString();

		} catch (Exception e) {
			log.debug("Add failed");
			e.printStackTrace();
			// return null;
		}
		return pojo;
	}

	// Delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable final String id) {
		academyService.delete(id);
		log.debug("Delete " + id + " successful");
		return "Delete " + id + " successful";
	}

	// Update
	@RequestMapping(value = "/academy/{pojo}", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@PathVariable String pojo) {
		log.debug("Starting update academy");
		try {
			pojo = URLAccepter.decrpt(pojo);
			Academy academy = new Gson().fromJson(pojo, Academy.class);
			academyService.update(academy);
			// JSONObject obj = new JsonUtils(academy).getJsonObject();
			log.debug("Update successful");

		} catch (Exception e) {
			log.debug("Update failed");
			e.printStackTrace();
			// return null;
		}
		return pojo;
	}

	// Find all
	@RequestMapping(value = "/academylist", method = RequestMethod.GET)
	@ResponseBody
	public String getAll() {
		List<Academy> list = academyService.findAll();
		return new JsonUtils(list).getJsonObject().toJSONString();

		// test block

		// System.out.println(academyService.findAll());
		// ModelAndView mv = new ModelAndView();
		// mv.addObject("list", academyService.findAll());
		// mv.addObject("message", "mes");
		// return mv;
	}

	// Find by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String findById(@PathVariable final String id) {
		JSONObject obj = new JsonUtils(academyService.findById(id))
				.getJsonObject();
		return obj.toJSONString();
	}
}
