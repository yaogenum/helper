package actioninfor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import inforbeans.Displaygoods;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @version 1
 * @author Ҧ��
 * struts2 action ʵ�ֵ�����ģ�鹦��:�Ź�
 * 
*/
public class Getdisplay extends ActionSupport{
	private String goodsname;
	private Displaygoods displaygoods;
	private InputStream inputstream;
	@SuppressWarnings("rawtypes")
	private List<HashMap> list;
	private InputStream status;
	private String username;
	private String address;
	private String Ac_id;
	private String goodsid;
	
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getAc_id() {
		return Ac_id;
	}
	public void setAc_id(String ac_id) {
		Ac_id = ac_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public InputStream getStatus() {
		return status;
	}
	public void setStatus(InputStream status) {
		this.status = status;
	}
	@JSON(name="list")
	public List<HashMap> getList() {
		return list;
	}
	public void setList(List<HashMap> list) {
		this.list = list;
	}
	public void setInputstream(InputStream inputstream){
		this.inputstream=inputstream;
	}
	public InputStream getInputstream(){
		return this.inputstream;
	}
	
	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Displaygoods getDisplaygoods() {
		return displaygoods;
	}

	public void setDisplaygoods(Displaygoods displaygoods) {
		this.displaygoods = displaygoods;
	}
	
	/**
	 * ����ջ���ַ,���Ӷ���
	 * @return String
	 * @throws Exception
	 * 
	 */
	public String addaddress() throws Exception{
		try{
			displaygoods.insert_order(Integer.parseInt(goodsid), username, address, Integer.parseInt(Ac_id));
			setStatus(new ByteArrayInputStream(("����ɹ�=>������"+username+"����ַ:"+address).getBytes("UTF-8")));
		}
		catch(Exception e){
			setStatus(new ByteArrayInputStream(("����ʧ��"+goodsid+username+address+Ac_id+e.toString()).getBytes("UTF-8")));			
		}
		return SUCCESS;
	}
	/**��ȡǰ7����ַ����ֵ
	 * @param �޲�
	 * @return ��ͼString
	 */
	public String execute() throws Exception{
		setList(displaygoods.get_addrs(goodsname));
		return SUCCESS;
	}
	/**
	 * ��ȡ����ͼ��������ֵ
	 * @return ��ͼ String
	 * @throws Exception
	 */
	public String getamount() throws Exception{
		HashMap map=displaygoods.get_amount(goodsname);
		setInputstream(new ByteArrayInputStream(( map.get("amount").toString().getBytes("UTF-8"))));
		return SUCCESS;
	}
	/**
	 * ��֤��Ʒ���Ʋ���Ϊ��
	 */
	
	public void validateExecute() {
		
		if(goodsname.isEmpty()){
			addFieldError(goodsname,"��Ʒ���Ʋ���Ϊ��");
		}
	}
	/**
	 * ��֤��Ʒ���Ʋ���Ϊ��
	 */
	public void validateGetamount() {
		if(goodsname.isEmpty()){
			addFieldError(goodsname,"��Ʒ���Ʋ���Ϊ��");
		}
	}
	
}
