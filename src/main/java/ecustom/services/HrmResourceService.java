package ecustom.services;

import org.apache.log4j.Logger;

import weaver.conn.RecordSetTrans;
import weaver.general.Util;
import ecustom.dao.HrmResourceDao;
import ecustom.entities.HrmResource;

/**
 * 人力资源服务类。
 * @author William
 */
public class HrmResourceService {

	private final static Logger log = Logger.getLogger(HrmResourceService.class);

	/**
	 * 根据LoginId修改用户密码。
	 * @param loginId	登录名
	 * @param newPassword	新密码
	 * @param oldMd5Password	旧密码（MD5格式）
	 * @return	是否修改成功
	 */
	public boolean changePassword(String loginId, String newPassword, String oldMd5Password) {
		String newMd5Password = Util.getEncrypt(newPassword);
		// 如果新密码与旧密码不同才需要修改密码
		if (oldMd5Password != null && !oldMd5Password.equals(newMd5Password)) {
			RecordSetTrans rst = new RecordSetTrans();
			try {
				rst.setAutoCommit(false);
				HrmResourceDao dao = new HrmResourceDao();
				dao.changePassword(loginId, newMd5Password, rst);
				rst.commit();
				log.debug("密码已更新[loginId=" + loginId + "]");
			} catch (Exception e) {
				log.error("更新密码失败[loginId=" + loginId + "]");
				rst.rollback();
				log.error(e.toString());
				return false;
			}

		}
		return true;
	}

	/**
	 * 根据登录名获取用户信息。
	 * @param loginId	登录名
	 * @return
	 */
	public HrmResource getByLoginId(String loginId) {
		HrmResourceDao dao = new HrmResourceDao();
		HrmResource item = dao.getByLoginId(loginId);
		return item;
	}

	/**
	 * 根据工号获取用户信息。
	 * @param workCode	工号
	 * @return
	 */
	public HrmResource getByWorkCode(String workCode) {
		HrmResourceDao dao = new HrmResourceDao();
		HrmResource item = dao.getByWorkCode(workCode);
		return item;
	}

	public String getWorkCodeById(int id) {
		HrmResourceDao dao = new HrmResourceDao();
		return dao.getWorkCodeById(id);
	}
	
	public HrmResource getById(int id) {
		HrmResourceDao dao = new HrmResourceDao();
		HrmResource item = dao.getById(id);
		return item;
	}
}
