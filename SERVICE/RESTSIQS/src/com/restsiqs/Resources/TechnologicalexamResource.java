package com.restsiqs.Resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.restsiqs.Entities.Technologicalexam;
import com.restsiqs.Services.TechnologicalexamService;
import com.restsiqs.Utils.JsonUtils;

/*
 * This class is the public interface for this system.It mainly deal with academy table's work.
 * API:
 * HOST: http://hostname:<port>/RESTSIQS/
 * FUNCTIONS:
 * 		Add:                  |POST /technologicalexam/technologicalexam
 * 		Delete:               |DELETE /technologicalexam/{id}
 * 		Update:               |PUT /technologicalexam/technologicalexam
 * 		FindAll:              |GET /technologicalexam/technologicalexamlist
 * 		Find by primary key:  |GET /technologicalexam/{id}
 * 		
 * Created by devouty on 2015/10/13.
 * 
 */

@RequestMapping("technologicalexam")
@Controller
public class TechnologicalexamResource {
	@Autowired
	private TechnologicalexamService TechnologicalexamService;
	private static final Logger log = LoggerFactory
			.getLogger(TechnologicalexamResource.class);

	// Add
	@RequestMapping(value = "/technologicalexam", method = RequestMethod.POST)
	@ResponseBody
	public String add(
			@RequestParam("technologicalexam") Technologicalexam technologicalexam) {
		log.debug("Starting add technologicalexam:"
				+ technologicalexam.getTid());
		JSONObject obj = null;
		try {
			TechnologicalexamService.save(technologicalexam);
			obj = new JsonUtils(technologicalexam).getJsonObject();
			log.debug("Add successful");
			return obj.toJSONString();
		} catch (Exception e) {
			log.debug("Add failed");
			e.printStackTrace();
			return null;
		}
	}

	// Delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable final String id) {
		TechnologicalexamService.delete(id);
		log.debug("Delete " + id + " successful");
		return "Delete " + id + " successful";
	}

	// Update
	@RequestMapping(value = "/technologicalexam", method = RequestMethod.PUT)
	@ResponseBody
	public String update(
			@RequestParam("technologicalexam") Technologicalexam technologicalexam) {
		log.debug("Starting update technologicalexam:"
				+ technologicalexam.getTid());
		try {
			TechnologicalexamService.save(technologicalexam);
			JSONObject obj = new JsonUtils(technologicalexam).getJsonObject();
			log.debug("Update successful");
			return obj.toJSONString();
		} catch (Exception e) {
			log.debug("Update failed");
			e.printStackTrace();
			return null;
		}
	}

	// Find all
	@RequestMapping(value = "/technologicalexamlist", method = RequestMethod.GET)
	@ResponseBody
	public String getAll() {
		List<Technologicalexam> list = TechnologicalexamService.findAll();
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
		JSONObject obj = new JsonUtils(TechnologicalexamService.findById(id))
				.getJsonObject();
		return obj.toJSONString();
	}
}