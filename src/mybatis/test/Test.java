package mybatis.test;


import java.io.IOException;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sql.mybatis.beans.Db_ac;
import sql.mybatis.dao.Db_acMap;
import sql.mybatis.dao.Db_orderMap;

public class Test {
	public static void main(String args[]) throws IOException,
			InterruptedException, SQLException {
		/*Reader reader = Resources.getResourceAsReader("mybatis.xml");
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);
		// BasicConfigurator.configure();
		SqlSession session = factory.openSession(ExecutorType.REUSE);

		
		Db_orderMap order=session.getMapper(Db_orderMap.class);
		HashMap hash=new HashMap();
		hash.put("goodsname","df");
	
		
		List<HashMap> list=order.getinterest(hash);
		
		Iterator iter=list.iterator();
		while(iter.hasNext()){
			HashMap single=(HashMap)iter.next();
			System.out.println(single.get("amount"));
			System.out.println(single.get("userinterest"));
		}
		
		
		
		System.out.println("all is ok");
		session.rollback();
		session.commit();
		session.close();*/
		
		
		

	}
}

/*
 * //Db_userinforDao dao=session.getMapper(Db_userinforDao.class); HashMap
 * map=new HashMap(); map.put("name", "yaoge"); map.put("pass", "yaoge22");
 * 
 * Db_userinforDao dao=session.getMapper(Db_userinforDao.class);
 * 
 * List<HashMap> list=dao.selectlist(); ListIterator<HashMap>
 * iter=list.listIterator(); while(iter.hasNext()){ HashMap hash=iter.next();
 * System.out.println(hash.get("username")); }
 * 
 * System.out.println("------------");
 * 
 * Db_userinfor infor=dao.selectmap(); System.out.println(infor.getUsername());
 * Db_userinfor userinfor=new Db_userinfor(); userinfor.setUsername("dsd");
 * userinfor.setUserpass("yaoge22"); userinfor.setUseraddress("www.");
 * userinfor.setUsergrades("50"); userinfor.setUserinterest("IT");
 * userinfor.setUserphone("122");
 * 
 * session.insert("mybatis.test.Db_userinforDao.insert",userinfor);
 * 
 * //dao.insert(userinfor); List<Db_userinfor> list=dao.selectall(map);
 * ListIterator<Db_userinfor> iter=list.listIterator();
 * 
 * while(iter.hasNext()){ Db_userinfor infor=iter.next();
 * System.out.println(infor.getUsername());
 * 
 * } *
 */
/*
Db_userinforDao dao = session.getMapper(Db_userinforDao.class);
HashMap map = new HashMap();
map.put("name", "yaoge22");
map.put("pass", "yaoge22");

List<Db_userinfor> query = dao.selectall(map);

ListIterator iter = query.listIterator();

while (iter.hasNext()) {
	Db_userinfor infor = (Db_userinfor) iter.next();
	System.out.println(infor.getUsername() + ":" + infor.getUserpass());

}

Db_userinfor db = new Db_userinfor();
db.setUseraddress("test");
db.setUsergrades("12");
db.setUserinterest("nima1");
db.setUsername("share");
db.setUserpass("fly");
db.setUserphone("123");

session.insert("mybatis.test.Db_userinforDao.insert", db);
session.delete("mybatis.test.Db_userinforDao.deleteobject", "share");

Configuration con = session.getConfiguration();

System.out.println();*/