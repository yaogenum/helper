package mybatis.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionTest extends JdbcDaoSupport{
	
	private TransactionTemplate template;

	public TransactionTemplate getTemplate() {
		return template;
	}

	public void setTemplate(TransactionTemplate template) {
		this.template = template;
	}
	
	public void purchase() {

		template.execute(new TransactionCallbackWithoutResult(){

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				// TODO Auto-generated method stub
				
				System.out.println(getJdbcTemplate().queryForRowSet("SELECT * from db_ac").toString());
				
				
			}
			
		}
		
		);
		
	}
	public static void main(String args[]){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationmybatis.xml");
		TransactionTest test=(TransactionTest) app.getBean("transactiondao");
		test.purchase();
	}
}
