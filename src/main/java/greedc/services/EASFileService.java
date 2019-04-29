package greedc.services;

import ecustom.dao.DocDetailDao;
import ecustom.dao.DocImageFileDao;
import ecustom.dao.HrmResourceDao;
import ecustom.dao.ImageFileDao;
import ecustom.entities.DocDetail;
import ecustom.entities.DocImageFile;
import ecustom.entities.HrmResource;
import ecustom.entities.ImageFile;
import ecustom.services.WorkflowBaseService;
import ecustom.util.CustomUtil;
import greedc.utils.Base64FileUtil;
import greedc.utils.DocUtil;
import greedc.vo.EASFileVo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import weaver.conn.RecordSetTrans;

public class EASFileService {
	
	public String saveFiles(List<EASFileVo> files, Integer creatorId, Integer workflowId, String contentType) throws IOException, SQLException {
		if ("base64".equals(contentType)) {
			storeBase64Files(files);
		}
		
		WorkflowBaseService flowService = new WorkflowBaseService();
		int[] cateIds = flowService.getDocCategoryById(workflowId);
		if (cateIds == null || cateIds.length == 0) {
			throw new RuntimeException("附件目录未设置");
		}
		
		if (files.isEmpty()) {
			return "";
		}
		
		HrmResourceDao resDao = new HrmResourceDao();
		HrmResource res = resDao.getById(creatorId);
		
		RecordSetTrans rst = new RecordSetTrans();
		String docIds = "";
		try {
			rst.setAutoCommit(false);
			for (EASFileVo vo : files) {
				String date = CustomUtil.dateFormat(new Date(), "yyyy-MM-dd");
				String time = CustomUtil.dateFormat(new Date(), "HH:mm:ss");
				ImageFile imageFile = new ImageFile();
				imageFile.setFileRealPath(vo.getFilePath());
				imageFile.setFileSize(vo.getFileSize());
				imageFile.setImageFileName(vo.getName() + "." + vo.getType());
				imageFile.setImageFileType("." + vo.getType());
				ImageFileDao imageFileDao = new ImageFileDao();
				imageFileDao.save(imageFile, rst);
				
				DocDetail docDetail = new DocDetail();
				docDetail.setDocCreaterId(creatorId);
				docDetail.setDocSubject(imageFile.getImageFileName());
				docDetail.setOwnerId(creatorId);
				docDetail.setDocLangurage(7);
				docDetail.setMainCategory(-1);
				docDetail.setSubCategory(-1);
				docDetail.setSecCategory(cateIds[cateIds.length - 1]);
				docDetail.setDocType(1);
				docDetail.setDocStatus(1);
				docDetail.setDocDepartmentId(res.getDepartmentId());
				docDetail.setDocCreateDate(date);
				docDetail.setDocCreateTime(time);
				docDetail.setDocLastModUserId(creatorId);
				docDetail.setDocLastModDate(date);
				docDetail.setDocLastModTime(time);
				docDetail.setAccessoryCount(1);
				docDetail.setDocExtendName("html");
				docDetail.setMainDoc(-1);
				docDetail.setDocValidUserId(0);
				DocDetailDao docDetailDao = new DocDetailDao();
				docDetailDao.save(docDetail, rst);
				
				DocImageFile docImageFile = new DocImageFile();
				docImageFile.setDocId(docDetail.getId());
				docImageFile.setImagefileId(imageFile.getImageFileId());
				docImageFile.setImageFileName(imageFile.getImageFileName());
				docImageFile.setImageFileDesc(imageFile.getImageFileName());
				docImageFile.setIsExtFile(1);
				docImageFile.setImageFileSize(vo.getFileSize());
				docImageFile.setDocFileType(2);
				DocImageFileDao docImageFileDao = new DocImageFileDao();
				docImageFileDao.save(docImageFile, rst);
				
				docIds += "," + docDetail.getId();
			}
			rst.commit();
		} catch (Exception e) {
			rst.rollback();
			e.printStackTrace();
			throw new SQLException("附件存储成功，保存附件信息至数据库失败。");
			
		}
		return docIds.length() > 0 ? docIds.substring(1) : "";
	}

	public String saveBase64Files(List<EASFileVo> files, Integer creatorId, Integer workflowId) throws IOException, SQLException {
		return saveFiles(files, creatorId, workflowId, "base64");
	}

	public String saveFiles(List<EASFileVo> files, Integer creatorId, Integer workflowId) throws IOException, SQLException {
		return saveFiles(files, creatorId, workflowId, "");
	}
	
	public void storeBase64Files(List<EASFileVo> files) throws IOException {
		String base64Code, fileNameInZip, zipTargetPath;
		for (EASFileVo vo : files) {
			base64Code = vo.getFile();
			fileNameInZip = DocUtil.generateRandomFileName();
			zipTargetPath = DocUtil.generateRandomDir(null) + fileNameInZip + ".zip";
			int fileSize = Base64FileUtil.base64CodeToZip(base64Code, fileNameInZip, zipTargetPath);
			vo.setFilePath(zipTargetPath);
			vo.setFileSize(fileSize);
			vo.setFile(null);
		}
	}
}
