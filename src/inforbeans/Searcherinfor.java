package inforbeans;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import search.PointSearcher;
//处理提示2级bean
public class Searcherinfor {
	private PointSearcher point;
	static CopyOnWriteArrayList<String> list;
	
	public PointSearcher getPoint() {
		return point;
	}

	public void setPoint(PointSearcher point) {
		this.point = point;
	}
	public CopyOnWriteArrayList<String> getpoints(String type,String theme,String sou) throws IOException{
		list=new CopyOnWriteArrayList<String>();
		try{
			list=point.getpoints(type,theme,sou, 5);			
			System.out.println(list.toString());
		}
		catch(Exception e){
			System.out.println(e.toString()+e.getMessage());
		}
		return list;
	}
	/*public static void main(String args[]) throws IOException{
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
	
		Searcherinfor s=app.getBean("interestbean",Searcherinfor.class);
		s.getpoints("Digital","gy");
	}*/
}
