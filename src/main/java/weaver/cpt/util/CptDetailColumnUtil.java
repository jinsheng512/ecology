
package weaver.cpt.util;

import org.json.JSONArray;
import org.json.JSONObject;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.hrm.User;
import weaver.systeminfo.SystemEnv;
/**
 * 
 * @author crazyDream
 * @createdDate 2014-4-15
 * @function : Capital Detail Column Util
 *
 */
public class CptDetailColumnUtil extends BaseBean {
	
	
	
	//private CptDetailFieldComInfo cptDetailFieldComInfo=new CptDetailFieldComInfo();
	private RecordSet rs=new RecordSet();
	private CptFieldManager cptFieldManager=new CptFieldManager();
	
	/**
	 * 取明细表列配置
	 * @param detailType
	 * @return
	 */
	public JSONArray getDetailColumnConf(String detailType,User user){
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		try {
			/**
			TreeMap<String,JSONObject> openfieldMap= cptDetailFieldComInfo.getOpenFieldMap(detailType.toUpperCase());
			if(openfieldMap!=null){
				Iterator it=openfieldMap.entrySet().iterator();
				while(it.hasNext()){
					Entry<String,JSONObject> entry=(Entry<String,JSONObject>)it.next();
					String k= entry.getKey();
					JSONObject v= entry.getValue();
					String colname= SystemEnv.getHtmlLabelName(v.getInt("fieldlabel"),user.getLanguage());
					String itemhtml= ((HtmlElement)Class.forName(v.getString("eleclazzname")).newInstance()).getHtmlElementString("", v, user);
					
					jsonObject=new JSONObject();
					jsonObject.put("width", "10%");
					jsonObject.put("colname", colname);
					jsonObject.put("itemhtml", itemhtml);
					jsonArray.put(jsonObject);
					
				}
			}
			**/
			
			
			
			if("CptStockInDetail".equalsIgnoreCase( detailType)){//入库申请
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1509,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=23&isdata=1' browserurl='/systeminfo/BrowserMain.jsp?url=/cpt/capital/CapitalBrowser.jsp?sqlwhere=where isdata=1' name='capitalid' isMustInput='2' hasInput='true'  isSingle='true' _callback='loadinfo' ></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(904,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='capitalspec' id='capitalspec' /><span class='mustinput'></span>");
				jsonArray.put(jsonObject);
				
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1330,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='price' maxLength='10' id='price' onkeyup=\"value=value.replace(/[^\\d\\.]/g,'')\"  /><span class='mustinput'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1331,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='capitalnum' maxLength='7' id='capitalnum' onkeyup=\"value=value.replace(/[^\\d\\.]/g,'')\" /><span class='mustinput'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(900,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='invoice' id='invoice' />");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1387,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='location' id='location' />");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15293,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='fnamark' id='fnamark' />");
				jsonArray.put(jsonObject);
				
				
			}else if("CptUse".equalsIgnoreCase( detailType)){//资产领用
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1412,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='hidden' name='StockInDate' value='"+TimeUtil.getCurrentDateString()+"'   class='wuiDate' style='width:90px!important;'  _span='StockInDate_span' _button='StockInDate_btn' _callback='' >");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15312,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=23&cptstateid=1&cptuse=1' browserurl='/systeminfo/BrowserMain.jsp?url=/cpt/capital/CapitalBrowser.jsp?sqlwhere=where isdata=2&cptstateid=1&cptuse=1' name='capitalid' hasInput='true' isMustInput='2' isSingle='true'  _callback='loadinfo'  ></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(368,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp' browserurl='/systeminfo/BrowserMain.jsp?url=/hrm/resource/ResourceBrowser.jsp' name='hrmid' hasInput='true' isMustInput='2' isSingle='true'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(904,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalspec_span' name='capitalspec_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "5%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(714,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='code_span' name='code_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1446,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalcount_span' name='capitalcount_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15313,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='capitalnum' id='capitalnum' size='5' maxlength='5' onkeyup=\"value=value.replace(/[^\\d\\.]/g,'')\" /><span class='mustinput'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1387,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='location' id='location' size='10' maxlength='10' />");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(454,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='remark' id='remark' />");
				jsonArray.put(jsonObject);
				
			}else if("CptMove".equalsIgnoreCase( detailType)){//资产调拨
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15310,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp' browserurl='/systeminfo/BrowserMain.jsp?url=/hrm/resource/ResourceBrowser.jsp' name='hrmid' hasInput='true' isMustInput='2' isSingle='true' _callback=''></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15311,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=4' browserurl='/systeminfo/BrowserMain.jsp?url=/hrm/company/DepartmentBrowser.jsp' name='CptDept_to' isMustInput='2' hasInput='true' isSingle='true' _callback=''></span>");
				jsonArray.put(jsonObject);
				
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15309,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=23&cptstateid=2' browserurl='/systeminfo/BrowserMain.jsp?url=/cpt/capital/CapitalBrowser.jsp?sqlwhere=where isdata=2&cptstateid=2&from1=move' name='capitalid' hasInput='true' isMustInput='2' isSingle='true' _callback='loadinfo'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(524,user.getLanguage())+SystemEnv.getHtmlLabelName(15393,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='curdept_span' name='curdept_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(524,user.getLanguage())+SystemEnv.getHtmlLabelName(1508,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='curresource_span' name='curresource_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(904,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalspec_span' name='capitalspec_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1387,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='location' id='location' size='10' maxlength='10' />");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "30%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(454,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='remark' id='remark' />");
				jsonArray.put(jsonObject);
				
			}else if("CptLend".equalsIgnoreCase( detailType)){//资产借用
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelNames("1379,368",user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp' browserurl='/systeminfo/BrowserMain.jsp?url=/hrm/resource/ResourceBrowser.jsp' name='hrmid' hasInput='true' isMustInput='2' isSingle='true' _callback=''></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1404,user.getLanguage()));
				jsonObject.put("itemhtml", "<input name='StockInDate' type='hidden' value='"+TimeUtil.getCurrentDateString()+"'   class='wuiDate' style='width:90px!important;'  _span='StockInDate_span' _button='StockInDate_btn' _callback='' >");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1379,user.getLanguage())+SystemEnv.getHtmlLabelName(535,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=23&cptstateid=1&cptsptcount=1' browserurl='/systeminfo/BrowserMain.jsp?url=/cpt/capital/CapitalBrowser.jsp?sqlwhere=where isdata=2&cptstateid=1&cptsptcount=1' name='capitalid' hasInput='true' isMustInput='2' isSingle='true' _callback='loadinfo'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15393,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='curdept_span' name='curdept_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(524,user.getLanguage())+SystemEnv.getHtmlLabelName(1508,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='curresource_span' name='curresource_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(904,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalspec_span' name='capitalspec_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1387,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='location' id='location' size='10' maxlength='10' />");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "30%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(454,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='remark' id='remark' />");
				jsonArray.put(jsonObject);
				
			}else if("CptLoss".equalsIgnoreCase( detailType)){//资产减损
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(17482,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp' browserurl='/systeminfo/BrowserMain.jsp?url=/hrm/resource/ResourceBrowser.jsp' name='operator' hasInput='true' isMustInput='1' isSingle='true' _callback=''></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1406,user.getLanguage()));
				jsonObject.put("itemhtml", "<input name='StockInDate' type='hidden' value='"+TimeUtil.getCurrentDateString()+"'   class='wuiDate' style='width:90px!important;'  _span='StockInDate_span' _button='StockInDate_btn' _callback='' >");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1385,user.getLanguage())+SystemEnv.getHtmlLabelName(535,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=23&cptstateid=1,2,3,4' browserurl='/systeminfo/BrowserMain.jsp?url=/cpt/capital/CapitalBrowser.jsp?stateid=0,1,2,3,4' name='capitalid' hasInput='true' isMustInput='2' isSingle='true' _callback='loadinfo'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15393,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='curdept_span' name='curdept_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(904,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalspec_span' name='capitalspec_span'></span>");
				jsonArray.put(jsonObject);
				
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1451,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalcount_span' name='capitalcount_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1385,user.getLanguage())+SystemEnv.getHtmlLabelName(1331,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='capitalnum' id='capitalnum' size='5' maxlength='5' onkeyup=\"value=value.replace(/[^\\d\\.]/g,'')\" /><span class='mustinput'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1393,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='cost' id='cost' size='10' maxlength='10' onkeyup=\"value=value.replace(/[^\\d\\.]/g,'')\" />");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(454,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='remark' id='remark' />");
				jsonArray.put(jsonObject);
				
			}else if("CptDiscard".equalsIgnoreCase( detailType)){//资产报废
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(17482,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp' browserurl='/systeminfo/BrowserMain.jsp?url=/hrm/resource/ResourceBrowser.jsp' name='operator' hasInput='true' isMustInput='1' isSingle='true' _callback=''></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1392,user.getLanguage()));
				jsonObject.put("itemhtml", "<input name='StockInDate' type='hidden' value='"+TimeUtil.getCurrentDateString()+"'   class='wuiDate' style='width:90px!important;'  _span='StockInDate_span' _button='StockInDate_btn' _callback='' >");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(21545,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=23&cptstateid=1,2,3,4' browserurl='/systeminfo/BrowserMain.jsp?url=/cpt/capital/CapitalBrowser.jsp?sqlwhere=where isdata=2&cptstateid=1,2,3,4&from1=discard' name='capitalid' hasInput='true' isMustInput='2' isSingle='true' _callback='loadinfo'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15393,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='curdept_span' name='curdept_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(904,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalspec_span' name='capitalspec_span'></span>");
				jsonArray.put(jsonObject);
				
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1451,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalcount_span' name='capitalcount_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1386,user.getLanguage())+SystemEnv.getHtmlLabelName(1331,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='capitalnum' id='capitalnum' size='5' maxlength='5' onkeyup=\"value=value.replace(/[^\\d\\.]/g,'')\" /><span class='mustinput'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1393,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='cost' id='cost' size='10' maxlength='10' onkeyup=\"value=value.replace(/[^\\d\\.]/g,'')\" />");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(454,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='remark' id='remark' />");
				jsonArray.put(jsonObject);
				
			}else if("CptMend".equalsIgnoreCase( detailType)){//资产送修
				
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1047,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser'  completeurl='/data.jsp' browserurl='/systeminfo/BrowserMain.jsp?url=/hrm/resource/ResourceBrowser.jsp' name='operator' hasInput='true' isMustInput='1' isSingle='true' _callback=''></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1409,user.getLanguage()));
				jsonObject.put("itemhtml", "<input name='menddate' type='hidden' value='"+TimeUtil.getCurrentDateString()+"'   class='wuiDate' style='width:90px!important;'  _span='menddate_span' _button='menddate_btn' _callback='' >");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(22457,user.getLanguage()));
				jsonObject.put("itemhtml", "<input name='mendperioddate' type='hidden' value=''   class='wuiDate' style='width:90px!important;'  _span='mendperioddate_span' _button='mendperioddate_btn' _callback='' >");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1399,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=7' browserurl='/systeminfo/BrowserMain.jsp?url=/CRM/data/CustomerBrowser.jsp?sqlwhere=where t1.type=2' name='maintaincompany' hasInput='true' isMustInput='1' isSingle='true' _callback=''></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(33016,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=23&cptstateid=1,2,3&cptsptcount=1' browserurl='/systeminfo/BrowserMain.jsp?url=/cpt/capital/CapitalBrowser.jsp?sqlwhere=where isdata=2&cptstateid=1,2,3&cptsptcount=1&from1=mend' name='capitalid' hasInput='true' isMustInput='2' isSingle='true' _callback='loadinfo'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15393,user.getLanguage()));
				jsonObject.put("itemhtml", "<span  id='curdept_span' name='curdept_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(904,user.getLanguage()));
				jsonObject.put("itemhtml", "<span  id='capitalspec_span' name='capitalspec_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(602,user.getLanguage()));
				jsonObject.put("itemhtml", "<span  id='status_span' name='status_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "6%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1393,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' style='width:50px!important;' name='cost' id='cost' size='10' maxlength='10' onkeyup=\"value=value.replace(/[^\\d\\.]/g,'')\" />");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "6%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(454,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' style='width:50px!importan;' name='remark' id='remark' />");
				jsonArray.put(jsonObject);
				
			}else if("CptBack".equalsIgnoreCase( detailType)){//资产归还
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "6%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1413,user.getLanguage()));
				jsonObject.put("itemhtml", "<input name='StockInDate' type='hidden' value='"+TimeUtil.getCurrentDateString()+"'   class='wuiDate' style='width:90px!important;'  _span='menddate_span' _button='menddate_btn' _callback='' >");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "15%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(1384,user.getLanguage())+SystemEnv.getHtmlLabelName(535,user.getLanguage()));
				jsonObject.put("itemhtml", "<span class='browser' completeurl='/data.jsp?type=23&cptstateid=2,3,4' browserurl='/systeminfo/BrowserMain.jsp?url=/cpt/capital/CapitalBrowser.jsp?sqlwhere=where isdata=2&cptstateid=2,3,4&from1=back' name='capitalid' hasInput='true' isMustInput='2' isSingle='true' _callback='loadinfo'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15393,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='curdept_span' name='curdept_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(904,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='capitalspec_span' name='capitalspec_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(602,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='status_span' name='status_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "8%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(524,user.getLanguage())+SystemEnv.getHtmlLabelName(1508,user.getLanguage()));
				jsonObject.put("itemhtml", "<span id='curresource_span' name='curresource_span'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(454,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='remark' id='remark' />");
				jsonArray.put(jsonObject);
				
			}else if("CptDefineField".equalsIgnoreCase( detailType)){//资产自定义字段
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelNames("15024,685",user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='itemDspName' id='itemDspName' size='15' maxlength='30' onblur=\"checkKey(this);checkinput_char_num(this.name);\" /><span class='mustinput'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(15456,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' name='itemFieldName' id='itemFieldName' onblur=\"checkKey(this);\" /><span class='mustinput'></span>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "50%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(686,user.getLanguage()));
				jsonObject.put("itemhtml", ""+cptFieldManager.getItemFieldTypeSelectForAddMainRow2(user));
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(18095,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='checkbox'  name='isopen' id='isopen' value='1' checked>");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(18019,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='checkbox'  onchange='' name='ismand' value='1' >");
				jsonArray.put(jsonObject);
				
				jsonObject=new JSONObject();
				jsonObject.put("width", "10%");
				jsonObject.put("colname", SystemEnv.getHtmlLabelName(88,user.getLanguage()));
				jsonObject.put("itemhtml", "<input type='text' size=10 maxlength=7 name='itemDspOrder' value=''  onKeyPress='ItemNum_KeyPress(this.name)' onchange='checknumber(this.name);checkDigit(this.name,15,2)' style='text-align:right;' />");
				jsonArray.put(jsonObject);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage());
		}
		
		return jsonArray;
	}
	
	

}