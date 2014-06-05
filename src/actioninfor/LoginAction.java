package actioninfor;

import java.io.File;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import inforbeans.Getgoodsinfor;
import inforbeans.Validate_username;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String userpassword;
	private String goodsname;
	private String useraddress;
	private Validate_username val_name;
	private Getgoodsinfor goodsinfor;
	
	

	

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public Getgoodsinfor getGoodsinfor() {
		return goodsinfor;
	}

	public void setGoodsinfor(Getgoodsinfor goodsinfor) {
		this.goodsinfor = goodsinfor;
	}

	public Validate_username getVal_name() {
		return val_name;
	}

	public void setVal_name(Validate_username val_name) {
		this.val_name = val_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
//��֤��½����������Ʒ�ͻ��Ϣ
	public String execute() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
	
	
		
		try {
			
			map = val_name.validate(username, userpassword);
			setUseraddress(map.get("useraddress"));
			
			
		} catch (Exception e) {
			addActionError(e.toString());
		}
		if (map != null) {
			ActionContext ac = ActionContext.getContext();
			
			
		/*	
			File file=new File(this.getClass().getResource("/").getPath()+"ipmap.txt");
			FileOutputStream outputStream=new FileOutputStream(file,true);
			SimpleDateFormat simple=new SimpleDateFormat("y/MM/dd h:mm:ss");
		
			
			outputStream.write((ServletActionContext.getRequest().getRemoteAddr()+"ʱ��:"+simple.format(Calendar.getInstance().getTime())+":\n").getBytes("UTF-8"));
			outputStream.close();*/
			
			ac.getSession().put(username, map);// session --->map��¼��¼��Ա��Ϣ
			
			// Ϊ��ʡ����Ʒ����,����ֱ�Ӽ�����Ʒ��Ϣ
			goodsinfor.get_goodsinfor("ipad");
			
			/*BasicConfigurator.configure();
			Logger log=Logger.getLogger(LoginAction.class);
			goodsinfor.getGoodsinfor().getGoodsname());*/
			
			
			if (goodsinfor.getGoodsinfor().getGoodsname()!=null) {
				int amount = goodsinfor.getGoodsinfor().getGoodsid();
				//addActionError(goodsinfor.getGoodsinfor().getGoodsname()+":"+amount);
				goodsinfor.get_goods_ac(amount);
				
			} else {
				goodsinfor.getDb_ac().setAc_name("���޴˻");
				
			}

			return SUCCESS;
		} else {
			clearFieldErrors();
			addFieldError("nottrue", "�û��������û���ƥ��ʧ�ܣ���" + username + "���µ�½");
			return INPUT;
		}

	}

	public void validateExecute() throws Exception {
		// TODO Auto-generated method stub
		if (username.isEmpty() || userpassword.isEmpty()) {
			clearFieldErrors();
			addFieldError("username", "�û��������û����볤�Ȳ���Ϊ��");
		} else {
			if (username.length() <= 5 || userpassword.length() <= 5) {
				clearFieldErrors();
				addFieldError("username", "�û��������û����볤�Ȳ��Ϸ�" + username.length()
						+ ":" + userpassword.length());
			}
		}
	}
}
