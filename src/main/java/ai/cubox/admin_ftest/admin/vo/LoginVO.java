package ai.cubox.admin_ftest.admin.vo;

public class LoginVO {

	private String esntlId;
	private String userId;
	private String userNm;	
	private String userPw;
	private int pwUpdtDays;
	private String useYn;
	private String role;

	public String getEsntlId() {
		return esntlId;
	}
	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public int getPwUpdtDays() {
		return pwUpdtDays;
	}
	public void setPwUpdtDays(int pwUpdtDays) {
		this.pwUpdtDays = pwUpdtDays;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "";
	}

}
