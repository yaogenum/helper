package analzyer;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import inforbeans.Analyzergoods;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
//代码大量使用原生态泛型不好，但是为了加强类型转换
public class AnalyzerAction extends ActionSupport {
	private String goodsname;
	private String amount;
	private Analyzergoods analyzer;
	private CopyOnWriteArrayList list;

	// 获取最近7次的销量，amount
	@JSON(name = "newname")
	public CopyOnWriteArrayList getList() {
		return list;
	}

	public void setList(CopyOnWriteArrayList list) {
		this.list = list;
	}

	public Analyzergoods getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzergoods analyzer) {
		this.analyzer = analyzer;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<HashMap> getsales(HashMap map) {
		return getAnalyzer().getsales(map);
	}

	// 获取购买同款产品的兴趣爱好amount
	public synchronized List<HashMap> getinterest(HashMap map) {
		return getAnalyzer().getinterest(map);
	}

	// 获取购买同类产品的价格信息
	public synchronized List<HashMap> getchangeprice(HashMap map) {
		return getAnalyzer().getchangeprice(map);
	}

	public synchronized HashMap getMap() {
		HashMap hash = new HashMap();
		hash.put("goodsname", getGoodsname());
		hash.put("amount", getAmount());
		return hash;
	}

	public synchronized CopyOnWriteArrayList getcopylist() {
		return new CopyOnWriteArrayList();
	}

	public synchronized void  translate(List<HashMap> listnew, String param1, String param2) {
		CopyOnWriteArrayList lists = getcopylist();
		Iterator iter = listnew.iterator();

		while (iter.hasNext()) {
			HashMap single = (HashMap) iter.next();
			if(param1.isEmpty()==false){
				lists.add(single.get(param1));				
			}
			if(param2.isEmpty()==false){
				lists.add(single.get(param2));				
			}
		}

		if (listnew.isEmpty() == false) {
			setList(lists);
		}
	}

	public String execute() throws Exception {

		List<HashMap> listnew = getsales(getMap());
		translate(listnew,"starttime","amount");

		return SUCCESS;
	}

	public synchronized String getprice() throws Exception {
		
		List<HashMap> listnew = getchangeprice(getMap());
		translate(listnew,"price","starttime");

		return SUCCESS;
	}
	
	public synchronized String interest() throws Exception{
		
		List<HashMap> listnew = getinterest(getMap());
		translate(listnew,"userinterest","amount");
		return SUCCESS;

	}

}
