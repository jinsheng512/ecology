
package weaver.cpt.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;

import org.bouncycastle.asn1.cmp.PKIBody;
import org.json.JSONException;
import org.json.JSONObject;

import weaver.cache.CacheBase;
import weaver.cache.CacheColumn;
import weaver.cache.CacheColumnType;
import weaver.cache.PKColumn;
import weaver.cpt.util.html.HtmlUtil;
import weaver.general.BaseBean;
import weaver.general.Util;

/**
 * 
 * @author crazyDream
 * @createdDate 2014-4-15
 * @function : Capital Customized Field Cache
 *
 */
public class CptFieldComInfo extends CacheBase
{
	protected static String TABLE_NAME = "CptDefineField";
    /** sql中的where信息，不要以where开始 */
    protected static String TABLE_WHERE = null;
    /** sql中的order by信息，不要以order by开始 */
    protected static String TABLE_ORDER = "groupid ASC, dsporder ASC";

    @PKColumn(type = CacheColumnType.NUMBER)
    protected static String PK_NAME = "id";
    
    @CacheColumn
    protected static int fieldname;
    @CacheColumn
    protected static int fielddbtype;
    @CacheColumn
    protected static int fieldhtmltype;
    @CacheColumn
    protected static int type;
    @CacheColumn
    protected static int imgwidth;
    @CacheColumn
    protected static int imgheight;
    @CacheColumn
    protected static int fieldlabel;
    @CacheColumn
    protected static int viewtype;
    @CacheColumn
    protected static int fromuser;
    @CacheColumn
    protected static int textheight;
    @CacheColumn
    protected static int dsporder;
    @CacheColumn
    protected static int isopen;
    @CacheColumn
    protected static int ismand;
    @CacheColumn
    protected static int isused;
    @CacheColumn
    protected static int issystem;
    @CacheColumn
    protected static int allowhide;
    @CacheColumn
    protected static int groupid;
    
	private DecimalFormat df=new DecimalFormat("00000.00");
	private static BaseBean baseBean=new BaseBean();
	
    /**
     * 指针返回第一行
     */
  public void setTofirstRow()
  {
	  super.setTofirstRow();
  }

    public ArrayList getField_ids() {
    	if(field_ids == null) {
    		field_ids = new ArrayList();
    		CptFieldComInfo rs = new CptFieldComInfo();
        	rs.setTofirstRow();
        	while(rs.next()) {
        		field_ids.add(rs.getFieldid());
        	}
    	}
        return field_ids;
    }

    public void setField_ids(ArrayList field_ids) {
        this.field_ids = field_ids;
    }

    private ArrayList field_ids = null;

    public ArrayList getField_names() {
    	if(field_names == null) {
    		field_names = new ArrayList();
    		CptFieldComInfo rs = new CptFieldComInfo();
        	rs.setTofirstRow();
        	while(rs.next()) {
        		field_names.add(rs.getFieldname());
        	}
    	}
        return field_names;
    }

    public void setField_names(ArrayList field_names) {
        this.field_names = field_names;
    }

    public ArrayList getField_htmltypes() {
    	if(field_htmltypes == null) {
    		field_htmltypes = new ArrayList();
    		CptFieldComInfo rs = new CptFieldComInfo();
        	rs.setTofirstRow();
        	while(rs.next()) {
        		field_htmltypes.add(rs.getFieldhtmltype());
        	}
    	}
        return field_htmltypes;
    }

    public void setField_htmltypes(ArrayList field_htmltypes) {
        this.field_htmltypes = field_htmltypes;
    }

    private ArrayList field_names = null;
    private ArrayList field_htmltypes = null;

    public ArrayList getField_types() {
    	if(field_types == null) {
    		field_types = new ArrayList();
    		CptFieldComInfo rs = new CptFieldComInfo();
        	rs.setTofirstRow();
        	while(rs.next()) {
        		field_types.add(rs.getFieldType());
        	}
    	}
        return field_types;
    }

    public void setField_types(ArrayList field_types) {
        this.field_types = field_types;
    }

    private ArrayList field_types = null;
    
