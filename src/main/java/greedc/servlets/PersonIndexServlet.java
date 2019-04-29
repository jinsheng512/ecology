package greedc.servlets;

import java.util.List;

import com.google.gson.Gson;

import ecustom.entities.HrmResource;
import ecustom.services.HrmResourceService;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import ecustom.util.GsonUtil;
import greedc.entities.PersonIndex;
import greedc.entities.PersonIndexDt1;
import greedc.entities.PersonIndexDt2;
import greedc.entities.UFIndexRoleDt1;
import greedc.services.*;
import weaver.hrm.User;

/**
 * 个人绩效考核页面请求处理类。
 * @author William
 */
public class PersonIndexServlet extends BaseServlet {
	
	public static final String MARK_TYPE = "selfMark,managerMark,viewMark";

	public Response getPersonIndex() throws Exception {
		int year = CustomUtil.getInt(getParameter("year", true));
		int season = CustomUtil.getInt(getParameter("season", true));
		String type = getParameter("type", true);
		if (!MARK_TYPE.contains(type)) {
			return new Response(false, "参数（type）无效");
		}
		
		if (getUser() == null || getUser().getUID() <= 0) {
			return new Response(false, "会话超时，请重新登录！");
		}
		int userId = 0;
		if ("selfMark".equals(type)) {
			UFJidpfkhrService service = new UFJidpfkhrService();
			if (!service.checkExistBeikhr(getUser().getUID())) {
				return new Response(false, "您的信息没有记录在被考核人列表中。");
			}

//			if (CustomUtil.getInt(getUser().getSeclevel()) > 23) {
//				return new Response(false, "您的安全级别大于 23 ，不需要在此页面提交个人绩效。");
//			}
			userId = getUser().getUID();
		} else {
			userId = CustomUtil.getInt(getParameter("userId", true));
			if (userId <= 0) {
				return new Response(false, "参数（userId）无效");
			}
		}

		User user = new User();
		user.setUid(userId);
		HrmResourceService hrmService = new HrmResourceService();
		HrmResource res = hrmService.getById(userId);
		user.setUserDepartment(res.getDepartmentId());
		user.setUserSubCompany1(res.getSubCompanyId());

		PersonIndexService service = new PersonIndexService();
		if (!service.exists(userId, year, season)) {
			service.createPersonIndex(getUser(), year, season);
		}
		PersonIndex index = service.getPersonIndex(userId, year, season);
		if (index == null) {
			throw new Exception("获取个人绩效数据失败！");
		}

		if ("managerMark".equals(type) && (index.getStatus() == null || index.getStatus() <= 0)) {
			type = "viewMark";
		} else if ("selfMark".equals(type) && (index.getStatus() != null && index.getStatus() > 0)) {
			type = "viewMark";
		}
		
		Response resp = new Response(true, "OK", index);
		resp.setAttr("imList", getIMList(index.getId()));
		resp.setAttr("baList", getBAList(index.getId(), user));
		resp.setAttr("type", type);
		return resp;
	}

	public Response listBumfzrAppr() throws Exception {
		if (getUser() == null || getUser().getUID() <= 0) {
			throw new Exception("会话超时，请重新登录！");
		}
		validateParamNull("year", "年份（year）参数不能为空！");
		validateParamNull("season", "季度（season）参数不能为空！");
		int year = CustomUtil.getInt(getParameter("year"));
		int season = CustomUtil.getInt(getParameter("season"));
		PersonIndexService service = new PersonIndexService();
		return new Response(true, "OK", service.listBumfzrAppr(getUser().getUID(), year, season));
	}
	
	/**
	 * 获取指定部门下面的考核人数据
	 * @return
	 * @throws Exception
	 */
	public Response listDeptAppr() throws Exception {
		if (getUser() == null || getUser().getUID() <= 0) {
			throw new Exception("会话超时，请重新登录！");
		}
		validateParamNull("year", "年份（year）参数不能为空！");
		validateParamNull("season", "季度（season）参数不能为空！");
		validateParamNull("deptId", "部门（deptId）参数不能为空！");
		int year = CustomUtil.getInt(getParameter("year"));
		int season = CustomUtil.getInt(getParameter("season"));
		int deptId = CustomUtil.getInt(getParameter("deptId"));
		PersonIndexService service = new PersonIndexService();
		return new Response(true, "OK", service.listDeptAppr(deptId, year, season));
	}
	
