package test;


public class As implements Runnable{
	
	String name=null;
	static int s=3;
	static int i=4;
	As(String name){
		this.name=name;
	}
	
	public void jian(){
		System.out.println(name+"计数"+i);
		i++;
	}
	public void run() {
		// TODO Auto-generated method stub
//		for(;s>=1;s--){
			System.out.println(name+"计数"+s);
			s--;
//		}
	}
	
}
