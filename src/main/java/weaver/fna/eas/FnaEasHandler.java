package weaver.fna.eas;

import java.util.Map;

import weaver.conn.RecordSet;
import weaver.general.Util;

public class FnaEasHandler {
	public String changeToJson(Map<String, Object> map,int mainOrDetail){
		String returnStr = "{";
		if(mainOrDetail == 1){
			for(Map.Entry<String, Object> entrySet:map.entrySet()){
				String key = entrySet.getKey();
				Object value = entrySet.getValue();
				returnStr += "\""+key+"\":\""+value+"\",";
			}
			returnStr += "\"entries\":";
		}else{
			for(Map.Entry<String, Object> entrySet:map.entrySet()){
				String key = entrySet.getKey();
				Object value = entrySet.getValue();
				returnStr += "\""+key+"\":\""+value+"\",";
			}
			returnStr = returnStr.substring(0, returnStr.length()-1);
			returnStr += "}";
		}
		return returnStr;
	}
	
	public String[] getSubcompanycode(int id){
		String[] returnStr = new String[2];
		RecordSet rs = new RecordSet();
		String sql = "select subcompanyname,subcompanycode from hrmsubcompany where id ="+id;
		rs.executeSql(sql);
		String subcompanyname = "";
		String subcompanycode = "";
		while(rs.next()){
			subcompanyname = Util.null2String(rs.getString("subcompanyname"));
			subcompanycode = Util.null2String(rs.getString("subcompanycode"));
		}
		returnStr[0] = subcompanyname;
		returnStr[1] = subcompanycode;
		return returnStr;
	}
	
	public String[] getWorkcode(int id){
		String[] returnStr = new String[2];
		RecordSet rs = new RecordSet();
		String sql = "select lastname,workcode from hrmresource where id = "+id;
		rs.executeSql(sql);
		String workcode = "";
		String lastname = "";
		while(rs.next()){
			workcode = Util.null2String(rs.getString("workcode"));
			lastname = Util.null2String(rs.getString("lastname"));
		}
		returnStr[0] = workcode;
		returnStr[1] = lastname;
		return returnStr;
	}

	public String getPayeeBank(int id) {
		RecordSet rs = new RecordSet();
		String sql = "select mingc from uf_yingh where id = "+id;
		rs.executeSql(sql);
		String mingc = "";
		while(rs.next()){
			mingc = Util.null2String(rs.getString("mingc"));
		}
		return mingc;
	}
}
