package test;

import static org.junit.Assert.assertEquals;
import junit.framework.TestCase;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
public class TestUnit extends TestCase{
	@Test
	public void method(){
		assertEquals("\n this is a error \n","1234","123");
	}
	@After
	public void afterexecute(){
		System.out.println("after");
	}
	@Before
	public void beforeexecute(){
		System.out.println("before");
		
	}
	
}
