package sql.mybatis.dao;

import java.util.HashMap;
import java.util.List;

import sql.mybatis.beans.Db_order;

public interface Db_orderMap {
	
	public List<HashMap> get_addrs(String goodsname);//����7����ַ
	public HashMap<String,String> get_amount(String goodsname);//��ѯ������
	public boolean insertorder(Db_order order); //�����¶�����¼
	public HashMap<String,String> getallamount(HashMap map);//����Ƿ�ﵽ����
	public HashMap<String,String> updateorder(HashMap map);//�޸Ķ���ֵ
	//����չʾ��Ʒͼ�η���
	public List<HashMap> getsales(HashMap map);//��ȡ���7�ε�������amount
	public List<HashMap> getinterest(HashMap map);//��ȡ����ͬ���Ʒ����Ȥ����amount
	public List<HashMap> getchangeprice(HashMap map);//��ȡ����ͬ���Ʒ�������Ϣ
}
