package stomp.co.kr.domain;

public class StompUser {
	private String stomp_id;
	private String stomp_pw;
	private String stomp_nm;
	
	public StompUser() {
		super();
	}

	public StompUser(String stomp_id, String stomp_pw, String stomp_nm) {
		super();
		this.stomp_id = stomp_id;
		this.stomp_pw = stomp_pw;
		this.stomp_nm = stomp_nm;
	}

	public String getStomp_id() {
		return stomp_id;
	}

	public void setStomp_id(String stomp_id) {
		this.stomp_id = stomp_id;
	}

	public String getStomp_pw() {
		return stomp_pw;
	}

	public void setStomp_pw(String stomp_pw) {
		this.stomp_pw = stomp_pw;
	}

	public String getStomp_nm() {
		return stomp_nm;
	}

	public void setStomp_nm(String stomp_nm) {
		this.stomp_nm = stomp_nm;
	}

	@Override
	public String toString() {
		return "StompUser [stomp_id=" + stomp_id + ", stomp_pw=" + stomp_pw + ", stomp_nm="
				+ stomp_nm + "]";
	}
	
}
