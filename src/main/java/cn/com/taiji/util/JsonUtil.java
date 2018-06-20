package cn.com.taiji.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonUtil {

	public JSONArray SwitchToJson(List<String> list) {
		
		JSONArray jsonArray=new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObject=new JSONObject();
			try {
				jsonObject.put("Permission", list.get(i));
				jsonArray.put(i, jsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return jsonArray;
	}
}
