package com.restsiqs.Utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
	/**
	 * CODE_1:连接正常 CODE_2:连接失败 CODE_3:参数出错 CODE_4: CODE_5:服务器故障
	 */
	private static final String DEFAULT_CHARSET_NAME = "UTF-8";
	private static final String CODE_1 = "1";
	private static final String CODE_2 = "2";
	private static final String CODE_3 = "3";
	private static final String CODE_4 = "4";
	private static final String CODE_5 = "5";
	private String jsonStringForList = null;
	private String jsonStringForObject = null;
	private int flag = 0;

	/**
	 * 此object应该为可序列化
	 * 
	 * @param object
	 */
	public JsonUtils(Object object) {
		this.jsonStringForObject = serialize(object);
		this.flag = 1;
	}

	public JsonUtils(List list) {
		this.jsonStringForList = serialize(list);
		this.flag = 2;
	}

	/**
	 * 1为传单个object，2为传一系列object
	 * 
	 * @return
	 */
	public JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();
		if (this.flag == 1) {
			JSONObject messageObject = new JSONObject()
					.parseObject(this.jsonStringForObject);
			JSONArray jsonArray = new JSONArray();
			jsonArray.add(messageObject);
			if (true) {
				jsonObject.put("code", CODE_1);
			}
			jsonObject.put("msg", "no message");
			jsonObject.put("result", jsonArray);

		} else if (this.flag == 2) {
			JSONArray jsonArray = new JSONArray()
					.parseArray(this.jsonStringForList);
			if (true) {
				jsonObject.put("code", CODE_1);
			}
			jsonObject.put("msg", "no message");
			jsonObject.put("result", jsonArray);
		}
		return jsonObject;
	}

	/**
	 * 序列化
	 */
	public <T> String serialize(T object) {
		return JSON.toJSONString(object);
	}
}