    private TreeMap<String, JSONObject> usedFieldMap=null;
    private TreeMap<String, JSONObject> mandFieldMap=null;
    private TreeMap<String, JSONObject> openFieldMap=null;//自定义字段
    private TreeMap<String, JSONObject> openFieldMapAll=null;//所有字段
    private TreeMap<String, JSONObject> openSysFieldMap=null;//系统字段
    
    private TreeMap<String, TreeMap<String, JSONObject>> groupFieldMap=null;//以分组存字段
    
    
    private String mandfieldStr=",";
    
    /**
     * 得到字段数目
     * 
     * @return
     */
    public int getFieldNum()
    {
    	return super.size();
    }

    /**
     * 指向下一个数组元素
     * 
     * @return true:成功 false:失败
     */
    public boolean next()
    {
    	return super.next();
    }

    /**
     * 指向最后一个数组元素
     * 
     * @param s
     * @return true:成功 false:失败
     */
    public boolean next(String s)
    {
    	setTofirstRow();
		return false;

    }

    /**
     * 得到得到当前字段ID
     * 
     * @return 当前字段ID
     */
    public String getFieldid()
    {
    	return (String) super.getRowValue(PK_INDEX);
    }

    /**
     * 得到得到当前字段名称
     * 
     * @return 当前字段名称
     */
    public String getFieldname()
    {
    	return (String) super.getRowValue(fieldname);
    }

    /**
     * 根据字段ID得到字段名称
     * 
     * @param key
     *            字段ID
     * @return 字段名称
     */
    public String getFieldname(String key)
    {
    	return (String) super.getValue(fieldname, key);
    }

    /**
     * 根据字段ID得到字段数据库类型
     * 
     * @param key
     *            字段ID
     * @return 字段数据库类型
     */
    public String getFielddbtype(String key)
    {
    	return (String) super.getValue(fielddbtype, key);
    }
    public String getFielddbtype()
    {
    	return (String) super.getRowValue(fielddbtype);
    }

    /**
     * 根据字段ID得到字段页面类型
     * 
     * @param key
     *            字段ID
     * @return 字段页面类型
     */
    public String getFieldhtmltype(String key)
    {
    	return (String) super.getValue(fieldhtmltype, key);
    }
    public String getFieldhtmltype()
    {
    	return (String) super.getRowValue(fieldhtmltype);
    }

    /**
     * 根据字段ID得到字段数据库类型
     * 
     * @param key
     *            字段ID
     * @return 字段数据库类型
     */
    public String getFieldType(String key)
    {
    	return (String) super.getValue(type, key);
    }
    public String getFieldType()
    {
    	return (String) super.getRowValue(type);
    }


    /**
     * 获得图片宽度
     * @return
     */
    public int getImgWidth()
    {
    	return Util.getIntValue((String) super.getRowValue(imgwidth));
    }
    /**
     * 根据字段id获得图片宽度
     * @param key
     * @return
     */
    public int getImgWidth(String key){
    	return Util.getIntValue((String) super.getValue(imgwidth, key), 0);
    }

    /**
     * 获得图片高度
     * @return
     */
    public int getImgHeight()
    {
    	return Util.getIntValue((String) super.getRowValue(imgheight));
    }