	public Response getNew() throws Exception {
		PersonIndexService service = new PersonIndexService();
		PersonIndex index = service.getNew(getUser());
		return new Response(true, "OK", index);
	}
	
	/**
	 * 部门负责人驳回。
	 * @return
	 * @throws Exception
	 */
	public Response reject() throws Exception {
		int id = Integer.parseInt(request().getParameter("id"));
		int year = Integer.parseInt(request().getParameter("year"));
		int season = Integer.parseInt(request().getParameter("season"));
		int userId = Integer.parseInt(request().getParameter("userId"));	// 被考核ID
		PersonIndexService service = new PersonIndexService();
		service.reject(id, year, season, userId);
		return new Response(true, "退回成功！");
	}
	
	public Response save() throws Exception {
		PersonIndex index = new Gson().fromJson(request().getParameter("index"), PersonIndex.class);
		List<PersonIndexDt1> imList = GsonUtil.toObjectList(request().getParameter("imRows"), PersonIndexDt1.class);
		List<PersonIndexDt2> baList = GsonUtil.toObjectList(request().getParameter("baRows"), PersonIndexDt2.class);
		String type = request().getParameter("type");
		List<Integer> deleteIds = GsonUtil.toObjectList(request().getParameter("deleteIds"), Integer.class);
		PersonIndexService service = new PersonIndexService();
		if ("selfMark".equals(type)) {	// 保存自评分
			service.saveSelfMark(index, imList, baList, deleteIds);
		}
		return new Response(true, "保存成功！");
	}
	
	public Response saveManagerMark() throws Exception {
		PersonIndex index = new Gson().fromJson(request().getParameter("index"), PersonIndex.class);
		List<PersonIndexDt1> imList = GsonUtil.toObjectList(request().getParameter("imRows"), PersonIndexDt1.class);
		List<PersonIndexDt2> baList = GsonUtil.toObjectList(request().getParameter("baRows"), PersonIndexDt2.class);
		String type = request().getParameter("type");
		PersonIndexService service = new PersonIndexService();
		if ("managerMark".equals(type)) {	// 保存自评分
			service.saveManagerMark(index, imList, baList);
		}
		return new Response(true, "保存评分成功！");
	}
	
	public Response submit() throws Exception {
		int id = CustomUtil.getInt(getParameter("id", true));
		int userId = CustomUtil.getInt(getParameter("userId", true));
		int year = CustomUtil.getInt(getParameter("year", true));
		int season = CustomUtil.getInt(getParameter("season", true));
		PersonIndexService service = new PersonIndexService();
		service.submintSelfMark(id, userId, year, season);
		return new Response(true, "提交成功！");
	}
	
	public Response listRoles() {
		UFIndexRoleDt1Service service = new UFIndexRoleDt1Service();
		List<UFIndexRoleDt1> list = service.list();
		return new Response(true, "OK", list);
	}
	
	/**
	 * 获取行为能力列表。
	 * @param mainId
	 * @return
	 */
	private List<PersonIndexDt2> getBAList(int mainId, User user) throws Exception {
		PersonIndexDt2Service service = new PersonIndexDt2Service();
		List<PersonIndexDt2> list = service.getList(mainId, user);
		return list;
	}
	
	/**
	 * 获取关键业务列表。
	 * @param mainId
	 * @return
	 */
	private List<PersonIndexDt1> getIMList(int mainId) {
		PersonIndexDt1Service service = new PersonIndexDt1Service();
		List<PersonIndexDt1> list = service.getList(mainId);
		return list;
	}
	
	private void validateParamNull(String name, String errorMsg) throws Exception {
		if (CustomUtil.isBlank(getParameter(name))) {
			throw new Exception(errorMsg);
		}
	}
}
