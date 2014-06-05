package sql.mybatis.beans;

public class Db_order {
	private int id;
	private String username;
	private int Ac_id;
	private String Ac_name;
	private String goodsname;
	private String goodsprice;
	private String starttime;
	private String address;
	private String effect;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAc_id() {
		return Ac_id;
	}

	public void setAc_id(int ac_id) {
		Ac_id = ac_id;
	}

	public String getAc_name() {
		return Ac_name;
	}

	public void setAc_name(String ac_name) {
		Ac_name = ac_name;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodsprice() {
		return goodsprice;
	}

	public void setGoodsprice(String goodsprice) {
		this.goodsprice = goodsprice;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

}