    /**
     * 根据字段id获得图片高度
     * @param key
     * @return
     */
    public int getImgHeight(String key){
    	return Util.getIntValue((String) super.getValue(imgheight, key), 0);
    }

    
    public String getLabel(){
    	return (String) super.getRowValue(fieldlabel);
    }
    public String getLabel(String key){
    	return (String) super.getValue(fieldlabel, key);
    }
    public String getViewtype(){
    	return (String) super.getRowValue(viewtype);
    }
    public String getViewtype(String key){
    	return (String) super.getValue(viewtype, key);
    }
    public String getFromuser(){
    	return (String) super.getRowValue(fromuser);
    }
    public String getFromuser(String key){
    	return (String) super.getValue(fromuser, key);
    }
    public String getTextheight(){
    	return (String) super.getRowValue(textheight);
    }
    public String getTextheight(String key){
    	return (String) super.getValue(textheight, key);
    }
    public String getDsporder(){
    	return (String) super.getRowValue(dsporder);
    }
    public String getDsporder(String key){
    	return (String) super.getValue(dsporder, key);
    }
    public String getIsopen(){
    	return (String) super.getRowValue(isopen);
    }
    public String getIsopen(String key){
    	return (String) super.getValue(isopen, key);
    }
    public String getIsmand(){
    	return (String) super.getRowValue(ismand);
    }
    public String getIsmand(String key){
    	return (String) super.getValue(ismand, key);
    }
    public String getIsused(){
    	return (String) super.getRowValue(isused);
    }
    public String getIsused(String key){
    	return (String) super.getValue(isused, key);
    }
    
    
    public String getIssystem(){
    	return Util.getIntValue((String) super.getRowValue(issystem),0)+"";
    }
    public String getIssystem(String key){
    	return Util.getIntValue((String) super.getValue(issystem, key),0)+"";
    }
    public String getAllowhide(){
    	return (String) super.getRowValue(allowhide);
    }
    public String getAllowhide(String key){
    	return (String) super.getValue(allowhide, key);
    }
    public String getGroupid(){
    	return (String) super.getRowValue(groupid);
    }
    public String getGroupid(String key){
    	return (String) super.getValue(groupid, key);
    }
    public String getFieldkind(){
    	return "1";
    }
    
    
    public void removeFieldCache(){
    	super.removeCache();
    }
    
