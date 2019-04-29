package ecustom.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GsonUtil {
	
	public static List toMapList(String jsonText) {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(jsonText);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (json.isJsonArray()) {
			JsonArray array = json.getAsJsonArray();
			Iterator<JsonElement> it = array.iterator();
			while(it.hasNext()){
				JsonElement e = it.next();
				list.add(gson.fromJson(e, Map.class));
			}
		}
		return list;
	}
	
	public static Object toObject(String jsonText, Class<?> clazz) {
		if (jsonText == null || jsonText.isEmpty()) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(jsonText, clazz);
	}
	
	public static List toObjectList(String jsonText, Class<?> clazz) {
		List list = new ArrayList();
		if (jsonText == null || jsonText.isEmpty()) {
			return list;
		}
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(jsonText);
		if (json.isJsonArray()) {
			JsonArray array = json.getAsJsonArray();
			Iterator<JsonElement> it = array.iterator();
			while(it.hasNext()){
				JsonElement e = it.next();
				list.add(gson.fromJson(e, clazz));
			}
		}
		
		return list;
	}
	
	public static Map<String, Object> toMap(String jsonText) {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(jsonText);
		Map<String, Object> map = new HashMap<String, Object>();
		if (json.isJsonObject()) {
			map = gson.fromJson(json, Map.class);
		}
		return map;
	}

	public static void main(String[] args) {
		Map<String, Object> map = GsonUtil.toMap("{\"name\":\"����\"}");
		System.out.println(map);

	}

	public static String toJSONString(Object data) {
		return new GsonBuilder().create().toJson(data);
	}
}
