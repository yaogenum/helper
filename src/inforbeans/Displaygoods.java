package inforbeans;

import java.io.ByteArrayInputStream;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import actioninfor.Getdisplay;

import sql.mybatis.beans.Db_ac;
import sql.mybatis.beans.Db_order;
import sql.mybatis.dao.Db_acMap;
import sql.mybatis.dao.Db_orderMap;

public class Displaygoods {
	private Db_orderMap orderdao;
	private Db_order order;
	private Db_acMap acdao;
	private Db_ac ac;
	

	public Db_acMap getAcdao() {
		return acdao;
	}

	public void setAcdao(Db_acMap acdao) {
		this.acdao = acdao;
	}

	public Db_ac getAc() {
		return ac;
	}

	public void setAc(Db_ac ac) {
		this.ac = ac;
	}

	public Db_order getOrder() {
		return order;
	}

	public void setOrder(Db_order order) {
		this.order = order;
	}

	public Db_orderMap getOrderdao() {
		return orderdao;
	}

	public void setOrderdao(Db_orderMap orderdao) {
		this.orderdao = orderdao;
	}
	
	public List<HashMap> get_addrs(String goodsname){
		return orderdao.get_addrs(goodsname);
	}
	public HashMap<String,String> get_amount(String goodsname){
		return orderdao.get_amount(goodsname);
	}
	public boolean insert_order(int  goodsid,String username,String address,int Ac_id){
		boolean bool=false;
		
		HashMap hash=new HashMap();
		hash.put("goodsid", goodsid);
		hash.put("Ac_id", Ac_id);
		setAc(acdao.get_infors(hash));
		//System.out.println(ac.getAc_name());
		
		order.setAc_name(ac.getAc_name());
		order.setAc_id(ac.getAc_id());
		order.setAddress(address);
		order.setEffect("false");
		order.setGoodsname(ac.getInfor().getGoodsname());
		order.setStarttime(new SimpleDateFormat("y.MM.dd").format(Calendar.getInstance().getTime()));
		order.setUsername(username);
		order.setGoodsprice(ac.getAc_cheapcode());		
		orderdao.insertorder(order);
		
		HashMap map2=new HashMap();
		map2.put("goodsname",ac.getInfor().getGoodsname());
		map2.put("address", address);
		HashMap map3=(HashMap)orderdao.getallamount(map2);
		long acamount=Integer.parseInt(ac.getAc_amount());
		long nowamount=(Long) map3.get("amount");
		
		if(nowamount>acamount){
			orderdao.updateorder(map3);
		}
		
		return bool;
	}
	
	
	/*public static void main(String args[]){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationmybatis.xml");
		Displaygoods display=app.getBean("displaygood",Displaygoods.class);
		for(int i=0;i<=1;i++){
		display.insert_order(Integer.parseInt("4"), "sharefly", "西南大学",Integer.parseInt("2"));
		}
	}*/
	
}













