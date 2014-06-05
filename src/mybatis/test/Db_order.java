package mybatis.test;

public class Db_order {
	private int id;
	private int Ac_id;
	private String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAc_id() {
		return Ac_id;
	}
	public void setAc_id(int ac_id) {
		Ac_id = ac_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
