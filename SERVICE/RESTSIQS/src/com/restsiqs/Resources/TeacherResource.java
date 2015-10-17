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
import com.restsiqs.Entities.Teacher;
import com.restsiqs.Services.TeacherService;
import com.restsiqs.Utils.JsonUtils;

/*
 * This class is the public interface for this system.It mainly deal with academy table's work.
 * API:
 * HOST: http://hostname:<port>/RESTSIQS/
 * FUNCTIONS:
 * 		Add:                  |POST /teacher/teacher
 * 		Delete:               |DELETE /teacher/{id}
 * 		Update:               |PUT /teacher/teacher
 * 		FindAll:              |GET /teacher/teacherlist
 * 		Find by primary key:  |GET /teacher/{id}
 * 		
 * Created by devouty on 2015/10/13.
 * 
 */

@RequestMapping("teacher")
@Controller
public class TeacherResource {
	@Autowired
	private TeacherService teacherService;
	private static final Logger log = LoggerFactory
			.getLogger(TeacherResource.class);

	// Add
	@RequestMapping(value = "/teacher", method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestParam("teacher") Teacher teacher) {
		log.debug("Starting add teacher:" + teacher.getTeacherId());
		JSONObject obj = null;
		try {
			teacherService.save(teacher);
			obj = new JsonUtils(teacher).getJsonObject();
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
		teacherService.delete(id);
		log.debug("Delete " + id + " successful");
		return "Delete " + id + " successful";
	}

	// Update
	@RequestMapping(value = "/teacher", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@RequestParam("teacher") Teacher teacher) {
		log.debug("Starting update teacher:" + teacher.getTeacherId());
		try {
			teacherService.save(teacher);
			JSONObject obj = new JsonUtils(teacher).getJsonObject();
			log.debug("Update successful");
			return obj.toJSONString();
		} catch (Exception e) {
			log.debug("Update failed");
			e.printStackTrace();
			return null;
		}
	}

	// Find all
	@RequestMapping(value = "/teacherlist", method = RequestMethod.GET)
	@ResponseBody
	public String getAll() {
		List<Teacher> list = teacherService.findAll();
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
		JSONObject obj = new JsonUtils(teacherService.findById(id))
				.getJsonObject();
		return obj.toJSONString();
	}
}