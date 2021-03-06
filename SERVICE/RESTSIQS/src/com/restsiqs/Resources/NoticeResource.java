package com.restsiqs.Resources;

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
import com.restsiqs.Entities.Notice;
import com.restsiqs.Services.NoticeService;
import com.restsiqs.Utils.JsonUtils;
import com.restsiqs.Utils.URLAccepter;

/*
 * This class is the public interface for this system.It mainly deal with academy table's work.
 * API:
 * HOST: http://hostname:<port>/RESTSIQS/
 * FUNCTIONS:
 * 		Add:                  |POST /notice/notice
 * 		Delete:               |DELETE /notice/{id}
 * 		Update:               |PUT /notice/notice
 * 		FindAll:              |GET /notice/noticelist
 * 		Find by primary key:  |GET /notice/{id}
 * 		
 * Created by devouty on 2015/10/13.
 * 
 */

@RequestMapping("notice")
@Controller
public class NoticeResource {
	@Autowired
	private NoticeService noticeService;
	private static final Logger log = LoggerFactory
			.getLogger(NoticeResource.class);

	// Add
	@RequestMapping(value = "/notice/{pojo}", method = RequestMethod.POST)
	@ResponseBody
	public String add(@PathVariable String pojo) {
		log.debug("Starting add notice");
		// JSONObject obj = null;
		try {
			pojo = URLAccepter.decrpt(pojo);
			Notice notice = new Gson().fromJson(pojo, Notice.class);
			noticeService.save(notice);
			// obj = new JsonUtils(notice).getJsonObject();
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
		noticeService.delete(id);
		log.debug("Delete " + id + " successful");
		return "Delete " + id + " successful";
	}

	// Update
	@RequestMapping(value = "/notice/{pojo}", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@PathVariable String pojo) {
		log.debug("Starting update notice");
		try {
			pojo = URLAccepter.decrpt(pojo);
			Notice notice = new Gson().fromJson(pojo, Notice.class);
			noticeService.update(notice);
			// JSONObject obj = new JsonUtils(notice).getJsonObject();
			log.debug("Update successful");
			// return obj.toJSONString();
		} catch (Exception e) {
			log.debug("Update failed");
			e.printStackTrace();
			// return null;
		}
		return pojo;
	}

	// Find all
	@RequestMapping(value = "/noticelist", method = RequestMethod.GET)
	@ResponseBody
	public String getAll() {
		List<Notice> list = noticeService.findAll();
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
		JSONObject obj = new JsonUtils(noticeService.findById(id))
				.getJsonObject();
		return obj.toJSONString();
	}
}