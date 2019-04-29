package greedc.entities;

public class UFIndexRoleDt1 {

	private Integer id;
	private Integer mainId;
	private Integer gescore;
	private Integer lescore;
	private Integer youlpd;
	private String youlpdName;
	private Integer rate;
	private Integer isPrior;
	private Integer glRole;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMainId() {
		return mainId;
	}
	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}
	public Integer getGescore() {
		return gescore;
	}
	public void setGescore(Integer gescore) {
		this.gescore = gescore;
	}
	public Integer getLescore() {
		return lescore;
	}
	public void setLescore(Integer lescore) {
		this.lescore = lescore;
	}
	public Integer getYoulpd() {
		return youlpd;
	}
	public void setYoulpd(Integer youlpd) {
		this.youlpd = youlpd;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public Integer getIsPrior() {
		return isPrior;
	}
	public void setIsPrior(Integer isPrior) {
		this.isPrior = isPrior;
	}

	public String getYoulpdName() {
		return youlpdName;
	}

	public void setYoulpdName(String youlpdName) {
		this.youlpdName = youlpdName;
	}

	public Integer getGlRole() {
		return glRole;
	}

	public void setGlRole(Integer glRole) {
		this.glRole = glRole;
	}
}
