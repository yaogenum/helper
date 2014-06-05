package inforbeans;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sql.mybatis.beans.Db_ac;
import sql.mybatis.beans.Db_goodsinfor;
import sql.mybatis.dao.Db_acMap;
import sql.mybatis.dao.Db_goodsinforMap;

public class Getgoodsinfor {
	private Db_goodsinforMap dao_goodsinfor;
	private Db_goodsinfor goodsinfor;
	private Db_ac db_ac;
	private Db_acMap dao_db_ac;
	
	public Db_ac getDb_ac() {
		return db_ac;
	}

	public void setDb_ac(Db_ac db_ac) {
		this.db_ac = db_ac;
	}

	public Db_acMap getDao_db_ac() {
		return dao_db_ac;
	}

	public void setDao_db_ac(Db_acMap dao_db_ac) {
		this.dao_db_ac = dao_db_ac;
	}

	public Db_goodsinfor getGoodsinfor() {
		return goodsinfor;
	}

	public void setGoodsinfor(Db_goodsinfor goodsinfor) {
		this.goodsinfor = goodsinfor;
	}

	public Db_goodsinforMap getDao_goodsinfor() {
		return dao_goodsinfor;
	}

	public void setDao_goodsinfor(Db_goodsinforMap dao_goodsinfor) {
		this.dao_goodsinfor = dao_goodsinfor;
	}
	public Db_goodsinfor get_goodsinfor(String goodsname){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("goodsname", goodsname);
		setGoodsinfor(dao_goodsinfor.getgoodsname(map));
		//System.out.println(goodsinfor.getGoodsname());
		return getGoodsinfor();
	}
	public Db_ac get_goods_ac(int goodsid){	
		//System.out.println(goodsid);
		setDb_ac(dao_db_ac.get_ac(goodsid));
		System.out.println(getDb_ac().getAc_id());
		return getDb_ac();
	}
	 
	
	/*public static void main(String args[]){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationmybatis.xml");
		Getgoodsinfor goods=(Getgoodsinfor) app.getBean("springgetgoodsinfor");
		int amount=goods.get_goodsinfor("ipad").getGoodsid();	
		goods.get_goods_ac(amount);
	}*/
	
}












