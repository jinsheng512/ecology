package greedc.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import ecustom.dao.HrmResourceDao;
import ecustom.servlets.BaseServlet;
import ecustom.servlets.Response;
import ecustom.util.CustomUtil;
import greedc.services.EASFileService;
import greedc.utils.DocUtil;
import greedc.vo.EASFileVo;

public class FileUploadServlet extends BaseServlet {

	// 上传配置
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

	public Object upload() throws IOException {
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request())) {
			return new Response(false, "Error: 表单必须包含 enctype=multipart/form-data");
		}

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		try {
			// 解析请求的内容提取文件数据
			List<FileItem> formItems = upload.parseRequest(request());
			if (formItems == null || formItems.isEmpty()) {
				return new Response(false, "Error: 没有检测到文件");
			}

			String zipTargetPath = "";
			Integer creatorId = null;
			Integer workflowId = null;
			EASFileVo fileVo = new EASFileVo();;
			// 迭代表单数据
			for (FileItem item : formItems) {
				
				// 处理不在表单中的字段
				if (!item.isFormField()) {
					String fileNameInZip = DocUtil.generateRandomFileName();
					zipTargetPath = DocUtil.generateRandomDir(null) + fileNameInZip + ".zip";
					File zipTargetFile = new File(zipTargetPath);
					FileOutputStream os = new FileOutputStream(zipTargetFile);
					ZipOutputStream zos = new ZipOutputStream(os);
					ZipEntry entry = new ZipEntry(fileNameInZip);
					zos.putNextEntry(entry);
					byte[] buffer = item.get();
					zos.write(buffer);
					zos.close();
					os.close();
					
					fileVo.setFilePath(zipTargetPath);
					fileVo.setFileSize((int)item.getSize());
				} else {
					String value = new String(item.get(), "UTF-8");
					if ("fileName".equals(item.getFieldName())) {
						fileVo.setName(value);
					} else if ("empCode".equals(item.getFieldName())) {
						HrmResourceDao dao = new HrmResourceDao();
						creatorId = dao.getIdByWorkcode(value);
					} else if ("workflowId".equals(item.getFieldName())) {
						workflowId = CustomUtil.getInt(value);
					} else if ("fileType".equals(item.getFieldName())) {
						fileVo.setType(value);
					}
				}
			}
			
			// 检查
			if (creatorId == null) {
				throw new Exception("根据 empCode 找不到人员ID");
			}
			
			List<EASFileVo> files = new ArrayList<EASFileVo>();
			files.add(fileVo);
			EASFileService fileService = new EASFileService();
			String docId = fileService.saveFiles(files, creatorId, workflowId);
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("docId", docId);
			data.put("filePath", zipTargetPath.replace("\\", "/"));
			return new Response(true, "上传成功！", data);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new Response(false, "Error: " + ex.getMessage());
		}
	}
}
