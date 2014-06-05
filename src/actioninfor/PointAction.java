package actioninfor;

import inforbeans.Searcherinfor;

import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.struts2.json.annotations.JSON;

import search.PointSearcher;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PointAction extends ActionSupport{
	private Searcherinfor interest;
	private CopyOnWriteArrayList<String> list;
	private String theme;
	private String type;
	private String sou;
	

	public String getSou() {
		return sou;
	}


	public void setSou(String sou) {
		this.sou = sou;
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


	@JSON(name="newname2")
	public CopyOnWriteArrayList<String> getList() {
		return list;
	}


	public void setList(CopyOnWriteArrayList<String> list) {
		this.list = list;
	}


	public Searcherinfor getInterest() {
		return interest;
	}


	public void setInterest(Searcherinfor interest) {
		this.interest = interest;
	}


	public String execute()throws Exception{
		list=new CopyOnWriteArrayList<String>();
		list=interest.getpoints(type,theme,sou);	

		return SUCCESS;	
	}
}
