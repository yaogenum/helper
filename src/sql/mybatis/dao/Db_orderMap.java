package sql.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import sql.mybatis.beans.Db_order;

public interface Db_orderMap {
	
	public List<HashMap> get_addrs(String goodsname);//查找7个地址
	public HashMap<String,String> get_amount(String goodsname);//查询总数量
	public boolean insertorder(Db_order order); //插入新订单记录
	public HashMap<String,String> getallamount(HashMap map);//检测是否达到数量
	public HashMap<String,String> updateorder(HashMap map);//修改订单值
	//处理展示商品图形分析
	public List<HashMap> getsales(HashMap map);//获取最近7次的销量，amount
	public List<HashMap> getinterest(HashMap map);//获取购买同款产品的兴趣爱好amount
	public List<HashMap> getchangeprice(HashMap map);//获取购买同类产品的相关信息
}
