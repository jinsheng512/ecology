
package weaver.cpt.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.cpt.capital.CapitalComInfo;
import weaver.crm.Maint.CustomerInfoComInfo;
import weaver.docs.docs.DocComInfo;
import weaver.docs.senddoc.DocReceiveUnitComInfo;
import weaver.file.FileUpload;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.proj.Maint.ProjectInfoComInfo;
import weaver.systeminfo.SystemEnv;
import weaver.workflow.field.BrowserComInfo;
import weaver.workflow.workflow.WorkflowRequestComInfo;
/**
 * 
 * @author crazyDream
 * @createdDate 2014-4-15
 * @function : Capital Customized Field Manager
 *
 */
public class CptFieldManager extends BaseBean {
	
	
	/**
	 * 更新资产自定义字段值
	 * @param cptid
	 */
	public void updateCusfieldValue(String cptid,FileUpload fu,User user){
		if("".equals( Util.null2String(cptid))||fu==null||user==null){
			return;
		}
		try {
			CptFieldComInfo cptFieldComInfo=new CptFieldComInfo();
			TreeMap<String, JSONObject> openfieldMap= cptFieldComInfo.getOpenFieldMap();
			
			if(openfieldMap!=null && openfieldMap.size()>0){
				StringBuilder sb=new StringBuilder("update cptcapital set  ");
				RecordSet rs=new RecordSet();
				Iterator it=openfieldMap.entrySet().iterator();
				while(it.hasNext()){
					Entry<String,JSONObject> entry=(Entry<String,JSONObject>)it.next();
					String k= entry.getKey();
					JSONObject v= entry.getValue();
                    int type = Util.getIntValue(v.getString("type"));
                    int fieldhtmltype = Util.getIntValue(v.getString("fieldhtmltype"));
                    String fieldname = v.getString("fieldname");
                    String fieldval = Util.null2String(fu.getParameter("field" + v.getString("id")));
                    if ((type == 2 || type == 3 || type == 4 || type == 5) && fieldhtmltype == 1 && "".equals(fieldval)) {
                        rs.executeSql("select " + fieldname + " from cptcapital where id=" + cptid);
                        if (rs.next()) {
                            String s = rs.getString(fieldname);
                            if (!"".equals(s)) {
                                sb.append(fieldname).append("=").append("null").append(",");
                            }
                        }
                        continue;
                    }
                    sb.append( fieldname).append("='").append(fieldval).append("',");
                }
				sb.deleteCharAt(sb.length()-1).append(" where id=").append(cptid);
				//System.out.println(sb.toString());
				rs.executeSql(sb.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param fieldname
	 * @return
	 */
	public static boolean isFieldUsed(String fieldname) {
		boolean hasUsed = false;
		weaver.conn.RecordSet rs = new RecordSet();
		String sql="";
		if("oracle".equalsIgnoreCase( rs.getDBType())){
			sql="select 1 from dual where exists(select 1 from cptcapital where " + fieldname + " is not null )"; 
		}else{
			sql="select 1  where exists(select 1 from cptcapital where convert(varchar(200)," + fieldname
					+ ") !='' and " + fieldname + " is not null )";
		}
		rs.execute(sql);
		if (rs.next()) {
			hasUsed=true;
			//rs.executeSql("update cptdefinefield set isused='1' where fieldname='"+fieldname+"' ");
		}
		
		return hasUsed;
	}

	/**
	 * 操作
	 */
	private String action = "";

	/**
	 * 数据库对象
	 */
	ConnStatement statement = null;

	/**
	 * 构造方法
	 * 
	 */
	public CptFieldManager() {

	}

	/**
	 * 重置类属性
	 * 
	 */
	public void reset() {
		action = "";

	}

	/**
	 * 设置操作
	 * 
	 * @param action
	 *            操作
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 新建表单时,生成最新的表单名对应的labelid
	 * 
	 * @return 正常情况返回生成的labelid，异常则返回-1。
	 */
	public synchronized int getNewIndexId(RecordSetTrans recordSetTrans) {
		int labelid = -1;
		try {
			recordSetTrans
					.executeSql("select min(id) as id from HtmlLabelIndex");
			if (recordSetTrans.next()) {
				labelid = recordSetTrans.getInt("id") - 1;
				if (labelid > -2)
					labelid = -2;// 从-2开始生成新的labelid
			}
		} catch (Exception e) {
			labelid = -1;
		}
		return labelid;
	}

	/**
	 * 获取批量编辑页面的输入项字段类型下拉选择框_新建一行时使用。针对主字段
	 * 
	 * @param user
	 *            当前用户对象
	 * 
	 * @return String 输入项字段类型下拉选择框以及相关数据
	 */
	@Deprecated
	public String getItemFieldTypeSelectForAddMainRow(User user) {
		String browserExcludes=",161,162,224,226,256,257,";
		String itemFieldTypeSelect = "";
		// 安全检查
		if (user == null) {
			return itemFieldTypeSelect;
		}
		BrowserComInfo browserComInfo = new BrowserComInfo();
		String itemFieldType = "1";

		itemFieldTypeSelect += "<select class='InputStyle' style='width:100px!important;' name='itemFieldType_\" + rowindex + \"'  onChange='onChangItemFieldType(\"  + rowindex +  \")'>"
				+ "<option value='1' "
				+ getSelectedForItemFieldType(itemFieldType, "1")
				+ ">"
				+ SystemEnv.getHtmlLabelName(688, user.getLanguage())
				+ "</option>"
				+ "<option value='2' "
				+ getSelectedForItemFieldType(itemFieldType, "2")
				+ ">"
				+ SystemEnv.getHtmlLabelName(689, user.getLanguage())
				+ "</option>"
				+ "<option value='3' "
				+ getSelectedForItemFieldType(itemFieldType, "3")
				+ ">"
				+ SystemEnv.getHtmlLabelName(695, user.getLanguage())
				+ "</option>"
				+ "<option value='4' "
				+ getSelectedForItemFieldType(itemFieldType, "4")
				+ ">"
				+ SystemEnv.getHtmlLabelName(691, user.getLanguage())
				+ "</option>"
				+ "<option value='5' "
				+ getSelectedForItemFieldType(itemFieldType, "5")
				+ ">"
				+ SystemEnv.getHtmlLabelName(690, user.getLanguage())
				/**+ "</option>"
				+ "<option value='6' "
				+ getSelectedForItemFieldType(itemFieldType, "6")
				+ ">"
				+ SystemEnv.getHtmlLabelName(17616, user.getLanguage())
				+ "</option>"
				+ "<option value='7' "
				+ getSelectedForItemFieldType(itemFieldType, "7")
				+ ">"
				+ SystemEnv.getHtmlLabelName(21691, user.getLanguage())**/
				+ "</option>" + "</select>";
		String itemFieldType1 = "style='display:none'";
		String itemFieldType2 = "style='display:none'";
		String itemFieldType3 = "style='display:none'";
		String itemFieldType5 = "style='display:none'";
		String itemFieldType6 = "style='display:none'";
		String itemFieldType6_1 = "style='display:none'";
		String itemFieldType7 = "style='display:none'";
		String itemFieldType7_1 = "style='display:none'";
		String itemFieldType7_2 = "style='display:none'";

		if ("1".equals(itemFieldType)) {
			itemFieldType1 = "style='display:inline'";
		} else if ("2".equals(itemFieldType)) {
			itemFieldType2 = "style='display:inline'";
		} else if ("3".equals(itemFieldType)) {
			itemFieldType3 = "style='display:inline'";
		} else if ("4".equals(itemFieldType)) {
		} else if ("5".equals(itemFieldType)) {
			itemFieldType5 = "style='display:inline'";
		} else if ("6".equals(itemFieldType)) {
			itemFieldType6 = "style='display:inline'";
		} else if ("7".equals(itemFieldType)) {
			itemFieldType7 = "style='display:inline'";
			itemFieldType7_1 = "style='display:block'";
		}

		itemFieldTypeSelect += "<div id=div1_\" + rowindex + \" "
				+ itemFieldType1
				+ " > "
				+ SystemEnv.getHtmlLabelName(63, user.getLanguage())
				+ "<select class='InputStyle' style='width:100px!important;' name='documentType_\" + rowindex + \"'  onChange='onChangType(\"  + rowindex +  \")'>"
				+ "<option value='1'>"
				+ SystemEnv.getHtmlLabelName(608, user.getLanguage())
				+ "</option>" + "<option value='2'>"
				+ SystemEnv.getHtmlLabelName(696, user.getLanguage())
				+ "</option>" + "<option value='3'>"
				+ SystemEnv.getHtmlLabelName(697, user.getLanguage())
				/**+ "</option>" + "<option value='4'>"
				+ SystemEnv.getHtmlLabelName(18004, user.getLanguage())
				+ "</option>" + "<option value='5'>"
				+ SystemEnv.getHtmlLabelName(22395, user.getLanguage())**/
				+ "</option>" + "</select></div>";
		itemFieldTypeSelect += "<div id='div1_1_\" + rowindex + \"' "
				+ itemFieldType1
				+ " > "
				+ SystemEnv.getHtmlLabelName(698, user.getLanguage())
				+ " <input class='InputStyle' style='width:100px!important;' type='text' size=3 maxlength=3 id='itemFieldScale1_\" + rowindex + \"' name='itemFieldScale1_\" + rowindex + \"' onKeyPress='ItemPlusCount_KeyPress()' onblur='checkPlusnumber1(this);checklength(itemFieldScale1_\" + rowindex + \",itemFieldScale1span_\" + rowindex + \");checkcount1(itemFieldScale1_\" + rowindex + \")' style='text-align:right;'><span id=itemFieldScale1span_\" + rowindex + \"><IMG src='/images/BacoError_wev8.gif' align=absMiddle></span></div>";

		itemFieldTypeSelect += "<div id='div1_3_\" + rowindex + \"' style='display:none'> "
				+ SystemEnv.getHtmlLabelName(15212, user.getLanguage())
				+ "<select id='decimaldigits_\" + rowindex + \"' name='decimaldigits_\" + rowindex + \"' style='width:100px!important;'>"
				+ "<option value='1' >1</option>"
				+ "<option value='2' selected>2</option>"
				+ "<option value='3' >3</option>"
				+ "<option value='4' >4</option>" + "</select>" + "</div>";

		itemFieldTypeSelect += "<div id=div2_\" + rowindex + \" "
				+ itemFieldType2
				+ " > "
				+ SystemEnv.getHtmlLabelName(207, user.getLanguage())
				+ " <input class='InputStyle' style='width:100px!important;' type='text' size=4 maxlength=2 value=4 id=textheight_\" + rowindex + \" name='textheight_\" + rowindex + \"' onKeyPress='ItemPlusCount_KeyPress()' onblur='checkPlusnumber1(this);checkcount1(textheight_\" + rowindex + \")' style='text-align:right;'>"
				+ SystemEnv.getHtmlLabelName(222, user.getLanguage())
				+ SystemEnv.getHtmlLabelName(15449, user.getLanguage())
				+ " <input type='checkbox' value='2' name='htmledit_\" + rowindex + \"' id='htmledit_\" + rowindex + \"' onclick='onfirmhtml(\"  + rowindex +  \")'></div>";
		itemFieldTypeSelect += "<div id=div3_\" + rowindex + \" "
				+ itemFieldType3
				+ " > "
				+ SystemEnv.getHtmlLabelName(63, user.getLanguage())
				+ "<select class='InputStyle' style='width:100px!important;' name='broswerType_\" + rowindex + \"' onChange='onChangBroswerType(\"  + rowindex +  \")'>";
		while (browserComInfo.next()) {
			if (browserComInfo.getBrowserurl().equals("")) {
				continue;
			}
			if(browserExcludes.indexOf (","+browserComInfo.getBrowserid()+",")>-1){
				continue;
			}
			itemFieldTypeSelect += "<option value='"
					+ browserComInfo.getBrowserid()
					+ "'>"
					+ SystemEnv.getHtmlLabelName(Util.getIntValue(
							browserComInfo.getBrowserlabelid(), 0), user
							.getLanguage()) + "</option>";
		}
		itemFieldTypeSelect += "</select></div>";
		itemFieldTypeSelect += "<div id=div3_0_\" + rowindex + \" "
				+ itemFieldType3
				+ " > "
				+ "<span><IMG src='/images/BacoError_wev8.gif' align=absMiddle></span>";
		itemFieldTypeSelect += "</div>";
		itemFieldTypeSelect += "<div id=div3_1_\" + rowindex + \" "
				+ itemFieldType3
				+ " > "
				+ "<select class='InputStyle' style='width:100px!important;' name='definebroswerType_\" + rowindex + \"' onChange='div3_0_show(\"+rowindex+\")'>";
		/**
		 * List l=StaticObj.getServiceIds(Browser.class);
		 * if(l!=null&&l.size()>0){ for(int j=0;j<l.size();j++){
		 * itemFieldTypeSelect
		 * +="<option value='"+l.get(j)+"'>"+l.get(j)+"</option>"; } }
		 **/
		itemFieldTypeSelect += "</select></div>";
		itemFieldTypeSelect += "<div id=div3_2_\" + rowindex + \" "
				+ itemFieldType3
				+ " > "
				+ SystemEnv.getHtmlLabelName(19340, user.getLanguage())
				+ "<select class='InputStyle' style='width:100px!important;' name='decentralizationbroswerType_\" + rowindex + \"'>"
				+ "<option value='1' selected>"
				+ SystemEnv.getHtmlLabelName(18916, user.getLanguage())
				+ "</option>" + "<option value='2'>"
				+ SystemEnv.getHtmlLabelName(18919, user.getLanguage())
				+ "</option>";
		itemFieldTypeSelect += "</select></div>";
		itemFieldTypeSelect += "<div id=div5_\" + rowindex + \" "
				+ itemFieldType5
				+ " > "
				+ "<input type='button' class=addbtn id=btnaddRow name=btnaddRow onclick='addoTableRow(\"  + rowindex +  \")'>"
				+ "<input type='button' class=delbtn id=btnsubmitClear name=btnsubmitClear onclick='submitClear(\"  + rowindex +  \")'>"
				+ "<span style='display:none;'>"
				+ SystemEnv.getHtmlLabelName(22662, user.getLanguage())
				+ "&nbsp;</span>"
				+ "<BUTTON style='display:none;' id='showChildFieldBotton' class=Browser onClick=\\\"onShowChildField(childfieldidSpan_\" + rowindex + \",childfieldid_\" + rowindex + \",'_\" + rowindex + \"')\\\"></BUTTON>"
				+ "<span style='display:none;' id='childfieldidSpan_\" + rowindex + \"'></span>"
				+ "<input type='hidden' value='' name='childfieldid_\" + rowindex + \"' id='childfieldid_\" + rowindex + \"'>"
				+ "</div>";
		itemFieldTypeSelect += "<div id=div5_5_\" + rowindex + \" "
				+ itemFieldType5
				+ " > "
				+ "<table class='ViewForm' id='choiceTable_\" + rowindex + \"' cols=6 border=0>"
				+ "<col width=20%><col width=40%><col width=40%><col width=0%><col width=0%><col width=0%>"
				+ "<tr><td>"
				+ SystemEnv.getHtmlLabelName(1426, user.getLanguage())
				+ "</td>"
				+ "<td>"
				+ SystemEnv.getHtmlLabelName(15442, user.getLanguage())
				+ "</td>"
				+ "<td>"
				+ SystemEnv.getHtmlLabelName(338, user.getLanguage())
				+ "</td>"
				+ "<td>"
				+ SystemEnv.getHtmlLabelName(19206, user.getLanguage())
				+ "</td>"
				+ "<td style='display:none;'>"
				+ SystemEnv.getHtmlLabelName(19207, user.getLanguage())
				+ "</td>"
				+ "<td style='display:none;'>"
				+ SystemEnv.getHtmlLabelName(22663, user.getLanguage())
				+ "</td></tr>"
				+ "<input type='hidden' value='0' name='choiceRows_\" + rowindex + \"' id='choiceRows_\" + rowindex + \"'>"
				+ "</table></div>";
		itemFieldTypeSelect += "<div id=div6_\" + rowindex + \" "
				+ itemFieldType6
				+ " > "
				+ SystemEnv.getHtmlLabelName(63, user.getLanguage())
				+ "<select class='InputStyle' style='width:100px!important;' name='uploadtype_\" + rowindex + \"'  onChange='onuploadtype(this, \"  + rowindex +  \")'>"
				+ "<option value='1'>"
				+ SystemEnv.getHtmlLabelName(20798, user.getLanguage())
				+ "</option>" + "<option value='2'>"
				+ SystemEnv.getHtmlLabelName(20001, user.getLanguage())
				+ "</option>" + "</select></div>";
		itemFieldTypeSelect += "<div id=div6_1_\" + rowindex + \" "
				+ itemFieldType6_1
				+ " > "
				+ SystemEnv.getHtmlLabelName(24030, user.getLanguage())
				+ "<input class=InputStyle style='width:100px!important;' type=text name=strlength_\" + rowindex + \" size=6 value=5 maxlength=3 onKeyPress='ItemPlusCount_KeyPress()' onBlur='checkPlusnumber1(this)'>"
				+ SystemEnv.getHtmlLabelName(22924, user.getLanguage())
				+ "<input class=InputStyle style='width:100px!important;' type=text name=imgwidth_\" + rowindex + \" size=6 value=100 maxlength=4 onKeyPress='ItemPlusCount_KeyPress()' onBlur='checkPlusnumber1(this)'>"
				+ SystemEnv.getHtmlLabelName(22925, user.getLanguage())
				+ "<input class=InputStyle style='width:100px!important;' type=text name=imgheight_\" + rowindex + \" size=6 value=100 maxlength=4 onKeyPress='ItemPlusCount_KeyPress()' onBlur='checkPlusnumber1(this)'>"
				+ "</div>";
		itemFieldTypeSelect += "<div id=div7_\" + rowindex + \" "
				+ itemFieldType7
				+ " > "
				+ SystemEnv.getHtmlLabelName(63, user.getLanguage())
				+ "<select class='InputStyle' style='width:100px!important;' name='specialfield_\" + rowindex + \"'  onChange='specialtype(this, \"  + rowindex +  \")'>"
				+ "<option value='1'>"
				+ SystemEnv.getHtmlLabelName(21692, user.getLanguage())
				+ "</option>" + "<option value='2'>"
				+ SystemEnv.getHtmlLabelName(21693, user.getLanguage())
				+ "</option>" + "</select></div>";
		itemFieldTypeSelect += "<div id=div7_1_\" + rowindex + \" "
				+ itemFieldType7_1
				+ " > "
				+ "<table width=100% class='ViewForm' border=0><tr><td width=50%>"
				+ SystemEnv.getHtmlLabelName(606, user.getLanguage())
				+ "　　<input class=InputStyle style='width:100px!important;' type=text name=displayname_\" + rowindex + \" size=25 maxlength=1000></td></tr>"
				+ "<tr><td width=100%>"
				+ SystemEnv.getHtmlLabelName(16208, user.getLanguage())
				+ "　<input class=InputStyle style='width:100px!important;' type=text size=25 name=linkaddress_\" + rowindex + \" maxlength=1000>"
				+ "<br>"
				+ SystemEnv.getHtmlLabelName(18391, user.getLanguage())
				+ "</td></tr></table></div>";
		itemFieldTypeSelect += "<div id=div7_2_\" + rowindex + \" "
				+ itemFieldType7_2
				+ " > "
				+ "<table width=100% class='ViewForm' border=0><tr><td width=12%>"
				+ SystemEnv.getHtmlLabelName(21693, user.getLanguage())
				+ "</td>"
				+ "<td>　<textarea class='InputStyle' style='width:88%;height:100px' name=descriptivetext_\" + rowindex + \"></textarea></td></tr></table></div>";

		return itemFieldTypeSelect;
	}
	
	
	public String getItemFieldTypeSelectForAddMainRow2(User user) {
		String itemFieldTypeSelect = "";
		// 安全检查
		if (user == null) {
			return itemFieldTypeSelect;
		}
		BrowserComInfo browserComInfo = new BrowserComInfo();
		String itemFieldType = "1";
		
		itemFieldTypeSelect += "<select class='InputStyle' name='itemFieldType' id='itemFieldType'  onChange='onChangItemFieldType(this.name)'>"
				+ "<option value='1' "
				+ getSelectedForItemFieldType(itemFieldType, "1")
				+ ">"
				+ SystemEnv.getHtmlLabelName(688, user.getLanguage())
				+ "</option>"
				+ "<option value='2' "
				+ getSelectedForItemFieldType(itemFieldType, "2")
				+ ">"
				+ SystemEnv.getHtmlLabelName(689, user.getLanguage())
				+ "</option>"
				+ "<option value='3' "
				+ getSelectedForItemFieldType(itemFieldType, "3")
				+ ">"
				+ SystemEnv.getHtmlLabelName(695, user.getLanguage())
				+ "</option>"
				+ "<option value='4' "
				+ getSelectedForItemFieldType(itemFieldType, "4")
				+ ">"
				+ SystemEnv.getHtmlLabelName(691, user.getLanguage())
				+ "</option>"
				+ "<option value='5' "
				+ getSelectedForItemFieldType(itemFieldType, "5")
				+ ">"
				+ SystemEnv.getHtmlLabelName(690, user.getLanguage())
				+ "</option>"
				+ "<option value='6' "
				+ getSelectedForItemFieldType(itemFieldType, "6")
				+ ">"
				+ SystemEnv.getHtmlLabelName(17616, user.getLanguage())
				+ "</option>"
				+ "<option value='7' "
				+ getSelectedForItemFieldType(itemFieldType, "7")
				+ ">"
				+ SystemEnv.getHtmlLabelName(21691, user.getLanguage())
				+ "</option>" + "</select>";
		String itemFieldType1 = "style='display:none'";
		String itemFieldType2 = "style='display:none'";
		String itemFieldType3 = "style='display:none'";
		String itemFieldType5 = "style='display:none'";
		String itemFieldType6 = "style='display:none'";
		String itemFieldType6_1 = "style='display:none'";
		String itemFieldType7 = "style='display:none'";
		String itemFieldType7_1 = "style='display:none'";
		String itemFieldType7_2 = "style='display:none'";
		
		if ("1".equals(itemFieldType)) {
			itemFieldType1 = "style='display:inline'";
		} else if ("2".equals(itemFieldType)) {
			itemFieldType2 = "style='display:inline'";
		} else if ("3".equals(itemFieldType)) {
			itemFieldType3 = "style='display:inline'";
		} else if ("4".equals(itemFieldType)) {
		} else if ("5".equals(itemFieldType)) {
			itemFieldType5 = "style='display:inline'";
		} else if ("6".equals(itemFieldType)) {
			itemFieldType6 = "style='display:inline'";
		} else if ("7".equals(itemFieldType)) {
			itemFieldType7 = "style='display:inline'";
			itemFieldType7_1 = "style='display:block'";
		}
		
		itemFieldTypeSelect += "<div id=div1 "
				+ itemFieldType1
				+ " > "
				+ SystemEnv.getHtmlLabelName(63, user.getLanguage())
				+ "<select class='InputStyle' name='documentType' id='documentType'  onChange='onChangType(this.name)'>"
				+ "<option value='1'>"
				+ SystemEnv.getHtmlLabelName(608, user.getLanguage())
				+ "</option>" + "<option value='2'>"
				+ SystemEnv.getHtmlLabelName(696, user.getLanguage())
				+ "</option>" + "<option value='3'>"
				+ SystemEnv.getHtmlLabelName(697, user.getLanguage())
				+ "</option>" + "<option value='4'>"
				+ SystemEnv.getHtmlLabelName(18004, user.getLanguage())
				+ "</option>" + "<option value='5'>"
				+ SystemEnv.getHtmlLabelName(22395, user.getLanguage())
				+ "</option>" + "</select></div>";
		itemFieldTypeSelect += "<div id='div1_1' "
				+ itemFieldType1
				+ " > "
				+ SystemEnv.getHtmlLabelName(698, user.getLanguage())
				+ " <input class='InputStyle' type='text' size=3 maxlength=3 id='itemFieldScale1' name='itemFieldScale1' onKeyPress='ItemPlusCount_KeyPress()' onblur='checkPlusnumber1(this);checklength(itemFieldScale1,itemFieldScale1span);checkcount1(itemFieldScale1)' style='text-align:right;'><span id=itemFieldScale1span><IMG src='/images/BacoError_wev8.gif' align=absMiddle></span></div>";
		
		itemFieldTypeSelect += "<div id='div1_3' style='display:none'> "
				+ SystemEnv.getHtmlLabelName(15212, user.getLanguage())
				+ "<select id='decimaldigits' name='decimaldigits'>"
				+ "<option value='1' >1</option>"
				+ "<option value='2' selected>2</option>"
				+ "<option value='3' >3</option>"
				+ "<option value='4' >4</option>" + "</select>" + "</div>";
		
		itemFieldTypeSelect += "<div id=div2 "
				+ itemFieldType2
				+ " > "
				+ SystemEnv.getHtmlLabelName(207, user.getLanguage())
				+ " <input class='InputStyle' type='text' size=4 maxlength=2 value=4 id=textheight name='textheight' onKeyPress='ItemPlusCount_KeyPress()' onblur='checkPlusnumber1(this);checkcount1(textheight)' style='text-align:right;'>"
				+ SystemEnv.getHtmlLabelName(222, user.getLanguage())
				+ SystemEnv.getHtmlLabelName(15449, user.getLanguage())
				+ " <input type='checkbox' value='2' name='htmledit' id='htmledit' onclick='onfirmhtml(this)'></div>";
		itemFieldTypeSelect += "<div id=div3 "
				+ itemFieldType3
				+ " > "
				+ SystemEnv.getHtmlLabelName(63, user.getLanguage())
				+ "<select class='InputStyle' name='broswerType' id='broswerType' onChange='onChangBroswerType(this.name)'>";
		while (browserComInfo.next()) {
			if (browserComInfo.getBrowserurl().equals("")) {
				continue;
			}
			itemFieldTypeSelect += "<option value='"
					+ browserComInfo.getBrowserid()
					+ "'>"
					+ SystemEnv.getHtmlLabelName(Util.getIntValue(
							browserComInfo.getBrowserlabelid(), 0), user
							.getLanguage()) + "</option>";
		}
		itemFieldTypeSelect += "</select></div>";
		itemFieldTypeSelect += "<div id=div3_0 "
				+ itemFieldType3
				+ " > "
				+ "<span><IMG src='/images/BacoError_wev8.gif' align=absMiddle></span>";
		itemFieldTypeSelect += "</div>";
		itemFieldTypeSelect += "<div id=div3_1 "
				+ itemFieldType3
				+ " > "
				+ "<select class='InputStyle' name='definebroswerType' onChange='div3_0_show(this)'>";
		itemFieldTypeSelect += "</select></div>";
		itemFieldTypeSelect += "<div id=div3_2 "
				+ itemFieldType3
				+ " > "
				+ SystemEnv.getHtmlLabelName(19340, user.getLanguage())
				+ "<select class='InputStyle' name='decentralizationbroswerType'>"
				+ "<option value='1' selected>"
				+ SystemEnv.getHtmlLabelName(18916, user.getLanguage())
				+ "</option>" + "<option value='2'>"
				+ SystemEnv.getHtmlLabelName(18919, user.getLanguage())
				+ "</option>";
		itemFieldTypeSelect += "</select></div>";
		itemFieldTypeSelect += "<div id=div5 "
				+ itemFieldType5
				+ " > "
				+ "<BUTTON class=addbtn id=btnaddRow name=btnaddRow onclick='addoTableRow(this)'>"
				+ ""
				+ "</BUTTON>"
				+ "<BUTTON class=delbtn id=btnsubmitClear name=btnsubmitClear onclick='submitClear(this)'>"
				+ ""
				+ "</BUTTON>"
				+ "<span style='display:none;'>"
				+ SystemEnv.getHtmlLabelName(22662, user.getLanguage())
				+ "&nbsp;</span>"
				+ "<BUTTON style='display:none;' id='showChildFieldBotton' class=Browser onClick=\\\"onShowChildField(childfieldidSpan,childfieldid,'')\\\"></BUTTON>"
				+ "<span style='display:none;' id='childfieldidSpan'></span>"
				+ "<input type='hidden' value='' name='childfieldid' id='childfieldid'>"
				+ "</div>";
		itemFieldTypeSelect += "<div id=div5_5 "
				+ itemFieldType5
				+ " > "
				+ "<table class='ViewForm' id='choiceTable' cols=6 border=0>"
				+ "<col width=20%><col width=40%><col width=40%><col width=0%><col width=0%><col width=0%>"
				+ "<tr><td>"
				+ SystemEnv.getHtmlLabelName(1426, user.getLanguage())
				+ "</td>"
				+ "<td>"
				+ SystemEnv.getHtmlLabelName(15442, user.getLanguage())
				+ "</td>"
				+ "<td>"
				+ SystemEnv.getHtmlLabelName(338, user.getLanguage())
				+ "</td>"
				+ "<td>"
				+ SystemEnv.getHtmlLabelName(19206, user.getLanguage())
				+ "</td>"
				+ "<td style='display:none;'>"
				+ SystemEnv.getHtmlLabelName(19207, user.getLanguage())
				+ "</td>"
				+ "<td style='display:none;'>"
				+ SystemEnv.getHtmlLabelName(22663, user.getLanguage())
				+ "</td></tr>"
				+ "<input type='hidden' value='0' name='choiceRows' id='choiceRows'>"
				+ "</table></div>";
		itemFieldTypeSelect += "<div id=div6 "
				+ itemFieldType6
				+ " > "
				+ SystemEnv.getHtmlLabelName(63, user.getLanguage())
				+ "<select class='InputStyle' name='uploadtype'  onChange='onuploadtype(this)'>"
				+ "<option value='1'>"
				+ SystemEnv.getHtmlLabelName(20798, user.getLanguage())
				+ "</option>" + "<option value='2'>"
				+ SystemEnv.getHtmlLabelName(20001, user.getLanguage())
				+ "</option>" + "</select></div>";
		itemFieldTypeSelect += "<div id=div6_1 "
				+ itemFieldType6_1
				+ " > "
				+ SystemEnv.getHtmlLabelName(24030, user.getLanguage())
				+ "<input class=InputStyle  type=text name=strlength size=6 value=5 maxlength=3 onKeyPress='ItemPlusCount_KeyPress()' onBlur='checkPlusnumber1(this)'>"
				+ SystemEnv.getHtmlLabelName(22924, user.getLanguage())
				+ "<input class=InputStyle  type=text name=imgwidth size=6 value=100 maxlength=4 onKeyPress='ItemPlusCount_KeyPress()' onBlur='checkPlusnumber1(this)'>"
				+ SystemEnv.getHtmlLabelName(22925, user.getLanguage())
				+ "<input class=InputStyle  type=text name=imgheight size=6 value=100 maxlength=4 onKeyPress='ItemPlusCount_KeyPress()' onBlur='checkPlusnumber1(this)'>"
				+ "</div>";
		itemFieldTypeSelect += "<div id=div7 "
				+ itemFieldType7
				+ " > "
				+ SystemEnv.getHtmlLabelName(63, user.getLanguage())
				+ "<select class='InputStyle' name='specialfield'  onChange='specialtype(this)'>"
				+ "<option value='1'>"
				+ SystemEnv.getHtmlLabelName(21692, user.getLanguage())
				+ "</option>" + "<option value='2'>"
				+ SystemEnv.getHtmlLabelName(21693, user.getLanguage())
				+ "</option>" + "</select></div>";
		itemFieldTypeSelect += "<div id=div7_1 "
				+ itemFieldType7_1
				+ " > "
				+ "<table width=100% class='ViewForm' border=0><tr><td width=50%>"
				+ SystemEnv.getHtmlLabelName(606, user.getLanguage())
				+ "　　<input class=InputStyle type=text name=displayname size=25 maxlength=1000></td></tr>"
				+ "<tr><td width=100%>"
				+ SystemEnv.getHtmlLabelName(16208, user.getLanguage())
				+ "　<input class=InputStyle type=text size=25 name=linkaddress maxlength=1000>"
				+ "<br>"
				+ SystemEnv.getHtmlLabelName(18391, user.getLanguage())
				+ "</td></tr></table></div>";
		itemFieldTypeSelect += "<div id=div7_2 "
				+ itemFieldType7_2
				+ " > "
				+ "<table width=100% class='ViewForm' border=0><tr><td width=12%>"
				+ SystemEnv.getHtmlLabelName(21693, user.getLanguage())
				+ "</td>"
				+ "<td>　<textarea class='inputstyle' style='width:88%;height:100px' name=descriptivetext></textarea></td></tr></table></div>";
		
		return itemFieldTypeSelect;
	}

	/**
	 * 判断输入项字段类型是否被选择。
	 * 
	 *            当前用户对象
	 * @param itemFieldType
	 *            当前选中的输入项字段类型
	 * @param optionValue
	 *            下拉框的类型
	 * 
	 * @return String 若被选择则返回"selected",否则返回""
	 */
	private String getSelectedForItemFieldType(String itemFieldType,
			String optionValue) {
		String selected = "";
		// 安全检查
		if (itemFieldType == null || optionValue == null) {
			return selected;
		}

		if (itemFieldType.equals(optionValue)) {
			selected = "selected";
		}

		return selected;
	}
	public String getFieldvalue(User user, int fieldId, int fieldHtmlType,
			int fieldType, String fieldValue, int isBill) throws Exception {
		return this.getFieldvalue(null, user, null, null, fieldId,
				fieldHtmlType, fieldType, fieldValue, isBill,false);
	}
	public String getFieldvalue(User user, int fieldId, int fieldHtmlType,
			int fieldType, String fieldValue, int isBill,boolean isViewPage) throws Exception {
		return this.getFieldvalue(null, user, null, null, fieldId,
				fieldHtmlType, fieldType, fieldValue, isBill,isViewPage);
	}

	public String getFieldvalue(HttpSession session, int fieldId,
			int fieldHtmlType, int fieldType, String fieldValue, int isBill)
			throws Exception {
		return this.getFieldvalue(session, null, null, null, fieldId,
				fieldHtmlType, fieldType, fieldValue, isBill,false);
	}
	

	public String getFieldvalue(HttpSession session, User user,
			String workflowid, String requestid, int fieldId,
			int fieldHtmlType, int fieldType, String fieldValue, int isBill,boolean isViewPage)
			throws Exception {
		RecordSet rs = new RecordSet();
		String showname = "";

		if (fieldHtmlType == 3) {
			ArrayList tempshowidlist = Util.TokenizerString(fieldValue, ",");
			BrowserComInfo borrow = new BrowserComInfo();
			String linkurl = Util.null2String(borrow.getLinkurl(fieldType+""));
			if(fieldType == 2 || fieldType == 19 || fieldType == 226 || fieldType == 227){
				showname=fieldValue;
			}else if (fieldType == 1 || fieldType == 17) { // 人员，多人员
				for (int k = 0; k < tempshowidlist.size(); k++) {
					if(isViewPage){
						showname +="<a href=\"javascript:openhrm("+tempshowidlist.get(k) +")\" onclick=\"pointerXY(event);\" >"+ new ResourceComInfo()
						.getResourcename((String) tempshowidlist.get(k))
						+ "</a>&nbsp;";
					}else{
						showname += new ResourceComInfo()
						.getResourcename((String) tempshowidlist.get(k))
						+ ",";
					}
				}
			}else if (fieldType == 23 || fieldType == 26 || fieldType == 3) { // 资产
                if ("0".equals(Util.null2String(fieldValue))) {
                    return "";
                }
                CptSettingsComInfo c=new CptSettingsComInfo();
                CapitalComInfo c1=new CapitalComInfo();
                for (int k = 0; k < tempshowidlist.size(); k++) {
                    if(isViewPage){
                        showname +="<a href=\"javascript:openFullWindowForXtable('"+linkurl+tempshowidlist.get(k) +"')\" onclick=\"pointerXY(event);\" >"+ c1
                                .getCapitalname((String) tempshowidlist.get(k))
                                + "</a>";
                        if ("1".equals( c.getIsopen2())) {
                            showname+="&nbsp;<a href='/CreateBarCode?barType=-1&capitalid="+tempshowidlist.get(k)+"' onclick=\"return hs.expand(this)\"><img src=\"/cpt/img/qrcode2_wev8.png\" onmouseover=\"this.src='/cpt/img/qrcode3_wev8.png'\" onmouseout=\"this.src='/cpt/img/qrcode2_wev8.png'\" width=\"16\" height=\"16\" style=\"vertical-align:middle;cursor: pointer;\"></a>";
                        }
                        showname+="&nbsp;";
                    }else{
                        showname += c1
                                .getCapitalname((String) tempshowidlist.get(k))
                                + ",";
                    }
                }
            }else {
				String tablename=borrow.getBrowsertablename(fieldType+"");
				String columname=borrow.getBrowsercolumname(fieldType+"");
				String keycolumname=borrow.getBrowserkeycolumname(fieldType+"");
				for (int k = 0; k < tempshowidlist.size(); k++) {
					int intfieldvalue=Util.getIntValue(tempshowidlist.get(k).toString(),0);
					String sql="select "+columname+" from "+tablename+" where "+keycolumname+"="+intfieldvalue;
					rs.executeSql(sql);
					rs.next();
					String shownamevalue=rs.getString(1);
					if(isViewPage){
						//258 58  263  国家    省份     城市      区县
						if(fieldType == 258 || fieldType == 58 || fieldType == 263){
							showname += Util.toScreen(shownamevalue,user.getLanguage());
						}else{
							if(!linkurl.equals("")){
								showname += "<a href=\"javascript:openFullWindowForXtable('"+linkurl+intfieldvalue+"')\">";
							}
							showname += Util.toScreen(shownamevalue,user.getLanguage());
							if(!linkurl.equals("")){
								showname += "</a>&nbsp;";
							}
						}
					}else{
						showname += shownamevalue + ",";
					}
				}
			}
			if (showname.endsWith(",")) {
				showname = showname.substring(0, showname.length() - 1);
			}
		} else if (fieldHtmlType == 4) { // check框
			showname += "<input type='checkbox' name='check_"+fieldId+"' value='1' "+("1".equals(fieldValue)?"checked":"")+" class='InputStyle' readonly='readonly' />";
			/**if (fieldValue.equals("1")) {
				showname += "√";
			}**/
		} else if (fieldHtmlType == 5) { // 选择框 select
			// 查询选择框的所有可以选择的值
			rs.executeSql("select selectvalue,selectname from cpt_SelectItem where fieldid = "
					+ fieldId + "  order by listorder,id");
			while (rs.next()) {
				String tmpselectvalue = Util.null2String(rs
						.getString("selectvalue"));
				if (session != null)
					user = (User) session.getAttribute("weaver_user@bean");
				String tmpselectname = Util.toScreen(
						rs.getString("selectname"), user.getLanguage());
				if (tmpselectvalue.equals(fieldValue)) {
					showname += tmpselectname;
				}
			}
		} else if (fieldHtmlType == 6) { // 附件
			rs.executeSql("select id,docsubject,accessorycount from docdetail where id in("
					+ fieldValue + ") order by id asc");
			while (rs.next()) {
				showname += rs.getString("docsubject") + ",";
			}
			if (showname.endsWith(",")) {
				showname = showname.substring(0, showname.length() - 1);
			}
		}else if(fieldHtmlType==2&&fieldType==1){//多行文本非html格式
			showname = Util.toHtml( fieldValue);
		}else {
			showname =  fieldValue;
		}
		return showname;
	}
	
	/**
	 * 自定义字段同步到资产查询定义,资产浏览框查询定义
	 */
	static synchronized public void syncFields(){
		RecordSet rs=new RecordSet();
		RecordSet rs2=new RecordSet();
		int seq=10000;
		//同步资产浏览框查询定义
		String sql="delete cpt_browdef where ( fieldid in(select id from cptDefineField where issystem is null and isopen!='1' ) "+
		" or not exists(select 1 from cptDefineField t where t.id=cpt_browdef.fieldid) "
				+" )";
		rs.executeSql(sql);
		sql=""+
				" INSERT INTO cpt_browdef( fieldid,displayorder ) "+
				" select t1.id,-9999 from cptDefineField t1 where t1.isopen='1' and t1.fieldhtmltype!=6 and t1.fieldhtmltype!=7 and not exists(select 1 from cpt_browdef t2 where  t2.fieldid=t1.id ) ";
		rs.executeSql(sql);
		sql="select * from cpt_browdef where displayorder=-9999";
		rs.executeSql(sql);
		while(rs.next()){
			rs2.executeSql("update cpt_browdef set displayorder="+(++seq)+" where fieldid="+rs.getInt("fieldid"));
		}
		
		//同步资产查询定义
		sql="delete CptSearchDefinition where ( fieldname in(select fieldname from cptDefineField where (issystem is null and isopen!='1') ) "+
		" or not exists (select 1 from cptDefineField t where lower(t.fieldname)=lower(CptSearchDefinition.fieldname )) "
				+" )";
		rs.executeSql(sql);
		
		sql=""+
				" INSERT INTO CptSearchDefinition( fieldname,isconditionstitle,istitle,isconditions,isseniorconditions,mouldid,displayorder ) "+
				" select t1.fieldname,1,0,1,0,-1,-9999 from cptDefineField t1 where t1.isopen='1' and t1.fieldhtmltype!=6 and t1.fieldhtmltype!=7 and not exists(select 1 from CptSearchDefinition t2 where  lower(t2.fieldname)=lower(t1.fieldname) ) ";
		rs.executeSql(sql);
		sql="select * from CptSearchDefinition where displayorder=-9999";
		seq=10000;
		rs.executeSql(sql);
		while(rs.next()){
			rs2.executeSql("update CptSearchDefinition set displayorder="+(++seq)+" where fieldname='"+rs.getString("fieldname")+"' ");
		}
		
	}
	/**
	 * 取浏览框字段值
	 * @param fieldValue
	 * @param otherpara
	 * @return
	 * @throws Exception
	 */
	public String getBrowserFieldvalue(String fieldValue, String otherpara) throws Exception {
		String[] s= Util.TokenizerString2(otherpara, "+");
		if(s!=null&&s.length>=4){
			return getFieldvalue(null, new User(Util.getIntValue(s[0])), null, null,Util.getIntValue( s[1],0),Util.getIntValue( s[2]), Util.getIntValue( s[3]), fieldValue, 0,true);
		}else{
			return "";
		}
	}

}