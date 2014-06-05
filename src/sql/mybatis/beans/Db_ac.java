package sql.mybatis.beans;

public class Db_ac {
	private int Ac_id;
	private int Ac_goodsid;
	private String  Ac_name;
	private String  Ac_amount;
	private String  Ac_cheapcode;
	private String  Ac_starttime;
	private String  Ac_endtime;
	private Db_goodsinfor infor;
	
	
	public Db_goodsinfor getInfor() {
		return infor;
	}
	public void setInfor(Db_goodsinfor infor) {
		this.infor = infor;
	}
	public int getAc_id() {
		return Ac_id;
	}
	public void setAc_id(int ac_id) {
		Ac_id = ac_id;
	}
	public int getAc_goodsid() {
		return Ac_goodsid;
	}
	public void setAc_goodsid(int ac_goodsid) {
		Ac_goodsid = ac_goodsid;
	}
	public String getAc_name() {
		return Ac_name;
	}
	public void setAc_name(String ac_name) {
		Ac_name = ac_name;
	}
	public String getAc_amount() {
		return Ac_amount;
	}
	public void setAc_amount(String ac_amount) {
		Ac_amount = ac_amount;
	}
	public String getAc_cheapcode() {
		return Ac_cheapcode;
	}
	public void setAc_cheapcode(String ac_cheapcode) {
		Ac_cheapcode = ac_cheapcode;
	}
	public String getAc_starttime() {
		return Ac_starttime;
	}
	public void setAc_starttime(String ac_starttime) {
		Ac_starttime = ac_starttime;
	}
	public String getAc_endtime() {
		return Ac_endtime;
	}
	public void setAc_endtime(String ac_endtime) {
		Ac_endtime = ac_endtime;
	}
	
}

