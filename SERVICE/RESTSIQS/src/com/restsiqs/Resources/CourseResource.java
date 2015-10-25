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
import com.restsiqs.Entities.Course;
import com.restsiqs.Services.CourseService;
import com.restsiqs.Utils.JsonUtils;

/*
 * This class is the public interface for this system.It mainly deal with academy table's work.
 * API:
 * HOST: http://hostname:<port>/RESTSIQS/
 * FUNCTIONS:
 * 		Add:                  |POST /course/course
 * 		Delete:               |DELETE /course/{id}
 * 		Update:               |PUT /course/course
 * 		FindAll:              |GET /course/courselist
 * 		Find by primary key:  |GET /course/{id}
 * 		
 * Created by devouty on 2015/10/13.
 * 
 */

@RequestMapping("course")
@Controller
public class CourseResource {
	@Autowired
	private CourseService courseService;
	private static final Logger log = LoggerFactory
			.getLogger(CourseResource.class);

	// Add
	@RequestMapping(value = "/course", method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestParam("course") Course course) {
		log.debug("Starting add course:" + course.getCourseId());
		JSONObject obj = null;
		try {
			courseService.save(course);
			obj = new JsonUtils(course).getJsonObject();
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
		courseService.delete(id);
		log.debug("Delete " + id + " successful");
		return "Delete " + id + " successful";
	}

	// Update
	@RequestMapping(value = "/course", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@RequestParam("course") Course course) {
		log.debug("Starting update course:" + course.getCourseId());
		try {
			courseService.save(course);
			JSONObject obj = new JsonUtils(course).getJsonObject();
			log.debug("Update successful");
			return obj.toJSONString();
		} catch (Exception e) {
			log.debug("Update failed");
			e.printStackTrace();
			return null;
		}
	}

	// Find all
	@RequestMapping(value = "/courselist", method = RequestMethod.GET)
	@ResponseBody
	public String getAll() {
		List<Course> list = courseService.findAll();
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
		JSONObject obj = new JsonUtils(courseService.findById(id))
				.getJsonObject();
		return obj.toJSONString();
	}

	// Find by id
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String findByStudentId(@PathVariable final String id) {
		JSONObject obj = new JsonUtils(courseService.findByStudentId(id))
				.getJsonObject();
		return obj.toJSONString();
	}
}
