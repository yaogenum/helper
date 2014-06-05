package mybatis.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest {
	public static void main(String args[]){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		Db_userinforDao dao=(Db_userinforDao) app.getBean("userDao");
		
		Db_userinfor db = new Db_userinfor();
		db.setUseraddress("test");
		db.setUsergrades("12");
		db.setUserinterest("nima1");
		db.setUsername("share12");
		db.setUserpass("fly");
		db.setUserphone("123");
		
		dao.insert(db);
		
		
		
		
		
		
	}
}
