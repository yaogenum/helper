package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import net.sf.json.JSONArray;

import org.aspectj.org.eclipse.jdt.core.dom.ThisExpression;
import org.springframework.stereotype.Component;


public class Hello {
	public String ok="143";
	public Hello(String a){
		ok=a;
		System.out.println("有参构造"+ok);
	}
	public Hello(){
		System.out.println("无参构造");
	}
	public static void main(String args[]) throws ParseException, ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		System.out.println("无参构造");
/*		As a=new As("a");
	
		
		int i=99;
	 	String time=new SimpleDateFormat("hhmmss").format(Calendar.getInstance().getTime());
		System.out.println(time);
		
		a.run();
		a.run();
		Hello hello=new Hello();
		hello.get_path();
		
		SimpleDateFormat simple=new SimpleDateFormat("Hmmss");
		
		String backuptime=simple.format(Calendar.getInstance().getTime());
		
		System.out.println(backuptime);
		int stime=Integer.parseInt(backuptime);
		System.out.println(stime);
		if(stime<240000&&stime>160000){				
			System.out.println("o");
		}
		else{
			
		}
		
		String s="it";
		System.out.println(":"+s.hashCode());
		Map map=new HashMap();
		map.put("wq", "wqw");
		System.out.println(map.get("wsq"));
		if(map.get("wsq")==null){
			System.out.println("ok");
		}*/
		
		List<String> list=new ArrayList<String>();
		list.add("123");
		list.add("dcsdd");
		Class hello=Class.forName("test.Hello");
		Hello shi=(Hello) hello.newInstance();
		Method[] method=hello.getMethods();
		method[1].invoke(shi);
		
		System.out.println(hello.getMethods()[1]);
		
		Constructor[] con=hello.getConstructors();
		System.out.println(con.length);
		Hello test=(Hello) con[0].newInstance("有残");
		
/*		ClassLoader loader=Thread.currentThread().getContextClassLoader();
		Class hellos=loader.loadClass("test.Hello");
		
		
		System.out.println(hello.getField("ok"));
*/	}
	public void get_path(){
		System.out.println(this.getClass().getResource("/").getPath());
	}
	
}