    /**
     * 已被使用过的字段
     * @return
     */
    public  TreeMap<String, JSONObject> getUsedFieldMap() {
    	if(usedFieldMap == null) {
    		usedFieldMap=new TreeMap<String, JSONObject>();
    		CptFieldComInfo rs = new CptFieldComInfo();
        	rs.setTofirstRow();
        	while(rs.next()) {
        		String isused=rs.getIsused();
        		if("1".equals(isused)){
        			JSONObject jsonObject=new JSONObject();
                	try {
                        jsonObject.put("id", rs.getFieldid());
                    	jsonObject.put("fieldname", rs.getFieldname());
                    	jsonObject.put("fielddbtype", rs.getFielddbtype());
                    	jsonObject.put("fieldhtmltype", rs.getFieldhtmltype());
                    	jsonObject.put("type", rs.getFieldType());
                    	jsonObject.put("imgwidth", rs.getImgWidth());
                    	jsonObject.put("imgheight", rs.getImgHeight());
                    	jsonObject.put("fieldlabel", rs.getLabel());
                    	jsonObject.put("viewtype", rs.getViewtype());
                    	jsonObject.put("fromuser", rs.getFromuser());
                    	jsonObject.put("textheight", rs.getTextheight());
                    	jsonObject.put("dsporder", rs.getDsporder());
                    	jsonObject.put("isopen", rs.getIsopen());
                    	jsonObject.put("ismand", rs.getIsmand());
                    	jsonObject.put("isused", rs.getIsused());
                    	jsonObject.put("issystem", rs.getIssystem());
                    	jsonObject.put("allowhide", rs.getAllowhide());
                    	jsonObject.put("groupid", rs.getGroupid());
                    	jsonObject.put("fieldkind", rs.getFieldkind());
                    	jsonObject.put("eleclazzname", HtmlUtil.getHtmlClassName(rs.getFieldhtmltype()));
                    	if("5".equals(rs.getFieldhtmltype())){
                    		jsonObject.put("seltype", "cpt");
                        }
					} catch (JSONException e) {
						writeLog(e);
					}
					usedFieldMap.put(df.format(Util.getDoubleValue(rs.getDsporder())), jsonObject);
                }
        	}
    	}
		return usedFieldMap;
	}
    /**
     * 已启用的字段(默认不包括系统字段)
     * @return
     */
    public  TreeMap<String, JSONObject> getOpenFieldMap() {
    	if(openFieldMap == null || openFieldMapAll == null || openSysFieldMap == null) {
    		openFieldMap=new TreeMap<String, JSONObject>();
    		openFieldMapAll=new TreeMap<String, JSONObject>();
    		openSysFieldMap=new TreeMap<String, JSONObject>();
    		CptFieldComInfo rs = new CptFieldComInfo();
        	rs.setTofirstRow();
        	while(rs.next()) {
        		String isopen=rs.getIsopen();
        		if("1".equals(isopen)){
        			JSONObject jsonObject=new JSONObject();
                	try {
                        jsonObject.put("id", rs.getFieldid());
                    	jsonObject.put("fieldname", rs.getFieldname());
                    	jsonObject.put("fielddbtype", rs.getFielddbtype());
                    	jsonObject.put("fieldhtmltype", rs.getFieldhtmltype());
                    	jsonObject.put("type", rs.getFieldType());
                    	jsonObject.put("imgwidth", rs.getImgWidth());
                    	jsonObject.put("imgheight", rs.getImgHeight());
                    	jsonObject.put("fieldlabel", rs.getLabel());
                    	jsonObject.put("viewtype", rs.getViewtype());
                    	jsonObject.put("fromuser", rs.getFromuser());
                    	jsonObject.put("textheight", rs.getTextheight());
                    	jsonObject.put("dsporder", rs.getDsporder());
                    	jsonObject.put("isopen", rs.getIsopen());
                    	jsonObject.put("ismand", rs.getIsmand());
                    	jsonObject.put("isused", rs.getIsused());
                    	jsonObject.put("issystem", rs.getIssystem());
                    	jsonObject.put("allowhide", rs.getAllowhide());
                    	jsonObject.put("groupid", rs.getGroupid());
                    	jsonObject.put("fieldkind", rs.getFieldkind());
                    	jsonObject.put("eleclazzname", HtmlUtil.getHtmlClassName(rs.getFieldhtmltype()));
                    	if("5".equals(rs.getFieldhtmltype())){
                    		jsonObject.put("seltype", "cpt");
                        }
					} catch (JSONException e) {
						writeLog(e);
					}
					openFieldMapAll.put(df.format(Util.getDoubleValue(rs.getDsporder())), jsonObject);
					if("1".equals(rs.getIssystem())) {
						openSysFieldMap.put(df.format(Util.getDoubleValue(rs.getDsporder())), jsonObject);
					}else {
						openFieldMap.put(df.format(Util.getDoubleValue(rs.getDsporder())), jsonObject);
					}
                }
        	}
    	}
		  baseBean.writeLog("openFieldMap="+openFieldMap);
    	return openFieldMap;
    }
    /**
     * 已启用的字段
     * type :all所有字段;sys:系统字段;其他:自定义字段
     * @return
     */
    public  TreeMap<String, JSONObject> getOpenFieldMap(String type) {
    	getOpenFieldMap();
    	if("all".equalsIgnoreCase(type)){
    		return openFieldMapAll;
    	}else if("sys".equalsIgnoreCase(type)){
    		return openSysFieldMap;
    	}else{
    		return openFieldMap;
    	}
    }
    /**
     * 分组的启用字段
     * @return
     */
    public  TreeMap<String, TreeMap<String, JSONObject>> getGroupFieldMap() {
    	if(groupFieldMap == null) {
    		groupFieldMap=new TreeMap<String, TreeMap<String, JSONObject>>();
    		CptFieldComInfo rs = new CptFieldComInfo();
        	rs.setTofirstRow();
        	while(rs.next()) {
        		String isopen=rs.getIsopen();
        		if("1".equals(isopen)){
        			JSONObject jsonObject=new JSONObject();
                	try {
                        jsonObject.put("id", rs.getFieldid());
                    	jsonObject.put("fieldname", rs.getFieldname());
                    	jsonObject.put("fielddbtype", rs.getFielddbtype());
                    	jsonObject.put("fieldhtmltype", rs.getFieldhtmltype());
                    	jsonObject.put("type", rs.getFieldType());
                    	jsonObject.put("imgwidth", rs.getImgWidth());
                    	jsonObject.put("imgheight", rs.getImgHeight());
                    	jsonObject.put("fieldlabel", rs.getLabel());
                    	jsonObject.put("viewtype", rs.getViewtype());
                    	jsonObject.put("fromuser", rs.getFromuser());
                    	jsonObject.put("textheight", rs.getTextheight());
                    	jsonObject.put("dsporder", rs.getDsporder());
                    	jsonObject.put("isopen", rs.getIsopen());
                    	jsonObject.put("ismand", rs.getIsmand());
                    	jsonObject.put("isused", rs.getIsused());
                    	jsonObject.put("issystem", rs.getIssystem());
                    	jsonObject.put("allowhide", rs.getAllowhide());
                    	jsonObject.put("groupid", rs.getGroupid());
                    	jsonObject.put("fieldkind", rs.getFieldkind());
                    	jsonObject.put("eleclazzname", HtmlUtil.getHtmlClassName(rs.getFieldhtmltype()));
                    	if("5".equals(rs.getFieldhtmltype())){
                    		jsonObject.put("seltype", "cpt");
                        }
					} catch (JSONException e) {
						writeLog(e);
					}
					//存分组启用字段
                	TreeMap<String, JSONObject> tmpMap=groupFieldMap.get(rs.getGroupid());
                	if(tmpMap!=null){
                		tmpMap.put(df.format(Util.getDoubleValue(rs.getDsporder())), jsonObject);
                	}else{
                		tmpMap=new TreeMap<String, JSONObject>();
                		tmpMap.put(df.format(Util.getDoubleValue(rs.getDsporder())), jsonObject);
                		groupFieldMap.put(rs.getGroupid(), tmpMap);
                	}
                }
        	}
    	}
    	return groupFieldMap;
    }
    
