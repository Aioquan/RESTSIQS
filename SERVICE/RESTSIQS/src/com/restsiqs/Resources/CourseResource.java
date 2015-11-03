package com.restsiqs.Resources;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.restsiqs.Entities.Course;
import com.restsiqs.Services.CourseService;
import com.restsiqs.Utils.JsonUtils;
import com.restsiqs.Utils.URLAccepter;

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
	@RequestMapping(value = "/course/{pojo}", method = RequestMethod.POST)
	@ResponseBody
	public String add(@PathVariable String pojo) {
		log.debug("Starting add course");
		try {
			pojo = URLAccepter.decrpt(pojo);
			Course course = new Gson().fromJson(pojo, Course.class);
			courseService.save(course);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return pojo;
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
	@RequestMapping(value = "/course/{pojo}", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@PathVariable String pojo) {
		log.debug("Starting update course");
		// try {
		// courseService.save(course);
		// JSONObject obj = new JsonUtils(course).getJsonObject();
		// log.debug("Update successful");
		// return obj.toJSONString();
		// } catch (Exception e) {
		// log.debug("Update failed");
		// e.printStackTrace();
		// return null;
		// }
		try {
			pojo = URLAccepter.decrpt(pojo);
			Course course = new Gson().fromJson(pojo, Course.class);
			courseService.update(course);
			// Course c2 = courseService.findById(course.getCourseId());
			// c2.setCourseDate(course.getCourseDate());
			// c2.setCourseName(course.getCourseName());
			// c2.setCourseTime(course.getCourseTime());
			// c2.setCredit(course.getCredit());
			// c2.setDailyMark(course.getDailyMark());
			// c2.setExercises1(course.getExercises1());
			// c2.setExercises2(course.getExercises2());
			// c2.setExercises3(course.getExercises3());
			// c2.setExercises4(course.getExercises4());
			// c2.setExercises5(course.getExercises5());
			// c2.setFinalTest(course.getFinalTest());
			// c2.setStudentId(course.getStudentId());
			// c2.setSum(course.getSum());
			// c2.setTeacherId(course.getTeacherId());
			// c2.setTest1(course.getTest1());
			// c2.setTest2(course.getTest2());
			// c2.setTest3(course.getTest3());
			// courseService.save(c2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// System.out.println(pojo);
		return pojo;
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
