package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class Testlog {
	Logger log=Logger.getLogger(getClass());
	Testlog(){
		log.warn("errors");
		
	}
	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws IOException{

		BasicConfigurator.configure();
		Testlog test=new Testlog();
		
	}
}
