package ecustom.services;

import java.util.List;

import ecustom.dao.GenerateDescriptionDao;
import ecustom.vo.GenerateDescriptionBaseVo;
import ecustom.vo.GenerateDescriptionFieldVo;

public class GenerateDescriptionService {

	public List<GenerateDescriptionFieldVo> getFields(int workflowId) {
		GenerateDescriptionDao dao = new GenerateDescriptionDao();
		List<GenerateDescriptionFieldVo> list = dao.getFields(workflowId);
		return list;
	}

	public GenerateDescriptionBaseVo getBase(int workflowId) {
		GenerateDescriptionDao dao = new GenerateDescriptionDao();
		GenerateDescriptionBaseVo vo = dao.getBase(workflowId);
		return vo;
	}
}
