package base;

import java.io.Serializable;
import java.sql.Timestamp;

public class BIDR implements Serializable{
	private static final long serialVersionUID = 1L;
	//aaa鏈嶅姟鍣ㄥ悕绉�
	private String AAA_login_name;
	//鐢ㄦ埛鐨刬p
	private String login_ip;
	//涓婄嚎鏃堕棿
	private Timestamp login_date;
	//涓嬬嚎鏃堕棿
	private Timestamp logout_date;
	//nas鏈嶅姟鍣╥p
	private String NAS_ip;
	//鐢ㄦ埛涓婄綉鎸佺画鏃堕棿
	private Integer time_duration;
	
	public BIDR(){}

	public BIDR(String aAA_login_name, String login_ip, Timestamp login_date,
			Timestamp logout_date, String nAS_ip, Integer time_duration) {
		super();
		AAA_login_name = aAA_login_name;
		this.login_ip = login_ip;
		this.login_date = login_date;
		this.logout_date = logout_date;
		NAS_ip = nAS_ip;
		this.time_duration = time_duration;
	}

	public String getAAA_login_name() {
		return AAA_login_name;
	}

	public void setAAA_login_name(String aAA_login_name) {
		AAA_login_name = aAA_login_name;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public Timestamp getLogin_date() {
		return login_date;
	}

	public void setLogin_date(Timestamp login_date) {
		this.login_date = login_date;
	}

	public Timestamp getLogout_date() {
		return logout_date;
	}

	public void setLogout_date(Timestamp logout_date) {
		this.logout_date = logout_date;
	}

	public String getNAS_ip() {
		return NAS_ip;
	}

	public void setNAS_ip(String nAS_ip) {
		NAS_ip = nAS_ip;
	}

	public Integer getTime_duration() {
		return time_duration;
	}

	public void setTime_duration(Integer time_duration) {
		this.time_duration = time_duration;
	}

	@Override
	public String toString() {
		return "BIDR [AAA_login_name=" + AAA_login_name + ", login_ip="
				+ login_ip + ", login_date=" + login_date + ", logout_date="
				+ logout_date + ", NAS_ip=" + NAS_ip + ", time_duration="
				+ time_duration + "]";
	}
	
}
