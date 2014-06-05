package inforbeans;

import interceptors.DesUtil;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sql.mybatis.dao.Db_userinforDAO;

public class Validate_username {
	
	private Db_userinforDAO val;
	private DesUtil des;
	public final static  String key = "lings!@#$%";
	public Db_userinforDAO getVal() {
		return val;
	}
	public void setVal(Db_userinforDAO val) {
		this.val = val;
	}
	
	
	public DesUtil getDes() {
		return des;
	}
	public void setDes(DesUtil des) {
		this.des = des;
	}
	public HashMap<String,String> validate(String username,String userpass) throws Exception{
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("username", username);
	
		map.put("userpass", des.encrypt(userpass, key));
		
		HashMap<String,String> hash=new HashMap<String,String>();
		hash=getVal().validate(map);
		return hash;	
	}



public static void main(String args[]) throws Exception{
	
		
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml","applicationmybatis.xml");
		Validate_username db=(Validate_username) app.getBean("springvalidate");
		HashMap m=db.validate("yaoge22", "yaoge2232");
	
		HashMap man=new HashMap();
		HashMap mans=new HashMap();
		mans.put("name", "yaoge22");
		man.put("mans", mans);
		HashMap pmans=(HashMap) man.get("mans");
		System.out.println(pmans.get("name"));

		
		
	}

}