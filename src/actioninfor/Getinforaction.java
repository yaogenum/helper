package actioninfor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import search.Getinfor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Getinforaction extends ActionSupport{
	
	private String type;
	private String theme;
	private InputStream inputstream;
	private InputStream inputstreams;
	static  int sum=0;
	static int sumnew=0;
	static String nowtime;
	static int time;
	static Map<String,String> map=new HashMap<String,String>();
	static Map<String,String> mapsum=new HashMap<String,String>();
	private Getinfor trans;
	static File file=null;
	static FileWriter writer=null;
	String path=this.getClass().getResource("/").getPath();
	static CopyOnWriteArrayList<String> listall=null;
	static String theme_before=null;
	static String text_before=null;
	private CopyOnWriteArrayList<String> lists;
	
	
	

	public InputStream getInputstreams() {
		return inputstreams;
	}
	public void setInputstreams(InputStream inputstreams) {
		this.inputstreams = inputstreams;
	}
	public Getinfor getTrans() {
		return trans;
	}
	@JSON(name="newname")
	public CopyOnWriteArrayList<String> getLists() {
		return lists;
	}

	public void setLists(CopyOnWriteArrayList<String> lists) {
		this.lists = lists;
	}

	public void setTrans(Getinfor trans) {
		this.trans = trans;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTheme() {
		return theme;
	}


	public void setTheme(String theme) {
		this.theme = theme;
	}

	public InputStream getInputstream() {
		return inputstream;
	}


	public void setInputstream(InputStream inputstream) {
		this.inputstream = inputstream;
	}
	
	public void getfile() throws IOException{
		file=new File(path+"/doc.txt");
		writer=new FileWriter(file,true);
	}
	
	public void close() throws IOException{
		if(writer==null){
		}
		else{
			writer.close();
		}
	}

	public  synchronized String execute() throws Exception{
		
		//返回数组，聊天内容
		if(writer==null){
			getfile();
		}
		else{
			
		}
		String text="";
		
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		list=null;
		try{
			list = trans.get_theme_content(type,theme);
			if(list!=null){
				text="";
				Iterator iter=list.iterator();
				while(iter.hasNext()){
					text=text+"\n"+(String)iter.next();
				}	
			}
			else{
				text="nocon";
			}
			
		}
		catch(Exception e){
			text="error";
			writer.write("error"+e.toString()+"\n");
		}
		
		//writer.write("query ok");
		//inputstream = new ByteArrayInputStream("true".getBytes("UTF-8"));
		
			
		setInputstream(new ByteArrayInputStream(text.getBytes("UTF-8")));
		
		writer.write("errors"+text+"\n");
		
		
		writer.flush();
		return SUCCESS;	
	}
	
	//返回统计在线人数map记录上一次数字，mapsum实时下一次
	public synchronized String getuseramount() throws Exception{
		
		
		if((String) map.get(theme)==null){
			String zero=String.valueOf(0);
			map.put(theme,zero);
			mapsum.put(theme,zero);
		}
		else{
			sum=Integer.parseInt(map.get(theme));//上一次的值
			sumnew=Integer.parseInt( mapsum.get(theme));//正在计算
		}
		
		nowtime=new SimpleDateFormat("Hmmss").format(Calendar.getInstance().getTime());
		time=Integer.parseInt(nowtime);
		//统计10s内的请求，大概人数
		

		if(writer==null){
			getfile();
		}
		else{
			
		}
		
		if(time%100==0){
			
			writer.write("user number "+sum/10+"\n");
			close();
			getfile();
			writer.write(":"+sumnew+":");
			sum=sumnew/10;
			sumnew=0;
			
			
		}
		else{			
			sumnew++;
		}
		if(sum==0){
			inputstream=new ByteArrayInputStream(String.valueOf(sumnew).getBytes("UTF-8"));
		}
		else{
			inputstream=new ByteArrayInputStream(String.valueOf(sum).getBytes("UTF-8"));
		}
		map.put(theme, String.valueOf(sum));
		mapsum.put(theme,String.valueOf(sumnew));
		
		writer.flush();
		return ERROR;
	}
	
	//查看更多消息记录
	public synchronized String gethitory() throws Exception{

			if(writer==null){
				getfile();
			}
			else{	
			}
			listall = new CopyOnWriteArrayList<String>();
			listall=null;
			
			try{
				//listall=new CopyOnWriteArrayList<String>();
				
				
				
				lists=trans.get_theme_history(type,theme);
				//list.add("123");
				//list.add("12werjjfekjgf3");
				/*if(listall!=null){
					text_before="";
					Iterator iter=listall.iterator();
					while(iter.hasNext()){
						text_before=text_before+"\n"+(String)iter.next();
					}	
					theme_before=theme;
					writer.write("查询成功");
				}
				else{
					text_before="nocon";
				}*/
				writer.write("ok history\n");
			}
			catch(Exception e){
				
				text_before="error";
				writer.write("error"+e.toString()+"\n");
			}
			
			writer.flush();
		//inputstream=new ByteArrayInputStream(text_before.getBytes("UTF-8"));
		
		return "history";
		
	}
}
