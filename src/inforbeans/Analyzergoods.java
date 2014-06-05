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
	//��ȡ���7�ε�������amount
	public  List<HashMap> getsales(HashMap map){
		return orderdao.getsales(map);
	}
	//��ȡ����ͬ���Ʒ����Ȥ����amount
	public synchronized List<HashMap> getinterest(HashMap map){
		return orderdao.getinterest(map);
	}
	//��ȡ����ͬ���Ʒ�ļ۸���Ϣ
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
