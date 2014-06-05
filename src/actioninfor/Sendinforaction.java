package actioninfor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


import search.Getinfor;
import com.opensymphony.xwork2.ActionSupport;
public class Sendinforaction extends ActionSupport {

	private String sender;
	private String information;
	private String theme;
	private String type;
	private InputStream inputstream;
	private Getinfor trans;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Getinfor getTrans() {
		return trans;
	}

	public void setTrans(Getinfor trans) {
		this.trans = trans;
	}

	public InputStream getInputstream() {
		return inputstream;
	}

	public void setInputstream(InputStream inputstream) {
		this.inputstream = inputstream;
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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		// 发送信息接受
		boolean bool = trans.trans_infor(type, theme, information,sender );
		
		if (bool) {
			inputstream = new ByteArrayInputStream("true".getBytes("UTF-8"));
			return SUCCESS;
		} else {
			inputstream = new ByteArrayInputStream("false".getBytes("UTF-8"));
			return ERROR;
		}

	}

}
