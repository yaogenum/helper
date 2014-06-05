package sql.mybatis.dao;

import java.util.HashMap;

import sql.mybatis.beans.Db_ac;

public interface Db_acMap {
	public Db_ac get_ac(int Ac_goodsid);
	public Db_ac get_infors(HashMap map);
}
