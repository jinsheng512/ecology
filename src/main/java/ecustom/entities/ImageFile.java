package ecustom.entities;

public class ImageFile {

	private Integer imageFileId;
	private String imageFileName;
	private String imageFileType;
	private String fileRealPath;
	private Integer fileSize;
	
	public Integer getImageFileId() {
		return imageFileId;
	}
	public void setImageFileId(Integer imageFileId) {
		this.imageFileId = imageFileId;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageFileType() {
		return imageFileType;
	}
	public void setImageFileType(String imageFileType) {
		this.imageFileType = imageFileType;
	}
	public String getFileRealPath() {
		return fileRealPath;
	}
	public void setFileRealPath(String fileRealPath) {
		this.fileRealPath = fileRealPath;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
}
