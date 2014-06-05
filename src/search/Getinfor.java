package search;




import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import interf.Tranainfor;
/**
 * 中间类调用RAMwriter，PointSearcher
 * @since 1.6
 * @version 1.0
 * @author yaoge
 *
 */
public class Getinfor implements Tranainfor {

	private Ramwriter allfind;

	CopyOnWriteArrayList<String> list=null;
	
	public Ramwriter getAllfind() {
		return allfind;
	}

	public void setAllfind(Ramwriter allfind) {
		this.allfind = allfind;
	}
	
//添加文档
	public  boolean trans_infor(String type, String theme,
			String sender, String information)
			throws IOException, Exception {
		// TODO Auto-generated method stub
		// 处理发送信息,处理索引文件夹
			boolean bool = true;	
			// 存入操作
				synchronized (this) {
				
				allfind.add_doc(type,theme,sender,information);
			//	allfind.get_theme_history(type, theme);
			//	allfind.searcher_interest(type, theme, sender, information);
				//System.out.println("已经加入");
//			allfind.get_theme_content(type, theme);
			}
			
	
		return bool;

	}
	
	public  CopyOnWriteArrayList<String> get_theme_content(
			String type, String theme) throws IOException{
		list = new CopyOnWriteArrayList<String>();
		list=allfind.get_theme_content(type, theme);
		System.out.println(allfind.sum);
		return list;
		
	}
	
	public synchronized CopyOnWriteArrayList<String> get_theme_history(
			String type, String theme) throws IOException {
		try{
			list =new CopyOnWriteArrayList<String>();
			list=allfind.get_theme_history(type, theme);			
			
		}
		catch(Exception e){
			list=null;
		}
		
		return list;
	}
	
	public synchronized CopyOnWriteArrayList<String> searcher_interest(
			String type, String theme, String sender, String information)
			throws IOException, ParseException, java.text.ParseException,
			InvalidTokenOffsetsException {
		try{
			list = new CopyOnWriteArrayList<String>();
			list=allfind.searcher_interest(type, theme, sender, information);
			System.out.println("ok");
		}
		catch(Exception e){
			list=null;
		}
		
		
		return list;
	}
//测试用例
/*	public static void main(String args[]) throws IOException, Exception {
			
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		Getinfor getinfor=app.getBean("getinfor",Getinfor.class );
		
		
		for(int i=1;i<=105;i++){
				getinfor.trans_infor("kitty","hello", "yaoge22two", "share");	
			}
		 System.out.println("加入完成");
		CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<String>();
		list=getinfor.searcher_interest("kitty","hello","yaoge22two","share");
		String text=null;
		if(list!=null){
			Iterator iter=list.iterator();
			while(iter.hasNext()){
//				System.out.println(":"+iter.next());
				String a=(String) iter.next();
				System.out.println(a);
				text=a+text;
				
			}
		}
		
		 for(int i=1;i<=5;i++){
			getinfor.trans_infor("sansing","123", "yaoge", "hello");	
		}
		
		
		
	//	System.out.println(text);
	}*/
}
