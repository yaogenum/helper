package test;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chinese {
	private Axe axe;
	private String name;
	private List<String> list;
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Axe getAxe() {
		return axe;
	}

	public void setAxe(Axe axe) {
		this.axe = axe;
	}
	public static void main(String args[]){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml","applicationmybatis.xml");
		
		
		Chinese c=app.getBean("chinese", Chinese.class);
//		System.out.println(c.getName());
		c.test();
	
		
	}
	public void test(){
		System.out.println(axe.tests()+axe.getPass()+getName()+":"+name+":");
		//getList();
		setName("shit");
		System.out.println("<<"+name+getName()+">z");
		Iterator iter=list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}
