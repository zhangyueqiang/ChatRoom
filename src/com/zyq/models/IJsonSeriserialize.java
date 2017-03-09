package com.zyq.models;

import org.json.JSONObject;

public interface IJsonSeriserialize {
	public JSONObject toJson();
	public void readFromJson(JSONObject json);
}
