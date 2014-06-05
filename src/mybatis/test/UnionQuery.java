package mybatis.test;

import java.util.List;

public class UnionQuery {
	private String username;
	private String userpass;
	
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	/*private List<Db_order> order;
	public List<Db_order> getOrder() {
		return order;
	}
	public void setOrder(List<Db_order> order) {
		this.order = order;
	}*/
	private Db_order order;
	
	public Db_order getOrder() {
		return order;
	}
	public void setOrder(Db_order order) {
		this.order = order;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
}
