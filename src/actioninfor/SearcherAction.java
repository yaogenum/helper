package actioninfor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.struts2.json.annotations.JSON;

import search.Getinfor;

import com.opensymphony.xwork2.ActionSupport;

public class SearcherAction extends ActionSupport{
	
	private Getinfor trans;
	private String type;
	private String theme;
	private String sender;
	private String information;
	public CopyOnWriteArrayList<String> list=null;
	String path=this.getClass().getResource("/").getPath();
	static File file=null;
	static FileWriter writer=null;
	
	public void getfile() throws IOException{
		file=new File(path+"/doc.txt");
		writer=new FileWriter(file,true);
	}
	
	public Getinfor getTrans() {
		return trans;
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


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getInformation() {
		return information;
	}


	public void setInformation(String information) {
		this.information = information;
	}

@JSON(name="newname")
	public CopyOnWriteArrayList<String> getList() {
		return list;
	}


	public void setList(CopyOnWriteArrayList<String> list) {
		this.list = list;
	}

//处理搜索结果返回
	public String execute() throws Exception{
		if(writer==null){
			getfile();
		}
		else{
			
		}
		list=new CopyOnWriteArrayList<String>();
		try{
			list=trans.searcher_interest(type, theme, sender, information);
			writer.write("searcher ok");
		}
		catch(Exception e){
			list.add("nocon");
			writer.write("searcher interest:"+e.toString());
			addActionMessage("errors");
		}		
		return SUCCESS;
	}
}
