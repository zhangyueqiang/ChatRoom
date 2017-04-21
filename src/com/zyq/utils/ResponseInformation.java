package com.zyq.utils;

import org.json.JSONException;
import org.json.JSONObject;


public class ResponseInformation {
	public static String getSuccessInformation(){
		JSONObject json = new JSONObject();
		try {
			json.put("status", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}	
	public static String getErrorInformation(String reason) {
		JSONObject json = new JSONObject();
		try {
			json.put("status", "error");
			json.put("reason", reason);
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	public static String getErrorInformation(Exception ex) {
		JSONObject json = new JSONObject();
		try {
			json.put("status", "error");
			json.put("reason", ex.getMessage());
		} catch (Exception e) {
			e.getSuppressed();
		}
		return json.toString();
	}
}
