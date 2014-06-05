package interceptors;

import java.util.HashMap;

import actioninfor.LoginAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation ac) throws Exception {
		// TODO Auto-generated method stub
		ac.addPreResultListener(new LoginLIstenner());
		HashMap<String, String> map;
		LoginAction login=(LoginAction) ac.getAction();
		String username=login.getUsername();
		ActionContext context=ActionContext.getContext();
		map=(HashMap<String, String>) context.getSession().get(username);
		
		
		if(map.isEmpty()){
			return "exception2";
		}
		else{			
			return ac.invoke();
		}
		
	}

}
