package ecustom.easyui.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNodeVo {

	private String id;
	private String text;
	private String state;
	private List<TreeNodeVo> children;
	private Map<String, Object> attributes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<TreeNodeVo> getChildren() {
		if (children == null) {
			children = new ArrayList<TreeNodeVo>();
		}
		return children;
	}
	public void setChildren(List<TreeNodeVo> children) {
		this.children = children;
	}
	public Map<String, Object> getAttributes() {
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
		}
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}
