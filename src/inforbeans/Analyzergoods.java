package inforbeans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sql.mybatis.dao.Db_orderMap;

public class Analyzergoods {
	private Db_orderMap orderdao;
	
	public Db_orderMap getOrderdao() {
		return orderdao;
	}

	public void setOrderdao(Db_orderMap orderdao) {
		this.orderdao = orderdao;
	}
	//获取最近7次的销量，amount
	public  List<HashMap> getsales(HashMap map){
		return orderdao.getsales(map);
	}
	//获取购买同款产品的兴趣爱好amount
	public synchronized List<HashMap> getinterest(HashMap map){
		return orderdao.getinterest(map);
	}
	//获取购买同类产品的价格信息
	public synchronized List<HashMap> getchangeprice(HashMap map){
		return orderdao.getchangeprice(map);
	}
/*	public static void main(String args[]){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationmybatis.xml");
		Analyzergoods goods = app.getBean("springanalyzer",Analyzergoods .class);
		HashMap<String,String> hash=new HashMap<String,String>();
		hash.put("goodsname","ipad");
		hash.put("amount", "7");
		
		@SuppressWarnings("rawtypes")
		List<HashMap> list=goods.getsales(hash);
		
		@SuppressWarnings("rawtypes")
		Iterator iter=list.iterator();
		while(iter.hasNext()){
			HashMap single=(HashMap)iter.next();
			System.out.println(single.get("amount"));
			System.out.println(single.get("starttime"));
		}
		
		if(list.isEmpty()){
			System.out.println("ok");
		}
		
	}*/
}
