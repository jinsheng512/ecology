package ecustom.entities;

public class DocImageFile {

	private Integer id;
	private Integer docId;
	private Integer imagefileId;
	private String imageFileName;
	private String imageFileDesc;
	private Integer docFileType;
	private Integer versionId;
	private Integer isExtFile;
	private Integer imageFileSize;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public Integer getImagefileId() {
		return imagefileId;
	}
	public void setImagefileId(Integer imagefileId) {
		this.imagefileId = imagefileId;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageFileDesc() {
		return imageFileDesc;
	}
	public void setImageFileDesc(String imageFileDesc) {
		this.imageFileDesc = imageFileDesc;
	}
	public Integer getDocFileType() {
		return docFileType;
	}
	public void setDocFileType(Integer docFileType) {
		this.docFileType = docFileType;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public Integer getIsExtFile() {
		return isExtFile;
	}
	public void setIsExtFile(Integer isExtFile) {
		this.isExtFile = isExtFile;
	}
	public Integer getImageFileSize() {
		return imageFileSize;
	}
	public void setImageFileSize(Integer imageFileSize) {
		this.imageFileSize = imageFileSize;
	}
}