    /**
     * 必填的字段
     * @return
     */
    public  TreeMap<String, JSONObject> getMandFieldMap() {
    	if(mandFieldMap == null) {
    		mandFieldMap=new TreeMap<String, JSONObject>();
    		CptFieldComInfo rs = new CptFieldComInfo();
        	rs.setTofirstRow();
        	while(rs.next()) {
        		if(!"1".equals(rs.getIssystem()) && "1".equals(rs.getIsmand())){
        			JSONObject jsonObject=new JSONObject();
                	try {
                        jsonObject.put("id", rs.getFieldid());
                    	jsonObject.put("fieldname", rs.getFieldname());
                    	jsonObject.put("fielddbtype", rs.getFielddbtype());
                    	jsonObject.put("fieldhtmltype", rs.getFieldhtmltype());
                    	jsonObject.put("type", rs.getFieldType());
                    	jsonObject.put("imgwidth", rs.getImgWidth());
                    	jsonObject.put("imgheight", rs.getImgHeight());
                    	jsonObject.put("fieldlabel", rs.getLabel());
                    	jsonObject.put("viewtype", rs.getViewtype());
                    	jsonObject.put("fromuser", rs.getFromuser());
                    	jsonObject.put("textheight", rs.getTextheight());
                    	jsonObject.put("dsporder", rs.getDsporder());
                    	jsonObject.put("isopen", rs.getIsopen());
                    	jsonObject.put("ismand", rs.getIsmand());
                    	jsonObject.put("isused", rs.getIsused());
                    	jsonObject.put("issystem", rs.getIssystem());
                    	jsonObject.put("allowhide", rs.getAllowhide());
                    	jsonObject.put("groupid", rs.getGroupid());
                    	jsonObject.put("fieldkind", rs.getFieldkind());
                    	jsonObject.put("eleclazzname", HtmlUtil.getHtmlClassName(rs.getFieldhtmltype()));
                    	if("5".equals(rs.getFieldhtmltype())){
                    		jsonObject.put("seltype", "cpt");
                        }
					} catch (JSONException e) {
						writeLog(e);
					}
					mandFieldMap.put(df.format(Util.getDoubleValue(rs.getDsporder())), jsonObject);
                }
        	}
    	}
    	return mandFieldMap;
    }
    /**
     * 启用的必填字段(str)
     * @return
     */
    public  String getMandFieldStr() {
    	CptFieldComInfo rs = new CptFieldComInfo();
    	rs.setTofirstRow();
    	while(rs.next()) {
    		if("1".equals(rs.getIssystem()) && "1".equals(rs.getIsopen())&&"1".equals(rs.getIsmand())){
            	mandfieldStr+=rs.getFieldname()+",";
            }else if (!"1".equals(rs.getIssystem()) && "1".equals(rs.getIsopen())&&"1".equals(rs.getIsmand())) {
            	mandfieldStr+="field"+rs.getFieldid()+",";
			}
    	}
    	return mandfieldStr;
    }
    
}