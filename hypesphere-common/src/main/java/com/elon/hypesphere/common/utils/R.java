/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.elon.hypesphere.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R setData (Object data) {
		this.put("data", data);
		return this;
	}

	public <T> T getData(TypeReference<T> typeReference) {
		Object data = this.get("data");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(data, mapper.getTypeFactory().constructType(typeReference.getType()));
	}

	public <T> T getData(String key,TypeReference<T> typeReference) {
		Object data = this.get(key);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(data, mapper.getTypeFactory().constructType(typeReference.getType()));
	}

	public R() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public Integer getCode(){
       return (Integer) this.get("code");
	}
}
