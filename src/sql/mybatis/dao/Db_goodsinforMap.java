package sql.mybatis.dao;

import java.util.HashMap;

import sql.mybatis.beans.Db_goodsinfor;

public interface Db_goodsinforMap {
	public Db_goodsinfor getgoodsname(HashMap<String,String> map);
}
