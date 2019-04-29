package greedc.services;

import greedc.dao.UFJidpfkhrDao;

/**
 * 季度评分被考核建模服务类。
 * Created by William on 2017-6-26.
 */
public class UFJidpfkhrService {

	/**
	 * 判断被考核人在建模表中是否存在。
	 * @param beikhrId
	 * @return true-存在，false-不存在
	 */
	public boolean checkExistBeikhr(int beikhrId) {
		UFJidpfkhrDao dao = new UFJidpfkhrDao();
		return dao.checkExistBeikhr(beikhrId);
	}

	/**
	 * 根据部门标识获取被考核人人数。
	 * @param deptId	部门标识
	 * @return	被考核人人数
	 */
	public int count(int deptId) {
		UFJidpfkhrDao dao = new UFJidpfkhrDao();
		return dao.count(deptId);
	}
}
